package fado.drill;

import fado.parse.GenericSQLBaseVisitor;
import fado.parse.GenericSQLLexer;
import fado.parse.GenericSQLParser;
import fado.parse.GenericSQLParser.*;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.ArrayList;
import java.util.Stack;

class Select
{
	public ArrayList fromList = new ArrayList<>();
}

//class From
//{
//	public Table table;
//	public String alias;
//}

class Table
{

}

public class Visit
extends GenericSQLBaseVisitor
{
	public static void main( String... args )
	{
		String sql = "SELECT 1;";
		CharStream chars = CharStreams.fromString( sql );
		GenericSQLLexer lexer = new GenericSQLLexer( chars );
		CommonTokenStream tokens = new CommonTokenStream( lexer );
		GenericSQLParser parser = new GenericSQLParser( tokens );
		StatementContext e = parser.statement();

		Visit visit = new Visit();
		visit.visitStatement( e );
//		System.out.println( e.toStringTree( parser ) );
//		System.out.println( e.toStringTree( parser ) );
	}

	Stack<Select> stack = new Stack<>();

	@Override
	public Object visitSelect( SelectContext ctx )
	{
		Select s = new Select();
		stack.push( s );
		super.visitSelect( ctx );
		return stack.pop();
	}

//	@Override
//	public Object visitTable( TableContext ctx )
//	{
//		Select s = stack.peek();
//		Table f = new Table();
////		ctx.tableRef().
//		s.fromList.add( f );
//		return super.visitTable( ctx );
//	}
}
