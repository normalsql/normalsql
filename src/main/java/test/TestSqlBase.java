// Copyright 2010-2022 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package test;

import normalsql.parse.SqlBaseLexer;
import normalsql.parse.ReservedParser;
import org.antlr.v4.runtime.*;

import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>TestGeneric class.</p>
 *
 * @author jasonosgood
 * @version $Id: $Id
 */
public class
TestSqlBase
{
	static int count = 0;

	/**
	 * <p>count.</p>
	 */
	static public void count()
	{
		count++;
	}

	static int error = 0;

	/**
	 * <p>error.</p>
	 */
	static public void error()
	{
		error++;
	}

	/**
	 * <p>main.</p>
	 *
	 * @param args an array of {@link String} objects
	 * @throws Exception if any.
	 */
	public static void main( String[] args ) throws Exception
	{
		TestSqlBase app = new TestSqlBase();
		app.go();
	}

	class Work {
		Path source;
		String sql;
		int line;
	}
	/**
	 * <p>go.</p>
	 *
	 * @throws Exception if any.
	 */
	public void go() throws Exception
	{

//		Path sourceRoot = Paths.get( "/Users/jasonosgood/Projects/h2database/h2/src/test/org/h2/test/scripts" );
		Path sourceRoot = Paths.get( "/Users/jasonosgood/Projects/normalsql/src/main/java/test/" );

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
						TestSqlBase.count();
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

//		System.out.println();
//		System.out.println();

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

	ArrayList<String> fails = new ArrayList<>();
	String last = null;

	ParserRuleContext context;

	public void parse( Path sourceFile, int nth, String sql )
	{
		CharStream chars = CharStreams.fromString( sql );
		SqlBaseLexer lexer = new SqlBaseLexer( chars );
		CommonTokenStream tokens = new CommonTokenStream( lexer );
		ReservedParser parser = new ReservedParser( tokens );
		parser.removeErrorListeners();
		// TODO catch all the errors
		parser.addErrorListener( new BaseErrorListener() {
			@Override
			public void syntaxError( Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int pos, String msg, RecognitionException e)
			{
				// dedupe statements
				if( !sql.equals( last ))
				{
					last = sql;

					error();
					fails.add( "---" );
					fails.add( sql );
					String temp = "line " + line + ":" + pos + " " + msg;
					fails.add( temp );
					fails.add( context.toStringTree( parser ) );
//					System.out.println( sourceFile );
//					System.out.println( "line: " + nth );
				}
			}

		} );

		context = parser.singleStatement();
	}
}
