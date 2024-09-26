// Copyright 2010-2023 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql;


// TODO option for template to use
/**
 * <p>CommandLineOptions class.</p>
 *
 * @author jasonosgood
 * @version $Id: $Id
 */
public class CommandLineOptions
{
	public static void main( String[] args )
	{
		CommandLineOptions options = new CommandLineOptions();
		String[] oops = { "-user", "sa", "-password", "root" };
		options.parse( oops );
	}


	Props _props = new Props();

	public static CommandLineOptions parse( String[] args )
	{

//		String filename = (String) me._options.valueOf( PROPERTY );
//		if( !filename.endsWith( ".properties" ))
//		{
//			filename = filename + ".properties";
//		}
//
//		// TODO try straight filename then file in current dir. do not try classpath.
//
//		try
//		{
//			File file = new File( filename );
//			if( !file.exists() )
//			{
//				File cwd = new File( "." ).getCanonicalFile();
//				file = new File( cwd, filename );
//				if( !file.exists() )
//				{
//					file = null;
//				}
//			}
//
//			if( file != null )
//			{
//				FileReader reader = new FileReader( file );
//				me._props = Props.load( reader );
//				me._props.setPropertiesFile( file.toURI().toURL() );
//				me._loaded = true;
//			}
//			else
//			{
//				System.err.printf( "properties file '%s' not found\n", filename );
//			}
//		}
//		catch( Exception e )
//		{
//			e.printStackTrace();
//		}
//
//		return me;

		return null;
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


}
