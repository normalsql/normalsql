package fado.meta;

public class 
	UpdateColumn
{
	public UpdateColumn( String name, String literal )
	{
		_name = name;
		_literal = literal;
	}

	private String _name;
	
	public String getName() { return _name; }
	
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
		int sqlType = getSQLType();
		String literal = getLiteral();
		String result = TypeConverter.getValueAsCode( sqlType, literal );
		return result;
	}
	
	private String _literal;
	
	public void setLiteral( String literal )
	{
		_literal = literal;
	}
	
	public String getLiteral()
	{
		return _literal;
	}
}
