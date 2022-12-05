package fado;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.*;
import java.util.*;
import java.util.Date;


import fado.voyager.Voyager;
import fado.voyager.Work;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;


public class
	Fado
{
	public static void main( String[] args )
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

//	boolean _onlyParse = false;

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
//		_onlyParse = options.getOnlyParse();

//		if( !_onlyParse )
		{
			String driver = options.getDriver();
			if( driver != null )
			{
				Class.forName( driver );
			}
			String username = options.getUsername();
			String password = options.getPassword();
//			String url = options.getUrl();
//			if( url != null )
//			{
//				_conn = DriverManager.getConnection( url, username, password );
//			}
//			// TODO: verify database, eg 'SELECT 1'
//			Statement s = _conn.createStatement();
//			if( s.execute("SELECT 1" ))
//			{
//				ResultSet rs = s.getResultSet();
//				System.out.println( rs );
//			}

//			Velocity.setProperty( RuntimeConstants.RESOURCE_LOADER, "classpath" );
//			Velocity.setProperty( "classpath.resource.loader.class", ClasspathResourceLoader.class.getName() );
//			Velocity.init();
//
//			_selectTemplate = Velocity.getTemplate( "fado/template/Select.vm" );
//			_resultSetTemplate = Velocity.getTemplate( "fado/template/ResultSet.vm" );
//			_insertTemplate = Velocity.getTemplate( "fado/template/Insert.vm" );
//			_updateTemplate = Velocity.getTemplate( "fado/template/Update.vm" );
		}

//			throw new FileNotFoundException( msg );

		// TODO verify source exists
		Path source = Paths.get( "/Users/jasonosgood/Projects/fado/test" );
		Path target = Paths.get( "/Users/jasonosgood/Projects/fado/test" );
		crawl( source, target );

		if( _conn != null )
		{
			_conn.close();
		}
	}

	public void crawl( Path sourceRoot, Path targetRoot )
	{
		try
		{
			Files.walkFileTree( sourceRoot, new SimpleFileVisitor<Path>()
			{
				@Override
				public FileVisitResult visitFile( Path sourceFile, BasicFileAttributes attrs )
				{
					String sourceFileName = sourceFile.getFileName().toString();
					// Skip "hidden" dotfiles
					if( sourceFileName.startsWith( "." ) ) return FileVisitResult.CONTINUE;

					String extension = ".sql";
					if( sourceFileName.toLowerCase().endsWith( extension ))
					{
						int len = sourceFileName.length() - extension.length();
						String className = sourceFileName.substring( 0, len );
						Path sourceDir = sourceFile.getParent();
						Path packagePath = sourceRoot.relativize( sourceDir );
						String packageName = packagePath.toString().replace( File.separatorChar, '.' );
						Path targetDir = targetRoot.resolve( packagePath );
						Path targetFile = targetDir.resolve( className + ".java" );

						try
						{
							boolean go = _alwaysOverwrite;
							if( Files.notExists( targetDir ))
							{
								Files.createDirectories( targetDir );
								go = true;
							}
							else if( !go || Files.notExists( targetFile ))
							{
								go = true;
							}

							// TODO compare last modified
							// TODO flag for always overwrite
							if( go )
							{
								Work work = new Work();
								work.sourceFile = sourceFile;
								work.targetFile = targetFile;
								work.packageName = packageName;
								work.className = className;

								new Voyager().process( work );
							}

						}
						catch( Exception ioe )
						{
							ioe.printStackTrace();
						}
					}
					return FileVisitResult.CONTINUE;
				}
			} );
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
	}

	// TODO: Create command line option for this? eg. for a clean build operation
	private boolean _alwaysOverwrite = true;

//						System.out.println( "parsing " + sourceFile );
//						System.out.println( "generating " + targetFile + " (" + sourceFile + ")" );
//					System.out.println( targetFile + " is current" );
//				System.out.println( "error processing : " + sourceFile );


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


}
