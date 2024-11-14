// Copyright 2010-2024 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.parse;

import normalsql.parse.NormalSQLParser.NameContext;
import normalsql.parse.NormalSQLParser.QnameContext;
//import normalsql.parse.NormalSQLParser.LiteralContext;

import java.util.List;

public class
	Insert
extends
	Statement
{
	public QnameContext table;
	public List<NameContext> columns;
}
