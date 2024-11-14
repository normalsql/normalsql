// Copyright 2010-2024 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import static java.nio.file.FileVisitResult.*;

import java.nio.file.attribute.BasicFileAttributes;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

/*
	TODO:

		parse all found files
		add just the DML statements to the work list
		add just the parseable statements to the work list
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
	public static LogShim INFO  = new ConsoleLog( "info", System.out );
	public static LogShim WARN  = new ConsoleLog( "warn", System.out );
	public static LogShim DEBUG = new ConsoleLog( "debug", System.out );
	public static LogShim ERROR = new ConsoleLog( "error", System.err );

	/**
	 * Returns -1 for config error, 1 for runtime error, 0 for success
	 *
	 * @param args
	 */
	public static void main( String[] args )
	{
		var cli = new CLI( args );

		var config = new Config();
		config.url = cli.getOptional( "", "-j", "--url" );
		config.username = cli.getOptional( "", "-u", "--username" );
		config.password = cli.getOptional( "", "-p", "--password" );
		config.source = cli.getOptional( "", "-s", "--source" );
		config.target = cli.getOptional( config.source, "-t", "--target" );
//		config.pkg = cli.getOptional( "", "-k", "--package" );
		config.extension = cli.getOptional( ".sql", "-e", "--extension" );
//			boolean help = cli.getFlag( "-h", "--help" );
		// TODO -v --version flag

		DEBUG.log( "command line: " + config );

		//  Default to config error
		int status = -1;
		if( cli.ok() )
		{
			try
			{
				var tool  = new Tool( config );
				tool.generate( config );
				// OK
				status = 0;
			}
			catch( Exception e )
			{
				ERROR.log( e );
				// Shell runtime error
				status = 1;
			}
		}

		System.exit( status );
	}

	Config config;

	public Tool( Config c ) throws Exception
	{
		config = c;
		config.cwd = new File( System.getProperty( "user.dir" )).toPath();

		config.validate();

		String properties = Glorp.coalesce( config.propsFilename, "normalsql.properties" );
		// TODO does this support subdirs? eg "./gosh/normalsql.properties"
		Path propertiesPath = config.sourcePath.resolve( properties );

		if( Files.exists( propertiesPath ))
		{
			Props props = Props.load( propertiesPath.toFile() );
			DEBUG.log( "properties file '%s' loaded".formatted( properties ));
			DEBUG.log( "loaded: " + props );

			// TODO: stopping here, for now. decided to go another way for misc config stuff.

		}
		else
		{
			DEBUG.log( "properties file '%s' not found".formatted( properties ));
		}
	}

	public void generate( Config config ) throws Exception
	{
		try
		(
			var conn = DriverManager.getConnection( config.url, config.username, config.password )
		)
		{
			var files = walkFileTree( config.cwd, config.sourcePath, config.extension );
			var count = files.size();
			INFO.log( "files found %d\n".formatted( count ));

			if( count > 0 )
			{
				var worker = new Worker( conn );

				for( var file : files )
				{
					UnitOfWork unitOfWork = resolvePaths( file, config );
					worker.process( unitOfWork );
				}
			}
		}
	}


	public List<Path> walkFileTree( Path cwd, Path sourcePath, String extension )
		throws Exception
	{
		var paths = new ArrayList<Path>();
		Files.walkFileTree( sourcePath,
			new SimpleFileVisitor<>()
			{
				@Override
				public FileVisitResult visitFile( Path file, BasicFileAttributes attrs )
				{
					var name = file.getFileName().toString();
					// Skip "hidden" dotfiles
					if( name.startsWith( "." ))
					{
						DEBUG.log(  "skipping hidden file: " + name );
					}
					else if( !name.toLowerCase().endsWith( extension ))
					{
						DEBUG.log(  "skipping unknown filetype: " + name );
					}
					else
					{
						var found = cwd.relativize( file );
						paths.add( found );
						DEBUG.log(  "found: " + found );
					}
					return CONTINUE;
				}

				public FileVisitResult preVisitDirectory( Path dir, BasicFileAttributes attrs )
				{
					var name = dir.getFileName();
					if( name.startsWith( "." ))
					{
						DEBUG.log( "skipping hidden directory: " + name );
						return SKIP_SUBTREE;
					}
					else
					{
						DEBUG.log( "visiting: " + name );
						return CONTINUE;
					}
				}
			}
		);
		return paths;
	}

	/**
	 * Generates list of Work (to be done) from source files found
	 *
	 * @param path
	 * @param config
	 * @return unit of work, representing a single SQL source file
	 */
	public static UnitOfWork resolvePaths( Path path, Config config )
		throws IOException
    {
		var work = new UnitOfWork( path );
		work.sourceDir = work.sourceFile.toAbsolutePath().getParent();

		// Duplicate directory structure for output
		var packagePath = config.sourcePath.relativize( work.sourceDir );
		work.targetDir = config.targetPath.resolve( packagePath );
		if( Files.notExists( work.targetDir ))
		{
			Files.createDirectories( work.targetDir );
		}

		// infers package name from directory structure, following javac's convention
		work.packageName = packagePath.toString().replace( File.separatorChar, '.' );

		var clazz = Glorp.getClassSimpleName( work.sourceFile );
		work.statementClassName = clazz;

		// TODO custom suffix for ResultSet
		work.resultSetClassName = work.statementClassName + "ResultSet";

		// TODO why isn't targetFile for ResultSet class also here?
		work.targetFile = work.targetDir.resolve( clazz + ".java" );

		return work;
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
