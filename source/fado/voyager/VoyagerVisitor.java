package fado.voyager;

import fado.parse.GenericSQLBaseVisitor;
import fado.parse.GenericSQLParser.*;

import java.util.ArrayList;
import java.util.Stack;

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
		Comparison comparison = new Comparison( ctx );
		if( comparison.isMatched() )
		{
			stack.peek().predicates.add( comparison );
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
