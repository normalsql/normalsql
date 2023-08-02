// Copyright 2010-2022 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.meta;

import normalsql.parse.NormalSQLParser.ColumnContext;

/**
 * <p>TableColumnRef class.</p>
 *
 * @author jasonosgood
 * @version $Id: $Id
 */
public class
	TableColumnRef
{
	/**
	 * <p>Constructor for TableColumnRef.</p>
	 *
	 * @param context a {@link normalsql.parse.NormalSQLParser.ColumnContext} object
	 */
	public TableColumnRef( ColumnContext context )
	{
//		catalog = context.catalog != null ? context.catalog.getTrimmedText() : null;
//		schema = context.schema != null ? context.schema.getTrimmedText() : null;
//		table = context.table != null ? context.table.getTrimmedText() : null;
//		column = context.column != null ? context.column.getTrimmedText() : null;
	}

	public String catalog;
	public String schema;
	public String table;
	public String column;
}
