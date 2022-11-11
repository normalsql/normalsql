/*
 Fado - Condition.java

 Copyright 2022, 2014, 2011, 2010 Jason Osgood

 Superclass representing conditions in SQL expressions.
*/

package fado.meta;

import static fado.parse.GenericSQLParser.PredicateContext;

public abstract class Predicate< T extends PredicateContext >
{
	public T tc;

	public Predicate( T tc )
	{
		this.tc = tc;
	}

}
