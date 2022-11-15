package fado.voyager;

import fado.meta.Work;
import fado.parse.GenericSQLBaseVisitor;
import fado.parse.GenericSQLParser.*;

import java.util.ArrayList;
import java.util.Stack;

import static java.lang.System.out;

class Select
{

	public ArrayList<Item> items = new ArrayList();
	public ArrayList<Item> tables = new ArrayList();
	public ArrayList<Item> terms = new ArrayList();
}

class Item
{
	public boolean wildcard;
	public TableRef tableRef;
	public Ref ref;
}

class Ref
{
	public Ref( RefContext context )
	{
		database = context.findFirstString( "database" );
		schema = context.findFirstString( "schema" );
		table = context.findFirstString( "table" );
		column = context.findFirstString( "column" );
	}

	public String database;
	public String schema;
	public String table;
	public String column;
}

class TableRef
{
	public TableRef( TableRefContext context )
	{
		database = context.findFirstString( "database" );
		schema = context.findFirstString( "schema" );
		table = context.findFirstString( "table" );
	}

	public String database;
	public String schema;
	public String table;
}

class From
{

}

public class
	VoyagerVisitor
extends
	GenericSQLBaseVisitor< Work >
{
	Stack<Select> stack = new Stack();

//	public <T> T last( List< T > list )
//	{
//		if( list == null ) return null;
//		if( list.isEmpty() ) return null;
//		return list.get( list.size() - 1 );
//	}

	public Work visitSelect( SelectContext context )
	{
		stack.push( new Select() );
//		System.out.println( ctx.getText() );
		Work work = super.visitSelect( context );
		stack.pop();
		return work;
	}

	public Work visitItemWildcard( ItemWildcardContext context )
	{
		Item item = new Item();
		item.wildcard = true;
		if( context.tableRef() != null )
		{
			item.tableRef = new TableRef( context.tableRef() );
		}
		stack.peek().items.add( item );
		return super.visitItemWildcard( context );
	}

	public Work visitItemColumn( ItemColumnContext context )
	{
		Item item = new Item();
		item.wildcard = false;
		RefContext rc = context.findFirst( RefContext.class, "term/subterm/ref" );
		if( rc != null )
		{
			item.ref = new Ref( rc );
			stack.peek().items.add( item );
		}
		return super.visitItemColumn( context );
	}

//	@Override
//	public Work visitFrom( FromContext ctx )
//	{
//		From from = new From();
////		ctx.
//		return super.visitFrom( ctx );
//	}
}
