package normalsql.util;// Copyright 2010-2025 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

//package classicmodels;

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
 * @version $Id: $Id
 */
public class
Dumper
{

	/**
	 * <p>dumpResultSet.</p>
	 *
	 * @param rs a ResultSet object
	 * @throws SQLException if any.
	 */
	public static void dumpResultSet( ResultSet rs ) 
		throws SQLException
	{
		dumpResultSet( rs, System.out, false ); 
	}	
	
	/**
	 * <p>dumpResultSet.</p>
	 *
	 * @param rs a ResultSet object
	 * @param out a {@link PrintStream} object
	 * @throws SQLException if any.
	 */
	public static void dumpResultSet( ResultSet rs, PrintStream out ) 
			throws SQLException
		{
			dumpResultSet( rs, out, false ); 
		}	
		
	/**
	 * Dumps ResultSet to OutpuStream. If reset = true and ResultSet type != TYPE_FORWARD_ONLY,
	 * dump will start with first row and iterator will be reset to first row.
	 *
	 * @param rs a ResultSet object
	 * @param out a {@link OutputStream} object
	 * @param reset a boolean
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
}
