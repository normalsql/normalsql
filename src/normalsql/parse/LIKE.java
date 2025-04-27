// Copyright 2010-2025 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.parse;

import normalsql.grammar.NormalSQLParser.*;

/*
	Represents LIKE, ILIKE, REGEXP, etc. expressions
 */
public class
	LIKE
extends
	Knockout<SubtermLIKEContext, LIKE.Pattern>
{
	public enum Pattern
	{
		Literal
	}

	public SubtermContext column;
	public SubtermContext literal;

	public LIKE( SubtermLIKEContext context )
	{
		super( context );
		column = context.subterm().get( 0 );
		literal = context.subterm().get( 1 );
		pattern = valueOf( Pattern.class, literal );
	}
}
