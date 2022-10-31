/*
 Fado - From.java

 Copyright 2022, 2014, 2011, 2010 Jason Osgood

 Represents SQL statement's FROM clause. References to tables, etc; not the actual tables.
*/

package fado.meta;

import fado.parse.GenericSQLParser.TableContext;
import fado.parse.GenericSQLParser.TableRefContext;

public class From
{
	public TableContext tc;
	public String schemaName;
	public String tableName;
	public String alias;
	public Table table;

	public From( TableContext tc )
	{
		this.tc = tc;
		TableRefContext trc = tc.tableRef();
		schemaName = ( trc.schemaName != null ? trc.schemaName.getTrimmedText() : null );
		tableName = trc.tableName.getTrimmedText();
		alias = tc.alias() != null ? tc.alias().name().getTrimmedText() : null;
	}
}