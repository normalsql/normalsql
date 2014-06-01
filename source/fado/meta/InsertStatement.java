package fado.meta;

import java.util.List;

public class 
	InsertStatement 
extends
	Statement
{
	private List<Field> _fields = null;
	
	public void setFields( List<Field> fields )
	{
		if( fields == null )
		{
			throw new NullPointerException( "fields" );
		}
		_fields = fields;
	}
	
	public List<Field> getFields()
	{
		return _fields;
	}
}
