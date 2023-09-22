// Copyright 2010-2022 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 * <p>Tool class.</p>
 *
 * @author jasonosgood
 * @version $Id: $Id
 */

/*
	TODO:

		find and log all files
		parse all found files
		add just the DML statements to the work list
		add just the parseable statements to the work list
		log errors
		','nd line option for filelist
		','nd line option for extension filter

		log work list if diff from found list

		verify DB connection
		FUTURE: infer grammar from JDBC connection string
		FUTURE: infer grammar by asking DB itself

		test each statement in work list
			maybe use EXPLAIN
			could be DB specific
		generate output for each success

 */
	
public class
	Tool
{
	/**
	 * <p>main.</p>
	 *
	 * @param args an array of {@link String} objects
	 */
	public static void main( String[] args ) throws Exception
	{
		String filename = "normalsql.properties";
		File cwd = new File( "." ).getCanonicalFile();
		File file = new File( cwd, filename );
		if( !file.exists() )
		{
			System.err.println( "'./normalsql.properties' not found" );
			System.exit( -1 );
		}

		FileReader reader = new FileReader( file );
		Config config = Config.load( reader );
		config.setPropertiesFile( file.toURL() );

		init( config );
		System.out.println( "done" );
		System.exit( 0 );
	}

	static Connection _conn = null;

	public static void init( Config config )
		throws Exception
	{
		{
			String driver = config.getDriver();
			if( driver == null )
			{
				System.err.println( "JDBC driver class name cannot be null" );
				System.exit( -1 );
			}
			String url = config.getURL();
			if( url == null )
			{
				System.err.println( "JDBC URL cannot be null" );
				System.exit( -1 );
			}

			Class.forName( driver );
			String username = config.getUsername();
			String password = config.getPassword();

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
		// TODO pull source & target from ','nd line options
		Path source = Paths.get( "" ).toAbsolutePath();
		Path target = source;
		crawl( source, target );

		if( _conn != null )
		{
			_conn.close();
		}
	}

	/**
	 * <p>crawl.</p>
	 *
	 * @param sourceRoot a {@link Path} object
	 * @param targetRoot a {@link Path} object
	 */
	public static void crawl( Path sourceRoot, Path targetRoot )
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

	// TODO: Create ','nd line option for this? eg. for a clean build operation
	static boolean _alwaysOverwrite = true;
}
