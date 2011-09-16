package play;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class InsertingRS 
{
	public static void main( String[] args )
		throws Exception
	{
		String driver = "org.h2.Driver";
		String url = "jdbc:h2:tcp://localhost/~/hydra";
		String username = "sa";
		String password = "";

		Class.forName( driver );
		Connection conn = DriverManager.getConnection( url, username, password );
		conn.setAutoCommit( false );
		Statement s1 = conn.createStatement(
		        ResultSet.TYPE_SCROLL_SENSITIVE,
		        ResultSet.CONCUR_UPDATABLE);

		s1.executeQuery( "SELECT TOP 0 * FROM Patient" );
	      ResultSet r1 = s1.getResultSet();
	      r1.moveToInsertRow();
	      r1.updateInt( "PATIENTKEY_ID", 1 );
	      r1.updateInt( "MESSAGE_ID", 1 );
	        r1.updateString( "FAMILYNAME", "WASHINGTON" );
	        r1.updateString( "MIDDLENAME", "HARVEY" );
	        r1.updateString( "GIVENNAME", "GEORGE" );
	      
	        r1.insertRow();
	      conn.commit();
	      r1.close();
	      s1.close();
	      
	      conn.close();

	}

}
