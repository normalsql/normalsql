// Copyright 2010-2023 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.jdbc;

import normalsql.template.Property;

/**
 * POJO representing metadata of a ResultSet column.
*/
public class
	Column
extends
	Property
{
//	public String catalog;
//	public String schema;
//	public String table;
	private String name;
	public String name() {return name;}
	public void name(String name	) { this.name = name; }

	private String label;
	public String label() {return label;}
	public void label(String label	) { this.label = label; }

	private int isNullable;
	public int isNullable() {return isNullable;}
	public void isNullable(int isNullable	) { this.isNullable = isNullable; }
}
