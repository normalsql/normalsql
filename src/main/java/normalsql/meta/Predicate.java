// Copyright 2010-2022 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.meta;

import normalsql.parse.NormalSQLParser.PredicateContext;
import normalsql.parse.NormalSQLParser.SubtermRefContext;
import normalsql.parse.NormalSQLParser.SubtermContext;
import normalsql.parse.NormalSQLParser.SubtermValueContext;

/**
 * <p>Abstract Predicate class.</p>
 *
 * @param <T> class extends PredicateContext
 * @author jasonosgood
 * @version $Id: $Id
 */
public abstract class
	Predicate<T extends PredicateContext>
{
	/** Constant <code>COL</code> */
	public final static Class COL = SubtermRefContext.class;
	/** Constant <code>VAL</code> */
	public final static Class VAL = SubtermValueContext.class;

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

	/**
	 * <p>isMatched.</p>
	 *
	 * @return a boolean
	 */
	abstract public boolean isMatched();
}
