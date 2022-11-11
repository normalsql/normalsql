package fado;

import fado.meta.*;
import fado.parse.GenericSQLLexer;
import fado.parse.GenericSQLParser;
import static fado.parse.GenericSQLParser.*;
import fado.parse.GlobbingRuleContext;
import fado.template.JavaHelper;
import org.antlr.v4.runtime.*;

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
//		work.sourceFile = Paths.get( "/Users/jasonosgood/Projects/fado/test/SelectCourseDescr.sql" );
		work.sourceFile = Paths.get( "/Users/jasonosgood/Projects/fado/test/SelectCourseTestBetweens.sql" );
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

		String url = "jdbc:h2:tcp://localhost/~/Projects/ambrose/db/cm";
		Connection conn = DriverManager.getConnection( url, "sa", null );
		Map<String, Table> tables = MetaData.getTablesAndColumns( conn );
		resolveFROMs( work.root, tables );

		MetaData.processPreparedStatement( conn, work );
		List<Item> itemList = work.root.get( 0 ).itemList;
		matchItemsToRSColumns( itemList, work.columnList );

		gatherConditions( work.root, work.termList );
		// TODO: some kind of sanity check to ensure datatypes of conditions and params match

//		// Creates text for PreparedStatement by replacing
//		// original literal value with a JDBC input parameter '?'
//		work.termList.forEach(
//			c -> c.vcList.forEach(
//				lc -> lc.setStartTokenText( "?" )));
//		work.preparedSQL = work.tokens.getText();
//		System.out.println( work.preparedSQL );
//
//		// Creates printf template for generated statement's
//		// toString() method (for easier inspection, debugging).
//		work.termList.forEach( c ->
//			c.vcList.forEach( lc ->
//				lc.setStartTokenText( JavaHelper.toPrintfConverter( c.column.type ))
//			)
//		);
		work.printfSQL = work.tokens.getText();
		System.out.println( work.printfSQL );

		SelectTemplate template = new SelectTemplate();
		template.merge( work );
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
		for( SourceContext sc : parent.context.find( SourceContext.class, "from/**/source" ))
		{
			if( sc.tableRef() != null )
			{
				From from = new From( sc );
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
		for( TermContext found : parent.context.find( TermContext.class, "where/term" ))
		{
			processExpression( parent, found );
		}

		for( SelectList child : parent )
		{
			findWHEREs( child );
		}

	}

	static void processExpression( SelectList parent, TermContext ec )
	{
		switch( ec )
		{
			case TermBooleanContext tbc:
				processExpression( parent, tbc.term( 0 ));
				processExpression( parent, tbc.term( 1 ));
				break;

			case TermUnaryContext tuc:
				processExpression( parent, tuc.term() );
				break;

			case TermComparisonContext tcc:
				Comparison comparison = new Comparison( tcc );
				if( comparison.match() != Comparison.Params.NotMatched )
				{
					parent.termList.add( comparison );
				}
				break;

//				TermContext left = tcc.term( 0 );
//				TermContext right = tcc.term( 1 );
//				RefContext ref = null;
//				ValueContext value = null;
//				if( left instanceof TermValueContext && right instanceof TermRefContext )
//				{
//					value = ((TermValueContext) left).value();
//					ref = ((TermRefContext) right).ref();
//				}
//				else if( right instanceof TermValueContext && left instanceof TermRefContext )
//				{
//					value = ((TermValueContext) right).value();
//					ref = ((TermRefContext) left).ref();
//				}
//
//				parent.termList.add( new Comparison( null, ref, value ));
//				break;

			case TermBetweenContext tbc:
				Between between = new Between( tbc );
				if( between.match() != Between.Params.NotMatched )
				{
					parent.termList.add( between );
				}
				break;

			default:
				break;
		}

//		Token op = ec.op;
//		switch( op.getType() )
//		{
//
//
//			case GT:
//			case GT2:
//			case GTE:
//			case LT:
//			case LT2:
//			case LTE:
//			case EQ:
//			case NEQ1:
//			case NEQ2:
//			case LIKE:
//			case ILIKE:
//			{
//				// TODO allow right-to-left too
//				ColumnRefContext columnRef = ec.left.columnRef();
//				LiteralContext literal = ec.right.literal();
//				if( columnRef != null && literal != null )
//				{
//					parent.conditionList.add( new Comparison( op, columnRef, literal ));
//				}
//				break;
//			}
//
//			case BETWEEN:
//			{
//				ColumnRefContext columnRef = ec.left.columnRef();
//				LiteralContext lower = ec.lower.literal();
//				LiteralContext upper = ec.upper.literal();
//				if( columnRef != null && lower != null && upper != null )
//				{
//					parent.conditionList.add( new Between( columnRef, lower, upper ));
//				}
//				break;
//			}
//
//			case IN:
//			{
//				ColumnRefContext columnRef = ec.left.columnRef();
//				ExpressionListContext list = ec.list;
//				if( list != null )
//				{
//					List<LiteralContext> literals = list.find( LiteralContext.class, "expression/literal" );
//					if( literals.size() > 0 )
//					{
//						parent.conditionList.add( new IN( columnRef, literals.toArray( new LiteralContext[0] )));
//					}
//				}
//				break;
//			}
//
//			// do nothing
//			case STRCAT:
//			case AMP:
//			case PIPE:
//			case STAR:
//			case DIV:
//			case MOD:
//			case PLUS:
//			case MINUS:
//			default:
//				break;
//		}
	}

	static void findItems( SelectList parent )
	{
		for( SelectList child : parent )
		{
			if( child.context.getRuleIndex() == RULE_select )
			{
				SelectContext sc = (SelectContext) child.context;
				for( ItemContext ic : sc.item() )
				{
					child.itemList.add( new Item( ic ));
				}
			}
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

		for( Term term : parent.termList )
		{
			resolveCondition( parent, term );
		}
	}

	static void resolveCondition( SelectList parent, Term term )
	{
		List<TermRefContext> refs = term.tc.find( TermRefContext.class, "ref" );
		System.out.println( "doink" );
//		String tableName = term.tableName;
//		tableName = ( tableName != null ? tableName : "*" );
//
//		for( From from : parent.fromList )
//		{
//			if( from.table == null ) continue;
//
//			if( tableName.equalsIgnoreCase( from.tableName ) || tableName.equalsIgnoreCase( from.alias ) || tableName.equals( "*" ))
//			{
//				Table.Column column = from.table.getColumn( term.columnName );
//				if( column != null )
//				{
//					term.from = from;
//					term.column = column;
//					break;
//				}
//			}
//		}
//
//		if( term.column == null )
//		{
//			System.out.printf( "condition.columnName '%s' not found\n", term.columnName );
//		}
	}

	static void gatherConditions( SelectList parent, ArrayList<Term> terms )
	{
		for( SelectList child : parent )
		{
			gatherConditions( child, terms );
		}
		terms.addAll( parent.termList );
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
}