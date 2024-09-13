// Copyright 2010-2023 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql;

import normalsql.template.Column;
import normalsql.template.Param;
import normalsql.parse.*;
import normalsql.parse.NormalSQLLexer;
import normalsql.parse.NormalSQLParser;
import normalsql.parse.NormalSQLParser.*;
import normalsql.parse.Statement;
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
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

public class
	Worker
{
	String _now;
	Connection _conn;
	VelocityEngine _engine;
	Template _selectTemplate;
	Template _insertTemplate;
	Template _deleteTemplate;
	Template _resultSetTemplate;
	JavaHelper _helper;

	public Worker( Connection conn )
	{
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm:ss" );
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm:ss'Z'" ).withZone( ZoneOffset.UTC );
		_now = formatter.format( Instant.now() );

		_conn = conn;

		_engine = new VelocityEngine();
		_engine.setProperty( RuntimeConstants.RESOURCE_LOADER, "classpath" );
		// TODO verify this works with jar
		_engine.setProperty( "classpath.resource.loader.class", ClasspathResourceLoader.class.getName() );
//		_engine.setProperty( "class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader" );
		_engine.setProperty( "runtime.introspector.uberspect", "org.apache.velocity.util.introspection.UberspectPublicFields, org.apache.velocity.util.introspection.UberspectImpl" );
		_engine.init();

		_selectTemplate = _engine.getTemplate( "normalsql/template/Select.vm" );
		_insertTemplate = _engine.getTemplate( "normalsql/template/Insert.vm" );
		_deleteTemplate = _engine.getTemplate( "normalsql/template/Delete.vm" );
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

	// TODO "roundtrip" test, verify new PreparedStatement.toString() is same as original source

	public void process( Work work )
		throws IOException, SQLException
	{
		work.originalSQL = new String( Files.readAllBytes( work.sourceFile ));

		// TODO attempt running statement before parsing

		CharStream chars = CharStreams.fromString( work.originalSQL );
		NormalSQLLexer lexer = new NormalSQLLexer( chars );
		CommonTokenStream tokens = new CommonTokenStream( lexer );
		NormalSQLParser parser = new NormalSQLParser( tokens );
		ScriptContext script = parser.script();

		KnockoutVisitor visitor = new KnockoutVisitor();
		visitor.parser = parser;
		visitor.tokens = tokens;
		visitor.visit( script );
//		work.visitor = visitor;
		if( work.root == null || work.root.isEmpty() )
		{
			System.out.println( "file contains no statements: " + work.sourceFile );
			return;
		}

		work.root = visitor.root;


//		work.knockouts = visitor.knockouts;

		for( var p : visitor.knockouts )
		{
			switch( p )
			{
				case BETWEEN b ->
				{
					switch( b.pattern )
					{
						case ColumnLiteralLiteral ->
						{
							String name = getColumn( b.test );
							Param low = _helper.create( b.low, name, "low" );
							work.params.add( low );

							Param high = _helper.create( b.high, name, "high" );
							work.params.add( high );
						}

						case LiteralColumnColumn ->
						{
							String columnLow = getColumn( b.low );
							String columnHigh = getColumn( b.high );
							Param high = _helper.create( b.test, "between", columnLow, "and", columnHigh );
							work.params.add( high );
						}
					}
				}
				case Comparison c ->
				{
					// TODO add operator to method signature
					String column = getColumn( c.column );
					Param prop = _helper.create( c.literal, column );
					work.params.add( prop );
				}
				case LIKE m ->
				{
					String column = getColumn( m.column );
					Param prop = _helper.create( m.literal, column );
					work.params.add( prop );
				}

				// TODO ANY predicate

				case IN in ->
				{
					String column = getColumn( in.column );
					for( int nth = 0; nth < in.literals.size(); nth++ )
					{
						SubtermLiteralContext l = in.literals.get( nth );
						String temp = column + "_" + ( nth + 1 );
						Param prop = _helper.create( l, temp );
						work.params.add( prop );
					}
				}

				case Row row ->
				{
					for( int nth = 0; nth < row.literals.size(); nth++ )
					{
						SubtermLiteralContext l = row.literals.get( nth );
						String col = row.insert.columns.get( nth ).getText();
						Param prop = _helper.create( l, col );
						work.params.add( prop );
					}
				}

				default -> throw new IllegalStateException( "Unexpected value: " + p );
			}
		}

		/**
		 * Transform original SQL source code into a prepared statement,
		 * by replacing literals with SQL "?" placeholders.
 		 */
		for( Param param : work.params )
		{
			setStartTokenText( param.context(), "?" );
		}
		work.preparedSQL = tokens.getText();
		PreparedStatement ps = _conn.prepareStatement( work.preparedSQL );

		// Copy paramenter meta data
		ParameterMetaData pmd = ps.getParameterMetaData();
		if( pmd != null )
		{
			for( int nth = 1; nth <= pmd.getParameterCount(); nth++ )
			{
				// Remember that SQL arrays are is 1-based
				Param param = work.params.get( nth - 1 );
				param.nth( nth );
				param.sqlType( pmd.getParameterType( nth ));
				param.sqlTypeName( pmd.getParameterTypeName( nth ));
				param.isNullable( pmd.isNullable( nth ));
//				param.isSigned( pmd.isSigned( nth ));
//				param.scaled( pmd.getScale( nth ));
//				param.precision( pmd.getPrecision( nth ));
//				param.mode( pmd.getParameterMode( nth ));
				param.className( pmd.getParameterClassName( nth ));
//				params.add( param );
			}
		}

		// Copy column meta data
		ResultSetMetaData md = ps.getMetaData();
		if( md != null )
		{
			// TODO: dedupe resultset columns. or maybe add suffix to dupes.
			for( int nth = 1; nth <= md.getColumnCount(); nth++ )
			{
				Column column = new Column();
				column.nth( nth );
//				Column column = work.resultSetProperties.get( nth - 1 );
//				column.catalog = md.getCatalogName( nth );
//				column.schema = md.getSchemaName( nth );
//				column.table = md.getTableName( nth );
				column.name( md.getColumnName( nth ));
				column.alias( md.getColumnLabel( nth ));
				column.sqlType( md.getColumnType( nth ));
				column.sqlTypeName( md.getColumnTypeName( nth ));
				column.isNullable( md.isNullable( nth ));
				column.className( md.getColumnClassName( nth ));
				work.columns.add( column );
			}
		}

		for( Param param : work.params)
		{
			var trimmed = _helper.trimQuotes( param.original() );
			param.translated( _helper.convertToCode( param.sqlType(), trimmed ));
		}

		/** Transform original SQL source code into a printf template.
		 * eg Replacing integer "100" with "%d".
		 *
		 * Generated printf templates are useful for debugging and logging.
		 */
		for( Param param : work.params )
		{
			String text = _helper.toPrintfConverter( param.sqlType() );
			setStartTokenText( param.context(), text );
		}
		work.printfSQL = tokens.getText();

		Statement statement = work.root.getFirst();
		switch( statement )
		{
			case Select ignored ->
                // TODO foreach statement this, to support unions, multiple statements, and such
                //				work.resultSetProperties = matchItemsToColumns( work.root.get(0).items, work.columns );
				matchItemsToColumns( statement.items, work.columns);
			case Insert ignored ->
			{
				// TODO fill in missing columns
			}
			case Delete ignored ->
			{
				// TODO may have to attach Params to Table & Columns
			}
	        default ->
			{
			}
    	}

		merge( work );

		System.out.println( work.sourceFile + " processed" );
	}

	public String getColumn( SubtermContext b )
	{
		RuleContext column = ( (SubtermColumnContext) b ).qname();
		return _helper.getTrimmedText( column );
	}

	/**
	 * Match query 'items' from original SQL to ResultSet's result columns. If original SQL
	 * used wildcard '*', there will be more columns than items. Assumes items and
	 * result columns appear in same order. There can be more result columns than items.
	 *
	 */
	void matchItemsToColumns( List<Item> items, List<Column> columns )
	{
		for( Column column : columns )
		{
			// Default bean name (eg when there's no items to match against)
			String bean = column.alias();

			// TODO also match to catalog, schema, table
			// TODO resolve best match
			for( Item item : items )
			{
				if( column.name().equalsIgnoreCase( item.name ) )
				{
					if( column.alias().equalsIgnoreCase( item.alias ) )
					{
						bean = item.alias;
						column.item( item );
						break;
					}
					else if( column.alias().equalsIgnoreCase( item.name ))
					{
						bean = item.name;
						column.item( item );
						break;
					}
				}
			}

			column.variable( _helper.toVariableCase( bean ));
			column.getter( "get" + _helper.toMethodCase( bean ));
			column.setter( "set" + _helper.toMethodCase( bean ));
		}
	}

	public void merge( Work work ) throws IOException
	{
		HashMap<String, Object> childMap = work.asMap();
		childMap.put( "esc", new EscapeTool() );
		// TODO change to 'now', use same Date for all artifacts
		childMap.put( "date", _now);

		VelocityContext vc = new VelocityContext( childMap );

		Statement statement = work.root.getFirst();
		switch( statement )
		{
			case Select ignored ->
			{
				generate( _selectTemplate, vc, work.targetDir, work.statementClassName );
				generate( _resultSetTemplate, vc, work.targetDir, work.resultSetClassName );
			}

			case Insert ignored ->
				generate( _insertTemplate, vc, work.targetDir, work.statementClassName );

			case Delete ignored ->
				generate( _deleteTemplate, vc, work.targetDir, work.statementClassName );

	        default ->
				System.out.println( "unrecognized: " + statement.getClass() );
    	}
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
