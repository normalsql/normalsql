package fado;

import fado.meta.Param;
import fado.meta.Column;
import fado.parse.GenericSQLParser.ValueContext;
import fado.template.JavaHelper;

/**
 * Property represents a class instance variable for code generation. aka JavaBean.
 * One Property for each PreparedStatement Param.
 *
 */

// TODO Maybe split into Statement and ResultSet specific subclasses.

public class Property
{
	Column column;
	Param param;
	ValueContext context;

	public int nth;
	// Name of local variable instance
	public String variable;
	// Copy of clause from source code
	public String source;
	// Name (identifier) stripped of punctuation
	public String trimmed;
	// Generated getter method
	public String getter;
	// Generated setter method
	public String setter;
	// Full class name scrapped from JDBC metadata
	public String className;
	// Shortened class name for us by templates
	public String classShortName;
	// SQL data type, scrapped from JDBC metadata
	public int sqlType;

	// Used by PreparedStatement templates
	public String getAsCode() {
		return JavaHelper.convertToCode( param.type, trimmed );
	};
	// Used by ResultSet templates. To specify variable default initial value.
	public String getInitial() {
		return JavaHelper.getInitializerValue( sqlType );
	};

	@Override
	public String toString()
	{
		return "Property{" +
			"column=" + column +
			", param=" + param +
			", context=" + context +
			", nth=" + nth +
			", variable='" + variable + '\'' +
			", source='" + source + '\'' +
			", trimmed='" + trimmed + '\'' +
			", getter='" + getter + '\'' +
			", setter='" + setter + '\'' +
			", className='" + className + '\'' +
			", classShortName='" + classShortName + '\'' +
			", sqlType=" + sqlType +
			'}';
	}
}
