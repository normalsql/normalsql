// Copyright 2010-2025 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

/**
 * Crawler is an External Iterator (per GoF's Design Patterns). It recursively
 * iterates a parse tree, depth first, as needed, extracting Knockouts.
 *
 * Visitor iterates entire parse tree, uses double dispatch. Crawler dispatches using
 * Java's (fancy) new switch (instanceof) patterns.
 *
 * Listener implements enter and exit methods for every parse context. As a very
 * simple bear, Listener is just too much.
 *
 */

package normalsql.postgresql;

import normalsql.grammar.PostgreSQLParser.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.Stack;

public class
    Crawler
{
    public Crawler()
    {
    }

    // TODO: Support multiple root-level statements
    Statement root = new Statement();

    Stack<Statement> statementStack = new Stack<>();
    public final ArrayList<Knockout<?,?>> knockouts = new ArrayList<>();

    public void visit( ParseTree parent )
    {
        for( int ffs = 0; ffs < parent.getChildCount() ; ffs++ )
        {
            var child = parent.getChild( ffs );
            var tada =
            switch( child )
            {
                case ParseContext select -> true;
                case SelectContext select -> true;
                case InsertContext select -> true;
                case UpdateContext select -> true;
                case DeleteContext select -> true;
                default -> false;
            };

            if( tada )
            {
                var statement = new Statement();
                statementStack.push( statement );
                visit( child );
                return;
            }

            Knockout<?,?> k = switch( child )
            {
                case TermBETWEENContext between -> new BETWEEN( between );
                case TermCompareContext compare -> new Comparison( compare );
                case TermINContext in           -> new IN( in );
                case TermLIKEContext like       -> new LIKE( like );
                case SetterContext setter       -> new Setter( setter );
                default -> null;
            };

            // TODO to aid debugging, add all potential knockouts, filter matches later
            if( k != null && k.isMatched() )
            {
                statementStack.peek().knockouts.add( k );
                knockouts.add( k );
            }
        }
    }
}
