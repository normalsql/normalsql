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

package normalsql.parse;

import normalsql.grammar.PostgreSQLParser.*;

import java.util.Stack;

public class
    Crawler
{
    public Crawler()
    {

    }

    // TODO: Support multiple root-level statements
    Statement root;

    Stack<Statement> statementStack = new Stack<>();

    public void visit(ParseContext parent )
    {
        root = new Statement();
        statementStack.push( root );

        for( var child : parent.children )
        {
            switch( child )
            {
                case SelectContext select:
                    visit( select );
                default:
                    break;
            }
        }

    }

    public void visit( SelectContext select )
    {
        root = new Statement();
        statementStack.add( root );
    }
}
