package test;

import normalsql.parse.SqlBaseParser;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ReservedParser extends SqlBaseParser
{
	public ReservedParser( TokenStream input )
	{
		super( input );
		loadReservedKeywords();
	}

	public final static void main( String[] arg ) throws Exception
	{
		ReservedParser me = new ReservedParser( null );
	}

	public void loadReservedKeywords()
	{
		try
		{
			ClassLoader cl = Thread.currentThread().getContextClassLoader();
			URL resource = cl.getResource( "sql-92_reserved.txt" );
			List<String> lines = Files.readAllLines( Paths.get( resource.toURI() ));
			for( String line : lines )
			{
				int comment = line.indexOf( '#' );
				if( comment > -1 )
				{
					line = line.substring( 0, comment );
				}
				line = line.trim();

				if( !line.isEmpty() )
				{
					_reserved.add( line.toUpperCase() );
				}
			}
//			System.out.println( "reserved keywords: " + _reserved.size() );
		}
		catch( IOException e )
		{
			throw new RuntimeException( e );
		}
		catch( URISyntaxException e )
		{
			throw new RuntimeException( e );
		}
	}

	HashSet<String> _reserved = new HashSet<>();

	public boolean isPunctuation( String text )
	{
		char a = text.charAt( 0 );
		return ",.;:{}()<>%+-=/*?|".indexOf( a ) != -1;
	}

	public boolean isValidKeyword()
	{
		Token t = this.getCurrentToken();
		String s = t.getText();
		if( !isPunctuation( s ) )
		{
			boolean contains = _reserved.contains( s.toUpperCase() );
			return !contains;
		}
		return false;
	}
}
