
/**
* SelectLIKEResultSet.java Sun Aug 20 10:04:29 PDT 2023
*
* Generated using NormalSQL's Select.vm template.
*
* Original SQL:
*
* <pre>
* {@code
* SELECT *
* FROM VALUES (1, 'apple pie'), (2, 'apple fritter'), (3, 'cherry pie'), (4, 'cookies') t("id", "treat")
* WHERE "treat" LIKE '%pie';
* }
* </pre>
*
* @see /Users/jasonosgood/Projects/normalsql/doc/demo/SelectLIKE.sql
*
**/


import java.sql.*;
import javax.sql.DataSource;
import java.math.BigDecimal;

public class
	SelectLIKE
{
	private PreparedStatement __ps = null;

    	public final String __preparedSQL =
		"SELECT *\n"+ 
		"FROM VALUES (1, 'apple pie'), (2, 'apple fritter'), (3, 'cherry pie'), (4, 'cookies') t("id", "treat")\n"+ 
		"WHERE "treat" LIKE '%pie';"; 

    	public final String __printfSQL =
		"SELECT *\n"+ 
		"FROM VALUES (1, 'apple pie'), (2, 'apple fritter'), (3, 'cherry pie'), (4, 'cookies') t("id", "treat")\n"+ 
		"WHERE "treat" LIKE '%pie';"; 

	/**
		Pass a Connection. You are responsible for closing that Connection.
		SelectLIKE is a wrapper for a PreparedStatement, not a Connection.
	**/
	public SelectLIKE( Connection connection, int resultSetType, int resultSetConcurrency )
		throws SQLException
	{
		__ps = connection.prepareStatement( __preparedSQL, resultSetType, resultSetConcurrency );
	}

	public SelectLIKE( Connection connection )
		throws SQLException
	{
		this( connection, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );
	}

	/**
		Pass a DataSource. You are responsible for closing that DataSource.
		SelectLIKE is a wrapper for a PreparedStatement, not a Connection.
	**/
	public SelectLIKE( DataSource dataSource, int resultSetType, int resultSetConcurrency )
		throws SQLException
	{
		this( dataSource.getConnection(), resultSetType, resultSetConcurrency );
	}

	public SelectLIKE( DataSource dataSource )
		throws SQLException
	{
		this( dataSource, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );
	}

	public final SelectLIKEResultSet execute()
		throws SQLException
	{

        ResultSet rs = null;
        if( __ps.execute() )
        {
            rs = __ps.getResultSet();
        }
		SelectLIKEResultSet result = new SelectLIKEResultSet( rs );
        return result;
    }

	// TODO add null check to setters (when !isnullable)

	public String toString()
	{
		return String.format( __printfSQL
		);
	}
}