package fado.meta;

/**
 * Class Field represents a field column name and value "setter" pair found within INSERT
 * and UPDATE queries.
 *  
 * @author jasonosgood
 *
 */
public class 
	Field
extends
	MetaField
{
	public Field( String name )
	{
		super( name );
	}

	public Field( String name, String literal )
	{
		super( name );
		_literal = literal;
	}

	public String getInitializerValue()
	{
		int sqlType = getSQLType();
		String literal = getLiteral();
		String result = TypeConverter.getValueAsCode( sqlType, literal );
		return result;
	}
	
	private String _literal;
	public void setLiteral( String literal ) { _literal = literal; }
	public String getLiteral() { return _literal; }
	
	@Override
	public String toString()
	{
		return "Field [name=" + getName() + ", literal=" + _literal + ", nullable=" + isNullable()
				+ ", sqlType=" + getSQLType() + ", sqlTypeName=" + getSQLTypeName() + "]";
	}

}
