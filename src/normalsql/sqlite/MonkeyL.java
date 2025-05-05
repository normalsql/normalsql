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

package normalsql.sqlite;

import normalsql.grammar.SQLiteParser.QnameContext;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static normalsql.Glorp.getLocalName;

/**
 * Monkeys climb trees. Torturing this metaphor, "trunk" (not "root") is the starting node.
 *
 */

public class
MonkeyL
{
    public Statement                trunk     = new Statement();
    public Stack<Statement>         stack     = new Stack<>();
    public ArrayList<KnockoutL<?>>  knockouts = new ArrayList<>();
    public CommonTokenStream        tokens;

    public MonkeyL( CommonTokenStream tokens )
    {
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

            if( temp instanceof GlobbingRuleContext child )
            {

                String name = child.getClass().getSimpleName();
                Statement isStatement = switch( name )
                {
                    case "SelectContext" -> new Select();
                    case "InsertContext" -> climbInsert( child );
                    case "UpdateContext" -> new Update();
                    case "DeleteContext" -> new Delete();
                    default -> null;
                };

                if( isStatement != null )
                {
                    top.add( isStatement );
                    stack.push( isStatement );
                    climb( child );
                    stack.pop();
                    continue;
                }


                if( "ItemContext".equals( name ))
                {
                    // TODO: Could or should these be inlined into visitSelect?
                    var item = new Item();
                    item.context  = parent;
                    item.verbatim = tokens.getText( (RuleContext) child );

                    // TODO alternately, inline Globbing into Monkey, imbued with the
                    // arcane knowledge of rule names (strangely omitted from each
                    // subclass of ParseTreeContext)

                    // TODO support wildcards in names, eg "term*"
                    var term = child.findFirst( "*" );
                    String qname = tokens.getText( term );
                    // TODO extract local name from column's qname, instead of string processing
                    item.localName = getLocalName( qname );
                    // TODO handle items without column name (eg expression) or alias. Use an index? Generated a column name?



                    var alias = child.findFirst( "alias" );
                    if( alias != null )
                    {
                        item.alias = tokens.getText( alias );
                    }
                    top.items.add( item );
                }
                else
                {
                    KnockoutL<?> k = switch( name )
                    {
                        case "TermBETWEENContext" -> new BETWEEN( child );
                        case "TermCompareContext" -> new Comparison( child );
                        case "TermINContext"  -> new IN( child );
                        case "TermLIKEContext"  -> new LIKE( child );
                        case "SetterContext"  -> new Setter( child );
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
    }

    public Insert climbInsert( GlobbingRuleContext context )
    {
        ArrayList<KnockoutL<?>> knockouts = new ArrayList<>();

        // using wildcard ** instead of precise "select/selectCore/values/terms/row
        var foundRows = context.find( "**/row" );
        for( var foundRow : foundRows )
        {
            var literals = foundRow.find( "termliteral/literal" );
            if( literals.isEmpty() ) continue;

            Row row = new Row( context );
            row.literals = literals;
            knockouts.add( row );
        }

        if( knockouts.isEmpty() ) return null;

        Insert insert = new Insert();
        insert.table = (QnameContext) context.findFirst( "qname" );
        insert.columns = context.find( "columns/qname" );
        insert.knockouts = knockouts;

        return insert;
    }
}
