// Copyright 2010-2023 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package test;

import normalsql.grammar.PostgreSQLLexer;
import normalsql.grammar.PostgreSQLParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.Tree;

import java.nio.file.Path;


public class BetweenAndDebug
{
	public static void main( String... args )
	{
		String sql =
		"0 between 1 and 2 and 3"
		;

		var drill = new BetweenAndDebug();
		if( drill.parse( null, sql ))
		{
		}
	}

	public  boolean parse( Path p, String sql )
	{
		var chars = CharStreams.fromString( sql );
		var lexer = new PostgreSQLLexer( chars );
		var tokens = new CommonTokenStream( lexer );
		var parser = new PostgreSQLParser( tokens );
//		parser.getInterpreter().setPredictionMode( PredictionMode.SLL );
//		parser.getInterpreter().setPredictionMode( PredictionMode.LL_EXACT_AMBIG_DETECTION );
		parser.getInterpreter().setPredictionMode( PredictionMode.LL );

//		var boop = parser.boop();
//		var tree = this.toStringTree( parser, boop );
//		System.out.println( tree );

		return false;
	}


	public  String toStringTree( Parser p, ParseTree parent )
	{
		String[] ruleNames = p.getRuleNames();
		StringBuilder sb = new StringBuilder();
		toStringTree( parent, ruleNames, sb, 0 );
		return sb.toString();
	}

	public static void toStringTree( Tree t, String[] ruleNames, StringBuilder buf, int depth )
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

		for( int nth = 0; nth < depth; nth++ )
		{
			buf.append( '\t' );
		}
		buf.append( '(' );
		buf.append( ruleName );

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
//					buf.append( ' ' );
					buf.append( '\n' );
					toStringTree( child, ruleNames, buf, depth+1 );
				}
			}
		}

		buf.append( ')' );
//		buf.append( '\n' );
	}

}
