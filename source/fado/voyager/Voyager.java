package fado.voyager;

import fado.parse.GenericSQLLexer;
import fado.parse.GenericSQLParser;
import fado.parse.GenericSQLParser.*;
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
import java.util.*;
import java.util.Date;

public class Voyager
{
	public static void main( String[] args )
		throws Exception
	{
//		Path sourceFile = Paths.get( "/Users/jasonosgood/Projects/fado/test/SelectCourseTestBetweens.sql" );
		Path sourceFile = Paths.get( "/Users/jasonosgood/Projects/fado/test/SelectCourseDescr.sql" );

		String originalSQL = new String( Files.readAllBytes( sourceFile ));

		CharStream chars = CharStreams.fromString( originalSQL );
		GenericSQLLexer lexer = new GenericSQLLexer( chars );
		CommonTokenStream tokens = new CommonTokenStream( lexer );
		GenericSQLParser parser = new GenericSQLParser( tokens );
		GenericSQLParser.ParseContext parse = parser.parse();
		VoyagerVisitor visitor = new VoyagerVisitor();

		Work work = new Work();
		work.packageName = "beepbeepp";
		work.className = "Bonkers";
		work.sourceFile = sourceFile;
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
					work.statementAccessors.add( a );
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
							work.statementAccessors.add( low );

							Accessor high = factory.create( b.high, column, "high" );
							work.statementAccessors.add( high );
							break;
						}
						case VAL_COL_COL:
						{
							String columnLow = getColumn( b.low );
							String columnHigh = getColumn( b.high );
							Accessor high = factory.create( b.test, "between", columnLow, "and", columnHigh );
							work.statementAccessors.add( high );
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

		for( Accessor a : work.statementAccessors )
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
			Accessor a = work.statementAccessors.get( nth );
			Param p = work.params.get( nth );
			a.param = p;
			a.nth = p.nth;
			a.className = p.className;
			a.classShortName = p.className.substring( p.className.lastIndexOf( "." ) + 1 );
		}

		for( Accessor a : work.statementAccessors )
		{
			String text = JavaHelper.toPrintfConverter( a.param.type );
			a.context.setStartTokenText( text );
		}

		work.printfSQL = tokens.getText();

		// TODO support unions, multiple statements, and such
		work.resultSetAccessors = matchItemsToColumns( work.root.get(0).items, work.columns );

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
			param.className = pmd.getParameterClassName( nth );
			work.params.add( param );
		}

		ResultSetMetaData md = ps.getMetaData();
		// TODO: dedupe resultset columns
		for( int nth = 1; nth <= md.getColumnCount(); nth++ )
		{
			RSColumn column = new RSColumn();
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
			work.columns.add( column );
		}
	}

	/**
	 * Match query 'items' from original SQL to ResultSet's result columns. If original SQL
	 * used wildcard '*', there will be more columns than items. Assumes items and
	 * result columns appear in same order. There can be more result columns than items.
	 *
	 */
	static List<Accessor> matchItemsToColumns( List<Item> items, List<RSColumn> columns )
	{
		ArrayList<Accessor> accessors = new ArrayList<>();

		for( RSColumn column : columns )
		{
			// TODO reference to parent Item
			Accessor a = new Accessor();
			a.nth = column.nth;
			a.column = column;
			String name = column.name;
			String label = column.label;
			// TODO also match to catalog, schema, table
			// TODO resolve best match
			for( Item item : items )
			{
				if( name.equalsIgnoreCase( item.name ) && label.equalsIgnoreCase( item.alias ) )
				{
					label = item.alias;
					a.source = item.context.getText();
					break;
				}
				else if( name.equalsIgnoreCase( item.name ) && label.equalsIgnoreCase( item.name ) )
				{
					label = item.name;
					a.source = item.context.getText();
					break;
				}
			}

			a.variable = AccessorFactory.toVariableCase( label );
			a.getter = "get" + AccessorFactory.toMethodCase( label );
			a.setter = "set" + AccessorFactory.toMethodCase( label );
			a.className = column.className;
			a.classShortName = column.className.substring( column.className.lastIndexOf( "." ) + 1 );
			a.sqlType = column.type;

			accessors.add( a );
		}

		return accessors;
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

			VelocityContext childContext = new VelocityContext( childMap );

			// TODO: Just one template instance
			Template selectTemplate = engine.getTemplate( "fado/template/Select.vm" );
			selectTemplate.merge( childContext, writer );
			Template resultSetTemplate = engine.getTemplate( "fado/template/ResultSet.vm" );
			resultSetTemplate.merge( childContext, writer );
		}
	}

}
