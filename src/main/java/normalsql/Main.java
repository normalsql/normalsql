// Copyright 2010-2022 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class
	Main
{
	public static void main( String[] args )
	{
		try
		{
			CommandLineOptions options = new CommandLineOptions();
			options.parse( args );
			System.out.println( options );
			Main main = new Main();
			main.init( options );
		}
		catch( Exception e )
		{
			e.printStackTrace( System.out );
		}
		System.out.println( "done" );
	}

	Connection _conn = null;

//	boolean _onlyParse = false;

//	public void init( Config props )
//		throws Exception
//	{
//		CommandLineOptions options = new CommandLineOptions( props );
//		init( options );
//	}

	// TODO: If properties aren't found or defined, then onlyparse = true
	public void init( CommandLineOptions options )
		throws Exception
	{
//		_onlyParse = options.getOnlyParse();

//		if( !_onlyParse )
		{
			String driver = options.getDriver();
			if( driver == null )
			{
				throw new NullPointerException( "JDBC driver class name cannot be null" );
			}

			Class.forName( driver );
			String username = options.getUsername();
			String password = options.getPassword();
			String url = options.getUrl();
			if( url == null )
			{
				throw new NullPointerException( "JDBC url cannot be null" );
			}

			_conn = DriverManager.getConnection( url, username, password );

			// TODO is this best way to confirm JDBC config?
			Statement s = _conn.createStatement();
			if( s.execute( "SELECT 1" ))
			{
				ResultSet rs = s.getResultSet();
				System.out.println( rs );
			}
		}

		// TODO verify source exists
		Path source = Paths.get( "/Users/jasonosgood/Projects/normalsql/test" );
		Path target = Paths.get( "/Users/jasonosgood/Projects/normalsql/test" );
		crawl( source, target );

		if( _conn != null )
		{
			_conn.close();
		}
	}

	public void crawl( Path sourceRoot, Path targetRoot )
	{
		Worker worker = new Worker( _conn );
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
								work.targetDir = targetDir;
								work.packageName = packageName;
								work.statementClassName = className;
								work.resultSetClassName = className + "ResultSet";
								worker.process( work );
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
}
