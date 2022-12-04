package fado.voyager;

import fado.parse.GenericSQLParser.*;

public class Item
{
	public ItemContext context;
	public boolean wildcard;
	public TableRef tableRef;
	public ColumnRef columnRef;
	public String name;
	public String alias;
}
