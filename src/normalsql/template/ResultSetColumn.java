// Copyright 2010-2025 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.template;

import normalsql.knockout.Item;

/**
 * POJO representing ResultSet column's metadata.
 *
 * Velocity's reflection (introspection) doesn't find "naked" public instance variables.
 * Had to add JavaBean style accessors.
 *
 * TODO Does EscapeVelocity (Velocity rewrite) work w/o accessors?
*/
public class
	ResultSetColumn
extends
	Property
{
	public String catalog;
	public String schema;
	public String table;

	public String name;
	public String name() { return name; }
	public void name( String name ) { this.name = name; }

	/**
	 * ResultSet column label is either the item's name or alias. We use "alias" here
	 * for clarity later when matching up items and columns.
	 */
	public String alias;
	public String label() { return alias; }
	public void label( String label ) { this.alias = label; }

	public Item item;
	public Item item() { return item; }
	public void item( Item item ) { this.item = item; }
}
