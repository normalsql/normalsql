package fado;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.StringReader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import org.antlr.runtime.ANTLRReaderStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
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
import fado.meta.UpdateStatement;
import fado.meta.WhereStatement;
import fado.parse.ParseNode;
import fado.parse.GenericSQLLexer;
import fado.parse.GenericSQLParser;
import fado.parse.ParseTreeBuilder;

public class 
	Fado
{
	public static void main( String[] args ) 
		throws Exception
	{
		FadoOptions options = new FadoOptions();
		options.parse( args );
		System.out.println( options );
		Fado fado = new Fado();
		fado.init( options );
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
	
	public void init( FadoOptions options ) 
		throws Exception
	{
		_onlyParse = options.getOnlyParse();
		
		if( !_onlyParse )
		{
			String driver = options.getDriver();
			String url = options.getUrl();
			String username = options.getUsername();
			String password = options.getPassword();
			Class.forName( driver );
			_conn = DriverManager.getConnection( url, username, password );
	
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
		
		Stack<String> path = new Stack<String>();
		String pkg = options.getPackage().trim();
		if( !"".equals( pkg )) 
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
			String msg = sourceDir.getCanonicalPath().toString() + " (filespec: " + source + ")";
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
		ParseTreeBuilder builder = null;
		try 
		{

			List<File> created = new ArrayList<File>();
			
			FileReader reader = new FileReader( sourceFile );
	
			builder = new ParseTreeBuilder();
			CharStream cs = new ANTLRReaderStream( reader );
			GenericSQLLexer lexer = new GenericSQLLexer( cs );
			CommonTokenStream tokens = new CommonTokenStream( lexer );
			GenericSQLParser parser = new GenericSQLParser( tokens, builder );
	
			parser.statement();
			reader.close();
	
			ParseNode source = builder.getTree();
//			boolean displayTree = true;
//			if( displayTree )
//			{
//				System.out.println( source.toParseTree() );
//			}
			source.addLexType( "String", GenericSQLParser.String );
			
			String temp = source.toOriginalString();
			// Replace Java style comments in generated code, so /* */ becomes /@ @/.
			temp = temp.replace( "/*", "/@" ); 
			temp = temp.replace( "*/", "@/" ); 
			List<String> originalLines = chopper( temp.trim() );
			
			
	
			String originalText = source.toOriginalString();
			
			ParseNode selectNode = source.findFirstNode( "statement/select" );
			if( selectNode != null )
			{
				SelectStatement statement = new SelectStatement();
				statement.setName( name );
				statement.setPackage( pkg );
				statement.setOriginalFileName( sourceFile.toString() );
				statement.setOriginalText( originalText );
				statement.setOriginalLines( originalLines );
				
				extractSelect( selectNode, statement );
				String retooledSQL = builder.getTree().toOriginalString().trim();
				statement.setRetooledSQL( chopper( retooledSQL ));
				
				inspectDatabaseForSelect( _conn, statement );
	
				generateSelect( statement, targetRoot, name, created );
				generateResultSet( statement, targetRoot, name, created );
			}
	
			ParseNode insertNode = source.findFirstNode( "statement/insert" );
			if( insertNode != null )
			{
				InsertStatement statement = new InsertStatement();
				statement.setName( name );
				statement.setPackage( pkg );
				statement.setOriginalFileName( sourceFile.toString() );
				statement.setOriginalText( originalText );
				statement.setOriginalLines( originalLines );
				
				extractInsert( insertNode, statement );
				String retooledSQL = builder.getTree().toOriginalString().trim();
				statement.setRetooledSQL( chopper( retooledSQL ));

				inspectDatabaseForInsert( _conn, statement );
				generateInsert( statement, targetRoot, name, created );
			}
	
			ParseNode updateNode = source.findFirstNode( "statement/update" );
			if( updateNode != null )
			{
				UpdateStatement statement = new UpdateStatement();
				statement.setName( name );
				statement.setPackage( pkg );
				statement.setOriginalFileName( sourceFile.toString() );
				statement.setOriginalText( originalText );
				statement.setOriginalLines( originalLines );
				
				extractUpdate( updateNode, statement );
				String retooledSQL = builder.getTree().toOriginalString().trim();
				statement.setRetooledSQL( chopper( retooledSQL ));
				
				inspectDatabaseForUpdate( _conn, statement );
				
				generateUpdate( statement, targetRoot, name, created );
			}
			return created;
		}
		catch( Exception e )
		{
			if( displayParseTree && builder != null )
			{
				System.out.println( "string tree: " + builder.getTree().toParseTree() );
			}
			throw e;
		}
	}

	private void extractSelect( ParseNode selectNode, SelectStatement statement ) 
		throws Exception
	{
		if( _onlyParse ) return;
		extractSelectTables( selectNode, statement );
		extractSelectColumns( selectNode, statement );
		extractSelectParams( selectNode, statement );
	}

	public void extractSelectTables( ParseNode selectNode, SelectStatement statement )
	{
		{
			List<ParseNode> list = selectNode.findNodes( "from/fromItem" );
			for( ParseNode item : list )
			{
				ParseNode tableRef = item.findFirstNode( "tableRef" );
				String databaseName = tableRef.findFirstString( "databaseName" );
				String tableName = tableRef.findFirstString( "**/tableName" );
				String alias = item.findFirstString( "alias" );
				Table table = new Table( databaseName, tableName, alias );
				statement.addTable( table );
			}
		}
		{
			List<ParseNode> list = selectNode.findNodes( "joinList/join" );
			for( ParseNode item : list )
			{
				ParseNode tableRef = item.findFirstNode( "**/tableRef" );
				String databaseName = tableRef.findFirstString( "databaseName" );
				String tableName = tableRef.findFirstString( "**/tableName" );
				String alias = item.findFirstString( "alias" );
				Table table = new Table( databaseName, tableName, alias );
				statement.addTable( table );
			}
		}
	}

	public void extractSelectColumns( ParseNode selectNode, SelectStatement statement ) 
		throws Exception
	{
//		String allStar = selectNode.findFirstString( "itemList" );
//		if( "*".equals( allStar ) )
//		{
//			for( Table table : statement.getTables() )
//			{
//				statement.tempColumn( new Column( table ) );
//			}
//		}
//		else
		{
			List<ParseNode> list = selectNode.findNodes( "itemList/item" );
			for( ParseNode item : list )
			{
//				// All columns from a table, eg alias.*
//				String tableAlias = item.findFirstString( "allColumns/tableAlias" );
//				if( tableAlias != null )
//				{
//					Table table = statement.getTable( tableAlias );
//					Column column = new Column( table );
//					statement.tempColumn( column );
//					continue;
//				}

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
	}

	public void extractSelectParams( ParseNode selectNode, SelectStatement statement ) 
		throws FadoException
	{
		for( ParseNode where : selectNode.findNodes( "where" ))
		{
			List<ParseNode> clist = where.findNodes( "**/condition" );
			for( ParseNode condition : clist )
			{
				for( ParseNode comparison : condition.findNodes( "comparison" ))
				{
					extractComparisonParams( comparison, statement );
					break;
				}
				
				for( ParseNode in : condition.findNodes( "in" ))
				{
					extractINParams( in, statement );
					break;
				}
				
				for( ParseNode between : condition.findNodes( "between" ))
				{
					extractBETWEENParams( between, statement );
					break;
				}
				
				for( ParseNode like : condition.findNodes( "like" ))
				{
					extractLIKEParams( like, statement );
					break;
				}
			}
			break;
		}

	}

	private void extractInsert( ParseNode insertNode, InsertStatement statement ) 
		throws Exception
	{
		if( _onlyParse ) return;
		ParseNode tableRef = insertNode.findFirstNode( "into/tableRef" );
		String databaseName = tableRef.findFirstString( "databaseName" );
		String tableName = tableRef.findFirstString( "**/tableName" );
		String alias = null;
		Table table = new Table( databaseName, tableName, alias );
		statement.addTable( table );

		ArrayList<Field> fields = new ArrayList<Field>();
		List<ParseNode> list = insertNode.findNodes( "columnList/columnName" );
		for( ParseNode item : list )
		{
			String name = item.toParsedString();
			fields.add( new Field( name ) );
		}

		List<ParseNode> literals = insertNode.findNodes( "values/literal" );

		if( fields.size() != literals.size() )
		{
			throw new Exception( "mismatch, fields: " + fields.size() + ", literals: " + literals.size() );
		}

		int nth = 0;
		for( ParseNode node : literals )
		{
			String literal = node.toParsedString();
			literal = trimQuotes( literal );

			node.convertToJDBCParam();

			Field field = fields.get( nth );
			field.setLiteral( literal );
			nth++;
		}
		statement.setFields( fields );
	}

	// TODO support unary literals (done?)
	private void extractUpdate( ParseNode updateNode, UpdateStatement statement ) 
		throws Exception
	{
		if( _onlyParse ) return;
		ParseNode tableRef = updateNode.findFirstNode( "tableRef" );
		String databaseName = tableRef.findFirstString( "databaseName" );
		String tableName = tableRef.findFirstString( "**/tableName" );
		String alias = null;
		Table table = new Table( databaseName, tableName, alias );
		statement.addTable( table );

		ArrayList<Field> fields = new ArrayList<Field>();
		List<ParseNode> setters = updateNode.findNodes( "setter" );
		for( ParseNode node : setters )
		{
			String name = node.findFirstString( "columnName" );
			ParseNode literalNode = node.findFirstNode( "literal" );
			String literal = literalNode.toParsedString();
			literal = trimQuotes( literal );
			
			literalNode.convertToJDBCParam();

			fields.add( new Field( name, literal ));
		}
		statement.setFields( fields );

		for( ParseNode where : updateNode.findNodes( "where" ))
		{
			List<ParseNode> clist = where.findNodes( "**/condition" );
			for( ParseNode condition : clist )
			{
				for( ParseNode comparison : condition.findNodes( "comparison" ))
				{
					extractComparisonParams( comparison, statement );
					break;
				}
				
				for( ParseNode in : condition.findNodes( "in" ))
				{
					extractINParams( in, statement );
					break;
				}
				
				for( ParseNode between : condition.findNodes( "between" ))
				{
					extractBETWEENParams( between, statement );
					break;
				}
				
				for( ParseNode like : condition.findNodes( "like" ))
				{
					extractLIKEParams( like, statement );
					break;
				}
			}
			break;
		}
	}
	
	// Comparisons, eg column = 'abc'
	public void extractComparisonParams( ParseNode comparisonNode, WhereStatement statement )
		throws TableNotFoundException
	{
		List<ParseNode> columns = comparisonNode.findNodes( "**/columnRef" );
		List<ParseNode> literals = comparisonNode.findNodes( "**/literal" );
		if( columns.size() == 1 && literals.size() == 1 )
		{
			// System.out.println( "found comparison: " + comparison.toInputString() );
			ParseNode columnRef = columns.get( 0 );
			String column = columnRef.findFirstString( "columnName" );
			String tableAlias = columnRef.findFirstString( "tableAlias" );
			Table table = null;
			if( tableAlias != null )
			{
				table = statement.getTable( tableAlias );
			}

			ParseNode node = literals.get( 0 );
			String literal = node.toParsedString();
			literal = trimQuotes( literal );
			Comparison comparison = new Comparison( table, column, literal );
			
			statement.addCondition( comparison );
			
			node.convertToJDBCParam();
		}
	}

	// BETWEEN, eg column BETWEEN 1 AND 100
	// TODO: BETWEEN condition
	public void extractBETWEENParams( ParseNode betweenNode, WhereStatement statement ) 
		throws FadoException
	{
		List<ParseNode> columns = betweenNode.findNodes( "**/columnRef" );
		List<ParseNode> literals = betweenNode.findNodes( "**/literal" );
		if( columns.size() == 1 && literals.size() == 2 )
		{
			ParseNode columnRef = columns.get( 0 );
			String column = columnRef.findFirstString( "columnName" );
			String tableAlias = columnRef.findFirstString( "tableAlias" );
			Table table = statement.getTable( tableAlias );

			BETWEEN between = new BETWEEN( table, column );

			ParseNode leftNode = literals.get( 0 );
			String left = leftNode.toParsedString();
			left = trimQuotes( left );
			leftNode.convertToJDBCParam();
			between.setLeft( left );

			ParseNode rightNode = literals.get( 1 );
			String right = rightNode.toParsedString();
			right = trimQuotes( right );
			rightNode.convertToJDBCParam();
			between.setRight( right );

			statement.addCondition( between );
		}
	}

	// LIKE, eg column LIKE 'abc%'
	// TODO: LIKE condition
	public void extractLIKEParams( ParseNode likeNode, WhereStatement statement ) 
			throws FadoException
		{
			List<ParseNode> columns = likeNode.findNodes( "**/columnRef" );
			List<ParseNode> literals = likeNode.findNodes( "**/literal" );
			if( columns.size() == 1 && literals.size() == 1 )
			{
				ParseNode columnRef = columns.get( 0 );
				String column = columnRef.findFirstString( "columnName" );
				String tableAlias = columnRef.findFirstString( "tableAlias" );
				Table table = statement.getTable( tableAlias );

				LIKE like = new LIKE( table, column );

				ParseNode patternNode = literals.get( 0 );
				String pattern = patternNode.toParsedString();
				pattern = trimQuotes( pattern );
				patternNode.convertToJDBCParam();
				like.setPattern( pattern );

				statement.addCondition( like );
			}
		}


	// IN, eg column IN ( 1, 2, 3 )
	// Very naive pattern extraction, assumes column on left and literals on right
	public void extractINParams( ParseNode inNode, WhereStatement statement ) 
		throws FadoException
	{
		List<ParseNode> columns = inNode.findNodes( "**/columnRef" );
		List<ParseNode> literals = inNode.findNodes( "**/literal" );
		if( columns.size() == 1 && literals.size() > 0 )
		{
			ParseNode columnRef = columns.get( 0 );
			String column = columnRef.findFirstString( "columnName" );
			String tableAlias = columnRef.findFirstString( "tableAlias" );
			Table table = null;
			if( tableAlias != null )
			{
				table = statement.getTable( tableAlias );
			}
			IN in = new IN( table, column );

			for( ParseNode node : literals )
			{
				String text = node.toParsedString();
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
				
				for( int i = 1; i <= count; i++ )
				{
//					String cls = meta.getColumnClassName( i );
					String name = meta.getColumnName( i );
					int sqlType = meta.getColumnType( i );
					String sqlTypeName = meta.getColumnTypeName( i );
					int nullable = meta.isNullable( i );
					for( Column temp : tempList )
					{
						if( name.equalsIgnoreCase( temp.getAlias() ))
						{
							name = temp.getAlias();
							tempList.remove( temp );
							break;
						}
//						{
//							name = temp.getName();
//							tempList.remove( temp );
//							break;
//						}
					}
					Column column = new Column( name, sqlType, sqlTypeName );
					column.setNullable( nullable == DatabaseMetaData.columnNullable );
					select.addFinalColumn( column );
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
					String tempTableName =  tableRS.getString( "TABLE_NAME" );
					boolean tableMatch = tableName.equalsIgnoreCase( tempTableName );
					if( tableMatch ) foundTable = true;

					String tempTableSchema = tableRS.getString( "TABLE_SCHEM" );
					boolean schemaMatch = "PUBLIC".equalsIgnoreCase( tempTableSchema );
					String tempTableCatalog = tableRS.getString( "TABLE_CAT" );
					boolean catalogMatch = catalog.equalsIgnoreCase( tempTableCatalog );
					if( tableMatch && schemaMatch && catalogMatch )
					{
						ResultSet columnRS  = meta.getColumns( tempTableCatalog, tempTableSchema, tempTableName, null );
						while( columnRS.next() && !foundColumn )
						{
							String tempColumnName = columnRS.getString(  "COLUMN_NAME" );
							boolean columnMatch = columnName.equalsIgnoreCase( tempColumnName );
							if( columnMatch )
							{
								int sqlType = columnRS.getInt( "DATA_TYPE" );
								condition.setSQLType( sqlType );
								int nullable = columnRS.getInt( "NULLABLE" );
								condition.setNullable( nullable == DatabaseMetaData.columnNullable );
								System.out.printf( "searched for column: %s  found: %s\n", columnName, tempColumnName );
								foundColumn = true;
							}
						}
						columnRS.close();
					}
				}
				tableRS.close();
			}

			if( !foundTable  && condition.hasTable() )
			{
				throw new Exception( "condition's table ref not found: " + condition.getTable().getName() );
			}
			if( !foundColumn )
			{
				throw new Exception( "condition's column ref not found: " + columnName );
			}
		}
	}

	public void inspectDatabaseForInsert( Connection conn, InsertStatement extract ) 
		throws Exception
	{
		if( _onlyParse ) return;

		DatabaseMetaData meta = conn.getMetaData();

		String catalog = null;
		String schema = null;
		Table table = extract.getTables().get( 0 );
//		String tableName = table.getName().toUpperCase();
		String tableName = table.getName();
		
		// Move declared fields to found list as able
		List<Field> declaredFields = extract.getFields();
		List<Field> foundFields = new ArrayList<Field>();
		
		// TODO first verify table exists
		// TODO Table name may also need case insensitive handling
		ResultSet columnRS  = meta.getColumns( catalog, schema, tableName, null );
//		dumpResultSet( columnRS );
//		columnRS.beforeFirst();
		while( columnRS.next() )
		{
			String columnName = columnRS.getString( "COLUMN_NAME" );
			Iterator<Field> fieldIterator = declaredFields.iterator();
			while( fieldIterator.hasNext()  )
			{
				Field field = fieldIterator.next();
				if( field.getName().equalsIgnoreCase( columnName ) )
				{
					int sqlType = columnRS.getInt( "DATA_TYPE" );
					field.setSQLType( sqlType );
					String sqlTypeName = columnRS.getString( "TYPE_NAME" );
					field.setSQLTypeName( sqlTypeName );
					int nullable = columnRS.getInt( "NULLABLE" );
					field.setNullable( nullable == DatabaseMetaData.columnNullable );

					fieldIterator.remove();
					foundFields.add( field );
					
					break;
				}
			}
		}
		columnRS.close();
		
		// Found all the declared fields, copy them back into place
		if( declaredFields.isEmpty() )
		{
			extract.setFields( foundFields );
		}
		else
		{
			// TODO Don't die on first error
			Field field = declaredFields.get( 0 );
			String name = field.getName();
			throw new Exception( "field '" + name + "' not found in table '" + tableName + "'" );
		}
	}

	public void inspectDatabaseForUpdate( Connection conn, UpdateStatement extract ) 
		throws Exception
	{
		if( _onlyParse ) return;

		DatabaseMetaData meta = conn.getMetaData();

		String catalog = null;
		String schema = null;
		Table table = extract.getTables().get( 0 );
//		String tableName = table.getName().toUpperCase();
		String tableName = table.getName();
		
		// Move declared fields to found list as able
		List<Field> declaredFields = extract.getFields();
		List<Field> foundFields = new ArrayList<Field>();
		
		// Move declared conditions to found list as able
		List<Condition> declaredConditions = extract.getConditions();
		List<Condition> foundConditions = new ArrayList<Condition>();
		
		// TODO Table name may also need case insensitive handling
		ResultSet columnRS  = meta.getColumns( catalog, schema, tableName, null );
//		dumpResultSet( columnRS );
//		columnRS.beforeFirst();
		while( columnRS.next() )
		{
			String columnName = columnRS.getString( "COLUMN_NAME" );
			int sqlType = columnRS.getInt( "DATA_TYPE" );
			String sqlTypeName = columnRS.getString( "TYPE_NAME" );
			int nullable = columnRS.getInt( "NULLABLE" );
			
			Iterator<Field> fieldIterator = declaredFields.iterator();
			while( fieldIterator.hasNext()  )
			{
				Field field = fieldIterator.next();
				if( field.getName().equalsIgnoreCase( columnName ) )
				{
					field.setSQLType( sqlType );
					field.setSQLTypeName( sqlTypeName );
					field.setNullable( nullable == DatabaseMetaData.columnNullable );

					fieldIterator.remove();
					foundFields.add( field );
					
					break;
				}
			}
			
			Iterator<Condition> conditionIterator = declaredConditions.iterator();
			while( conditionIterator.hasNext()  )
			{
				Condition condition = conditionIterator.next();
				if( condition.getName().equalsIgnoreCase( columnName ) )
				{
					condition.setSQLType( sqlType );
					condition.setSQLTypeName( sqlTypeName );
					condition.setNullable( nullable == DatabaseMetaData.columnNullable );

					conditionIterator.remove();
					foundConditions.add( condition );
					
					break;
				}
			}
		}
		columnRS.close();
		
		// Found all the declared fields, copy them back into place
		if( declaredFields.isEmpty() )
		{
			extract.setFields( foundFields );
		}
		else
		{
			// TODO Don't die on first error
			Field field = declaredFields.get( 0 );
			String name = field.getName();
			throw new Exception( "field '" + name + "' not found in table '" + tableName + "'" );
		}		
		
		// Found all the declared fields, copy them back into place
		if( declaredConditions.isEmpty() )
		{
			extract.setConditions( foundConditions );
		}
		else
		{
			// TODO Don't die on first error
			Condition condition = declaredConditions.get( 0 );
			String name = condition.getName();
			throw new Exception( "field '" + name + "' not found in table '" + tableName + "'" );
		}		
		
	}

	public void generateSelect( SelectStatement statement, File targetRoot, String name, List<File> created ) 
		throws Exception
	{
		if( _onlyParse ) return;
		
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

	public void generateSelect( SelectStatement statement, Writer writer ) 
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

		_selectTemplate.merge( context, writer );
	}

	public void generateResultSet( SelectStatement statement, File targetRoot, String name, List<File> created ) 
		throws Exception
	{
		if( _onlyParse ) return;
		
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
		if( _onlyParse ) return;
		
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
		if( _onlyParse ) return;
		
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
