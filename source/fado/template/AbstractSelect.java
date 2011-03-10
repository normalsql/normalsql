package fado.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class 
	AbstractSelect
{
	protected Connection _connection = null;
	protected PreparedStatement _prepared = null;
	protected ResultSet _resultSet = null;
	
	public AbstractSelect( Connection connection )
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
			_prepared = _connection.prepareStatement( sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

		}
		return _prepared;
	}

	protected boolean _executed = false;
	public final boolean execute()
		throws SQLException
	{
		_executed = true;
		PreparedStatement statement = getPreparedStatement();
		setParameters( statement );
		boolean success = statement.execute();
		if( success )
		{
			_resultSet = statement.getResultSet();
		}
		return success;
	}

	public final ResultSet getResultSet()
	{
		return _resultSet;
	}
	
	public final boolean next()
		throws SQLException
	{
		if( !_executed )
		{
			execute();
		}
		
		boolean success = false;
		if( _resultSet != null )
		{
			success = _resultSet.next();
			updateValues( success );

		}
		return success;
	}
	
	public final boolean nextNoFail()
	{
		try
		{
			return next();
		}
		catch( SQLException e )
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public abstract void updateValues( boolean success ) throws SQLException;

	public int count()
	{
		int result = 0;
		try
		{
			if( !_executed )
			{
				execute();
			}
			ResultSet rs = getResultSet();
			int current = rs.getRow();
			if( rs.last() )
			{
				result = rs.getRow();
				if( current == 0 )
				{
					rs.beforeFirst();
				}
				else
				{
					rs.absolute( current );
				}
			}
				
		}
		catch( SQLException e )
		{
			e.printStackTrace();
		}
		return result;
	}
	

}
