package normalsql.meta;

import java.util.ArrayList;

public class Statement extends ArrayList<Statement>
{
	public ArrayList<Item> items = new ArrayList<>();
	public ArrayList<Source> sources = new ArrayList<>();
	public ArrayList<Predicate> predicates = new ArrayList<>();
}
