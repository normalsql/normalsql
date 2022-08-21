package fado;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.StringReader;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;
import java.util.Date;


import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;



import static fado.parse.GenericSQLParser.RULE_select;
import static fado.parse.GenericSQLParser.RULE_insert;
import static fado.parse.GenericSQLParser.RULE_update;

public class
	Fado
{
	public static void main( String[] args )
//		throws Exception
	{
		try
		{
			FadoOptions options = new FadoOptions();
			options.parse( args );
			System.out.println( options );
			Fado fado = new Fado();
			fado.init( options );
		}
		catch( Exception e )
		{
			e.printStackTrace( System.out );
		}
		System.out.println( "done" );
	}

	Template _selectTemplate = null;
	Template _resultSetTemplate = null;
	Template _insertTemplate = null;
	Template _updateTemplate = null;
	Connection _conn = null;

	boolean _onlyParse = false;

	public void init( Properties props )
			throws Exception
	{
		FadoOptions options = new FadoOptions( props );
		init( options );
	}

	// TODO: If properties aren't found or defined, then onlyparse = true
	public void init( FadoOptions options )
		throws Exception
	{
		_onlyParse = options.getOnlyParse();

		if( !_onlyParse )
		{
			String driver = options.getDriver();
			if( driver != null )
			{
				Class.forName( driver );
			}
			String username = options.getUsername();
			String password = options.getPassword();
			String url = options.getUrl();
			if( url != null )
			{
				_conn = DriverManager.getConnection( url, username, password );
			}
			// TODO: verify database, eg 'SELECT 1'
			Statement s = _conn.createStatement();
			if( s.execute("SELECT 1" ))
			{
				ResultSet rs = s.getResultSet();
				System.out.println( rs );
			}

			Velocity.setProperty( RuntimeConstants.RESOURCE_LOADER, "classpath" );
			Velocity.setProperty( "classpath.resource.loader.class", ClasspathResourceLoader.class.getName() );
			Velocity.init();

			_selectTemplate = Velocity.getTemplate( "fado/template/Select.vm" );
			_resultSetTemplate = Velocity.getTemplate( "fado/template/ResultSet.vm" );
			_insertTemplate = Velocity.getTemplate( "fado/template/Insert.vm" );
			_updateTemplate = Velocity.getTemplate( "fado/template/Update.vm" );
		}

		String target = options.getTarget();
		File targetDir = new File( target );

		Stack<String> path = new Stack<>();
		String pkg = options.getPackage().trim();
		if( !"".equals( pkg ) )
		{
			// TODO regex to validate package
			String[] list = pkg.split( "\\." );
			for( String item : list )
			{
				path.push( item );
				targetDir = new File( targetDir, item );
			}
		}

		String source = options.getSource();
		File sourceDir = new File( source );
		if( !sourceDir.exists() )
		{
			String msg = sourceDir.getCanonicalPath() + " (filespec: " + source + ")";
			throw new FileNotFoundException( msg );
		}

		crawl( sourceDir, targetDir, path );

		if( _conn != null )
		{
			_conn.close();
		}
	}

	FileFilter sqlFilter = new FileFilter()
	{
		public boolean accept( File file )
		{
			if( file.isHidden() ) return false;
			if( file.isDirectory() ) return false;
			String name = file.getName();
			if( name.startsWith( "." ) ) return false;
			if( !name.endsWith( ".sql" ) ) return false;
			if( name.toLowerCase().contains( "create" ) ) return false;
			return true;
		}
	};

	FileFilter dirFilter = new FileFilter()
	{
		public boolean accept( File file )
		{
			if( file.isHidden() ) return false;
			if( file.getName().startsWith( "." ) ) return false;
			return file.isDirectory();
		}
	};

	public static String join( List<String> list, String sep )
	{
		StringBuilder sb = new StringBuilder();
		for( String item : list )
		{
			if( sb.length() > 0 )
			{
				sb.append( sep );
			}
			sb.append( item );
		}
		return sb.toString();
	}

	public static String join( String[] list, String sep )
	{
		StringBuilder sb = new StringBuilder();
		for( String item : list )
		{
			if( sb.length() > 0 )
			{
				sb.append( sep );
			}
			sb.append( item );
		}
		return sb.toString();
	}

	// TODO: Create command line option for this? eg. for a clean build operation
	private boolean _alwaysOverwrite = true;

	public void crawl( File sourceRoot, File targetRoot, Stack<String> path )
	{
		targetRoot.mkdirs();
		String pkg = join( path, "." );
		File[] sourceList = sourceRoot.listFiles( sqlFilter );
		for( File sourceFile : sourceList )
		{
			try
			{
				String name = getFileName( sourceFile );
				// TODO: Also check lastModified of generated ResultSet wrapper
				File targetFile = new File( targetRoot, name + ".java" );
				long a = sourceFile.lastModified();
				long b = targetFile.lastModified();
				if( _alwaysOverwrite || !targetFile.exists() || a > b )
				{
					if( _onlyParse )
					{
						System.out.println( "parsing " + sourceFile );
					}
					else
					{
						System.out.println( "generating " + targetFile + " (" + sourceFile + ")" );
					}
					process( pkg, name, sourceFile, targetRoot );
				}
				else
				{
					System.out.println( targetFile + " is current" );
				}
			}
			catch( Exception e )
			{
				System.out.println( "error processing : " + sourceFile );
				e.printStackTrace();
				// throw new FadoException( "file: " + sourceFile.toString(), e );
			}
		}

		File[] childDirList = sourceRoot.listFiles( dirFilter );
		for( File child : childDirList )
		{
			String name = child.getName();
			path.push( name );
			File targetDir = new File( targetRoot, name );
			crawl( child, targetDir, path );
			path.pop();
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

	public List<File> process( String pkg, String name, File sourceFile, File targetRoot )
		throws Exception
	{
		List<File> created = new ArrayList<File>();
//		try
//		{
//			CharStream chars = CharStreams.fromPath( Paths.get( sourceFile.getPath() ) );
//			GenericSQLLexer lexer = new GenericSQLLexer( chars );
//			CommonTokenStream tokens = new CommonTokenStream( lexer );
//			GenericSQLParser parser = new GenericSQLParser( tokens );
//
//			GenericSQLParser.StatementContext statementContext = parser.statement();
//
//			// TODO: getOriginalText() which includes all channels, eg whitespace, comments
//			String originalText = tokens.getText();
//			// Replace (escape) Java style comments in generated code, so /* */ becomes /@ @/.
//			String temp = originalText.replace( "/*", "/@" );
//			temp = temp.replace( "*/", "@/" );
//			List<String> originalLines = chopper( temp.trim() );
//
//			// Params for Velocity templates
//			HashMap map = new HashMap();
//			map.put( "packageName", pkg );
//			map.put( "className", name );
//			map.put( "date", new Date() );
//			map.put( "originalfile", sourceFile.toString() );
//			map.put( "originallines", originalLines );
//
//			for( GlobbingRuleContext child : statementContext.getRuleContexts( GlobbingRuleContext.class ) )
//			{
//				switch( child.getRuleIndex() )
//				{
//					case RULE_select:
//					{
//						SelectFadoStatement statement = new SelectFadoStatement();
//
//						extractSelect( child, statement );
//						String retooledSQL = tokens.getText().trim();
//						statement.setRetooledSQL( chopper( retooledSQL ) );
//
//						inspectDatabaseForSelect( _conn, statement );
//
//						map.put( "sql", chopper( retooledSQL ) );
//						// For PreparedStatement wrapper
//						map.put( "conditions", statement.getConditions() );
//						// For ResultSet wrapper
//						map.put( "columns", statement.getFinalColumns() );
//
//						File file1 = generate( _selectTemplate, map, targetRoot, name );
//						created.add( file1 );
//						File file2 = generate( _resultSetTemplate, map, targetRoot, "ResultSet" + name );
//						created.add( file2 );
//						break;
//					}
////					case RULE_insert:
////					{
////						InsertFadoStatement statement = new InsertFadoStatement();
////						statement.setName( name );
////						statement.setPackage( pkg );
////						statement.setOriginalFileName( sourceFile.toString() );
////						statement.setOriginalText( originalText );
////						statement.setOriginalLines( originalLines );
////
////						extractInsert( child, statement );
////						String retooledSQL = tokens.getText().trim();
////						statement.setRetooledSQL( chopper( retooledSQL ) );
////
////						Table table = statement.getTables().iterator().next();
////						inspectTableAndFields( _conn, table, statement.getFields() );
////
////						File file = generateInsert( statement, targetRoot, name );
////						created.add( file );
////						break;
////					}
////					case RULE_update:
////					{
////						UpdateFadoStatement statement = new UpdateFadoStatement();
////						statement.setName( name );
////						statement.setPackage( pkg );
////						statement.setOriginalFileName( sourceFile.toString() );
////						statement.setOriginalText( originalText );
////						statement.setOriginalLines( originalLines );
////
////						extractUpdate( child, statement );
////						String retooledSQL = tokens.getText().trim();
////						statement.setRetooledSQL( chopper( retooledSQL ) );
////
////						Table table = statement.getTables().iterator().next();
////						inspectTableAndFields( _conn, table, statement.getFields() );
////						inspectTableAndFields( _conn, table, statement.getConditions() );
////
////						File file = generateUpdate( statement, targetRoot, name );
////						created.add( file );
////						break;
////					}
//					default:
//						break;
//				}
//			}
//		}
//		catch( Exception e )
//		{
//			throw e;
//		}
		return created;
	}


//	public void extractSelectTables( GlobbingRuleContext selectNode, SelectFadoStatement statement )
//	{
//		{
//			List<GlobbingRuleContext> list = selectNode.findContexts( "from/fromItem" );
//			for( GlobbingRuleContext item : list )
//			{
//				GlobbingRuleContext tableRef = item.findFirst( "tableRef" );
//				String databaseName = tableRef.findFirstString( "databaseName" );
//				String tableName = tableRef.findFirstString( "**/tableName" );
//				String alias = item.findFirstString( "alias" );
//				Table table = new Table( databaseName, tableName, alias );
//				statement.addTable( table );
//			}
//		}
//		{
//			List<GlobbingRuleContext> list = selectNode.findContexts( "joinList/join" );
//			for( GlobbingRuleContext item : list )
//			{
//				GlobbingRuleContext tableRef = item.findFirst( "**/tableRef" );
//				String databaseName = tableRef.findFirstString( "databaseName" );
//				String tableName = tableRef.findFirstString( "**/tableName" );
//				String alias = item.findFirstString( "alias" );
//				Table table = new Table( databaseName, tableName, alias );
//				statement.addTable( table );
//			}
//		}
//	}




//	private void extractInsert( GlobbingRuleContext insertNode, InsertFadoStatement statement )
//		throws Exception
//	{
//		if( _onlyParse ) return;
//		GlobbingRuleContext tableRef = insertNode.findFirst( "into/tableRef" );
//		String databaseName = tableRef.findFirstString( "databaseName" );
//		String tableName = tableRef.findFirstString( "**/tableName" );
//		String alias = null;
//		Table table = new Table( databaseName, tableName, alias );
//		statement.addTable( table );
//
//		ArrayList<Field> fields = new ArrayList<Field>();
//		List<GlobbingRuleContext> list = insertNode.findContexts( "columnList/columnName" );
//		for( GlobbingRuleContext item : list )
//		{
//			String name = item.getText();
//			fields.add( new Field( name ) );
//		}
//
//		List<GlobbingRuleContext> literals = insertNode.findContexts( "values/literal" );
//
//		if( fields.size() != literals.size() )
//		{
//			throw new Exception( "mismatch, fields: " + fields.size() + ", literals: " + literals.size() );
//		}
//
//		int nth = 0;
//		for( GlobbingRuleContext node : literals )
//		{
//			String literal = node.getText();
//			literal = trimQuotes( literal );
//
//			node.convertToInputParam();
//
//			Field field = fields.get( nth );
//			field.setLiteral( literal );
//			nth++;
//		}
//		statement.setFields( fields );
//	}




	public File generate( Template template, Map map, File targetRoot, String name )
		throws Exception
	{
		VelocityContext context = new VelocityContext( map );
		File targetFile = new File( targetRoot, name + ".java" );
		FileWriter writer = new FileWriter( targetFile );
		template.merge( context, writer );
		writer.flush();
		writer.close();

		return targetFile;
	}


//	public File generateSelect( SelectFadoStatement statement, File targetRoot, String name )
//		throws Exception
//	{
//		VelocityContext context = new VelocityContext();
//		context.put( "packageName", statement.getPackage() );
//		context.put( "className", statement.getName() );
//		context.put( "sql", statement.getRetooledSQL() );
//		context.put( "conditions", statement.getConditions() );
//		context.put( "date", new Date() );
//		context.put( "originalfile", statement.getOriginalFileName() );
//		context.put( "originallines", statement.getOriginalLines() );
//
//		File targetFile = new File( targetRoot, name + ".java" );
//		FileWriter writer = new FileWriter( targetFile );
//		_selectTemplate.merge( context, writer );
//		writer.flush();
//		writer.close();
//
//		return targetFile;
//	}
//
//	public File generateResultSet( SelectFadoStatement statement, File targetRoot, String name )
//		throws Exception
//	{
//		VelocityContext context = new VelocityContext();
//		context.put( "packageName", statement.getPackage() );
//		context.put( "className", statement.getName() );
//		context.put( "sql", statement.getRetooledSQL() );
//		context.put( "columns", statement.getFinalColumns() );
//		context.put( "date", new Date() );
//		context.put( "originalfile", statement.getOriginalFileName() );
//		context.put( "originallines", statement.getOriginalLines() );
//
//		File targetFile = new File( targetRoot, name + "ResultSet.java" );
//		FileWriter writer = new FileWriter( targetFile );
//		_resultSetTemplate.merge( context, writer );
//		writer.flush();
//		writer.close();
//
//		return targetFile;
//	}

//	public File generateInsert( InsertFadoStatement statement, File targetRoot, String name )
//		throws Exception
//	{
//		VelocityContext context = new VelocityContext();
//		context.put( "packageName", statement.getPackage() );
//		context.put( "className", statement.getName() );
//		context.put( "sql", statement.getRetooledSQL() );
//		context.put( "fields", statement.getFields() );
//		context.put( "date", new Date() );
//		context.put( "originalfile", statement.getOriginalFileName() );
//		context.put( "originallines", statement.getOriginalLines() );
//
//		File targetFile = new File( targetRoot, name + ".java" );
//		FileWriter writer = new FileWriter( targetFile );
//		_insertTemplate.merge( context, writer );
//		writer.flush();
//		writer.close();
//
//		return targetFile;
//	}
//
//	public File generateUpdate( UpdateFadoStatement statement, File targetRoot, String name )
//		throws Exception
//	{
//		VelocityContext context = new VelocityContext();
//		context.put( "packageName", statement.getPackage() );
//		context.put( "className", statement.getName() );
//		context.put( "sql", statement.getRetooledSQL() );
//		context.put( "fields", statement.getFields() );
//		context.put( "conditions", statement.getConditions() );
//		context.put( "date", new Date() );
//		context.put( "originalfile", statement.getOriginalFileName() );
//		context.put( "originallines", statement.getOriginalLines() );
//
//		File targetFile = new File( targetRoot, name + ".java" );
//		FileWriter writer = new FileWriter( targetFile );
//		_updateTemplate.merge( context, writer );
//		writer.flush();
//		writer.close();
//
//		return targetFile;
//	}

//	public String trimQuotes( String text )
//	{
//		String result = text;
//		int len = text.length();
//		// Parser ensures token has quotes front and back
//		if( text.indexOf( '[' ) > -1 || text.indexOf( '"' ) > -1 || text.indexOf( '\'' ) > -1 )
//		{
//			result = text.substring( 1, len - 1 );
//		}
//		return result;
//	}
//
//	public List<String> chopper( String sql )
//	{
//		StringReader sr = new StringReader( sql );
//		LineReader lr = new LineReader( sr );
//		List<String> result = lr.toArray();
//		return result;
//	}

//	public static void dumpResultSet( ResultSet rs )
//			throws SQLException
//	{
//		System.out.println();
//		ResultSetMetaData meta = rs.getMetaData();
//		int count = meta.getColumnCount();
//		for( int i = 0; i < count; i++ )
//		{
//			String temp = meta.getColumnName( i + 1 );
//			System.out.print( temp + "\t" );
//		}
//		System.out.print( "\n--\n" );
////		set.first();
//		while( rs.next() )
//		{
//			for( int i = 0; i < count; i++ )
//			{
//				Object temp = rs.getObject( i + 1 );
//				System.out.print( temp + "\t" );
//			}
//			System.out.print( "\n" );
//
//		}
//		System.out.println();
//	}

}
