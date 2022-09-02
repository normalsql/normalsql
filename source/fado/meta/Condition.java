/*
 Fado - Condition.java

 Copyright 2022, 2014, 2011, 2010 Jason Osgood

 Superclass representing conditions in SQL expressions.
*/

package fado.meta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static fado.parse.GenericSQLParser.*;

public abstract class Condition
{
	public ColumnRefContext columnRef;
	public String tableName;
	public String columnName;
	// TODO remove 'from' and add Table reference to Column
	public From from;
	public TColumn column;
	public List<LiteralContext> literalList;
	public ArrayList<String> valueList = new ArrayList<>();

	public Condition( ColumnRefContext columnRef, LiteralContext... literals )
	{
		this( columnRef, Arrays.asList( literals ));
	}

	public Condition( ColumnRefContext columnRef, List<LiteralContext> literalList )
	{
		this.columnRef = columnRef;
		this.literalList = literalList;
		if( columnRef != null )
		{
			this.columnName = columnRef.columnName().getTrimmedText();
			this.tableName = columnRef.findFirstString( "tableName" );
		}

		for( LiteralContext lc : literalList )
		{
			valueList.add( lc.getTrimmedText() );
		}
	}
}
