package jsqlparser;

import test.Drill;

import java.io.IOException;
import java.nio.file.*;
import static java.nio.file.FileVisitResult.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class JSQLParserTests {

    public static void main( String[] args )
        throws IOException
    {
        String dir = "/Users/jasonosgood/Projects/normalsql-resources/JSqlParser/src/test/resources/net/sf/jsqlparser/statement/select/oracle-tests";
        List<Path> files = getAllTheFiles( dir );
        for( Path f : files )
        {
            var joiner = new StringJoiner( "\n" );
            var original = Files.readAllLines( f );
            for( String line : original )
            {
                if( line.startsWith( "--" )) continue;
                joiner.add( line );
            }
            String sql = joiner.toString();
            Drill.parse( f, sql );
        }
    }

    public static List<Path> getAllTheFiles( String dir )
        throws IOException
    {
        Path sourceRoot = Paths.get( dir );
        ArrayList<Path> files = new ArrayList<>();
        String extension = ".sql";

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
