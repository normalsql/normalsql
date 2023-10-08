// Copyright 2010-2022 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql;

import normalsql.meta.*;
import normalsql.parse.NormalSQLLexer;
import normalsql.parse.NormalSQLParser;
import normalsql.parse.NormalSQLParser.*;
import normalsql.template.JavaHelper;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.WritableToken;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.apache.velocity.tools.generic.EscapeTool;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * <p>Worker class.</p>
 *
 * @author jasonosgood
 * @version $Id: $Id
 */
public class Worker
{
	Connection _conn;
	VelocityEngine _engine;
	Template _selectTemplate;
	Template _resultSetTemplate;
	JavaHelper _helper;

	/**
	 * <p>Constructor for Worker.</p>
	 *
	 * @param conn a Connection object
	 */
	public Worker( Connection conn )
	{
		_conn = conn;

		_engine = new VelocityEngine();
		_engine.setProperty( RuntimeConstants.RESOURCE_LOADER, "classpath" );
		// TODO verify this works with jar
		_engine.setProperty( "classpath.resource.loader.class", ClasspathResourceLoader.class.getName() );
//		_engine.setProperty( "class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader" );
		_engine.setProperty( "runtime.introspector.uberspect", "org.apache.velocity.util.introspection.UberspectPublicFields, org.apache.velocity.util.introspection.UberspectImpl" );
		_engine.init();

		_selectTemplate = _engine.getTemplate( "normalsql/template/Select.vm" );
		_resultSetTemplate = _engine.getTemplate( "normalsql/template/ResultSet.vm" );
		_helper = new JavaHelper();
	}

	// TODO: use ANTLR's token stream rewriter instead
	public void setStartTokenText( ParserRuleContext context, String text )
	{
		// Replace text of first "visible" (non whitespace) token, then exit
		WritableToken start = (WritableToken) context.getStart();
		start.setText( text );
	}

	/**
	 * <p>process.</p>
	 *
	 * @param work a {@link normalsql.Work} object
	 * @throws java.io.IOException if any.
	 * @throws SQLException if any.
	 */
	public void process( Work work )
		throws IOException, SQLException
	{
		work.originalSQL = new String( Files.readAllBytes( work.sourceFile ) );

		// TODO attempt running statement before parsing

		// TODO reuse one instance of ANTLR?
		CharStream chars = CharStreams.fromString( work.originalSQL );
		NormalSQLLexer lexer = new NormalSQLLexer( chars );
		CommonTokenStream tokens = new CommonTokenStream( lexer );
		NormalSQLParser parser = new NormalSQLParser( tokens );
		ScriptContext script = parser.script();
		NormalSQLVisitor visitor = new NormalSQLVisitor();
		visitor.parser = parser;
		visitor.tokens = tokens;

		visitor.visit( script );
		work.root = visitor.root;
		work.predicates = visitor.predicates;

		for( var p : work.predicates )
		{
			switch( p.getClass().getSimpleName() )
			{
				case "Between":
				{
					Between b = (Between) p;
					switch( b.pattern )
					{
						case ColumnLiteralLiteral:
						{
							String name = getColumn( b.test );
							Property low = _helper.create( b.low, name, "low" );
							work.statementProperties.add( low );

							Property high = _helper.create( b.high, name, "high" );
							work.statementProperties.add( high );
							break;
						}
						case LiteralColumnColumn:
						{
							String columnLow = getColumn( b.low );
							String columnHigh = getColumn( b.high );
							Property high = _helper.create( b.test, "between", columnLow, "and", columnHigh );
							work.statementProperties.add( high );
							break;
						}
						default:
							break;
					}
					break;
				}
				case "Comparison":
				{
					Comparison c = (Comparison) p;
					// TODO add operator to method signature
					String column = getColumn( c.column );
					Property prop = _helper.create( c.literal, column );
					work.statementProperties.add( prop );
					break;
				}
				case "Match":
				{
					Match m = (Match) p;
					String column = getColumn( m.column );
					Property prop = _helper.create( m.literal, column );
					work.statementProperties.add( prop );
					break;
				}
				case "IN":
				{
					IN in = (IN) p;
					String column = getColumn( in.column );
					for( int nth = 0; nth < in.literals.size(); nth++ )
					{
						SubtermLiteralContext l = in.literals.get( nth );
						String temp = column + "_" + (nth + 1);
						Property prop = _helper.create( l, temp);
						work.statementProperties.add( prop );
					}
					break;
				}
				default: break;
			}
		}

		for( Property prop : work.statementProperties )
		{
			setStartTokenText( prop.context, "?" );
		}

		work.preparedSQL = tokens.getText();

		processPreparedStatement( _conn, work );

		for( int nth = 0; nth < work.params.size(); nth++ )
		{
			Property prop = work.statementProperties.get( nth );
			Param p = work.params.get( nth );
			prop.param = p;
			prop.nth = p.nth;
			prop.className = p.className;
			prop.classShortName = p.className.substring( p.className.lastIndexOf( "." ) + 1 );
			prop.asCode = _helper.convertToCode( p.type, prop.trimmed );
		}

		for( Property prop : work.statementProperties )
		{
			String text = _helper.toPrintfConverter( prop.param.type );
			setStartTokenText( prop.context, text );
		}

		work.printfSQL = tokens.getText();

//		// TODO foreach statement this, to support unions, multiple statements, and such
//		work.resultSetProperties = matchItemsToColumns( work.root.get(0).items, work.columns );
//
//		// TODO match values' literals to columns
//
//		merge( work );

		// TODO "roundtrip" test, verify new PreparedStatement.toString() is same as original source

		System.out.println( work.sourceFile + " processed" );
	}

	/**
	 * <p>returns name of column</p>
	 *
	 * @param b a {@link normalsql.parse.NormalSQLParser.SubtermContext} object
	 * @return a {@link java.lang.String} object
	 */
	public String getColumn( SubtermContext b )
	{
		RuleContext column = ( (SubtermColumnContext) b ).column();
		return _helper.getTrimmedText( column );
	}

	/**
	 * <p>processPreparedStatement.</p>
	 *
	 * @param conn a Connection object
	 * @param work a {@link normalsql.Work} object
	 * @throws SQLException if any.
	 */
	public void processPreparedStatement( Connection conn, Work work )
		throws SQLException
	{
		PreparedStatement ps = conn.prepareStatement( work.preparedSQL );

		ParameterMetaData pmd = ps.getParameterMetaData();
		for( int nth = 1; nth <= pmd.getParameterCount(); nth++ )
		{
			Param param = new Param();
			param.nth = nth;
			param.type = pmd.getParameterType( nth );
			param.typeName = pmd.getParameterTypeName( nth );
			param.isNullable = pmd.isNullable( nth );
			param.isSigned = pmd.isSigned( nth );
			param.scaled = pmd.getScale( nth );
			param.precision = pmd.getPrecision( nth );
			param.mode = pmd.getParameterMode( nth );
			param.className = pmd.getParameterClassName( nth );
			work.params.add( param );
		}

		ResultSetMetaData md = ps.getMetaData();
		if( md != null )
		{
			// TODO: dedupe resultset columns
			for( int nth = 1; nth <= md.getColumnCount(); nth++ )
			{
				Column column = new Column();
				column.nth = nth;
				column.catalog = md.getCatalogName( nth );
				column.schema = md.getSchemaName( nth );
				column.table = md.getTableName( nth );
				column.name = md.getColumnName( nth );
				column.label = md.getColumnLabel( nth );
				column.type = md.getColumnType( nth );
				column.typeName = md.getColumnTypeName( nth );
				column.isNullable = md.isNullable( nth );
				column.className = md.getColumnClassName( nth );
				work.columns.add( column );
			}
		}
	}

	/**
	 * Match query 'items' from original SQL to ResultSet's result columns. If original SQL
	 * used wildcard '*', there will be more columns than items. Assumes items and
	 * result columns appear in same order. There can be more result columns than items.
	 *
	 */
	List<Property> matchItemsToColumns( List<Item> items, List<Column> columns )
	{
		ArrayList<Property> properties = new ArrayList<>();

		for( Column column : columns )
		{
			// TODO reference to parent Item
			Property prop = new Property();
			prop.nth = column.nth;
			prop.column = column;
			String bean = column.label;
			// TODO also match to catalog, schema, table
			// TODO resolve best match
			for( Item item : items )
			{
				if( column.name.equalsIgnoreCase( item.name ) )
				{
					if( column.label.equalsIgnoreCase( item.alias ) )
					{
						bean = item.alias;
						break;
					}
					else if( column.label.equalsIgnoreCase( item.name ) )
					{
						bean = item.name;
						break;
					}
				}
			}

			prop.original = bean;
			prop.variable = _helper.toVariableCase( bean );
			prop.getter = "get" + _helper.toMethodCase( bean );
			prop.setter = "set" + _helper.toMethodCase( bean );
			prop.className = column.className;
			prop.classShortName = column.className.substring( column.className.lastIndexOf( "." ) + 1 );
			prop.sqlType = column.type;
			prop.initial = _helper.getInitializerValue( column.type );

			properties.add( prop );
		}

		return properties;
	}

	/**
	 * <p>merge.</p>
	 *
	 * @param work a {@link normalsql.Work} object
	 * @throws java.io.IOException if any.
	 */
	public void merge( Work work ) throws IOException
	{
		HashMap<String, Object> childMap = work.asMap();
		childMap.put( "esc", new EscapeTool() );
		// TODO change to 'now', use same Date for all artifacts
		childMap.put( "date", new Date() );

		VelocityContext vc = new VelocityContext( childMap );

		generate( _selectTemplate, vc, work.targetDir, work.statementClassName );
		generate( _resultSetTemplate, vc, work.targetDir, work.resultSetClassName );
	}

	/**
	 * <p>generate.</p>
	 *
	 * @param template a {@link org.apache.velocity.Template} object
	 * @param vc a {@link org.apache.velocity.VelocityContext} object
	 * @param targetDir a {@link java.nio.file.Path} object
	 * @param name a {@link java.lang.String} object
	 * @throws java.io.IOException if any.
	 */
	public void generate( Template template, VelocityContext vc, Path targetDir, String name )
		throws IOException
	{
		// TODO property for generated file name (incl extension)
		Path targetFile = targetDir.resolve( name + ".java" );
		try (
			FileWriter writer = new FileWriter( targetFile.toFile() )
		)
		{
			template.merge( vc, writer );
			writer.flush();
		}
	}
}
