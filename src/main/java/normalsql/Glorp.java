package normalsql;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;

import static java.sql.Types.*;

public class Glorp
{
    public static String getLocalName( String qname ) {
        // Use just the name; remove any reference prefixes
        if( qname.contains( "." )) {
            return qname.substring( qname.lastIndexOf( "." ) + 1 );
        }
        return qname;
    }

    /**
     * "Serializes" a POJO into a HashMap. Only adds 'public' fields to resulting map.
     * <p>
     * One use is to create a 'context map' for Velocity templates. Passing values into a
     * Velocity template sucks. This is the least turrible strategy I could think of.
     *
     * @return a {@link java.util.HashMap} object
     */

    public static HashMap<String, Object> toMap( Object o )
    {
        var map = new HashMap<String, Object>();
        try
        {
            for( var f : o.getClass().getFields() )
            {
                var value = f.get( o );
                if( value != null )
                {
                    map.put( f.getName(), value );
                }
            }
        }
        catch( IllegalAccessException e )
        {
            // do nothing
        }
        return map;
    }

    public static String getClassSimpleName( Path file )
    {
        // TODO fix this
        var name = file.getFileName().toString();
        int len = name.length() - ".sql".length();
        var base = name.substring( 0, len );
        return base;
    }

    public static int inferGeneratedKeyType( String catalog, String schema, String table, Connection connection )
        throws SQLException
    {
        // default sqlType
        int sqlType = Types.INTEGER;
        // TODO use database's settings for case and sensitivity
        table = table.toUpperCase();

        var md = connection.getMetaData();
        var keys = md.getPrimaryKeys( catalog, schema, table );

        // Collect all primary key names
        var names = new ArrayList<String>();
        while( keys.next() )
        {
            var name = keys.getString( "COLUMN_NAME" );
            names.add( name );
        }

        for( var name : names ) {
            var rs = md.getColumns( catalog, schema, table, name );
            if( rs.next() ) {
                sqlType = rs.getInt( "DATA_TYPE" );
            }
        }

//        for( var name : names )
//        {
//            var rs = md.getColumns( catalog, schema, table, name );
//            if( rs.next() )
//            {
//                sqlType = rs.getInt( "DATA_TYPE" );
//            }
//        }
//
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

    public static String getJavaClassName( int sqlType )
    {
        // TODO verify these mappings
        return switch( sqlType )
        {
            case ARRAY -> "java.sql.Array";
            case BIGINT -> "java.lang.Long";
            case BINARY, VARBINARY, LONGVARBINARY -> "byte[]";
            case BIT, BOOLEAN -> "java.lang.Boolean";
            case BLOB -> "java.sql.Blob";
            case CHAR, LONGVARCHAR, LONGNVARCHAR, NCHAR, NVARCHAR, VARCHAR -> "java.lang.String";
            case CLOB -> "java.sql.Clob";
            case DATALINK -> "java.net.URL";
            case DATE -> "java.sql.Date";
            case DECIMAL, NUMERIC -> "java.math.BigDecimal";
            case DISTINCT, JAVA_OBJECT, OTHER -> "java.lang.Object";
            case DOUBLE, FLOAT -> "java.lang.Double";
            case INTEGER -> "java.lang.Integer";
            case NCLOB -> "java.sql.NClob";
            case NULL -> "null";
            case REAL -> "java.lang.Float";
            case REF, REF_CURSOR -> "java.sql.Ref";
            case ROWID -> "java.sql.RowId";
            case SMALLINT -> "java.lang.Short";
            case SQLXML -> "java.sql.SQLXML";
            case STRUCT -> "java.sql.Struct";
            case TIME -> "java.sql.Time";
            case TIME_WITH_TIMEZONE -> "java.time.OffsetTime";
            case TIMESTAMP -> "java.sql.Timestamp";
            case TIMESTAMP_WITH_TIMEZONE -> "java.time.OffsetDateTime";
            case TINYINT -> "java.lang.Byte";
            default -> "java.lang.Object";
        };
    }
}
