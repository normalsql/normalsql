/*
 Fado - JavaHelper.java

 Copyright 2022, 2014, 2011, 2010 Jason Osgood

 Capitalization and type conversions methods.
*/
package fado.template;

import fado.Property;
import fado.parse.GenericSQLParser.SubtermContext;
import fado.parse.GenericSQLParser.SubtermValueContext;

import static java.lang.Character.toLowerCase;
import static java.lang.Character.toUpperCase;
import static java.sql.Types.*;

public class
	JavaHelper
{
	public JavaHelper()
	{

	}

	// TODO separate factory methods for Statement and ResultSet properties
	public Property create( SubtermContext context, String... method )
	{
		Property prop = new Property();
		prop.context = ((SubtermValueContext) context).value();
		// TODO fix this to include all tokens, incl. whitespace
		prop.source = prop.context.getText();
		prop.trimmed = prop.context.getTrimmedText();
		prop.variable = toVariableCase( method );
		prop.getter = "get" + toMethodCase( method );
		prop.setter = "set" + toMethodCase( method );
		return prop;
	}

	// TODO remove invalid chars (eg spaces), toUpper, toLower, toCapitals, recognize abbreviations (eg "ID"), to snake_case
	// TODO refactor to Strategy pattern, to support other languages, idioms
	public String toMethodCase( String... name )
	{
		return String.join( "", name );
	}

	public String toVariableCase( String... name )
	{
		return String.join( "_", name );
	}

	public boolean isAllUpperCase( String s )
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
	// id => ID
	public String toMethodName( String name )
	{
		if( "id".equalsIgnoreCase( name ))
		{
			name = "ID";
		}
		else if( !isAllUpperCase( name ))
		{
			name = toUpperCase( name.charAt( 0 ) ) + name.substring( 1 );
		}
		return name;
	}
	
	// BIGPONY => bigpony
	// BigPony => bigPony
	// ID => id
	public String toVariableName( String name )
	{
		if( "id".equalsIgnoreCase( name ))
		{
			name = "id";
		}
		else if( isAllUpperCase( name ))
		{
			name = name.toLowerCase();
		}
		else
		{
			name = toLowerCase( name.charAt( 0 ) ) + name.substring( 1 );
		}
		return name;
	}

//	public String toVariableType( int sqlType )
//	{
//		String result = null;
//		switch( sqlType )
//		{
//			case ARRAY:
//				result = "java.sql.Array";
//				break;
//			case BIGINT:
////			result = "java.math.BigInteger";
//				result = "long";
//				break;
//			case BINARY:
//				result = "byte[]";
//				break;
//			case BIT:
//				result = "boolean";
//				break;
//			case BLOB:
//				result = "java.sql.Blob";
//				break;
//			case BOOLEAN:
//				result = "boolean";
//				break;
//			case CHAR:
//				result = "String";
//				break;
//			case CLOB:
//				result = "java.sql.Clob";
//				break;
//			case DATALINK:
//				throw new IllegalArgumentException( "don't know what to do with SQL Type DATALINK" );
//			case DATE:
//				result = "java.sql.Date";
//				break;
//			case DECIMAL:
//				result = "java.math.BigDecimal";
//				break;
//			case DISTINCT:
//				throw new IllegalArgumentException( "don't know what to do with SQL Type DISTINCT" );
//			case DOUBLE:
//				result = "double";
//				break;
//			case FLOAT:
//				result = "double";
//				break;
//			case INTEGER:
//				result = "int";
//				break;
//			case JAVA_OBJECT:
//				throw new IllegalArgumentException( "don't know what to do with SQL Type JAVA_OBJECT" );
//			case LONGVARBINARY:
//				result = "byte[]";
//				break;
//			case LONGVARCHAR:
//			case LONGNVARCHAR:
//				result = "String";
//				break;
//			case NUMERIC:
//				result = "java.math.BigDecimal";
//				break;
//			case NULL:
//				throw new IllegalArgumentException( "don't know what to do with SQL Type NULL" );
//			case OTHER:
//				throw new IllegalArgumentException( "don't know what to do with SQL Type OTHER" );
//			case REAL:
//				result = "float";
//				break;
//			case REF:
//				result = "java.sql.Ref";
//				break;
//			case SMALLINT:
//				result = "short";
//				break;
//			case STRUCT:
//				result = "java.sql.Struct";
//				break;
//			case TINYINT:
//				result = "byte";
//				break;
//			case TIME:
//				result = "java.sql.Time";
//				break;
//			case TIMESTAMP:
//				result = "java.sql.Timestamp";
//				break;
//			case VARBINARY:
//				result = "byte[]";
//				break;
//			case VARCHAR:
//				result = "String";
//				break;
//			default:
//				result = "unknown SQL Type: " + sqlType;
//				throw new IllegalArgumentException( result );
//		}
//		return result;
//	}

	// TODO change to Types enum?
	public String toPrintfConverter( int sqlType )
	{
		String result = null;
		switch( sqlType )
		{
//			case ARRAY:
//				result = "java.sql.Array";
//				break;
			case BIGINT:
				result = "%d";
				break;
//			case BINARY:
//				result = "byte[]";
//				break;
			case BIT:
				result = "%b";
				break;
//			case BLOB:
//				result = "java.sql.Blob";
//				break;
			case BOOLEAN:
				result = "%b";
				break;
			case CHAR:
				result = "'%s'";
				break;
//			case CLOB:
//				result = "java.sql.Clob";
//				break;
//			case DATALINK:
//				throw new IllegalArgumentException( "don't know what to do with SQL Type DATALINK" );
			case DATE:
				result = "'%t'";
				break;
			case DECIMAL:
				result = "%f";
				break;
			case DISTINCT:
				result = "%d";
				break;
			case DOUBLE:
				result = "%f";
				break;
			case FLOAT:
				result = "%f";
				break;
			case INTEGER:
				result = "%d";
				break;
//			case JAVA_OBJECT:
//				throw new IllegalArgumentException( "don't know what to do with SQL Type JAVA_OBJECT" );
//			case LONGVARBINARY:
//				result = "byte[]";
//				break;
			case LONGVARCHAR:
			case LONGNVARCHAR:
				result = "'%s'";
				break;

			case NUMERIC:
				result = "%d";
				break;
//			case NULL:
//				throw new IllegalArgumentException( "don't know what to do with SQL Type NULL" );
//			case OTHER:
//				throw new IllegalArgumentException( "don't know what to do with SQL Type OTHER" );
			case REAL:
				result = "%f";
				break;
//			case REF:
//				result = "java.sql.Ref";
//				break;
			case SMALLINT:
				result = "%d";
				break;
//			case STRUCT:
//				result = "java.sql.Struct";
//				break;
			case TINYINT:
				result = "%d";
				break;
//			case TIME:
//				result = "java.sql.Time";
//				break;
//			case TIMESTAMP:
//				result = "java.sql.Timestamp";
//				break;
//			case VARBINARY:
//				result = "byte[]";
//				break;
			case VARCHAR:
				result = "'%s'";
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
	 */
	// TODO: Add 'value' to Exception messages
	public String convertToCode( int sqlType, String value )
	{
		String code = null;
		switch( sqlType )
		{
			case CHAR:
			case VARCHAR:
			case LONGVARCHAR:
			case LONGNVARCHAR:
				code = "\"" + value + "\"";
				break;
			case BIGINT:
//				code = "new java.math.BigInteger( " + literal + " );";
				code = value + "L";
				break;
			case BIT:
			case BOOLEAN:
			{
				// assume value is already either "true" or "false"?
				boolean ugh = Boolean.parseBoolean( value );
				code = ugh ? "true" : "false";
				break;
			}
			case BINARY:
				throw new IllegalArgumentException( "don't know what to do with SQL Type BINARY" );

			case ARRAY:
				throw new IllegalArgumentException( "don't know what to do with SQL Type BINARY" );
			case BLOB:
				throw new IllegalArgumentException( "don't know what to do with SQL Type BLOB" );
			case CLOB:
				throw new IllegalArgumentException( "don't know what to do with SQL Type CLOB" );
			case DATALINK:
				throw new IllegalArgumentException( "don't know what to do with SQL Type DATALINK" );
			case DECIMAL:
				code = "new java.math.BigDecimal( " + value + " );";
				break;
			case DISTINCT:
				throw new IllegalArgumentException( "don't know what to do with SQL Type DISTINCT" );
			case DOUBLE:
				code = value + "d";
				break;
			case FLOAT:
			case REAL:
				code = value + "f";
				break;
			case INTEGER:
				code = value;
				break;
			case JAVA_OBJECT:
				throw new IllegalArgumentException( "don't know what to do with SQL Type JAVA_OBJECT" );

			case LONGVARBINARY:
				throw new IllegalArgumentException( "don't know what to do with SQL Type LONGVARBINARY" );

			case NUMERIC:
				code = "new java.math.BigDecimal( " + value + " );";
				break;
			case NULL:
				throw new IllegalArgumentException( "don't know what to do with SQL Type NULL" );
			case OTHER:
				throw new IllegalArgumentException( "don't know what to do with SQL Type OTHER" );
			case REF:
				throw new IllegalArgumentException( "don't know what to do with SQL Type REF" );
			case SMALLINT:
				code = "(short) " + value;
				break;
			case TINYINT:
				code = "(byte) " + value;
				break;

			case STRUCT:
				throw new IllegalArgumentException( "don't know what to do with SQL Type STRUCT" );

			case DATE:
				code = "java.sql.Date.valueOf( \"" + value + "\" )";
				break;
			case TIME:
				code = "java.sql.Time.valueOf( \"" + value + "\" )";
				break;
			case TIMESTAMP:
				code = "java.sql.Timestamp.valueOf( \"" + value + "\" )";
				break;
			case VARBINARY:
				throw new IllegalArgumentException( "don't know what to do with SQL Type VARBINARY" );

			default:
				code = "unknown SQL Type: " + sqlType;
				throw new IllegalArgumentException( code );
		}
		return code;
	}

	public String getInitializerValue( int sqlType )
	{
		String result = null;
		switch( sqlType )
		{
			case ARRAY:
				result = "null";
				break;
			case BIGINT:
				result = "0L";
//				result = "null";
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
			case LONGNVARCHAR:
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
}
