package fado.meta;

import fado.parse.GenericSQLParser.SubtermContext;
import fado.parse.GenericSQLParser.SubtermValueContext;
import fado.parse.GenericSQLParser.SubtermColumnRefContext;
import fado.parse.GenericSQLParser.PredicateContext;

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
