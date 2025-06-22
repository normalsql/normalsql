import normalsql.grammar.SQLiteBaseListener;
import normalsql.grammar.SQLiteLexer;
import normalsql.grammar.SQLiteParser;
import normalsql.grammar.SQLiteParser.*;
import normalsql.knockout.Select;
import normalsql.knockout.Statement;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import static java.lang.System.out;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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


////    out.println( ugh.toStringTree( parser) );
//    if ( parser.getNumberOfSyntaxErrors() > 0 ) {
//        throw new Exception( "Syntax error" );
//    }
//    if ( parser.getNumberOfSyntaxErrors() == 0 ) {
////        out.println( "OK" );
//    } else {
//        out.println( "Syntax error" );
//        out.println( p.getFileName() );
//    }

    nodes = 0;
    terminals = 0;
    _level = 0;
    dump( tree, 0 );
//    out.println( "statements " + ugh.children.size() );
//    out.println( "level " + _level );
//    out.println( "nodes " + nodes );
//    out.println( "terminals " + terminals );
//    out.println( "##" );
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
//            out.println( p.getFileName() );
            report.println( p.toString()+ " " + line + ":" + charPositionInLine  );
            report.flush();
            out.println( p.getFileName()+ " " + line + ":" + charPositionInLine  );
//            out.println( "line " + line + ":" + charPositionInLine + " " + msg );
        }
    });



    var ugh = parser.statements();
    MyListener extractor = new MyListener();
    ParseTreeWalker.DEFAULT.walk(extractor, ugh);
    return ugh;
}

int nodes = 0;
int terminals = 0;
int _level = 0;

int _errors = 0;

PrintWriter report = null;

void dump( ParseTree parent, int level )
{
    _level = Math.max( _level, level );
    for( int i = 0; i< parent.getChildCount(); i++ )
    {
        ParseTree child = parent.getChild( i );
        switch( child )
        {
            case ParserRuleContext ctx:
                nodes++;
//                out.println( "node " + nodes++ + " " + ctx.getClass().getSimpleName() );
                {
                    dump( child, ++level );
                }
                break;
            case TerminalNodeImpl node:
                terminals++;
//                out.println( "token " + terminals + " " + node.getText() );
                break;
            default:

        }

    }
}

class MyListener extends SQLiteBaseListener
{
    public Stack<Statement> stack = new Stack<>();
    public Statement              trunk     = new Statement();
    MyListener()
    {
        stack.push( trunk );
    }

    @Override
    public void enterStatement( StatementContext ctx )
    {
        fudge(  new Select( ));
    }

    void fudge( Statement statement )
    {
        var top = stack.peek();
        top.add( statement );
        stack.push( statement );
    }
}
