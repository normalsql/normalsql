package play;

import java.sql.Connection;
import java.sql.DriverManager;

public class PlayAllergiesPlus
{

	public static void main( String[] args )
		throws Exception
	{
		String driver = "org.h2.Driver";
		String url = "jdbc:h2:tcp://localhost/~/hydra";
		String username = "sa";
		String password = "";

		Class.forName( driver );
		Connection connection = DriverManager.getConnection( url, username, password );

		AllergiesPlus plus = new AllergiesPlus( connection );

		System.out.println( "*** BEFORE ****" );
		while( plus.next() )
		{
			System.out.printf( "\r%s %s", plus.getID(), plus.getDescription()  );
		}
		plus.close();
		while( plus.next() )
		{
			plus.startUpdating();
			String desc = plus.getDescription();
			plus.setDescription(  desc + "A" );
			plus.finishUpdating();
		}
		
		System.out.println( "\n\n\n\n*** AFTER ****" );
		plus.close();
		while( plus.next() )
		{
			System.out.printf( "\r%s %s", plus.getID(), plus.getDescription()  );
		}
		plus.close();
		
		plus.startInsert();
		plus.setID( 107 );
		plus.setDescription( "description" );
		plus.setMessageID( 1 );
		plus.setPatientKeyID( 1 );
		plus.finishInsert();

	}

}
