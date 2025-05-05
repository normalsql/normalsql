// Copyright 2010-2025 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.sqlite;


import normalsql.grammar.SQLiteParser.QnameContext;

import java.util.List;

public class
	Insert
extends
        Statement
{
	public QnameContext table;
//	public List<QnameContext>        columns;
	public List<GlobbingRuleContext>        columns;
}
