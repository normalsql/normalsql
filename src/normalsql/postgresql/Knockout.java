// Copyright 2010-2025 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.postgresql;

import org.antlr.v4.runtime.ParserRuleContext;
import normalsql.grammar.PostgreSQLParser.*;

// TODO Create subclasses for subterm and predicate rules. Specialize valueOf method accordingly.


/**
 * A Knockout references a parse tree containing literals to be considered
 * for replacement by a placeholder question mark '?' by the code generator.
 * ("Knockout" is prepress term for removing ink to make something else
 * visible underneath. I'm open to name suggestions; other than "parameter",
 * which is already used throughout. Maybe "cut-out", "hole", "cantalope"...)
 *
 * Subclasses have any additional context needed to code generate
 * corresponding accessors.
 *
 * @author Jason Osgood
 *
 * @param <T> concrete context for a rule
 * @param <E> specific enum for this context's matching patterns
 */

public abstract class Knockout<T extends ParserRuleContext, E extends Enum<E>>
{
	public T context;
	public ParserRuleContext parent;

	public Knockout( T context )
	{
		this.context = context;
		parent = (ParserRuleContext) context.parent;
	}

	public E pattern;

	/**
	 * Checks if the pattern has been set.
	 *
	 * @return is pattern matched
	 */
	public boolean isMatched() {
		return pattern != null;
	}

	/**
	 * Matches and returns an enum constant from an array of Enums.
	 *
	 * @param <E> added this just to fix javadoc error, ignore it
	 *
	 * @param patterns The class object representing the Enum to search.
	 * @param contexts Array of rule contexts
	 *
	 * @return the enum constant that matches the name built from the context array, or null if none matches.
	 */
	public static <E extends Enum<E>> E valueOf( Class<E> patterns, TermContext... contexts )
	{
		var name = new StringBuilder();
		for( var c : contexts )
		{
			var simple = c.getClass().getSimpleName()
				// TODO remove Subterm after refactoring NormalSQL.g4
				.replaceFirst( "^Subterm", "" )
				.replaceFirst( "^Term", "" )
				.replaceFirst( "Context$", "" );
			name.append( simple );
		}

		E[] boo = patterns.getEnumConstants();
		for( E zip : boo )
		{
			if( zip.name().contentEquals( name )) return zip;
		}

		return null;
	}
}
