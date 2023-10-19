// Copyright 2010-2023 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.parse;

import normalsql.parse.NormalSQLParser.SubtermRowContext;

import java.util.ArrayList;
import java.util.List;

public class Source
{
	public TableRef tableRef;
	public String alias;
	public List<SubtermRowContext> rows = new ArrayList<>();
}
