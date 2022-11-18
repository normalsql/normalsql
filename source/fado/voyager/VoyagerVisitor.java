package fado.voyager;

import fado.meta.Param;
import fado.meta.RSColumn;
import fado.parse.GenericSQLBaseVisitor;
import fado.parse.GenericSQLParser.*;

import java.util.ArrayList;
import java.util.Stack;

class Work
{
	public Select root;
	public String originalSQL;
	// Copied from the PreparedStatement's metadata
	public ArrayList<Param> paramList = new ArrayList<>();
	// Copied from the PreparedStatement's metadata
	public ArrayList<RSColumn> columns = new ArrayList<>();
	public String preparedSQL;
}

// TODO extends ArrayList
class Select
{
	public ArrayList<Item> items = new ArrayList<>();
	public ArrayList<Source> tables = new ArrayList<>();
	public ArrayList<Predicate> predicates = new ArrayList<>();
}

class Item
{
	public boolean wildcard;
	public TableRef tableRef;
	public ColumnRef columnRef;
	public String alias;
}

class ColumnRef
{
	public ColumnRef( ColumnRefContext context )
	{
		database = context.database != null ? context.database.getTrimmedText() : null ;
		schema = context.schema != null ? context.schema.getTrimmedText() : null ;
		table = context.table != null ? context.table.getTrimmedText() : null ;
		column = context.column != null ? context.column.getTrimmedText() : null ;
	}

	public String database;
	public String schema;
	public String table;
	public String column;
}

class TableRef
{
	public TableRef( TableRefContext context )
	{
		database = context.database != null ? context.database.getTrimmedText() : null ;
		schema = context.schema != null ? context.schema.getTrimmedText() : null ;
		table = context.table != null ? context.table.getTrimmedText() : null ;
	}

	public String database;
	public String schema;
	public String table;
}

class Source
{
	public TableRef tableRef;
	public String alias;
}

abstract class Predicate< T extends PredicateContext >
{
	public final static Class COL = SubtermColumnRefContext.class;
	public final static Class VAL = SubtermValueContext.class;


	public T context;
	public SubtermContext parent;

	public Predicate( T context )
	{
		this.context = context;
		parent = (SubtermContext) context.parent;
	}

	abstract public boolean isMatched();
}

class Compare extends Predicate< PredicateCompareContext >
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
			this.left = left; this.right = right;
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

	public final SubtermContext left;
	public final SubtermContext right;
	public String leftText;
	public String rightText;
	public Param param;
	public String clazz;
	public final Match match;

	public Compare( PredicateCompareContext context )
	{
		super( context );
		left = (SubtermContext) context.parent.getChild( 0 );
		right = context.subterm();
		match = Match.match( left, right );

		if( !isMatched() ) return;

		switch( match )
		{
			case VAL_COL:
				leftText= left.getTrimmedText();
				rightText = ((SubtermColumnRefContext) right).columnRef().column.getTrimmedText();
				break;
			case COL_VAL:
				leftText = ((SubtermColumnRefContext) left).columnRef().column.getTrimmedText();
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

class Between extends Predicate< PredicateBETWEENContext >
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

		public final boolean isLeftVAL() { return left == VAL; }
		public final boolean isLowVAL() { return low == VAL; }
		public final boolean isHighVAL() { return high == VAL; }

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
	public SubtermContext left;
	public SubtermContext low;
	public SubtermContext high;
	public String leftText;
	public String lowText;
	public String highText;
	public Param leftParam;
	public Param lowParam;
	public Param highParam;
	public String clazz;

	public Between( PredicateBETWEENContext context )
	{
		super( context );
		left = (SubtermContext) context.parent.getChild( 0 );
		low = context.subterm( 0 );
		high = context.subterm( 1 );
		match = Match.match( left, low, high );

		if( !isMatched() ) return;

		if( match.isLeftVAL() )
		{
			leftText = left.getTrimmedText();
		}
		else
		{
			leftText = ((SubtermColumnRefContext) left).columnRef().column.getTrimmedText();
		}

		if( match.isLowVAL() )
		{
			lowText = low.getTrimmedText();
		}
		else
		{
			lowText = ((SubtermColumnRefContext) low).columnRef().column.getTrimmedText();
		}

		if( match.isHighVAL() )
		{
			highText = high.getTrimmedText();
		}
		else
		{
			highText = ((SubtermColumnRefContext) high).columnRef().column.getTrimmedText();
		}
	}

	@Override
	public boolean isMatched()
	{
		return match != Match.NotMatched;
	}
}

public class
	VoyagerVisitor
extends
	GenericSQLBaseVisitor< Work > // TODO change to <Select>
{
	Stack<Select> stack;
	Work work;

//	public <T> T last( List< T > list )
//	{
//		if( list == null ) return null;
//		if( list.isEmpty() ) return null;
//		return list.get( list.size() - 1 );
//	}

	public Work visitParse( ParseContext context )
	{
		stack = new Stack();
		work = new Work();
		super.visitParse( context );
		return work;
	}

	public Work visitSelect( SelectContext context )
	{
		stack.push( new Select() );
//		System.out.println( ctx.getText() );
		super.visitSelect( context );
		Select select = stack.pop();
		work.root = select;
		return work;
	}

	public Work visitItemWildcard( ItemWildcardContext context )
	{
		Item item = new Item();
		item.wildcard = true;
		if( context.tableRef() != null )
		{
			item.tableRef = new TableRef( context.tableRef() );
		}
		stack.peek().items.add( item );
		return super.visitItemWildcard( context );
	}

	public Work visitItemColumn( ItemColumnContext context )
	{
		Item item = new Item();
		item.wildcard = false;
		ColumnRefContext rc = context.findFirst( ColumnRefContext.class, "term/subterm/columnRef" );
		if( rc != null )
		{
			item.columnRef = new ColumnRef( rc );
			item.alias = context.findFirstString( "alias/name" );
			stack.peek().items.add( item );
		}
		return super.visitItemColumn( context );
	}

	@Override
	public Work visitSource( SourceContext context )
	{
		if( context.tableRef() != null )
		{
			Source source = new Source();
			source.tableRef = new TableRef( context.tableRef() );
			if( context.alias() != null )
			{
				source.alias = context.alias().name().getTrimmedText();
			}
			stack.peek().tables.add( source );
		}
		return super.visitSource( context );
	}

	@Override
	public Work visitPredicateCompare( PredicateCompareContext ctx )
	{
		Compare compare = new Compare( ctx );
		if( compare.isMatched() )
		{
			stack.peek().predicates.add( compare );
		}
		return super.visitPredicateCompare( ctx );
	}

	@Override
	public Work visitPredicateBETWEEN( PredicateBETWEENContext ctx )
	{
		Between between = new Between( ctx );
		if( between.isMatched() )
		{
			stack.peek().predicates.add( between );
		}
		return super.visitPredicateBETWEEN( ctx );
	}
}
