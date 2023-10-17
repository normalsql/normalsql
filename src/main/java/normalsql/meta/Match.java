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

	public SubtermContext column;
	public SubtermContext literal;

	// TODO Rename to LIKE. I already forgot what a "match" is.
	public Match(PredicateMatchContext context )
	{
		super( context );
		column = (SubtermContext) context.parent.getChild( 0 );
		literal = context.subterm();
		pattern = valueOf( Pattern.class, literal );
	}
}
