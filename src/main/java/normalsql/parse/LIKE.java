// Copyright 2010-2023 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.parse;

import normalsql.parse.NormalSQLParser.*;

/*
	Represents LIKE, ILIKE, and REGEXP predicates.
 */
public class
	LIKE
extends
	Knockout<PredicateLIKEContext, LIKE.Pattern>
{
	public enum Pattern
	{
		Literal
	}

	public SubtermContext column;
	public SubtermContext literal;

	public LIKE( PredicateLIKEContext context )
	{
		super( context );
		column = (SubtermContext) context.parent.getChild( 0 );
		literal = context.subterm().get( 0 );
		pattern = valueOf( Pattern.class, literal );
	}
}
