package fado.voyager;

import fado.parse.GenericSQLBaseVisitor;
import fado.parse.GenericSQLParser.*;

import java.util.ArrayList;
import java.util.Stack;

class Work
{
	public Select root;
}

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
	public Ref ref;
}

class Ref
{
	public Ref( RefContext context )
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
	public T context;

	public Predicate( T context )
	{
		this.context = context;
	}

}
class Between extends Predicate< PredicateBETWEENContext >
{
	public Between( PredicateBETWEENContext context )
	{
		super( context );
	}

	public enum Params
	{
		LowHigh,     // isLeftBetween( low, high )     eg 'value BETWEEN 0 AND 10'
		High,        // isLeftBetweenLowAnd( high )    eg 'value BETWEEN low AND 10'
		Low,         // isLeftBetweenAndHigh( low )    eg 'value BETWEEN 0 AND high'
		Left,        // isBetweenLowAndHigh( left )    eg '5 BETWEEN low AND high'
		LeftHigh,    // isBetweenLowAnd( left, high )  eg '5 BETWEEN low AND 10'
		LeftLow,     // isBetweenAndHigh( left, low )  eg '5 BETWEEN 0 AND high'
		LeftLowHigh, // isBetween( left, low, high )   eg '5 BETWEEN 0 AND 10'
		NotMatched
	}

	public Params params = Params.NotMatched;

	public Params match()
	{
		SubtermContext left = (SubtermContext) context.parent.getChild( 0 );
		SubtermContext low = context.subterm( 0 );
		SubtermContext high = context.subterm( 1 );

		boolean leftIsValue = left instanceof SubtermValueContext;
		boolean leftIsRef   = left instanceof SubtermRefContext;
		boolean lowIsValue  = low  instanceof SubtermValueContext;
		boolean lowIsRef    = low  instanceof SubtermRefContext;
		boolean highIsValue = high instanceof SubtermValueContext;
		boolean highIsRef   = high instanceof SubtermRefContext;

		     if( leftIsRef   && lowIsValue && highIsValue ) params = Params.LowHigh;
		else if( leftIsRef   && lowIsRef   && highIsValue ) params = Params.High;
		else if( leftIsRef   && lowIsValue && highIsRef   ) params = Params.Low;
		else if( leftIsValue && lowIsRef   && highIsRef   ) params = Params.Left;
		else if( leftIsValue && lowIsRef   && highIsValue ) params = Params.LeftHigh;
		else if( leftIsValue && lowIsValue && highIsRef   ) params = Params.LeftLow;
		else if( leftIsValue && lowIsValue && highIsValue ) params = Params.LeftLowHigh;

		return params;
	}

//	public String value() { return left.getTrimmedText(); }
//	public String low() { return right.get( 0 ).getTrimmedText(); }
//	public String high() { return right.get( 1 ).getTrimmedText(); }
}

public class
	VoyagerVisitor
extends
	GenericSQLBaseVisitor< Work >
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
		RefContext rc = context.findFirst( RefContext.class, "term/subterm/ref" );
		if( rc != null )
		{
			item.ref = new Ref( rc );
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

//	@Override
//	public Work visitSubtermPredicate( SubtermPredicateContext ctx )
//	{
//		return super.visitSubtermPredicate( ctx );
//	}

	@Override
	public Work visitPredicateBETWEEN( PredicateBETWEENContext ctx )
	{
		Between between = new Between( ctx );
		if( between.match() != Between.Params.NotMatched )
		{
			stack.peek().predicates.add( between );
		}
		return super.visitPredicateBETWEEN( ctx );
	}
}
