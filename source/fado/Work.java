package fado;

import fado.meta.Param;
import fado.meta.Predicate;
import fado.meta.Column;
import fado.meta.Statement;

import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

/**
 * Represents the work of processing one (1) SQL source file.
 *
 */

// TODO support multiple statements
// TODO support multiple resultsets
public class Work
{
	Statement root;

	// All the (matched) predicates in one flattened list.
	ArrayList<Predicate> predicates = new ArrayList<>();

	// Parameters copied from PreparedStatement's metadata
	ArrayList<Param> params = new ArrayList<>();
	// Columns copied from ResultSet's metadata
	public ArrayList<Column> columns = new ArrayList<>();

	public Path sourceFile;
	public Path targetDir;
	public String packageName;
	public String statementClassName;
	public String resultSetClassName;
	public String originalSQL;
	public String preparedSQL;
	public String printfSQL;
	public List<Property> statementProperties = new ArrayList<>();
	public List<Property> resultSetProperties;

	// Create map of meta data to be used as 'context map' for
	// Velocity template.
	//
	// Only adds 'public' fields to resulting map.
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
