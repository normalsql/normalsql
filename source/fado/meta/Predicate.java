package fado.meta;

import fado.parse.GenericSQLParser.PredicateContext;
import fado.parse.GenericSQLParser.SubtermColumnRefContext;
import fado.parse.GenericSQLParser.SubtermContext;
import fado.parse.GenericSQLParser.SubtermValueContext;

public abstract class Predicate<T extends PredicateContext>
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
