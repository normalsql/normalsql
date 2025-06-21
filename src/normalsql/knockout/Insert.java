// Copyright 2010-2025 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.knockout;


import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Collection;
import java.util.List;

public class
	Insert
extends
        Statement
{
	public ParseTree       table;
	public List<ParseTree> columns;
}
