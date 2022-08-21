/*
 Fado - Column.java

 Copyright 2022, 2014, 2011, 2010 Jason Osgood

 Represents SQL table.
*/
package fado.meta;

import java.util.HashMap;

public class Table
{
	public String name;
	// TODO: replace Map with searching List, cuz Map is not case sensitive
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
