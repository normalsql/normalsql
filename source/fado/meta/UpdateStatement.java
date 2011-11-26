package fado.meta;

import java.util.ArrayList;
import java.util.List;

public class 
	UpdateStatement 
extends
	WhereStatement
{
	private ArrayList<UpdateColumn> _columns = null;
	
	public void setColumns( ArrayList<UpdateColumn> columns )
	{
		_columns = columns;
	}
	
	public List<UpdateColumn> getColumns()
	{
		return _columns;
	}
}
