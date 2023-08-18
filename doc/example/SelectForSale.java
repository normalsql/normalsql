
/**
* SelectForSaleResultSet.java Tue Aug 15 10:19:31 PDT 2023
*
* Generated using NormalSQL's Select.vm template.
*
* Original SQL:
*
* <pre>
* {@code
* SELECT "id", "make", "model", "year"
* FROM public.AUTOMOBILES
* WHERE "style" = 'coupe'
*   AND "odometer" < 100000;
* }
* </pre>
*
* @see /Users/jasonosgood/Projects/normalsql/doc/example/SelectForSale.sql
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
		"SELECT "id", "make", "model", "year"\n"+ 
		"FROM public.AUTOMOBILES\n"+ 
		"WHERE "style" = ?\n"+ 
		"  AND "odometer" < ?;"; 

    	public final String __printfSQL =
		"SELECT "id", "make", "model", "year"\n"+ 
		"FROM public.AUTOMOBILES\n"+ 
		"WHERE "style" = '%s'\n"+ 
		"  AND "odometer" < %d;"; 

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
        __ps.setString( 1, getStyle() );
        __ps.setInteger( 2, getOdometer() );

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
    public void setStyle( String style ) { _style = style; }
    public String getStyle() { return _style; }

    private Integer _odometer = 100000;
    public void setOdometer( Integer odometer ) { _odometer = odometer; }
    public Integer getOdometer() { return _odometer; }


	public String toString()
	{
		return String.format( __printfSQL
            , getStyle()
            , getOdometer()
		);
	}
}