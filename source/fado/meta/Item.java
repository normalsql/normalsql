/*
 Fado - Item.java

 Copyright 2022, 2014, 2011, 2010 Jason Osgood

 Represents an item in a SQL SELECT statement. Items correspond to ResultSet result columns.
*/
package fado.meta;

import fado.parse.GenericSQLParser.ColumnRefContext;
import fado.parse.GenericSQLParser.ItemContext;

public class Item
{
	public ItemContext ic;
	public String alias;
	public String name;

	public Item( ItemContext ic )
	{
		this.ic = ic;
		ColumnRefContext columnRef = ic.findFirst( ColumnRefContext.class, "**/columnRef" );
		if( columnRef != null )
		{
			// am pretty sure this can't be null
			this.name = ic.trimQuotes( columnRef.columnName().getText() );
		}

		this.alias = ic.trimQuotes( ic.findFirstString( "**/aliasName" ) );
	}

	@Override
	public String toString()
	{
		return "Item{" +
				"alias='" + alias + '\'' +
				", name='" + name + '\'' +
				'}';
	}
}
