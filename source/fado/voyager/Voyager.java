package fado.voyager;

import fado.meta.Param;
import fado.meta.RSColumn;
import fado.parse.GenericSQLLexer;
import fado.parse.GenericSQLParser;
import fado.parse.GenericSQLParser.ValueContext;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Iterator;
import java.util.List;

public class Voyager
{
	public static void main( String[] args )
		throws Exception
	{

		Path sourceFile = Paths.get( "/Users/jasonosgood/Projects/fado/test/SelectCourseTestBetweens.sql" );

		String originalSQL = new String( Files.readAllBytes( sourceFile ));

		CharStream chars = CharStreams.fromString( originalSQL );
		GenericSQLLexer lexer = new GenericSQLLexer( chars );
		CommonTokenStream tokens = new CommonTokenStream( lexer );
		GenericSQLParser parser = new GenericSQLParser( tokens );
		GenericSQLParser.ParseContext parse = parser.parse();
		VoyagerVisitor visitor = new VoyagerVisitor();
		Work work = visitor.visit( parse );
		work.originalSQL = originalSQL;

		for( Predicate p : work.root.predicates )
		{
			List<ValueContext> dork = p.parent.find( ValueContext.class, "**/value" );
			for( ValueContext d : dork )
			{
				d.setStartTokenText( "?" );
			}
		}

		work.preparedSQL = tokens.getText();
		System.out.println( work.preparedSQL );


		String url = "jdbc:h2:tcp://localhost/~/Projects/ambrose/db/cm";
		Connection conn = DriverManager.getConnection( url, "sa", null );
//		Map<String, Table> tables = MetaData.getTablesAndColumns( conn );

		processPreparedStatement( conn, work );
		Iterator<Param> i = work.paramList.iterator();
		for( Predicate p : work.root.predicates )
		{
			switch( p )
			{
				case Compare c:
					c.param = i.next();
					c.clazz = c.param.clazz;
					break;

				case Between b:
					switch( b.match )
					{
						case COL_VAL_VAL:
							b.lowParam = i.next();
							b.highParam = i.next();
							b.clazz = b.lowParam.clazz;
							break;
						case VAL_COL_COL:
							b.leftParam = i.next();
							b.clazz = b.leftParam.clazz;
							break;
						default: break;
					}
					break;
				default: break;
			}
		}

		// TODO throw exception if not null
		System.out.println( "i.hasNext() " + i.hasNext() );

		matchItemsToColumns( work.root.items, work.columns );

		System.out.println( work );

	}

	public static void processPreparedStatement( Connection conn, Work work )
		throws SQLException
	{
		PreparedStatement ps = conn.prepareStatement( work.preparedSQL );

		ParameterMetaData pmd = ps.getParameterMetaData();
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
			param.clazz = pmd.getParameterClassName( nth );
			work.paramList.add( param );
			System.out.println( param );
		}

		ResultSetMetaData md = ps.getMetaData();
		for( int nth = 1; nth <= md.getColumnCount(); nth++ )
		{
			RSColumn column = new RSColumn();
			column.nth = nth;
			column.name = md.getColumnName( nth );
			column.label = md.getColumnLabel( nth );
			column.type = md.getColumnType( nth );
			column.typeName = md.getColumnTypeName( nth );
			column.isNullable = md.isNullable( nth );
			column.clazz = md.getColumnClassName( nth );
			work.columns.add( column );
			System.out.println( column );
		}
	}

	/**
	 * Match query 'items' from original SQL to ResultSet's result columns. If original SQL
	 * used wildcard '*', there will be more columns than items. Assumes items and
	 * result columns appear in same order. There can be more result columns than items.
	 *
	 */
	static void matchItemsToColumns( List<Item> items, List<RSColumn> columns )
	{
		Iterator<Item> itemIterator = items.iterator();
		String preferred = null;

		outer:
		for( RSColumn column : columns )
		{
			while( preferred == null )
			{
				if( !itemIterator.hasNext() ) break outer;
				Item item = itemIterator.next();
				preferred = item.alias != null ? item.alias : item.columnRef.column;
			}

			if( preferred.equalsIgnoreCase( column.label ) || preferred.equalsIgnoreCase( column.name ))
			{
				column.preferredName = preferred;
				preferred = null;
			}
		}
	}

}
