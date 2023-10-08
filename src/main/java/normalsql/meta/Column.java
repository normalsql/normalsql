// Copyright 2010-2022 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

/*
 Represents a ResultSet's result column. Columns correspond to the items of a SELECT query.
*/
package normalsql.meta;

public class
	Column
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
