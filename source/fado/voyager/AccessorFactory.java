package fado.voyager;

import fado.parse.GenericSQLParser.*;

/**
 * Using a Factory in anticipation of a Strategy.
 */
// TODO merge AccessorFactory and JavaHelper
public class AccessorFactory
{
	public Accessor create( SubtermContext context, String... method )
	{
		Accessor a = new Accessor();
		a.context = ((SubtermValueContext) context).value();
		// TODO fix this to include all tokens, incl. whitespace
		a.source = a.context.getText();
		a.trimmed = a.context.getTrimmedText();
		a.variable = toVariableCase( method );
		a.getter = "get" + toMethodCase( method );
		a.setter = "set" + toMethodCase( method );
		return a;
	}

	// TODO remove invalid chars (eg spaces), toUpper, toLower, toCapitals, recognize abbreviations (eg "ID"), to snake_case
	// TODO refactor to Strategy pattern, to support other languages, idioms
	public static String toMethodCase( String... name )
	{
		return String.join( "", name );
	}

	public static String toVariableCase( String... name )
	{
		return String.join( "_", name );
	}

}
