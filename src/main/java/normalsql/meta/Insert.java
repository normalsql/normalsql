// Copyright 2010-2022 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.meta;

import normalsql.parse.NormalSQLParser.NameContext;
import normalsql.parse.NormalSQLParser.QnameContext;
import normalsql.parse.NormalSQLParser.LiteralContext;

import java.util.List;

public class
	Insert
extends
	Statement
{
	public QnameContext table;
	public List<NameContext> columns;
	public List<LiteralContext> literals;
}
