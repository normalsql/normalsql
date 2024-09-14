// Copyright 2010-2023 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql;

import normalsql.template.Column;
import normalsql.template.Param;
import normalsql.parse.Statement;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Represents the work of processing one (1) SQL source file.
 *
 */

// TODO support multiple statements
// TODO support multiple resultsets
public class Work
{
	Statement root;

	public Path sourceDir;
	public Path sourceFile;
	public Path targetDir;
	public Path targetFile;
	public String packageName;
	public String statementClassName;
	public String resultSetClassName;
	public String originalSQL;
	public String preparedSQL;
	public String printfSQL;

	public List<Param> params = new ArrayList<>();
	public List<Column> columns = new ArrayList<>();

	/**
	 * "Serializes" this POJO into a HashMap, to be used as 'context map' for
	 * Velocity templates. Only adds 'public' fields to resulting map.
	 *
	 * Passing values into a Velocity template sucks. This is the least
	 * turrible strategy I could think of.
	 *
	 * @return a {@link java.util.HashMap} object
	 */
	public HashMap<String, Object> asMap()
	{
		var map = new HashMap<String, Object>();
		try
		{
			for( var f : getClass().getFields() )
			{
				var value = f.get( this );
				if( value != null )
				{
					map.put( f.getName(), value );
				}
			}
		}
		catch( IllegalAccessException e )
		{
			// do nothing
		}
		return map;
	}
}
