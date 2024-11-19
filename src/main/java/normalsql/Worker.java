// Copyright 2010-2024 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql;

import static java.sql.ParameterMetaData.parameterNoNulls;
import static java.sql.ResultSetMetaData.columnNoNulls;
import static normalsql.Tool.*;
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

import static normalsql.Glorp.inferGeneratedKeyType;
import static normalsql.Glorp.getJavaClassName;

public class
	Worker
{
	          String _now;
	      Connection _conn;
  	  VelocityEngine _engine;
	        Template _insertTemplate;
	        Template _deleteTemplate;
	        Template _updateTemplate;
            Template _selectTemplate;
	        Template _resultSetTemplate;
		  JavaHelper _helper;

	public Worker( Connection conn )
	{
		_conn = conn;

		var formatter = DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm:ss'Z'" ).withZone( ZoneOffset.UTC );
		_now = formatter.format( Instant.now() );

		_engine = new VelocityEngine();
		_engine.setProperty( RuntimeConstants.RESOURCE_LOADERS, "classpath" );
		_engine.setProperty( "resource.loader.classpath.class", ClasspathResourceLoader.class.getName() );
		_engine.setProperty( "resource.loader.classpath.cache", "true" );
		_engine.setProperty( RuntimeConstants.UBERSPECT_CLASSNAME, "org.apache.velocity.util.introspection.UberspectPublicFields, org.apache.velocity.util.introspection.UberspectImpl" );
		_engine.init();

		_insertTemplate = _engine.getTemplate( "normalsql/template/Insert.vm" );
		_deleteTemplate = _engine.getTemplate( "normalsql/template/Delete.vm" );
		_updateTemplate = _engine.getTemplate( "normalsql/template/Update.vm" );
		_selectTemplate = _engine.getTemplate( "normalsql/template/Select.vm" );
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

	public void process( UnitOfWork unitOfWork )
		throws IOException, SQLException
	{
		unitOfWork.originalSQL = new String( Files.readAllBytes( unitOfWork.sourceFile ));

		// TODO attempt running statement before parsing

		 var chars = CharStreams.fromString( unitOfWork.originalSQL );
		 var lexer = new NormalSQLLexer( chars );
		var tokens = new CommonTokenStream( lexer );
		var parser = new NormalSQLParser( tokens );
		var script = parser.script();

		var visitor = new KnockoutVisitor();
		visitor.parser = parser;
		visitor.tokens = tokens;
		visitor.visit( script );

		unitOfWork.root = visitor.root;
		if( unitOfWork.root == null || unitOfWork.root.isEmpty() )
		{
			WARN.log( "file contains no statements: " + unitOfWork.sourceFile ) ;
			return;
		}

		for( var knockout : visitor.knockouts )
		{
			// TODO this doesn't work yet because bugs in knockouts
			if( !knockout.isMatched() )
			{
				ERROR.log( "parameter expression not match(able): " + knockout.context.getText() );
			}

			switch( knockout )
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
							unitOfWork.params.add( low );

							var high = new PreparedStatementParameter( b.high );
							_helper.signatures( high, name, "high" );
							unitOfWork.params.add( high );
						}

						case LiteralColumnColumn ->
						{
							var columnLow = getColumnQname( b.low );
							var columnHigh = getColumnQname( b.high );
							var test = new PreparedStatementParameter( b.test );
							_helper.signatures( test, "between", columnLow, "and", columnHigh );
							unitOfWork.params.add( test );
						}
					}
				}
				case Comparison c ->
				{
					var column = getColumnQname( c.column );

					var param = new PreparedStatementParameter( c.literal );
					// TODO add operator to method signature
					_helper.signatures( param, column );
					unitOfWork.params.add( param );
				}
				case IN in ->
				{
					var column = getColumnQname( in.column );
					for( int nth = 0; nth < in.literals.size(); nth++ )
					{
						var l = in.literals.get( nth );
						var temp = column + "_" + ( nth + 1 );
						var param = new PreparedStatementParameter( l );
						_helper.signatures( param, temp );
						unitOfWork.params.add( param );
					}
				}
				case LIKE like ->
				{
					var column = getColumnQname( like.column );
					var param = new PreparedStatementParameter( like.literal );
					_helper.signatures( param, column );
					unitOfWork.params.add( param );
				}
				case Setter setter ->
				{
					var temp = setter.qname.getText();
					var column = _helper.trimQuotes( temp );
					var param = new PreparedStatementParameter( setter.literal );
					_helper.signatures( param, column );
					unitOfWork.params.add( param );
				}

				// TODO ANY predicate

				case Row row ->
				{
					for( int nth = 0; nth < row.literals.size(); nth++ )
					{
						var l = row.literals.get( nth );
						var col = row.insert.columns.get( nth ).getText();
						var param = new PreparedStatementParameter( l );
						_helper.signatures( param, col );
						unitOfWork.params.add( param );
					}
				}

				default -> throw new IllegalStateException( "Unexpected value: " + knockout );
			}
		}

		/*
		  Transform original SQL source code into a prepared statement,
		  by replacing literals with SQL "?" placeholders.
 		 */
		for( var param : unitOfWork.params )
		{
			setStartTokenText( param.context(), "?" );
		}
		unitOfWork.preparedSQL = tokens.getText();
		var ps = _conn.prepareStatement( unitOfWork.preparedSQL );

		// TODO move to PreparedStatementParameter, cuz I can't keep all these details straight in my head
		// Copy parameter meta data
		var pmd = ps.getParameterMetaData();
//		var ugh = ps.getMetaData();
//		var dink = ps.getResultSetType();
		if( pmd != null )
		{
			// SQL arrays are 1-based
			for( int nth = 1; nth <= pmd.getParameterCount(); nth++ )
			{
				var param = unitOfWork.params.get( nth - 1 );
				param.nth( nth );
				param.sqlType( pmd.getParameterType( nth ));
				param.sqlTypeName( pmd.getParameterTypeName( nth ));
				param.isNullable( pmd.isNullable( nth ));
//				param.isSigned( pmd.isSigned( nth ));
//				param.scaled( pmd.getScale( nth ));
//				param.precision( pmd.getPrecision( nth ));
//				param.mode( pmd.getParameterMode( nth ));
				param.className( pmd.getParameterClassName( nth ));
				if( param.isNullable() == parameterNoNulls )
				{
					var name = toScalarClassName( param.className() );
					param.className( name );
				}

				// TODO but this stays here, cuz template related. or moves to merge?
				var trimmed = _helper.trimQuotes( param.original() );
				param.translated( _helper.convertToCode( param.sqlType(), trimmed ));
			}
		}

		/* Transform original SQL source code into a printf template.
		  eg Replacing integer "100" with "%d".

		  Generated printf templates are useful for debugging and logging.

		 */
		for( var param : unitOfWork.params )
		{
			var text = _helper.toPrintfConverter( param.sqlType() );
			setStartTokenText( param.context(), text );
		}
		unitOfWork.printfSQL = tokens.getText();


		// TODO move to ResultSetColumn, cuz I can't keep all these details straight in my head
		// Copy column meta data
		var md = ps.getMetaData();
		if( md != null )
		{
			// TODO: dedupe resultset columns. or maybe add suffix to dupes.
			for( int nth = 1; nth <= md.getColumnCount(); nth++ )
			{
				var column = new ResultSetColumn();
				column.nth( nth );
				column.catalog = md.getCatalogName( nth );
				column.schema = md.getSchemaName( nth );
				column.table = md.getTableName( nth );
				column.name( md.getColumnName( nth ));
				column.label( md.getColumnLabel( nth ));
				column.sqlType( md.getColumnType( nth ));
				column.sqlTypeName( md.getColumnTypeName( nth ));
				column.isNullable( md.isNullable( nth ));
				column.className( md.getColumnClassName( nth ));
				if( column.isNullable() == columnNoNulls )
				{
					var name = toScalarClassName( column.className() );
					column.className( name );
				}
				unitOfWork.columns.add( column );
			}
		}


		var statement = unitOfWork.root.getFirst();
		switch( statement )
		{
			case Select ignored ->
			{
                // TODO foreach statement this, to support unions, multiple statements, and such
                //				work.resultSetProperties = matchItemsToColumns( work.root.get(0).items, work.columns );
				matchItemsToColumns( statement.items, unitOfWork.columns );
			}
			case Insert insert ->
			{
				var table = insert.table.getText();
				var sqlType = inferGeneratedKeyType( null, null, table, _conn );
				unitOfWork.keyClassName = getJavaClassName( sqlType );
			}
			case Delete ignored ->
			{
				// TODO may have to attach Params to Table & Columns
			}
	        default ->
			{
			}
    	}

		INFO.log( "processed: " + unitOfWork.sourceFile );

		merge( unitOfWork );

//		JavaCompiler javac = ToolProvider.getSystemJavaCompiler();
//		int compilationResult = javac.run(null, null, null, work.targetFile.toString());

//		if (compilationResult == 0) {
//			System.out.println("Compilation successful.");
//		} else {
//			System.out.println("Compilation failed.");
//		}
		//  compare originalSQL == toString()
		// TODO verify generation worked
		//  javac succeeds
		//  forName

	}

	private static String toScalarClassName( String name )
	{
		name = switch( name )
		{
			case "java.lang.Boolean" -> "boolean";
			case "java.lang.Byte" -> "byte";
			case "java.lang.Short" -> "short";
			case "java.lang.Character" -> "char";
			case "java.lang.Integer" -> "int";
			case "java.lang.Long" -> "long";
			case "java.lang.Float" -> "float";
			case "java.lang.Double" -> "double";
			default -> name;
		};
		return name;
	}

	/**
	 *
	 * @param b
	 * @return qname (qualified name) of a resultset column
	 */
	public String getColumnQname( SubtermContext b )
	{
		var column = ( (SubtermColumnContext) b ).qname();
		return _helper.getTrimmedText( column );
	}

	/**
	 * Match SELECT items to ResultSet columns. When SELECT items use wildcard '*', there can be more
	 * ResultSet columns than SELECT items. Assumes items and result columns appear in same order.
	 *
	 * TODO Use database's case sensitivity settings & quoted ids must be exact match
	 * TODO ensure each column matches just one time, eg "SELECT 1 AS name, 2 AS name"
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
				if( column.name().equalsIgnoreCase( item.localName ))
				{
					if( label.equalsIgnoreCase( item.alias ))
					{
						label = item.alias;
						column.item( item );
						break;
					}
					// TODO per best match idea above, this logic might be wrong for yet unknown edge cases. need test coverage.
					else if( label.equalsIgnoreCase( item.localName ))
					{
						label = item.localName;
						column.item( item );
						break;
					}
				}
			}

			if( column.item() != null )
			{
				column.original( column.item().verbatim );
			}
			else
			{
				column.original( column.name() );
			}
			_helper.signatures( column, label );
			column.variable( _helper.toVariableCase( label ));
		}
	}

	public void merge( UnitOfWork unitOfWork ) throws IOException
	{
		// Prime Velocity context for next task
		var contextChildMap = Glorp.toMap( unitOfWork );
		contextChildMap.put( "esc", new EscapeTool() );
		contextChildMap.put( "now", _now );
		var vc = new VelocityContext( contextChildMap );

		var statement = unitOfWork.root.getFirst();
		switch( statement )
		{
			case Select ignored ->
			{
				generate( _selectTemplate, vc, unitOfWork.targetDir, unitOfWork.statementClassName );
				generate( _resultSetTemplate, vc, unitOfWork.targetDir, unitOfWork.resultSetClassName );
			}

			case Insert ignored ->
				generate( _insertTemplate, vc, unitOfWork.targetDir, unitOfWork.statementClassName );

			case Delete ignored ->
				generate( _deleteTemplate, vc, unitOfWork.targetDir, unitOfWork.statementClassName );

			case Update ignored ->
				generate( _updateTemplate, vc, unitOfWork.targetDir, unitOfWork.statementClassName );

	        default ->
				WARN.log( "skipped unrecognized: " + statement.getClass() );
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
		var resolved = targetDir.resolve( name + ".java" ).normalize();
		try ( FileWriter writer = new FileWriter( resolved.toFile() ))
		{
			template.merge( vc, writer );
			writer.flush();
			INFO.log( "generated: " + resolved );
		}
	}
}
