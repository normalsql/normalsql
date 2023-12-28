package normalsql.parse;

import java.util.Set;

public
class Reserved
{
	// SQL reserved words. Must be quoted to be used as either table or column names.
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

	public static boolean isWord( String text )
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
			case 0x7F:

				return false;

			default:
				if( c < 'A' ) return false;
		}

		return true;
	}
}
