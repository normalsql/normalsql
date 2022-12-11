package spare;

import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Writes contents of a ResultSet to an outputstream. 
 * 
 * One row per line. Fields are tab delimited. Newlines and tabs within content
 * are escaped, eg '\n' and '\t'. 
 * 
 * First line contains names of columns. 
 * 
 * @author jasonosgood
 *
 */

public class 
	Dumper 
{

	public static void dumpResultSet( ResultSet rs ) 
		throws SQLException
	{
		dumpResultSet( rs, System.out, false ); 
	}	
	
	public static void dumpResultSet( ResultSet rs, PrintStream out ) 
			throws SQLException
		{
			dumpResultSet( rs, out, false ); 
		}	
		
	/**
	 * Dumps ResultSet to OutpuStream. If reset = true and ResultSet type != TYPE_FORWARD_ONLY, 
	 * dump will start with first row and iterator will be reset to first row.
	 * 
	 * @param rs
	 * @param out
	 * @param reset
	 * @throws SQLException
	 */
	
	public static void dumpResultSet( ResultSet rs, OutputStream out, boolean reset ) 
		throws SQLException
	{
		PrintStream ps = null;
		if( out instanceof PrintStream )
		{
			ps = (PrintStream) out;
		}
		else
		{
			ps = new PrintStream( out );
		}
		ResultSetMetaData meta = rs.getMetaData();
		int count = meta.getColumnCount();
		for( int i = 0; i < count; i++ )
		{
			String temp = meta.getColumnName( i + 1 );
			ps.print( temp + "\t" );
		}
		ps.println();

		if( rs.getType() != ResultSet.TYPE_FORWARD_ONLY )
		{
			rs.beforeFirst();
		}
		while( rs.next() )
		{
			for( int i = 0; i < count; i++ )
			{
				String s = null;
				Object o = rs.getObject( i + 1 );
				if( o != null ) 
				{
					s = o.toString();
					s = s.replace( "\n", "\\n" );
					s = s.replace( "\t", "\\t" );
				}
				ps.print( s + "\t" );
			}
			ps.println();
		}
		if( rs.getType() != ResultSet.TYPE_FORWARD_ONLY )
		{
			rs.beforeFirst();
		}
	}
//	public static void dumpResultSet( ResultSet rs )
//			throws SQLException
//	{
//		System.out.println();
//		ResultSetMetaData meta = rs.getMetaData();
//		int count = meta.getColumnCount();
//		for( int i = 0; i < count; i++ )
//		{
//			String temp = meta.getColumnName( i + 1 );
//			System.out.print( temp + "\t" );
//		}
//		System.out.print( "\n--\n" );
////		set.first();
//		while( rs.next() )
//		{
//			for( int i = 0; i < count; i++ )
//			{
//				Object temp = rs.getObject( i + 1 );
//				System.out.print( temp + "\t" );
//			}
//			System.out.print( "\n" );
//
//		}
//		System.out.println();
//	}
}
