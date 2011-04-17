package fado.meta;

import java.util.ArrayList;
import java.util.List;

public class 
	UpdateMeta 
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
	
	private ArrayList<UpdateColumn> _columns = null;
	
	public void setColumns( ArrayList<UpdateColumn> columns )
	{
		_columns = columns;
	}
	
	public List<UpdateColumn> getColumns()
	{
		return _columns;
	}
	
	private String _table = null; 
	
	public void setTable( String table )
	{
		if( table == null )
		{
			throw new NullPointerException( "table" );
		}
		_table = table;
	}
	
	public String getTable()
	{
		return _table;
	}
	
	private ArrayList<Condition> _conditions = new ArrayList<Condition>();
	
	public void addCondition( Condition condition )
	{
		if( condition == null )
		{
			throw new NullPointerException( "condition" );
		}
		_conditions.add( condition );
	}
	
	public List<Condition> getConditions()
	{
		return _conditions;
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
}
