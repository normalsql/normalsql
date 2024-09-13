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

	public String sqlTypeName() { return sqlTypeName; }
	public void sqlTypeName( String sqlTypeName ) { this.sqlTypeName = sqlTypeName; }

	public String className() {return className;}
	public void className(String className	) { this.className = className; }

	public String getter() {return getter;}
	public void getter(String getter	) { this.getter = getter; }

	public String setter() {return setter;}
	public void setter(String setter	) { this.setter = setter; }

	public String variable() {return variable;}
	public void variable(String variable	) { this.variable = variable; }

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
	 * SQL's type name
	 */
	public String sqlTypeName;

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

	// TODO move 'original' to Param



	public int isNullable;
	public int isNullable() {return isNullable;}
	public void isNullable(int isNullable	) { this.isNullable = isNullable; }

}
