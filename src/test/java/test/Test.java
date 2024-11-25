package test;

import petclinic.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class Test {

    public static void main( String[] args )
        throws Exception
    {
//		var ugh = Class.forName( "org.h2.Driver" );
		Connection conn = DriverManager.getConnection( "jdbc:h2:tcp://localhost/petclinic-jdbc", "", "" );

        var max = new SelectNullables( conn );
        var rs = max.execute();
        System.out.println( rs );
        while( rs.hasNext() )
        {
            System.out.println( rs );
        }
        System.out.println( rs );

//        DeleteOopsByID delete = new DeleteOopsByID( conn );
//        delete.setID( 5L );
//        int count = delete.execute();
//        System.out.println( "deleted: " + count );
    }
}
