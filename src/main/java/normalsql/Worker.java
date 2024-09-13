// Copyright 2010-2023 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql;

import normalsql.jdbc.Column;
import normalsql.jdbc.FancyMetaData;
import normalsql.jdbc.Param;
import normalsql.parse.*;
import normalsql.parse.NormalSQLLexer;
import normalsql.parse.NormalSQLParser;
import normalsql.parse.NormalSQLParser.*;
import normalsql.template.JavaHelper;
import normalsql.template.Property;
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
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
//		_now = new Date();
//		Instant instant = date.toInstant();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm:ss'Z'" ).withZone( ZoneOffset.UTC );
		_now = formatter.format( Instant.now() );

//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm:ss" );
//		String formattedDateTime = Instant.now().format( formatter );

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
		work.visitor = visitor;
		work.root = visitor.root;

		work.knockouts = visitor.knockouts;

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
							Property low = _helper.create( b.low, name, "low" );
							work.statementProperties.add( low );

							Property high = _helper.create( b.high, name, "high" );
							work.statementProperties.add( high );
						}

						case LiteralColumnColumn ->
						{
							String columnLow = getColumn( b.low );
							String columnHigh = getColumn( b.high );
							Property high = _helper.create( b.test, "between", columnLow, "and", columnHigh );
							work.statementProperties.add( high );
						}
					}
				}
				case Comparison c ->
				{
					// TODO add operator to method signature
					String column = getColumn( c.column );
					Property prop = _helper.create( c.literal, column );
					work.statementProperties.add( prop );
				}
				case LIKE m ->
				{
					String column = getColumn( m.column );
					Property prop = _helper.create( m.literal, column );
					work.statementProperties.add( prop );
				}

				// TODO ANY predicate

				case IN in ->
				{
					String column = getColumn( in.column );
					for( int nth = 0; nth < in.literals.size(); nth++ )
					{
						SubtermLiteralContext l = in.literals.get( nth );
						String temp = column + "_" + ( nth + 1 );
						Property prop = _helper.create( l, temp );
						work.statementProperties.add( prop );
					}
				}

				case Row row ->
				{
					for( int nth = 0; nth < row.literals.size(); nth++ )
					{
						SubtermLiteralContext l = row.literals.get( nth );
						String col = row.insert.columns.get( nth ).getText();
						Property prop = _helper.create( l, col );
						work.statementProperties.add( prop );
					}
				}

				default -> throw new IllegalStateException( "Unexpected value: " + p );
			}
		}

		for( Property prop : work.statementProperties )
		{
			setStartTokenText( prop.context(), "?" );
		}

		work.preparedSQL = tokens.getText();

		FancyMetaData md = new FancyMetaData( _conn, work.preparedSQL );
		work.columns = md.columns;

		for( int nth = 0; nth < md.params.size(); nth++ )
		{
			Property prop = work.statementProperties.get( nth );
			Param p = md.params.get( nth );
			prop.param = p;
			prop.nth = p.nth;
			// Copy the className from the PreparedStatement's Param to our Property
			prop.className = p.className;
//			var trimmed = _helper.getTrimmedText( prop.context );
			var trimmed = _helper.trimQuotes( prop.original );
			prop.asCode = _helper.convertToCode( p.sqlType, trimmed );
		}



		for( Property prop : work.statementProperties )
		{
			String text = _helper.toPrintfConverter( prop.sqlType() );
			setStartTokenText( prop.context(), text );
		}

		work.printfSQL = tokens.getText();

		if( work.root == null || work.root.isEmpty() )
		{
			System.out.println( "file contains no statements: " + work.sourceFile );
			return;
		}

		switch ( work.root.get( 0 ))
		{
			case Select ignore ->
			{
				// TODO foreach statement this, to support unions, multiple statements, and such
				work.resultSetProperties = matchItemsToColumns( work.root.get(0).items, work.columns );
			}
			case Insert ignore ->
			{
				// TODO fill in missing columns
			}
			case Delete ignore ->
			{
				// TODO may have to attach Params to Table & Columns
			}
	        default ->
			{
			}
    	}

		// TODO match values' literals to columns

		merge( work );

		// TODO "roundtrip" test, verify new PreparedStatement.toString() is same as original source

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
	List<Property> matchItemsToColumns( List<Item> items, List<Column> columns )
	{
//		var properties = new ArrayList<Property>();

		for( Column column : columns )
		{
//			// TODO reference to parent Item
//			var prop = new Property();
//			prop.nth = column.nth;
//			prop.column = column;
			String bean = column.label();
			// TODO also match to catalog, schema, table
			// TODO resolve best match
			for( Item item : items )
			{
				if( column.name().equalsIgnoreCase( item.name ) )
				{
					if( column.label().equalsIgnoreCase( item.alias ) )
					{
						bean = item.alias;
						break;
					}
					else if( column.label().equalsIgnoreCase( item.name ))
					{
						bean = item.name;
						break;
					}
				}
			}

			column.original( bean );
			column.variable( _helper.toVariableCase( bean ));
			column.getter(  "get" + _helper.toMethodCase( bean ));
			column.setter( "set" + _helper.toMethodCase( bean ));
//			prop.className = column.className;

//			properties.add( prop );
		}

		return properties;
	}

	public void merge( Work work ) throws IOException
	{
		HashMap<String, Object> childMap = work.asMap();
		childMap.put( "esc", new EscapeTool() );
		// TODO change to 'now', use same Date for all artifacts
		childMap.put( "date", _now);

		VelocityContext vc = new VelocityContext( childMap );

		switch ( work.root.get( 0 ))
		{
			case Select ignore ->
			{
				generate( _selectTemplate, vc, work.targetDir, work.statementClassName );
				generate( _resultSetTemplate, vc, work.targetDir, work.resultSetClassName );
			}
			case Insert ignore ->
			{
				generate( _insertTemplate, vc, work.targetDir, work.statementClassName );
			}
			case Delete ignore ->
			{
				generate( _deleteTemplate, vc, work.targetDir, work.statementClassName );
			}
	        default ->
			{
				System.out.println( "unrecognized: " + work.root.get( 0 ).getClass() );
			}
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
