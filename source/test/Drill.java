// Copyright 2010-2022 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package test;

import normalsql.parse.NormalSQLLexer;
import normalsql.parse.NormalSQLParser;
import org.antlr.v4.runtime.*;

public class Drill
{
	public static void main( String... args )
	{
		String sql =
//			"SELECT _ROWID_ S1, TEST._ROWID_ S2, PUBLIC.TEST._ROWID_ S3, SCRIPT.PUBLIC.TEST._ROWID_ S4,\n" +
//				"    \"_ROWID_\" U1, TEST.\"_ROWID_\" U2, PUBLIC.TEST.\"_ROWID_\" U3, SCRIPT.PUBLIC.TEST.\"_ROWID_\" U4\n" +
//				"    FROM TEST;"
			"select {fn TIMESTAMPADD(SQL_TSI_DAY, 1, {ts '2011-10-20 20:30:40.001'})};"
//			"select date '+0011-01-01';"
			;
		System.out.println( sql.length() );
		System.out.println( sql.charAt( sql.length() - 1 ));
		System.out.println( Character.isAlphabetic( sql.charAt( sql.length() - 1 )) );
		System.out.println( Character.isAlphabetic( sql.charAt( sql.length() - 1 )) );

		CharStream chars = CharStreams.fromString( sql );
		NormalSQLLexer lexer = new NormalSQLLexer( chars );
		CommonTokenStream tokens = new CommonTokenStream( lexer );
		NormalSQLParser parser = new NormalSQLParser( tokens );
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
