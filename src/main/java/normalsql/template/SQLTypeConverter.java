package normalsql.template;

import java.sql.Types;

// TODO from ChatGPT. Need to verify these mappings.

public class SQLTypeConverter {


    public static String getJavaClassName(int sqlType) {
        switch (sqlType) {
            case Types.ARRAY:
                return "java.sql.Array";
            case Types.BIGINT:
                return "java.lang.Long";
            case Types.BINARY:
                return "byte[]";
            case Types.BIT:
                return "java.lang.Boolean"; // Deprecated, replaced by BOOLEAN
            case Types.BLOB:
                return "java.sql.Blob";
            case Types.BOOLEAN:
                return "java.lang.Boolean";
            case Types.CHAR:
                return "java.lang.String";
            case Types.CLOB:
                return "java.sql.Clob";
            case Types.DATALINK:
                return "java.net.URL";
            case Types.DATE:
                return "java.sql.Date";
            case Types.DECIMAL:
                return "java.math.BigDecimal";
            case Types.DISTINCT:
                return "java.lang.Object";
            case Types.DOUBLE:
                return "java.lang.Double";
            case Types.FLOAT:
                return "java.lang.Double"; // FLOAT maps to Double in Java
            case Types.INTEGER:
                return "java.lang.Integer";
            case Types.JAVA_OBJECT:
                return "java.lang.Object";
            case Types.LONGNVARCHAR:
                return "java.lang.String";
            case Types.LONGVARBINARY:
                return "byte[]";
            case Types.LONGVARCHAR:
                return "java.lang.String";
            case Types.NCHAR:
                return "java.lang.String";
            case Types.NCLOB:
                return "java.sql.NClob";
            case Types.NULL:
                return "null";
            case Types.NUMERIC:
                return "java.math.BigDecimal";
            case Types.NVARCHAR:
                return "java.lang.String";
            case Types.OTHER:
                return "java.lang.Object";
            case Types.REAL:
                return "java.lang.Float";
            case Types.REF:
                return "java.sql.Ref";
            case Types.REF_CURSOR:
                return "java.sql.Ref"; // For SQL REF CURSOR
            case Types.ROWID:
                return "java.sql.RowId";
            case Types.SMALLINT:
                return "java.lang.Short";
            case Types.SQLXML:
                return "java.sql.SQLXML";
            case Types.STRUCT:
                return "java.sql.Struct";
            case Types.TIME:
                return "java.sql.Time";
            case Types.TIME_WITH_TIMEZONE:
                return "java.time.OffsetTime";
            case Types.TIMESTAMP:
                return "java.sql.Timestamp";
            case Types.TIMESTAMP_WITH_TIMEZONE:
                return "java.time.OffsetDateTime";
            case Types.TINYINT:
                return "java.lang.Byte";
            case Types.VARBINARY:
                return "byte[]";
            case Types.VARCHAR:
                return "java.lang.String";
            default:
                return "java.lang.Object"; // Fallback for unknown types
        }
    }

    public static void main(String[] args) {
        int sqlType = Types.VARCHAR;
        System.out.println(getJavaClassName(sqlType)); // Output: java.lang.String
    }
}
