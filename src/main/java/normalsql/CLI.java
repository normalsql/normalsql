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
                // IIRC this is a bash thing. everything after "--" is ignored, to be passed thru next program.
                if( arg.equals( "--" ) ) break;
                nu.add( arg );
            }
        }
    }
    // TODO getRequired
    // TODO getFlag


    public <T> T getOptional( T missing, String... keys )
    {
//        if( missing == null ) throw new NullPointerException( "missing cannot be null" );
        if( keys == null ) throw new NullPointerException( "keys cannot be null" );

        int size = nu.size();
        for( int i = 0; i < size; i++ )
        {
            for( var key : keys )
            {
                if( nu.get( i ).equals( key ) )
                {
                    if( i + 1 < size )
                    {
                        String value = nu.get( i + 1 );
                        nu.remove( i );
                        nu.remove( i );

                        switch( missing )
                        {
                            case String ignore ->
                            {
                                return (T) value;
                            }
                            case Integer ignore ->
                            {
                                return (T) Integer.valueOf( value );
                            }
                            default -> throw new IllegalStateException( "don't know how to convert/cast: " + missing.getClass() );
                        }
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

    public static void main( String[] args )
    {
        String[] blah = { "-u", "sa", "--password", "root" };

        CLI m = new CLI( blah );
         m.go( blah );
         System.out.println( m );
    }

    String username;
    int    count;
    String    stinky;

    public void go( String[] args )
    {
        var nu = new ArrayList<String>();
        for( var arg : args )
        {
            if( arg.equals( "--" ) ) break;
            nu.add( arg );
        }

        username = getOptional( "nope", "-u", "--username" );
        count    = getOptional( 1, "count" );
        stinky   = getOptional( stinky, "--stinky" );
        // TODO find orphans
//        source = next();
//        target = next();


    }


    @Override
    public String toString()
    {
        final StringBuffer sb = new StringBuffer( "Main\n{" );
        sb.append( "\nusername='" ).append( username ).append( '\'' );
        sb.append( ", \ncount=" ).append( count );
        sb.append( ", \nstinky='" ).append( stinky ).append( '\'' );
        sb.append( "\n}" );
        return sb.toString();
    }
}
