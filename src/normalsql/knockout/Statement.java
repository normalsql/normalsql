// Copyright 2010-2025 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.knockout;

import java.util.ArrayList;
import java.util.List;

public class
	Statement
extends
	ArrayList<Statement>
{
	// TODO handle duplicate item names, eg replace List with LinkedHashMap, add suffixes to names
	public List<Item>   items     = new ArrayList<>();
//	public ArrayList<Source> sources = new ArrayList<>();
	public List<Knockout<?>> knockouts = new ArrayList<>();
}
