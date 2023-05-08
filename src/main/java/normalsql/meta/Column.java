// Copyright 2010-2022 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

/*
 Represents a ResultSet's result column. Columns correspond to the items of a SELECT query.
*/
package normalsql.meta;

/**
 * <p>Column class.</p>
 *
 * @author jasonosgood
 * @version $Id: $Id
 */
public class
	Column
{
	/** comment */
	public int nth;
	/** comment */
	public String catalog;
	/** comment */
	public String schema;
	/** comment */
	public String table;
	/** comment */
	public String name;
	/** comment */
	public String label;
	/** comment */
	// TODO rename to sqlType
	public int type;
	/** comment */
	public String typeName;
	/** comment */
	public int isNullable;
	/** comment */
	public String className;
}
