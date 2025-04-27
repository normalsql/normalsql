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
import normalsql.grammar.PostgreSQLParser.ParseContext;
import normalsql.grammar.PostgreSQLParser.SelectContext;
import normalsql.grammar.PostgreSQLParser.DeleteContext;
import normalsql.grammar.PostgreSQLParser.InsertContext;
import normalsql.grammar.PostgreSQLParser.UpdateContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.Stack;

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

    public Monkey()
    {
        stack.add( trunk );
    }


    public void banana( ParseTree parent )
    {
        Statement top = stack.peek();
        for( int nth = 0; nth < parent.getChildCount(); nth++ )
        {
            ParseTree child = parent.getChild( nth );

            if( child instanceof TerminalNode )
            {
                continue;
            }

            boolean isStatement = switch( child )
            {
                case ParseContext ignore -> true;
                case SelectContext ignore -> true;
                case InsertContext ignore -> true;
                case UpdateContext ignore -> true;
                case DeleteContext ignore -> true;
                default -> false;
            };

            if( isStatement )
            {
                Statement statement = new Statement();
                top.add( statement );
                stack.push( statement );
                banana( child );
                stack.pop();
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

                // TODO to aid debugging, add all potential knockouts, filter matches later
                if( k != null )
//                if( k != null && k.isMatched() )
                {
                    top.knockouts.add( k );
                    knockouts.add( k );
                }

                banana( child );
            }
        }
    }
}
