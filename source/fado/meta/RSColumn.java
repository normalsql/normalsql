/*
 Fado - Result.java

 Copyright 2022, 2014, 2011, 2010 Jason Osgood

 Represents a ResultSet's result column. Result columns correspond to SELECT query items.
 RSColumns are not TColumns.
*/
package fado.meta;

public class RSColumn
{
	// TODO: nth might be redundant
	public int nth;
	public String name;
	// JDBC driver's capitalization of alias
	public String label;
	// Developer's preferred capitalization of alias from original SQL source
	public String preferredName;
	public int type;
	public String typeName;
	public int isNullable;
	public String clazz;

	@Override
	public String toString()
	{
		return "RSColumn{" +
			"name='" + name + '\'' +
			", label='" + label + '\'' +
			", preferredName='" + preferredName + '\'' +
			", type=" + type +
			", typeName='" + typeName + '\'' +
			", isNullable=" + isNullable +
			", clazz='" + clazz + '\'' +
			'}';
	}
}