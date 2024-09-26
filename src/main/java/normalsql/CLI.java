// Copyright 2010-2024 Jason Osgood
// SPDX-License-Identifier: Apache-2.0


package normalsql;

import java.util.ArrayList;
import java.util.List;

public class CLI
{
    List<String> nu;

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

    public <T> T getOptional(  T def, String... keys )
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

                        var ugh = switch( def )
                        {
                            case String ignore -> (T) value;
                            case Integer ignore -> (T) Integer.valueOf( value );
                            default ->
                            {
                                String name =  def.getClass().getCanonicalName();
                                throw new IllegalArgumentException( "don't know how to convert/cast: " + name );
                            }
                        };

                        return ugh;
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

    public boolean done()
    {
        if( !nu.isEmpty() )
        {
            String extra = String.join( " ", nu );
            System.out.println( "unknown parameter(s): " + extra );
            System.out.println(  );
            System.out.println( "TODO: help info happens here" );
//            throw new IllegalArgumentException( "unknown parameter(s): " + extra );
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
