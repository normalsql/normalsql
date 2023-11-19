// Copyright 2010-2023 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package test;

import normalsql.parse.KnockoutVisitor;
import normalsql.parse.NormalSQLLexer;
import normalsql.parse.NormalSQLParser;
import static normalsql.parse.NormalSQLParser.*;

import normalsql.parse.NormalSQLVisitor;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.Tree;

import java.nio.file.*;
import java.util.ArrayList;

public class Drill
{
	public static void main( String... args )
	{
		String sql =
		"""
-- SELECT (SELECT sum(value2==xyz) FROM t2) FROM (SELECT curr.value1 as xyz FROM t1 AS curr LEFT JOIN t1 AS other GROUP BY curr.id1);
-- select * from (SELECT 1);
select (  select 1 )
"""
		;

//		int count = 0;
//		while( count < 1_000_000 )
//		{
//			var drill = new Drill();
//			drill.parse( null, sql, false );
//			count++;
//			if( count % 200 == 0 )
//			{
//				System.out.println( count );
//			}
//		}
		var drill = new Drill();
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
		class SyntaxError
		{
			Recognizer<?, ?> recognizer;
			Object offendingSymbol;
			int line;
			int charPositionInLine;
			String msg;
			RecognitionException e;
		}


		var errors = new ArrayList<SyntaxError>();

		var chars = CharStreams.fromString( sql );
		var lexer = new NormalSQLLexer( chars );
		lexer.bracketsEnabled = bracketsEnabled;
		var tokens = new CommonTokenStream( lexer );
		parser = new NormalSQLParser( tokens );
//		parser.removeErrorListeners();
		parser.addErrorListener( new DiagnosticErrorListener() );
//		parser.addErrorListener( new BaseErrorListener() {
//			@Override
//			public void syntaxError(Recognizer<?, ?> recognizer,
//									Object offendingSymbol,
//									int line,
//									int charPositionInLine,
//									String msg,
//									RecognitionException e)
//			{
//				var error = new SyntaxError();
//				error.recognizer = recognizer;
//				error.offendingSymbol = offendingSymbol;
//				error.line = line;
//				error.charPositionInLine = charPositionInLine;
//				error.msg = msg;
//				error.e = e;
//				errors.add( error );
//			}
//
//		} );

		script = parser.script();
		if( errors.isEmpty() ) return true;

//		var visitor = new KnockoutVisitor();
//		visitor.parser = parser;
//		visitor.tokens = tokens;
//		visitor.visit( script );

		System.out.println();
		if( p != null ) System.out.println( p );
		for( var e : errors )
		{
			System.out.println( "ERROR line " + e.line + ":" + e.charPositionInLine + " " + e.msg );
		}

		System.out.println();
		var lines = sql.split( "\\n" );
		for( int i = 0; i < lines.length ; i++ )
		{
//			System.out.printf( "%d: %s\n", i + 1, lines[i] );
			System.out.println( lines[i] );
		}

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

		if( t instanceof QnameContext || t instanceof NameContext )
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
