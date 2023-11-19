// Copyright 2010-2023 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.parse;

import normalsql.parse.NormalSQLParser.PredicateOperatorContext;
import normalsql.parse.NormalSQLParser.SubtermContext;

import java.util.HashMap;

public class
	Comparison
extends
	Knockout<PredicateOperatorContext, Comparison.Pattern>
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
	public SubtermContext literal;
	public SubtermContext column;

	public Comparison(NormalSQLParser.PredicateOperatorContext context )
	{
		super( context );
////		op = operatorMap.get( context.compare().getText() );
//		op = operatorMap.get( context.COMPARE().getText() );
//		SubtermContext left = (SubtermContext) context.parent.getChild( 0 );
//		SubtermContext right = context.subterm();
//		pattern = valueOf( Pattern.class, left, right );
//
//		if( !isMatched() ) return;
//
//		// TODO: Move this logic to Worker, like for BETWEEN predicates
//		switch( pattern )
//		{
//			case LiteralColumn -> { literal = left; column = right; }
//			case ColumnLiteral -> { literal = right; column = left; }
//		}
	}
}
