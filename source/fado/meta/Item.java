package fado.meta;

import fado.parse.GenericSQLParser.ItemContext;

/**
 * SELECT Statement item. Specified in the query's source code. Later matched with
 * ResultSet Columns.
 *
 */

// TODO Maybe split into TableRef (wildcard) and TableColumnRef variants

public class Item
{
	public ItemContext context;

	public boolean wildcard;
	public TableRef tableRef;
	public TableColumnRef columnRef;
	public String name;
	public String alias;
}
