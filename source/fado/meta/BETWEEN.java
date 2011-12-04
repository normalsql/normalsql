package fado.meta;

public class 
	BETWEEN
extends
	Condition
{
	public BETWEEN( Table table, String column )
	{
		super( table, column );
	}
	
	String _left = null;
	public void setLeft( String left )
	{
		_left = left;
	}
	public String getLeft()
	{
		return _left;
	}
	
	String _right = null;
	public void setRight( String right )
	{
		_right = right;
	}
	public String getRight()
	{
		return _right;
	}
	
	public String getLeftAsCode()
	{
		String result = TypeConverter.getValueAsCode( getSQLType(), getLeft() );
		return result;
	}
	
	public String getRightAsCode()
	{
		String result = TypeConverter.getValueAsCode( getSQLType(), getRight() );
		return result;
	}
	
	public boolean isBETWEEN() { return true; }

}
