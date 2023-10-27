// Copyright 2010-2023 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.parse;

import normalsql.parse.NormalSQLParser.*;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;

import java.util.ArrayList;
import java.util.Stack;

public class
	KnockoutVisitor
extends
	NormalSQLBaseVisitor<Void>
{
	public Parser parser;
	public CommonTokenStream tokens;
	public ArrayList<Knockout<?,?>> knockouts;

	Stack<Statement> statementStack;
	// TODO: Support multiple root-level statements
	public Statement root;

	@Override
	public Void visitScript( ScriptContext context )
	{
		knockouts = new ArrayList<>();

		statementStack = new Stack<>();
		root = new Statement();
		statementStack.add( root );
		// TODO create contexts stack, so rules can match on parent too

		super.visitScript( context );
		return null;
	}

	@Override
	public Void visitDelete( DeleteContext context )
	{
		// TODO StatementStack w/ auto parenting
		Statement parent = statementStack.peek();
		Statement child = new Delete();
		statementStack.push( child );
		parent.add( child );
		super.visitDelete( context );
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
	public Void visitItemTerm( ItemTermContext context )
	{
		// TODO: Could or should these be inlined into visitSelect?
		Item item = new Item();
		item.context = context;
		item.source = tokens.getText( context );
		item.name = tokens.getText( context.term() );
		if( context.alias() != null )
		{
			item.alias = tokens.getText( context.alias() );
		}
		statementStack.peek().items.add( item );
		super.visitItemTerm( context );
		return null;
	}

	@Override
	public Void visitInsert( InsertContext context )
	{
		try
		{
			// TODO this method chaining SUCKS, bring back the globber
			// Drill down to first row
			var termList = context.source().values().terms().term();
			var subterm = termList.get( 0 ).subterm();


			if( subterm instanceof SubtermRowContext )
			{
//				var found = new ArrayList<LiteralContext>();
				var found = new ArrayList<SubtermLiteralContext>();
//				List<TermContext> maybes = ((SubtermRowContext) subterm).terms().term();
				var maybes = ((SubtermRowContext) subterm).terms().term();
				for( TermContext term : maybes )
				{
					SubtermContext sc = term.subterm();
					if( sc instanceof SubtermLiteralContext )
					{
//						found.add( ((SubtermLiteralContext) sc).literal() );
						found.add( (SubtermLiteralContext) sc );
					}
					else
					{
						break;
					}
				}

				if( found.size() == maybes.size() )
				{
					Insert child = new Insert();
					if( context.qname() != null )
					{
						child.table = context.qname();
					}

					if( context.names() != null )
					{
						child.columns = context.names().name();
					}

					Row row = new Row( context, child );
					row.literals = found;

					child.knockouts.add( row );
					knockouts.add( row );

					Statement parent = statementStack.peek();
					parent.add( child );
					statementStack.push( child );

					return null;
				}
			}
		}
		// ignore
		catch( NullPointerException npe ) {}

		return super.visitInsert( context );
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
//	public Void visitSubtermRow( SubtermRowContext context )
//	{
//		if( context.terms() == null || context.terms().term() == null )
//		{
//			return null;
//		}
//
//		var temp = new ArrayList<SubtermLiteralContext>();
//		for( TermContext term : context.terms().term() )
//		{
//			SubtermContext sc = term.subterm();
//			if( sc instanceof SubtermLiteralContext )
//			{
//				temp.add( (SubtermLiteralContext) sc );
//			}
//		}
//
//		if( temp.size() == context.terms().term().size() )
//		{
//			// TODO also add literals to parent Statement
//			literals.addAll( temp );
//			return null;
//		}
//
//		return super.visitSubtermRow( context );
//	}

	@Override
	public Void visitSubtermPredicate( SubtermPredicateContext ctx )
	{
		PredicateContext pc = ctx.predicate();
//			// TODO: ANY
//			case PredicateAnyContext any -> {}
		Knockout<?,?> k = switch( pc )
		{
			case PredicateBETWEENContext between -> new BETWEEN( between );
			case PredicateCompareContext compare -> new Comparison( compare );
			case PredicateINContext in -> new IN( in );
			case PredicateLIKEContext like -> new LIKE( like );
			default -> null;
		};

		if( k != null && k.isMatched() )
		{
			statementStack.peek().knockouts.add( k );
			knockouts.add( k );
			return null;
		}

		return super.visitSubtermPredicate( ctx );
	}
}