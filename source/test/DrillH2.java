package test;

import fado.parse.H2Lexer;
import fado.parse.H2Parser;
import org.antlr.v4.runtime.*;

public class DrillH2
{
	public static void main( String... args )
	{
		String sql =
"SELECT;"
			;
		System.out.println( sql.length() );
		System.out.println( sql.charAt( sql.length() - 1 ));
		System.out.println( Character.isAlphabetic( sql.charAt( sql.length() - 1 )) );
		System.out.println( Character.isAlphabetic( sql.charAt( sql.length() - 1 )) );

		CharStream chars = CharStreams.fromString( sql );
		H2Lexer lexer = new H2Lexer( chars );
		CommonTokenStream tokens = new CommonTokenStream( lexer );
		H2Parser parser = new H2Parser( tokens );
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
