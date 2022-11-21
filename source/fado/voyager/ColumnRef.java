package fado.voyager;

import fado.parse.GenericSQLParser;

public class ColumnRef
{
	public ColumnRef( GenericSQLParser.ColumnRefContext context )
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
