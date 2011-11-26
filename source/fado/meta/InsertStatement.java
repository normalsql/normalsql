package fado.meta;

import java.util.ArrayList;
import java.util.List;

public class 
	InsertStatement 
extends
	Statement
{
	private ArrayList<InsertColumn> _columns = null;
	
	public void setColumns( ArrayList<InsertColumn> columns )
	{
		_columns = columns;
	}
	
	public List<InsertColumn> getColumns()
	{
		return _columns;
	}
}
