/*
 Fado - From.java

 Copyright 2022, 2014, 2011, 2010 Jason Osgood

 Represents SQL statement's FROM clause. References to tables, etc; not the actual tables.
*/

package fado.meta;

import fado.parse.GenericSQLParser.TableContext;

public class From
{
	public TableContext tc;
	public String databaseName;
	public String tableName;
	public String alias;
	public Table table;

	public From( TableContext tc )
	{
		this.tc = tc;
		this.databaseName = tc.findFirstString( "**/databaseName" );
		this.tableName = tc.findFirstString( "**/tableName" );
		this.alias = tc.findFirstString( "**/aliasName" );
	}
}