package sqlite;

import normalsql.parse.NormalSQLLexer;
import normalsql.parse.NormalSQLParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import test.Drill;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;

public class RandExpr1 {

    // A counter that keeps track of the total amount of statements
    // parsed in the test below.
    static int totalStatements = 0;

    public static void main( String[] args )

    {
        int maxErrors = 100;

        // Retrieve all files from `src/test/resources` ending with ".sql".
        var tests = new File( "/Users/jasonosgood/Projects/SQL/Parsers/sqlite-parser/src/test/resources" )
                .listFiles(
                        file -> file.isFile() && file.getName().endsWith(".sql")
//                                && file.getName().startsWith( "rand" )
                );

//        var f = new File("/Users/jasonosgood/Projects/SQL/Parsers/sqlite-parser/src/test/resources/randexpr1.test_2534.sql");
//        var tests = new File[1];
//        tests[0] = f;
        Arrays.sort( tests );

        int testCounter = 0;

		long start = System.currentTimeMillis();

        outer:
        for (File test : tests) {

            testCounter++;

            try {
                Path path = test.toPath();
//                System.out.println( path );

                var sql = Files.readString(path);

                parse( path, sql );
//                {
//                    errors++;
//                    if( errors >= maxErrors )
//                    {
//                        break ;
//                    }
//                }

//                if ( count % 100 == 0) {
//                    System.out.println(testCounter + "/" + tests.length);
//                }
            }
            catch (Exception e) {
//                e.printStackTrace();
//                System.out.println("could not parse file: " + test);
//                return;
            }

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

        System.out.println("finished parsing " + tests.length + " test files containing " +
                totalStatements + " SQL statements");
    }

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
    static ArrayList<String> fails = new ArrayList<>();
	static String last = null;
	static String lastFile = null;

    public static void parse( Path path, String sql )
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
                error();

                var file = path.toString();
                if( !file.equals( lastFile ))
                {
					fails.add( "\n\n###\n\n" );
					fails.add( file );
                    lastFile = file;
                    last = null;
                }

                if( !sql.equals( last ))
				{
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
