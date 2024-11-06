package normalsql;

import java.io.PrintStream;

public interface
	Echo
{
	void log( CharSequence msg );
	void log( Throwable error );
	void log( CharSequence msg, Throwable error );
	boolean isEnabled();

	class Basic implements Echo
	{
		public boolean disabled = false;

		public final PrintStream _out;
		public final CharSequence _prefix;

		public Basic( CharSequence prefix, PrintStream out )
		{
			_prefix = "[" + prefix + "] ";
			_out = out;
		}

		@Override
		public void log( CharSequence msg )
		{
			if( disabled ) return;
			log( msg, null );
		}

		@Override
		public void log( Throwable error )
		{
			if( disabled ) return;
			log( null, error );
		}

		@Override
		public void log( CharSequence msg, Throwable error )
		{
			if( disabled ) return;

			if( error == null && msg == null )
			{
				var npe = new NullPointerException( "both 'error' and 'msg' are null" );
				log( "error logger throwing an error. ironic.", npe );
				throw npe;
			}

			_out.print( _prefix );

			if( msg != null )
			{
				_out.println( msg );
			}

			if( error != null )
			{
				if( msg != null )
				{
					_out.println();
				}
				error.printStackTrace( _out );
				_out.println();
			}
		}

		public boolean isEnabled() { return !disabled; }

	}


}
