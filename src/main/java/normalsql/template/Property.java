// Copyright 2010-2023 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.template;

import normalsql.parse.NormalSQLParser.LiteralContext;

/**
 * Property (aka JavaBean) represents a class instance variable and its accessors
 *  (getters, setters).
 *
 * One Property is created for each ResultSet Column and each PreparedStatement Param.
 */

public abstract class
	Property
{

	public LiteralContext context() {return context;}
	public void context(LiteralContext context	) { this.context = context; }

	public int nth() {return nth;}
	public void nth( int nth	) { this.nth = nth; }

	public int sqlType() {return sqlType;}
	public void sqlType( int sqlType ) { this.sqlType = sqlType; }

	public String sqlTypeName() {return sqlTypeName;}
	public void sqlTypeName( String sqlTypeName ) { this.sqlTypeName = sqlTypeName; }

	public String className() {return className;}
	public void className(String className	) { this.className = className; }

	public String getter() {return getter;}
	public void getter(String getter	) { this.getter = getter; }

	public String setter() {return setter;}
	public void setter(String setter	) { this.setter = setter; }

	public String variable() {return variable;}
	public void variable(String variable	) { this.variable = variable; }

	public String original() {return original;}
	public void original(String original	) { this.original = original; }

	public String asCode() {return asCode;}
	public void asCode(String asCode	) { this.asCode = asCode; }
	/**
	 * LiteralContext
	 */
	private LiteralContext context;

	/**
     * Index scrapped from JDBC metadata
     */
	private int nth;

	/**
     * SQL data type, scrapped from JDBC metadata
     */
	private int sqlType;

	/**
	 * SQL's type name
	 */
	private String sqlTypeName;

	/**
     * Full class name scrapped from JDBC metadata
     */
	private String className;

	/**
     * Generated type-safe getter method
     */
	private String getter;

	/**
     * Generated type-safe setter method
     */
	private String setter;

	/**
     * Name of local variable instance
     */
	private String variable;

	/**
     * Original text copied from SQL source code. Used by templates to populate JavaDocs.
     */
	private String original;

	/**
     * The default SQL value literal transliterated to Java source.
	 *  eg SQL's FLOAT 10.0 would become Java's <code>10.0f</code>.
	 * Used by PreparedStatement templates.
	 *
     */
	// TODO rename to 'valueLiteral'
	private String asCode;
}
