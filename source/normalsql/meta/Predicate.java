// Copyright 2010-2022 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.meta;

import normalsql.parse.NormalSQLParser.PredicateContext;
import normalsql.parse.NormalSQLParser.SubtermColumnRefContext;
import normalsql.parse.NormalSQLParser.SubtermContext;
import normalsql.parse.NormalSQLParser.SubtermValueContext;

public abstract class
	Predicate<T extends PredicateContext>
{
	public final static Class COL = SubtermColumnRefContext.class;
	public final static Class VAL = SubtermValueContext.class;

	public T context;
	public SubtermContext parent;

	public Predicate( T context )
	{
		this.context = context;
		parent = (SubtermContext) context.parent;
	}

	abstract public boolean isMatched();
}
