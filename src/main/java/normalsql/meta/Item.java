// Copyright 2010-2022 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.meta;

import normalsql.parse.NormalSQLParser.ItemContext;

/**
 SELECT statement item. Specified in the query's source code. Later matched with
 ResultSet Columns.
 */

// TODO Maybe split into TableRef (wildcard) and TableColumnRef variants

public class Item
{
	public ItemContext context;

//	public boolean wildcard;
//	public TableRef tableRef;
//	public TableColumnRef columnRef;
	public String name;
	public String alias;
}
