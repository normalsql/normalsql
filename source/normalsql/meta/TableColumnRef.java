// Copyright 2010-2022 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.meta;

import normalsql.parse.NormalSQLParser.ColumnRefContext;

public class
	TableColumnRef
{
	public TableColumnRef( ColumnRefContext context )
	{
		database = context.database != null ? context.database.getTrimmedText() : null;
		schema = context.schema != null ? context.schema.getTrimmedText() : null;
		table = context.table != null ? context.table.getTrimmedText() : null;
		column = context.column != null ? context.column.getTrimmedText() : null;
	}

	public String database;
	public String schema;
	public String table;
	public String column;
}
