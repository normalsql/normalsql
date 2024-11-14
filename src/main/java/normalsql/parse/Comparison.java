// Copyright 2010-2024 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.parse;

import normalsql.parse.NormalSQLParser.*;

import java.util.Map;
import static java.util.Map.entry;
import static java.util.Map.ofEntries;

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

	/**

	Map from lexer token to mnemonic. Used to generate accessor methods. eg "setCountGTE( value )".

	copypasta from NormalSQL.g4 2024-01-10

    compare
        : EQ | COMPARE | TILDE | MATCH ;

	EQ      : '=' ;
	COMPARE : '==' | '<>' | '!=' | '<' | '<=' | '>' | '>=' | '&&' ;
	TILDE   : '~' ;
	MATCH   : '~*' | '!~' | '!~*' ;

	*/


	static final Map<String, String> operatorMap = ofEntries(
		entry( "=", "EQ" ),
		entry( "==", "EQ" ),
		entry( "<>", "NEQ" ),
		entry( "!=", "NEQ" ),
		entry( "<", "LT" ),
		entry( "<=", "LTE" ),
		entry( ">", "GT" ),
		entry( ">=", "GTE" ),
		entry( "&&", "OVERLAP" ),

		entry( "~", "MATCH" ),
		entry( "~*", "MATCH" ),
		entry( "!~", "MATCH" ),
		entry( "!~*", "MATCH" )
	);



	public String op;
	public SubtermContext literal;
	public SubtermContext column;

	public Comparison( SubtermCompareContext context )
	{
		super( context );
		op = operatorMap.get( context.compare().getText() );
		SubtermContext left = context.subterm( 0 );
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
