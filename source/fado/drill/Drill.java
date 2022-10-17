package fado.drill;

import fado.parse.GenericSQLLexer;
import fado.parse.GenericSQLParser;
import fado.parse.GenericSQLParser.*;
import org.antlr.v4.runtime.*;

public class Drill
{
	public static void main( String... args )
	{
		String sql =
"select * from (select 1), (select 2);"
//"SELECT * FROM VALUES (1), (2) T(x)"
//"SELECT * FROM SYSTEM_RANGE(1, 10) WHERE X IN ((SELECT 1), (SELECT 2));"
//"select id from test where 1=0;"
//"SELECT X FROM VALUES (2), (3);"
			;
		CharStream chars = CharStreams.fromString( sql );
		GenericSQLLexer lexer = new GenericSQLLexer( chars );
		CommonTokenStream tokens = new CommonTokenStream( lexer );
		GenericSQLParser parser = new GenericSQLParser( tokens );
		parser.addErrorListener( new BaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer,
			                        Object offendingSymbol,
			                        int line,
			                        int charPositionInLine,
			                        String msg,
			                        RecognitionException e)
			{
					System.err.println("line " + line + ":" + charPositionInLine + " " + msg);
			}

		} );

		ParserRuleContext e = parser.parse();
//		ParserRuleContext e = parser.function();

		System.out.println( e.toInfoString( parser ) );
		System.out.println( e.toStringTree( parser ) );

//		process( e );

	}

//	static void process( MaybeContext ec )
//	{
//		if( ec.op != null && ec.op.getType() == MINUS )
//		{
//			ExpressionContext parent = (ExpressionContext) ec.getParent();
//			ArrayList<ParseTree> kids = new ArrayList<>();
//			kids.add( parent.getChild( 0 ) );
//			kids.add( parent.getChild( 1 ) );
//			kids.add( ec.getChild( 1 ) );
//			parent.children = kids;
//			String text = ec.getText();
//			ec.expression( 0 ).literal().setStartTokenText( text );
//			ec.setParent( null );
//			ec.expression( 0 ).setParent( parent );
//			ec.children.clear();
//		}
//		else
//		{
//			List<ExpressionContext> children = ec.getRuleContexts( ExpressionContext.class );
//			for( ExpressionContext child : children )
//			{
//				process( child );
//			}
//		}
//	}
}
