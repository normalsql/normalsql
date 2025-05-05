// Copyright 2010-2025 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.sqlite;

import org.antlr.v4.runtime.tree.ParseTree;

public class
	BETWEEN
extends
	KnockoutL<BETWEEN.Pattern>
{
	/**
	 * Various patterns of literals and columns which can be matched.
	 *
	 * All combinations listed for reference. Unsupported combos commented out;
	 * They might be supported in the future.
	 * If you need a variant that's missing, please submit a feature request.
	 * Also suggest the method signatures you think should be generated.
	 */
	public enum Pattern
	{
	 	ColumnLiteralLiteral,  // 'column BETWEEN 0 AND 10'
//		ColumnColumnLiteral,   // 'left BETWEEN low AND 10'
//		ColumnLiteralColumn,   // 'left BETWEEN 0 AND high'

		LiteralColumnColumn    // '5 BETWEEN low AND high'
//		LiteralColumnLiteral,  // '5 BETWEEN low AND 10'
//		LiteralLiteralColumn,  // '5 BETWEEN 0 AND high'
//		LiteralLiteralLiteral  // '5 BETWEEN 0 AND 10'
	}

	public ParseTree test;
	public ParseTree low;
	public ParseTree high;

	public BETWEEN( GlobbingRuleContext context )
	{
		super( context );
		var term = context.find( "term" );
		test = term.get( 0 );
		low = term.get( 1 );
		high = term.get( 2 );
		pattern = valueOf( Pattern.class, test, low, high );
	}
}
