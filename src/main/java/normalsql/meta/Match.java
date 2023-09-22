// Copyright 2010-2022 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.meta;

import normalsql.parse.NormalSQLParser.*;

/*
	Represents LIKE, ILIKE, and REGEXP predicates.
 */
public class
	Match
extends
	Predicate<PredicateMatchContext, Match.Pattern>
{
	public enum Pattern
	{
		Literal
	}

	public TermContext column;
	public TermContext literal;

	public Match(PredicateMatchContext context )
	{
		super( context );
		column = (TermContext) context.parent.getChild( 0 );
		literal = context.term();
		pattern = valueOf( Pattern.class, literal );
	}
}
