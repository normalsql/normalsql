// Copyright 2010-2023 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package test;

import normalsql.grammar.NormalSQLLexer;
import normalsql.grammar.NormalSQLParser;
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

import static normalsql.grammar.NormalSQLParser.NameContext;
import static normalsql.grammar.NormalSQLParser.QnameContext;

public class Combo
{
	public static void main( String... args )
	{
		String sql =
		"SELECT coalesce((select max(11- -19-f-t1.b+a) from t1 where exists(select 1 from t1 where 11-~(d)-c*a*~t1.a-t1.e-t1.e+coalesce((select coalesce((select t1.c from t1 where case (c) when d then e else 11 end=t1.f),t1.d) from t1 where (t1.d)>b), -11) | f+t1.f not in (((c)),b,13))),f) FROM t1 WHERE case when 19+c>=t1.a then t1.c when not case when not exists(select 1 from t1 where +f | b*b*19+19*13-a | case when t1.e not in (t1.f,t1.c,b) then 11 when 17>t1.c then a else e end<>e) then b when 17=t1.e then b else e end<>t1.b then a else d end-t1.b=(13)"
		;

		var drill = new Combo();
		if( drill.parse( null, sql, false ))
		{
//			drill.toStringTree(  );
		}
	}

//	 exprParser.ExprContext expr = null;

	public  boolean parse( Path p, String sql )
	{
		return parse( p, sql, false );
	}

	public  boolean parse( Path p, String sql, boolean bracketsEnabled )
	{
//		class SyntaxError
//		{
//			Recognizer<?, ?> recognizer;
//			Object offendingSymbol;
//			int line;
//			int charPositionInLine;
//			String msg;
//			RecognitionException e;
//		}


//		var errors = new ArrayList<SyntaxError>();

		var chars = CharStreams.fromString( sql );
		var lexer = new NormalSQLLexer( chars );
		var tokens = new CommonTokenStream( lexer );
		var parser = new NormalSQLParser( tokens );
//		parser.getInterpreter().setPredictionMode( PredictionMode.SLL );
//		parser.getInterpreter().setPredictionMode( PredictionMode.LL_EXACT_AMBIG_DETECTION );
		parser.getInterpreter().setPredictionMode( PredictionMode.LL );
		parser.setProfile( true );
		parser.setTrace( true );
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

		var expr = parser.aaa1();
//		if( errors.isEmpty() ) return true;

//		var visitor = new KnockoutVisitor();
//		visitor.parser = parser;
//		visitor.tokens = tokens;
//		visitor.visit( script );

		System.out.println();
		if( p != null ) System.out.println( p );
//		for( var e : errors )
//		{
//			System.out.println( "ERROR line " + e.line + ":" + e.charPositionInLine + " " + e.msg );
//		}

		System.out.println();
		var lines = sql.split( "\\n" );
		for( int i = 0; i < lines.length ; i++ )
		{
//			System.out.printf( "%d: %s\n", i + 1, lines[i] );
			System.out.println( lines[i] );
		}

		System.out.println( toStringTree( parser, expr ) );
		return false;
	}

//	public  String toStringTree( )
//	{
//		return toStringTree( expr );
//	}


	public  String toStringTree( Parser p, ParseTree parent )
	{
		String[] ruleNames = p.getRuleNames();
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
