package fado.voyager;

import fado.parse.GenericSQLBaseListener;
import fado.parse.GenericSQLParser;
import static java.lang.System.out;

public class VoyagerListener
	extends
	GenericSQLBaseListener
{
	@Override
	public void exitSelect( GenericSQLParser.SelectContext ctx )
	{
		out.println( ctx.getText() );
	}
}
