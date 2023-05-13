// Copyright 2010-2022 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.meta;

import normalsql.parse.NormalSQLParser.TableRefContext;

/**
 * <p>TableRef class.</p>
 *
 * @author jasonosgood
 * @version $Id: $Id
 */
public class
	TableRef
{
	/**
	 * <p>Constructor for TableRef.</p>
	 *
	 * @param context a {@link normalsql.parse.NormalSQLParser.TableRefContext} object
	 */
	public TableRef( TableRefContext context )
	{
		catalog = context.catalog != null ? context.catalog.getTrimmedText() : null;
		schema = context.schema != null ? context.schema.getTrimmedText() : null;
		table = context.table != null ? context.table.getTrimmedText() : null;
	}

	public String catalog;
	public String schema;
	public String table;
}
