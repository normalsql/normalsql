/*
 Fado - Result.java

 Copyright 2022, 2014, 2011, 2010 Jason Osgood

 Represents a ResultSet's result column. Result columns correspond to query items.
*/
package fado.meta;

public class Result
{
	public String name;
	public String label;
	// Developer's preferred capitalization from original SQL source
	public String preferredName;
	public int type;
	public String typeName;
	public int isNullable;

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
