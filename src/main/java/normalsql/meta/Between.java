// Copyright 2010-2022 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.meta;

import normalsql.parse.NormalSQLParser.PredicateBETWEENContext;
import normalsql.parse.NormalSQLParser.SubtermContext;

/**
 * POJO holding the subterms of a BETWEEN predicate's parse tree.
 *
 * @author jasonosgood
 * @version $Id: $Id
 */
public class
	Between
extends
	Predicate<PredicateBETWEENContext>
{
	/**
	 * Variations which can be matched. Disabled variants kept for reference.
	 * They might be supported in the future. If they'd be useful. Assuming a
	 * legible code generation strategy is divined. (Suggestions encouraged.)
	 */
	public enum Match
	{
		/** eg 'column BETWEEN 0 AND 10' */
		COL_VAL_VAL( COL, VAL, VAL ),

//		COL_COL_VAL( COL, COL, VAL ),  /** eg 'left BETWEEN low AND 10' */
//		COL_VAL_COL( COL, VAL, COL ),  /** eg 'left BETWEEN 0 AND high' */

		/** eg '5 BETWEEN low AND high' */
		VAL_COL_COL( VAL, COL, COL ),

//		VAL_COL_VAL( VAL, COL, VAL ),  /** eg '5 BETWEEN low AND 10' */
//		VAL_VAL_COL( VAL, VAL, COL ),  /** eg '5 BETWEEN 0 AND high' */
//		VAL_VAL_VAL( VAL, VAL, VAL ),  /** eg '5 BETWEEN 0 AND 10' */

		/** No match found. */
		NotMatched( null, null, null );

		/** comment */
		public final Class left;
		/** comment */
		public final Class low;
		/** comment */
		public final Class high;


		/** comment */
		Match( Class left, Class low, Class high )
		{
			this.left = left;
			this.low = low;
			this.high = high;
		}

		/** comment */
		public static Match match( SubtermContext left, SubtermContext low, SubtermContext high )
		{
			for( Match p : values() )
			{
				if( left.getClass() == p.left && low.getClass() == p.low && high.getClass() == p.high ) return p;
			}
			return NotMatched;
		}
	}

	/** comment */
	public Match match;
	/** comment */
	public SubtermContext test;
	/** comment */
	public SubtermContext low;
	/** comment */
	public SubtermContext high;

	/**
	 * <p>Constructor for Between.</p>
	 *
	 * @param context a {@link normalsql.parse.NormalSQLParser.PredicateBETWEENContext} object
	 */
	public Between( PredicateBETWEENContext context )
	{
		super( context );
		test = (SubtermContext) context.parent.getChild( 0 );
		low = context.subterm( 0 );
		high = context.subterm( 1 );
		match = Match.match( test, low, high );
	}

	/** {@inheritDoc} */
	@Override
	public boolean isMatched()
	{
		return match != Match.NotMatched;
	}
}
