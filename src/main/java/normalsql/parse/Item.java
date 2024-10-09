// Copyright 2010-2023 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.parse;

import normalsql.parse.NormalSQLParser.ItemContext;


// TODO maybe rename to SelectItem. To help me better keep items and columns straight in my head.

/**
 * A SELECT statement Item. Extracted from SQL query's source code.
 * Then matched with a ResultSet Column.
 *
 * Item's capitalization is used for generating accessors.
 */

public class Item
{
	public ItemContext context;
	public String      localName;
	public String      alias;
	/**
	 * Actual source code of original SELECT item. Not called "source" to disambiquate.
	 */
	public String verbatim;
}
