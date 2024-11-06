package normalsql;

import org.apache.maven.plugin.logging.Log;

import java.util.function.Consumer;

class MavenEcho implements Echo
{
	public final String prefix;
	public final Log log;

	public Consumer<CharSequence> a;

	public MavenEcho( String prefix, Log log )
	{
		this.prefix = prefix;
		this.log = log;
	}

	@Override
	public void log( CharSequence msg )
	{
		switch( prefix )
		{
			case "info" -> log.info( msg );
			case "warn" -> log.warn( msg );
			case "debug" -> log.debug( msg );
			case "error" -> log.error( msg );
            default ->
                throw new IllegalStateException("Unexpected value: " + prefix);
        }
	}

	@Override
	public void log( Throwable error )
	{
		switch( prefix )
		{
			case "info": log.info( error ); return;
			case "warn": log.warn( error ); return;
			case "debug": log.debug( error ); return;
			case "error": log.error( error ); return;
		}
	}

	@Override
	public void log( CharSequence msg, Throwable error )
	{

	}

	@Override
	public boolean isEnabled()
	{
		return false;
	}

	public void log( Throwable error, CharSequence msg )
	{
		switch( prefix )
		{
			case "info": log.info( msg, error ); return;
			case "warn": log.warn( msg, error ); return;
			case "debug": log.debug( msg, error ); return;
			case "error": log.error( msg, error ); return;
		}
	}
}
