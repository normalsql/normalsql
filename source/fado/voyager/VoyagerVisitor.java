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
	public ArrayList<Source> tables = new ArrayList();
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
		database = context.database != null ? context.database.getTrimmedText() : null ;
		schema = context.schema != null ? context.schema.getTrimmedText() : null ;
		table = context.table != null ? context.table.getTrimmedText() : null ;
		column = context.column != null ? context.column.getTrimmedText() : null ;
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
		database = context.database != null ? context.database.getTrimmedText() : null ;
		schema = context.schema != null ? context.schema.getTrimmedText() : null ;
		table = context.table != null ? context.table.getTrimmedText() : null ;
	}

	public String database;
	public String schema;
	public String table;
}

class Source
{
	public TableRef tableRef;
	public String alias;
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

	@Override
	public Work visitSource( SourceContext context )
	{
		if( context.tableRef() != null )
		{
			Source source = new Source();
			source.tableRef = new TableRef( context.tableRef() );
			if( context.alias() != null )
			{
				source.alias = context.alias().name().getTrimmedText();
			}
			stack.peek().tables.add( source );
		}
		return super.visitSource( context );
	}
}
