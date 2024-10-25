package normalsql;

import java.io.PrintStream;

public interface
	Echo
{
	void log( CharSequence msg );
	void log( Throwable error );
	void log( Throwable error, CharSequence msg );

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
			log( null, msg );
		}

		@Override
		public void log( Throwable error )
		{
			if( disabled ) return;
			log( error, null );
		}

		@Override
		public void log( Throwable error, CharSequence msg )
		{
			if( disabled ) return;

			if( error == null && msg == null )
			{
				var npe = new NullPointerException( "both 'error' and 'msg' are null" );
				log( npe, "error logger throwing an error. ironic." );
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
	}


}
