// Copyright 2010-2024 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import static java.nio.file.FileVisitResult.*;
import static normalsql.Work.asMap;

import java.nio.file.attribute.BasicFileAttributes;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


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
		int exit = -1;
//		String[] blah = { "-j", "jdbc:h2:mem:", "-u", "sa", "--password", "root", "--count", "123" };
//		args = blah;

		try
		{
			CLI cli = new CLI( args );

			Config cfg = new Config();
			cfg.url       = cli.getOptional( "", "-j", "--url" );
			cfg.username  = cli.getOptional( "", "-u", "--username" );
			cfg.password  = cli.getOptional( "", "-p", "--password" );
			cfg.source    = cli.getOptional( "", "-s", "--source" );
			cfg.target    = cli.getOptional( cfg.source, "-t", "--target" );
			cfg.pkg       = cli.getOptional( "", "-k", "--package" );
			cfg.extension = cli.getOptional( ".sql", "-e", "--extension" );
//			var count = cli.getOptional( 1, "-c", "--count" );
//			boolean help = cli.getFlag( "-h", "--help" );

			// TODO debug logging
			var map = asMap( cfg );
			System.out.println( map );

			if( cli.ok() )
			{
				cfg.validate();

				var	conn = DriverManager.getConnection( cfg.url, cfg.username, cfg.password );

				var tool  = new Tool();
				var files = tool.go( cfg.sourcePath, cfg.extension );
				var works = new ArrayList<Work>();
				for( var file : files )
				{
					works.add( new Work( file ) );
				}

				long count = files.size();
				System.out.printf( "files found %d\n", count );

				if( count > 0 )
				{
					var worker = new Worker( conn );

					for( var work : works )
					{
						resolvePaths( work, cfg );
						worker.process( work );
					}
				}

				if( conn != null )
				{
					conn.close();
				}

				exit = 0;
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
			exit = 1;
		}

		System.exit( exit );
	}


	// TODO: Detect if targets need to be (re)created.

//	static String extension = ".sql";
	/*
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

	}
	*/

	public List<Path> go( Path sourcePath, String extension )
		throws Exception
	{
		var files = new ArrayList<Path>();
		Stream.Builder<Path>  hate = Stream.builder();
		Files.walkFileTree( sourcePath,
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
		return files;
	}

	/**
	 * Generates list of Work (to be done) from source files found
	 *
	 * @param work
	 * @param config
	 * @return
	 */
	public static void resolvePaths( Work work, Config config )
	{
		work.sourceDir = work.sourceFile.getParent();
		// Duplicate directory structure for output
		Path packagePath = config.sourcePath.relativize( work.sourceDir );
		work.targetDir = config.targetPath.resolve( packagePath );
//		if( Files.notExists( work.targetDir ))
//		{
//			Files.createDirectories( work.targetDir );
//		}

		// infers packa`ge name from directory structure
		work.packageName = packagePath.toString().replace( File.separatorChar, '.' );

		var clazz = getSimpleName( work.sourceFile );
		work.statementClassName = clazz;

		// TODO custom suffix for ResultSet
		work.resultSetClassName = work.statementClassName + "ResultSet";

		// TODO why isn't targetFile for ResultSet class also here?
		work.targetFile = work.targetDir.resolve( clazz + ".java" );
	}



//	// "How to get the filename without the extension in Java?"
//	// https://stackoverflow.com/a/29083921
//	private static final Pattern ext = Pattern.compile( "(?<=.)\\.[^.]+$" );
//
//	public static String getBaseName( File file )
//	{
//		return ext.matcher( file.getName() ).replaceAll( "" );
//	}

	public static String getSimpleName( Path file )
	{
		// TODO fix this
		var name = file.getFileName().toString();
		int len = name.length() - ".sql".length();
		var base = name.substring( 0, len );
		return base;
	}
}
