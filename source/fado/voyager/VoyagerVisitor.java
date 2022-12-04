package fado.voyager;

import fado.parse.GenericSQLBaseVisitor;
import fado.parse.GenericSQLParser.*;

import java.util.ArrayList;
import java.util.Stack;

public class
	VoyagerVisitor
extends
	GenericSQLBaseVisitor< Void >
{
	ArrayList<Predicate> predicates;

	Stack<Statement> stack;
	Statement root;

	@Override
	public Void visitParse( ParseContext context )
	{
		predicates = new ArrayList<>();
		stack = new Stack();
		root = new Statement();
		stack.add( root );

		super.visitParse( context );
		return null;
	}

	@Override
	public Void visitSelect( SelectContext context )
	{
		stack.push( new Select() );
		super.visitSelect( context );
		Statement child = stack.pop();
		Statement parent = stack.peek();
		parent.add( child );
		return null;
	}

//	@Override
//	public Void visitItemWildcard( ItemWildcardContext context )
//	{
//		Item item = new Item();
//		item.context = context;
//		item.wildcard = true;
//		if( context.tableRef() != null )
//		{
//			item.tableRef = new TableRef( context.tableRef() );
//		}
//		stack.peek().items.add( item );
////		return super.visitItemWildcard( context );
//		return null;
//	}

	@Override
	public Void visitItemColumn( ItemColumnContext context )
	{
		Item item = new Item();
		item.context = context;
		item.wildcard = false;
		ColumnRefContext rc = context.findFirst( ColumnRefContext.class, "term/subterm/columnRef" );
		if( rc != null )
		{
			item.columnRef = new ColumnRef( rc );
			item.name = item.columnRef.column;
			item.alias = context.findFirstString( "alias/name" );
			stack.peek().items.add( item );
		}
		super.visitItemColumn( context );
		return null;
	}

	// TODO verify this is needed
	@Override
	public Void visitSource( SourceContext context )
	{
		if( context.tableRef() != null )
		{
			Source source = new Source();
			source.tableRef = new TableRef( context.tableRef() );
			if( context.alias() != null )
			{
				source.alias = context.alias().name().getTrimmedText();
			}
			stack.peek().sources.add( source );
		}
		return super.visitSource( context );
	}

	@Override
	public Void visitPredicateCompare( PredicateCompareContext ctx )
	{
		Comparison comparison = new Comparison( ctx );
		if( comparison.isMatched() )
		{
			stack.peek().predicates.add( comparison );
			predicates.add( comparison );
		}
		else
		{
			super.visitPredicateCompare( ctx );
		}
		return null;
	}

	@Override
	public Void visitPredicateBETWEEN( PredicateBETWEENContext ctx )
	{
		Between between = new Between( ctx );
		if( between.isMatched() )
		{
			stack.peek().predicates.add( between );
			predicates.add( between );
		}
		else
		{
			super.visitPredicateBETWEEN( ctx );
		}
		return null;
	}
}
