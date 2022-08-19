package fado.meta;

import static java.lang.Character.toLowerCase;
import static java.lang.Character.toUpperCase;
import static java.sql.Types.*;

public class 
	Util 
{
	public static boolean isAllUpperCase( String s ) 
	{
		for( char c : s.toCharArray() ) 
		{
			if( Character.isLetter( c ) && Character.isLowerCase( c )) 
			{
				return false;
			}
		}
		return true;
	}
	
	// BIGPONY => BIGPONY
	// bigPony => BigPony
	public static String toMethodName( String name )
	{
		if( !Util.isAllUpperCase( name ))
		{
			name = toUpperCase( name.charAt( 0 ) ) + name.substring( 1 );
		}
		return name;
	}
	
	// BIGPONY => bigpony
	// BigPony => bigPony
	public static String toVariableName( String name )
	{
		if( Util.isAllUpperCase( name ))
		{
			name = name.toLowerCase();
		}
		else
		{
			name = toLowerCase( name.charAt( 0 ) ) + name.substring( 1 );
		}
		return name;
	}

	public static String toMethodType( int sqlType )
	{
		String result = null;
		switch( sqlType )
		{
			case ARRAY:
				result = "Array";
				break;
			case BIGINT:
//				result = "BigInteger";
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
				result = "Bytes";
				break;
			case LONGVARCHAR:
			case LONGNVARCHAR:
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

	public static String toVariableType( int sqlType )
	{
		String result = null;
		switch( sqlType )
		{
			case ARRAY:
				result = "java.sql.Array";
				break;
			case BIGINT:
//			result = "java.math.BigInteger";
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
			case LONGNVARCHAR:
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

}
