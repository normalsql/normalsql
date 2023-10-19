// Copyright 2010-2023 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.jdbc;

/*
 POJO for a ResultSet's column.
*/
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
