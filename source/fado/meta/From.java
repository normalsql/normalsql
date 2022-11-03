/*
 Fado - From.java

 Copyright 2022, 2014, 2011, 2010 Jason Osgood

 Represents SQL statement's FROM clause. References to tables, etc; not the actual tables.
*/

package fado.meta;

import fado.parse.GenericSQLParser.SourceContext;
import fado.parse.GenericSQLParser.TableRefContext;

// TODO maybe rename this to TableRef and change to have Table contain TableRef
public class From
{
	public SourceContext sc;
	public String database;
	public String schema;
	public String tableName;
	public String alias;
	public Table table;

	public From( SourceContext sc )
	{
		this.sc = sc;
		TableRefContext trc = sc.tableRef();
		database = ( trc.database != null ? trc.database.getTrimmedText() : null );
		schema = ( trc.schema != null ? trc.schema.getTrimmedText() : null );
		tableName = trc.table.getTrimmedText();
		alias = sc.alias() != null ? sc.alias().name().getTrimmedText() : null;
	}
}