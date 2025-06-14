// Copyright 2010-2025 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.knockout;

import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Map;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;

public class
	Comparison
extends
		Knockout<Comparison.Pattern>
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

	public String    op;
	public ParseTree literal;
	public ParseTree column;

	public Comparison( GlobbingRuleContext context )
	{
		super( context );
//		op = operatorMap.get( context.compare.getText() );
		ParseTree left = context.getChild( 0 );
		ParseTree operator = context.getChild( 1 );
		ParseTree right = context.getChild( 2 );
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
