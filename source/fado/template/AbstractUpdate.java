package fado.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class 
	AbstractUpdate
{
	private Connection _connection = null;
	private PreparedStatement _prepared = null;
	
	public AbstractUpdate( Connection connection )
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
		}
		return _prepared;
	}

	public final int execute()
		throws SQLException
	{
		PreparedStatement statement = getPreparedStatement();
		setParameters( statement );
		int rows = statement.executeUpdate();
		return rows;
	}
}
