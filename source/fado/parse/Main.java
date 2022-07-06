package fado.parse;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;

import java.util.List;

//import static fado.parse.GenericSQLParser.RULE_select;
import static fado.parse.GenericSQLParser.*;

import fado.meta.*;

/**
 * Created by jasonosgood on 6/29/14.
 */
public class Main
{
	public static void main( String[] args )
		throws Exception
	{
		CharStream chars = CharStreams.fromFileName("/Users/jasonosgood/Projects/ambrose/sql/uwsdb/SelectCourseDetails.sql" );
		GenericSQLLexer lexer = new GenericSQLLexer( chars );
		org.antlr.v4.runtime.CommonTokenStream tokens = new org.antlr.v4.runtime.CommonTokenStream(lexer);
		GenericSQLParser parser = new GenericSQLParser( tokens );

		StatementContext statementContext = parser.statement();

		GlobbingRuleContext zz = statementContext.getChild( GlobbingRuleContext.class, 0 );
		List<GlobbingRuleContext> fromItemList = zz.findNodes( "from/fromItem" );

		for( GlobbingRuleContext fromItem : fromItemList )
		{
			GlobbingRuleContext tableRef = fromItem.findFirstNode( "tableRef" );
			String databaseName = tableRef.findFirstString( "databaseName" );
			String tableName = tableRef.findFirstString( "**/tableName" );
			String alias = fromItem.findFirstString( "alias" );
			Table table = new Table( databaseName, tableName, alias );
//			statement.addTable( table );
		}


//		switch( zz.getRuleIndex() )
//		{
//			case RULE_select:
//			{
//				System.out.println( zz.getText() );
//				Collection<ParseTree> abc = XPath.findAll( zz, "/from", parser );
//				Collection<ParseTree> abc2 = XPath.findAll( pt1, "/from/fromItem", parser );
//				System.out.println( abc );
//			}
//		}


//		SelectContext select = statement.select();
//		FromContext from = select.from();
//		List<FromItemContext> fromItems = from.fromItem();
//		for( FromItemContext fromItem : fromItems )
//		{
//			TableRefContext tableRefContext = fromItem.tableRef();
//			TableNameContext tableName = tableRefContext.tableName();
//			DatabaseNameContext databaseNameContext = tableRefContext.databaseName();
//		}

//		Collection<ParseTree> shit = XPath.findAll( statement, "//fudge/*", parser );
//		Collection<ParseTree> statements = XPath.findAll( statement, "//statement/*", parser );
//		for( ParseTree pt : statements )
//		{
//			ParserRuleContext pt1 = (ParserRuleContext) pt;
//			int ruleIndex = pt1.getRuleIndex();
//			switch( ruleIndex )
//			{
//				case RULE_select:
//				{
//					System.out.println( pt1.getText() );
//					Collection<ParseTree> abc = XPath.findAll( pt1, "/from", parser );
//					Collection<ParseTree> abc2 = XPath.findAll( pt1, "/from/fromItem", parser );
//					System.out.println( abc );
//				}
//			}
//			System.out.println( pt );
//		}
	}
}
