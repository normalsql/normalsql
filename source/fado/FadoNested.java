package fado;

import fado.meta.*;
import fado.parse.GenericSQLLexer;
import fado.parse.GenericSQLParser;
import static fado.parse.GenericSQLParser.*;
import fado.parse.GlobbingRuleContext;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;


public class FadoNested
{
	public static void main( String[] args )
		throws Exception
	{
		Class.forName( "org.h2.Driver" );

		Work work = new Work();
		work.sourceFile = Paths.get( "/Users/jasonosgood/Projects/fado/test/SelectCourseDescr.sql" );
		work.targetFile = Paths.get( "" );
		work.packageName = "blerg";
		work.className = "SelectCourseDescr";

		process( work );
	}

	public static void process( Work work )
		throws Exception
	{
		// TODO support batches, multiple queries, multiple resultsets

		String originalSQL = new String( Files.readAllBytes( work.sourceFile ));
		work.originalSQL = originalSQL;

		parse( work );

		Connection conn = DriverManager.getConnection( "jdbc:h2:tcp://localhost/~/Projects/ambrose/db/cm", "sa", null );
		Map<String, Table> tables = MetaData.getTablesAndColumns( conn );
		resolveFROMs( work.root, tables );

		MetaData.processPreparedStatement( conn, work );
		List<Item> itemList = work.root.get( 0 ).itemList;
		matchItemsToRSColumns( itemList, work.columnList );

		gatherConditions( work.root, work.conditionList );
		// TODO: some kind of sanity check to ensure datatypes of conditions and params match

		updateLiterals( work.conditionList );
		String preparedSQL = work.tokens.getText();
		work.preparedSQL = preparedSQL;

		SelectTemplate template = new SelectTemplate();
		template.merge( work );

//		ArrayList<StatementParam> paramList =
//		gatherStatementParams( conditionList, tables );

	}

	public static void parse( Work work )
		throws IOException
	{
		CharStream chars = CharStreams.fromString( work.originalSQL );
		GenericSQLLexer lexer = new GenericSQLLexer( chars );
		CommonTokenStream tokens = new CommonTokenStream( lexer );
		GenericSQLParser parser = new GenericSQLParser( tokens );
		StatementContext statement = parser.statement();

		SelectList root = findSELECTs( statement );
		findFROMs( root );
		findWHEREs( root );
		findItems( root );
		work.root = root;
		work.tokens = tokens;
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
					parent.conditionList.add( new Comparison( op,columnRef, literal ));
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
						parent.conditionList.add( new IN( columnRef, literals.toArray( new LiteralContext[0] )));
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
		for( SelectList child : parent )
		{
			resolveFROMs( child, tableMap );
		}

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
				TColumn column = from.table.getColumn( condition.columnName );
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

	static void gatherConditions( SelectList parent, ArrayList<Condition> conditions )
	{
		for( SelectList child : parent )
		{
			gatherConditions( child, conditions );
		}
		conditions.addAll( parent.conditionList );
	}

	/**
	 * Populate each Condition with text from literals. Replace each
	 * literal's text with '?' parameter, for creating a PreparedStatement.
	 *
	 * @param conditionList
	 */
	// TODO split into two methods 'copyLiteralsToValues' and 'rewriteLiterals( String text )'
	static void updateLiterals( ArrayList<Condition> conditionList )
	{
		for( Condition condition : conditionList )
		{
			for( LiteralContext lc : condition.literals )
			{
				// TODO: move this to 'copyLiteralsToValues'
				String value = lc.trimQuotes( lc.getText() );
				condition.valueList.add( value );

				// TODO: change literal text to '?'
				lc.convertToInputParam();
			}
		}
	}

	/**
	 * Match query 'items' from original SQL to ResultSet's result columns. If original SQL
	 * used wildcard '*', there will be more columns than items. Assumes items and
	 * result columns appear in same order. There can be more result columns than items.
	 *
	 */
	static void matchItemsToRSColumns( List<Item> itemList, List<RSColumn> rsColumnList )
	{
		Iterator<Item> itemIterator = itemList.iterator();
		String preferred = null;

		outer:
		for( RSColumn column : rsColumnList )
		{
			while( preferred == null )
			{
				if( !itemIterator.hasNext() ) break outer;
				Item item = itemIterator.next();
				preferred = item.alias != null ? item.alias : item.name;
			}

			if( preferred.equalsIgnoreCase( column.label ) || preferred.equalsIgnoreCase( column.name ))
			{
				column.preferredName = preferred;
				preferred = null;
			}
		}
	}

	//	/** Create one StatementParam per literal found. */
//	static ArrayList<StatementParam> gatherStatementParams( List<Condition> conditionList, Map<String, Table> tableMap )
//	{
//		ArrayList<StatementParam> params = new ArrayList<>();
////		StatementParam param = new StatementParam();
//		for( Condition condition : conditionList )
//		{
////			switch( condition.getClass().getSimpleName() )
////			{
////				case "Comparison":
////				{
////					System.out.println( "yup " );
////					break;
////				}
////			}
//
//			Table table = tableMap.get( condition.tableName.toLowerCase() );
//			Column column = table.getColumn( condition.columnName );
//
//			if( condition instanceof Comparison )
//			{
////				LiteralContext literal = condition.
////				StatementParam param = new StatementParam( condition.columnName, column.dataType );
//			}
//			else if( condition instanceof Between )
//			{
//
//			}
//			else if( condition instanceof IN )
//			{
//
//			}
//		}
//		return params;
//	}


}