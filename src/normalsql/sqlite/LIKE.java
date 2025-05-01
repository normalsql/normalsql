// Copyright 2010-2025 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.sqlite;

import normalsql.grammar.PostgreSQLParser.TermContext;
import normalsql.grammar.PostgreSQLParser.TermLIKEContext;

/*
	Represents LIKE, ILIKE, REGEXP, etc. expressions
 */
public class
	LIKE
extends
        Knockout<TermLIKEContext, LIKE.Pattern>
{
	public enum Pattern
	{
		Literal
	}

	public TermContext column;
	public TermContext literal;

	public LIKE( TermLIKEContext context )
	{
		super( context );
		column = context.term( 0 );
		literal = context.term( 1 );
		pattern = valueOf( Pattern.class, literal );
	}
}
