// Copyright 2010-2022 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package test;

import normalsql.NormalSQLVisitor;
import normalsql.parse.NormalSQLLexer;
import normalsql.parse.NormalSQLParser;
import static normalsql.parse.NormalSQLParser.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.Tree;

import java.util.Arrays;
import java.util.List;

/**
 * <p>Drill class.</p>
 *
 * @author jasonosgood
 * @version $Id: $Id
 */
public class Drill
{
	/**
	 * <p>main.</p>
	 *
	 * @param args a {@link java.lang.String} object
	 */
	public static void main( String... args )
	{
		String sql =
			"""
			 SELECT DATEADD(HOUR, 1, TIME '23:00:00');
			""";
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

		ScriptContext e = parser.script();

		NormalSQLVisitor visitor = new NormalSQLVisitor();
		visitor.parser = parser;
		visitor.tokens = tokens;
		visitor.visit( e );


		System.out.println( e.toStringTree( parser ) );
	}
}
