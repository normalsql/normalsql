// Copyright 2010-2023 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.parse;

import normalsql.parse.NormalSQLParser.*;

import java.util.HashMap;

public class
	Comparison
extends
	Knockout<SubtermCompareContext, Comparison.Pattern>
{
	public enum Pattern
	{
		ColumnLiteral,
		LiteralColumn
	}

	static HashMap<String, String> operatorMap = new HashMap<>();


	// TODO add missing operators, verify
	// TILDE   : '~' ;
	// MATCH   : '~*' | '!~' | '!~*' ;

	// TODO maybe there's a new constructor
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

	public Comparison( SubtermCompareContext context )
	{
		super( context );
		op = operatorMap.get( context.compare().getText() );
		SubtermContext left = context.subterm( 1 );
		SubtermContext right = context.subterm( 1 );
		pattern = valueOf( Pattern.class, left, right );

		if( !isMatched() ) return;

		// TODO: Move this logic to Worker, like for BETWEEN predicates
		switch( pattern )
		{
			case LiteralColumn -> { literal = left; column = right; }
			case ColumnLiteral -> { literal = right; column = left; }
		}
	}
}
