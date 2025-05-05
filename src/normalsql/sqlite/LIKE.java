// Copyright 2010-2025 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.sqlite;

import normalsql.grammar.PostgreSQLParser.TermContext;
import normalsql.grammar.PostgreSQLParser.TermLIKEContext;
import org.antlr.v4.runtime.tree.ParseTree;

/*
	Represents LIKE, ILIKE, REGEXP, etc. expressions
 */
public class
	LIKE
extends
	KnockoutL<LIKE.Pattern>
{
	public enum Pattern
	{
		// TODO change this to ColumnLiteral
		Literal
	}

	public ParseTree column;
	public ParseTree literal;

	public LIKE( GlobbingRuleContext context )
	{
		super( context );
		var terms = context.find( "term" );
		column  = terms.get( 0 );
		literal = terms.get( 1 );

		pattern = valueOf( Pattern.class, literal );
	}
}
