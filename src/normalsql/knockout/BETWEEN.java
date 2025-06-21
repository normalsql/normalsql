// Copyright 2010-2025 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.knockout;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.xpath.XPath;

import java.util.Iterator;

public class
	BETWEEN
extends
	Knockout<BETWEEN.Pattern>
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

	public BETWEEN( RuleContext context, Parser parser )
    {
        super( context );
		var gah  = XPath.findAll( context, "/term/term", parser );
        var iter = gah.iterator();
        test = iter.next();
        low = iter.next();
        high = iter.next();

		pattern = valueOf( Pattern.class, test, low, high );
	}
}
