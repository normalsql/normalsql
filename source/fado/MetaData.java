package fado;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

class Table
{
	String name;
	HashMap<String, Column> columnMap = new HashMap<>();
}

class Column
{
	String name;
	String dataType;
	String isNullable;
}

public class MetaData
{
	public static void main( String[] args )
		throws Exception
	{
		Class.forName( "org.h2.Driver" );
		Connection _conn = DriverManager.getConnection( "jdbc:h2:tcp://localhost/~/Projects/ambrose/db/cm", "sa", null );

		Map<String, Table> tableMap = getTablesAndColumns( _conn );

	}

	public static Map<String, Table> getTablesAndColumns( Connection conn ) throws SQLException
	{
		HashMap<String, Table> tableMap = new HashMap<>();
		DatabaseMetaData meta = conn.getMetaData();
		try( ResultSet tablesRS = meta.getTables(null, null, null, new String[]{"TABLE"} ))
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
						column.dataType = columnsRS.getString( "DATA_TYPE" );
						column.isNullable = columnsRS.getString( "IS_NULLABLE" );
//						column.isAutoIncrement = columnsRS.getString("IS_AUTOINCREMENT");
						table.columnMap.put( column.name.toLowerCase(), column );
					}
				}
				tableMap.put( table.name.toLowerCase(), table );
			}
		}
		return tableMap;
	}
}