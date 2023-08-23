
/**
* TopSalesResultSet.java Sat Aug 19 10:42:23 PDT 2023
*
* Generated using NormalSQL's ResultSet.vm template.
*
* @see /Users/jasonosgood/Projects/normalsql/doc/classicmodels/TopSales.sql
*
**/


import java.sql.*;
import java.math.BigDecimal;

public class 
	TopSalesResultSet
{
	private ResultSet __resultSet = null;

	public TopSalesResultSet( ResultSet resultSet )
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
			_EMPLOYEENUMBER = rs.getInteger( 1 );

			_FIRSTNAME = rs.getString( 2 );

			_LASTNAME = rs.getString( 3 );

			_SALES = rs.getBigDecimal( 4 );

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

	private Integer _EMPLOYEENUMBER = 0;
	/** column "${p.source}" **/
	public Integer getEMPLOYEENUMBER() { return _EMPLOYEENUMBER; }

	private String _FIRSTNAME = null;
	/** column "${p.source}" **/
	public String getFIRSTNAME() { return _FIRSTNAME; }

	private String _LASTNAME = null;
	/** column "${p.source}" **/
	public String getLASTNAME() { return _LASTNAME; }

	private BigDecimal _SALES = null;
	/** column "${p.source}" **/
	public BigDecimal getSALES() { return _SALES; }


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
		sb.append( "EMPLOYEENUMBER: " );
		sb.append( getEMPLOYEENUMBER() );
		sb.append( ", \n" );
		sb.append( "FIRSTNAME: " );
		sb.append( getFIRSTNAME() );
		sb.append( ", \n" );
		sb.append( "LASTNAME: " );
		sb.append( getLASTNAME() );
		sb.append( ", \n" );
		sb.append( "SALES: " );
		sb.append( getSALES() );
		sb.append( ", \n" );
		return sb.toString();
	}
}
