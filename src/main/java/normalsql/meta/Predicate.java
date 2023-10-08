// Copyright 2010-2022 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.meta;

import org.antlr.v4.runtime.ParserRuleContext;
import normalsql.parse.NormalSQLParser.PredicateContext;
import normalsql.parse.NormalSQLParser.SubtermContext;

/**
 * Placeholder represents a question mark '?' in parsed query. In contrast
 * to a Param which represents a JDBC PreparedStatement Parameter.
 *
 *
 */

public abstract class
	Predicate<T extends ParserRuleContext, E extends Enum<E>>
//	Predicate<T extends PredicateContext, E extends Enum<E>>
{
	public T context;
	public SubtermContext parent;

	/**
	 * <p>Constructor for Predicate.</p>
	 *
	 * @param context a T object
	 */
	public Predicate( T context )
	{
		this.context = context;
		parent = (SubtermContext) context.parent;
	}

	public E pattern;

	public boolean isMatched()
	{
		return pattern != null;
	}

	public static <E extends Enum<E>> E valueOf( Class<E> ugh, SubtermContext... contexts )
	{
		StringBuilder name = new StringBuilder();
		for( SubtermContext c : contexts )
		{
			String simple = c.getClass().getSimpleName();
			simple = simple.replaceFirst( "^Subterm", "" ).replaceFirst( "Context$", "" );
			name.append(simple);
		}

		E[] boo = ugh.getEnumConstants();
		for( E zip : boo )
		{
			if( zip.name().equals( name.toString() )) return zip;

		}

		return null;
	}


}
