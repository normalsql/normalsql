package fado.meta;

import static java.lang.Character.toLowerCase;
import static java.lang.Character.toUpperCase;

public class 
	Util 
{
	public static boolean isAllUpperCase( String s ) 
	{
		for( char c : s.toCharArray() ) 
		{
			if( Character.isLetter( c ) && Character.isLowerCase( c )) 
			{
				return false;
			}
		}
		return true;
	}
	
	// BIGPONY => BIGPONY
	// bigPony => BigPony
	public static String toMethodName( String name )
	{
		if( !Util.isAllUpperCase( name ))
		{
			name = toUpperCase( name.charAt( 0 ) ) + name.substring( 1 );
		}
		return name;
	}
	
	// BIGPONY => bigpony
	// BigPony => bigPony
	public static String toVariableName( String name )
	{
		if( Util.isAllUpperCase( name ))
		{
			name = name.toLowerCase();
		}
		else
		{
			name = toLowerCase( name.charAt( 0 ) ) + name.substring( 1 );
		}
		return name;
	}
}
