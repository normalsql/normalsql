package fado;

import fado.parse.GenericSQLParser.*;

/**
 * Using a Factory in anticipation of a Strategy.
 */
// TODO merge AccessorFactory and JavaHelper
public class AccessorFactory
{
	public Property create( SubtermContext context, String... method )
	{
		Property prop = new Property();
		prop.context = ((SubtermValueContext) context).value();
		// TODO fix this to include all tokens, incl. whitespace
		prop.source = prop.context.getText();
		prop.trimmed = prop.context.getTrimmedText();
		prop.variable = toVariableCase( method );
		prop.getter = "get" + toMethodCase( method );
		prop.setter = "set" + toMethodCase( method );
		return prop;
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
