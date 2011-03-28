package fado.parse;

import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.debug.BlankDebugEventListener;


import java.util.Stack;
import java.util.ArrayList;
import java.util.List;

/** This parser listener tracks rule entry/exit and token matches
 *  to build a simple parse tree using MyParseTree nodes.
 */
public class 
	ParseTreeBuilder 
extends 
	BlankDebugEventListener 
{
	public static final String EPSILON_PAYLOAD = "<epsilon>";
	
	Stack callStack = new Stack();
	List hiddenTokens = new ArrayList();
	int backtracking = 0;

	public ParseTreeBuilder(String grammarName) {
		FadoParseNode root = create("<grammar "+grammarName+">");
		callStack.push(root);
	}

	public FadoParseNode getTree() {
		return (FadoParseNode)callStack.elementAt(0);
	}

	/**  What kind of node to create.  You might want to override
	 *   so I factored out creation here.
	 */
	public FadoParseNode create(Object payload) {
		return new FadoParseNode(payload);
	}

	public FadoParseNode epsilonNode() {
		return create(EPSILON_PAYLOAD);
	}

	/** Backtracking or cyclic DFA, don't want to add nodes to tree */
	public void enterDecision(int d) { backtracking++; }
	public void exitDecision(int i) { backtracking--; }

	public void enterRule(String filename, String ruleName) {
		if ( backtracking>0 ) return;
		FadoParseNode parentRuleNode = (FadoParseNode)callStack.peek();
		FadoParseNode ruleNode = create(ruleName);
		parentRuleNode.addChild(ruleNode);
		callStack.push(ruleNode);
	}

	public void exitRule(String filename, String ruleName) {
		if ( backtracking>0 ) return;
		FadoParseNode ruleNode = (FadoParseNode)callStack.peek();
		if ( ruleNode.getChildCount()==0 ) {
			ruleNode.addChild(epsilonNode());
		}
		callStack.pop();		
	}

	public void consumeToken(Token token) {
		if ( backtracking>0 ) return;
		FadoParseNode ruleNode = (FadoParseNode)callStack.peek();
		FadoParseNode elementNode = create(token);
		elementNode.hiddenTokens = this.hiddenTokens;
		this.hiddenTokens = new ArrayList();
		ruleNode.addChild(elementNode);
	}

	public void consumeHiddenToken(Token token) {
		if ( backtracking>0 ) return;
		hiddenTokens.add(token);
	}

	public void recognitionException(RecognitionException e) {
		if ( backtracking>0 ) return;
		FadoParseNode ruleNode = (FadoParseNode)callStack.peek();
		FadoParseNode errorNode = create(e);
		ruleNode.addChild(errorNode);
	}
}
