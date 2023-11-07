package sqlite;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlExtractor
{

    public static void main( String[] args )
        throws Exception
    {
        // Define the SQL statement prefixes
        String[] tests = {
                "eval",
//                "memdbsql",
//                "stepsql",
//                "execsql2",
////                "explain_no_trace",
////                "explain",
////                "catchsql",
//                "execsql",
//                "do_execsql_test",
////                "do_catchsql_test",
//                "do_timed_execsql_test",
        };

        String root = "/Users/jasonosgood/Projects/sqlite.org/sqlite/test";
//        String root = "/Users/jasonosgood/Projects/sqlite.org/sqlite/chat";
        File directory = new File( root );
        File[] files = directory.listFiles();
        if( files == null )
        {
            System.err.println( "not found: " + root );
            System.exit( -1 );
        }

        Pattern spaces = Pattern.compile("\\s+(?=(?:[^\\'\"]*[\\'\"][^\\'\"]*[\\'\"])*[^\\'\"]*$)", Pattern.DOTALL);
        String biff = ";(?=(?:[^\\']*[\\'][^\\']*[\\'])*[^\\']*$)";
//        var COMMENT1 = Pattern.compile( "(?m)[^:]//.*$" );
        var COMMENT1 = Pattern.compile( "(?m)\\s//.*$" );
        var COMMENT2 = Pattern.compile( "(?m)\\s--.*$" );
        var JOINER = Pattern.compile( "[\r]?[\n]" );
        var BLOCKS = Pattern.compile( "/\\*[^*]*\\*+(?:[^/*][^*]*\\*+)*/" );

        var writer = new PrintWriter( root + "/dump2.sql" );
        var hashset = new HashSet<String>();

        for( var file : files )
        {
            if (file.isFile() && file.getName().endsWith( ".test" ))
            {
                System.out.println( "-- File: " + file.getName() );
                writer.println( "\n-- FILE: " + file.getName() );

                var sb = new StringBuilder();

                var raws = Files.readAllLines( file.toPath() );
                for( var raw : raws )
                {
                    raw = raw.trim();
                    if( raw.startsWith( "#" )) continue;
                    sb.append( raw );
                    sb.append( "\n" );
                }

                var content = sb.toString();

                for( var test : tests )
                {
                    var testsString = "\\s+(" + test + ") [a-zA-Z0-9\\- _\\.]*\\{";
                    var testsPattern = Pattern.compile( testsString, Pattern.DOTALL );

                    Matcher matcher = testsPattern.matcher( content );

                    while( matcher.find() )
                    {
                        var prefix = matcher.group(1);
                        writer.println( "-- Prefix: " + prefix );

                        var start = matcher.end();
                        int nest = 0;
                        int end = matcher.end();
                        char last = 0;
                        gorp:
                        for( ; end < content.length(); end++ )
                        {
                            char c = content.charAt( end );
                            switch( c )
                            {
                                case '{':
                                {
                                    if( last == '\\' ) break;
                                    nest++;
                                    break;
                                }
                                case '}':
                                {
                                    if( last == '\\' ) break;
                                    if( nest == 0 ) break gorp;
                                    nest--; break;
                                }
                                default: break;
                            }
                            last = c;
                        }
                        var found = content.substring( start, end );

                        if( found.length() == 0 )
                        {
                            continue;
                        }
                        if( found.contains( "$:" )) continue;

                        // Remove block comments
                        found = BLOCKS.matcher( found ).replaceAll( " " );
                        found = COMMENT1.matcher( found ).replaceAll( "" );
                        found = COMMENT2.matcher( found ).replaceAll( "" );
                        // Join lines
                        found = JOINER.matcher( found ).replaceAll( " " );

                        var lines = found.split( biff );
                        for( var line : lines )
                        {
                            line = line.trim();
                            if( line.length() == 0 ) continue;
                            if( line.startsWith( "--" )) continue;
                            if( line.startsWith( "#" )) continue;
                            if( line.startsWith( "$sql" )) continue;

                            var foundSpaces = spaces.matcher( line );
                            String finish = foundSpaces.replaceAll(" ") + ";";
                            writer.println( finish );

                            hashset.add( finish );
                        }
                    }
                }
            }
        }
        var dedupe = new PrintWriter( root + "/dedupe2.sql" );
        var myTreeSet = new TreeSet<>( hashset );
        for( var line : myTreeSet )
        {
            dedupe.println( line );
        }
        dedupe.flush();
        writer.flush();
    }
}
