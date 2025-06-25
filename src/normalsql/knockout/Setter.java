// Copyright 2010-2025 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.knockout;

import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

/**
	Represents an UPDATE's SET.
 */
public class
	Setter
extends
	Knockout<Setter.Pattern>
{
	public enum Pattern
	{
		Literal
	}

	public ParseTree qname;
	public ParseTree literal;

	// TODO wire up literal and qname
	public Setter( RuleContext context )
	{
		super( context );
		pattern = valueOf( Pattern.class, literal );
	}
}
