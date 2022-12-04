package fado.voyager;

public class Param
{
	public int nth;
	public int type;
	public String typeName;
	public int isNullable;
	public String className;
	public boolean isSigned;
	public int scaled;
	public int precision;
	public int mode;

	@Override
	public String toString()
	{
		return "Param{" +
			"type=" + type +
			", typeName='" + typeName + '\'' +
			", isNullable=" + isNullable +
			", clazz='" + className + '\'' +
			", isSigned=" + isSigned +
			", scaled=" + scaled +
			", precision=" + precision +
			", mode=" + mode +
			'}';
	}
}