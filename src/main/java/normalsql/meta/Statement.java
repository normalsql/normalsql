// Copyright 2010-2022 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.meta;

import java.util.ArrayList;

/**
 * Statements are lists containing child Statements. Simplifies recursion.
 */
public class
	Statement
extends
	ArrayList<Statement>
{
	// TODO handle duplicate item names, eg replace List with LinkedHashMap, add suffixes to names
	public ArrayList<Item> items = new ArrayList<>();
//	public ArrayList<Source> sources = new ArrayList<>();
	public ArrayList<Predicate> predicates = new ArrayList<>();
}
