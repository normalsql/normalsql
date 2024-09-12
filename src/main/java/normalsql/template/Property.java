// Copyright 2010-2023 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.template;

import normalsql.jdbc.Column;
import normalsql.jdbc.Param;
import normalsql.parse.NormalSQLParser.LiteralContext;

/**
 * Property (aka JavaBean) represents a class instance variable and its accessors
 *  (getters, setters).
 *
 * One Property is created for each ResultSet Column and each PreparedStatement Param.
 */

public class
	Property
{
	/**
	 * Remember parent Column, for debugging
	 */
	public Column column;
	/**
	 * Remember parent Param, for debugging
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
//	public int sqlType;
	/**
	 * SQL's type name
	 */
//	public String typeName;

	/**
     * Full class name scrapped from JDBC metadata
     */
	public String className;

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
     * Original text copied from SQL source code. Used by templates to populate JavaDocs.
     */
	public String original;
	/**
     * Name (identifier) stripped of punctuation
     */
//	public String trimmed;
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
//     // TODO Are initial default values necessary? I can't remember why I added them.
//    public String initial;
}
