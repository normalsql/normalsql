package fado.parse;

import java.util.Locale;

import org.apache.commons.jxpath.ri.QName;
import org.apache.commons.jxpath.ri.model.NodePointer;
import org.apache.commons.jxpath.ri.model.NodePointerFactory;



public class 
	ParseNodePointerFactory
implements
	NodePointerFactory
{

	@Override
	public NodePointer createNodePointer( QName name, Object obj, Locale locale ) 
	{
		NodePointer result = null;
		if( obj instanceof FadoParseNode )
		{
			FadoParseNode tree = (FadoParseNode) obj;
			result = new ParseNodePointer( tree );
		}
		return result;
	}

	@Override
	public NodePointer createNodePointer( NodePointer parent, QName name, Object obj ) 
	{
		NodePointer result = null;
		if( obj instanceof FadoParseNode )
		{
			FadoParseNode tree = (FadoParseNode) obj;
			result = new ParseNodePointer( parent, tree );
		}
		return result;
	}

	@Override
	public int getOrder() 
	{
		return -100;
	}

}
