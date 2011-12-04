package fado.meta;


public class 
	LIKE 
extends 
	Condition 
{
	public LIKE( Table table, String column )
	{
		super( table, column );
	}
	
	String _pattern = null;
	public void setPattern( String pattern )
	{
		_pattern = pattern;
	}
	public String getPattern()
	{
		return _pattern;
	}
	
	public String getValueAsCode()
	{
		String result = TypeConverter.getValueAsCode( getSQLType(), getPattern() );
		return result;
	}
	
	
	public boolean isLIKE() { return true; }
}
