package fado.meta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class 
	SelectStatement
extends
	WhereStatement
{
//	/** Temporary columns are what's found in the parsed SQL command. When found,
//	 * they're moved to the final columns list.
//	 */
	
	/**
	 * Temp columns capture query specified case sensitive alias for columns.
	 * 
	 */
	private ArrayList<Column> _tempColumns = new ArrayList<Column>();
	
	public void tempColumn( Column column )
	{
		if( column == null )
		{
			throw new NullPointerException( "column" );
		}
		_tempColumns.add( column );
	}
	
	public List<Column> getTempColumns()
	{
		return _tempColumns;
	}
	
	private ArrayList<Column> _columns = new ArrayList<Column>();
	private HashMap<String,Column> _columnMap = new HashMap<String,Column>();
	
	public void addFinalColumn( Column column )
	{
		if( column == null )
		{
			throw new NullPointerException( "column" );
		}
		_columns.add( column );
		String key = column.getName();
		_columnMap.put( key, column );
	}
	
	public List<Column> getFinalColumns()
	{
		return _columns;
	}
	
	public Column getFinalColumn( String key )
	{
		return _columnMap.get( key );
	}
	
}
