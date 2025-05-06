package normalsql.util;//package classicmodels;

import java.io.File;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UseInsertOops {

    public static void main( String[] args )
        throws Exception
    {
		Class.forName( "org.h2.Driver" );
        System.out.println( new File( System.getProperty( "user.dir" )).toPath() );
        Connection conn = DriverManager.getConnection( "jdbc:h2:./classicmodels", "sa", null );
//		Connection conn = DriverManager.getConnection( "jdbc:h2:tcp://localhost/~/Projects/normalsql/doc/classicmodels/classicmodels", "sa", null );
//		Connection conn = DriverManager.getConnection( "jdbc:h2:tcp://localhost/petclinic-jdbc", "sa", null );
//        InsertOopsMaster abc = new InsertOopsMaster( conn, Statement.RETURN_GENERATED_KEYS );
//        InsertOops abc = new InsertOops( conn );
//        abc.setID( 18L );
//        System.out.println( abc );
//        ResultSet rs = abc.execute();
//        Dumper.dumpResultSet( rs );

//        SelectOopsMaxID max = new SelectOopsMaxID( conn );
//        var rs = max.execute();
//        System.out.println( rs );
//        while( rs.hasNext() )
//        {
//            System.out.println( rs );
//        }

//        DeleteOopsByID delete = new DeleteOopsByID( conn );
//        delete.setID( 5L );
//        int count = delete.execute();
//        System.out.println( "deleted: " + count );
    }
}
