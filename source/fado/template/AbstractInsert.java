package fado.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class 
	AbstractInsert
{
	private Connection _connection = null;
	private PreparedStatement _prepared = null;
	private ResultSet _resultSet = null;
	
	public AbstractInsert( Connection connection )
		throws Exception
	{
		_connection = connection;
	}
	
	public abstract String getSQL();
	
	public abstract void setParameters( PreparedStatement prepared )
		throws SQLException;

	protected final PreparedStatement getPreparedStatement()
		throws SQLException
	{
		if( _prepared == null )
		{
			String sql = getSQL();
			_prepared = _connection.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS );
	
		}
		return _prepared;
	}


	protected boolean _executed = false;

	// TODO: Auto detect if row has key/identity column
	
	public final boolean execute()
		throws SQLException
	{
		_executed = true;
		PreparedStatement statement = getPreparedStatement();
		setParameters( statement );

		boolean success = statement.executeUpdate() > 0;
		if( success )
		{
			ResultSet keys = statement.getGeneratedKeys();
			keys.next();
			_identity = keys.getInt(1);
		}
		
		return success;
	}

	private long _identity = 0L;

	public long getIdentity()
	{
		return _identity;
	}
	

}
