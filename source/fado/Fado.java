package fado;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.antlr.runtime.ANTLRReaderStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.Token;
import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.jxpath.ri.JXPathContextReferenceImpl;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import fado.meta.Column;
import fado.meta.Condition;
import fado.meta.IN;
import fado.meta.InsertColumn;
import fado.meta.InsertMeta;
import fado.meta.SelectMeta;
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
		options.parse( args );
		System.out.println( options );
		Fado fado = new Fado();
		fado.init( options );
	}
	
	Template _selectTemplate = null;
	Template _insertTemplate = null;
	Connection _conn = null;
	
	public void init( FadoOptions options )
		throws Exception
	{
		String driver = options.getDriver();
		String url = options.getUrl();
		String username = options.getUsername();
		String password = options.getPassword();
		Class.forName( driver );
		_conn = DriverManager.getConnection( url, username, password );

		Velocity.setProperty( RuntimeConstants.RESOURCE_LOADER, "classpath" );
		Velocity.setProperty( "classpath.resource.loader.class", ClasspathResourceLoader.class.getName() );
		Velocity.init();

		_selectTemplate = Velocity.getTemplate( "fado/template/Select.vm" );
		_insertTemplate = Velocity.getTemplate( "fado/template/Insert.vm" );

		String target = options.getTarget();
		File targetDir = new File( target );
		
		String source = options.getSource();
		File sourceDir = new File( source );
		if( !sourceDir.exists() )
		{
			String msg = sourceDir.getCanonicalPath().toString() + " (filespec: " + source + ")";
			throw new FileNotFoundException( msg );
		}
		ArrayList<String> path = new ArrayList<String>();
		crawl( sourceDir, targetDir, path );
		_conn.close();

	}
	
	FileFilter sqlFilter = new FileFilter() 
	{
		public boolean accept( File file )
		{
			if( file.isHidden() ) return false;
			if( file.isDirectory() ) return false;
			String name = file.getName();
			if( name.startsWith( "." )) return false;
			return name.endsWith( ".sql" );
		}
	};
	
	FileFilter dirFilter = new FileFilter() 
	{
		public boolean accept( File file )
		{
			if( file.isHidden() ) return false;
			if( file.getName().startsWith( "." )) return false;
			return file.isDirectory();
		}
	};
	
	public String join( List<String> list, String sep )
	{
		StringBuilder sb = new StringBuilder();
		for( String item : list )
		{
			if( sb.length() > 0 ) sb.append( sep );
			sb.append( item );
		}
		return sb.toString();
	}
	
	public void crawl( File sourceRoot, File targetRoot, List<String> path )
//		throws FadoException
	{
		targetRoot.mkdirs();
		String pkg = join( path, "." );
		File[] sourceList = sourceRoot.listFiles( sqlFilter );
		for( File sourceFile : sourceList )
		{
			try
			{
				String name = getFileName( sourceFile );
				FileReader reader = new FileReader( sourceFile );
				File targetFile = new File( targetRoot, name + ".java" );
				FileWriter writer = new FileWriter( targetFile );
				extract( pkg, name, reader, writer );
				writer.flush();
				writer.close();
				reader.close();
			}
			catch( Exception e )
			{
				System.out.println( "error processing : " + sourceFile );
				e.printStackTrace();
//				throw new FadoException( "file: " + sourceFile.toString(), e );
			}
		}
		
		File[] childDirList = sourceRoot.listFiles( dirFilter );
		for( File child : childDirList )
		{
			String name = child.getName();
			path.add( name );
			File targetDir = new File( targetRoot, name );
			crawl( child, targetDir, path );
			System.out.println( child.toString() + " : " + child.getName() );
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
	
	private void extract( String pkg, String name, Reader reader, Writer writer ) 
		throws Exception
	{
		
		ParseTreeBuilder builder = new ParseTreeBuilder( "GenericSQL.g" );
		CharStream cs = new ANTLRReaderStream( reader );
		GenericSQLLexer lexer = new GenericSQLLexer( cs );
		CommonTokenStream tokens = new CommonTokenStream( lexer );
		GenericSQLParser parser = new GenericSQLParser( tokens, builder );

		parser.statement();
		
		FadoParseNode source = builder.getTree();
		
//		System.out.println( "string tree: " + builder.getTree().toStringTree() );
//		System.out.println( "input string: " + builder.getTree().toInputString() );
		
		ParseNodePointerFactory factory = new ParseNodePointerFactory();
		JXPathContextReferenceImpl.addNodePointerFactory( factory );
		
		JXPathContext commandContext = JXPathContext.newContext( source );

		try
		{
			Object selectNode =  commandContext.selectSingleNode( "/statement/select" );
			SelectMeta meta = new SelectMeta();
			meta.setName( name );
			meta.setPackage( pkg );
			extractSelect( source, meta );
			inspectDatabaseForSelect( _conn, meta );
			
			String retooledSQL = builder.getTree().toInputString();
			List<String> choppedSQL = chopper( retooledSQL.trim() );
			generateSelect( meta, choppedSQL, writer );
		}
		catch( Exception e )
		{
			// Jaxen sucks
		}
		
		try
		{
			Object insertNode =  commandContext.selectSingleNode( "/statement/insert" );
			try
			{
				InsertMeta meta = new InsertMeta();
				meta.setName( name );
				meta.setPackage( pkg );
				extractInsert( source, meta );
				inspectDatabaseForInsert( _conn, meta );
				String retooledSQL = builder.getTree().toInputString();
				List<String> choppedSQL = chopper( retooledSQL.trim() );
				generateInsert( meta, choppedSQL, writer );
			}
			catch( Exception e )
			{
				e.printStackTrace();
			}
		}
		catch( Exception e )
		{
			// Jaxen sucks
		}
	}
	

	private void extractSelect( FadoParseNode source, SelectMeta extract )
		throws Exception
	{
		// Tables
		extractSelectTables( source, extract );
		// Columns
		extractSelectColumns( source, extract );
		// Parameters
		extractSelectParams( source, extract );
	}
	
	
	// TODO support unary literals, need fix for '?' knockouts
	private void extractInsert( FadoParseNode source, InsertMeta meta )
		throws Exception
	{
		JXPathContext statementContext = JXPathContext.newContext( source );
		String table = (String) statementContext.selectSingleNode( "/statement/insert/into/tableRef/text()" );
		meta.setTable( table );
		
		ArrayList<InsertColumn> columns = new ArrayList<InsertColumn>();
		List list = statementContext.selectNodes( "/statement/insert/columnList/columnName" );
		for( Object item : list )
		{
			String name = getTokenText( (FadoParseNode) item );
			columns.add( new InsertColumn( name ));
		}
		
		List argh = statementContext.selectNodes( "/statement/insert/values/literal" );
		
		if( columns.size() != argh.size() )
		{
			throw new Exception( "mismatch, columns: " + columns.size() + ", literals: " + argh.size() );
		}
		
		int nth = 0;
		for( Object item : argh )
		{
			FadoParseNode node = (FadoParseNode) item;
			String literal = getTokenText( node );
			literal = trimQuotes( literal );
			
			// Replace original value with JDBC parameter '?'
			setTokenText( node, "?" ); 
			
			InsertColumn col = columns.get( nth );
			col.setLiteral( literal );
			nth++;
		}
		meta.setColumns( columns );
	}
	
	public void extractSelectTables( FadoParseNode source, SelectMeta meta )
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
	
	public void extractSelectColumns( FadoParseNode source, SelectMeta meta )
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
	
	public void extractSelectParams( FadoParseNode source, SelectMeta meta )
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
				extractSelectComparisonParams( (FadoParseNode) comparison.get( 0 ), meta );
			}
			List in = conditionContext.selectNodes( "in" );
			if( in.size() == 1 )
			{
				extractSelectINParams( (FadoParseNode) in.get( 0 ), meta );
			}
			List between = conditionContext.selectNodes( "between" );
		}
	}
	
	// Comparisons, eg column = 'abc'
	public void extractSelectComparisonParams( FadoParseNode comparison, SelectMeta meta )
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
	public void extractSelectINParams( FadoParseNode in, SelectMeta meta ) 
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

	public void inspectDatabaseForSelect( Connection conn, SelectMeta extract )
		throws Exception 
	{
		
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
	}


	public void inspectDatabaseForInsert( Connection conn, InsertMeta extract )
		throws Exception 
	{
		PreparedStatement tablesPS = conn.prepareStatement( "SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = ?" );
		PreparedStatement columnPS = conn.prepareStatement( "SELECT DATA_TYPE, TYPE_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = ? AND COLUMN_NAME = ?" );

		String tableName = extract.getTable().toUpperCase();
		tablesPS.setString( 1, tableName );
		if( tablesPS.execute() )
		{
			ResultSet gorp = tablesPS.getResultSet();
			if( gorp.next() )
			{
				columnPS.setString( 1, tableName );
				
				for( InsertColumn column : extract.getColumns() )
				{
					
					String columnName = column.getName().toUpperCase();
					columnPS.setString( 2, columnName );
				
					if( columnPS.execute() )
					{
						ResultSet columnRS = columnPS.getResultSet();
						if( columnRS.next() )
						{
							int sqlType = columnRS.getInt( "DATA_TYPE" );
							column.setSQLType( sqlType );
							String sqlTypeName = columnRS.getString( "TYPE_NAME" );
							column.setSQLTypeName( sqlTypeName );
						}
						else
						{
							throw new Exception( "column not found: "+ columnName );
						}
						columnRS.close();
					}
				}
			}
			else
			{
				throw new Exception( "table not found: "+ tableName );
			}
		}
		
		tablesPS.close();
		columnPS.close();
	}


	public void generateSelect( SelectMeta meta, List<String> sql, Writer writer )
		throws Exception
	{
		List<Column> columns = meta.getFinalColumns();
		List<Condition> conditions = meta.getConditions();
		
		VelocityContext context = new VelocityContext();
		context.put( "packageName", meta.getPackage() );
		context.put( "className", meta.getName() );
		context.put( "sql", sql );
		context.put( "columns", columns );
		context.put( "conditions", conditions );
		context.put( "date", new Date() );
		
		_selectTemplate.merge( context, writer );
	}

	public void generateInsert( InsertMeta meta, List<String> sql, Writer writer )
		throws Exception
	{
		List<InsertColumn> columns = meta.getColumns();
		
		VelocityContext context = new VelocityContext();
		context.put( "packageName", meta.getPackage() );
		context.put( "className", meta.getName() );
		context.put( "sql", sql );
		context.put( "columns", columns );
		context.put( "date", new Date() );
		
		_insertTemplate.merge( context, writer );
	}

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
	
	public List<String> chopper( String sql )
	{
		StringReader sr = new StringReader( sql );
		LineReader lr = new LineReader( sr );
		List<String> result = lr.toArray();
		return result;
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
