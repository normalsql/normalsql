// Copyright 2010-2023 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package test;

import normalsql.parse.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;

import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class
	H2_SelectOnly_Tests
{
	static int count = 0;

	static public void count()
	{
		count++;
	}

	static int error = 0;
	static int ambig = 0;

	static public void error()
	{
		error++;
	}
	static public void ambig()
	{
		ambig++;
	}

	static class Work {
		Path source;
		String sql;
		int line;
	}

	public static void main( String[] args ) throws Exception
	{
//		System.out.println( new File( ".").getAbsolutePath() );
//		Path sourceRoot = Paths.get( "src/test/sql/scrappedFromH2" );
		Path sourceRoot = Paths.get( "/Users/jasonosgood/Projects/normalsql-resources/grammars-v4/sql/sqlite/examples" );

		ArrayList<Path> files = new ArrayList<>();
		Files.walkFileTree( sourceRoot, new SimpleFileVisitor<>()
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


		long start = System.currentTimeMillis();

		for( Work w : workList )
		{
			parse( w.source, w.line, w.sql );
		}
		long stop = System.currentTimeMillis();

		System.out.println( "elapsed: " + ( stop - start ));

		for( String fail : fails )
		{
			System.out.println( fail );
			System.out.println( );
		}

		System.out.println( "statements found: " + count );
		System.out.println( "errors found: " + error );
		System.out.println( "ambigs found: " + ambig );
		System.out.println( new java.util.Date() );
	}

	static ArrayList<String> fails = new ArrayList<>();
	static String last = null;

	public static void parse( Path sourceFile, int nth, String sql )
	{
		var chars = CharStreams.fromString( sql );
		var lexer = new NormalSQLLexer( chars );
		var tokens = new CommonTokenStream( lexer );
		var parser = new NormalSQLParser( tokens );
		parser.removeErrorListeners();
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
//					fails.add( msg );
					fails.add( sql );
					last = sql;
				}
			}
				@Override
	public void reportAmbiguity(Parser recognizer,
								DFA dfa,
								int startIndex,
								int stopIndex,
								boolean exact,
								BitSet ambigAlts,
								ATNConfigSet configs)
	{
//		var span = sql.substring( startIndex, stopIndex );
//		fails.add( "ambig " + startIndex + ":" + stopIndex + " token " + recognizer.getCurrentToken() + "   " + span );
//		if( !sql.equals( last ))
//		{
//			ambig();
//			fails.add( sql );
//			last = sql;
//		}
	}

	@Override
	public void reportAttemptingFullContext(Parser recognizer,
											DFA dfa,
											int startIndex,
											int stopIndex,
											BitSet conflictingAlts,
											ATNConfigSet configs)
	{
//		fails.add( "full " + startIndex + ":" + stopIndex + " token " + recognizer.getCurrentToken() + "   " + sql );

	}

	@Override
	public void reportContextSensitivity(Parser recognizer,
										 DFA dfa,
										 int startIndex,
										 int stopIndex,
										 int prediction,
										 ATNConfigSet configs)
	{
//		fails.add( "sensitive " + startIndex + ":" + stopIndex + " token " + recognizer.getCurrentToken() + "   " + sql );
	}

		} );

		parser.setProfile( true );
		var result = parser.script();
	}
}
