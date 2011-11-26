package fado.meta;

public abstract class 
	Condition 
{
	public Condition( Table table, String column )
	{
		_table = table;
		_column = column;
	}
	
	private Table _table = null;
	public final Table getTable() { return _table; }
	public boolean hasTable() { return _table != null; }
	
	private String _column = null;
	public final String getColumn() { return _column; } 
	
	public String getNameAsMethod()
	{
		return Util.toMethodName( getColumn() );
	}
	
	public String getNameAsVariable()
	{
		return Util.toVariableName( getColumn() );
	}
	
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
