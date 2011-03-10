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
	
//	protected final PreparedStatement getPreparedStatement()
//		throws SQLException
//	{
//		if( _prepared == null )
//		{
//			String sql = getSQL();
//			_prepared = _connection.prepareStatement( sql, PreparedStatement.RETURN_GENERATED_KEYS );
//
//			Statement ha = _connection.createStatement();
//			ha.execute(sql, )
//		}
//		return _prepared;
//	}

	public final boolean execute()
		throws SQLException
	{
		String sql = getSQL();
		Statement ha = _connection.createStatement();
		int rows = ha.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS );
		
//		if( rows > 0 )
		{
//			success = true;
			_resultSet = ha.getGeneratedKeys();
//			ha.
			if( _resultSet != null )
			{
				if( _resultSet.next() )
				{
					_identity = _resultSet.getInt( 1 );
				}
			}
		}
		boolean success = false;
		return success;
	}

	private long _identity = 0L;

	public long getIdentity()
	{
		return _identity;
	}
	

}
