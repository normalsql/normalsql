package normalsql.util;

//import static java.sql.Types.*;

import normalsql.Tool;

public class Hello {
    public static void main( String[] args )
    {
        Tool.INFO.log( "apple" );
        Tool.WARN.log( "banana" );
        Tool.DEBUG.log( "cherry" );
        Tool.ERROR.log( "daikon" );

        try
        {
            sniffles();
        }
        catch( Exception e )
        {
            Tool.INFO.log( "apple", e );
            Tool.WARN.log( "banana", e );
            Tool.DEBUG.log( "cherry", e );
            Tool.ERROR.log( "daikon", e );
        }

//        System.out.println( "Hello" );
    }

    public static Throwable sniffles()
    {
        throw new NullPointerException( "awchoo!" );
    }

}
