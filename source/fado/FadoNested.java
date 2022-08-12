package fado;

import fado.parse.GenericSQLLexer;
import fado.parse.GenericSQLParser;
import static fado.parse.GenericSQLParser.*;
import fado.parse.GlobbingRuleContext;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;

class SelectList extends ArrayList<SelectList>
{
	GlobbingRuleContext context;
	ArrayList<Item> itemList = new ArrayList<>();
	ArrayList<From> fromList = new ArrayList<>();
	ArrayList<Condition> conditionList = new ArrayList<>();
}

class Item
{
	ItemContext ic;
	String alias;
	String name;

	Item( ItemContext ic )
	{
		this.ic = ic;
		ColumnRefContext columnRef = ic.findFirst( ColumnRefContext.class, "**/columnRef" );
		if( columnRef != null )
		{
			// am pretty sure this can't be null
			this.name = ic.trimQuotes( columnRef.columnName().getText() );
		}

		this.alias = ic.trimQuotes( ic.findFirstString( "**/aliasName" ));
	}

	@Override
	public java.lang.String toString()
	{
		return "Item{" +
				"alias='" + alias + '\'' +
				", name='" + name + '\'' +
				'}';
	}
}

class From
{
	TableContext tc;
	String databaseName;
	String tableName;
	String alias;
	Table table;

	From( TableContext tc )
	{
		this.tc = tc;
		this.databaseName = tc.trimQuotes( tc.findFirstString( "**/databaseName" ));
		this.tableName = tc.trimQuotes( tc.findFirstString( "**/tableName" ));
		this.alias = tc.trimQuotes( tc.findFirstString( "**/aliasName" ));
	}
}

class Condition
{
	ColumnRefContext columnRef;
	String tableName;
	String columnName;
	From from;
	Column column;

	Condition( ColumnRefContext columnRef )
	{
		this.columnRef = columnRef;
		this.columnName = columnRef.trimQuotes( columnRef.columnName().getText() );
		// Null-safe query to get 'tableName'
		this.tableName = columnRef.trimQuotes( columnRef.findFirstString( "tableName" ));
	}
}

class Comparison extends Condition
{
	LiteralContext literal;
	Token op;
	Comparison( ColumnRefContext columnRef, LiteralContext literal, Token op )
	{
		super( columnRef );
		this.literal = literal;
		this.op = op;
	}
}

class Between extends Condition
{
	LiteralContext lower;
	LiteralContext upper;

	Between( ColumnRefContext columnRef, LiteralContext lower, LiteralContext upper )
	{
		super( columnRef );
		this.lower = lower;
		this.upper = upper;
	}
}

class IN extends Condition
{
	List<LiteralContext> literals;

	IN( ColumnRefContext columnRef, List<LiteralContext> literals )
	{
		super( columnRef );
		this.literals = literals;
	}
}


public class FadoNested
{
	public static void main( String[] args )
			throws Exception
	{
		String sql = new String( Files.readAllBytes( Paths.get( "/Users/jasonosgood/Projects/fado/test/SelectCourseDescr.sql" )));

//		CharStream chars = CharStreams.fromPath( Paths.get( sourceFile.getPath() ) );
//		CharStream chars = CharStreams.fromFileName( "/Users/jasonosgood/Projects/fado/test/NestedSelect.sql" );
//		CharStream chars = CharStreams.fromFileName( "/Users/jasonosgood/Projects/fado/test/SelectCourseDescr.sql" );
		CharStream chars = CharStreams.fromString( sql );
		GenericSQLLexer lexer = new GenericSQLLexer( chars );
		CommonTokenStream tokens = new CommonTokenStream( lexer );
		GenericSQLParser parser = new GenericSQLParser( tokens );
		StatementContext statement = parser.statement();

		SelectList root = findSELECTs( statement );
		findFROMs( root );
		findWHEREs( root );
		findItems( root );

		Class.forName( "org.h2.Driver" );
		Connection conn = DriverManager.getConnection( "jdbc:h2:tcp://localhost/~/Projects/ambrose/db/cm", "sa", null );

		Map<String, Table> tables = MetaData.getTablesAndColumns( conn );
		resolveFROMs( root, tables );

		List<Result> resultColumnList = MetaData.extractResultColumns( conn, sql );
		// TODO support batches, multiple queries, multiple resultsets
		List<Item> itemList = root.get( 0 ).itemList;
		matchItemsToResultColumns( itemList, resultColumnList );
		// sort literals (stream order)
//		convertConditionsToParams( root );
		System.out.println( root.size() );

	}

	static SelectList findSELECTs( GlobbingRuleContext parent )
	{
		SelectList result = new SelectList();
		result.context = parent;

		for( GlobbingRuleContext context : parent.find( "**/select" ) )
		{
			result.add( findSELECTs( context ) );
		}

		return result;
	}

	static void findFROMs( SelectList parent )
	{
		for( TableContext tc : parent.context.find( TableContext.class, "from/**/table" ))
		{
			if( tc.tableRef() != null )
			{
				From from = new From( tc );
				parent.fromList.add( from );
			}
			// TODO: support nested 'select', and maybe 'VALUES (...)' too
//			else
//			{
//
//			}
		}
		for( SelectList child : parent )
		{
			findFROMs( child );
		}
	}

	static void findWHEREs( SelectList parent )
	{
		for( GlobbingRuleContext found : parent.context.find( "where/expression" ) )
		{
			processExpression( parent, (ExpressionContext) found );
		}

		for( SelectList child : parent )
		{
			findWHEREs( child );
		}
	}

	static void findItems( SelectList parent )
	{
		for( SelectList child : parent )
		{
			if( child.context.getRuleIndex() == RULE_select )
			{
				SelectContext sc = (SelectContext) child.context;
				for( ItemContext ic : sc.itemList().item() )
				{
					child.itemList.add( new Item( ic ));
				}
			}
		}
	}

	static void processExpression( SelectList parent, ExpressionContext ec )
	{
		Token op = ec.op;
		switch( op.getType() )
		{
			case AND:
			case OR:
				processExpression( parent, ec.left );
				processExpression( parent, ec.right );
				break;

			case NOT:
				processExpression( parent, ec.right );
				break;

			case GT:
			case GT2:
			case GTE:
			case LT:
			case LT2:
			case LTE:
			case EQ:
			case NEQ1:
			case NEQ2:
			case LIKE:
			case ILIKE:
			{
				// TODO allow right-to-left too
				ColumnRefContext columnRef = ec.left.columnRef();
				LiteralContext literal = ec.right.literal();
				if( columnRef != null && literal != null )
				{
//					node.convertToInputParam();
					parent.conditionList.add( new Comparison( columnRef, literal, op ));
				}
				break;
			}

			case BETWEEN:
			{
				ColumnRefContext columnRef = ec.left.columnRef();
				LiteralContext lower = ec.lower.literal();
				LiteralContext upper = ec.upper.literal();
				if( columnRef != null && lower != null && upper != null )
				{
					parent.conditionList.add( new Between( columnRef, lower, upper ));
				}
				break;
			}

			case IN:
			{
				ColumnRefContext columnRef = ec.left.columnRef();
				ExpressionListContext list = ec.list;
				if( list != null )
				{
					List<LiteralContext> literals = list.find( LiteralContext.class, "expression/literal" );
					if( literals.size() > 0 )
					{
						parent.conditionList.add( new IN( columnRef, literals ));
					}
				}
				break;
			}

			// do nothing
			case STRCAT:
			case AMP:
			case PIPE:
			case STAR:
			case DIV:
			case MOD:
			case PLUS:
			case MINUS:
			default:
				break;
		}
	}

	static void resolveFROMs( SelectList parent, Map<String, Table> tableMap )
	{
		for( From from : parent.fromList )
		{
			from.table = tableMap.get( from.tableName.toLowerCase() );
			if( from.table == null )
			{
				System.out.printf( "from.name '%s' not found\n", from.tableName );
			}
		}

		for( Condition condition : parent.conditionList )
		{
			resolveCondition( parent, condition );
		}

		for( SelectList child : parent )
		{
			resolveFROMs( child, tableMap );
		}
	}

	static void resolveCondition( SelectList parent, Condition condition )
	{
		String tableName = condition.tableName;
		tableName = ( tableName != null ? tableName : "*" );

		for( From from : parent.fromList )
		{
			if( from.table == null ) continue;

			if( tableName.equalsIgnoreCase( from.tableName ) || tableName.equalsIgnoreCase( from.alias ) || tableName.equals( "*" ))
			{
				Column column = from.table.getColumn( condition.columnName );
				if( column != null )
				{
					condition.from = from;
					condition.column = column;
					break;
				}
			}
		}

		if( condition.column == null )
		{
			System.out.printf( "condition.columnName '%s' not found\n", condition.columnName );
		}
	}

	/**
	 * Match query 'items' from original SQL to ResultSet's result columns. If original SQL
	 * used wildcard '*', there will be more columns than items. Assumes items and
	 * result columns appear in same order. There can be more result columns than items.
	 *
	 */
	static void matchItemsToResultColumns( List<Item> itemList, List<Result> resultColumnList )
	{
		try
		{
			Iterator<Result> resultIterator = resultColumnList.iterator();
			Iterator<Item> itemIterator = itemList.iterator();
			Result column = resultIterator.next();
			Item item = null;

			while( true )
			{
				if( item == null ) item = itemIterator.next();
				String preferred = item.alias != null ? item.alias : item.name;

				// Skip items without alias or name
				if( preferred == null )
				{
					item = null;
					continue;
				}

				if( preferred.equalsIgnoreCase( column.label ) || preferred.equalsIgnoreCase( column.name ))
				{
					column.preferredName = preferred;
					item = null;
				}

				column = resultIterator.next();
			}
		}
		// Just catch exception, instead of checking for hasNext
		catch( NoSuchElementException ignore ) {}

		System.out.println( "tada" );
	}
}