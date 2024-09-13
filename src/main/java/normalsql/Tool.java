// Copyright 2010-2023 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import static java.nio.file.FileVisitResult.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/*
	TODO:

		find and log all files
		parse all found files
		add just the DML statements to the work list
		add just the parseable statements to the work list
		log errors
		command line option for filelist
		command line option for extension filter

		log work list if diff from found list

		verify DB connection
		FUTURE: infer dialect from JDBC connection string
		FUTURE: infer dialect by asking DB itself

		test each statement in work list
			maybe use EXPLAIN
			could be DB specific
		generate output for each success

		setClientInfo to "normalsql"
			https://franckpachot.medium.com/you-should-set-ocsid-clientid-e00cb81ed7e2

			configurable
			or maybe default to appname, if possible

 */
	
public class
	Tool
{
	// TODO: Detect if targets need to be (re)created.

	static String extension = ".sql";
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
		Props props = Props.load( reader );
		props.setPropertiesFile( file.toURI().toURL() );

		init(props);
		System.out.println( "done" );
		System.exit( 0 );
	}

	static Connection _conn = null;

	public static void init( Props props)
		throws Exception
	{
		{
			String driver = props.getDriver();
			if( driver == null )
			{
				System.err.println( "JDBC driver class name cannot be null" );
				System.exit( -1 );
			}
			String url = props.getURL();
			if( url == null )
			{
				System.err.println( "JDBC URL cannot be null" );
				System.exit( -1 );
			}

			String username = props.getUsername();
			String password = props.getPassword();

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
		// TODO pull source & target from command line options

		Path source = Paths.get( "" ).toAbsolutePath();
		var files = new ArrayList<Path>();
		Files.walkFileTree( source,
			new SimpleFileVisitor<>()
			{
				@Override
				public FileVisitResult visitFile( Path file, BasicFileAttributes attrs )
				{
					var name = file.getFileName().toString();
					// Skip "hidden" dotfiles
					var hidden = name.startsWith( "." );
					var match = name.toLowerCase().endsWith( extension );
					if( !hidden && match )
					{
						files.add( file );
					}
					return CONTINUE;
				}

				public FileVisitResult preVisitDirectory( Path dir, BasicFileAttributes attrs )
				{
					var name = dir.getFileName();
					var hidden = name.startsWith( "." );
					return hidden ? SKIP_SUBTREE : CONTINUE;
				}
			}
		);

		System.out.printf( "files found %d\n", files.size() );


		Path target = source;
		var works = resolve( files, source, target );

		Worker worker = new Worker( _conn );
		for( var work : works )
		{
			worker.process( work );
		}

//
//			// TODO tidy this up. Remove boolean go.
//			//  ... If file exists and _alwaysOverwrite == false, exit early
//			boolean go = _alwaysOverwrite;
//			if( Files.notExists( targetDir ) )
//			{
//				Files.createDirectories( targetDir );
//				go = true;
//			}
//			else if( !go || Files.notExists( targetFile ) )
//			{
//				go = true;
//			}
//
//			// TODO compare last modified
//			// TODO flag for always overwrite
//			if( go )
//			{
//				worker.process( work );
//			}
//		}
//	}

		if( _conn != null )
		{
			_conn.close();
		}
	}

	public void go( Config config )
		throws Exception
	{
		{
			if( config.url == null )
			{
				throw new NullPointerException( "missing JDBC URL" );
			}

			_conn = DriverManager.getConnection( config.url, config.username, config.password );

			// TODO is this best way to confirm JDBC config?
			Statement s = _conn.createStatement();
			if( s.execute( "SELECT 1" ))
			{
				ResultSet rs = s.getResultSet();
				System.out.println( "JDBC connection verified" );
			}
		}

		// TODO verify source exists
		// TODO pull source & target from command line options

//		Path source = Paths.get( config.source ).toAbsolutePath();
//		Path target = Paths.get( config.target ).toAbsolutePath();
		var files = new ArrayList<Path>();
		Files.walkFileTree( config.source,
			new SimpleFileVisitor<>()
			{
				@Override
				public FileVisitResult visitFile( Path file, BasicFileAttributes attrs )
				{
					var name = file.getFileName().toString();
					// Skip "hidden" dotfiles
					var hidden = name.startsWith( "." );
					var match = name.toLowerCase().endsWith( extension );
					if( !hidden && match )
					{
						files.add( file );
					}
					return CONTINUE;
				}

				public FileVisitResult preVisitDirectory( Path dir, BasicFileAttributes attrs )
				{
					var name = dir.getFileName();
					var hidden = name.startsWith( "." );
					return hidden ? SKIP_SUBTREE : CONTINUE;
				}
			}
		);

		System.out.printf( "files found %d\n", files.size() );

		var works = resolve( files, config.source, config.target );

		Worker worker = new Worker( _conn );
		for( var work : works )
		{
			worker.process( work );
		}

		// TODO compare last modified
		if( _conn != null )
		{
			_conn.close();
		}
	}

	/**
	 * Generates list of Work (to be done) from source files found
	 *
	 * @param files
	 * @param sourceRoot
	 * @param targetRoot
	 * @return
	 */
	public static List<Work> resolve( List<Path> files, Path sourceRoot, Path targetRoot )
		throws IOException
	{
		var works = new ArrayList<Work>();

		for( var sourceFile : files )
		{
			Work work = new Work();
			work.sourceFile = sourceFile;
			work.sourceDir = sourceFile.getParent();
			// Duplicate directory structure for output
			Path packagePath = sourceRoot.relativize( work.sourceDir );
			work.targetDir = targetRoot.resolve( packagePath );
			if( Files.notExists( work.targetDir ))
			{
				Files.createDirectories( work.targetDir );
			}

			// infers package name from directory structure
			work.packageName = packagePath.toString().replace( File.separatorChar, '.' );

			var clazz = getBaseName( sourceFile );
			work.statementClassName = clazz;
			// TODO custom suffix for ResultSet
			work.resultSetClassName = work.statementClassName + "ResultSet";

			// TODO why isn't targetFile for ResultSet class also here?
			work.targetFile = work.targetDir.resolve( clazz + ".java" );

			works.add( work );
		}
		return works;
	}



//	// "How to get the filename without the extension in Java?"
//	// https://stackoverflow.com/a/29083921
//	private static final Pattern ext = Pattern.compile( "(?<=.)\\.[^.]+$" );
//
//	public static String getBaseName( File file )
//	{
//		return ext.matcher( file.getName() ).replaceAll( "" );
//	}

	public static String getBaseName( Path file )
	{
		var name = file.getFileName().toString();
		int len = name.length() - extension.length();
		var base = name.substring( 0, len );
		return base;
	}
}
