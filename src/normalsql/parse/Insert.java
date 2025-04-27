// Copyright 2010-2025 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.parse;

import normalsql.grammar.NormalSQLParser.NameContext;
import normalsql.grammar.NormalSQLParser.QnameContext;
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
