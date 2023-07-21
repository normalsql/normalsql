// Copyright 2010-2022 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql;

import normalsql.meta.Column;
import normalsql.meta.Param;
import normalsql.parse.NormalSQLParser.LiteralContext;

/**
 * Property represents a class instance variable for code generation. aka JavaBean.
 * One Property for each PreparedStatement Param.
 *
 * @author jasonosgood
 * @version $Id: $Id
 */

// TODO Maybe split into Statement and ResultSet specific subclasses.
public class
	Property
{
	public Column column;
	public Param param;
	public LiteralContext context;

	// Index scrapped from JDBC metadata
	public int nth;
	// SQL data type, scrapped from JDBC metadata
	public int sqlType;
	// Full class name scrapped from JDBC metadata
	public String className;
	// Shortened class name for use by templates
	public String classShortName;

	// Name of local variable instance
	public String variable;
	// Copy of clause from source code
	public String source;
	// Name (identifier) stripped of punctuation
	public String trimmed;
	// Generated getter method
	public String getter;
	// Generated setter method
	public String setter;

	// Used by PreparedStatement templates. Syntax conversion of value from SQL to Java.
	public String asCode;
	// Used by ResultSet templates. To specify variable default initial value.
	public String initial;

	/** {@inheritDoc} */
	@Override
	public String toString()
	{
		return "Property{" +
			"column=" + column +
			", param=" + param +
			", context=" + context +
			", nth=" + nth +
			", variable='" + variable + '\'' +
			", source='" + source + '\'' +
			", trimmed='" + trimmed + '\'' +
			", getter='" + getter + '\'' +
			", setter='" + setter + '\'' +
			", className='" + className + '\'' +
			", classShortName='" + classShortName + '\'' +
			", sqlType=" + sqlType +
			'}';
	}
}
