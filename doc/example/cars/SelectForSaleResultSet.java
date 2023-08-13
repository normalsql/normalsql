
/**
* SelectForSaleResultSet.java Tue Aug 15 10:19:31 PDT 2023
*
* Generated using NormalSQL's ResultSet.vm template.
*
* @see /Users/jasonosgood/Projects/normalsql/doc/example/SelectForSale.sql
*
**/


import java.sql.*;
import java.math.BigDecimal;

public class 
	SelectForSaleResultSet
{
	private ResultSet __resultSet = null;

	public SelectForSaleResultSet( ResultSet resultSet )
	{
		__resultSet = resultSet;
	}

	public final ResultSet getResultSet()
	{
		return __resultSet;
	}

	public boolean getSuccess()
	{
		return __resultSet != null;
	}

	/**
		Closes the contained ResultSet instance. Also closes contained Connection instance
		created via a DataSource connection factory.
	**/
	public void close()
		throws SQLException
	{
		if( __resultSet != null )
		{
			__resultSet.close();
			__resultSet = null;
		}
		Connection connection = getConnection();
		if( connection != null )
		{
			connection.close();
		}
	}

	private Connection __connection = null;

	public void setConnection( Connection connection )
	{
		__connection = connection;
	}

	public Connection getConnection()
	{
		return __connection;
	}

	public final boolean hasNext()
		throws SQLException
	{
		ResultSet rs = getResultSet();
		if( rs == null ) return false;
		boolean	success = rs.next();
		if( success )
		{
			_id = rs.getInteger( 1 );

			_make = rs.getString( 2 );

			_model = rs.getString( 3 );

			_year = rs.getInteger( 4 );

		}
		return success;
	}

	/**
     *  Attempt to count this ResultSet's number of rows.
     *  @return number of rows. Or -1 if ResultSet's scroll type is TYPE_FORWARD_ONLY
     *  or if any exception occurs.
     */
	public int countRows()
	{
		int result = -1;
		try
		{
			ResultSet rs = getResultSet();
			if( rs.getType() != ResultSet.TYPE_FORWARD_ONLY )
			{
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
		}
		catch( SQLException e )
		{
			e.printStackTrace();
		}
		return result;
	}

	private Integer _id = 0;
	/** column "${p.source}" **/
	public Integer getID() { return _id; }

	private String _make = null;
	/** column "${p.source}" **/
	public String getMake() { return _make; }

	private String _model = null;
	/** column "${p.source}" **/
	public String getModel() { return _model; }

	private Integer _year = 0;
	/** column "${p.source}" **/
	public Integer getYear() { return _year; }


	// TODO switch from StringBuilder to printf
	public String toString()
	{
		if( getResultSet() == null )
		{
			return "No results";
		}
		StringBuilder sb = new StringBuilder();
		sb.append( "#row: " );
		sb.append( getRow() );
		sb.append( ", \n" );
		sb.append( "id: " );
		sb.append( getID() );
		sb.append( ", \n" );
		sb.append( "make: " );
		sb.append( getMake() );
		sb.append( ", \n" );
		sb.append( "model: " );
		sb.append( getModel() );
		sb.append( ", \n" );
		sb.append( "year: " );
		sb.append( getYear() );
		sb.append( ", \n" );
		return sb.toString();
	}
}
