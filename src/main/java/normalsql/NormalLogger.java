package normalsql;

import java.io.PrintStream;
import java.io.PrintWriter;

public class
	NormalLogger
implements
	Logger
{
	final PrintStream _out;

	public NormalLogger()
	{
		_out = System.out;
	}

	public NormalLogger( PrintStream out )
	{
		_out = out;
	}

	/**
	 * @param msg
	 */
	@Override
	public void log( String... msg )
	{
		_out.println( msg );
	}
}
