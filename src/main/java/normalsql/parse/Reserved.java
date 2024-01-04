package normalsql.parse;

import static normalsql.parse.NormalSQLParser.*;
import org.antlr.v4.runtime.CommonToken;

import java.util.Set;

public
class Reserved
{
	// TODO move to helper class, rename to "morphKeywords()" or something
	static public void fixID( NormalSQLParser p )
	{
		var t1 = (CommonToken) p.getCurrentToken();
		if( t1.getType() == ID ) return;

		var text = t1.getText().toUpperCase();
		if( Reserved.isID( text ) )
		{
			if( Reserved.keywords.contains( text ) )
			{
				t1.setType( RESERVED );
			}
		}
	}

	// Rolled my own tester. Mostly because I couldn't quickly fathom
	// Character.isAlphabetic(...) and the like. But also because
	// this is just needs to distinguish identifiers (ID, STRING, etc) from
	// everything else (mostly meaning punctuation).

	public static boolean isID(String text )
	{
		var c = text.charAt( 0 );
		switch( c )
		{
			case '\'':
			case '"':
			case '[':
			case '`':
				return true;

			case '\\':
			case ']':
			case '^':

			case '{':
			case '|':
			case '}':
			case '~':
			case 0x7F: // ASCII 'DEL' character
				return false;

			default:
				if( c < 'A' ) return false;
		}

		return true;
	}

	// Just ~100 SQL reserved keywords. Versus the 100s of all keywords.
	// These are the keywords which must be quoted to be used as either
	// table or column names.
	//
	// Pulling this list out of the grammar eases maintenance.
	//
	// Generally, because of the current limitations of ANTLR v4's NOT '~' operator,
	// SQL grammars hard-code the list of allowable (unreserved)
	// keywords. Meaning [all-tokens] - [reserved-words] = [unreserved-keywords].
	// Maintenance hell. There are many more allowable keywords, so the rule for
	// unreserved words becomes huge. That strategy is brittle (error-prone)
	// as the grammar is updated to accommodate language changes.
	//
	// Also, the list of reserved keywords is now
	// dynamic (changeable at runtime). This should make it easier to support
	// multiple SQL standards (SQL-92 thru SQL:2016) and dialects (SQLite, T-SQL, etc)
	// without changing the hard-coded grammar.
	//
	// If this explanation fails, please help me fix it. Thanks. -- Jason

	public static Set<String> keywords = Set.of
    (
		"ALL",
		"AND",
		"ANY",
		"ARRAY",
		"AS",
		"ASYMMETRIC",
		"AUTHORIZATION",
		"BETWEEN",
		"BOTH",
		"CASE",
		"CAST",
		"CHECK",
		"CONSTRAINT",
		"CROSS",
		"CURRENT_CATALOG",
		"CURRENT_DATE",
		"CURRENT_PATH",
		"CURRENT_ROLE",
		"CURRENT_SCHEMA",
		"CURRENT_TIME",
		"CURRENT_TIMESTAMP",
		"CURRENT_USER",
		"DAY",
		"DEFAULT",
		"DISTINCT",
		"ELSE",
		"END",
		"EXCEPT",
		"EXISTS",
		"FALSE",
		"FETCH",
		"FOR",
		"FOREIGN",
		"FROM",
		"FULL",
		"GROUP",
		"GROUPS",
		"HAVING",
		"HOUR",
		"IF",
		"ILIKE",
		"IN",
		"INNER",
		"INTERSECT",
		"INTERVAL",
		"IS",
		"JOIN",
		"KEY",
		"LEADING",
		"LEFT",
		"LIKE",
		"LIMIT",
		"LOCALTIME",
		"LOCALTIMESTAMP",
		"MINUS",
		"MINUTE",
		"MONTH",
		"NATURAL",
		"NOT",
		"NULL",
		"OFFSET",
		"ON",
		"OR",
		"ORDER",
		"OVER",
		"PARTITION",
		"PRIMARY",
		"QUALIFY",
		"RANGE",
		"REGEXP",
		"RIGHT",
		"ROW",
		"ROWNUM",
		"ROWS",
		"SECOND",
		"SELECT",
		"SESSION_USER",
		"SET",
		"SOME",
		"SYMMETRIC",
		"SYSTEM_USER",
		"TABLE",
		"TO",
		"TOP",
		"TRAILING",
		"TRUE",
		"UESCAPE",
		"UNION",
		"UNIQUE",
		"UNKNOWN",
		"USER",
		"USING",
		"VALUE",
		"VALUES",
		"WHEN",
		"WHERE",
		"WINDOW",
		"WITH",
		"YEAR",
		"_ROWID_"
	);

}
