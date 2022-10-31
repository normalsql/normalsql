/*
 Fado - Work.java

 Copyright 2022, 2014, 2011, 2010 Jason Osgood

 Class Work is used to pass around data.
*/
package fado.meta;

import org.antlr.v4.runtime.CommonTokenStream;

import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class Work
{
	// TODO make RSColumn inner class

	public Path sourceFile;
	public Path targetFile;
	public String packageName;
	public String className;
	public SelectList root;
	public CommonTokenStream tokens;
	public String originalSQL;
	public String preparedSQL;
	public String printfSQL;
	// All the conditions from all the selects and sub-selects gathered into one list
	public ArrayList<Condition> conditionList = new ArrayList<>();
	// Copied from the PreparedStatement's metadata
	public ArrayList<Param> paramList = new ArrayList<>();
	// Copied from the PreparedStatement's metadata
	public ArrayList<RSColumn> columnList = new ArrayList<>();

	public HashMap<String, Object> asMap()
	{
		HashMap<String, Object>  bug = new HashMap<>();
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