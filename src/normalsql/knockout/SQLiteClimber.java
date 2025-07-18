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

import normalsql.grammar.SQLiteLexer;
import normalsql.grammar.SQLiteParser;
import normalsql.grammar.SQLiteParser.*;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.xpath.XPath;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Monkeys climb trees. Torturing this metaphor, "trunk" (not "root") is the starting node.
 *
 */

public class
    SQLiteClimber
{

    public static void main( String[] args )
        throws Exception
    {
        var path = Paths.get( "/Users/jasonosgood/Projects/normalsql/doc/classicmodels/InsertOops.sql" );
        var monkey = new SQLiteClimber();
        var ko = monkey.go( path );
        System.out.println( ko.size() );

    }

    public Parser parser;
    public CommonTokenStream tokens;
    public Statement         trunk     = new Statement();
    public Stack<Statement>  stack     = new Stack<>();
    public List<Knockout<?>> knockouts = new ArrayList<>();

    public SQLiteClimber()
    {
        stack.push( trunk );
    }

    Statement top;

    public List<Knockout<?>> go( Path path )
        throws IOException
    {
        var chars = CharStreams.fromPath( path );
		var lexer = new SQLiteLexer( chars );
        tokens = new CommonTokenStream( lexer );
        var parser = new SQLiteParser( tokens );
        this.parser = parser;
		var statements = parser.statements();

        climb( statements );

        return knockouts;
    }

    public void climb( GlobbingRuleContext parent )
    {
        top = stack.peek();
        for( var child : parent )
        {
            Statement statement = switch( child )
            {
                case SelectContext ignore -> new Select();
                case InsertContext ctx -> newInsert( ctx );
                case UpdateContext ignored -> new Update();
                case DeleteContext ignored -> new Delete();
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

            switch( child )
            {
                case ItemAllContext ctx -> newItem( ctx ).qname = "*";
                case ItemColumnContext ctx ->
                {
                    var item = newItem( ctx );
                    item.qname = ctx.qname().getText();
                    if( ctx.alias() != null )
                    {
                        item.alias = ctx.alias().name().getText();
                    }
                }
                case ItemTermContext ctx ->
                {
                    var item = newItem( ctx );
                    if( ctx.alias() != null )
                    {
                        item.alias = ctx.alias().name().getText();
                    }
                }
                case TermBETWEENContext ctx -> knockout( new BETWEEN( ctx, parser ));
                case TermCompareContext ctx -> knockout( new Comparison( ctx ));
                case TermINContext ctx -> knockout( new IN( ctx, parser ));
                case TermLIKEContext ctx -> knockout( new LIKE( ctx ));

                case ValuesContext ctx ->
                {
                    var knockouts = new ArrayList<Knockout<?>>();
                    var rows      = ctx.term();

                    for( var r : rows )
                    {
                        var literals = XPath.findAll( r, "/term/term/literal", parser );
                        if( literals.isEmpty() ) continue;

                        var row = new Row( ctx );
                        row.literals = new ArrayList<>( literals );
                        knockouts.add( row );
                    }

                    if( !knockouts.isEmpty() && top instanceof Insert insert )
                    {
                        insert.knockouts = knockouts;
                    }
                }

                default -> {}
            }

//            {
//                Knockout<?> k = switch( name )
//                {
//                    case "SetterContext"  ->
//                    {
//                        Setter setter = new Setter( child );
//                        setter.qname = XPath.findAll( child, "/setter/qname", parser ).iterator().next();
//                        setter.literal = XPath.findAll( child, "/setter/literal", parser ).iterator().next();
//
//                        yield setter;
//                    }
//                    default -> null;
//                };
//            }
            climb( child );
        }
    }

    public Insert newInsert( InsertContext parent )
    {
        var insert = new Insert();
        var names  = parent.qname().iterator();
        // First is table name, rest are column names.
        insert.table = names.next();
        while( names.hasNext() )
        {
            insert.columns.add( names.next() );
        }

        insert.knockouts = knockouts;
        return insert;
    }

    public Item newItem( ItemContext ctx )
    {
        var item = new Item();
        top.items.add( item );

        item.context  = ctx;
        item.verbatim = tokens.getText( ctx );
        return item;
    }

    public void knockout( Knockout<?> k )
    {
        if( k != null && k.isMatched() )
        {
            top.knockouts.add( k );
            knockouts.add( k );
        }
    }


}
