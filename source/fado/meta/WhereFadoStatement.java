package fado.meta;

import java.util.ArrayList;
import java.util.List;

public class
WhereFadoStatement
extends
		FadoStatement
{
	private List<Condition> _conditions = new ArrayList<Condition>();
	
	public final void setConditions( List<Condition> conditions )
	{
		if( conditions == null )
		{
			throw new NullPointerException( "conditions" );
		}
		_conditions = conditions;
	}
	
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
