package sqlite;

import test.Drill;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class IntegrationTest {

    // A counter that keeps track of the total amount of statements
    // parsed in the test below.
    static int totalStatements = 0;

    public static void main( String[] args )

    {
        int count = 0;
        int errors = 0;
        int maxErrors = 100;

        // Retrieve all files from `src/test/resources` ending with ".sql".
        var tests = new File("/Users/jasonosgood/Projects/SQL/Parsers/sqlite-parser/src/test/resources")
                .listFiles(
                        file -> file.isFile() && file.getName().endsWith(".sql")
                                && file.getName().startsWith( "rand" )
                );

//        var f = new File("/Users/jasonosgood/Projects/SQL/Parsers/sqlite-parser/src/test/resources/randexpr1.test_2534.sql");
//        var tests = new File[1];
//        tests[0] = f;

        int testCounter = 0;


        outer:
        for (File test : tests) {

            testCounter++;

            try {
                Path path = test.toPath();
                var sql = Files.readString(path);
                var drill = new Drill();

                if( drill.parse( path, sql, true ))
                {
                    count++;
                }
                else
                {
                    errors++;
                    if( errors >= maxErrors )
                    {
                        break ;
                    }
                }

                if ( count % 100 == 0) {
                    System.out.println(testCounter + "/" + tests.length);
                }
            }
            catch (Exception e) {
//                e.printStackTrace();
//                System.out.println("could not parse file: " + test);
//                return;
            }
        }
        System.out.println("finished parsing " + tests.length + " test files containing " +
                totalStatements + " SQL statements");
    }
}
