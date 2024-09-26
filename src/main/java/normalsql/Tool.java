// Copyright 2010-2023 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import static java.nio.file.FileVisitResult.*;
import static normalsql.Work.asMap;

import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Connection;
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
	public static void main( String[] args )
	{
		int exit = 0;
		String[] blah = { "-j", "jdbc:h2:mem:", "-u", "sa", "--password", "root" };
		args = blah;

		try
		{
			CLI cli = new CLI( blah );
//			CLI cli = new CLI( args );

			Config c = new Config();
			c.url       = cli.getOptional( String.class, "-j", "--url" );
//			c.url       = cli.getOptional( null, "-j", "--url" );
			c.username  = cli.getOptional( null, "-u", "--username" );
			c.password  = cli.getOptional( null, "-p", "--password" );
			c.source    = cli.getOptional( ".", missing, "-s", "--source" );
			c.target    = cli.getOptional( c.source, missing, "-t", "--target" );
			c.pkg       = cli.getOptional( null, "-k", "--package" );
			c.extension = cli.getOptional( ".sql", missing, "-e", "--extension" );
//			boolean help = cli.getFlag( "-h", "--help" );
			if( !cli.nu.isEmpty() )
			{
				throw new IllegalArgumentException( "don't understand argument" + cli.nu );
			}

			c.validate();

			var map = asMap( c );
			System.out.println( map );

			Tool tool = new Tool();
			tool.go( c );

		}
		catch( Exception e )
		{
			e.printStackTrace();
			exit = 1;
		}

		System.exit( exit );
	}

	// TODO: Detect if targets need to be (re)created.

	static String extension = ".sql";
	public static void old_main( String[] args ) throws Exception
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

	public static void init( Props props )
		throws Exception
	{
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

		if( _conn != null )
		{
			_conn.close();
		}
	}

	public void go( Config config )
		throws Exception
	{
//		Path source = Paths.get( config.source ).toAbsolutePath();
//		Path target = Paths.get( config.target ).toAbsolutePath();
		var files = new ArrayList<Path>();
		Files.walkFileTree( config.sourcePath,
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

		var works = resolve( files, config.sourcePath, config.targetPath );

		var worker = new Worker( _conn );
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
			var work = new Work();
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
