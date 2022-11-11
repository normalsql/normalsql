/*
 Fado - Between.java

 Copyright 2022, 2014, 2011, 2010 Jason Osgood

 Wrapper for TermBetweenContext.
 Represents BETWEEN operator in SQL expressions.
*/
package fado.meta;

import static fado.parse.GenericSQLParser.*;

public class Between extends Predicate< PredicateBETWEENContext >
{
	public Between( PredicateBETWEENContext tc )
	{
		super( tc );
	}

	public enum Params
	{
		LowHigh,      // isValueBetween( low, high )     eg '5 BETWEEN low AND high'
		Value,        // isBetweenLowAndHigh( value )    eg 'value BETWEEN 0 AND 10'
		ValueHigh,    // isBetweenLowAnd( value, high )  eg '5 BETWEEN low AND 10'
		ValueLow,     // isBetweenAndHigh( value, low )  eg '5 BETWEEN 0 AND high'
		ValueLowHigh, // isBetween( value, low, high )   eg '5 BETWEEN 0 AND 10'
		NotMatched
	}

	public Params params = Params.NotMatched;

	public Params match()
	{
//		boolean leftIsValue = tc.left instanceof TermValueContext;
//		boolean leftIsRef   =   tc.left instanceof TermRefContext;
//		boolean lowIsValue  =   tc.low   instanceof TermValueContext;
//		boolean lowIsRef    =     tc.low   instanceof TermRefContext;
//		boolean highIsValue =  tc.high  instanceof TermValueContext;
//		boolean highIsRef   =    tc.high  instanceof TermRefContext;

//		if( leftIsRef   && lowIsValue && highIsValue ) return Params.LowHigh;
//		if( leftIsValue && lowIsRef   && highIsRef ) return Params.Value;
		// TODO add rest of tests
		return Params.NotMatched;
	}

//	public String value() { return left.getTrimmedText(); }
//	public String low() { return right.get( 0 ).getTrimmedText(); }
//	public String high() { return right.get( 1 ).getTrimmedText(); }
}