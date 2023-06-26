// Copyright 2010-2022 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package test;

import normalsql.parse.SqlBaseLexer;
import normalsql.parse.SqlBaseParser;
import org.antlr.v4.runtime.*;

/**
 * <p>Drill class.</p>
 *
 * @author jasonosgood
 * @version $Id: $Id
 */
public class DrillSqlBase
{
	public static void main( String... args )
	{
		String sql =

//			"SELECT ID FROM TEST LIMIT 1;"
			"SELECT 'a' = any ( ? );"
			;
		CharStream chars = CharStreams.fromString( sql );
		SqlBaseLexer lexer = new SqlBaseLexer( chars );
		CommonTokenStream tokens = new CommonTokenStream( lexer );
		SqlBaseParser parser = new SqlBaseParser( tokens );
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

		ParserRuleContext e = parser.statement();
//		NormalSQLVisitor visitor = new NormalSQLVisitor();
//		visitor.parser = parser;
//		visitor.tokens = tokens;
//		visitor.visit( e );


		System.out.println( e.toStringTree( parser ) );

	}
}
