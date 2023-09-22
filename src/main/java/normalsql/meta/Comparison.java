// Copyright 2010-2022 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.meta;

import normalsql.parse.NormalSQLParser.PredicateCompareContext;
import normalsql.parse.NormalSQLParser.TermContext;

import java.util.HashMap;

/**
 * Comparison class.
 *
 * @author jasonosgood
 * @version $Id: $Id
 */
public class
	Comparison
extends
	Predicate<PredicateCompareContext, Comparison.Pattern>
{
	public enum Pattern
	{
		ColumnLiteral,
		LiteralColumn
	}

	static HashMap<String, String> operatorMap = new HashMap<>();

	static {
		operatorMap.put( "=", "EQ" );
		operatorMap.put( ":=", "EQ" );
		operatorMap.put( "<>", "NEQ" );
		operatorMap.put( "!=", "NEQ" );
		operatorMap.put( "<", "LT" );
		operatorMap.put( "<=", "LTE" );
		operatorMap.put( ">", "GT" );
		operatorMap.put( ">=", "GTE" );
		operatorMap.put( "&&", "OVERLAP" );
	}

	public String op;
	public TermContext literal;
	public TermContext column;

	public Comparison( PredicateCompareContext context )
	{
		super( context );
		op = operatorMap.get( context.compare().getText() );
		TermContext left = (TermContext) context.parent.getChild( 0 );
		TermContext right = context.term();
		pattern = valueOf( Pattern.class, left, right );

		if( !isMatched() ) return;

		// TODO: Move this logic to Worker, like for Between predicates
		switch( pattern )
		{
			case LiteralColumn:
				literal = left;
				column = right;
				break;
			case ColumnLiteral:
				literal = right;
				column = left;
				break;
			default:
				break;
		}
	}
}
