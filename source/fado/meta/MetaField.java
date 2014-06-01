package fado.meta;

/**
 * MetaField represents a table's field. Not semantically important. For code reuse.
 *  
 * @author jasonosgood
 *
 */
public abstract class 
	MetaField
{
	private String _name;
	public MetaField( String name ) { _name = name; }
	public final String getName() { return _name; }
	
	public final String getNameAsMethod()
	{
		return Util.toMethodName( getName() );
	}
	
	public final String getNameAsVariable()
	{
		return Util.toVariableName( getName() );
	}
	
	private int _sqlType = Integer.MIN_VALUE;
	public final void setSQLType( int sqlType ) { _sqlType = sqlType; }
	public final int getSQLType() { return _sqlType; }
	
	private String _sqlTypeName;
	public final void setSQLTypeName( String sqlTypeName ) { _sqlTypeName = sqlTypeName; }
	public final String getSQLTypeName() { return _sqlTypeName; }
	
	public final String getJavaType()
	{
		return TypeConverter.getJavaType( _sqlType );
	}

	public final String getTypedMethod()
	{
		String result = TypeConverter.getTypedMethod( getSQLType() );
		return result;
	}
	
	private boolean _nullable;
	public final void setNullable( boolean nullable ) { _nullable = nullable; }
	public final boolean isNullable() { return _nullable; }
	
}
