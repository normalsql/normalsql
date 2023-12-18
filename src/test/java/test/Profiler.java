// Copyright 2010-2023 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package test;

import normalsql.parse.NormalSQLLexer;
import normalsql.parse.NormalSQLParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.Tree;

import java.nio.file.Path;
import java.util.ArrayList;

import static normalsql.parse.NormalSQLParser.*;

public class Profiler
{
	public static void main( String... args )
	{
		String sql =
		"""
SELECT (SELECT sum(value2==xyz) FROM t2) FROM (SELECT curr.value1 as xyz FROM t1 AS curr LEFT JOIN t1 AS other GROUP BY curr.id1);
"""
		;

		var drill = new Profiler();
		if( drill.parse( null, sql, false ))
		{
			drill.toStringTree(  );
		}
	}

	 NormalSQLParser parser = null;
	 ScriptContext script = null;

	public  boolean parse( Path p, String sql )
	{
		return parse( p, sql, false );
	}

	public  boolean parse( Path p, String sql, boolean bracketsEnabled )
	{
		var chars = CharStreams.fromString( sql );
		var lexer = new NormalSQLLexer( chars );
		lexer.bracketsEnabled = bracketsEnabled;
		var tokens = new CommonTokenStream( lexer );
		parser = new NormalSQLParser( tokens );
		parser.setProfile( true );

		script = parser.script();
		var parseinfo = parser.getParseInfo();
		for( var di : parseinfo.getDecisionInfo() )
		{
//			if( di.ambiguities.size
		}


//		System.out.println( info );

		System.out.println();
		if( p != null ) System.out.println( p );


		System.out.println( toStringTree( script ) );
		return false;
	}

	public  String toStringTree( )
	{
		return toStringTree( script );
	}


	public  String toStringTree( Tree parent )
	{
		String[] ruleNames = parser.getRuleNames();
		StringBuilder sb = new StringBuilder();
		toStringTree( parent, ruleNames, sb );
		return sb.toString();
	}

	public static void toStringTree( Tree t, String[] ruleNames, StringBuilder buf )
	{
		if( t instanceof ErrorNode)
		{
			buf.append( '*' );
			buf.append( t );
			buf.append( '*' );
			return;
		}

		int ruleIndex = ((RuleContext) t ).getRuleContext().getRuleIndex();
		String ruleName = ruleNames[ruleIndex];

		buf.append( '(' );
		buf.append( ruleName );

		if( t instanceof ColumnContext || t instanceof TableContext || t instanceof NameContext )
		{
			// Combine multiple names
			String text = ((RuleContext) t ).getText();
			buf.append( ' ' );
			buf.append( '«' );
			buf.append( text );
			buf.append( '»' );
		}
		else
		{
			for( int i = 0; i < t.getChildCount(); i++ )
			{
				Tree child = t.getChild( i );

				if( child instanceof TerminalNode )
				{
					String symbol = child.toString();
					switch( symbol )
					{
						// eat punctuation
						case "(":
						case ")":
						case "[":
						case "]":
						case ",":
						case ";":
						case "<EOF>":
							break;

						default:
							buf.append( ' ' );
							buf.append( '«' );
							buf.append( symbol );
							buf.append( '»' );
							break;
					}
				}
				else
				{
					buf.append( ' ' );
					toStringTree( child, ruleNames, buf );
				}
			}
		}

		buf.append( ')' );
	}

}
