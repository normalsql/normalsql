package play;

import ganesh.Allergies2;

import java.sql.Connection;
import java.sql.DriverManager;

public class PlayAllergies2
{

	public static void main( String[] args )
		throws Exception
	{
		String driver = "org.h2.Driver";
		String url = "jdbc:h2:tcp://localhost/~/test";
		String username = "sa";
		String password = "";

		Class.forName( driver );
		Connection connection = DriverManager.getConnection( url, username, password );

		Allergies2 plus = new Allergies2( connection );

		System.out.println( "*** BEFORE ****" );
		while( plus.hasNext() )
		{
			System.out.printf( "\r%s %s", plus.getId(), plus.getDescription()  );
		}
		plus.close();
		while( plus.hasNext() )
		{
			plus.startUpdating();
			String desc = plus.getDescription();
			plus.setDescription(  desc + "A" );
			plus.finishUpdating();
		}
		
		System.out.println( "\n\n\n\n*** AFTER ****" );
		plus.close();
		while( plus.hasNext() )
		{
			System.out.printf( "\r%s %s", plus.getId(), plus.getDescription()  );
		}
		plus.close();
		
		plus.startInsert();
		plus.setId( 105 );
		plus.setDescription( "description" );
		plus.setMessage_ID( 1 );
		plus.setPatientKey_ID( 1 );
		plus.finishInsert();

	}

}
