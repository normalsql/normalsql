package fado;

import java.io.IOException;

import joptsimple.OptionParser;
import joptsimple.OptionSet;

// TODO: validate
// TODO: print help
public class FadoOptions
{
	public FadoOptions()
	{
	}

	public final static String DRIVER = "driver";
	public final static String URL = "url";
	public final static String USERNAME = "username";
	public final static String PASSWORD = "password";
	public final static String SOURCE = "source";
	public final static String TARGET = "target";
	public final static String PROPERTY = "property";
	public final static String ONLYPARSE = "onlyparse";

	String _filename = "fado";

	Properties _props = null;

	OptionSet _options = null;

	public String getDriver()
	{
		return coalesce( (String) _options.valueOf( DRIVER ), _props.get( DRIVER ) );
	}

	public String getUrl()
	{
		return coalesce( (String) _options.valueOf( URL ), _props.get( URL ) );
	}

	public String getUsername()
	{
		return coalesce( (String) _options.valueOf( USERNAME ), _props.get( USERNAME ) );
	}

	public String getPassword()
	{
		return coalesce( (String) _options.valueOf( PASSWORD ), _props.get( PASSWORD ) );
	}

	public String getSource()
	{
		return coalesce( (String) _options.valueOf( SOURCE ), _props.get( SOURCE ) );
	}

	public String getTarget()
	{
		return coalesce( (String) _options.valueOf( TARGET ), _props.get( TARGET ) );
	}
	
	public boolean getOnlyParse()
	{
		String onlyParse = coalesce( (String) _options.valueOf( ONLYPARSE ), _props.get( ONLYPARSE ));
		return Boolean.valueOf( onlyParse );
	}

	public void parse( String[] args )
	{
		OptionParser parser = new OptionParser();
		parser.accepts( DRIVER ).withRequiredArg();
		parser.accepts( URL ).withRequiredArg();
		parser.accepts( USERNAME ).withRequiredArg();
		parser.accepts( PASSWORD ).withRequiredArg();

		parser.accepts( SOURCE ).withRequiredArg();
		parser.accepts( TARGET ).withRequiredArg();
		parser.accepts( PROPERTY ).withRequiredArg().defaultsTo( _filename );
		parser.accepts( ONLYPARSE ).withRequiredArg();

		_options = parser.parse( args );

		try
		{
			_props = new Properties();
			String filename = (String) _options.valueOf( PROPERTY );
			_props.load( filename );
		}
		catch( IOException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String coalesce( String... values )
	{
		for( String value : values )
		{
			if( value != null ) return value;
		}
		return null;
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append( "fado properties loaded from " ).append( _props.getURL() ).append( '\n' );
		sb.append( '\n' );
		sb.append( DRIVER ).append( " = " ).append( getDriver() ).append( '\n' );
		sb.append( URL ).append( " = " ).append( getUrl() ).append( '\n' );
		sb.append( USERNAME ).append( " = " ).append( getUsername() ).append( '\n' );
		sb.append( PASSWORD ).append( " = " ).append( getPassword() ).append( '\n' );
		sb.append( SOURCE ).append( " = " ).append( getSource() ).append( '\n' );
		sb.append( TARGET ).append( " = " ).append( getTarget() ).append( '\n' );
		sb.append( ONLYPARSE ).append( " = " ).append( Boolean.valueOf( getOnlyParse() ));
		sb.append( '\n' );
		return sb.toString();
	}

}
