package fado.meta;

public abstract class 
	Condition 
{
	public Condition( Table table, String column, int sqlType )
	{
		_table = table;
		_column = column;
		_sqlType = sqlType;
	}
	
	public Condition( Table table, String column )
	{
		_table = table;
		_column = column;
	}
	
	private Table _table = null;
	public final Table getTable() { return _table; }
	
	private String _column = null;
	public final String getColumn() { return _column; } 
	
	private int _sqlType = Integer.MIN_VALUE;
	public final void setSQLType( int sqlType ) { _sqlType = sqlType; }
	public final int getSQLType() { return _sqlType; }
	
	public final String getJavaType()
	{
		return TypeConverter.getJavaType( getSQLType() );
	}
	
	public final String getTypedMethod()
	{
		String result = TypeConverter.getTypedMethod( getSQLType() );
		return result;
	}

	/** 
	 * These four are used by Velocity template, because I don't know how to do Java instanceof
	 * from within the template. 
	 * 
	 * @return
	 */
	public boolean isComparison() { return false; }
	public boolean isIN() { return false; }
	public boolean isBETWEEN() { return false; }
	public boolean isLIKE() { return false; }
}
