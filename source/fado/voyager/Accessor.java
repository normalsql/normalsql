package fado.voyager;

import fado.parse.GenericSQLParser.ValueContext;
import fado.template.JavaHelper;

public class Accessor
{
	Param param;
	ValueContext context;

	public int nth;
	public String variable;
	public String text;
	public String trimmed;
	public String getter;
	public String setter;
	public String clazz;

	public String getAsCode() {
		return JavaHelper.convertToCode( param.type, trimmed );
	};

	public String getInitial() {
		return JavaHelper.getInitializerValue( param.type );
	};

	@Override
	public String toString()
	{
		return "Accessor{" +
			"context='" + context + '\'' +
			"nth='" + nth + '\'' +
			", variable='" + variable + '\'' +
			", value='" + text + '\'' +
			", getter='" + getter + '\'' +
			", setter='" + setter + '\'' +
			", clazz='" + clazz + '\'' +
			'}';
	}
}
