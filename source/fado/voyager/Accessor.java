package fado.voyager;

import fado.parse.GenericSQLParser.ValueContext;

public class Accessor
{
	Param param;
	ValueContext context;

	public int nth;
	public String variable;
	public String value;
	public String getter;
	public String setter;
	public String clazz;

	@Override
	public String toString()
	{
		return "Accessor{" +
			"context='" + context + '\'' +
			"nth='" + nth + '\'' +
			", variable='" + variable + '\'' +
			", value='" + value + '\'' +
			", getter='" + getter + '\'' +
			", setter='" + setter + '\'' +
			", clazz='" + clazz + '\'' +
			'}';
	}
}
