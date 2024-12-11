// Copyright 2010-2024 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.parse;

import static normalsql.Glorp.getLocalName;
import normalsql.parse.NormalSQLParser.*;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.RuleNode;

import java.util.ArrayList;
import java.util.Stack;

public class
	KnockoutVisitor
extends
	NormalSQLBaseVisitor<Void>
{
	public Parser parser;
	public CommonTokenStream tokens;

	// All the (matched) knockouts in one flattened list.
//	public ArrayList<Knockout<?,?>> knockouts;
	public final ArrayList<Knockout<?,?>> knockouts = new ArrayList<>();

	final Stack<Statement> statementStack = new Stack<>();

	// TODO: Support multiple root-level statements
	public Statement root;

	@Override
	public Void visitScript( ScriptContext context )
	{
		root = new Statement();
		statementStack.add( root );
		// TODO create contexts stack, so rules can match on parent too

		return super.visitScript( context );
	}

	@Override
	public Void visitDelete( DeleteContext context )
	{
		// TODO StatementStack w/ auto parenting
		var parent = statementStack.peek();
		var child = new Delete();
		statementStack.push( child );
		parent.add( child );
		return super.visitDelete( context );
	}

	@Override
	public Void visitUpdate( UpdateContext context )
	{
		// TODO StatementStack w/ auto parenting
		var parent = statementStack.peek();
		var child = new Update();
		statementStack.push( child );
		parent.add( child );
		return super.visitUpdate( context );
	}

	@Override
	public Void visitSelect( SelectContext context )
	{
		var parent = statementStack.peek();
		var child = new Select();
		statementStack.push( child );
		parent.add( child );
		return super.visitSelect( context );
	}

	@Override
	public Void visitItemTerm( ItemTermContext context )
	{
		// TODO: Could or should these be inlined into visitSelect?
		var item = new Item();
		item.context  = context;
		item.verbatim = tokens.getText( context );

		String qname = tokens.getText( context.term() );
		item.localName = getLocalName( qname );

		if( context.alias() != null )
		{
			item.alias = tokens.getText( context.alias() );
		}
		statementStack.peek().items.add( item );
		return super.visitItemTerm( context );
	}

	// TODO restore visitInsert after resolving ambiguity in 'insert' rule

	@Override
	public Void visitInsert( InsertContext context )
	{
		try
		{
			// TODO this method chaining SUCKS, bring back the globber
			// Drill down to first row
//			var termList = context.source().values().terms().term();
			var termList = context.query().combine().values().terms().term();
			var subterm = termList.get( 0 ).subterm();


			if( subterm instanceof SubtermRowContext )
			{
				var found = new ArrayList<SubtermLiteralContext>();
				var maybes = ((SubtermRowContext) subterm).terms().term();
				for( TermContext term : maybes )
				{
					SubtermContext sc = term.subterm();
					if( sc instanceof SubtermLiteralContext )
					{
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
					// TODO unnecessary null check?
					if( context.tableRef() != null )
					{
						child.table = context.tableRef().qname();
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
	public Void visitChildren( RuleNode node )
	{
		Knockout<?,?> k = switch( node )
		{
			case SubtermBETWEENContext between -> new BETWEEN( between );
			case SubtermCompareContext compare -> new Comparison( compare );
			case SubtermINContext in -> new IN( in );
			case SubtermLIKEContext like -> new LIKE( like );
			case SetterContext setter -> new Setter( setter );
			default -> null;
		};

		if( k != null && k.isMatched() )
		{
			statementStack.peek().knockouts.add( k );
			knockouts.add( k );
			return null;
		}

		return super.visitChildren( node );
	}
}
