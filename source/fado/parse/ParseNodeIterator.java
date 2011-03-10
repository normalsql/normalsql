package fado.parse;

import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.Token;
import org.apache.commons.jxpath.ri.Compiler;
import org.apache.commons.jxpath.ri.compiler.NodeNameTest;
import org.apache.commons.jxpath.ri.compiler.NodeTest;
import org.apache.commons.jxpath.ri.compiler.NodeTypeTest;
import org.apache.commons.jxpath.ri.model.NodeIterator;
import org.apache.commons.jxpath.ri.model.NodePointer;
import org.bouncycastle.asn1.tsp.TSTInfo;
import org.w3c.dom.Node;


public class 
	ParseNodeIterator 
implements 
	NodeIterator 
{
	private ParseNodePointer _pointer;
	private ParseNodePointer _start;
	private boolean _reverse = false;
	private int _position = 0;
	
	private ArrayList<Object> _children = new ArrayList<Object>();
	
	public ParseNodeIterator( NodePointer pointer, NodeTest test, boolean reverse, NodePointer start )
	{
		_pointer = (ParseNodePointer) pointer;
		_reverse = reverse;
		_start = (ParseNodePointer) start;
		
		FadoParseNode parent = (FadoParseNode) _pointer.getNode();
		if( parent.getChildCount() == 0 ) return;

		if( test instanceof NodeTypeTest && ((NodeTypeTest) test).getNodeType() == Compiler.NODE_TYPE_TEXT )
		{
			// TODO: Need method that excluded channel = hidden tokens (e.g. comments)
			String text = parent.toInputString().trim(); 
			FadoParseNode spare = new FadoParseNode( text );
			_children.add( text );
			return;
		}
		
		
		for( Object obj : parent.getChildren() )
		{
			FadoParseNode node = (FadoParseNode) obj;

			if( node.getPayload() instanceof Token )
			{
				continue;
			}
			
			if( test == null )
			{
				_children.add( node );
			}
			else if( test instanceof NodeNameTest )
			{
				NodeNameTest nameTest = (NodeNameTest) test;
				
				if( nameTest.toString().equals( node.toString() ))
				{
					_children.add( node );
				}
			}
			else if( test instanceof NodeTypeTest )
			{
				NodeTypeTest typeTest = (NodeTypeTest) test;
	            switch( typeTest.getNodeType() ) 
	            {
	                case Compiler.NODE_TYPE_NODE :
	                	_children.add( node );
	                	break;
	                case Compiler.NODE_TYPE_TEXT :
	                case Compiler.NODE_TYPE_COMMENT :
	                case Compiler.NODE_TYPE_PI :
	                default:
	                	break;
	            }
			}
		}
			
	}
	
	@Override
	public NodePointer getNodePointer() 
	{
		int actual = _position - 1;
		actual = Math.max( 0, actual );
		if( _reverse )
		{
			actual = _children.size() - actual;
		}
		
//		FadoParseNode child = _children.get( actual );
		Object child = _children.get( actual );
		ParseNodePointer pointer = null;
		if( child instanceof FadoParseNode )
		{
			pointer = new ParseNodePointer( _pointer, (FadoParseNode) child );
		}
		if( child instanceof String )
		{
			pointer = new ParseNodePointer( _pointer, (String) child );
		}
		return pointer;
	}
	
	@Override
	public int getPosition() {
		return _position;
	}

	@Override
	public boolean setPosition( int position ) 
	{
		boolean result = false;
	
		if( position > 0 && position <= _children.size() )
		{
			result = true;
			_position = position;
		}
		return result;
	}

}
