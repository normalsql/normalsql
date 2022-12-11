package fado.meta;

import fado.parse.GenericSQLParser.SubtermContext;
import fado.parse.GenericSQLParser.PredicateBETWEENContext;

public class Between extends Predicate<PredicateBETWEENContext>
{
	public enum Match
	{
		COL_VAL_VAL( COL, VAL, VAL ),  // eg 'column BETWEEN 0 AND 10'
//		COL_COL_VAL( COL, COL, VAL ),  // eg 'column BETWEEN low AND 10'
//		COL_VAL_COL( COL, VAL, COL ),  // eg 'column BETWEEN 0 AND high'
		VAL_COL_COL( VAL, COL, COL ),  // eg '5 BETWEEN low AND high'
//		VAL_COL_VAL( VAL, COL, VAL ),  // eg '5 BETWEEN low AND 10'
//		VAL_VAL_COL( VAL, VAL, COL ),  // eg '5 BETWEEN 0 AND high'
//		VAL_VAL_VAL( VAL, VAL, VAL ),  // eg '5 BETWEEN 0 AND 10'
		NotMatched( null, null, null );

		public final Class left;
		public final Class low;
		public final Class high;

		Match( Class left, Class low, Class high )
		{
			this.left = left;
			this.low = low;
			this.high = high;
		}

		public static Match match( SubtermContext left, SubtermContext low, SubtermContext high )
		{
			for( Match p : values() )
			{
				if( left.getClass() == p.left && low.getClass() == p.low && high.getClass() == p.high ) return p;
			}
			return NotMatched;
		}
	}

	public Match match;
	public SubtermContext test;
	public SubtermContext low;
	public SubtermContext high;

	public Between( PredicateBETWEENContext context )
	{
		super( context );
		test = (SubtermContext) context.parent.getChild( 0 );
		low = context.subterm( 0 );
		high = context.subterm( 1 );
		match = Match.match( test, low, high );

		if( !isMatched() ) return;
	}

	@Override
	public boolean isMatched()
	{
		return match != Match.NotMatched;
	}
}
