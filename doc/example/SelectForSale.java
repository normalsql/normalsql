
/**
* SelectForSaleResultSet.java Fri Jul 21 06:40:32 PDT 2023
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
        __ps.setString( 1, getnull() );
        __ps.setInteger( 2, getnull() );

        ResultSet rs = null;
        if( __ps.execute() )
        {
            rs = __ps.getResultSet();
        }
		SelectForSaleResultSet result = new SelectForSaleResultSet( rs );
        return result;
    }

	// TODO add null check to setters (when !isnullable)
    private String _null = "coupe";
    public void setnull( String null ) { _null = null; }
    public String getnull() { return _null; }

    private Integer _null = 100000;
    public void setnull( Integer null ) { _null = null; }
    public Integer getnull() { return _null; }


	public String toString()
	{
		return String.format( __printfSQL
            , getnull()
            , getnull()
		);
	}
}