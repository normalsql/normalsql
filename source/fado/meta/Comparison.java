package fado.meta;

public class 
	Comparison
extends
	Condition
{
	public Comparison( Table table, String accessor, String value )
	{
		super( table, accessor );
		_value = value;
	}
	
	String _value = null;
	public void setValue( String value )
	{
		_value = value;
	}
	public String getValue()
	{
		return _value;
	}
	
	public String getValueAsCode()
	{
		String result = TypeConverter.getValueAsCode( getSQLType(), getValue() );
		return result;
	}
	
	public boolean isComparison() { return true; }
}
