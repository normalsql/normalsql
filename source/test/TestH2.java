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
import java.util.Objects;

public class
	TestH2
	extends
//	BaseErrorListener
	DiagnosticErrorListener
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

	public void go() throws Exception
	{

		Path sourceRoot = Paths.get( "/Users/jasonosgood/Projects/h2database/h2/src/test/org/h2/test/scripts" );
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

		System.out.printf( "found %d\n", files.size() );

		rooster:
		for( Path file : files )
		{
			try
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
						sql.clear();
						TestH2.count();
						parse( file, nth, gorp );
					}
					if( error > 0 )
					{
						break rooster;
					}
				}
			}
			catch( Exception e )
			{
				e.printStackTrace();
			}
		}

		System.out.println( "statements found: " + count );
		System.out.println( "errors found: " + error );
	}

	public void parse( Path sourceFile, int nth, String sql ) throws Exception
	{
		CharStream chars = CharStreams.fromString( sql );
		GenericSQLLexer lexer = new GenericSQLLexer( chars );
		CommonTokenStream tokens = new CommonTokenStream( lexer );
		GenericSQLParser parser = new GenericSQLParser( tokens );
		parser.removeErrorListeners();
		parser.addErrorListener( this );

		GenericSQLParser.ParseContext result = parser.parse();
		if( error > 0 )
		{
			System.out.println( sourceFile );
			System.out.println( "line: " + nth );
			System.out.println( sql );

			System.out.println( result.toStringTree( parser ) );
		}
	}

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
			System.err.println("line " + line + ":" + charPositionInLine + " " + msg);
		}
	}

}
