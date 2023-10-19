// Copyright 2010-2023 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.parse;

import normalsql.parse.NormalSQLParser.ItemContext;

/**
 * A SELECT statement Item. Extracted from SQL query's source code.
 * Then matched with a ResultSet Column.
 *
 * Item's capitalization is used for generating accessors.
 */

public class Item
{
	public ItemContext context;
	public String name;
	public String alias;
	public String source;
}
