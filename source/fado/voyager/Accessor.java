package fado.voyager;

public class Accessor
{
	// hold onto parents to ease debugging
	public Predicate predicate;
	public Param param;

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
			"nth='" + nth + '\'' +
			", variable='" + variable + '\'' +
			", value='" + value + '\'' +
			", getter='" + getter + '\'' +
			", setter='" + setter + '\'' +
			", clazz='" + clazz + '\'' +
			'}';
	}
}
