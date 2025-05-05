package sqlite;

import test.Drill;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import static java.nio.file.FileVisitResult.CONTINUE;
import static java.nio.file.FileVisitResult.SKIP_SUBTREE;

public class SQLiteTests {

    public static void main( String[] args )
        throws IOException
    {
//        String dir = "/Users/jasonosgood/Projects/sqlite/chat/";
//        String dir = "/Users/jasonosgood/Projects/normalsql/spare/SQLite";
        String dir = "/Users/jasonosgood/Projects/normalsql-resources/grammars-v4/sql/sqlite";
//        String dir = "/Users/jasonosgood/Projects/normalsql/src/test/sql.disabled/sqlite";
//        dir = "/Users/jasonosgood/Projects/normalsql-resources/sqllogictest/test";
        List<Path> files = getAllTheFiles( dir );
        int count = 0;
        int errors = 0;
        int maxErrors = 100;
        outer:
        for( Path f : files )
        {
            var lines = Files.readAllLines( f );
            int nth = 0;
            for( var sql : lines )
            {
                nth++;
                if( sql.startsWith( "--" )) continue;
//                if( !sql.startsWith( "ALTER" )) continue;
//                if( !sql.startsWith( "ANALYZE" )) continue;
//                if( !sql.startsWith( "ATTACH" )) continue;
//                if( !sql.startsWith( "CREATE" )) continue;
//                if( !sql.startsWith( "EXPLAIN" )) continue;
                if( !sql.startsWith( "SELECT" )) continue;
                var drill = new Drill();
                if( drill.parse( f, sql, true ))
                {
                    count++;
                }
                else
                {
                    errors++;
                    if( errors >= maxErrors )
                    {
                        break outer;
                    }
                }
            }
        }
        System.out.println( "parsed: "+ count );
        System.out.println( "errors: "+ errors );
        System.out.println( "done" );
        System.exit( -1 );
    }

    public static List<Path> getAllTheFiles( String dir )
        throws IOException
    {
        Path sourceRoot = Paths.get( dir );
        if( !Files.exists( sourceRoot ))
        {
            throw new NoSuchFileException( "not found: " + sourceRoot );
        }
        ArrayList<Path> files = new ArrayList<>();
        String extension = ".test";
//        String extension = ".sql";

        SimpleFileVisitor<Path> visitor = new SimpleFileVisitor<>()
        {
            @Override
            public FileVisitResult visitFile( Path sourceFile, BasicFileAttributes attrs )
            {
                String sourceFileName = sourceFile.getFileName().toString();

                // Skip "hidden" dotfiles
                if( sourceFileName.startsWith( "." )) return CONTINUE;

                if( sourceFileName.toLowerCase().endsWith( extension ))
                {
                    files.add( sourceFile );
                }
                return CONTINUE;
            }

            public FileVisitResult preVisitDirectory( Path dir, BasicFileAttributes attrs )
            {
                String full = dir.toString();
                return full.contains( "skip" ) ? SKIP_SUBTREE : CONTINUE;
            }
        };

        Files.walkFileTree( sourceRoot, visitor );

		System.out.printf( "files found %d\n", files.size() );

        return files;
    }
}
