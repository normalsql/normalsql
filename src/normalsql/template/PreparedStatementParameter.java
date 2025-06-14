// Copyright 2010-2025 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.template;

import normalsql.grammar.NormalSQLParser.SubtermContext;
import normalsql.grammar.NormalSQLParser.SubtermLiteralContext;
import normalsql.grammar.PostgreSQLParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * POJO representing a PreparedStatement Parameter's metadata
 */
public class
	PreparedStatementParameter
extends
	Property
{
	public PreparedStatementParameter( SubtermContext context )
	{
		context( ((SubtermLiteralContext) context).literal() );
		original( context.getText() );
	}

	public PreparedStatementParameter( PostgreSQLParser.TermContext context )
	{
		context( ((PostgreSQLParser.TermLiteralContext) context).literal() );
		original( context.getText() );
	}

	public PreparedStatementParameter( ParseTree context, String original )
	{
		context( context );
		original( original );
	}

	public PreparedStatementParameter( ParseTree context )
	{
		context( context );
	}

//	public boolean isSigned;
//	public int scaled;
//	public int precision;
//	public int mode;

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
