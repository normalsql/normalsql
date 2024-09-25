// Copyright 2010-2023 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql;

import normalsql.template.ResultSetColumn;
import normalsql.template.PreparedStatementParameter;
import normalsql.parse.*;
import normalsql.parse.NormalSQLLexer;
import normalsql.parse.NormalSQLParser;
import normalsql.parse.NormalSQLParser.*;
import normalsql.template.JavaHelper;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
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
		var formatter = DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm:ss'Z'" ).withZone( ZoneOffset.UTC );
		_now = formatter.format( Instant.now() );

		_conn = conn;

		_engine = new VelocityEngine();
		_engine.setProperty( RuntimeConstants.RESOURCE_LOADERS, "classpath" );
		_engine.setProperty( "resource.loader.classpath.class", ClasspathResourceLoader.class.getName() );
		_engine.setProperty( "resource.loader.classpath.cache", "true" );
		_engine.setProperty( RuntimeConstants.UBERSPECT_CLASSNAME, "org.apache.velocity.util.introspection.UberspectPublicFields, org.apache.velocity.util.introspection.UberspectImpl" );
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
		var start = (WritableToken) context.getStart();
		start.setText( text );
	}

	// TODO "roundtrip" test, verify new PreparedStatement.toString() is same as original source

	// process( Work work ) throws IO, SQL
	public void process( Work work )
		throws IOException, SQLException
	{
		work.originalSQL = new String( Files.readAllBytes( work.sourceFile ));

		// TODO attempt running statement before parsing

		 var chars = CharStreams.fromString( work.originalSQL );
		 var lexer = new NormalSQLLexer( chars );
		var tokens = new CommonTokenStream( lexer );
		var parser = new NormalSQLParser( tokens );
		var script = parser.script();

		var visitor = new KnockoutVisitor();
		visitor.parser = parser;
		visitor.tokens = tokens;
		visitor.visit( script );

		work.root = visitor.root;
		if( work.root == null || work.root.isEmpty() )
		{
			System.out.println( "file contains no statements: " + work.sourceFile );
			return;
		}


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
							var name = getColumnQname( b.test );

							var low = new PreparedStatementParameter( b.low );
							_helper.signatures( low, name, "low" );
							work.params.add( low );

							var high = new PreparedStatementParameter( b.high );
							_helper.signatures( high, name, "high" );
							work.params.add( high );
						}

						case LiteralColumnColumn ->
						{
							var columnLow = getColumnQname( b.low );
							var columnHigh = getColumnQname( b.high );
							var test = new PreparedStatementParameter( b.test );
							_helper.signatures( test, "between", columnLow, "and", columnHigh );
							work.params.add( test );
						}
					}
				}
				case Comparison c ->
				{
					var column = getColumnQname( c.column );

					var param = new PreparedStatementParameter( c.literal );
					// TODO add operator to method signature
					_helper.signatures( param, column );
					work.params.add( param );
				}
				case LIKE m ->
				{
					var column = getColumnQname( m.column );
					var param = new PreparedStatementParameter( m.literal );
					_helper.signatures( param, column );
					work.params.add( param );
				}

				// TODO ANY predicate

				case IN in ->
				{
					var column = getColumnQname( in.column );
					for( int nth = 0; nth < in.literals.size(); nth++ )
					{
						var l = in.literals.get( nth );
						var temp = column + "_" + ( nth + 1 );
						var param = new PreparedStatementParameter( l );
						_helper.signatures( param, temp );
						work.params.add( param );
					}
				}

				case Row row ->
				{
					for( int nth = 0; nth < row.literals.size(); nth++ )
					{
						var l = row.literals.get( nth );
						var col = row.insert.columns.get( nth ).getText();
						var param = new PreparedStatementParameter( l );
						_helper.signatures( param, col );
						work.params.add( param );
					}
				}

				default -> throw new IllegalStateException( "Unexpected value: " + p );
			}
		}

		/*
		  Transform original SQL source code into a prepared statement,
		  by replacing literals with SQL "?" placeholders.
 		 */
		for( var param : work.params )
		{
			setStartTokenText( param.context(), "?" );
		}
		work.preparedSQL = tokens.getText();
		var ps = _conn.prepareStatement( work.preparedSQL );

		// Copy parameter meta data
		var pmd = ps.getParameterMetaData();
		if( pmd != null )
		{
			for( int nth = 1; nth <= pmd.getParameterCount(); nth++ )
			{
				// Remember that SQL arrays are 1-based
				var param = work.params.get( nth - 1 );
				param.nth( nth );
				param.sqlType( pmd.getParameterType( nth ));
				param.sqlTypeName( pmd.getParameterTypeName( nth ));
				param.isNullable( pmd.isNullable( nth ));
//				param.isSigned( pmd.isSigned( nth ));
//				param.scaled( pmd.getScale( nth ));
//				param.precision( pmd.getPrecision( nth ));
//				param.mode( pmd.getParameterMode( nth ));
				param.className( pmd.getParameterClassName( nth ));

//				for( var param : work.params )
				{
					var trimmed = _helper.trimQuotes( param.original() );
					param.translated( _helper.convertToCode( param.sqlType(), trimmed ));
				}

			}
		}

		/* Transform original SQL source code into a printf template.
		  eg Replacing integer "100" with "%d".

		  Generated printf templates are useful for debugging and logging.

		 */
		for( var param : work.params )
		{
			var text = _helper.toPrintfConverter( param.sqlType() );
			setStartTokenText( param.context(), text );
		}
		work.printfSQL = tokens.getText();


		// Copy column meta data
		var md = ps.getMetaData();
		if( md != null )
		{
			// TODO: dedupe resultset columns. or maybe add suffix to dupes.
			for( int nth = 1; nth <= md.getColumnCount(); nth++ )
			{
				var column = new ResultSetColumn();
				column.nth( nth );
//				Column column = work.resultSetProperties.get( nth - 1 );
//				column.catalog = md.getCatalogName( nth );
//				column.schema = md.getSchemaName( nth );
//				column.table = md.getTableName( nth );
				column.name( md.getColumnName( nth ));
				column.label( md.getColumnLabel( nth ));
				column.sqlType( md.getColumnType( nth ));
				column.sqlTypeName( md.getColumnTypeName( nth ));
				column.isNullable( md.isNullable( nth ));
				column.className( md.getColumnClassName( nth ));
				work.columns.add( column );
			}
		}


		var statement = work.root.getFirst();
		switch( statement )
		{
			case Select ignored ->
                // TODO foreach statement this, to support unions, multiple statements, and such
                //				work.resultSetProperties = matchItemsToColumns( work.root.get(0).items, work.columns );
				matchItemsToColumns( statement.items, work.columns );
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

	/**
	 *
	 * @param b
	 * @return qname (qualified name) of a resultset column
	 */
	public String getColumnQname(SubtermContext b )
	{
		var column = ( (SubtermColumnContext) b ).qname();
		return _helper.getTrimmedText( column );
	}

	/**
	 * Match query 'items' from original SQL to ResultSet's result columns. If original SQL
	 * used wildcard '*', there will be more columns than items. Assumes items and
	 * result columns appear in same order. There can be more result columns than items.
	 *
	 */
	void matchItemsToColumns( List<Item> items, List<ResultSetColumn> columns )
	{
		for( var column : columns )
		{
			// Use column's label by default (eg when there's no items to match against)
			var label = column.label();

			// TODO also match to catalog, schema, table
			// TODO resolve best match, eg when multiple columns have same name but label != alias
			// TODO eventually, account for locale, collation, etc. use IBM's ICU lib?
			for( var item : items )
			{
				if( column.name().equalsIgnoreCase( item.name ))
				{
					if( label.equalsIgnoreCase( item.alias ))
					{
						label = item.alias;
						column.item( item );
						break;
					}
					// TODO per best match idea above, this logic might be wrong for yet unknown edge cases. need test coverage.
					else if( label.equalsIgnoreCase( item.name ))
					{
						label = item.name;
						column.item( item );
						break;
					}
				}
			}

			if( column.item() != null )
			{
				column.original( column.item().context.getText() );
			}
			else
			{
				column.original( column.name() );
			}
			_helper.signatures( column, label );
			column.variable( _helper.toVariableCase( label ));
		}
	}

	public void merge( Work work ) throws IOException
	{
		var childMap = work.asMap();
		childMap.put( "esc", new EscapeTool() );
		// TODO change to 'now', use same Date for all artifacts
		childMap.put( "now", _now );

		var vc = new VelocityContext( childMap );

		var statement = work.root.getFirst();
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
