package fado.voyager;

import fado.parse.GenericSQLParser;

public class TableRef
{
	public TableRef( GenericSQLParser.TableRefContext context )
	{
		database = context.database != null ? context.database.getTrimmedText() : null;
		schema = context.schema != null ? context.schema.getTrimmedText() : null;
		table = context.table != null ? context.table.getTrimmedText() : null;
	}

	public String database;
	public String schema;
	public String table;
}
