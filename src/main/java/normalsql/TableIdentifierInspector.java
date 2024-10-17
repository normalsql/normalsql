package normalsql;

import java.sql.*;
import java.util.ArrayList;

import static normalsql.jdbc.Dumper.dumpResultSet;

public class TableIdentifierInspector
{

    public static int inferGeneratedKeyType(String catalog, String schema, String table, Connection connection )
        throws SQLException
    {
        int sqlType = Types.INTEGER;
        table = table.toUpperCase();

        var metaData = connection.getMetaData();
        var rsPrimaryKeys = metaData.getPrimaryKeys( catalog, schema, table );

        // Collect all primary key columns
        var columns = new ArrayList<String>();
        while( rsPrimaryKeys.next() )
        {
            var columnName = rsPrimaryKeys.getString( "COLUMN_NAME" );
            columns.add( columnName );
        }

        for( var column : columns )
        {
            var digger = metaData.getColumns( catalog, schema, table, column );
//            dumpResultSet( digger );
            if( digger.next() )
            {
                sqlType = digger.getInt( "DATA_TYPE" );
            }
        }

        return sqlType;

//        if (primaryKeyColumn != null) {
//            System.out.println("Primary Key: " + primaryKeyColumn);
//        } else {
//            System.out.println("No primary key found. Searching for unique constraints...");
//
//            // Fallback to unique constraint if no primary key is defined
//            var rsUniqueColumns = metaData.getIndexInfo(null, schema, table, true, true);
//
//            String uniqueColumns = null;
//            while (rsUniqueColumns.next()) {
//                if (!rsUniqueColumns.getBoolean("NON_UNIQUE")) {
//                    var uniqueColumn = rsUniqueColumns.getString("COLUMN_NAME");
//                    if (uniqueColumns == null) {
//                        uniqueColumns = uniqueColumn;
//                    } else {
//                        uniqueColumns += ", " + uniqueColumn; // Composite unique key
//                    }
//                }
//            }
//
//            if (uniqueColumns != null) {
//                System.out.println("Unique Constraint: " + uniqueColumns);
//            } else {
//                System.out.println("No primary key or unique constraint found.");
//            }
    }

//    public static void main(String[] args) {
//        String url = "jdbc:your_database_url"; // Example: jdbc:postgresql://localhost/testdb
//        String user = "your_db_user";
//        String password = "your_db_password";
//
//        try (Connection connection = DriverManager.getConnection(url, user, password)) {
//            findTableIdentifier("your_table_name", connection);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
