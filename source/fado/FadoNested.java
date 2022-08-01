package fado;

import fado.meta.Comparison;
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
	public ArrayList<Condition> conditionList = new ArrayList<>();
}

class Table
{
	public String database;
	public String name;
	public String alias;

	public Table( String d, String n, String a )
	{
		database = d;
		name = n;
		alias = a;
	}
}

class Condition
{
	public ColumnRefContext columnRef;
	public LiteralContext literal;
	public Token op;
	public Condition( ColumnRefContext c, LiteralContext l, Token o )
	{
		columnRef = c;
		literal = l;
		op = o;
	}
}

public class FadoNested
{
	public final static void main( String[] args )
		throws Exception
	{
//		CharStream chars = CharStreams.fromPath( Paths.get( sourceFile.getPath() ) );
		CharStream chars = CharStreams.fromFileName( "/Users/jasonosgood/Projects/fado/test/NestedSelect.sql" );
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
		// query gathers every 'table' under 'from', no matter how deep
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
		for( GlobbingRuleContext found : parent.context.find( "where/expression" ) )
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
			case ASSIGN:
			case NEQ1:
			case NEQ2:
			{
				// TODO allow right-to-left too
				ColumnRefContext columnRef = ec.left.columnRef();
				LiteralContext literal = ec.right.literal();
				if( columnRef != null && literal != null )
				{
					parent.conditionList.add( new Condition( columnRef, literal, op ));
//					GlobbingRuleContext node = literals.get( 0 );
//					String literal = node.getText();
//					literal = trimQuotes( literal );
//					node.convertToInputParam();
				}
				break;
			}

			// TODO LIKE, BETWEEN, IN

			// do nothing. listed here to be explicit.
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
