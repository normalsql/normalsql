// Copyright 2010-2022 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package test;

import normalsql.meta.Column;
import normalsql.meta.Param;
import spare.Dumper;

import java.sql.*;

/**
 * <p>SelectIN class.</p>
 *
 * @author jasonosgood
 * @version $Id: $Id
 */
public class SelectIN
{
	/**
	 * <p>main.</p>
	 *
	 * @param args an array of {@link java.lang.String} objects
	 * @throws java.lang.Exception if any.
	 */
	public static void main( String[] args )
		throws Exception
	{
//		String url = "jdbc:h2:tcp://localhost/~/Projects/ambrose/db/cm";
//		String url = "jdbc:h2:mem:";
		String url = "jdbc:h2:~/temp";
		Connection conn = DriverManager.getConnection( url, "sa", null );
//		Map<String, Table> tables = MetaData.getTablesAndColumns( conn );

//		PreparedStatement ps = processPreparedStatement( conn, "select 1 in ( ?, ? );" );
//		PreparedStatement ps = processPreparedStatement( conn, "select 'a' in ( ?, ? );" );
//		PreparedStatement ps = processPreparedStatement( conn, "select 'a' = any ( ? );" );
//		String array[] = {  "2", "a", "3", "4" };
//		String array[] = {  "2", "3", "4" };
//		ps.setObject( 1, array );

		// http://h2database.com/html/performance.html

		/*
		Prepared Statements and IN(...)

Avoid generating SQL statements with a variable size IN(...) list. Instead, use a prepared statement with arrays as in the following example:

PreparedStatement prep = conn.prepareStatement(
    "SELECT * FROM TEST WHERE ID = ANY(?)");
prep.setObject(1, new Long[] { 1L, 2L });
ResultSet rs = prep.executeQuery();

		 */
		PreparedStatement ps = processPreparedStatement( conn, "explain SELECT * FROM TEST WHERE ID = ANY(?)" );
		ps.setObject(1, new Long[] { 1L, 2L });
//		ps.setString( 1, "a" );
		if( ps.execute() )
		{
			Dumper.dumpResultSet(  ps.getResultSet() );
		}

	}

	/**
	 * <p>processPreparedStatement.</p>
	 *
	 * @param conn a Connection object
	 * @param preparedSQL a {@link java.lang.String} object
	 * @return a PreparedStatement object
	 * @throws SQLException if any.
	 */
	public static PreparedStatement processPreparedStatement( Connection conn, String preparedSQL )
		throws SQLException
	{
		PreparedStatement ps = conn.prepareStatement( preparedSQL );

		ParameterMetaData pmd = ps.getParameterMetaData();
		for( int nth = 1; nth <= pmd.getParameterCount(); nth++ )
		{
			Param param = new Param();
			param.nth = nth;
			param.type = pmd.getParameterType( nth );
			param.typeName = pmd.getParameterTypeName( nth );
			param.isNullable = pmd.isNullable( nth );
			param.isSigned = pmd.isSigned( nth );
			param.scaled = pmd.getScale( nth );
			param.precision = pmd.getPrecision( nth );
			param.mode = pmd.getParameterMode( nth );
			param.className = pmd.getParameterClassName( nth );
			System.out.println( param );
//			work.params.add( param );
		}

		ResultSetMetaData md = ps.getMetaData();
		for( int nth = 1; nth <= md.getColumnCount(); nth++ )
		{
			Column column = new Column();
			column.nth = nth;
			column.name = md.getColumnName( nth );
			column.label = md.getColumnLabel( nth );
			column.type = md.getColumnType( nth );
			column.typeName = md.getColumnTypeName( nth );
			column.isNullable = md.isNullable( nth );
			column.className = md.getColumnClassName( nth );
			System.out.println( column );
//			work.columns.add( column );
		}

		return ps;
	}
}
