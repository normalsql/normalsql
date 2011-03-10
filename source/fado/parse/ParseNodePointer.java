package fado.parse;

import org.apache.commons.jxpath.ri.QName;
import org.apache.commons.jxpath.ri.compiler.NodeTest;
import org.apache.commons.jxpath.ri.model.NodeIterator;
import org.apache.commons.jxpath.ri.model.NodePointer;


public class 
	ParseNodePointer 
extends 
	NodePointer 
{
	private static final long serialVersionUID = 1L;
	
	private FadoParseNode _tree = null;
	private String _text = null;

	protected ParseNodePointer( NodePointer parent, FadoParseNode tree ) 
	{
		super( parent );
		if( tree == null )
		{
			throw new NullPointerException( "tree" );
		}
		_tree = tree;
	}

	protected ParseNodePointer( NodePointer parent, String text ) 
	{
		super( parent );
		if( text == null )
		{
			throw new NullPointerException( "text" );
		}
		_text = text;
	}

	protected ParseNodePointer( FadoParseNode tree ) 
	{
		this( null, tree );
	}


	/**
	 Have no idea how this is used. Just comparing the start indexes of tokens.
	 */
	@Override
	public int compareChildNodePointers( NodePointer a, NodePointer b ) 
	{
		int result = 0;
		FadoParseNode left = (FadoParseNode) a.getBaseValue();
		FadoParseNode right = (FadoParseNode) b.getBaseValue();
		if( left != right )
		{
			result = left.getTokenStartIndex() - right.getTokenStartIndex();
		}
		return result;
	}

	@Override
	public Object getBaseValue() 
	{
		Object result = null;
		if( _tree != null )
		{
			result = _tree;
		}
		if( _text != null )
		{
			result = _text;
		}
		return result;
	}

	@Override
	public Object getImmediateNode() 
	{
		return getBaseValue();
//		return _tree;
	}

	@Override
	public int getLength() 
	{
		return 1;
	}

	@Override
	public QName getName() 
	{
		String text = _tree.getText();
		QName name = new QName( text );
		return name;
	}

	@Override
	public boolean isCollection() 
	{
		return !isLeaf();
	}

	@Override
	public boolean isLeaf() 
	{
		return _tree.isLeaf();
	}

	@Override
	public void setValue( Object arg0 ) 
	{
		throw new IllegalArgumentException( " not implemented " );
		
	}
	
    public NodeIterator childIterator( NodeTest test, boolean reverse, NodePointer startWith ) 
    {
        return new ParseNodeIterator( this, test, reverse, startWith );
    }

    public NodeIterator attributeIterator(QName name) {
        return null;
    }

    public NodePointer namespacePointer(String prefix) {
        return null;
    }

    public NodeIterator namespaceIterator() {
        return null;
    }

}
