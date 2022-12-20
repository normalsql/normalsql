// Copyright 2010-2022 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package spare;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

class Table
{
	public static class Column
	{
		public String name;
		public int type;
		public String typeName;
		public String isNullable;
	}

	public String name;
	// TODO: replace Map with searching List, cuz Map is not case sensitive
	private HashMap<String, Table.Column> columnMap = new HashMap<>();
	public void addColumn( Table.Column c )
	{
		columnMap.put( c.name.toLowerCase(), c );
	}
	public Table.Column getColumn( String name )
	{
		return columnMap.get( name.toLowerCase() );
	}
}
// TODO move methods to main Main.java
public class MetaData
{
	public static void main( String[] args )
		throws Exception
	{
		Class.forName( "org.h2.Driver" );
		Connection conn = DriverManager.getConnection( "jdbc:h2:tcp://localhost/~/Projects/ambrose/db/cm", "sa", null );

		Map<String, Table> tableMap = getTablesAndColumns( conn );
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
						Table.Column column = new Table.Column();
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