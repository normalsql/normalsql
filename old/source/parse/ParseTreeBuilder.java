package fado.parse;

import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.debug.BlankDebugEventListener;

import java.util.Stack;

/** 
 * Captures parse tree from execution of grammar. 
 */

public class 
	ParseTreeBuilder 
extends 
	BlankDebugEventListener 
{
	Stack<ParseNode> _stack = new Stack<ParseNode>();
	int backtracking = 0;

	public ParseTreeBuilder() 
	{
		ParseNode root = new ParseNode( "<>" );
		_stack.push( root );
	}

	public ParseNode getTree() 
	{
		return _stack.elementAt( 0 );
	}

	/** Backtracking or cyclic DFA, don't want to add nodes to tree */
	/** I don't even know what this stuff does... -- Jason */
	@Override
	public void enterDecision( int i ) 
	{ 
//		System.out.printf( "\nenterDecision %d", i );
		backtracking++; 
	}
	
	@Override
	public void exitDecision( int i ) 
	{ 
//		System.out.printf( "\nexitDecision %d", i );
		backtracking--; 
	}

	public void enterRule( String filename, String rule ) 
	{
//		System.out.printf( "\nenterRule %s", rule );
		if( backtracking > 0 ) return;
		ParseNode top = _stack.peek();
		ParseNode child = new ParseNode( rule );
		top.addChild( child );
		_stack.push( child );
	}

	public void exitRule( String filename, String rule ) 
	{
//		System.out.printf( "\nexitRule %s", rule );
		if( backtracking > 0 ) return;
//		ParseNode top = _stack.peek();
//		FadoParseNode ruleNode = (FadoParseNode)callStack.peek();
//		if ( ruleNode.getChildCount()==0 ) {
//			ruleNode.addChild(epsilonNode());
//		}
		_stack.pop();		
	}

	public void consumeToken( Token token ) 
	{
//		System.out.printf( "\nconsumeToken %s %s", token.getText(), token.getType() );
		if( backtracking > 0 ) return;
		if( token.getType() == Token.EOF ) return;
		ParseNode top = _stack.peek();
		top.addChild( token );
	}

	public void consumeHiddenToken( Token token ) 
	{
//		System.out.printf( "\nconsumeHiddenToken %s %s", token.getText(), token.getType() );
		consumeToken( token );
	}

	public void recognitionException( RecognitionException e ) 
	{
//		System.out.printf( "\nrecognitionException" );
		if( backtracking > 0 ) return;
		ParseNode ruleNode = _stack.peek();
		ruleNode.addChild( e );
	}
}
