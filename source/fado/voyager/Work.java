package fado.voyager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

public class Work
{
	public Select root;
	public String originalSQL;
	public String preparedSQL;

	// Copied from the PreparedStatement's metadata
	public ArrayList<Param> params = new ArrayList<>();
	// Copied from the PreparedStatement's metadata
	public ArrayList<RSColumn> columns = new ArrayList<>();
	// All the terms (matched to params) in one flattened list.
	public ArrayList<Predicate> predicates = new ArrayList<>();
	public ArrayList<Accessor> accessors = new ArrayList<>();

	public HashMap<String, Object> asMap()
	{
		HashMap<String, Object> bug = new HashMap<>();
		try
		{
			Field[] fields = getClass().getFields();
			for( Field f : fields )
			{
				Object value = f.get( this );
				if( value != null )
				{
					bug.put( f.getName(), value );
				}
			}
		}
		catch( IllegalAccessException e )
		{
			// do nothing
		}
		return bug;
	}

}
