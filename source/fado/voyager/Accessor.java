package fado.voyager;

import fado.parse.GenericSQLParser.ValueContext;
import fado.template.JavaHelper;

public class Accessor
{
	RSColumn column;
	Param param;
	ValueContext context;

	public int nth;
	public String variable;
	public String source;
	public String trimmed;
	public String getter;
	public String setter;
	public String className;
	public String classShortName;
	public int sqlType;

	public String getAsCode() {
		return JavaHelper.convertToCode( param.type, trimmed );
	};
	public String getInitial() {
		return JavaHelper.getInitializerValue( sqlType );
	};


	@Override
	public String toString()
	{
		return "Accessor{" +
			"context='" + context + '\'' +
			"nth='" + nth + '\'' +
			", variable='" + variable + '\'' +
			", source='" + source + '\'' +
			", getter='" + getter + '\'' +
			", setter='" + setter + '\'' +
			", className='" + className + '\'' +
			'}';
	}
}
