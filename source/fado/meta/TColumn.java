/*
 Fado - Column.java

 Copyright 2022, 2014, 2011, 2010 Jason Osgood

 Represents SQL table column. Table Columns are not ResultSet Columns.
*/
package fado.meta;

public class TColumn
{
	public String name;
	public int type;
	public String typeName;
	public String isNullable;
}