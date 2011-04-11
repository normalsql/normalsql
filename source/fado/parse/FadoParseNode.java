package fado.parse;

import org.antlr.runtime.Token;
import org.antlr.runtime.tree.BaseTree;
import org.antlr.runtime.tree.Tree;

import java.util.ArrayList;
import java.util.List;

/** A record of the rules used to match a token sequence.  The tokens
 *  end up as the leaves of this tree and rule nodes are the interior nodes.
 *  This really adds no functionality, it is just an alias for CommonTree
 *  that is more meaningful (specific) and holds a String to display for a node.
 */
public class 
	FadoParseNode 
extends 
	BaseTree
{
	private Object _payload;
	public List<?> hiddenTokens;

	public FadoParseNode( Object payload ) 
	{
		_payload = payload;
	}

	public Tree dupNode() 
	{
		return null;
	}

	public int getType() 
	{
		return 0;
	}

	public String getText() 
	{
		return toString();
	}

	public int getTokenStartIndex() 
	{
		return 0;
	}

	public void setTokenStartIndex( int index ) 
	{
	}

	public int getTokenStopIndex() {
		return 0;
	}

	public void setTokenStopIndex(int index) {
	}

	public String toString() {
		if ( _payload instanceof Token ) {
			Token t = (Token)_payload;
			if ( t.getType() == Token.EOF ) {
				return "<EOF>";
			}
			return t.getText();
		}
		return _payload.toString();
	}

	/** Emit a token and all hidden nodes before.  EOF node holds all
	 *  hidden tokens after last real token.
	 */
	public String toStringWithHiddenTokens() {
		StringBuffer buf = new StringBuffer();
		if ( hiddenTokens!=null ) {
			for (int i = 0; i < hiddenTokens.size(); i++) {
				Token hidden = (Token) hiddenTokens.get(i);
				buf.append(hidden.getText());
			}
		}
		String nodeText = this.toString();
		if ( !nodeText.equals("<EOF>") ) buf.append(nodeText);
		return buf.toString();
	}

	/** Print out the leaves of this tree, which means printing original
	 *  input back out.
	 */
	public String toInputString() 
	{
		StringBuilder sb = new StringBuilder();
		toStringLeaves( sb );
		return sb.toString();
	}

	private void toStringLeaves( StringBuilder sb ) 
	{
		if( _payload instanceof Token ) 
		{
			sb.append( this.toStringWithHiddenTokens() );
			return;
		}
		for( int i = 0; children!=null && i < children.size(); i++ ) 
		{
			FadoParseNode t = (FadoParseNode)children.get(i);
			t.toStringLeaves( sb );
		}
	}
	
	public boolean isLeaf()
	{
		return children == null || children.size() == 0;
	}
	
	public Object getPayload()
	{
		return _payload;
	}
	public Token getToken()
	{
		Token result = null;
		if( _payload instanceof Token )
		{
			result = (Token) _payload;
		}
		else
		{
			for( Object child : children )
			{
				FadoParseNode node = ((FadoParseNode) child);
				if( !"unary".equals( node._payload ))
				{
					result = node.getToken();
				}
				if( result != null ) break;
			}
		}
		return result;
	}
	
	public List<FadoParseNode> find( String expression )
	{
		return find( expression, false );
	}
	
	// TODO: validate expression
	protected List<FadoParseNode> find( String expression, boolean first )
	{
		if( expression == null )
		{
			throw new NullPointerException( "expression" );
		}
		
		ArrayList<String> query = new ArrayList<String>();
		for( String atom : expression.split( "/" ))
		{
			atom = atom.trim();
			if( atom.length() == 0 )
			{
				throw new IllegalArgumentException( query + " contains empty match string" );
			}
			query.add( atom );
		}
		
		ArrayList<FadoParseNode> result = new ArrayList<FadoParseNode>();
		find( first, this, query, 0, false, result );
		return result;
	}

	private void find( boolean first, FadoParseNode parent, ArrayList<String> query, int nth, boolean seeking, ArrayList<FadoParseNode> result )
	{
		String spot = query.get( nth );
		List<BaseTree> kids = parent.getChildren();
		if( kids == null ) return;
		for( BaseTree kid : kids )
		{
			FadoParseNode child = (FadoParseNode) kid;
			{
				String text = child.getText();
				if( "*".equals( spot ) || text.equalsIgnoreCase( spot ))
				{
					if( nth + 1 < query.size() )
					{
						find( first, child, query, nth + 1, false, result );
					}
					else
					{
						result.add( child );
					}
				}
				else if( "**".equals( spot ))
				{
					find( first, child, query, nth + 1, true, result );
				}
				else if( seeking )
				{
					find( first, child, query, nth, true, result );
				}
			}
			
			if( first && result.size() > 0 ) break;
		}
	}

	public FadoParseNode findFirst( String expression )
	{
		FadoParseNode result = null;
		List<FadoParseNode> found = find( expression, true );
		if( found.size() > 0 )
		{
			result = found.get( 0 );
		}
		return result;
	}
	
	public String findFirstString( String expression )
	{
		String result = null;
		FadoParseNode first = findFirst( expression );
		if( first != null )
		{
			Token token = first.getToken();
			if( token != null )
			{
				result = token.getText();
			}
		}
		return result;
	}
}
