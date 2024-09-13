// Copyright 2010-2023 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql;

import normalsql.jdbc.Column;
import normalsql.parse.Knockout;
import normalsql.parse.KnockoutVisitor;
import normalsql.parse.Statement;
import normalsql.template.Property;

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
public class
	Work
{
	KnockoutVisitor visitor;
	Statement root;

	// All the (matched) knockouts in one flattened list.
	ArrayList<Knockout<?,?>> knockouts;

	// Columns copied from ResultSet's metadata
	public ArrayList<Column> columns;

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
	public List<Property> statementProperties = new ArrayList<>();
	public List<Property> resultSetProperties;

	// "Serializes" this POJO into a HashMap, to be used as 'context map' for
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
