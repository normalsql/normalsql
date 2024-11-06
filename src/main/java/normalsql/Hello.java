package normalsql;

import static java.sql.Types.*;

public class Hello {
    public static void main( String[] args )
    {
        Tool.INFO.log( "apple" );
        Tool.WARN.log( "banana" );
        Tool.DEBUG.log( "cherry" );
        Tool.ERROR.log( "daikon" );

        try
        {
            sniffles();
        }
        catch( Exception e )
        {
            Tool.INFO.log( "apple", e );
            Tool.WARN.log( "banana", e );
            Tool.DEBUG.log( "cherry", e );
            Tool.ERROR.log( "daikon", e );
        }

//        System.out.println( "Hello" );
    }

    public static Throwable sniffles()
    {
        throw new NullPointerException( "awchoo!" );
    }

    String gorp = switch ( 123 ) {
        case BIT -> "";
        case TINYINT -> "";
        case SMALLINT -> "";
        case INTEGER -> "";
        case BIGINT -> "";
        case FLOAT -> "";
        case REAL -> "";
        case DOUBLE -> "";
        case NUMERIC -> "";
        case DECIMAL -> "";
        case CHAR -> "";
        case VARCHAR -> "";
        case LONGVARCHAR -> "";
        case DATE -> "";
        case TIME -> "";
        case TIMESTAMP -> "";
        case BINARY -> "";
        case VARBINARY -> "";
        case LONGVARBINARY -> "";
        case NULL -> "";
        case OTHER -> "";
        case JAVA_OBJECT -> "";
        case DISTINCT -> "";
        case STRUCT -> "";
        case ARRAY -> "";
        case BLOB -> "";
        case CLOB -> "";
        case REF -> "";
        case DATALINK -> "";
        case BOOLEAN -> "";
        case ROWID -> "";
        case NCHAR -> "";
        case NVARCHAR -> "";
        case LONGNVARCHAR -> "";
        case NCLOB -> "";
        case SQLXML -> "";
        case REF_CURSOR -> "";
        case TIME_WITH_TIMEZONE -> "";
        case TIMESTAMP_WITH_TIMEZONE -> "";
        default -> throw new IllegalStateException("Unexpected value: " + 123);
    };

}
