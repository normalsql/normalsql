package fado.voyager;

public class AccessorFactory
{
	public Accessor create( Predicate predicate, String value, String... name )
	{
		Accessor a = new Accessor();
		a.predicate = predicate;
		a.variable = toVariableCase( name );
		a.value = value;
		a.getter = "get" + toMethodCase( name  );
		a.setter = "set" + toMethodCase( name  );;
		return a;
	}

	// TODO remove invalid chars (eg spaces), toUpper, toLower, toCapitals, recognize abbreviations (eg "ID"), to snake_case
	public String toMethodCase( String[] name )
	{
		return String.join( "", name );
	}

	public String toVariableCase( String[] name )
	{
		return String.join( "_", name );
	}

}
