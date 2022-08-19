package fado;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class Table
{
	String name;
	private HashMap<String, Column> columnMap = new HashMap<>();

	public void addColumn( Column c )
	{
		columnMap.put( c.name.toLowerCase(), c );
	}

	public Column getColumn( String name )
	{
		return columnMap.get( name.toLowerCase() );
	}
}

class Result
{
	String name;
	String label;
	// Preferred capitalization from original SQL source
	String preferredName;
	int type;
	String typeName;
	int isNullable;

	@Override
	public String toString()
	{
		return "Result{" +
				"name='" + name + '\'' +
				", label='" + label + '\'' +
				", preferredName='" + preferredName + '\'' +
				", type=" + type +
				", typeName='" + typeName + '\'' +
				", isNullable=" + isNullable +
				'}';
	}
}

//class Param
//{
//	boolean isSigned;
//	int scaled;
//	int precision;
//	int mode;
//	int type;
//	String typeName;
//	int isNullable;
//
//	@Override
//	public String toString()
//	{
//		return "Param{" +
//				"isSigned=" + isSigned +
//				", scaled=" + scaled +
//				", precision=" + precision +
//				", mode=" + mode +
//				", type=" + type +
//				", typeName='" + typeName + '\'' +
//				", isNullable=" + isNullable +
//				'}';
//	}
//}

public class MetaData
{
	public static void main( String[] args )
		throws Exception
	{
		Class.forName( "org.h2.Driver" );
		Connection conn = DriverManager.getConnection( "jdbc:h2:tcp://localhost/~/Projects/ambrose/db/cm", "sa", null );

//		Map<String, Table> tableMap = getTablesAndColumns( conn );

//		String sql = "SELECT id as ugh, lastATP, course_title, description, revision FROM Course WHERE department_abbrev = ?";
		String sql = new String( Files.readAllBytes( Paths.get( "/Users/jasonosgood/Projects/fado/test/SelectCourseDescr.sql" )));
		System.out.println( sql );
		extractResultColumns( conn, sql );
//		{
//			PreparedStatement ps = conn.prepareStatement( sql );
//			ParameterMetaData md = ps.getParameterMetaData();
//			for( int nth = 1; nth <= md.getParameterCount(); nth++ )
//			{
//				Param param = new Param();
//				param.type = md.getParameterType( nth );
//				param.typeName = md.getParameterTypeName( nth );
//				param.isNullable = md.isNullable( nth );
//				param.isSigned = md.isSigned( nth );
//				param.scaled = md.getScale( nth );
//				param.precision = md.getPrecision( nth );
//				param.mode = md.getParameterMode( nth );
//				System.out.println( param );
//			}
//		}
	}

	static List<Result> extractResultColumns( Connection conn, String sql ) throws SQLException
	{
		try
		(
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery( sql );
		)
		{
			ArrayList<Result> results = new ArrayList<>();
			ResultSetMetaData md = rs.getMetaData();
			for( int nth = 1; nth <= md.getColumnCount(); nth++ )
			{
				Result result = new Result();
				result.name = md.getColumnName( nth );
				result.label = md.getColumnLabel( nth );
				result.type = md.getColumnType( nth );
				result.typeName = md.getColumnTypeName( nth );
				result.isNullable = md.isNullable( nth );
				results.add( result );
//				System.out.println( result );
			}
			return results;
		}
//		catch( SQLException e )
//		{
//			throw e;
//		}
	}

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
						Column column = new Column();
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