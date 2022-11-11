/*
 Fado - Comparison.java

 Copyright 2022, 2014, 2011, 2010 Jason Osgood

 Wrapper for TermComparisonContext.
 Represents comparison operators in SQL expressions.
*/

package fado.meta;

import static fado.parse.GenericSQLParser.*;

public class Comparison extends Predicate< PredicateCompareContext >
{
	public Comparison( PredicateCompareContext tc )
	{
		super( tc );
	}

	public enum Params
	{
		RefValue, // eg 'ref > 10'
		ValueRef, // eg '10 > ref'
		NotMatched
	}

//	public Between.Params params = Between.Params.NotMatched;

	public Params match()
	{
//		boolean leftIsValue   = tc.term().get( 0 ) instanceof TermValueContext;
//		boolean leftIsRef     = tc.term().get( 0 ) instanceof TermRefContext;
//		boolean rightIsValue  = tc.term().get( 1 ) instanceof TermValueContext;
//		boolean rightIsRef    = tc.term().get( 1 ) instanceof TermRefContext;

//		if( leftIsRef   && rightIsValue ) return Params.RefValue;
//		if( leftIsValue && rightIsRef   ) return Params.ValueRef;

		return Params.NotMatched;
	}

}