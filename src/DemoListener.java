import normalsql.grammar.SQLiteBaseListener;
import normalsql.grammar.SQLiteLexer;
import normalsql.grammar.SQLiteParser;

import static normalsql.grammar.SQLiteParser.*;
import normalsql.knockout.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import org.antlr.v4.runtime.tree.xpath.XPath;

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


    nodes = 0;
    terminals = 0;
    _level = 0;
    dump( tree, 0 );
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
    var extractor = new MyListener( parser, tokens );
    ParseTreeWalker.DEFAULT.walk( extractor, ugh );

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
    public Statement trunk = new Statement();
    public ArrayList<Knockout<?>> knockouts = new ArrayList<>();

    public SQLiteParser parser;
    public CommonTokenStream tokens;

    MyListener( SQLiteParser parser, CommonTokenStream tokens )
    {
        this.parser = parser;
        this.tokens = tokens;
        stack.push( trunk );
    }

    Statement top;

    void push( Statement statement )
    {
        top = stack.peek();
        top.add( statement );
        stack.push( statement );
        top = statement;
    }
    private void pop()
    {
        stack.pop();
        top = stack.peek();
    }

    @Override
    public void enterSelect( SelectContext ctx )
    {
        push( new Select() );
    }

    @Override
    public void exitSelect( SelectContext ctx )
    {
        pop();
    }

    @Override public void enterItemAll( ItemAllContext ctx )
    {
        var item = new Item();
        top.items.add( item );

        item.context  = ctx;
        item.verbatim = tokens.getText( ctx );
        item.qname = "*";

    }
    @Override public void enterItemColumn( ItemColumnContext ctx )
    {
        var item = newItem( ctx );
        item.qname = ctx.qname().getText();
        item.alias = ctx.alias().name().getText();
    }

    @Override public void enterItemTerm( ItemTermContext ctx )
    {
        var item = newItem( ctx );
        // TODO handle items without column name (eg expression) or alias. Use an index? Generate a column name?
        item.alias = ctx.alias().name().getText();
    }

    private Item newItem( ItemContext ctx )
    {
        var item = new Item();
        top.items.add( item );

        item.context  = ctx;
        item.verbatim = tokens.getText( ctx );
        return item;
    }

    @Override
    public void enterInsert( InsertContext ctx )
    {
        push( new Insert() );
    }

    @Override
    public void exitInsert( InsertContext ctx )
    {
        pop();
    }

    @Override
    public void enterDelete( DeleteContext ctx )
    {
        push( new Delete() );
    }

    @Override
    public void exitDelete( DeleteContext ctx )
    {
        pop();
    }

    @Override
    public void enterUpdate( UpdateContext ctx )
    {
        push( new Update() );
    }

    @Override
    public void exitUpdate( UpdateContext ctx )
    {
        pop();
    }

    @Override
    public void enterValues( ValuesContext ctx )
    {
        var knockouts = new ArrayList<Knockout<?>>();
        var terms     = ctx.term();

//        var rowsX = XPath.findAll( ctx, "/values/term", parser );
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

    @Override
    public void enterTermBETWEEN( TermBETWEENContext ctx )
    {
        knockout( new BETWEEN( ctx, parser ));
    }
    @Override
    public void enterTermLIKE( TermLIKEContext ctx )
    {
        var like = new LIKE( ctx );
        like.column  = ctx.getChild( 0 );
        like.literal  = ctx.getChild( 1 );
    }

    @Override
    public void enterTermCompare( TermCompareContext ctx )
    {
        knockout( new Comparison( ctx ));
    }

    @Override
    public void enterTermIN( TermINContext ctx )
    {
        knockout( new IN( ctx, parser ));
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
