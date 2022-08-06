package fado;

import fado.parse.GenericSQLLexer;
import fado.parse.GenericSQLParser;
import static fado.parse.GenericSQLParser.*;
import fado.parse.GlobbingRuleContext;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;

class SelectList extends ArrayList<SelectList>
{
	public GlobbingRuleContext context;
	public ArrayList<From> fromList = new ArrayList<>();
	public ArrayList<Condition> conditionList = new ArrayList<>();
}

class From
{
	TableContext context;
	public final String database;
	public final String name;
	public final String alias;
	Table table;

	public From( String database, String name, String alias )
	{
		this.database = database;
		this.name = name;
		this.alias = alias;
	}
}

class Condition
{
	public ColumnRefContext columnRef;
	String tableName;
	String columnName;
	public From from;
	Column column;
}

class Comparison extends Condition
{
	public final LiteralContext literal;
	public final Token op;
	public Comparison( ColumnRefContext columnRef, LiteralContext literal, Token op )
	{
		this.columnRef = columnRef;
		this.literal = literal;
		this.op = op;
	}
}

class Between extends Condition
{
	public LiteralContext lower;
	public LiteralContext upper;

	public Between( ColumnRefContext columnRef, LiteralContext lower, LiteralContext upper )
	{
		this.columnRef = columnRef;
		this.lower = lower;
		this.upper = upper;
	}
}

class IN extends Condition
{
	public List<LiteralContext> literals;

	public IN( ColumnRefContext columnRef, List<LiteralContext> literals )
	{
		this.columnRef = columnRef;
		this.literals = literals;
	}
}


public class FadoNested
{
	public static void main( String[] args )
			throws Exception
	{
//		CharStream chars = CharStreams.fromPath( Paths.get( sourceFile.getPath() ) );
//		CharStream chars = CharStreams.fromFileName( "/Users/jasonosgood/Projects/fado/test/NestedSelect.sql" );
		CharStream chars = CharStreams.fromFileName( "/Users/jasonosgood/Projects/fado/test/SelectCourseDescr.sql" );
		GenericSQLLexer lexer = new GenericSQLLexer( chars );
		CommonTokenStream tokens = new CommonTokenStream( lexer );
		GenericSQLParser parser = new GenericSQLParser( tokens );
		StatementContext statement = parser.statement();

		SelectList root = findSELECTs( statement );
		findFROMs( root );
		findWHEREs( root );

		Class.forName( "org.h2.Driver" );
		Connection conn = DriverManager.getConnection( "jdbc:h2:tcp://localhost/~/Projects/ambrose/db/cm", "sa", null );
		Map<String, Table> tableList = MetaData.getTablesAndColumns( conn );

		resolveFROMs( root, tableList );
//		inferTypes( root );
		// sort literals (stream order)
//		convertConditions( root );
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
		// query gathers every 'table' under 'from'
		for( GlobbingRuleContext tc : parent.context.find( "from/**/table" ) )
		{
			if( ( (TableContext) tc ).tableRef() != null )
			{
				String databaseName = tc.findFirstString( "**/databaseName" );
				String tableName = tc.findFirstString( "**/tableName" );
				String alias = tc.findFirstString( "**/aliasName" );
				From from = new From( databaseName, tableName, alias );
				parent.fromList.add( from );
			}
//			else
//			{
//				// TODO: support nested select, and I think maybe 'VALUES (...)'
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
//					GlobbingRuleContext node = literals.get( 0 );
//					String literal = node.getText();
//					literal = trimQuotes( literal );
//					node.convertToInputParam();
					parent.conditionList.add( new Comparison( columnRef, literal, op ) );
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
					parent.conditionList.add( new Between( columnRef, lower, upper ) );
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
						parent.conditionList.add( new IN( columnRef, literals ) );
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


	public static String trimQuotes( String text )
	{
		String result = text;
		int len = text.length();
		// Parser ensures token has quotes front and back
		if( text.indexOf( '[' ) > -1 || text.indexOf( '"' ) > -1 || text.indexOf( '\'' ) > -1 )
		{
			result = text.substring( 1, len - 1 );
		}
		return result;
	}


	static void resolveFROMs( SelectList parent, Map<String, Table> tableMap )
	{
		for( From from : parent.fromList )
		{
			from.table = tableMap.get( from.name.toLowerCase() );
			if( from.table == null )
			{
				System.out.printf( "from.name '%s' not found\n", from.name );
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
		String columnName = condition.columnRef.columnName().getText();
		// Nullsafe query to get tableName
		String tableName = condition.columnRef.findFirstString( "tableName" );
		tableName = ( tableName != null ? tableName : "*" );

		found:
		for( From from : parent.fromList )
		{
			if( from.table == null ) continue;
			if( tableName.equalsIgnoreCase( from.name ) || tableName.equalsIgnoreCase( from.alias ) || tableName.equals( "*" ))
			{
				Column column = from.table.columnMap.get( columnName.toLowerCase() );
				if( column != null )
				{
					condition.from = from;
					condition.column = column;
					break found;
				}
			}
		}

		if( condition.column == null )
		{
			System.out.printf( "condition.columnName '%s' not found\n", columnName );
		}
	}
}