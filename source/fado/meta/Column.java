package fado.meta;


public class 
	Column
{
	public enum Style 
	{ 
		WholeTable, // SELECT table.* FROM ...
		Column,   // SELECT column FROM ...
		Equals,   // SELECT alias = 1 + 2 FROM ...
		Alias     // SELECT column AS alias FROM ... 
	}
	
	public Column( Table table )
	{
		_style = Style.WholeTable;
		_table = table;
		_name = "*";
	}

	public Column( Table table, String name, String alias )
	{
		_style = Style.Column;
		_table = table;
		_name = name;
		_alias = alias;
	}

	public Column( String name, int sqlType, String sqlTypeName )
	{
		_name = name;
		_sqlType = sqlType;
		_sqlTypeName = sqlTypeName;
	}

	private Style _style;
	public Style getStyle() { return _style; }
	
	private Table _table;
	public void setTable( Table table ) { _table = table; }
	public Table getTable() { return _table; }
	public boolean hasTable() { return _table != null; }
	
	private String _name;
	public String getName() { return _name; }
	
	private String _alias;
	public String getAlias() 
	{ 
		return _alias != null ? getAlias() : getName();
	}
	
	public String getNameAsMethod()
	{
		return Util.toMethodName( getName() );
	}
	
	public String getNameAsVariable()
	{
		return Util.toVariableName( getName() );
	}
	
	private int _sqlType;
	
	public int getSQLType()
	{
		return _sqlType;
	}
	
	public void setSQLType( int sqlType )
	{
		_sqlType = sqlType;
	}
	
	private String _sqlTypeName;
	
	public String getSQLTypeName()
	{
		return _sqlTypeName;
	}
	
	public void setSQLTypeName( String sqlTypeName )
	{
		_sqlTypeName = sqlTypeName;
	}
	
	public String getJavaType()
	{
		return TypeConverter.getJavaType( _sqlType );
	}

	public String getTypedMethod()
	{
		String result = TypeConverter.getTypedMethod( getSQLType() );
		return result;
	}
	
	public String getInitializerValue()
	{
		String result = TypeConverter.getInitializerValue( getSQLType() );
		return result;
		
	}

	@Override
	public String toString()
	{
		return "Column [_style=" + _style + ", _table=" + _table + ", _name=" + _name + ", _alias=" + _alias
				+ ", _sqlType=" + _sqlType + ", _sqlTypeName=" + _sqlTypeName + "]";
	}
	
	
}
