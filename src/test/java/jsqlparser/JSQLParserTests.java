package jsqlparser;

import java.io.IOException;
import java.nio.file.*;
import static java.nio.file.FileVisitResult.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class JSQLParserTests {

    public static void main( String[] args )
        throws IOException
    {
        String dir = "";
        List<Path> files = getAllTheFiles( dir );
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
