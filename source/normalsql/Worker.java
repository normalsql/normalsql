// Copyright 2010-2022 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql;

import normalsql.meta.*;
import normalsql.parse.NormalSQLLexer;
import normalsql.parse.NormalSQLParser;
import normalsql.parse.NormalSQLParser.ParseContext;
import normalsql.parse.NormalSQLParser.SubtermRefContext;
import normalsql.parse.NormalSQLParser.SubtermContext;
import normalsql.template.JavaHelper;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Worker
{
	Connection _conn;
	VelocityEngine _engine;
	Template _selectTemplate;
	Template _resultSetTemplate;
	JavaHelper _helper;

	public Worker( Connection conn )
	{
		_conn = conn;

		// TODO: This will have to be moved to common place, maybe into superclass
		_engine = new VelocityEngine();
		_engine.setProperty( RuntimeConstants.RESOURCE_LOADER, "classpath" );
		_engine.setProperty( "classpath.resource.loader.class", ClasspathResourceLoader.class.getName() );
		_engine.setProperty( "runtime.introspector.uberspect", "org.apache.velocity.util.introspection.UberspectPublicFields, org.apache.velocity.util.introspection.UberspectImpl" );
		_engine.init();

		_selectTemplate = _engine.getTemplate( "normalsql/template/Select.vm" );
		_resultSetTemplate = _engine.getTemplate( "normalsql/template/ResultSet.vm" );
		_helper = new JavaHelper();
	}

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
		ParseContext parse = parser.parse();
		SQLVisitor visitor = new SQLVisitor();

		visitor.visit( parse );
		work.root = visitor.root;
		work.predicates = visitor.predicates;

		for( var p : work.predicates )
		{
			switch( p )
			{
				case Comparison c:
				{
					// TODO add operator to method signature
					String column = getColumn( c.column );
					Property prop = _helper.create( c.value, column );
					work.statementProperties.add( prop );
					break;
				}
				case Between b:
				{
					switch( b.match )
					{
						case COL_VAL_VAL:
						{
							String column = getColumn( b.test );
							Property low = _helper.create( b.low, column, "low" );
							work.statementProperties.add( low );

							Property high = _helper.create( b.high, column, "high" );
							work.statementProperties.add( high );
							break;
						}
						case VAL_COL_COL:
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
				default: break;
			}
		}

		for( Property prop : work.statementProperties )
		{
			prop.context.setStartTokenText( "?" );
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
			prop.context.setStartTokenText( text );
		}

		work.printfSQL = tokens.getText();

		// TODO support unions, multiple statements, and such
		work.resultSetProperties = matchItemsToColumns( work.root.get(0).items, work.columns );

		merge( work );

		// TODO "run trip" test, verify new PreparedStatement.toString() is same as original source

		System.out.println( work.sourceFile + " processed" );
	}

	public static String getColumn( SubtermContext b )
	{
		return ( (SubtermRefContext) b ).ref().column.getTrimmedText();
	}

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
			String name = column.name;
			String label = column.label;
			// TODO also match to catalog, schema, table
			// TODO resolve best match
			for( Item item : items )
			{
				if( name.equalsIgnoreCase( item.name ) && label.equalsIgnoreCase( item.alias ) )
				{
					label = item.alias;
					prop.source = item.context.getText();
					break;
				}
				else if( name.equalsIgnoreCase( item.name ) && label.equalsIgnoreCase( item.name ) )
				{
					label = item.name;
					prop.source = item.context.getText();
					break;
				}
			}

			prop.variable = _helper.toVariableCase( label );
			prop.getter = "get" + _helper.toMethodCase( label );
			prop.setter = "set" + _helper.toMethodCase( label );
			prop.className = column.className;
			prop.classShortName = column.className.substring( column.className.lastIndexOf( "." ) + 1 );
			prop.sqlType = column.type;
			prop.initial = _helper.getInitializerValue( column.type );

			properties.add( prop );
		}

		return properties;
	}

	public void merge( Work work ) throws IOException
	{
		HashMap<String, Object> childMap = work.asMap();
		// TODO change to 'now', use same Date for all artifacts
		childMap.put( "date", new Date() );

		VelocityContext vc = new VelocityContext( childMap );

		generate( _selectTemplate, vc, work.targetDir, work.statementClassName );
		generate( _resultSetTemplate, vc, work.targetDir, work.resultSetClassName );
	}

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
