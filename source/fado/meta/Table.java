package fado.meta;

public class 
	Table
{
	private String _database;
	private String _name;
	private String _alias;
	
	public Table( String database, String name, String alias )
	{
		_database = database;
		_name = name;
		_alias = alias;
	}
	
	public String getDatabase() { return _database; }
	public String getName() { return _name; }
	public String getAlias() { return _alias; }
	
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		if( getDatabase() != null )
		{
			sb.append( getDatabase() );
			sb.append( "." );
		}
		sb.append( getName() );
		if( getAlias() != null )
		{
			sb.append( " AS " );
			sb.append( getAlias() );
		}
		return sb.toString();
	}
}
