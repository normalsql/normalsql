// Copyright 2010-2025 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Very simple properties file. Preserves order of entries in file, including comments and blank lines.
 *
 * Format of each line:
 *
 * key = value # comment
 *
 * If duplicate key is in file, a later key entry hides the prior entry. For example
 *
 * fruit = apple fruit = banana
 *
 * The "banana" is the value for key "fruit".
 *
 * Writing out the properties file "pretty prints" the data.
 *
 * @author jasonosgood
 * @version $Id: $Id
 */
public class Props
{
	ArrayList<Line> lines = null;
	HashMap<String, Line> map = null;

	/**
	 * <p>Constructor for Config.</p>
	 */
	public Props()
	{
		clear();
	}

	private URL _url = null;
	
	public void setPropertiesFile( URL url ) { _url = url; }
	
	public URL getPropertiesFile() { return _url; }
	
	/**
	 * <p>clear.</p>
	 */
	public void clear()
	{
		lines = new ArrayList<>();
		map = new HashMap<>();

	}

	/**
	 * <p>set.</p>
	 *
	 * @param key a {@link java.lang.String} object
	 * @param value a {@link java.lang.String} object
	 */
	public void set( String key, String value )
	{
		if( key == null )
		{
			throw new NullPointerException( "key" );
		}

		Line line = null;
		if( map.containsKey( key ) )
		{
			line = map.get( key );
			line.value = value;
		}
		else
		{
			line = new Line();
			line.key = key;
			line.value = value;
			lines.add( line );
			map.put( key, line );

		}
	}

	/**
	 * <p>get.</p>
	 *
	 * @param key a {@link java.lang.String} object
	 * @return a {@link java.lang.String} object
	 */
	public String get( String key )
	{
		return get( key, null );
	}

	/**
	 * <p>get.</p>
	 *
	 * @param key a {@link java.lang.String} object
	 * @param defaultValue a {@link java.lang.String} object
	 * @return a {@link java.lang.String} object
	 */
	public String get( String key, String defaultValue )
	{
		if( key == null )
		{
			throw new NullPointerException( "key" );
		}
		String value = defaultValue;
		if( map.containsKey( key ) )
		{
			Line line = map.get( key );
			Object temp = line.value;
			if( temp != null )
			{
				value = temp.toString();
			}
		}
		return value;
	}

	/**
	 * Feed in list of parameters which make up the compound key. Null parameters and empty strings are dropped.
	 *
	 * @param args a {@link java.lang.String} object
	 * @return a {@link java.lang.String} object
	 */
	public String getCompound( String... args )
	{
		String key = merge( args );
		return get( key );
	}

	/**
	 * <p>merge.</p>
	 *
	 * @param args a {@link java.lang.String} object
	 * @return a {@link java.lang.String} object
	 */
	public String merge( String... args )
	{
		StringBuilder sb = new StringBuilder();
		boolean more = false;
		for( String arg : args )
		{
			if( more )
			{
				sb.append( "." );
			}
			sb.append( arg );
			more = true;
		}

		return sb.toString();
	}

	/**
	 * <p>remove.</p>
	 *
	 * @param key a {@link java.lang.String} object
	 */
	public void remove( String key )
	{
		if( key == null )
		{
			throw new NullPointerException( "key" );
		}
		if( map.containsKey( key ) )
		{
			Line line = map.get( key );
			map.remove( key );
			lines.remove( line );
		}
	}

	/**
	 * <p>isEmpty.</p>
	 *
	 * @return a boolean
	 */
	public boolean isEmpty()
	{
		return lines.isEmpty();
	}

	static class Line
	{
		String key;
		String value;
		String comment;

		public String toString()
		{
			StringBuilder sb = new StringBuilder();
			if( key != null && value != null )
			{
				sb.append( key );
				sb.append( " = " );
				sb.append( value.toString() );
				sb.append( " " );
			}
			if( comment != null )
			{
				sb.append( "# " );
				sb.append( comment );
			}
			return sb.toString();
		}
	}

	/**
	 * <p>load.</p>
	 *
	 * @param filename a {@link java.lang.String} object
	 * @return a {@link Props} object
	 * @throws java.io.IOException if any.
	 */
	public static Props load( String filename ) throws IOException
	{
		ClassLoader loader = Props.class.getClassLoader();
		URL url = loader.getResource( filename + ".properties" );
		if( url != null )
		{
			InputStream in = url.openStream();
			InputStreamReader reader = new InputStreamReader( in );
			Props props = load( reader );
			props._url = url;
			return props;
		}
		throw new FileNotFoundException( filename );
	}
	
	/**
	 * <p>load.</p>
	 *
	 * @param file a {@link java.io.File} object
	 * @return a {@link Props} object
	 * @throws java.io.IOException if any.
	 */
	public static Props load(File file )
		throws IOException
	{
		if( file.exists() )
		{
			try( FileReader reader = new FileReader( file ))
			{
				Props props = load( reader );
				props._url = file.toURI().toURL();
				return props;
			}
		}
		throw new FileNotFoundException( file.toString() );
	}

	/**
	 * <p>load.</p>
	 *
	 * @param reader a {@link java.io.Reader} object
	 * @return a {@link Props} object
	 * @throws java.io.IOException if any.
	 */
	public static Props load(Reader reader ) throws IOException
	{
		if( reader == null ) 
		{
			throw new NullPointerException( "reader" );
		}
		
		Props props = new Props();
		try( BufferedReader buffer = new BufferedReader( reader ))
		{
			while( true )
			{
				String data = buffer.readLine();
				if( data == null ) break;

				Line line = new Line();

				int comment = data.indexOf( '#' );
				if( comment > -1 )
				{
					String temp = data.substring( comment + 1 );
					line.comment = temp;
					data         = data.substring( 0, comment );
				}

				int equal = data.indexOf( '=' );
				if( equal > -1 )
				{
					String key = data.substring( 0, equal );
					line.key = key.trim();
					String value = data.substring( equal + 1 );
					line.value = value.trim();
				}
				props.lines.add( line );
				if( line.key != null )
				{
					props.map.put( line.key, line );
				}
			}
		}

		return props;
	}

	/**
	 * <p>store.</p>
	 *
	 * @param writer a {@link java.io.Writer} object
	 * @throws java.io.IOException if any.
	 */
	public void store( Writer writer ) throws IOException
	{
		PrintWriter pw = new PrintWriter( writer );
		for( Line line : lines )
		{
			if( line != null )
			{
				pw.println( line.toString() );
			}
		}
		writer.close();
	}

	/**
	 * <p>toString.</p>
	 *
	 * @return a {@link java.lang.String} object
	 */
	public String toString()
	{
		StringWriter sw = new StringWriter();
		try
		{
			store( sw );
		}
		catch( IOException e )
		{
			// Do nothing
		}
		return sw.toString();
	}

	/**
	 * <p>toProperties.</p>
	 *
	 * @return a {@link java.util.Properties} object
	 */
	public java.util.Properties toProperties()
	{
		java.util.Properties props = new java.util.Properties();
		for( Map.Entry<String, Line> entry : map.entrySet() )
		{
			String key = entry.getKey();
			Line line = entry.getValue();
			if( line != null )
			{
				String value = line.value;
				if( value != null )
				{
					props.put( key, value );
				}
			}
		}
		return props;
	}

	/**
	 * <p>fromProperties.</p>
	 *
	 * @param props a {@link java.util.Properties} object
	 */
	public void fromProperties( java.util.Properties props )
	{
		for( Map.Entry<Object, Object> entry : props.entrySet() )
		{
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();
			set( key, value );
		}
	}

	/**
	 * <p>hashCode.</p>
	 *
	 * @return a int
	 */
	public int hashCode()
	{
		return map.hashCode();
	}


	public final static String DRIVER = "driver";
	public final static String URL = "url";
	public final static String USERNAME = "username";
	public final static String PASSWORD = "password";
	public final static String PACKAGE = "package";
	public final static String SOURCE = "source";
	public final static String TARGET = "target";

	public String getDriver()
	{
		return get( DRIVER );
	}

	public String getURL()
	{
		return get( URL );
	}

	public String getUsername()
	{
		return get( USERNAME );
	}

	public String getPassword()
	{
		return get( PASSWORD );
	}


}
