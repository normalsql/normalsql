package fado.meta;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class 
	Statement 
{
	private String _name = null;
	
	public void setName( String name )
	{
		_name = name;
	}
	
	public String getName()
	{
		return _name;
	}
	
	private String _package;
	public void setPackage( String pkg )
	{
		_package = pkg;
	}
	
	public String getPackage()
	{
		return _package;
	}
	
	
	
	private String _rewrite = "n/a";

	public void setRewrite( String rewrite )
	{
		if( rewrite == null )
		{
			throw new NullPointerException( "rewrite" );
		}
		_rewrite = rewrite;
	}
	
	public String getRewrite()
	{
		return _rewrite;
	}
	
	
	private String _originalFileName = null;
	
	public void setOriginalFileName( String originalFileName )
	{
		_originalFileName = originalFileName;
	}
	
	public String getOriginalFileName()
	{
		return _originalFileName;
	}
	
	private List<String> _originalSQL = null;
	
	public void setOriginalSQL( List<String> originalSQL )
	{
		_originalSQL = originalSQL;
	}
	
	public List<String> getOriginalSQL()
	{
		return _originalSQL;
	}

	private ArrayList<Table> _tables = new ArrayList<Table>(); 
	
	public void addTable( Table table )
	{
		if( table == null )
		{
			throw new NullPointerException( "table" );
		}
		_tables.add( table );
	}
	
	public List<Table> getTables()
	{
		return _tables;
	}
	
	public Table getTable( String name )
		throws TableNotFoundException
	{
		if( name == null ) return null;
		List<Table> tables = getTables();
		
		for( Table table : tables )
		{
			if( name.equals( table.getAlias() ) || name.equals( table.getName() ))
			{
				return table;
			}
		}
		
		throw new TableNotFoundException( "table alias not found: " + name );
	}

	private File _source;
	
	public void setSourceFile( File source )
	{
		_source = source;
	}
	
	public File getSourceFile()
	{
		return _source;
	}
}
