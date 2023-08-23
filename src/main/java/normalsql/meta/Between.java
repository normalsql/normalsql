// Copyright 2010-2022 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.meta;

import normalsql.parse.NormalSQLParser.PredicateBETWEENContext;
import normalsql.parse.NormalSQLParser.SubtermContext;

/**
 * POJO holding the subterms of a BETWEEN predicate's parse tree.
 *
 * @author jasonosgood
 * @version $Id: $Id
 */
public class
	Between
extends
	Predicate<PredicateBETWEENContext, Between.Pattern>
{
	/**
	 * Various patterns of literals and columns which can be matched.
	 *
	 * Disabled variants kept for reference; They might be supported in the future.
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

	public Between( PredicateBETWEENContext context )
	{
		super( context );
		test = (SubtermContext) context.parent.getChild( 0 );
		low = context.subterm( 0 );
		high = context.subterm( 1 );
		pattern = valueOf( Pattern.class, test, low, high );
	}

}
