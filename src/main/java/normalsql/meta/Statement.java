// Copyright 2010-2022 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.meta;

import java.util.ArrayList;

public class
	Statement
extends
	ArrayList<Statement>
{
	// TODO handle duplicate item names, eg replace List with LinkedHashMap, add suffixes to names
	public ArrayList<Item> items = new ArrayList<>();
	public ArrayList<Source> sources = new ArrayList<>();
	// TODO: Trying "Placeholder" name
	public ArrayList<Predicate> placeholders = new ArrayList<>();
}
