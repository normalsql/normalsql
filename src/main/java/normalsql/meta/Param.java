// Copyright 2010-2022 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.meta;

/**
 * PreparedStatement parameter.
 */
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
}