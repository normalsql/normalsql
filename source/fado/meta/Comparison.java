/*
 Fado - Comparison.java

 Copyright 2022, 2014, 2011, 2010 Jason Osgood

 Represents comparison operators in SQL expressions.
*/

package fado.meta;

import static fado.parse.GenericSQLParser.*;

import org.antlr.v4.runtime.Token;

public class Comparison extends Condition
{
	public Token op;
//	public Comparison( Token op, ColumnRefContext columnRef, LiteralContext... literals )
//	{
//		super( columnRef, literals );
//		this.op = op;
//	}
}