package normalsql;

import java.nio.file.Path;
import java.util.HashMap;

public class Glorp
{
    public static String getLocalName( String qname )
    {
        // Use just the name; remove any reference prefixes
        if( qname.contains( "." ))
        {
            qname = qname.substring( qname.lastIndexOf( "." ) + 1 );
        }
        return qname;
    }

    /**
     * "Serializes" a POJO into a HashMap. Only adds 'public' fields to resulting map.
     * <p>
     * One use is to create a 'context map' for Velocity templates. Passing values into a
     * Velocity template sucks. This is the least turrible strategy I could think of.
     *
     * @return a {@link java.util.HashMap} object
     */

    public static HashMap<String, Object> toMap( Object o )
    {
        var map = new HashMap<String, Object>();
        try
        {
            for( var f : o.getClass().getFields() )
            {
                var value = f.get( o );
                if( value != null )
                {
                    map.put( f.getName(), value );
                }
            }
        }
        catch( IllegalAccessException e )
        {
            // do nothing
        }
        return map;
    }

    public static String getClassSimpleName( Path file )
    {
        // TODO fix this
        var name = file.getFileName().toString();
        int len = name.length() - ".sql".length();
        var base = name.substring( 0, len );
        return base;
    }
}
