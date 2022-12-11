package normalsql.meta;

import normalsql.parse.NormalSQLParser.TableRefContext;

public class TableRef
{
	public TableRef( TableRefContext context )
	{
		database = context.database != null ? context.database.getTrimmedText() : null;
		schema = context.schema != null ? context.schema.getTrimmedText() : null;
		table = context.table != null ? context.table.getTrimmedText() : null;
	}

	public String database;
	public String schema;
	public String table;

	@Override
	public String toString()
	{
		return "TableRef{" +
			"database='" + database + '\'' +
			", schema='" + schema + '\'' +
			", table='" + table + '\'' +
			'}';
	}
}
