package sqlite;

import normalsql.parse.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Scraps individual SQL statements from project sqlite-parser's test suite. Which
 * were scrapped from the original SQLite project.
 */
public class Scrap
{

    // A counter that keeps track of the total amount of statements
    // parsed in the test below.
    static int totalStatements = 0;

    public static void main( String[] args )
    {
        int fileCount = 0;
        int statementCount = 0;

		long start = System.currentTimeMillis();
		String root = "/Users/jasonosgood/Projects/normalsql/spare";

	    var spaces = Pattern.compile("\\s+(?=(?:[^\\'\"]*[\\'\"][^\\'\"]*[\\'\"])*[^\\'\"]*$)", Pattern.DOTALL);
	    String biff = ";(?=(?:[^\\']*[\\'][^\\']*[\\'])*[^\\']*$)";
	    var JOINER = Pattern.compile( "[\r]?[\n]" );


	    Path path;
	    String sql;
	    String cleaned;
	    String[] lines;
	    String lineffs;

	    try
		{
			var writer = new PrintWriter( root + "/cleaned.sql" );
	        var hashset = new HashSet<String>();


			// Retrieve all files from `src/test/resources` ending with ".sql".
	        var tests = new File( "/Users/jasonosgood/Projects/SQL/Parsers/sqlite-parser/src/test/resources" )
	                .listFiles(
	                        file -> file.isFile() && file.getName().endsWith(".sql")
	//                                && file.getName().startsWith( "rand" )
	                );

			Arrays.sort( tests );


	        for( File test : tests )
			{
	            fileCount++;
				if( ( fileCount % 100 ) == 0 ) System.out.println( "fileCount: " + fileCount );

	                 path = test.toPath();

					 sql = Files.readString(path);
					 if( sql.length() > 50000 )
					 {
						 System.out.println( "really big !!! " + path.toString() + "  size " + sql.length() );
						 continue;
					 }

					 cleaned = parse( sql );


	//				writer.append( cleaned );

					if( cleaned == null ) continue;

	//        var COMMENT1 = Pattern.compile( "(?m)[^:]//.*$" );
	//        var COMMENT1 = Pattern.compile( "(?m)\\s//.*$" );
	//        var COMMENT2 = Pattern.compile( "(?m)\\s--.*$" );
	//        var JOINER = Pattern.compile( "[\r]?[\n]" );
	//        var BLOCKS = Pattern.compile( "/\\*[^*]*\\*+(?:[^/*][^*]*\\*+)*/" );

				lines = cleaned.split( biff );
		        for(  var line : lines )
		        {
//					lineffs = line;

										                         // Join lines
//	                cleaned = JOINER.matcher( cleaned ).replaceAll( " " );
//					var ugh = cleaned.replaceAll( "[\r]?[\n]", " " );
//					if( cleaned.compareTo( ugh ) != 0 )
//					{
//						System.out.println( "join not equals" );
//					}

			        line = line.replaceAll( "[\r]?[\n]", " " );

			        line = line.trim();
			        if( line.length() == 0 ) continue;


			        var foundSpaces = spaces.matcher( line );
			        String finish = foundSpaces.replaceAll(" ") + ";";

			        writer.println( finish );
//					var finish = line + ";";
			        hashset.add( finish );

			        statementCount++;

			        if( ( statementCount % 100 ) == 0 ) System.out.println( "statements: " + statementCount );
		        }
	        }
			writer.flush();
	        var dedupe = new PrintWriter( root + "/dedupe2.sql" );
	        var myTreeSet = new TreeSet<>( hashset );
	        for( var line : myTreeSet )
	        {
	            dedupe.println( line );
	        }
	        dedupe.flush();

		    long stop = System.currentTimeMillis();

		    System.out.println( "elapsed: " + ( stop - start ));


		    System.out.println( "statements found: " + count );
		    System.out.println( new java.util.Date() );

		    System.out.println("finished parsing " + tests.length + " test files containing " + totalStatements + " SQL statements");
        }
        catch (Exception e) {
			System.out.println( "statements: " + statementCount );
        }

    }

    static int count = 0;

	static public void count()
	{
		count++;
	}


    public static String parse( String sql )
	{
		var chars = CharStreams.fromString( sql );
		var lexer = new ScrapLexer( chars );
		var tokens = new CommonTokenStream( lexer );
		var parser = new ScrapParser( tokens );

		parser.removeErrorListeners();

		var sb = new StringBuilder();
		parser.addParseListener( new ScrapBaseListener()
		{
			@Override public void visitTerminal( TerminalNode node )
			{
				if( node.getSymbol().getType() == -1 )
					return;
				var t = node.getText();
				if( "\n".equals( t )) return;
				sb.append( t );
			}
		} );
		var root = parser.root();
//		System.out.println( sb.toString() );
		var found = sb.toString().trim();
		return found;

	}
}
