// Copyright 2010-2025 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

/**
 * Langur is an External Iterator (per GoF's Design Patterns). It recursively
 * iterates a parse tree, depth first, as needed, extracting Knockouts.
 *
 * Visitor iterates entire parse tree, uses double dispatch. Langur dispatches using
 * Java's (fancy) new switch (instanceof) patterns.
 *
 * Listener implements enter and exit methods for every parse context. As a very
 * simple bear, Listener is just too much.
 *
 */

package normalsql.postgresql;

import normalsql.grammar.PostgreSQLParser.*;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.Stack;

import static normalsql.Glorp.getLocalName;

/**
 * Monkey's climb trees. Torturing this metaphor, "trunk" (not "root") is the starting node.
 *
 */

public class
    Monkey
{
    public Statement        trunk = new Statement();
    public Stack<Statement> stack = new Stack<>();
    public ArrayList<Knockout<?,?>> knockouts = new ArrayList<>();
    public CommonTokenStream tokens;

    public Monkey( CommonTokenStream tokens )
    {
        this.tokens = tokens;
        stack.add( trunk );
    }

    public void climb( ParseTree parent )
    {
        Statement top = stack.peek();
        for( int nth = 0; nth < parent.getChildCount(); nth++ )
        {
            ParseTree child = parent.getChild( nth );

            if( child instanceof TerminalNode )
            {
                continue;
            }

            Statement isStatement = switch( child )
            {
                case SelectContext ignore -> new Select();
                case InsertContext insert -> climb( insert );
                case UpdateContext ignore -> new Update();
                case DeleteContext ignore -> new Delete();
                default -> null;
            };

            if( isStatement != null)
            {
                top.add( isStatement );
                stack.push( isStatement );
                climb( child );
                stack.pop();
                continue;
            }

            if( parent instanceof ItemContext context )
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
                top.items.add( item );
            }
            else
            {
                Knockout<?, ?> k = switch( child )
                {
                    case TermBETWEENContext between -> new BETWEEN( between );
                    case TermCompareContext compare -> new Comparison( compare );
                    case TermINContext in           -> new IN( in );
                    case TermLIKEContext like       -> new LIKE( like );
                    case SetterContext setter       -> new Setter( setter );
                    default -> null;
                };

//                // TODO to aid debugging, add all potential knockouts, filter matches later
//                if( k != null )
                if( k != null && k.isMatched() )
                {
                    top.knockouts.add( k );
                    knockouts.add( k );
                }

            }
            climb( child );
        }
    }

    public Insert climb( InsertContext context )
    {
        Insert insert = null;

        // Drill down to first row
        var termList = context.select().selectCore(0).values().terms();
        var subterm = termList.getFirst().term();

        if( subterm instanceof TermRowContext termRow )
        {
            var found = new ArrayList<TermLiteralContext>();
            var maybes = termRow.row().term();
            for( TermContext term : maybes )
            {
                if( term instanceof TermLiteralContext sc )
                {
                    found.add( sc );
                }
                else
                {
                    break;
                }
            }

            if( found.size() == maybes.size() )
            {
                insert = new Insert();
                insert.table = context.qname();

                if( context.columns() != null )
                {
                    insert.columns = context.columns().qname();
                }

                Row row = new Row( context, insert );
                row.literals = found;

                insert.knockouts.add( row );
                knockouts.add( row );
            }
        }

        return insert;

    }
}
