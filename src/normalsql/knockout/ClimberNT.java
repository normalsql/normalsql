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

//package normalsql.knockout;

import normalsql.grammar.SQLiteLexer;
import normalsql.grammar.SQLiteParser;
import normalsql.grammar.SQLiteParser.*;
import normalsql.knockout.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import org.antlr.v4.runtime.tree.xpath.XPath;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import static java.lang.System.out;

/**
 * Monkeys climb trees. Torturing this metaphor, "trunk" (not "root") is the starting node.
 *
 */

void main() throws Exception
{
    // Record start time
    long startTime = System.currentTimeMillis();

    report = new PrintWriter( "/Users/jasonosgood/Projects/fork/grammars-v4/sql/mysql/Oracle/errors.txt" );
    report.println( "##" );

    String[] source =
            {
//        "/Users/jasonosgood/Projects/ScrapeMySQLTests/logs",
//        "./examples",
//        "/Users/jasonosgood/Projects/grammars-v4/sql/mysql/Positive-Technologies/examples"
                    "/Users/jasonosgood/Projects/normalsql/doc/classicmodels"
            };

    for( var src : source )
    {
        walker( src );
    }

    report.close();

    // Record end time and calculate elapsed time
    long endTime = System.currentTimeMillis();
    long elapsedTimeMs = endTime - startTime;
    double elapsedTimeSec = elapsedTimeMs / 1000.0;

    out.println( "Errors: " + _errors );

    // Display elapsed time
    out.println("Elapsed time: " + elapsedTimeMs + " ms (" + elapsedTimeSec + " seconds)");
}

private void walker( String source ) throws Exception
{
    var paths = Files.walk( Paths.get( source ) ).filter( Files::isRegularFile );

    for( var p : paths.toList() )
    {
        if( p.getFileName().toString().endsWith( ".sql" ) )
        {
            parse( p );
        }
//        if( _errors > 20 ) break;
    }
}

Path fileName;

void parse( Path p ) throws Exception
{
    fileName = p.getFileName();
    out.println( fileName );

    var tree   = toStatements( p );


    nodes = 0;
    terminals = 0;
    _level = 0;
    dump( tree, 0 );
}

void dump( ParseTree parent, int level )
{
    _level = Math.max( _level, level );
    for( int i = 0; i< parent.getChildCount(); i++ )
    {
        ParseTree child = parent.getChild( i );
        switch( child )
        {
            case ParserRuleContext ctx:
            {
                nodes++;
                dump( child, ++level );
            }
//                out.println( "node " + nodes++ + " " + ctx.getClass().getSimpleName() );
            break;
            case TerminalNodeImpl node:
                terminals++;
//                out.println( "token " + terminals + " " + node.getText() );
                break;
            default:

        }

    }
}

ParserRuleContext toStatements( Path p ) throws IOException
{
    var chars = CharStreams.fromPath( p );
    var lexer = new SQLiteLexer( chars );
    var tokens = new CommonTokenStream( lexer );
    var parser = new SQLiteParser( tokens );
    parser.removeErrorListeners( );
    parser.addErrorListener( new BaseErrorListener() {
        @Override
        public void syntaxError( Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e )
        {
            _errors++;
            report.println( p + " " + line + ":" + charPositionInLine  );
            report.flush();
            out.println( p.getFileName() + " " + line + ":" + charPositionInLine  );
        }
    });



    var ugh = parser.statements();
    var extractor = new ClimberNT2( parser, tokens );
//    ParseTreeWalker.DEFAULT.walk( extractor, ugh );

    return ugh;
}

int nodes = 0;
int terminals = 0;
int _level = 0;

int _errors = 0;

PrintWriter report = null;

//public
class
ClimberNT2
{
    Parser parser;
    public Statement        trunk = new Statement();
    public Stack<Statement>       stack     = new Stack<>();
    public ArrayList<Knockout<?>> knockouts = new ArrayList<>();
    public CommonTokenStream      tokens;

    public ClimberNT2( Parser parser, CommonTokenStream tokens )
    {
        this.parser = parser;
        this.tokens = tokens;
        stack.add( trunk );
    }

    public void climb( GlobbingRuleContext parent )
    {
        Statement top = stack.peek();
        for( var child : parent )
        {
            Statement statement = switch( child )
            {
                case SelectContext ignore -> new Select();
                case InsertContext ignored -> climbInsert( child );
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
                    var terms     = ctx.term();

                    for( var term : terms )
                    {
                        var literals = XPath.findAll( term, "/term/term/literal", parser );
                        if( literals.isEmpty() ) continue;

                        var row = new Row( ctx );
                        row.literals = new ArrayList<>( literals );
                        knockouts.add( row );
                    }

                    if( !knockouts.isEmpty() )
                    {
                        if( top instanceof Insert insert )
                        {
                            insert.knockouts = knockouts;
                        }
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

    public Insert climbInsert( ParseTree parent )
    {
        var insert = new Insert();
        Iterator<ParseTree> iterator = XPath.findAll( parent, "/insert/qname", parser ).iterator();
        // First is table name, rest are column names.
        insert.table = iterator.next();
        while( iterator.hasNext() )
        {
            insert.columns.add( iterator.next() );
        }

        insert.knockouts = knockouts;
        return insert;
    }

    public Item newItem( ItemContext ctx )
    {
        var item = new Item();
        var top = stack.peek();
        top.items.add( item );

        item.context  = ctx;
        item.verbatim = tokens.getText( ctx );
        return item;
    }

    public void knockout( Knockout<?> k )
    {
        if( k != null && k.isMatched() )
        {
            var top = stack.peek();
            top.knockouts.add( k );
            knockouts.add( k );
        }
    }


}
