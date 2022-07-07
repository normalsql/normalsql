package fado;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.StringReader;
import java.io.Writer;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;


import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import org.antlr.v4.runtime.ParserRuleContext;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import fado.meta.BETWEEN;
import fado.meta.Column;
import fado.meta.Condition;
import fado.meta.IN;
import fado.meta.InsertStatement;
import fado.meta.LIKE;
import fado.meta.SelectStatement;
import fado.meta.Comparison;
import fado.meta.Table;
import fado.meta.TableNotFoundException;
import fado.meta.Field;
import fado.meta.MetaField;
import fado.meta.UpdateStatement;
import fado.meta.WhereStatement;
import fado.parse.GlobbingRuleContext;
import fado.parse.GenericSQLLexer;
import fado.parse.GenericSQLParser;

import static fado.parse.GenericSQLParser.RULE_select;
import static fado.parse.GenericSQLParser.RULE_insert;
import static fado.parse.GenericSQLParser.RULE_update;

//			"/Users/jasonosgood/Projects/ambrose/sql/uwsdb/SelectCourseDetails.sql"

public class
	Fado
{
	public static void main( String[] args )
//		throws Exception
	{
		try
		{
			FadoOptions options = new FadoOptions();
			options.parse( args );
			System.out.println( options );
			Fado fado = new Fado();
			fado.init( options );
		}
		catch( Exception e )
		{
			e.printStackTrace( System.out );
		}
		System.out.println( "done" );
	}

	Template _selectTemplate = null;
	Template _resultSetTemplate = null;
	Template _insertTemplate = null;
	Template _updateTemplate = null;
	Connection _conn = null;

	boolean _onlyParse = false;

	public void init( Properties props )
			throws Exception
	{
		FadoOptions options = new FadoOptions( props );
		init( options );
	}

	// TODO: If properties aren't found or defined, then onlyparse = true
	public void init( FadoOptions options )
		throws Exception
	{
		_onlyParse = options.getOnlyParse();

		if( !_onlyParse )
		{
			String driver = options.getDriver();
			if( driver != null )
			{
				Class.forName( driver );
			}
			String username = options.getUsername();
			String password = options.getPassword();
			String url = options.getUrl();
			if( url != null )
			{
				_conn = DriverManager.getConnection( url, username, password );
			}
			// TODO: verify database, eg 'SELECT 1'
			Statement s = _conn.createStatement();
			if( s.execute("SELECT 1" ))
//			if( s.execute("SELECT id, lastATP, course_title, description, revision FROM Course WHERE department_abbrev = 'ENGL' AND course_number = '100' ORDER BY lastATP desc;" ))
			{
				ResultSet rs = s.getResultSet();
				System.out.println( rs );
			}

			Velocity.setProperty( RuntimeConstants.RESOURCE_LOADER, "classpath" );
			Velocity.setProperty( "classpath.resource.loader.class", ClasspathResourceLoader.class.getName() );
			Velocity.init();

			_selectTemplate = Velocity.getTemplate( "fado/template/Select.vm" );
			_resultSetTemplate = Velocity.getTemplate( "fado/template/ResultSet.vm" );
			_insertTemplate = Velocity.getTemplate( "fado/template/Insert.vm" );
			_updateTemplate = Velocity.getTemplate( "fado/template/Update.vm" );
		}

		String target = options.getTarget();
		File targetDir = new File( target );

		Stack<String> path = new Stack<>();
		String pkg = options.getPackage().trim();
		if( !"".equals( pkg ) )
		{
			// TODO regex to validate package
			String[] list = pkg.split( "\\." );
			for( String item : list )
			{
				path.push( item );
				targetDir = new File( targetDir, item );
			}
		}

		String source = options.getSource();
		File sourceDir = new File( source );
		if( !sourceDir.exists() )
		{
			String msg = sourceDir.getCanonicalPath() + " (filespec: " + source + ")";
			throw new FileNotFoundException( msg );
		}

		crawl( sourceDir, targetDir, path );

		if( _conn != null )
		{
			_conn.close();
		}
	}

	FileFilter sqlFilter = new FileFilter()
	{
		public boolean accept( File file )
		{
			if( file.isHidden() ) return false;
			if( file.isDirectory() ) return false;
			String name = file.getName();
			if( name.startsWith( "." ) ) return false;
			if( !name.endsWith( ".sql" ) ) return false;
			if( name.toLowerCase().contains( "create" ) ) return false;
			return true;
		}
	};

	FileFilter dirFilter = new FileFilter()
	{
		public boolean accept( File file )
		{
			if( file.isHidden() ) return false;
			if( file.getName().startsWith( "." ) ) return false;
			return file.isDirectory();
		}
	};

	public static String join( List<String> list, String sep )
	{
		StringBuilder sb = new StringBuilder();
		for( String item : list )
		{
			if( sb.length() > 0 )
			{
				sb.append( sep );
			}
			sb.append( item );
		}
		return sb.toString();
	}

	public static String join( String[] list, String sep )
	{
		StringBuilder sb = new StringBuilder();
		for( String item : list )
		{
			if( sb.length() > 0 )
			{
				sb.append( sep );
			}
			sb.append( item );
		}
		return sb.toString();
	}

	// TODO: Create command line option for this? eg. for a clean build operation
	private boolean _alwaysOverwrite = true;

	public void crawl( File sourceRoot, File targetRoot, Stack<String> path )
	{
		targetRoot.mkdirs();
		String pkg = join( path, "." );
		File[] sourceList = sourceRoot.listFiles( sqlFilter );
		for( File sourceFile : sourceList )
		{
			try
			{
				String name = getFileName( sourceFile );
				// TODO: Also check lastModified of generated ResultSet wrapper
				File targetFile = new File( targetRoot, name + ".java" );
				long a = sourceFile.lastModified();
				long b = targetFile.lastModified();
				if( _alwaysOverwrite || !targetFile.exists() || a > b )
				{
					if( _onlyParse )
					{
						System.out.println( "parsing " + sourceFile );
					}
					else
					{
						System.out.println( "generating " + targetFile + " (" + sourceFile + ")" );
					}
					process( pkg, name, sourceFile, targetRoot );
				}
				else
				{
					System.out.println( targetFile + " is current" );
				}
			}
			catch( Exception e )
			{
				System.out.println( "error processing : " + sourceFile );
				e.printStackTrace();
				// throw new FadoException( "file: " + sourceFile.toString(), e );
			}
		}

		File[] childDirList = sourceRoot.listFiles( dirFilter );
		for( File child : childDirList )
		{
			String name = child.getName();
			path.push( name );
			File targetDir = new File( targetRoot, name );
			crawl( child, targetDir, path );
			path.pop();
		}
	}

	public String getFileName( File file )
	{
		String name = file.getName();
		int i = name.indexOf( '.' );
		if( i > -1 )
		{
			name = name.substring( 0, i );
		}
		return name;
	}

	//	public boolean displayParseTree = true;
	public boolean displayParseTree = false;

	public List<File> process( String pkg, String name, File sourceFile, File targetRoot )
		throws Exception
	{
		try
		{
			List<File> created = new ArrayList<File>();
			CharStream chars = CharStreams.fromPath( Paths.get( sourceFile.getPath() ) );
			GenericSQLLexer lexer = new GenericSQLLexer( chars );
			CommonTokenStream tokens = new CommonTokenStream( lexer );
			GenericSQLParser parser = new GenericSQLParser( tokens );

			GenericSQLParser.StatementContext statementContext = parser.statement();

			// TODO: getOriginalText() which includes all channels, eg whitespace, comments
			String originalText = tokens.getText();
			// Replace (escape) Java style comments in generated code, so /* */ becomes /@ @/.
			String temp = originalText.replace( "/*", "/@" );
			temp = temp.replace( "*/", "@/" );
			List<String> originalLines = chopper( temp.trim() );

			for( GlobbingRuleContext child : statementContext.getRuleContexts( GlobbingRuleContext.class ) )
			{
				switch( child.getRuleIndex() )
				{
					case RULE_select:
					{
						SelectStatement statement = new SelectStatement();
						statement.setName( name );
						statement.setPackage( pkg );
						statement.setOriginalFileName( sourceFile.toString() );
						statement.setOriginalText( originalText );
						statement.setOriginalLines( originalLines );

						extractSelect( child, statement );
						String retooledSQL = tokens.getText().trim();
						statement.setRetooledSQL( chopper( retooledSQL ) );

						inspectDatabaseForSelect( _conn, statement );

						generateSelect( statement, targetRoot, name, created );
						generateResultSet( statement, targetRoot, name, created );
						break;
					}
					case RULE_insert:
					{
						InsertStatement statement = new InsertStatement();
						statement.setName( name );
						statement.setPackage( pkg );
						statement.setOriginalFileName( sourceFile.toString() );
						statement.setOriginalText( originalText );
						statement.setOriginalLines( originalLines );

						extractInsert( child, statement );
						String retooledSQL = tokens.getText().trim();
						statement.setRetooledSQL( chopper( retooledSQL ) );

						Table table = statement.getTables().iterator().next();
						inspectTableAndFields( _conn, table, statement.getFields() );

						generateInsert( statement, targetRoot, name, created );
						break;
					}
					case RULE_update:
					{
						UpdateStatement statement = new UpdateStatement();
						statement.setName( name );
						statement.setPackage( pkg );
						statement.setOriginalFileName( sourceFile.toString() );
						statement.setOriginalText( originalText );
						statement.setOriginalLines( originalLines );

						extractUpdate( child, statement );
						String retooledSQL = tokens.getText().trim();
						statement.setRetooledSQL( chopper( retooledSQL ) );

						Table table = statement.getTables().iterator().next();
						inspectTableAndFields( _conn, table, statement.getFields() );
						inspectTableAndFields( _conn, table, statement.getConditions() );

						generateUpdate( statement, targetRoot, name, created );
						break;
					}
					default:
						break;
				}
			}
			return created;
		}
		catch( Exception e )
		{
//			if( displayParseTree )
//			{
//				System.out.println( "string tree: " + builder.getTree().toParseTree() );
//			}
			throw e;
		}
	}

	private void extractSelect( GlobbingRuleContext selectNode, SelectStatement statement )
			throws Exception
	{
		if( _onlyParse ) return;
		extractSelectTables( selectNode, statement );
		extractSelectColumns( selectNode, statement );
		extractSelectParams( selectNode, statement );
	}

	public void extractSelectTables( GlobbingRuleContext selectNode, SelectStatement statement )
	{
		{
			List<GlobbingRuleContext> list = selectNode.findNodes( "from/fromItem" );
			for( GlobbingRuleContext item : list )
			{
				GlobbingRuleContext tableRef = item.findFirstNode( "tableRef" );
				String databaseName = tableRef.findFirstString( "databaseName" );
				String tableName = tableRef.findFirstString( "**/tableName" );
				String alias = item.findFirstString( "alias" );
				Table table = new Table( databaseName, tableName, alias );
				statement.addTable( table );
			}
		}
		{
			List<GlobbingRuleContext> list = selectNode.findNodes( "joinList/join" );
			for( GlobbingRuleContext item : list )
			{
				GlobbingRuleContext tableRef = item.findFirstNode( "**/tableRef" );
				String databaseName = tableRef.findFirstString( "databaseName" );
				String tableName = tableRef.findFirstString( "**/tableName" );
				String alias = item.findFirstString( "alias" );
				Table table = new Table( databaseName, tableName, alias );
				statement.addTable( table );
			}
		}
	}

	public void extractSelectColumns( GlobbingRuleContext selectNode, SelectStatement statement )
			throws Exception
	{
		List<GlobbingRuleContext> list = selectNode.findNodes( "itemList/item" );
		for( GlobbingRuleContext item : list )
		{
			// Single column reference, eg alias.FamilyName
			String columnName = item.findFirstString( "**/value/columnRef/columnName" );
			if( columnName != null )
			{
				columnName = trimQuotes( columnName );
				String tableAlias = item.findFirstString( "**/value/columnRef/tableAlias" );
				String columnAlias = item.findFirstString( "alias" );
				Table table = statement.getTable( tableAlias );
				Column column = new Column( table, columnName, columnAlias );
				statement.tempColumn( column );
				continue;
			}

//				// Expression returned as column
//				// TODO: expression columns

		}
	}

	public void extractSelectParams( GlobbingRuleContext selectNode, SelectStatement statement )
			throws FadoException
	{
		for( GlobbingRuleContext where : selectNode.findNodes( "where" ) )
		{
			List<GlobbingRuleContext> clist = where.findNodes( "**/condition" );
			for( GlobbingRuleContext condition : clist )
			{
				for( GlobbingRuleContext comparison : condition.findNodes( "comparison" ) )
				{
					extractComparisonParams( comparison, statement );
					break;
				}

				for( GlobbingRuleContext in : condition.findNodes( "in" ) )
				{
					extractINParams( in, statement );
					break;
				}

				for( GlobbingRuleContext between : condition.findNodes( "between" ) )
				{
					extractBETWEENParams( between, statement );
					break;
				}

				for( GlobbingRuleContext like : condition.findNodes( "like" ) )
				{
					extractLIKEParams( like, statement );
					break;
				}
			}
			break;
		}
	}

	private void extractInsert( GlobbingRuleContext insertNode, InsertStatement statement )
			throws Exception
	{
		if( _onlyParse ) return;
		GlobbingRuleContext tableRef = insertNode.findFirstNode( "into/tableRef" );
		String databaseName = tableRef.findFirstString( "databaseName" );
		String tableName = tableRef.findFirstString( "**/tableName" );
		String alias = null;
		Table table = new Table( databaseName, tableName, alias );
		statement.addTable( table );

		ArrayList<Field> fields = new ArrayList<Field>();
		List<GlobbingRuleContext> list = insertNode.findNodes( "columnList/columnName" );
		for( GlobbingRuleContext item : list )
		{
			String name = item.getText();
			fields.add( new Field( name ) );
		}

		List<GlobbingRuleContext> literals = insertNode.findNodes( "values/literal" );

		if( fields.size() != literals.size() )
		{
			throw new Exception( "mismatch, fields: " + fields.size() + ", literals: " + literals.size() );
		}

		int nth = 0;
		for( GlobbingRuleContext node : literals )
		{
			String literal = node.getText();
			literal = trimQuotes( literal );

			node.convertToJDBCParam();

			Field field = fields.get( nth );
			field.setLiteral( literal );
			nth++;
		}
		statement.setFields( fields );
	}

	// TODO support unary literals (done?)
	private void extractUpdate( GlobbingRuleContext updateNode, UpdateStatement statement )
			throws Exception
	{
		if( _onlyParse ) return;
		GlobbingRuleContext tableRef = updateNode.findFirstNode( "tableRef" );
		String databaseName = tableRef.findFirstString( "databaseName" );
		String tableName = tableRef.findFirstString( "**/tableName" );
		String alias = null;
		Table table = new Table( databaseName, tableName, alias );
		statement.addTable( table );

		ArrayList<Field> fields = new ArrayList<Field>();
		List<GlobbingRuleContext> setters = updateNode.findNodes( "setter" );
		for( GlobbingRuleContext node : setters )
		{
			String name = node.findFirstString( "columnName" );
			GlobbingRuleContext literalNode = node.findFirstNode( "literal" );
			String literal = literalNode.getText();
			literal = trimQuotes( literal );

			literalNode.convertToJDBCParam();

			fields.add( new Field( name, literal ) );
		}
		statement.setFields( fields );

		for( GlobbingRuleContext where : updateNode.findNodes( "where" ) )
		{
			List<GlobbingRuleContext> clist = where.findNodes( "**/condition" );
			for( GlobbingRuleContext condition : clist )
			{
				for( GlobbingRuleContext comparison : condition.findNodes( "comparison" ) )
				{
					extractComparisonParams( comparison, statement );
					break;
				}

				for( GlobbingRuleContext in : condition.findNodes( "in" ) )
				{
					extractINParams( in, statement );
					break;
				}

				for( GlobbingRuleContext between : condition.findNodes( "between" ) )
				{
					extractBETWEENParams( between, statement );
					break;
				}

				for( GlobbingRuleContext like : condition.findNodes( "like" ) )
				{
					extractLIKEParams( like, statement );
					break;
				}
			}
			break;
		}
	}

	// Comparisons, eg column = 'abc'
	public void extractComparisonParams( GlobbingRuleContext comparisonNode, WhereStatement statement )
			throws TableNotFoundException
	{
		List<GlobbingRuleContext> columns = comparisonNode.findNodes( "**/columnRef" );
		List<GlobbingRuleContext> literals = comparisonNode.findNodes( "**/literal" );
		if( columns.size() == 1 && literals.size() == 1 )
		{
			// System.out.println( "found comparison: " + comparison.toInputString() );
			GlobbingRuleContext columnRef = columns.get( 0 );
			String column = columnRef.findFirstString( "columnName" );
			String tableAlias = columnRef.findFirstString( "tableAlias" );
			Table table = null;
			if( tableAlias != null )
			{
				table = statement.getTable( tableAlias );
			}

			GlobbingRuleContext node = literals.get( 0 );
			String literal = node.getText();
			literal = trimQuotes( literal );
			Comparison comparison = new Comparison( table, column, literal );

			statement.addCondition( comparison );

			node.convertToJDBCParam();
		}
	}

	// BETWEEN, eg column BETWEEN 1 AND 100
	// TODO: BETWEEN condition
	public void extractBETWEENParams( GlobbingRuleContext betweenNode, WhereStatement statement )
			throws FadoException
	{
		List<GlobbingRuleContext> columns = betweenNode.findNodes( "**/columnRef" );
		List<GlobbingRuleContext> literals = betweenNode.findNodes( "**/literal" );
		if( columns.size() == 1 && literals.size() == 2 )
		{
			GlobbingRuleContext columnRef = columns.get( 0 );
			String column = columnRef.findFirstString( "columnName" );
			String tableAlias = columnRef.findFirstString( "tableAlias" );
			Table table = statement.getTable( tableAlias );

			BETWEEN between = new BETWEEN( table, column );

			GlobbingRuleContext leftNode = literals.get( 0 );
			String left = leftNode.getText();
			left = trimQuotes( left );
			leftNode.convertToJDBCParam();
			between.setLeft( left );

			GlobbingRuleContext rightNode = literals.get( 1 );
			String right = rightNode.getText();
			right = trimQuotes( right );
			rightNode.convertToJDBCParam();
			between.setRight( right );

			statement.addCondition( between );
		}
	}

	// LIKE, eg column LIKE 'abc%'
	// TODO: LIKE condition
	public void extractLIKEParams( GlobbingRuleContext likeNode, WhereStatement statement )
			throws FadoException
	{
		List<GlobbingRuleContext> columns = likeNode.findNodes( "**/columnRef" );
		List<GlobbingRuleContext> literals = likeNode.findNodes( "**/literal" );
		if( columns.size() == 1 && literals.size() == 1 )
		{
			GlobbingRuleContext columnRef = columns.get( 0 );
			String column = columnRef.findFirstString( "columnName" );
			String tableAlias = columnRef.findFirstString( "tableAlias" );
			Table table = statement.getTable( tableAlias );

			LIKE like = new LIKE( table, column );

			GlobbingRuleContext patternNode = literals.get( 0 );
			String pattern = patternNode.getText();
			pattern = trimQuotes( pattern );
			patternNode.convertToJDBCParam();
			like.setPattern( pattern );

			statement.addCondition( like );
		}
	}


	// IN, eg column IN ( 1, 2, 3 )
	// Very naive pattern extraction, assumes column on left and literals on right
	public void extractINParams( GlobbingRuleContext inNode, WhereStatement statement )
			throws FadoException
	{
		List<GlobbingRuleContext> columns = inNode.findNodes( "**/columnRef" );
		List<GlobbingRuleContext> literals = inNode.findNodes( "**/literal" );
		if( columns.size() == 1 && literals.size() > 0 )
		{
			GlobbingRuleContext columnRef = columns.get( 0 );
			String column = columnRef.findFirstString( "columnName" );
			String tableAlias = columnRef.findFirstString( "tableAlias" );
			Table table = null;
			if( tableAlias != null )
			{
				table = statement.getTable( tableAlias );
			}
			IN in = new IN( table, column );

			for( GlobbingRuleContext node : literals )
			{
				String text = node.getText();
				text = trimQuotes( text );

				node.convertToJDBCParam();

				in.addValue( text );
			}
			statement.addCondition( in );
		}
	}

	public void inspectDatabaseForSelect( Connection conn, SelectStatement select )
		throws Exception
	{
		if( _onlyParse ) return;
		{
			String sql = select.getOriginalText();

			PreparedStatement query = _conn.prepareStatement( sql );
			query.setMaxRows( 1 );
			if( query.execute() )
			{
				ResultSet rs = query.getResultSet();
				List<Column> tempList = select.getTempColumns();

				ResultSetMetaData meta = rs.getMetaData();
				int count = meta.getColumnCount();

				for( Column temp : tempList )
				{
					boolean found = false;
					for( int i = 1; i <= count; i++ )
					{
						String name = meta.getColumnName( i );
						int sqlType = meta.getColumnType( i );
						String sqlTypeName = meta.getColumnTypeName( i );
						int nullable = meta.isNullable( i );
						if( name.equalsIgnoreCase( temp.getAlias() ) )
						{
							found = true;
							// choose user's capitalization of the column name or alias
							name = temp.getAlias();
							Column column = new Column( name, sqlType, sqlTypeName );
							column.setNullable( nullable == DatabaseMetaData.columnNullable );
							select.addFinalColumn( column );
							break;
						}
					}
					if( !found )
					{
						String msg = String.format( "SELECT's resultset column metadata does not contain '%s'", temp.getAlias() );
						throw new FadoException( msg );
					}
				}
			}
		}

		String catalog = conn.getCatalog();
		DatabaseMetaData meta = conn.getMetaData();

		for( Condition condition : select.getConditions() )
		{
			String columnName = condition.getName();

			List<Table> tables = null;
			if( condition.hasTable() )
			{
				tables = new ArrayList<Table>();
				Table table = condition.getTable();
				tables.add( table );
			}
			else
			{
				tables = select.getTables();
			}

			boolean foundColumn = false;
			boolean foundTable = false;

			for( Table table : tables )
			{
				String tableName = table.getName();
				ResultSet tableRS = meta.getTables( null, null, null, null );
//				Dumper.dumpResultSet( tableRS );
//				tableRS.beforeFirst();
				while( tableRS.next() && !foundColumn )
				{
					String tempTableName = tableRS.getString( "TABLE_NAME" );
					boolean tableMatch = tableName.equalsIgnoreCase( tempTableName );
					if( tableMatch ) foundTable = true;

					String tempTableSchema = tableRS.getString( "TABLE_SCHEM" );
					boolean schemaMatch = "PUBLIC".equalsIgnoreCase( tempTableSchema );
					String tempTableCatalog = tableRS.getString( "TABLE_CAT" );
					boolean catalogMatch = catalog.equalsIgnoreCase( tempTableCatalog );
					if( tableMatch && schemaMatch && catalogMatch )
					{
						ResultSet columnRS = meta.getColumns( tempTableCatalog, tempTableSchema, tempTableName, null );
						while( columnRS.next() && !foundColumn )
						{
							String tempColumnName = columnRS.getString( "COLUMN_NAME" );
							boolean columnMatch = columnName.equalsIgnoreCase( tempColumnName );
							if( columnMatch )
							{
								int sqlType = columnRS.getInt( "DATA_TYPE" );
								condition.setSQLType( sqlType );
								int nullable = columnRS.getInt( "NULLABLE" );
								condition.setNullable( nullable == DatabaseMetaData.columnNullable );
//								System.out.printf( "searched for column: %s  found: %s\n", columnName, tempColumnName );
								foundColumn = true;
							}
						}
						columnRS.close();
					}
				}
				tableRS.close();
			}

			if( !foundTable && condition.hasTable() )
			{
				throw new Exception( "condition's table ref not found: " + condition.getTable().getName() );
			}
			if( !foundColumn )
			{
				throw new Exception( "condition's column ref not found: " + columnName );
			}
		}
	}

	/**
	 * Find table name, case insensitive
	 *
	 * @param conn
	 * @param table
	 * @param fields
	 * @throws Exception
	 */

	public void inspectTableAndFields( Connection conn, Table table, List<? extends MetaField> fields )
			throws Exception
	{
		ResultSet tableRS = null;
		try
		{
			String catalog = null;
			String schema = "PUBLIC";
			String tableName = table.getName();

			DatabaseMetaData meta = conn.getMetaData();
			tableRS = meta.getTables( catalog, schema, null, null );

			while( tableRS.next() )
			{
				String tempTableName = tableRS.getString( "TABLE_NAME" );
				if( !tableName.equalsIgnoreCase( tempTableName ) ) continue;

				catalog = tableRS.getString( "TABLE_CAT" );
				schema = tableRS.getString( "TABLE_SCHEM" );
				inspectFields( meta, catalog, schema, tempTableName, fields );
			}
		}
		finally
		{
			safeClose( tableRS );
		}
	}

	public void inspectFields( DatabaseMetaData meta, String catalog, String schema, String table, List<? extends MetaField> fields )
			throws FadoException, SQLException
	{
		for( MetaField field : fields )
		{
			boolean found = false;

			ResultSet columnRS = meta.getColumns( catalog, schema, table, null );

			while( columnRS.next() )
			{
				String columnName = columnRS.getString( "COLUMN_NAME" );
				if( field.getName().equalsIgnoreCase( columnName ) )
				{
					int sqlType = columnRS.getInt( "DATA_TYPE" );
					field.setSQLType( sqlType );
					String sqlTypeName = columnRS.getString( "TYPE_NAME" );
					field.setSQLTypeName( sqlTypeName );
					int nullable = columnRS.getInt( "NULLABLE" );
					field.setNullable( nullable == DatabaseMetaData.columnNullable );
					found = true;
					break;
				}
			}
			columnRS.close();
			if( !found )
			{
				String msg = String.format( "table '%s' does not contain field '%s'", table, field.getName() );
				throw new FadoException( msg );
			}
		}
	}


	public void generateSelect( SelectStatement statement, File targetRoot, String name, List<File> created )
			throws Exception
	{
		VelocityContext context = new VelocityContext();
		context.put( "packageName", statement.getPackage() );
		context.put( "className", statement.getName() );
		context.put( "sql", statement.getRetooledSQL() );
		context.put( "conditions", statement.getConditions() );
		context.put( "date", new Date() );
		context.put( "originalfile", statement.getOriginalFileName() );
		context.put( "originallines", statement.getOriginalLines() );

		File targetFile = new File( targetRoot, name + ".java" );
		FileWriter writer = new FileWriter( targetFile );
		_selectTemplate.merge( context, writer );
		writer.flush();
		writer.close();

		created.add( targetFile );
	}

	public void generateResultSet( SelectStatement statement, File targetRoot, String name, List<File> created )
			throws Exception
	{
		VelocityContext context = new VelocityContext();
		context.put( "packageName", statement.getPackage() );
		context.put( "className", statement.getName() );
		context.put( "sql", statement.getRetooledSQL() );
		context.put( "columns", statement.getFinalColumns() );
		context.put( "date", new Date() );
		context.put( "originalfile", statement.getOriginalFileName() );
		context.put( "originallines", statement.getOriginalLines() );

		File targetFile = new File( targetRoot, name + "ResultSet.java" );
		FileWriter writer = new FileWriter( targetFile );
		_resultSetTemplate.merge( context, writer );
		writer.flush();
		writer.close();

		created.add( targetFile );
	}

	public void generateInsert( InsertStatement statement, File targetRoot, String name, List<File> created )
			throws Exception
	{
		VelocityContext context = new VelocityContext();
		context.put( "packageName", statement.getPackage() );
		context.put( "className", statement.getName() );
		context.put( "sql", statement.getRetooledSQL() );
		context.put( "fields", statement.getFields() );
		context.put( "date", new Date() );
		context.put( "originalfile", statement.getOriginalFileName() );
		context.put( "originallines", statement.getOriginalLines() );

		File targetFile = new File( targetRoot, name + ".java" );
		FileWriter writer = new FileWriter( targetFile );
		_insertTemplate.merge( context, writer );
		writer.flush();
		writer.close();

		created.add( targetFile );
	}

	public void generateUpdate( UpdateStatement statement, File targetRoot, String name, List<File> created )
			throws Exception
	{
		VelocityContext context = new VelocityContext();
		context.put( "packageName", statement.getPackage() );
		context.put( "className", statement.getName() );
		context.put( "sql", statement.getRetooledSQL() );
		context.put( "fields", statement.getFields() );
		context.put( "conditions", statement.getConditions() );
		context.put( "date", new Date() );
		context.put( "originalfile", statement.getOriginalFileName() );
		context.put( "originallines", statement.getOriginalLines() );

		File targetFile = new File( targetRoot, name + ".java" );
		FileWriter writer = new FileWriter( targetFile );
		_updateTemplate.merge( context, writer );
		writer.flush();
		writer.close();

		created.add( targetFile );
	}

	public String trimQuotes( String text )
	{
		String result = text;
		int len = text.length();
		// Parser ensures token has quotes front and back
		if( text.indexOf( '[' ) > -1 || text.indexOf( '"' ) > -1 || text.indexOf( '\'' ) > -1 )
		{
			result = text.substring( 1, len - 1 );
		}
		return result;
	}

	public List<String> chopper( String sql )
	{
		StringReader sr = new StringReader( sql );
		LineReader lr = new LineReader( sr );
		List<String> result = lr.toArray();
		return result;
	}

	public void safeClose( ResultSet rs )
	{
		if( rs == null ) return;
		try
		{
			rs.close();
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}

	public static void dumpResultSet( ResultSet rs )
			throws SQLException
	{
		System.out.println();
		ResultSetMetaData meta = rs.getMetaData();
		int count = meta.getColumnCount();
		for( int i = 0; i < count; i++ )
		{
			String temp = meta.getColumnName( i + 1 );
			System.out.print( temp + "\t" );
		}
		System.out.print( "\n--\n" );
//		set.first();
		while( rs.next() )
		{
			for( int i = 0; i < count; i++ )
			{
				Object temp = rs.getObject( i + 1 );
				System.out.print( temp + "\t" );
			}
			System.out.print( "\n" );

		}
		System.out.println();
	}

}
