/*
 Fado - Result.java

 Copyright 2022, 2014, 2011, 2010 Jason Osgood

 Represents a ResultSet's result column. Result columns correspond to SELECT query items.
 RSColumns are not TColumns.
*/
package fado.meta;

/**
 * ResultSet column.
 */
public class Column
{
	public int nth;
	public String catalog;
	public String schema;
	public String table;
	public String name;
	public String label;
	// TODO rename to sqlType
	public int type;
	public String typeName;
	public int isNullable;
	public String className;
}