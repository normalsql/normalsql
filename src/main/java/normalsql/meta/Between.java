// Copyright 2010-2022 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.meta;

import normalsql.parse.NormalSQLParser.PredicateBETWEENContext;
import normalsql.parse.NormalSQLParser.SubtermContext;

/**
 * Needed bits from parse tree of a BETWEEN clause
 */
public class
	Between
extends
	Predicate<PredicateBETWEENContext>
{
	/**
	 * enum of clause patterns matched (recognized)
	 */
	public enum Match
	{
		/**
		 * eg 'column BETWEEN 0 AND 10'
		 */
		COL_VAL_VAL( COL, VAL, VAL ),
//		COL_COL_VAL( COL, COL, VAL ),  // eg 'column BETWEEN low AND 10'
//		COL_VAL_COL( COL, VAL, COL ),  // eg 'column BETWEEN 0 AND high'
		/**
		 * eg '5 BETWEEN low AND high'
		 */
		VAL_COL_COL( VAL, COL, COL ),
//		VAL_COL_VAL( VAL, COL, VAL ),  // eg '5 BETWEEN low AND 10'
//		VAL_VAL_COL( VAL, VAL, COL ),  // eg '5 BETWEEN 0 AND high'
//		VAL_VAL_VAL( VAL, VAL, VAL ),  // eg '5 BETWEEN 0 AND 10'
		/**
		 * no match
		 */
		NotMatched( null, null, null );

		/**
		 * The Left.
		 */
		public final Class left;
		/**
		 * The Low.
		 */
		public final Class low;
		/**
		 * The High.
		 */
		public final Class high;

		Match( Class left, Class low, Class high )
		{
			this.left = left;
			this.low = low;
			this.high = high;
		}

		/**
		 * Match match.
		 *
		 * @param left the left
		 * @param low  the low
		 * @param high the high
		 * @return the match
		 */
		public static Match match( SubtermContext left, SubtermContext low, SubtermContext high )
		{
			for( Match p : values() )
			{
				if( left.getClass() == p.left && low.getClass() == p.low && high.getClass() == p.high ) return p;
			}
			return NotMatched;
		}
	}

	/**
	 * The Match.
	 */
	public final Match match;
	/**
	 * The Test.
	 */
	public final SubtermContext test;
	/**
	 * The Low.
	 */
	public final SubtermContext low;
	/**
	 * The High.
	 */
	public final SubtermContext high;

	/**
	 * Instantiates a new Between.
	 *
	 * @param context the context
	 */
	public Between( PredicateBETWEENContext context )
	{
		super( context );
		test = (SubtermContext) context.parent.getChild( 0 );
		low = context.subterm( 0 );
		high = context.subterm( 1 );
		match = Match.match( test, low, high );
	}

	@Override
	public boolean isMatched()
	{
		return match != Match.NotMatched;
	}
}
