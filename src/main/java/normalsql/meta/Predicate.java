// Copyright 2010-2022 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.meta;

import org.antlr.v4.runtime.ParserRuleContext;
import normalsql.parse.NormalSQLParser.SubtermContext;

/**
 * Predicate references parsed literals which will be replaced with a
 * placeholder question mark '?' in parsed query. Subclasses also
 * have any additional context needed for code generating the accessors.
 *
 *
 *
 */

// TODO: Rename to Knockout or something totally distinct from "param".

public abstract class
	Predicate<T extends ParserRuleContext, E extends Enum<E>>
{
	public T context;
	public ParserRuleContext parent;

	/**
	 * <p>Constructor for Predicate.</p>
	 *
	 * @param context a T object
	 */
	public Predicate( T context )
	{
		this.context = context;
		parent = (ParserRuleContext) context.parent;
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
