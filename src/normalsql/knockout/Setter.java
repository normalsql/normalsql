// Copyright 2010-2025 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.knockout;

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

	public Setter( GlobbingRuleContext context )
	{
		super( context );
		qname = context.findFirst( "qname" );
		literal = context.findFirst( "literal" );
		pattern = valueOf( Pattern.class, literal );
	}
}
