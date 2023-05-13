// Copyright 2010-2022 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.meta;

import normalsql.parse.NormalSQLParser.PredicateCompareContext;
import normalsql.parse.NormalSQLParser.SubtermContext;

import java.util.HashMap;

/**
 * <p>Comparison class.</p>
 *
 * @author jasonosgood
 * @version $Id: $Id
 */
public class
	Comparison
extends
	Predicate<PredicateCompareContext>
{
	/** comment */
	public enum Match
	{
		COL_VAL( COL, VAL ),
		VAL_COL( VAL, COL ),
		NotMatched( null, null );

		/** comment */
		public final Class left;
		/** comment */
		public final Class right;

		/** comment */
		Match( Class left, Class right )
		{
			this.left = left;
			this.right = right;
		}

		/** comment */
		public static Match match( SubtermContext left, SubtermContext right )
		{
			for( Match p : values() )
			{
				if( left.getClass() == p.left && right.getClass() == p.right ) return p;
			}
			return NotMatched;
		}
	}

	/** comment */
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

	/** comment */
	public SubtermContext value;
	/** comment */
	public SubtermContext column;
	/** comment */
	public String op;
	/** comment */
	public Match match;

	/**
	 * <p>Constructor for Comparison.</p>
	 *
	 * @param context a {@link normalsql.parse.NormalSQLParser.PredicateCompareContext} object
	 */
	public Comparison( PredicateCompareContext context )
	{
		super( context );
		SubtermContext left = (SubtermContext) context.parent.getChild( 0 );
		SubtermContext right = context.subterm();
		match = Match.match( left, right );

		if( !isMatched() ) return;

		op = operatorMap.get( context.op.getText() );
		switch( match )
		{
			case VAL_COL:
				value = left;
				column = right;
				break;
			case COL_VAL:
				value = right;
				column = left;
				break;
			default:
				break;
		}
	}

	/** {@inheritDoc} */
	@Override
	public boolean isMatched()
	{
		return match != Match.NotMatched;
	}
}
