package fado.voyager;

import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class Work
{
	Statement root;

	// Copied from the PreparedStatement's metadata
	ArrayList<Param> params = new ArrayList<>();
	ArrayList<RSColumn> columns = new ArrayList<>();

	// All the (matched) predicates in one flattened list.
	ArrayList<Predicate> predicates = new ArrayList<>();


	public String packageName;
	public String className;
	public Path sourceFile;
	public String originalSQL;
	public String preparedSQL;
	public String printfSQL;
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
