// Copyright 2010-2025 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.knockout;

import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

/*
	Represents LIKE, ILIKE, REGEXP, etc. expressions
 */
public class
	LIKE
extends
	Knockout<LIKE.Pattern>
{
	public enum Pattern
	{
		// TODO change this to ColumnLiteral
		Literal
	}

	public ParseTree column;
	public ParseTree literal;

	public LIKE( RuleContext context )
	{
		super( context );
		column  = context.getChild( 0 );
		literal  = context.getChild( 1 );
		pattern = valueOf( Pattern.class, literal );
	}
}
