/*
 Fado - Condition.java

 Copyright 2022, 2014, 2011, 2010 Jason Osgood

 Superclass representing conditions in SQL expressions.
*/

package fado.meta;

import java.util.ArrayList;

import static fado.parse.GenericSQLParser.*;

public abstract class Condition // implements Comparable<Condition>
{
	public ColumnRefContext columnRef;
	public String tableName;
	public String columnName;
	// TODO remove 'from' and add Table reference to Column
	public From from;
	public Column column;
	// TODO: convert to List<LiteralContext>
	public LiteralContext[] literals;
	public ArrayList<String> valueList = new ArrayList<>();

	// TODO add constuctor w/ List<LiteralContext>
	public Condition( ColumnRefContext columnRef, LiteralContext... literals )
	{
		this.columnRef = columnRef;
		this.literals = literals;
		if( columnRef != null )
		{
			this.columnName = columnRef.trimQuotes( columnRef.columnName().getText() );
			// Null-safe query to get 'tableName'
			this.tableName = columnRef.trimQuotes( columnRef.findFirstString( "tableName" ));
		}
	}

//	@Override
//	public int compareTo( Condition that )
//	{
//		int a = this.columnRef.start.getStartIndex();
//		int b = that.columnRef.start.getStartIndex();
//		return a - b;
//	}
}
