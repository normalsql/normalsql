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

public class
	NormalSQLVisitor
extends
	NormalSQLBaseVisitor<Void>
{
	public Parser parser;
	public CommonTokenStream tokens;
	ArrayList<Predicate<?,?>> predicates;

	ArrayList<SubtermLiteralContext> literals = new ArrayList<>();


	Stack<Statement> statementStack;
	// TODO: Support multiple root-level statements
	Statement root;

	@Override
	public Void visitScript( ScriptContext context )
	{
		predicates = new ArrayList<>();

		statementStack = new Stack<>();
		root = new Statement();
		statementStack.add( root );
		// TODO create contexts stack, so rules can match on parent too

		super.visitScript( context );
		return null;
	}

	@Override
	public Void visitSelect( SelectContext context )
	{
		Statement parent = statementStack.peek();
		Statement child = new Select();
		statementStack.push( child );
		parent.add( child );
		super.visitSelect( context );
		return null;
	}

	@Override
	public Void visitInsert( InsertContext context )
	{
		Statement parent = statementStack.peek();
		Statement child = new Insert();
		statementStack.push( child );
		parent.add( child );
		super.visitInsert( context );
		return null;
	}

	@Override
	public Void visitItemColumn( ItemColumnContext context )
	{
		Item item = new Item();
		item.context = context;
		item.source = tokens.getText( context );
		item.name = tokens.getText( context.aliasedTerm().term() );
		if( context.aliasedTerm().alias() != null )
		{
			item.alias = tokens.getText( context.aliasedTerm().alias() );
		}
		statementStack.peek().items.add( item );
		super.visitItemColumn( context );
		return null;
	}

//	// TODO verify this is needed
//	// TODO will likely need this, since preparedstatement param metadata is incomplete
//	@Override
//	public Void visitSource( SourceContext context )
//	{
//		Source source = new Source();
//		statementStack.peek().sources.add( source );
////		if( context.tableRef() != null )
////		{
////			Source source = new Source();
////			source.tableRef = new TableRef( context.tableRef() );
////			if( context.alias() != null )
////			{
//////				source.alias = context.alias().name().getTrimmedText();
////			}
////			stack.peek().sources.add( source );
////		}
//		return super.visitSource( context );
//	}

//	@Override
//	public Void visitValues( ValuesContext context )
//	{
//		super.visitValues( context );
//		return null;
//	}

	@Override
	public Void visitSubtermRow( SubtermRowContext context )
	{
		if( context.terms() == null || context.terms().term() == null )
		{
			return null;
		}

		var temp = new ArrayList<SubtermLiteralContext>();
		for( TermContext term : context.terms().term() )
		{
			SubtermContext sc = term.subterm();
			if( sc instanceof SubtermLiteralContext )
			{
				temp.add( (SubtermLiteralContext) sc );
			}
		}

		if( temp.size() == context.terms().term().size() )
		{
			// TODO also add literals to parent Statement
			literals.addAll( temp );
			return null;
		}

		return super.visitSubtermRow( context );
	}

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

			case "PredicateINContext":
				p = new IN( (PredicateINContext) pc );
				break;

			case "PredicateMatchContext":
				p = new Match( (PredicateMatchContext) pc );
				break;

			// TODO: ANY
			case "PredicateAnyContext":
				break;

			default:
				break;
		}

		if( p != null && p.isMatched() )
		{
			statementStack.peek().placeholders.add( p );
			predicates.add( p );
			return null;
		}
		return super.visitSubtermPredicate( ctx );
	}
}
