// Copyright 2010-2024 Jason Osgood
// SPDX-License-Identifier: Apache-2.0


package normalsql;

import java.util.ArrayList;
import java.util.List;

record Option( String name, String shortName, String description, Object defaultValue ){}

/**
 *  Quick & dirty command line arguments parser. I wanted typesafe
 *  1-pass imperative API, instead of annotations or getopt style "parse
 *  then fetch". I wanted explicit constraints, instead of delegated via
 *  annotation or otherwise.
 *
 *  Get it order:
 *
 *      options
 *      flags
 *      ordinal parameters
 *      done()
 *
 */
public class CLI
{
    public static final String BASH_END_OF_OPTIONS = "--";
    // Copy of command line arguments. Up to, but not including, "" (double-dash)
    List<String> _args;
    List<Option> options = new ArrayList<>();

    public static void main( String[] args )
    {
//        var cli = new CLI( new String[]{"x"} );
        var cli = new CLI( new String[]{ "-f", "tangerine", "-x" });
//        var cli = new CLI( new String[]{} );
        String fave = cli.getOption( "fruit", "f", "favorite fruit", "pineapple" );
        boolean help = cli.getFlag( "h", "help", "show help" );
        boolean version = cli.getFlag( "v", "version", "show version" );
        if( cli.done() )
        {
            System.out.println( fave );
            System.out.println( help );
            System.out.println( version );
        }
    }

    public CLI( String[] args )
    {
        _args = new ArrayList<>();
        if( args != null )
        {
            for( var arg : args )
            {
                // Copy command line up to end of options
                if( arg.equals( BASH_END_OF_OPTIONS ) ) break;
                _args.add( arg );
            }
        }
    }
    // TODO getRequired

    @SuppressWarnings( "unchecked" )
    public <T> T getOption( String name, String shortName, String description, T defaultValue )
    {
        if( shortName == null && name == null ) { throw new NullPointerException( "both name and abbrev cannot be null" ); }
        if( defaultValue == null ) { throw new NullPointerException( "default value cannot be null" ); }

        options.add( new Option( name, shortName, description, defaultValue ));

        if( !name.startsWith( "--" ))
        {
            name = "--" + name;
        }

        if( !shortName.startsWith( "-" ))
        {
            shortName = "-" + shortName;
        }

        int size = _args.size();
        for( int i = 0; i < size; i++ )
        {
            var key = _args.get( i );
            if( key.equals( name ) || key.equals( shortName ) )
            {
                if( i + 1 < size )
                {
                    // "consume" option name
                    _args.remove( i );
                    // get and then "consume" option argument
                    var arg = _args.remove( i );

                    return (T) switch( defaultValue )
                    {
                        case String ignore -> arg;
                        case Integer ignore -> Integer.valueOf( arg );
                        case Float ignore -> Float.valueOf( arg );
                        case Boolean ignore -> Boolean.valueOf( arg );
                        default ->
                        {
                            String canonicalName = defaultValue.getClass().getCanonicalName();
                            throw new IllegalArgumentException( "don't know how to convert/cast: " + canonicalName );
                        }
                    };
                }

                throw new IllegalArgumentException( "missing value for key: " + key );
            }
        }
        return defaultValue;
    }


    public boolean getFlag( String abbrev, String name, String blurb )
    {
        options.add( new Option( name, abbrev, blurb, null ));

        int size = _args.size();
        for( int i = 0; i < size; i++ )
        {
            var arg = _args.get( i );
            if( arg.equals( abbrev ) || arg.equals( name ) )
            {
                // "consume" flag
                _args.remove( i );
                return true;
            }
        }
        return false;
    }

    public boolean done()
    {
        if( _args.isEmpty() ) return true;

        String extra = String.join( " ", _args );
        System.out.println( "unknown parameter(s): " + extra );
        System.out.println(  );
        System.out.println( "TODO: help info happens here" );
        for( var spot : options )
        {
            System.out.println( spot );
        }

        return false;
    }
}
