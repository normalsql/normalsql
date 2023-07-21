// Copyright 2010-2022 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package test;

//import normalsql.parse.ReservedParser;
import normalsql.parse.ReservedParser;
import normalsql.parse.SqlBaseParser;
import normalsql.parse.SqlBaseLexer;
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
				"""
				 SELECT CEIL(1.5::REAL), CEIL(-1.5::REAL), CEIL(1.5::REAL) IS OF (REAL);
				""";

		CharStream chars = CharStreams.fromString( sql );
		SqlBaseLexer lexer = new SqlBaseLexer( chars );
		CommonTokenStream tokens = new CommonTokenStream( lexer );
		ReservedParser parser = new ReservedParser( tokens );
//		SqlBaseParser parser = new SqlBaseParser( tokens );

		ParserRuleContext e = parser.singleStatement();
		System.out.println( e.toStringTree( parser ) );
	}
}
