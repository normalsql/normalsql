// Copyright 2010-2023 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.template;

import normalsql.parse.NormalSQLParser.SubtermContext;
import normalsql.parse.NormalSQLParser.SubtermLiteralContext;

/**
 * POJO for a PreparedStatement Parameter.
 */
public class
	Param
extends
	Property
{
	public Param( SubtermContext context )
	{
		context( ((SubtermLiteralContext) context).literal() );
		original( context().getText() );
	}



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
	public void original( String original ) { this.original = original; }

	/**
	 * Translated version of original literal. eg SQL FLOAT 10.0 translates to Java <code>10.0f</code>.
	 */
	public String translated;
	public String translated() {return translated;}
	public void translated( String asCode ) { this.translated = asCode; }
}

/**
 enum IsNullable
 {
 parameterNoNulls,
 parameterNullable,
 parameterNullableUnknown
 }

 enum Mode
 {
 parameterModeUnknown,
 parameterModeIn,
 parameterModeInOut,
 parameterModeOut
 }

 */
