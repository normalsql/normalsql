/*
 Fado - IN.java

 Copyright 2022, 2014, 2011, 2010 Jason Osgood

  Represents IN operator in SQL expressions.
*/
package fado.meta;

import static fado.parse.GenericSQLParser.*;

public class IN extends Predicate< PredicateINContext >
{
	public IN( PredicateINContext tc )
	{
		super( tc );
	}
}