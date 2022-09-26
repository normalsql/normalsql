package test;

import fado.parse.GenericSQLLexer;
import fado.parse.GenericSQLParser;
import org.antlr.v4.runtime.*;

import java.io.IOException;
import java.nio.file.FileVisitResult;

import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class
	TestH2
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
		TestH2 app = new TestH2();
		app.go();
	}

	class Work {
		Path source;
		String sql;
		int line;
	}
	public void go() throws Exception
	{

//		Path sourceRoot = Paths.get( "/Users/jasonosgood/Projects/h2database/h2/src/test/org/h2/test/scripts" );
		Path sourceRoot = Paths.get( "/Users/jasonosgood/Projects/fado/source/test/" );
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
					throws IOException
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
			rooster:
			for( Path file : files )
			{
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
					if( line.toLowerCase().startsWith( "select" ))
					{
						sql.add( line );
					}
					if( !sql.isEmpty() && line.endsWith( ";" ))
					{
						String gorp = String.join( "\n", sql );
//									String gorp = String.join( " ", sql );
						int argh = nth;
						Work w = new Work() {{ source=file;sql=gorp;line=argh;}};
						workList.add( w );
						sql.clear();
						TestH2.count();
					}
					if( error > 0 )
					{
						break rooster;
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


		System.out.println( "statements found: " + count );
		System.out.println( "errors found: " + error );

	}

	ArrayList<String> fails = new ArrayList<>();
	public void parse( Path sourceFile, int nth, String sql )
	{
		CharStream chars = CharStreams.fromString( sql );
		GenericSQLLexer lexer = new GenericSQLLexer( chars );
		CommonTokenStream tokens = new CommonTokenStream( lexer );
		GenericSQLParser parser = new GenericSQLParser( tokens );
//		parser.removeErrorListeners();
		parser.addErrorListener( new BaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer,
			                        Object offendingSymbol,
			                        int line,
			                        int charPositionInLine,
			                        String msg,
			                        RecognitionException e)
			{
				if( e != null )
				{
					error();
					fails.add( msg );
					fails.add( sql );
//					System.err.println("line " + line + ":" + charPositionInLine + " " + msg);
//					System.out.println( sourceFile );
//					System.out.println( "line: " + nth );
//					System.out.println( sql );

//					System.out.println( );

				}
			}

		} );

		GenericSQLParser.ParseContext result = parser.parse();
		for( String fail : fails )
		{
					System.out.println( fail );
					System.out.println( );
		}
	}
}
