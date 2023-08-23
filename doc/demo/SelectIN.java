
/**
* SelectINResultSet.java Sun Aug 20 10:04:19 PDT 2023
*
* Generated using NormalSQL's Select.vm template.
*
* Original SQL:
*
* <pre>
* {@code
* SELECT *
* FROM VALUES (1, 'apple'), (2, 'banana'), (3, 'cherry'), (4, 'daikon') t("count", "fruit")
* WHERE "count" IN ( 1, 4 );
* }
* </pre>
*
* @see /Users/jasonosgood/Projects/normalsql/doc/demo/SelectIN.sql
*
**/


import java.sql.*;
import javax.sql.DataSource;
import java.math.BigDecimal;

public class
	SelectIN
{
	private PreparedStatement __ps = null;

    	public final String __preparedSQL =
		"SELECT *\n"+ 
		"FROM VALUES (1, 'apple'), (2, 'banana'), (3, 'cherry'), (4, 'daikon') t("count", "fruit")\n"+ 
		"WHERE "count" IN ( 1, 4 );"; 

    	public final String __printfSQL =
		"SELECT *\n"+ 
		"FROM VALUES (1, 'apple'), (2, 'banana'), (3, 'cherry'), (4, 'daikon') t("count", "fruit")\n"+ 
		"WHERE "count" IN ( 1, 4 );"; 

	/**
		Pass a Connection. You are responsible for closing that Connection.
		SelectIN is a wrapper for a PreparedStatement, not a Connection.
	**/
	public SelectIN( Connection connection, int resultSetType, int resultSetConcurrency )
		throws SQLException
	{
		__ps = connection.prepareStatement( __preparedSQL, resultSetType, resultSetConcurrency );
	}

	public SelectIN( Connection connection )
		throws SQLException
	{
		this( connection, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );
	}

	/**
		Pass a DataSource. You are responsible for closing that DataSource.
		SelectIN is a wrapper for a PreparedStatement, not a Connection.
	**/
	public SelectIN( DataSource dataSource, int resultSetType, int resultSetConcurrency )
		throws SQLException
	{
		this( dataSource.getConnection(), resultSetType, resultSetConcurrency );
	}

	public SelectIN( DataSource dataSource )
		throws SQLException
	{
		this( dataSource, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );
	}

	public final SelectINResultSet execute()
		throws SQLException
	{

        ResultSet rs = null;
        if( __ps.execute() )
        {
            rs = __ps.getResultSet();
        }
		SelectINResultSet result = new SelectINResultSet( rs );
        return result;
    }

	// TODO add null check to setters (when !isnullable)

	public String toString()
	{
		return String.format( __printfSQL
		);
	}
}