// Copyright 2010-2025 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.sqlite;

import normalsql.grammar.PostgreSQLParser.QnameContext;
import normalsql.grammar.PostgreSQLParser.SetterContext;
import normalsql.grammar.PostgreSQLParser.TermContext;

/**
	Represents an UPDATE's SET.
 */
public class
	Setter
extends
        Knockout<SetterContext, Setter.Pattern>
{
	public enum Pattern
	{
		Literal
	}

	public QnameContext qname;
	public TermContext literal;

	public Setter( SetterContext context )
	{
		super( context );
		qname = context.qname();
		literal = context.term();
		pattern = valueOf( Pattern.class, literal );
	}
}
