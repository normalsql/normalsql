
/**
* TopSalesResultSet.java Sat Aug 19 10:42:23 PDT 2023
*
* Generated using NormalSQL's Select.vm template.
*
* Original SQL:
*
* <pre>
* {@code
* WITH topsales AS (
*     SELECT
*         salesRepEmployeeNumber employeeNumber,
*         SUM(quantityOrdered * priceEach) sales
*     FROM
*         orders
*             INNER JOIN
*         orderdetails USING (orderNumber)
*             INNER JOIN
*         customers USING (customerNumber)
*     WHERE
*             YEAR(shippedDate) = 2003
*       AND status = 'Shipped'
*     GROUP BY salesRepEmployeeNumber
*     ORDER BY sales DESC
*     LIMIT 5
* )
* SELECT
*     a.employeeNumber,
*     a.firstName,
*     a.lastName,
*     b.sales
* FROM
*     employees a
*         JOIN
*     topsales b USING (employeeNumber);
* }
* </pre>
*
* @see /Users/jasonosgood/Projects/normalsql/doc/classicmodels/TopSales.sql
*
**/


import java.sql.*;
import javax.sql.DataSource;
import java.math.BigDecimal;

public class
	TopSales
{
	private PreparedStatement __ps = null;

    	public final String __preparedSQL =
		"WITH topsales AS (\n"+ 
		"    SELECT\n"+ 
		"        salesRepEmployeeNumber employeeNumber,\n"+ 
		"        SUM(quantityOrdered * priceEach) sales\n"+ 
		"    FROM\n"+ 
		"        orders\n"+ 
		"            INNER JOIN\n"+ 
		"        orderdetails USING (orderNumber)\n"+ 
		"            INNER JOIN\n"+ 
		"        customers USING (customerNumber)\n"+ 
		"    WHERE\n"+ 
		"            YEAR(shippedDate) = 2003\n"+ 
		"      AND status = ?\n"+ 
		"    GROUP BY salesRepEmployeeNumber\n"+ 
		"    ORDER BY sales DESC\n"+ 
		"    LIMIT 5\n"+ 
		")\n"+ 
		"SELECT\n"+ 
		"    a.employeeNumber,\n"+ 
		"    a.firstName,\n"+ 
		"    a.lastName,\n"+ 
		"    b.sales\n"+ 
		"FROM\n"+ 
		"    employees a\n"+ 
		"        JOIN\n"+ 
		"    topsales b USING (employeeNumber);"; 

    	public final String __printfSQL =
		"WITH topsales AS (\n"+ 
		"    SELECT\n"+ 
		"        salesRepEmployeeNumber employeeNumber,\n"+ 
		"        SUM(quantityOrdered * priceEach) sales\n"+ 
		"    FROM\n"+ 
		"        orders\n"+ 
		"            INNER JOIN\n"+ 
		"        orderdetails USING (orderNumber)\n"+ 
		"            INNER JOIN\n"+ 
		"        customers USING (customerNumber)\n"+ 
		"    WHERE\n"+ 
		"            YEAR(shippedDate) = 2003\n"+ 
		"      AND status = '%s'\n"+ 
		"    GROUP BY salesRepEmployeeNumber\n"+ 
		"    ORDER BY sales DESC\n"+ 
		"    LIMIT 5\n"+ 
		")\n"+ 
		"SELECT\n"+ 
		"    a.employeeNumber,\n"+ 
		"    a.firstName,\n"+ 
		"    a.lastName,\n"+ 
		"    b.sales\n"+ 
		"FROM\n"+ 
		"    employees a\n"+ 
		"        JOIN\n"+ 
		"    topsales b USING (employeeNumber);"; 

	/**
		Pass a Connection. You are responsible for closing that Connection.
		TopSales is a wrapper for a PreparedStatement, not a Connection.
	**/
	public TopSales( Connection connection, int resultSetType, int resultSetConcurrency )
		throws SQLException
	{
		__ps = connection.prepareStatement( __preparedSQL, resultSetType, resultSetConcurrency );
	}

	public TopSales( Connection connection )
		throws SQLException
	{
		this( connection, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );
	}

	/**
		Pass a DataSource. You are responsible for closing that DataSource.
		TopSales is a wrapper for a PreparedStatement, not a Connection.
	**/
	public TopSales( DataSource dataSource, int resultSetType, int resultSetConcurrency )
		throws SQLException
	{
		this( dataSource.getConnection(), resultSetType, resultSetConcurrency );
	}

	public TopSales( DataSource dataSource )
		throws SQLException
	{
		this( dataSource, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );
	}

	public final TopSalesResultSet execute()
		throws SQLException
	{
        __ps.setString( 1, getStatus() );

        ResultSet rs = null;
        if( __ps.execute() )
        {
            rs = __ps.getResultSet();
        }
		TopSalesResultSet result = new TopSalesResultSet( rs );
        return result;
    }

	// TODO add null check to setters (when !isnullable)
    private String _status = "Shipped";
    public void setStatus( String status ) { _status = status; }
    public String getStatus() { return _status; }


	public String toString()
	{
		return String.format( __printfSQL
            , getStatus()
		);
	}
}