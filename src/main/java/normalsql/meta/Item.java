// Copyright 2010-2022 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.meta;

import normalsql.parse.NormalSQLParser.ItemContext;

/**
 * Item from a SELECT statement item. Extracted from SQL query's source code.
 * Then matched with a Column from the query's ResultSet. Item's capitalization
 * is used for generating accessors. Note: Accessors use the data type, etc. from
 * matched Column, not from Item.
 *
 * @author jasonosgood
 * @version $Id: $Id
 */

public class Item
{
	public ItemContext context;
	public String name;
	public String alias;
	public String source;
}
