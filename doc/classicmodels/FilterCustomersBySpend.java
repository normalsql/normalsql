
/**
* FilterCustomersBySpendResultSet.java Sat Aug 19 10:42:24 PDT 2023
*
* Generated using NormalSQL's Select.vm template.
*
* Original SQL:
*
* <pre>
* {@code
* SELECT
*     c.customerNumber, customerName, SUM(priceEach * quantityOrdered) spend
* FROM
*     orderdetails a
*         JOIN
*     orders b ON a.orderNumber = b.orderNumber
*          JOIN
*    customers c ON b.customerNumber = c.customerNumber
* WHERE c.customerNumber = 1
* GROUP BY customerName
* HAVING spend > 60000
* ORDER BY spend DESC;
* }
* </pre>
*
* @see /Users/jasonosgood/Projects/normalsql/doc/classicmodels/FilterCustomersBySpend.sql
*
**/


import java.sql.*;
import javax.sql.DataSource;
import java.math.BigDecimal;

public class
	FilterCustomersBySpend
{
	private PreparedStatement __ps = null;

    	public final String __preparedSQL =
		"SELECT\n"+ 
		"    c.customerNumber, customerName, SUM(priceEach * quantityOrdered) spend\n"+ 
		"FROM\n"+ 
		"    orderdetails a\n"+ 
		"        JOIN\n"+ 
		"    orders b ON a.orderNumber = b.orderNumber\n"+ 
		"         JOIN\n"+ 
		"   customers c ON b.customerNumber = c.customerNumber\n"+ 
		"WHERE c.customerNumber = ?\n"+ 
		"GROUP BY customerName\n"+ 
		"HAVING spend > ?\n"+ 
		"ORDER BY spend DESC;"; 

    	public final String __printfSQL =
		"SELECT\n"+ 
		"    c.customerNumber, customerName, SUM(priceEach * quantityOrdered) spend\n"+ 
		"FROM\n"+ 
		"    orderdetails a\n"+ 
		"        JOIN\n"+ 
		"    orders b ON a.orderNumber = b.orderNumber\n"+ 
		"         JOIN\n"+ 
		"   customers c ON b.customerNumber = c.customerNumber\n"+ 
		"WHERE c.customerNumber = %d\n"+ 
		"GROUP BY customerName\n"+ 
		"HAVING spend > '%s'\n"+ 
		"ORDER BY spend DESC;"; 

	/**
		Pass a Connection. You are responsible for closing that Connection.
		FilterCustomersBySpend is a wrapper for a PreparedStatement, not a Connection.
	**/
	public FilterCustomersBySpend( Connection connection, int resultSetType, int resultSetConcurrency )
		throws SQLException
	{
		__ps = connection.prepareStatement( __preparedSQL, resultSetType, resultSetConcurrency );
	}

	public FilterCustomersBySpend( Connection connection )
		throws SQLException
	{
		this( connection, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );
	}

	/**
		Pass a DataSource. You are responsible for closing that DataSource.
		FilterCustomersBySpend is a wrapper for a PreparedStatement, not a Connection.
	**/
	public FilterCustomersBySpend( DataSource dataSource, int resultSetType, int resultSetConcurrency )
		throws SQLException
	{
		this( dataSource.getConnection(), resultSetType, resultSetConcurrency );
	}

	public FilterCustomersBySpend( DataSource dataSource )
		throws SQLException
	{
		this( dataSource, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );
	}

	public final FilterCustomersBySpendResultSet execute()
		throws SQLException
	{
        __ps.setInteger( 1, getC.customerNumber() );
        __ps.setString( 2, getSpend() );

        ResultSet rs = null;
        if( __ps.execute() )
        {
            rs = __ps.getResultSet();
        }
		FilterCustomersBySpendResultSet result = new FilterCustomersBySpendResultSet( rs );
        return result;
    }

	// TODO add null check to setters (when !isnullable)
    private Integer _c.customerNumber = 1;
    public void setC.customerNumber( Integer c.customerNumber ) { _c.customerNumber = c.customerNumber; }
    public Integer getC.customerNumber() { return _c.customerNumber; }

    private String _spend = "60000";
    public void setSpend( String spend ) { _spend = spend; }
    public String getSpend() { return _spend; }


	public String toString()
	{
		return String.format( __printfSQL
            , getC.customerNumber()
            , getSpend()
		);
	}
}