package fado;

import fado.parse.GenericSQLLexer;
import fado.parse.GenericSQLParser;
import static fado.parse.GenericSQLParser.*;
import fado.parse.GlobbingRuleContext;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;
import java.util.List;

class SelectList extends ArrayList<SelectList>
{
	public GlobbingRuleContext context;
	public ArrayList<Table> tableList = new ArrayList<>();
	public ArrayList<Object> conditionList = new ArrayList<>();
}

class Table
{
	public final String database;
	public final String name;
	public final String alias;

	public Table( String database, String name, String alias )
	{
		this.database = database;
		this.name = name;
		this.alias = alias;
	}
}

class Condition
{
	public final ColumnRefContext columnRef;
	public final LiteralContext literal;
	public final Token op;
	public Condition( ColumnRefContext columnRef, LiteralContext literal, Token op )
	{
		this.columnRef = columnRef;
		this.literal = literal;
		this.op = op;
	}
}

class Between
{
	public ColumnRefContext columnRef;
	public LiteralContext lower;
	public LiteralContext upper;

	public Between( ColumnRefContext columnRef, LiteralContext lower, LiteralContext upper )
	{
		this.columnRef = columnRef;
		this.lower = lower;
		this.upper = upper;
	}
}

class IN
{
	public ColumnRefContext columnRef;
	public List<LiteralContext> literals;

	public IN( ColumnRefContext columnRef, List<LiteralContext> literals )
	{
		this.columnRef = columnRef;
		this.literals = literals;
	}
}


public class FadoNested
{
	public final static void main( String[] args )
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
		processFROMs( root );
		processWHEREs( root );
		System.out.println( root.size() );

	}

	static SelectList findSELECTs( GlobbingRuleContext parent )
	{
		SelectList result = new SelectList();
		result.context = parent;

		for( GlobbingRuleContext context : parent.find( "**/select" ))
		{
			result.add( findSELECTs( context ));
		}

		return result;
	}

	static void processFROMs( SelectList parent )
	{
		// query gathers every 'table' under 'from'
		for( GlobbingRuleContext tc : parent.context.find( "from/**/table" ) )
		{
			if( ((TableContext) tc).tableRef() != null )
			{
				String databaseName = tc.findFirstString( "**/databaseName" );
				String tableName = tc.findFirstString( "**/tableName" );
				String alias = tc.findFirstString( "**/aliasName" );
				Table table = new Table( databaseName, tableName, alias );
				parent.tableList.add( table );
			}
//			else
//			{
//				// TODO: support nested select, and I think maybe 'VALUES (...)'
//			}
		}
		for( SelectList child : parent )
		{
			processFROMs( child );
		}
	}

	static void processWHEREs( SelectList parent )
	{
		for( GlobbingRuleContext found : parent.context.find( "where/expression" ))
		{
			processExpression( parent, (ExpressionContext) found );
		}

		for( SelectList child : parent )
		{
			processWHEREs( child );
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
					parent.conditionList.add( new Condition( columnRef, literal, op ));
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
					List<LiteralContext> literals = list.find( LiteralContext.class,"expression/literal" );
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
}
