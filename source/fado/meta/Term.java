/*
 Fado - Condition.java

 Copyright 2022, 2014, 2011, 2010 Jason Osgood

 Superclass representing conditions in SQL expressions.
*/

package fado.meta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import static fado.parse.GenericSQLParser.*;

public abstract class Term< T extends TermContext >
{
	public T tc;

	public Term( T tc )
	{
		this.tc = tc;
	}

//	public RefContext rc;
//	public String tableName;
//	public String columnName;
//	// TODO remove 'from' and add Table reference to Column
//	public From from;
//	public Table.Column column;
//	public List<ValueContext> vcList;
//	public ArrayList<String> valueList = new ArrayList<>();

//	public TermContext left;
//	public List<TermContext> right;

//	public Term( TermContext left, TermContext... right )
//	{
//		this( left, Arrays.asList( right ));
//	}
//
//	public Term( TermContext left, List<TermContext> right )
//	{
//		this.left = left;
//		this.right = right;
//	}

//	public Term( RefContext rc, ValueContext... literals )
//	{
//		this( rc, Arrays.asList( literals ));
//	}

//	public Term( RefContext rc, List<ValueContext> vcList )
//	{
//		this.rc = rc;
//		this.vcList = vcList;
//		if( rc != null )
//		{
//			ListIterator<NameContext> i = rc.name().listIterator( rc.name().size() );
//			if( i.hasPrevious() ) this.columnName = i.previous().getTrimmedText();
//			if( i.hasPrevious() ) this.tableName = i.previous().getTrimmedText();
////
//////			this.columnName = columnRef.columnName().getTrimmedText();
////			this.columnName = columnRef.getTrimmedText();
////			this.tableName = columnRef.findFirstString( "tableName" );
//		}
//
//		for( ValueContext vc : vcList )
//		{
//			this.valueList.add( vc.getTrimmedText() );
//		}
//	}
}
