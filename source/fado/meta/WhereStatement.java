package fado.meta;

import java.util.ArrayList;
import java.util.List;

public class 
	WhereStatement
extends
	Statement
{
	private ArrayList<Condition> _conditions = new ArrayList<Condition>();
	
	public final void addCondition( Condition condition )
	{
		if( condition == null )
		{
			throw new NullPointerException( "condition" );
		}
		_conditions.add( condition );
	}
	
	public final List<Condition> getConditions()
	{
		return _conditions;
	}
	

}
