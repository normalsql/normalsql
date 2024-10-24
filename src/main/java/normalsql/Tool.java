// Copyright 2010-2024 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import static java.nio.file.FileVisitResult.*;

import java.nio.file.attribute.BasicFileAttributes;
import java.sql.DriverManager;
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
	PrintWriter INFO = new PrintWriter( System.out, true );
	PrintWriter DEBUG = new PrintWriter( System.out, true );
	PrintWriter WARN = new PrintWriter( System.out, true );
	PrintWriter ERROR = new PrintWriter( System.err, true );

	/**
	 * Returns -1 for config error, 1 for runtime error, 0 for success
	 *
	 * @param args
	 */
	public static void main( String[] args )
	{
//		String[] blah = { "-j", "jdbc:h2:mem:", "-u", "sa", "--password", "root", "--count", "123" };
//		args = blah;

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

		//  Config error
		int status = -1;
		if( cli.ok() )
		{
			try
			{
				var tool  = new Tool();
				tool.generate( cfg );
				// OK
				status = 0;
			}
			catch( Exception e )
			{
				e.printStackTrace();
				// Runtime error
				status = 1;
			}
		}

		System.exit( status );
	}

	public void generate( Config config ) throws Exception
	{
		// TODO debug logging
		var map = Glorp.toMap( config );
		System.out.println( map );

		config.validate();

		try
		(
			var conn = DriverManager.getConnection( config.url, config.username, config.password )
		)
		{
			var files = getSourceSQL( config.sourcePath, config.extension );
			var works = new ArrayList<Work>();
			for( var file : files )
			{
				works.add( new Work( file ) );
			}

			var count = files.size();
			System.out.printf( "files found %d\n", count );

			if( count > 0 )
			{
				var worker = new Worker( conn );

				for( var work : works )
				{
					resolvePaths( work, config );
					worker.process( work );
				}
			}
		}
	}

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

	*/

	public List<Path> getSourceSQL( Path sourcePath, String extension )
		throws Exception
	{
		var files = new ArrayList<Path>();
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
		throws IOException
    {
		work.sourceDir = work.sourceFile.getParent();

		// Duplicate directory structure for output
		Path packagePath = config.sourcePath.relativize( work.sourceDir );
		work.targetDir = config.targetPath.resolve( packagePath );
		if( Files.notExists( work.targetDir ))
		{
			Files.createDirectories( work.targetDir );
		}

		// infers packa`ge name from directory structure
		work.packageName = packagePath.toString().replace( File.separatorChar, '.' );

		var clazz = Glorp.getClassSimpleName( work.sourceFile );
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

}
