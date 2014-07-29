package fado.parse2;

/**
 * Created by jasonosgood on 6/29/14.
 */
public class Main
{
	public static void main( String[] args )
		throws Exception
	{
//		java.io.File sourceFile = new java.io.File( "/Users/jasonosgood/Projects/ambrose/sql/uwsdb/SelectCourseDetails.sql" );
		org.antlr.v4.runtime.ANTLRFileStream fileStream = new org.antlr.v4.runtime.ANTLRFileStream( "/Users/jasonosgood/Projects/ambrose/sql/uwsdb/SelectCourseDetails.sql" );

		GenericSQLLexer lexer = new GenericSQLLexer( fileStream );
		org.antlr.v4.runtime.CommonTokenStream tokens = new org.antlr.v4.runtime.CommonTokenStream(lexer);
		GenericSQLParser parser = new GenericSQLParser( tokens );
		parser.setBuildParseTree( true );

		fado.parse2.GenericSQLParser.StatementContext tigger = parser.statement();

		System.out.println( tigger );
//		parser.get
	}
}
