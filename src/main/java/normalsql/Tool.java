// Copyright 2010-2023 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql;

import java.io.File;
import java.io.FileReader;
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

 */
	
public class
	Tool
{
	// TODO: Create command line option for this? eg. for a clean build operation
	static boolean _alwaysOverwrite = true;

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
		Config config = Config.load( reader );
		config.setPropertiesFile( file.toURI().toURL() );

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

	public static List<Work> resolve(List<Path> files, Path sourceRoot, Path targetRoot )
	{
		var works = new ArrayList<Work>();

		for( var sourceFile : files )
		{
			Work work = new Work();
			work.sourceFile = sourceFile;
			work.sourceDir = sourceFile.getParent();

			var clazz = getBaseName( sourceFile );
			work.statementClassName = clazz;
			work.resultSetClassName = work.statementClassName + "ResultSet";

			Path packagePath = sourceRoot.relativize( work.sourceDir );
			work.packageName = packagePath.toString().replace( File.separatorChar, '.' );

			work.targetDir = targetRoot.resolve( packagePath );
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
