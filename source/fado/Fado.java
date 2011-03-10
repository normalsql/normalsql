package fado;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import joptsimple.OptionParser;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.ANTLRReaderStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.Token;
import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.jxpath.ri.JXPathContextReferenceImpl;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import fado.meta.Column;
import fado.meta.Condition;
import fado.meta.IN;
import fado.meta.Meta;
import fado.meta.Comparison;
import fado.meta.Table;
import fado.meta.TableNotFoundException;
import fado.meta.TypeConverter;
import fado.parse.FadoParseNode;
import fado.parse.GenericSQLLexer;
import fado.parse.GenericSQLParser;
import fado.parse.ParseNodePointerFactory;
import fado.parse.ParseTreeBuilder;

public class Fado 
{
	public static void main( String[] args )
		throws Exception 
	{
		FadoOptions options = new FadoOptions();
//		options.parse( args );
		
		Fado main = new Fado();
		main.go( options );
	}
	
	public void go( FadoOptions options )
		throws Exception
	{
		// TODO Handle subpackages, eg apple.banana => /apple/banana

		String target = options.target + options.packageName;
		File targetDir = new File( target );
		targetDir.mkdirs();
		
		File sourceDir = new File( options.source );
		FileFilter filter = new FileFilter() 
		{
			public boolean accept( File file )
			{
				String name = file.getName();
				boolean result = name.endsWith( ".sql" );
				return result;
			}
		};
		
		File[] files = sourceDir.listFiles( filter );
		for( File file : files)
		{
			try
			{
				Meta extract = new Meta();
				String className = getFileName( file );
				FileReader reader = new FileReader( file );
				CharStream cs = new ANTLRReaderStream( reader );
				String retooledSQL = extract( cs, extract );
				List<String> choppedSQL = chopper( retooledSQL.trim() );
				
				inspectDatabase( options.driver, options.url, options.username, options.password, extract );
				
				File targetFile = new File( targetDir, className + "Select.java" );
				generate( targetFile, options.packageName, className, choppedSQL, extract );
			}
			catch( Exception e )
			{
				throw new FadoException( "file: " + file.toString(), e );
			}
		}
	}

	public String getFileName( File file )
	{
		String name = file.getName();
		int i = name.indexOf( '.' );
		if( i > -1 )
		{
			name = name.substring( 0, i );
		}
		return name;
	}
	
	private String extract( CharStream cs, Meta extract ) 
		throws Exception 
	{
		ParseTreeBuilder builder = new ParseTreeBuilder( "GenericSQL.g" );
		GenericSQLLexer lexer = new GenericSQLLexer( cs );
		CommonTokenStream tokens = new CommonTokenStream( lexer );
		GenericSQLParser parser = new GenericSQLParser( tokens, builder );

		parser.statement();
		
		FadoParseNode source = builder.getTree();
		
//		System.out.println( "string tree: " + builder.getTree().toStringTree() );
//		System.out.println( "input string: " + builder.getTree().toInputString() );
		
		ParseNodePointerFactory factory = new ParseNodePointerFactory();
		JXPathContextReferenceImpl.addNodePointerFactory( factory );

		
		// Tables
		extractTables( source, extract );
		// Columns
		extractColumns( source, extract );
		// Parameters
		extractParams( source, extract );
		
		String result = builder.getTree().toInputString();
//		System.out.println( "rewrite : " + result );
		return result;
	}
	
	public List<String> chopper( String sql )
	{
		StringReader sr = new StringReader( sql );
		LineReader lr = new LineReader( sr );
		List<String> result = lr.toArray();
		return result;
	}
	
	public void extractColumns( FadoParseNode source, Meta meta )
		throws Exception
	{
		JXPathContext statementContext = JXPathContext.newContext( source );
		
		String allStar = (String) statementContext.selectSingleNode("/statement/select/itemList/text()" );
		if( "*".equals( allStar ))
		{
			for( Table table : meta.getTables() )
			{
				meta.tempColumn( new Column( table ));
			}
		}
		else
		{
			List list = statementContext.selectNodes("/statement/select/itemList/item" );
			for( Object item : list )
			{
				JXPathContext itemContext = JXPathContext.newContext( item );
				
				// All columns from a table, eg alias.*
				String tableAlias = (String) itemContext.selectSingleNode( "/allColumns/tableAlias/text()" );
				if( tableAlias != null )
				{
					Table table = meta.getTableByAlias( tableAlias );
					Column column = new Column( table );
					meta.tempColumn( column);
					continue;
				}
				
				// Single column reference, eg alias.FamilyName
				String columnName = (String) itemContext.selectSingleNode( "/value/columnRef/columnName/text()" );
				
				if( columnName != null )
				{
					columnName = trimQuotes( columnName );
					tableAlias = (String) itemContext.selectSingleNode( "/value/columnRef/tableAlias/text()" );
					String columnAlias = (String) itemContext.selectSingleNode( "/alias/text()" );
					Table table = meta.getTableByAlias( tableAlias );
					Column column = new Column( table, columnName, columnAlias );
					meta.tempColumn( column );
					continue;
				}
				
				// Expression returned as column
				// TODO: expression columns
				
			}
		}
	}
	
	public void extractTables( FadoParseNode source, Meta meta )
	{
		JXPathContext statementContext = JXPathContext.newContext( source );
		List list = statementContext.selectNodes("/statement/select/from/fromItem" );
		for( Object item : list )
		{
			JXPathContext context = JXPathContext.newContext( item );
			
			String databaseName = (String) context.selectSingleNode( "/tableRef/databaseName/text()" );
			String tableName = (String) context.selectSingleNode( "/tableRef/tableName/text()" );
			String alias = (String) context.selectSingleNode( "/alias/text()" );
			System.out.printf( "database: %s, table: %s, alias: %s \n", databaseName, tableName, alias );
			Table table = new Table( databaseName, tableName, alias );
			meta.table( table );
		}
	}
	
	public void extractParams( FadoParseNode source, Meta meta )
		throws FadoException
	{
		JXPathContext context = JXPathContext.newContext( source );
		List ugh = context.selectNodes( "/statement/select/where" );
		if( ugh.size() < 1 ) return;
		
		FadoParseNode where = (FadoParseNode) ugh.get( 0 );
		JXPathContext whereContext = JXPathContext.newContext( where );
		
		List list = whereContext.selectNodes( "//condition" );
		for( Object object : list )
		{
			FadoParseNode condition = (FadoParseNode) object;
			JXPathContext conditionContext = JXPathContext.newContext( condition );
			List comparison = conditionContext.selectNodes( "comparison" );
			if( comparison.size() == 1 )
			{
				extractComparisonParams( (FadoParseNode) comparison.get( 0 ), meta );
			}
			List in = conditionContext.selectNodes( "in" );
			if( in.size() == 1 )
			{
				extractINParams( (FadoParseNode) in.get( 0 ), meta );
			}
			List between = conditionContext.selectNodes( "between" );
		}
	}
	
	// Comparisons, eg column = 'abc'
	public void extractComparisonParams( FadoParseNode comparison, Meta meta )
		throws TableNotFoundException
	{
		JXPathContext comparisonContext = JXPathContext.newContext( comparison );
		List columns = comparisonContext.selectNodes( "//columnRef" );
		List literals = comparisonContext.selectNodes( "//literal");
		if( columns.size() == 1 && literals.size() == 1 )
		{
			System.out.println( "found comparison: " + comparison.toInputString() );
			FadoParseNode columnRef = (FadoParseNode) columns.get( 0 );
			JXPathContext columnRefContext = JXPathContext.newContext( columnRef );
			String column = (String) columnRefContext.selectSingleNode( "/columnName/text()" );
			String tableAlias = (String) columnRefContext.selectSingleNode( "/tableAlias/text()" );

			FadoParseNode literalNode = (FadoParseNode) literals.get( 0 );
			String literal = getTokenText( literalNode );
			literal = trimQuotes( literal );
			
			// Replace original value with JDBC parameter '?'
			setTokenText( literalNode, "?" ); 
			int literalType = getTokenType( literalNode );
			
			int sqlType = TypeConverter.fromLiteralTypeToSqlType( literalType );
			
			Table table = meta.getTableByAlias( tableAlias );

			Comparison c = new Comparison( table, column, sqlType, literal );
			meta.comparison( c );
		}
	}
	
	// IN, eg column IN ( 1, 2, 3 )
	// Very naive pattern extraction, assumes column on left and literals on right
	public void extractINParams( FadoParseNode in, Meta meta ) 
		throws FadoException
	{
		JXPathContext inContext = JXPathContext.newContext( in );
		List columns = inContext.selectNodes( "//columnRef" );
		List literals = inContext.selectNodes( "//literal");
		if( columns.size() == 1 && literals.size() > 0 )
		{
			FadoParseNode columnRef = (FadoParseNode) columns.get( 0 );
			JXPathContext columnRefContext = JXPathContext.newContext( columnRef );
			String column = (String) columnRefContext.selectSingleNode( "/columnName/text()" );
			String tableAlias = (String) columnRefContext.selectSingleNode( "/tableAlias/text()" );
			Table table = meta.getTableByAlias( tableAlias );

			IN param = new IN( table, column );
			
			for( Object object : literals )
			{
				FadoParseNode literal = (FadoParseNode) object;
				String text = getTokenText( literal );
				text = trimQuotes( text );
				
				// Replace original value with JDBC parameter '?'
				setTokenText( literal, "?" ); 
				int literalType = getTokenType( literal );
				
				int sqlType = TypeConverter.fromLiteralTypeToSqlType( literalType );
				param.addValue( text, sqlType );
			

			}
			meta.comparison( param );
		}
	}
	
	
	// BETWEEN, eg column BETWEEN 1 AND 100
	// TODO: BETWEEN condition
	
	// LIKE, eg column LIKE 'abc%'
	// TODO: LIKE condition

	public String getTokenText( FadoParseNode node )
	{
		String result = null;
		if( node != null )
		{
			FadoParseNode temp = (FadoParseNode) node.getChild( 0 );
			Token token = temp.getToken();
			result = token.getText();
		}
		return result;
	}
		
	public void setTokenText( FadoParseNode node, String text )
	{
		FadoParseNode temp = (FadoParseNode) node.getChild( 0 );
		Token token = temp.getToken();
		token.setText( text );
	}

	public int getTokenType( FadoParseNode node )
	{
		FadoParseNode temp = (FadoParseNode) node.getChild( 0 );
		Token token = temp.getToken();
		int type = token.getType();
		return type;
	}
	
	public String trimQuotes( String text )
	{
		String result = text;
		int len = text.length();
		// Parser ensures token has quotes front and back
		if( text.indexOf( '[' ) > -1 || text.indexOf( '"' ) > -1 || text.indexOf( '\'' ) > -1 )
		{
			result = text.substring( 1, len - 1 );
		}
		return result;
	}	
	
	public void inspectDatabase( String driver, String url, String username, String password, Meta extract )
		throws Exception 
	{
		Class.forName( driver );
		Connection conn = DriverManager.getConnection( url, username, password );
		
		PreparedStatement tablesPS = conn.prepareStatement( "SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = ?" );
		PreparedStatement columnPS = conn.prepareStatement( "SELECT DATA_TYPE, TYPE_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = ? AND COLUMN_NAME = ?" );
		PreparedStatement allColumnsPS = conn.prepareStatement( "SELECT COLUMN_NAME, DATA_TYPE, TYPE_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = ? ORDER BY ORDINAL_POSITION" );

		for( Column tempColumn : extract.getTempColumns() )
		{
			switch( tempColumn.getStyle() )
			{
				case WholeTable:
				{
					String tableName = tempColumn.getTable().getName().toUpperCase();
					allColumnsPS.setString( 1, tableName );
					
					if( allColumnsPS.execute() )
					{
						ResultSet allColumnsRS = allColumnsPS.getResultSet();
						while( allColumnsRS.next() )
						{
							String name = allColumnsRS.getString( "COLUMN_NAME" );
							int sqlType = allColumnsRS.getInt( "DATA_TYPE" );
							String sqlTypeName = allColumnsRS.getString( "TYPE_NAME" );
							
							Column column = new Column( name, sqlType, sqlTypeName );
							extract.addFinalColumn( column );
						}
						allColumnsRS.close();
					}
					
					break;
				}
				
				case Column:
				case Alias:
				{
					List<Table> tables = null;
					if( tempColumn.hasTable() )
					{
						tables = new ArrayList<Table>();
						Table table = tempColumn.getTable();
						tables.add( table );
					}
					else
					{
						tables = extract.getTables();
					}
					
					boolean found = false;
					for( Table table : tables )
					{
						String tableName = table.getName().toUpperCase();
						columnPS.setString( 1, tableName );
					
						String columnName = tempColumn.getName().toUpperCase();
						columnPS.setString( 2, columnName );
					
						if( columnPS.execute() )
						{
							ResultSet columnRS = columnPS.getResultSet();
							if( columnRS.next() )
							{
								int sqlType = columnRS.getInt( "DATA_TYPE" );
								tempColumn.setSQLType( sqlType );
								String sqlTypeName = columnRS.getString( "TYPE_NAME" );
								tempColumn.setSQLTypeName( sqlTypeName );
								extract.addFinalColumn( tempColumn );
								found = true;
							}
							columnRS.close();
						}
						if( found ) break;
					}
					
					if( !found )
					{
						String columnName = tempColumn.getName().toUpperCase();
						throw new Exception( "column not found: "+ columnName );
					}
					break;
				}
				
				case Equals:
				default:
					break;
			}
		}
		tablesPS.close();
		columnPS.close();
		conn.close();
	}


	public void generate( File target, String packageName, String className, List<String> sql, Meta extract )
		throws Exception
	{
		Velocity.init();

		List<Column> columns = extract.getFinalColumns();
		List<Condition> conditions = extract.getConditions();
		
		VelocityContext context = new VelocityContext();
		context.put( "packageName", packageName );
		context.put( "className", className );
		context.put( "sql", sql );
		context.put( "columns", columns );
		context.put( "conditions", conditions );

		Template template = Velocity.getTemplate( "./src/fado/template/Select.vm" );
		
//		FileWriter fw = new FileWriter( new File( targetDir, className + "Select.java" ));
		FileWriter fw = new FileWriter( target );
//		StringWriter fw = new StringWriter();
		template.merge( context, fw );
//		System.out.println( fw );
		fw.close();

	}
	public static void dumpResultSet( ResultSet set )
		throws SQLException
	{
		System.out.println();
		ResultSetMetaData meta = set.getMetaData();
		int count = meta.getColumnCount();
		for( int i = 0; i < count; i++ )
		{
			String temp = meta.getColumnName( i + 1 );
			System.out.print( temp + "\t" );
		}
		System.out.print(  "\n--\n" );
		set.first();
		while( set.next() )
		{
			for( int i = 0; i < count; i++ )
			{
				Object temp = set.getObject( i + 1 );
				System.out.print( temp + "\t" );
			}
			System.out.print(  "\n" );
			
		}
		System.out.println();
	}
	
}
