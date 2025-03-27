// Copyright 2010-2023 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package test;

import java.io.PrintWriter;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.regex.Pattern;

public class
	H2_Cull_Tests
{
	static int count = 0;

	static public void count()
	{
		count++;
	}

	static int error = 0;

	static public void error()
	{
		error++;
	}

	public static void main( String[] args ) throws Exception
	{
//		System.out.println( new File( ".").getAbsolutePath() );
		Path sourceRoot = Paths.get( "src/test/sql/scrappedFromH2" );

		ArrayList<Path> files = new ArrayList<>();
		Files.walkFileTree( sourceRoot, new SimpleFileVisitor<Path>()
			{
				@Override
				public FileVisitResult visitFile( Path sourceFile, BasicFileAttributes attrs )
				{
					String sourceFileName = sourceFile.getFileName().toString();
					// Skip "hidden" dotfiles
					if( sourceFileName.startsWith( "." ) ) return FileVisitResult.CONTINUE;

					String extension = ".sql";
					if( sourceFileName.toLowerCase().endsWith( extension ) )
					{
						files.add( sourceFile );
					}
					return FileVisitResult.CONTINUE;
				}

				public FileVisitResult preVisitDirectory( Path dir, BasicFileAttributes attrs)
				{
					String full = dir.toString();
					return full.contains( "skip" ) ? FileVisitResult.SKIP_SUBTREE : FileVisitResult.CONTINUE;
				}
			}
			);

		System.out.printf( "files found %d\n", files.size() );

		var workList = new ArrayList<String>();
		var hashset = new TreeSet<String>();
        var spaces = Pattern.compile("\\s+(?=(?:[^\\'\"]*[\\'\"][^\\'\"]*[\\'\"])*[^\\'\"]*$)", Pattern.DOTALL);

		try
		{
			for( Path file : files )
			{
				System.out.println( file );

				var sql = new ArrayList<String>();
				var lines = Files.readAllLines( file );
				int nth = 0;
				for( String line : lines )
				{
					nth++;
					if( !sql.isEmpty() )
					{
						sql.add( line );
					}
					else if( line.toLowerCase().startsWith( "select" ))
					{
						sql.add( line );
					}
					if( !sql.isEmpty() && line.endsWith( ";" ))
					{
						String gorp = String.join( " ", sql );
						var foundSpaces = spaces.matcher( gorp );
						String finish = foundSpaces.replaceAll( " " );
						workList.add( finish );
						hashset.add( finish );
						sql.clear();
						H2_Cull_Tests.count();
					}
				}
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		System.out.println();
		System.out.printf( "statements found %d\n", workList.size() );
		System.out.printf( "dedupe found %d\n", hashset.size() );

		System.out.println();

//		for( var w : workList )
//		{
//			System.out.println( w );
//		}
//		System.out.println();

		String root = "./src/test/sql/scrappedFromH2/";
		var pw = new PrintWriter( root + "h2database-selectonly-deduped.sql" );
		for( var w : hashset )
		{
			pw.println( w );
		}


        pw.flush();
	}
}
