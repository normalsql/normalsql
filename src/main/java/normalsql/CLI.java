// Copyright 2010-2024 Jason Osgood
// SPDX-License-Identifier: Apache-2.0


package normalsql;

import java.util.ArrayList;
import java.util.List;

record Spot ( String small, String big, String blurb, Object filler ){}

public class CLI
{
    List<String> nu;

    List<Spot> spots = new ArrayList<Spot>();

    public static void main( String[] args )
    {
//        var cli = new CLI( new String[]{"-x"} );
        var cli = new CLI( new String[]{"-f", "tangerine", "-x"} );
        String fave = cli.getOptional( "pineapple", "-f", "--fruit", "favorite fruit" );
        boolean help = cli.getFlag( "-h", "--help", "show help" );
        boolean version = cli.getFlag( "-v", "--version", "show version" );
        if( cli.ok() )
        {
            System.out.println( fave );
        }
    }

    public CLI( String[] args )
    {
        nu = new ArrayList<>();
        if( args != null )
        {
            for( var arg : args )
            {
                // IIRC this is a shell (bash) thing. Everything after "--" is ignored,
                // to be passed thru to next program.
                if( arg.equals( "--" ) ) break;
                nu.add( arg );
            }
        }
    }
    // TODO getRequired
    // TODO getFlag

    public <T> T getOptional( T def, String... keys )
    {
        if( def == null ) throw new NullPointerException( "default value cannot be null" );
        if( keys == null ) throw new NullPointerException( "key(s) cannot be null" );

        int size = nu.size();
        for( int i = 0; i < size; i++ )
        {
            for( var key : keys )
            {
                if( nu.get( i ).equals( key ) )
                {
                    if( i + 1 < size )
                    {
                        nu.remove( i );
                        var value = nu.remove( i );

	                    return switch( def )
	                    {
	                        case String ignore -> (T) value;
	                        case Integer ignore -> (T) Integer.valueOf( value );
	                        default ->
	                        {
	                            String name =  def.getClass().getCanonicalName();
	                            throw new IllegalArgumentException( "don't know how to convert/cast: " + name );
	                        }
	                    };
                    }
                    else
                    {
                        throw new IllegalArgumentException( "missing value for key: " + key );
                    }
                }
            }
        }
        return def;
    }

    public <T> T getOptional( T filler, String small, String big, String blurb )
    {

        if( filler == null ) { throw new NullPointerException( "default value cannot be null" ); }
        if( small == null && big == null ) { throw new NullPointerException( "both small and big keys cannot be null" ); }

        spots.add( new Spot( small, big, blurb, filler ));

        int size = nu.size();
        for( int i = 0; i < size; i++ )
        {
            var key = nu.get( i );
            if( key.equals( small ) || key.equals( big ) )
            {
                if( i + 1 < size )
                {
                    // "consume" token for key
                    nu.remove( i );
                    // get and then "consume" token for value
                    var value = nu.remove( i );

                    return switch( filler )
                    {
                        case String ignore -> (T) value;
                        case Integer ignore -> (T) Integer.valueOf( value );
                        default ->
                        {
                            String name = filler.getClass().getCanonicalName();
                            throw new IllegalArgumentException( "don't know how to convert/cast: " + name );
                        }
                    };
                }
                else
                {
                    throw new IllegalArgumentException( "missing value for key: " + key );
                }
            }
        }
        return filler;
    }


    public boolean getFlag( String small, String big, String blurb )
    {
        spots.add( new Spot( small, big, "blurb", null ));

        int size = nu.size();
        for( int i = 0; i < size; i++ )
        {
            var arg = nu.get( i );
            if( arg.equals( small ) || arg.equals( big ) )
            {
                // "consume" token
                nu.remove( i );
                return true;
            }
        }
        return false;
    }

    public boolean ok()
    {
        if( !nu.isEmpty() )
        {
            String extra = String.join( " ", nu );
            System.out.println( "unknown parameter(s): " + extra );
            System.out.println(  );
            System.out.println( "TODO: help info happens here" );
            for( var spot : spots )
            {
                System.out.println( spot );
            }
//            throw new IllegalArgumentException( "unknown parameter(s): " + extra );
            return false;
        }
        return true;
    }

//    public static void main( String[] args )
//    {
//        String[] blah = { "-u", "sa", "--password", "root" };
//
//        CLI m = new CLI( blah );
//         m.go( blah );
//         System.out.println( m );
//    }

//    String username;
//    int    count;
//    String    stinky;
//
//    public void go( String[] args )
//    {
//        var nu = new ArrayList<String>();
//        for( var arg : args )
//        {
//            if( arg.equals( "--" ) ) break;
//            nu.add( arg );
//        }
//
//        username = getOptional( "nope", "-u", "--username" );
//        count    = getOptional( 1, "count" );
//        stinky   = getOptional( stinky, "--stinky" );
//        // TODO find orphans
////        source = next();
////        target = next();
//
//
//    }
//
}
