// Copyright 2010-2023 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.parse;

import normalsql.parse.NormalSQLParser.PredicateBETWEENContext;
import normalsql.parse.NormalSQLParser.SubtermContext;

public class
	BETWEEN
extends
	Knockout<PredicateBETWEENContext, BETWEEN.Pattern>
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

	public SubtermContext test;
	public SubtermContext low;
	public SubtermContext high;

	public BETWEEN( PredicateBETWEENContext context )
	{
		super( context );
		test = (SubtermContext) context.parent.getChild( 0 );
		low = context.subterm( 0 );
		high = context.subterm( 1 );
		pattern = valueOf( Pattern.class, test, low, high );
	}
}
