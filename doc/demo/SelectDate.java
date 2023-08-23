
/**
* SelectDateResultSet.java Sun Aug 20 10:04:49 PDT 2023
*
* Generated using NormalSQL's Select.vm template.
*
* Original SQL:
*
* <pre>
* {@code
* SELECT *
* FROM VALUES
*     ({d '2023-01-01'}, 'apple'),
*     ({d '2023-02-02'}, 'banana'),
*     ({d '2023-03-03'}, 'cherry'),
*     ({d '2023-04-04'}, 'daikon')
*     t("ripe", "fruit")
* WHERE "ripe" >= '2023-03-03';
* }
* </pre>
*
* @see /Users/jasonosgood/Projects/normalsql/doc/demo/SelectDate.sql
*
**/


import java.sql.*;
import javax.sql.DataSource;
import java.math.BigDecimal;

public class
	SelectDate
{
	private PreparedStatement __ps = null;

    	public final String __preparedSQL =
		"SELECT *\n"+ 
		"FROM VALUES\n"+ 
		"    ({d '2023-01-01'}, 'apple'),\n"+ 
		"    ({d '2023-02-02'}, 'banana'),\n"+ 
		"    ({d '2023-03-03'}, 'cherry'),\n"+ 
		"    ({d '2023-04-04'}, 'daikon')\n"+ 
		"    t("ripe", "fruit")\n"+ 
		"WHERE "ripe" >= ?;"; 

    	public final String __printfSQL =
		"SELECT *\n"+ 
		"FROM VALUES\n"+ 
		"    ({d '2023-01-01'}, 'apple'),\n"+ 
		"    ({d '2023-02-02'}, 'banana'),\n"+ 
		"    ({d '2023-03-03'}, 'cherry'),\n"+ 
		"    ({d '2023-04-04'}, 'daikon')\n"+ 
		"    t("ripe", "fruit")\n"+ 
		"WHERE "ripe" >= '%t';"; 

	/**
		Pass a Connection. You are responsible for closing that Connection.
		SelectDate is a wrapper for a PreparedStatement, not a Connection.
	**/
	public SelectDate( Connection connection, int resultSetType, int resultSetConcurrency )
		throws SQLException
	{
		__ps = connection.prepareStatement( __preparedSQL, resultSetType, resultSetConcurrency );
	}

	public SelectDate( Connection connection )
		throws SQLException
	{
		this( connection, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );
	}

	/**
		Pass a DataSource. You are responsible for closing that DataSource.
		SelectDate is a wrapper for a PreparedStatement, not a Connection.
	**/
	public SelectDate( DataSource dataSource, int resultSetType, int resultSetConcurrency )
		throws SQLException
	{
		this( dataSource.getConnection(), resultSetType, resultSetConcurrency );
	}

	public SelectDate( DataSource dataSource )
		throws SQLException
	{
		this( dataSource, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );
	}

	public final SelectDateResultSet execute()
		throws SQLException
	{
        __ps.setDate( 1, getRipe() );

        ResultSet rs = null;
        if( __ps.execute() )
        {
            rs = __ps.getResultSet();
        }
		SelectDateResultSet result = new SelectDateResultSet( rs );
        return result;
    }

	// TODO add null check to setters (when !isnullable)
    private Date _ripe = java.sql.Date.valueOf( "2023-03-03" );
    public void setRipe( Date ripe ) { _ripe = ripe; }
    public Date getRipe() { return _ripe; }


	public String toString()
	{
		return String.format( __printfSQL
            , getRipe()
		);
	}
}