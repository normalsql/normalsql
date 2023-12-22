// Copyright 2010-2023 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package test;

import normalsql.parse.NormalSQLLexer;
import normalsql.parse.NormalSQLParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.AmbiguityInfo;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.misc.Utils;
import org.antlr.v4.runtime.tree.Tree;
import org.antlr.v4.runtime.tree.Trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.out;

public class KeywordTester
{
	public static void main( String... args )
	{
		String sql =
		"abc"
		;

		var chars = CharStreams.fromString( sql );
		var lexer = new NormalSQLLexer( chars );
		var tokens = new CommonTokenStream( lexer );
		var parser = new NormalSQLParser( tokens );
		parser.setProfile( true );
//		parser.setTrace( true );
//		parser.getInterpreter().setPredictionMode( PredictionMode.LL_EXACT_AMBIG_DETECTION );
		parser.getInterpreter().setPredictionMode( PredictionMode.LL_EXACT_AMBIG_DETECTION );
//		parser.getInterpreter().setPredictionMode( PredictionMode.LL_EXACT_AMBIG_DETECTION );
//		out.println( parser.getInterpreter().getPredictionMode());
	    var aa = parser.aa();


	}


	/** Given an ambiguous parse information, return the list of ambiguous parse trees.
	 *  An ambiguity occurs when a specific token sequence can be recognized
	 *  in more than one way by the grammar. These ambiguities are detected only
	 *  at decision points.
	 *
	 *  The list of trees includes the actual interpretation (that for
	 *  the minimum alternative number) and all ambiguous alternatives.
	 *  The actual interpretation is always first.
	 *
	 *  This method reuses the same physical input token stream used to
	 *  detect the ambiguity by the original parser in the first place.
	 *  This method resets/seeks within but does not alter originalParser.
	 *
	 *  The trees are rooted at the node whose start..stop token indices
	 *  include the start and stop indices of this ambiguity event. That is,
	 *  the trees returned will always include the complete ambiguous subphrase
	 *  identified by the ambiguity event.  The subtrees returned will
	 *  also always contain the node associated with the overridden decision.
	 *
	 *  Be aware that this method does NOT notify error or parse listeners as
	 *  it would trigger duplicate or otherwise unwanted events.
	 *
	 *  This uses a temporary ParserATNSimulator and a ParserInterpreter
	 *  so we don't mess up any statistics, event lists, etc...
	 *  The parse tree constructed while identifying/making ambiguityInfo is
	 *  not affected by this method as it creates a new parser interp to
	 *  get the ambiguous interpretations.
	 *
	 *  Nodes in the returned ambig trees are independent of the original parse
	 *  tree (constructed while identifying/creating ambiguityInfo).
	 *
	 *  @since 4.5.1
	 *
	 *  @ param g              From which grammar should we drive alternative
	 *                        numbers and alternative labels.
	 *
	 *  @param op The parser used to create ambiguityInfo; it
	 *                        is not modified by this routine and can be either
	 *                        a generated or interpreted parser. It's token
	 *                        stream *is* reset/seek()'d.
	 *  @param tokens		  A stream of tokens to use with the temporary parser.
	 *                        This will often be just the token stream within the
	 *                        original parser but here it is for flexibility.
	 *
	 *  @ param decision       Which decision to try different alternatives for.
	 *
	 *  @ param alts           The set of alternatives to try while re-parsing.
	 *
	 *  @ param startIndex	  The index of the first token of the ambiguous
	 *                        input or other input of interest.
	 *
	 *  @ param stopIndex      The index of the last token of the ambiguous input.
	 *                        The start and stop indexes are used primarily to
	 *                        identify how much of the resulting parse tree
	 *                        to return.
	 *
	 *  @param startRuleIndex The start rule for the entire grammar, not
	 *                        the ambiguous decision. We re-parse the entire input
	 *                        and so we need the original start rule.
	 *
	 *  @return               The list of all possible interpretations of
	 *                        the input for the decision in ambiguityInfo.
	 *                        The actual interpretation chosen by the parser
	 *                        is always given first because this method
	 *                        retests the input in alternative order and
	 *                        ANTLR always resolves ambiguities by choosing
	 *                        the first alternative that matches the input.
	 *                        The subtree returned
	 *
	 *  @throws RecognitionException Throws upon syntax error while matching
	 *                               ambig input.
	 */
	public static
	List<ParserRuleContext> getAltTrees( Parser op, TokenStream tokens, AmbiguityInfo ai, int startRuleIndex )
		throws RecognitionException
	{
		var trees = new ArrayList<ParserRuleContext>();

		// Create a new parser interpreter to parse the ambiguous subphrase
		ParserInterpreter parser = deriveTempParserInterpreter( op, tokens );

		int stopIndex = ai.stopIndex;
		if ( stopIndex>=(tokens.size()-1) ) { // if we are pointing at EOF token
			// EOF is not in tree, so must be 1 less than last non-EOF token
			stopIndex = tokens.size()-2;
		}

		// get ambig trees
		int alt = ai.ambigAlts.nextSetBit(0);
		while( alt>=0 )
		{
			// re-parse entire input for all ambiguous alternatives
			// (don't have to do first as it's been parsed, but do again for simplicity
			//  using this temp parser.)
			parser.reset();
			parser.addDecisionOverride(ai.decision, ai.startIndex, alt);
			ParserRuleContext t = parser.parse(startRuleIndex);

			var ambigSubTree =
				Trees.getRootOfSubtreeEnclosingRegion(t, ai.startIndex, stopIndex);


			// Use higher of overridden decision tree or tree enclosing all tokens
			if ( Trees.isAncestorOf(parser.getOverrideDecisionRoot(), ambigSubTree) )
			{
				ambigSubTree =  parser.getOverrideDecisionRoot();
			}
			trees.add(ambigSubTree);
			alt =  ai.ambigAlts.nextSetBit(alt+1);
		}

		return trees;
	}


	/** Derive a new parser from an old one that has knowledge of the grammar.
	 *  The Grammar object is used to correctly compute outer alternative
	 *  numbers for parse tree nodes. A parser of the same type is created
	 *  for subclasses of {@link ParserInterpreter}.
	 */
	public static ParserInterpreter deriveTempParserInterpreter( Parser originalParser, TokenStream tokens) {
		var parser = new ParserInterpreter(originalParser.getGrammarFileName(),
										   originalParser.getVocabulary(),
										   Arrays.asList(originalParser.getRuleNames()),
					                       originalParser.getATN(),
										   tokens);

		parser.setInputStream(tokens);

		// Make sure that we don't get any error messages from using this temporary parser
		parser.setErrorHandler(new BailErrorStrategy());
		parser.removeErrorListeners();
		parser.removeParseListeners();
		parser.getInterpreter().setPredictionMode( PredictionMode.LL_EXACT_AMBIG_DETECTION );
		return parser;
	}

	public static
	String toStringTree( Tree t, List<String> gosh )
	{
		if( t == null )
		{
			return "null";
		}

		String s = Utils.escapeWhitespace( Trees.getNodeText( t, gosh ), false );
		if( t.getChildCount() == 0 )
		{
			return s;
		}

		StringBuilder buf = new StringBuilder();
//		buf.append( '❰' );
		buf.append( '「' );
		buf.append( s );
		buf.append( ' ' );
		for( int i = 0; i < t.getChildCount(); i++ )
		{
			if( i > 0 )
			{
				buf.append( ' ' );
			}
			buf.append( toStringTree( t.getChild( i ), gosh ) );
		}
//		buf.append( '❱' );
		buf.append( '」' );
		return buf.toString();
	}

}

