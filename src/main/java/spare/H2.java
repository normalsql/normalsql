package spare;

import org.h2.tools.Server;

import java.io.File;

/**
 * <p>H2 class.</p>
 *
 * @author jasonosgood
 * @version $Id: $Id
 */
public class H2
{
	/**
	 * <p>main.</p>
	 *
	 * @param args an array of {@link java.lang.String} objects
	 * @throws java.lang.Exception if any.
	 */
	public static void main( String[] args ) throws Exception
	{
		File file = new File( "." );
		System.out.println( file.getCanonicalPath() );
		start();
		// stop();
		// Server server = Server.createTcpServer( args );
		// server.start();
		// System.out.println( server.getURL() );
		// System.out.println( server.isRunning() );
	}

	private static boolean _tcpStarted = false;
	private static boolean _webStarted = false;

	/**
	 * <p>start.</p>
	 */
	public static void start()
	{
		Server server = null;

		if( !_tcpStarted )
		{
			try
			{
				server = Server.createTcpServer( new String[]{ "-tcp", "-tcpAllowOthers" } );
				server.start();
				System.out.println( "Started H2 TCP Server: " + server.getURL() );

				_tcpStarted = true;
			}
			catch( Exception e )
			{
				System.out.println( "H2 TCP Server is already running" );
			}
		}

		if( !_webStarted )
		{
			try
			{
				server = Server.createWebServer( new String[]{} );
				server.start();
				System.out.println( "Started H2 Web Server: " + server.getURL() );

				_webStarted = true;
			}
			catch( Exception e )
			{
				System.out.println( "H2 Web Server is already running" );
			}
		}
	}

	/**
	 * <p>stop.</p>
	 */
	public static void stop()
	{
		Server server = null;

		if( _tcpStarted )
		{
			try
			{
				server = Server.createTcpServer( new String[]{} );
				server.stop();
				System.out.println( "Stopped H2 TCP Server: " + server.getURL() );

				_tcpStarted = false;
			}
			catch( Exception e )
			{
				System.out.println( "H2 TCP Server could not be stopped:" );
				e.printStackTrace( System.err );
			}
		}

		if( _webStarted )
		{
			try
			{
				server = Server.createWebServer( new String[]{} );
				try
				{
					server.start();
				}
				catch( RuntimeException e )
				{
					e.printStackTrace( System.err );
				}
				server.stop();
				System.out.println( "Stopped H2 Web Server: " + server.getURL() );

				_webStarted = false;
			}
			catch( Exception e )
			{
				System.out.println( "H2 Web Server could not be stopped:" );
				e.printStackTrace( System.err );
			}
		}

	}
}
