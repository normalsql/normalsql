// Copyright 2010-2023 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.jdbc;

/**
 * POJO for a PreparedStatement Parameter.
 */
public class Param
{
	enum IsNullable
	{
		parameterNoNulls,
		parameterNullable,
		parameterNullableUnknown
	}

	enum Mode
	{
		parameterModeUnknown,
		parameterModeIn,
		parameterModeInOut,
		parameterModeOut
	}

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
	public String toString() {
		return "Param{" +
				"nth=" + nth +
				", type=" + type +
				", typeName='" + typeName + '\'' +
				", isNullable=" + isNullable +
				", isNullable=" + IsNullable.values()[isNullable] +
				", className='" + className + '\'' +
				", isSigned=" + isSigned +
				", scaled=" + scaled +
				", precision=" + precision +
				", mode=" + mode +
				", mode=" + Mode.values()[mode]  +
				'}';
	}
}
