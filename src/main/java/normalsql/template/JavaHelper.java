// Copyright 2010-2023 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

/*
 NormalSQL - JavaHelper.java

 Capitalization, type conversions, and misc helper methods.
*/
package normalsql.template;

import org.antlr.v4.runtime.RuleContext;

//import static java.lang.Character.toLowerCase;
//import static java.lang.Character.toUpperCase;
import static java.sql.Types.*;

public class
	JavaHelper
{
	/**
	 * <p>Constructor for JavaHelper.</p>
	 */
	public JavaHelper() {}

	/**
	 * <p>trimQuotes.</p>
	 *
	 * @param text a {@link java.lang.String} object
	 * @return a {@link java.lang.String} object
	 */
	public String trimQuotes( String text )
	{
		if( text == null ) return null;
		switch( text.charAt( 0 ) )
		{
			case '[': // T-SQL style
			case '"': // SQL identifier
			case '\'': // SQL string
			case '`': // MySQL?
				// No sanity checks. Lexer already ensured token has
				// matching quotes front and back
				text = text.substring( 1, text.length() - 1 );
				break;
			default:
				break;
		}
		return text;
	}

	/**
	 * <p>getTrimmedText.</p>
	 *
	 * @return a {@link java.lang.String} object
	 */
	public String getTrimmedText( RuleContext context )
	{
		// TODO fix this to include all tokens, incl. whitespace
		var text = context.getText();
		return trimQuotes( text );
	}

	public void signatures( Property property, String... method )
	{
		property.variable( toVariableCase( method ) );
		property.getter( "get" + toMethodCase( method ) );
		property.setter( "set" + toMethodCase( method ) );
	}

	// TODO remove invalid chars (eg spaces), toUpper, toLower, toCapitals, recognize abbreviations (eg "ID"), to snake_case
	// TODO refactor to Strategy pattern, to support other languages, idioms
	/**
	 * <p>toMethodCase.</p>
	 *
	 * @param name a {@link java.lang.String} object
	 * @return a {@link java.lang.String} object
	 */
	public String toMethodCase( String... name )
	{
		var temp = new String[name.length];
		for( int i = 0; i < name.length; i++ )
		{
			temp[i] = capitalize( name[i] );
		}
        return String.join( "", temp );
	}

	public String capitalize( String text )
	{
		if( text == null || text.isBlank() ) return null;
		if( "ID".equalsIgnoreCase( text )) return "ID";

		char original = text.charAt(0);
		char upper = Character.toUpperCase( original );
		if( original == upper ) return text;

		char[] chars = text.toCharArray();
		chars[0] = upper;
		return new String( chars );
	}

	/**
	 * <p>toVariableCase.</p>
	 *
	 * @param name a {@link java.lang.String} object
	 * @return a {@link java.lang.String} object
	 */
	public String toVariableCase( String... name )
	{
		return String.join( "_", name );
	}

//	/**
//	 * <p>isAllUpperCase.</p>
//	 *
//	 * @param s a {@link java.lang.String} object
//	 * @return a boolean
//	 */
//	public boolean isAllUpperCase( String s )
//	{
//		for( char c : s.toCharArray() )
//		{
//			if( Character.isLetter( c ) && Character.isLowerCase( c ))
//			{
//				return false;
//			}
//		}
//		return true;
//	}
	
//	// BIGPONY => BIGPONY
//	// bigPony => BigPony
//	// id => ID
//	/**
//	 * <p>toMethodName.</p>
//	 *
//	 * @param name a {@link java.lang.String} object
//	 * @return a {@link java.lang.String} object
//	 */
//	public String toMethodName( String name )
//	{
//		if( "id".equalsIgnoreCase( name ))
//		{
//			name = "ID";
//		}
//		else if( !isAllUpperCase( name ))
//		{
//			name = toUpperCase( name.charAt( 0 ) ) + name.substring( 1 );
//		}
//		return name;
//	}
//
//	// BIGPONY => bigpony
//	// BigPony => bigPony
//	// ID => id
//	/**
//	 * <p>toVariableName.</p>
//	 *
//	 * @param name a {@link java.lang.String} object
//	 * @return a {@link java.lang.String} object
//	 */
//	public String toVariableName( String name )
//	{
//		if( "id".equalsIgnoreCase( name ))
//		{
//			name = "id";
//		}
//		else if( isAllUpperCase( name ))
//		{
//			name = name.toLowerCase();
//		}
//		else
//		{
//			name = toLowerCase( name.charAt( 0 ) ) + name.substring( 1 );
//		}
//		return name;
//	}

	// TODO change to Types enum?
	/**
	 * <p>toPrintfConverter.</p>
	 *
	 * @param sqlType a int
	 * @return a {@link java.lang.String} object
	 */
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
	 *
	 * @param sqlType a int
	 * @param value a {@link java.lang.String} object
	 * @return a {@link java.lang.String} object
	 */
	// TODO: Add 'value' to Exception messages
	// TODO rename to toValueLiteral
	// TODO use Property.className instead of sqlType?
	public String convertToCode( int sqlType, String value )
	{
		String code;
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

}
