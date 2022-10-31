/*
 Fado - Between.java

 Copyright 2022, 2014, 2011, 2010 Jason Osgood

 Represents BETWEEN operator in SQL expressions.
*/
package fado.meta;

import static fado.parse.GenericSQLParser.*;

public class Between extends Condition
{
	public ValueContext lower;
	public ValueContext upper;

	public Between( RefContext columnRef, ValueContext... literals )
	{
		super( columnRef, literals );
		if( literals.length > 1 )
		{
			this.lower = literals[ 0 ];
			this.upper = literals[ 1 ];
		}
	}
}