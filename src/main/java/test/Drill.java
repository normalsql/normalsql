// Copyright 2010-2022 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package test;

import normalsql.SQLVisitor;
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
//			"select {fn TIMESTAMPADD(SQL_TSI_DAY, 1, {ts '2011-10-20 20:30:40.001'})};"
//			"select a as \"apple\", 1, true, 1 between 0 and 2, max( 1, 2, 3 ), 1 + 2 from test;"
			"SELECT NOT 0;"
//			"select date '+0011-01-01';"
			;
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
		SQLVisitor visitor = new SQLVisitor();
		visitor.parser = parser;
		visitor.tokens = tokens;
		visitor.visit( e );


		System.out.println( e.toStringTree( parser ) );

	}
}
