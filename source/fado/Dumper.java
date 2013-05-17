package fado;

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

public class Dumper {

	public static void dumpResultSet( ResultSet set, OutputStream out ) 
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
		ResultSetMetaData meta = set.getMetaData();
		int count = meta.getColumnCount();
		for( int i = 0; i < count; i++ )
		{
			String temp = meta.getColumnName( i + 1 );
			ps.print( temp + "\t" );
		}
		ps.println();

		set.beforeFirst();
		while( set.next() )
		{
			for( int i = 0; i < count; i++ )
			{
				Object o = set.getObject( i + 1 );
				String s = o.toString();
				s = s.replace( "\n", "\\n" );
				s = s.replace( "\t", "\\t" );
				ps.print( o + "\t" );
			}
			ps.println();
		}
	}

}
