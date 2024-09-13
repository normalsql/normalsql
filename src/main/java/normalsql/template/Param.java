// Copyright 2010-2023 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.template;

/**
 * POJO for a PreparedStatement Parameter.
 */
public class Param
	extends Property
{
//	enum IsNullable
//	{
//		parameterNoNulls,
//		parameterNullable,
//		parameterNullableUnknown
//	}
//
//	enum Mode
//	{
//		parameterModeUnknown,
//		parameterModeIn,
//		parameterModeInOut,
//		parameterModeOut
//	}

//	public int nth;
//	public int sqlType;
//	public String sqlTypeName;
//	public int isNullable;
//	public String className;

//	public boolean isSigned;
//	public int scaled;
//	public int precision;
//	public int mode;

	/**
	 * Original literal copied from the SQL source code. Later transformed into Java source code.
	 * Also used by templates to populate JavaDocs.
	 */
	public String original;
	public String original() {return original;}
	public void original(String original	) { this.original = original; }

	/**
	 * The original default SQL value literal transliterated to Java source.
	 *  eg SQL's FLOAT 10.0 would become Java's <code>10.0f</code>.
	 * Used by PreparedStatement templates.
	 *
	 */
	// TODO rename to 'valueLiteral'
	public String asCode;
	public String asCode() {return asCode;}
	public void asCode(String asCode	) { this.asCode = asCode; }
}
