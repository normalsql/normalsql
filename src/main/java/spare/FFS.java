package spare;

import com.mysql.cj.jdbc.ConnectionImpl;
import com.mysql.cj.jdbc.JdbcPropertySet;
import normalsql.meta.Param;

import java.sql.DriverManager;
import java.sql.*;
import java.util.ArrayList;

public class FFS
{
	public static void main( String[] args ) throws Exception
	{
		Class.forName( "com.mysql.cj.jdbc.Driver" );
		ConnectionImpl conn = (ConnectionImpl) DriverManager.getConnection( "jdbc:mysql://localhost/classicmodels?useServerPrepStmts=true", "root", null );
//		ConnectionImpl conn = (ConnectionImpl) DriverManager.getConnection( "jdbc:mysql://localhost?useServerPrepStmts=true", "root", null );

		JdbcPropertySet propertySet = conn.getPropertySet();
//		propertySet.getBooleanProperty( "generateSimpleParameterMetadata" ).setValue( false );
//		propertySet.getBooleanProperty( "useServerPrepStmts" ).setValue( true );
//		propertySet.getBooleanProperty( "emulateUnsupportedPstmts" ).setValue( false );

		System.out.println( "generateSimpleParameterMetadata: " + propertySet.getBooleanProperty( "generateSimpleParameterMetadata" ).getValue() );
		System.out.println( "useServerPrepStmts: " + propertySet.getBooleanProperty( "useServerPrepStmts" ).getValue() );
		System.out.println( "emulateUnsupportedPstmts: " + propertySet.getBooleanProperty( "emulateUnsupportedPstmts" ).getValue() );
//		Statement s = conn.createStatement();
//		if( s.execute( "SELECT 1" ))
//		{
//			ResultSet rs = s.getResultSet();
//			Dumper.dumpResultSet( rs );
//		}
//		if( s.execute( "SELECT customerName, state FROM customers WHERE country = 'USA'" ))
//		{
//			ResultSet rs = s.getResultSet();
//			Dumper.dumpResultSet( rs );
//		}

//		PreparedStatement ps = conn.prepareStatement( "SELECT customerName, state FROM classicmodels.customers WHERE country = ?" );
		PreparedStatement ps = conn.prepareStatement( "SELECT count(*) FROM customers WHERE country = ?" );
		ParameterMetaData pmd = ps.getParameterMetaData();
		ArrayList<Param> params = new ArrayList<>();
		for( int nth = 1; nth <= pmd.getParameterCount(); nth++ )
		{
			Param param = new Param();
			param.nth = nth;
			param.type = pmd.getParameterType( nth );
			param.typeName = pmd.getParameterTypeName( nth );
			param.isNullable = pmd.isNullable( nth );
			param.isSigned = pmd.isSigned( nth );
			param.scaled = pmd.getScale( nth );
			param.precision = pmd.getPrecision( nth );
			param.mode = pmd.getParameterMode( nth );
			param.className = pmd.getParameterClassName( nth );
			params.add( param );
//			System.out.println( param );
		}
		ps.setString( 1, "USA" );
		if( ps.execute() )
		{
			ResultSet rs = ps.getResultSet();
			Dumper.dumpResultSet( rs );
		}
	}
}
