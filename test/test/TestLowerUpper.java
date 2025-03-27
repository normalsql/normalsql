package test;

import normalsql.jdbc.Dumper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class TestLowerUpper
{
    public static void main( String[] args )
            throws Exception
    {
//        var ugh = "SELECT Apple, BANANA, cherry, \"Durian\", \"GRAPE\", \"kiwi\" FROM ABCxyz;";
        var ugh = "SELECT Apple, BANANA, cherry, Durian, GRAPE, kiwi FROM ABCxyz;";
        var biff = "jdbc:h2:tcp://localhost/TestUpperLower1;DATABASE_TO_UPPER=FALSE;DATABASE_TO_LOWER=TRUE;CASE_INSENSITIVE_IDENTIFIERS=TRUE  ";
        Connection conn = DriverManager.getConnection( biff, "", "" );

        Statement statement = conn.createStatement();
        var       tada = statement.execute( ugh );
        Dumper.dumpResultSet( statement.getResultSet()  );

    }
}
