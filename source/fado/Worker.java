package fado;

import fado.meta.*;
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

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class Worker
{
	Connection _conn;
	VelocityEngine _engine;
	Template _selectTemplate;
	Template _resultSetTemplate;



	public static void main( String[] args )
		throws Exception
	{
		String url = "jdbc:h2:tcp://localhost/~/Projects/ambrose/db/cm";
		Connection conn = DriverManager.getConnection( url, "sa", null );
//		Map<String, Table> tables = MetaData.getTablesAndColumns( conn );

//		Path sourceFile = Paths.get( "/Users/jasonosgood/Projects/fado/test/SelectCourseTestBetweens.sql" );
		Path sourceFile = Paths.get( "/Users/jasonosgood/Projects/fado/test/SelectCourseDescr.sql" );
		Path targetFile = Paths.get( "/Users/jasonosgood/Projects/fado/test/SelectCourseDescrX.java" );

		Work work = new Work();
		work.packageName = "beepbeepp";
		work.statementClassName = "Bonkers";
		work.sourceFile = sourceFile;

		new Worker( conn ).process( work );
	}

	public Worker( Connection conn )
	{
		_conn = conn;

		// TODO: This will have to be moved to common place, maybe into superclass
		_engine = new VelocityEngine();
		_engine.setProperty( RuntimeConstants.RESOURCE_LOADER, "classpath" );
		_engine.setProperty( "classpath.resource.loader.class", ClasspathResourceLoader.class.getName() );
		_engine.setProperty( "runtime.introspector.uberspect", "org.apache.velocity.util.introspection.UberspectPublicFields, org.apache.velocity.util.introspection.UberspectImpl" );
		_engine.init();

		_selectTemplate = _engine.getTemplate( "fado/template/Select.vm" );
		_resultSetTemplate = _engine.getTemplate( "fado/template/ResultSet.vm" );
	}

	public void process( Work work )
		throws IOException, SQLException
	{
		String originalSQL = new String( Files.readAllBytes( work.sourceFile ) );
		work.originalSQL = originalSQL;

		// TODO reuse one instance of ANTLR?
		CharStream chars = CharStreams.fromString( work.originalSQL );
		GenericSQLLexer lexer = new GenericSQLLexer( chars );
		CommonTokenStream tokens = new CommonTokenStream( lexer );
		GenericSQLParser parser = new GenericSQLParser( tokens );
		GenericSQLParser.ParseContext parse = parser.parse();
		SQLVisitor visitor = new SQLVisitor();

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
					// TODO add operator to method signature
					String column = getColumn( c.column );
					Property prop = factory.create( c.value, column );
					work.statementProperties.add( prop );
					break;
				}
				case Between b:
				{
					switch( b.match )
					{
						case COL_VAL_VAL:
						{
							String column = getColumn( b.test );
							Property low = factory.create( b.low, column, "low" );
							work.statementProperties.add( low );

							Property high = factory.create( b.high, column, "high" );
							work.statementProperties.add( high );
							break;
						}
						case VAL_COL_COL:
						{
							String columnLow = getColumn( b.low );
							String columnHigh = getColumn( b.high );
							Property high = factory.create( b.test, "between", columnLow, "and", columnHigh );
							work.statementProperties.add( high );
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

		for( Property prop : work.statementProperties )
		{
			prop.context.setStartTokenText( "?" );
		}

		work.preparedSQL = tokens.getText();

		processPreparedStatement( _conn, work );

		for( int nth = 0; nth < work.params.size(); nth++ )
		{
			Property prop = work.statementProperties.get( nth );
			Param p = work.params.get( nth );
			prop.param = p;
			prop.nth = p.nth;
			prop.className = p.className;
			prop.classShortName = p.className.substring( p.className.lastIndexOf( "." ) + 1 );
		}

		for( Property prop : work.statementProperties )
		{
			String text = JavaHelper.toPrintfConverter( prop.param.type );
			prop.context.setStartTokenText( text );
		}

		work.printfSQL = tokens.getText();

		// TODO support unions, multiple statements, and such
		work.resultSetProperties = matchItemsToColumns( work.root.get(0).items, work.columns );

		merge( work );

		System.out.println( work );
	}

	public static String getColumn( SubtermContext b )
	{
		return ( (SubtermColumnRefContext) b ).columnRef().column.getTrimmedText();
	}

	public void processPreparedStatement( Connection conn, Work work )
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
			work.columns.add( column );
		}
	}

	/**
	 * Match query 'items' from original SQL to ResultSet's result columns. If original SQL
	 * used wildcard '*', there will be more columns than items. Assumes items and
	 * result columns appear in same order. There can be more result columns than items.
	 *
	 */
	List<Property> matchItemsToColumns( List<Item> items, List<Column> columns )
	{
		ArrayList<Property> properties = new ArrayList<>();

		for( Column column : columns )
		{
			// TODO reference to parent Item
			Property prop = new Property();
			prop.nth = column.nth;
			prop.column = column;
			String name = column.name;
			String label = column.label;
			// TODO also match to catalog, schema, table
			// TODO resolve best match
			for( Item item : items )
			{
				if( name.equalsIgnoreCase( item.name ) && label.equalsIgnoreCase( item.alias ) )
				{
					label = item.alias;
					prop.source = item.context.getText();
					break;
				}
				else if( name.equalsIgnoreCase( item.name ) && label.equalsIgnoreCase( item.name ) )
				{
					label = item.name;
					prop.source = item.context.getText();
					break;
				}
			}

			prop.variable = AccessorFactory.toVariableCase( label );
			prop.getter = "get" + AccessorFactory.toMethodCase( label );
			prop.setter = "set" + AccessorFactory.toMethodCase( label );
			prop.className = column.className;
			prop.classShortName = column.className.substring( column.className.lastIndexOf( "." ) + 1 );
			prop.sqlType = column.type;

			properties.add( prop );
		}

		return properties;
	}

	public void merge( Work work ) throws IOException
	{
		HashMap<String, Object> childMap = work.asMap();
		// TODO change to 'now', use same Date for all artifacts
		childMap.put( "date", new Date() );

		VelocityContext vc = new VelocityContext( childMap );

		generate( _selectTemplate, vc, work.targetDir, work.statementClassName );
		generate( _resultSetTemplate, vc, work.targetDir, work.resultSetClassName );
	}

	public void generate( Template template, VelocityContext vc, Path targetDir, String name )
		throws IOException
	{
		// TODO property for generated file name (incl extension)
		Path targetFile = targetDir.resolve( name + ".java" );
		try (
			FileWriter writer = new FileWriter( targetFile.toFile() );
//			BufferedWriter writer = new BufferedWriter( osw );
		)
		{
			template.merge( vc, writer );
			writer.flush();
		}
	}
}
