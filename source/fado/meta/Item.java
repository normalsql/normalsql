/*
 Fado - Item.java

 Copyright 2022, 2014, 2011, 2010 Jason Osgood

 Represents an item in a SQL SELECT statement. Each item is resolved to one or more ResultSet result columns.
*/
package fado.meta;

import fado.parse.GenericSQLParser.RefContext;
import fado.parse.GenericSQLParser.ItemContext;

public class Item
{
	public ItemContext ic;
	public String alias;
	// TODO don't store this if not used later...
	public String name;

	public Item( ItemContext ic )
	{
		this.ic = ic;
		RefContext rc = ic.findFirst( RefContext.class, "**/ref" );
		if( rc != null )
		{
			// TODO get last 'name' in list
			// TODO handle both 'id' and 'Name' rules
			this.name = rc.getTrimmedText();
		}
		this.alias = ic.findFirstString( "**/alias/name" );
	}

//	@Override
//	public String toString()
//	{
//		return "Item{" +
//				"alias='" + alias + '\'' +
//				", name='" + name + '\'' +
//				'}';
//	}
}