package fado.voyager;

public class Param
{
	// TODO: nth might be redundant
	public int nth;
	public int type;
	public String typeName;
	public int isNullable;
	public String clazz;
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
			", clazz='" + clazz + '\'' +
			", isSigned=" + isSigned +
			", scaled=" + scaled +
			", precision=" + precision +
			", mode=" + mode +
			'}';
	}
}