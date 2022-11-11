/*
 Fado - SelectList.java

 Copyright 2022, 2014, 2011, 2010 Jason Osgood

 Represents SQL SELECT with sub-SELECTs as children. Root element actually
 refers to parsetree's root StatementContext.

 Cleverly extends ArrayList, simplifying recursion.

 TODO: Evolve to support INSERT and UPDATE statements too.
*/
package fado.meta;

import fado.parse.GlobbingRuleContext;

import java.util.ArrayList;

public class SelectList extends ArrayList<SelectList>
{
	public GlobbingRuleContext context;
	public ArrayList<Item> itemList = new ArrayList<>();
	public ArrayList<From> fromList = new ArrayList<>();
	public ArrayList<Term> termList = new ArrayList<>();
}