// Copyright 2010-2022 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.meta;

import normalsql.parse.NormalSQLParser.PredicateContext;
import normalsql.parse.NormalSQLParser.TermContext;

/**
 * <p>Abstract Predicate class.</p>
 *
 * @param <T> class extends PredicateContext
 * @author jasonosgood
 * @version $Id: $Id
 */
public abstract class
	Predicate<T extends PredicateContext, E extends Enum<E>>
{
	public T context;
	public TermContext parent;

	/**
	 * <p>Constructor for Predicate.</p>
	 *
	 * @param context a T object
	 */
	public Predicate( T context )
	{
		this.context = context;
		parent = (TermContext) context.parent;
	}

	public E pattern;

	public boolean isMatched()
	{
		return pattern != null;
	}

	public static <E extends Enum<E>> E valueOf( Class<E> ugh, TermContext... contexts )
	{
		String name = "";
		for( TermContext c : contexts )
		{
			String simple = c.getClass().getSimpleName();
			simple = simple.replaceFirst( "^Subterm", "" ).replaceFirst( "Context$", "" );
			name += simple;
		}

		E[] boo = ugh.getEnumConstants();
		for( E zip : boo )
		{
			if( zip.name().equals( name ) ) return zip;

		}

		return null;
	}


}
