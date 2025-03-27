package sqllogictest;

import test.Drill;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import static java.nio.file.FileVisitResult.CONTINUE;
import static java.nio.file.FileVisitResult.SKIP_SUBTREE;

public class ParseTests
{

	public static void main( String args[] ) throws Exception
	{

		int errors = 0;

//		Path f = Paths.get("/Users/jasonosgood/Projects/normalsql-resources/sqllogictest/proto/select1.proto");
//		String dir = "/Users/jasonosgood/Projects/normalsql-resources/sqllogictest/proto";
		String dir = "/Users/jasonosgood/Projects/normalsql-resources/sqllogictest/test";

		List<Path> files = getAllTheFiles( dir );
		for( Path f : files )
		{
			int parsed = 0;
			int nth = 0;

			List<String> lines = Files.readAllLines(f);
			ListIterator<String> ugh = lines.listIterator();

			ArrayList<String> boop = new ArrayList<>();

			while( ugh.hasNext() )
			{
				String s = ugh.next();
				nth++;
				if( s.startsWith( "statement" ))
				{
					while( ugh.hasNext() )
					{
						String t = ugh.next();
						nth++;

						if( t.trim().isEmpty() )
						{
	//						System.out.println( String.join( "\n", boop ));
//							System.out.println( "nth: " + nth );
//							System.out.println( String.join( " ", boop ).substring( 0, 39));
							boop.clear();
							break;
						}
						boop.add( t );
					}
				}
				else if( s.startsWith( "query" ))
				{
					boolean expectedResults = false;
					while( ugh.hasNext() )
					{
						String t = ugh.next();
						nth++;

						if( t.trim().isEmpty() )
						{
							String sql = String.join("\n", boop);
//							String sql = String.join(" ", boop);
							boop.clear();
//							System.out.println( "nth: " + nth );
//							int end = Math.min( 39, sql.length() );
//							System.out.println(sql.substring(0, end ));
//							System.out.println();
                var drill = new Drill();
							if( !drill.parse( f, sql))
							{
								errors++;
								if( errors > 20 )
								{
									System.exit( -1 );
								}
							}
							parsed++;
							break;
						}
						if( t.trim().startsWith( "---" ))
						{
							expectedResults = true;
						}
						if( !expectedResults )
						{
							boop.add( t );
						}
					}

				}
			}

			System.out.println( f );
			System.out.println( "parsed: " + parsed );

		}
	}

	public static List<Path> getAllTheFiles( String dir )
			throws IOException
	{
		Path sourceRoot = Paths.get( dir );
		ArrayList<Path> files = new ArrayList<>();
//		String extension = ".proto";
		String[] extensions = { ".proto", ".test" };

		SimpleFileVisitor<Path> visitor = new SimpleFileVisitor<>()
		{
			@Override
			public FileVisitResult visitFile(Path sourceFile, BasicFileAttributes attrs )
			{
				String sourceFileName = sourceFile.getFileName().toString();

				// Skip "hidden" dotfiles
				if( sourceFileName.startsWith( "." )) return CONTINUE;

				for( String ext : extensions )
				{
					if( sourceFileName.toLowerCase().endsWith( ext ))
					{
						files.add( sourceFile );
						break;
					}
				}
				return CONTINUE;
			}

			public FileVisitResult preVisitDirectory( Path dir, BasicFileAttributes attrs )
			{
				String full = dir.toString();
				return full.contains( "skip" ) ? SKIP_SUBTREE : CONTINUE;
//				return SKIP_SUBTREE;
			}
		};

		Files.walkFileTree( sourceRoot, visitor );

		System.out.printf( "files found %d\n", files.size() );

		return files;
	}


}
