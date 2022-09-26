package fado.drill;

import fado.parse.GenericSQLLexer;
import fado.parse.GenericSQLParser;
import fado.parse.GenericSQLParser.*;
import org.antlr.v4.runtime.*;

public class Drill
{
	public static void main( String... args )
	{
		String sql = "BIT_NAND_AGG(V) FILTER (WHERE V <= 0xffffffff0fff) FROM TEST"
			;
		CharStream chars = CharStreams.fromString( sql );
		GenericSQLLexer lexer = new GenericSQLLexer( chars );
		CommonTokenStream tokens = new CommonTokenStream( lexer );
		GenericSQLParser parser = new GenericSQLParser( tokens );
		ParserRuleContext e = parser.parse();

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
