package normalsql.jdbc;

import java.sql.*;
import java.util.ArrayList;

public class
	FancyMetaData
{
	// Parameters copied from PreparedStatement's metadata
	public ArrayList<Param> params = new ArrayList<>();
	// Columns copied from ResultSet's metadata
	public ArrayList<Column> columns = new ArrayList<>();

	public FancyMetaData( Connection conn, String preparedSQL )
		throws SQLException
	{
		PreparedStatement ps = conn.prepareStatement( preparedSQL );

		ParameterMetaData pmd = ps.getParameterMetaData();
		if( pmd != null )
		{
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
			}
		}

		ResultSetMetaData md = ps.getMetaData();
		if( md != null )
		{
			// TODO: dedupe resultset columns
			for( int nth = 1; nth <= md.getColumnCount(); nth++ )
			{
				Column column = new Column();
				column.nth = nth;
				column.catalog = md.getCatalogName( nth );
				column.schema = md.getSchemaName( nth );
				column.table = md.getTableName( nth );
				column.name = md.getColumnName( nth );
				column.label = md.getColumnLabel( nth );
				column.type = md.getColumnType( nth );
				column.typeName = md.getColumnTypeName( nth );
				column.isNullable = md.isNullable( nth );
				column.className = md.getColumnClassName( nth );
				columns.add( column );
			}
		}
	}
}
