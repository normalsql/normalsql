// Copyright 2010-2022 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package test;

import normalsql.parse.NormalSQLLexer;
import normalsql.parse.NormalSQLParser;
import normalsql.parse.NormalSQLParser.ParseContext;
import org.antlr.v4.runtime.*;

import java.io.File;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>H2_SelectOnly_Tests class.</p>
 *
 * @author jasonosgood
 * @version $Id: $Id
 */
public class
H2_SelectOnly_Tests
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

	static class Work {
		Path source;
		String sql;
		int line;
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

		ArrayList<Work> workList = new ArrayList<>();

		try
		{
			for( Path file : files )
			{
				System.out.println( file );

				ArrayList<String> sql = new ArrayList<>();
				List<String> lines = Files.readAllLines( file );
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
						String gorp = String.join( "\n", sql );
						int argh = nth;
						Work w = new Work() {{ source=file;sql=gorp;line=argh;}};
						workList.add( w );
						sql.clear();
						H2_SelectOnly_Tests.count();
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

		System.out.println();

//		for( Work w : workList )
//		{
//			System.out.println( w.sql );
//		}

		System.out.println();
		System.out.println();

		for( Work w : workList )
		{
			parse( w.source, w.line, w.sql );
		}

		for( String fail : fails )
		{
			System.out.println( fail );
			System.out.println( );
		}

		System.out.println( "statements found: " + count );
		System.out.println( "errors found: " + error );
		System.out.println( new java.util.Date() );
	}

	static ArrayList<String> fails = new ArrayList<>();
	static String last = null;

	public static void parse( Path sourceFile, int nth, String sql )
	{
		CharStream chars = CharStreams.fromString( sql );
		NormalSQLLexer lexer = new NormalSQLLexer( chars );
		CommonTokenStream tokens = new CommonTokenStream( lexer );
		NormalSQLParser parser = new NormalSQLParser( tokens );
//		parser.removeErrorListeners();
		// TODO catch all the errors
		parser.addErrorListener( new BaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer,
			                        Object offendingSymbol,
			                        int line,
			                        int charPositionInLine,
			                        String msg,
			                        RecognitionException e)
			{
				if( !sql.equals( last ))
				{
					error();
					fails.add( msg );
					fails.add( sql );
					last = sql;
//					System.err.println("line " + line + ":" + charPositionInLine + " " + msg);
//					System.out.println( sourceFile );
//					System.out.println( "line: " + nth );
//					System.out.println( sql );

//					System.out.println( );

				}
			}

		} );

		ParseContext result = parser.parse();
	}
}
