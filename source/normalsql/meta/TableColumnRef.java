package normalsql.meta;

import normalsql.parse.NormalSQLParser.ColumnRefContext;

public class TableColumnRef
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

	@Override
	public String toString()
	{
		return "TableColumnRef{" +
			"database='" + database + '\'' +
			", schema='" + schema + '\'' +
			", table='" + table + '\'' +
			", column='" + column + '\'' +
			'}';
	}
}
