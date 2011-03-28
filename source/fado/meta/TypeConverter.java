package fado.meta;

import static java.sql.Types.ARRAY;
import static java.sql.Types.BIGINT;
import static java.sql.Types.BINARY;
import static java.sql.Types.BIT;
import static java.sql.Types.BLOB;
import static java.sql.Types.BOOLEAN;
import static java.sql.Types.CHAR;
import static java.sql.Types.CLOB;
import static java.sql.Types.DATALINK;
import static java.sql.Types.DATE;
import static java.sql.Types.DECIMAL;
import static java.sql.Types.DISTINCT;
import static java.sql.Types.DOUBLE;
import static java.sql.Types.FLOAT;
import static java.sql.Types.INTEGER;
import static java.sql.Types.JAVA_OBJECT;
import static java.sql.Types.LONGVARBINARY;
import static java.sql.Types.LONGVARCHAR;
import static java.sql.Types.NULL;
import static java.sql.Types.NUMERIC;
import static java.sql.Types.OTHER;
import static java.sql.Types.REAL;
import static java.sql.Types.REF;
import static java.sql.Types.SMALLINT;
import static java.sql.Types.STRUCT;
import static java.sql.Types.TIME;
import static java.sql.Types.TIMESTAMP;
import static java.sql.Types.TINYINT;
import static java.sql.Types.VARBINARY;
import static java.sql.Types.VARCHAR;
import fado.parse.GenericSQLLexer;

public class 
	TypeConverter 
{
	public static String getJavaType( int sqlType )
	{
		String result = null;
		switch( sqlType )
		{
		case ARRAY:
			result = "java.sql.Array";
			break;
		case BIGINT:
			result = "long";
			break;
		case BINARY:
			result = "byte[]";
			break;
		case BIT:
			result = "boolean";
			break;
		case BLOB:
			result = "java.sql.Blob";
			break;
		case BOOLEAN:
			result = "boolean";
			break;
		case CHAR:
			result = "java.lang.String";
			break;
		case CLOB:
			result = "java.sql.Clob";
			break;
		case DATALINK:
			throw new IllegalArgumentException( "don't know what to do with SQL Type DATALINK" );
		case DATE:
			result = "java.sql.Date";
			break;
		case DECIMAL:
			result = "java.math.BigDecimal";
			break;
		case DISTINCT:
			throw new IllegalArgumentException( "don't know what to do with SQL Type DISTINCT" );
		case DOUBLE:
			result = "double";
			break;
		case FLOAT:
			result = "double";
			break;
		case INTEGER:
			result = "int";
			break;
		case JAVA_OBJECT:
			throw new IllegalArgumentException( "don't know what to do with SQL Type JAVA_OBJECT" );
		case LONGVARBINARY:
			result = "byte[]";
			break;
		case LONGVARCHAR:
			result = "java.lang.String";
			break;
			
		case NUMERIC:
			result = "java.math.BigDecimal";
			break;
		case NULL:
			throw new IllegalArgumentException( "don't know what to do with SQL Type NULL" );
		case OTHER:
			throw new IllegalArgumentException( "don't know what to do with SQL Type OTHER" );
		case REAL:
			result = "float";
			break;
		case REF:
			result = "java.sql.Ref";
			break;
		case SMALLINT:
			result = "short";
			break;
		case STRUCT:
			result = "java.sql.Struct";
			break;
		case TINYINT:
			result = "byte";
			break;
		case TIME:
			result = "java.sql.Time";
			break;
		case TIMESTAMP:
			result = "java.sql.Timestamp";
			break;
		case VARBINARY:
			result = "byte[]";
			break;
		case VARCHAR:
			result = "String";
			break;
		default:
			result = "unknown SQL Type: " + sqlType;
			throw new IllegalArgumentException( result );
				
		}
		return result;
	}

	public static String getInitializerValue( int sqlType )
	{
		String result = null;
		switch( sqlType )
		{
		case ARRAY:
			result = "null";
			break;
		case BIGINT:
			result = "0L";
			break;
		case BINARY:
			result = "null";
			break;
		case BIT:
			result = "false";
			break;
		case BLOB:
			result = "null";
			break;
		case BOOLEAN:
			result = "false";
			break;
		case CHAR:
			result = "null";
			break;
		case CLOB:
			result = "null";
			break;
		case DATALINK:
			throw new IllegalArgumentException( "don't know what to do with SQL Type DATALINK" );
		case DATE:
			result = "null";
			break;
		case DECIMAL:
			result = "null";
			break;
		case DISTINCT:
			throw new IllegalArgumentException( "don't know what to do with SQL Type DISTINCT" );
		case DOUBLE:
			result = "0d";
			break;
		case FLOAT:
			result = "0f";
			break;
		case INTEGER:
			result = "0";
			break;
		case JAVA_OBJECT:
			throw new IllegalArgumentException( "don't know what to do with SQL Type JAVA_OBJECT" );
		case LONGVARBINARY:
			result = "null";
			break;
		case LONGVARCHAR:
			result = "null";
			break;
			
		case NUMERIC:
			result = "null";
			break;
		case NULL:
			throw new IllegalArgumentException( "don't know what to do with SQL Type NULL" );
		case OTHER:
			throw new IllegalArgumentException( "don't know what to do with SQL Type OTHER" );
		case REAL:
			result = "0f";
			break;
		case REF:
			result = "null";
			break;
		case SMALLINT:
			result = "0";
			break;
		case STRUCT:
			result = "null";
			break;
		case TINYINT:
			result = "0";
			break;
		case TIME:
			result = "null";
			break;
		case TIMESTAMP:
			result = "null";
			break;
		case VARBINARY:
			result = "null";
			break;
		case VARCHAR:
			result = "null";
			break;
		default:
			result = "unknown SQL Type: " + sqlType;
			throw new IllegalArgumentException( result );
				
		}
		return result;
	}

	/**
	 * Convert value to an appropriate Java code value/instance declaration. Used by 
	 * code generating templates.
	 * 
	 * @param sqlType
	 * @param value
	 * @return
	 */
	public static String getValueAsCode( int sqlType, String value )
	{
		String result = null;
		switch( sqlType )
		{
		case CHAR:
		case LONGVARCHAR:
		case VARCHAR:
			result = "\"" + value + "\"";
			break;
		case BIGINT:
			result = "long";
			break;
		case BIT:
		case BOOLEAN:
		{
			// assume value is already either "true" or "false"?
			boolean ugh = Boolean.parseBoolean( value );
			result = ugh ? "true" : "false";
			break;
		}
		case BINARY:
			result = "byte[]";
			break;
		case ARRAY:
			result = "java.sql.Array";
			break;
		case BLOB:
			result = "java.sql.Blob";
			break;
		case CLOB:
			result = "java.sql.Clob";
			break;
		case DATALINK:
			throw new IllegalArgumentException( "don't know what to do with SQL Type DATALINK" );
		case DECIMAL:
			result = "java.math.BigDecimal";
			break;
		case DISTINCT:
			throw new IllegalArgumentException( "don't know what to do with SQL Type DISTINCT" );
		case DOUBLE:
			result = value + "d";
			break;
		case FLOAT:
		case REAL:
			result = value + "f";
			break;
		case INTEGER:
			result = value;
			break;
		case JAVA_OBJECT:
			throw new IllegalArgumentException( "don't know what to do with SQL Type JAVA_OBJECT" );
		case LONGVARBINARY:
			result = "byte[]";
			
		case NUMERIC:
			result = "java.math.BigDecimal";
			break;
		case NULL:
			throw new IllegalArgumentException( "don't know what to do with SQL Type NULL" );
		case OTHER:
			throw new IllegalArgumentException( "don't know what to do with SQL Type OTHER" );
		case REF:
			result = "java.sql.Ref";
			break;
		case SMALLINT:
			result = "short";
			break;
		case TINYINT:
			result = "byte";
			break;
		case STRUCT:
			result = "java.sql.Struct";
			break;
		case DATE:
			result = "java.sql.Date";
			break;
		case TIME:
			result = "java.sql.Time";
			break;
		case TIMESTAMP:
			result = "Date";
			break;
		case VARBINARY:
			result = "byte[]";
			break;
		default:
			result = "unknown SQL Type: " + sqlType;
			throw new IllegalArgumentException( result );
				
		}
		return result;
	}

	public static String getTypedMethod( int sqlType )
	{
		String result = null;
		switch( sqlType )
		{
		case ARRAY:
			result = "Array";
			break;
		case BIGINT:
			result = "Long";
			break;
		case BINARY:
			result = "byte[]";
			break;
		case BIT:
			result = "Boolean";
			break;
		case BLOB:
			result = "Blob";
			break;
		case BOOLEAN:
			result = "Boolean";
			break;
		case CHAR:
			result = "String";
			break;
		case CLOB:
			result = "Clob";
			break;
		case DATALINK:
			throw new IllegalArgumentException( "don't know what to do with SQL Type DATALINK" );
		case DATE:
			result = "Date";
			break;
		case DECIMAL:
			result = "BigDecimal";
			break;
		case DISTINCT:
			throw new IllegalArgumentException( "don't know what to do with SQL Type DISTINCT" );
		case DOUBLE:
			result = "Double";
			break;
		case FLOAT:
			result = "Double";
			break;
		case INTEGER:
			result = "Int";
			break;
		case JAVA_OBJECT:
			throw new IllegalArgumentException( "don't know what to do with SQL Type JAVA_OBJECT" );
		case LONGVARBINARY:
			result = "byte[]";
			break;
		case LONGVARCHAR:
			result = "String";
			break;
			
		case NUMERIC:
			result = "BigDecimal";
			break;
		case NULL:
			throw new IllegalArgumentException( "don't know what to do with SQL Type NULL" );
		case OTHER:
			throw new IllegalArgumentException( "don't know what to do with SQL Type OTHER" );
		case REAL:
			result = "Float";
			break;
		case REF:
			result = "Ref";
			break;
		case SMALLINT:
			result = "Short";
			break;
		case STRUCT:
			result = "Object";
			break;
		case TINYINT:
			result = "Byte";
			break;
		case TIME:
			result = "Time";
			break;
		case TIMESTAMP:
			result = "Timestamp";
			break;
		case VARBINARY:
			result = "byte[]";
			break;
		case VARCHAR:
			result = "String";
			break;
		default:
			result = "unknown SQL Type: " + sqlType;
			throw new IllegalArgumentException( result );
				
		}
		return result;
	}

	public static int fromLiteralTypeToSqlType( int literalType )
	{
		int sqlType = java.sql.Types.TIMESTAMP;
		switch( literalType )
		{
			case GenericSQLLexer.Timestamp:
				sqlType = java.sql.Types.TIMESTAMP;
				break;
			case GenericSQLLexer.String:
				sqlType = java.sql.Types.VARCHAR;
				break;
			case GenericSQLLexer.Float:
				sqlType = java.sql.Types.REAL;
				break;
			case GenericSQLLexer.Integer:
				sqlType = java.sql.Types.INTEGER;
				break;
			default:
				break;
		}
		return sqlType;
	}	

}
