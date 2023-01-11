
/**
* SelectForSaleResultSet.java Tue Jan 10 19:40:42 PST 2023
*
* Generated using NormalSQL's Select.vm template.
*
* Original SQL:
*
* <pre>
* {@code
* SELECT id, make, model, year
* FROM automobiles
* WHERE style = 'coupe' AND odometer < 100000;
* }
* </pre>
*
* @see /Users/jasonosgood/Projects/normalsql/example/SelectForSale.sql
*
**/


import java.sql.*;
import javax.sql.DataSource;
import java.math.BigDecimal;

public class
	SelectForSale
{
	private PreparedStatement __ps = null;

    	public final String __preparedSQL =
		"SELECT id, make, model, year\n"+ 
		"FROM automobiles\n"+ 
		"WHERE style = ? AND odometer < ?;"; 

    	public final String __printfSQL =
		"SELECT id, make, model, year\n"+ 
		"FROM automobiles\n"+ 
		"WHERE style = '%s' AND odometer < %d;"; 

	/**
		Pass a Connection. You are responsible for closing that Connection.
		SelectForSale is a wrapper for a PreparedStatement, not a Connection.
	**/
	public SelectForSale( Connection connection, int resultSetType, int resultSetConcurrency )
		throws SQLException
	{
		__ps = connection.prepareStatement( __preparedSQL, resultSetType, resultSetConcurrency );
	}

	public SelectForSale( Connection connection )
		throws SQLException
	{
		this( connection, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );
	}

	/**
		Pass a DataSource. You are responsible for closing that DataSource.
		SelectForSale is a wrapper for a PreparedStatement, not a Connection.
	**/
	public SelectForSale( DataSource dataSource, int resultSetType, int resultSetConcurrency )
		throws SQLException
	{
		this( dataSource.getConnection(), resultSetType, resultSetConcurrency );
	}

	public SelectForSale( DataSource dataSource )
		throws SQLException
	{
		this( dataSource, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );
	}

	public final SelectForSaleResultSet execute()
		throws SQLException
	{
        __ps.setString( 1, getstyle() );
        __ps.setLong( 2, getodometer() );

        ResultSet rs = null;
        if( __ps.execute() )
        {
            rs = __ps.getResultSet();
        }
		SelectForSaleResultSet result = new SelectForSaleResultSet( rs );
        return result;
    }

	// TODO add null check to setters (when !isnullable)
    private String _style = "coupe";
    public void setstyle( String style ) { _style = style; }
    public String getstyle() { return _style; }

    private Long _odometer = 100000L;
    public void setodometer( Long odometer ) { _odometer = odometer; }
    public Long getodometer() { return _odometer; }


	public String toString()
	{
		return String.format( __printfSQL
            , getstyle()
            , getodometer()
		);
	}
}