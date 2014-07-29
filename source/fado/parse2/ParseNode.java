package fado.parse2.parse;

public class
	ParseNode 
{
	static java.util.HashMap<String,Integer> lexMap = new java.util.HashMap<String,Integer>();

	public static void addLexType( String name, int type )
	{
		lexMap.put( name, type );
	}

	public static int getLexType( String name )
	{
		int result = 0;
		if( lexMap.containsKey( name ))
		{
			result = lexMap.get( name );
		}
		return result;
	}

	private String _ruleName;

	public ParseNode( String ruleName )
	{
		_ruleName = ruleName;
	}

	java.util.ArrayList<Object> _children = new java.util.ArrayList<Object>();

	public void addChild( Object child )
	{
		_children.add( child );
	}

	public int getChildCount()
	{
		return _children.size();
	}

	public Object getChild( int index )
	{
		return _children.get( index );
	}

	public java.util.List<Object> getChildren()
	{
		return _children;
	}

	public org.antlr.runtime.Token getToken( int index )
	{
		int count = index;
		for( Object temp : _children )
		{
			if( temp instanceof org.antlr.runtime.Token )
			{
				org.antlr.runtime.Token token = (org.antlr.runtime.Token) temp;
				if( token.getChannel() != org.antlr.runtime.Token.HIDDEN_CHANNEL )
				{
					if( count == 0 )
					{
						return token;
					}
					else
					{
						count--;
					}
				}
			}
		}
		return null;
	}

	// Replace original value with JDBC parameter '?'
	// TODO: Handle unary numbers, eg -100
	public void convertToJDBCParam()
	{
		// Replace text of first "visible" (non whitespace) token, then exit
		for( Object o : _children )
		{
			if( o instanceof org.antlr.runtime.Token )
			{
				org.antlr.runtime.Token token = (org.antlr.runtime.Token) o;
				if( token.getChannel() != org.antlr.runtime.Token.HIDDEN_CHANNEL )
				{
					token.setText( "?" );
					break;
				}
			}
		}
	}

	public String toString() {
		return toParseTree();
	}

	public String toParseTree()
	{
		StringBuilder sb = new StringBuilder();
		toParseTree( sb, 0, this );
		return sb.toString();
	}

	public static void toParseTree( StringBuilder sb, int indent, ParseNode node )
	{
		for( int x = 0; x < indent; x++ )
		{
			sb.append( "  " );
		}
		sb.append( "( " );
		sb.append( node.getRule() );
		sb.append( "\n" );
		for( Object child : node._children )
		{
			if( child instanceof ParseNode )
			{
				toParseTree( sb, indent + 1, (ParseNode) child );
			}
			else if( child instanceof org.antlr.runtime.Token )
			{
				org.antlr.runtime.Token token = (org.antlr.runtime.Token) child;
				if( token.getChannel() != token.HIDDEN_CHANNEL )
				{
					for( int x = 0; x < indent + 1; x++ )
					{
						sb.append( "  " );
					}
					sb.append( token.getType() );
					sb.append( ":" );
					sb.append( token.getText() );
					sb.append( "\n" );
				}
			}
		}
		for( int x = 0; x < indent; x++ )
		{
			sb.append( "  " );
		}
		sb.append( ")" );
		sb.append( "\n" );
	}

	public String toOriginalString()
	{
		StringBuilder sb = new StringBuilder();
		toString( sb, this, true );
		return sb.toString();
	}

	public String toParsedString()
	{
		StringBuilder sb = new StringBuilder();
		toString( sb, this, false );
		return sb.toString();
	}

	public static void toString( StringBuilder sb, ParseNode node, boolean hiddenToo )
	{
		for( Object child : node._children )
		{
			if( child instanceof ParseNode )
			{
				toString( sb, (ParseNode) child, hiddenToo );
			}
			else if( child instanceof org.antlr.runtime.Token )
			{
				org.antlr.runtime.Token token = (org.antlr.runtime.Token) child;
				if( hiddenToo || token.getChannel() != token.HIDDEN_CHANNEL )
				{
					sb.append( token.getText() );
				}
			}
		}
	}

	public String getRule()
	{
		return _ruleName;
	}

	public java.util.List<Object> find( String expression )
	{
		return find( expression, false );
	}

	public java.util.List<ParseNode> findNodes( String expression )
	{
		java.util.List<Object> list = find( expression );
		java.util.ArrayList<ParseNode> result = new java.util.ArrayList<ParseNode>();
		for( Object ugh : list )
		{
			if( ugh instanceof ParseNode )
			{
				result.add( (ParseNode) ugh );
			}
		}
		return result;
	}

	// TODO: validate expression
	protected java.util.List<Object> find( String expression, boolean first )
	{
		if( expression == null )
		{
			throw new NullPointerException( "expression" );
		}

		java.util.ArrayList<String> query = new java.util.ArrayList<String>();
		for( String atom : expression.split( "/" ))
		{
			atom = atom.trim();
			if( atom.length() == 0 )
			{
				throw new IllegalArgumentException( query + " contains empty match string" );
			}
			query.add( atom );
		}

		java.util.ArrayList<Object> result = new java.util.ArrayList<Object>();
		find( first, this, query, 0, false, result );
		return result;
	}

	private static void find( boolean first, ParseNode parent, java.util.ArrayList<String> query, int nth, boolean seeking, java.util.ArrayList<Object> result )
	{
		String spot = query.get( nth );
		if( "**".equals( spot ))
		{
			find( first, parent, query, nth + 1, true, result );
		}
		for( Object child : parent._children )
		{
			if( child instanceof org.antlr.runtime.Token && Character.isUpperCase( spot.charAt( 0 )) )
			{
				org.antlr.runtime.Token token = (org.antlr.runtime.Token) child;
				int type = getLexType( spot );
				if( token.getType() == type )
				{
					result.add( token );
				}
			}
			else if( child instanceof ParseNode && Character.isLowerCase( spot.charAt( 0 )) )
			{
				ParseNode childNode = (ParseNode) child;
				if( "*".equals( spot ) || childNode.getRule().equals( spot ))
				{
					if( nth + 1 < query.size() )
					{
						find( first, childNode, query, nth + 1, false, result );
					}
					else
					{
						result.add( childNode );
					}
				}
//				else
				else if( seeking )
				{
					find( first, childNode, query, nth, true, result );
				}
			}

			if( first && result.size() > 0 ) break;
		}
	}

	public Object findFirst( String expression )
	{
		Object result = null;
		java.util.List<Object> found = find( expression, true );
		if( found.size() > 0 )
		{
			result = found.get( 0 );
		}
		return result;
	}

	public ParseNode findFirstNode( String expression )
	{
		ParseNode result = null;
		Object first = findFirst( expression );
		if( first instanceof ParseNode )
		{
			result = (ParseNode) first;
		}
		return result;
	}

	public String findFirstString( String expression )
	{
		String result = null;
		Object first = findFirst( expression );
		if( first instanceof org.antlr.runtime.Token )
		{
			String ugh = first.toString();
			result = ((org.antlr.runtime.Token) first).getText();
		}
		else if( first instanceof ParseNode )
		{
			result = ((ParseNode) first).toParsedString();
		}
		return result;
	}

}