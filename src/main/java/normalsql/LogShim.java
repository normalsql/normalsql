package normalsql;

public interface
	LogShim
{
	void log( CharSequence msg );
	void log( Throwable error );
	void log( CharSequence msg, Throwable error );
	boolean isEnabled();
}
