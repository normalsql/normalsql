package fado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UpdatableRS 
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

		s1.executeQuery( "SELECT * FROM Patient" );
	      ResultSet r1 = s1.getResultSet();

	      while (r1.next())
	      {
	        String middle = r1.getString( "MIDDLENAME" );
	        System.out.println( "middle: " + middle );
	        if( middle == null ) {
	        	middle = "";
	        }

	        r1.updateString( "MIDDLENAME", middle + "B" );
	        r1.updateRow();
	      }
	      
			Statement s2 = conn.createStatement(
			        ResultSet.TYPE_SCROLL_SENSITIVE,
			        ResultSet.CONCUR_UPDATABLE);
			s2.executeQuery( "SELECT * FROM Allergy" );
		      ResultSet r2 = s2.getResultSet();
		      while (r2.next())
		      {
		        String code = r2.getString( "CODE" );
		        System.out.println( "code: " + code );
		        if( code == null ) {
		        	code = "";
		        }

		        r2.updateString( "CODE", code + "B" );
		        r2.updateRow();
		      }

	      conn.commit();
	      s1.close();
	      
	      conn.close();

	}

}
