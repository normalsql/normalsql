package fado;

import fado.meta.*;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;


public class MetaData
{
	public static void main( String[] args )
		throws Exception
	{
		Class.forName( "org.h2.Driver" );
		Connection conn = DriverManager.getConnection( "jdbc:h2:tcp://localhost/~/Projects/ambrose/db/cm", "sa", null );

//		Map<String, Table> tableMap = getTablesAndColumns( conn );

		String sql = "SELECT id as ugh, lastATP, course_title, description, revision FROM Course WHERE department_abbrev = ?";
//		String sql = new String( Files.readAllBytes( Paths.get( "/Users/jasonosgood/Projects/fado/test/SelectCourseDescr.sql" )));
//		System.out.println( sql );
//		extractResultColumns( conn, sql );
	}

	/**
	 * Gathers input parameters and output resultset columns from a PreparedStatement.
	 *
	 * @param conn
	 * @param work
	 * @throws SQLException
	 */
	public static void processPreparedStatement( Connection conn, Work work )
		throws SQLException
	{
		PreparedStatement ps = conn.prepareStatement( work.originalSQL );

		ParameterMetaData pmd = ps.getParameterMetaData();
		for( int nth = 1; nth <= pmd.getParameterCount(); nth++ )
		{
			Param param = new Param();
			param.nth = nth;
			param.type = pmd.getParameterType( nth );
			param.typeName = pmd.getParameterTypeName( nth );
			param.isNullable = pmd.isNullable( nth );
//			param.isSigned = pmd.isSigned( nth );
//			param.scaled = pmd.getScale( nth );
//			param.precision = pmd.getPrecision( nth );
//			param.mode = pmd.getParameterMode( nth );
			param.clazz = pmd.getParameterClassName( nth );
			work.paramList.add( param );
//			System.out.println( param );
		}

		ResultSetMetaData md = ps.getMetaData();
		for( int nth = 1; nth <= md.getColumnCount(); nth++ )
		{
			RSColumn column = new RSColumn();
			column.nth = nth;
			column.name = md.getColumnName( nth );
			column.label = md.getColumnLabel( nth );
			column.type = md.getColumnType( nth );
			column.typeName = md.getColumnTypeName( nth );
			column.isNullable = md.isNullable( nth );
			column.clazz = md.getColumnClassName( nth );
			work.rsColumnList.add( column );
//			System.out.println( column );
		}
	}

//	static List<RSColumn> extractResultColumns( Connection conn, String sql ) throws SQLException
//	{
//		try
//		(
//			Statement s = conn.createStatement();
//			ResultSet rs = s.executeQuery( sql );
//		)
//		{
//			ArrayList<RSColumn> rsColumnList = new ArrayList<>();
//			ResultSetMetaData md = rs.getMetaData();
//			for( int nth = 1; nth <= md.getColumnCount(); nth++ )
//			{
//				RSColumn column = new RSColumn();
//				column.name = md.getColumnName( nth );
//				column.label = md.getColumnLabel( nth );
//				column.type = md.getColumnType( nth );
//				column.typeName = md.getColumnTypeName( nth );
//				column.isNullable = md.isNullable( nth );
//				rsColumnList.add( column );
////				System.out.println( result );
//			}
//			return rsColumnList;
//		}
////		catch( SQLException e )
////		{
////			throw e;
////		}
//	}

	public static Map<String, Table> getTablesAndColumns( Connection conn ) throws SQLException
	{
		HashMap<String, Table> tableMap = new HashMap<>();
		DatabaseMetaData meta = conn.getMetaData();
		try( ResultSet tablesRS = meta.getTables( null, null, null, new String[]{ "TABLE" } ))
		{
			while( tablesRS.next() )
			{
				Table table = new Table();
				table.name = tablesRS.getString( "TABLE_NAME" );

				try( ResultSet columnsRS = meta.getColumns( null, null, table.name, null ))
				{
					while( columnsRS.next() )
					{
						TColumn column = new TColumn();
						column.name = columnsRS.getString( "COLUMN_NAME" );
//						column.columnSize = columnsRS.getString("COLUMN_SIZE");
						column.type = columnsRS.getInt( "DATA_TYPE" );
						column.typeName = columnsRS.getString( "TYPE_NAME" );
						column.isNullable = columnsRS.getString( "IS_NULLABLE" );
//						column.isAutoIncrement = columnsRS.getString("IS_AUTOINCREMENT");
						table.addColumn( column );
					}
				}
				tableMap.put( table.name.toLowerCase(), table );
			}
		}
		return tableMap;
	}
}