// Copyright 2010-2022 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql;

import normalsql.meta.*;
import normalsql.parse.NormalSQLBaseVisitor;
import normalsql.parse.NormalSQLParser.*;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;

import java.util.ArrayList;
import java.util.Stack;

/**
 * <p>NormalSQLVisitor class.</p>
 *
 * @author jasonosgood
 * @version $Id: $Id
 */
public class
NormalSQLVisitor
extends
	NormalSQLBaseVisitor<Void>
{
	public Parser parser;
	public CommonTokenStream tokens;
	ArrayList<Predicate<?>> predicates;

	Stack<Statement> stack;
	Statement root;

	/** {@inheritDoc} */
	@Override
	public Void visitParse( ParseContext context )
	{
		predicates = new ArrayList<>();
		stack = new Stack<>();
		root = new Statement();
		stack.add( root );

		super.visitParse( context );
		return null;
	}

	/** {@inheritDoc} */
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

	// TODO populate Work with table's columns (scrapped from metadata)
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

	/** {@inheritDoc} */
	@Override
	public Void visitItemColumn( ItemColumnContext context )
	{
		Item item = new Item();
		item.context = context;
		item.source = tokens.getText( context );
		item.name = tokens.getText( context.term() );
		if( context.name() != null )
		{
			item.alias = context.name().getTrimmedText();
		}
		stack.peek().items.add( item );
		super.visitItemColumn( context );
		return null;
	}

	// TODO verify this is needed
//	@Override
//	public Void visitSource( SourceContext context )
//	{
//		if( context.tableRef() != null )
//		{
//			Source source = new Source();
//			source.tableRef = new TableRef( context.tableRef() );
//			if( context.alias() != null )
//			{
////				source.alias = context.alias().name().getTrimmedText();
//			}
//			stack.peek().sources.add( source );
//		}
//		return super.visitSource( context );
//	}

	/** {@inheritDoc} */
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

	/** {@inheritDoc} */
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
