package fado.voyager;

import fado.parse.GenericSQLParser.*;

/**
 * Using a Factory in anticipation of a Strategy.
 */
public class AccessorFactory
{
	public Accessor create( SubtermContext context, String... method )
	{
		Accessor a = new Accessor();
//		a.predicate = predicate;
		a.context = ((SubtermValueContext) context).value();
		a.value = a.context.getTrimmedText();
		a.variable = toVariableCase( method );
		a.getter = "get" + toMethodCase( method );
		a.setter = "set" + toMethodCase( method );;
		return a;
	}

	// TODO remove invalid chars (eg spaces), toUpper, toLower, toCapitals, recognize abbreviations (eg "ID"), to snake_case
	// TODO refactor to Strategy pattern, to support other languages, idioms
	public String toMethodCase( String[] name )
	{
		return String.join( "", name );
	}

	public String toVariableCase( String[] name )
	{
		return String.join( "_", name );
	}

}
