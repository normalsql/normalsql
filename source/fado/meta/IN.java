package fado.meta;

import java.util.ArrayList;
import java.util.List;

import fado.FadoException;

public class 
	IN
extends
	Condition
{
	public IN( Table table, String column )
	{
		super( table, column );
	}
	
	private ArrayList<String> _values = new ArrayList<String>();
	public void addValue( String value )
		throws FadoException
	{
		_values.add( value );
	}
	
	public List<String> getValues()
	{
		return _values;
	}
	
	// Get all values rendered as Java code array init
	public String getValueAsCode()
	{
		StringBuilder sb = new StringBuilder();
		for( String value : getValues() )
		{
			String code = TypeConverter.getValueAsCode( getSQLType(), value );
			sb.append( code );
			sb.append( ", " );
		}
		return sb.toString();
	}
	
	public boolean isIN() { return true; }
}
