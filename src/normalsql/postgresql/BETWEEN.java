// Copyright 2010-2024 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.postgresql;

import normalsql.grammar.PostgreSQLParser.*;

public class
	BETWEEN
extends
	Knockout<TermBETWEENContext, BETWEEN.Pattern>
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

	public TermContext test;
	public TermContext low;
	public TermContext high;

	public BETWEEN( TermBETWEENContext context )
	{
		super( context );
		test = context.term( 0 );
		low = context.term( 1 );
		high = context.term( 2 );
		pattern = valueOf( Pattern.class, test, low, high );
	}
}
