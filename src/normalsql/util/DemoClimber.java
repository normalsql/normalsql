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
import normalsql.knockout.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;


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
    var monkey = new Climber( parser, tokens );

    return ugh;
}

int nodes = 0;
int terminals = 0;
int _level = 0;

int _errors = 0;

PrintWriter report = null;
