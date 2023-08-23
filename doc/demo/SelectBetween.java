
/**
* SelectBetweenResultSet.java Sun Aug 20 10:04:08 PDT 2023
*
* Generated using NormalSQL's Select.vm template.
*
* Original SQL:
*
* <pre>
* {@code
* SELECT *
* FROM VALUES (1, 'apple'), (2, 'banana'), (3, 'cherry'), (4, 'daikon') t("count", "fruit")
* WHERE "count" BETWEEN 2 and 3;
* }
* </pre>
*
* @see /Users/jasonosgood/Projects/normalsql/doc/demo/SelectBetween.sql
*
**/


import java.sql.*;
import javax.sql.DataSource;
import java.math.BigDecimal;

public class
	SelectBetween
{
	private PreparedStatement __ps = null;

    	public final String __preparedSQL =
		"SELECT *\n"+ 
		"FROM VALUES (1, 'apple'), (2, 'banana'), (3, 'cherry'), (4, 'daikon') t("count", "fruit")\n"+ 
		"WHERE "count" BETWEEN ? and ?;"; 

    	public final String __printfSQL =
		"SELECT *\n"+ 
		"FROM VALUES (1, 'apple'), (2, 'banana'), (3, 'cherry'), (4, 'daikon') t("count", "fruit")\n"+ 
		"WHERE "count" BETWEEN '%s' and '%s';"; 

	/**
		Pass a Connection. You are responsible for closing that Connection.
		SelectBetween is a wrapper for a PreparedStatement, not a Connection.
	**/
	public SelectBetween( Connection connection, int resultSetType, int resultSetConcurrency )
		throws SQLException
	{
		__ps = connection.prepareStatement( __preparedSQL, resultSetType, resultSetConcurrency );
	}

	public SelectBetween( Connection connection )
		throws SQLException
	{
		this( connection, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );
	}

	/**
		Pass a DataSource. You are responsible for closing that DataSource.
		SelectBetween is a wrapper for a PreparedStatement, not a Connection.
	**/
	public SelectBetween( DataSource dataSource, int resultSetType, int resultSetConcurrency )
		throws SQLException
	{
		this( dataSource.getConnection(), resultSetType, resultSetConcurrency );
	}

	public SelectBetween( DataSource dataSource )
		throws SQLException
	{
		this( dataSource, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );
	}

	public final SelectBetweenResultSet execute()
		throws SQLException
	{
        __ps.setString( 1, getCountLow() );
        __ps.setString( 2, getCountHigh() );

        ResultSet rs = null;
        if( __ps.execute() )
        {
            rs = __ps.getResultSet();
        }
		SelectBetweenResultSet result = new SelectBetweenResultSet( rs );
        return result;
    }

	// TODO add null check to setters (when !isnullable)
    private String _count_low = "2";
    public void setCountLow( String count_low ) { _count_low = count_low; }
    public String getCountLow() { return _count_low; }

    private String _count_high = "3";
    public void setCountHigh( String count_high ) { _count_high = count_high; }
    public String getCountHigh() { return _count_high; }


	public String toString()
	{
		return String.format( __printfSQL
            , getCountLow()
            , getCountHigh()
		);
	}
}