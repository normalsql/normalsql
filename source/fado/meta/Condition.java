package fado.meta;

public abstract class 
	Condition 
extends
	MetaField
{
	public Condition( Table table, String name )
	{
		super( name );
		_table = table;
	}
	
	private Table _table = null;
	public final Table getTable() { return _table; }
	public final boolean hasTable() { return _table != null; }
	
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
	
	@Override
	public String toString()
	{
		return "Condition [table=" + _table + 
				", field=" + getName() + 
				", nullable=" + isNullable()
				+ ", sqlType=" + getSQLType() + "]";
	}

}
