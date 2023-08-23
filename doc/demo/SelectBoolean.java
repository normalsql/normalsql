
/**
* SelectBooleanResultSet.java Sun Aug 20 22:51:53 PDT 2023
*
* Generated using NormalSQL's Select.vm template.
*
* Original SQL:
*
* <pre>
* {@code
* SELECT *
* FROM VALUES (1, TRUE), (2, FALSE), (3, NULL) t("id", "status")
* WHERE "status" = TRUE;
* }
* </pre>
*
* @see /Users/jasonosgood/Projects/normalsql/doc/demo/SelectBoolean.sql
*
**/


import java.sql.*;
import javax.sql.DataSource;
import java.math.BigDecimal;

public class
	SelectBoolean
{
	private PreparedStatement __ps = null;

    	public final String __preparedSQL =
		"SELECT *\n"+ 
		"FROM VALUES (1, TRUE), (2, FALSE), (3, NULL) t("id", "status")\n"+ 
		"WHERE "status" = ?;"; 

    	public final String __printfSQL =
		"SELECT *\n"+ 
		"FROM VALUES (1, TRUE), (2, FALSE), (3, NULL) t("id", "status")\n"+ 
		"WHERE "status" = %b;"; 

	/**
		Pass a Connection. You are responsible for closing that Connection.
		SelectBoolean is a wrapper for a PreparedStatement, not a Connection.
	**/
	public SelectBoolean( Connection connection, int resultSetType, int resultSetConcurrency )
		throws SQLException
	{
		__ps = connection.prepareStatement( __preparedSQL, resultSetType, resultSetConcurrency );
	}

	public SelectBoolean( Connection connection )
		throws SQLException
	{
		this( connection, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );
	}

	/**
		Pass a DataSource. You are responsible for closing that DataSource.
		SelectBoolean is a wrapper for a PreparedStatement, not a Connection.
	**/
	public SelectBoolean( DataSource dataSource, int resultSetType, int resultSetConcurrency )
		throws SQLException
	{
		this( dataSource.getConnection(), resultSetType, resultSetConcurrency );
	}

	public SelectBoolean( DataSource dataSource )
		throws SQLException
	{
		this( dataSource, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );
	}

	public final SelectBooleanResultSet execute()
		throws SQLException
	{
        __ps.setBoolean( 1, getStatus() );

        ResultSet rs = null;
        if( __ps.execute() )
        {
            rs = __ps.getResultSet();
        }
		SelectBooleanResultSet result = new SelectBooleanResultSet( rs );
        return result;
    }

	// TODO add null check to setters (when !isnullable)
    private Boolean _status = true;
    public void setStatus( Boolean status ) { _status = status; }
    public Boolean getStatus() { return _status; }


	public String toString()
	{
		return String.format( __printfSQL
            , getStatus()
		);
	}
}