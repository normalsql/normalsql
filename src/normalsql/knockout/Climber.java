// Copyright 2010-2025 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

/*
 * Climber is an External Iterator (per GoF's Design Patterns). It recursively
 * iterates a parse tree, depth first, as needed, extracting Knockouts.
 *
 * Visitor iterates entire parse tree, uses double dispatch. Climber dispatches using
 * Java's (fancy) new switch (instanceof) patterns.
 *
 * Listener implements enter and exit methods for every parse context. As a very
 * simple bear, Listener is just too much.
 *
 */

package normalsql.knockout;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.xpath.XPath;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

/**
 * Monkeys climb trees. Torturing this metaphor, "trunk" (not "root") is the starting node.
 *
 */

public class
    Climber
{
    Parser parser;
    public Statement              trunk     = new Statement();
    public Stack<Statement>       stack     = new Stack<>();
    public ArrayList<Knockout<?>> knockouts = new ArrayList<>();
    public CommonTokenStream      tokens;

    public Climber( Parser parser, CommonTokenStream tokens )
    {
        this.parser = parser;
        this.tokens = tokens;
        stack.add( trunk );
    }

    public void climb( ParseTree parent )
    {
        Statement top = stack.peek();
        for( int nth = 0; nth < parent.getChildCount(); nth++ )
        {
            ParseTree temp = parent.getChild( nth );

            if( temp instanceof TerminalNode )
            {
                continue;
            }

            RuleContext child = (RuleContext) temp;

            String name = child.getClass().getSimpleName();
            Statement statement = switch( name )
            {
                case "SelectContext" -> new Select();
                case "InsertContext" -> climbInsert( child );
                case "UpdateContext" -> new Update();
                case "DeleteContext" -> new Delete();
                default -> null;
            };

            if( statement != null )
            {
                top.add( statement );
                stack.push( statement );
                climb( child );
                stack.pop();
                continue;
            }

            if( name.startsWith( "Item" ))
            {
                // TODO: Could or should these be inlined into a climbSelect?
                var item = new Item();
                top.items.add( item );

                item.context  = child;
                item.verbatim = tokens.getText( child );

                if( "ItemAllContext".equals( name ))
                {
                    item.qname = "*";
                }
                else if( "ItemColumnContext".equals( name ))
                {
                    // TODO extract local name from column's qname, instead of string processing
//                    item.localName = getLocalName( qname );

                    item.qname = child.getChild( 0 ).getText();
                    if( child.getChildCount() > 1 )
                    {
                        item.alias = child.getChild( 1 ).getText();
                    }
                }
                else if( "ItemTermContext".equals( name ))
                {
                    // TODO handle items without column name (eg expression) or alias. Use an index? Generate a column name?
                    if( child.getChildCount() > 1 )
                    {
                        item.alias = child.getChild( 1 ).getText();
                    }
                }

            }
            else
            {
                Knockout<?> k = switch( name )
                {
                    case "TermBETWEENContext" ->
                    {
                        yield new BETWEEN( child, parser );
                    }
                    case "TermCompareContext" -> new Comparison( child );
                    case "TermINContext"  ->
                    {
                        yield new IN( child, parser );
                    }
                    case "TermLIKEContext"  ->
                    {
                        LIKE like = new LIKE( child );
                        var terms = XPath.findAll( child, "/like/term", parser );
                        var ick = new ArrayList<>( terms );
                        like.column  = ick.get( 0 );
                        like.literal  = ick.get( 1 );
                        yield like;
                    }
                    case "SetterContext"  ->
                    {
                        Setter setter = new Setter( child );
                        setter.qname = XPath.findAll( child, "/setter/qname", parser ).iterator().next();
                        setter.literal = XPath.findAll( child, "/setter/literal", parser ).iterator().next();

                        yield setter;
                    }
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

    public Insert climbInsert( ParseTree parent )
    {
        ArrayList<Knockout<?>> knockouts = new ArrayList<>();

        var rowsX = XPath.findAll( parent, "/insert/select/selectCore/values/term", parser );
        for( var rowX : rowsX )
        {
            var literals = XPath.findAll( rowX, "/term/term/literal", parser );
            if( literals.isEmpty() ) continue;

            var row = new Row( parent );
            row.literals = new ArrayList<>( literals );
            knockouts.add( row );
        }

        if( knockouts.isEmpty() ) return null;

        Insert insert = new Insert();
        insert.table = XPath.findAll( parent, "/insert/qname", parser ).iterator().next();
        Collection<ParseTree> columns = XPath.findAll( parent, "/insert/name", parser );
        insert.columns   = new ArrayList<>( columns );
        insert.knockouts = knockouts;

        return insert;
    }
}
