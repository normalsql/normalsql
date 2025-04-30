// Copyright 2010-2025 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.postgresql;

import normalsql.grammar.PostgreSQLParser.ItemContext;
//import org.antlr.v4.runtime.ParserRuleContext;


// TODO maybe rename to SelectItem. To help me better keep items and columns straight in my head.

/**
 * A SELECT statement Item. Extracted from SQL query's source code.
 * Then matched with a ResultSet Column.
 *
 * Item's capitalization is used for generating accessors.
 */

public class Item
{
//	public ParserRuleContext context;
	public ItemContext context;
	public String            localName;
	public String      alias;
	/**
	 * Actual source code of original SELECT item. Not called "source" to disambiguate.
	 */
	public String verbatim;
}
