package fado.voyager;

import fado.parse.GenericSQLParser.SubtermContext;
import fado.parse.GenericSQLParser.PredicateCompareContext;

import java.util.HashMap;

public class Comparison extends Predicate<PredicateCompareContext>
{
	public enum Match
	{
		COL_VAL( COL, VAL ),
		VAL_COL( VAL, COL ),
		NotMatched( null, null );

		public final Class left;
		public final Class right;

		Match( Class left, Class right )
		{
			this.left = left;
			this.right = right;
		}

		public static Match match( SubtermContext left, SubtermContext right )
		{
			for( Match p : values() )
			{
				if( left.getClass() == p.left && right.getClass() == p.right ) return p;
			}
			return NotMatched;
		}
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

	public final SubtermContext left;
	public final SubtermContext right;
	public String leftText;
	public String rightText;
	public String opText;
	public Param param;
	public Match match;

	public Comparison( PredicateCompareContext context )
	{
		super( context );
		left = (SubtermContext) context.parent.getChild( 0 );
		right = context.subterm();
		match = Match.match( left, right );

		if( !isMatched() ) return;

		opText = operatorMap.get( context.op.getText() );
		switch( match )
		{
			case VAL_COL:
				leftText = left.getTrimmedText();
//				rightText = ( (GenericSQLParser.SubtermColumnRefContext) right ).columnRef().column.getTrimmedText();
				rightText = getNameContext( right ).getTrimmedText();
				break;
			case COL_VAL:
//				leftText = ( (GenericSQLParser.SubtermColumnRefContext) left ).columnRef().column.getTrimmedText();
				leftText = getNameContext( left ).getTrimmedText();
				rightText = right.getTrimmedText();
				break;
			default:
				break;
		}
	}

	@Override
	public boolean isMatched()
	{
		return match != Match.NotMatched;
	}
}
