// Copyright 2010-2022 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package test;

import normalsql.parse.NormalSQLLexer;
import normalsql.parse.NormalSQLParser;
import static normalsql.parse.NormalSQLParser.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ErrorNode;
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
with rn as (
  select rownum rn
  from dual
  connect by level <= (select max(cases) from t1))
select pname
from t1, rn
where rn <= cases
order by pname
		"""
//		"""
//			-- whoops
//			-- whoops
//			gorp /* dang 'not this time' /* nested */ */ dorf -- bing
//			-- whoops
//			argh ('as -- oh boy /* hot dog */')
//			/*
//			big space gap
//			*/
//			SELECT 1, a.b.c FROM VALUES (), () gorp gorp;
//		""";
//		SELECT a.b.c;
//                SELECT DATEADD(HOUR, 1, TIME '23:00:00');
		;

//		parse( null, sql );
	}

	public static boolean parse( Path p, String sql )
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


		ArrayList<SyntaxError> errors = new ArrayList<>();

		CharStream chars = CharStreams.fromString( sql );
		NormalSQLLexer lexer = new NormalSQLLexer( chars );
		CommonTokenStream tokens = new CommonTokenStream( lexer );
		NormalSQLParser parser = new NormalSQLParser( tokens );
		parser.removeErrorListeners();
		parser.addErrorListener( new BaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer,
									Object offendingSymbol,
									int line,
									int charPositionInLine,
									String msg,
									RecognitionException e)
			{
				var error = new SyntaxError();
				error.recognizer = recognizer;
				error.offendingSymbol = offendingSymbol;
				error.line = line;
				error.charPositionInLine = charPositionInLine;
				error.msg = msg;
				error.e = e;
				errors.add( error );
			}

		} );

		ScriptContext script = parser.script();
		if( errors.isEmpty() ) return true;

//		NormalSQLVisitor visitor = new NormalSQLVisitor();
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
		String[] lines = sql.split( "\\n" );
		for( int i = 0; i < lines.length ; i++ )
		{
//			System.out.printf( "%d: %s\n", i + 1, lines[i] );
			System.out.println( lines[i] );
		}
//		System.out.println( sql );

//		System.out.println();
		System.out.println( toStringTree( script, parser ) );
//		System.out.println(script.toInfoString( script, parser ) );
		return false;
	}

	public static String toStringTree( Tree parent, Parser recog )
	{
		String[] ruleNames = recog.getRuleNames();
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
