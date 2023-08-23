
/**
* FilterCustomersBySpendResultSet.java Sat Aug 19 10:42:24 PDT 2023
*
* Generated using NormalSQL's ResultSet.vm template.
*
* @see /Users/jasonosgood/Projects/normalsql/doc/classicmodels/FilterCustomersBySpend.sql
*
**/


import java.sql.*;
import java.math.BigDecimal;

public class 
	FilterCustomersBySpendResultSet
{
	private ResultSet __resultSet = null;

	public FilterCustomersBySpendResultSet( ResultSet resultSet )
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
			_CUSTOMERNUMBER = rs.getInteger( 1 );

			_customerName = rs.getString( 2 );

			_SPEND = rs.getBigDecimal( 3 );

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

	private Integer _CUSTOMERNUMBER = 0;
	/** column "${p.source}" **/
	public Integer getCUSTOMERNUMBER() { return _CUSTOMERNUMBER; }

	private String _customerName = null;
	/** column "${p.source}" **/
	public String getCustomerName() { return _customerName; }

	private BigDecimal _SPEND = null;
	/** column "${p.source}" **/
	public BigDecimal getSPEND() { return _SPEND; }


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
		sb.append( "CUSTOMERNUMBER: " );
		sb.append( getCUSTOMERNUMBER() );
		sb.append( ", \n" );
		sb.append( "customerName: " );
		sb.append( getCustomerName() );
		sb.append( ", \n" );
		sb.append( "SPEND: " );
		sb.append( getSPEND() );
		sb.append( ", \n" );
		return sb.toString();
	}
}
