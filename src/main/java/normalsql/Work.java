// Copyright 2010-2022 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql;

import normalsql.meta.Column;
import normalsql.meta.Param;
import normalsql.meta.Predicate;
import normalsql.meta.Statement;

import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Represents the work of processing one (1) SQL source file.
 *
 * @author jasonosgood
 * @version $Id: $Id
 */

// TODO support multiple statements
// TODO support multiple resultsets
public class Work
{
	Statement root;

	// All the (matched) predicates in one flattened list.
	ArrayList<Predicate<?>> predicates = new ArrayList<>();

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
	/**
	 * <p>asMap.</p>
	 *
	 * @return a {@link java.util.HashMap} object
	 */
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
