package fado;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class Squishy {

	public static void main(String[] args)
		throws Exception
	{
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@orlando.cac.washington.edu:1521:XE";
		String username = "JASONCM20";
		String password = "JASONCM20";
		Class.forName( driver );
		Connection conn = DriverManager.getConnection( url, username, password );
		DatabaseMetaData meta = conn.getMetaData();
		String catalog = null;
		String schemaPattern = null;
		String tableName = "sr_curric_code";
		ResultSet tableRS = meta.getTables( catalog, schemaPattern, tableName, null );
		Dumper.dumpResultSet( tableRS, System.out );


	}

}
