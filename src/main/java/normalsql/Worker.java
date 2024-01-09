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
import normalsql.template.Accessor;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Worker
{
	Connection _conn;
	VelocityEngine _engine;
	Template _selectTemplate;
	Template _insertTemplate;
	Template _deleteTemplate;
	Template _resultSetTemplate;
	JavaHelper _helper;

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
//		work.originalSQL = new String( Files.readAllBytes( work.sourceFile ));
//
//		// TODO attempt running statement before parsing
//
//		// TODO reuse one instance of ANTLR?
//		CharStream chars = CharStreams.fromString( work.originalSQL );
//		NormalSQLLexer lexer = new NormalSQLLexer( chars );
//		CommonTokenStream tokens = new CommonTokenStream( lexer );
//		NormalSQLParser parser = new NormalSQLParser( tokens );
//		ScriptContext script = parser.script();
//
//		KnockoutVisitor visitor = new KnockoutVisitor();
//		visitor.parser = parser;
//		visitor.tokens = tokens;
//		visitor.visit( script );
//		work.root = visitor.root;
//
//		work.knockouts = visitor.knockouts;
//
//		for( var p : work.knockouts)
//		{
//			switch( p )
//			{
//				case BETWEEN b ->
//				{
//					switch( b.pattern )
//					{
//						case ColumnLiteralLiteral ->
//						{
//							String name = getColumn( b.test );
//							Accessor low = _helper.create( b.low, name, "low" );
//							work.statementAccessors.add( low );
//
//							Accessor high = _helper.create( b.high, name, "high" );
//							work.statementAccessors.add( high );
//						}
//
//						case LiteralColumnColumn ->
//						{
//							String columnLow = getColumn( b.low );
//							String columnHigh = getColumn( b.high );
//							Accessor high = _helper.create( b.test, "between", columnLow, "and", columnHigh );
//							work.statementAccessors.add( high );
//						}
//					}
//				}
////				case Comparison c ->
////				{
////					// TODO add operator to method signature
////					String column = getColumn( c.column );
////					Accessor prop = _helper.create( c.literal, column );
////					work.statementAccessors.add( prop );
////				}
//				case LIKE m ->
//				{
//					String column = getColumn( m.column );
//					Accessor prop = _helper.create( m.literal, column );
//					work.statementAccessors.add( prop );
//				}
//
//				// TODO ANY predicate
//
//				case IN in ->
//				{
////					String column = getColumn( in.column );
////					for( int nth = 0; nth < in.literals.size(); nth++ )
////					{
////						SubtermValueContext l = in.literals.get( nth );
////						String temp = column + "_" + ( nth + 1 );
////						Accessor prop = _helper.create( l, temp );
////						work.statementAccessors.add( prop );
////					}
//				}
//
//				case Row row ->
//				{
//					for( int nth = 0; nth < row.literals.size(); nth++ )
//					{
////						SubtermLiteralContext l = row.literals.get( nth );
////						String col = row.insert.columns.get( nth ).getText();
////						Accessor prop = _helper.create( l, col );
////						work.statementAccessors.add( prop );
//					}
//				}
//
//				default -> throw new IllegalStateException( "Unexpected value: " + p );
//			}
//		}
//
//		for( Accessor prop : work.statementAccessors )
//		{
//			setStartTokenText( prop.context, "?" );
//		}
//
//		work.preparedSQL = tokens.getText();
//
//		FancyMetaData md = new FancyMetaData( _conn, work.preparedSQL );
//		work.columns = md.columns;
//
//		for( int nth = 0; nth < md.params.size(); nth++ )
//		{
//			Accessor prop = work.statementAccessors.get( nth );
//			Param p = md.params.get( nth );
//			prop.param = p;
//			prop.nth = p.nth;
//			prop.className = p.className;
//			prop.classShortName = p.className.substring( p.className.lastIndexOf( "." ) + 1 );
//			prop.asCode = _helper.convertToCode( p.type, prop.trimmed );
//		}
//
//		for( Accessor prop : work.statementAccessors )
//		{
//			String text = _helper.toPrintfConverter( prop.param.type );
//			setStartTokenText( prop.context, text );
//		}
//
//		work.printfSQL = tokens.getText();
//
//		switch ( work.root.get( 0 ))
//		{
//			case Select ignore ->
//			{
//				// TODO foreach statement this, to support unions, multiple statements, and such
//				work.resultSetAccessors = matchItemsToColumns( work.root.get(0).items, work.columns );
//			}
//			case Insert ignore ->
//			{
//				// TODO fill in missing columns
//			}
//			case Delete ignore ->
//			{
//				// TODO may have to attach Params to Table & Columns
//			}
//	        default ->
//			{
//			}
//    	}
//
//		// TODO match values' literals to columns
//
//		merge( work );
//
//		// TODO "roundtrip" test, verify new PreparedStatement.toString() is same as original source
//
//		System.out.println( work.sourceFile + " processed" );
	}

	public String getColumn( SubtermContext b )
	{
////		RuleContext column = ( (SubtermColumnContext) b ).id().qname();
//		RuleContext column = ( (SubtermColumnContext) b ).id();
//		return _helper.getTrimmedText( column );
		return null;
	}

	/**
	 * Match query 'items' from original SQL to ResultSet's result columns. If original SQL
	 * used wildcard '*', there will be more columns than items. Assumes items and
	 * result columns appear in same order. There can be more result columns than items.
	 *
	 */
	List<Accessor> matchItemsToColumns(List<Item> items, List<Column> columns )
	{
		ArrayList<Accessor> properties = new ArrayList<>();

		for( Column column : columns )
		{
			// TODO reference to parent Item
			Accessor prop = new Accessor();
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

	public void merge( Work work ) throws IOException
	{
		HashMap<String, Object> childMap = work.asMap();
		childMap.put( "esc", new EscapeTool() );
		// TODO change to 'now', use same Date for all artifacts
		childMap.put( "date", new Date() );

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
