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
	ArrayList<Predicate<?,?>> predicates;

	Stack<Statement> stack;
	Statement root;

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

	@Override
	public Void visitItemColumn( ItemColumnContext context )
	{
		Item item = new Item();
		item.context = context;
		item.source = tokens.getText( context );
		item.name = tokens.getText( context.term() );
		if( context.name() != null )
		{
			item.alias = tokens.getText( context.name() );
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

//	@Override
//	public Void visitPredicateCompare( PredicateCompareContext ctx )
//	{
//		Comparison comparison = new Comparison( ctx );
//		if( comparison.isMatched() )
//		{
//			stack.peek().predicates.add( comparison );
//			predicates.add( comparison );
//		}
//		else
//		{
//			super.visitPredicateCompare( ctx );
//		}
//		return null;
//	}

	@Override
	public Void visitSubtermPredicate( SubtermPredicateContext ctx )
	{
		PredicateContext pc = ctx.predicate();
		Predicate p = null;
		switch( pc.getClass().getSimpleName() )
		{
			case "PredicateBETWEENContext":
				p = new Between( (PredicateBETWEENContext) pc );
				break;

			case "PredicateCompareContext":
				p = new Comparison( (PredicateCompareContext) pc );
				break;

			default:
				break;
		}

		if( p != null && p.isMatched() )
		{
			stack.peek().predicates.add( p );
			predicates.add( p );
			return null;
		}
		return super.visitSubtermPredicate( ctx );
	}

//	@Override
//	public Void visitPredicateBETWEEN( PredicateBETWEENContext ctx )
//	{
//		Between between = new Between( ctx );
//		if( between.isMatched() )
//		{
//			stack.peek().predicates.add( between );
//			predicates.add( between );
//		}
//		else
//		{
//			super.visitPredicateBETWEEN( ctx );
//		}
//		return null;
//	}
}
