// Copyright 2010-2023 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql;

import joptsimple.OptionParser;
import joptsimple.OptionSet;

import java.io.File;
import java.io.FileReader;

// TODO: validate
// TODO: print help
// TODO option for template to use
/**
 * <p>CommandLineOptions class.</p>
 *
 * @author jasonosgood
 * @version $Id: $Id
 */
public class CommandLineOptions
{
//	/**
//	 * <p>Constructor for CommandLineOptions.</p>
//	 */
//	public CommandLineOptions()
//	{
//	}

//	/**
//	 * <p>Constructor for CommandLineOptions.</p>
//	 *
//	 * @param props a {@link normalsql.Config} object
//	 */
//	public CommandLineOptions( Config props )
//	{
//		if( props == null )
//		{
//			throw new NullPointerException( "props" );
//		}
//		_props = props;
//	}

	/** Constant <code>DRIVER="driver"</code> */
	public final static String DRIVER = "driver";
	/** Constant <code>URL="url"</code> */
	public final static String URL = "url";
	/** Constant <code>USERNAME="username"</code> */
	public final static String USERNAME = "username";
	/** Constant <code>PASSWORD="password"</code> */
	public final static String PASSWORD = "password";
	/** Constant <code>PACKAGE="package"</code> */
	public final static String PACKAGE = "package";
	/** Constant <code>SOURCE="source"</code> */
	public final static String SOURCE = "source";
	/** Constant <code>TARGET="target"</code> */
	public final static String TARGET = "target";
	/** Constant <code>PROPERTY="property"</code> */
	public final static String PROPERTY = "property";
	/** Constant <code>ONLYPARSE="onlyparse"</code> */
	public final static String ONLYPARSE = "onlyparse";

	boolean _loaded = false;


	String _filename = "normalsql";

	Config _props = new Config();

	OptionSet _options = null;

	/**
	 * <p>getDriver.</p>
	 *
	 * @return a {@link java.lang.String} object
	 */
	public String getDriver()
	{
		return coalesce( (String) _options.valueOf( DRIVER ), _props.get( DRIVER ) );
	}

	/**
	 * <p>getUrl.</p>
	 *
	 * @return a {@link java.lang.String} object
	 */
	public String getURL()
	{
		return coalesce( (String) _options.valueOf( URL ), _props.get( URL ) );
	}

	/**
	 * <p>getUsername.</p>
	 *
	 * @return a {@link java.lang.String} object
	 */
	public String getUsername()
	{
		return coalesce( (String) _options.valueOf( USERNAME ), _props.get( USERNAME ), "" );
	}

	/**
	 * <p>getPassword.</p>
	 *
	 * @return a {@link java.lang.String} object
	 */
	public String getPassword()
	{
		return coalesce( (String) _options.valueOf( PASSWORD ), _props.get( PASSWORD ), "" );
	}

	/**
	 * <p>getSource.</p>
	 *
	 * @return a {@link java.lang.String} object
	 */
	public String getSource()
	{
		return coalesce( (String) _options.valueOf( SOURCE ), _props.get( SOURCE ), "." );
	}

	/**
	 * <p>getPackage.</p>
	 *
	 * @return a {@link java.lang.String} object
	 */
	public String getPackage()
	{
		return coalesce( (String) _options.valueOf( PACKAGE ), _props.get( PACKAGE ), "" );
	}

	/**
	 * <p>getTarget.</p>
	 *
	 * @return a {@link java.lang.String} object
	 */
	public String getTarget()
	{
		return coalesce( (String) _options.valueOf( TARGET ), _props.get( TARGET ), "." );
	}
	
	/**
	 * <p>getOnlyParse.</p>
	 *
	 * @return a boolean
	 */
	public boolean getOnlyParse()
	{
		String onlyParse = coalesce( (String) _options.valueOf( ONLYPARSE ), _props.get( ONLYPARSE ), "false" );
		return Boolean.parseBoolean( onlyParse );
	}

	/**
	 * <p>parse.</p>
	 *
	 * @param args an array of {@link java.lang.String} objects
	 */
	public static CommandLineOptions parse( String[] args )
	{
		CommandLineOptions me = new CommandLineOptions();
		OptionParser parser = new OptionParser();
		parser.accepts( DRIVER ).withRequiredArg();
		parser.accepts( URL ).withRequiredArg();
		parser.accepts( USERNAME ).withRequiredArg();
		parser.accepts( PASSWORD ).withRequiredArg();
		
		parser.accepts( PACKAGE ).withRequiredArg();
		parser.accepts( SOURCE ).withRequiredArg();
		parser.accepts( TARGET ).withRequiredArg();
		parser.accepts( PROPERTY ).withRequiredArg().defaultsTo( me._filename );
		parser.accepts( ONLYPARSE ).withRequiredArg();

		me._options = parser.parse( args );

		String filename = (String) me._options.valueOf( PROPERTY );
		if( !filename.endsWith( ".properties" )) 
		{
			filename = filename + ".properties";
		}

		// TODO try straight filename then file in current dir. do not try classpath.
		
		try
		{
			File file = new File( filename );
			if( !file.exists() ) 
			{
				File cwd = new File( "." ).getCanonicalFile();
				file = new File( cwd, filename );
				if( !file.exists() ) 
				{
					file = null;
				}
			}
			
			if( file != null ) 
			{
				FileReader reader = new FileReader( file );
				me._props = Config.load( reader );
				me._props.setPropertiesFile( file.toURI().toURL() );
				me._loaded = true;
			}
			else
			{
				System.err.printf( "properties file '%s' not found\n", filename );
			}
		}
		catch( Exception e ) 
		{
			e.printStackTrace();
		}

		return me;
	}

	/**
	 * <p>coalesce.</p>
	 *
	 * @param values a {@link java.lang.String} object
	 * @return a {@link java.lang.String} object
	 */
	public static String coalesce( String... values )
	{
		for( String value : values )
		{
			if( value != null ) return value;
		}
		return null;
	}

	// TODO report if .properties not found
	/**
	 * <p>toString.</p>
	 *
	 * @return a {@link java.lang.String} object
	 */
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		if( _loaded )
		{
			sb.append( "NormalSQL properties loaded from " ).append( _props.getPropertiesFile() ).append( '\n' );
			sb.append( '\n' );
			sb.append( DRIVER ).append( " = " ).append( getDriver() ).append( '\n' );
			sb.append( URL ).append( " = " ).append( getURL() ).append( '\n' );
			sb.append( USERNAME ).append( " = " ).append( getUsername() ).append( '\n' );
			sb.append( PASSWORD ).append( " = " ).append( getPassword() ).append( '\n' );
			sb.append( PACKAGE ).append( " = " ).append( getPackage() ).append( '\n' );
			sb.append( SOURCE ).append( " = " ).append( getSource() ).append( '\n' );
			sb.append( TARGET ).append( " = " ).append( getTarget() ).append( '\n' );
			sb.append( ONLYPARSE ).append( " = " ).append( Boolean.valueOf( getOnlyParse() ));
			sb.append( '\n' );
		}
		else
		{
			sb.append( "no properties loaded" );
		}
		return sb.toString();
	}

}
