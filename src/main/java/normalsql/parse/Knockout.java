// Copyright 2010-2023 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.parse;

import org.antlr.v4.runtime.ParserRuleContext;
import normalsql.parse.NormalSQLParser.SubtermContext;

// TODO Create subclasses for subterm and predicate rules. Specialize valueOf method accordingly.


/**
 * A Knockout references a parse tree containing literals to be considered
 * for replacement by a placeholder question mark '?' by the code generator.
 *
 * Subclasses will have any additional context needed for code generating
 * their corresponding accessors.
 */


public abstract class
	Knockout<T extends ParserRuleContext, E extends Enum<E>>
//	Knockout<T extends SubtermContext, E extends Enum<E>>
{
	public T context;
	public ParserRuleContext parent;

	public Knockout( T context )
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
			if( zip.name().contentEquals( name )) return zip;
		}

		return null;
	}
}
