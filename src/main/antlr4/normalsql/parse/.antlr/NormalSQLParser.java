// Generated from /Users/jasonosgood/Projects/normalsql/src/main/antlr4/normalsql/parse/NormalSQL.g4 by ANTLR 4.13.1
package hello;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class NormalSQLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, T__44=45, 
		T__45=46, T__46=47, T__47=48, T__48=49, T__49=50, T__50=51, T__51=52, 
		T__52=53, T__53=54, T__54=55, T__55=56, T__56=57, T__57=58, T__58=59, 
		T__59=60, T__60=61, T__61=62, T__62=63, T__63=64, T__64=65, T__65=66, 
		T__66=67, T__67=68, T__68=69, T__69=70, T__70=71, T__71=72, T__72=73, 
		T__73=74, T__74=75, T__75=76, T__76=77, T__77=78, T__78=79, T__79=80, 
		T__80=81, T__81=82, T__82=83, T__83=84, T__84=85, T__85=86, T__86=87, 
		T__87=88, T__88=89, T__89=90, T__90=91, T__91=92, T__92=93, T__93=94, 
		T__94=95, T__95=96, T__96=97, T__97=98, T__98=99, T__99=100, T__100=101, 
		T__101=102, T__102=103, T__103=104, T__104=105, T__105=106, T__106=107, 
		T__107=108, T__108=109, T__109=110, T__110=111, T__111=112, T__112=113, 
		T__113=114, T__114=115, T__115=116, T__116=117, T__117=118, T__118=119, 
		T__119=120, T__120=121, T__121=122, T__122=123, T__123=124, T__124=125, 
		T__125=126, T__126=127, T__127=128, T__128=129, T__129=130, T__130=131, 
		T__131=132, T__132=133, T__133=134, T__134=135, T__135=136, T__136=137, 
		T__137=138, T__138=139, T__139=140, T__140=141, T__141=142, T__142=143, 
		T__143=144, T__144=145, T__145=146, T__146=147, T__147=148, T__148=149, 
		T__149=150, T__150=151, T__151=152, T__152=153, T__153=154, T__154=155, 
		T__155=156, T__156=157, T__157=158, T__158=159, T__159=160, T__160=161, 
		T__161=162, T__162=163, T__163=164, T__164=165, T__165=166, T__166=167, 
		T__167=168, T__168=169, T__169=170, T__170=171, T__171=172, T__172=173, 
		T__173=174, T__174=175, T__175=176, T__176=177, T__177=178, T__178=179, 
		T__179=180, T__180=181, T__181=182, T__182=183, T__183=184, T__184=185, 
		T__185=186, T__186=187, T__187=188, T__188=189, T__189=190, T__190=191, 
		T__191=192, T__192=193, T__193=194, T__194=195, T__195=196, T__196=197, 
		T__197=198, T__198=199, T__199=200, T__200=201, T__201=202, T__202=203, 
		T__203=204, T__204=205, T__205=206, T__206=207, T__207=208, T__208=209, 
		T__209=210, T__210=211, T__211=212, T__212=213, T__213=214, T__214=215, 
		T__215=216, T__216=217, T__217=218, T__218=219, T__219=220, T__220=221, 
		T__221=222, T__222=223, T__223=224, T__224=225, T__225=226, T__226=227, 
		T__227=228, T__228=229, T__229=230, T__230=231, T__231=232, T__232=233, 
		T__233=234, T__234=235, T__235=236, T__236=237, T__237=238, T__238=239, 
		T__239=240, T__240=241, T__241=242, T__242=243, T__243=244, T__244=245, 
		T__245=246, T__246=247, T__247=248, T__248=249, T__249=250, T__250=251, 
		T__251=252, T__252=253, T__253=254, T__254=255, T__255=256, T__256=257, 
		T__257=258, T__258=259, T__259=260, T__260=261, T__261=262, T__262=263, 
		T__263=264, T__264=265, T__265=266, T__266=267, T__267=268, T__268=269, 
		T__269=270, T__270=271, T__271=272, T__272=273, T__273=274, T__274=275, 
		T__275=276, T__276=277, T__277=278, T__278=279, T__279=280, T__280=281, 
		T__281=282, T__282=283, T__283=284, T__284=285, T__285=286, T__286=287, 
		T__287=288, T__288=289, T__289=290, T__290=291, T__291=292, T__292=293, 
		T__293=294, T__294=295, T__295=296, T__296=297, T__297=298, T__298=299, 
		T__299=300, T__300=301, T__301=302, T__302=303, T__303=304, T__304=305, 
		T__305=306, T__306=307, T__307=308, T__308=309, T__309=310, T__310=311, 
		T__311=312, T__312=313, T__313=314, T__314=315, T__315=316, T__316=317, 
		T__317=318, T__318=319, T__319=320, T__320=321, T__321=322, T__322=323, 
		T__323=324, T__324=325, T__325=326, T__326=327, T__327=328, T__328=329, 
		T__329=330, T__330=331, T__331=332, T__332=333, T__333=334, T__334=335, 
		T__335=336, T__336=337, T__337=338, T__338=339, T__339=340, T__340=341, 
		T__341=342, T__342=343, T__343=344, T__344=345, T__345=346, T__346=347, 
		T__347=348, T__348=349, T__349=350, T__350=351, T__351=352, T__352=353, 
		T__353=354, T__354=355, T__355=356, T__356=357, T__357=358, T__358=359, 
		T__359=360, T__360=361, T__361=362, T__362=363, T__363=364, T__364=365, 
		T__365=366, T__366=367, T__367=368, T__368=369, T__369=370, T__370=371, 
		T__371=372, T__372=373, T__373=374, T__374=375, T__375=376, T__376=377, 
		T__377=378, T__378=379, T__379=380, T__380=381, T__381=382, T__382=383, 
		T__383=384, T__384=385, T__385=386, T__386=387, T__387=388, T__388=389, 
		T__389=390, T__390=391, T__391=392, T__392=393, T__393=394, T__394=395, 
		T__395=396, T__396=397, T__397=398, T__398=399, T__399=400, T__400=401, 
		T__401=402, UNICODE_NAME=403, UNICODE_STRING=404, NATIONAL_STRING=405, 
		STRING=406, ID=407, BRACKETS=408, DOLLARS=409, BITS=410, BYTES=411, OCTALS=412, 
		INTEGER=413, FLOAT=414, PARAMETER=415, VARIABLE=416, COMMENT=417, BLOCK_COMMENT=418, 
		WHITESPACE=419, OPERATOR=420;
	public static final int
		RULE_script = 0, RULE_statement = 1, RULE_explain = 2, RULE_option = 3, 
		RULE_alter = 4, RULE_set = 5, RULE_drop = 6, RULE_dropsicle = 7, RULE_createTable = 8, 
		RULE_createVirtualTable = 9, RULE_moduleArgument = 10, RULE_createTrigger = 11, 
		RULE_temporary = 12, RULE_createView = 13, RULE_createIndex = 14, RULE_indexedColumn = 15, 
		RULE_indexedColumns = 16, RULE_ifNotExists = 17, RULE_columnDef = 18, 
		RULE_columnStuff = 19, RULE_tableStuff = 20, RULE_onConflict = 21, RULE_foreignKey = 22, 
		RULE_with = 23, RULE_cte = 24, RULE_delete = 25, RULE_insert = 26, RULE_overriding = 27, 
		RULE_upsert = 28, RULE_merge = 29, RULE_when = 30, RULE_update = 31, RULE_setter = 32, 
		RULE_afirr = 33, RULE_indexedBy = 34, RULE_returning = 35, RULE_returned = 36, 
		RULE_select = 37, RULE_selectCore = 38, RULE_unions = 39, RULE_sources = 40, 
		RULE_source = 41, RULE_unnest = 42, RULE_tableFunc = 43, RULE_tableFuncParam = 44, 
		RULE_offset = 45, RULE_limit = 46, RULE_fetch = 47, RULE_forUpdate = 48, 
		RULE_quantifier = 49, RULE_top = 50, RULE_item = 51, RULE_into = 52, RULE_join = 53, 
		RULE_pivot = 54, RULE_aliasedFunction = 55, RULE_unpivot = 56, RULE_where = 57, 
		RULE_groupBy = 58, RULE_groupByItem = 59, RULE_having = 60, RULE_windows = 61, 
		RULE_windowAlias = 62, RULE_qualify = 63, RULE_xmlTable = 64, RULE_passing = 65, 
		RULE_xmlColumn = 66, RULE_terms = 67, RULE_aliasedTerm = 68, RULE_aliasedTerms = 69, 
		RULE_term = 70, RULE_assign = 71, RULE_subterm = 72, RULE_case = 73, RULE_predicate = 74, 
		RULE_compare = 75, RULE_logicals = 76, RULE_jsonType = 77, RULE_type = 78, 
		RULE_scalar = 79, RULE_chars = 80, RULE_length = 81, RULE_precision = 82, 
		RULE_values = 83, RULE_array = 84, RULE_arrayTerms = 85, RULE_arrayNested = 86, 
		RULE_function = 87, RULE_withinGroup = 88, RULE_filter = 89, RULE_over = 90, 
		RULE_xmlAttrib = 91, RULE_xmlContent = 92, RULE_window = 93, RULE_partitionBy = 94, 
		RULE_windowFrame = 95, RULE_preceding = 96, RULE_following = 97, RULE_orderBy = 98, 
		RULE_orderByItem = 99, RULE_sortDir = 100, RULE_literal = 101, RULE_truth = 102, 
		RULE_boolean = 103, RULE_interval = 104, RULE_timeUnit = 105, RULE_jsonArray = 106, 
		RULE_jsonObject = 107, RULE_jsonPairs = 108, RULE_jsonPair = 109, RULE_jsonKey = 110, 
		RULE_columns = 111, RULE_column = 112, RULE_table = 113, RULE_qname = 114, 
		RULE_index = 115, RULE_unreserved = 116, RULE_allDistinct = 117, RULE_firstLast = 118, 
		RULE_formatJson = 119, RULE_onNull = 120, RULE_respectIgnore = 121, RULE_rowRows = 122, 
		RULE_timeZone = 123, RULE_uniqueKeys = 124, RULE_withTies = 125, RULE_withWithout = 126, 
		RULE_alias = 127, RULE_names = 128, RULE_qnames0 = 129, RULE_qnames = 130, 
		RULE_name = 131, RULE_string = 132, RULE_uescape = 133;
	private static String[] makeRuleNames() {
		return new String[] {
			"script", "statement", "explain", "option", "alter", "set", "drop", "dropsicle", 
			"createTable", "createVirtualTable", "moduleArgument", "createTrigger", 
			"temporary", "createView", "createIndex", "indexedColumn", "indexedColumns", 
			"ifNotExists", "columnDef", "columnStuff", "tableStuff", "onConflict", 
			"foreignKey", "with", "cte", "delete", "insert", "overriding", "upsert", 
			"merge", "when", "update", "setter", "afirr", "indexedBy", "returning", 
			"returned", "select", "selectCore", "unions", "sources", "source", "unnest", 
			"tableFunc", "tableFuncParam", "offset", "limit", "fetch", "forUpdate", 
			"quantifier", "top", "item", "into", "join", "pivot", "aliasedFunction", 
			"unpivot", "where", "groupBy", "groupByItem", "having", "windows", "windowAlias", 
			"qualify", "xmlTable", "passing", "xmlColumn", "terms", "aliasedTerm", 
			"aliasedTerms", "term", "assign", "subterm", "case", "predicate", "compare", 
			"logicals", "jsonType", "type", "scalar", "chars", "length", "precision", 
			"values", "array", "arrayTerms", "arrayNested", "function", "withinGroup", 
			"filter", "over", "xmlAttrib", "xmlContent", "window", "partitionBy", 
			"windowFrame", "preceding", "following", "orderBy", "orderByItem", "sortDir", 
			"literal", "truth", "boolean", "interval", "timeUnit", "jsonArray", "jsonObject", 
			"jsonPairs", "jsonPair", "jsonKey", "columns", "column", "table", "qname", 
			"index", "unreserved", "allDistinct", "firstLast", "formatJson", "onNull", 
			"respectIgnore", "rowRows", "timeZone", "uniqueKeys", "withTies", "withWithout", 
			"alias", "names", "qnames0", "qnames", "name", "string", "uescape"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'ANALYZE'", "'ANALYSE'", "'ATTACH'", "'DATABASE'", "'AS'", 
			"'BEGIN'", "'DEFERRED'", "'EXCLUSIVE'", "'IMMEDIATE'", "'TRANSACTION'", 
			"'COMMIT'", "'DETACH'", "'END'", "'PRAGMA'", "'='", "'('", "')'", "'REINDEX'", 
			"'RELEASE'", "'SAVEPOINT'", "'RESET'", "'ROLLBACK'", "'TO'", "'VACUUM'", 
			"'INTO'", "'EXPLAIN'", "'VERBOSE'", "','", "'QUERY'", "'PLAN'", "'COSTS'", 
			"'SETTINGS'", "'BUFFERS'", "'WAL'", "'TIMING'", "'SUMMARY'", "'FORMAT'", 
			"'TEXT'", "'XML'", "'JSON'", "'YAML'", "'ALTER'", "'TABLE'", "'RENAME'", 
			"'COLUMN'", "'ADD'", "'DROP'", "'SET'", "'LOCAL'", "'GLOBAL'", "'IF'", 
			"'EXISTS'", "'CASCADE'", "'RESTRICT'", "'ACCESS METHOD'", "'COLLATION'", 
			"'CONVERSION'", "'EVENT'", "'TRIGGER'", "'EXTENSION'", "'FOREIGN'", "'DATA'", 
			"'WRAPPER'", "'INDEX'", "'MATERIALIZED'", "'VIEW'", "'PROCEDURAL'", "'LANGUAGE'", 
			"'PUBLICATION'", "'ROLE'", "'SCHEMA'", "'SEQUENCE'", "'SERVER'", "'STATISTICS'", 
			"'SEARCH'", "'CONFIGURATION'", "'DICTIONARY'", "'PARSER'", "'TEMPLATE'", 
			"'CREATE'", "'CACHED'", "'MEMORY'", "'UNLOGGED'", "'WITHOUT'", "'WITH'", 
			"'NO'", "'STRICT'", "'VIRTUAL'", "'USING'", "'BEFORE'", "'AFTER'", "'INSTEAD'", 
			"'OF'", "'DELETE'", "'INSERT'", "'UPDATE'", "'ON'", "'FOR'", "'EACH'", 
			"'ROW'", "'WHEN'", "'TEMP'", "'TEMPORARY'", "'OR'", "'REPLACE'", "'UNIQUE'", 
			"'NOT'", "'CONSTRAINT'", "'PRIMARY'", "'KEY'", "'AUTOINCREMENT'", "'NULL'", 
			"'CHECK'", "'INHERIT'", "'DEFAULT'", "'COLLATE'", "'GENERATED'", "'ALWAYS'", 
			"'STORED'", "'CONFLICT'", "'REFERENCES'", "'ACTION'", "'MATCH'", "'FULL'", 
			"'PARTIAL'", "'SIMPLE'", "'DEFERRABLE'", "'INITIALLY'", "'RECURSIVE'", 
			"'BREADTH'", "'DEPTH'", "'FIRST'", "'BY'", "'CYCLE'", "'FROM'", "'ONLY'", 
			"'VALUES'", "'OVERRIDING'", "'SYSTEM'", "'USER'", "'VALUE'", "'DO'", 
			"'NOTHING'", "'MERGE'", "'MATCHED'", "'AND'", "'THEN'", "'ABORT'", "'FAIL'", 
			"'IGNORE'", "'INDEXED'", "'RETURNING'", "'*'", "'SELECT'", "'INTERSECT'", 
			"'MINUS'", "'UNION'", "'ALL'", "'EXCEPT'", "'MULTISET'", "'USE'", "'NEW'", 
			"'OLD'", "'FINAL'", "'JSON_TABLE'", "'UNNEST'", "'ORDINALITY'", "'TABLE_DISTINCT'", 
			"'OFFSET'", "'LIMIT'", "'FETCH'", "'NEXT'", "'PERCENT'", "'READ'", "'SHARE'", 
			"'NOWAIT'", "'WAIT'", "'SKIP'", "'LOCKED'", "'DISTINCT'", "'TOP'", "'.'", 
			"'NATURAL'", "'LEFT'", "'RIGHT'", "'OUTER'", "'INNER'", "'CROSS'", "'JOIN'", 
			"'PIVOT'", "'IN'", "'ANY'", "'UNPIVOT'", "'INCLUDE'", "'EXCLUDE'", "'NULLS'", 
			"'WHERE'", "'CURRENT'", "'GROUP'", "'ROLLUP'", "'CUBE'", "'GROUPING'", 
			"'SETS'", "'HAVING'", "'WINDOW'", "'QUALIFY'", "'XMLTABLE'", "'COLUMNS'", 
			"'PASSING'", "'REF'", "'PATH'", "'XOR'", "':='", "'+='", "'-='", "'*='", 
			"'/='", "'%='", "'&='", "'^='", "'|='", "'+'", "'-'", "'~'", "'!'", "'::'", 
			"'&'", "'|'", "'||'", "'->'", "'->>'", "'<<'", "'>>'", "'AT'", "'INTERVAL'", 
			"'CAST'", "'TRY_CAST'", "'OVERLAPS'", "'^'", "'/'", "'DIV'", "'%'", "'MOD'", 
			"'CASE'", "'ELSE'", "'ISNULL'", "'NOTNULL'", "'IS'", "'TYPE'", "'BETWEEN'", 
			"'ASYMMETRIC'", "'SYMMETRIC'", "'LIKE'", "'ILIKE'", "'REGEXP'", "'GLOB'", 
			"'ESCAPE'", "'RAISE'", "'=='", "'<>'", "'!='", "'<'", "'<='", "'>'", 
			"'>='", "'&&'", "'~*'", "'!~'", "'!~*'", "'NAN'", "'INFINITE'", "'PRESENT'", 
			"'A'", "'EMPTY'", "'ARRAY'", "'OBJECT'", "'SCALAR'", "'SETOF'", "'['", 
			"']'", "'INT'", "'INTEGER'", "'TINYINT'", "'SMALLINT'", "'BIGINT'", "'REAL'", 
			"'DECFLOAT'", "'FLOAT'", "'DOUBLE'", "'PRECISION'", "'DECIMAL'", "'DEC'", 
			"'NUMBER'", "'NUMERIC'", "'BOOL'", "'BOOLEAN'", "'VARBINARY'", "'BIT'", 
			"'VARYING'", "'BLOB'", "'CLOB'", "'NCLOB'", "'DATE'", "'TIME'", "'TIMESTAMP'", 
			"'UUID'", "'JSONB'", "'CHARACTER'", "'CHAR'", "'NCHAR'", "'VARCHAR'", 
			"'VARCHAR2'", "'NATIONAL'", "'TRIM'", "'BOTH'", "'LEADING'", "'TRAILING'", 
			"'SUBSTRING'", "'JSON_OBJECTAGG'", "'EXTRACT'", "'COLLECT'", "'XMLATTRIBUTES'", 
			"'XMLCONCAT'", "'XMLELEMENT'", "'NAME'", "'XMLEXISTS'", "'XMLFOREST'", 
			"'XMLPARSE'", "'PRESERVE'", "'STRIP'", "'WHITESPACE'", "'XMLPI'", "'XMLROOT'", 
			"'VERSION'", "'STANDALONE'", "'YES'", "'XMLSERIALIZE'", "'{fn'", "'}'", 
			"'OVERFLOW'", "'ERROR'", "'SEPARATOR'", "'CURRENT_DATE'", "'CURRENT_TIME'", 
			"'CURRENT_TIMESTAMP'", "'WITHIN'", "'FILTER'", "'OVER'", "'DOCUMENT'", 
			"'CONTENT'", "'PARTITION'", "'RANGE'", "'ROWS'", "'GROUPS'", "'TIES'", 
			"'OTHERS'", "'UNBOUNDED'", "'CATEGORY'", "'PRECEDING'", "'FOLLOWING'", 
			"'ORDER'", "'ASC'", "'DESC'", "'{d'", "'{t'", "'{ts'", "'TRUE'", "'FALSE'", 
			"'UNKNOWN'", "'OFF'", "'EPOCH'", "'MILLENNIUM'", "'CENTURY'", "'DECADE'", 
			"'YEAR'", "'YEARS'", "'QUARTER'", "'MONTH'", "'MONTHS'", "'WEEK'", "'WEEKS'", 
			"'DAY'", "'DAYS'", "'HOUR'", "'HOURS'", "'MINUTE'", "'MINUTES'", "'SECOND'", 
			"'SECONDS'", "'MILLISECOND'", "'MICROSECOND'", "'NANOSECOND'", "'JSON_ARRAY'", 
			"'JSON_OBJECT'", "':'", "'UESCAPE'", "'LAST'", "'ABSENT'", "'RESPECT'", 
			"'ZONE'", "'KEYS'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, "UNICODE_NAME", "UNICODE_STRING", 
			"NATIONAL_STRING", "STRING", "ID", "BRACKETS", "DOLLARS", "BITS", "BYTES", 
			"OCTALS", "INTEGER", "FLOAT", "PARAMETER", "VARIABLE", "COMMENT", "BLOCK_COMMENT", 
			"WHITESPACE", "OPERATOR"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "NormalSQL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public NormalSQLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ScriptContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(NormalSQLParser.EOF, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public ScriptContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_script; }
	}

	public final ScriptContext script() throws RecognitionException {
		ScriptContext _localctx = new ScriptContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_script);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(269);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 870813393416348L) != 0) || ((((_la - 81)) & ~0x3f) == 0 && ((1L << (_la - 81)) & 144115188109525025L) != 0) || _la==T__144 || _la==T__154) {
				{
				setState(268);
				statement();
				}
			}

			setState(277);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(271);
				match(T__0);
				setState(273);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 870813393416348L) != 0) || ((((_la - 81)) & ~0x3f) == 0 && ((1L << (_la - 81)) & 144115188109525025L) != 0) || _la==T__144 || _la==T__154) {
					{
					setState(272);
					statement();
					}
				}

				}
				}
				setState(279);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(280);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public AlterContext alter() {
			return getRuleContext(AlterContext.class,0);
		}
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public QnameContext qname() {
			return getRuleContext(QnameContext.class,0);
		}
		public CreateIndexContext createIndex() {
			return getRuleContext(CreateIndexContext.class,0);
		}
		public CreateTableContext createTable() {
			return getRuleContext(CreateTableContext.class,0);
		}
		public CreateTriggerContext createTrigger() {
			return getRuleContext(CreateTriggerContext.class,0);
		}
		public CreateViewContext createView() {
			return getRuleContext(CreateViewContext.class,0);
		}
		public CreateVirtualTableContext createVirtualTable() {
			return getRuleContext(CreateVirtualTableContext.class,0);
		}
		public DeleteContext delete() {
			return getRuleContext(DeleteContext.class,0);
		}
		public DropContext drop() {
			return getRuleContext(DropContext.class,0);
		}
		public InsertContext insert() {
			return getRuleContext(InsertContext.class,0);
		}
		public MergeContext merge() {
			return getRuleContext(MergeContext.class,0);
		}
		public SelectContext select() {
			return getRuleContext(SelectContext.class,0);
		}
		public SetContext set() {
			return getRuleContext(SetContext.class,0);
		}
		public UpdateContext update() {
			return getRuleContext(UpdateContext.class,0);
		}
		public ExplainContext explain() {
			return getRuleContext(ExplainContext.class,0);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(283);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__26) {
				{
				setState(282);
				explain();
				}
			}

			setState(378);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				{
				setState(285);
				alter();
				}
				break;
			case 2:
				{
				setState(286);
				_la = _input.LA(1);
				if ( !(_la==T__1 || _la==T__2) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(288);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -4620693218219474948L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & -23672811834834945L) != 0) || ((((_la - 128)) & ~0x3f) == 0 && ((1L << (_la - 128)) & -4656726419123471361L) != 0) || ((((_la - 193)) & ~0x3f) == 0 && ((1L << (_la - 193)) & -1522243062332412065L) != 0) || ((((_la - 257)) & ~0x3f) == 0 && ((1L << (_la - 257)) & -4035225266149656561L) != 0) || ((((_la - 321)) & ~0x3f) == 0 && ((1L << (_la - 321)) & -562958543355905L) != 0) || ((((_la - 385)) & ~0x3f) == 0 && ((1L << (_la - 385)) & 67677712383L) != 0)) {
					{
					setState(287);
					qname();
					}
				}

				}
				break;
			case 3:
				{
				setState(290);
				match(T__3);
				setState(292);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
				case 1:
					{
					setState(291);
					match(T__4);
					}
					break;
				}
				setState(294);
				term(0);
				setState(295);
				match(T__5);
				setState(296);
				qname();
				}
				break;
			case 4:
				{
				setState(298);
				match(T__6);
				setState(300);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1792L) != 0)) {
					{
					setState(299);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1792L) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
				}

				setState(303);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__10) {
					{
					setState(302);
					match(T__10);
					}
				}

				}
				break;
			case 5:
				{
				setState(305);
				match(T__11);
				setState(307);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__10) {
					{
					setState(306);
					match(T__10);
					}
				}

				}
				break;
			case 6:
				{
				setState(309);
				createIndex();
				}
				break;
			case 7:
				{
				setState(310);
				createTable();
				}
				break;
			case 8:
				{
				setState(311);
				createTrigger();
				}
				break;
			case 9:
				{
				setState(312);
				createView();
				}
				break;
			case 10:
				{
				setState(313);
				createVirtualTable();
				}
				break;
			case 11:
				{
				setState(314);
				delete();
				}
				break;
			case 12:
				{
				setState(315);
				match(T__12);
				setState(317);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
				case 1:
					{
					setState(316);
					match(T__4);
					}
					break;
				}
				setState(319);
				term(0);
				}
				break;
			case 13:
				{
				setState(320);
				drop();
				}
				break;
			case 14:
				{
				setState(321);
				match(T__13);
				setState(323);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__10) {
					{
					setState(322);
					match(T__10);
					}
				}

				}
				break;
			case 15:
				{
				setState(325);
				insert();
				}
				break;
			case 16:
				{
				setState(326);
				merge();
				}
				break;
			case 17:
				{
				setState(327);
				match(T__14);
				setState(328);
				qname();
				setState(341);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__15:
					{
					setState(329);
					match(T__15);
					setState(332);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
					case 1:
						{
						setState(330);
						literal();
						}
						break;
					case 2:
						{
						setState(331);
						name();
						}
						break;
					}
					}
					break;
				case T__16:
					{
					setState(334);
					match(T__16);
					setState(337);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
					case 1:
						{
						setState(335);
						literal();
						}
						break;
					case 2:
						{
						setState(336);
						name();
						}
						break;
					}
					setState(339);
					match(T__17);
					}
					break;
				case EOF:
				case T__0:
					break;
				default:
					break;
				}
				}
				break;
			case 18:
				{
				setState(343);
				select(0);
				}
				break;
			case 19:
				{
				setState(344);
				match(T__18);
				setState(346);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -4620693218219474948L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & -23672811834834945L) != 0) || ((((_la - 128)) & ~0x3f) == 0 && ((1L << (_la - 128)) & -4656726419123471361L) != 0) || ((((_la - 193)) & ~0x3f) == 0 && ((1L << (_la - 193)) & -1522243062332412065L) != 0) || ((((_la - 257)) & ~0x3f) == 0 && ((1L << (_la - 257)) & -4035225266149656561L) != 0) || ((((_la - 321)) & ~0x3f) == 0 && ((1L << (_la - 321)) & -562958543355905L) != 0) || ((((_la - 385)) & ~0x3f) == 0 && ((1L << (_la - 385)) & 67677712383L) != 0)) {
					{
					setState(345);
					qname();
					}
				}

				}
				break;
			case 20:
				{
				setState(348);
				match(T__19);
				setState(350);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
				case 1:
					{
					setState(349);
					match(T__20);
					}
					break;
				}
				setState(352);
				qname();
				}
				break;
			case 21:
				{
				setState(353);
				match(T__21);
				setState(354);
				qname();
				}
				break;
			case 22:
				{
				setState(355);
				match(T__22);
				setState(357);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__10) {
					{
					setState(356);
					match(T__10);
					}
				}

				setState(364);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__23) {
					{
					setState(359);
					match(T__23);
					setState(361);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
					case 1:
						{
						setState(360);
						match(T__20);
						}
						break;
					}
					setState(363);
					name();
					}
				}

				}
				break;
			case 23:
				{
				setState(366);
				match(T__20);
				setState(367);
				qname();
				}
				break;
			case 24:
				{
				setState(368);
				set();
				}
				break;
			case 25:
				{
				setState(369);
				update();
				}
				break;
			case 26:
				{
				setState(370);
				match(T__24);
				setState(372);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
				case 1:
					{
					setState(371);
					qname();
					}
					break;
				}
				setState(376);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__25) {
					{
					setState(374);
					match(T__25);
					setState(375);
					term(0);
					}
				}

				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExplainContext extends ParserRuleContext {
		public List<OptionContext> option() {
			return getRuleContexts(OptionContext.class);
		}
		public OptionContext option(int i) {
			return getRuleContext(OptionContext.class,i);
		}
		public ExplainContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_explain; }
	}

	public final ExplainContext explain() throws RecognitionException {
		ExplainContext _localctx = new ExplainContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_explain);
		int _la;
		try {
			setState(403);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(380);
				match(T__26);
				setState(382);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
				case 1:
					{
					setState(381);
					match(T__1);
					}
					break;
				}
				setState(385);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__27) {
					{
					setState(384);
					match(T__27);
					}
				}

				setState(398);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
				case 1:
					{
					setState(387);
					match(T__16);
					setState(388);
					option();
					setState(393);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__28) {
						{
						{
						setState(389);
						match(T__28);
						setState(390);
						option();
						}
						}
						setState(395);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(396);
					match(T__17);
					}
					break;
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(400);
				match(T__26);
				setState(401);
				match(T__29);
				setState(402);
				match(T__30);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OptionContext extends ParserRuleContext {
		public BooleanContext boolean_() {
			return getRuleContext(BooleanContext.class,0);
		}
		public OptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_option; }
	}

	public final OptionContext option() throws RecognitionException {
		OptionContext _localctx = new OptionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_option);
		int _la;
		try {
			setState(439);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
				enterOuterAlt(_localctx, 1);
				{
				setState(405);
				match(T__1);
				setState(407);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__97 || ((((_la - 368)) & ~0x3f) == 0 && ((1L << (_la - 368)) & 11L) != 0)) {
					{
					setState(406);
					boolean_();
					}
				}

				}
				break;
			case T__27:
				enterOuterAlt(_localctx, 2);
				{
				setState(409);
				match(T__27);
				setState(411);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__97 || ((((_la - 368)) & ~0x3f) == 0 && ((1L << (_la - 368)) & 11L) != 0)) {
					{
					setState(410);
					boolean_();
					}
				}

				}
				break;
			case T__31:
				enterOuterAlt(_localctx, 3);
				{
				setState(413);
				match(T__31);
				setState(415);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__97 || ((((_la - 368)) & ~0x3f) == 0 && ((1L << (_la - 368)) & 11L) != 0)) {
					{
					setState(414);
					boolean_();
					}
				}

				}
				break;
			case T__32:
				enterOuterAlt(_localctx, 4);
				{
				setState(417);
				match(T__32);
				setState(419);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__97 || ((((_la - 368)) & ~0x3f) == 0 && ((1L << (_la - 368)) & 11L) != 0)) {
					{
					setState(418);
					boolean_();
					}
				}

				}
				break;
			case T__33:
				enterOuterAlt(_localctx, 5);
				{
				setState(421);
				match(T__33);
				setState(423);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__97 || ((((_la - 368)) & ~0x3f) == 0 && ((1L << (_la - 368)) & 11L) != 0)) {
					{
					setState(422);
					boolean_();
					}
				}

				}
				break;
			case T__34:
				enterOuterAlt(_localctx, 6);
				{
				setState(425);
				match(T__34);
				setState(427);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__97 || ((((_la - 368)) & ~0x3f) == 0 && ((1L << (_la - 368)) & 11L) != 0)) {
					{
					setState(426);
					boolean_();
					}
				}

				}
				break;
			case T__35:
				enterOuterAlt(_localctx, 7);
				{
				setState(429);
				match(T__35);
				setState(431);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__97 || ((((_la - 368)) & ~0x3f) == 0 && ((1L << (_la - 368)) & 11L) != 0)) {
					{
					setState(430);
					boolean_();
					}
				}

				}
				break;
			case T__36:
				enterOuterAlt(_localctx, 8);
				{
				setState(433);
				match(T__36);
				setState(435);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__97 || ((((_la - 368)) & ~0x3f) == 0 && ((1L << (_la - 368)) & 11L) != 0)) {
					{
					setState(434);
					boolean_();
					}
				}

				}
				break;
			case T__37:
				enterOuterAlt(_localctx, 9);
				{
				setState(437);
				match(T__37);
				setState(438);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 8246337208320L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AlterContext extends ParserRuleContext {
		public List<QnameContext> qname() {
			return getRuleContexts(QnameContext.class);
		}
		public QnameContext qname(int i) {
			return getRuleContext(QnameContext.class,i);
		}
		public ColumnDefContext columnDef() {
			return getRuleContext(ColumnDefContext.class,0);
		}
		public AlterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alter; }
	}

	public final AlterContext alter() throws RecognitionException {
		AlterContext _localctx = new AlterContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_alter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(441);
			match(T__42);
			setState(442);
			match(T__43);
			setState(443);
			qname();
			setState(463);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__44:
				{
				setState(444);
				match(T__44);
				setState(449);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
				case 1:
					{
					setState(446);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
					case 1:
						{
						setState(445);
						match(T__45);
						}
						break;
					}
					setState(448);
					qname();
					}
					break;
				}
				setState(451);
				match(T__23);
				setState(452);
				qname();
				}
				break;
			case T__46:
				{
				setState(453);
				match(T__46);
				setState(455);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
				case 1:
					{
					setState(454);
					match(T__45);
					}
					break;
				}
				setState(457);
				columnDef();
				}
				break;
			case T__47:
				{
				setState(458);
				match(T__47);
				setState(460);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
				case 1:
					{
					setState(459);
					match(T__45);
					}
					break;
				}
				setState(462);
				qname();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SetContext extends ParserRuleContext {
		public TermsContext terms() {
			return getRuleContext(TermsContext.class,0);
		}
		public QnameContext qname() {
			return getRuleContext(QnameContext.class,0);
		}
		public TerminalNode VARIABLE() { return getToken(NormalSQLParser.VARIABLE, 0); }
		public SetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_set; }
	}

	public final SetContext set() throws RecognitionException {
		SetContext _localctx = new SetContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_set);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(465);
			match(T__48);
			setState(467);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
			case 1:
				{
				setState(466);
				_la = _input.LA(1);
				if ( !(_la==T__49 || _la==T__50) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			}
			setState(471);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
			case 1:
				{
				setState(469);
				qname();
				}
				break;
			case 2:
				{
				setState(470);
				match(VARIABLE);
				}
				break;
			}
			setState(473);
			_la = _input.LA(1);
			if ( !(_la==T__15 || _la==T__23) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(474);
			terms();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DropContext extends ParserRuleContext {
		public DropsicleContext dropsicle() {
			return getRuleContext(DropsicleContext.class,0);
		}
		public Qnames0Context qnames0() {
			return getRuleContext(Qnames0Context.class,0);
		}
		public DropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_drop; }
	}

	public final DropContext drop() throws RecognitionException {
		DropContext _localctx = new DropContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_drop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(476);
			match(T__47);
			setState(477);
			dropsicle();
			setState(480);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
			case 1:
				{
				setState(478);
				match(T__51);
				setState(479);
				match(T__52);
				}
				break;
			}
			setState(482);
			qnames0();
			setState(484);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__53 || _la==T__54) {
				{
				setState(483);
				_la = _input.LA(1);
				if ( !(_la==T__53 || _la==T__54) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DropsicleContext extends ParserRuleContext {
		public DropsicleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dropsicle; }
	}

	public final DropsicleContext dropsicle() throws RecognitionException {
		DropsicleContext _localctx = new DropsicleContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_dropsicle);
		int _la;
		try {
			setState(516);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(486);
				match(T__55);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(487);
				match(T__56);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(488);
				match(T__57);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(489);
				match(T__58);
				setState(490);
				match(T__59);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(491);
				match(T__60);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(492);
				match(T__61);
				setState(493);
				match(T__62);
				setState(494);
				match(T__63);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(495);
				match(T__61);
				setState(496);
				match(T__43);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(497);
				match(T__64);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(498);
				match(T__65);
				setState(499);
				match(T__66);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(501);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__67) {
					{
					setState(500);
					match(T__67);
					}
				}

				setState(503);
				match(T__68);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(504);
				match(T__69);
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(505);
				match(T__70);
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(506);
				match(T__71);
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(507);
				match(T__72);
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(508);
				match(T__73);
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(509);
				match(T__74);
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(510);
				match(T__43);
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 18);
				{
				setState(511);
				match(T__38);
				setState(512);
				match(T__75);
				setState(513);
				_la = _input.LA(1);
				if ( !(((((_la - 67)) & ~0x3f) == 0 && ((1L << (_la - 67)) & 15361L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 19:
				enterOuterAlt(_localctx, 19);
				{
				setState(514);
				match(T__59);
				}
				break;
			case 20:
				enterOuterAlt(_localctx, 20);
				{
				setState(515);
				match(T__66);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CreateTableContext extends ParserRuleContext {
		public QnameContext qname() {
			return getRuleContext(QnameContext.class,0);
		}
		public List<ColumnDefContext> columnDef() {
			return getRuleContexts(ColumnDefContext.class);
		}
		public ColumnDefContext columnDef(int i) {
			return getRuleContext(ColumnDefContext.class,i);
		}
		public SelectContext select() {
			return getRuleContext(SelectContext.class,0);
		}
		public TemporaryContext temporary() {
			return getRuleContext(TemporaryContext.class,0);
		}
		public IfNotExistsContext ifNotExists() {
			return getRuleContext(IfNotExistsContext.class,0);
		}
		public List<TableStuffContext> tableStuff() {
			return getRuleContexts(TableStuffContext.class);
		}
		public TableStuffContext tableStuff(int i) {
			return getRuleContext(TableStuffContext.class,i);
		}
		public TerminalNode ID() { return getToken(NormalSQLParser.ID, 0); }
		public CreateTableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createTable; }
	}

	public final CreateTableContext createTable() throws RecognitionException {
		CreateTableContext _localctx = new CreateTableContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_createTable);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(518);
			match(T__80);
			setState(520);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__81 || _la==T__82) {
				{
				setState(519);
				_la = _input.LA(1);
				if ( !(_la==T__81 || _la==T__82) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(523);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__49 || _la==T__50) {
				{
				setState(522);
				_la = _input.LA(1);
				if ( !(_la==T__49 || _la==T__50) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(526);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__102 || _la==T__103) {
				{
				setState(525);
				temporary();
				}
			}

			setState(529);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__83) {
				{
				setState(528);
				match(T__83);
				}
			}

			setState(531);
			match(T__43);
			setState(533);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
			case 1:
				{
				setState(532);
				ifNotExists();
				}
				break;
			}
			setState(535);
			qname();
			setState(566);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__16:
				{
				setState(536);
				match(T__16);
				setState(537);
				columnDef();
				setState(542);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,52,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(538);
						match(T__28);
						setState(539);
						columnDef();
						}
						} 
					}
					setState(544);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,52,_ctx);
				}
				setState(549);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__28) {
					{
					{
					setState(545);
					match(T__28);
					setState(546);
					tableStuff();
					}
					}
					setState(551);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(552);
				match(T__17);
				setState(555);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__84) {
					{
					setState(553);
					match(T__84);
					setState(554);
					match(ID);
					}
				}

				}
				break;
			case T__5:
				{
				setState(557);
				match(T__5);
				setState(558);
				select(0);
				setState(564);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__85) {
					{
					setState(559);
					match(T__85);
					setState(561);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__86) {
						{
						setState(560);
						match(T__86);
						}
					}

					setState(563);
					match(T__62);
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(569);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__87) {
				{
				setState(568);
				match(T__87);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CreateVirtualTableContext extends ParserRuleContext {
		public List<QnameContext> qname() {
			return getRuleContexts(QnameContext.class);
		}
		public QnameContext qname(int i) {
			return getRuleContext(QnameContext.class,i);
		}
		public IfNotExistsContext ifNotExists() {
			return getRuleContext(IfNotExistsContext.class,0);
		}
		public List<ModuleArgumentContext> moduleArgument() {
			return getRuleContexts(ModuleArgumentContext.class);
		}
		public ModuleArgumentContext moduleArgument(int i) {
			return getRuleContext(ModuleArgumentContext.class,i);
		}
		public CreateVirtualTableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createVirtualTable; }
	}

	public final CreateVirtualTableContext createVirtualTable() throws RecognitionException {
		CreateVirtualTableContext _localctx = new CreateVirtualTableContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_createVirtualTable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(571);
			match(T__80);
			setState(572);
			match(T__88);
			setState(573);
			match(T__43);
			setState(575);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,59,_ctx) ) {
			case 1:
				{
				setState(574);
				ifNotExists();
				}
				break;
			}
			setState(577);
			qname();
			setState(578);
			match(T__89);
			setState(579);
			qname();
			setState(594);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__16) {
				{
				setState(580);
				match(T__16);
				setState(591);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -4620693218219409412L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & -19169195027595265L) != 0) || ((((_la - 128)) & ~0x3f) == 0 && ((1L << (_la - 128)) & -4656726419123471361L) != 0) || ((((_la - 193)) & ~0x3f) == 0 && ((1L << (_la - 193)) & -1522234266237292705L) != 0) || ((((_la - 257)) & ~0x3f) == 0 && ((1L << (_la - 257)) & -4035225266149654529L) != 0) || ((((_la - 321)) & ~0x3f) == 0 && ((1L << (_la - 321)) & -8589934593L) != 0) || ((((_la - 385)) & ~0x3f) == 0 && ((1L << (_la - 385)) & 68719472639L) != 0)) {
					{
					setState(581);
					moduleArgument();
					setState(588);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__28) {
						{
						{
						setState(582);
						match(T__28);
						setState(584);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -4620693218219409412L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & -19169195027595265L) != 0) || ((((_la - 128)) & ~0x3f) == 0 && ((1L << (_la - 128)) & -4656726419123471361L) != 0) || ((((_la - 193)) & ~0x3f) == 0 && ((1L << (_la - 193)) & -1522234266237292705L) != 0) || ((((_la - 257)) & ~0x3f) == 0 && ((1L << (_la - 257)) & -4035225266149654529L) != 0) || ((((_la - 321)) & ~0x3f) == 0 && ((1L << (_la - 321)) & -8589934593L) != 0) || ((((_la - 385)) & ~0x3f) == 0 && ((1L << (_la - 385)) & 68719472639L) != 0)) {
							{
							setState(583);
							moduleArgument();
							}
						}

						}
						}
						setState(590);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(593);
				match(T__17);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ModuleArgumentContext extends ParserRuleContext {
		public List<NameContext> name() {
			return getRuleContexts(NameContext.class);
		}
		public NameContext name(int i) {
			return getRuleContext(NameContext.class,i);
		}
		public List<LiteralContext> literal() {
			return getRuleContexts(LiteralContext.class);
		}
		public LiteralContext literal(int i) {
			return getRuleContext(LiteralContext.class,i);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public CompareContext compare() {
			return getRuleContext(CompareContext.class,0);
		}
		public AssignContext assign() {
			return getRuleContext(AssignContext.class,0);
		}
		public ModuleArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_moduleArgument; }
	}

	public final ModuleArgumentContext moduleArgument() throws RecognitionException {
		ModuleArgumentContext _localctx = new ModuleArgumentContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_moduleArgument);
		int _la;
		try {
			setState(621);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,71,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(599); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					setState(599);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,64,_ctx) ) {
					case 1:
						{
						setState(596);
						name();
						}
						break;
					case 2:
						{
						setState(597);
						literal();
						}
						break;
					case 3:
						{
						setState(598);
						type(0);
						}
						break;
					}
					}
					setState(601); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & -4620693218219474948L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & -19169195027595265L) != 0) || ((((_la - 128)) & ~0x3f) == 0 && ((1L << (_la - 128)) & -4656726419123471361L) != 0) || ((((_la - 193)) & ~0x3f) == 0 && ((1L << (_la - 193)) & -1522234266239389857L) != 0) || ((((_la - 257)) & ~0x3f) == 0 && ((1L << (_la - 257)) & -4035225266149656561L) != 0) || ((((_la - 321)) & ~0x3f) == 0 && ((1L << (_la - 321)) & -8589934593L) != 0) || ((((_la - 385)) & ~0x3f) == 0 && ((1L << (_la - 385)) & 68719472639L) != 0) );
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(605); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					setState(605);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,66,_ctx) ) {
					case 1:
						{
						setState(603);
						name();
						}
						break;
					case 2:
						{
						setState(604);
						literal();
						}
						break;
					}
					}
					setState(607); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & -4620693218219474948L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & -19169195027595265L) != 0) || ((((_la - 128)) & ~0x3f) == 0 && ((1L << (_la - 128)) & -4656726419123471361L) != 0) || ((((_la - 193)) & ~0x3f) == 0 && ((1L << (_la - 193)) & -1522243062332412065L) != 0) || ((((_la - 257)) & ~0x3f) == 0 && ((1L << (_la - 257)) & -4035225266149656561L) != 0) || ((((_la - 321)) & ~0x3f) == 0 && ((1L << (_la - 321)) & -8589934593L) != 0) || ((((_la - 385)) & ~0x3f) == 0 && ((1L << (_la - 385)) & 68719472639L) != 0) );
				setState(617);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__15) {
					{
					setState(609);
					match(T__15);
					setState(614);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while ((((_la) & ~0x3f) == 0 && ((1L << _la) & -4620693218219474948L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & -19169195027595265L) != 0) || ((((_la - 128)) & ~0x3f) == 0 && ((1L << (_la - 128)) & -4656726419123471361L) != 0) || ((((_la - 193)) & ~0x3f) == 0 && ((1L << (_la - 193)) & -1522243062332412065L) != 0) || ((((_la - 257)) & ~0x3f) == 0 && ((1L << (_la - 257)) & -4035225266149656561L) != 0) || ((((_la - 321)) & ~0x3f) == 0 && ((1L << (_la - 321)) & -8589934593L) != 0) || ((((_la - 385)) & ~0x3f) == 0 && ((1L << (_la - 385)) & 68719472639L) != 0)) {
						{
						setState(612);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,68,_ctx) ) {
						case 1:
							{
							setState(610);
							name();
							}
							break;
						case 2:
							{
							setState(611);
							literal();
							}
							break;
						}
						}
						setState(616);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(619);
				compare();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(620);
				assign();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CreateTriggerContext extends ParserRuleContext {
		public List<QnameContext> qname() {
			return getRuleContexts(QnameContext.class);
		}
		public QnameContext qname(int i) {
			return getRuleContext(QnameContext.class,i);
		}
		public TemporaryContext temporary() {
			return getRuleContext(TemporaryContext.class,0);
		}
		public IfNotExistsContext ifNotExists() {
			return getRuleContext(IfNotExistsContext.class,0);
		}
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public Qnames0Context qnames0() {
			return getRuleContext(Qnames0Context.class,0);
		}
		public List<UpdateContext> update() {
			return getRuleContexts(UpdateContext.class);
		}
		public UpdateContext update(int i) {
			return getRuleContext(UpdateContext.class,i);
		}
		public List<InsertContext> insert() {
			return getRuleContexts(InsertContext.class);
		}
		public InsertContext insert(int i) {
			return getRuleContext(InsertContext.class,i);
		}
		public List<DeleteContext> delete() {
			return getRuleContexts(DeleteContext.class);
		}
		public DeleteContext delete(int i) {
			return getRuleContext(DeleteContext.class,i);
		}
		public List<SelectContext> select() {
			return getRuleContexts(SelectContext.class);
		}
		public SelectContext select(int i) {
			return getRuleContext(SelectContext.class,i);
		}
		public CreateTriggerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createTrigger; }
	}

	public final CreateTriggerContext createTrigger() throws RecognitionException {
		CreateTriggerContext _localctx = new CreateTriggerContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_createTrigger);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(623);
			match(T__80);
			setState(625);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__102 || _la==T__103) {
				{
				setState(624);
				temporary();
				}
			}

			setState(627);
			match(T__59);
			setState(629);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,73,_ctx) ) {
			case 1:
				{
				setState(628);
				ifNotExists();
				}
				break;
			}
			setState(631);
			qname();
			setState(636);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__90:
				{
				setState(632);
				match(T__90);
				}
				break;
			case T__91:
				{
				setState(633);
				match(T__91);
				}
				break;
			case T__92:
				{
				setState(634);
				match(T__92);
				setState(635);
				match(T__93);
				}
				break;
			case T__94:
			case T__95:
			case T__96:
				break;
			default:
				break;
			}
			setState(645);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__94:
				{
				setState(638);
				match(T__94);
				}
				break;
			case T__95:
				{
				setState(639);
				match(T__95);
				}
				break;
			case T__96:
				{
				setState(640);
				match(T__96);
				setState(643);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__93) {
					{
					setState(641);
					match(T__93);
					setState(642);
					qnames0();
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(647);
			match(T__97);
			setState(648);
			qname();
			setState(652);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__98) {
				{
				setState(649);
				match(T__98);
				setState(650);
				match(T__99);
				setState(651);
				match(T__100);
				}
			}

			setState(656);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__101) {
				{
				setState(654);
				match(T__101);
				setState(655);
				term(0);
				}
			}

			setState(658);
			match(T__6);
			setState(667); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(663);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,79,_ctx) ) {
				case 1:
					{
					setState(659);
					update();
					}
					break;
				case 2:
					{
					setState(660);
					insert();
					}
					break;
				case 3:
					{
					setState(661);
					delete();
					}
					break;
				case 4:
					{
					setState(662);
					select(0);
					}
					break;
				}
				setState(665);
				match(T__0);
				}
				}
				setState(669); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__16 || _la==T__43 || ((((_la - 86)) & ~0x3f) == 0 && ((1L << (_la - 86)) & 4503599628422657L) != 0) || _la==T__154 );
			setState(672);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__13) {
				{
				setState(671);
				match(T__13);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TemporaryContext extends ParserRuleContext {
		public TemporaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_temporary; }
	}

	public final TemporaryContext temporary() throws RecognitionException {
		TemporaryContext _localctx = new TemporaryContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_temporary);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(674);
			_la = _input.LA(1);
			if ( !(_la==T__102 || _la==T__103) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CreateViewContext extends ParserRuleContext {
		public QnameContext qname() {
			return getRuleContext(QnameContext.class,0);
		}
		public SelectContext select() {
			return getRuleContext(SelectContext.class,0);
		}
		public TemporaryContext temporary() {
			return getRuleContext(TemporaryContext.class,0);
		}
		public IfNotExistsContext ifNotExists() {
			return getRuleContext(IfNotExistsContext.class,0);
		}
		public ColumnsContext columns() {
			return getRuleContext(ColumnsContext.class,0);
		}
		public CreateViewContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createView; }
	}

	public final CreateViewContext createView() throws RecognitionException {
		CreateViewContext _localctx = new CreateViewContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_createView);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(676);
			match(T__80);
			setState(678);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__102 || _la==T__103) {
				{
				setState(677);
				temporary();
				}
			}

			setState(682);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__104) {
				{
				setState(680);
				match(T__104);
				setState(681);
				match(T__105);
				}
			}

			setState(684);
			match(T__66);
			setState(686);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,84,_ctx) ) {
			case 1:
				{
				setState(685);
				ifNotExists();
				}
				break;
			}
			setState(688);
			qname();
			setState(690);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__16) {
				{
				setState(689);
				columns();
				}
			}

			setState(692);
			match(T__5);
			setState(693);
			select(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CreateIndexContext extends ParserRuleContext {
		public List<QnameContext> qname() {
			return getRuleContexts(QnameContext.class);
		}
		public QnameContext qname(int i) {
			return getRuleContext(QnameContext.class,i);
		}
		public IndexedColumnsContext indexedColumns() {
			return getRuleContext(IndexedColumnsContext.class,0);
		}
		public IfNotExistsContext ifNotExists() {
			return getRuleContext(IfNotExistsContext.class,0);
		}
		public WhereContext where() {
			return getRuleContext(WhereContext.class,0);
		}
		public CreateIndexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createIndex; }
	}

	public final CreateIndexContext createIndex() throws RecognitionException {
		CreateIndexContext _localctx = new CreateIndexContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_createIndex);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(695);
			match(T__80);
			setState(697);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__106) {
				{
				setState(696);
				match(T__106);
				}
			}

			setState(699);
			match(T__64);
			setState(701);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,87,_ctx) ) {
			case 1:
				{
				setState(700);
				ifNotExists();
				}
				break;
			}
			setState(703);
			qname();
			setState(704);
			match(T__97);
			setState(705);
			qname();
			setState(706);
			indexedColumns();
			setState(708);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__197) {
				{
				setState(707);
				where();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IndexedColumnContext extends ParserRuleContext {
		public QnameContext qname() {
			return getRuleContext(QnameContext.class,0);
		}
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public SortDirContext sortDir() {
			return getRuleContext(SortDirContext.class,0);
		}
		public IndexedColumnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexedColumn; }
	}

	public final IndexedColumnContext indexedColumn() throws RecognitionException {
		IndexedColumnContext _localctx = new IndexedColumnContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_indexedColumn);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(712);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,89,_ctx) ) {
			case 1:
				{
				setState(710);
				qname();
				}
				break;
			case 2:
				{
				setState(711);
				term(0);
				}
				break;
			}
			setState(715);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__89 || _la==T__362 || _la==T__363) {
				{
				setState(714);
				sortDir();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IndexedColumnsContext extends ParserRuleContext {
		public List<IndexedColumnContext> indexedColumn() {
			return getRuleContexts(IndexedColumnContext.class);
		}
		public IndexedColumnContext indexedColumn(int i) {
			return getRuleContext(IndexedColumnContext.class,i);
		}
		public IndexedColumnsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexedColumns; }
	}

	public final IndexedColumnsContext indexedColumns() throws RecognitionException {
		IndexedColumnsContext _localctx = new IndexedColumnsContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_indexedColumns);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(717);
			match(T__16);
			setState(718);
			indexedColumn();
			setState(723);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__28) {
				{
				{
				setState(719);
				match(T__28);
				setState(720);
				indexedColumn();
				}
				}
				setState(725);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(726);
			match(T__17);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IfNotExistsContext extends ParserRuleContext {
		public IfNotExistsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifNotExists; }
	}

	public final IfNotExistsContext ifNotExists() throws RecognitionException {
		IfNotExistsContext _localctx = new IfNotExistsContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_ifNotExists);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(728);
			match(T__51);
			setState(729);
			match(T__107);
			setState(730);
			match(T__52);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ColumnDefContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<ColumnStuffContext> columnStuff() {
			return getRuleContexts(ColumnStuffContext.class);
		}
		public ColumnStuffContext columnStuff(int i) {
			return getRuleContext(ColumnStuffContext.class,i);
		}
		public ColumnDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnDef; }
	}

	public final ColumnDefContext columnDef() throws RecognitionException {
		ColumnDefContext _localctx = new ColumnDefContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_columnDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(732);
			name();
			setState(734);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,92,_ctx) ) {
			case 1:
				{
				setState(733);
				type(0);
				}
				break;
			}
			setState(739);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5 || ((((_la - 107)) & ~0x3f) == 0 && ((1L << (_la - 107)) & 36559L) != 0)) {
				{
				{
				setState(736);
				columnStuff();
				}
				}
				setState(741);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ColumnStuffContext extends ParserRuleContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public List<NameContext> name() {
			return getRuleContexts(NameContext.class);
		}
		public NameContext name(int i) {
			return getRuleContext(NameContext.class,i);
		}
		public ForeignKeyContext foreignKey() {
			return getRuleContext(ForeignKeyContext.class,0);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public SortDirContext sortDir() {
			return getRuleContext(SortDirContext.class,0);
		}
		public OnConflictContext onConflict() {
			return getRuleContext(OnConflictContext.class,0);
		}
		public ColumnStuffContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnStuff; }
	}

	public final ColumnStuffContext columnStuff() throws RecognitionException {
		ColumnStuffContext _localctx = new ColumnStuffContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_columnStuff);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(744);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__108) {
				{
				setState(742);
				match(T__108);
				setState(743);
				name();
				}
			}

			setState(795);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__109:
				{
				setState(746);
				match(T__109);
				setState(747);
				match(T__110);
				setState(749);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__89 || _la==T__362 || _la==T__363) {
					{
					setState(748);
					sortDir();
					}
				}

				setState(752);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__97) {
					{
					setState(751);
					onConflict();
					}
				}

				setState(755);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__111) {
					{
					setState(754);
					match(T__111);
					}
				}

				}
				break;
			case T__107:
			case T__112:
				{
				setState(758);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__107) {
					{
					setState(757);
					match(T__107);
					}
				}

				setState(760);
				match(T__112);
				setState(762);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__97) {
					{
					setState(761);
					onConflict();
					}
				}

				}
				break;
			case T__106:
				{
				setState(764);
				match(T__106);
				setState(766);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__97) {
					{
					setState(765);
					onConflict();
					}
				}

				}
				break;
			case T__113:
				{
				setState(768);
				match(T__113);
				setState(769);
				match(T__16);
				setState(770);
				term(0);
				setState(771);
				match(T__17);
				setState(774);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__86) {
					{
					setState(772);
					match(T__86);
					setState(773);
					match(T__114);
					}
				}

				}
				break;
			case T__115:
				{
				setState(776);
				match(T__115);
				setState(779);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,102,_ctx) ) {
				case 1:
					{
					setState(777);
					literal();
					}
					break;
				case 2:
					{
					setState(778);
					term(0);
					}
					break;
				}
				}
				break;
			case T__116:
				{
				setState(781);
				match(T__116);
				setState(782);
				name();
				}
				break;
			case T__121:
				{
				setState(783);
				foreignKey();
				}
				break;
			case T__5:
			case T__117:
				{
				setState(786);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__117) {
					{
					setState(784);
					match(T__117);
					setState(785);
					match(T__118);
					}
				}

				setState(788);
				match(T__5);
				setState(789);
				match(T__16);
				setState(790);
				term(0);
				setState(791);
				match(T__17);
				setState(793);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__88 || _la==T__119) {
					{
					setState(792);
					_la = _input.LA(1);
					if ( !(_la==T__88 || _la==T__119) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(801);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,106,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(797);
					match(T__108);
					setState(798);
					name();
					}
					} 
				}
				setState(803);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,106,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TableStuffContext extends ParserRuleContext {
		public List<NameContext> name() {
			return getRuleContexts(NameContext.class);
		}
		public NameContext name(int i) {
			return getRuleContext(NameContext.class,i);
		}
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public List<IndexedColumnsContext> indexedColumns() {
			return getRuleContexts(IndexedColumnsContext.class);
		}
		public IndexedColumnsContext indexedColumns(int i) {
			return getRuleContext(IndexedColumnsContext.class,i);
		}
		public List<NamesContext> names() {
			return getRuleContexts(NamesContext.class);
		}
		public NamesContext names(int i) {
			return getRuleContext(NamesContext.class,i);
		}
		public List<ForeignKeyContext> foreignKey() {
			return getRuleContexts(ForeignKeyContext.class);
		}
		public ForeignKeyContext foreignKey(int i) {
			return getRuleContext(ForeignKeyContext.class,i);
		}
		public List<OnConflictContext> onConflict() {
			return getRuleContexts(OnConflictContext.class);
		}
		public OnConflictContext onConflict(int i) {
			return getRuleContext(OnConflictContext.class,i);
		}
		public TableStuffContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableStuff; }
	}

	public final TableStuffContext tableStuff() throws RecognitionException {
		TableStuffContext _localctx = new TableStuffContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_tableStuff);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(806);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__108) {
				{
				setState(804);
				match(T__108);
				setState(805);
				name();
				}
			}

			setState(836); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(836);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,111,_ctx) ) {
				case 1:
					{
					setState(808);
					match(T__113);
					setState(809);
					match(T__16);
					setState(810);
					term(0);
					setState(811);
					match(T__17);
					setState(813);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__97) {
						{
						setState(812);
						onConflict();
						}
					}

					}
					break;
				case 2:
					{
					{
					setState(815);
					match(T__61);
					setState(816);
					match(T__110);
					setState(817);
					names();
					setState(818);
					foreignKey();
					}
					}
					break;
				case 3:
					{
					setState(823);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case T__109:
						{
						setState(820);
						match(T__109);
						setState(821);
						match(T__110);
						}
						break;
					case T__106:
						{
						setState(822);
						match(T__106);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(825);
					indexedColumns();
					setState(827);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__97) {
						{
						setState(826);
						onConflict();
						}
					}

					}
					break;
				case 4:
					{
					setState(829);
					match(T__109);
					setState(830);
					match(T__110);
					setState(831);
					match(T__16);
					setState(832);
					name();
					setState(833);
					match(T__111);
					setState(834);
					match(T__17);
					}
					break;
				}
				}
				setState(838); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( ((((_la - 62)) & ~0x3f) == 0 && ((1L << (_la - 62)) & 4820258976169985L) != 0) );
			setState(844);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__108) {
				{
				{
				setState(840);
				match(T__108);
				setState(841);
				name();
				}
				}
				setState(846);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OnConflictContext extends ParserRuleContext {
		public AfirrContext afirr() {
			return getRuleContext(AfirrContext.class,0);
		}
		public OnConflictContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_onConflict; }
	}

	public final OnConflictContext onConflict() throws RecognitionException {
		OnConflictContext _localctx = new OnConflictContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_onConflict);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(847);
			match(T__97);
			setState(848);
			match(T__120);
			setState(849);
			afirr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ForeignKeyContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public NamesContext names() {
			return getRuleContext(NamesContext.class,0);
		}
		public ForeignKeyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_foreignKey; }
	}

	public final ForeignKeyContext foreignKey() throws RecognitionException {
		ForeignKeyContext _localctx = new ForeignKeyContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_foreignKey);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(851);
			match(T__121);
			setState(852);
			name();
			setState(854);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__16) {
				{
				setState(853);
				names();
				}
			}

			setState(870);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__97 || _la==T__123) {
				{
				setState(868);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__97:
					{
					setState(856);
					match(T__97);
					setState(857);
					_la = _input.LA(1);
					if ( !(_la==T__94 || _la==T__96) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(864);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case T__48:
						{
						setState(858);
						match(T__48);
						setState(859);
						_la = _input.LA(1);
						if ( !(_la==T__112 || _la==T__115) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					case T__53:
						{
						setState(860);
						match(T__53);
						}
						break;
					case T__54:
						{
						setState(861);
						match(T__54);
						}
						break;
					case T__86:
						{
						setState(862);
						match(T__86);
						setState(863);
						match(T__122);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					break;
				case T__123:
					{
					setState(866);
					match(T__123);
					setState(867);
					_la = _input.LA(1);
					if ( !(((((_la - 125)) & ~0x3f) == 0 && ((1L << (_la - 125)) & 7L) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(872);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(881);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,120,_ctx) ) {
			case 1:
				{
				setState(874);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__107) {
					{
					setState(873);
					match(T__107);
					}
				}

				setState(876);
				match(T__127);
				setState(879);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__128) {
					{
					setState(877);
					match(T__128);
					setState(878);
					_la = _input.LA(1);
					if ( !(_la==T__7 || _la==T__9) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
				}

				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WithContext extends ParserRuleContext {
		public List<CteContext> cte() {
			return getRuleContexts(CteContext.class);
		}
		public CteContext cte(int i) {
			return getRuleContext(CteContext.class,i);
		}
		public WithContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_with; }
	}

	public final WithContext with() throws RecognitionException {
		WithContext _localctx = new WithContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_with);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(883);
			match(T__85);
			setState(885);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,121,_ctx) ) {
			case 1:
				{
				setState(884);
				match(T__129);
				}
				break;
			}
			setState(887);
			cte();
			setState(892);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__28) {
				{
				{
				setState(888);
				match(T__28);
				setState(889);
				cte();
				}
				}
				setState(894);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CteContext extends ParserRuleContext {
		public AliasContext alias() {
			return getRuleContext(AliasContext.class,0);
		}
		public SelectContext select() {
			return getRuleContext(SelectContext.class,0);
		}
		public List<NameContext> name() {
			return getRuleContexts(NameContext.class);
		}
		public NameContext name(int i) {
			return getRuleContext(NameContext.class,i);
		}
		public List<LiteralContext> literal() {
			return getRuleContexts(LiteralContext.class);
		}
		public LiteralContext literal(int i) {
			return getRuleContext(LiteralContext.class,i);
		}
		public CteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cte; }
	}

	public final CteContext cte() throws RecognitionException {
		CteContext _localctx = new CteContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_cte);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(895);
			alias();
			setState(907);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__16) {
				{
				setState(896);
				match(T__16);
				setState(897);
				name();
				setState(902);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__28) {
					{
					{
					setState(898);
					match(T__28);
					setState(899);
					name();
					}
					}
					setState(904);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(905);
				match(T__17);
				}
			}

			setState(909);
			match(T__5);
			setState(914);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__65 || _la==T__107) {
				{
				setState(911);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__107) {
					{
					setState(910);
					match(T__107);
					}
				}

				setState(913);
				match(T__65);
				}
			}

			setState(916);
			match(T__16);
			setState(917);
			select(0);
			setState(918);
			match(T__17);
			setState(934);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__75) {
				{
				setState(919);
				match(T__75);
				setState(920);
				_la = _input.LA(1);
				if ( !(_la==T__130 || _la==T__131) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(921);
				match(T__132);
				setState(922);
				match(T__133);
				setState(923);
				name();
				setState(928);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__28) {
					{
					{
					setState(924);
					match(T__28);
					setState(925);
					name();
					}
					}
					setState(930);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(931);
				match(T__48);
				setState(932);
				name();
				}
			}

			setState(958);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__134) {
				{
				setState(936);
				match(T__134);
				setState(937);
				name();
				setState(942);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__28) {
					{
					{
					setState(938);
					match(T__28);
					setState(939);
					name();
					}
					}
					setState(944);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(945);
				match(T__48);
				setState(946);
				name();
				setState(952);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__23) {
					{
					setState(947);
					match(T__23);
					setState(948);
					literal();
					setState(949);
					match(T__115);
					setState(950);
					literal();
					}
				}

				setState(956);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__89) {
					{
					setState(954);
					match(T__89);
					setState(955);
					name();
					}
				}

				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeleteContext extends ParserRuleContext {
		public QnameContext qname() {
			return getRuleContext(QnameContext.class,0);
		}
		public WithContext with() {
			return getRuleContext(WithContext.class,0);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public IndexedByContext indexedBy() {
			return getRuleContext(IndexedByContext.class,0);
		}
		public List<SourcesContext> sources() {
			return getRuleContexts(SourcesContext.class);
		}
		public SourcesContext sources(int i) {
			return getRuleContext(SourcesContext.class,i);
		}
		public WhereContext where() {
			return getRuleContext(WhereContext.class,0);
		}
		public ReturningContext returning() {
			return getRuleContext(ReturningContext.class,0);
		}
		public OrderByContext orderBy() {
			return getRuleContext(OrderByContext.class,0);
		}
		public LimitContext limit() {
			return getRuleContext(LimitContext.class,0);
		}
		public OffsetContext offset() {
			return getRuleContext(OffsetContext.class,0);
		}
		public DeleteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_delete; }
	}

	public final DeleteContext delete() throws RecognitionException {
		DeleteContext _localctx = new DeleteContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_delete);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(961);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__85) {
				{
				setState(960);
				with();
				}
			}

			setState(963);
			match(T__94);
			setState(964);
			match(T__135);
			setState(966);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,134,_ctx) ) {
			case 1:
				{
				setState(965);
				match(T__136);
				}
				break;
			}
			setState(968);
			qname();
			setState(971);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__5) {
				{
				setState(969);
				match(T__5);
				setState(970);
				name();
				}
			}

			setState(974);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__107 || _la==T__151) {
				{
				setState(973);
				indexedBy();
				}
			}

			setState(985);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__89) {
				{
				setState(976);
				match(T__89);
				setState(977);
				sources(0);
				setState(982);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__28) {
					{
					{
					setState(978);
					match(T__28);
					setState(979);
					sources(0);
					}
					}
					setState(984);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(988);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__197) {
				{
				setState(987);
				where();
				}
			}

			setState(991);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__152) {
				{
				setState(990);
				returning();
				}
			}

			setState(994);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__361) {
				{
				setState(993);
				orderBy();
				}
			}

			setState(997);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__170) {
				{
				setState(996);
				limit();
				}
			}

			setState(1000);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__169) {
				{
				setState(999);
				offset();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InsertContext extends ParserRuleContext {
		public QnameContext qname() {
			return getRuleContext(QnameContext.class,0);
		}
		public SourceContext source() {
			return getRuleContext(SourceContext.class,0);
		}
		public WithContext with() {
			return getRuleContext(WithContext.class,0);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public NamesContext names() {
			return getRuleContext(NamesContext.class,0);
		}
		public OverridingContext overriding() {
			return getRuleContext(OverridingContext.class,0);
		}
		public ReturningContext returning() {
			return getRuleContext(ReturningContext.class,0);
		}
		public AfirrContext afirr() {
			return getRuleContext(AfirrContext.class,0);
		}
		public List<UpsertContext> upsert() {
			return getRuleContexts(UpsertContext.class);
		}
		public UpsertContext upsert(int i) {
			return getRuleContext(UpsertContext.class,i);
		}
		public InsertContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_insert; }
	}

	public final InsertContext insert() throws RecognitionException {
		InsertContext _localctx = new InsertContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_insert);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1003);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__85) {
				{
				setState(1002);
				with();
				}
			}

			setState(1011);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__95:
				{
				setState(1005);
				match(T__95);
				setState(1008);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__104) {
					{
					setState(1006);
					match(T__104);
					setState(1007);
					afirr();
					}
				}

				}
				break;
			case T__105:
				{
				setState(1010);
				match(T__105);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(1013);
			match(T__25);
			setState(1014);
			qname();
			setState(1017);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,147,_ctx) ) {
			case 1:
				{
				setState(1015);
				match(T__5);
				setState(1016);
				name();
				}
				break;
			}
			setState(1020);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,148,_ctx) ) {
			case 1:
				{
				setState(1019);
				names();
				}
				break;
			}
			setState(1023);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,149,_ctx) ) {
			case 1:
				{
				setState(1022);
				overriding();
				}
				break;
			}
			setState(1034);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
			case T__2:
			case T__3:
			case T__4:
			case T__5:
			case T__6:
			case T__7:
			case T__8:
			case T__9:
			case T__10:
			case T__11:
			case T__12:
			case T__14:
			case T__16:
			case T__18:
			case T__19:
			case T__20:
			case T__21:
			case T__22:
			case T__23:
			case T__24:
			case T__25:
			case T__26:
			case T__27:
			case T__29:
			case T__30:
			case T__31:
			case T__32:
			case T__33:
			case T__34:
			case T__35:
			case T__36:
			case T__37:
			case T__38:
			case T__39:
			case T__40:
			case T__41:
			case T__42:
			case T__43:
			case T__44:
			case T__45:
			case T__46:
			case T__47:
			case T__48:
			case T__49:
			case T__50:
			case T__51:
			case T__53:
			case T__54:
			case T__55:
			case T__56:
			case T__57:
			case T__58:
			case T__59:
			case T__60:
			case T__62:
			case T__63:
			case T__64:
			case T__65:
			case T__66:
			case T__67:
			case T__68:
			case T__69:
			case T__70:
			case T__71:
			case T__72:
			case T__73:
			case T__74:
			case T__75:
			case T__76:
			case T__77:
			case T__78:
			case T__79:
			case T__80:
			case T__81:
			case T__82:
			case T__83:
			case T__84:
			case T__85:
			case T__86:
			case T__87:
			case T__88:
			case T__90:
			case T__91:
			case T__92:
			case T__93:
			case T__94:
			case T__95:
			case T__96:
			case T__99:
			case T__100:
			case T__102:
			case T__103:
			case T__105:
			case T__108:
			case T__109:
			case T__110:
			case T__111:
			case T__112:
			case T__114:
			case T__116:
			case T__118:
			case T__119:
			case T__120:
			case T__121:
			case T__122:
			case T__123:
			case T__124:
			case T__125:
			case T__126:
			case T__127:
			case T__128:
			case T__129:
			case T__130:
			case T__131:
			case T__132:
			case T__133:
			case T__134:
			case T__135:
			case T__136:
			case T__137:
			case T__138:
			case T__139:
			case T__140:
			case T__141:
			case T__142:
			case T__143:
			case T__144:
			case T__145:
			case T__147:
			case T__148:
			case T__149:
			case T__150:
			case T__151:
			case T__152:
			case T__154:
			case T__158:
			case T__160:
			case T__161:
			case T__162:
			case T__163:
			case T__164:
			case T__165:
			case T__166:
			case T__167:
			case T__168:
			case T__170:
			case T__171:
			case T__172:
			case T__173:
			case T__174:
			case T__175:
			case T__176:
			case T__177:
			case T__178:
			case T__179:
			case T__181:
			case T__183:
			case T__184:
			case T__185:
			case T__186:
			case T__187:
			case T__188:
			case T__190:
			case T__192:
			case T__193:
			case T__194:
			case T__195:
			case T__196:
			case T__198:
			case T__200:
			case T__201:
			case T__202:
			case T__203:
			case T__205:
			case T__207:
			case T__208:
			case T__209:
			case T__210:
			case T__211:
			case T__212:
			case T__214:
			case T__215:
			case T__216:
			case T__217:
			case T__218:
			case T__219:
			case T__220:
			case T__221:
			case T__222:
			case T__223:
			case T__224:
			case T__225:
			case T__226:
			case T__227:
			case T__228:
			case T__229:
			case T__230:
			case T__231:
			case T__232:
			case T__233:
			case T__234:
			case T__237:
			case T__238:
			case T__239:
			case T__240:
			case T__241:
			case T__242:
			case T__243:
			case T__244:
			case T__246:
			case T__247:
			case T__249:
			case T__251:
			case T__253:
			case T__254:
			case T__255:
			case T__256:
			case T__257:
			case T__258:
			case T__259:
			case T__267:
			case T__268:
			case T__269:
			case T__270:
			case T__271:
			case T__272:
			case T__273:
			case T__274:
			case T__275:
			case T__276:
			case T__277:
			case T__278:
			case T__281:
			case T__282:
			case T__283:
			case T__284:
			case T__285:
			case T__286:
			case T__287:
			case T__288:
			case T__289:
			case T__290:
			case T__291:
			case T__292:
			case T__293:
			case T__294:
			case T__295:
			case T__296:
			case T__297:
			case T__298:
			case T__299:
			case T__300:
			case T__301:
			case T__302:
			case T__303:
			case T__304:
			case T__305:
			case T__306:
			case T__307:
			case T__308:
			case T__309:
			case T__310:
			case T__311:
			case T__312:
			case T__313:
			case T__314:
			case T__318:
			case T__319:
			case T__320:
			case T__321:
			case T__322:
			case T__323:
			case T__324:
			case T__325:
			case T__326:
			case T__327:
			case T__328:
			case T__329:
			case T__330:
			case T__331:
			case T__332:
			case T__333:
			case T__334:
			case T__335:
			case T__336:
			case T__337:
			case T__338:
			case T__339:
			case T__340:
			case T__341:
			case T__342:
			case T__343:
			case T__344:
			case T__345:
			case T__346:
			case T__347:
			case T__348:
			case T__349:
			case T__350:
			case T__351:
			case T__352:
			case T__354:
			case T__355:
			case T__356:
			case T__357:
			case T__358:
			case T__359:
			case T__360:
			case T__361:
			case T__362:
			case T__363:
			case T__364:
			case T__365:
			case T__366:
			case T__367:
			case T__368:
			case T__370:
			case T__371:
			case T__372:
			case T__373:
			case T__374:
			case T__375:
			case T__376:
			case T__377:
			case T__378:
			case T__379:
			case T__380:
			case T__381:
			case T__382:
			case T__383:
			case T__384:
			case T__385:
			case T__386:
			case T__387:
			case T__388:
			case T__389:
			case T__390:
			case T__391:
			case T__392:
			case T__393:
			case T__394:
			case T__395:
			case T__397:
			case T__398:
			case T__399:
			case T__400:
			case T__401:
			case UNICODE_NAME:
			case STRING:
			case ID:
			case BRACKETS:
			case DOLLARS:
			case PARAMETER:
			case VARIABLE:
			case COMMENT:
			case BLOCK_COMMENT:
			case WHITESPACE:
			case OPERATOR:
				{
				setState(1025);
				source();
				setState(1029);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__97) {
					{
					{
					setState(1026);
					upsert();
					}
					}
					setState(1031);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case T__115:
				{
				setState(1032);
				match(T__115);
				setState(1033);
				match(T__137);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(1037);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__152) {
				{
				setState(1036);
				returning();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OverridingContext extends ParserRuleContext {
		public OverridingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_overriding; }
	}

	public final OverridingContext overriding() throws RecognitionException {
		OverridingContext _localctx = new OverridingContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_overriding);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1039);
			match(T__138);
			setState(1040);
			_la = _input.LA(1);
			if ( !(_la==T__139 || _la==T__140) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(1041);
			match(T__141);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UpsertContext extends ParserRuleContext {
		public List<SetterContext> setter() {
			return getRuleContexts(SetterContext.class);
		}
		public SetterContext setter(int i) {
			return getRuleContext(SetterContext.class,i);
		}
		public TermsContext terms() {
			return getRuleContext(TermsContext.class,0);
		}
		public List<WhereContext> where() {
			return getRuleContexts(WhereContext.class);
		}
		public WhereContext where(int i) {
			return getRuleContext(WhereContext.class,i);
		}
		public UpsertContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_upsert; }
	}

	public final UpsertContext upsert() throws RecognitionException {
		UpsertContext _localctx = new UpsertContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_upsert);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1043);
			match(T__97);
			setState(1044);
			match(T__120);
			setState(1049);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,154,_ctx) ) {
			case 1:
				{
				setState(1045);
				terms();
				setState(1047);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__197) {
					{
					setState(1046);
					where();
					}
				}

				}
				break;
			}
			setState(1051);
			match(T__142);
			setState(1066);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__143:
				{
				setState(1052);
				match(T__143);
				}
				break;
			case T__96:
				{
				setState(1053);
				match(T__96);
				setState(1054);
				match(T__48);
				setState(1055);
				setter();
				setState(1060);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__28) {
					{
					{
					setState(1056);
					match(T__28);
					setState(1057);
					setter();
					}
					}
					setState(1062);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1064);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__197) {
					{
					setState(1063);
					where();
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MergeContext extends ParserRuleContext {
		public List<NameContext> name() {
			return getRuleContexts(NameContext.class);
		}
		public NameContext name(int i) {
			return getRuleContext(NameContext.class,i);
		}
		public SourceContext source() {
			return getRuleContext(SourceContext.class,0);
		}
		public TermsContext terms() {
			return getRuleContext(TermsContext.class,0);
		}
		public WithContext with() {
			return getRuleContext(WithContext.class,0);
		}
		public List<WhenContext> when() {
			return getRuleContexts(WhenContext.class);
		}
		public WhenContext when(int i) {
			return getRuleContext(WhenContext.class,i);
		}
		public MergeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_merge; }
	}

	public final MergeContext merge() throws RecognitionException {
		MergeContext _localctx = new MergeContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_merge);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1069);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__85) {
				{
				setState(1068);
				with();
				}
			}

			setState(1071);
			match(T__144);
			setState(1072);
			match(T__25);
			setState(1074);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,159,_ctx) ) {
			case 1:
				{
				setState(1073);
				match(T__136);
				}
				break;
			}
			setState(1076);
			name();
			setState(1081);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -4620693218219474948L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & -23672811834834945L) != 0) || ((((_la - 128)) & ~0x3f) == 0 && ((1L << (_la - 128)) & -4656726419123471361L) != 0) || ((((_la - 193)) & ~0x3f) == 0 && ((1L << (_la - 193)) & -1522243062332412065L) != 0) || ((((_la - 257)) & ~0x3f) == 0 && ((1L << (_la - 257)) & -4035225266149656561L) != 0) || ((((_la - 321)) & ~0x3f) == 0 && ((1L << (_la - 321)) & -562958543355905L) != 0) || ((((_la - 385)) & ~0x3f) == 0 && ((1L << (_la - 385)) & 67677712383L) != 0)) {
				{
				setState(1078);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,160,_ctx) ) {
				case 1:
					{
					setState(1077);
					match(T__5);
					}
					break;
				}
				setState(1080);
				name();
				}
			}

			setState(1083);
			match(T__89);
			setState(1085);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,162,_ctx) ) {
			case 1:
				{
				setState(1084);
				match(T__136);
				}
				break;
			}
			setState(1087);
			source();
			setState(1088);
			match(T__97);
			setState(1089);
			terms();
			setState(1093);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__101) {
				{
				{
				setState(1090);
				when();
				}
				}
				setState(1095);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WhenContext extends ParserRuleContext {
		public List<SetterContext> setter() {
			return getRuleContexts(SetterContext.class);
		}
		public SetterContext setter(int i) {
			return getRuleContext(SetterContext.class,i);
		}
		public List<SubtermContext> subterm() {
			return getRuleContexts(SubtermContext.class);
		}
		public SubtermContext subterm(int i) {
			return getRuleContext(SubtermContext.class,i);
		}
		public ValuesContext values() {
			return getRuleContext(ValuesContext.class,0);
		}
		public QnamesContext qnames() {
			return getRuleContext(QnamesContext.class,0);
		}
		public OverridingContext overriding() {
			return getRuleContext(OverridingContext.class,0);
		}
		public WhereContext where() {
			return getRuleContext(WhereContext.class,0);
		}
		public WhenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_when; }
	}

	public final WhenContext when() throws RecognitionException {
		WhenContext _localctx = new WhenContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_when);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1096);
			match(T__101);
			setState(1098);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__107) {
				{
				setState(1097);
				match(T__107);
				}
			}

			setState(1100);
			match(T__145);
			setState(1105);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__146) {
				{
				{
				setState(1101);
				match(T__146);
				setState(1102);
				subterm(0);
				}
				}
				setState(1107);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1108);
			match(T__147);
			setState(1143);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__95:
				{
				setState(1109);
				match(T__95);
				setState(1111);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__16) {
					{
					setState(1110);
					qnames();
					}
				}

				setState(1114);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__138) {
					{
					setState(1113);
					overriding();
					}
				}

				setState(1119);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__137:
					{
					setState(1116);
					values();
					}
					break;
				case T__115:
					{
					setState(1117);
					match(T__115);
					setState(1118);
					match(T__137);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1122);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__197) {
					{
					setState(1121);
					where();
					}
				}

				}
				break;
			case T__96:
				{
				setState(1124);
				match(T__96);
				setState(1125);
				match(T__48);
				setState(1126);
				setter();
				setState(1131);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__28) {
					{
					{
					setState(1127);
					match(T__28);
					setState(1128);
					setter();
					}
					}
					setState(1133);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1138);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__94 || _la==T__197) {
					{
					setState(1135);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__94) {
						{
						setState(1134);
						match(T__94);
						}
					}

					setState(1137);
					where();
					}
				}

				}
				break;
			case T__94:
				{
				setState(1140);
				match(T__94);
				}
				break;
			case T__142:
				{
				setState(1141);
				match(T__142);
				setState(1142);
				match(T__143);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UpdateContext extends ParserRuleContext {
		public QnameContext qname() {
			return getRuleContext(QnameContext.class,0);
		}
		public List<SetterContext> setter() {
			return getRuleContexts(SetterContext.class);
		}
		public SetterContext setter(int i) {
			return getRuleContext(SetterContext.class,i);
		}
		public WithContext with() {
			return getRuleContext(WithContext.class,0);
		}
		public AfirrContext afirr() {
			return getRuleContext(AfirrContext.class,0);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public IndexedByContext indexedBy() {
			return getRuleContext(IndexedByContext.class,0);
		}
		public SourcesContext sources() {
			return getRuleContext(SourcesContext.class,0);
		}
		public WhereContext where() {
			return getRuleContext(WhereContext.class,0);
		}
		public ReturningContext returning() {
			return getRuleContext(ReturningContext.class,0);
		}
		public OrderByContext orderBy() {
			return getRuleContext(OrderByContext.class,0);
		}
		public LimitContext limit() {
			return getRuleContext(LimitContext.class,0);
		}
		public OffsetContext offset() {
			return getRuleContext(OffsetContext.class,0);
		}
		public UpdateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_update; }
	}

	public final UpdateContext update() throws RecognitionException {
		UpdateContext _localctx = new UpdateContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_update);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1146);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__85) {
				{
				setState(1145);
				with();
				}
			}

			setState(1148);
			match(T__96);
			setState(1151);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__104) {
				{
				setState(1149);
				match(T__104);
				setState(1150);
				afirr();
				}
			}

			setState(1153);
			qname();
			setState(1158);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,177,_ctx) ) {
			case 1:
				{
				setState(1155);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,176,_ctx) ) {
				case 1:
					{
					setState(1154);
					match(T__5);
					}
					break;
				}
				setState(1157);
				name();
				}
				break;
			}
			setState(1161);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__107 || _la==T__151) {
				{
				setState(1160);
				indexedBy();
				}
			}

			setState(1163);
			match(T__48);
			setState(1164);
			setter();
			setState(1169);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__28) {
				{
				{
				setState(1165);
				match(T__28);
				setState(1166);
				setter();
				}
				}
				setState(1171);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1174);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__135) {
				{
				setState(1172);
				match(T__135);
				setState(1173);
				sources(0);
				}
			}

			setState(1177);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__197) {
				{
				setState(1176);
				where();
				}
			}

			setState(1180);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__152) {
				{
				setState(1179);
				returning();
				}
			}

			setState(1183);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__361) {
				{
				setState(1182);
				orderBy();
				}
			}

			setState(1186);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__170) {
				{
				setState(1185);
				limit();
				}
			}

			setState(1189);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__169) {
				{
				setState(1188);
				offset();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SetterContext extends ParserRuleContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public QnameContext qname() {
			return getRuleContext(QnameContext.class,0);
		}
		public QnamesContext qnames() {
			return getRuleContext(QnamesContext.class,0);
		}
		public SetterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setter; }
	}

	public final SetterContext setter() throws RecognitionException {
		SetterContext _localctx = new SetterContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_setter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1193);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
			case T__2:
			case T__3:
			case T__4:
			case T__5:
			case T__6:
			case T__7:
			case T__8:
			case T__9:
			case T__10:
			case T__11:
			case T__12:
			case T__14:
			case T__18:
			case T__19:
			case T__20:
			case T__21:
			case T__22:
			case T__23:
			case T__24:
			case T__25:
			case T__26:
			case T__27:
			case T__29:
			case T__30:
			case T__31:
			case T__32:
			case T__33:
			case T__34:
			case T__35:
			case T__36:
			case T__37:
			case T__38:
			case T__39:
			case T__40:
			case T__41:
			case T__42:
			case T__43:
			case T__44:
			case T__45:
			case T__46:
			case T__47:
			case T__48:
			case T__49:
			case T__50:
			case T__51:
			case T__53:
			case T__54:
			case T__55:
			case T__56:
			case T__57:
			case T__58:
			case T__59:
			case T__60:
			case T__62:
			case T__63:
			case T__64:
			case T__65:
			case T__66:
			case T__67:
			case T__68:
			case T__69:
			case T__70:
			case T__71:
			case T__72:
			case T__73:
			case T__74:
			case T__75:
			case T__76:
			case T__77:
			case T__78:
			case T__79:
			case T__80:
			case T__81:
			case T__82:
			case T__83:
			case T__84:
			case T__86:
			case T__87:
			case T__88:
			case T__90:
			case T__91:
			case T__92:
			case T__93:
			case T__94:
			case T__95:
			case T__96:
			case T__99:
			case T__100:
			case T__102:
			case T__103:
			case T__105:
			case T__108:
			case T__109:
			case T__110:
			case T__111:
			case T__112:
			case T__114:
			case T__116:
			case T__118:
			case T__119:
			case T__120:
			case T__121:
			case T__122:
			case T__123:
			case T__124:
			case T__125:
			case T__126:
			case T__127:
			case T__128:
			case T__129:
			case T__130:
			case T__131:
			case T__132:
			case T__133:
			case T__134:
			case T__135:
			case T__136:
			case T__138:
			case T__139:
			case T__140:
			case T__141:
			case T__142:
			case T__143:
			case T__144:
			case T__145:
			case T__147:
			case T__148:
			case T__149:
			case T__150:
			case T__151:
			case T__152:
			case T__158:
			case T__160:
			case T__161:
			case T__162:
			case T__163:
			case T__164:
			case T__165:
			case T__166:
			case T__167:
			case T__168:
			case T__170:
			case T__171:
			case T__172:
			case T__173:
			case T__174:
			case T__175:
			case T__176:
			case T__177:
			case T__178:
			case T__179:
			case T__181:
			case T__183:
			case T__184:
			case T__185:
			case T__186:
			case T__187:
			case T__188:
			case T__190:
			case T__192:
			case T__193:
			case T__194:
			case T__195:
			case T__196:
			case T__198:
			case T__200:
			case T__201:
			case T__202:
			case T__203:
			case T__205:
			case T__207:
			case T__208:
			case T__209:
			case T__210:
			case T__211:
			case T__212:
			case T__214:
			case T__215:
			case T__216:
			case T__217:
			case T__218:
			case T__219:
			case T__220:
			case T__221:
			case T__222:
			case T__223:
			case T__224:
			case T__225:
			case T__226:
			case T__227:
			case T__228:
			case T__229:
			case T__230:
			case T__231:
			case T__232:
			case T__233:
			case T__234:
			case T__237:
			case T__238:
			case T__239:
			case T__240:
			case T__241:
			case T__242:
			case T__243:
			case T__244:
			case T__246:
			case T__247:
			case T__249:
			case T__251:
			case T__253:
			case T__254:
			case T__255:
			case T__256:
			case T__257:
			case T__258:
			case T__259:
			case T__267:
			case T__268:
			case T__269:
			case T__270:
			case T__271:
			case T__272:
			case T__273:
			case T__274:
			case T__276:
			case T__277:
			case T__278:
			case T__281:
			case T__282:
			case T__283:
			case T__284:
			case T__285:
			case T__286:
			case T__287:
			case T__288:
			case T__289:
			case T__290:
			case T__291:
			case T__292:
			case T__293:
			case T__294:
			case T__295:
			case T__296:
			case T__297:
			case T__298:
			case T__299:
			case T__300:
			case T__301:
			case T__302:
			case T__303:
			case T__304:
			case T__305:
			case T__306:
			case T__307:
			case T__308:
			case T__309:
			case T__310:
			case T__311:
			case T__312:
			case T__313:
			case T__314:
			case T__318:
			case T__319:
			case T__320:
			case T__321:
			case T__322:
			case T__323:
			case T__324:
			case T__325:
			case T__326:
			case T__327:
			case T__328:
			case T__329:
			case T__330:
			case T__331:
			case T__332:
			case T__333:
			case T__334:
			case T__335:
			case T__336:
			case T__337:
			case T__338:
			case T__339:
			case T__340:
			case T__341:
			case T__342:
			case T__343:
			case T__344:
			case T__345:
			case T__346:
			case T__347:
			case T__348:
			case T__349:
			case T__350:
			case T__351:
			case T__352:
			case T__354:
			case T__355:
			case T__356:
			case T__357:
			case T__358:
			case T__359:
			case T__360:
			case T__361:
			case T__362:
			case T__363:
			case T__364:
			case T__365:
			case T__366:
			case T__367:
			case T__368:
			case T__370:
			case T__371:
			case T__372:
			case T__373:
			case T__374:
			case T__375:
			case T__376:
			case T__377:
			case T__378:
			case T__379:
			case T__380:
			case T__381:
			case T__382:
			case T__383:
			case T__384:
			case T__385:
			case T__386:
			case T__387:
			case T__388:
			case T__389:
			case T__390:
			case T__391:
			case T__392:
			case T__393:
			case T__394:
			case T__395:
			case T__397:
			case T__398:
			case T__399:
			case T__400:
			case T__401:
			case UNICODE_NAME:
			case STRING:
			case ID:
			case BRACKETS:
			case DOLLARS:
			case PARAMETER:
			case VARIABLE:
			case COMMENT:
			case BLOCK_COMMENT:
			case WHITESPACE:
			case OPERATOR:
				{
				setState(1191);
				qname();
				}
				break;
			case T__16:
				{
				setState(1192);
				qnames();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(1195);
			match(T__15);
			setState(1196);
			term(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AfirrContext extends ParserRuleContext {
		public AfirrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_afirr; }
	}

	public final AfirrContext afirr() throws RecognitionException {
		AfirrContext _localctx = new AfirrContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_afirr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1198);
			_la = _input.LA(1);
			if ( !(_la==T__22 || ((((_la - 106)) & ~0x3f) == 0 && ((1L << (_la - 106)) & 61572651155457L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IndexedByContext extends ParserRuleContext {
		public QnameContext qname() {
			return getRuleContext(QnameContext.class,0);
		}
		public IndexedByContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexedBy; }
	}

	public final IndexedByContext indexedBy() throws RecognitionException {
		IndexedByContext _localctx = new IndexedByContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_indexedBy);
		try {
			setState(1205);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__107:
				enterOuterAlt(_localctx, 1);
				{
				setState(1200);
				match(T__107);
				setState(1201);
				match(T__151);
				}
				break;
			case T__151:
				enterOuterAlt(_localctx, 2);
				{
				setState(1202);
				match(T__151);
				setState(1203);
				match(T__133);
				setState(1204);
				qname();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ReturningContext extends ParserRuleContext {
		public List<ReturnedContext> returned() {
			return getRuleContexts(ReturnedContext.class);
		}
		public ReturnedContext returned(int i) {
			return getRuleContext(ReturnedContext.class,i);
		}
		public ReturningContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returning; }
	}

	public final ReturningContext returning() throws RecognitionException {
		ReturningContext _localctx = new ReturningContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_returning);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1207);
			match(T__152);
			setState(1208);
			returned();
			setState(1213);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__28) {
				{
				{
				setState(1209);
				match(T__28);
				setState(1210);
				returned();
				}
				}
				setState(1215);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ReturnedContext extends ParserRuleContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public AliasContext alias() {
			return getRuleContext(AliasContext.class,0);
		}
		public ReturnedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returned; }
	}

	public final ReturnedContext returned() throws RecognitionException {
		ReturnedContext _localctx = new ReturnedContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_returned);
		try {
			setState(1224);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__153:
				enterOuterAlt(_localctx, 1);
				{
				setState(1216);
				match(T__153);
				}
				break;
			case T__1:
			case T__2:
			case T__3:
			case T__4:
			case T__5:
			case T__6:
			case T__7:
			case T__8:
			case T__9:
			case T__10:
			case T__11:
			case T__12:
			case T__14:
			case T__16:
			case T__18:
			case T__19:
			case T__20:
			case T__21:
			case T__22:
			case T__23:
			case T__24:
			case T__25:
			case T__26:
			case T__27:
			case T__29:
			case T__30:
			case T__31:
			case T__32:
			case T__33:
			case T__34:
			case T__35:
			case T__36:
			case T__37:
			case T__38:
			case T__39:
			case T__40:
			case T__41:
			case T__42:
			case T__43:
			case T__44:
			case T__45:
			case T__46:
			case T__47:
			case T__48:
			case T__49:
			case T__50:
			case T__51:
			case T__52:
			case T__53:
			case T__54:
			case T__55:
			case T__56:
			case T__57:
			case T__58:
			case T__59:
			case T__60:
			case T__62:
			case T__63:
			case T__64:
			case T__65:
			case T__66:
			case T__67:
			case T__68:
			case T__69:
			case T__70:
			case T__71:
			case T__72:
			case T__73:
			case T__74:
			case T__75:
			case T__76:
			case T__77:
			case T__78:
			case T__79:
			case T__80:
			case T__81:
			case T__82:
			case T__83:
			case T__84:
			case T__85:
			case T__86:
			case T__87:
			case T__88:
			case T__90:
			case T__91:
			case T__92:
			case T__93:
			case T__94:
			case T__95:
			case T__96:
			case T__97:
			case T__99:
			case T__100:
			case T__102:
			case T__103:
			case T__105:
			case T__106:
			case T__107:
			case T__108:
			case T__109:
			case T__110:
			case T__111:
			case T__112:
			case T__114:
			case T__115:
			case T__116:
			case T__118:
			case T__119:
			case T__120:
			case T__121:
			case T__122:
			case T__123:
			case T__124:
			case T__125:
			case T__126:
			case T__127:
			case T__128:
			case T__129:
			case T__130:
			case T__131:
			case T__132:
			case T__133:
			case T__134:
			case T__135:
			case T__136:
			case T__137:
			case T__138:
			case T__139:
			case T__140:
			case T__141:
			case T__142:
			case T__143:
			case T__144:
			case T__145:
			case T__147:
			case T__148:
			case T__149:
			case T__150:
			case T__151:
			case T__152:
			case T__154:
			case T__158:
			case T__160:
			case T__161:
			case T__162:
			case T__163:
			case T__164:
			case T__165:
			case T__166:
			case T__167:
			case T__168:
			case T__170:
			case T__171:
			case T__172:
			case T__173:
			case T__174:
			case T__175:
			case T__176:
			case T__177:
			case T__178:
			case T__179:
			case T__181:
			case T__183:
			case T__184:
			case T__185:
			case T__186:
			case T__187:
			case T__188:
			case T__190:
			case T__192:
			case T__193:
			case T__194:
			case T__195:
			case T__196:
			case T__198:
			case T__200:
			case T__201:
			case T__202:
			case T__203:
			case T__205:
			case T__207:
			case T__208:
			case T__209:
			case T__210:
			case T__211:
			case T__212:
			case T__214:
			case T__215:
			case T__216:
			case T__217:
			case T__218:
			case T__219:
			case T__220:
			case T__221:
			case T__222:
			case T__223:
			case T__224:
			case T__225:
			case T__226:
			case T__227:
			case T__228:
			case T__229:
			case T__230:
			case T__231:
			case T__232:
			case T__233:
			case T__234:
			case T__235:
			case T__236:
			case T__237:
			case T__238:
			case T__239:
			case T__240:
			case T__241:
			case T__242:
			case T__243:
			case T__244:
			case T__246:
			case T__247:
			case T__249:
			case T__251:
			case T__253:
			case T__254:
			case T__255:
			case T__256:
			case T__257:
			case T__258:
			case T__259:
			case T__267:
			case T__268:
			case T__269:
			case T__270:
			case T__271:
			case T__272:
			case T__273:
			case T__274:
			case T__275:
			case T__276:
			case T__277:
			case T__278:
			case T__281:
			case T__282:
			case T__283:
			case T__284:
			case T__285:
			case T__286:
			case T__287:
			case T__288:
			case T__289:
			case T__290:
			case T__291:
			case T__292:
			case T__293:
			case T__294:
			case T__295:
			case T__296:
			case T__297:
			case T__298:
			case T__299:
			case T__300:
			case T__301:
			case T__302:
			case T__303:
			case T__304:
			case T__305:
			case T__306:
			case T__307:
			case T__308:
			case T__309:
			case T__310:
			case T__311:
			case T__312:
			case T__313:
			case T__314:
			case T__318:
			case T__319:
			case T__320:
			case T__321:
			case T__322:
			case T__323:
			case T__324:
			case T__325:
			case T__326:
			case T__327:
			case T__328:
			case T__329:
			case T__330:
			case T__331:
			case T__332:
			case T__333:
			case T__334:
			case T__335:
			case T__336:
			case T__337:
			case T__338:
			case T__339:
			case T__340:
			case T__341:
			case T__342:
			case T__343:
			case T__344:
			case T__345:
			case T__346:
			case T__347:
			case T__348:
			case T__349:
			case T__350:
			case T__351:
			case T__352:
			case T__354:
			case T__355:
			case T__356:
			case T__357:
			case T__358:
			case T__359:
			case T__360:
			case T__361:
			case T__362:
			case T__363:
			case T__364:
			case T__365:
			case T__366:
			case T__367:
			case T__368:
			case T__369:
			case T__370:
			case T__371:
			case T__372:
			case T__373:
			case T__374:
			case T__375:
			case T__376:
			case T__377:
			case T__378:
			case T__379:
			case T__380:
			case T__381:
			case T__382:
			case T__383:
			case T__384:
			case T__385:
			case T__386:
			case T__387:
			case T__388:
			case T__389:
			case T__390:
			case T__391:
			case T__392:
			case T__393:
			case T__394:
			case T__395:
			case T__397:
			case T__398:
			case T__399:
			case T__400:
			case T__401:
			case UNICODE_NAME:
			case UNICODE_STRING:
			case NATIONAL_STRING:
			case STRING:
			case ID:
			case BRACKETS:
			case DOLLARS:
			case BITS:
			case BYTES:
			case OCTALS:
			case INTEGER:
			case FLOAT:
			case PARAMETER:
			case VARIABLE:
			case COMMENT:
			case BLOCK_COMMENT:
			case WHITESPACE:
			case OPERATOR:
				enterOuterAlt(_localctx, 2);
				{
				setState(1217);
				term(0);
				setState(1222);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,190,_ctx) ) {
				case 1:
					{
					setState(1219);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,189,_ctx) ) {
					case 1:
						{
						setState(1218);
						match(T__5);
						}
						break;
					}
					setState(1221);
					alias();
					}
					break;
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SelectContext extends ParserRuleContext {
		public SelectCoreContext selectCore() {
			return getRuleContext(SelectCoreContext.class,0);
		}
		public WithContext with() {
			return getRuleContext(WithContext.class,0);
		}
		public List<OrderByContext> orderBy() {
			return getRuleContexts(OrderByContext.class);
		}
		public OrderByContext orderBy(int i) {
			return getRuleContext(OrderByContext.class,i);
		}
		public List<OffsetContext> offset() {
			return getRuleContexts(OffsetContext.class);
		}
		public OffsetContext offset(int i) {
			return getRuleContext(OffsetContext.class,i);
		}
		public List<FetchContext> fetch() {
			return getRuleContexts(FetchContext.class);
		}
		public FetchContext fetch(int i) {
			return getRuleContext(FetchContext.class,i);
		}
		public List<LimitContext> limit() {
			return getRuleContexts(LimitContext.class);
		}
		public LimitContext limit(int i) {
			return getRuleContext(LimitContext.class,i);
		}
		public List<ForUpdateContext> forUpdate() {
			return getRuleContexts(ForUpdateContext.class);
		}
		public ForUpdateContext forUpdate(int i) {
			return getRuleContext(ForUpdateContext.class,i);
		}
		public List<SelectContext> select() {
			return getRuleContexts(SelectContext.class);
		}
		public SelectContext select(int i) {
			return getRuleContext(SelectContext.class,i);
		}
		public List<UnionsContext> unions() {
			return getRuleContexts(UnionsContext.class);
		}
		public UnionsContext unions(int i) {
			return getRuleContext(UnionsContext.class,i);
		}
		public SelectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select; }
	}

	public final SelectContext select() throws RecognitionException {
		return select(0);
	}

	private SelectContext select(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		SelectContext _localctx = new SelectContext(_ctx, _parentState);
		SelectContext _prevctx = _localctx;
		int _startState = 74;
		enterRecursionRule(_localctx, 74, RULE_select, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1245);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__43:
			case T__85:
			case T__137:
			case T__154:
				{
				setState(1228);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__85) {
					{
					setState(1227);
					with();
					}
				}

				setState(1230);
				selectCore();
				setState(1238);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,194,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						setState(1236);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case T__361:
							{
							setState(1231);
							orderBy();
							}
							break;
						case T__169:
							{
							setState(1232);
							offset();
							}
							break;
						case T__171:
							{
							setState(1233);
							fetch();
							}
							break;
						case T__170:
							{
							setState(1234);
							limit();
							}
							break;
						case T__98:
							{
							setState(1235);
							forUpdate();
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						} 
					}
					setState(1240);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,194,_ctx);
				}
				}
				break;
			case T__16:
				{
				setState(1241);
				match(T__16);
				setState(1242);
				select(0);
				setState(1243);
				match(T__17);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(1257);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,197,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new SelectContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_select);
					setState(1247);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(1251); 
					_errHandler.sync(this);
					_alt = 1;
					do {
						switch (_alt) {
						case 1:
							{
							{
							setState(1248);
							unions();
							setState(1249);
							select(0);
							}
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(1253); 
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,196,_ctx);
					} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
					}
					} 
				}
				setState(1259);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,197,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SelectCoreContext extends ParserRuleContext {
		public QuantifierContext quantifier() {
			return getRuleContext(QuantifierContext.class,0);
		}
		public TopContext top() {
			return getRuleContext(TopContext.class,0);
		}
		public List<ItemContext> item() {
			return getRuleContexts(ItemContext.class);
		}
		public ItemContext item(int i) {
			return getRuleContext(ItemContext.class,i);
		}
		public IntoContext into() {
			return getRuleContext(IntoContext.class,0);
		}
		public SourcesContext sources() {
			return getRuleContext(SourcesContext.class,0);
		}
		public WhereContext where() {
			return getRuleContext(WhereContext.class,0);
		}
		public GroupByContext groupBy() {
			return getRuleContext(GroupByContext.class,0);
		}
		public HavingContext having() {
			return getRuleContext(HavingContext.class,0);
		}
		public WindowsContext windows() {
			return getRuleContext(WindowsContext.class,0);
		}
		public QualifyContext qualify() {
			return getRuleContext(QualifyContext.class,0);
		}
		public TermsContext terms() {
			return getRuleContext(TermsContext.class,0);
		}
		public TableContext table() {
			return getRuleContext(TableContext.class,0);
		}
		public SelectCoreContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectCore; }
	}

	public final SelectCoreContext selectCore() throws RecognitionException {
		SelectCoreContext _localctx = new SelectCoreContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_selectCore);
		try {
			int _alt;
			setState(1306);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__154:
				enterOuterAlt(_localctx, 1);
				{
				setState(1260);
				match(T__154);
				setState(1262);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,198,_ctx) ) {
				case 1:
					{
					setState(1261);
					quantifier();
					}
					break;
				}
				setState(1265);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,199,_ctx) ) {
				case 1:
					{
					setState(1264);
					top();
					}
					break;
				}
				setState(1278);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,202,_ctx) ) {
				case 1:
					{
					setState(1267);
					item();
					setState(1272);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,200,_ctx);
					while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1 ) {
							{
							{
							setState(1268);
							match(T__28);
							setState(1269);
							item();
							}
							} 
						}
						setState(1274);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,200,_ctx);
					}
					setState(1276);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,201,_ctx) ) {
					case 1:
						{
						setState(1275);
						match(T__28);
						}
						break;
					}
					}
					break;
				}
				setState(1281);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,203,_ctx) ) {
				case 1:
					{
					setState(1280);
					into();
					}
					break;
				}
				setState(1285);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,204,_ctx) ) {
				case 1:
					{
					setState(1283);
					match(T__135);
					setState(1284);
					sources(0);
					}
					break;
				}
				setState(1288);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,205,_ctx) ) {
				case 1:
					{
					setState(1287);
					where();
					}
					break;
				}
				setState(1291);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,206,_ctx) ) {
				case 1:
					{
					setState(1290);
					groupBy();
					}
					break;
				}
				setState(1294);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,207,_ctx) ) {
				case 1:
					{
					setState(1293);
					having();
					}
					break;
				}
				setState(1297);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,208,_ctx) ) {
				case 1:
					{
					setState(1296);
					windows();
					}
					break;
				}
				setState(1300);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,209,_ctx) ) {
				case 1:
					{
					setState(1299);
					qualify();
					}
					break;
				}
				}
				break;
			case T__137:
				enterOuterAlt(_localctx, 2);
				{
				setState(1302);
				match(T__137);
				setState(1303);
				terms();
				}
				break;
			case T__43:
				enterOuterAlt(_localctx, 3);
				{
				setState(1304);
				match(T__43);
				setState(1305);
				table();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UnionsContext extends ParserRuleContext {
		public UnionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unions; }
	}

	public final UnionsContext unions() throws RecognitionException {
		UnionsContext _localctx = new UnionsContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_unions);
		int _la;
		try {
			setState(1316);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__155:
				enterOuterAlt(_localctx, 1);
				{
				setState(1308);
				match(T__155);
				}
				break;
			case T__156:
				enterOuterAlt(_localctx, 2);
				{
				setState(1309);
				match(T__156);
				}
				break;
			case T__157:
				enterOuterAlt(_localctx, 3);
				{
				setState(1310);
				match(T__157);
				setState(1312);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__158) {
					{
					setState(1311);
					match(T__158);
					}
				}

				}
				break;
			case T__159:
				enterOuterAlt(_localctx, 4);
				{
				setState(1314);
				match(T__159);
				}
				break;
			case T__160:
				enterOuterAlt(_localctx, 5);
				{
				setState(1315);
				match(T__160);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SourcesContext extends ParserRuleContext {
		public SourceContext source() {
			return getRuleContext(SourceContext.class,0);
		}
		public List<SourcesContext> sources() {
			return getRuleContexts(SourcesContext.class);
		}
		public SourcesContext sources(int i) {
			return getRuleContext(SourcesContext.class,i);
		}
		public List<JoinContext> join() {
			return getRuleContexts(JoinContext.class);
		}
		public JoinContext join(int i) {
			return getRuleContext(JoinContext.class,i);
		}
		public List<PivotContext> pivot() {
			return getRuleContexts(PivotContext.class);
		}
		public PivotContext pivot(int i) {
			return getRuleContext(PivotContext.class,i);
		}
		public List<UnpivotContext> unpivot() {
			return getRuleContexts(UnpivotContext.class);
		}
		public UnpivotContext unpivot(int i) {
			return getRuleContext(UnpivotContext.class,i);
		}
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public List<ColumnsContext> columns() {
			return getRuleContexts(ColumnsContext.class);
		}
		public ColumnsContext columns(int i) {
			return getRuleContext(ColumnsContext.class,i);
		}
		public SourcesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sources; }
	}

	public final SourcesContext sources() throws RecognitionException {
		return sources(0);
	}

	private SourcesContext sources(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		SourcesContext _localctx = new SourcesContext(_ctx, _parentState);
		SourcesContext _prevctx = _localctx;
		int _startState = 80;
		enterRecursionRule(_localctx, 80, RULE_sources, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1324);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,213,_ctx) ) {
			case 1:
				{
				setState(1319);
				source();
				}
				break;
			case 2:
				{
				setState(1320);
				match(T__16);
				setState(1321);
				sources(0);
				setState(1322);
				match(T__17);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(1346);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,218,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new SourcesContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_sources);
					setState(1326);
					if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
					setState(1340); 
					_errHandler.sync(this);
					_alt = 1;
					do {
						switch (_alt) {
						case 1:
							{
							setState(1340);
							_errHandler.sync(this);
							switch (_input.LA(1)) {
							case T__28:
							case T__124:
							case T__183:
							case T__184:
							case T__185:
							case T__186:
							case T__187:
							case T__188:
							case T__189:
								{
								setState(1327);
								join();
								setState(1328);
								sources(0);
								setState(1335);
								_errHandler.sync(this);
								_alt = getInterpreter().adaptivePredict(_input,215,_ctx);
								while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
									if ( _alt==1 ) {
										{
										setState(1333);
										_errHandler.sync(this);
										switch (_input.LA(1)) {
										case T__97:
											{
											setState(1329);
											match(T__97);
											setState(1330);
											term(0);
											}
											break;
										case T__89:
											{
											setState(1331);
											match(T__89);
											setState(1332);
											columns();
											}
											break;
										default:
											throw new NoViableAltException(this);
										}
										} 
									}
									setState(1337);
									_errHandler.sync(this);
									_alt = getInterpreter().adaptivePredict(_input,215,_ctx);
								}
								}
								break;
							case T__190:
								{
								setState(1338);
								pivot();
								}
								break;
							case T__193:
								{
								setState(1339);
								unpivot();
								}
								break;
							default:
								throw new NoViableAltException(this);
							}
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(1342); 
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,217,_ctx);
					} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
					}
					} 
				}
				setState(1348);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,218,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SourceContext extends ParserRuleContext {
		public TableContext table() {
			return getRuleContext(TableContext.class,0);
		}
		public AliasContext alias() {
			return getRuleContext(AliasContext.class,0);
		}
		public List<NamesContext> names() {
			return getRuleContexts(NamesContext.class);
		}
		public NamesContext names(int i) {
			return getRuleContext(NamesContext.class,i);
		}
		public QnameContext qname() {
			return getRuleContext(QnameContext.class,0);
		}
		public SelectContext select() {
			return getRuleContext(SelectContext.class,0);
		}
		public SourceContext source() {
			return getRuleContext(SourceContext.class,0);
		}
		public TableFuncContext tableFunc() {
			return getRuleContext(TableFuncContext.class,0);
		}
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public XmlTableContext xmlTable() {
			return getRuleContext(XmlTableContext.class,0);
		}
		public FunctionContext function() {
			return getRuleContext(FunctionContext.class,0);
		}
		public UnnestContext unnest() {
			return getRuleContext(UnnestContext.class,0);
		}
		public DeleteContext delete() {
			return getRuleContext(DeleteContext.class,0);
		}
		public InsertContext insert() {
			return getRuleContext(InsertContext.class,0);
		}
		public MergeContext merge() {
			return getRuleContext(MergeContext.class,0);
		}
		public UpdateContext update() {
			return getRuleContext(UpdateContext.class,0);
		}
		public SourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_source; }
	}

	public final SourceContext source() throws RecognitionException {
		SourceContext _localctx = new SourceContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_source);
		int _la;
		try {
			setState(1408);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,229,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1349);
				table();
				setState(1357);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,221,_ctx) ) {
				case 1:
					{
					setState(1351);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,219,_ctx) ) {
					case 1:
						{
						setState(1350);
						match(T__5);
						}
						break;
					}
					setState(1353);
					alias();
					setState(1355);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,220,_ctx) ) {
					case 1:
						{
						setState(1354);
						names();
						}
						break;
					}
					}
					break;
				}
				setState(1369);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,223,_ctx) ) {
				case 1:
					{
					{
					setState(1359);
					match(T__161);
					setState(1360);
					match(T__64);
					setState(1361);
					names();
					}
					}
					break;
				case 2:
					{
					setState(1367);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case T__107:
						{
						setState(1362);
						match(T__107);
						setState(1363);
						match(T__151);
						}
						break;
					case T__151:
						{
						setState(1364);
						match(T__151);
						setState(1365);
						match(T__133);
						setState(1366);
						qname();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					break;
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1397);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,225,_ctx) ) {
				case 1:
					{
					setState(1371);
					select(0);
					}
					break;
				case 2:
					{
					setState(1372);
					match(T__16);
					setState(1373);
					source();
					setState(1374);
					match(T__17);
					}
					break;
				case 3:
					{
					setState(1376);
					tableFunc();
					}
					break;
				case 4:
					{
					setState(1377);
					_la = _input.LA(1);
					if ( !(((((_la - 163)) & ~0x3f) == 0 && ((1L << (_la - 163)) & 7L) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(1378);
					match(T__43);
					setState(1379);
					match(T__16);
					setState(1384);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,224,_ctx) ) {
					case 1:
						{
						setState(1380);
						delete();
						}
						break;
					case 2:
						{
						setState(1381);
						insert();
						}
						break;
					case 3:
						{
						setState(1382);
						merge();
						}
						break;
					case 4:
						{
						setState(1383);
						update();
						}
						break;
					}
					setState(1386);
					match(T__17);
					}
					break;
				case 5:
					{
					setState(1388);
					match(T__43);
					setState(1389);
					match(T__16);
					setState(1390);
					term(0);
					setState(1391);
					match(T__17);
					}
					break;
				case 6:
					{
					setState(1393);
					match(T__165);
					}
					break;
				case 7:
					{
					setState(1394);
					xmlTable();
					}
					break;
				case 8:
					{
					setState(1395);
					function();
					}
					break;
				case 9:
					{
					setState(1396);
					unnest();
					}
					break;
				}
				setState(1406);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,228,_ctx) ) {
				case 1:
					{
					setState(1400);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,226,_ctx) ) {
					case 1:
						{
						setState(1399);
						match(T__5);
						}
						break;
					}
					setState(1402);
					alias();
					setState(1404);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,227,_ctx) ) {
					case 1:
						{
						setState(1403);
						names();
						}
						break;
					}
					}
					break;
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UnnestContext extends ParserRuleContext {
		public List<ArrayContext> array() {
			return getRuleContexts(ArrayContext.class);
		}
		public ArrayContext array(int i) {
			return getRuleContext(ArrayContext.class,i);
		}
		public UnnestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unnest; }
	}

	public final UnnestContext unnest() throws RecognitionException {
		UnnestContext _localctx = new UnnestContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_unnest);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1410);
			match(T__166);
			setState(1411);
			match(T__16);
			setState(1412);
			array();
			setState(1417);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__28) {
				{
				{
				setState(1413);
				match(T__28);
				setState(1414);
				array();
				}
				}
				setState(1419);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1420);
			match(T__17);
			setState(1423);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,231,_ctx) ) {
			case 1:
				{
				setState(1421);
				match(T__85);
				setState(1422);
				match(T__167);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TableFuncContext extends ParserRuleContext {
		public List<TableFuncParamContext> tableFuncParam() {
			return getRuleContexts(TableFuncParamContext.class);
		}
		public TableFuncParamContext tableFuncParam(int i) {
			return getRuleContext(TableFuncParamContext.class,i);
		}
		public TableFuncContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableFunc; }
	}

	public final TableFuncContext tableFunc() throws RecognitionException {
		TableFuncContext _localctx = new TableFuncContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_tableFunc);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1425);
			_la = _input.LA(1);
			if ( !(_la==T__43 || _la==T__168) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(1426);
			match(T__16);
			setState(1427);
			tableFuncParam();
			setState(1432);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__28) {
				{
				{
				setState(1428);
				match(T__28);
				setState(1429);
				tableFuncParam();
				}
				}
				setState(1434);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1435);
			match(T__17);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TableFuncParamContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public SubtermContext subterm() {
			return getRuleContext(SubtermContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public QnameContext qname() {
			return getRuleContext(QnameContext.class,0);
		}
		public TableFuncParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableFuncParam; }
	}

	public final TableFuncParamContext tableFuncParam() throws RecognitionException {
		TableFuncParamContext _localctx = new TableFuncParamContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_tableFuncParam);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1437);
			name();
			setState(1441);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,233,_ctx) ) {
			case 1:
				{
				setState(1438);
				type(0);
				}
				break;
			case 2:
				{
				setState(1439);
				qname();
				}
				break;
			case 3:
				{
				setState(1440);
				match(T__112);
				}
				break;
			}
			setState(1443);
			match(T__15);
			setState(1444);
			subterm(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OffsetContext extends ParserRuleContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public RowRowsContext rowRows() {
			return getRuleContext(RowRowsContext.class,0);
		}
		public OffsetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_offset; }
	}

	public final OffsetContext offset() throws RecognitionException {
		OffsetContext _localctx = new OffsetContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_offset);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1446);
			match(T__169);
			setState(1447);
			term(0);
			setState(1449);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,234,_ctx) ) {
			case 1:
				{
				setState(1448);
				rowRows();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LimitContext extends ParserRuleContext {
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public LimitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_limit; }
	}

	public final LimitContext limit() throws RecognitionException {
		LimitContext _localctx = new LimitContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_limit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1451);
			match(T__170);
			setState(1452);
			term(0);
			setState(1455);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,235,_ctx) ) {
			case 1:
				{
				setState(1453);
				match(T__28);
				setState(1454);
				term(0);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FetchContext extends ParserRuleContext {
		public RowRowsContext rowRows() {
			return getRuleContext(RowRowsContext.class,0);
		}
		public WithTiesContext withTies() {
			return getRuleContext(WithTiesContext.class,0);
		}
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public FetchContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fetch; }
	}

	public final FetchContext fetch() throws RecognitionException {
		FetchContext _localctx = new FetchContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_fetch);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1457);
			match(T__171);
			setState(1458);
			_la = _input.LA(1);
			if ( !(_la==T__132 || _la==T__172) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(1463);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,237,_ctx) ) {
			case 1:
				{
				setState(1459);
				term(0);
				setState(1461);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__173) {
					{
					setState(1460);
					match(T__173);
					}
				}

				}
				break;
			}
			setState(1465);
			rowRows();
			setState(1468);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__136:
				{
				setState(1466);
				match(T__136);
				}
				break;
			case T__85:
				{
				setState(1467);
				withTies();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ForUpdateContext extends ParserRuleContext {
		public List<QnameContext> qname() {
			return getRuleContexts(QnameContext.class);
		}
		public QnameContext qname(int i) {
			return getRuleContext(QnameContext.class,i);
		}
		public TerminalNode INTEGER() { return getToken(NormalSQLParser.INTEGER, 0); }
		public ForUpdateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forUpdate; }
	}

	public final ForUpdateContext forUpdate() throws RecognitionException {
		ForUpdateContext _localctx = new ForUpdateContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_forUpdate);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1470);
			match(T__98);
			setState(1482);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__174:
				{
				setState(1471);
				match(T__174);
				setState(1472);
				match(T__136);
				}
				break;
			case T__86:
			case T__96:
				{
				setState(1475);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__86) {
					{
					setState(1473);
					match(T__86);
					setState(1474);
					match(T__110);
					}
				}

				setState(1477);
				match(T__96);
				}
				break;
			case T__110:
			case T__175:
				{
				setState(1479);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__110) {
					{
					setState(1478);
					match(T__110);
					}
				}

				setState(1481);
				match(T__175);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(1493);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,243,_ctx) ) {
			case 1:
				{
				setState(1484);
				match(T__93);
				setState(1485);
				qname();
				setState(1490);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,242,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1486);
						match(T__28);
						setState(1487);
						qname();
						}
						} 
					}
					setState(1492);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,242,_ctx);
				}
				}
				break;
			}
			setState(1500);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,244,_ctx) ) {
			case 1:
				{
				setState(1495);
				match(T__176);
				}
				break;
			case 2:
				{
				setState(1496);
				match(T__177);
				setState(1497);
				match(INTEGER);
				}
				break;
			case 3:
				{
				setState(1498);
				match(T__178);
				setState(1499);
				match(T__179);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QuantifierContext extends ParserRuleContext {
		public TermsContext terms() {
			return getRuleContext(TermsContext.class,0);
		}
		public QuantifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quantifier; }
	}

	public final QuantifierContext quantifier() throws RecognitionException {
		QuantifierContext _localctx = new QuantifierContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_quantifier);
		try {
			setState(1511);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__180:
				enterOuterAlt(_localctx, 1);
				{
				setState(1502);
				match(T__180);
				setState(1508);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,245,_ctx) ) {
				case 1:
					{
					setState(1503);
					match(T__97);
					setState(1504);
					match(T__16);
					setState(1505);
					terms();
					setState(1506);
					match(T__17);
					}
					break;
				}
				}
				break;
			case T__158:
				enterOuterAlt(_localctx, 2);
				{
				setState(1510);
				match(T__158);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TopContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(NormalSQLParser.INTEGER, 0); }
		public TerminalNode FLOAT() { return getToken(NormalSQLParser.FLOAT, 0); }
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public WithTiesContext withTies() {
			return getRuleContext(WithTiesContext.class,0);
		}
		public TopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_top; }
	}

	public final TopContext top() throws RecognitionException {
		TopContext _localctx = new TopContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_top);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1513);
			match(T__181);
			setState(1520);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTEGER:
				{
				setState(1514);
				match(INTEGER);
				}
				break;
			case FLOAT:
				{
				setState(1515);
				match(FLOAT);
				}
				break;
			case T__16:
				{
				setState(1516);
				match(T__16);
				setState(1517);
				term(0);
				setState(1518);
				match(T__17);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(1523);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,248,_ctx) ) {
			case 1:
				{
				setState(1522);
				match(T__173);
				}
				break;
			}
			setState(1526);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,249,_ctx) ) {
			case 1:
				{
				setState(1525);
				withTies();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ItemContext extends ParserRuleContext {
		public ItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_item; }
	 
		public ItemContext() { }
		public void copyFrom(ItemContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ItemTermContext extends ItemContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public AliasContext alias() {
			return getRuleContext(AliasContext.class,0);
		}
		public ItemTermContext(ItemContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ItemTableRefContext extends ItemContext {
		public ColumnsContext columns() {
			return getRuleContext(ColumnsContext.class,0);
		}
		public TableContext table() {
			return getRuleContext(TableContext.class,0);
		}
		public ItemTableRefContext(ItemContext ctx) { copyFrom(ctx); }
	}

	public final ItemContext item() throws RecognitionException {
		ItemContext _localctx = new ItemContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_item);
		int _la;
		try {
			setState(1546);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,254,_ctx) ) {
			case 1:
				_localctx = new ItemTableRefContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(1531);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -4620693218219474948L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & -23672811834834945L) != 0) || ((((_la - 128)) & ~0x3f) == 0 && ((1L << (_la - 128)) & -4656726419123471361L) != 0) || ((((_la - 193)) & ~0x3f) == 0 && ((1L << (_la - 193)) & -1522243062332412065L) != 0) || ((((_la - 257)) & ~0x3f) == 0 && ((1L << (_la - 257)) & -4035225266149656561L) != 0) || ((((_la - 321)) & ~0x3f) == 0 && ((1L << (_la - 321)) & -562958543355905L) != 0) || ((((_la - 385)) & ~0x3f) == 0 && ((1L << (_la - 385)) & 67677712383L) != 0)) {
					{
					setState(1528);
					table();
					setState(1529);
					match(T__182);
					}
				}

				setState(1533);
				match(T__153);
				}
				setState(1537);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,251,_ctx) ) {
				case 1:
					{
					setState(1535);
					match(T__159);
					setState(1536);
					columns();
					}
					break;
				}
				}
				break;
			case 2:
				_localctx = new ItemTermContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(1539);
				term(0);
				setState(1544);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,253,_ctx) ) {
				case 1:
					{
					setState(1541);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,252,_ctx) ) {
					case 1:
						{
						setState(1540);
						match(T__5);
						}
						break;
					}
					setState(1543);
					alias();
					}
					break;
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IntoContext extends ParserRuleContext {
		public List<QnameContext> qname() {
			return getRuleContexts(QnameContext.class);
		}
		public QnameContext qname(int i) {
			return getRuleContext(QnameContext.class,i);
		}
		public List<TerminalNode> VARIABLE() { return getTokens(NormalSQLParser.VARIABLE); }
		public TerminalNode VARIABLE(int i) {
			return getToken(NormalSQLParser.VARIABLE, i);
		}
		public TemporaryContext temporary() {
			return getRuleContext(TemporaryContext.class,0);
		}
		public IntoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_into; }
	}

	public final IntoContext into() throws RecognitionException {
		IntoContext _localctx = new IntoContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_into);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1548);
			match(T__25);
			setState(1551);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,255,_ctx) ) {
			case 1:
				{
				setState(1549);
				temporary();
				}
				break;
			case 2:
				{
				setState(1550);
				match(T__83);
				}
				break;
			}
			setState(1554);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,256,_ctx) ) {
			case 1:
				{
				setState(1553);
				match(T__43);
				}
				break;
			}
			setState(1558);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,257,_ctx) ) {
			case 1:
				{
				setState(1556);
				qname();
				}
				break;
			case 2:
				{
				setState(1557);
				match(VARIABLE);
				}
				break;
			}
			setState(1567);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,259,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1560);
					match(T__28);
					setState(1563);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,258,_ctx) ) {
					case 1:
						{
						setState(1561);
						qname();
						}
						break;
					case 2:
						{
						setState(1562);
						match(VARIABLE);
						}
						break;
					}
					}
					} 
				}
				setState(1569);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,259,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class JoinContext extends ParserRuleContext {
		public JoinContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_join; }
	}

	public final JoinContext join() throws RecognitionException {
		JoinContext _localctx = new JoinContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_join);
		int _la;
		try {
			setState(1584);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__28:
				enterOuterAlt(_localctx, 1);
				{
				setState(1570);
				match(T__28);
				}
				break;
			case T__124:
			case T__183:
			case T__184:
			case T__185:
			case T__186:
			case T__187:
			case T__188:
			case T__189:
				enterOuterAlt(_localctx, 2);
				{
				setState(1572);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__183) {
					{
					setState(1571);
					match(T__183);
					}
				}

				setState(1581);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__124:
				case T__184:
				case T__185:
				case T__186:
					{
					setState(1575); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(1574);
						_la = _input.LA(1);
						if ( !(((((_la - 125)) & ~0x3f) == 0 && ((1L << (_la - 125)) & 8070450532247928833L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						}
						setState(1577); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( ((((_la - 125)) & ~0x3f) == 0 && ((1L << (_la - 125)) & 8070450532247928833L) != 0) );
					}
					break;
				case T__187:
					{
					setState(1579);
					match(T__187);
					}
					break;
				case T__188:
					{
					setState(1580);
					match(T__188);
					}
					break;
				case T__189:
					break;
				default:
					break;
				}
				setState(1583);
				match(T__189);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PivotContext extends ParserRuleContext {
		public List<AliasedFunctionContext> aliasedFunction() {
			return getRuleContexts(AliasedFunctionContext.class);
		}
		public AliasedFunctionContext aliasedFunction(int i) {
			return getRuleContext(AliasedFunctionContext.class,i);
		}
		public AliasedTermsContext aliasedTerms() {
			return getRuleContext(AliasedTermsContext.class,0);
		}
		public ColumnContext column() {
			return getRuleContext(ColumnContext.class,0);
		}
		public ColumnsContext columns() {
			return getRuleContext(ColumnsContext.class,0);
		}
		public SelectContext select() {
			return getRuleContext(SelectContext.class,0);
		}
		public PivotContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pivot; }
	}

	public final PivotContext pivot() throws RecognitionException {
		PivotContext _localctx = new PivotContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_pivot);
		int _la;
		try {
			setState(1632);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,269,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1586);
				match(T__190);
				setState(1587);
				match(T__16);
				setState(1588);
				aliasedFunction();
				setState(1593);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__28) {
					{
					{
					setState(1589);
					match(T__28);
					setState(1590);
					aliasedFunction();
					}
					}
					setState(1595);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1596);
				match(T__98);
				setState(1599);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__1:
				case T__2:
				case T__3:
				case T__4:
				case T__5:
				case T__6:
				case T__7:
				case T__8:
				case T__9:
				case T__10:
				case T__11:
				case T__12:
				case T__14:
				case T__18:
				case T__19:
				case T__20:
				case T__21:
				case T__22:
				case T__23:
				case T__24:
				case T__25:
				case T__26:
				case T__27:
				case T__29:
				case T__30:
				case T__31:
				case T__32:
				case T__33:
				case T__34:
				case T__35:
				case T__36:
				case T__37:
				case T__38:
				case T__39:
				case T__40:
				case T__41:
				case T__42:
				case T__43:
				case T__44:
				case T__45:
				case T__46:
				case T__47:
				case T__48:
				case T__49:
				case T__50:
				case T__51:
				case T__53:
				case T__54:
				case T__55:
				case T__56:
				case T__57:
				case T__58:
				case T__59:
				case T__60:
				case T__62:
				case T__63:
				case T__64:
				case T__65:
				case T__66:
				case T__67:
				case T__68:
				case T__69:
				case T__70:
				case T__71:
				case T__72:
				case T__73:
				case T__74:
				case T__75:
				case T__76:
				case T__77:
				case T__78:
				case T__79:
				case T__80:
				case T__81:
				case T__82:
				case T__83:
				case T__84:
				case T__86:
				case T__87:
				case T__88:
				case T__90:
				case T__91:
				case T__92:
				case T__93:
				case T__94:
				case T__95:
				case T__96:
				case T__99:
				case T__100:
				case T__102:
				case T__103:
				case T__105:
				case T__108:
				case T__109:
				case T__110:
				case T__111:
				case T__112:
				case T__114:
				case T__116:
				case T__118:
				case T__119:
				case T__120:
				case T__121:
				case T__122:
				case T__123:
				case T__124:
				case T__125:
				case T__126:
				case T__127:
				case T__128:
				case T__129:
				case T__130:
				case T__131:
				case T__132:
				case T__133:
				case T__134:
				case T__135:
				case T__136:
				case T__138:
				case T__139:
				case T__140:
				case T__141:
				case T__142:
				case T__143:
				case T__144:
				case T__145:
				case T__147:
				case T__148:
				case T__149:
				case T__150:
				case T__151:
				case T__152:
				case T__158:
				case T__160:
				case T__161:
				case T__162:
				case T__163:
				case T__164:
				case T__165:
				case T__166:
				case T__167:
				case T__168:
				case T__170:
				case T__171:
				case T__172:
				case T__173:
				case T__174:
				case T__175:
				case T__176:
				case T__177:
				case T__178:
				case T__179:
				case T__181:
				case T__183:
				case T__184:
				case T__185:
				case T__186:
				case T__187:
				case T__188:
				case T__190:
				case T__192:
				case T__193:
				case T__194:
				case T__195:
				case T__196:
				case T__198:
				case T__200:
				case T__201:
				case T__202:
				case T__203:
				case T__205:
				case T__207:
				case T__208:
				case T__209:
				case T__210:
				case T__211:
				case T__212:
				case T__214:
				case T__215:
				case T__216:
				case T__217:
				case T__218:
				case T__219:
				case T__220:
				case T__221:
				case T__222:
				case T__223:
				case T__224:
				case T__225:
				case T__226:
				case T__227:
				case T__228:
				case T__229:
				case T__230:
				case T__231:
				case T__232:
				case T__233:
				case T__234:
				case T__237:
				case T__238:
				case T__239:
				case T__240:
				case T__241:
				case T__242:
				case T__243:
				case T__244:
				case T__246:
				case T__247:
				case T__249:
				case T__251:
				case T__253:
				case T__254:
				case T__255:
				case T__256:
				case T__257:
				case T__258:
				case T__259:
				case T__267:
				case T__268:
				case T__269:
				case T__270:
				case T__271:
				case T__272:
				case T__273:
				case T__274:
				case T__276:
				case T__277:
				case T__278:
				case T__281:
				case T__282:
				case T__283:
				case T__284:
				case T__285:
				case T__286:
				case T__287:
				case T__288:
				case T__289:
				case T__290:
				case T__291:
				case T__292:
				case T__293:
				case T__294:
				case T__295:
				case T__296:
				case T__297:
				case T__298:
				case T__299:
				case T__300:
				case T__301:
				case T__302:
				case T__303:
				case T__304:
				case T__305:
				case T__306:
				case T__307:
				case T__308:
				case T__309:
				case T__310:
				case T__311:
				case T__312:
				case T__313:
				case T__314:
				case T__318:
				case T__319:
				case T__320:
				case T__321:
				case T__322:
				case T__323:
				case T__324:
				case T__325:
				case T__326:
				case T__327:
				case T__328:
				case T__329:
				case T__330:
				case T__331:
				case T__332:
				case T__333:
				case T__334:
				case T__335:
				case T__336:
				case T__337:
				case T__338:
				case T__339:
				case T__340:
				case T__341:
				case T__342:
				case T__343:
				case T__344:
				case T__345:
				case T__346:
				case T__347:
				case T__348:
				case T__349:
				case T__350:
				case T__351:
				case T__352:
				case T__354:
				case T__355:
				case T__356:
				case T__357:
				case T__358:
				case T__359:
				case T__360:
				case T__361:
				case T__362:
				case T__363:
				case T__364:
				case T__365:
				case T__366:
				case T__367:
				case T__368:
				case T__370:
				case T__371:
				case T__372:
				case T__373:
				case T__374:
				case T__375:
				case T__376:
				case T__377:
				case T__378:
				case T__379:
				case T__380:
				case T__381:
				case T__382:
				case T__383:
				case T__384:
				case T__385:
				case T__386:
				case T__387:
				case T__388:
				case T__389:
				case T__390:
				case T__391:
				case T__392:
				case T__393:
				case T__394:
				case T__395:
				case T__397:
				case T__398:
				case T__399:
				case T__400:
				case T__401:
				case UNICODE_NAME:
				case STRING:
				case ID:
				case BRACKETS:
				case DOLLARS:
				case PARAMETER:
				case VARIABLE:
				case COMMENT:
				case BLOCK_COMMENT:
				case WHITESPACE:
				case OPERATOR:
					{
					setState(1597);
					column();
					}
					break;
				case T__16:
					{
					setState(1598);
					columns();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1601);
				match(T__191);
				setState(1602);
				match(T__16);
				setState(1603);
				aliasedTerms();
				setState(1604);
				match(T__17);
				setState(1605);
				match(T__17);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1607);
				match(T__190);
				setState(1608);
				match(T__39);
				setState(1609);
				match(T__16);
				setState(1610);
				aliasedFunction();
				setState(1615);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__28) {
					{
					{
					setState(1611);
					match(T__28);
					setState(1612);
					aliasedFunction();
					}
					}
					setState(1617);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1618);
				match(T__98);
				setState(1621);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__1:
				case T__2:
				case T__3:
				case T__4:
				case T__5:
				case T__6:
				case T__7:
				case T__8:
				case T__9:
				case T__10:
				case T__11:
				case T__12:
				case T__14:
				case T__18:
				case T__19:
				case T__20:
				case T__21:
				case T__22:
				case T__23:
				case T__24:
				case T__25:
				case T__26:
				case T__27:
				case T__29:
				case T__30:
				case T__31:
				case T__32:
				case T__33:
				case T__34:
				case T__35:
				case T__36:
				case T__37:
				case T__38:
				case T__39:
				case T__40:
				case T__41:
				case T__42:
				case T__43:
				case T__44:
				case T__45:
				case T__46:
				case T__47:
				case T__48:
				case T__49:
				case T__50:
				case T__51:
				case T__53:
				case T__54:
				case T__55:
				case T__56:
				case T__57:
				case T__58:
				case T__59:
				case T__60:
				case T__62:
				case T__63:
				case T__64:
				case T__65:
				case T__66:
				case T__67:
				case T__68:
				case T__69:
				case T__70:
				case T__71:
				case T__72:
				case T__73:
				case T__74:
				case T__75:
				case T__76:
				case T__77:
				case T__78:
				case T__79:
				case T__80:
				case T__81:
				case T__82:
				case T__83:
				case T__84:
				case T__86:
				case T__87:
				case T__88:
				case T__90:
				case T__91:
				case T__92:
				case T__93:
				case T__94:
				case T__95:
				case T__96:
				case T__99:
				case T__100:
				case T__102:
				case T__103:
				case T__105:
				case T__108:
				case T__109:
				case T__110:
				case T__111:
				case T__112:
				case T__114:
				case T__116:
				case T__118:
				case T__119:
				case T__120:
				case T__121:
				case T__122:
				case T__123:
				case T__124:
				case T__125:
				case T__126:
				case T__127:
				case T__128:
				case T__129:
				case T__130:
				case T__131:
				case T__132:
				case T__133:
				case T__134:
				case T__135:
				case T__136:
				case T__138:
				case T__139:
				case T__140:
				case T__141:
				case T__142:
				case T__143:
				case T__144:
				case T__145:
				case T__147:
				case T__148:
				case T__149:
				case T__150:
				case T__151:
				case T__152:
				case T__158:
				case T__160:
				case T__161:
				case T__162:
				case T__163:
				case T__164:
				case T__165:
				case T__166:
				case T__167:
				case T__168:
				case T__170:
				case T__171:
				case T__172:
				case T__173:
				case T__174:
				case T__175:
				case T__176:
				case T__177:
				case T__178:
				case T__179:
				case T__181:
				case T__183:
				case T__184:
				case T__185:
				case T__186:
				case T__187:
				case T__188:
				case T__190:
				case T__192:
				case T__193:
				case T__194:
				case T__195:
				case T__196:
				case T__198:
				case T__200:
				case T__201:
				case T__202:
				case T__203:
				case T__205:
				case T__207:
				case T__208:
				case T__209:
				case T__210:
				case T__211:
				case T__212:
				case T__214:
				case T__215:
				case T__216:
				case T__217:
				case T__218:
				case T__219:
				case T__220:
				case T__221:
				case T__222:
				case T__223:
				case T__224:
				case T__225:
				case T__226:
				case T__227:
				case T__228:
				case T__229:
				case T__230:
				case T__231:
				case T__232:
				case T__233:
				case T__234:
				case T__237:
				case T__238:
				case T__239:
				case T__240:
				case T__241:
				case T__242:
				case T__243:
				case T__244:
				case T__246:
				case T__247:
				case T__249:
				case T__251:
				case T__253:
				case T__254:
				case T__255:
				case T__256:
				case T__257:
				case T__258:
				case T__259:
				case T__267:
				case T__268:
				case T__269:
				case T__270:
				case T__271:
				case T__272:
				case T__273:
				case T__274:
				case T__276:
				case T__277:
				case T__278:
				case T__281:
				case T__282:
				case T__283:
				case T__284:
				case T__285:
				case T__286:
				case T__287:
				case T__288:
				case T__289:
				case T__290:
				case T__291:
				case T__292:
				case T__293:
				case T__294:
				case T__295:
				case T__296:
				case T__297:
				case T__298:
				case T__299:
				case T__300:
				case T__301:
				case T__302:
				case T__303:
				case T__304:
				case T__305:
				case T__306:
				case T__307:
				case T__308:
				case T__309:
				case T__310:
				case T__311:
				case T__312:
				case T__313:
				case T__314:
				case T__318:
				case T__319:
				case T__320:
				case T__321:
				case T__322:
				case T__323:
				case T__324:
				case T__325:
				case T__326:
				case T__327:
				case T__328:
				case T__329:
				case T__330:
				case T__331:
				case T__332:
				case T__333:
				case T__334:
				case T__335:
				case T__336:
				case T__337:
				case T__338:
				case T__339:
				case T__340:
				case T__341:
				case T__342:
				case T__343:
				case T__344:
				case T__345:
				case T__346:
				case T__347:
				case T__348:
				case T__349:
				case T__350:
				case T__351:
				case T__352:
				case T__354:
				case T__355:
				case T__356:
				case T__357:
				case T__358:
				case T__359:
				case T__360:
				case T__361:
				case T__362:
				case T__363:
				case T__364:
				case T__365:
				case T__366:
				case T__367:
				case T__368:
				case T__370:
				case T__371:
				case T__372:
				case T__373:
				case T__374:
				case T__375:
				case T__376:
				case T__377:
				case T__378:
				case T__379:
				case T__380:
				case T__381:
				case T__382:
				case T__383:
				case T__384:
				case T__385:
				case T__386:
				case T__387:
				case T__388:
				case T__389:
				case T__390:
				case T__391:
				case T__392:
				case T__393:
				case T__394:
				case T__395:
				case T__397:
				case T__398:
				case T__399:
				case T__400:
				case T__401:
				case UNICODE_NAME:
				case STRING:
				case ID:
				case BRACKETS:
				case DOLLARS:
				case PARAMETER:
				case VARIABLE:
				case COMMENT:
				case BLOCK_COMMENT:
				case WHITESPACE:
				case OPERATOR:
					{
					setState(1619);
					column();
					}
					break;
				case T__16:
					{
					setState(1620);
					columns();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1623);
				match(T__191);
				setState(1624);
				match(T__16);
				setState(1627);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__16:
				case T__43:
				case T__85:
				case T__137:
				case T__154:
					{
					setState(1625);
					select(0);
					}
					break;
				case T__192:
					{
					setState(1626);
					match(T__192);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1629);
				match(T__17);
				setState(1630);
				match(T__17);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AliasedFunctionContext extends ParserRuleContext {
		public FunctionContext function() {
			return getRuleContext(FunctionContext.class,0);
		}
		public AliasContext alias() {
			return getRuleContext(AliasContext.class,0);
		}
		public AliasedFunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aliasedFunction; }
	}

	public final AliasedFunctionContext aliasedFunction() throws RecognitionException {
		AliasedFunctionContext _localctx = new AliasedFunctionContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_aliasedFunction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1634);
			function();
			setState(1639);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -4620693218219474948L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & -23672811834834945L) != 0) || ((((_la - 128)) & ~0x3f) == 0 && ((1L << (_la - 128)) & -4656726419123471361L) != 0) || ((((_la - 193)) & ~0x3f) == 0 && ((1L << (_la - 193)) & -1522243062332412065L) != 0) || ((((_la - 257)) & ~0x3f) == 0 && ((1L << (_la - 257)) & -4035225266149656561L) != 0) || ((((_la - 321)) & ~0x3f) == 0 && ((1L << (_la - 321)) & -562958543355905L) != 0) || ((((_la - 385)) & ~0x3f) == 0 && ((1L << (_la - 385)) & 67679285247L) != 0)) {
				{
				setState(1636);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,270,_ctx) ) {
				case 1:
					{
					setState(1635);
					match(T__5);
					}
					break;
				}
				setState(1638);
				alias();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UnpivotContext extends ParserRuleContext {
		public AliasedTermsContext aliasedTerms() {
			return getRuleContext(AliasedTermsContext.class,0);
		}
		public List<ColumnContext> column() {
			return getRuleContexts(ColumnContext.class);
		}
		public ColumnContext column(int i) {
			return getRuleContext(ColumnContext.class,i);
		}
		public List<ColumnsContext> columns() {
			return getRuleContexts(ColumnsContext.class);
		}
		public ColumnsContext columns(int i) {
			return getRuleContext(ColumnsContext.class,i);
		}
		public UnpivotContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unpivot; }
	}

	public final UnpivotContext unpivot() throws RecognitionException {
		UnpivotContext _localctx = new UnpivotContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_unpivot);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1641);
			match(T__193);
			setState(1644);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__194 || _la==T__195) {
				{
				setState(1642);
				_la = _input.LA(1);
				if ( !(_la==T__194 || _la==T__195) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1643);
				match(T__196);
				}
			}

			setState(1646);
			match(T__16);
			setState(1649);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
			case T__2:
			case T__3:
			case T__4:
			case T__5:
			case T__6:
			case T__7:
			case T__8:
			case T__9:
			case T__10:
			case T__11:
			case T__12:
			case T__14:
			case T__18:
			case T__19:
			case T__20:
			case T__21:
			case T__22:
			case T__23:
			case T__24:
			case T__25:
			case T__26:
			case T__27:
			case T__29:
			case T__30:
			case T__31:
			case T__32:
			case T__33:
			case T__34:
			case T__35:
			case T__36:
			case T__37:
			case T__38:
			case T__39:
			case T__40:
			case T__41:
			case T__42:
			case T__43:
			case T__44:
			case T__45:
			case T__46:
			case T__47:
			case T__48:
			case T__49:
			case T__50:
			case T__51:
			case T__53:
			case T__54:
			case T__55:
			case T__56:
			case T__57:
			case T__58:
			case T__59:
			case T__60:
			case T__62:
			case T__63:
			case T__64:
			case T__65:
			case T__66:
			case T__67:
			case T__68:
			case T__69:
			case T__70:
			case T__71:
			case T__72:
			case T__73:
			case T__74:
			case T__75:
			case T__76:
			case T__77:
			case T__78:
			case T__79:
			case T__80:
			case T__81:
			case T__82:
			case T__83:
			case T__84:
			case T__86:
			case T__87:
			case T__88:
			case T__90:
			case T__91:
			case T__92:
			case T__93:
			case T__94:
			case T__95:
			case T__96:
			case T__99:
			case T__100:
			case T__102:
			case T__103:
			case T__105:
			case T__108:
			case T__109:
			case T__110:
			case T__111:
			case T__112:
			case T__114:
			case T__116:
			case T__118:
			case T__119:
			case T__120:
			case T__121:
			case T__122:
			case T__123:
			case T__124:
			case T__125:
			case T__126:
			case T__127:
			case T__128:
			case T__129:
			case T__130:
			case T__131:
			case T__132:
			case T__133:
			case T__134:
			case T__135:
			case T__136:
			case T__138:
			case T__139:
			case T__140:
			case T__141:
			case T__142:
			case T__143:
			case T__144:
			case T__145:
			case T__147:
			case T__148:
			case T__149:
			case T__150:
			case T__151:
			case T__152:
			case T__158:
			case T__160:
			case T__161:
			case T__162:
			case T__163:
			case T__164:
			case T__165:
			case T__166:
			case T__167:
			case T__168:
			case T__170:
			case T__171:
			case T__172:
			case T__173:
			case T__174:
			case T__175:
			case T__176:
			case T__177:
			case T__178:
			case T__179:
			case T__181:
			case T__183:
			case T__184:
			case T__185:
			case T__186:
			case T__187:
			case T__188:
			case T__190:
			case T__192:
			case T__193:
			case T__194:
			case T__195:
			case T__196:
			case T__198:
			case T__200:
			case T__201:
			case T__202:
			case T__203:
			case T__205:
			case T__207:
			case T__208:
			case T__209:
			case T__210:
			case T__211:
			case T__212:
			case T__214:
			case T__215:
			case T__216:
			case T__217:
			case T__218:
			case T__219:
			case T__220:
			case T__221:
			case T__222:
			case T__223:
			case T__224:
			case T__225:
			case T__226:
			case T__227:
			case T__228:
			case T__229:
			case T__230:
			case T__231:
			case T__232:
			case T__233:
			case T__234:
			case T__237:
			case T__238:
			case T__239:
			case T__240:
			case T__241:
			case T__242:
			case T__243:
			case T__244:
			case T__246:
			case T__247:
			case T__249:
			case T__251:
			case T__253:
			case T__254:
			case T__255:
			case T__256:
			case T__257:
			case T__258:
			case T__259:
			case T__267:
			case T__268:
			case T__269:
			case T__270:
			case T__271:
			case T__272:
			case T__273:
			case T__274:
			case T__276:
			case T__277:
			case T__278:
			case T__281:
			case T__282:
			case T__283:
			case T__284:
			case T__285:
			case T__286:
			case T__287:
			case T__288:
			case T__289:
			case T__290:
			case T__291:
			case T__292:
			case T__293:
			case T__294:
			case T__295:
			case T__296:
			case T__297:
			case T__298:
			case T__299:
			case T__300:
			case T__301:
			case T__302:
			case T__303:
			case T__304:
			case T__305:
			case T__306:
			case T__307:
			case T__308:
			case T__309:
			case T__310:
			case T__311:
			case T__312:
			case T__313:
			case T__314:
			case T__318:
			case T__319:
			case T__320:
			case T__321:
			case T__322:
			case T__323:
			case T__324:
			case T__325:
			case T__326:
			case T__327:
			case T__328:
			case T__329:
			case T__330:
			case T__331:
			case T__332:
			case T__333:
			case T__334:
			case T__335:
			case T__336:
			case T__337:
			case T__338:
			case T__339:
			case T__340:
			case T__341:
			case T__342:
			case T__343:
			case T__344:
			case T__345:
			case T__346:
			case T__347:
			case T__348:
			case T__349:
			case T__350:
			case T__351:
			case T__352:
			case T__354:
			case T__355:
			case T__356:
			case T__357:
			case T__358:
			case T__359:
			case T__360:
			case T__361:
			case T__362:
			case T__363:
			case T__364:
			case T__365:
			case T__366:
			case T__367:
			case T__368:
			case T__370:
			case T__371:
			case T__372:
			case T__373:
			case T__374:
			case T__375:
			case T__376:
			case T__377:
			case T__378:
			case T__379:
			case T__380:
			case T__381:
			case T__382:
			case T__383:
			case T__384:
			case T__385:
			case T__386:
			case T__387:
			case T__388:
			case T__389:
			case T__390:
			case T__391:
			case T__392:
			case T__393:
			case T__394:
			case T__395:
			case T__397:
			case T__398:
			case T__399:
			case T__400:
			case T__401:
			case UNICODE_NAME:
			case STRING:
			case ID:
			case BRACKETS:
			case DOLLARS:
			case PARAMETER:
			case VARIABLE:
			case COMMENT:
			case BLOCK_COMMENT:
			case WHITESPACE:
			case OPERATOR:
				{
				setState(1647);
				column();
				}
				break;
			case T__16:
				{
				setState(1648);
				columns();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(1651);
			match(T__98);
			setState(1654);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
			case T__2:
			case T__3:
			case T__4:
			case T__5:
			case T__6:
			case T__7:
			case T__8:
			case T__9:
			case T__10:
			case T__11:
			case T__12:
			case T__14:
			case T__18:
			case T__19:
			case T__20:
			case T__21:
			case T__22:
			case T__23:
			case T__24:
			case T__25:
			case T__26:
			case T__27:
			case T__29:
			case T__30:
			case T__31:
			case T__32:
			case T__33:
			case T__34:
			case T__35:
			case T__36:
			case T__37:
			case T__38:
			case T__39:
			case T__40:
			case T__41:
			case T__42:
			case T__43:
			case T__44:
			case T__45:
			case T__46:
			case T__47:
			case T__48:
			case T__49:
			case T__50:
			case T__51:
			case T__53:
			case T__54:
			case T__55:
			case T__56:
			case T__57:
			case T__58:
			case T__59:
			case T__60:
			case T__62:
			case T__63:
			case T__64:
			case T__65:
			case T__66:
			case T__67:
			case T__68:
			case T__69:
			case T__70:
			case T__71:
			case T__72:
			case T__73:
			case T__74:
			case T__75:
			case T__76:
			case T__77:
			case T__78:
			case T__79:
			case T__80:
			case T__81:
			case T__82:
			case T__83:
			case T__84:
			case T__86:
			case T__87:
			case T__88:
			case T__90:
			case T__91:
			case T__92:
			case T__93:
			case T__94:
			case T__95:
			case T__96:
			case T__99:
			case T__100:
			case T__102:
			case T__103:
			case T__105:
			case T__108:
			case T__109:
			case T__110:
			case T__111:
			case T__112:
			case T__114:
			case T__116:
			case T__118:
			case T__119:
			case T__120:
			case T__121:
			case T__122:
			case T__123:
			case T__124:
			case T__125:
			case T__126:
			case T__127:
			case T__128:
			case T__129:
			case T__130:
			case T__131:
			case T__132:
			case T__133:
			case T__134:
			case T__135:
			case T__136:
			case T__138:
			case T__139:
			case T__140:
			case T__141:
			case T__142:
			case T__143:
			case T__144:
			case T__145:
			case T__147:
			case T__148:
			case T__149:
			case T__150:
			case T__151:
			case T__152:
			case T__158:
			case T__160:
			case T__161:
			case T__162:
			case T__163:
			case T__164:
			case T__165:
			case T__166:
			case T__167:
			case T__168:
			case T__170:
			case T__171:
			case T__172:
			case T__173:
			case T__174:
			case T__175:
			case T__176:
			case T__177:
			case T__178:
			case T__179:
			case T__181:
			case T__183:
			case T__184:
			case T__185:
			case T__186:
			case T__187:
			case T__188:
			case T__190:
			case T__192:
			case T__193:
			case T__194:
			case T__195:
			case T__196:
			case T__198:
			case T__200:
			case T__201:
			case T__202:
			case T__203:
			case T__205:
			case T__207:
			case T__208:
			case T__209:
			case T__210:
			case T__211:
			case T__212:
			case T__214:
			case T__215:
			case T__216:
			case T__217:
			case T__218:
			case T__219:
			case T__220:
			case T__221:
			case T__222:
			case T__223:
			case T__224:
			case T__225:
			case T__226:
			case T__227:
			case T__228:
			case T__229:
			case T__230:
			case T__231:
			case T__232:
			case T__233:
			case T__234:
			case T__237:
			case T__238:
			case T__239:
			case T__240:
			case T__241:
			case T__242:
			case T__243:
			case T__244:
			case T__246:
			case T__247:
			case T__249:
			case T__251:
			case T__253:
			case T__254:
			case T__255:
			case T__256:
			case T__257:
			case T__258:
			case T__259:
			case T__267:
			case T__268:
			case T__269:
			case T__270:
			case T__271:
			case T__272:
			case T__273:
			case T__274:
			case T__276:
			case T__277:
			case T__278:
			case T__281:
			case T__282:
			case T__283:
			case T__284:
			case T__285:
			case T__286:
			case T__287:
			case T__288:
			case T__289:
			case T__290:
			case T__291:
			case T__292:
			case T__293:
			case T__294:
			case T__295:
			case T__296:
			case T__297:
			case T__298:
			case T__299:
			case T__300:
			case T__301:
			case T__302:
			case T__303:
			case T__304:
			case T__305:
			case T__306:
			case T__307:
			case T__308:
			case T__309:
			case T__310:
			case T__311:
			case T__312:
			case T__313:
			case T__314:
			case T__318:
			case T__319:
			case T__320:
			case T__321:
			case T__322:
			case T__323:
			case T__324:
			case T__325:
			case T__326:
			case T__327:
			case T__328:
			case T__329:
			case T__330:
			case T__331:
			case T__332:
			case T__333:
			case T__334:
			case T__335:
			case T__336:
			case T__337:
			case T__338:
			case T__339:
			case T__340:
			case T__341:
			case T__342:
			case T__343:
			case T__344:
			case T__345:
			case T__346:
			case T__347:
			case T__348:
			case T__349:
			case T__350:
			case T__351:
			case T__352:
			case T__354:
			case T__355:
			case T__356:
			case T__357:
			case T__358:
			case T__359:
			case T__360:
			case T__361:
			case T__362:
			case T__363:
			case T__364:
			case T__365:
			case T__366:
			case T__367:
			case T__368:
			case T__370:
			case T__371:
			case T__372:
			case T__373:
			case T__374:
			case T__375:
			case T__376:
			case T__377:
			case T__378:
			case T__379:
			case T__380:
			case T__381:
			case T__382:
			case T__383:
			case T__384:
			case T__385:
			case T__386:
			case T__387:
			case T__388:
			case T__389:
			case T__390:
			case T__391:
			case T__392:
			case T__393:
			case T__394:
			case T__395:
			case T__397:
			case T__398:
			case T__399:
			case T__400:
			case T__401:
			case UNICODE_NAME:
			case STRING:
			case ID:
			case BRACKETS:
			case DOLLARS:
			case PARAMETER:
			case VARIABLE:
			case COMMENT:
			case BLOCK_COMMENT:
			case WHITESPACE:
			case OPERATOR:
				{
				setState(1652);
				column();
				}
				break;
			case T__16:
				{
				setState(1653);
				columns();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(1656);
			match(T__191);
			setState(1657);
			match(T__16);
			setState(1658);
			aliasedTerms();
			setState(1659);
			match(T__17);
			setState(1660);
			match(T__17);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WhereContext extends ParserRuleContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public WhereContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_where; }
	}

	public final WhereContext where() throws RecognitionException {
		WhereContext _localctx = new WhereContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_where);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1662);
			match(T__197);
			setState(1667);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,275,_ctx) ) {
			case 1:
				{
				setState(1663);
				term(0);
				}
				break;
			case 2:
				{
				setState(1664);
				match(T__198);
				setState(1665);
				match(T__93);
				setState(1666);
				name();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class GroupByContext extends ParserRuleContext {
		public List<GroupByItemContext> groupByItem() {
			return getRuleContexts(GroupByItemContext.class);
		}
		public GroupByItemContext groupByItem(int i) {
			return getRuleContext(GroupByItemContext.class,i);
		}
		public AllDistinctContext allDistinct() {
			return getRuleContext(AllDistinctContext.class,0);
		}
		public GroupByContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_groupBy; }
	}

	public final GroupByContext groupBy() throws RecognitionException {
		GroupByContext _localctx = new GroupByContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_groupBy);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1669);
			match(T__199);
			setState(1670);
			match(T__133);
			setState(1672);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,276,_ctx) ) {
			case 1:
				{
				setState(1671);
				allDistinct();
				}
				break;
			}
			setState(1674);
			groupByItem();
			setState(1679);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,277,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1675);
					match(T__28);
					setState(1676);
					groupByItem();
					}
					} 
				}
				setState(1681);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,277,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class GroupByItemContext extends ParserRuleContext {
		public TermsContext terms() {
			return getRuleContext(TermsContext.class,0);
		}
		public List<GroupByItemContext> groupByItem() {
			return getRuleContexts(GroupByItemContext.class);
		}
		public GroupByItemContext groupByItem(int i) {
			return getRuleContext(GroupByItemContext.class,i);
		}
		public GroupByItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_groupByItem; }
	}

	public final GroupByItemContext groupByItem() throws RecognitionException {
		GroupByItemContext _localctx = new GroupByItemContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_groupByItem);
		int _la;
		try {
			setState(1706);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,279,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1682);
				terms();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1683);
				match(T__200);
				setState(1684);
				match(T__16);
				setState(1685);
				terms();
				setState(1686);
				match(T__17);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1688);
				match(T__201);
				setState(1689);
				match(T__16);
				setState(1690);
				terms();
				setState(1691);
				match(T__17);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1693);
				match(T__202);
				setState(1694);
				match(T__203);
				setState(1695);
				match(T__16);
				setState(1696);
				groupByItem();
				setState(1701);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__28) {
					{
					{
					setState(1697);
					match(T__28);
					setState(1698);
					groupByItem();
					}
					}
					setState(1703);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1704);
				match(T__17);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class HavingContext extends ParserRuleContext {
		public TermsContext terms() {
			return getRuleContext(TermsContext.class,0);
		}
		public HavingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_having; }
	}

	public final HavingContext having() throws RecognitionException {
		HavingContext _localctx = new HavingContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_having);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1708);
			match(T__204);
			setState(1709);
			terms();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WindowsContext extends ParserRuleContext {
		public List<WindowAliasContext> windowAlias() {
			return getRuleContexts(WindowAliasContext.class);
		}
		public WindowAliasContext windowAlias(int i) {
			return getRuleContext(WindowAliasContext.class,i);
		}
		public WindowsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_windows; }
	}

	public final WindowsContext windows() throws RecognitionException {
		WindowsContext _localctx = new WindowsContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_windows);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1711);
			match(T__205);
			setState(1712);
			windowAlias();
			setState(1717);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,280,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1713);
					match(T__28);
					setState(1714);
					windowAlias();
					}
					} 
				}
				setState(1719);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,280,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WindowAliasContext extends ParserRuleContext {
		public AliasContext alias() {
			return getRuleContext(AliasContext.class,0);
		}
		public WindowContext window() {
			return getRuleContext(WindowContext.class,0);
		}
		public WindowAliasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_windowAlias; }
	}

	public final WindowAliasContext windowAlias() throws RecognitionException {
		WindowAliasContext _localctx = new WindowAliasContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_windowAlias);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1720);
			alias();
			setState(1721);
			match(T__5);
			setState(1722);
			window();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QualifyContext extends ParserRuleContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public QualifyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualify; }
	}

	public final QualifyContext qualify() throws RecognitionException {
		QualifyContext _localctx = new QualifyContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_qualify);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1724);
			match(T__206);
			setState(1725);
			term(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class XmlTableContext extends ParserRuleContext {
		public SubtermContext subterm() {
			return getRuleContext(SubtermContext.class,0);
		}
		public List<XmlColumnContext> xmlColumn() {
			return getRuleContexts(XmlColumnContext.class);
		}
		public XmlColumnContext xmlColumn(int i) {
			return getRuleContext(XmlColumnContext.class,i);
		}
		public PassingContext passing() {
			return getRuleContext(PassingContext.class,0);
		}
		public XmlTableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_xmlTable; }
	}

	public final XmlTableContext xmlTable() throws RecognitionException {
		XmlTableContext _localctx = new XmlTableContext(_ctx, getState());
		enterRule(_localctx, 128, RULE_xmlTable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1727);
			match(T__207);
			setState(1728);
			match(T__16);
			setState(1729);
			subterm(0);
			setState(1731);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__209) {
				{
				setState(1730);
				passing();
				}
			}

			setState(1733);
			match(T__208);
			setState(1734);
			xmlColumn();
			setState(1739);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__28) {
				{
				{
				setState(1735);
				match(T__28);
				setState(1736);
				xmlColumn();
				}
				}
				setState(1741);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1742);
			match(T__17);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PassingContext extends ParserRuleContext {
		public AliasedTermsContext aliasedTerms() {
			return getRuleContext(AliasedTermsContext.class,0);
		}
		public PassingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_passing; }
	}

	public final PassingContext passing() throws RecognitionException {
		PassingContext _localctx = new PassingContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_passing);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1744);
			match(T__209);
			setState(1747);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,283,_ctx) ) {
			case 1:
				{
				setState(1745);
				match(T__133);
				setState(1746);
				_la = _input.LA(1);
				if ( !(_la==T__141 || _la==T__210) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			}
			setState(1749);
			aliasedTerms();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class XmlColumnContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public SubtermContext subterm() {
			return getRuleContext(SubtermContext.class,0);
		}
		public XmlColumnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_xmlColumn; }
	}

	public final XmlColumnContext xmlColumn() throws RecognitionException {
		XmlColumnContext _localctx = new XmlColumnContext(_ctx, getState());
		enterRule(_localctx, 132, RULE_xmlColumn);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1751);
			name();
			setState(1759);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
			case T__2:
			case T__3:
			case T__4:
			case T__5:
			case T__6:
			case T__7:
			case T__8:
			case T__9:
			case T__10:
			case T__11:
			case T__12:
			case T__14:
			case T__18:
			case T__19:
			case T__20:
			case T__21:
			case T__22:
			case T__23:
			case T__24:
			case T__25:
			case T__26:
			case T__27:
			case T__29:
			case T__30:
			case T__31:
			case T__32:
			case T__33:
			case T__34:
			case T__35:
			case T__36:
			case T__37:
			case T__38:
			case T__39:
			case T__40:
			case T__41:
			case T__42:
			case T__43:
			case T__44:
			case T__45:
			case T__46:
			case T__47:
			case T__48:
			case T__49:
			case T__50:
			case T__51:
			case T__53:
			case T__54:
			case T__55:
			case T__56:
			case T__57:
			case T__58:
			case T__59:
			case T__60:
			case T__62:
			case T__63:
			case T__64:
			case T__65:
			case T__66:
			case T__67:
			case T__68:
			case T__69:
			case T__70:
			case T__71:
			case T__72:
			case T__73:
			case T__74:
			case T__75:
			case T__76:
			case T__77:
			case T__78:
			case T__79:
			case T__80:
			case T__81:
			case T__82:
			case T__83:
			case T__84:
			case T__86:
			case T__87:
			case T__88:
			case T__90:
			case T__91:
			case T__92:
			case T__93:
			case T__94:
			case T__95:
			case T__96:
			case T__99:
			case T__100:
			case T__102:
			case T__103:
			case T__105:
			case T__108:
			case T__109:
			case T__110:
			case T__111:
			case T__112:
			case T__114:
			case T__116:
			case T__118:
			case T__119:
			case T__120:
			case T__121:
			case T__122:
			case T__123:
			case T__124:
			case T__125:
			case T__126:
			case T__127:
			case T__128:
			case T__129:
			case T__130:
			case T__131:
			case T__132:
			case T__133:
			case T__134:
			case T__135:
			case T__136:
			case T__138:
			case T__139:
			case T__140:
			case T__141:
			case T__142:
			case T__143:
			case T__144:
			case T__145:
			case T__147:
			case T__148:
			case T__149:
			case T__150:
			case T__151:
			case T__152:
			case T__158:
			case T__160:
			case T__161:
			case T__162:
			case T__163:
			case T__164:
			case T__165:
			case T__166:
			case T__167:
			case T__168:
			case T__170:
			case T__171:
			case T__172:
			case T__173:
			case T__174:
			case T__175:
			case T__176:
			case T__177:
			case T__178:
			case T__179:
			case T__181:
			case T__183:
			case T__184:
			case T__185:
			case T__186:
			case T__187:
			case T__188:
			case T__190:
			case T__192:
			case T__193:
			case T__194:
			case T__195:
			case T__196:
			case T__198:
			case T__200:
			case T__201:
			case T__202:
			case T__203:
			case T__205:
			case T__207:
			case T__208:
			case T__209:
			case T__210:
			case T__211:
			case T__212:
			case T__214:
			case T__215:
			case T__216:
			case T__217:
			case T__218:
			case T__219:
			case T__220:
			case T__221:
			case T__222:
			case T__223:
			case T__224:
			case T__225:
			case T__226:
			case T__227:
			case T__228:
			case T__229:
			case T__230:
			case T__231:
			case T__232:
			case T__233:
			case T__234:
			case T__235:
			case T__237:
			case T__238:
			case T__239:
			case T__240:
			case T__241:
			case T__242:
			case T__243:
			case T__244:
			case T__246:
			case T__247:
			case T__249:
			case T__251:
			case T__253:
			case T__254:
			case T__255:
			case T__256:
			case T__257:
			case T__258:
			case T__259:
			case T__267:
			case T__268:
			case T__269:
			case T__270:
			case T__271:
			case T__272:
			case T__273:
			case T__274:
			case T__276:
			case T__277:
			case T__278:
			case T__281:
			case T__282:
			case T__283:
			case T__284:
			case T__285:
			case T__286:
			case T__287:
			case T__288:
			case T__289:
			case T__290:
			case T__291:
			case T__292:
			case T__293:
			case T__294:
			case T__295:
			case T__296:
			case T__297:
			case T__298:
			case T__299:
			case T__300:
			case T__301:
			case T__302:
			case T__303:
			case T__304:
			case T__305:
			case T__306:
			case T__307:
			case T__308:
			case T__309:
			case T__310:
			case T__311:
			case T__312:
			case T__313:
			case T__314:
			case T__318:
			case T__319:
			case T__320:
			case T__321:
			case T__322:
			case T__323:
			case T__324:
			case T__325:
			case T__326:
			case T__327:
			case T__328:
			case T__329:
			case T__330:
			case T__331:
			case T__332:
			case T__333:
			case T__334:
			case T__335:
			case T__336:
			case T__337:
			case T__338:
			case T__339:
			case T__340:
			case T__341:
			case T__342:
			case T__343:
			case T__344:
			case T__345:
			case T__346:
			case T__347:
			case T__348:
			case T__349:
			case T__350:
			case T__351:
			case T__352:
			case T__354:
			case T__355:
			case T__356:
			case T__357:
			case T__358:
			case T__359:
			case T__360:
			case T__361:
			case T__362:
			case T__363:
			case T__364:
			case T__365:
			case T__366:
			case T__367:
			case T__368:
			case T__370:
			case T__371:
			case T__372:
			case T__373:
			case T__374:
			case T__375:
			case T__376:
			case T__377:
			case T__378:
			case T__379:
			case T__380:
			case T__381:
			case T__382:
			case T__383:
			case T__384:
			case T__385:
			case T__386:
			case T__387:
			case T__388:
			case T__389:
			case T__390:
			case T__391:
			case T__392:
			case T__393:
			case T__394:
			case T__395:
			case T__397:
			case T__398:
			case T__399:
			case T__400:
			case T__401:
			case UNICODE_NAME:
			case STRING:
			case ID:
			case BRACKETS:
			case DOLLARS:
			case PARAMETER:
			case VARIABLE:
			case COMMENT:
			case BLOCK_COMMENT:
			case WHITESPACE:
			case OPERATOR:
				{
				setState(1752);
				type(0);
				setState(1755);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__211) {
					{
					setState(1753);
					match(T__211);
					setState(1754);
					subterm(0);
					}
				}

				}
				break;
			case T__98:
				{
				setState(1757);
				match(T__98);
				setState(1758);
				match(T__167);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TermsContext extends ParserRuleContext {
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public TermsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_terms; }
	}

	public final TermsContext terms() throws RecognitionException {
		TermsContext _localctx = new TermsContext(_ctx, getState());
		enterRule(_localctx, 134, RULE_terms);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1761);
			term(0);
			setState(1766);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,286,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1762);
					match(T__28);
					setState(1763);
					term(0);
					}
					} 
				}
				setState(1768);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,286,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AliasedTermContext extends ParserRuleContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public AliasContext alias() {
			return getRuleContext(AliasContext.class,0);
		}
		public AliasedTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aliasedTerm; }
	}

	public final AliasedTermContext aliasedTerm() throws RecognitionException {
		AliasedTermContext _localctx = new AliasedTermContext(_ctx, getState());
		enterRule(_localctx, 136, RULE_aliasedTerm);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1769);
			term(0);
			setState(1774);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,288,_ctx) ) {
			case 1:
				{
				setState(1771);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,287,_ctx) ) {
				case 1:
					{
					setState(1770);
					match(T__5);
					}
					break;
				}
				setState(1773);
				alias();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AliasedTermsContext extends ParserRuleContext {
		public List<AliasedTermContext> aliasedTerm() {
			return getRuleContexts(AliasedTermContext.class);
		}
		public AliasedTermContext aliasedTerm(int i) {
			return getRuleContext(AliasedTermContext.class,i);
		}
		public AliasedTermsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aliasedTerms; }
	}

	public final AliasedTermsContext aliasedTerms() throws RecognitionException {
		AliasedTermsContext _localctx = new AliasedTermsContext(_ctx, getState());
		enterRule(_localctx, 138, RULE_aliasedTerms);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1776);
			aliasedTerm();
			setState(1781);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__28) {
				{
				{
				setState(1777);
				match(T__28);
				setState(1778);
				aliasedTerm();
				}
				}
				setState(1783);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TermContext extends ParserRuleContext {
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public SubtermContext subterm() {
			return getRuleContext(SubtermContext.class,0);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public QnameContext qname() {
			return getRuleContext(QnameContext.class,0);
		}
		public TerminalNode VARIABLE() { return getToken(NormalSQLParser.VARIABLE, 0); }
		public AssignContext assign() {
			return getRuleContext(AssignContext.class,0);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
	}

	public final TermContext term() throws RecognitionException {
		return term(0);
	}

	private TermContext term(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TermContext _localctx = new TermContext(_ctx, _parentState);
		TermContext _prevctx = _localctx;
		int _startState = 140;
		enterRecursionRule(_localctx, 140, RULE_term, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1809);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,290,_ctx) ) {
			case 1:
				{
				setState(1785);
				match(T__16);
				setState(1786);
				term(0);
				setState(1787);
				match(T__17);
				}
				break;
			case 2:
				{
				setState(1789);
				subterm(0);
				}
				break;
			case 3:
				{
				setState(1790);
				match(T__107);
				setState(1791);
				term(6);
				}
				break;
			case 4:
				{
				setState(1792);
				match(T__123);
				setState(1793);
				match(T__16);
				setState(1794);
				name();
				setState(1795);
				match(T__28);
				setState(1796);
				string();
				setState(1797);
				match(T__17);
				setState(1798);
				match(T__89);
				setState(1799);
				qname();
				setState(1800);
				match(T__85);
				setState(1801);
				match(T__16);
				setState(1802);
				subterm(0);
				setState(1803);
				match(T__17);
				}
				break;
			case 5:
				{
				setState(1805);
				match(VARIABLE);
				setState(1806);
				assign();
				setState(1807);
				term(1);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(1822);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,292,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(1820);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,291,_ctx) ) {
					case 1:
						{
						_localctx = new TermContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_term);
						setState(1811);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(1812);
						match(T__146);
						setState(1813);
						term(6);
						}
						break;
					case 2:
						{
						_localctx = new TermContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_term);
						setState(1814);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(1815);
						match(T__212);
						setState(1816);
						term(5);
						}
						break;
					case 3:
						{
						_localctx = new TermContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_term);
						setState(1817);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(1818);
						match(T__104);
						setState(1819);
						term(4);
						}
						break;
					}
					} 
				}
				setState(1824);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,292,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignContext extends ParserRuleContext {
		public AssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign; }
	}

	public final AssignContext assign() throws RecognitionException {
		AssignContext _localctx = new AssignContext(_ctx, getState());
		enterRule(_localctx, 142, RULE_assign);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1825);
			_la = _input.LA(1);
			if ( !(_la==T__15 || ((((_la - 214)) & ~0x3f) == 0 && ((1L << (_la - 214)) & 511L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SubtermContext extends ParserRuleContext {
		public SubtermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subterm; }
	 
		public SubtermContext() { }
		public void copyFrom(SubtermContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SubtermFunctionContext extends SubtermContext {
		public List<FunctionContext> function() {
			return getRuleContexts(FunctionContext.class);
		}
		public FunctionContext function(int i) {
			return getRuleContext(FunctionContext.class,i);
		}
		public SubtermFunctionContext(SubtermContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SubtermRowContext extends SubtermContext {
		public TermsContext terms() {
			return getRuleContext(TermsContext.class,0);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public SubtermRowContext(SubtermContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SubtermCastContext extends SubtermContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public SubtermContext subterm() {
			return getRuleContext(SubtermContext.class,0);
		}
		public SubtermCastContext(SubtermContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SubtermIntervalContext extends SubtermContext {
		public SubtermContext subterm() {
			return getRuleContext(SubtermContext.class,0);
		}
		public IntervalContext interval() {
			return getRuleContext(IntervalContext.class,0);
		}
		public SubtermIntervalContext(SubtermContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SubtermSequenceContext extends SubtermContext {
		public ColumnContext column() {
			return getRuleContext(ColumnContext.class,0);
		}
		public SubtermSequenceContext(SubtermContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SubtermArrayContext extends SubtermContext {
		public ArrayContext array() {
			return getRuleContext(ArrayContext.class,0);
		}
		public SubtermArrayContext(SubtermContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SubtermBinaryContext extends SubtermContext {
		public List<SubtermContext> subterm() {
			return getRuleContexts(SubtermContext.class);
		}
		public SubtermContext subterm(int i) {
			return getRuleContext(SubtermContext.class,i);
		}
		public SubtermBinaryContext(SubtermContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SubtermEXISTSContext extends SubtermContext {
		public SelectContext select() {
			return getRuleContext(SelectContext.class,0);
		}
		public SubtermEXISTSContext(SubtermContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SubtermTimeContext extends SubtermContext {
		public SubtermContext subterm() {
			return getRuleContext(SubtermContext.class,0);
		}
		public TimeZoneContext timeZone() {
			return getRuleContext(TimeZoneContext.class,0);
		}
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public SubtermTimeContext(SubtermContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SubtermPredicateContext extends SubtermContext {
		public SubtermContext subterm() {
			return getRuleContext(SubtermContext.class,0);
		}
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
		}
		public SubtermPredicateContext(SubtermContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SubtermOverlapsContext extends SubtermContext {
		public List<SubtermContext> subterm() {
			return getRuleContexts(SubtermContext.class);
		}
		public SubtermContext subterm(int i) {
			return getRuleContext(SubtermContext.class,i);
		}
		public SubtermOverlapsContext(SubtermContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SubtermColumnContext extends SubtermContext {
		public ColumnContext column() {
			return getRuleContext(ColumnContext.class,0);
		}
		public SubtermColumnContext(SubtermContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SubtermCaseContext extends SubtermContext {
		public CaseContext case_() {
			return getRuleContext(CaseContext.class,0);
		}
		public SubtermCaseContext(SubtermContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SubtermScopeContext extends SubtermContext {
		public List<SubtermContext> subterm() {
			return getRuleContexts(SubtermContext.class);
		}
		public SubtermContext subterm(int i) {
			return getRuleContext(SubtermContext.class,i);
		}
		public SubtermScopeContext(SubtermContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SubtermIndexContext extends SubtermContext {
		public SubtermContext subterm() {
			return getRuleContext(SubtermContext.class,0);
		}
		public List<IndexContext> index() {
			return getRuleContexts(IndexContext.class);
		}
		public IndexContext index(int i) {
			return getRuleContext(IndexContext.class,i);
		}
		public SubtermIndexContext(SubtermContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SubtermUNIQUEContext extends SubtermContext {
		public SelectContext select() {
			return getRuleContext(SelectContext.class,0);
		}
		public SubtermUNIQUEContext(SubtermContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SubtermCOLLATEContext extends SubtermContext {
		public SubtermContext subterm() {
			return getRuleContext(SubtermContext.class,0);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public SubtermCOLLATEContext(SubtermContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SubtermLiteralContext extends SubtermContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public SubtermLiteralContext(SubtermContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SubtermQueryContext extends SubtermContext {
		public SelectContext select() {
			return getRuleContext(SelectContext.class,0);
		}
		public SubtermQueryContext(SubtermContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SubtermUnaryContext extends SubtermContext {
		public SubtermContext subterm() {
			return getRuleContext(SubtermContext.class,0);
		}
		public SubtermUnaryContext(SubtermContext ctx) { copyFrom(ctx); }
	}

	public final SubtermContext subterm() throws RecognitionException {
		return subterm(0);
	}

	private SubtermContext subterm(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		SubtermContext _localctx = new SubtermContext(_ctx, _parentState);
		SubtermContext _prevctx = _localctx;
		int _startState = 144;
		enterRecursionRule(_localctx, 144, RULE_subterm, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1907);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,301,_ctx) ) {
			case 1:
				{
				_localctx = new SubtermLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(1828);
				literal();
				}
				break;
			case 2:
				{
				_localctx = new SubtermUnaryContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1829);
				_la = _input.LA(1);
				if ( !(_la==T__107 || ((((_la - 223)) & ~0x3f) == 0 && ((1L << (_la - 223)) & 15L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1830);
				subterm(29);
				}
				break;
			case 3:
				{
				_localctx = new SubtermFunctionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1831);
				function();
				setState(1836);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,293,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1832);
						match(T__182);
						setState(1833);
						function();
						}
						} 
					}
					setState(1838);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,293,_ctx);
				}
				}
				break;
			case 4:
				{
				_localctx = new SubtermColumnContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1839);
				column();
				}
				break;
			case 5:
				{
				_localctx = new SubtermIntervalContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1840);
				match(T__235);
				setState(1841);
				subterm(0);
				setState(1843);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,294,_ctx) ) {
				case 1:
					{
					setState(1842);
					interval();
					}
					break;
				}
				}
				break;
			case 6:
				{
				_localctx = new SubtermQueryContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1845);
				select(0);
				}
				break;
			case 7:
				{
				_localctx = new SubtermArrayContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1846);
				array();
				}
				break;
			case 8:
				{
				_localctx = new SubtermCaseContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1847);
				case_();
				}
				break;
			case 9:
				{
				_localctx = new SubtermCastContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1848);
				_la = _input.LA(1);
				if ( !(_la==T__236 || _la==T__237) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1849);
				match(T__16);
				setState(1850);
				term(0);
				setState(1851);
				match(T__5);
				setState(1852);
				type(0);
				setState(1853);
				match(T__17);
				}
				break;
			case 10:
				{
				_localctx = new SubtermEXISTSContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1855);
				match(T__52);
				setState(1856);
				match(T__16);
				setState(1857);
				select(0);
				setState(1858);
				match(T__17);
				}
				break;
			case 11:
				{
				_localctx = new SubtermUNIQUEContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1860);
				match(T__106);
				setState(1865);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__107 || _la==T__158 || _la==T__180) {
					{
					setState(1862);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__107 || _la==T__158) {
						{
						setState(1861);
						_la = _input.LA(1);
						if ( !(_la==T__107 || _la==T__158) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
					}

					setState(1864);
					match(T__180);
					}
				}

				setState(1867);
				match(T__16);
				setState(1868);
				select(0);
				setState(1869);
				match(T__17);
				}
				break;
			case 12:
				{
				_localctx = new SubtermSequenceContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1871);
				_la = _input.LA(1);
				if ( !(_la==T__172 || _la==T__198) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1872);
				match(T__141);
				setState(1873);
				match(T__98);
				setState(1874);
				column();
				}
				break;
			case 13:
				{
				_localctx = new SubtermOverlapsContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1875);
				match(T__16);
				setState(1876);
				subterm(0);
				setState(1877);
				match(T__28);
				setState(1878);
				subterm(0);
				setState(1879);
				match(T__17);
				setState(1880);
				match(T__238);
				setState(1881);
				match(T__16);
				setState(1882);
				subterm(0);
				setState(1883);
				match(T__28);
				setState(1884);
				subterm(0);
				setState(1885);
				match(T__17);
				}
				break;
			case 14:
				{
				_localctx = new SubtermRowContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1888);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__100) {
					{
					setState(1887);
					match(T__100);
					}
				}

				setState(1890);
				match(T__16);
				setState(1892);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -4611686018964602884L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & -19142806744334337L) != 0) || ((((_la - 128)) & ~0x3f) == 0 && ((1L << (_la - 128)) & -4656726418989252609L) != 0) || ((((_la - 193)) & ~0x3f) == 0 && ((1L << (_la - 193)) & -1522216674053345441L) != 0) || ((((_la - 257)) & ~0x3f) == 0 && ((1L << (_la - 257)) & -4035225266149132273L) != 0) || ((((_la - 321)) & ~0x3f) == 0 && ((1L << (_la - 321)) & -8589934593L) != 0) || ((((_la - 385)) & ~0x3f) == 0 && ((1L << (_la - 385)) & 68719472639L) != 0)) {
					{
					setState(1891);
					terms();
					}
				}

				setState(1894);
				match(T__17);
				setState(1897);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,299,_ctx) ) {
				case 1:
					{
					setState(1895);
					match(T__182);
					setState(1896);
					name();
					}
					break;
				}
				}
				break;
			case 15:
				{
				_localctx = new SubtermFunctionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1899);
				function();
				setState(1904);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,300,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1900);
						match(T__182);
						setState(1901);
						function();
						}
						} 
					}
					setState(1906);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,300,_ctx);
				}
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(1966);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,306,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(1964);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,305,_ctx) ) {
					case 1:
						{
						_localctx = new SubtermBinaryContext(new SubtermContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_subterm);
						setState(1909);
						if (!(precpred(_ctx, 22))) throw new FailedPredicateException(this, "precpred(_ctx, 22)");
						setState(1910);
						match(T__227);
						setState(1911);
						subterm(23);
						}
						break;
					case 2:
						{
						_localctx = new SubtermBinaryContext(new SubtermContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_subterm);
						setState(1912);
						if (!(precpred(_ctx, 21))) throw new FailedPredicateException(this, "precpred(_ctx, 21)");
						setState(1913);
						match(T__228);
						setState(1914);
						subterm(22);
						}
						break;
					case 3:
						{
						_localctx = new SubtermBinaryContext(new SubtermContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_subterm);
						setState(1915);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(1916);
						match(T__229);
						setState(1917);
						subterm(20);
						}
						break;
					case 4:
						{
						_localctx = new SubtermBinaryContext(new SubtermContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_subterm);
						setState(1918);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(1919);
						_la = _input.LA(1);
						if ( !(_la==T__230 || _la==T__231) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1920);
						subterm(19);
						}
						break;
					case 5:
						{
						_localctx = new SubtermBinaryContext(new SubtermContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_subterm);
						setState(1921);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(1922);
						_la = _input.LA(1);
						if ( !(_la==T__232 || _la==T__233) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1923);
						subterm(18);
						}
						break;
					case 6:
						{
						_localctx = new SubtermBinaryContext(new SubtermContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_subterm);
						setState(1924);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(1925);
						match(T__239);
						setState(1926);
						subterm(3);
						}
						break;
					case 7:
						{
						_localctx = new SubtermBinaryContext(new SubtermContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_subterm);
						setState(1927);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(1928);
						_la = _input.LA(1);
						if ( !(_la==T__153 || ((((_la - 241)) & ~0x3f) == 0 && ((1L << (_la - 241)) & 15L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1929);
						subterm(3);
						}
						break;
					case 8:
						{
						_localctx = new SubtermBinaryContext(new SubtermContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_subterm);
						setState(1930);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(1931);
						_la = _input.LA(1);
						if ( !(_la==T__222 || _la==T__223) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(1932);
						subterm(2);
						}
						break;
					case 9:
						{
						_localctx = new SubtermCastContext(new SubtermContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_subterm);
						setState(1933);
						if (!(precpred(_ctx, 28))) throw new FailedPredicateException(this, "precpred(_ctx, 28)");
						setState(1934);
						match(T__226);
						setState(1935);
						type(0);
						}
						break;
					case 10:
						{
						_localctx = new SubtermScopeContext(new SubtermContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_subterm);
						setState(1936);
						if (!(precpred(_ctx, 27))) throw new FailedPredicateException(this, "precpred(_ctx, 27)");
						setState(1939); 
						_errHandler.sync(this);
						_alt = 1;
						do {
							switch (_alt) {
							case 1:
								{
								{
								setState(1937);
								match(T__226);
								setState(1938);
								subterm(0);
								}
								}
								break;
							default:
								throw new NoViableAltException(this);
							}
							setState(1941); 
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,302,_ctx);
						} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
						}
						break;
					case 11:
						{
						_localctx = new SubtermIndexContext(new SubtermContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_subterm);
						setState(1943);
						if (!(precpred(_ctx, 26))) throw new FailedPredicateException(this, "precpred(_ctx, 26)");
						setState(1945); 
						_errHandler.sync(this);
						_alt = 1;
						do {
							switch (_alt) {
							case 1:
								{
								{
								setState(1944);
								index();
								}
								}
								break;
							default:
								throw new NoViableAltException(this);
							}
							setState(1947); 
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,303,_ctx);
						} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
						}
						break;
					case 12:
						{
						_localctx = new SubtermCOLLATEContext(new SubtermContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_subterm);
						setState(1949);
						if (!(precpred(_ctx, 23))) throw new FailedPredicateException(this, "precpred(_ctx, 23)");
						setState(1950);
						match(T__116);
						setState(1951);
						name();
						}
						break;
					case 13:
						{
						_localctx = new SubtermPredicateContext(new SubtermContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_subterm);
						setState(1952);
						if (!(precpred(_ctx, 20))) throw new FailedPredicateException(this, "precpred(_ctx, 20)");
						setState(1953);
						predicate();
						}
						break;
					case 14:
						{
						_localctx = new SubtermTimeContext(new SubtermContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_subterm);
						setState(1954);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(1955);
						match(T__234);
						setState(1960);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case T__49:
							{
							setState(1956);
							match(T__49);
							}
							break;
						case T__304:
							{
							setState(1957);
							timeZone();
							setState(1958);
							string();
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						}
						break;
					case 15:
						{
						_localctx = new SubtermIntervalContext(new SubtermContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_subterm);
						setState(1962);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(1963);
						interval();
						}
						break;
					}
					} 
				}
				setState(1968);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,306,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CaseContext extends ParserRuleContext {
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public List<TermsContext> terms() {
			return getRuleContexts(TermsContext.class);
		}
		public TermsContext terms(int i) {
			return getRuleContext(TermsContext.class,i);
		}
		public List<PredicateContext> predicate() {
			return getRuleContexts(PredicateContext.class);
		}
		public PredicateContext predicate(int i) {
			return getRuleContext(PredicateContext.class,i);
		}
		public CaseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_case; }
	}

	public final CaseContext case_() throws RecognitionException {
		CaseContext _localctx = new CaseContext(_ctx, getState());
		enterRule(_localctx, 146, RULE_case);
		int _la;
		try {
			setState(2005);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,312,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1969);
				match(T__244);
				setState(1970);
				term(0);
				setState(1979); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(1971);
					match(T__101);
					setState(1974);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,307,_ctx) ) {
					case 1:
						{
						setState(1972);
						terms();
						}
						break;
					case 2:
						{
						setState(1973);
						predicate();
						}
						break;
					}
					setState(1976);
					match(T__147);
					setState(1977);
					term(0);
					}
					}
					setState(1981); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==T__101 );
				setState(1985);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__245) {
					{
					setState(1983);
					match(T__245);
					setState(1984);
					term(0);
					}
				}

				setState(1987);
				match(T__13);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1989);
				match(T__244);
				setState(1995); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(1990);
					match(T__101);
					setState(1991);
					term(0);
					setState(1992);
					match(T__147);
					setState(1993);
					term(0);
					}
					}
					setState(1997); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==T__101 );
				setState(2001);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__245) {
					{
					setState(1999);
					match(T__245);
					setState(2000);
					term(0);
					}
				}

				setState(2003);
				match(T__13);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PredicateContext extends ParserRuleContext {
		public PredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate; }
	 
		public PredicateContext() { }
		public void copyFrom(PredicateContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PredicateJSONContext extends PredicateContext {
		public JsonTypeContext jsonType() {
			return getRuleContext(JsonTypeContext.class,0);
		}
		public UniqueKeysContext uniqueKeys() {
			return getRuleContext(UniqueKeysContext.class,0);
		}
		public PredicateJSONContext(PredicateContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PredicateINContext extends PredicateContext {
		public SelectContext select() {
			return getRuleContext(SelectContext.class,0);
		}
		public TermsContext terms() {
			return getRuleContext(TermsContext.class,0);
		}
		public SubtermContext subterm() {
			return getRuleContext(SubtermContext.class,0);
		}
		public PredicateINContext(PredicateContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PredicateLIKEContext extends PredicateContext {
		public SubtermContext subterm() {
			return getRuleContext(SubtermContext.class,0);
		}
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public PredicateLIKEContext(PredicateContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PredicateIsTruthContext extends PredicateContext {
		public TruthContext truth() {
			return getRuleContext(TruthContext.class,0);
		}
		public PredicateIsTruthContext(PredicateContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PredicateLogicalContext extends PredicateContext {
		public LogicalsContext logicals() {
			return getRuleContext(LogicalsContext.class,0);
		}
		public PredicateLogicalContext(PredicateContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PredicateOfTypeContext extends PredicateContext {
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public PredicateOfTypeContext(PredicateContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PredicateBETWEENContext extends PredicateContext {
		public List<SubtermContext> subterm() {
			return getRuleContexts(SubtermContext.class);
		}
		public SubtermContext subterm(int i) {
			return getRuleContext(SubtermContext.class,i);
		}
		public PredicateBETWEENContext(PredicateContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PredicateCompareContext extends PredicateContext {
		public CompareContext compare() {
			return getRuleContext(CompareContext.class,0);
		}
		public SubtermContext subterm() {
			return getRuleContext(SubtermContext.class,0);
		}
		public PredicateCompareContext(PredicateContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PredicateRaiseContext extends PredicateContext {
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public PredicateRaiseContext(PredicateContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PredicateISContext extends PredicateContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public PredicateISContext(PredicateContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PredicateIsNullContext extends PredicateContext {
		public PredicateIsNullContext(PredicateContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PredicateDistinctContext extends PredicateContext {
		public SubtermContext subterm() {
			return getRuleContext(SubtermContext.class,0);
		}
		public PredicateDistinctContext(PredicateContext ctx) { copyFrom(ctx); }
	}

	public final PredicateContext predicate() throws RecognitionException {
		PredicateContext _localctx = new PredicateContext(_ctx, getState());
		enterRule(_localctx, 148, RULE_predicate);
		int _la;
		try {
			setState(2118);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,334,_ctx) ) {
			case 1:
				_localctx = new PredicateCompareContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(2007);
				compare();
				setState(2008);
				subterm(0);
				}
				break;
			case 2:
				_localctx = new PredicateIsNullContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(2014);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__246:
					{
					setState(2010);
					match(T__246);
					}
					break;
				case T__247:
					{
					setState(2011);
					match(T__247);
					}
					break;
				case T__107:
					{
					setState(2012);
					match(T__107);
					setState(2013);
					match(T__112);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 3:
				_localctx = new PredicateIsTruthContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(2016);
				match(T__248);
				setState(2018);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__107) {
					{
					setState(2017);
					match(T__107);
					}
				}

				setState(2020);
				truth();
				}
				break;
			case 4:
				_localctx = new PredicateLogicalContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(2021);
				match(T__248);
				setState(2023);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__107) {
					{
					setState(2022);
					match(T__107);
					}
				}

				setState(2025);
				logicals();
				}
				break;
			case 5:
				_localctx = new PredicateDistinctContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(2026);
				match(T__248);
				setState(2028);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__107) {
					{
					setState(2027);
					match(T__107);
					}
				}

				setState(2030);
				match(T__180);
				setState(2031);
				match(T__135);
				setState(2032);
				subterm(0);
				}
				break;
			case 6:
				_localctx = new PredicateOfTypeContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(2033);
				match(T__248);
				setState(2035);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__107) {
					{
					setState(2034);
					match(T__107);
					}
				}

				setState(2037);
				match(T__93);
				setState(2039);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__249) {
					{
					setState(2038);
					match(T__249);
					}
				}

				setState(2041);
				match(T__16);
				setState(2043);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,319,_ctx) ) {
				case 1:
					{
					setState(2042);
					match(T__136);
					}
					break;
				}
				setState(2045);
				type(0);
				setState(2050);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__28) {
					{
					{
					setState(2046);
					match(T__28);
					setState(2047);
					type(0);
					}
					}
					setState(2052);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(2053);
				match(T__17);
				}
				break;
			case 7:
				_localctx = new PredicateJSONContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(2055);
				match(T__248);
				setState(2057);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__107) {
					{
					setState(2056);
					match(T__107);
					}
				}

				setState(2059);
				match(T__40);
				setState(2061);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,322,_ctx) ) {
				case 1:
					{
					setState(2060);
					jsonType();
					}
					break;
				}
				setState(2064);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,323,_ctx) ) {
				case 1:
					{
					setState(2063);
					uniqueKeys();
					}
					break;
				}
				}
				break;
			case 8:
				_localctx = new PredicateISContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(2066);
				match(T__248);
				setState(2068);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,324,_ctx) ) {
				case 1:
					{
					setState(2067);
					match(T__107);
					}
					break;
				}
				setState(2070);
				term(0);
				}
				break;
			case 9:
				_localctx = new PredicateINContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(2072);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__107) {
					{
					setState(2071);
					match(T__107);
					}
				}

				setState(2074);
				match(T__191);
				setState(2075);
				match(T__16);
				setState(2078);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,326,_ctx) ) {
				case 1:
					{
					setState(2076);
					select(0);
					}
					break;
				case 2:
					{
					setState(2077);
					terms();
					}
					break;
				}
				setState(2080);
				match(T__17);
				}
				break;
			case 10:
				_localctx = new PredicateINContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(2082);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__107) {
					{
					setState(2081);
					match(T__107);
					}
				}

				setState(2084);
				match(T__191);
				setState(2085);
				subterm(0);
				}
				break;
			case 11:
				_localctx = new PredicateBETWEENContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(2087);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__107) {
					{
					setState(2086);
					match(T__107);
					}
				}

				setState(2089);
				match(T__250);
				setState(2091);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,329,_ctx) ) {
				case 1:
					{
					setState(2090);
					_la = _input.LA(1);
					if ( !(_la==T__251 || _la==T__252) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					break;
				}
				setState(2093);
				subterm(0);
				setState(2094);
				match(T__146);
				setState(2095);
				subterm(0);
				}
				break;
			case 12:
				_localctx = new PredicateLIKEContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(2098);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__107) {
					{
					setState(2097);
					match(T__107);
					}
				}

				setState(2100);
				_la = _input.LA(1);
				if ( !(_la==T__123 || ((((_la - 254)) & ~0x3f) == 0 && ((1L << (_la - 254)) & 15L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(2101);
				subterm(0);
				setState(2107);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,332,_ctx) ) {
				case 1:
					{
					setState(2102);
					match(T__257);
					setState(2105);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,331,_ctx) ) {
					case 1:
						{
						setState(2103);
						string();
						}
						break;
					case 2:
						{
						setState(2104);
						term(0);
						}
						break;
					}
					}
					break;
				}
				}
				break;
			case 13:
				_localctx = new PredicateRaiseContext(_localctx);
				enterOuterAlt(_localctx, 13);
				{
				setState(2109);
				match(T__258);
				setState(2110);
				match(T__16);
				setState(2115);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__150:
					{
					setState(2111);
					match(T__150);
					}
					break;
				case T__22:
				case T__148:
				case T__149:
					{
					setState(2112);
					_la = _input.LA(1);
					if ( !(_la==T__22 || _la==T__148 || _la==T__149) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(2113);
					match(T__28);
					setState(2114);
					string();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(2117);
				match(T__17);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CompareContext extends ParserRuleContext {
		public TerminalNode OPERATOR() { return getToken(NormalSQLParser.OPERATOR, 0); }
		public CompareContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compare; }
	}

	public final CompareContext compare() throws RecognitionException {
		CompareContext _localctx = new CompareContext(_ctx, getState());
		enterRule(_localctx, 150, RULE_compare);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2120);
			_la = _input.LA(1);
			if ( !(_la==T__15 || ((((_la - 221)) & ~0x3f) == 0 && ((1L << (_la - 221)) & 1125350151028753L) != 0) || _la==OPERATOR) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LogicalsContext extends ParserRuleContext {
		public LogicalsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicals; }
	}

	public final LogicalsContext logicals() throws RecognitionException {
		LogicalsContext _localctx = new LogicalsContext(_ctx, getState());
		enterRule(_localctx, 152, RULE_logicals);
		try {
			setState(2128);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__270:
				enterOuterAlt(_localctx, 1);
				{
				setState(2122);
				match(T__270);
				}
				break;
			case T__271:
				enterOuterAlt(_localctx, 2);
				{
				setState(2123);
				match(T__271);
				}
				break;
			case T__272:
				enterOuterAlt(_localctx, 3);
				{
				setState(2124);
				match(T__272);
				}
				break;
			case T__273:
				enterOuterAlt(_localctx, 4);
				{
				setState(2125);
				match(T__273);
				setState(2126);
				match(T__48);
				}
				break;
			case T__274:
				enterOuterAlt(_localctx, 5);
				{
				setState(2127);
				match(T__274);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class JsonTypeContext extends ParserRuleContext {
		public JsonTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jsonType; }
	}

	public final JsonTypeContext jsonType() throws RecognitionException {
		JsonTypeContext _localctx = new JsonTypeContext(_ctx, getState());
		enterRule(_localctx, 154, RULE_jsonType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2130);
			_la = _input.LA(1);
			if ( !(_la==T__141 || ((((_la - 276)) & ~0x3f) == 0 && ((1L << (_la - 276)) & 7L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeContext extends ParserRuleContext {
		public List<NameContext> name() {
			return getRuleContexts(NameContext.class);
		}
		public NameContext name(int i) {
			return getRuleContext(NameContext.class,i);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public ScalarContext scalar() {
			return getRuleContext(ScalarContext.class,0);
		}
		public List<TerminalNode> INTEGER() { return getTokens(NormalSQLParser.INTEGER); }
		public TerminalNode INTEGER(int i) {
			return getToken(NormalSQLParser.INTEGER, i);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	}

	public final TypeContext type() throws RecognitionException {
		return type(0);
	}

	private TypeContext type(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TypeContext _localctx = new TypeContext(_ctx, _parentState);
		TypeContext _prevctx = _localctx;
		int _startState = 156;
		enterRecursionRule(_localctx, 156, RULE_type, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(2151);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,337,_ctx) ) {
			case 1:
				{
				setState(2133);
				match(T__100);
				setState(2134);
				match(T__16);
				setState(2135);
				name();
				setState(2136);
				type(0);
				setState(2143);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__28) {
					{
					{
					setState(2137);
					match(T__28);
					setState(2138);
					name();
					setState(2139);
					type(0);
					}
					}
					setState(2145);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(2146);
				match(T__17);
				}
				break;
			case 2:
				{
				setState(2148);
				match(T__278);
				setState(2149);
				type(4);
				}
				break;
			case 3:
				{
				setState(2150);
				scalar();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(2172);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,342,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(2170);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,341,_ctx) ) {
					case 1:
						{
						_localctx = new TypeContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_type);
						setState(2153);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(2154);
						match(T__275);
						setState(2158);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,338,_ctx) ) {
						case 1:
							{
							setState(2155);
							match(T__279);
							setState(2156);
							match(INTEGER);
							setState(2157);
							match(T__280);
							}
							break;
						}
						}
						break;
					case 2:
						{
						_localctx = new TypeContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_type);
						setState(2160);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(2166); 
						_errHandler.sync(this);
						_alt = 1;
						do {
							switch (_alt) {
							case 1:
								{
								{
								setState(2161);
								match(T__279);
								setState(2163);
								_errHandler.sync(this);
								_la = _input.LA(1);
								if (_la==INTEGER) {
									{
									setState(2162);
									match(INTEGER);
									}
								}

								setState(2165);
								match(T__280);
								}
								}
								break;
							default:
								throw new NoViableAltException(this);
							}
							setState(2168); 
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,340,_ctx);
						} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
						}
						break;
					}
					} 
				}
				setState(2174);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,342,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ScalarContext extends ParserRuleContext {
		public LengthContext length() {
			return getRuleContext(LengthContext.class,0);
		}
		public PrecisionContext precision() {
			return getRuleContext(PrecisionContext.class,0);
		}
		public CharsContext chars() {
			return getRuleContext(CharsContext.class,0);
		}
		public WithWithoutContext withWithout() {
			return getRuleContext(WithWithoutContext.class,0);
		}
		public TimeZoneContext timeZone() {
			return getRuleContext(TimeZoneContext.class,0);
		}
		public List<NameContext> name() {
			return getRuleContexts(NameContext.class);
		}
		public NameContext name(int i) {
			return getRuleContext(NameContext.class,i);
		}
		public ScalarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scalar; }
	}

	public final ScalarContext scalar() throws RecognitionException {
		ScalarContext _localctx = new ScalarContext(_ctx, getState());
		enterRule(_localctx, 158, RULE_scalar);
		int _la;
		try {
			int _alt;
			setState(2250);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,357,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(2175);
				match(T__281);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(2176);
				match(T__282);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(2177);
				match(T__283);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(2178);
				match(T__284);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(2179);
				match(T__285);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(2180);
				match(T__286);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(2181);
				match(T__287);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(2182);
				match(T__288);
				setState(2184);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,343,_ctx) ) {
				case 1:
					{
					setState(2183);
					length();
					}
					break;
				}
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(2186);
				match(T__289);
				setState(2187);
				match(T__290);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(2188);
				match(T__291);
				setState(2190);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,344,_ctx) ) {
				case 1:
					{
					setState(2189);
					precision();
					}
					break;
				}
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(2192);
				match(T__292);
				setState(2194);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,345,_ctx) ) {
				case 1:
					{
					setState(2193);
					precision();
					}
					break;
				}
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(2196);
				match(T__293);
				setState(2198);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,346,_ctx) ) {
				case 1:
					{
					setState(2197);
					precision();
					}
					break;
				}
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(2200);
				match(T__294);
				setState(2202);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,347,_ctx) ) {
				case 1:
					{
					setState(2201);
					precision();
					}
					break;
				}
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(2204);
				match(T__295);
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(2205);
				match(T__296);
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(2206);
				match(T__235);
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(2207);
				match(T__297);
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 18);
				{
				setState(2208);
				match(T__298);
				setState(2210);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,348,_ctx) ) {
				case 1:
					{
					setState(2209);
					match(T__299);
					}
					break;
				}
				setState(2213);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,349,_ctx) ) {
				case 1:
					{
					setState(2212);
					length();
					}
					break;
				}
				}
				break;
			case 19:
				enterOuterAlt(_localctx, 19);
				{
				setState(2215);
				chars();
				setState(2217);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,350,_ctx) ) {
				case 1:
					{
					setState(2216);
					length();
					}
					break;
				}
				}
				break;
			case 20:
				enterOuterAlt(_localctx, 20);
				{
				setState(2219);
				match(T__300);
				setState(2221);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,351,_ctx) ) {
				case 1:
					{
					setState(2220);
					precision();
					}
					break;
				}
				}
				break;
			case 21:
				enterOuterAlt(_localctx, 21);
				{
				setState(2223);
				match(T__301);
				}
				break;
			case 22:
				enterOuterAlt(_localctx, 22);
				{
				setState(2224);
				match(T__302);
				}
				break;
			case 23:
				enterOuterAlt(_localctx, 23);
				{
				setState(2225);
				match(T__303);
				}
				break;
			case 24:
				enterOuterAlt(_localctx, 24);
				{
				setState(2226);
				_la = _input.LA(1);
				if ( !(_la==T__304 || _la==T__305) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(2228);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,352,_ctx) ) {
				case 1:
					{
					setState(2227);
					length();
					}
					break;
				}
				setState(2236);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,354,_ctx) ) {
				case 1:
					{
					setState(2230);
					withWithout();
					setState(2232);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__49) {
						{
						setState(2231);
						match(T__49);
						}
					}

					setState(2234);
					timeZone();
					}
					break;
				}
				}
				break;
			case 25:
				enterOuterAlt(_localctx, 25);
				{
				setState(2238);
				match(T__306);
				}
				break;
			case 26:
				enterOuterAlt(_localctx, 26);
				{
				setState(2239);
				match(T__40);
				}
				break;
			case 27:
				enterOuterAlt(_localctx, 27);
				{
				setState(2240);
				match(T__307);
				}
				break;
			case 28:
				enterOuterAlt(_localctx, 28);
				{
				setState(2241);
				match(T__39);
				}
				break;
			case 29:
				enterOuterAlt(_localctx, 29);
				{
				setState(2243); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(2242);
						name();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(2245); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,355,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(2248);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,356,_ctx) ) {
				case 1:
					{
					setState(2247);
					precision();
					}
					break;
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CharsContext extends ParserRuleContext {
		public CharsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_chars; }
	}

	public final CharsContext chars() throws RecognitionException {
		CharsContext _localctx = new CharsContext(_ctx, getState());
		enterRule(_localctx, 160, RULE_chars);
		int _la;
		try {
			setState(2263);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__308:
			case T__309:
			case T__310:
				enterOuterAlt(_localctx, 1);
				{
				setState(2252);
				_la = _input.LA(1);
				if ( !(((((_la - 309)) & ~0x3f) == 0 && ((1L << (_la - 309)) & 7L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(2254);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,358,_ctx) ) {
				case 1:
					{
					setState(2253);
					match(T__299);
					}
					break;
				}
				}
				break;
			case T__311:
				enterOuterAlt(_localctx, 2);
				{
				setState(2256);
				match(T__311);
				}
				break;
			case T__312:
				enterOuterAlt(_localctx, 3);
				{
				setState(2257);
				match(T__312);
				}
				break;
			case T__313:
				enterOuterAlt(_localctx, 4);
				{
				setState(2258);
				match(T__313);
				setState(2259);
				_la = _input.LA(1);
				if ( !(_la==T__308 || _la==T__309) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(2261);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,359,_ctx) ) {
				case 1:
					{
					setState(2260);
					match(T__299);
					}
					break;
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LengthContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(NormalSQLParser.INTEGER, 0); }
		public LengthContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_length; }
	}

	public final LengthContext length() throws RecognitionException {
		LengthContext _localctx = new LengthContext(_ctx, getState());
		enterRule(_localctx, 162, RULE_length);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(2265);
			match(T__16);
			setState(2266);
			match(INTEGER);
			setState(2267);
			match(T__17);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PrecisionContext extends ParserRuleContext {
		public List<SubtermContext> subterm() {
			return getRuleContexts(SubtermContext.class);
		}
		public SubtermContext subterm(int i) {
			return getRuleContext(SubtermContext.class,i);
		}
		public PrecisionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_precision; }
	}

	public final PrecisionContext precision() throws RecognitionException {
		PrecisionContext _localctx = new PrecisionContext(_ctx, getState());
		enterRule(_localctx, 164, RULE_precision);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2269);
			match(T__16);
			setState(2270);
			subterm(0);
			setState(2273);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__28) {
				{
				setState(2271);
				match(T__28);
				setState(2272);
				subterm(0);
				}
			}

			setState(2275);
			match(T__17);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ValuesContext extends ParserRuleContext {
		public TermsContext terms() {
			return getRuleContext(TermsContext.class,0);
		}
		public ValuesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_values; }
	}

	public final ValuesContext values() throws RecognitionException {
		ValuesContext _localctx = new ValuesContext(_ctx, getState());
		enterRule(_localctx, 166, RULE_values);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2277);
			match(T__137);
			setState(2278);
			terms();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArrayContext extends ParserRuleContext {
		public ArrayTermsContext arrayTerms() {
			return getRuleContext(ArrayTermsContext.class,0);
		}
		public ArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array; }
	}

	public final ArrayContext array() throws RecognitionException {
		ArrayContext _localctx = new ArrayContext(_ctx, getState());
		enterRule(_localctx, 168, RULE_array);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2280);
			match(T__275);
			setState(2281);
			arrayTerms();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArrayTermsContext extends ParserRuleContext {
		public TermsContext terms() {
			return getRuleContext(TermsContext.class,0);
		}
		public ArrayNestedContext arrayNested() {
			return getRuleContext(ArrayNestedContext.class,0);
		}
		public ArrayTermsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayTerms; }
	}

	public final ArrayTermsContext arrayTerms() throws RecognitionException {
		ArrayTermsContext _localctx = new ArrayTermsContext(_ctx, getState());
		enterRule(_localctx, 170, RULE_arrayTerms);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2283);
			match(T__279);
			setState(2286);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
			case T__2:
			case T__3:
			case T__4:
			case T__5:
			case T__6:
			case T__7:
			case T__8:
			case T__9:
			case T__10:
			case T__11:
			case T__12:
			case T__14:
			case T__16:
			case T__18:
			case T__19:
			case T__20:
			case T__21:
			case T__22:
			case T__23:
			case T__24:
			case T__25:
			case T__26:
			case T__27:
			case T__29:
			case T__30:
			case T__31:
			case T__32:
			case T__33:
			case T__34:
			case T__35:
			case T__36:
			case T__37:
			case T__38:
			case T__39:
			case T__40:
			case T__41:
			case T__42:
			case T__43:
			case T__44:
			case T__45:
			case T__46:
			case T__47:
			case T__48:
			case T__49:
			case T__50:
			case T__51:
			case T__52:
			case T__53:
			case T__54:
			case T__55:
			case T__56:
			case T__57:
			case T__58:
			case T__59:
			case T__60:
			case T__62:
			case T__63:
			case T__64:
			case T__65:
			case T__66:
			case T__67:
			case T__68:
			case T__69:
			case T__70:
			case T__71:
			case T__72:
			case T__73:
			case T__74:
			case T__75:
			case T__76:
			case T__77:
			case T__78:
			case T__79:
			case T__80:
			case T__81:
			case T__82:
			case T__83:
			case T__84:
			case T__85:
			case T__86:
			case T__87:
			case T__88:
			case T__90:
			case T__91:
			case T__92:
			case T__93:
			case T__94:
			case T__95:
			case T__96:
			case T__97:
			case T__99:
			case T__100:
			case T__102:
			case T__103:
			case T__105:
			case T__106:
			case T__107:
			case T__108:
			case T__109:
			case T__110:
			case T__111:
			case T__112:
			case T__114:
			case T__115:
			case T__116:
			case T__118:
			case T__119:
			case T__120:
			case T__121:
			case T__122:
			case T__123:
			case T__124:
			case T__125:
			case T__126:
			case T__127:
			case T__128:
			case T__129:
			case T__130:
			case T__131:
			case T__132:
			case T__133:
			case T__134:
			case T__135:
			case T__136:
			case T__137:
			case T__138:
			case T__139:
			case T__140:
			case T__141:
			case T__142:
			case T__143:
			case T__144:
			case T__145:
			case T__147:
			case T__148:
			case T__149:
			case T__150:
			case T__151:
			case T__152:
			case T__154:
			case T__158:
			case T__160:
			case T__161:
			case T__162:
			case T__163:
			case T__164:
			case T__165:
			case T__166:
			case T__167:
			case T__168:
			case T__170:
			case T__171:
			case T__172:
			case T__173:
			case T__174:
			case T__175:
			case T__176:
			case T__177:
			case T__178:
			case T__179:
			case T__181:
			case T__183:
			case T__184:
			case T__185:
			case T__186:
			case T__187:
			case T__188:
			case T__190:
			case T__192:
			case T__193:
			case T__194:
			case T__195:
			case T__196:
			case T__198:
			case T__200:
			case T__201:
			case T__202:
			case T__203:
			case T__205:
			case T__207:
			case T__208:
			case T__209:
			case T__210:
			case T__211:
			case T__212:
			case T__214:
			case T__215:
			case T__216:
			case T__217:
			case T__218:
			case T__219:
			case T__220:
			case T__221:
			case T__222:
			case T__223:
			case T__224:
			case T__225:
			case T__226:
			case T__227:
			case T__228:
			case T__229:
			case T__230:
			case T__231:
			case T__232:
			case T__233:
			case T__234:
			case T__235:
			case T__236:
			case T__237:
			case T__238:
			case T__239:
			case T__240:
			case T__241:
			case T__242:
			case T__243:
			case T__244:
			case T__246:
			case T__247:
			case T__249:
			case T__251:
			case T__253:
			case T__254:
			case T__255:
			case T__256:
			case T__257:
			case T__258:
			case T__259:
			case T__267:
			case T__268:
			case T__269:
			case T__270:
			case T__271:
			case T__272:
			case T__273:
			case T__274:
			case T__275:
			case T__276:
			case T__277:
			case T__278:
			case T__281:
			case T__282:
			case T__283:
			case T__284:
			case T__285:
			case T__286:
			case T__287:
			case T__288:
			case T__289:
			case T__290:
			case T__291:
			case T__292:
			case T__293:
			case T__294:
			case T__295:
			case T__296:
			case T__297:
			case T__298:
			case T__299:
			case T__300:
			case T__301:
			case T__302:
			case T__303:
			case T__304:
			case T__305:
			case T__306:
			case T__307:
			case T__308:
			case T__309:
			case T__310:
			case T__311:
			case T__312:
			case T__313:
			case T__314:
			case T__318:
			case T__319:
			case T__320:
			case T__321:
			case T__322:
			case T__323:
			case T__324:
			case T__325:
			case T__326:
			case T__327:
			case T__328:
			case T__329:
			case T__330:
			case T__331:
			case T__332:
			case T__333:
			case T__334:
			case T__335:
			case T__336:
			case T__337:
			case T__338:
			case T__339:
			case T__340:
			case T__341:
			case T__342:
			case T__343:
			case T__344:
			case T__345:
			case T__346:
			case T__347:
			case T__348:
			case T__349:
			case T__350:
			case T__351:
			case T__352:
			case T__354:
			case T__355:
			case T__356:
			case T__357:
			case T__358:
			case T__359:
			case T__360:
			case T__361:
			case T__362:
			case T__363:
			case T__364:
			case T__365:
			case T__366:
			case T__367:
			case T__368:
			case T__369:
			case T__370:
			case T__371:
			case T__372:
			case T__373:
			case T__374:
			case T__375:
			case T__376:
			case T__377:
			case T__378:
			case T__379:
			case T__380:
			case T__381:
			case T__382:
			case T__383:
			case T__384:
			case T__385:
			case T__386:
			case T__387:
			case T__388:
			case T__389:
			case T__390:
			case T__391:
			case T__392:
			case T__393:
			case T__394:
			case T__395:
			case T__397:
			case T__398:
			case T__399:
			case T__400:
			case T__401:
			case UNICODE_NAME:
			case UNICODE_STRING:
			case NATIONAL_STRING:
			case STRING:
			case ID:
			case BRACKETS:
			case DOLLARS:
			case BITS:
			case BYTES:
			case OCTALS:
			case INTEGER:
			case FLOAT:
			case PARAMETER:
			case VARIABLE:
			case COMMENT:
			case BLOCK_COMMENT:
			case WHITESPACE:
			case OPERATOR:
				{
				setState(2284);
				terms();
				}
				break;
			case T__279:
				{
				setState(2285);
				arrayNested();
				}
				break;
			case T__280:
				break;
			default:
				break;
			}
			setState(2288);
			match(T__280);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArrayNestedContext extends ParserRuleContext {
		public List<ArrayTermsContext> arrayTerms() {
			return getRuleContexts(ArrayTermsContext.class);
		}
		public ArrayTermsContext arrayTerms(int i) {
			return getRuleContext(ArrayTermsContext.class,i);
		}
		public ArrayNestedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayNested; }
	}

	public final ArrayNestedContext arrayNested() throws RecognitionException {
		ArrayNestedContext _localctx = new ArrayNestedContext(_ctx, getState());
		enterRule(_localctx, 172, RULE_arrayNested);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2290);
			arrayTerms();
			setState(2295);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__28) {
				{
				{
				setState(2291);
				match(T__28);
				setState(2292);
				arrayTerms();
				}
				}
				setState(2297);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionContext extends ParserRuleContext {
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public JsonPairsContext jsonPairs() {
			return getRuleContext(JsonPairsContext.class,0);
		}
		public OnNullContext onNull() {
			return getRuleContext(OnNullContext.class,0);
		}
		public UniqueKeysContext uniqueKeys() {
			return getRuleContext(UniqueKeysContext.class,0);
		}
		public FilterContext filter() {
			return getRuleContext(FilterContext.class,0);
		}
		public OverContext over() {
			return getRuleContext(OverContext.class,0);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public OrderByContext orderBy() {
			return getRuleContext(OrderByContext.class,0);
		}
		public List<XmlAttribContext> xmlAttrib() {
			return getRuleContexts(XmlAttribContext.class);
		}
		public XmlAttribContext xmlAttrib(int i) {
			return getRuleContext(XmlAttribContext.class,i);
		}
		public TermsContext terms() {
			return getRuleContext(TermsContext.class,0);
		}
		public SubtermContext subterm() {
			return getRuleContext(SubtermContext.class,0);
		}
		public PassingContext passing() {
			return getRuleContext(PassingContext.class,0);
		}
		public XmlContentContext xmlContent() {
			return getRuleContext(XmlContentContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public SelectContext select() {
			return getRuleContext(SelectContext.class,0);
		}
		public FunctionContext function() {
			return getRuleContext(FunctionContext.class,0);
		}
		public QnameContext qname() {
			return getRuleContext(QnameContext.class,0);
		}
		public List<RespectIgnoreContext> respectIgnore() {
			return getRuleContexts(RespectIgnoreContext.class);
		}
		public RespectIgnoreContext respectIgnore(int i) {
			return getRuleContext(RespectIgnoreContext.class,i);
		}
		public WithinGroupContext withinGroup() {
			return getRuleContext(WithinGroupContext.class,0);
		}
		public FirstLastContext firstLast() {
			return getRuleContext(FirstLastContext.class,0);
		}
		public TableContext table() {
			return getRuleContext(TableContext.class,0);
		}
		public AllDistinctContext allDistinct() {
			return getRuleContext(AllDistinctContext.class,0);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 174, RULE_function);
		int _la;
		try {
			int _alt;
			setState(2528);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,398,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(2298);
				match(T__314);
				setState(2299);
				match(T__16);
				setState(2301);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 316)) & ~0x3f) == 0 && ((1L << (_la - 316)) & 7L) != 0)) {
					{
					setState(2300);
					_la = _input.LA(1);
					if ( !(((((_la - 316)) & ~0x3f) == 0 && ((1L << (_la - 316)) & 7L) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
				}

				setState(2304);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,365,_ctx) ) {
				case 1:
					{
					setState(2303);
					term(0);
					}
					break;
				}
				setState(2307);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,366,_ctx) ) {
				case 1:
					{
					setState(2306);
					match(T__135);
					}
					break;
				}
				setState(2309);
				term(0);
				setState(2310);
				match(T__17);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(2312);
				match(T__318);
				setState(2313);
				match(T__16);
				setState(2314);
				term(0);
				setState(2315);
				match(T__135);
				setState(2316);
				term(0);
				setState(2319);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__98) {
					{
					setState(2317);
					match(T__98);
					setState(2318);
					term(0);
					}
				}

				setState(2321);
				match(T__17);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(2323);
				match(T__319);
				setState(2324);
				match(T__16);
				setState(2325);
				jsonPairs();
				setState(2327);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__112 || _la==T__398) {
					{
					setState(2326);
					onNull();
					}
				}

				setState(2330);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 85)) & ~0x3f) == 0 && ((1L << (_la - 85)) & 4194307L) != 0)) {
					{
					setState(2329);
					uniqueKeys();
					}
				}

				setState(2332);
				match(T__17);
				setState(2334);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,370,_ctx) ) {
				case 1:
					{
					setState(2333);
					filter();
					}
					break;
				}
				setState(2337);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,371,_ctx) ) {
				case 1:
					{
					setState(2336);
					over();
					}
					break;
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(2339);
				match(T__320);
				setState(2340);
				match(T__16);
				setState(2341);
				name();
				setState(2342);
				match(T__135);
				setState(2346);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,372,_ctx);
				while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1+1 ) {
						{
						{
						setState(2343);
						matchWildcard();
						}
						} 
					}
					setState(2348);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,372,_ctx);
				}
				setState(2349);
				match(T__17);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(2351);
				match(T__321);
				setState(2352);
				match(T__16);
				setState(2353);
				_la = _input.LA(1);
				if ( !(_la==T__106 || _la==T__180) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(2354);
				name();
				setState(2356);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__361) {
					{
					setState(2355);
					orderBy();
					}
				}

				setState(2358);
				match(T__17);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(2360);
				match(T__322);
				setState(2361);
				match(T__16);
				setState(2362);
				xmlAttrib();
				setState(2367);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__28) {
					{
					{
					setState(2363);
					match(T__28);
					setState(2364);
					xmlAttrib();
					}
					}
					setState(2369);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(2370);
				match(T__17);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(2372);
				match(T__323);
				setState(2373);
				match(T__16);
				setState(2374);
				terms();
				setState(2375);
				match(T__17);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(2377);
				match(T__324);
				setState(2378);
				match(T__16);
				setState(2380);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,375,_ctx) ) {
				case 1:
					{
					setState(2379);
					match(T__325);
					}
					break;
				}
				setState(2382);
				name();
				setState(2385);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__28) {
					{
					setState(2383);
					match(T__28);
					setState(2384);
					terms();
					}
				}

				setState(2387);
				match(T__17);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(2389);
				match(T__326);
				setState(2390);
				match(T__16);
				setState(2391);
				subterm(0);
				setState(2393);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__209) {
					{
					setState(2392);
					passing();
					}
				}

				setState(2395);
				match(T__17);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(2397);
				match(T__327);
				setState(2398);
				match(T__16);
				setState(2399);
				xmlAttrib();
				setState(2404);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__28) {
					{
					{
					setState(2400);
					match(T__28);
					setState(2401);
					xmlAttrib();
					}
					}
					setState(2406);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(2407);
				match(T__17);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(2409);
				match(T__328);
				setState(2410);
				match(T__16);
				setState(2411);
				xmlContent();
				setState(2412);
				term(0);
				setState(2413);
				_la = _input.LA(1);
				if ( !(_la==T__329 || _la==T__330) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(2414);
				match(T__331);
				setState(2415);
				match(T__17);
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(2417);
				match(T__332);
				setState(2418);
				match(T__16);
				setState(2419);
				match(T__325);
				setState(2420);
				name();
				setState(2423);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__28) {
					{
					setState(2421);
					match(T__28);
					setState(2422);
					term(0);
					}
				}

				setState(2425);
				match(T__17);
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(2427);
				match(T__333);
				setState(2428);
				match(T__16);
				setState(2429);
				match(T__39);
				setState(2430);
				term(0);
				setState(2431);
				match(T__28);
				setState(2432);
				match(T__334);
				setState(2436);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,380,_ctx) ) {
				case 1:
					{
					setState(2433);
					term(0);
					}
					break;
				case 2:
					{
					setState(2434);
					match(T__86);
					setState(2435);
					match(T__141);
					}
					break;
				}
				setState(2438);
				match(T__28);
				setState(2439);
				match(T__335);
				setState(2445);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__336:
					{
					setState(2440);
					match(T__336);
					}
					break;
				case T__86:
					{
					setState(2441);
					match(T__86);
					setState(2443);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__141) {
						{
						setState(2442);
						match(T__141);
						}
					}

					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(2447);
				match(T__17);
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(2449);
				match(T__337);
				setState(2450);
				match(T__16);
				setState(2451);
				xmlContent();
				setState(2452);
				term(0);
				setState(2453);
				match(T__5);
				setState(2454);
				type(0);
				setState(2455);
				match(T__17);
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(2457);
				match(T__275);
				setState(2458);
				match(T__16);
				setState(2459);
				select(0);
				setState(2460);
				match(T__17);
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(2462);
				match(T__338);
				setState(2463);
				function();
				setState(2464);
				match(T__339);
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(2466);
				qname();
				setState(2467);
				match(T__16);
				setState(2468);
				term(0);
				setState(2470);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__150 || _la==T__399) {
					{
					setState(2469);
					respectIgnore();
					}
				}

				setState(2472);
				match(T__17);
				setState(2474);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,384,_ctx) ) {
				case 1:
					{
					setState(2473);
					respectIgnore();
					}
					break;
				}
				setState(2477);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,385,_ctx) ) {
				case 1:
					{
					setState(2476);
					over();
					}
					break;
				}
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 18);
				{
				setState(2479);
				qname();
				setState(2480);
				match(T__16);
				setState(2506);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,392,_ctx) ) {
				case 1:
					{
					setState(2484);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -4620693218219474948L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & -23672811834834945L) != 0) || ((((_la - 128)) & ~0x3f) == 0 && ((1L << (_la - 128)) & -4656726419123471361L) != 0) || ((((_la - 193)) & ~0x3f) == 0 && ((1L << (_la - 193)) & -1522243062332412065L) != 0) || ((((_la - 257)) & ~0x3f) == 0 && ((1L << (_la - 257)) & -4035225266149656561L) != 0) || ((((_la - 321)) & ~0x3f) == 0 && ((1L << (_la - 321)) & -562958543355905L) != 0) || ((((_la - 385)) & ~0x3f) == 0 && ((1L << (_la - 385)) & 67677712383L) != 0)) {
						{
						setState(2481);
						table();
						setState(2482);
						match(T__182);
						}
					}

					setState(2486);
					match(T__153);
					}
					break;
				case 2:
					{
					setState(2488);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,387,_ctx) ) {
					case 1:
						{
						setState(2487);
						allDistinct();
						}
						break;
					}
					setState(2490);
					terms();
					setState(2492);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__361) {
						{
						setState(2491);
						orderBy();
						}
					}

					setState(2497);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__97) {
						{
						setState(2494);
						match(T__97);
						setState(2495);
						match(T__340);
						setState(2496);
						match(T__341);
						}
					}

					setState(2501);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__342) {
						{
						setState(2499);
						match(T__342);
						setState(2500);
						term(0);
						}
					}

					setState(2504);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__112 || _la==T__398) {
						{
						setState(2503);
						onNull();
						}
					}

					}
					break;
				}
				setState(2508);
				match(T__17);
				setState(2510);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,393,_ctx) ) {
				case 1:
					{
					setState(2509);
					withinGroup();
					}
					break;
				}
				setState(2513);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,394,_ctx) ) {
				case 1:
					{
					setState(2512);
					filter();
					}
					break;
				}
				setState(2517);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,395,_ctx) ) {
				case 1:
					{
					setState(2515);
					match(T__135);
					setState(2516);
					firstLast();
					}
					break;
				}
				setState(2520);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,396,_ctx) ) {
				case 1:
					{
					setState(2519);
					respectIgnore();
					}
					break;
				}
				setState(2523);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,397,_ctx) ) {
				case 1:
					{
					setState(2522);
					over();
					}
					break;
				}
				}
				break;
			case 19:
				enterOuterAlt(_localctx, 19);
				{
				setState(2525);
				match(T__343);
				}
				break;
			case 20:
				enterOuterAlt(_localctx, 20);
				{
				setState(2526);
				match(T__344);
				}
				break;
			case 21:
				enterOuterAlt(_localctx, 21);
				{
				setState(2527);
				match(T__345);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WithinGroupContext extends ParserRuleContext {
		public OrderByContext orderBy() {
			return getRuleContext(OrderByContext.class,0);
		}
		public WithinGroupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_withinGroup; }
	}

	public final WithinGroupContext withinGroup() throws RecognitionException {
		WithinGroupContext _localctx = new WithinGroupContext(_ctx, getState());
		enterRule(_localctx, 176, RULE_withinGroup);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2530);
			match(T__346);
			setState(2531);
			match(T__199);
			setState(2532);
			match(T__16);
			setState(2533);
			orderBy();
			setState(2534);
			match(T__17);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FilterContext extends ParserRuleContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public FilterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_filter; }
	}

	public final FilterContext filter() throws RecognitionException {
		FilterContext _localctx = new FilterContext(_ctx, getState());
		enterRule(_localctx, 178, RULE_filter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2536);
			match(T__347);
			setState(2537);
			match(T__16);
			setState(2538);
			match(T__197);
			setState(2539);
			term(0);
			setState(2540);
			match(T__17);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OverContext extends ParserRuleContext {
		public WindowContext window() {
			return getRuleContext(WindowContext.class,0);
		}
		public OverContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_over; }
	}

	public final OverContext over() throws RecognitionException {
		OverContext _localctx = new OverContext(_ctx, getState());
		enterRule(_localctx, 180, RULE_over);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2542);
			match(T__348);
			setState(2543);
			window();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class XmlAttribContext extends ParserRuleContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public AliasContext alias() {
			return getRuleContext(AliasContext.class,0);
		}
		public XmlAttribContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_xmlAttrib; }
	}

	public final XmlAttribContext xmlAttrib() throws RecognitionException {
		XmlAttribContext _localctx = new XmlAttribContext(_ctx, getState());
		enterRule(_localctx, 182, RULE_xmlAttrib);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2545);
			term(0);
			setState(2548);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__5) {
				{
				setState(2546);
				match(T__5);
				setState(2547);
				alias();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class XmlContentContext extends ParserRuleContext {
		public XmlContentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_xmlContent; }
	}

	public final XmlContentContext xmlContent() throws RecognitionException {
		XmlContentContext _localctx = new XmlContentContext(_ctx, getState());
		enterRule(_localctx, 184, RULE_xmlContent);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2550);
			_la = _input.LA(1);
			if ( !(_la==T__349 || _la==T__350) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WindowContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public PartitionByContext partitionBy() {
			return getRuleContext(PartitionByContext.class,0);
		}
		public OrderByContext orderBy() {
			return getRuleContext(OrderByContext.class,0);
		}
		public WindowFrameContext windowFrame() {
			return getRuleContext(WindowFrameContext.class,0);
		}
		public WindowContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_window; }
	}

	public final WindowContext window() throws RecognitionException {
		WindowContext _localctx = new WindowContext(_ctx, getState());
		enterRule(_localctx, 186, RULE_window);
		int _la;
		try {
			setState(2567);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__16:
				enterOuterAlt(_localctx, 1);
				{
				setState(2552);
				match(T__16);
				setState(2554);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,400,_ctx) ) {
				case 1:
					{
					setState(2553);
					name();
					}
					break;
				}
				setState(2557);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__351) {
					{
					setState(2556);
					partitionBy();
					}
				}

				setState(2560);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__361) {
					{
					setState(2559);
					orderBy();
					}
				}

				setState(2563);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 353)) & ~0x3f) == 0 && ((1L << (_la - 353)) & 7L) != 0)) {
					{
					setState(2562);
					windowFrame();
					}
				}

				setState(2565);
				match(T__17);
				}
				break;
			case T__1:
			case T__2:
			case T__3:
			case T__4:
			case T__5:
			case T__6:
			case T__7:
			case T__8:
			case T__9:
			case T__10:
			case T__11:
			case T__12:
			case T__14:
			case T__18:
			case T__19:
			case T__20:
			case T__21:
			case T__22:
			case T__23:
			case T__24:
			case T__25:
			case T__26:
			case T__27:
			case T__29:
			case T__30:
			case T__31:
			case T__32:
			case T__33:
			case T__34:
			case T__35:
			case T__36:
			case T__37:
			case T__38:
			case T__39:
			case T__40:
			case T__41:
			case T__42:
			case T__43:
			case T__44:
			case T__45:
			case T__46:
			case T__47:
			case T__48:
			case T__49:
			case T__50:
			case T__51:
			case T__53:
			case T__54:
			case T__55:
			case T__56:
			case T__57:
			case T__58:
			case T__59:
			case T__60:
			case T__62:
			case T__63:
			case T__64:
			case T__65:
			case T__66:
			case T__67:
			case T__68:
			case T__69:
			case T__70:
			case T__71:
			case T__72:
			case T__73:
			case T__74:
			case T__75:
			case T__76:
			case T__77:
			case T__78:
			case T__79:
			case T__80:
			case T__81:
			case T__82:
			case T__83:
			case T__84:
			case T__86:
			case T__87:
			case T__88:
			case T__90:
			case T__91:
			case T__92:
			case T__93:
			case T__94:
			case T__95:
			case T__96:
			case T__99:
			case T__100:
			case T__102:
			case T__103:
			case T__105:
			case T__108:
			case T__109:
			case T__110:
			case T__111:
			case T__112:
			case T__114:
			case T__116:
			case T__118:
			case T__119:
			case T__120:
			case T__121:
			case T__122:
			case T__123:
			case T__124:
			case T__125:
			case T__126:
			case T__127:
			case T__128:
			case T__129:
			case T__130:
			case T__131:
			case T__132:
			case T__133:
			case T__134:
			case T__135:
			case T__136:
			case T__138:
			case T__139:
			case T__140:
			case T__141:
			case T__142:
			case T__143:
			case T__144:
			case T__145:
			case T__147:
			case T__148:
			case T__149:
			case T__150:
			case T__151:
			case T__152:
			case T__158:
			case T__160:
			case T__161:
			case T__162:
			case T__163:
			case T__164:
			case T__165:
			case T__166:
			case T__167:
			case T__168:
			case T__170:
			case T__171:
			case T__172:
			case T__173:
			case T__174:
			case T__175:
			case T__176:
			case T__177:
			case T__178:
			case T__179:
			case T__181:
			case T__183:
			case T__184:
			case T__185:
			case T__186:
			case T__187:
			case T__188:
			case T__190:
			case T__192:
			case T__193:
			case T__194:
			case T__195:
			case T__196:
			case T__198:
			case T__200:
			case T__201:
			case T__202:
			case T__203:
			case T__205:
			case T__207:
			case T__208:
			case T__209:
			case T__210:
			case T__211:
			case T__212:
			case T__214:
			case T__215:
			case T__216:
			case T__217:
			case T__218:
			case T__219:
			case T__220:
			case T__221:
			case T__222:
			case T__223:
			case T__224:
			case T__225:
			case T__226:
			case T__227:
			case T__228:
			case T__229:
			case T__230:
			case T__231:
			case T__232:
			case T__233:
			case T__234:
			case T__237:
			case T__238:
			case T__239:
			case T__240:
			case T__241:
			case T__242:
			case T__243:
			case T__244:
			case T__246:
			case T__247:
			case T__249:
			case T__251:
			case T__253:
			case T__254:
			case T__255:
			case T__256:
			case T__257:
			case T__258:
			case T__259:
			case T__267:
			case T__268:
			case T__269:
			case T__270:
			case T__271:
			case T__272:
			case T__273:
			case T__274:
			case T__276:
			case T__277:
			case T__278:
			case T__281:
			case T__282:
			case T__283:
			case T__284:
			case T__285:
			case T__286:
			case T__287:
			case T__288:
			case T__289:
			case T__290:
			case T__291:
			case T__292:
			case T__293:
			case T__294:
			case T__295:
			case T__296:
			case T__297:
			case T__298:
			case T__299:
			case T__300:
			case T__301:
			case T__302:
			case T__303:
			case T__304:
			case T__305:
			case T__306:
			case T__307:
			case T__308:
			case T__309:
			case T__310:
			case T__311:
			case T__312:
			case T__313:
			case T__314:
			case T__318:
			case T__319:
			case T__320:
			case T__321:
			case T__322:
			case T__323:
			case T__324:
			case T__325:
			case T__326:
			case T__327:
			case T__328:
			case T__329:
			case T__330:
			case T__331:
			case T__332:
			case T__333:
			case T__334:
			case T__335:
			case T__336:
			case T__337:
			case T__338:
			case T__339:
			case T__340:
			case T__341:
			case T__342:
			case T__343:
			case T__344:
			case T__345:
			case T__346:
			case T__347:
			case T__348:
			case T__349:
			case T__350:
			case T__351:
			case T__352:
			case T__354:
			case T__355:
			case T__356:
			case T__357:
			case T__358:
			case T__359:
			case T__360:
			case T__361:
			case T__362:
			case T__363:
			case T__364:
			case T__365:
			case T__366:
			case T__367:
			case T__368:
			case T__370:
			case T__371:
			case T__372:
			case T__373:
			case T__374:
			case T__375:
			case T__376:
			case T__377:
			case T__378:
			case T__379:
			case T__380:
			case T__381:
			case T__382:
			case T__383:
			case T__384:
			case T__385:
			case T__386:
			case T__387:
			case T__388:
			case T__389:
			case T__390:
			case T__391:
			case T__392:
			case T__393:
			case T__394:
			case T__395:
			case T__397:
			case T__398:
			case T__399:
			case T__400:
			case T__401:
			case UNICODE_NAME:
			case STRING:
			case ID:
			case BRACKETS:
			case DOLLARS:
			case PARAMETER:
			case VARIABLE:
			case COMMENT:
			case BLOCK_COMMENT:
			case WHITESPACE:
			case OPERATOR:
				enterOuterAlt(_localctx, 2);
				{
				setState(2566);
				name();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PartitionByContext extends ParserRuleContext {
		public TermsContext terms() {
			return getRuleContext(TermsContext.class,0);
		}
		public PartitionByContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_partitionBy; }
	}

	public final PartitionByContext partitionBy() throws RecognitionException {
		PartitionByContext _localctx = new PartitionByContext(_ctx, getState());
		enterRule(_localctx, 188, RULE_partitionBy);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2569);
			match(T__351);
			setState(2570);
			match(T__133);
			setState(2571);
			terms();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WindowFrameContext extends ParserRuleContext {
		public PrecedingContext preceding() {
			return getRuleContext(PrecedingContext.class,0);
		}
		public List<FollowingContext> following() {
			return getRuleContexts(FollowingContext.class);
		}
		public FollowingContext following(int i) {
			return getRuleContext(FollowingContext.class,i);
		}
		public WindowFrameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_windowFrame; }
	}

	public final WindowFrameContext windowFrame() throws RecognitionException {
		WindowFrameContext _localctx = new WindowFrameContext(_ctx, getState());
		enterRule(_localctx, 190, RULE_windowFrame);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2573);
			_la = _input.LA(1);
			if ( !(((((_la - 353)) & ~0x3f) == 0 && ((1L << (_la - 353)) & 7L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(2580);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
			case T__2:
			case T__3:
			case T__4:
			case T__5:
			case T__6:
			case T__7:
			case T__8:
			case T__9:
			case T__10:
			case T__11:
			case T__12:
			case T__14:
			case T__16:
			case T__18:
			case T__19:
			case T__20:
			case T__21:
			case T__22:
			case T__23:
			case T__24:
			case T__25:
			case T__26:
			case T__27:
			case T__29:
			case T__30:
			case T__31:
			case T__32:
			case T__33:
			case T__34:
			case T__35:
			case T__36:
			case T__37:
			case T__38:
			case T__39:
			case T__40:
			case T__41:
			case T__42:
			case T__43:
			case T__44:
			case T__45:
			case T__46:
			case T__47:
			case T__48:
			case T__49:
			case T__50:
			case T__51:
			case T__52:
			case T__53:
			case T__54:
			case T__55:
			case T__56:
			case T__57:
			case T__58:
			case T__59:
			case T__60:
			case T__62:
			case T__63:
			case T__64:
			case T__65:
			case T__66:
			case T__67:
			case T__68:
			case T__69:
			case T__70:
			case T__71:
			case T__72:
			case T__73:
			case T__74:
			case T__75:
			case T__76:
			case T__77:
			case T__78:
			case T__79:
			case T__80:
			case T__81:
			case T__82:
			case T__83:
			case T__84:
			case T__85:
			case T__86:
			case T__87:
			case T__88:
			case T__90:
			case T__91:
			case T__92:
			case T__93:
			case T__94:
			case T__95:
			case T__96:
			case T__97:
			case T__99:
			case T__100:
			case T__102:
			case T__103:
			case T__105:
			case T__106:
			case T__107:
			case T__108:
			case T__109:
			case T__110:
			case T__111:
			case T__112:
			case T__114:
			case T__115:
			case T__116:
			case T__118:
			case T__119:
			case T__120:
			case T__121:
			case T__122:
			case T__123:
			case T__124:
			case T__125:
			case T__126:
			case T__127:
			case T__128:
			case T__129:
			case T__130:
			case T__131:
			case T__132:
			case T__133:
			case T__134:
			case T__135:
			case T__136:
			case T__137:
			case T__138:
			case T__139:
			case T__140:
			case T__141:
			case T__142:
			case T__143:
			case T__144:
			case T__145:
			case T__147:
			case T__148:
			case T__149:
			case T__150:
			case T__151:
			case T__152:
			case T__154:
			case T__158:
			case T__160:
			case T__161:
			case T__162:
			case T__163:
			case T__164:
			case T__165:
			case T__166:
			case T__167:
			case T__168:
			case T__170:
			case T__171:
			case T__172:
			case T__173:
			case T__174:
			case T__175:
			case T__176:
			case T__177:
			case T__178:
			case T__179:
			case T__181:
			case T__183:
			case T__184:
			case T__185:
			case T__186:
			case T__187:
			case T__188:
			case T__190:
			case T__192:
			case T__193:
			case T__194:
			case T__195:
			case T__196:
			case T__198:
			case T__200:
			case T__201:
			case T__202:
			case T__203:
			case T__205:
			case T__207:
			case T__208:
			case T__209:
			case T__210:
			case T__211:
			case T__212:
			case T__214:
			case T__215:
			case T__216:
			case T__217:
			case T__218:
			case T__219:
			case T__220:
			case T__221:
			case T__222:
			case T__223:
			case T__224:
			case T__225:
			case T__226:
			case T__227:
			case T__228:
			case T__229:
			case T__230:
			case T__231:
			case T__232:
			case T__233:
			case T__234:
			case T__235:
			case T__236:
			case T__237:
			case T__238:
			case T__239:
			case T__240:
			case T__241:
			case T__242:
			case T__243:
			case T__244:
			case T__246:
			case T__247:
			case T__249:
			case T__251:
			case T__253:
			case T__254:
			case T__255:
			case T__256:
			case T__257:
			case T__258:
			case T__259:
			case T__267:
			case T__268:
			case T__269:
			case T__270:
			case T__271:
			case T__272:
			case T__273:
			case T__274:
			case T__275:
			case T__276:
			case T__277:
			case T__278:
			case T__281:
			case T__282:
			case T__283:
			case T__284:
			case T__285:
			case T__286:
			case T__287:
			case T__288:
			case T__289:
			case T__290:
			case T__291:
			case T__292:
			case T__293:
			case T__294:
			case T__295:
			case T__296:
			case T__297:
			case T__298:
			case T__299:
			case T__300:
			case T__301:
			case T__302:
			case T__303:
			case T__304:
			case T__305:
			case T__306:
			case T__307:
			case T__308:
			case T__309:
			case T__310:
			case T__311:
			case T__312:
			case T__313:
			case T__314:
			case T__318:
			case T__319:
			case T__320:
			case T__321:
			case T__322:
			case T__323:
			case T__324:
			case T__325:
			case T__326:
			case T__327:
			case T__328:
			case T__329:
			case T__330:
			case T__331:
			case T__332:
			case T__333:
			case T__334:
			case T__335:
			case T__336:
			case T__337:
			case T__338:
			case T__339:
			case T__340:
			case T__341:
			case T__342:
			case T__343:
			case T__344:
			case T__345:
			case T__346:
			case T__347:
			case T__348:
			case T__349:
			case T__350:
			case T__351:
			case T__352:
			case T__354:
			case T__355:
			case T__356:
			case T__357:
			case T__358:
			case T__359:
			case T__360:
			case T__361:
			case T__362:
			case T__363:
			case T__364:
			case T__365:
			case T__366:
			case T__367:
			case T__368:
			case T__369:
			case T__370:
			case T__371:
			case T__372:
			case T__373:
			case T__374:
			case T__375:
			case T__376:
			case T__377:
			case T__378:
			case T__379:
			case T__380:
			case T__381:
			case T__382:
			case T__383:
			case T__384:
			case T__385:
			case T__386:
			case T__387:
			case T__388:
			case T__389:
			case T__390:
			case T__391:
			case T__392:
			case T__393:
			case T__394:
			case T__395:
			case T__397:
			case T__398:
			case T__399:
			case T__400:
			case T__401:
			case UNICODE_NAME:
			case UNICODE_STRING:
			case NATIONAL_STRING:
			case STRING:
			case ID:
			case BRACKETS:
			case DOLLARS:
			case BITS:
			case BYTES:
			case OCTALS:
			case INTEGER:
			case FLOAT:
			case PARAMETER:
			case VARIABLE:
			case COMMENT:
			case BLOCK_COMMENT:
			case WHITESPACE:
			case OPERATOR:
				{
				setState(2574);
				preceding();
				}
				break;
			case T__250:
				{
				setState(2575);
				match(T__250);
				setState(2576);
				following();
				setState(2577);
				match(T__146);
				setState(2578);
				following();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(2591);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__195) {
				{
				setState(2582);
				match(T__195);
				setState(2589);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__198:
					{
					setState(2583);
					match(T__198);
					setState(2584);
					match(T__100);
					}
					break;
				case T__199:
					{
					setState(2585);
					match(T__199);
					}
					break;
				case T__355:
					{
					setState(2586);
					match(T__355);
					}
					break;
				case T__86:
					{
					setState(2587);
					match(T__86);
					setState(2588);
					match(T__356);
					}
					break;
				case T__17:
					break;
				default:
					break;
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PrecedingContext extends ParserRuleContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public PrecedingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_preceding; }
	}

	public final PrecedingContext preceding() throws RecognitionException {
		PrecedingContext _localctx = new PrecedingContext(_ctx, getState());
		enterRule(_localctx, 192, RULE_preceding);
		try {
			setState(2601);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,409,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(2596);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,408,_ctx) ) {
				case 1:
					{
					setState(2593);
					match(T__357);
					}
					break;
				case 2:
					{
					setState(2594);
					match(T__358);
					}
					break;
				case 3:
					{
					setState(2595);
					term(0);
					}
					break;
				}
				setState(2598);
				match(T__359);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(2599);
				match(T__198);
				setState(2600);
				match(T__100);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FollowingContext extends ParserRuleContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public PrecedingContext preceding() {
			return getRuleContext(PrecedingContext.class,0);
		}
		public FollowingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_following; }
	}

	public final FollowingContext following() throws RecognitionException {
		FollowingContext _localctx = new FollowingContext(_ctx, getState());
		enterRule(_localctx, 194, RULE_following);
		try {
			setState(2609);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,411,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(2605);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,410,_ctx) ) {
				case 1:
					{
					setState(2603);
					match(T__357);
					}
					break;
				case 2:
					{
					setState(2604);
					term(0);
					}
					break;
				}
				setState(2607);
				match(T__360);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(2608);
				preceding();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OrderByContext extends ParserRuleContext {
		public List<OrderByItemContext> orderByItem() {
			return getRuleContexts(OrderByItemContext.class);
		}
		public OrderByItemContext orderByItem(int i) {
			return getRuleContext(OrderByItemContext.class,i);
		}
		public OrderByContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orderBy; }
	}

	public final OrderByContext orderBy() throws RecognitionException {
		OrderByContext _localctx = new OrderByContext(_ctx, getState());
		enterRule(_localctx, 196, RULE_orderBy);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(2611);
			match(T__361);
			setState(2612);
			match(T__133);
			setState(2613);
			orderByItem();
			setState(2618);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,412,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(2614);
					match(T__28);
					setState(2615);
					orderByItem();
					}
					} 
				}
				setState(2620);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,412,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OrderByItemContext extends ParserRuleContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public SortDirContext sortDir() {
			return getRuleContext(SortDirContext.class,0);
		}
		public FirstLastContext firstLast() {
			return getRuleContext(FirstLastContext.class,0);
		}
		public OrderByItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orderByItem; }
	}

	public final OrderByItemContext orderByItem() throws RecognitionException {
		OrderByItemContext _localctx = new OrderByItemContext(_ctx, getState());
		enterRule(_localctx, 198, RULE_orderByItem);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2621);
			term(0);
			setState(2623);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,413,_ctx) ) {
			case 1:
				{
				setState(2622);
				sortDir();
				}
				break;
			}
			setState(2627);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,414,_ctx) ) {
			case 1:
				{
				setState(2625);
				match(T__196);
				setState(2626);
				firstLast();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SortDirContext extends ParserRuleContext {
		public CompareContext compare() {
			return getRuleContext(CompareContext.class,0);
		}
		public SortDirContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sortDir; }
	}

	public final SortDirContext sortDir() throws RecognitionException {
		SortDirContext _localctx = new SortDirContext(_ctx, getState());
		enterRule(_localctx, 200, RULE_sortDir);
		try {
			setState(2633);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__362:
				enterOuterAlt(_localctx, 1);
				{
				setState(2629);
				match(T__362);
				}
				break;
			case T__363:
				enterOuterAlt(_localctx, 2);
				{
				setState(2630);
				match(T__363);
				}
				break;
			case T__89:
				enterOuterAlt(_localctx, 3);
				{
				setState(2631);
				match(T__89);
				setState(2632);
				compare();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(NormalSQLParser.INTEGER, 0); }
		public TerminalNode FLOAT() { return getToken(NormalSQLParser.FLOAT, 0); }
		public TerminalNode BITS() { return getToken(NormalSQLParser.BITS, 0); }
		public TerminalNode BYTES() { return getToken(NormalSQLParser.BYTES, 0); }
		public TerminalNode OCTALS() { return getToken(NormalSQLParser.OCTALS, 0); }
		public TruthContext truth() {
			return getRuleContext(TruthContext.class,0);
		}
		public BooleanContext boolean_() {
			return getRuleContext(BooleanContext.class,0);
		}
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public WithWithoutContext withWithout() {
			return getRuleContext(WithWithoutContext.class,0);
		}
		public TimeZoneContext timeZone() {
			return getRuleContext(TimeZoneContext.class,0);
		}
		public JsonObjectContext jsonObject() {
			return getRuleContext(JsonObjectContext.class,0);
		}
		public JsonArrayContext jsonArray() {
			return getRuleContext(JsonArrayContext.class,0);
		}
		public TerminalNode PARAMETER() { return getToken(NormalSQLParser.PARAMETER, 0); }
		public TerminalNode VARIABLE() { return getToken(NormalSQLParser.VARIABLE, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 202, RULE_literal);
		int _la;
		try {
			setState(2665);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,419,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(2636);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__222 || _la==T__223) {
					{
					setState(2635);
					_la = _input.LA(1);
					if ( !(_la==T__222 || _la==T__223) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
				}

				setState(2638);
				_la = _input.LA(1);
				if ( !(_la==INTEGER || _la==FLOAT) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(2639);
				match(BITS);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(2640);
				match(BYTES);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(2641);
				match(OCTALS);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(2642);
				truth();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(2643);
				boolean_();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(2644);
				match(T__115);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(2645);
				match(T__303);
				setState(2646);
				string();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(2647);
				_la = _input.LA(1);
				if ( !(((((_la - 365)) & ~0x3f) == 0 && ((1L << (_la - 365)) & 7L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(2648);
				string();
				setState(2649);
				match(T__339);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(2651);
				_la = _input.LA(1);
				if ( !(_la==T__304 || _la==T__305) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(2655);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,417,_ctx) ) {
				case 1:
					{
					setState(2652);
					withWithout();
					setState(2653);
					timeZone();
					}
					break;
				}
				setState(2658);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,418,_ctx) ) {
				case 1:
					{
					setState(2657);
					string();
					}
					break;
				}
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(2660);
				jsonObject();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(2661);
				jsonArray();
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(2662);
				match(PARAMETER);
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(2663);
				match(VARIABLE);
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(2664);
				string();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TruthContext extends ParserRuleContext {
		public TruthContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_truth; }
	}

	public final TruthContext truth() throws RecognitionException {
		TruthContext _localctx = new TruthContext(_ctx, getState());
		enterRule(_localctx, 204, RULE_truth);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2667);
			_la = _input.LA(1);
			if ( !(_la==T__112 || ((((_la - 368)) & ~0x3f) == 0 && ((1L << (_la - 368)) & 7L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BooleanContext extends ParserRuleContext {
		public BooleanContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolean; }
	}

	public final BooleanContext boolean_() throws RecognitionException {
		BooleanContext _localctx = new BooleanContext(_ctx, getState());
		enterRule(_localctx, 206, RULE_boolean);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2669);
			_la = _input.LA(1);
			if ( !(_la==T__97 || ((((_la - 368)) & ~0x3f) == 0 && ((1L << (_la - 368)) & 11L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IntervalContext extends ParserRuleContext {
		public List<TimeUnitContext> timeUnit() {
			return getRuleContexts(TimeUnitContext.class);
		}
		public TimeUnitContext timeUnit(int i) {
			return getRuleContext(TimeUnitContext.class,i);
		}
		public List<PrecisionContext> precision() {
			return getRuleContexts(PrecisionContext.class);
		}
		public PrecisionContext precision(int i) {
			return getRuleContext(PrecisionContext.class,i);
		}
		public IntervalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interval; }
	}

	public final IntervalContext interval() throws RecognitionException {
		IntervalContext _localctx = new IntervalContext(_ctx, getState());
		enterRule(_localctx, 208, RULE_interval);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2671);
			timeUnit();
			setState(2673);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,420,_ctx) ) {
			case 1:
				{
				setState(2672);
				precision();
				}
				break;
			}
			setState(2680);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,422,_ctx) ) {
			case 1:
				{
				setState(2675);
				match(T__23);
				setState(2676);
				timeUnit();
				setState(2678);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,421,_ctx) ) {
				case 1:
					{
					setState(2677);
					precision();
					}
					break;
				}
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TimeUnitContext extends ParserRuleContext {
		public TimeUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_timeUnit; }
	}

	public final TimeUnitContext timeUnit() throws RecognitionException {
		TimeUnitContext _localctx = new TimeUnitContext(_ctx, getState());
		enterRule(_localctx, 210, RULE_timeUnit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2682);
			_la = _input.LA(1);
			if ( !(((((_la - 372)) & ~0x3f) == 0 && ((1L << (_la - 372)) & 4194303L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class JsonArrayContext extends ParserRuleContext {
		public TermsContext terms() {
			return getRuleContext(TermsContext.class,0);
		}
		public SelectContext select() {
			return getRuleContext(SelectContext.class,0);
		}
		public FormatJsonContext formatJson() {
			return getRuleContext(FormatJsonContext.class,0);
		}
		public OnNullContext onNull() {
			return getRuleContext(OnNullContext.class,0);
		}
		public JsonArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jsonArray; }
	}

	public final JsonArrayContext jsonArray() throws RecognitionException {
		JsonArrayContext _localctx = new JsonArrayContext(_ctx, getState());
		enterRule(_localctx, 212, RULE_jsonArray);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2684);
			match(T__393);
			setState(2685);
			match(T__16);
			setState(2691);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,423,_ctx) ) {
			case 1:
				{
				setState(2686);
				terms();
				}
				break;
			case 2:
				{
				setState(2687);
				match(T__16);
				setState(2688);
				select(0);
				setState(2689);
				match(T__17);
				}
				break;
			}
			setState(2694);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__37) {
				{
				setState(2693);
				formatJson();
				}
			}

			setState(2697);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__112 || _la==T__398) {
				{
				setState(2696);
				onNull();
				}
			}

			setState(2699);
			match(T__17);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class JsonObjectContext extends ParserRuleContext {
		public JsonPairsContext jsonPairs() {
			return getRuleContext(JsonPairsContext.class,0);
		}
		public OnNullContext onNull() {
			return getRuleContext(OnNullContext.class,0);
		}
		public UniqueKeysContext uniqueKeys() {
			return getRuleContext(UniqueKeysContext.class,0);
		}
		public FormatJsonContext formatJson() {
			return getRuleContext(FormatJsonContext.class,0);
		}
		public JsonObjectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jsonObject; }
	}

	public final JsonObjectContext jsonObject() throws RecognitionException {
		JsonObjectContext _localctx = new JsonObjectContext(_ctx, getState());
		enterRule(_localctx, 214, RULE_jsonObject);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2701);
			match(T__394);
			setState(2702);
			match(T__16);
			setState(2704);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,426,_ctx) ) {
			case 1:
				{
				setState(2703);
				jsonPairs();
				}
				break;
			}
			setState(2707);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__112 || _la==T__398) {
				{
				setState(2706);
				onNull();
				}
			}

			setState(2710);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 85)) & ~0x3f) == 0 && ((1L << (_la - 85)) & 4194307L) != 0)) {
				{
				setState(2709);
				uniqueKeys();
				}
			}

			setState(2713);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__37) {
				{
				setState(2712);
				formatJson();
				}
			}

			setState(2715);
			match(T__17);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class JsonPairsContext extends ParserRuleContext {
		public List<JsonPairContext> jsonPair() {
			return getRuleContexts(JsonPairContext.class);
		}
		public JsonPairContext jsonPair(int i) {
			return getRuleContext(JsonPairContext.class,i);
		}
		public JsonPairsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jsonPairs; }
	}

	public final JsonPairsContext jsonPairs() throws RecognitionException {
		JsonPairsContext _localctx = new JsonPairsContext(_ctx, getState());
		enterRule(_localctx, 216, RULE_jsonPairs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2717);
			jsonPair();
			setState(2722);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__28) {
				{
				{
				setState(2718);
				match(T__28);
				setState(2719);
				jsonPair();
				}
				}
				setState(2724);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class JsonPairContext extends ParserRuleContext {
		public JsonKeyContext jsonKey() {
			return getRuleContext(JsonKeyContext.class,0);
		}
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public JsonPairContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jsonPair; }
	}

	public final JsonPairContext jsonPair() throws RecognitionException {
		JsonPairContext _localctx = new JsonPairContext(_ctx, getState());
		enterRule(_localctx, 218, RULE_jsonPair);
		try {
			setState(2736);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,432,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(2725);
				jsonKey();
				setState(2726);
				match(T__395);
				setState(2727);
				term(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(2730);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,431,_ctx) ) {
				case 1:
					{
					setState(2729);
					match(T__110);
					}
					break;
				}
				setState(2732);
				jsonKey();
				setState(2733);
				match(T__141);
				setState(2734);
				term(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class JsonKeyContext extends ParserRuleContext {
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public JsonKeyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jsonKey; }
	}

	public final JsonKeyContext jsonKey() throws RecognitionException {
		JsonKeyContext _localctx = new JsonKeyContext(_ctx, getState());
		enterRule(_localctx, 220, RULE_jsonKey);
		try {
			setState(2741);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,433,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(2738);
				match(T__112);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(2739);
				string();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(2740);
				name();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ColumnsContext extends ParserRuleContext {
		public List<ColumnContext> column() {
			return getRuleContexts(ColumnContext.class);
		}
		public ColumnContext column(int i) {
			return getRuleContext(ColumnContext.class,i);
		}
		public ColumnsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columns; }
	}

	public final ColumnsContext columns() throws RecognitionException {
		ColumnsContext _localctx = new ColumnsContext(_ctx, getState());
		enterRule(_localctx, 222, RULE_columns);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2743);
			match(T__16);
			setState(2744);
			column();
			setState(2749);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__28) {
				{
				{
				setState(2745);
				match(T__28);
				setState(2746);
				column();
				}
				}
				setState(2751);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(2752);
			match(T__17);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ColumnContext extends ParserRuleContext {
		public List<NameContext> name() {
			return getRuleContexts(NameContext.class);
		}
		public NameContext name(int i) {
			return getRuleContext(NameContext.class,i);
		}
		public ColumnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column; }
	}

	public final ColumnContext column() throws RecognitionException {
		ColumnContext _localctx = new ColumnContext(_ctx, getState());
		enterRule(_localctx, 224, RULE_column);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(2754);
			name();
			setState(2759);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,435,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(2755);
					match(T__182);
					setState(2756);
					name();
					}
					} 
				}
				setState(2761);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,435,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TableContext extends ParserRuleContext {
		public List<NameContext> name() {
			return getRuleContexts(NameContext.class);
		}
		public NameContext name(int i) {
			return getRuleContext(NameContext.class,i);
		}
		public TableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table; }
	}

	public final TableContext table() throws RecognitionException {
		TableContext _localctx = new TableContext(_ctx, getState());
		enterRule(_localctx, 226, RULE_table);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(2762);
			name();
			setState(2767);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,436,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(2763);
					match(T__182);
					setState(2764);
					name();
					}
					} 
				}
				setState(2769);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,436,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QnameContext extends ParserRuleContext {
		public TerminalNode VARIABLE() { return getToken(NormalSQLParser.VARIABLE, 0); }
		public TerminalNode PARAMETER() { return getToken(NormalSQLParser.PARAMETER, 0); }
		public List<NameContext> name() {
			return getRuleContexts(NameContext.class);
		}
		public NameContext name(int i) {
			return getRuleContext(NameContext.class,i);
		}
		public QnameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qname; }
	}

	public final QnameContext qname() throws RecognitionException {
		QnameContext _localctx = new QnameContext(_ctx, getState());
		enterRule(_localctx, 228, RULE_qname);
		try {
			int _alt;
			setState(2780);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,438,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(2770);
				match(VARIABLE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(2771);
				match(PARAMETER);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(2772);
				name();
				setState(2777);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,437,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(2773);
						match(T__182);
						setState(2774);
						name();
						}
						} 
					}
					setState(2779);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,437,_ctx);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IndexContext extends ParserRuleContext {
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public IndexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_index; }
	}

	public final IndexContext index() throws RecognitionException {
		IndexContext _localctx = new IndexContext(_ctx, getState());
		enterRule(_localctx, 230, RULE_index);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2782);
			match(T__279);
			setState(2791);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,441,_ctx) ) {
			case 1:
				{
				setState(2783);
				term(0);
				}
				break;
			case 2:
				{
				setState(2785);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,439,_ctx) ) {
				case 1:
					{
					setState(2784);
					term(0);
					}
					break;
				}
				setState(2787);
				match(T__395);
				setState(2789);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -4611686018964602884L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & -19142806744334337L) != 0) || ((((_la - 128)) & ~0x3f) == 0 && ((1L << (_la - 128)) & -4656726418989252609L) != 0) || ((((_la - 193)) & ~0x3f) == 0 && ((1L << (_la - 193)) & -1522216674053345441L) != 0) || ((((_la - 257)) & ~0x3f) == 0 && ((1L << (_la - 257)) & -4035225266149132273L) != 0) || ((((_la - 321)) & ~0x3f) == 0 && ((1L << (_la - 321)) & -8589934593L) != 0) || ((((_la - 385)) & ~0x3f) == 0 && ((1L << (_la - 385)) & 68719472639L) != 0)) {
					{
					setState(2788);
					term(0);
					}
				}

				}
				break;
			}
			setState(2793);
			match(T__280);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UnreservedContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(NormalSQLParser.INTEGER, 0); }
		public TerminalNode FLOAT() { return getToken(NormalSQLParser.FLOAT, 0); }
		public TerminalNode BITS() { return getToken(NormalSQLParser.BITS, 0); }
		public TerminalNode BYTES() { return getToken(NormalSQLParser.BYTES, 0); }
		public TerminalNode OCTALS() { return getToken(NormalSQLParser.OCTALS, 0); }
		public TerminalNode PARAMETER() { return getToken(NormalSQLParser.PARAMETER, 0); }
		public TerminalNode VARIABLE() { return getToken(NormalSQLParser.VARIABLE, 0); }
		public TerminalNode STRING() { return getToken(NormalSQLParser.STRING, 0); }
		public TerminalNode UNICODE_STRING() { return getToken(NormalSQLParser.UNICODE_STRING, 0); }
		public TerminalNode NATIONAL_STRING() { return getToken(NormalSQLParser.NATIONAL_STRING, 0); }
		public UnreservedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unreserved; }
	}

	public final UnreservedContext unreserved() throws RecognitionException {
		UnreservedContext _localctx = new UnreservedContext(_ctx, getState());
		enterRule(_localctx, 232, RULE_unreserved);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2795);
			_la = _input.LA(1);
			if ( _la <= 0 || ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4620693218219474946L) != 0) || ((((_la - 86)) & ~0x3f) == 0 && ((1L << (_la - 86)) & 2310346614485102609L) != 0) || ((((_la - 154)) & ~0x3f) == 0 && ((1L << (_la - 154)) & 1164268808874033247L) != 0) || ((((_la - 236)) & ~0x3f) == 0 && ((1L << (_la - 236)) & 53880331346947L) != 0) || ((((_la - 316)) & ~0x3f) == 0 && ((1L << (_la - 316)) & 18014673387388935L) != 0) || ((((_la - 397)) & ~0x3f) == 0 && ((1L << (_la - 397)) & 1041281L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AllDistinctContext extends ParserRuleContext {
		public AllDistinctContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_allDistinct; }
	}

	public final AllDistinctContext allDistinct() throws RecognitionException {
		AllDistinctContext _localctx = new AllDistinctContext(_ctx, getState());
		enterRule(_localctx, 234, RULE_allDistinct);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2797);
			_la = _input.LA(1);
			if ( !(_la==T__158 || _la==T__180) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FirstLastContext extends ParserRuleContext {
		public FirstLastContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_firstLast; }
	}

	public final FirstLastContext firstLast() throws RecognitionException {
		FirstLastContext _localctx = new FirstLastContext(_ctx, getState());
		enterRule(_localctx, 236, RULE_firstLast);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2799);
			_la = _input.LA(1);
			if ( !(_la==T__132 || _la==T__397) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FormatJsonContext extends ParserRuleContext {
		public FormatJsonContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formatJson; }
	}

	public final FormatJsonContext formatJson() throws RecognitionException {
		FormatJsonContext _localctx = new FormatJsonContext(_ctx, getState());
		enterRule(_localctx, 238, RULE_formatJson);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2801);
			match(T__37);
			setState(2802);
			match(T__40);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OnNullContext extends ParserRuleContext {
		public OnNullContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_onNull; }
	}

	public final OnNullContext onNull() throws RecognitionException {
		OnNullContext _localctx = new OnNullContext(_ctx, getState());
		enterRule(_localctx, 240, RULE_onNull);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2804);
			_la = _input.LA(1);
			if ( !(_la==T__112 || _la==T__398) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(2805);
			match(T__97);
			setState(2806);
			match(T__112);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RespectIgnoreContext extends ParserRuleContext {
		public RespectIgnoreContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_respectIgnore; }
	}

	public final RespectIgnoreContext respectIgnore() throws RecognitionException {
		RespectIgnoreContext _localctx = new RespectIgnoreContext(_ctx, getState());
		enterRule(_localctx, 242, RULE_respectIgnore);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2808);
			_la = _input.LA(1);
			if ( !(_la==T__150 || _la==T__399) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(2809);
			match(T__196);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RowRowsContext extends ParserRuleContext {
		public RowRowsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rowRows; }
	}

	public final RowRowsContext rowRows() throws RecognitionException {
		RowRowsContext _localctx = new RowRowsContext(_ctx, getState());
		enterRule(_localctx, 244, RULE_rowRows);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2811);
			_la = _input.LA(1);
			if ( !(_la==T__100 || _la==T__353) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TimeZoneContext extends ParserRuleContext {
		public TimeZoneContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_timeZone; }
	}

	public final TimeZoneContext timeZone() throws RecognitionException {
		TimeZoneContext _localctx = new TimeZoneContext(_ctx, getState());
		enterRule(_localctx, 246, RULE_timeZone);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2813);
			match(T__304);
			setState(2814);
			match(T__400);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UniqueKeysContext extends ParserRuleContext {
		public WithWithoutContext withWithout() {
			return getRuleContext(WithWithoutContext.class,0);
		}
		public UniqueKeysContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_uniqueKeys; }
	}

	public final UniqueKeysContext uniqueKeys() throws RecognitionException {
		UniqueKeysContext _localctx = new UniqueKeysContext(_ctx, getState());
		enterRule(_localctx, 248, RULE_uniqueKeys);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2817);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__84 || _la==T__85) {
				{
				setState(2816);
				withWithout();
				}
			}

			setState(2819);
			match(T__106);
			setState(2820);
			match(T__401);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WithTiesContext extends ParserRuleContext {
		public WithTiesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_withTies; }
	}

	public final WithTiesContext withTies() throws RecognitionException {
		WithTiesContext _localctx = new WithTiesContext(_ctx, getState());
		enterRule(_localctx, 250, RULE_withTies);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2822);
			match(T__85);
			setState(2823);
			match(T__355);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WithWithoutContext extends ParserRuleContext {
		public WithWithoutContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_withWithout; }
	}

	public final WithWithoutContext withWithout() throws RecognitionException {
		WithWithoutContext _localctx = new WithWithoutContext(_ctx, getState());
		enterRule(_localctx, 252, RULE_withWithout);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2825);
			_la = _input.LA(1);
			if ( !(_la==T__84 || _la==T__85) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AliasContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public AliasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alias; }
	}

	public final AliasContext alias() throws RecognitionException {
		AliasContext _localctx = new AliasContext(_ctx, getState());
		enterRule(_localctx, 254, RULE_alias);
		try {
			setState(2829);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,443,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(2827);
				name();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(2828);
				string();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NamesContext extends ParserRuleContext {
		public List<NameContext> name() {
			return getRuleContexts(NameContext.class);
		}
		public NameContext name(int i) {
			return getRuleContext(NameContext.class,i);
		}
		public NamesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_names; }
	}

	public final NamesContext names() throws RecognitionException {
		NamesContext _localctx = new NamesContext(_ctx, getState());
		enterRule(_localctx, 256, RULE_names);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2831);
			match(T__16);
			setState(2832);
			name();
			setState(2837);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__28) {
				{
				{
				setState(2833);
				match(T__28);
				setState(2834);
				name();
				}
				}
				setState(2839);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(2840);
			match(T__17);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Qnames0Context extends ParserRuleContext {
		public List<QnameContext> qname() {
			return getRuleContexts(QnameContext.class);
		}
		public QnameContext qname(int i) {
			return getRuleContext(QnameContext.class,i);
		}
		public Qnames0Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qnames0; }
	}

	public final Qnames0Context qnames0() throws RecognitionException {
		Qnames0Context _localctx = new Qnames0Context(_ctx, getState());
		enterRule(_localctx, 258, RULE_qnames0);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2842);
			qname();
			setState(2847);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__28) {
				{
				{
				setState(2843);
				match(T__28);
				setState(2844);
				qname();
				}
				}
				setState(2849);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QnamesContext extends ParserRuleContext {
		public List<QnameContext> qname() {
			return getRuleContexts(QnameContext.class);
		}
		public QnameContext qname(int i) {
			return getRuleContext(QnameContext.class,i);
		}
		public QnamesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qnames; }
	}

	public final QnamesContext qnames() throws RecognitionException {
		QnamesContext _localctx = new QnamesContext(_ctx, getState());
		enterRule(_localctx, 260, RULE_qnames);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2850);
			match(T__16);
			setState(2851);
			qname();
			setState(2856);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__28) {
				{
				{
				setState(2852);
				match(T__28);
				setState(2853);
				qname();
				}
				}
				setState(2858);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(2859);
			match(T__17);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(NormalSQLParser.ID, 0); }
		public TerminalNode BRACKETS() { return getToken(NormalSQLParser.BRACKETS, 0); }
		public TerminalNode STRING() { return getToken(NormalSQLParser.STRING, 0); }
		public TerminalNode UNICODE_NAME() { return getToken(NormalSQLParser.UNICODE_NAME, 0); }
		public UescapeContext uescape() {
			return getRuleContext(UescapeContext.class,0);
		}
		public TerminalNode DOLLARS() { return getToken(NormalSQLParser.DOLLARS, 0); }
		public TerminalNode VARIABLE() { return getToken(NormalSQLParser.VARIABLE, 0); }
		public TerminalNode PARAMETER() { return getToken(NormalSQLParser.PARAMETER, 0); }
		public UnreservedContext unreserved() {
			return getRuleContext(UnreservedContext.class,0);
		}
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 262, RULE_name);
		try {
			setState(2872);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,448,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(2861);
				match(ID);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(2862);
				match(BRACKETS);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(2863);
				match(STRING);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(2864);
				match(UNICODE_NAME);
				setState(2866);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,447,_ctx) ) {
				case 1:
					{
					setState(2865);
					uescape();
					}
					break;
				}
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(2868);
				match(DOLLARS);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(2869);
				match(VARIABLE);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(2870);
				match(PARAMETER);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(2871);
				unreserved();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StringContext extends ParserRuleContext {
		public List<TerminalNode> STRING() { return getTokens(NormalSQLParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(NormalSQLParser.STRING, i);
		}
		public TerminalNode UNICODE_STRING() { return getToken(NormalSQLParser.UNICODE_STRING, 0); }
		public UescapeContext uescape() {
			return getRuleContext(UescapeContext.class,0);
		}
		public TerminalNode NATIONAL_STRING() { return getToken(NormalSQLParser.NATIONAL_STRING, 0); }
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 264, RULE_string);
		try {
			int _alt;
			setState(2896);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(2875); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(2874);
						match(STRING);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(2877); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,449,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case UNICODE_STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(2879);
				match(UNICODE_STRING);
				setState(2883);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,450,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(2880);
						match(STRING);
						}
						} 
					}
					setState(2885);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,450,_ctx);
				}
				setState(2887);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,451,_ctx) ) {
				case 1:
					{
					setState(2886);
					uescape();
					}
					break;
				}
				}
				break;
			case NATIONAL_STRING:
				enterOuterAlt(_localctx, 3);
				{
				setState(2889);
				match(NATIONAL_STRING);
				setState(2893);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,452,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(2890);
						match(STRING);
						}
						} 
					}
					setState(2895);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,452,_ctx);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UescapeContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(NormalSQLParser.STRING, 0); }
		public UescapeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_uescape; }
	}

	public final UescapeContext uescape() throws RecognitionException {
		UescapeContext _localctx = new UescapeContext(_ctx, getState());
		enterRule(_localctx, 266, RULE_uescape);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2898);
			match(T__396);
			setState(2899);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 37:
			return select_sempred((SelectContext)_localctx, predIndex);
		case 40:
			return sources_sempred((SourcesContext)_localctx, predIndex);
		case 70:
			return term_sempred((TermContext)_localctx, predIndex);
		case 72:
			return subterm_sempred((SubtermContext)_localctx, predIndex);
		case 78:
			return type_sempred((TypeContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean select_sempred(SelectContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean sources_sempred(SourcesContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 3);
		}
		return true;
	}
	private boolean term_sempred(TermContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 5);
		case 3:
			return precpred(_ctx, 4);
		case 4:
			return precpred(_ctx, 3);
		}
		return true;
	}
	private boolean subterm_sempred(SubtermContext _localctx, int predIndex) {
		switch (predIndex) {
		case 5:
			return precpred(_ctx, 22);
		case 6:
			return precpred(_ctx, 21);
		case 7:
			return precpred(_ctx, 19);
		case 8:
			return precpred(_ctx, 18);
		case 9:
			return precpred(_ctx, 17);
		case 10:
			return precpred(_ctx, 3);
		case 11:
			return precpred(_ctx, 2);
		case 12:
			return precpred(_ctx, 1);
		case 13:
			return precpred(_ctx, 28);
		case 14:
			return precpred(_ctx, 27);
		case 15:
			return precpred(_ctx, 26);
		case 16:
			return precpred(_ctx, 23);
		case 17:
			return precpred(_ctx, 20);
		case 18:
			return precpred(_ctx, 16);
		case 19:
			return precpred(_ctx, 15);
		}
		return true;
	}
	private boolean type_sempred(TypeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 20:
			return precpred(_ctx, 3);
		case 21:
			return precpred(_ctx, 2);
		}
		return true;
	}

	private static final String _serializedATNSegment0 =
		"\u0004\u0001\u01a4\u0b56\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007"+
		"\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007"+
		"\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007"+
		"\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007"+
		"\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007"+
		"\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007"+
		"\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007"+
		"\"\u0002#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007"+
		"\'\u0002(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007+\u0002,\u0007"+
		",\u0002-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u00070\u00021\u0007"+
		"1\u00022\u00072\u00023\u00073\u00024\u00074\u00025\u00075\u00026\u0007"+
		"6\u00027\u00077\u00028\u00078\u00029\u00079\u0002:\u0007:\u0002;\u0007"+
		";\u0002<\u0007<\u0002=\u0007=\u0002>\u0007>\u0002?\u0007?\u0002@\u0007"+
		"@\u0002A\u0007A\u0002B\u0007B\u0002C\u0007C\u0002D\u0007D\u0002E\u0007"+
		"E\u0002F\u0007F\u0002G\u0007G\u0002H\u0007H\u0002I\u0007I\u0002J\u0007"+
		"J\u0002K\u0007K\u0002L\u0007L\u0002M\u0007M\u0002N\u0007N\u0002O\u0007"+
		"O\u0002P\u0007P\u0002Q\u0007Q\u0002R\u0007R\u0002S\u0007S\u0002T\u0007"+
		"T\u0002U\u0007U\u0002V\u0007V\u0002W\u0007W\u0002X\u0007X\u0002Y\u0007"+
		"Y\u0002Z\u0007Z\u0002[\u0007[\u0002\\\u0007\\\u0002]\u0007]\u0002^\u0007"+
		"^\u0002_\u0007_\u0002`\u0007`\u0002a\u0007a\u0002b\u0007b\u0002c\u0007"+
		"c\u0002d\u0007d\u0002e\u0007e\u0002f\u0007f\u0002g\u0007g\u0002h\u0007"+
		"h\u0002i\u0007i\u0002j\u0007j\u0002k\u0007k\u0002l\u0007l\u0002m\u0007"+
		"m\u0002n\u0007n\u0002o\u0007o\u0002p\u0007p\u0002q\u0007q\u0002r\u0007"+
		"r\u0002s\u0007s\u0002t\u0007t\u0002u\u0007u\u0002v\u0007v\u0002w\u0007"+
		"w\u0002x\u0007x\u0002y\u0007y\u0002z\u0007z\u0002{\u0007{\u0002|\u0007"+
		"|\u0002}\u0007}\u0002~\u0007~\u0002\u007f\u0007\u007f\u0002\u0080\u0007"+
		"\u0080\u0002\u0081\u0007\u0081\u0002\u0082\u0007\u0082\u0002\u0083\u0007"+
		"\u0083\u0002\u0084\u0007\u0084\u0002\u0085\u0007\u0085\u0001\u0000\u0003"+
		"\u0000\u010e\b\u0000\u0001\u0000\u0001\u0000\u0003\u0000\u0112\b\u0000"+
		"\u0005\u0000\u0114\b\u0000\n\u0000\f\u0000\u0117\t\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0001\u0003\u0001\u011c\b\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0003\u0001\u0121\b\u0001\u0001\u0001\u0001\u0001\u0003\u0001\u0125"+
		"\b\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0003\u0001\u012d\b\u0001\u0001\u0001\u0003\u0001\u0130\b\u0001"+
		"\u0001\u0001\u0001\u0001\u0003\u0001\u0134\b\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0003\u0001\u013e\b\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0003\u0001\u0144\b\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001\u014d\b\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0003\u0001\u0152\b\u0001\u0001\u0001\u0001\u0001"+
		"\u0003\u0001\u0156\b\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001"+
		"\u015b\b\u0001\u0001\u0001\u0001\u0001\u0003\u0001\u015f\b\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001\u0166"+
		"\b\u0001\u0001\u0001\u0001\u0001\u0003\u0001\u016a\b\u0001\u0001\u0001"+
		"\u0003\u0001\u016d\b\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0003\u0001\u0175\b\u0001\u0001\u0001\u0001\u0001"+
		"\u0003\u0001\u0179\b\u0001\u0003\u0001\u017b\b\u0001\u0001\u0002\u0001"+
		"\u0002\u0003\u0002\u017f\b\u0002\u0001\u0002\u0003\u0002\u0182\b\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0005\u0002\u0188\b\u0002"+
		"\n\u0002\f\u0002\u018b\t\u0002\u0001\u0002\u0001\u0002\u0003\u0002\u018f"+
		"\b\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002\u0194\b\u0002"+
		"\u0001\u0003\u0001\u0003\u0003\u0003\u0198\b\u0003\u0001\u0003\u0001\u0003"+
		"\u0003\u0003\u019c\b\u0003\u0001\u0003\u0001\u0003\u0003\u0003\u01a0\b"+
		"\u0003\u0001\u0003\u0001\u0003\u0003\u0003\u01a4\b\u0003\u0001\u0003\u0001"+
		"\u0003\u0003\u0003\u01a8\b\u0003\u0001\u0003\u0001\u0003\u0003\u0003\u01ac"+
		"\b\u0003\u0001\u0003\u0001\u0003\u0003\u0003\u01b0\b\u0003\u0001\u0003"+
		"\u0001\u0003\u0003\u0003\u01b4\b\u0003\u0001\u0003\u0001\u0003\u0003\u0003"+
		"\u01b8\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0003\u0004\u01bf\b\u0004\u0001\u0004\u0003\u0004\u01c2\b\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004\u01c8\b\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0003\u0004\u01cd\b\u0004\u0001\u0004\u0003"+
		"\u0004\u01d0\b\u0004\u0001\u0005\u0001\u0005\u0003\u0005\u01d4\b\u0005"+
		"\u0001\u0005\u0001\u0005\u0003\u0005\u01d8\b\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006"+
		"\u01e1\b\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u01e5\b\u0006\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u01f6\b\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003"+
		"\u0007\u0205\b\u0007\u0001\b\u0001\b\u0003\b\u0209\b\b\u0001\b\u0003\b"+
		"\u020c\b\b\u0001\b\u0003\b\u020f\b\b\u0001\b\u0003\b\u0212\b\b\u0001\b"+
		"\u0001\b\u0003\b\u0216\b\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0005"+
		"\b\u021d\b\b\n\b\f\b\u0220\t\b\u0001\b\u0001\b\u0005\b\u0224\b\b\n\b\f"+
		"\b\u0227\t\b\u0001\b\u0001\b\u0001\b\u0003\b\u022c\b\b\u0001\b\u0001\b"+
		"\u0001\b\u0001\b\u0003\b\u0232\b\b\u0001\b\u0003\b\u0235\b\b\u0003\b\u0237"+
		"\b\b\u0001\b\u0003\b\u023a\b\b\u0001\t\u0001\t\u0001\t\u0001\t\u0003\t"+
		"\u0240\b\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003"+
		"\t\u0249\b\t\u0005\t\u024b\b\t\n\t\f\t\u024e\t\t\u0003\t\u0250\b\t\u0001"+
		"\t\u0003\t\u0253\b\t\u0001\n\u0001\n\u0001\n\u0004\n\u0258\b\n\u000b\n"+
		"\f\n\u0259\u0001\n\u0001\n\u0004\n\u025e\b\n\u000b\n\f\n\u025f\u0001\n"+
		"\u0001\n\u0001\n\u0005\n\u0265\b\n\n\n\f\n\u0268\t\n\u0003\n\u026a\b\n"+
		"\u0001\n\u0001\n\u0003\n\u026e\b\n\u0001\u000b\u0001\u000b\u0003\u000b"+
		"\u0272\b\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u0276\b\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u027d"+
		"\b\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003"+
		"\u000b\u0284\b\u000b\u0003\u000b\u0286\b\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u028d\b\u000b\u0001\u000b"+
		"\u0001\u000b\u0003\u000b\u0291\b\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0003\u000b\u0298\b\u000b\u0001\u000b\u0001\u000b"+
		"\u0004\u000b\u029c\b\u000b\u000b\u000b\f\u000b\u029d\u0001\u000b\u0003"+
		"\u000b\u02a1\b\u000b\u0001\f\u0001\f\u0001\r\u0001\r\u0003\r\u02a7\b\r"+
		"\u0001\r\u0001\r\u0003\r\u02ab\b\r\u0001\r\u0001\r\u0003\r\u02af\b\r\u0001"+
		"\r\u0001\r\u0003\r\u02b3\b\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001"+
		"\u000e\u0003\u000e\u02ba\b\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u02be"+
		"\b\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0003"+
		"\u000e\u02c5\b\u000e\u0001\u000f\u0001\u000f\u0003\u000f\u02c9\b\u000f"+
		"\u0001\u000f\u0003\u000f\u02cc\b\u000f\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0005\u0010\u02d2\b\u0010\n\u0010\f\u0010\u02d5\t\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0012\u0001\u0012\u0003\u0012\u02df\b\u0012\u0001\u0012\u0005\u0012\u02e2"+
		"\b\u0012\n\u0012\f\u0012\u02e5\t\u0012\u0001\u0013\u0001\u0013\u0003\u0013"+
		"\u02e9\b\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u02ee\b"+
		"\u0013\u0001\u0013\u0003\u0013\u02f1\b\u0013\u0001\u0013\u0003\u0013\u02f4"+
		"\b\u0013\u0001\u0013\u0003\u0013\u02f7\b\u0013\u0001\u0013\u0001\u0013"+
		"\u0003\u0013\u02fb\b\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u02ff\b"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0003\u0013\u0307\b\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0003"+
		"\u0013\u030c\b\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0003\u0013\u0313\b\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0003\u0013\u031a\b\u0013\u0003\u0013\u031c\b\u0013"+
		"\u0001\u0013\u0001\u0013\u0005\u0013\u0320\b\u0013\n\u0013\f\u0013\u0323"+
		"\t\u0013\u0001\u0014\u0001\u0014\u0003\u0014\u0327\b\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u032e\b\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0003\u0014\u0338\b\u0014\u0001\u0014\u0001\u0014"+
		"\u0003\u0014\u033c\b\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0004\u0014\u0345\b\u0014\u000b\u0014"+
		"\f\u0014\u0346\u0001\u0014\u0001\u0014\u0005\u0014\u034b\b\u0014\n\u0014"+
		"\f\u0014\u034e\t\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u0357\b\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0003\u0016\u0361\b\u0016\u0001\u0016\u0001\u0016\u0005\u0016"+
		"\u0365\b\u0016\n\u0016\f\u0016\u0368\t\u0016\u0001\u0016\u0003\u0016\u036b"+
		"\b\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u0370\b\u0016"+
		"\u0003\u0016\u0372\b\u0016\u0001\u0017\u0001\u0017\u0003\u0017\u0376\b"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0005\u0017\u037b\b\u0017\n"+
		"\u0017\f\u0017\u037e\t\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0005\u0018\u0385\b\u0018\n\u0018\f\u0018\u0388\t\u0018"+
		"\u0001\u0018\u0001\u0018\u0003\u0018\u038c\b\u0018\u0001\u0018\u0001\u0018"+
		"\u0003\u0018\u0390\b\u0018\u0001\u0018\u0003\u0018\u0393\b\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0005\u0018\u039f\b\u0018\n"+
		"\u0018\f\u0018\u03a2\t\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0003"+
		"\u0018\u03a7\b\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0005"+
		"\u0018\u03ad\b\u0018\n\u0018\f\u0018\u03b0\t\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0003\u0018"+
		"\u03b9\b\u0018\u0001\u0018\u0001\u0018\u0003\u0018\u03bd\b\u0018\u0003"+
		"\u0018\u03bf\b\u0018\u0001\u0019\u0003\u0019\u03c2\b\u0019\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0003\u0019\u03c7\b\u0019\u0001\u0019\u0001\u0019"+
		"\u0001\u0019\u0003\u0019\u03cc\b\u0019\u0001\u0019\u0003\u0019\u03cf\b"+
		"\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0005\u0019\u03d5"+
		"\b\u0019\n\u0019\f\u0019\u03d8\t\u0019\u0003\u0019\u03da\b\u0019\u0001"+
		"\u0019\u0003\u0019\u03dd\b\u0019\u0001\u0019\u0003\u0019\u03e0\b\u0019"+
		"\u0001\u0019\u0003\u0019\u03e3\b\u0019\u0001\u0019\u0003\u0019\u03e6\b"+
		"\u0019\u0001\u0019\u0003\u0019\u03e9\b\u0019\u0001\u001a\u0003\u001a\u03ec"+
		"\b\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0003\u001a\u03f1\b\u001a"+
		"\u0001\u001a\u0003\u001a\u03f4\b\u001a\u0001\u001a\u0001\u001a\u0001\u001a"+
		"\u0001\u001a\u0003\u001a\u03fa\b\u001a\u0001\u001a\u0003\u001a\u03fd\b"+
		"\u001a\u0001\u001a\u0003\u001a\u0400\b\u001a\u0001\u001a\u0001\u001a\u0005"+
		"\u001a\u0404\b\u001a\n\u001a\f\u001a\u0407\t\u001a\u0001\u001a\u0001\u001a"+
		"\u0003\u001a\u040b\b\u001a\u0001\u001a\u0003\u001a\u040e\b\u001a\u0001"+
		"\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0003\u001c\u0418\b\u001c\u0003\u001c\u041a\b\u001c"+
		"\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c"+
		"\u0001\u001c\u0005\u001c\u0423\b\u001c\n\u001c\f\u001c\u0426\t\u001c\u0001"+
		"\u001c\u0003\u001c\u0429\b\u001c\u0003\u001c\u042b\b\u001c\u0001\u001d"+
		"\u0003\u001d\u042e\b\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0003\u001d"+
		"\u0433\b\u001d\u0001\u001d\u0001\u001d\u0003\u001d\u0437\b\u001d\u0001"+
		"\u001d\u0003\u001d\u043a\b\u001d\u0001\u001d\u0001\u001d\u0003\u001d\u043e"+
		"\b\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0005\u001d\u0444"+
		"\b\u001d\n\u001d\f\u001d\u0447\t\u001d\u0001\u001e\u0001\u001e\u0003\u001e"+
		"\u044b\b\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0005\u001e\u0450\b"+
		"\u001e\n\u001e\f\u001e\u0453\t\u001e\u0001\u001e\u0001\u001e\u0001\u001e"+
		"\u0003\u001e\u0458\b\u001e\u0001\u001e\u0003\u001e\u045b\b\u001e\u0001"+
		"\u001e\u0001\u001e\u0001\u001e\u0003\u001e\u0460\b\u001e\u0001\u001e\u0003"+
		"\u001e\u0463\b\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001"+
		"\u001e\u0005\u001e\u046a\b\u001e\n\u001e\f\u001e\u046d\t\u001e\u0001\u001e"+
		"\u0003\u001e\u0470\b\u001e\u0001\u001e\u0003\u001e\u0473\b\u001e\u0001"+
		"\u001e\u0001\u001e\u0001\u001e\u0003\u001e\u0478\b\u001e\u0001\u001f\u0003"+
		"\u001f\u047b\b\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0003\u001f\u0480"+
		"\b\u001f\u0001\u001f\u0001\u001f\u0003\u001f\u0484\b\u001f\u0001\u001f"+
		"\u0003\u001f\u0487\b\u001f\u0001\u001f\u0003\u001f\u048a\b\u001f\u0001"+
		"\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0005\u001f\u0490\b\u001f\n"+
		"\u001f\f\u001f\u0493\t\u001f\u0001\u001f\u0001\u001f\u0003\u001f\u0497"+
		"\b\u001f\u0001\u001f\u0003\u001f\u049a\b\u001f\u0001\u001f\u0003\u001f"+
		"\u049d\b\u001f\u0001\u001f\u0003\u001f\u04a0\b\u001f\u0001\u001f\u0003"+
		"\u001f\u04a3\b\u001f\u0001\u001f\u0003\u001f\u04a6\b\u001f\u0001 \u0001"+
		" \u0003 \u04aa\b \u0001 \u0001 \u0001 \u0001!\u0001!\u0001\"\u0001\"\u0001"+
		"\"\u0001\"\u0001\"\u0003\"\u04b6\b\"\u0001#\u0001#\u0001#\u0001#\u0005"+
		"#\u04bc\b#\n#\f#\u04bf\t#\u0001$\u0001$\u0001$\u0003$\u04c4\b$\u0001$"+
		"\u0003$\u04c7\b$\u0003$\u04c9\b$\u0001%\u0001%\u0003%\u04cd\b%\u0001%"+
		"\u0001%\u0001%\u0001%\u0001%\u0001%\u0005%\u04d5\b%\n%\f%\u04d8\t%\u0001"+
		"%\u0001%\u0001%\u0001%\u0003%\u04de\b%\u0001%\u0001%\u0001%\u0001%\u0004"+
		"%\u04e4\b%\u000b%\f%\u04e5\u0005%\u04e8\b%\n%\f%\u04eb\t%\u0001&\u0001"+
		"&\u0003&\u04ef\b&\u0001&\u0003&\u04f2\b&\u0001&\u0001&\u0001&\u0005&\u04f7"+
		"\b&\n&\f&\u04fa\t&\u0001&\u0003&\u04fd\b&\u0003&\u04ff\b&\u0001&\u0003"+
		"&\u0502\b&\u0001&\u0001&\u0003&\u0506\b&\u0001&\u0003&\u0509\b&\u0001"+
		"&\u0003&\u050c\b&\u0001&\u0003&\u050f\b&\u0001&\u0003&\u0512\b&\u0001"+
		"&\u0003&\u0515\b&\u0001&\u0001&\u0001&\u0001&\u0003&\u051b\b&\u0001\'"+
		"\u0001\'\u0001\'\u0001\'\u0003\'\u0521\b\'\u0001\'\u0001\'\u0003\'\u0525"+
		"\b\'\u0001(\u0001(\u0001(\u0001(\u0001(\u0001(\u0003(\u052d\b(\u0001("+
		"\u0001(\u0001(\u0001(\u0001(\u0001(\u0001(\u0005(\u0536\b(\n(\f(\u0539"+
		"\t(\u0001(\u0001(\u0004(\u053d\b(\u000b(\f(\u053e\u0005(\u0541\b(\n(\f"+
		"(\u0544\t(\u0001)\u0001)\u0003)\u0548\b)\u0001)\u0001)\u0003)\u054c\b"+
		")\u0003)\u054e\b)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001"+
		")\u0003)\u0558\b)\u0003)\u055a\b)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001"+
		")\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0003)\u0569\b)\u0001"+
		")\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001"+
		")\u0003)\u0576\b)\u0001)\u0003)\u0579\b)\u0001)\u0001)\u0003)\u057d\b"+
		")\u0003)\u057f\b)\u0003)\u0581\b)\u0001*\u0001*\u0001*\u0001*\u0001*\u0005"+
		"*\u0588\b*\n*\f*\u058b\t*\u0001*\u0001*\u0001*\u0003*\u0590\b*\u0001+"+
		"\u0001+\u0001+\u0001+\u0001+\u0005+\u0597\b+\n+\f+\u059a\t+\u0001+\u0001"+
		"+\u0001,\u0001,\u0001,\u0001,\u0003,\u05a2\b,\u0001,\u0001,\u0001,\u0001"+
		"-\u0001-\u0001-\u0003-\u05aa\b-\u0001.\u0001.\u0001.\u0001.\u0003.\u05b0"+
		"\b.\u0001/\u0001/\u0001/\u0001/\u0003/\u05b6\b/\u0003/\u05b8\b/\u0001"+
		"/\u0001/\u0001/\u0003/\u05bd\b/\u00010\u00010\u00010\u00010\u00010\u0003"+
		"0\u05c4\b0\u00010\u00010\u00030\u05c8\b0\u00010\u00030\u05cb\b0\u0001"+
		"0\u00010\u00010\u00010\u00050\u05d1\b0\n0\f0\u05d4\t0\u00030\u05d6\b0"+
		"\u00010\u00010\u00010\u00010\u00010\u00030\u05dd\b0\u00011\u00011\u0001"+
		"1\u00011\u00011\u00011\u00031\u05e5\b1\u00011\u00031\u05e8\b1\u00012\u0001"+
		"2\u00012\u00012\u00012\u00012\u00012\u00032\u05f1\b2\u00012\u00032\u05f4"+
		"\b2\u00012\u00032\u05f7\b2\u00013\u00013\u00013\u00033\u05fc\b3\u0001"+
		"3\u00013\u00013\u00013\u00033\u0602\b3\u00013\u00013\u00033\u0606\b3\u0001"+
		"3\u00033\u0609\b3\u00033\u060b\b3\u00014\u00014\u00014\u00034\u0610\b"+
		"4\u00014\u00034\u0613\b4\u00014\u00014\u00034\u0617\b4\u00014\u00014\u0001"+
		"4\u00034\u061c\b4\u00054\u061e\b4\n4\f4\u0621\t4\u00015\u00015\u00035"+
		"\u0625\b5\u00015\u00045\u0628\b5\u000b5\f5\u0629\u00015\u00015\u00035"+
		"\u062e\b5\u00015\u00035\u0631\b5\u00016\u00016\u00016\u00016\u00016\u0005"+
		"6\u0638\b6\n6\f6\u063b\t6\u00016\u00016\u00016\u00036\u0640\b6\u00016"+
		"\u00016\u00016\u00016\u00016\u00016\u00016\u00016\u00016\u00016\u0001"+
		"6\u00016\u00056\u064e\b6\n6\f6\u0651\t6\u00016\u00016\u00016\u00036\u0656"+
		"\b6\u00016\u00016\u00016\u00016\u00036\u065c\b6\u00016\u00016\u00016\u0003"+
		"6\u0661\b6\u00017\u00017\u00037\u0665\b7\u00017\u00037\u0668\b7\u0001"+
		"8\u00018\u00018\u00038\u066d\b8\u00018\u00018\u00018\u00038\u0672\b8\u0001"+
		"8\u00018\u00018\u00038\u0677\b8\u00018\u00018\u00018\u00018\u00018\u0001"+
		"8\u00019\u00019\u00019\u00019\u00019\u00039\u0684\b9\u0001:\u0001:\u0001"+
		":\u0003:\u0689\b:\u0001:\u0001:\u0001:\u0005:\u068e\b:\n:\f:\u0691\t:"+
		"\u0001;\u0001;\u0001;\u0001;\u0001;\u0001;\u0001;\u0001;\u0001;\u0001"+
		";\u0001;\u0001;\u0001;\u0001;\u0001;\u0001;\u0001;\u0005;\u06a4\b;\n;"+
		"\f;\u06a7\t;\u0001;\u0001;\u0003;\u06ab\b;\u0001<\u0001<\u0001<\u0001"+
		"=\u0001=\u0001=\u0001=\u0005=\u06b4\b=\n=\f=\u06b7\t=\u0001>\u0001>\u0001"+
		">\u0001>\u0001?\u0001?\u0001?\u0001@\u0001@\u0001@\u0001@\u0003@\u06c4"+
		"\b@\u0001@\u0001@\u0001@\u0001@\u0005@\u06ca\b@\n@\f@\u06cd\t@\u0001@"+
		"\u0001@\u0001A\u0001A\u0001A\u0003A\u06d4\bA\u0001A\u0001A\u0001B\u0001"+
		"B\u0001B\u0001B\u0003B\u06dc\bB\u0001B\u0001B\u0003B\u06e0\bB\u0001C\u0001"+
		"C\u0001C\u0005C\u06e5\bC\nC\fC\u06e8\tC\u0001D\u0001D\u0003D\u06ec\bD"+
		"\u0001D\u0003D\u06ef\bD\u0001E\u0001E\u0001E\u0005E\u06f4\bE\nE\fE\u06f7"+
		"\tE\u0001F\u0001F\u0001F\u0001F\u0001F\u0001F\u0001F\u0001F\u0001F\u0001"+
		"F\u0001F\u0001F\u0001F\u0001F\u0001F\u0001F\u0001F\u0001F\u0001F\u0001"+
		"F\u0001F\u0001F\u0001F\u0001F\u0001F\u0003F\u0712\bF\u0001F\u0001F\u0001"+
		"F\u0001F\u0001F\u0001F\u0001F\u0001F\u0001F\u0005F\u071d\bF\nF\fF\u0720"+
		"\tF\u0001G\u0001G\u0001H\u0001H\u0001H\u0001H\u0001H\u0001H\u0001H\u0005"+
		"H\u072b\bH\nH\fH\u072e\tH\u0001H\u0001H\u0001H\u0001H\u0003H\u0734\bH"+
		"\u0001H\u0001H\u0001H\u0001H\u0001H\u0001H\u0001H\u0001H\u0001H\u0001"+
		"H\u0001H\u0001H\u0001H\u0001H\u0001H\u0001H\u0001H\u0003H\u0747\bH\u0001"+
		"H\u0003H\u074a\bH\u0001H\u0001H\u0001H\u0001H\u0001H\u0001H\u0001H\u0001"+
		"H\u0001H\u0001H\u0001H\u0001H\u0001H\u0001H\u0001H\u0001H\u0001H\u0001"+
		"H\u0001H\u0001H\u0001H\u0003H\u0761\bH\u0001H\u0001H\u0003H\u0765\bH\u0001"+
		"H\u0001H\u0001H\u0003H\u076a\bH\u0001H\u0001H\u0001H\u0005H\u076f\bH\n"+
		"H\fH\u0772\tH\u0003H\u0774\bH\u0001H\u0001H\u0001H\u0001H\u0001H\u0001"+
		"H\u0001H\u0001H\u0001H\u0001H\u0001H\u0001H\u0001H\u0001H\u0001H\u0001"+
		"H\u0001H\u0001H\u0001H\u0001H\u0001H\u0001H\u0001H\u0001H\u0001H\u0001"+
		"H\u0001H\u0001H\u0001H\u0001H\u0004H\u0794\bH\u000bH\fH\u0795\u0001H\u0001"+
		"H\u0004H\u079a\bH\u000bH\fH\u079b\u0001H\u0001H\u0001H\u0001H\u0001H\u0001"+
		"H\u0001H\u0001H\u0001H\u0001H\u0001H\u0003H\u07a9\bH\u0001H\u0001H\u0005"+
		"H\u07ad\bH\nH\fH\u07b0\tH\u0001I\u0001I\u0001I\u0001I\u0001I\u0003I\u07b7"+
		"\bI\u0001I\u0001I\u0001I\u0004I\u07bc\bI\u000bI\fI\u07bd\u0001I\u0001"+
		"I\u0003I\u07c2\bI\u0001I\u0001I\u0001I\u0001I\u0001I\u0001I\u0001I\u0001"+
		"I\u0004I\u07cc\bI\u000bI\fI\u07cd\u0001I\u0001I\u0003I\u07d2\bI\u0001"+
		"I\u0001I\u0003I\u07d6\bI\u0001J\u0001J\u0001J\u0001J\u0001J\u0001J\u0001"+
		"J\u0003J\u07df\bJ\u0001J\u0001J\u0003J\u07e3\bJ\u0001J\u0001J\u0001J\u0003"+
		"J\u07e8\bJ\u0001J\u0001J\u0001J\u0003J\u07ed\bJ\u0001J\u0001J\u0001J\u0001"+
		"J\u0001J\u0003J\u07f4\bJ\u0001J\u0001J\u0003J\u07f8\bJ\u0001J\u0001J\u0003"+
		"J\u07fc\bJ\u0001J\u0001J\u0001J\u0005J\u0801\bJ\nJ\fJ\u0804\tJ\u0001J"+
		"\u0001J\u0001J\u0001J\u0003J\u080a\bJ\u0001J\u0001J\u0003J\u080e\bJ\u0001"+
		"J\u0003J\u0811\bJ\u0001J\u0001J\u0003J\u0815\bJ\u0001J\u0001J\u0003J\u0819"+
		"\bJ\u0001J\u0001J\u0001J\u0001J\u0003J\u081f\bJ\u0001J\u0001J\u0003J\u0823"+
		"\bJ\u0001J\u0001J\u0001J\u0003J\u0828\bJ\u0001J\u0001J\u0003J\u082c\b"+
		"J\u0001J\u0001J\u0001J\u0001J\u0001J\u0003J\u0833\bJ\u0001J\u0001J\u0001"+
		"J\u0001J\u0001J\u0003J\u083a\bJ\u0003J\u083c\bJ\u0001J\u0001J\u0001J\u0001"+
		"J\u0001J\u0001J\u0003J\u0844\bJ\u0001J\u0003J\u0847\bJ\u0001K\u0001K\u0001"+
		"L\u0001L\u0001L\u0001L\u0001L\u0001L\u0003L\u0851\bL\u0001M\u0001M\u0001"+
		"N\u0001N\u0001N\u0001N\u0001N\u0001N\u0001N\u0001N\u0001N\u0005N\u085e"+
		"\bN\nN\fN\u0861\tN\u0001N\u0001N\u0001N\u0001N\u0001N\u0003N\u0868\bN"+
		"\u0001N\u0001N\u0001N\u0001N\u0001N\u0003N\u086f\bN\u0001N\u0001N\u0001"+
		"N\u0003N\u0874\bN\u0001N\u0004N\u0877\bN\u000bN\fN\u0878\u0005N\u087b"+
		"\bN\nN\fN\u087e\tN\u0001O\u0001O\u0001O\u0001O\u0001O\u0001O\u0001O\u0001"+
		"O\u0001O\u0003O\u0889\bO\u0001O\u0001O\u0001O\u0001O\u0003O\u088f\bO\u0001"+
		"O\u0001O\u0003O\u0893\bO\u0001O\u0001O\u0003O\u0897\bO\u0001O\u0001O\u0003"+
		"O\u089b\bO\u0001O\u0001O\u0001O\u0001O\u0001O\u0001O\u0003O\u08a3\bO\u0001"+
		"O\u0003O\u08a6\bO\u0001O\u0001O\u0003O\u08aa\bO\u0001O\u0001O\u0003O\u08ae"+
		"\bO\u0001O\u0001O\u0001O\u0001O\u0001O\u0003O\u08b5\bO\u0001O\u0001O\u0003"+
		"O\u08b9\bO\u0001O\u0001O\u0003O\u08bd\bO\u0001O\u0001O\u0001O\u0001O\u0001"+
		"O\u0004O\u08c4\bO\u000bO\fO\u08c5\u0001O\u0003O\u08c9\bO\u0003O\u08cb"+
		"\bO\u0001P\u0001P\u0003P\u08cf\bP\u0001P\u0001P\u0001P\u0001P\u0001P\u0003"+
		"P\u08d6\bP\u0003P\u08d8\bP\u0001Q\u0001Q\u0001Q\u0001Q\u0001R\u0001R\u0001"+
		"R\u0001R\u0003R\u08e2\bR\u0001R\u0001R\u0001S\u0001S\u0001S\u0001T\u0001"+
		"T\u0001T\u0001U\u0001U\u0001U\u0003U\u08ef\bU\u0001U\u0001U\u0001V\u0001"+
		"V\u0001V\u0005V\u08f6\bV\nV\fV\u08f9\tV\u0001W\u0001W\u0001W\u0003W\u08fe"+
		"\bW\u0001W\u0003W\u0901\bW\u0001W\u0003W\u0904\bW\u0001W\u0001W\u0001"+
		"W\u0001W\u0001W\u0001W\u0001W\u0001W\u0001W\u0001W\u0003W\u0910\bW\u0001"+
		"W\u0001W\u0001W\u0001W\u0001W\u0001W\u0003W\u0918\bW\u0001W\u0003W\u091b"+
		"\bW\u0001W\u0001W\u0003W\u091f\bW\u0001W\u0003W\u0922\bW\u0001W\u0001"+
		"W\u0001W\u0001W\u0001W\u0005W\u0929\bW\nW\fW\u092c\tW\u0001W\u0001W\u0001"+
		"W\u0001W\u0001W\u0001W\u0001W\u0003W\u0935\bW\u0001W\u0001W\u0001W\u0001"+
		"W\u0001W\u0001W\u0001W\u0005W\u093e\bW\nW\fW\u0941\tW\u0001W\u0001W\u0001"+
		"W\u0001W\u0001W\u0001W\u0001W\u0001W\u0001W\u0001W\u0003W\u094d\bW\u0001"+
		"W\u0001W\u0001W\u0003W\u0952\bW\u0001W\u0001W\u0001W\u0001W\u0001W\u0001"+
		"W\u0003W\u095a\bW\u0001W\u0001W\u0001W\u0001W\u0001W\u0001W\u0001W\u0005"+
		"W\u0963\bW\nW\fW\u0966\tW\u0001W\u0001W\u0001W\u0001W\u0001W\u0001W\u0001"+
		"W\u0001W\u0001W\u0001W\u0001W\u0001W\u0001W\u0001W\u0001W\u0001W\u0003"+
		"W\u0978\bW\u0001W\u0001W\u0001W\u0001W\u0001W\u0001W\u0001W\u0001W\u0001"+
		"W\u0001W\u0001W\u0003W\u0985\bW\u0001W\u0001W\u0001W\u0001W\u0001W\u0003"+
		"W\u098c\bW\u0003W\u098e\bW\u0001W\u0001W\u0001W\u0001W\u0001W\u0001W\u0001"+
		"W\u0001W\u0001W\u0001W\u0001W\u0001W\u0001W\u0001W\u0001W\u0001W\u0001"+
		"W\u0001W\u0001W\u0001W\u0001W\u0001W\u0001W\u0003W\u09a7\bW\u0001W\u0001"+
		"W\u0003W\u09ab\bW\u0001W\u0003W\u09ae\bW\u0001W\u0001W\u0001W\u0001W\u0001"+
		"W\u0003W\u09b5\bW\u0001W\u0001W\u0003W\u09b9\bW\u0001W\u0001W\u0003W\u09bd"+
		"\bW\u0001W\u0001W\u0001W\u0003W\u09c2\bW\u0001W\u0001W\u0003W\u09c6\b"+
		"W\u0001W\u0003W\u09c9\bW\u0003W\u09cb\bW\u0001W\u0001W\u0003W\u09cf\b"+
		"W\u0001W\u0003W\u09d2\bW\u0001W\u0001W\u0003W\u09d6\bW\u0001W\u0003W\u09d9"+
		"\bW\u0001W\u0003W\u09dc\bW\u0001W\u0001W\u0001W\u0003W\u09e1\bW\u0001"+
		"X\u0001X\u0001X\u0001X\u0001X\u0001X\u0001Y\u0001Y\u0001Y\u0001Y\u0001"+
		"Y\u0001Y\u0001Z\u0001Z\u0001Z\u0001[\u0001[\u0001[\u0003[\u09f5\b[\u0001"+
		"\\\u0001\\\u0001]\u0001]\u0003]\u09fb\b]\u0001]\u0003]\u09fe\b]\u0001"+
		"]\u0003]\u0a01\b]\u0001]\u0003]\u0a04\b]\u0001]\u0001]\u0003]\u0a08\b"+
		"]\u0001^\u0001^\u0001^\u0001^\u0001_\u0001_\u0001_\u0001_\u0001_\u0001"+
		"_\u0001_\u0003_\u0a15\b_\u0001_\u0001_\u0001_\u0001_\u0001_\u0001_\u0001"+
		"_\u0003_\u0a1e\b_\u0003_\u0a20\b_\u0001`\u0001`\u0001`\u0003`\u0a25\b"+
		"`\u0001`\u0001`\u0001`\u0003`\u0a2a\b`\u0001a\u0001a\u0003a\u0a2e\ba\u0001"+
		"a\u0001a\u0003a\u0a32\ba\u0001b\u0001b\u0001b\u0001b\u0001b\u0005b\u0a39"+
		"\bb\nb\fb\u0a3c\tb\u0001c\u0001c\u0003c\u0a40\bc\u0001c\u0001c\u0003c"+
		"\u0a44\bc\u0001d\u0001d\u0001d\u0001d\u0003d\u0a4a\bd\u0001e\u0003e\u0a4d"+
		"\be\u0001e\u0001e\u0001e\u0001e\u0001e\u0001e\u0001e\u0001e\u0001e\u0001"+
		"e\u0001e\u0001e\u0001e\u0001e\u0001e\u0001e\u0001e\u0003e\u0a60\be\u0001"+
		"e\u0003e\u0a63\be\u0001e\u0001e\u0001e\u0001e\u0001e\u0003e\u0a6a\be\u0001"+
		"f\u0001f\u0001g\u0001g\u0001h\u0001h\u0003h\u0a72\bh\u0001h\u0001h\u0001"+
		"h\u0003h\u0a77\bh\u0003h\u0a79\bh\u0001i\u0001i\u0001j\u0001j\u0001j\u0001"+
		"j\u0001j\u0001j\u0001j\u0003j\u0a84\bj\u0001j\u0003j\u0a87\bj\u0001j\u0003"+
		"j\u0a8a\bj\u0001j\u0001j\u0001k\u0001k\u0001k\u0003k\u0a91\bk\u0001k\u0003"+
		"k\u0a94\bk\u0001k\u0003k\u0a97\bk\u0001k\u0003k\u0a9a\bk\u0001k\u0001"+
		"k\u0001l\u0001l\u0001l\u0005l\u0aa1\bl\nl\fl\u0aa4\tl\u0001m\u0001m\u0001"+
		"m\u0001m\u0001m\u0003m\u0aab\bm\u0001m\u0001m\u0001m\u0001m\u0003m\u0ab1"+
		"\bm\u0001n\u0001n\u0001n\u0003n\u0ab6\bn\u0001o\u0001o\u0001o\u0001o\u0005"+
		"o\u0abc\bo\no\fo\u0abf\to\u0001o\u0001o\u0001p\u0001p\u0001p\u0005p\u0ac6"+
		"\bp\np\fp\u0ac9\tp\u0001q\u0001q\u0001q\u0005q\u0ace\bq\nq\fq\u0ad1\t"+
		"q\u0001r\u0001r\u0001r\u0001r\u0001r\u0005r\u0ad8\br\nr\fr\u0adb\tr\u0003"+
		"r\u0add\br\u0001s\u0001s\u0001s\u0003s\u0ae2\bs\u0001s\u0001s\u0003s\u0ae6"+
		"\bs\u0003s\u0ae8\bs\u0001s\u0001s\u0001t\u0001t\u0001u\u0001u\u0001v\u0001"+
		"v\u0001w\u0001w\u0001w\u0001x\u0001x\u0001x\u0001x\u0001y\u0001y\u0001"+
		"y\u0001z\u0001z\u0001{\u0001{\u0001{\u0001|\u0003|\u0b02\b|\u0001|\u0001"+
		"|\u0001|\u0001}\u0001}\u0001}\u0001~\u0001~\u0001\u007f\u0001\u007f\u0003"+
		"\u007f\u0b0e\b\u007f\u0001\u0080\u0001\u0080\u0001\u0080\u0001\u0080\u0005"+
		"\u0080\u0b14\b\u0080\n\u0080\f\u0080\u0b17\t\u0080\u0001\u0080\u0001\u0080"+
		"\u0001\u0081\u0001\u0081\u0001\u0081\u0005\u0081\u0b1e\b\u0081\n\u0081"+
		"\f\u0081\u0b21\t\u0081\u0001\u0082\u0001\u0082\u0001\u0082\u0001\u0082"+
		"\u0005\u0082\u0b27\b\u0082\n\u0082\f\u0082\u0b2a\t\u0082\u0001\u0082\u0001"+
		"\u0082\u0001\u0083\u0001\u0083\u0001\u0083\u0001\u0083\u0001\u0083\u0003"+
		"\u0083\u0b33\b\u0083\u0001\u0083\u0001\u0083\u0001\u0083\u0001\u0083\u0003"+
		"\u0083\u0b39\b\u0083\u0001\u0084\u0004\u0084\u0b3c\b\u0084\u000b\u0084"+
		"\f\u0084\u0b3d\u0001\u0084\u0001\u0084\u0005\u0084\u0b42\b\u0084\n\u0084"+
		"\f\u0084\u0b45\t\u0084\u0001\u0084\u0003\u0084\u0b48\b\u0084\u0001\u0084"+
		"\u0001\u0084\u0005\u0084\u0b4c\b\u0084\n\u0084\f\u0084\u0b4f\t\u0084\u0003"+
		"\u0084\u0b51\b\u0084\u0001\u0085\u0001\u0085\u0001\u0085\u0001\u0085\u0001"+
		"\u092a\u0005JP\u008c\u0090\u009c\u0086\u0000\u0002\u0004\u0006\b\n\f\u000e"+
		"\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDF"+
		"HJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086\u0088\u008a\u008c"+
		"\u008e\u0090\u0092\u0094\u0096\u0098\u009a\u009c\u009e\u00a0\u00a2\u00a4"+
		"\u00a6\u00a8\u00aa\u00ac\u00ae\u00b0\u00b2\u00b4\u00b6\u00b8\u00ba\u00bc"+
		"\u00be\u00c0\u00c2\u00c4\u00c6\u00c8\u00ca\u00cc\u00ce\u00d0\u00d2\u00d4"+
		"\u00d6\u00d8\u00da\u00dc\u00de\u00e0\u00e2\u00e4\u00e6\u00e8\u00ea\u00ec"+
		"\u00ee\u00f0\u00f2\u00f4\u00f6\u00f8\u00fa\u00fc\u00fe\u0100\u0102\u0104"+
		"\u0106\u0108\u010a\u00009\u0001\u0000\u0002\u0003\u0001\u0000\b\n\u0001"+
		"\u0000\'*\u0001\u000023\u0002\u0000\u0010\u0010\u0018\u0018\u0001\u0000"+
		"67\u0002\u0000CCMP\u0001\u0000RS\u0001\u0000gh\u0002\u0000YYxx\u0002\u0000"+
		"__aa\u0002\u0000qqtt\u0001\u0000}\u007f\u0002\u0000\b\b\n\n\u0001\u0000"+
		"\u0083\u0084\u0001\u0000\u008c\u008d\u0003\u0000\u0017\u0017jj\u0095\u0097"+
		"\u0001\u0000\u00a3\u00a5\u0002\u0000,,\u00a9\u00a9\u0002\u0000\u0085\u0085"+
		"\u00ad\u00ad\u0002\u0000}}\u00b9\u00bb\u0001\u0000\u00c3\u00c4\u0002\u0000"+
		"\u008e\u008e\u00d3\u00d3\u0002\u0000\u0010\u0010\u00d6\u00de\u0002\u0000"+
		"ll\u00df\u00e2\u0001\u0000\u00ed\u00ee\u0002\u0000ll\u009f\u009f\u0002"+
		"\u0000\u00ad\u00ad\u00c7\u00c7\u0001\u0000\u00e7\u00e8\u0001\u0000\u00e9"+
		"\u00ea\u0002\u0000\u009a\u009a\u00f1\u00f4\u0001\u0000\u00df\u00e0\u0001"+
		"\u0000\u00fc\u00fd\u0002\u0000||\u00fe\u0101\u0002\u0000\u0017\u0017\u0095"+
		"\u0096\u0005\u0000\u0010\u0010\u00dd\u00dd\u00e1\u00e1\u0104\u010e\u01a4"+
		"\u01a4\u0002\u0000\u008e\u008e\u0114\u0116\u0001\u0000\u0131\u0132\u0001"+
		"\u0000\u0135\u0137\u0001\u0000\u0135\u0136\u0001\u0000\u013c\u013e\u0002"+
		"\u0000kk\u00b5\u00b5\u0001\u0000\u014a\u014b\u0001\u0000\u015e\u015f\u0001"+
		"\u0000\u0161\u0163\u0001\u0000\u019d\u019e\u0001\u0000\u016d\u016f\u0002"+
		"\u0000qq\u0170\u0172\u0003\u0000bb\u0170\u0171\u0173\u0173\u0001\u0000"+
		"\u0174\u0189+\u0000\u0001\u0001\u000e\u000e\u0010\u0012\u001d\u001d55"+
		">>VVZZbcffiiklrrttvv\u008a\u008a\u0093\u0093\u009a\u009e\u00a0\u00a0\u00aa"+
		"\u00aa\u00b5\u00b5\u00b7\u00b7\u00be\u00be\u00c0\u00c0\u00c6\u00c6\u00c8"+
		"\u00c8\u00cd\u00cd\u00cf\u00cf\u00d6\u00d6\u00ec\u00ed\u00f6\u00f6\u00f9"+
		"\u00f9\u00fb\u00fb\u00fd\u00fd\u0105\u010b\u0114\u0114\u0118\u0119\u013c"+
		"\u013e\u0162\u0162\u0172\u0172\u018d\u018d\u0194\u0196\u019a\u01a0\u0002"+
		"\u0000\u009f\u009f\u00b5\u00b5\u0002\u0000\u0085\u0085\u018e\u018e\u0002"+
		"\u0000qq\u018f\u018f\u0002\u0000\u0097\u0097\u0190\u0190\u0002\u0000e"+
		"e\u0162\u0162\u0001\u0000UV\u0d76\u0000\u010d\u0001\u0000\u0000\u0000"+
		"\u0002\u011b\u0001\u0000\u0000\u0000\u0004\u0193\u0001\u0000\u0000\u0000"+
		"\u0006\u01b7\u0001\u0000\u0000\u0000\b\u01b9\u0001\u0000\u0000\u0000\n"+
		"\u01d1\u0001\u0000\u0000\u0000\f\u01dc\u0001\u0000\u0000\u0000\u000e\u0204"+
		"\u0001\u0000\u0000\u0000\u0010\u0206\u0001\u0000\u0000\u0000\u0012\u023b"+
		"\u0001\u0000\u0000\u0000\u0014\u026d\u0001\u0000\u0000\u0000\u0016\u026f"+
		"\u0001\u0000\u0000\u0000\u0018\u02a2\u0001\u0000\u0000\u0000\u001a\u02a4"+
		"\u0001\u0000\u0000\u0000\u001c\u02b7\u0001\u0000\u0000\u0000\u001e\u02c8"+
		"\u0001\u0000\u0000\u0000 \u02cd\u0001\u0000\u0000\u0000\"\u02d8\u0001"+
		"\u0000\u0000\u0000$\u02dc\u0001\u0000\u0000\u0000&\u02e8\u0001\u0000\u0000"+
		"\u0000(\u0326\u0001\u0000\u0000\u0000*\u034f\u0001\u0000\u0000\u0000,"+
		"\u0353\u0001\u0000\u0000\u0000.\u0373\u0001\u0000\u0000\u00000\u037f\u0001"+
		"\u0000\u0000\u00002\u03c1\u0001\u0000\u0000\u00004\u03eb\u0001\u0000\u0000"+
		"\u00006\u040f\u0001\u0000\u0000\u00008\u0413\u0001\u0000\u0000\u0000:"+
		"\u042d\u0001\u0000\u0000\u0000<\u0448\u0001\u0000\u0000\u0000>\u047a\u0001"+
		"\u0000\u0000\u0000@\u04a9\u0001\u0000\u0000\u0000B\u04ae\u0001\u0000\u0000"+
		"\u0000D\u04b5\u0001\u0000\u0000\u0000F\u04b7\u0001\u0000\u0000\u0000H"+
		"\u04c8\u0001\u0000\u0000\u0000J\u04dd\u0001\u0000\u0000\u0000L\u051a\u0001"+
		"\u0000\u0000\u0000N\u0524\u0001\u0000\u0000\u0000P\u052c\u0001\u0000\u0000"+
		"\u0000R\u0580\u0001\u0000\u0000\u0000T\u0582\u0001\u0000\u0000\u0000V"+
		"\u0591\u0001\u0000\u0000\u0000X\u059d\u0001\u0000\u0000\u0000Z\u05a6\u0001"+
		"\u0000\u0000\u0000\\\u05ab\u0001\u0000\u0000\u0000^\u05b1\u0001\u0000"+
		"\u0000\u0000`\u05be\u0001\u0000\u0000\u0000b\u05e7\u0001\u0000\u0000\u0000"+
		"d\u05e9\u0001\u0000\u0000\u0000f\u060a\u0001\u0000\u0000\u0000h\u060c"+
		"\u0001\u0000\u0000\u0000j\u0630\u0001\u0000\u0000\u0000l\u0660\u0001\u0000"+
		"\u0000\u0000n\u0662\u0001\u0000\u0000\u0000p\u0669\u0001\u0000\u0000\u0000"+
		"r\u067e\u0001\u0000\u0000\u0000t\u0685\u0001\u0000\u0000\u0000v\u06aa"+
		"\u0001\u0000\u0000\u0000x\u06ac\u0001\u0000\u0000\u0000z\u06af\u0001\u0000"+
		"\u0000\u0000|\u06b8\u0001\u0000\u0000\u0000~\u06bc\u0001\u0000\u0000\u0000"+
		"\u0080\u06bf\u0001\u0000\u0000\u0000\u0082\u06d0\u0001\u0000\u0000\u0000"+
		"\u0084\u06d7\u0001\u0000\u0000\u0000\u0086\u06e1\u0001\u0000\u0000\u0000"+
		"\u0088\u06e9\u0001\u0000\u0000\u0000\u008a\u06f0\u0001\u0000\u0000\u0000"+
		"\u008c\u0711\u0001\u0000\u0000\u0000\u008e\u0721\u0001\u0000\u0000\u0000"+
		"\u0090\u0773\u0001\u0000\u0000\u0000\u0092\u07d5\u0001\u0000\u0000\u0000"+
		"\u0094\u0846\u0001\u0000\u0000\u0000\u0096\u0848\u0001\u0000\u0000\u0000"+
		"\u0098\u0850\u0001\u0000\u0000\u0000\u009a\u0852\u0001\u0000\u0000\u0000"+
		"\u009c\u0867\u0001\u0000\u0000\u0000\u009e\u08ca\u0001\u0000\u0000\u0000"+
		"\u00a0\u08d7\u0001\u0000\u0000\u0000\u00a2\u08d9\u0001\u0000\u0000\u0000"+
		"\u00a4\u08dd\u0001\u0000\u0000\u0000\u00a6\u08e5\u0001\u0000\u0000\u0000"+
		"\u00a8\u08e8\u0001\u0000\u0000\u0000\u00aa\u08eb\u0001\u0000\u0000\u0000"+
		"\u00ac\u08f2\u0001\u0000\u0000\u0000\u00ae\u09e0\u0001\u0000\u0000\u0000"+
		"\u00b0\u09e2\u0001\u0000\u0000\u0000\u00b2\u09e8\u0001\u0000\u0000\u0000"+
		"\u00b4\u09ee\u0001\u0000\u0000\u0000\u00b6\u09f1\u0001\u0000\u0000\u0000"+
		"\u00b8\u09f6\u0001\u0000\u0000\u0000\u00ba\u0a07\u0001\u0000\u0000\u0000"+
		"\u00bc\u0a09\u0001\u0000\u0000\u0000\u00be\u0a0d\u0001\u0000\u0000\u0000"+
		"\u00c0\u0a29\u0001\u0000\u0000\u0000\u00c2\u0a31\u0001\u0000\u0000\u0000"+
		"\u00c4\u0a33\u0001\u0000\u0000\u0000\u00c6\u0a3d\u0001\u0000\u0000\u0000"+
		"\u00c8\u0a49\u0001\u0000\u0000\u0000\u00ca\u0a69\u0001\u0000\u0000\u0000"+
		"\u00cc\u0a6b\u0001\u0000\u0000\u0000\u00ce\u0a6d\u0001\u0000\u0000\u0000"+
		"\u00d0\u0a6f\u0001\u0000\u0000\u0000\u00d2\u0a7a\u0001\u0000\u0000\u0000"+
		"\u00d4\u0a7c\u0001\u0000\u0000\u0000\u00d6\u0a8d\u0001\u0000\u0000\u0000"+
		"\u00d8\u0a9d\u0001\u0000\u0000\u0000\u00da\u0ab0\u0001\u0000\u0000\u0000"+
		"\u00dc\u0ab5\u0001\u0000\u0000\u0000\u00de\u0ab7\u0001\u0000\u0000\u0000"+
		"\u00e0\u0ac2\u0001\u0000\u0000\u0000\u00e2\u0aca\u0001\u0000\u0000\u0000"+
		"\u00e4\u0adc\u0001\u0000\u0000\u0000\u00e6\u0ade\u0001\u0000\u0000\u0000"+
		"\u00e8\u0aeb\u0001\u0000\u0000\u0000\u00ea\u0aed\u0001\u0000\u0000\u0000"+
		"\u00ec\u0aef\u0001\u0000\u0000\u0000\u00ee\u0af1\u0001\u0000\u0000\u0000"+
		"\u00f0\u0af4\u0001\u0000\u0000\u0000\u00f2\u0af8\u0001\u0000\u0000\u0000"+
		"\u00f4\u0afb\u0001\u0000\u0000\u0000\u00f6\u0afd\u0001\u0000\u0000\u0000"+
		"\u00f8\u0b01\u0001\u0000\u0000\u0000\u00fa\u0b06\u0001\u0000\u0000\u0000"+
		"\u00fc\u0b09\u0001\u0000\u0000\u0000\u00fe\u0b0d\u0001\u0000\u0000\u0000"+
		"\u0100\u0b0f\u0001\u0000\u0000\u0000\u0102\u0b1a\u0001\u0000\u0000\u0000"+
		"\u0104\u0b22\u0001\u0000\u0000\u0000\u0106\u0b38\u0001\u0000\u0000\u0000"+
		"\u0108\u0b50\u0001\u0000\u0000\u0000\u010a\u0b52\u0001\u0000\u0000\u0000"+
		"\u010c\u010e\u0003\u0002\u0001\u0000\u010d\u010c\u0001\u0000\u0000\u0000"+
		"\u010d\u010e\u0001\u0000\u0000\u0000\u010e\u0115\u0001\u0000\u0000\u0000"+
		"\u010f\u0111\u0005\u0001\u0000\u0000\u0110\u0112\u0003\u0002\u0001\u0000"+
		"\u0111\u0110\u0001\u0000\u0000\u0000\u0111\u0112\u0001\u0000\u0000\u0000"+
		"\u0112\u0114\u0001\u0000\u0000\u0000\u0113\u010f\u0001\u0000\u0000\u0000"+
		"\u0114\u0117\u0001\u0000\u0000\u0000\u0115\u0113\u0001\u0000\u0000\u0000"+
		"\u0115\u0116\u0001\u0000\u0000\u0000\u0116\u0118\u0001\u0000\u0000\u0000"+
		"\u0117\u0115\u0001\u0000\u0000\u0000\u0118\u0119\u0005\u0000\u0000\u0001"+
		"\u0119\u0001\u0001\u0000\u0000\u0000\u011a\u011c\u0003\u0004\u0002\u0000"+
		"\u011b\u011a\u0001\u0000\u0000\u0000\u011b\u011c\u0001\u0000\u0000\u0000"+
		"\u011c\u017a\u0001\u0000\u0000\u0000\u011d\u017b\u0003\b\u0004\u0000\u011e"+
		"\u0120\u0007\u0000\u0000\u0000\u011f\u0121\u0003\u00e4r\u0000\u0120\u011f"+
		"\u0001\u0000\u0000\u0000\u0120\u0121\u0001\u0000\u0000\u0000\u0121\u017b"+
		"\u0001\u0000\u0000\u0000\u0122\u0124\u0005\u0004\u0000\u0000\u0123\u0125"+
		"\u0005\u0005\u0000\u0000\u0124\u0123\u0001\u0000\u0000\u0000\u0124\u0125"+
		"\u0001\u0000\u0000\u0000\u0125\u0126\u0001\u0000\u0000\u0000\u0126\u0127"+
		"\u0003\u008cF\u0000\u0127\u0128\u0005\u0006\u0000\u0000\u0128\u0129\u0003"+
		"\u00e4r\u0000\u0129\u017b\u0001\u0000\u0000\u0000\u012a\u012c\u0005\u0007"+
		"\u0000\u0000\u012b\u012d\u0007\u0001\u0000\u0000\u012c\u012b\u0001\u0000"+
		"\u0000\u0000\u012c\u012d\u0001\u0000\u0000\u0000\u012d\u012f\u0001\u0000"+
		"\u0000\u0000\u012e\u0130\u0005\u000b\u0000\u0000\u012f\u012e\u0001\u0000"+
		"\u0000\u0000\u012f\u0130\u0001\u0000\u0000\u0000\u0130\u017b\u0001\u0000"+
		"\u0000\u0000\u0131\u0133\u0005\f\u0000\u0000\u0132\u0134\u0005\u000b\u0000"+
		"\u0000\u0133\u0132\u0001\u0000\u0000\u0000\u0133\u0134\u0001\u0000\u0000"+
		"\u0000\u0134\u017b\u0001\u0000\u0000\u0000\u0135\u017b\u0003\u001c\u000e"+
		"\u0000\u0136\u017b\u0003\u0010\b\u0000\u0137\u017b\u0003\u0016\u000b\u0000"+
		"\u0138\u017b\u0003\u001a\r\u0000\u0139\u017b\u0003\u0012\t\u0000\u013a"+
		"\u017b\u00032\u0019\u0000\u013b\u013d\u0005\r\u0000\u0000\u013c\u013e"+
		"\u0005\u0005\u0000\u0000\u013d\u013c\u0001\u0000\u0000\u0000\u013d\u013e"+
		"\u0001\u0000\u0000\u0000\u013e\u013f\u0001\u0000\u0000\u0000\u013f\u017b"+
		"\u0003\u008cF\u0000\u0140\u017b\u0003\f\u0006\u0000\u0141\u0143\u0005"+
		"\u000e\u0000\u0000\u0142\u0144\u0005\u000b\u0000\u0000\u0143\u0142\u0001"+
		"\u0000\u0000\u0000\u0143\u0144\u0001\u0000\u0000\u0000\u0144\u017b\u0001"+
		"\u0000\u0000\u0000\u0145\u017b\u00034\u001a\u0000\u0146\u017b\u0003:\u001d"+
		"\u0000\u0147\u0148\u0005\u000f\u0000\u0000\u0148\u0155\u0003\u00e4r\u0000"+
		"\u0149\u014c\u0005\u0010\u0000\u0000\u014a\u014d\u0003\u00cae\u0000\u014b"+
		"\u014d\u0003\u0106\u0083\u0000\u014c\u014a\u0001\u0000\u0000\u0000\u014c"+
		"\u014b\u0001\u0000\u0000\u0000\u014d\u0156\u0001\u0000\u0000\u0000\u014e"+
		"\u0151\u0005\u0011\u0000\u0000\u014f\u0152\u0003\u00cae\u0000\u0150\u0152"+
		"\u0003\u0106\u0083\u0000\u0151\u014f\u0001\u0000\u0000\u0000\u0151\u0150"+
		"\u0001\u0000\u0000\u0000\u0152\u0153\u0001\u0000\u0000\u0000\u0153\u0154"+
		"\u0005\u0012\u0000\u0000\u0154\u0156\u0001\u0000\u0000\u0000\u0155\u0149"+
		"\u0001\u0000\u0000\u0000\u0155\u014e\u0001\u0000\u0000\u0000\u0155\u0156"+
		"\u0001\u0000\u0000\u0000\u0156\u017b\u0001\u0000\u0000\u0000\u0157\u017b"+
		"\u0003J%\u0000\u0158\u015a\u0005\u0013\u0000\u0000\u0159\u015b\u0003\u00e4"+
		"r\u0000\u015a\u0159\u0001\u0000\u0000\u0000\u015a\u015b\u0001\u0000\u0000"+
		"\u0000\u015b\u017b\u0001\u0000\u0000\u0000\u015c\u015e\u0005\u0014\u0000"+
		"\u0000\u015d\u015f\u0005\u0015\u0000\u0000\u015e\u015d\u0001\u0000\u0000"+
		"\u0000\u015e\u015f\u0001\u0000\u0000\u0000\u015f\u0160\u0001\u0000\u0000"+
		"\u0000\u0160\u017b\u0003\u00e4r\u0000\u0161\u0162\u0005\u0016\u0000\u0000"+
		"\u0162\u017b\u0003\u00e4r\u0000\u0163\u0165\u0005\u0017\u0000\u0000\u0164"+
		"\u0166\u0005\u000b\u0000\u0000\u0165\u0164\u0001\u0000\u0000\u0000\u0165"+
		"\u0166\u0001\u0000\u0000\u0000\u0166\u016c\u0001\u0000\u0000\u0000\u0167"+
		"\u0169\u0005\u0018\u0000\u0000\u0168\u016a\u0005\u0015\u0000\u0000\u0169"+
		"\u0168\u0001\u0000\u0000\u0000\u0169\u016a\u0001\u0000\u0000\u0000\u016a"+
		"\u016b\u0001\u0000\u0000\u0000\u016b\u016d\u0003\u0106\u0083\u0000\u016c"+
		"\u0167\u0001\u0000\u0000\u0000\u016c\u016d\u0001\u0000\u0000\u0000\u016d"+
		"\u017b\u0001\u0000\u0000\u0000\u016e\u016f\u0005\u0015\u0000\u0000\u016f"+
		"\u017b\u0003\u00e4r\u0000\u0170\u017b\u0003\n\u0005\u0000\u0171\u017b"+
		"\u0003>\u001f\u0000\u0172\u0174\u0005\u0019\u0000\u0000\u0173\u0175\u0003"+
		"\u00e4r\u0000\u0174\u0173\u0001\u0000\u0000\u0000\u0174\u0175\u0001\u0000"+
		"\u0000\u0000\u0175\u0178\u0001\u0000\u0000\u0000\u0176\u0177\u0005\u001a"+
		"\u0000\u0000\u0177\u0179\u0003\u008cF\u0000\u0178\u0176\u0001\u0000\u0000"+
		"\u0000\u0178\u0179\u0001\u0000\u0000\u0000\u0179\u017b\u0001\u0000\u0000"+
		"\u0000\u017a\u011d\u0001\u0000\u0000\u0000\u017a\u011e\u0001\u0000\u0000"+
		"\u0000\u017a\u0122\u0001\u0000\u0000\u0000\u017a\u012a\u0001\u0000\u0000"+
		"\u0000\u017a\u0131\u0001\u0000\u0000\u0000\u017a\u0135\u0001\u0000\u0000"+
		"\u0000\u017a\u0136\u0001\u0000\u0000\u0000\u017a\u0137\u0001\u0000\u0000"+
		"\u0000\u017a\u0138\u0001\u0000\u0000\u0000\u017a\u0139\u0001\u0000\u0000"+
		"\u0000\u017a\u013a\u0001\u0000\u0000\u0000\u017a\u013b\u0001\u0000\u0000"+
		"\u0000\u017a\u0140\u0001\u0000\u0000\u0000\u017a\u0141\u0001\u0000\u0000"+
		"\u0000\u017a\u0145\u0001\u0000\u0000\u0000\u017a\u0146\u0001\u0000\u0000"+
		"\u0000\u017a\u0147\u0001\u0000\u0000\u0000\u017a\u0157\u0001\u0000\u0000"+
		"\u0000\u017a\u0158\u0001\u0000\u0000\u0000\u017a\u015c\u0001\u0000\u0000"+
		"\u0000\u017a\u0161\u0001\u0000\u0000\u0000\u017a\u0163\u0001\u0000\u0000"+
		"\u0000\u017a\u016e\u0001\u0000\u0000\u0000\u017a\u0170\u0001\u0000\u0000"+
		"\u0000\u017a\u0171\u0001\u0000\u0000\u0000\u017a\u0172\u0001\u0000\u0000"+
		"\u0000\u017b\u0003\u0001\u0000\u0000\u0000\u017c\u017e\u0005\u001b\u0000"+
		"\u0000\u017d\u017f\u0005\u0002\u0000\u0000\u017e\u017d\u0001\u0000\u0000"+
		"\u0000\u017e\u017f\u0001\u0000\u0000\u0000\u017f\u0181\u0001\u0000\u0000"+
		"\u0000\u0180\u0182\u0005\u001c\u0000\u0000\u0181\u0180\u0001\u0000\u0000"+
		"\u0000\u0181\u0182\u0001\u0000\u0000\u0000\u0182\u018e\u0001\u0000\u0000"+
		"\u0000\u0183\u0184\u0005\u0011\u0000\u0000\u0184\u0189\u0003\u0006\u0003"+
		"\u0000\u0185\u0186\u0005\u001d\u0000\u0000\u0186\u0188\u0003\u0006\u0003"+
		"\u0000\u0187\u0185\u0001\u0000\u0000\u0000\u0188\u018b\u0001\u0000\u0000"+
		"\u0000\u0189\u0187\u0001\u0000\u0000\u0000\u0189\u018a\u0001\u0000\u0000"+
		"\u0000\u018a\u018c\u0001\u0000\u0000\u0000\u018b\u0189\u0001\u0000\u0000"+
		"\u0000\u018c\u018d\u0005\u0012\u0000\u0000\u018d\u018f\u0001\u0000\u0000"+
		"\u0000\u018e\u0183\u0001\u0000\u0000\u0000\u018e\u018f\u0001\u0000\u0000"+
		"\u0000\u018f\u0194\u0001\u0000\u0000\u0000\u0190\u0191\u0005\u001b\u0000"+
		"\u0000\u0191\u0192\u0005\u001e\u0000\u0000\u0192\u0194\u0005\u001f\u0000"+
		"\u0000\u0193\u017c\u0001\u0000\u0000\u0000\u0193\u0190\u0001\u0000\u0000"+
		"\u0000\u0194\u0005\u0001\u0000\u0000\u0000\u0195\u0197\u0005\u0002\u0000"+
		"\u0000\u0196\u0198\u0003\u00ceg\u0000\u0197\u0196\u0001\u0000\u0000\u0000"+
		"\u0197\u0198\u0001\u0000\u0000\u0000\u0198\u01b8\u0001\u0000\u0000\u0000"+
		"\u0199\u019b\u0005\u001c\u0000\u0000\u019a\u019c\u0003\u00ceg\u0000\u019b"+
		"\u019a\u0001\u0000\u0000\u0000\u019b\u019c\u0001\u0000\u0000\u0000\u019c"+
		"\u01b8\u0001\u0000\u0000\u0000\u019d\u019f\u0005 \u0000\u0000\u019e\u01a0"+
		"\u0003\u00ceg\u0000\u019f\u019e\u0001\u0000\u0000\u0000\u019f\u01a0\u0001"+
		"\u0000\u0000\u0000\u01a0\u01b8\u0001\u0000\u0000\u0000\u01a1\u01a3\u0005"+
		"!\u0000\u0000\u01a2\u01a4\u0003\u00ceg\u0000\u01a3\u01a2\u0001\u0000\u0000"+
		"\u0000\u01a3\u01a4\u0001\u0000\u0000\u0000\u01a4\u01b8\u0001\u0000\u0000"+
		"\u0000\u01a5\u01a7\u0005\"\u0000\u0000\u01a6\u01a8\u0003\u00ceg\u0000"+
		"\u01a7\u01a6\u0001\u0000\u0000\u0000\u01a7\u01a8\u0001\u0000\u0000\u0000"+
		"\u01a8\u01b8\u0001\u0000\u0000\u0000\u01a9\u01ab\u0005#\u0000\u0000\u01aa"+
		"\u01ac\u0003\u00ceg\u0000\u01ab\u01aa\u0001\u0000\u0000\u0000\u01ab\u01ac"+
		"\u0001\u0000\u0000\u0000\u01ac\u01b8\u0001\u0000\u0000\u0000\u01ad\u01af"+
		"\u0005$\u0000\u0000\u01ae\u01b0\u0003\u00ceg\u0000\u01af\u01ae\u0001\u0000"+
		"\u0000\u0000\u01af\u01b0\u0001\u0000\u0000\u0000\u01b0\u01b8\u0001\u0000"+
		"\u0000\u0000\u01b1\u01b3\u0005%\u0000\u0000\u01b2\u01b4\u0003\u00ceg\u0000"+
		"\u01b3\u01b2\u0001\u0000\u0000\u0000\u01b3\u01b4\u0001\u0000\u0000\u0000"+
		"\u01b4\u01b8\u0001\u0000\u0000\u0000\u01b5\u01b6\u0005&\u0000\u0000\u01b6"+
		"\u01b8\u0007\u0002\u0000\u0000\u01b7\u0195\u0001\u0000\u0000\u0000\u01b7"+
		"\u0199\u0001\u0000\u0000\u0000\u01b7\u019d\u0001\u0000\u0000\u0000\u01b7"+
		"\u01a1\u0001\u0000\u0000\u0000\u01b7\u01a5\u0001\u0000\u0000\u0000\u01b7"+
		"\u01a9\u0001\u0000\u0000\u0000\u01b7\u01ad\u0001\u0000\u0000\u0000\u01b7"+
		"\u01b1\u0001\u0000\u0000\u0000\u01b7\u01b5\u0001\u0000\u0000\u0000\u01b8"+
		"\u0007\u0001\u0000\u0000\u0000\u01b9\u01ba\u0005+\u0000\u0000\u01ba\u01bb"+
		"\u0005,\u0000\u0000\u01bb\u01cf\u0003\u00e4r\u0000\u01bc\u01c1\u0005-"+
		"\u0000\u0000\u01bd\u01bf\u0005.\u0000\u0000\u01be\u01bd\u0001\u0000\u0000"+
		"\u0000\u01be\u01bf\u0001\u0000\u0000\u0000\u01bf\u01c0\u0001\u0000\u0000"+
		"\u0000\u01c0\u01c2\u0003\u00e4r\u0000\u01c1\u01be\u0001\u0000\u0000\u0000"+
		"\u01c1\u01c2\u0001\u0000\u0000\u0000\u01c2\u01c3\u0001\u0000\u0000\u0000"+
		"\u01c3\u01c4\u0005\u0018\u0000\u0000\u01c4\u01d0\u0003\u00e4r\u0000\u01c5"+
		"\u01c7\u0005/\u0000\u0000\u01c6\u01c8\u0005.\u0000\u0000\u01c7\u01c6\u0001"+
		"\u0000\u0000\u0000\u01c7\u01c8\u0001\u0000\u0000\u0000\u01c8\u01c9\u0001"+
		"\u0000\u0000\u0000\u01c9\u01d0\u0003$\u0012\u0000\u01ca\u01cc\u00050\u0000"+
		"\u0000\u01cb\u01cd\u0005.\u0000\u0000\u01cc\u01cb\u0001\u0000\u0000\u0000"+
		"\u01cc\u01cd\u0001\u0000\u0000\u0000\u01cd\u01ce\u0001\u0000\u0000\u0000"+
		"\u01ce\u01d0\u0003\u00e4r\u0000\u01cf\u01bc\u0001\u0000\u0000\u0000\u01cf"+
		"\u01c5\u0001\u0000\u0000\u0000\u01cf\u01ca\u0001\u0000\u0000\u0000\u01d0"+
		"\t\u0001\u0000\u0000\u0000\u01d1\u01d3\u00051\u0000\u0000\u01d2\u01d4"+
		"\u0007\u0003\u0000\u0000\u01d3\u01d2\u0001\u0000\u0000\u0000\u01d3\u01d4"+
		"\u0001\u0000\u0000\u0000\u01d4\u01d7\u0001\u0000\u0000\u0000\u01d5\u01d8"+
		"\u0003\u00e4r\u0000\u01d6\u01d8\u0005\u01a0\u0000\u0000\u01d7\u01d5\u0001"+
		"\u0000\u0000\u0000\u01d7\u01d6\u0001\u0000\u0000\u0000\u01d8\u01d9\u0001"+
		"\u0000\u0000\u0000\u01d9\u01da\u0007\u0004\u0000\u0000\u01da\u01db\u0003"+
		"\u0086C\u0000\u01db\u000b\u0001\u0000\u0000\u0000\u01dc\u01dd\u00050\u0000"+
		"\u0000\u01dd\u01e0\u0003\u000e\u0007\u0000\u01de\u01df\u00054\u0000\u0000"+
		"\u01df\u01e1\u00055\u0000\u0000\u01e0\u01de\u0001\u0000\u0000\u0000\u01e0"+
		"\u01e1\u0001\u0000\u0000\u0000\u01e1\u01e2\u0001\u0000\u0000\u0000\u01e2"+
		"\u01e4\u0003\u0102\u0081\u0000\u01e3\u01e5\u0007\u0005\u0000\u0000\u01e4"+
		"\u01e3\u0001\u0000\u0000\u0000\u01e4\u01e5\u0001\u0000\u0000\u0000\u01e5"+
		"\r\u0001\u0000\u0000\u0000\u01e6\u0205\u00058\u0000\u0000\u01e7\u0205"+
		"\u00059\u0000\u0000\u01e8\u0205\u0005:\u0000\u0000\u01e9\u01ea\u0005;"+
		"\u0000\u0000\u01ea\u0205\u0005<\u0000\u0000\u01eb\u0205\u0005=\u0000\u0000"+
		"\u01ec\u01ed\u0005>\u0000\u0000\u01ed\u01ee\u0005?\u0000\u0000\u01ee\u0205"+
		"\u0005@\u0000\u0000\u01ef\u01f0\u0005>\u0000\u0000\u01f0\u0205\u0005,"+
		"\u0000\u0000\u01f1\u0205\u0005A\u0000\u0000\u01f2\u01f3\u0005B\u0000\u0000"+
		"\u01f3\u0205\u0005C\u0000\u0000\u01f4\u01f6\u0005D\u0000\u0000\u01f5\u01f4"+
		"\u0001\u0000\u0000\u0000\u01f5\u01f6\u0001\u0000\u0000\u0000\u01f6\u01f7"+
		"\u0001\u0000\u0000\u0000\u01f7\u0205\u0005E\u0000\u0000\u01f8\u0205\u0005"+
		"F\u0000\u0000\u01f9\u0205\u0005G\u0000\u0000\u01fa\u0205\u0005H\u0000"+
		"\u0000\u01fb\u0205\u0005I\u0000\u0000\u01fc\u0205\u0005J\u0000\u0000\u01fd"+
		"\u0205\u0005K\u0000\u0000\u01fe\u0205\u0005,\u0000\u0000\u01ff\u0200\u0005"+
		"\'\u0000\u0000\u0200\u0201\u0005L\u0000\u0000\u0201\u0205\u0007\u0006"+
		"\u0000\u0000\u0202\u0205\u0005<\u0000\u0000\u0203\u0205\u0005C\u0000\u0000"+
		"\u0204\u01e6\u0001\u0000\u0000\u0000\u0204\u01e7\u0001\u0000\u0000\u0000"+
		"\u0204\u01e8\u0001\u0000\u0000\u0000\u0204\u01e9\u0001\u0000\u0000\u0000"+
		"\u0204\u01eb\u0001\u0000\u0000\u0000\u0204\u01ec\u0001\u0000\u0000\u0000"+
		"\u0204\u01ef\u0001\u0000\u0000\u0000\u0204\u01f1\u0001\u0000\u0000\u0000"+
		"\u0204\u01f2\u0001\u0000\u0000\u0000\u0204\u01f5\u0001\u0000\u0000\u0000"+
		"\u0204\u01f8\u0001\u0000\u0000\u0000\u0204\u01f9\u0001\u0000\u0000\u0000"+
		"\u0204\u01fa\u0001\u0000\u0000\u0000\u0204\u01fb\u0001\u0000\u0000\u0000"+
		"\u0204\u01fc\u0001\u0000\u0000\u0000\u0204\u01fd\u0001\u0000\u0000\u0000"+
		"\u0204\u01fe\u0001\u0000\u0000\u0000\u0204\u01ff\u0001\u0000\u0000\u0000"+
		"\u0204\u0202\u0001\u0000\u0000\u0000\u0204\u0203\u0001\u0000\u0000\u0000"+
		"\u0205\u000f\u0001\u0000\u0000\u0000\u0206\u0208\u0005Q\u0000\u0000\u0207"+
		"\u0209\u0007\u0007\u0000\u0000\u0208\u0207\u0001\u0000\u0000\u0000\u0208"+
		"\u0209\u0001\u0000\u0000\u0000\u0209\u020b\u0001\u0000\u0000\u0000\u020a"+
		"\u020c\u0007\u0003\u0000\u0000\u020b\u020a\u0001\u0000\u0000\u0000\u020b"+
		"\u020c\u0001\u0000\u0000\u0000\u020c\u020e\u0001\u0000\u0000\u0000\u020d"+
		"\u020f\u0003\u0018\f\u0000\u020e\u020d\u0001\u0000\u0000\u0000\u020e\u020f"+
		"\u0001\u0000\u0000\u0000\u020f\u0211\u0001\u0000\u0000\u0000\u0210\u0212"+
		"\u0005T\u0000\u0000\u0211\u0210\u0001\u0000\u0000\u0000\u0211\u0212\u0001"+
		"\u0000\u0000\u0000\u0212\u0213\u0001\u0000\u0000\u0000\u0213\u0215\u0005"+
		",\u0000\u0000\u0214\u0216\u0003\"\u0011\u0000\u0215\u0214\u0001\u0000"+
		"\u0000\u0000\u0215\u0216\u0001\u0000\u0000\u0000\u0216\u0217\u0001\u0000"+
		"\u0000\u0000\u0217\u0236\u0003\u00e4r\u0000\u0218\u0219\u0005\u0011\u0000"+
		"\u0000\u0219\u021e\u0003$\u0012\u0000\u021a\u021b\u0005\u001d\u0000\u0000"+
		"\u021b\u021d\u0003$\u0012\u0000\u021c\u021a\u0001\u0000\u0000\u0000\u021d"+
		"\u0220\u0001\u0000\u0000\u0000\u021e\u021c\u0001\u0000\u0000\u0000\u021e"+
		"\u021f\u0001\u0000\u0000\u0000\u021f\u0225\u0001\u0000\u0000\u0000\u0220"+
		"\u021e\u0001\u0000\u0000\u0000\u0221\u0222\u0005\u001d\u0000\u0000\u0222"+
		"\u0224\u0003(\u0014\u0000\u0223\u0221\u0001\u0000\u0000\u0000\u0224\u0227"+
		"\u0001\u0000\u0000\u0000\u0225\u0223\u0001\u0000\u0000\u0000\u0225\u0226"+
		"\u0001\u0000\u0000\u0000\u0226\u0228\u0001\u0000\u0000\u0000\u0227\u0225"+
		"\u0001\u0000\u0000\u0000\u0228\u022b\u0005\u0012\u0000\u0000\u0229\u022a"+
		"\u0005U\u0000\u0000\u022a\u022c\u0005\u0197\u0000\u0000\u022b\u0229\u0001"+
		"\u0000\u0000\u0000\u022b\u022c\u0001\u0000\u0000\u0000\u022c\u0237\u0001"+
		"\u0000\u0000\u0000\u022d\u022e\u0005\u0006\u0000\u0000\u022e\u0234\u0003"+
		"J%\u0000\u022f\u0231\u0005V\u0000\u0000\u0230\u0232\u0005W\u0000\u0000"+
		"\u0231\u0230\u0001\u0000\u0000\u0000\u0231\u0232\u0001\u0000\u0000\u0000"+
		"\u0232\u0233\u0001\u0000\u0000\u0000\u0233\u0235\u0005?\u0000\u0000\u0234"+
		"\u022f\u0001\u0000\u0000\u0000\u0234\u0235\u0001\u0000\u0000\u0000\u0235"+
		"\u0237\u0001\u0000\u0000\u0000\u0236\u0218\u0001\u0000\u0000\u0000\u0236"+
		"\u022d\u0001\u0000\u0000\u0000\u0237\u0239\u0001\u0000\u0000\u0000\u0238"+
		"\u023a\u0005X\u0000\u0000\u0239\u0238\u0001\u0000\u0000\u0000\u0239\u023a"+
		"\u0001\u0000\u0000\u0000\u023a\u0011\u0001\u0000\u0000\u0000\u023b\u023c"+
		"\u0005Q\u0000\u0000\u023c\u023d\u0005Y\u0000\u0000\u023d\u023f\u0005,"+
		"\u0000\u0000\u023e\u0240\u0003\"\u0011\u0000\u023f\u023e\u0001\u0000\u0000"+
		"\u0000\u023f\u0240\u0001\u0000\u0000\u0000\u0240\u0241\u0001\u0000\u0000"+
		"\u0000\u0241\u0242\u0003\u00e4r\u0000\u0242\u0243\u0005Z\u0000\u0000\u0243"+
		"\u0252\u0003\u00e4r\u0000\u0244\u024f\u0005\u0011\u0000\u0000\u0245\u024c"+
		"\u0003\u0014\n\u0000\u0246\u0248\u0005\u001d\u0000\u0000\u0247\u0249\u0003"+
		"\u0014\n\u0000\u0248\u0247\u0001\u0000\u0000\u0000\u0248\u0249\u0001\u0000"+
		"\u0000\u0000\u0249\u024b\u0001\u0000\u0000\u0000\u024a\u0246\u0001\u0000"+
		"\u0000\u0000\u024b\u024e\u0001\u0000\u0000\u0000\u024c\u024a\u0001\u0000"+
		"\u0000\u0000\u024c\u024d\u0001\u0000\u0000\u0000\u024d\u0250\u0001\u0000"+
		"\u0000\u0000\u024e\u024c\u0001\u0000\u0000\u0000\u024f\u0245\u0001\u0000"+
		"\u0000\u0000\u024f\u0250\u0001\u0000\u0000\u0000\u0250\u0251\u0001\u0000"+
		"\u0000\u0000\u0251\u0253\u0005\u0012\u0000\u0000\u0252\u0244\u0001\u0000"+
		"\u0000\u0000\u0252\u0253\u0001\u0000\u0000\u0000\u0253\u0013\u0001\u0000"+
		"\u0000\u0000\u0254\u0258\u0003\u0106\u0083\u0000\u0255\u0258\u0003\u00ca"+
		"e\u0000\u0256\u0258\u0003\u009cN\u0000\u0257\u0254\u0001\u0000\u0000\u0000"+
		"\u0257\u0255\u0001\u0000\u0000\u0000\u0257\u0256\u0001\u0000\u0000\u0000"+
		"\u0258\u0259\u0001\u0000\u0000\u0000\u0259\u0257\u0001\u0000\u0000\u0000"+
		"\u0259\u025a\u0001\u0000\u0000\u0000\u025a\u026e\u0001\u0000\u0000\u0000"+
		"\u025b\u025e\u0003\u0106\u0083\u0000\u025c\u025e\u0003\u00cae\u0000\u025d"+
		"\u025b\u0001\u0000\u0000\u0000\u025d\u025c\u0001\u0000\u0000\u0000\u025e"+
		"\u025f\u0001\u0000\u0000\u0000\u025f\u025d\u0001\u0000\u0000\u0000\u025f"+
		"\u0260\u0001\u0000\u0000\u0000\u0260\u0269\u0001\u0000\u0000\u0000\u0261"+
		"\u0266\u0005\u0010\u0000\u0000\u0262\u0265\u0003\u0106\u0083\u0000\u0263"+
		"\u0265\u0003\u00cae\u0000\u0264\u0262\u0001\u0000\u0000\u0000\u0264\u0263"+
		"\u0001\u0000\u0000\u0000\u0265\u0268\u0001\u0000\u0000\u0000\u0266\u0264"+
		"\u0001\u0000\u0000\u0000\u0266\u0267\u0001\u0000\u0000\u0000\u0267\u026a"+
		"\u0001\u0000\u0000\u0000\u0268\u0266\u0001\u0000\u0000\u0000\u0269\u0261"+
		"\u0001\u0000\u0000\u0000\u0269\u026a\u0001\u0000\u0000\u0000\u026a\u026e"+
		"\u0001\u0000\u0000\u0000\u026b\u026e\u0003\u0096K\u0000\u026c\u026e\u0003"+
		"\u008eG\u0000\u026d\u0257\u0001\u0000\u0000\u0000\u026d\u025d\u0001\u0000"+
		"\u0000\u0000\u026d\u026b\u0001\u0000\u0000\u0000\u026d\u026c\u0001\u0000"+
		"\u0000\u0000\u026e\u0015\u0001\u0000\u0000\u0000\u026f\u0271\u0005Q\u0000"+
		"\u0000\u0270\u0272\u0003\u0018\f\u0000\u0271\u0270\u0001\u0000\u0000\u0000"+
		"\u0271\u0272\u0001\u0000\u0000\u0000\u0272\u0273\u0001\u0000\u0000\u0000"+
		"\u0273\u0275\u0005<\u0000\u0000\u0274\u0276\u0003\"\u0011\u0000\u0275"+
		"\u0274\u0001\u0000\u0000\u0000\u0275\u0276\u0001\u0000\u0000\u0000\u0276"+
		"\u0277\u0001\u0000\u0000\u0000\u0277\u027c\u0003\u00e4r\u0000\u0278\u027d"+
		"\u0005[\u0000\u0000\u0279\u027d\u0005\\\u0000\u0000\u027a\u027b\u0005"+
		"]\u0000\u0000\u027b\u027d\u0005^\u0000\u0000\u027c\u0278\u0001\u0000\u0000"+
		"\u0000\u027c\u0279\u0001\u0000\u0000\u0000\u027c\u027a\u0001\u0000\u0000"+
		"\u0000\u027c\u027d\u0001\u0000\u0000\u0000\u027d\u0285\u0001\u0000\u0000"+
		"\u0000\u027e\u0286\u0005_\u0000\u0000\u027f\u0286\u0005`\u0000\u0000\u0280"+
		"\u0283\u0005a\u0000\u0000\u0281\u0282\u0005^\u0000\u0000\u0282\u0284\u0003"+
		"\u0102\u0081\u0000\u0283\u0281\u0001\u0000\u0000\u0000\u0283\u0284\u0001"+
		"\u0000\u0000\u0000\u0284\u0286\u0001\u0000\u0000\u0000\u0285\u027e\u0001"+
		"\u0000\u0000\u0000\u0285\u027f\u0001\u0000\u0000\u0000\u0285\u0280\u0001"+
		"\u0000\u0000\u0000\u0286\u0287\u0001\u0000\u0000\u0000\u0287\u0288\u0005"+
		"b\u0000\u0000\u0288\u028c\u0003\u00e4r\u0000\u0289\u028a\u0005c\u0000"+
		"\u0000\u028a\u028b\u0005d\u0000\u0000\u028b\u028d\u0005e\u0000\u0000\u028c"+
		"\u0289\u0001\u0000\u0000\u0000\u028c\u028d\u0001\u0000\u0000\u0000\u028d"+
		"\u0290\u0001\u0000\u0000\u0000\u028e\u028f\u0005f\u0000\u0000\u028f\u0291"+
		"\u0003\u008cF\u0000\u0290\u028e\u0001\u0000\u0000\u0000\u0290\u0291\u0001"+
		"\u0000\u0000\u0000\u0291\u0292\u0001\u0000\u0000\u0000\u0292\u029b\u0005"+
		"\u0007\u0000\u0000\u0293\u0298\u0003>\u001f\u0000\u0294\u0298\u00034\u001a"+
		"\u0000\u0295\u0298\u00032\u0019\u0000\u0296\u0298\u0003J%\u0000\u0297"+
		"\u0293\u0001\u0000\u0000\u0000\u0297\u0294\u0001\u0000\u0000\u0000\u0297"+
		"\u0295\u0001\u0000\u0000\u0000\u0297\u0296\u0001\u0000\u0000\u0000\u0298"+
		"\u0299\u0001\u0000\u0000\u0000\u0299\u029a\u0005\u0001\u0000\u0000\u029a"+
		"\u029c\u0001\u0000\u0000\u0000\u029b\u0297\u0001\u0000\u0000\u0000\u029c"+
		"\u029d\u0001\u0000\u0000\u0000\u029d\u029b\u0001\u0000\u0000\u0000\u029d"+
		"\u029e\u0001\u0000\u0000\u0000\u029e\u02a0\u0001\u0000\u0000\u0000\u029f"+
		"\u02a1\u0005\u000e\u0000\u0000\u02a0\u029f\u0001\u0000\u0000\u0000\u02a0"+
		"\u02a1\u0001\u0000\u0000\u0000\u02a1\u0017\u0001\u0000\u0000\u0000\u02a2"+
		"\u02a3\u0007\b\u0000\u0000\u02a3\u0019\u0001\u0000\u0000\u0000\u02a4\u02a6"+
		"\u0005Q\u0000\u0000\u02a5\u02a7\u0003\u0018\f\u0000\u02a6\u02a5\u0001"+
		"\u0000\u0000\u0000\u02a6\u02a7\u0001\u0000\u0000\u0000\u02a7\u02aa\u0001"+
		"\u0000\u0000\u0000\u02a8\u02a9\u0005i\u0000\u0000\u02a9\u02ab\u0005j\u0000"+
		"\u0000\u02aa\u02a8\u0001\u0000\u0000\u0000\u02aa\u02ab\u0001\u0000\u0000"+
		"\u0000\u02ab\u02ac\u0001\u0000\u0000\u0000\u02ac\u02ae\u0005C\u0000\u0000"+
		"\u02ad\u02af\u0003\"\u0011\u0000\u02ae\u02ad\u0001\u0000\u0000\u0000\u02ae"+
		"\u02af\u0001\u0000\u0000\u0000\u02af\u02b0\u0001\u0000\u0000\u0000\u02b0"+
		"\u02b2\u0003\u00e4r\u0000\u02b1\u02b3\u0003\u00deo\u0000\u02b2\u02b1\u0001"+
		"\u0000\u0000\u0000\u02b2\u02b3\u0001\u0000\u0000\u0000\u02b3\u02b4\u0001"+
		"\u0000\u0000\u0000\u02b4\u02b5\u0005\u0006\u0000\u0000\u02b5\u02b6\u0003"+
		"J%\u0000\u02b6\u001b\u0001\u0000\u0000\u0000\u02b7\u02b9\u0005Q\u0000"+
		"\u0000\u02b8\u02ba\u0005k\u0000\u0000\u02b9\u02b8\u0001\u0000\u0000\u0000"+
		"\u02b9\u02ba\u0001\u0000\u0000\u0000\u02ba\u02bb\u0001\u0000\u0000\u0000"+
		"\u02bb\u02bd\u0005A\u0000\u0000\u02bc\u02be\u0003\"\u0011\u0000\u02bd"+
		"\u02bc\u0001\u0000\u0000\u0000\u02bd\u02be\u0001\u0000\u0000\u0000\u02be"+
		"\u02bf\u0001\u0000\u0000\u0000\u02bf\u02c0\u0003\u00e4r\u0000\u02c0\u02c1"+
		"\u0005b\u0000\u0000\u02c1\u02c2\u0003\u00e4r\u0000\u02c2\u02c4\u0003 "+
		"\u0010\u0000\u02c3\u02c5\u0003r9\u0000\u02c4\u02c3\u0001\u0000\u0000\u0000"+
		"\u02c4\u02c5\u0001\u0000\u0000\u0000\u02c5\u001d\u0001\u0000\u0000\u0000"+
		"\u02c6\u02c9\u0003\u00e4r\u0000\u02c7\u02c9\u0003\u008cF\u0000\u02c8\u02c6"+
		"\u0001\u0000\u0000\u0000\u02c8\u02c7\u0001\u0000\u0000\u0000\u02c9\u02cb"+
		"\u0001\u0000\u0000\u0000\u02ca\u02cc\u0003\u00c8d\u0000\u02cb\u02ca\u0001"+
		"\u0000\u0000\u0000\u02cb\u02cc\u0001\u0000\u0000\u0000\u02cc\u001f\u0001"+
		"\u0000\u0000\u0000\u02cd\u02ce\u0005\u0011\u0000\u0000\u02ce\u02d3\u0003"+
		"\u001e\u000f\u0000\u02cf\u02d0\u0005\u001d\u0000\u0000\u02d0\u02d2\u0003"+
		"\u001e\u000f\u0000\u02d1\u02cf\u0001\u0000\u0000\u0000\u02d2\u02d5\u0001"+
		"\u0000\u0000\u0000\u02d3\u02d1\u0001\u0000\u0000\u0000\u02d3\u02d4\u0001"+
		"\u0000\u0000\u0000\u02d4\u02d6\u0001\u0000\u0000\u0000\u02d5\u02d3\u0001"+
		"\u0000\u0000\u0000\u02d6\u02d7\u0005\u0012\u0000\u0000\u02d7!\u0001\u0000"+
		"\u0000\u0000\u02d8\u02d9\u00054\u0000\u0000\u02d9\u02da\u0005l\u0000\u0000"+
		"\u02da\u02db\u00055\u0000\u0000\u02db#\u0001\u0000\u0000\u0000\u02dc\u02de"+
		"\u0003\u0106\u0083\u0000\u02dd\u02df\u0003\u009cN\u0000\u02de\u02dd\u0001"+
		"\u0000\u0000\u0000\u02de\u02df\u0001\u0000\u0000\u0000\u02df\u02e3\u0001"+
		"\u0000\u0000\u0000\u02e0\u02e2\u0003&\u0013\u0000\u02e1\u02e0\u0001\u0000"+
		"\u0000\u0000\u02e2\u02e5\u0001\u0000\u0000\u0000\u02e3\u02e1\u0001\u0000"+
		"\u0000\u0000\u02e3\u02e4\u0001\u0000\u0000\u0000\u02e4%\u0001\u0000\u0000"+
		"\u0000\u02e5\u02e3\u0001\u0000\u0000\u0000\u02e6\u02e7\u0005m\u0000\u0000"+
		"\u02e7\u02e9\u0003\u0106\u0083\u0000\u02e8\u02e6\u0001\u0000\u0000\u0000"+
		"\u02e8\u02e9\u0001\u0000\u0000\u0000\u02e9\u031b\u0001\u0000\u0000\u0000"+
		"\u02ea\u02eb\u0005n\u0000\u0000\u02eb\u02ed\u0005o\u0000\u0000\u02ec\u02ee"+
		"\u0003\u00c8d\u0000\u02ed\u02ec\u0001\u0000\u0000\u0000\u02ed\u02ee\u0001"+
		"\u0000\u0000\u0000\u02ee\u02f0\u0001\u0000\u0000\u0000\u02ef\u02f1\u0003"+
		"*\u0015\u0000\u02f0\u02ef\u0001\u0000\u0000\u0000\u02f0\u02f1\u0001\u0000"+
		"\u0000\u0000\u02f1\u02f3\u0001\u0000\u0000\u0000\u02f2\u02f4\u0005p\u0000"+
		"\u0000\u02f3\u02f2\u0001\u0000\u0000\u0000\u02f3\u02f4\u0001\u0000\u0000"+
		"\u0000\u02f4\u031c\u0001\u0000\u0000\u0000\u02f5\u02f7\u0005l\u0000\u0000"+
		"\u02f6\u02f5\u0001\u0000\u0000\u0000\u02f6\u02f7\u0001\u0000\u0000\u0000"+
		"\u02f7\u02f8\u0001\u0000\u0000\u0000\u02f8\u02fa\u0005q\u0000\u0000\u02f9"+
		"\u02fb\u0003*\u0015\u0000\u02fa\u02f9\u0001\u0000\u0000\u0000\u02fa\u02fb"+
		"\u0001\u0000\u0000\u0000\u02fb\u031c\u0001\u0000\u0000\u0000\u02fc\u02fe"+
		"\u0005k\u0000\u0000\u02fd\u02ff\u0003*\u0015\u0000\u02fe\u02fd\u0001\u0000"+
		"\u0000\u0000\u02fe\u02ff\u0001\u0000\u0000\u0000\u02ff\u031c\u0001\u0000"+
		"\u0000\u0000\u0300\u0301\u0005r\u0000\u0000\u0301\u0302\u0005\u0011\u0000"+
		"\u0000\u0302\u0303\u0003\u008cF\u0000\u0303\u0306\u0005\u0012\u0000\u0000"+
		"\u0304\u0305\u0005W\u0000\u0000\u0305\u0307\u0005s\u0000\u0000\u0306\u0304"+
		"\u0001\u0000\u0000\u0000\u0306\u0307\u0001\u0000\u0000\u0000\u0307\u031c"+
		"\u0001\u0000\u0000\u0000\u0308\u030b\u0005t\u0000\u0000\u0309\u030c\u0003"+
		"\u00cae\u0000\u030a\u030c\u0003\u008cF\u0000\u030b\u0309\u0001\u0000\u0000"+
		"\u0000\u030b\u030a\u0001\u0000\u0000\u0000\u030c\u031c\u0001\u0000\u0000"+
		"\u0000\u030d\u030e\u0005u\u0000\u0000\u030e\u031c\u0003\u0106\u0083\u0000"+
		"\u030f\u031c\u0003,\u0016\u0000\u0310\u0311\u0005v\u0000\u0000\u0311\u0313"+
		"\u0005w\u0000\u0000\u0312\u0310\u0001\u0000\u0000\u0000\u0312\u0313\u0001"+
		"\u0000\u0000\u0000\u0313\u0314\u0001\u0000\u0000\u0000\u0314\u0315\u0005"+
		"\u0006\u0000\u0000\u0315\u0316\u0005\u0011\u0000\u0000\u0316\u0317\u0003"+
		"\u008cF\u0000\u0317\u0319\u0005\u0012\u0000\u0000\u0318\u031a\u0007\t"+
		"\u0000\u0000\u0319\u0318\u0001\u0000\u0000\u0000\u0319\u031a\u0001\u0000"+
		"\u0000\u0000\u031a\u031c\u0001\u0000\u0000\u0000\u031b\u02ea\u0001\u0000"+
		"\u0000\u0000\u031b\u02f6\u0001\u0000\u0000\u0000\u031b\u02fc\u0001\u0000"+
		"\u0000\u0000\u031b\u0300\u0001\u0000\u0000\u0000\u031b\u0308\u0001\u0000"+
		"\u0000\u0000\u031b\u030d\u0001\u0000\u0000\u0000\u031b\u030f\u0001\u0000"+
		"\u0000\u0000\u031b\u0312\u0001\u0000\u0000\u0000\u031c\u0321\u0001\u0000"+
		"\u0000\u0000\u031d\u031e\u0005m\u0000\u0000\u031e\u0320\u0003\u0106\u0083"+
		"\u0000\u031f\u031d\u0001\u0000\u0000\u0000\u0320\u0323\u0001\u0000\u0000"+
		"\u0000\u0321\u031f\u0001\u0000\u0000\u0000\u0321\u0322\u0001\u0000\u0000"+
		"\u0000\u0322\'\u0001\u0000\u0000\u0000\u0323\u0321\u0001\u0000\u0000\u0000"+
		"\u0324\u0325\u0005m\u0000\u0000\u0325\u0327\u0003\u0106\u0083\u0000\u0326"+
		"\u0324\u0001\u0000\u0000\u0000\u0326\u0327\u0001\u0000\u0000\u0000\u0327"+
		"\u0344\u0001\u0000\u0000\u0000\u0328\u0329\u0005r\u0000\u0000\u0329\u032a"+
		"\u0005\u0011\u0000\u0000\u032a\u032b\u0003\u008cF\u0000\u032b\u032d\u0005"+
		"\u0012\u0000\u0000\u032c\u032e\u0003*\u0015\u0000\u032d\u032c\u0001\u0000"+
		"\u0000\u0000\u032d\u032e\u0001\u0000\u0000\u0000\u032e\u0345\u0001\u0000"+
		"\u0000\u0000\u032f\u0330\u0005>\u0000\u0000\u0330\u0331\u0005o\u0000\u0000"+
		"\u0331\u0332\u0003\u0100\u0080\u0000\u0332\u0333\u0003,\u0016\u0000\u0333"+
		"\u0345\u0001\u0000\u0000\u0000\u0334\u0335\u0005n\u0000\u0000\u0335\u0338"+
		"\u0005o\u0000\u0000\u0336\u0338\u0005k\u0000\u0000\u0337\u0334\u0001\u0000"+
		"\u0000\u0000\u0337\u0336\u0001\u0000\u0000\u0000\u0338\u0339\u0001\u0000"+
		"\u0000\u0000\u0339\u033b\u0003 \u0010\u0000\u033a\u033c\u0003*\u0015\u0000"+
		"\u033b\u033a\u0001\u0000\u0000\u0000\u033b\u033c\u0001\u0000\u0000\u0000"+
		"\u033c\u0345\u0001\u0000\u0000\u0000\u033d\u033e\u0005n\u0000\u0000\u033e"+
		"\u033f\u0005o\u0000\u0000\u033f\u0340\u0005\u0011\u0000\u0000\u0340\u0341"+
		"\u0003\u0106\u0083\u0000\u0341\u0342\u0005p\u0000\u0000\u0342\u0343\u0005"+
		"\u0012\u0000\u0000\u0343\u0345\u0001\u0000\u0000\u0000\u0344\u0328\u0001"+
		"\u0000\u0000\u0000\u0344\u032f\u0001\u0000\u0000\u0000\u0344\u0337\u0001"+
		"\u0000\u0000\u0000\u0344\u033d\u0001\u0000\u0000\u0000\u0345\u0346\u0001"+
		"\u0000\u0000\u0000\u0346\u0344\u0001\u0000\u0000\u0000\u0346\u0347\u0001"+
		"\u0000\u0000\u0000\u0347\u034c\u0001\u0000\u0000\u0000\u0348\u0349\u0005"+
		"m\u0000\u0000\u0349\u034b\u0003\u0106\u0083\u0000\u034a\u0348\u0001\u0000"+
		"\u0000\u0000\u034b\u034e\u0001\u0000\u0000\u0000\u034c\u034a\u0001\u0000"+
		"\u0000\u0000\u034c\u034d\u0001\u0000\u0000\u0000\u034d)\u0001\u0000\u0000"+
		"\u0000\u034e\u034c\u0001\u0000\u0000\u0000\u034f\u0350\u0005b\u0000\u0000"+
		"\u0350\u0351\u0005y\u0000\u0000\u0351\u0352\u0003B!\u0000\u0352+\u0001"+
		"\u0000\u0000\u0000\u0353\u0354\u0005z\u0000\u0000\u0354\u0356\u0003\u0106"+
		"\u0083\u0000\u0355\u0357\u0003\u0100\u0080\u0000\u0356\u0355\u0001\u0000"+
		"\u0000\u0000\u0356\u0357\u0001\u0000\u0000\u0000\u0357\u0366\u0001\u0000"+
		"\u0000\u0000\u0358\u0359\u0005b\u0000\u0000\u0359\u0360\u0007\n\u0000"+
		"\u0000\u035a\u035b\u00051\u0000\u0000\u035b\u0361\u0007\u000b\u0000\u0000"+
		"\u035c\u0361\u00056\u0000\u0000\u035d\u0361\u00057\u0000\u0000\u035e\u035f"+
		"\u0005W\u0000\u0000\u035f\u0361\u0005{\u0000\u0000\u0360\u035a\u0001\u0000"+
		"\u0000\u0000\u0360\u035c\u0001\u0000\u0000\u0000\u0360\u035d\u0001\u0000"+
		"\u0000\u0000\u0360\u035e\u0001\u0000\u0000\u0000\u0361\u0365\u0001\u0000"+
		"\u0000\u0000\u0362\u0363\u0005|\u0000\u0000\u0363\u0365\u0007\f\u0000"+
		"\u0000\u0364\u0358\u0001\u0000\u0000\u0000\u0364\u0362\u0001\u0000\u0000"+
		"\u0000\u0365\u0368\u0001\u0000\u0000\u0000\u0366\u0364\u0001\u0000\u0000"+
		"\u0000\u0366\u0367\u0001\u0000\u0000\u0000\u0367\u0371\u0001\u0000\u0000"+
		"\u0000\u0368\u0366\u0001\u0000\u0000\u0000\u0369\u036b\u0005l\u0000\u0000"+
		"\u036a\u0369\u0001\u0000\u0000\u0000\u036a\u036b\u0001\u0000\u0000\u0000"+
		"\u036b\u036c\u0001\u0000\u0000\u0000\u036c\u036f\u0005\u0080\u0000\u0000"+
		"\u036d\u036e\u0005\u0081\u0000\u0000\u036e\u0370\u0007\r\u0000\u0000\u036f"+
		"\u036d\u0001\u0000\u0000\u0000\u036f\u0370\u0001\u0000\u0000\u0000\u0370"+
		"\u0372\u0001\u0000\u0000\u0000\u0371\u036a\u0001\u0000\u0000\u0000\u0371"+
		"\u0372\u0001\u0000\u0000\u0000\u0372-\u0001\u0000\u0000\u0000\u0373\u0375"+
		"\u0005V\u0000\u0000\u0374\u0376\u0005\u0082\u0000\u0000\u0375\u0374\u0001"+
		"\u0000\u0000\u0000\u0375\u0376\u0001\u0000\u0000\u0000\u0376\u0377\u0001"+
		"\u0000\u0000\u0000\u0377\u037c\u00030\u0018\u0000\u0378\u0379\u0005\u001d"+
		"\u0000\u0000\u0379\u037b\u00030\u0018\u0000\u037a\u0378\u0001\u0000\u0000"+
		"\u0000\u037b\u037e\u0001\u0000\u0000\u0000\u037c\u037a\u0001\u0000\u0000"+
		"\u0000\u037c\u037d\u0001\u0000\u0000\u0000\u037d/\u0001\u0000\u0000\u0000"+
		"\u037e\u037c\u0001\u0000\u0000\u0000\u037f\u038b\u0003\u00fe\u007f\u0000"+
		"\u0380\u0381\u0005\u0011\u0000\u0000\u0381\u0386\u0003\u0106\u0083\u0000"+
		"\u0382\u0383\u0005\u001d\u0000\u0000\u0383\u0385\u0003\u0106\u0083\u0000"+
		"\u0384\u0382\u0001\u0000\u0000\u0000\u0385\u0388\u0001\u0000\u0000\u0000"+
		"\u0386\u0384\u0001\u0000\u0000\u0000\u0386\u0387\u0001\u0000\u0000\u0000"+
		"\u0387\u0389\u0001\u0000\u0000\u0000\u0388\u0386\u0001\u0000\u0000\u0000"+
		"\u0389\u038a\u0005\u0012\u0000\u0000\u038a\u038c\u0001\u0000\u0000\u0000"+
		"\u038b\u0380\u0001\u0000\u0000\u0000\u038b\u038c\u0001\u0000\u0000\u0000"+
		"\u038c\u038d\u0001\u0000\u0000\u0000\u038d\u0392\u0005\u0006\u0000\u0000"+
		"\u038e\u0390\u0005l\u0000\u0000\u038f\u038e\u0001\u0000\u0000\u0000\u038f"+
		"\u0390\u0001\u0000\u0000\u0000\u0390\u0391\u0001\u0000\u0000\u0000\u0391"+
		"\u0393\u0005B\u0000\u0000\u0392\u038f\u0001\u0000\u0000\u0000\u0392\u0393"+
		"\u0001\u0000\u0000\u0000\u0393\u0394\u0001\u0000\u0000\u0000\u0394\u0395"+
		"\u0005\u0011\u0000\u0000\u0395\u0396\u0003J%\u0000\u0396\u03a6\u0005\u0012"+
		"\u0000\u0000\u0397\u0398\u0005L\u0000\u0000\u0398\u0399\u0007\u000e\u0000"+
		"\u0000\u0399\u039a\u0005\u0085\u0000\u0000\u039a\u039b\u0005\u0086\u0000"+
		"\u0000\u039b\u03a0\u0003\u0106\u0083\u0000\u039c\u039d\u0005\u001d\u0000"+
		"\u0000\u039d\u039f\u0003\u0106\u0083\u0000\u039e\u039c\u0001\u0000\u0000"+
		"\u0000\u039f\u03a2\u0001\u0000\u0000\u0000\u03a0\u039e\u0001\u0000\u0000"+
		"\u0000\u03a0\u03a1\u0001\u0000\u0000\u0000\u03a1\u03a3\u0001\u0000\u0000"+
		"\u0000\u03a2\u03a0\u0001\u0000\u0000\u0000\u03a3\u03a4\u00051\u0000\u0000"+
		"\u03a4\u03a5\u0003\u0106\u0083\u0000\u03a5\u03a7\u0001\u0000\u0000\u0000"+
		"\u03a6\u0397\u0001\u0000\u0000\u0000\u03a6\u03a7\u0001\u0000\u0000\u0000"+
		"\u03a7\u03be\u0001\u0000\u0000\u0000\u03a8\u03a9\u0005\u0087\u0000\u0000"+
		"\u03a9\u03ae\u0003\u0106\u0083\u0000\u03aa\u03ab\u0005\u001d\u0000\u0000"+
		"\u03ab\u03ad\u0003\u0106\u0083\u0000\u03ac\u03aa\u0001\u0000\u0000\u0000"+
		"\u03ad\u03b0\u0001\u0000\u0000\u0000\u03ae\u03ac\u0001\u0000\u0000\u0000"+
		"\u03ae\u03af\u0001\u0000\u0000\u0000\u03af\u03b1\u0001\u0000\u0000\u0000"+
		"\u03b0\u03ae\u0001\u0000\u0000\u0000\u03b1\u03b2\u00051\u0000\u0000\u03b2"+
		"\u03b8\u0003\u0106\u0083\u0000\u03b3\u03b4\u0005\u0018\u0000\u0000\u03b4"+
		"\u03b5\u0003\u00cae\u0000\u03b5\u03b6\u0005t\u0000\u0000\u03b6\u03b7\u0003"+
		"\u00cae\u0000\u03b7\u03b9\u0001\u0000\u0000\u0000\u03b8\u03b3\u0001\u0000"+
		"\u0000\u0000\u03b8\u03b9\u0001\u0000\u0000\u0000\u03b9\u03bc\u0001\u0000"+
		"\u0000\u0000\u03ba\u03bb\u0005Z\u0000\u0000\u03bb\u03bd\u0003\u0106\u0083"+
		"\u0000\u03bc\u03ba\u0001\u0000\u0000\u0000\u03bc\u03bd\u0001\u0000\u0000"+
		"\u0000\u03bd\u03bf\u0001\u0000\u0000\u0000\u03be\u03a8\u0001\u0000\u0000"+
		"\u0000\u03be\u03bf\u0001\u0000\u0000\u0000\u03bf1\u0001\u0000\u0000\u0000"+
		"\u03c0\u03c2\u0003.\u0017\u0000\u03c1\u03c0\u0001\u0000\u0000\u0000\u03c1"+
		"\u03c2\u0001\u0000\u0000\u0000\u03c2\u03c3\u0001\u0000\u0000\u0000\u03c3"+
		"\u03c4\u0005_\u0000\u0000\u03c4\u03c6\u0005\u0088\u0000\u0000\u03c5\u03c7"+
		"\u0005\u0089\u0000\u0000\u03c6\u03c5\u0001\u0000\u0000\u0000\u03c6\u03c7"+
		"\u0001\u0000\u0000\u0000\u03c7\u03c8\u0001\u0000\u0000\u0000\u03c8\u03cb"+
		"\u0003\u00e4r\u0000\u03c9\u03ca\u0005\u0006\u0000\u0000\u03ca\u03cc\u0003"+
		"\u0106\u0083\u0000\u03cb\u03c9\u0001\u0000\u0000\u0000\u03cb\u03cc\u0001"+
		"\u0000\u0000\u0000\u03cc\u03ce\u0001\u0000\u0000\u0000\u03cd\u03cf\u0003"+
		"D\"\u0000\u03ce\u03cd\u0001\u0000\u0000\u0000\u03ce\u03cf\u0001\u0000"+
		"\u0000\u0000\u03cf\u03d9\u0001\u0000\u0000\u0000\u03d0\u03d1\u0005Z\u0000"+
		"\u0000\u03d1\u03d6\u0003P(\u0000\u03d2\u03d3\u0005\u001d\u0000\u0000\u03d3"+
		"\u03d5\u0003P(\u0000\u03d4\u03d2\u0001\u0000\u0000\u0000\u03d5\u03d8\u0001"+
		"\u0000\u0000\u0000\u03d6\u03d4\u0001\u0000\u0000\u0000\u03d6\u03d7\u0001"+
		"\u0000\u0000\u0000\u03d7\u03da\u0001\u0000\u0000\u0000\u03d8\u03d6\u0001"+
		"\u0000\u0000\u0000\u03d9\u03d0\u0001\u0000\u0000\u0000\u03d9\u03da\u0001"+
		"\u0000\u0000\u0000\u03da\u03dc\u0001\u0000\u0000\u0000\u03db\u03dd\u0003"+
		"r9\u0000\u03dc\u03db\u0001\u0000\u0000\u0000\u03dc\u03dd\u0001\u0000\u0000"+
		"\u0000\u03dd\u03df\u0001\u0000\u0000\u0000\u03de\u03e0\u0003F#\u0000\u03df"+
		"\u03de\u0001\u0000\u0000\u0000\u03df\u03e0\u0001\u0000\u0000\u0000\u03e0"+
		"\u03e2\u0001\u0000\u0000\u0000\u03e1\u03e3\u0003\u00c4b\u0000\u03e2\u03e1"+
		"\u0001\u0000\u0000\u0000\u03e2\u03e3\u0001\u0000\u0000\u0000\u03e3\u03e5"+
		"\u0001\u0000\u0000\u0000\u03e4\u03e6\u0003\\.\u0000\u03e5\u03e4\u0001"+
		"\u0000\u0000\u0000\u03e5\u03e6\u0001\u0000\u0000\u0000\u03e6\u03e8\u0001"+
		"\u0000\u0000\u0000\u03e7\u03e9\u0003Z-\u0000\u03e8\u03e7\u0001\u0000\u0000"+
		"\u0000\u03e8\u03e9\u0001\u0000\u0000\u0000\u03e93\u0001\u0000\u0000\u0000"+
		"\u03ea\u03ec\u0003.\u0017\u0000\u03eb\u03ea\u0001\u0000\u0000\u0000\u03eb"+
		"\u03ec\u0001\u0000\u0000\u0000\u03ec\u03f3\u0001\u0000\u0000\u0000\u03ed"+
		"\u03f0\u0005`\u0000\u0000\u03ee\u03ef\u0005i\u0000\u0000\u03ef\u03f1\u0003"+
		"B!\u0000\u03f0\u03ee\u0001\u0000\u0000\u0000\u03f0\u03f1\u0001\u0000\u0000"+
		"\u0000\u03f1\u03f4\u0001\u0000\u0000\u0000\u03f2\u03f4\u0005j\u0000\u0000"+
		"\u03f3\u03ed\u0001\u0000\u0000\u0000\u03f3\u03f2\u0001\u0000\u0000\u0000"+
		"\u03f4\u03f5\u0001\u0000\u0000\u0000\u03f5\u03f6\u0005\u001a\u0000\u0000"+
		"\u03f6\u03f9\u0003\u00e4r\u0000\u03f7\u03f8\u0005\u0006\u0000\u0000\u03f8"+
		"\u03fa\u0003\u0106\u0083\u0000\u03f9\u03f7\u0001\u0000\u0000\u0000\u03f9"+
		"\u03fa\u0001\u0000\u0000\u0000\u03fa\u03fc\u0001\u0000\u0000\u0000\u03fb"+
		"\u03fd\u0003\u0100\u0080\u0000\u03fc\u03fb\u0001\u0000\u0000\u0000\u03fc"+
		"\u03fd\u0001\u0000\u0000\u0000\u03fd\u03ff\u0001\u0000\u0000\u0000\u03fe"+
		"\u0400\u00036\u001b\u0000\u03ff\u03fe\u0001\u0000\u0000\u0000\u03ff\u0400"+
		"\u0001\u0000\u0000\u0000\u0400\u040a\u0001\u0000\u0000\u0000\u0401\u0405"+
		"\u0003R)\u0000\u0402\u0404\u00038\u001c\u0000\u0403\u0402\u0001\u0000"+
		"\u0000\u0000\u0404\u0407\u0001\u0000\u0000\u0000\u0405\u0403\u0001\u0000"+
		"\u0000\u0000\u0405\u0406\u0001\u0000\u0000\u0000\u0406\u040b\u0001\u0000"+
		"\u0000\u0000\u0407\u0405\u0001\u0000\u0000\u0000\u0408\u0409\u0005t\u0000"+
		"\u0000\u0409\u040b\u0005\u008a\u0000\u0000\u040a\u0401\u0001\u0000\u0000"+
		"\u0000\u040a\u0408\u0001\u0000\u0000\u0000\u040b\u040d\u0001\u0000\u0000"+
		"\u0000\u040c\u040e\u0003F#\u0000\u040d\u040c\u0001\u0000\u0000\u0000\u040d"+
		"\u040e\u0001\u0000\u0000\u0000\u040e5\u0001\u0000\u0000\u0000\u040f\u0410"+
		"\u0005\u008b\u0000\u0000\u0410\u0411\u0007\u000f\u0000\u0000\u0411\u0412"+
		"\u0005\u008e\u0000\u0000\u04127\u0001\u0000\u0000\u0000\u0413\u0414\u0005"+
		"b\u0000\u0000\u0414\u0419\u0005y\u0000\u0000\u0415\u0417\u0003\u0086C"+
		"\u0000\u0416\u0418\u0003r9\u0000\u0417\u0416\u0001\u0000\u0000\u0000\u0417"+
		"\u0418\u0001\u0000\u0000\u0000\u0418\u041a\u0001\u0000\u0000\u0000\u0419"+
		"\u0415\u0001\u0000\u0000\u0000\u0419\u041a\u0001\u0000\u0000\u0000\u041a"+
		"\u041b\u0001\u0000\u0000\u0000\u041b\u042a\u0005\u008f\u0000\u0000\u041c"+
		"\u042b\u0005\u0090\u0000\u0000\u041d\u041e\u0005a\u0000\u0000\u041e\u041f"+
		"\u00051\u0000\u0000\u041f\u0424\u0003@ \u0000\u0420\u0421\u0005\u001d"+
		"\u0000\u0000\u0421\u0423\u0003@ \u0000\u0422\u0420\u0001\u0000\u0000\u0000"+
		"\u0423\u0426\u0001\u0000\u0000\u0000\u0424\u0422\u0001\u0000\u0000\u0000"+
		"\u0424\u0425\u0001\u0000\u0000\u0000\u0425\u0428\u0001\u0000\u0000\u0000"+
		"\u0426\u0424\u0001\u0000\u0000\u0000\u0427\u0429\u0003r9\u0000\u0428\u0427"+
		"\u0001\u0000\u0000\u0000\u0428\u0429\u0001\u0000\u0000\u0000\u0429\u042b"+
		"\u0001\u0000\u0000\u0000\u042a\u041c\u0001\u0000\u0000\u0000\u042a\u041d"+
		"\u0001\u0000\u0000\u0000\u042b9\u0001\u0000\u0000\u0000\u042c\u042e\u0003"+
		".\u0017\u0000\u042d\u042c\u0001\u0000\u0000\u0000\u042d\u042e\u0001\u0000"+
		"\u0000\u0000\u042e\u042f\u0001\u0000\u0000\u0000\u042f\u0430\u0005\u0091"+
		"\u0000\u0000\u0430\u0432\u0005\u001a\u0000\u0000\u0431\u0433\u0005\u0089"+
		"\u0000\u0000\u0432\u0431\u0001\u0000\u0000\u0000\u0432\u0433\u0001\u0000"+
		"\u0000\u0000\u0433\u0434\u0001\u0000\u0000\u0000\u0434\u0439\u0003\u0106"+
		"\u0083\u0000\u0435\u0437\u0005\u0006\u0000\u0000\u0436\u0435\u0001\u0000"+
		"\u0000\u0000\u0436\u0437\u0001\u0000\u0000\u0000\u0437\u0438\u0001\u0000"+
		"\u0000\u0000\u0438\u043a\u0003\u0106\u0083\u0000\u0439\u0436\u0001\u0000"+
		"\u0000\u0000\u0439\u043a\u0001\u0000\u0000\u0000\u043a\u043b\u0001\u0000"+
		"\u0000\u0000\u043b\u043d\u0005Z\u0000\u0000\u043c\u043e\u0005\u0089\u0000"+
		"\u0000\u043d\u043c\u0001\u0000\u0000\u0000\u043d\u043e\u0001\u0000\u0000"+
		"\u0000\u043e\u043f\u0001\u0000\u0000\u0000\u043f\u0440\u0003R)\u0000\u0440"+
		"\u0441\u0005b\u0000\u0000\u0441\u0445\u0003\u0086C\u0000\u0442\u0444\u0003"+
		"<\u001e\u0000\u0443\u0442\u0001\u0000\u0000\u0000\u0444\u0447\u0001\u0000"+
		"\u0000\u0000\u0445\u0443\u0001\u0000\u0000\u0000\u0445\u0446\u0001\u0000"+
		"\u0000\u0000\u0446;\u0001\u0000\u0000\u0000\u0447\u0445\u0001\u0000\u0000"+
		"\u0000\u0448\u044a\u0005f\u0000\u0000\u0449\u044b\u0005l\u0000\u0000\u044a"+
		"\u0449\u0001\u0000\u0000\u0000\u044a\u044b\u0001\u0000\u0000\u0000\u044b"+
		"\u044c\u0001\u0000\u0000\u0000\u044c\u0451\u0005\u0092\u0000\u0000\u044d"+
		"\u044e\u0005\u0093\u0000\u0000\u044e\u0450\u0003\u0090H\u0000\u044f\u044d"+
		"\u0001\u0000\u0000\u0000\u0450\u0453\u0001\u0000\u0000\u0000\u0451\u044f"+
		"\u0001\u0000\u0000\u0000\u0451\u0452\u0001\u0000\u0000\u0000\u0452\u0454"+
		"\u0001\u0000\u0000\u0000\u0453\u0451\u0001\u0000\u0000\u0000\u0454\u0477"+
		"\u0005\u0094\u0000\u0000\u0455\u0457\u0005`\u0000\u0000\u0456\u0458\u0003"+
		"\u0104\u0082\u0000\u0457\u0456\u0001\u0000\u0000\u0000\u0457\u0458\u0001"+
		"\u0000\u0000\u0000\u0458\u045a\u0001\u0000\u0000\u0000\u0459\u045b\u0003"+
		"6\u001b\u0000\u045a\u0459\u0001\u0000\u0000\u0000\u045a\u045b\u0001\u0000"+
		"\u0000\u0000\u045b\u045f\u0001\u0000\u0000\u0000\u045c\u0460\u0003\u00a6"+
		"S\u0000\u045d\u045e\u0005t\u0000\u0000\u045e\u0460\u0005\u008a\u0000\u0000"+
		"\u045f\u045c\u0001\u0000\u0000\u0000\u045f\u045d\u0001\u0000\u0000\u0000"+
		"\u0460\u0462\u0001\u0000\u0000\u0000\u0461\u0463\u0003r9\u0000\u0462\u0461"+
		"\u0001\u0000\u0000\u0000\u0462\u0463\u0001\u0000\u0000\u0000\u0463\u0478"+
		"\u0001\u0000\u0000\u0000\u0464\u0465\u0005a\u0000\u0000\u0465\u0466\u0005"+
		"1\u0000\u0000\u0466\u046b\u0003@ \u0000\u0467\u0468\u0005\u001d\u0000"+
		"\u0000\u0468\u046a\u0003@ \u0000\u0469\u0467\u0001\u0000\u0000\u0000\u046a"+
		"\u046d\u0001\u0000\u0000\u0000\u046b\u0469\u0001\u0000\u0000\u0000\u046b"+
		"\u046c\u0001\u0000\u0000\u0000\u046c\u0472\u0001\u0000\u0000\u0000\u046d"+
		"\u046b\u0001\u0000\u0000\u0000\u046e\u0470\u0005_\u0000\u0000\u046f\u046e"+
		"\u0001\u0000\u0000\u0000\u046f\u0470\u0001\u0000\u0000\u0000\u0470\u0471"+
		"\u0001\u0000\u0000\u0000\u0471\u0473\u0003r9\u0000\u0472\u046f\u0001\u0000"+
		"\u0000\u0000\u0472\u0473\u0001\u0000\u0000\u0000\u0473\u0478\u0001\u0000"+
		"\u0000\u0000\u0474\u0478\u0005_\u0000\u0000\u0475\u0476\u0005\u008f\u0000"+
		"\u0000\u0476\u0478\u0005\u0090\u0000\u0000\u0477\u0455\u0001\u0000\u0000"+
		"\u0000\u0477\u0464\u0001\u0000\u0000\u0000\u0477\u0474\u0001\u0000\u0000"+
		"\u0000\u0477\u0475\u0001\u0000\u0000\u0000\u0478=\u0001\u0000\u0000\u0000"+
		"\u0479\u047b\u0003.\u0017\u0000\u047a\u0479\u0001\u0000\u0000\u0000\u047a"+
		"\u047b\u0001\u0000\u0000\u0000\u047b\u047c\u0001\u0000\u0000\u0000\u047c"+
		"\u047f\u0005a\u0000\u0000\u047d\u047e\u0005i\u0000\u0000\u047e\u0480\u0003"+
		"B!\u0000\u047f\u047d\u0001\u0000\u0000\u0000\u047f\u0480\u0001\u0000\u0000"+
		"\u0000\u0480\u0481\u0001\u0000\u0000\u0000\u0481\u0486\u0003\u00e4r\u0000"+
		"\u0482\u0484\u0005\u0006\u0000\u0000\u0483\u0482\u0001\u0000\u0000\u0000"+
		"\u0483\u0484\u0001\u0000\u0000\u0000\u0484\u0485\u0001\u0000\u0000\u0000"+
		"\u0485\u0487\u0003\u0106\u0083\u0000\u0486\u0483\u0001\u0000\u0000\u0000"+
		"\u0486\u0487\u0001\u0000\u0000\u0000\u0487\u0489\u0001\u0000\u0000\u0000"+
		"\u0488\u048a\u0003D\"\u0000\u0489\u0488\u0001\u0000\u0000\u0000\u0489"+
		"\u048a\u0001\u0000\u0000\u0000\u048a\u048b\u0001\u0000\u0000\u0000\u048b"+
		"\u048c\u00051\u0000\u0000\u048c\u0491\u0003@ \u0000\u048d\u048e\u0005"+
		"\u001d\u0000\u0000\u048e\u0490\u0003@ \u0000\u048f\u048d\u0001\u0000\u0000"+
		"\u0000\u0490\u0493\u0001\u0000\u0000\u0000\u0491\u048f\u0001\u0000\u0000"+
		"\u0000\u0491\u0492\u0001\u0000\u0000\u0000\u0492\u0496\u0001\u0000\u0000"+
		"\u0000\u0493\u0491\u0001\u0000\u0000\u0000\u0494\u0495\u0005\u0088\u0000"+
		"\u0000\u0495\u0497\u0003P(\u0000\u0496\u0494\u0001\u0000\u0000\u0000\u0496"+
		"\u0497\u0001\u0000\u0000\u0000\u0497\u0499\u0001\u0000\u0000\u0000\u0498"+
		"\u049a\u0003r9\u0000\u0499\u0498\u0001\u0000\u0000\u0000\u0499\u049a\u0001"+
		"\u0000\u0000\u0000\u049a\u049c\u0001\u0000\u0000\u0000\u049b\u049d\u0003"+
		"F#\u0000\u049c\u049b\u0001\u0000\u0000\u0000\u049c\u049d\u0001\u0000\u0000"+
		"\u0000\u049d\u049f\u0001\u0000\u0000\u0000\u049e\u04a0\u0003\u00c4b\u0000"+
		"\u049f\u049e\u0001\u0000\u0000\u0000\u049f\u04a0\u0001\u0000\u0000\u0000"+
		"\u04a0\u04a2\u0001\u0000\u0000\u0000\u04a1\u04a3\u0003\\.\u0000\u04a2"+
		"\u04a1\u0001\u0000\u0000\u0000\u04a2\u04a3\u0001\u0000\u0000\u0000\u04a3"+
		"\u04a5\u0001\u0000\u0000\u0000\u04a4\u04a6\u0003Z-\u0000\u04a5\u04a4\u0001"+
		"\u0000\u0000\u0000\u04a5\u04a6\u0001\u0000\u0000\u0000\u04a6?\u0001\u0000"+
		"\u0000\u0000\u04a7\u04aa\u0003\u00e4r\u0000\u04a8\u04aa\u0003\u0104\u0082"+
		"\u0000\u04a9\u04a7\u0001\u0000\u0000\u0000\u04a9\u04a8\u0001\u0000\u0000"+
		"\u0000\u04aa\u04ab\u0001\u0000\u0000\u0000\u04ab\u04ac\u0005\u0010\u0000"+
		"\u0000\u04ac\u04ad\u0003\u008cF\u0000\u04adA\u0001\u0000\u0000\u0000\u04ae"+
		"\u04af\u0007\u0010\u0000\u0000\u04afC\u0001\u0000\u0000\u0000\u04b0\u04b1"+
		"\u0005l\u0000\u0000\u04b1\u04b6\u0005\u0098\u0000\u0000\u04b2\u04b3\u0005"+
		"\u0098\u0000\u0000\u04b3\u04b4\u0005\u0086\u0000\u0000\u04b4\u04b6\u0003"+
		"\u00e4r\u0000\u04b5\u04b0\u0001\u0000\u0000\u0000\u04b5\u04b2\u0001\u0000"+
		"\u0000\u0000\u04b6E\u0001\u0000\u0000\u0000\u04b7\u04b8\u0005\u0099\u0000"+
		"\u0000\u04b8\u04bd\u0003H$\u0000\u04b9\u04ba\u0005\u001d\u0000\u0000\u04ba"+
		"\u04bc\u0003H$\u0000\u04bb\u04b9\u0001\u0000\u0000\u0000\u04bc\u04bf\u0001"+
		"\u0000\u0000\u0000\u04bd\u04bb\u0001\u0000\u0000\u0000\u04bd\u04be\u0001"+
		"\u0000\u0000\u0000\u04beG\u0001\u0000\u0000\u0000\u04bf\u04bd\u0001\u0000"+
		"\u0000\u0000\u04c0\u04c9\u0005\u009a\u0000\u0000\u04c1\u04c6\u0003\u008c"+
		"F\u0000\u04c2\u04c4\u0005\u0006\u0000\u0000\u04c3\u04c2\u0001\u0000\u0000"+
		"\u0000\u04c3\u04c4\u0001\u0000\u0000\u0000\u04c4\u04c5\u0001\u0000\u0000"+
		"\u0000\u04c5\u04c7\u0003\u00fe\u007f\u0000\u04c6\u04c3\u0001\u0000\u0000"+
		"\u0000\u04c6\u04c7\u0001\u0000\u0000\u0000\u04c7\u04c9\u0001\u0000\u0000"+
		"\u0000\u04c8\u04c0\u0001\u0000\u0000\u0000\u04c8\u04c1\u0001\u0000\u0000"+
		"\u0000\u04c9I\u0001\u0000\u0000\u0000\u04ca\u04cc\u0006%\uffff\uffff\u0000"+
		"\u04cb\u04cd\u0003.\u0017\u0000\u04cc\u04cb\u0001\u0000\u0000\u0000\u04cc"+
		"\u04cd\u0001\u0000\u0000\u0000\u04cd\u04ce\u0001\u0000\u0000\u0000\u04ce"+
		"\u04d6\u0003L&\u0000\u04cf\u04d5\u0003\u00c4b\u0000\u04d0\u04d5\u0003"+
		"Z-\u0000\u04d1\u04d5\u0003^/\u0000\u04d2\u04d5\u0003\\.\u0000\u04d3\u04d5"+
		"\u0003`0\u0000\u04d4\u04cf\u0001\u0000\u0000\u0000\u04d4\u04d0\u0001\u0000"+
		"\u0000\u0000\u04d4\u04d1\u0001\u0000\u0000\u0000\u04d4\u04d2\u0001\u0000"+
		"\u0000\u0000\u04d4\u04d3\u0001\u0000\u0000\u0000\u04d5\u04d8\u0001\u0000"+
		"\u0000\u0000\u04d6\u04d4\u0001\u0000\u0000\u0000\u04d6\u04d7\u0001\u0000"+
		"\u0000\u0000\u04d7\u04de\u0001\u0000\u0000\u0000\u04d8\u04d6\u0001\u0000"+
		"\u0000\u0000\u04d9\u04da\u0005\u0011\u0000\u0000\u04da\u04db\u0003J%\u0000"+
		"\u04db\u04dc\u0005\u0012\u0000\u0000\u04dc\u04de\u0001\u0000\u0000\u0000"+
		"\u04dd\u04ca\u0001\u0000\u0000\u0000\u04dd\u04d9\u0001\u0000\u0000\u0000"+
		"\u04de\u04e9\u0001\u0000\u0000\u0000\u04df\u04e3\n\u0002\u0000\u0000\u04e0"+
		"\u04e1\u0003N\'\u0000\u04e1\u04e2\u0003J%\u0000\u04e2\u04e4\u0001\u0000"+
		"\u0000\u0000\u04e3\u04e0\u0001\u0000\u0000\u0000\u04e4\u04e5\u0001\u0000"+
		"\u0000\u0000\u04e5\u04e3\u0001\u0000\u0000\u0000\u04e5\u04e6\u0001\u0000"+
		"\u0000\u0000\u04e6\u04e8\u0001\u0000\u0000\u0000\u04e7\u04df\u0001\u0000"+
		"\u0000\u0000\u04e8\u04eb\u0001\u0000\u0000\u0000\u04e9\u04e7\u0001\u0000"+
		"\u0000\u0000\u04e9\u04ea\u0001\u0000\u0000\u0000\u04eaK\u0001\u0000\u0000"+
		"\u0000\u04eb\u04e9\u0001\u0000\u0000\u0000\u04ec\u04ee\u0005\u009b\u0000"+
		"\u0000\u04ed\u04ef\u0003b1\u0000\u04ee\u04ed\u0001\u0000\u0000\u0000\u04ee"+
		"\u04ef\u0001\u0000\u0000\u0000\u04ef\u04f1\u0001\u0000\u0000\u0000\u04f0"+
		"\u04f2\u0003d2\u0000\u04f1\u04f0\u0001\u0000\u0000\u0000\u04f1\u04f2\u0001"+
		"\u0000\u0000\u0000\u04f2\u04fe\u0001\u0000\u0000\u0000\u04f3\u04f8\u0003"+
		"f3\u0000\u04f4\u04f5\u0005\u001d\u0000\u0000\u04f5\u04f7\u0003f3\u0000"+
		"\u04f6\u04f4\u0001\u0000\u0000\u0000\u04f7\u04fa\u0001\u0000\u0000\u0000"+
		"\u04f8\u04f6\u0001\u0000\u0000\u0000\u04f8\u04f9\u0001\u0000\u0000\u0000"+
		"\u04f9\u04fc\u0001\u0000\u0000\u0000\u04fa\u04f8\u0001\u0000\u0000\u0000"+
		"\u04fb\u04fd\u0005\u001d\u0000\u0000\u04fc\u04fb\u0001\u0000\u0000\u0000"+
		"\u04fc\u04fd\u0001\u0000\u0000\u0000\u04fd\u04ff\u0001\u0000\u0000\u0000"+
		"\u04fe\u04f3\u0001\u0000\u0000\u0000\u04fe\u04ff\u0001\u0000\u0000\u0000"+
		"\u04ff\u0501\u0001\u0000\u0000\u0000\u0500\u0502\u0003h4\u0000\u0501\u0500"+
		"\u0001\u0000\u0000\u0000\u0501\u0502\u0001\u0000\u0000\u0000\u0502\u0505"+
		"\u0001\u0000\u0000\u0000\u0503\u0504\u0005\u0088\u0000\u0000\u0504\u0506"+
		"\u0003P(\u0000\u0505\u0503\u0001\u0000\u0000\u0000\u0505\u0506\u0001\u0000"+
		"\u0000\u0000\u0506\u0508\u0001\u0000\u0000\u0000\u0507\u0509\u0003r9\u0000"+
		"\u0508\u0507\u0001\u0000\u0000\u0000\u0508\u0509\u0001\u0000\u0000\u0000"+
		"\u0509\u050b\u0001\u0000\u0000\u0000\u050a\u050c\u0003t:\u0000\u050b\u050a"+
		"\u0001\u0000\u0000\u0000\u050b\u050c\u0001\u0000\u0000\u0000\u050c\u050e"+
		"\u0001\u0000\u0000\u0000\u050d\u050f\u0003x<\u0000\u050e\u050d\u0001\u0000"+
		"\u0000\u0000\u050e\u050f\u0001\u0000\u0000\u0000\u050f\u0511\u0001\u0000"+
		"\u0000\u0000\u0510\u0512\u0003z=\u0000\u0511\u0510\u0001\u0000\u0000\u0000"+
		"\u0511\u0512\u0001\u0000\u0000\u0000\u0512\u0514\u0001\u0000\u0000\u0000"+
		"\u0513\u0515\u0003~?\u0000\u0514\u0513\u0001\u0000\u0000\u0000\u0514\u0515"+
		"\u0001\u0000\u0000\u0000\u0515\u051b\u0001\u0000\u0000\u0000\u0516\u0517"+
		"\u0005\u008a\u0000\u0000\u0517\u051b\u0003\u0086C\u0000\u0518\u0519\u0005"+
		",\u0000\u0000\u0519\u051b\u0003\u00e2q\u0000\u051a\u04ec\u0001\u0000\u0000"+
		"\u0000\u051a\u0516\u0001\u0000\u0000\u0000\u051a\u0518\u0001\u0000\u0000"+
		"\u0000\u051bM\u0001\u0000\u0000\u0000\u051c\u0525\u0005\u009c\u0000\u0000"+
		"\u051d\u0525\u0005\u009d\u0000\u0000\u051e\u0520\u0005\u009e\u0000\u0000"+
		"\u051f\u0521\u0005\u009f\u0000\u0000\u0520\u051f\u0001\u0000\u0000\u0000"+
		"\u0520\u0521\u0001\u0000\u0000\u0000\u0521\u0525\u0001\u0000\u0000\u0000"+
		"\u0522\u0525\u0005\u00a0\u0000\u0000\u0523\u0525\u0005\u00a1\u0000\u0000"+
		"\u0524\u051c\u0001\u0000\u0000\u0000\u0524\u051d\u0001\u0000\u0000\u0000"+
		"\u0524\u051e\u0001\u0000\u0000\u0000\u0524\u0522\u0001\u0000\u0000\u0000"+
		"\u0524\u0523\u0001\u0000\u0000\u0000\u0525O\u0001\u0000\u0000\u0000\u0526"+
		"\u0527\u0006(\uffff\uffff\u0000\u0527\u052d\u0003R)\u0000\u0528\u0529"+
		"\u0005\u0011\u0000\u0000\u0529\u052a\u0003P(\u0000\u052a\u052b\u0005\u0012"+
		"\u0000\u0000\u052b\u052d\u0001\u0000\u0000\u0000\u052c\u0526\u0001\u0000"+
		"\u0000\u0000\u052c\u0528\u0001\u0000\u0000\u0000\u052d\u0542\u0001\u0000"+
		"\u0000\u0000\u052e\u053c\n\u0003\u0000\u0000\u052f\u0530\u0003j5\u0000"+
		"\u0530\u0537\u0003P(\u0000\u0531\u0532\u0005b\u0000\u0000\u0532\u0536"+
		"\u0003\u008cF\u0000\u0533\u0534\u0005Z\u0000\u0000\u0534\u0536\u0003\u00de"+
		"o\u0000\u0535\u0531\u0001\u0000\u0000\u0000\u0535\u0533\u0001\u0000\u0000"+
		"\u0000\u0536\u0539\u0001\u0000\u0000\u0000\u0537\u0535\u0001\u0000\u0000"+
		"\u0000\u0537\u0538\u0001\u0000\u0000\u0000\u0538\u053d\u0001\u0000\u0000"+
		"\u0000\u0539\u0537\u0001\u0000\u0000\u0000\u053a\u053d\u0003l6\u0000\u053b"+
		"\u053d\u0003p8\u0000\u053c\u052f\u0001\u0000\u0000\u0000\u053c\u053a\u0001"+
		"\u0000\u0000\u0000\u053c\u053b\u0001\u0000\u0000\u0000\u053d\u053e\u0001"+
		"\u0000\u0000\u0000\u053e\u053c\u0001\u0000\u0000\u0000\u053e\u053f\u0001"+
		"\u0000\u0000\u0000\u053f\u0541\u0001\u0000\u0000\u0000\u0540\u052e\u0001"+
		"\u0000\u0000\u0000\u0541\u0544\u0001\u0000\u0000\u0000\u0542\u0540\u0001"+
		"\u0000\u0000\u0000\u0542\u0543\u0001\u0000\u0000\u0000\u0543Q\u0001\u0000"+
		"\u0000\u0000\u0544\u0542\u0001\u0000\u0000\u0000\u0545\u054d\u0003\u00e2"+
		"q\u0000\u0546\u0548\u0005\u0006\u0000\u0000\u0547\u0546\u0001\u0000\u0000"+
		"\u0000\u0547\u0548\u0001\u0000\u0000\u0000\u0548\u0549\u0001\u0000\u0000"+
		"\u0000\u0549\u054b\u0003\u00fe\u007f\u0000\u054a\u054c\u0003\u0100\u0080"+
		"\u0000\u054b\u054a\u0001\u0000\u0000\u0000\u054b\u054c\u0001\u0000\u0000"+
		"\u0000\u054c\u054e\u0001\u0000\u0000\u0000\u054d\u0547\u0001\u0000\u0000"+
		"\u0000\u054d\u054e\u0001\u0000\u0000\u0000\u054e\u0559\u0001\u0000\u0000"+
		"\u0000\u054f\u0550\u0005\u00a2\u0000\u0000\u0550\u0551\u0005A\u0000\u0000"+
		"\u0551\u055a\u0003\u0100\u0080\u0000\u0552\u0553\u0005l\u0000\u0000\u0553"+
		"\u0558\u0005\u0098\u0000\u0000\u0554\u0555\u0005\u0098\u0000\u0000\u0555"+
		"\u0556\u0005\u0086\u0000\u0000\u0556\u0558\u0003\u00e4r\u0000\u0557\u0552"+
		"\u0001\u0000\u0000\u0000\u0557\u0554\u0001\u0000\u0000\u0000\u0558\u055a"+
		"\u0001\u0000\u0000\u0000\u0559\u054f\u0001\u0000\u0000\u0000\u0559\u0557"+
		"\u0001\u0000\u0000\u0000\u0559\u055a\u0001\u0000\u0000\u0000\u055a\u0581"+
		"\u0001\u0000\u0000\u0000\u055b\u0576\u0003J%\u0000\u055c\u055d\u0005\u0011"+
		"\u0000\u0000\u055d\u055e\u0003R)\u0000\u055e\u055f\u0005\u0012\u0000\u0000"+
		"\u055f\u0576\u0001\u0000\u0000\u0000\u0560\u0576\u0003V+\u0000\u0561\u0562"+
		"\u0007\u0011\u0000\u0000\u0562\u0563\u0005,\u0000\u0000\u0563\u0568\u0005"+
		"\u0011\u0000\u0000\u0564\u0569\u00032\u0019\u0000\u0565\u0569\u00034\u001a"+
		"\u0000\u0566\u0569\u0003:\u001d\u0000\u0567\u0569\u0003>\u001f\u0000\u0568"+
		"\u0564\u0001\u0000\u0000\u0000\u0568\u0565\u0001\u0000\u0000\u0000\u0568"+
		"\u0566\u0001\u0000\u0000\u0000\u0568\u0567\u0001\u0000\u0000\u0000\u0569"+
		"\u056a\u0001\u0000\u0000\u0000\u056a\u056b\u0005\u0012\u0000\u0000\u056b"+
		"\u0576\u0001\u0000\u0000\u0000\u056c\u056d\u0005,\u0000\u0000\u056d\u056e"+
		"\u0005\u0011\u0000\u0000\u056e\u056f\u0003\u008cF\u0000\u056f\u0570\u0005"+
		"\u0012\u0000\u0000\u0570\u0576\u0001\u0000\u0000\u0000\u0571\u0576\u0005"+
		"\u00a6\u0000\u0000\u0572\u0576\u0003\u0080@\u0000\u0573\u0576\u0003\u00ae"+
		"W\u0000\u0574\u0576\u0003T*\u0000\u0575\u055b\u0001\u0000\u0000\u0000"+
		"\u0575\u055c\u0001\u0000\u0000\u0000\u0575\u0560\u0001\u0000\u0000\u0000"+
		"\u0575\u0561\u0001\u0000\u0000\u0000\u0575\u056c\u0001\u0000\u0000\u0000"+
		"\u0575\u0571\u0001\u0000\u0000\u0000\u0575\u0572\u0001\u0000\u0000\u0000"+
		"\u0575\u0573\u0001\u0000\u0000\u0000\u0575\u0574\u0001\u0000\u0000\u0000"+
		"\u0576\u057e\u0001\u0000\u0000\u0000\u0577\u0579\u0005\u0006\u0000\u0000"+
		"\u0578\u0577\u0001\u0000\u0000\u0000\u0578\u0579\u0001\u0000\u0000\u0000"+
		"\u0579\u057a\u0001\u0000\u0000\u0000\u057a\u057c\u0003\u00fe\u007f\u0000"+
		"\u057b\u057d\u0003\u0100\u0080\u0000\u057c\u057b\u0001\u0000\u0000\u0000"+
		"\u057c\u057d\u0001\u0000\u0000\u0000\u057d\u057f\u0001\u0000\u0000\u0000"+
		"\u057e\u0578\u0001\u0000\u0000\u0000\u057e\u057f\u0001\u0000\u0000\u0000"+
		"\u057f\u0581\u0001\u0000\u0000\u0000\u0580\u0545\u0001\u0000\u0000\u0000"+
		"\u0580\u0575\u0001\u0000\u0000\u0000\u0581S\u0001\u0000\u0000\u0000\u0582"+
		"\u0583\u0005\u00a7\u0000\u0000\u0583\u0584\u0005\u0011\u0000\u0000\u0584"+
		"\u0589\u0003\u00a8T\u0000\u0585\u0586\u0005\u001d\u0000\u0000\u0586\u0588"+
		"\u0003\u00a8T\u0000\u0587\u0585\u0001\u0000\u0000\u0000\u0588\u058b\u0001"+
		"\u0000\u0000\u0000\u0589\u0587\u0001\u0000\u0000\u0000\u0589\u058a\u0001"+
		"\u0000\u0000\u0000\u058a\u058c\u0001\u0000\u0000\u0000\u058b\u0589\u0001"+
		"\u0000\u0000\u0000\u058c\u058f\u0005\u0012\u0000\u0000\u058d\u058e\u0005"+
		"V\u0000\u0000\u058e\u0590\u0005\u00a8\u0000\u0000\u058f\u058d\u0001\u0000"+
		"\u0000\u0000\u058f\u0590\u0001\u0000\u0000\u0000\u0590U\u0001\u0000\u0000"+
		"\u0000\u0591\u0592\u0007\u0012\u0000\u0000\u0592\u0593\u0005\u0011\u0000"+
		"\u0000\u0593\u0598\u0003X,\u0000\u0594\u0595\u0005\u001d\u0000\u0000\u0595"+
		"\u0597\u0003X,\u0000\u0596\u0594\u0001\u0000\u0000\u0000\u0597\u059a\u0001"+
		"\u0000\u0000\u0000\u0598\u0596\u0001\u0000\u0000\u0000\u0598\u0599\u0001"+
		"\u0000\u0000\u0000\u0599\u059b\u0001\u0000\u0000\u0000\u059a\u0598\u0001"+
		"\u0000\u0000\u0000\u059b\u059c\u0005\u0012\u0000\u0000\u059cW\u0001\u0000"+
		"\u0000\u0000\u059d\u05a1\u0003\u0106\u0083\u0000\u059e\u05a2\u0003\u009c"+
		"N\u0000\u059f\u05a2\u0003\u00e4r\u0000\u05a0\u05a2\u0005q\u0000\u0000"+
		"\u05a1\u059e\u0001\u0000\u0000\u0000\u05a1\u059f\u0001\u0000\u0000\u0000"+
		"\u05a1\u05a0\u0001\u0000\u0000\u0000\u05a2\u05a3\u0001\u0000\u0000\u0000"+
		"\u05a3\u05a4\u0005\u0010\u0000\u0000\u05a4\u05a5\u0003\u0090H\u0000\u05a5"+
		"Y\u0001\u0000\u0000\u0000\u05a6\u05a7\u0005\u00aa\u0000\u0000\u05a7\u05a9"+
		"\u0003\u008cF\u0000\u05a8\u05aa\u0003\u00f4z\u0000\u05a9\u05a8\u0001\u0000"+
		"\u0000\u0000\u05a9\u05aa\u0001\u0000\u0000\u0000\u05aa[\u0001\u0000\u0000"+
		"\u0000\u05ab\u05ac\u0005\u00ab\u0000\u0000\u05ac\u05af\u0003\u008cF\u0000"+
		"\u05ad\u05ae\u0005\u001d\u0000\u0000\u05ae\u05b0\u0003\u008cF\u0000\u05af"+
		"\u05ad\u0001\u0000\u0000\u0000\u05af\u05b0\u0001\u0000\u0000\u0000\u05b0"+
		"]\u0001\u0000\u0000\u0000\u05b1\u05b2\u0005\u00ac\u0000\u0000\u05b2\u05b7"+
		"\u0007\u0013\u0000\u0000\u05b3\u05b5\u0003\u008cF\u0000\u05b4\u05b6\u0005"+
		"\u00ae\u0000\u0000\u05b5\u05b4\u0001\u0000\u0000\u0000\u05b5\u05b6\u0001"+
		"\u0000\u0000\u0000\u05b6\u05b8\u0001\u0000\u0000\u0000\u05b7\u05b3\u0001"+
		"\u0000\u0000\u0000\u05b7\u05b8\u0001\u0000\u0000\u0000\u05b8\u05b9\u0001"+
		"\u0000\u0000\u0000\u05b9\u05bc\u0003\u00f4z\u0000\u05ba\u05bd\u0005\u0089"+
		"\u0000\u0000\u05bb\u05bd\u0003\u00fa}\u0000\u05bc\u05ba\u0001\u0000\u0000"+
		"\u0000\u05bc\u05bb\u0001\u0000\u0000\u0000\u05bd_\u0001\u0000\u0000\u0000"+
		"\u05be\u05ca\u0005c\u0000\u0000\u05bf\u05c0\u0005\u00af\u0000\u0000\u05c0"+
		"\u05cb\u0005\u0089\u0000\u0000\u05c1\u05c2\u0005W\u0000\u0000\u05c2\u05c4"+
		"\u0005o\u0000\u0000\u05c3\u05c1\u0001\u0000\u0000\u0000\u05c3\u05c4\u0001"+
		"\u0000\u0000\u0000\u05c4\u05c5\u0001\u0000\u0000\u0000\u05c5\u05cb\u0005"+
		"a\u0000\u0000\u05c6\u05c8\u0005o\u0000\u0000\u05c7\u05c6\u0001\u0000\u0000"+
		"\u0000\u05c7\u05c8\u0001\u0000\u0000\u0000\u05c8\u05c9\u0001\u0000\u0000"+
		"\u0000\u05c9\u05cb\u0005\u00b0\u0000\u0000\u05ca\u05bf\u0001\u0000\u0000"+
		"\u0000\u05ca\u05c3\u0001\u0000\u0000\u0000\u05ca\u05c7\u0001\u0000\u0000"+
		"\u0000\u05cb\u05d5\u0001\u0000\u0000\u0000\u05cc\u05cd\u0005^\u0000\u0000"+
		"\u05cd\u05d2\u0003\u00e4r\u0000\u05ce\u05cf\u0005\u001d\u0000\u0000\u05cf"+
		"\u05d1\u0003\u00e4r\u0000\u05d0\u05ce\u0001\u0000\u0000\u0000\u05d1\u05d4"+
		"\u0001\u0000\u0000\u0000\u05d2\u05d0\u0001\u0000\u0000\u0000\u05d2\u05d3"+
		"\u0001\u0000\u0000\u0000\u05d3\u05d6\u0001\u0000\u0000\u0000\u05d4\u05d2"+
		"\u0001\u0000\u0000\u0000\u05d5\u05cc\u0001\u0000\u0000\u0000\u05d5\u05d6"+
		"\u0001\u0000\u0000\u0000\u05d6\u05dc\u0001\u0000\u0000\u0000\u05d7\u05dd"+
		"\u0005\u00b1\u0000\u0000\u05d8\u05d9\u0005\u00b2\u0000\u0000\u05d9\u05dd"+
		"\u0005\u019d\u0000\u0000\u05da\u05db\u0005\u00b3\u0000\u0000\u05db\u05dd"+
		"\u0005\u00b4\u0000\u0000\u05dc\u05d7\u0001\u0000\u0000\u0000\u05dc\u05d8"+
		"\u0001\u0000\u0000\u0000\u05dc\u05da\u0001\u0000\u0000\u0000\u05dc\u05dd"+
		"\u0001\u0000\u0000\u0000\u05dda\u0001\u0000\u0000\u0000\u05de\u05e4\u0005"+
		"\u00b5\u0000\u0000\u05df\u05e0\u0005b\u0000\u0000\u05e0\u05e1\u0005\u0011"+
		"\u0000\u0000\u05e1\u05e2\u0003\u0086C\u0000\u05e2\u05e3\u0005\u0012\u0000"+
		"\u0000\u05e3\u05e5\u0001\u0000\u0000\u0000\u05e4\u05df\u0001\u0000\u0000"+
		"\u0000\u05e4\u05e5\u0001\u0000\u0000\u0000\u05e5\u05e8\u0001\u0000\u0000"+
		"\u0000\u05e6\u05e8\u0005\u009f\u0000\u0000\u05e7\u05de\u0001\u0000\u0000"+
		"\u0000\u05e7\u05e6\u0001\u0000\u0000\u0000\u05e8c\u0001\u0000\u0000\u0000"+
		"\u05e9\u05f0\u0005\u00b6\u0000\u0000\u05ea\u05f1\u0005\u019d\u0000\u0000"+
		"\u05eb\u05f1\u0005\u019e\u0000\u0000\u05ec\u05ed\u0005\u0011\u0000\u0000"+
		"\u05ed\u05ee\u0003\u008cF\u0000\u05ee\u05ef\u0005\u0012\u0000\u0000\u05ef"+
		"\u05f1\u0001\u0000\u0000\u0000\u05f0\u05ea\u0001\u0000\u0000\u0000\u05f0"+
		"\u05eb\u0001\u0000\u0000\u0000\u05f0\u05ec\u0001\u0000\u0000\u0000\u05f1"+
		"\u05f3\u0001\u0000\u0000\u0000\u05f2\u05f4\u0005\u00ae\u0000\u0000\u05f3"+
		"\u05f2\u0001\u0000\u0000\u0000\u05f3\u05f4\u0001\u0000\u0000\u0000\u05f4"+
		"\u05f6\u0001\u0000\u0000\u0000\u05f5\u05f7\u0003\u00fa}\u0000\u05f6\u05f5"+
		"\u0001\u0000\u0000\u0000\u05f6\u05f7\u0001\u0000\u0000\u0000\u05f7e\u0001"+
		"\u0000\u0000\u0000\u05f8\u05f9\u0003\u00e2q\u0000\u05f9\u05fa\u0005\u00b7"+
		"\u0000\u0000\u05fa\u05fc\u0001\u0000\u0000\u0000\u05fb\u05f8\u0001\u0000"+
		"\u0000\u0000\u05fb\u05fc\u0001\u0000\u0000\u0000\u05fc\u05fd\u0001\u0000"+
		"\u0000\u0000\u05fd\u05fe\u0005\u009a\u0000\u0000\u05fe\u0601\u0001\u0000"+
		"\u0000\u0000\u05ff\u0600\u0005\u00a0\u0000\u0000\u0600\u0602\u0003\u00de"+
		"o\u0000\u0601\u05ff\u0001\u0000\u0000\u0000\u0601\u0602\u0001\u0000\u0000"+
		"\u0000\u0602\u060b\u0001\u0000\u0000\u0000\u0603\u0608\u0003\u008cF\u0000"+
		"\u0604\u0606\u0005\u0006\u0000\u0000\u0605\u0604\u0001\u0000\u0000\u0000"+
		"\u0605\u0606\u0001\u0000\u0000\u0000\u0606\u0607\u0001\u0000\u0000\u0000"+
		"\u0607\u0609\u0003\u00fe\u007f\u0000\u0608\u0605\u0001\u0000\u0000\u0000"+
		"\u0608\u0609\u0001\u0000\u0000\u0000\u0609\u060b\u0001\u0000\u0000\u0000"+
		"\u060a\u05fb\u0001\u0000\u0000\u0000\u060a\u0603\u0001\u0000\u0000\u0000"+
		"\u060bg\u0001\u0000\u0000\u0000\u060c\u060f\u0005\u001a\u0000\u0000\u060d"+
		"\u0610\u0003\u0018\f\u0000\u060e\u0610\u0005T\u0000\u0000\u060f\u060d"+
		"\u0001\u0000\u0000\u0000\u060f\u060e\u0001\u0000\u0000\u0000\u060f\u0610"+
		"\u0001\u0000\u0000\u0000\u0610\u0612\u0001\u0000\u0000\u0000\u0611\u0613"+
		"\u0005,\u0000\u0000\u0612\u0611\u0001\u0000\u0000\u0000\u0612\u0613\u0001"+
		"\u0000\u0000\u0000\u0613\u0616\u0001\u0000\u0000\u0000\u0614\u0617\u0003"+
		"\u00e4r\u0000\u0615\u0617\u0005\u01a0\u0000\u0000\u0616\u0614\u0001\u0000"+
		"\u0000\u0000\u0616\u0615\u0001\u0000\u0000\u0000\u0617\u061f\u0001\u0000"+
		"\u0000\u0000\u0618\u061b\u0005\u001d\u0000\u0000\u0619\u061c\u0003\u00e4"+
		"r\u0000\u061a\u061c\u0005\u01a0\u0000\u0000\u061b\u0619\u0001\u0000\u0000"+
		"\u0000\u061b\u061a\u0001\u0000\u0000\u0000\u061c\u061e\u0001\u0000\u0000"+
		"\u0000\u061d\u0618\u0001\u0000\u0000\u0000\u061e\u0621\u0001\u0000\u0000"+
		"\u0000\u061f\u061d\u0001\u0000\u0000\u0000\u061f\u0620\u0001\u0000\u0000"+
		"\u0000\u0620i\u0001\u0000\u0000\u0000\u0621\u061f\u0001\u0000\u0000\u0000"+
		"\u0622\u0631\u0005\u001d\u0000\u0000\u0623\u0625\u0005\u00b8\u0000\u0000"+
		"\u0624\u0623\u0001\u0000\u0000\u0000\u0624\u0625\u0001\u0000\u0000\u0000"+
		"\u0625\u062d\u0001\u0000\u0000\u0000\u0626\u0628\u0007\u0014\u0000\u0000"+
		"\u0627\u0626\u0001\u0000\u0000\u0000\u0628\u0629\u0001\u0000\u0000\u0000"+
		"\u0629\u0627\u0001\u0000\u0000\u0000\u0629\u062a\u0001\u0000\u0000\u0000"+
		"\u062a\u062e\u0001\u0000\u0000\u0000\u062b\u062e\u0005\u00bc\u0000\u0000"+
		"\u062c\u062e\u0005\u00bd\u0000\u0000\u062d\u0627\u0001\u0000\u0000\u0000"+
		"\u062d\u062b\u0001\u0000\u0000\u0000\u062d\u062c\u0001\u0000\u0000\u0000"+
		"\u062d\u062e\u0001\u0000\u0000\u0000\u062e\u062f\u0001\u0000\u0000\u0000"+
		"\u062f\u0631\u0005\u00be\u0000\u0000\u0630\u0622\u0001\u0000\u0000\u0000"+
		"\u0630\u0624\u0001\u0000\u0000\u0000\u0631k\u0001\u0000\u0000\u0000\u0632"+
		"\u0633\u0005\u00bf\u0000\u0000\u0633\u0634\u0005\u0011\u0000\u0000\u0634"+
		"\u0639\u0003n7\u0000\u0635\u0636\u0005\u001d\u0000\u0000\u0636\u0638\u0003"+
		"n7\u0000\u0637\u0635\u0001\u0000\u0000\u0000\u0638\u063b\u0001\u0000\u0000"+
		"\u0000\u0639\u0637\u0001\u0000\u0000\u0000\u0639\u063a\u0001\u0000\u0000"+
		"\u0000\u063a\u063c\u0001\u0000\u0000\u0000\u063b\u0639\u0001\u0000\u0000"+
		"\u0000\u063c\u063f\u0005c\u0000\u0000\u063d\u0640\u0003\u00e0p\u0000\u063e"+
		"\u0640\u0003\u00deo\u0000\u063f\u063d\u0001\u0000\u0000\u0000\u063f\u063e"+
		"\u0001\u0000\u0000\u0000\u0640\u0641\u0001\u0000\u0000\u0000\u0641\u0642"+
		"\u0005\u00c0\u0000\u0000\u0642\u0643\u0005\u0011\u0000\u0000\u0643\u0644"+
		"\u0003\u008aE\u0000\u0644\u0645\u0005\u0012\u0000\u0000\u0645\u0646\u0005"+
		"\u0012\u0000\u0000\u0646\u0661\u0001\u0000\u0000\u0000\u0647\u0648\u0005"+
		"\u00bf\u0000\u0000\u0648\u0649\u0005(\u0000\u0000\u0649\u064a\u0005\u0011"+
		"\u0000\u0000\u064a\u064f\u0003n7\u0000\u064b\u064c\u0005\u001d\u0000\u0000"+
		"\u064c\u064e\u0003n7\u0000\u064d\u064b\u0001\u0000\u0000\u0000\u064e\u0651"+
		"\u0001\u0000\u0000\u0000\u064f\u064d\u0001\u0000\u0000\u0000\u064f\u0650"+
		"\u0001\u0000\u0000\u0000\u0650\u0652\u0001\u0000\u0000\u0000\u0651\u064f"+
		"\u0001\u0000\u0000\u0000\u0652\u0655\u0005c\u0000\u0000\u0653\u0656\u0003"+
		"\u00e0p\u0000\u0654\u0656\u0003\u00deo\u0000\u0655\u0653\u0001\u0000\u0000"+
		"\u0000\u0655\u0654\u0001\u0000\u0000\u0000\u0656\u0657\u0001\u0000\u0000"+
		"\u0000\u0657\u0658\u0005\u00c0\u0000\u0000\u0658\u065b\u0005\u0011\u0000"+
		"\u0000\u0659\u065c\u0003J%\u0000\u065a\u065c\u0005\u00c1\u0000\u0000\u065b"+
		"\u0659\u0001\u0000\u0000\u0000\u065b\u065a\u0001\u0000\u0000\u0000\u065c"+
		"\u065d\u0001\u0000\u0000\u0000\u065d\u065e\u0005\u0012\u0000\u0000\u065e"+
		"\u065f\u0005\u0012\u0000\u0000\u065f\u0661\u0001\u0000\u0000\u0000\u0660"+
		"\u0632\u0001\u0000\u0000\u0000\u0660\u0647\u0001\u0000\u0000\u0000\u0661"+
		"m\u0001\u0000\u0000\u0000\u0662\u0667\u0003\u00aeW\u0000\u0663\u0665\u0005"+
		"\u0006\u0000\u0000\u0664\u0663\u0001\u0000\u0000\u0000\u0664\u0665\u0001"+
		"\u0000\u0000\u0000\u0665\u0666\u0001\u0000\u0000\u0000\u0666\u0668\u0003"+
		"\u00fe\u007f\u0000\u0667\u0664\u0001\u0000\u0000\u0000\u0667\u0668\u0001"+
		"\u0000\u0000\u0000\u0668o\u0001\u0000\u0000\u0000\u0669\u066c\u0005\u00c2"+
		"\u0000\u0000\u066a\u066b\u0007\u0015\u0000\u0000\u066b\u066d\u0005\u00c5"+
		"\u0000\u0000\u066c\u066a\u0001\u0000\u0000\u0000\u066c\u066d\u0001\u0000"+
		"\u0000\u0000\u066d\u066e\u0001\u0000\u0000\u0000\u066e\u0671\u0005\u0011"+
		"\u0000\u0000\u066f\u0672\u0003\u00e0p\u0000\u0670\u0672\u0003\u00deo\u0000"+
		"\u0671\u066f\u0001\u0000\u0000\u0000\u0671\u0670\u0001\u0000\u0000\u0000"+
		"\u0672\u0673\u0001\u0000\u0000\u0000\u0673\u0676\u0005c\u0000\u0000\u0674"+
		"\u0677\u0003\u00e0p\u0000\u0675\u0677\u0003\u00deo\u0000\u0676\u0674\u0001"+
		"\u0000\u0000\u0000\u0676\u0675\u0001\u0000\u0000\u0000\u0677\u0678\u0001"+
		"\u0000\u0000\u0000\u0678\u0679\u0005\u00c0\u0000\u0000\u0679\u067a\u0005"+
		"\u0011\u0000\u0000\u067a\u067b\u0003\u008aE\u0000\u067b\u067c\u0005\u0012"+
		"\u0000\u0000\u067c\u067d\u0005\u0012\u0000\u0000\u067dq\u0001\u0000\u0000"+
		"\u0000\u067e\u0683\u0005\u00c6\u0000\u0000\u067f\u0684\u0003\u008cF\u0000"+
		"\u0680\u0681\u0005\u00c7\u0000\u0000\u0681\u0682\u0005^\u0000\u0000\u0682"+
		"\u0684\u0003\u0106\u0083\u0000\u0683\u067f\u0001\u0000\u0000\u0000\u0683"+
		"\u0680\u0001\u0000\u0000\u0000\u0684s\u0001\u0000\u0000\u0000\u0685\u0686"+
		"\u0005\u00c8\u0000\u0000\u0686\u0688\u0005\u0086\u0000\u0000\u0687\u0689"+
		"\u0003\u00eau\u0000\u0688\u0687\u0001\u0000\u0000\u0000\u0688\u0689\u0001"+
		"\u0000\u0000\u0000\u0689\u068a\u0001\u0000\u0000\u0000\u068a\u068f\u0003"+
		"v;\u0000\u068b\u068c\u0005\u001d\u0000\u0000\u068c\u068e\u0003v;\u0000"+
		"\u068d\u068b\u0001\u0000\u0000\u0000\u068e\u0691\u0001\u0000\u0000\u0000"+
		"\u068f\u068d\u0001\u0000\u0000\u0000\u068f\u0690\u0001\u0000\u0000\u0000"+
		"\u0690u\u0001\u0000\u0000\u0000\u0691\u068f\u0001\u0000\u0000\u0000\u0692"+
		"\u06ab\u0003\u0086C\u0000\u0693\u0694\u0005\u00c9\u0000\u0000\u0694\u0695"+
		"\u0005\u0011\u0000\u0000\u0695\u0696\u0003\u0086C\u0000\u0696\u0697\u0005"+
		"\u0012\u0000\u0000\u0697\u06ab\u0001\u0000\u0000\u0000\u0698\u0699\u0005"+
		"\u00ca\u0000\u0000\u0699\u069a\u0005\u0011\u0000\u0000\u069a\u069b\u0003"+
		"\u0086C\u0000\u069b\u069c\u0005\u0012\u0000\u0000\u069c\u06ab\u0001\u0000"+
		"\u0000\u0000\u069d\u069e\u0005\u00cb\u0000\u0000\u069e\u069f\u0005\u00cc"+
		"\u0000\u0000\u069f\u06a0\u0005\u0011\u0000\u0000\u06a0\u06a5\u0003v;\u0000"+
		"\u06a1\u06a2\u0005\u001d\u0000\u0000\u06a2\u06a4\u0003v;\u0000\u06a3\u06a1"+
		"\u0001\u0000\u0000\u0000\u06a4\u06a7\u0001\u0000\u0000\u0000\u06a5\u06a3"+
		"\u0001\u0000\u0000\u0000\u06a5\u06a6\u0001\u0000\u0000\u0000\u06a6\u06a8"+
		"\u0001\u0000\u0000\u0000\u06a7\u06a5\u0001\u0000\u0000\u0000\u06a8\u06a9"+
		"\u0005\u0012\u0000\u0000\u06a9\u06ab\u0001\u0000\u0000\u0000\u06aa\u0692"+
		"\u0001\u0000\u0000\u0000\u06aa\u0693\u0001\u0000\u0000\u0000\u06aa\u0698"+
		"\u0001\u0000\u0000\u0000\u06aa\u069d\u0001\u0000\u0000\u0000\u06abw\u0001"+
		"\u0000\u0000\u0000\u06ac\u06ad\u0005\u00cd\u0000\u0000\u06ad\u06ae\u0003"+
		"\u0086C\u0000\u06aey\u0001\u0000\u0000\u0000\u06af\u06b0\u0005\u00ce\u0000"+
		"\u0000\u06b0\u06b5\u0003|>\u0000\u06b1\u06b2\u0005\u001d\u0000\u0000\u06b2"+
		"\u06b4\u0003|>\u0000\u06b3\u06b1\u0001\u0000\u0000\u0000\u06b4\u06b7\u0001"+
		"\u0000\u0000\u0000\u06b5\u06b3\u0001\u0000\u0000\u0000\u06b5\u06b6\u0001"+
		"\u0000\u0000\u0000\u06b6{\u0001\u0000\u0000\u0000\u06b7\u06b5\u0001\u0000"+
		"\u0000\u0000\u06b8\u06b9\u0003\u00fe\u007f\u0000\u06b9\u06ba\u0005\u0006"+
		"\u0000\u0000\u06ba\u06bb\u0003\u00ba]\u0000\u06bb}\u0001\u0000\u0000\u0000"+
		"\u06bc\u06bd\u0005\u00cf\u0000\u0000\u06bd\u06be\u0003\u008cF\u0000\u06be"+
		"\u007f\u0001\u0000\u0000\u0000\u06bf\u06c0\u0005\u00d0\u0000\u0000\u06c0"+
		"\u06c1\u0005\u0011\u0000\u0000\u06c1\u06c3\u0003\u0090H\u0000\u06c2\u06c4"+
		"\u0003\u0082A\u0000\u06c3\u06c2\u0001\u0000\u0000\u0000\u06c3\u06c4\u0001"+
		"\u0000\u0000\u0000\u06c4\u06c5\u0001\u0000\u0000\u0000\u06c5\u06c6\u0005"+
		"\u00d1\u0000\u0000\u06c6\u06cb\u0003\u0084B\u0000\u06c7\u06c8\u0005\u001d"+
		"\u0000\u0000\u06c8\u06ca\u0003\u0084B\u0000\u06c9\u06c7\u0001\u0000\u0000"+
		"\u0000\u06ca\u06cd\u0001\u0000\u0000\u0000\u06cb\u06c9\u0001\u0000\u0000"+
		"\u0000\u06cb\u06cc\u0001\u0000\u0000\u0000\u06cc\u06ce\u0001\u0000\u0000"+
		"\u0000\u06cd\u06cb\u0001\u0000\u0000\u0000\u06ce\u06cf\u0005\u0012\u0000"+
		"\u0000\u06cf\u0081\u0001\u0000\u0000\u0000\u06d0\u06d3\u0005\u00d2\u0000"+
		"\u0000\u06d1\u06d2\u0005\u0086\u0000\u0000\u06d2\u06d4\u0007\u0016\u0000"+
		"\u0000\u06d3\u06d1\u0001\u0000\u0000\u0000\u06d3\u06d4\u0001\u0000\u0000"+
		"\u0000\u06d4\u06d5\u0001\u0000\u0000\u0000\u06d5\u06d6\u0003\u008aE\u0000"+
		"\u06d6\u0083\u0001\u0000\u0000\u0000\u06d7\u06df\u0003\u0106\u0083\u0000"+
		"\u06d8\u06db\u0003\u009cN\u0000\u06d9\u06da\u0005\u00d4\u0000\u0000\u06da"+
		"\u06dc\u0003\u0090H\u0000\u06db\u06d9\u0001\u0000\u0000\u0000\u06db\u06dc"+
		"\u0001\u0000\u0000\u0000\u06dc\u06e0\u0001\u0000\u0000\u0000\u06dd\u06de"+
		"\u0005c\u0000\u0000\u06de\u06e0\u0005\u00a8\u0000\u0000\u06df\u06d8\u0001"+
		"\u0000\u0000\u0000\u06df\u06dd\u0001\u0000\u0000\u0000\u06e0\u0085\u0001"+
		"\u0000\u0000\u0000\u06e1\u06e6\u0003\u008cF\u0000\u06e2\u06e3\u0005\u001d"+
		"\u0000\u0000\u06e3\u06e5\u0003\u008cF\u0000\u06e4\u06e2\u0001\u0000\u0000"+
		"\u0000\u06e5\u06e8\u0001\u0000\u0000\u0000\u06e6\u06e4\u0001\u0000\u0000"+
		"\u0000\u06e6\u06e7\u0001\u0000\u0000\u0000\u06e7\u0087\u0001\u0000\u0000"+
		"\u0000\u06e8\u06e6\u0001\u0000\u0000\u0000\u06e9\u06ee\u0003\u008cF\u0000"+
		"\u06ea\u06ec\u0005\u0006\u0000\u0000\u06eb\u06ea\u0001\u0000\u0000\u0000"+
		"\u06eb\u06ec\u0001\u0000\u0000\u0000\u06ec\u06ed\u0001\u0000\u0000\u0000"+
		"\u06ed\u06ef\u0003\u00fe\u007f\u0000\u06ee\u06eb\u0001\u0000\u0000\u0000"+
		"\u06ee\u06ef\u0001\u0000\u0000\u0000\u06ef\u0089\u0001\u0000\u0000\u0000"+
		"\u06f0\u06f5\u0003\u0088D\u0000\u06f1\u06f2\u0005\u001d\u0000\u0000\u06f2"+
		"\u06f4\u0003\u0088D\u0000\u06f3\u06f1\u0001\u0000\u0000\u0000\u06f4\u06f7"+
		"\u0001\u0000\u0000\u0000\u06f5\u06f3\u0001\u0000\u0000\u0000\u06f5\u06f6"+
		"\u0001\u0000\u0000\u0000\u06f6\u008b\u0001\u0000\u0000\u0000\u06f7\u06f5"+
		"\u0001\u0000\u0000\u0000\u06f8\u06f9\u0006F\uffff\uffff\u0000\u06f9\u06fa"+
		"\u0005\u0011\u0000\u0000\u06fa\u06fb\u0003\u008cF\u0000\u06fb\u06fc\u0005"+
		"\u0012\u0000\u0000\u06fc\u0712\u0001\u0000\u0000\u0000\u06fd\u0712\u0003"+
		"\u0090H\u0000\u06fe\u06ff\u0005l\u0000\u0000\u06ff\u0712\u0003\u008cF"+
		"\u0006\u0700\u0701\u0005|\u0000\u0000\u0701\u0702\u0005\u0011\u0000\u0000"+
		"\u0702\u0703\u0003\u0106\u0083\u0000\u0703\u0704\u0005\u001d\u0000\u0000"+
		"\u0704\u0705\u0003\u0108\u0084\u0000\u0705\u0706\u0005\u0012\u0000\u0000"+
		"\u0706\u0707\u0005Z\u0000\u0000\u0707\u0708\u0003\u00e4r\u0000\u0708\u0709"+
		"\u0005V\u0000\u0000\u0709\u070a\u0005\u0011\u0000\u0000\u070a\u070b\u0003"+
		"\u0090H\u0000\u070b\u070c\u0005\u0012\u0000\u0000\u070c\u0712\u0001\u0000"+
		"\u0000\u0000\u070d\u070e\u0005\u01a0\u0000\u0000\u070e\u070f\u0003\u008e"+
		"G\u0000\u070f\u0710\u0003\u008cF\u0001\u0710\u0712\u0001\u0000\u0000\u0000"+
		"\u0711\u06f8\u0001\u0000\u0000\u0000\u0711\u06fd\u0001\u0000\u0000\u0000"+
		"\u0711\u06fe\u0001\u0000\u0000\u0000\u0711\u0700\u0001\u0000\u0000\u0000"+
		"\u0711\u070d\u0001\u0000\u0000\u0000\u0712\u071e\u0001\u0000\u0000\u0000"+
		"\u0713\u0714\n\u0005\u0000\u0000\u0714\u0715\u0005\u0093\u0000\u0000\u0715"+
		"\u071d\u0003\u008cF\u0006\u0716\u0717\n\u0004\u0000\u0000\u0717\u0718"+
		"\u0005\u00d5\u0000\u0000\u0718\u071d\u0003\u008cF\u0005\u0719\u071a\n"+
		"\u0003\u0000\u0000\u071a\u071b\u0005i\u0000\u0000\u071b\u071d\u0003\u008c"+
		"F\u0004\u071c\u0713\u0001\u0000\u0000\u0000\u071c\u0716\u0001\u0000\u0000"+
		"\u0000\u071c\u0719\u0001\u0000\u0000\u0000\u071d\u0720\u0001\u0000\u0000"+
		"\u0000\u071e\u071c\u0001\u0000\u0000\u0000\u071e\u071f\u0001\u0000\u0000"+
		"\u0000\u071f\u008d\u0001\u0000\u0000\u0000\u0720\u071e\u0001\u0000\u0000"+
		"\u0000\u0721\u0722\u0007\u0017\u0000\u0000\u0722\u008f\u0001\u0000\u0000"+
		"\u0000\u0723\u0724\u0006H\uffff\uffff\u0000\u0724\u0774\u0003\u00cae\u0000"+
		"\u0725\u0726\u0007\u0018\u0000\u0000\u0726\u0774\u0003\u0090H\u001d\u0727"+
		"\u072c\u0003\u00aeW\u0000\u0728\u0729\u0005\u00b7\u0000\u0000\u0729\u072b"+
		"\u0003\u00aeW\u0000\u072a\u0728\u0001\u0000\u0000\u0000\u072b\u072e\u0001"+
		"\u0000\u0000\u0000\u072c\u072a\u0001\u0000\u0000\u0000\u072c\u072d\u0001"+
		"\u0000\u0000\u0000\u072d\u0774\u0001\u0000\u0000\u0000\u072e\u072c\u0001"+
		"\u0000\u0000\u0000\u072f\u0774\u0003\u00e0p\u0000\u0730\u0731\u0005\u00ec"+
		"\u0000\u0000\u0731\u0733\u0003\u0090H\u0000\u0732\u0734\u0003\u00d0h\u0000"+
		"\u0733\u0732\u0001\u0000\u0000\u0000\u0733\u0734\u0001\u0000\u0000\u0000"+
		"\u0734\u0774\u0001\u0000\u0000\u0000\u0735\u0774\u0003J%\u0000\u0736\u0774"+
		"\u0003\u00a8T\u0000\u0737\u0774\u0003\u0092I\u0000\u0738\u0739\u0007\u0019"+
		"\u0000\u0000\u0739\u073a\u0005\u0011\u0000\u0000\u073a\u073b\u0003\u008c"+
		"F\u0000\u073b\u073c\u0005\u0006\u0000\u0000\u073c\u073d\u0003\u009cN\u0000"+
		"\u073d\u073e\u0005\u0012\u0000\u0000\u073e\u0774\u0001\u0000\u0000\u0000"+
		"\u073f\u0740\u00055\u0000\u0000\u0740\u0741\u0005\u0011\u0000\u0000\u0741"+
		"\u0742\u0003J%\u0000\u0742\u0743\u0005\u0012\u0000\u0000\u0743\u0774\u0001"+
		"\u0000\u0000\u0000\u0744\u0749\u0005k\u0000\u0000\u0745\u0747\u0007\u001a"+
		"\u0000\u0000\u0746\u0745\u0001\u0000\u0000\u0000\u0746\u0747\u0001\u0000"+
		"\u0000\u0000\u0747\u0748\u0001\u0000\u0000\u0000\u0748\u074a\u0005\u00b5"+
		"\u0000\u0000\u0749\u0746\u0001\u0000\u0000\u0000\u0749\u074a\u0001\u0000"+
		"\u0000\u0000\u074a\u074b\u0001\u0000\u0000\u0000\u074b\u074c\u0005\u0011"+
		"\u0000\u0000\u074c\u074d\u0003J%\u0000\u074d\u074e\u0005\u0012\u0000\u0000"+
		"\u074e\u0774\u0001\u0000\u0000\u0000\u074f\u0750\u0007\u001b\u0000\u0000"+
		"\u0750\u0751\u0005\u008e\u0000\u0000\u0751\u0752\u0005c\u0000\u0000\u0752"+
		"\u0774\u0003\u00e0p\u0000\u0753\u0754\u0005\u0011\u0000\u0000\u0754\u0755"+
		"\u0003\u0090H\u0000\u0755\u0756\u0005\u001d\u0000\u0000\u0756\u0757\u0003"+
		"\u0090H\u0000\u0757\u0758\u0005\u0012\u0000\u0000\u0758\u0759\u0005\u00ef"+
		"\u0000\u0000\u0759\u075a\u0005\u0011\u0000\u0000\u075a\u075b\u0003\u0090"+
		"H\u0000\u075b\u075c\u0005\u001d\u0000\u0000\u075c\u075d\u0003\u0090H\u0000"+
		"\u075d\u075e\u0005\u0012\u0000\u0000\u075e\u0774\u0001\u0000\u0000\u0000"+
		"\u075f\u0761\u0005e\u0000\u0000\u0760\u075f\u0001\u0000\u0000\u0000\u0760"+
		"\u0761\u0001\u0000\u0000\u0000\u0761\u0762\u0001\u0000\u0000\u0000\u0762"+
		"\u0764\u0005\u0011\u0000\u0000\u0763\u0765\u0003\u0086C\u0000\u0764\u0763"+
		"\u0001\u0000\u0000\u0000\u0764\u0765\u0001\u0000\u0000\u0000\u0765\u0766"+
		"\u0001\u0000\u0000\u0000\u0766\u0769\u0005\u0012\u0000\u0000\u0767\u0768"+
		"\u0005\u00b7\u0000\u0000\u0768\u076a\u0003\u0106\u0083\u0000\u0769\u0767"+
		"\u0001\u0000\u0000\u0000\u0769\u076a\u0001\u0000\u0000\u0000\u076a\u0774"+
		"\u0001\u0000\u0000\u0000\u076b\u0770\u0003\u00aeW\u0000\u076c\u076d\u0005"+
		"\u00b7\u0000\u0000\u076d\u076f\u0003\u00aeW\u0000\u076e\u076c\u0001\u0000"+
		"\u0000\u0000\u076f\u0772\u0001\u0000\u0000\u0000\u0770\u076e\u0001\u0000"+
		"\u0000\u0000\u0770\u0771\u0001\u0000\u0000\u0000\u0771\u0774\u0001\u0000"+
		"\u0000\u0000\u0772\u0770\u0001\u0000\u0000\u0000\u0773\u0723\u0001\u0000"+
		"\u0000\u0000\u0773\u0725\u0001\u0000\u0000\u0000\u0773\u0727\u0001\u0000"+
		"\u0000\u0000\u0773\u072f\u0001\u0000\u0000\u0000\u0773\u0730\u0001\u0000"+
		"\u0000\u0000\u0773\u0735\u0001\u0000\u0000\u0000\u0773\u0736\u0001\u0000"+
		"\u0000\u0000\u0773\u0737\u0001\u0000\u0000\u0000\u0773\u0738\u0001\u0000"+
		"\u0000\u0000\u0773\u073f\u0001\u0000\u0000\u0000\u0773\u0744\u0001\u0000"+
		"\u0000\u0000\u0773\u074f\u0001\u0000\u0000\u0000\u0773\u0753\u0001\u0000"+
		"\u0000\u0000\u0773\u0760\u0001\u0000\u0000\u0000\u0773\u076b\u0001\u0000"+
		"\u0000\u0000\u0774\u07ae\u0001\u0000\u0000\u0000\u0775\u0776\n\u0016\u0000"+
		"\u0000\u0776\u0777\u0005\u00e4\u0000\u0000\u0777\u07ad\u0003\u0090H\u0017"+
		"\u0778\u0779\n\u0015\u0000\u0000\u0779\u077a\u0005\u00e5\u0000\u0000\u077a"+
		"\u07ad\u0003\u0090H\u0016\u077b\u077c\n\u0013\u0000\u0000\u077c\u077d"+
		"\u0005\u00e6\u0000\u0000\u077d\u07ad\u0003\u0090H\u0014\u077e\u077f\n"+
		"\u0012\u0000\u0000\u077f\u0780\u0007\u001c\u0000\u0000\u0780\u07ad\u0003"+
		"\u0090H\u0013\u0781\u0782\n\u0011\u0000\u0000\u0782\u0783\u0007\u001d"+
		"\u0000\u0000\u0783\u07ad\u0003\u0090H\u0012\u0784\u0785\n\u0003\u0000"+
		"\u0000\u0785\u0786\u0005\u00f0\u0000\u0000\u0786\u07ad\u0003\u0090H\u0003"+
		"\u0787\u0788\n\u0002\u0000\u0000\u0788\u0789\u0007\u001e\u0000\u0000\u0789"+
		"\u07ad\u0003\u0090H\u0003\u078a\u078b\n\u0001\u0000\u0000\u078b\u078c"+
		"\u0007\u001f\u0000\u0000\u078c\u07ad\u0003\u0090H\u0002\u078d\u078e\n"+
		"\u001c\u0000\u0000\u078e\u078f\u0005\u00e3\u0000\u0000\u078f\u07ad\u0003"+
		"\u009cN\u0000\u0790\u0793\n\u001b\u0000\u0000\u0791\u0792\u0005\u00e3"+
		"\u0000\u0000\u0792\u0794\u0003\u0090H\u0000\u0793\u0791\u0001\u0000\u0000"+
		"\u0000\u0794\u0795\u0001\u0000\u0000\u0000\u0795\u0793\u0001\u0000\u0000"+
		"\u0000\u0795\u0796\u0001\u0000\u0000\u0000\u0796\u07ad\u0001\u0000\u0000"+
		"\u0000\u0797\u0799\n\u001a\u0000\u0000\u0798\u079a\u0003\u00e6s\u0000"+
		"\u0799\u0798\u0001\u0000\u0000\u0000\u079a\u079b\u0001\u0000\u0000\u0000"+
		"\u079b\u0799\u0001\u0000\u0000\u0000\u079b\u079c\u0001\u0000\u0000\u0000"+
		"\u079c\u07ad\u0001\u0000\u0000\u0000\u079d\u079e\n\u0017\u0000\u0000\u079e"+
		"\u079f\u0005u\u0000\u0000\u079f\u07ad\u0003\u0106\u0083\u0000\u07a0\u07a1"+
		"\n\u0014\u0000\u0000\u07a1\u07ad\u0003\u0094J\u0000\u07a2\u07a3\n\u0010"+
		"\u0000\u0000\u07a3\u07a8\u0005\u00eb\u0000\u0000\u07a4\u07a9\u00052\u0000"+
		"\u0000\u07a5\u07a6\u0003\u00f6{\u0000\u07a6\u07a7\u0003\u0108\u0084\u0000"+
		"\u07a7\u07a9\u0001\u0000\u0000\u0000\u07a8\u07a4\u0001\u0000\u0000\u0000"+
		"\u07a8\u07a5\u0001\u0000\u0000\u0000\u07a9\u07ad\u0001\u0000\u0000\u0000"+
		"\u07aa\u07ab\n\u000f\u0000\u0000\u07ab\u07ad\u0003\u00d0h\u0000\u07ac"+
		"\u0775\u0001\u0000\u0000\u0000\u07ac\u0778\u0001\u0000\u0000\u0000\u07ac"+
		"\u077b\u0001\u0000\u0000\u0000\u07ac\u077e\u0001\u0000\u0000\u0000\u07ac"+
		"\u0781\u0001\u0000\u0000\u0000\u07ac\u0784\u0001\u0000\u0000\u0000\u07ac"+
		"\u0787\u0001\u0000\u0000\u0000\u07ac\u078a\u0001\u0000\u0000\u0000\u07ac"+
		"\u078d\u0001\u0000\u0000\u0000\u07ac\u0790\u0001\u0000\u0000\u0000\u07ac"+
		"\u0797\u0001\u0000\u0000\u0000\u07ac\u079d\u0001\u0000\u0000\u0000\u07ac"+
		"\u07a0\u0001\u0000\u0000\u0000\u07ac\u07a2\u0001\u0000\u0000\u0000\u07ac"+
		"\u07aa\u0001\u0000\u0000\u0000\u07ad\u07b0\u0001\u0000\u0000\u0000\u07ae"+
		"\u07ac\u0001\u0000\u0000\u0000\u07ae\u07af\u0001\u0000\u0000\u0000\u07af"+
		"\u0091\u0001\u0000\u0000\u0000\u07b0\u07ae\u0001\u0000\u0000\u0000\u07b1"+
		"\u07b2\u0005\u00f5\u0000\u0000\u07b2\u07bb\u0003\u008cF\u0000\u07b3\u07b6"+
		"\u0005f\u0000\u0000\u07b4\u07b7\u0003\u0086C\u0000\u07b5\u07b7\u0003\u0094"+
		"J\u0000\u07b6\u07b4\u0001\u0000\u0000\u0000\u07b6\u07b5\u0001\u0000\u0000"+
		"\u0000\u07b7\u07b8\u0001\u0000\u0000\u0000\u07b8\u07b9\u0005\u0094\u0000"+
		"\u0000\u07b9\u07ba\u0003\u008cF\u0000\u07ba\u07bc\u0001\u0000\u0000\u0000"+
		"\u07bb\u07b3\u0001\u0000\u0000\u0000\u07bc\u07bd\u0001\u0000\u0000\u0000"+
		"\u07bd\u07bb\u0001\u0000\u0000\u0000\u07bd\u07be\u0001\u0000\u0000\u0000"+
		"\u07be\u07c1\u0001\u0000\u0000\u0000\u07bf\u07c0\u0005\u00f6\u0000\u0000"+
		"\u07c0\u07c2\u0003\u008cF\u0000\u07c1\u07bf\u0001\u0000\u0000\u0000\u07c1"+
		"\u07c2\u0001\u0000\u0000\u0000\u07c2\u07c3\u0001\u0000\u0000\u0000\u07c3"+
		"\u07c4\u0005\u000e\u0000\u0000\u07c4\u07d6\u0001\u0000\u0000\u0000\u07c5"+
		"\u07cb\u0005\u00f5\u0000\u0000\u07c6\u07c7\u0005f\u0000\u0000\u07c7\u07c8"+
		"\u0003\u008cF\u0000\u07c8\u07c9\u0005\u0094\u0000\u0000\u07c9\u07ca\u0003"+
		"\u008cF\u0000\u07ca\u07cc\u0001\u0000\u0000\u0000\u07cb\u07c6\u0001\u0000"+
		"\u0000\u0000\u07cc\u07cd\u0001\u0000\u0000\u0000\u07cd\u07cb\u0001\u0000"+
		"\u0000\u0000\u07cd\u07ce\u0001\u0000\u0000\u0000\u07ce\u07d1\u0001\u0000"+
		"\u0000\u0000\u07cf\u07d0\u0005\u00f6\u0000\u0000\u07d0\u07d2\u0003\u008c"+
		"F\u0000\u07d1\u07cf\u0001\u0000\u0000\u0000\u07d1\u07d2\u0001\u0000\u0000"+
		"\u0000\u07d2\u07d3\u0001\u0000\u0000\u0000\u07d3\u07d4\u0005\u000e\u0000"+
		"\u0000\u07d4\u07d6\u0001\u0000\u0000\u0000\u07d5\u07b1\u0001\u0000\u0000"+
		"\u0000\u07d5\u07c5\u0001\u0000\u0000\u0000\u07d6\u0093\u0001\u0000\u0000"+
		"\u0000\u07d7\u07d8\u0003\u0096K\u0000\u07d8\u07d9\u0003\u0090H\u0000\u07d9"+
		"\u0847\u0001\u0000\u0000\u0000\u07da\u07df\u0005\u00f7\u0000\u0000\u07db"+
		"\u07df\u0005\u00f8\u0000\u0000\u07dc\u07dd\u0005l\u0000\u0000\u07dd\u07df"+
		"\u0005q\u0000\u0000\u07de\u07da\u0001\u0000\u0000\u0000\u07de\u07db\u0001"+
		"\u0000\u0000\u0000\u07de\u07dc\u0001\u0000\u0000\u0000\u07df\u0847\u0001"+
		"\u0000\u0000\u0000\u07e0\u07e2\u0005\u00f9\u0000\u0000\u07e1\u07e3\u0005"+
		"l\u0000\u0000\u07e2\u07e1\u0001\u0000\u0000\u0000\u07e2\u07e3\u0001\u0000"+
		"\u0000\u0000\u07e3\u07e4\u0001\u0000\u0000\u0000\u07e4\u0847\u0003\u00cc"+
		"f\u0000\u07e5\u07e7\u0005\u00f9\u0000\u0000\u07e6\u07e8\u0005l\u0000\u0000"+
		"\u07e7\u07e6\u0001\u0000\u0000\u0000\u07e7\u07e8\u0001\u0000\u0000\u0000"+
		"\u07e8\u07e9\u0001\u0000\u0000\u0000\u07e9\u0847\u0003\u0098L\u0000\u07ea"+
		"\u07ec\u0005\u00f9\u0000\u0000\u07eb\u07ed\u0005l\u0000\u0000\u07ec\u07eb"+
		"\u0001\u0000\u0000\u0000\u07ec\u07ed\u0001\u0000\u0000\u0000\u07ed\u07ee"+
		"\u0001\u0000\u0000\u0000\u07ee\u07ef\u0005\u00b5\u0000\u0000\u07ef\u07f0"+
		"\u0005\u0088\u0000\u0000\u07f0\u0847\u0003\u0090H\u0000\u07f1\u07f3\u0005"+
		"\u00f9\u0000\u0000\u07f2\u07f4\u0005l\u0000\u0000\u07f3\u07f2\u0001\u0000"+
		"\u0000\u0000\u07f3\u07f4\u0001\u0000\u0000\u0000\u07f4\u07f5\u0001\u0000"+
		"\u0000\u0000\u07f5\u07f7\u0005^\u0000\u0000\u07f6\u07f8\u0005\u00fa\u0000"+
		"\u0000\u07f7\u07f6\u0001\u0000\u0000\u0000\u07f7\u07f8\u0001\u0000\u0000"+
		"\u0000\u07f8\u07f9\u0001\u0000\u0000\u0000\u07f9\u07fb\u0005\u0011\u0000"+
		"\u0000\u07fa\u07fc\u0005\u0089\u0000\u0000\u07fb\u07fa\u0001\u0000\u0000"+
		"\u0000\u07fb\u07fc\u0001\u0000\u0000\u0000\u07fc\u07fd\u0001\u0000\u0000"+
		"\u0000\u07fd\u0802\u0003\u009cN\u0000\u07fe\u07ff\u0005\u001d\u0000\u0000"+
		"\u07ff\u0801\u0003\u009cN\u0000\u0800\u07fe\u0001\u0000\u0000\u0000\u0801"+
		"\u0804\u0001\u0000\u0000\u0000\u0802\u0800\u0001\u0000\u0000\u0000\u0802"+
		"\u0803\u0001\u0000\u0000\u0000\u0803\u0805\u0001\u0000\u0000\u0000\u0804"+
		"\u0802\u0001\u0000\u0000\u0000\u0805\u0806\u0005\u0012\u0000\u0000\u0806"+
		"\u0847\u0001\u0000\u0000\u0000\u0807\u0809\u0005\u00f9\u0000\u0000\u0808"+
		"\u080a\u0005l\u0000\u0000\u0809\u0808\u0001\u0000\u0000\u0000\u0809\u080a"+
		"\u0001\u0000\u0000\u0000\u080a\u080b\u0001\u0000\u0000\u0000\u080b\u080d"+
		"\u0005)\u0000\u0000\u080c\u080e\u0003\u009aM\u0000\u080d\u080c\u0001\u0000"+
		"\u0000\u0000\u080d\u080e\u0001\u0000\u0000\u0000\u080e\u0810\u0001\u0000"+
		"\u0000\u0000\u080f\u0811\u0003\u00f8|\u0000\u0810\u080f\u0001\u0000\u0000"+
		"\u0000\u0810\u0811\u0001\u0000\u0000\u0000\u0811\u0847\u0001\u0000\u0000"+
		"\u0000\u0812\u0814\u0005\u00f9\u0000\u0000\u0813\u0815\u0005l\u0000\u0000"+
		"\u0814\u0813\u0001\u0000\u0000\u0000\u0814\u0815\u0001\u0000\u0000\u0000"+
		"\u0815\u0816\u0001\u0000\u0000\u0000\u0816\u0847\u0003\u008cF\u0000\u0817"+
		"\u0819\u0005l\u0000\u0000\u0818\u0817\u0001\u0000\u0000\u0000\u0818\u0819"+
		"\u0001\u0000\u0000\u0000\u0819\u081a\u0001\u0000\u0000\u0000\u081a\u081b"+
		"\u0005\u00c0\u0000\u0000\u081b\u081e\u0005\u0011\u0000\u0000\u081c\u081f"+
		"\u0003J%\u0000\u081d\u081f\u0003\u0086C\u0000\u081e\u081c\u0001\u0000"+
		"\u0000\u0000\u081e\u081d\u0001\u0000\u0000\u0000\u081e\u081f\u0001\u0000"+
		"\u0000\u0000\u081f\u0820\u0001\u0000\u0000\u0000\u0820\u0847\u0005\u0012"+
		"\u0000\u0000\u0821\u0823\u0005l\u0000\u0000\u0822\u0821\u0001\u0000\u0000"+
		"\u0000\u0822\u0823\u0001\u0000\u0000\u0000\u0823\u0824\u0001\u0000\u0000"+
		"\u0000\u0824\u0825\u0005\u00c0\u0000\u0000\u0825\u0847\u0003\u0090H\u0000"+
		"\u0826\u0828\u0005l\u0000\u0000\u0827\u0826\u0001\u0000\u0000\u0000\u0827"+
		"\u0828\u0001\u0000\u0000\u0000\u0828\u0829\u0001\u0000\u0000\u0000\u0829"+
		"\u082b\u0005\u00fb\u0000\u0000\u082a\u082c\u0007 \u0000\u0000\u082b\u082a"+
		"\u0001\u0000\u0000\u0000\u082b\u082c\u0001\u0000\u0000\u0000\u082c\u082d"+
		"\u0001\u0000\u0000\u0000\u082d\u082e\u0003\u0090H\u0000\u082e\u082f\u0005"+
		"\u0093\u0000\u0000\u082f\u0830\u0003\u0090H\u0000\u0830\u0847\u0001\u0000"+
		"\u0000\u0000\u0831\u0833\u0005l\u0000\u0000\u0832\u0831\u0001\u0000\u0000"+
		"\u0000\u0832\u0833\u0001\u0000\u0000\u0000\u0833\u0834\u0001\u0000\u0000"+
		"\u0000\u0834\u0835\u0007!\u0000\u0000\u0835\u083b\u0003\u0090H\u0000\u0836"+
		"\u0839\u0005\u0102\u0000\u0000\u0837\u083a\u0003\u0108\u0084\u0000\u0838"+
		"\u083a\u0003\u008cF\u0000\u0839\u0837\u0001\u0000\u0000\u0000\u0839\u0838"+
		"\u0001\u0000\u0000\u0000\u083a\u083c\u0001\u0000\u0000\u0000\u083b\u0836"+
		"\u0001\u0000\u0000\u0000\u083b\u083c\u0001\u0000\u0000\u0000\u083c\u0847"+
		"\u0001\u0000\u0000\u0000\u083d\u083e\u0005\u0103\u0000\u0000\u083e\u0843"+
		"\u0005\u0011\u0000\u0000\u083f\u0844\u0005\u0097\u0000\u0000\u0840\u0841"+
		"\u0007\"\u0000\u0000\u0841\u0842\u0005\u001d\u0000\u0000\u0842\u0844\u0003"+
		"\u0108\u0084\u0000\u0843\u083f\u0001\u0000\u0000\u0000\u0843\u0840\u0001"+
		"\u0000\u0000\u0000\u0844\u0845\u0001\u0000\u0000\u0000\u0845\u0847\u0005"+
		"\u0012\u0000\u0000\u0846\u07d7\u0001\u0000\u0000\u0000\u0846\u07de\u0001"+
		"\u0000\u0000\u0000\u0846\u07e0\u0001\u0000\u0000\u0000\u0846\u07e5\u0001"+
		"\u0000\u0000\u0000\u0846\u07ea\u0001\u0000\u0000\u0000\u0846\u07f1\u0001"+
		"\u0000\u0000\u0000\u0846\u0807\u0001\u0000\u0000\u0000\u0846\u0812\u0001"+
		"\u0000\u0000\u0000\u0846\u0818\u0001\u0000\u0000\u0000\u0846\u0822\u0001"+
		"\u0000\u0000\u0000\u0846\u0827\u0001\u0000\u0000\u0000\u0846\u0832\u0001"+
		"\u0000\u0000\u0000\u0846\u083d\u0001\u0000\u0000\u0000\u0847\u0095\u0001"+
		"\u0000\u0000\u0000\u0848\u0849\u0007#\u0000\u0000\u0849\u0097\u0001\u0000"+
		"\u0000\u0000\u084a\u0851\u0005\u010f\u0000\u0000\u084b\u0851\u0005\u0110"+
		"\u0000\u0000\u084c\u0851\u0005\u0111\u0000\u0000\u084d\u084e\u0005\u0112"+
		"\u0000\u0000\u084e\u0851\u00051\u0000\u0000\u084f\u0851\u0005\u0113\u0000"+
		"\u0000\u0850\u084a\u0001\u0000\u0000\u0000\u0850\u084b\u0001\u0000\u0000"+
		"\u0000\u0850\u084c\u0001\u0000\u0000\u0000\u0850\u084d\u0001\u0000\u0000"+
		"\u0000\u0850\u084f\u0001\u0000\u0000\u0000\u0851\u0099\u0001\u0000\u0000"+
		"\u0000\u0852\u0853\u0007$\u0000\u0000\u0853\u009b\u0001\u0000\u0000\u0000"+
		"\u0854\u0855\u0006N\uffff\uffff\u0000\u0855\u0856\u0005e\u0000\u0000\u0856"+
		"\u0857\u0005\u0011\u0000\u0000\u0857\u0858\u0003\u0106\u0083\u0000\u0858"+
		"\u085f\u0003\u009cN\u0000\u0859\u085a\u0005\u001d\u0000\u0000\u085a\u085b"+
		"\u0003\u0106\u0083\u0000\u085b\u085c\u0003\u009cN\u0000\u085c\u085e\u0001"+
		"\u0000\u0000\u0000\u085d\u0859\u0001\u0000\u0000\u0000\u085e\u0861\u0001"+
		"\u0000\u0000\u0000\u085f\u085d\u0001\u0000\u0000\u0000\u085f\u0860\u0001"+
		"\u0000\u0000\u0000\u0860\u0862\u0001\u0000\u0000\u0000\u0861\u085f\u0001"+
		"\u0000\u0000\u0000\u0862\u0863\u0005\u0012\u0000\u0000\u0863\u0868\u0001"+
		"\u0000\u0000\u0000\u0864\u0865\u0005\u0117\u0000\u0000\u0865\u0868\u0003"+
		"\u009cN\u0004\u0866";
	private static final String _serializedATNSegment1 =
		"\u0868\u0003\u009eO\u0000\u0867\u0854\u0001\u0000\u0000\u0000\u0867\u0864"+
		"\u0001\u0000\u0000\u0000\u0867\u0866\u0001\u0000\u0000\u0000\u0868\u087c"+
		"\u0001\u0000\u0000\u0000\u0869\u086a\n\u0003\u0000\u0000\u086a\u086e\u0005"+
		"\u0114\u0000\u0000\u086b\u086c\u0005\u0118\u0000\u0000\u086c\u086d\u0005"+
		"\u019d\u0000\u0000\u086d\u086f\u0005\u0119\u0000\u0000\u086e\u086b\u0001"+
		"\u0000\u0000\u0000\u086e\u086f\u0001\u0000\u0000\u0000\u086f\u087b\u0001"+
		"\u0000\u0000\u0000\u0870\u0876\n\u0002\u0000\u0000\u0871\u0873\u0005\u0118"+
		"\u0000\u0000\u0872\u0874\u0005\u019d\u0000\u0000\u0873\u0872\u0001\u0000"+
		"\u0000\u0000\u0873\u0874\u0001\u0000\u0000\u0000\u0874\u0875\u0001\u0000"+
		"\u0000\u0000\u0875\u0877\u0005\u0119\u0000\u0000\u0876\u0871\u0001\u0000"+
		"\u0000\u0000\u0877\u0878\u0001\u0000\u0000\u0000\u0878\u0876\u0001\u0000"+
		"\u0000\u0000\u0878\u0879\u0001\u0000\u0000\u0000\u0879\u087b\u0001\u0000"+
		"\u0000\u0000\u087a\u0869\u0001\u0000\u0000\u0000\u087a\u0870\u0001\u0000"+
		"\u0000\u0000\u087b\u087e\u0001\u0000\u0000\u0000\u087c\u087a\u0001\u0000"+
		"\u0000\u0000\u087c\u087d\u0001\u0000\u0000\u0000\u087d\u009d\u0001\u0000"+
		"\u0000\u0000\u087e\u087c\u0001\u0000\u0000\u0000\u087f\u08cb\u0005\u011a"+
		"\u0000\u0000\u0880\u08cb\u0005\u011b\u0000\u0000\u0881\u08cb\u0005\u011c"+
		"\u0000\u0000\u0882\u08cb\u0005\u011d\u0000\u0000\u0883\u08cb\u0005\u011e"+
		"\u0000\u0000\u0884\u08cb\u0005\u011f\u0000\u0000\u0885\u08cb\u0005\u0120"+
		"\u0000\u0000\u0886\u0888\u0005\u0121\u0000\u0000\u0887\u0889\u0003\u00a2"+
		"Q\u0000\u0888\u0887\u0001\u0000\u0000\u0000\u0888\u0889\u0001\u0000\u0000"+
		"\u0000\u0889\u08cb\u0001\u0000\u0000\u0000\u088a\u088b\u0005\u0122\u0000"+
		"\u0000\u088b\u08cb\u0005\u0123\u0000\u0000\u088c\u088e\u0005\u0124\u0000"+
		"\u0000\u088d\u088f\u0003\u00a4R\u0000\u088e\u088d\u0001\u0000\u0000\u0000"+
		"\u088e\u088f\u0001\u0000\u0000\u0000\u088f\u08cb\u0001\u0000\u0000\u0000"+
		"\u0890\u0892\u0005\u0125\u0000\u0000\u0891\u0893\u0003\u00a4R\u0000\u0892"+
		"\u0891\u0001\u0000\u0000\u0000\u0892\u0893\u0001\u0000\u0000\u0000\u0893"+
		"\u08cb\u0001\u0000\u0000\u0000\u0894\u0896\u0005\u0126\u0000\u0000\u0895"+
		"\u0897\u0003\u00a4R\u0000\u0896\u0895\u0001\u0000\u0000\u0000\u0896\u0897"+
		"\u0001\u0000\u0000\u0000\u0897\u08cb\u0001\u0000\u0000\u0000\u0898\u089a"+
		"\u0005\u0127\u0000\u0000\u0899\u089b\u0003\u00a4R\u0000\u089a\u0899\u0001"+
		"\u0000\u0000\u0000\u089a\u089b\u0001\u0000\u0000\u0000\u089b\u08cb\u0001"+
		"\u0000\u0000\u0000\u089c\u08cb\u0005\u0128\u0000\u0000\u089d\u08cb\u0005"+
		"\u0129\u0000\u0000\u089e\u08cb\u0005\u00ec\u0000\u0000\u089f\u08cb\u0005"+
		"\u012a\u0000\u0000\u08a0\u08a2\u0005\u012b\u0000\u0000\u08a1\u08a3\u0005"+
		"\u012c\u0000\u0000\u08a2\u08a1\u0001\u0000\u0000\u0000\u08a2\u08a3\u0001"+
		"\u0000\u0000\u0000\u08a3\u08a5\u0001\u0000\u0000\u0000\u08a4\u08a6\u0003"+
		"\u00a2Q\u0000\u08a5\u08a4\u0001\u0000\u0000\u0000\u08a5\u08a6\u0001\u0000"+
		"\u0000\u0000\u08a6\u08cb\u0001\u0000\u0000\u0000\u08a7\u08a9\u0003\u00a0"+
		"P\u0000\u08a8\u08aa\u0003\u00a2Q\u0000\u08a9\u08a8\u0001\u0000\u0000\u0000"+
		"\u08a9\u08aa\u0001\u0000\u0000\u0000\u08aa\u08cb\u0001\u0000\u0000\u0000"+
		"\u08ab\u08ad\u0005\u012d\u0000\u0000\u08ac\u08ae\u0003\u00a4R\u0000\u08ad"+
		"\u08ac\u0001\u0000\u0000\u0000\u08ad\u08ae\u0001\u0000\u0000\u0000\u08ae"+
		"\u08cb\u0001\u0000\u0000\u0000\u08af\u08cb\u0005\u012e\u0000\u0000\u08b0"+
		"\u08cb\u0005\u012f\u0000\u0000\u08b1\u08cb\u0005\u0130\u0000\u0000\u08b2"+
		"\u08b4\u0007%\u0000\u0000\u08b3\u08b5\u0003\u00a2Q\u0000\u08b4\u08b3\u0001"+
		"\u0000\u0000\u0000\u08b4\u08b5\u0001\u0000\u0000\u0000\u08b5\u08bc\u0001"+
		"\u0000\u0000\u0000\u08b6\u08b8\u0003\u00fc~\u0000\u08b7\u08b9\u00052\u0000"+
		"\u0000\u08b8\u08b7\u0001\u0000\u0000\u0000\u08b8\u08b9\u0001\u0000\u0000"+
		"\u0000\u08b9\u08ba\u0001\u0000\u0000\u0000\u08ba\u08bb\u0003\u00f6{\u0000"+
		"\u08bb\u08bd\u0001\u0000\u0000\u0000\u08bc\u08b6\u0001\u0000\u0000\u0000"+
		"\u08bc\u08bd\u0001\u0000\u0000\u0000\u08bd\u08cb\u0001\u0000\u0000\u0000"+
		"\u08be\u08cb\u0005\u0133\u0000\u0000\u08bf\u08cb\u0005)\u0000\u0000\u08c0"+
		"\u08cb\u0005\u0134\u0000\u0000\u08c1\u08cb\u0005(\u0000\u0000\u08c2\u08c4"+
		"\u0003\u0106\u0083\u0000\u08c3\u08c2\u0001\u0000\u0000\u0000\u08c4\u08c5"+
		"\u0001\u0000\u0000\u0000\u08c5\u08c3\u0001\u0000\u0000\u0000\u08c5\u08c6"+
		"\u0001\u0000\u0000\u0000\u08c6\u08c8\u0001\u0000\u0000\u0000\u08c7\u08c9"+
		"\u0003\u00a4R\u0000\u08c8\u08c7\u0001\u0000\u0000\u0000\u08c8\u08c9\u0001"+
		"\u0000\u0000\u0000\u08c9\u08cb\u0001\u0000\u0000\u0000\u08ca\u087f\u0001"+
		"\u0000\u0000\u0000\u08ca\u0880\u0001\u0000\u0000\u0000\u08ca\u0881\u0001"+
		"\u0000\u0000\u0000\u08ca\u0882\u0001\u0000\u0000\u0000\u08ca\u0883\u0001"+
		"\u0000\u0000\u0000\u08ca\u0884\u0001\u0000\u0000\u0000\u08ca\u0885\u0001"+
		"\u0000\u0000\u0000\u08ca\u0886\u0001\u0000\u0000\u0000\u08ca\u088a\u0001"+
		"\u0000\u0000\u0000\u08ca\u088c\u0001\u0000\u0000\u0000\u08ca\u0890\u0001"+
		"\u0000\u0000\u0000\u08ca\u0894\u0001\u0000\u0000\u0000\u08ca\u0898\u0001"+
		"\u0000\u0000\u0000\u08ca\u089c\u0001\u0000\u0000\u0000\u08ca\u089d\u0001"+
		"\u0000\u0000\u0000\u08ca\u089e\u0001\u0000\u0000\u0000\u08ca\u089f\u0001"+
		"\u0000\u0000\u0000\u08ca\u08a0\u0001\u0000\u0000\u0000\u08ca\u08a7\u0001"+
		"\u0000\u0000\u0000\u08ca\u08ab\u0001\u0000\u0000\u0000\u08ca\u08af\u0001"+
		"\u0000\u0000\u0000\u08ca\u08b0\u0001\u0000\u0000\u0000\u08ca\u08b1\u0001"+
		"\u0000\u0000\u0000\u08ca\u08b2\u0001\u0000\u0000\u0000\u08ca\u08be\u0001"+
		"\u0000\u0000\u0000\u08ca\u08bf\u0001\u0000\u0000\u0000\u08ca\u08c0\u0001"+
		"\u0000\u0000\u0000\u08ca\u08c1\u0001\u0000\u0000\u0000\u08ca\u08c3\u0001"+
		"\u0000\u0000\u0000\u08cb\u009f\u0001\u0000\u0000\u0000\u08cc\u08ce\u0007"+
		"&\u0000\u0000\u08cd\u08cf\u0005\u012c\u0000\u0000\u08ce\u08cd\u0001\u0000"+
		"\u0000\u0000\u08ce\u08cf\u0001\u0000\u0000\u0000\u08cf\u08d8\u0001\u0000"+
		"\u0000\u0000\u08d0\u08d8\u0005\u0138\u0000\u0000\u08d1\u08d8\u0005\u0139"+
		"\u0000\u0000\u08d2\u08d3\u0005\u013a\u0000\u0000\u08d3\u08d5\u0007\'\u0000"+
		"\u0000\u08d4\u08d6\u0005\u012c\u0000\u0000\u08d5\u08d4\u0001\u0000\u0000"+
		"\u0000\u08d5\u08d6\u0001\u0000\u0000\u0000\u08d6\u08d8\u0001\u0000\u0000"+
		"\u0000\u08d7\u08cc\u0001\u0000\u0000\u0000\u08d7\u08d0\u0001\u0000\u0000"+
		"\u0000\u08d7\u08d1\u0001\u0000\u0000\u0000\u08d7\u08d2\u0001\u0000\u0000"+
		"\u0000\u08d8\u00a1\u0001\u0000\u0000\u0000\u08d9\u08da\u0005\u0011\u0000"+
		"\u0000\u08da\u08db\u0005\u019d\u0000\u0000\u08db\u08dc\u0005\u0012\u0000"+
		"\u0000\u08dc\u00a3\u0001\u0000\u0000\u0000\u08dd\u08de\u0005\u0011\u0000"+
		"\u0000\u08de\u08e1\u0003\u0090H\u0000\u08df\u08e0\u0005\u001d\u0000\u0000"+
		"\u08e0\u08e2\u0003\u0090H\u0000\u08e1\u08df\u0001\u0000\u0000\u0000\u08e1"+
		"\u08e2\u0001\u0000\u0000\u0000\u08e2\u08e3\u0001\u0000\u0000\u0000\u08e3"+
		"\u08e4\u0005\u0012\u0000\u0000\u08e4\u00a5\u0001\u0000\u0000\u0000\u08e5"+
		"\u08e6\u0005\u008a\u0000\u0000\u08e6\u08e7\u0003\u0086C\u0000\u08e7\u00a7"+
		"\u0001\u0000\u0000\u0000\u08e8\u08e9\u0005\u0114\u0000\u0000\u08e9\u08ea"+
		"\u0003\u00aaU\u0000\u08ea\u00a9\u0001\u0000\u0000\u0000\u08eb\u08ee\u0005"+
		"\u0118\u0000\u0000\u08ec\u08ef\u0003\u0086C\u0000\u08ed\u08ef\u0003\u00ac"+
		"V\u0000\u08ee\u08ec\u0001\u0000\u0000\u0000\u08ee\u08ed\u0001\u0000\u0000"+
		"\u0000\u08ee\u08ef\u0001\u0000\u0000\u0000\u08ef\u08f0\u0001\u0000\u0000"+
		"\u0000\u08f0\u08f1\u0005\u0119\u0000\u0000\u08f1\u00ab\u0001\u0000\u0000"+
		"\u0000\u08f2\u08f7\u0003\u00aaU\u0000\u08f3\u08f4\u0005\u001d\u0000\u0000"+
		"\u08f4\u08f6\u0003\u00aaU\u0000\u08f5\u08f3\u0001\u0000\u0000\u0000\u08f6"+
		"\u08f9\u0001\u0000\u0000\u0000\u08f7\u08f5\u0001\u0000\u0000\u0000\u08f7"+
		"\u08f8\u0001\u0000\u0000\u0000\u08f8\u00ad\u0001\u0000\u0000\u0000\u08f9"+
		"\u08f7\u0001\u0000\u0000\u0000\u08fa\u08fb\u0005\u013b\u0000\u0000\u08fb"+
		"\u08fd\u0005\u0011\u0000\u0000\u08fc\u08fe\u0007(\u0000\u0000\u08fd\u08fc"+
		"\u0001\u0000\u0000\u0000\u08fd\u08fe\u0001\u0000\u0000\u0000\u08fe\u0900"+
		"\u0001\u0000\u0000\u0000\u08ff\u0901\u0003\u008cF\u0000\u0900\u08ff\u0001"+
		"\u0000\u0000\u0000\u0900\u0901\u0001\u0000\u0000\u0000\u0901\u0903\u0001"+
		"\u0000\u0000\u0000\u0902\u0904\u0005\u0088\u0000\u0000\u0903\u0902\u0001"+
		"\u0000\u0000\u0000\u0903\u0904\u0001\u0000\u0000\u0000\u0904\u0905\u0001"+
		"\u0000\u0000\u0000\u0905\u0906\u0003\u008cF\u0000\u0906\u0907\u0005\u0012"+
		"\u0000\u0000\u0907\u09e1\u0001\u0000\u0000\u0000\u0908\u0909\u0005\u013f"+
		"\u0000\u0000\u0909\u090a\u0005\u0011\u0000\u0000\u090a\u090b\u0003\u008c"+
		"F\u0000\u090b\u090c\u0005\u0088\u0000\u0000\u090c\u090f\u0003\u008cF\u0000"+
		"\u090d\u090e\u0005c\u0000\u0000\u090e\u0910\u0003\u008cF\u0000\u090f\u090d"+
		"\u0001\u0000\u0000\u0000\u090f\u0910\u0001\u0000\u0000\u0000\u0910\u0911"+
		"\u0001\u0000\u0000\u0000\u0911\u0912\u0005\u0012\u0000\u0000\u0912\u09e1"+
		"\u0001\u0000\u0000\u0000\u0913\u0914\u0005\u0140\u0000\u0000\u0914\u0915"+
		"\u0005\u0011\u0000\u0000\u0915\u0917\u0003\u00d8l\u0000\u0916\u0918\u0003"+
		"\u00f0x\u0000\u0917\u0916\u0001\u0000\u0000\u0000\u0917\u0918\u0001\u0000"+
		"\u0000\u0000\u0918\u091a\u0001\u0000\u0000\u0000\u0919\u091b\u0003\u00f8"+
		"|\u0000\u091a\u0919\u0001\u0000\u0000\u0000\u091a\u091b\u0001\u0000\u0000"+
		"\u0000\u091b\u091c\u0001\u0000\u0000\u0000\u091c\u091e\u0005\u0012\u0000"+
		"\u0000\u091d\u091f\u0003\u00b2Y\u0000\u091e\u091d\u0001\u0000\u0000\u0000"+
		"\u091e\u091f\u0001\u0000\u0000\u0000\u091f\u0921\u0001\u0000\u0000\u0000"+
		"\u0920\u0922\u0003\u00b4Z\u0000\u0921\u0920\u0001\u0000\u0000\u0000\u0921"+
		"\u0922\u0001\u0000\u0000\u0000\u0922\u09e1\u0001\u0000\u0000\u0000\u0923"+
		"\u0924\u0005\u0141\u0000\u0000\u0924\u0925\u0005\u0011\u0000\u0000\u0925"+
		"\u0926\u0003\u0106\u0083\u0000\u0926\u092a\u0005\u0088\u0000\u0000\u0927"+
		"\u0929\t\u0000\u0000\u0000\u0928\u0927\u0001\u0000\u0000\u0000\u0929\u092c"+
		"\u0001\u0000\u0000\u0000\u092a\u092b\u0001\u0000\u0000\u0000\u092a\u0928"+
		"\u0001\u0000\u0000\u0000\u092b\u092d\u0001\u0000\u0000\u0000\u092c\u092a"+
		"\u0001\u0000\u0000\u0000\u092d\u092e\u0005\u0012\u0000\u0000\u092e\u09e1"+
		"\u0001\u0000\u0000\u0000\u092f\u0930\u0005\u0142\u0000\u0000\u0930\u0931"+
		"\u0005\u0011\u0000\u0000\u0931\u0932\u0007)\u0000\u0000\u0932\u0934\u0003"+
		"\u0106\u0083\u0000\u0933\u0935\u0003\u00c4b\u0000\u0934\u0933\u0001\u0000"+
		"\u0000\u0000\u0934\u0935\u0001\u0000\u0000\u0000\u0935\u0936\u0001\u0000"+
		"\u0000\u0000\u0936\u0937\u0005\u0012\u0000\u0000\u0937\u09e1\u0001\u0000"+
		"\u0000\u0000\u0938\u0939\u0005\u0143\u0000\u0000\u0939\u093a\u0005\u0011"+
		"\u0000\u0000\u093a\u093f\u0003\u00b6[\u0000\u093b\u093c\u0005\u001d\u0000"+
		"\u0000\u093c\u093e\u0003\u00b6[\u0000\u093d\u093b\u0001\u0000\u0000\u0000"+
		"\u093e\u0941\u0001\u0000\u0000\u0000\u093f\u093d\u0001\u0000\u0000\u0000"+
		"\u093f\u0940\u0001\u0000\u0000\u0000\u0940\u0942\u0001\u0000\u0000\u0000"+
		"\u0941\u093f\u0001\u0000\u0000\u0000\u0942\u0943\u0005\u0012\u0000\u0000"+
		"\u0943\u09e1\u0001\u0000\u0000\u0000\u0944\u0945\u0005\u0144\u0000\u0000"+
		"\u0945\u0946\u0005\u0011\u0000\u0000\u0946\u0947\u0003\u0086C\u0000\u0947"+
		"\u0948\u0005\u0012\u0000\u0000\u0948\u09e1\u0001\u0000\u0000\u0000\u0949"+
		"\u094a\u0005\u0145\u0000\u0000\u094a\u094c\u0005\u0011\u0000\u0000\u094b"+
		"\u094d\u0005\u0146\u0000\u0000\u094c\u094b\u0001\u0000\u0000\u0000\u094c"+
		"\u094d\u0001\u0000\u0000\u0000\u094d\u094e\u0001\u0000\u0000\u0000\u094e"+
		"\u0951\u0003\u0106\u0083\u0000\u094f\u0950\u0005\u001d\u0000\u0000\u0950"+
		"\u0952\u0003\u0086C\u0000\u0951\u094f\u0001\u0000\u0000\u0000\u0951\u0952"+
		"\u0001\u0000\u0000\u0000\u0952\u0953\u0001\u0000\u0000\u0000\u0953\u0954"+
		"\u0005\u0012\u0000\u0000\u0954\u09e1\u0001\u0000\u0000\u0000\u0955\u0956"+
		"\u0005\u0147\u0000\u0000\u0956\u0957\u0005\u0011\u0000\u0000\u0957\u0959"+
		"\u0003\u0090H\u0000\u0958\u095a\u0003\u0082A\u0000\u0959\u0958\u0001\u0000"+
		"\u0000\u0000\u0959\u095a\u0001\u0000\u0000\u0000\u095a\u095b\u0001\u0000"+
		"\u0000\u0000\u095b\u095c\u0005\u0012\u0000\u0000\u095c\u09e1\u0001\u0000"+
		"\u0000\u0000\u095d\u095e\u0005\u0148\u0000\u0000\u095e\u095f\u0005\u0011"+
		"\u0000\u0000\u095f\u0964\u0003\u00b6[\u0000\u0960\u0961\u0005\u001d\u0000"+
		"\u0000\u0961\u0963\u0003\u00b6[\u0000\u0962\u0960\u0001\u0000\u0000\u0000"+
		"\u0963\u0966\u0001\u0000\u0000\u0000\u0964\u0962\u0001\u0000\u0000\u0000"+
		"\u0964\u0965\u0001\u0000\u0000\u0000\u0965\u0967\u0001\u0000\u0000\u0000"+
		"\u0966\u0964\u0001\u0000\u0000\u0000\u0967\u0968\u0005\u0012\u0000\u0000"+
		"\u0968\u09e1\u0001\u0000\u0000\u0000\u0969\u096a\u0005\u0149\u0000\u0000"+
		"\u096a\u096b\u0005\u0011\u0000\u0000\u096b\u096c\u0003\u00b8\\\u0000\u096c"+
		"\u096d\u0003\u008cF\u0000\u096d\u096e\u0007*\u0000\u0000\u096e\u096f\u0005"+
		"\u014c\u0000\u0000\u096f\u0970\u0005\u0012\u0000\u0000\u0970\u09e1\u0001"+
		"\u0000\u0000\u0000\u0971\u0972\u0005\u014d\u0000\u0000\u0972\u0973\u0005"+
		"\u0011\u0000\u0000\u0973\u0974\u0005\u0146\u0000\u0000\u0974\u0977\u0003"+
		"\u0106\u0083\u0000\u0975\u0976\u0005\u001d\u0000\u0000\u0976\u0978\u0003"+
		"\u008cF\u0000\u0977\u0975\u0001\u0000\u0000\u0000\u0977\u0978\u0001\u0000"+
		"\u0000\u0000\u0978\u0979\u0001\u0000\u0000\u0000\u0979\u097a\u0005\u0012"+
		"\u0000\u0000\u097a\u09e1\u0001\u0000\u0000\u0000\u097b\u097c\u0005\u014e"+
		"\u0000\u0000\u097c\u097d\u0005\u0011\u0000\u0000\u097d\u097e\u0005(\u0000"+
		"\u0000\u097e\u097f\u0003\u008cF\u0000\u097f\u0980\u0005\u001d\u0000\u0000"+
		"\u0980\u0984\u0005\u014f\u0000\u0000\u0981\u0985\u0003\u008cF\u0000\u0982"+
		"\u0983\u0005W\u0000\u0000\u0983\u0985\u0005\u008e\u0000\u0000\u0984\u0981"+
		"\u0001\u0000\u0000\u0000\u0984\u0982\u0001\u0000\u0000\u0000\u0985\u0986"+
		"\u0001\u0000\u0000\u0000\u0986\u0987\u0005\u001d\u0000\u0000\u0987\u098d"+
		"\u0005\u0150\u0000\u0000\u0988\u098e\u0005\u0151\u0000\u0000\u0989\u098b"+
		"\u0005W\u0000\u0000\u098a\u098c\u0005\u008e\u0000\u0000\u098b\u098a\u0001"+
		"\u0000\u0000\u0000\u098b\u098c\u0001\u0000\u0000\u0000\u098c\u098e\u0001"+
		"\u0000\u0000\u0000\u098d\u0988\u0001\u0000\u0000\u0000\u098d\u0989\u0001"+
		"\u0000\u0000\u0000\u098e\u098f\u0001\u0000\u0000\u0000\u098f\u0990\u0005"+
		"\u0012\u0000\u0000\u0990\u09e1\u0001\u0000\u0000\u0000\u0991\u0992\u0005"+
		"\u0152\u0000\u0000\u0992\u0993\u0005\u0011\u0000\u0000\u0993\u0994\u0003"+
		"\u00b8\\\u0000\u0994\u0995\u0003\u008cF\u0000\u0995\u0996\u0005\u0006"+
		"\u0000\u0000\u0996\u0997\u0003\u009cN\u0000\u0997\u0998\u0005\u0012\u0000"+
		"\u0000\u0998\u09e1\u0001\u0000\u0000\u0000\u0999\u099a\u0005\u0114\u0000"+
		"\u0000\u099a\u099b\u0005\u0011\u0000\u0000\u099b\u099c\u0003J%\u0000\u099c"+
		"\u099d\u0005\u0012\u0000\u0000\u099d\u09e1\u0001\u0000\u0000\u0000\u099e"+
		"\u099f\u0005\u0153\u0000\u0000\u099f\u09a0\u0003\u00aeW\u0000\u09a0\u09a1"+
		"\u0005\u0154\u0000\u0000\u09a1\u09e1\u0001\u0000\u0000\u0000\u09a2\u09a3"+
		"\u0003\u00e4r\u0000\u09a3\u09a4\u0005\u0011\u0000\u0000\u09a4\u09a6\u0003"+
		"\u008cF\u0000\u09a5\u09a7\u0003\u00f2y\u0000\u09a6\u09a5\u0001\u0000\u0000"+
		"\u0000\u09a6\u09a7\u0001\u0000\u0000\u0000\u09a7\u09a8\u0001\u0000\u0000"+
		"\u0000\u09a8\u09aa\u0005\u0012\u0000\u0000\u09a9\u09ab\u0003\u00f2y\u0000"+
		"\u09aa\u09a9\u0001\u0000\u0000\u0000\u09aa\u09ab\u0001\u0000\u0000\u0000"+
		"\u09ab\u09ad\u0001\u0000\u0000\u0000\u09ac\u09ae\u0003\u00b4Z\u0000\u09ad"+
		"\u09ac\u0001\u0000\u0000\u0000\u09ad\u09ae\u0001\u0000\u0000\u0000\u09ae"+
		"\u09e1\u0001\u0000\u0000\u0000\u09af\u09b0\u0003\u00e4r\u0000\u09b0\u09ca"+
		"\u0005\u0011\u0000\u0000\u09b1\u09b2\u0003\u00e2q\u0000\u09b2\u09b3\u0005"+
		"\u00b7\u0000\u0000\u09b3\u09b5\u0001\u0000\u0000\u0000\u09b4\u09b1\u0001"+
		"\u0000\u0000\u0000\u09b4\u09b5\u0001\u0000\u0000\u0000\u09b5\u09b6\u0001"+
		"\u0000\u0000\u0000\u09b6\u09cb\u0005\u009a\u0000\u0000\u09b7\u09b9\u0003"+
		"\u00eau\u0000\u09b8\u09b7\u0001\u0000\u0000\u0000\u09b8\u09b9\u0001\u0000"+
		"\u0000\u0000\u09b9\u09ba\u0001\u0000\u0000\u0000\u09ba\u09bc\u0003\u0086"+
		"C\u0000\u09bb\u09bd\u0003\u00c4b\u0000\u09bc\u09bb\u0001\u0000\u0000\u0000"+
		"\u09bc\u09bd\u0001\u0000\u0000\u0000\u09bd\u09c1\u0001\u0000\u0000\u0000"+
		"\u09be\u09bf\u0005b\u0000\u0000\u09bf\u09c0\u0005\u0155\u0000\u0000\u09c0"+
		"\u09c2\u0005\u0156\u0000\u0000\u09c1\u09be\u0001\u0000\u0000\u0000\u09c1"+
		"\u09c2\u0001\u0000\u0000\u0000\u09c2\u09c5\u0001\u0000\u0000\u0000\u09c3"+
		"\u09c4\u0005\u0157\u0000\u0000\u09c4\u09c6\u0003\u008cF\u0000\u09c5\u09c3"+
		"\u0001\u0000\u0000\u0000\u09c5\u09c6\u0001\u0000\u0000\u0000\u09c6\u09c8"+
		"\u0001\u0000\u0000\u0000\u09c7\u09c9\u0003\u00f0x\u0000\u09c8\u09c7\u0001"+
		"\u0000\u0000\u0000\u09c8\u09c9\u0001\u0000\u0000\u0000\u09c9\u09cb\u0001"+
		"\u0000\u0000\u0000\u09ca\u09b4\u0001\u0000\u0000\u0000\u09ca\u09b8\u0001"+
		"\u0000\u0000\u0000\u09ca\u09cb\u0001\u0000\u0000\u0000\u09cb\u09cc\u0001"+
		"\u0000\u0000\u0000\u09cc\u09ce\u0005\u0012\u0000\u0000\u09cd\u09cf\u0003"+
		"\u00b0X\u0000\u09ce\u09cd\u0001\u0000\u0000\u0000\u09ce\u09cf\u0001\u0000"+
		"\u0000\u0000\u09cf\u09d1\u0001\u0000\u0000\u0000\u09d0\u09d2\u0003\u00b2"+
		"Y\u0000\u09d1\u09d0\u0001\u0000\u0000\u0000\u09d1\u09d2\u0001\u0000\u0000"+
		"\u0000\u09d2\u09d5\u0001\u0000\u0000\u0000\u09d3\u09d4\u0005\u0088\u0000"+
		"\u0000\u09d4\u09d6\u0003\u00ecv\u0000\u09d5\u09d3\u0001\u0000\u0000\u0000"+
		"\u09d5\u09d6\u0001\u0000\u0000\u0000\u09d6\u09d8\u0001\u0000\u0000\u0000"+
		"\u09d7\u09d9\u0003\u00f2y\u0000\u09d8\u09d7\u0001\u0000\u0000\u0000\u09d8"+
		"\u09d9\u0001\u0000\u0000\u0000\u09d9\u09db\u0001\u0000\u0000\u0000\u09da"+
		"\u09dc\u0003\u00b4Z\u0000\u09db\u09da\u0001\u0000\u0000\u0000\u09db\u09dc"+
		"\u0001\u0000\u0000\u0000\u09dc\u09e1\u0001\u0000\u0000\u0000\u09dd\u09e1"+
		"\u0005\u0158\u0000\u0000\u09de\u09e1\u0005\u0159\u0000\u0000\u09df\u09e1"+
		"\u0005\u015a\u0000\u0000\u09e0\u08fa\u0001\u0000\u0000\u0000\u09e0\u0908"+
		"\u0001\u0000\u0000\u0000\u09e0\u0913\u0001\u0000\u0000\u0000\u09e0\u0923"+
		"\u0001\u0000\u0000\u0000\u09e0\u092f\u0001\u0000\u0000\u0000\u09e0\u0938"+
		"\u0001\u0000\u0000\u0000\u09e0\u0944\u0001\u0000\u0000\u0000\u09e0\u0949"+
		"\u0001\u0000\u0000\u0000\u09e0\u0955\u0001\u0000\u0000\u0000\u09e0\u095d"+
		"\u0001\u0000\u0000\u0000\u09e0\u0969\u0001\u0000\u0000\u0000\u09e0\u0971"+
		"\u0001\u0000\u0000\u0000\u09e0\u097b\u0001\u0000\u0000\u0000\u09e0\u0991"+
		"\u0001\u0000\u0000\u0000\u09e0\u0999\u0001\u0000\u0000\u0000\u09e0\u099e"+
		"\u0001\u0000\u0000\u0000\u09e0\u09a2\u0001\u0000\u0000\u0000\u09e0\u09af"+
		"\u0001\u0000\u0000\u0000\u09e0\u09dd\u0001\u0000\u0000\u0000\u09e0\u09de"+
		"\u0001\u0000\u0000\u0000\u09e0\u09df\u0001\u0000\u0000\u0000\u09e1\u00af"+
		"\u0001\u0000\u0000\u0000\u09e2\u09e3\u0005\u015b\u0000\u0000\u09e3\u09e4"+
		"\u0005\u00c8\u0000\u0000\u09e4\u09e5\u0005\u0011\u0000\u0000\u09e5\u09e6"+
		"\u0003\u00c4b\u0000\u09e6\u09e7\u0005\u0012\u0000\u0000\u09e7\u00b1\u0001"+
		"\u0000\u0000\u0000\u09e8\u09e9\u0005\u015c\u0000\u0000\u09e9\u09ea\u0005"+
		"\u0011\u0000\u0000\u09ea\u09eb\u0005\u00c6\u0000\u0000\u09eb\u09ec\u0003"+
		"\u008cF\u0000\u09ec\u09ed\u0005\u0012\u0000\u0000\u09ed\u00b3\u0001\u0000"+
		"\u0000\u0000\u09ee\u09ef\u0005\u015d\u0000\u0000\u09ef\u09f0\u0003\u00ba"+
		"]\u0000\u09f0\u00b5\u0001\u0000\u0000\u0000\u09f1\u09f4\u0003\u008cF\u0000"+
		"\u09f2\u09f3\u0005\u0006\u0000\u0000\u09f3\u09f5\u0003\u00fe\u007f\u0000"+
		"\u09f4\u09f2\u0001\u0000\u0000\u0000\u09f4\u09f5\u0001\u0000\u0000\u0000"+
		"\u09f5\u00b7\u0001\u0000\u0000\u0000\u09f6\u09f7\u0007+\u0000\u0000\u09f7"+
		"\u00b9\u0001\u0000\u0000\u0000\u09f8\u09fa\u0005\u0011\u0000\u0000\u09f9"+
		"\u09fb\u0003\u0106\u0083\u0000\u09fa\u09f9\u0001\u0000\u0000\u0000\u09fa"+
		"\u09fb\u0001\u0000\u0000\u0000\u09fb\u09fd\u0001\u0000\u0000\u0000\u09fc"+
		"\u09fe\u0003\u00bc^\u0000\u09fd\u09fc\u0001\u0000\u0000\u0000\u09fd\u09fe"+
		"\u0001\u0000\u0000\u0000\u09fe\u0a00\u0001\u0000\u0000\u0000\u09ff\u0a01"+
		"\u0003\u00c4b\u0000\u0a00\u09ff\u0001\u0000\u0000\u0000\u0a00\u0a01\u0001"+
		"\u0000\u0000\u0000\u0a01\u0a03\u0001\u0000\u0000\u0000\u0a02\u0a04\u0003"+
		"\u00be_\u0000\u0a03\u0a02\u0001\u0000\u0000\u0000\u0a03\u0a04\u0001\u0000"+
		"\u0000\u0000\u0a04\u0a05\u0001\u0000\u0000\u0000\u0a05\u0a08\u0005\u0012"+
		"\u0000\u0000\u0a06\u0a08\u0003\u0106\u0083\u0000\u0a07\u09f8\u0001\u0000"+
		"\u0000\u0000\u0a07\u0a06\u0001\u0000\u0000\u0000\u0a08\u00bb\u0001\u0000"+
		"\u0000\u0000\u0a09\u0a0a\u0005\u0160\u0000\u0000\u0a0a\u0a0b\u0005\u0086"+
		"\u0000\u0000\u0a0b\u0a0c\u0003\u0086C\u0000\u0a0c\u00bd\u0001\u0000\u0000"+
		"\u0000\u0a0d\u0a14\u0007,\u0000\u0000\u0a0e\u0a15\u0003\u00c0`\u0000\u0a0f"+
		"\u0a10\u0005\u00fb\u0000\u0000\u0a10\u0a11\u0003\u00c2a\u0000\u0a11\u0a12"+
		"\u0005\u0093\u0000\u0000\u0a12\u0a13\u0003\u00c2a\u0000\u0a13\u0a15\u0001"+
		"\u0000\u0000\u0000\u0a14\u0a0e\u0001\u0000\u0000\u0000\u0a14\u0a0f\u0001"+
		"\u0000\u0000\u0000\u0a15\u0a1f\u0001\u0000\u0000\u0000\u0a16\u0a1d\u0005"+
		"\u00c4\u0000\u0000\u0a17\u0a18\u0005\u00c7\u0000\u0000\u0a18\u0a1e\u0005"+
		"e\u0000\u0000\u0a19\u0a1e\u0005\u00c8\u0000\u0000\u0a1a\u0a1e\u0005\u0164"+
		"\u0000\u0000\u0a1b\u0a1c\u0005W\u0000\u0000\u0a1c\u0a1e\u0005\u0165\u0000"+
		"\u0000\u0a1d\u0a17\u0001\u0000\u0000\u0000\u0a1d\u0a19\u0001\u0000\u0000"+
		"\u0000\u0a1d\u0a1a\u0001\u0000\u0000\u0000\u0a1d\u0a1b\u0001\u0000\u0000"+
		"\u0000\u0a1d\u0a1e\u0001\u0000\u0000\u0000\u0a1e\u0a20\u0001\u0000\u0000"+
		"\u0000\u0a1f\u0a16\u0001\u0000\u0000\u0000\u0a1f\u0a20\u0001\u0000\u0000"+
		"\u0000\u0a20\u00bf\u0001\u0000\u0000\u0000\u0a21\u0a25\u0005\u0166\u0000"+
		"\u0000\u0a22\u0a25\u0005\u0167\u0000\u0000\u0a23\u0a25\u0003\u008cF\u0000"+
		"\u0a24\u0a21\u0001\u0000\u0000\u0000\u0a24\u0a22\u0001\u0000\u0000\u0000"+
		"\u0a24\u0a23\u0001\u0000\u0000\u0000\u0a25\u0a26\u0001\u0000\u0000\u0000"+
		"\u0a26\u0a2a\u0005\u0168\u0000\u0000\u0a27\u0a28\u0005\u00c7\u0000\u0000"+
		"\u0a28\u0a2a\u0005e\u0000\u0000\u0a29\u0a24\u0001\u0000\u0000\u0000\u0a29"+
		"\u0a27\u0001\u0000\u0000\u0000\u0a2a\u00c1\u0001\u0000\u0000\u0000\u0a2b"+
		"\u0a2e\u0005\u0166\u0000\u0000\u0a2c\u0a2e\u0003\u008cF\u0000\u0a2d\u0a2b"+
		"\u0001\u0000\u0000\u0000\u0a2d\u0a2c\u0001\u0000\u0000\u0000\u0a2e\u0a2f"+
		"\u0001\u0000\u0000\u0000\u0a2f\u0a32\u0005\u0169\u0000\u0000\u0a30\u0a32"+
		"\u0003\u00c0`\u0000\u0a31\u0a2d\u0001\u0000\u0000\u0000\u0a31\u0a30\u0001"+
		"\u0000\u0000\u0000\u0a32\u00c3\u0001\u0000\u0000\u0000\u0a33\u0a34\u0005"+
		"\u016a\u0000\u0000\u0a34\u0a35\u0005\u0086\u0000\u0000\u0a35\u0a3a\u0003"+
		"\u00c6c\u0000\u0a36\u0a37\u0005\u001d\u0000\u0000\u0a37\u0a39\u0003\u00c6"+
		"c\u0000\u0a38\u0a36\u0001\u0000\u0000\u0000\u0a39\u0a3c\u0001\u0000\u0000"+
		"\u0000\u0a3a\u0a38\u0001\u0000\u0000\u0000\u0a3a\u0a3b\u0001\u0000\u0000"+
		"\u0000\u0a3b\u00c5\u0001\u0000\u0000\u0000\u0a3c\u0a3a\u0001\u0000\u0000"+
		"\u0000\u0a3d\u0a3f\u0003\u008cF\u0000\u0a3e\u0a40\u0003\u00c8d\u0000\u0a3f"+
		"\u0a3e\u0001\u0000\u0000\u0000\u0a3f\u0a40\u0001\u0000\u0000\u0000\u0a40"+
		"\u0a43\u0001\u0000\u0000\u0000\u0a41\u0a42\u0005\u00c5\u0000\u0000\u0a42"+
		"\u0a44\u0003\u00ecv\u0000\u0a43\u0a41\u0001\u0000\u0000\u0000\u0a43\u0a44"+
		"\u0001\u0000\u0000\u0000\u0a44\u00c7\u0001\u0000\u0000\u0000\u0a45\u0a4a"+
		"\u0005\u016b\u0000\u0000\u0a46\u0a4a\u0005\u016c\u0000\u0000\u0a47\u0a48"+
		"\u0005Z\u0000\u0000\u0a48\u0a4a\u0003\u0096K\u0000\u0a49\u0a45\u0001\u0000"+
		"\u0000\u0000\u0a49\u0a46\u0001\u0000\u0000\u0000\u0a49\u0a47\u0001\u0000"+
		"\u0000\u0000\u0a4a\u00c9\u0001\u0000\u0000\u0000\u0a4b\u0a4d\u0007\u001f"+
		"\u0000\u0000\u0a4c\u0a4b\u0001\u0000\u0000\u0000\u0a4c\u0a4d\u0001\u0000"+
		"\u0000\u0000\u0a4d\u0a4e\u0001\u0000\u0000\u0000\u0a4e\u0a6a\u0007-\u0000"+
		"\u0000\u0a4f\u0a6a\u0005\u019a\u0000\u0000\u0a50\u0a6a\u0005\u019b\u0000"+
		"\u0000\u0a51\u0a6a\u0005\u019c\u0000\u0000\u0a52\u0a6a\u0003\u00ccf\u0000"+
		"\u0a53\u0a6a\u0003\u00ceg\u0000\u0a54\u0a6a\u0005t\u0000\u0000\u0a55\u0a56"+
		"\u0005\u0130\u0000\u0000\u0a56\u0a6a\u0003\u0108\u0084\u0000\u0a57\u0a58"+
		"\u0007.\u0000\u0000\u0a58\u0a59\u0003\u0108\u0084\u0000\u0a59\u0a5a\u0005"+
		"\u0154\u0000\u0000\u0a5a\u0a6a\u0001\u0000\u0000\u0000\u0a5b\u0a5f\u0007"+
		"%\u0000\u0000\u0a5c\u0a5d\u0003\u00fc~\u0000\u0a5d\u0a5e\u0003\u00f6{"+
		"\u0000\u0a5e\u0a60\u0001\u0000\u0000\u0000\u0a5f\u0a5c\u0001\u0000\u0000"+
		"\u0000\u0a5f\u0a60\u0001\u0000\u0000\u0000\u0a60\u0a62\u0001\u0000\u0000"+
		"\u0000\u0a61\u0a63\u0003\u0108\u0084\u0000\u0a62\u0a61\u0001\u0000\u0000"+
		"\u0000\u0a62\u0a63\u0001\u0000\u0000\u0000\u0a63\u0a6a\u0001\u0000\u0000"+
		"\u0000\u0a64\u0a6a\u0003\u00d6k\u0000\u0a65\u0a6a\u0003\u00d4j\u0000\u0a66"+
		"\u0a6a\u0005\u019f\u0000\u0000\u0a67\u0a6a\u0005\u01a0\u0000\u0000\u0a68"+
		"\u0a6a\u0003\u0108\u0084\u0000\u0a69\u0a4c\u0001\u0000\u0000\u0000\u0a69"+
		"\u0a4f\u0001\u0000\u0000\u0000\u0a69\u0a50\u0001\u0000\u0000\u0000\u0a69"+
		"\u0a51\u0001\u0000\u0000\u0000\u0a69\u0a52\u0001\u0000\u0000\u0000\u0a69"+
		"\u0a53\u0001\u0000\u0000\u0000\u0a69\u0a54\u0001\u0000\u0000\u0000\u0a69"+
		"\u0a55\u0001\u0000\u0000\u0000\u0a69\u0a57\u0001\u0000\u0000\u0000\u0a69"+
		"\u0a5b\u0001\u0000\u0000\u0000\u0a69\u0a64\u0001\u0000\u0000\u0000\u0a69"+
		"\u0a65\u0001\u0000\u0000\u0000\u0a69\u0a66\u0001\u0000\u0000\u0000\u0a69"+
		"\u0a67\u0001\u0000\u0000\u0000\u0a69\u0a68\u0001\u0000\u0000\u0000\u0a6a"+
		"\u00cb\u0001\u0000\u0000\u0000\u0a6b\u0a6c\u0007/\u0000\u0000\u0a6c\u00cd"+
		"\u0001\u0000\u0000\u0000\u0a6d\u0a6e\u00070\u0000\u0000\u0a6e\u00cf\u0001"+
		"\u0000\u0000\u0000\u0a6f\u0a71\u0003\u00d2i\u0000\u0a70\u0a72\u0003\u00a4"+
		"R\u0000\u0a71\u0a70\u0001\u0000\u0000\u0000\u0a71\u0a72\u0001\u0000\u0000"+
		"\u0000\u0a72\u0a78\u0001\u0000\u0000\u0000\u0a73\u0a74\u0005\u0018\u0000"+
		"\u0000\u0a74\u0a76\u0003\u00d2i\u0000\u0a75\u0a77\u0003\u00a4R\u0000\u0a76"+
		"\u0a75\u0001\u0000\u0000\u0000\u0a76\u0a77\u0001\u0000\u0000\u0000\u0a77"+
		"\u0a79\u0001\u0000\u0000\u0000\u0a78\u0a73\u0001\u0000\u0000\u0000\u0a78"+
		"\u0a79\u0001\u0000\u0000\u0000\u0a79\u00d1\u0001\u0000\u0000\u0000\u0a7a"+
		"\u0a7b\u00071\u0000\u0000\u0a7b\u00d3\u0001\u0000\u0000\u0000\u0a7c\u0a7d"+
		"\u0005\u018a\u0000\u0000\u0a7d\u0a83\u0005\u0011\u0000\u0000\u0a7e\u0a84"+
		"\u0003\u0086C\u0000\u0a7f\u0a80\u0005\u0011\u0000\u0000\u0a80\u0a81\u0003"+
		"J%\u0000\u0a81\u0a82\u0005\u0012\u0000\u0000\u0a82\u0a84\u0001\u0000\u0000"+
		"\u0000\u0a83\u0a7e\u0001\u0000\u0000\u0000\u0a83\u0a7f\u0001\u0000\u0000"+
		"\u0000\u0a83\u0a84\u0001\u0000\u0000\u0000\u0a84\u0a86\u0001\u0000\u0000"+
		"\u0000\u0a85\u0a87\u0003\u00eew\u0000\u0a86\u0a85\u0001\u0000\u0000\u0000"+
		"\u0a86\u0a87\u0001\u0000\u0000\u0000\u0a87\u0a89\u0001\u0000\u0000\u0000"+
		"\u0a88\u0a8a\u0003\u00f0x\u0000\u0a89\u0a88\u0001\u0000\u0000\u0000\u0a89"+
		"\u0a8a\u0001\u0000\u0000\u0000\u0a8a\u0a8b\u0001\u0000\u0000\u0000\u0a8b"+
		"\u0a8c\u0005\u0012\u0000\u0000\u0a8c\u00d5\u0001\u0000\u0000\u0000\u0a8d"+
		"\u0a8e\u0005\u018b\u0000\u0000\u0a8e\u0a90\u0005\u0011\u0000\u0000\u0a8f"+
		"\u0a91\u0003\u00d8l\u0000\u0a90\u0a8f\u0001\u0000\u0000\u0000\u0a90\u0a91"+
		"\u0001\u0000\u0000\u0000\u0a91\u0a93\u0001\u0000\u0000\u0000\u0a92\u0a94"+
		"\u0003\u00f0x\u0000\u0a93\u0a92\u0001\u0000\u0000\u0000\u0a93\u0a94\u0001"+
		"\u0000\u0000\u0000\u0a94\u0a96\u0001\u0000\u0000\u0000\u0a95\u0a97\u0003"+
		"\u00f8|\u0000\u0a96\u0a95\u0001\u0000\u0000\u0000\u0a96\u0a97\u0001\u0000"+
		"\u0000\u0000\u0a97\u0a99\u0001\u0000\u0000\u0000\u0a98\u0a9a\u0003\u00ee"+
		"w\u0000\u0a99\u0a98\u0001\u0000\u0000\u0000\u0a99\u0a9a\u0001\u0000\u0000"+
		"\u0000\u0a9a\u0a9b\u0001\u0000\u0000\u0000\u0a9b\u0a9c\u0005\u0012\u0000"+
		"\u0000\u0a9c\u00d7\u0001\u0000\u0000\u0000\u0a9d\u0aa2\u0003\u00dam\u0000"+
		"\u0a9e\u0a9f\u0005\u001d\u0000\u0000\u0a9f\u0aa1\u0003\u00dam\u0000\u0aa0"+
		"\u0a9e\u0001\u0000\u0000\u0000\u0aa1\u0aa4\u0001\u0000\u0000\u0000\u0aa2"+
		"\u0aa0\u0001\u0000\u0000\u0000\u0aa2\u0aa3\u0001\u0000\u0000\u0000\u0aa3"+
		"\u00d9\u0001\u0000\u0000\u0000\u0aa4\u0aa2\u0001\u0000\u0000\u0000\u0aa5"+
		"\u0aa6\u0003\u00dcn\u0000\u0aa6\u0aa7\u0005\u018c\u0000\u0000\u0aa7\u0aa8"+
		"\u0003\u008cF\u0000\u0aa8\u0ab1\u0001\u0000\u0000\u0000\u0aa9\u0aab\u0005"+
		"o\u0000\u0000\u0aaa\u0aa9\u0001\u0000\u0000\u0000\u0aaa\u0aab\u0001\u0000"+
		"\u0000\u0000\u0aab\u0aac\u0001\u0000\u0000\u0000\u0aac\u0aad\u0003\u00dc"+
		"n\u0000\u0aad\u0aae\u0005\u008e\u0000\u0000\u0aae\u0aaf\u0003\u008cF\u0000"+
		"\u0aaf\u0ab1\u0001\u0000\u0000\u0000\u0ab0\u0aa5\u0001\u0000\u0000\u0000"+
		"\u0ab0\u0aaa\u0001\u0000\u0000\u0000\u0ab1\u00db\u0001\u0000\u0000\u0000"+
		"\u0ab2\u0ab6\u0005q\u0000\u0000\u0ab3\u0ab6\u0003\u0108\u0084\u0000\u0ab4"+
		"\u0ab6\u0003\u0106\u0083\u0000\u0ab5\u0ab2\u0001\u0000\u0000\u0000\u0ab5"+
		"\u0ab3\u0001\u0000\u0000\u0000\u0ab5\u0ab4\u0001\u0000\u0000\u0000\u0ab6"+
		"\u00dd\u0001\u0000\u0000\u0000\u0ab7\u0ab8\u0005\u0011\u0000\u0000\u0ab8"+
		"\u0abd\u0003\u00e0p\u0000\u0ab9\u0aba\u0005\u001d\u0000\u0000\u0aba\u0abc"+
		"\u0003\u00e0p\u0000\u0abb\u0ab9\u0001\u0000\u0000\u0000\u0abc\u0abf\u0001"+
		"\u0000\u0000\u0000\u0abd\u0abb\u0001\u0000\u0000\u0000\u0abd\u0abe\u0001"+
		"\u0000\u0000\u0000\u0abe\u0ac0\u0001\u0000\u0000\u0000\u0abf\u0abd\u0001"+
		"\u0000\u0000\u0000\u0ac0\u0ac1\u0005\u0012\u0000\u0000\u0ac1\u00df\u0001"+
		"\u0000\u0000\u0000\u0ac2\u0ac7\u0003\u0106\u0083\u0000\u0ac3\u0ac4\u0005"+
		"\u00b7\u0000\u0000\u0ac4\u0ac6\u0003\u0106\u0083\u0000\u0ac5\u0ac3\u0001"+
		"\u0000\u0000\u0000\u0ac6\u0ac9\u0001\u0000\u0000\u0000\u0ac7\u0ac5\u0001"+
		"\u0000\u0000\u0000\u0ac7\u0ac8\u0001\u0000\u0000\u0000\u0ac8\u00e1\u0001"+
		"\u0000\u0000\u0000\u0ac9\u0ac7\u0001\u0000\u0000\u0000\u0aca\u0acf\u0003"+
		"\u0106\u0083\u0000\u0acb\u0acc\u0005\u00b7\u0000\u0000\u0acc\u0ace\u0003"+
		"\u0106\u0083\u0000\u0acd\u0acb\u0001\u0000\u0000\u0000\u0ace\u0ad1\u0001"+
		"\u0000\u0000\u0000\u0acf\u0acd\u0001\u0000\u0000\u0000\u0acf\u0ad0\u0001"+
		"\u0000\u0000\u0000\u0ad0\u00e3\u0001\u0000\u0000\u0000\u0ad1\u0acf\u0001"+
		"\u0000\u0000\u0000\u0ad2\u0add\u0005\u01a0\u0000\u0000\u0ad3\u0add\u0005"+
		"\u019f\u0000\u0000\u0ad4\u0ad9\u0003\u0106\u0083\u0000\u0ad5\u0ad6\u0005"+
		"\u00b7\u0000\u0000\u0ad6\u0ad8\u0003\u0106\u0083\u0000\u0ad7\u0ad5\u0001"+
		"\u0000\u0000\u0000\u0ad8\u0adb\u0001\u0000\u0000\u0000\u0ad9\u0ad7\u0001"+
		"\u0000\u0000\u0000\u0ad9\u0ada\u0001\u0000\u0000\u0000\u0ada\u0add\u0001"+
		"\u0000\u0000\u0000\u0adb\u0ad9\u0001\u0000\u0000\u0000\u0adc\u0ad2\u0001"+
		"\u0000\u0000\u0000\u0adc\u0ad3\u0001\u0000\u0000\u0000\u0adc\u0ad4\u0001"+
		"\u0000\u0000\u0000\u0add\u00e5\u0001\u0000\u0000\u0000\u0ade\u0ae7\u0005"+
		"\u0118\u0000\u0000\u0adf\u0ae8\u0003\u008cF\u0000\u0ae0\u0ae2\u0003\u008c"+
		"F\u0000\u0ae1\u0ae0\u0001\u0000\u0000\u0000\u0ae1\u0ae2\u0001\u0000\u0000"+
		"\u0000\u0ae2\u0ae3\u0001\u0000\u0000\u0000\u0ae3\u0ae5\u0005\u018c\u0000"+
		"\u0000\u0ae4\u0ae6\u0003\u008cF\u0000\u0ae5\u0ae4\u0001\u0000\u0000\u0000"+
		"\u0ae5\u0ae6\u0001\u0000\u0000\u0000\u0ae6\u0ae8\u0001\u0000\u0000\u0000"+
		"\u0ae7\u0adf\u0001\u0000\u0000\u0000\u0ae7\u0ae1\u0001\u0000\u0000\u0000"+
		"\u0ae7\u0ae8\u0001\u0000\u0000\u0000\u0ae8\u0ae9\u0001\u0000\u0000\u0000"+
		"\u0ae9\u0aea\u0005\u0119\u0000\u0000\u0aea\u00e7\u0001\u0000\u0000\u0000"+
		"\u0aeb\u0aec\b2\u0000\u0000\u0aec\u00e9\u0001\u0000\u0000\u0000\u0aed"+
		"\u0aee\u00073\u0000\u0000\u0aee\u00eb\u0001\u0000\u0000\u0000\u0aef\u0af0"+
		"\u00074\u0000\u0000\u0af0\u00ed\u0001\u0000\u0000\u0000\u0af1\u0af2\u0005"+
		"&\u0000\u0000\u0af2\u0af3\u0005)\u0000\u0000\u0af3\u00ef\u0001\u0000\u0000"+
		"\u0000\u0af4\u0af5\u00075\u0000\u0000\u0af5\u0af6\u0005b\u0000\u0000\u0af6"+
		"\u0af7\u0005q\u0000\u0000\u0af7\u00f1\u0001\u0000\u0000\u0000\u0af8\u0af9"+
		"\u00076\u0000\u0000\u0af9\u0afa\u0005\u00c5\u0000\u0000\u0afa\u00f3\u0001"+
		"\u0000\u0000\u0000\u0afb\u0afc\u00077\u0000\u0000\u0afc\u00f5\u0001\u0000"+
		"\u0000\u0000\u0afd\u0afe\u0005\u0131\u0000\u0000\u0afe\u0aff\u0005\u0191"+
		"\u0000\u0000\u0aff\u00f7\u0001\u0000\u0000\u0000\u0b00\u0b02\u0003\u00fc"+
		"~\u0000\u0b01\u0b00\u0001\u0000\u0000\u0000\u0b01\u0b02\u0001\u0000\u0000"+
		"\u0000\u0b02\u0b03\u0001\u0000\u0000\u0000\u0b03\u0b04\u0005k\u0000\u0000"+
		"\u0b04\u0b05\u0005\u0192\u0000\u0000\u0b05\u00f9\u0001\u0000\u0000\u0000"+
		"\u0b06\u0b07\u0005V\u0000\u0000\u0b07\u0b08\u0005\u0164\u0000\u0000\u0b08"+
		"\u00fb\u0001\u0000\u0000\u0000\u0b09\u0b0a\u00078\u0000\u0000\u0b0a\u00fd"+
		"\u0001\u0000\u0000\u0000\u0b0b\u0b0e\u0003\u0106\u0083\u0000\u0b0c\u0b0e"+
		"\u0003\u0108\u0084\u0000\u0b0d\u0b0b\u0001\u0000\u0000\u0000\u0b0d\u0b0c"+
		"\u0001\u0000\u0000\u0000\u0b0e\u00ff\u0001\u0000\u0000\u0000\u0b0f\u0b10"+
		"\u0005\u0011\u0000\u0000\u0b10\u0b15\u0003\u0106\u0083\u0000\u0b11\u0b12"+
		"\u0005\u001d\u0000\u0000\u0b12\u0b14\u0003\u0106\u0083\u0000\u0b13\u0b11"+
		"\u0001\u0000\u0000\u0000\u0b14\u0b17\u0001\u0000\u0000\u0000\u0b15\u0b13"+
		"\u0001\u0000\u0000\u0000\u0b15\u0b16\u0001\u0000\u0000\u0000\u0b16\u0b18"+
		"\u0001\u0000\u0000\u0000\u0b17\u0b15\u0001\u0000\u0000\u0000\u0b18\u0b19"+
		"\u0005\u0012\u0000\u0000\u0b19\u0101\u0001\u0000\u0000\u0000\u0b1a\u0b1f"+
		"\u0003\u00e4r\u0000\u0b1b\u0b1c\u0005\u001d\u0000\u0000\u0b1c\u0b1e\u0003"+
		"\u00e4r\u0000\u0b1d\u0b1b\u0001\u0000\u0000\u0000\u0b1e\u0b21\u0001\u0000"+
		"\u0000\u0000\u0b1f\u0b1d\u0001\u0000\u0000\u0000\u0b1f\u0b20\u0001\u0000"+
		"\u0000\u0000\u0b20\u0103\u0001\u0000\u0000\u0000\u0b21\u0b1f\u0001\u0000"+
		"\u0000\u0000\u0b22\u0b23\u0005\u0011\u0000\u0000\u0b23\u0b28\u0003\u00e4"+
		"r\u0000\u0b24\u0b25\u0005\u001d\u0000\u0000\u0b25\u0b27\u0003\u00e4r\u0000"+
		"\u0b26\u0b24\u0001\u0000\u0000\u0000\u0b27\u0b2a\u0001\u0000\u0000\u0000"+
		"\u0b28\u0b26\u0001\u0000\u0000\u0000\u0b28\u0b29\u0001\u0000\u0000\u0000"+
		"\u0b29\u0b2b\u0001\u0000\u0000\u0000\u0b2a\u0b28\u0001\u0000\u0000\u0000"+
		"\u0b2b\u0b2c\u0005\u0012\u0000\u0000\u0b2c\u0105\u0001\u0000\u0000\u0000"+
		"\u0b2d\u0b39\u0005\u0197\u0000\u0000\u0b2e\u0b39\u0005\u0198\u0000\u0000"+
		"\u0b2f\u0b39\u0005\u0196\u0000\u0000\u0b30\u0b32\u0005\u0193\u0000\u0000"+
		"\u0b31\u0b33\u0003\u010a\u0085\u0000\u0b32\u0b31\u0001\u0000\u0000\u0000"+
		"\u0b32\u0b33\u0001\u0000\u0000\u0000\u0b33\u0b39\u0001\u0000\u0000\u0000"+
		"\u0b34\u0b39\u0005\u0199\u0000\u0000\u0b35\u0b39\u0005\u01a0\u0000\u0000"+
		"\u0b36\u0b39\u0005\u019f\u0000\u0000\u0b37\u0b39\u0003\u00e8t\u0000\u0b38"+
		"\u0b2d\u0001\u0000\u0000\u0000\u0b38\u0b2e\u0001\u0000\u0000\u0000\u0b38"+
		"\u0b2f\u0001\u0000\u0000\u0000\u0b38\u0b30\u0001\u0000\u0000\u0000\u0b38"+
		"\u0b34\u0001\u0000\u0000\u0000\u0b38\u0b35\u0001\u0000\u0000\u0000\u0b38"+
		"\u0b36\u0001\u0000\u0000\u0000\u0b38\u0b37\u0001\u0000\u0000\u0000\u0b39"+
		"\u0107\u0001\u0000\u0000\u0000\u0b3a\u0b3c\u0005\u0196\u0000\u0000\u0b3b"+
		"\u0b3a\u0001\u0000\u0000\u0000\u0b3c\u0b3d\u0001\u0000\u0000\u0000\u0b3d"+
		"\u0b3b\u0001\u0000\u0000\u0000\u0b3d\u0b3e\u0001\u0000\u0000\u0000\u0b3e"+
		"\u0b51\u0001\u0000\u0000\u0000\u0b3f\u0b43\u0005\u0194\u0000\u0000\u0b40"+
		"\u0b42\u0005\u0196\u0000\u0000\u0b41\u0b40\u0001\u0000\u0000\u0000\u0b42"+
		"\u0b45\u0001\u0000\u0000\u0000\u0b43\u0b41\u0001\u0000\u0000\u0000\u0b43"+
		"\u0b44\u0001\u0000\u0000\u0000\u0b44\u0b47\u0001\u0000\u0000\u0000\u0b45"+
		"\u0b43\u0001\u0000\u0000\u0000\u0b46\u0b48\u0003\u010a\u0085\u0000\u0b47"+
		"\u0b46\u0001\u0000\u0000\u0000\u0b47\u0b48\u0001\u0000\u0000\u0000\u0b48"+
		"\u0b51\u0001\u0000\u0000\u0000\u0b49\u0b4d\u0005\u0195\u0000\u0000\u0b4a"+
		"\u0b4c\u0005\u0196\u0000\u0000\u0b4b\u0b4a\u0001\u0000\u0000\u0000\u0b4c"+
		"\u0b4f\u0001\u0000\u0000\u0000\u0b4d\u0b4b\u0001\u0000\u0000\u0000\u0b4d"+
		"\u0b4e\u0001\u0000\u0000\u0000\u0b4e\u0b51\u0001\u0000\u0000\u0000\u0b4f"+
		"\u0b4d\u0001\u0000\u0000\u0000\u0b50\u0b3b\u0001\u0000\u0000\u0000\u0b50"+
		"\u0b3f\u0001\u0000\u0000\u0000\u0b50\u0b49\u0001\u0000\u0000\u0000\u0b51"+
		"\u0109\u0001\u0000\u0000\u0000\u0b52\u0b53\u0005\u018d\u0000\u0000\u0b53"+
		"\u0b54\u0005\u0196\u0000\u0000\u0b54\u010b\u0001\u0000\u0000\u0000\u01c6"+
		"\u010d\u0111\u0115\u011b\u0120\u0124\u012c\u012f\u0133\u013d\u0143\u014c"+
		"\u0151\u0155\u015a\u015e\u0165\u0169\u016c\u0174\u0178\u017a\u017e\u0181"+
		"\u0189\u018e\u0193\u0197\u019b\u019f\u01a3\u01a7\u01ab\u01af\u01b3\u01b7"+
		"\u01be\u01c1\u01c7\u01cc\u01cf\u01d3\u01d7\u01e0\u01e4\u01f5\u0204\u0208"+
		"\u020b\u020e\u0211\u0215\u021e\u0225\u022b\u0231\u0234\u0236\u0239\u023f"+
		"\u0248\u024c\u024f\u0252\u0257\u0259\u025d\u025f\u0264\u0266\u0269\u026d"+
		"\u0271\u0275\u027c\u0283\u0285\u028c\u0290\u0297\u029d\u02a0\u02a6\u02aa"+
		"\u02ae\u02b2\u02b9\u02bd\u02c4\u02c8\u02cb\u02d3\u02de\u02e3\u02e8\u02ed"+
		"\u02f0\u02f3\u02f6\u02fa\u02fe\u0306\u030b\u0312\u0319\u031b\u0321\u0326"+
		"\u032d\u0337\u033b\u0344\u0346\u034c\u0356\u0360\u0364\u0366\u036a\u036f"+
		"\u0371\u0375\u037c\u0386\u038b\u038f\u0392\u03a0\u03a6\u03ae\u03b8\u03bc"+
		"\u03be\u03c1\u03c6\u03cb\u03ce\u03d6\u03d9\u03dc\u03df\u03e2\u03e5\u03e8"+
		"\u03eb\u03f0\u03f3\u03f9\u03fc\u03ff\u0405\u040a\u040d\u0417\u0419\u0424"+
		"\u0428\u042a\u042d\u0432\u0436\u0439\u043d\u0445\u044a\u0451\u0457\u045a"+
		"\u045f\u0462\u046b\u046f\u0472\u0477\u047a\u047f\u0483\u0486\u0489\u0491"+
		"\u0496\u0499\u049c\u049f\u04a2\u04a5\u04a9\u04b5\u04bd\u04c3\u04c6\u04c8"+
		"\u04cc\u04d4\u04d6\u04dd\u04e5\u04e9\u04ee\u04f1\u04f8\u04fc\u04fe\u0501"+
		"\u0505\u0508\u050b\u050e\u0511\u0514\u051a\u0520\u0524\u052c\u0535\u0537"+
		"\u053c\u053e\u0542\u0547\u054b\u054d\u0557\u0559\u0568\u0575\u0578\u057c"+
		"\u057e\u0580\u0589\u058f\u0598\u05a1\u05a9\u05af\u05b5\u05b7\u05bc\u05c3"+
		"\u05c7\u05ca\u05d2\u05d5\u05dc\u05e4\u05e7\u05f0\u05f3\u05f6\u05fb\u0601"+
		"\u0605\u0608\u060a\u060f\u0612\u0616\u061b\u061f\u0624\u0629\u062d\u0630"+
		"\u0639\u063f\u064f\u0655\u065b\u0660\u0664\u0667\u066c\u0671\u0676\u0683"+
		"\u0688\u068f\u06a5\u06aa\u06b5\u06c3\u06cb\u06d3\u06db\u06df\u06e6\u06eb"+
		"\u06ee\u06f5\u0711\u071c\u071e\u072c\u0733\u0746\u0749\u0760\u0764\u0769"+
		"\u0770\u0773\u0795\u079b\u07a8\u07ac\u07ae\u07b6\u07bd\u07c1\u07cd\u07d1"+
		"\u07d5\u07de\u07e2\u07e7\u07ec\u07f3\u07f7\u07fb\u0802\u0809\u080d\u0810"+
		"\u0814\u0818\u081e\u0822\u0827\u082b\u0832\u0839\u083b\u0843\u0846\u0850"+
		"\u085f\u0867\u086e\u0873\u0878\u087a\u087c\u0888\u088e\u0892\u0896\u089a"+
		"\u08a2\u08a5\u08a9\u08ad\u08b4\u08b8\u08bc\u08c5\u08c8\u08ca\u08ce\u08d5"+
		"\u08d7\u08e1\u08ee\u08f7\u08fd\u0900\u0903\u090f\u0917\u091a\u091e\u0921"+
		"\u092a\u0934\u093f\u094c\u0951\u0959\u0964\u0977\u0984\u098b\u098d\u09a6"+
		"\u09aa\u09ad\u09b4\u09b8\u09bc\u09c1\u09c5\u09c8\u09ca\u09ce\u09d1\u09d5"+
		"\u09d8\u09db\u09e0\u09f4\u09fa\u09fd\u0a00\u0a03\u0a07\u0a14\u0a1d\u0a1f"+
		"\u0a24\u0a29\u0a2d\u0a31\u0a3a\u0a3f\u0a43\u0a49\u0a4c\u0a5f\u0a62\u0a69"+
		"\u0a71\u0a76\u0a78\u0a83\u0a86\u0a89\u0a90\u0a93\u0a96\u0a99\u0aa2\u0aaa"+
		"\u0ab0\u0ab5\u0abd\u0ac7\u0acf\u0ad9\u0adc\u0ae1\u0ae5\u0ae7\u0b01\u0b0d"+
		"\u0b15\u0b1f\u0b28\u0b32\u0b38\u0b3d\u0b43\u0b47\u0b4d\u0b50";
	public static final String _serializedATN = Utils.join(
		new String[] {
			_serializedATNSegment0,
			_serializedATNSegment1
		},
		""
	);
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}