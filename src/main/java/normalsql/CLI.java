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

    public <T> T getOptional( Class<T> kind, String... keys )
    {
        return getOptional( kind, null, keys );
    }

    public <T> T getOptional( T missing, String... keys )
    {
        Class<T> kind = (Class<T>) missing.getClass();
        return getOptional( kind, missing, keys );
    }

    public <T> T getOptional( Class<T> kind, T missing, String... keys )
    {
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

                        T ugh = switch( kind.getClass() )
                        {
                            case String ignore -> (T) value;
                            case Integer ignore -> (T) Integer.valueOf( value );
                            case null -> null;
                            default ->
                                throw new IllegalStateException( "don't know how to convert/cast: " + missing.getClass() );
                        };

                        return ugh;
                    }
                    else
                    {
                        throw new IllegalStateException( "missing value for key: " + key );
                    }
                }
            }
        }
        return missing;
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
