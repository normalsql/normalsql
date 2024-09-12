// Copyright 2010-2023 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.template;

import normalsql.jdbc.Column;
import normalsql.jdbc.Param;
import normalsql.parse.NormalSQLParser.LiteralContext;

/**
 * An Accessor represents a class instance variable and its accessor
 * methods (getter, setter). aka a JavaBean.
 *
 * One Accessor for each ResultSet Column and PreparedStatement Param.
 */

// TODO rename to Property
public class
	Accessor
{
	/**
	 * Column
	 */
	public Column column;
	/**
	 * Param
	 */
	public Param param;
	/**
	 * LiteralContext
	 */
	public LiteralContext context;

	/**
     * Index scrapped from JDBC metadata
     */
	public int nth;
	/**
     * SQL data type, scrapped from JDBC metadata
     */
	public int sqlType;
	/**
     * Full class name scrapped from JDBC metadata
     */
	public String className;
	/**
     * Shortened class name for use by templates
     */
	public String classShortName;
	public String typeName;

	/**
     * JDBC's underlying getter method
     */
	// TODO jdbcGetter
	/**
     * JDBC's underlying setter method
     */
	// TODO jdbcSetter
	/**
     * Generated type-safe getter method
     */
	public String getter;
	/**
     * Generated type-safe setter method
     */
	public String setter;

	/**
     * Name of local variable instance
     */
	public String variable;
	/**
     * Original text copied from source code or metadata
     */
	public String original;
	/**
     * Name (identifier) stripped of punctuation
     */
	public String trimmed;
	/**
     * The default SQL value literal transliterated to Java source.
	 *  eg SQL's FLOAT 10.0 would become Java's <code>10.0f</code>.
	 * Used by PreparedStatement templates.
	 *
     */
	// TODO rename to 'valueLiteral'
	public String asCode;
	/**
     * Used by ResultSet templates. To specify variable default initial value.
     */
     // TODO Are initial default values necessary? I can't remember why I added them.
    public String initial;

	/**
	 * @return String
	 */
	@Override
	public String toString()
	{
		return "Accessor{" +
			"column=" + column +
			", param=" + param +
			", context=" + context +
			", nth=" + nth +
			", variable='" + variable + '\'' +
			", source='" + original + '\'' +
			", trimmed='" + trimmed + '\'' +
			", getter='" + getter + '\'' +
			", setter='" + setter + '\'' +
			", className='" + className + '\'' +
			", classShortName='" + classShortName + '\'' +
			", sqlType=" + sqlType +
			'}';
	}
}
