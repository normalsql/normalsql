package fado.voyager;

import fado.parse.GenericSQLLexer;
import fado.parse.GenericSQLParser;
import fado.parse.GenericSQLParser.*;
import fado.parse.GenericSQLParser.SubtermValueContext;
import fado.parse.GenericSQLParser.ValueContext;
import fado.template.JavaHelper;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Date;
import java.util.HashMap;
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

		Work work = new Work();
		work.originalSQL = originalSQL;

		visitor.visit( parse );
		work.root = visitor.root;
		work.predicates = visitor.predicates;

		AccessorFactory factory = new AccessorFactory();
		for( Predicate p : work.predicates )
		{
			switch( p )
			{
				case Comparison c:
				{
					String column = getColumn( c.column );
					Accessor a = factory.create( c.value, column );
					work.accessors.add( a );
					break;
				}
				case Between b:
				{
					switch( b.match )
					{
						case COL_VAL_VAL:
						{
							String column = getColumn( b.test );
							Accessor low = factory.create( b.low, column, "low" );
							work.accessors.add( low );

							Accessor high = factory.create( b.high, column, "high" );
							work.accessors.add( high );
							break;
						}
						case VAL_COL_COL:
						{
							String columnLow = getColumn( b.low );
							String columnHigh = getColumn( b.high );
							Accessor high = factory.create( b.test, "between", columnLow, "and", columnHigh );
							work.accessors.add( high );
							break;
						}
						default:
							break;
					}
					break;
				}
				default: break;
			}
		}

		for( Accessor a : work.accessors )
		{
			a.context.setStartTokenText( "?" );
		}

		work.preparedSQL = tokens.getText();

		String url = "jdbc:h2:tcp://localhost/~/Projects/ambrose/db/cm";
		Connection conn = DriverManager.getConnection( url, "sa", null );
//		Map<String, Table> tables = MetaData.getTablesAndColumns( conn );

		processPreparedStatement( conn, work );

		for( int nth = 0; nth < work.params.size(); nth++ )
		{
			Accessor a = work.accessors.get( nth );
			Param p = work.params.get( nth );
			a.param = p;
			a.nth = p.nth;
			a.clazz = p.clazz.substring( p.clazz.lastIndexOf( "." ) + 1 );
		}

		for( Accessor a : work.accessors )
		{
			String text = JavaHelper.toPrintfConverter( a.param.type );
			a.context.setStartTokenText( text );
		}

		work.printfSQL = tokens.getText();

		matchItemsToColumns( work.root.items, work.columns );

		merge( work );

		System.out.println( work );
	}

	public static String getColumn( SubtermContext b )
	{
		return ( (SubtermColumnRefContext) b ).columnRef().column.getTrimmedText();
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
			work.params.add( param );
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


	public static void merge( Work work ) throws IOException
	{
		try (
			OutputStreamWriter osw = new OutputStreamWriter( System.out );
			BufferedWriter writer = new BufferedWriter( osw );
		)
		{
			// TODO: This will have to be moved to common place, maybe into superclass
			VelocityEngine engine = new VelocityEngine();
			engine.setProperty( RuntimeConstants.RESOURCE_LOADER, "classpath" );
			engine.setProperty( "classpath.resource.loader.class", ClasspathResourceLoader.class.getName() );
			engine.setProperty( "runtime.introspector.uberspect", "org.apache.velocity.util.introspection.UberspectPublicFields, org.apache.velocity.util.introspection.UberspectImpl" );
			engine.init();

			// TODO change to 'now', use same Date for all artifacts
			HashMap<String, Object> childMap = work.asMap();
			childMap.put( "date", new Date() );
			childMap.put( "className", "Bonkers" );
			childMap.put( "packageName", "beepbeepp" );

			VelocityContext childContext = new VelocityContext( childMap );

			// TODO: Just one template instance
//			Template selectTemplate = engine.getTemplate( "fado/template/Select.vm" );
			Template selectTemplate = engine.getTemplate( "fado/template/JustBetweens.vm" );
			selectTemplate.merge( childContext, writer );
//			Template resultSetTemplate = engine.getTemplate( "fado/template/ResultSet.vm" );
//			resultSetTemplate.merge( childContext, writer );
		}
	}

}
