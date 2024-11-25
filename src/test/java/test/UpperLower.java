package test;

import normalsql.Glorp;
import normalsql.jdbc.Dumper;
import petclinic.SelectNullables;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class UpperLower
{
     static   String     mask     = "DATABASE_TO_LOWER=%b;DATABASE_TO_UPPER=%b;CASE_INSENSITIVE_IDENTIFIERS=%b";

    public static void main( String[] args )
        throws Exception
    {
//        boolean lower = false;
//        boolean upper = false;
//        boolean identifiers = false;
//
//        extracted( lower, upper, identifiers );

        System.out.println( "flags\t     \t      \tPLAIN\t       \t       \tQUOTED\t\t");
        System.out.println( "LOWER\tUPPER\tCASE\tlower\tupper\tmixed\tlower\tupper\tmixed");
        extracted( false, false, false );
        extracted( false, true, false );
        extracted( true, false, false );
        extracted( false, false, true );
        extracted( false, true, true );
        extracted( true, false, true );
    }

    static void extracted( boolean lower, boolean upper, boolean identifiers ) throws SQLException
    {
        String     flags = mask.formatted( lower, upper, identifiers );
        Connection conn  = DriverManager.getConnection( "jdbc:h2:tcp://localhost/TestUpperLowerFF;" + flags, "", "" );
        var        meta  = conn.getMetaData();

//        System.out.println(  flags );
        printMetaData( meta, lower, upper, identifiers );
        conn.close();
    }

    public static void printMetaData(DatabaseMetaData metaData, boolean lower, boolean upper, boolean identifiers ) throws SQLException
    {
        boolean plainLower = metaData.storesLowerCaseIdentifiers();
        boolean plainUpper = metaData.storesUpperCaseIdentifiers();
        boolean plainMixed = metaData.storesMixedCaseIdentifiers();

        boolean quotedLower = metaData.storesLowerCaseQuotedIdentifiers();
        boolean quotedUpper = metaData.storesUpperCaseQuotedIdentifiers();
        boolean quotedMixed = metaData.storesMixedCaseQuotedIdentifiers();

        System.out.printf(  "%b\t%b\t%b\t%b\t%b\t%b\t%b\t%b\t%b\n", lower, upper, identifiers, plainLower, plainUpper, plainMixed, quotedLower, quotedUpper, quotedMixed );

//        System.out.println("=== DatabaseMetaData ===");
//        System.out.println("Database Product Name: " + metaData.getDatabaseProductName());
//        System.out.println("Database Product Version: " + metaData.getDatabaseProductVersion());
//        System.out.println("Driver Name: " + metaData.getDriverName());
//        System.out.println("Driver Version: " + metaData.getDriverVersion());
//        System.out.println("URL: " + metaData.getURL());
//        System.out.println("User Name: " + metaData.getUserName());
//        System.out.println("Max Connections: " + metaData.getMaxConnections());
//        System.out.println("SQL Keywords: " + metaData.getSQLKeywords());
//        System.out.println("Numeric Functions: " + metaData.getNumericFunctions());
//        System.out.println("String Functions: " + metaData.getStringFunctions());
//        System.out.println("System Functions: " + metaData.getSystemFunctions());
//        System.out.println("Time/Date Functions: " + metaData.getTimeDateFunctions());
//        System.out.println("Supports Transactions: " + metaData.supportsTransactions());
//        System.out.println("Default Transaction Isolation: " + metaData.getDefaultTransactionIsolation());
//        System.out.println("=== End of DatabaseMetaData ===");
    }

}
