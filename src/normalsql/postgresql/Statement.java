// Copyright 2010-2024 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.postgresql;

import java.util.ArrayList;

public class
	Statement
extends
	ArrayList<Statement>
{
	// TODO handle duplicate item names, eg replace List with LinkedHashMap, add suffixes to names
	public ArrayList<Item> items = new ArrayList<>();
//	public ArrayList<Source> sources = new ArrayList<>();
	public ArrayList<Knockout<?,?>> knockouts = new ArrayList<>();
}
