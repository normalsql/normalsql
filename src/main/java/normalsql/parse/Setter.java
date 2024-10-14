// Copyright 2010-2024 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.parse;

import normalsql.parse.NormalSQLParser.SubtermContext;
import normalsql.parse.NormalSQLParser.SetterContext;
import normalsql.parse.NormalSQLParser.QnameContext;

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



//	public SubtermContext column;

	public QnameContext qname;
	public SubtermContext literal;

	public Setter( SetterContext context )
	{
		super( context );
		// TODO change to qnames()
		qname = context.qname();
		literal = context.term().subterm();
//		column = context.subterm().get( 0 );
//		literal = context.subterm().get( 1 );
		pattern = valueOf( Pattern.class, literal );
	}
}
