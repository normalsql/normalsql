// Copyright 2010-2023 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

/*
 NormalSQL.g4, SQL DML grammar for ANTLR 4.x

 Style:

 Child rules are indented when its (mostly) only used by a parent rule.

   Heuristics:

      1. Use alt labels, subrules, or both, to ease parse tree navigation.
         Versus using element labels.

      2. Per DRY, create subrule for 3 (sometimes 2) or more copypastas.
         Except when doing so conflicts with #1 or #3.

      3. Inline lists to ease processing parse trees, eg comma separated
         items or terms?

Labels for rule alts use UPPERCASE for SQL keywords, MixedCase for other rules. See rules
'subterm' and 'predicate'. TODO Maybe use underscores as separator.

*/

grammar NormalSQL;

options { caseInsensitive=true; }

@parser::members
{

// TODO move to helper class, rename to "morphKeywords()" or something
void fixID()
{
    var t1 = (CommonToken) getCurrentToken();
    if( t1.getType() == ID ) return;

    var text = t1.getText();
    if( Reserved.isWord( text ) )
    {
        if( Reserved.keywords.contains( text ) )
        {
            t1.setType( RESERVED );
        }
    }
}

}

// convenience for debugging
aaa1 : script ;

script : statement? ( ';' statement? )* EOF ;

statement
    : explain?
    ( alter
    | ( 'ANALYZE' | 'ANALYSE' ) qname?
    | 'ATTACH' 'DATABASE'? term 'AS' ( qname | 'NULL' )
    | 'BEGIN' ( 'DEFERRED' | 'EXCLUSIVE' | 'IMMEDIATE' )? 'TRANSACTION'? name?
    | 'COMMIT' 'TRANSACTION'?
    | createIndex
    | createTable
    | createTrigger
    | createView
    | createVirtualTable
    | delete
    | 'DETACH' 'DATABASE'? term
    | drop
    | 'END' 'TRANSACTION'?
    | insert
    | merge
    | pragma
    | query
    | 'REINDEX' qname?
    | 'RELEASE' 'SAVEPOINT'? qname
    | 'RESET' qname
    | 'ROLLBACK' 'TRANSACTION'? (( 'TO' 'SAVEPOINT'? )? name )?
//    | 'ROLLBACK' 'TRANSACTION'? 'TO'? name?
    | 'SAVEPOINT' qname
    | set
    | update
    | 'VACUUM' qname? ( 'INTO' term )?
    )
    ;

    pragma : 'PRAGMA' qname ( '=' pragmaValue | '(' pragmaValue ')' )? ;

        pragmaValue : signedNumber | name | string ;

explain
    : 'EXPLAIN' 'ANALYZE'? 'VERBOSE'? ( '(' option ( ',' option )* ')' )?
    | 'EXPLAIN' 'QUERY' 'PLAN'
    ;

    option
        : 'ANALYZE' boolean?
        | 'VERBOSE' boolean?
        | 'COSTS' boolean?
        | 'SETTINGS' boolean?
        | 'BUFFERS' boolean?
        | 'WAL' boolean?
        | 'TIMING' boolean?
        | 'SUMMARY' boolean?
        | 'FORMAT' ( 'TEXT' | 'XML' | 'JSON' | 'YAML' )
        ;

alter
    : 'ALTER' 'TABLE' qname
      ( 'RENAME' ( 'COLUMN'? qname )? 'TO' qname
      | 'ADD' 'COLUMN'? columnDef
      | 'DROP' 'COLUMN'? qname
      )
    ;

// TODO so much more to add
set
    : 'SET' ( 'LOCAL' | 'GLOBAL' )? ( qname | VARIABLE ) ( '=' | 'TO' ) terms ;

//reset
//    : 'RESET' qname;

drop
    : 'DROP' dropsicle ( 'IF' 'EXISTS' )? qnames0 ( 'CASCADE' | 'RESTRICT' )?
//    | 'DROP' 'TEMPORARY'? 'FUNCTION' ifExists? table ( '(' ( type ( ',' type )* )? ')' )?
    ;

    // TODO new name. this copypasta from Postgres probably needs to be validated.
    dropsicle
        : 'ACCESS METHOD'
        | 'COLLATION'
        | 'CONVERSION'
        | 'EVENT' 'TRIGGER'
        | 'EXTENSION'
        | 'FOREIGN' 'DATA' 'WRAPPER'
        | 'FOREIGN' 'TABLE'
        | 'INDEX'
        | 'MATERIALIZED' 'VIEW'
        | 'PROCEDURAL'? 'LANGUAGE'
        | 'PUBLICATION'
        | 'ROLE'
        | 'SCHEMA'
        | 'SEQUENCE'
        | 'SERVER'
        | 'STATISTICS'
        | 'TABLE'
        | 'TEXT' 'SEARCH' ( 'CONFIGURATION' | 'DICTIONARY' | 'PARSER' | 'TEMPLATE' | 'VIEW' )
        | 'TRIGGER'
        | 'VIEW'
        ;

createTable
    : 'CREATE' ( 'CACHED' | 'MEMORY' )? ( 'LOCAL' | 'GLOBAL' )? temporary? 'UNLOGGED'?
      'TABLE' ifNotExists? qname
      ( '(' columnDef ( ',' columnDef )* ( ',' tableStuff )* ')' ( 'WITHOUT' ID )?
      | 'AS' query ( 'WITH' 'NO'? 'DATA' )?
      ) // TODO (COMMENT string)? (WITH properties)?
      // SQLite
      'STRICT'?
      ;

// https://www.sqlite.org/vtab.html
createVirtualTable
    : 'CREATE' 'VIRTUAL' 'TABLE' ifNotExists? qname
      'USING' qname ( '(' ( moduleArgument ( ',' moduleArgument?  )*  )? ')' )?
    ;

    moduleArgument
        : ( name | literal | type )+
        | ( name | literal )+ ( '=' ( name | literal )* )?
//        | compare
//        | COMPARE
//        | assign
//        | ASSIGN
//        | EQ
        ;

createTrigger
     : 'CREATE' temporary? 'TRIGGER' ifNotExists? qname ( 'BEFORE' | 'AFTER' | 'INSTEAD' 'OF' )?
       ( 'DELETE' | 'INSERT' | 'UPDATE' ( 'OF' qnames0 )? ) 'ON' qname
       ( 'FOR' 'EACH' 'ROW' )? ( 'WHEN' term )?
       'BEGIN' (( update | insert | delete | query ) ';' )+
       // TODO remove '?' after testing!!
       'END'?
     ;

temporary : 'TEMP' | 'TEMPORARY' ;

createView
      : 'CREATE' temporary?
        // Postgres?
        ( 'OR' 'REPLACE' )?
        'VIEW' ifNotExists? qname qnames? 'AS' query ;

createIndex
      : 'CREATE' 'UNIQUE'? 'INDEX' ifNotExists? qname 'ON' qname indexedColumns where? ;
//    : 'CREATE' (OR REPLACE)? TEMPORARY? FUNCTION tableRef '(' (sqlParameterDeclaration (',' sqlParameterDeclaration)*)? ')' RETURNS type (COMMENT string)? routineCharacteristics routineBody
//    | 'CREATE' (OR REPLACE)? VIEW tableRef (SECURITY (DEFINER | INVOKER))? AS query
//    | 'CREATE' MATERIALIZED VIEW (IF NOT EXISTS)? tableRef (COMMENT string)? (WITH properties)? AS (query | '(' query ')' )
//    | 'CREATE' ROLE id (WITH ADMIN grantor)?
//    | 'CREATE' SCHEMA (IF NOT EXISTS)? tableRef (WITH properties)?
//    | 'CREATE' TYPE tableRef AS ( '(' sqlParameterDeclaration (',' sqlParameterDeclaration)* ')' | type)

    indexedColumn
//        : ( qname | term ) sortDir? ;
//        : qname sortDir? ;
        : term sortDir? ;

    indexedColumns
        : '(' indexedColumn ( ',' indexedColumn )* ')' ;

    ifNotExists : 'IF' 'NOT' 'EXISTS' ;

    columnDef
        : name type? columnStuff*  // TODO (COMMENT string)? (WITH properties)?
        ;

    columnStuff
        : ( 'CONSTRAINT' name )?
          ( 'PRIMARY' 'KEY' sortDir? onConflict? 'AUTOINCREMENT'?
          | 'AUTOINCREMENT'
          | 'COUNTER'
          | 'NOT'? 'NULL' onConflict?
          | 'UNIQUE' onConflict?
          | 'CHECK' '(' term ')' ( 'NO' 'INHERIT' )?
          | 'DEFAULT' ( literal | term )
//          | 'COLLATE' name
          | 'COLLATE' type
          | foreignKey
          | ( 'GENERATED' 'ALWAYS' )? 'AS' '(' term ')' ( 'STORED' | 'VIRTUAL' )?
          )
          // SQLite undocumented
          ( 'CONSTRAINT' name )*
        ;

    // SQLite
    tableStuff
        : ( 'CONSTRAINT' name)?
          ( 'CHECK' '(' term ')' onConflict?
          | ( 'FOREIGN' 'KEY' names foreignKey )
          | ( 'PRIMARY' 'KEY' | 'UNIQUE' ) indexedColumns onConflict?
          // SQLite per autoinc.test autoinc-7.1
          | 'PRIMARY' 'KEY' '(' name 'AUTOINCREMENT' ')'
          )+
          // SQLite undocumented
          ( 'CONSTRAINT' name )*
          ;

        onConflict
            : 'ON' 'CONFLICT' afirr
            ;

        foreignKey
            : 'REFERENCES' name names?
              ( 'ON' ( 'DELETE' | 'UPDATE' )
                ( 'SET' ( 'NULL' | 'DEFAULT' )
                | 'CASCADE'
                | 'RESTRICT'
                | 'NO' 'ACTION'
                )
              | 'MATCH' ( 'FULL' |  'PARTIAL' |  'SIMPLE' )
//              | 'MATCH' name
              )*
              ( 'NOT'? 'DEFERRABLE' ( 'INITIALLY' ( 'DEFERRED' | 'IMMEDIATE' ))? )?
            ;

with
    : 'WITH' 'RECURSIVE'? cte ( ',' cte )*
    ;

    cte
        // TODO add rule for column aliases
        : alias ( '(' name ( ',' name )* ')' )? 'AS' ( 'NOT'? 'MATERIALIZED' )?
        '(' query ')'
        ( 'SEARCH' ( 'BREADTH' | 'DEPTH' ) 'FIRST' 'BY' name ( ',' name )* 'SET' name )?
        ( 'CYCLE' name ( ',' name )* 'SET' name ( 'TO' literal 'DEFAULT' literal )? ( 'USING' name )? )?
        ;

delete
    : with? 'DELETE' 'FROM' 'ONLY'? qname ( 'AS' name )?
      indexedBy?
      // TODO need sources which doesn't collide with this rule's orderBy, limit, offset
      // Postgres
//      ( 'USING' sources ( ',' sources )* )?
//      ( 'USING' sources )?
      where? returning? orderBy? limit? offset?
    ;

insert
    : with?
      ( 'INSERT' ( 'OR' afirr )? | 'REPLACE' )
      'INTO' qname ( 'AS' name )? names?
      overriding?
//      ( source upsert* | 'DEFAULT' 'VALUES' )
      ( query upsert* | 'DEFAULT' 'VALUES' )
      returning?
    ;

    overriding
        : 'OVERRIDING' ( 'SYSTEM' | 'USER' ) 'VALUE' ;

    // SQLite
    upsert
        : 'ON' 'CONFLICT' ( terms where? )?
          'DO' ( 'NOTHING' | 'UPDATE' 'SET' setter ( ',' setter )* where? )
        ;

merge
    : with? 'MERGE' 'INTO' 'ONLY'? name ( 'AS'? name )?
//      'USING' 'ONLY'? source 'ON' terms
      'USING' 'ONLY'? query 'ON' terms
      when*
    ;

    when
        : 'WHEN' 'NOT'? 'MATCHED' ( 'AND' subterm )* 'THEN'
          ( 'INSERT' qnames? overriding? ( values | 'DEFAULT' 'VALUES' ) where?
          | 'UPDATE' 'SET' setter ( ',' setter )* ( 'DELETE'? where )?
          | 'DELETE'
          | 'DO' 'NOTHING'
          )
        ;

update
    : with? 'UPDATE' ( 'OR' afirr )?
      qname ( 'AS'? name )? indexedBy?
      'SET' setter ( ',' setter )*
      // TODO need sources which doesn't collide with this rule's orderBy, limit, offset
//      ( 'FROM' sources )?
      where? returning? orderBy? limit? offset?
    ;

    setter
        : ( qname | qnames ) '=' term ;

afirr
    : 'ABORT' | 'FAIL' | 'IGNORE' | 'REPLACE' | 'ROLLBACK' ;

// SQLite
indexedBy
    : 'NOT' 'INDEXED' | 'INDEXED' 'BY' qname ;

returning
    : 'RETURNING' returned ( ',' returned )* ;

    returned
        : '*' | term ( 'AS'? alias )?
        ;

query
    : with? combine
      // Because various dialects order these clauses differently
      // TODO: Allow just one of each clause (somehow)
      ( orderBy | offset | fetch | limit | forUpdate )*
    ;

    combine
        : combine ( 'INTERSECT' | 'MINUS' ) combine
        | combine ( 'UNION' 'ALL'? | 'EXCEPT' ) combine
        | combine 'MULTISET' combine
        | select
        ;

    select
        : 'SELECT' quantifier?
          top?
          ( item ( ',' item )* ','? )?
          into?
          ( 'FROM' tables )?
          where?
          groupBy?
          having?
          windows?
          qualify?
        | '(' query ')'
        | values
        // MySQL table statement
        | 'TABLE' qname
        ;

    tables
        : tables ',' tables
          // TODO validate this. added ON clause to pass sqlite's tkt2141.test
          ( 'ON' term )?
        | tables join tables  ( 'ON' term | 'USING' qnames )?
        | '(' tables ')' tableAlias?

        | ( qname | '(' qname ')' ) tableAlias?
          ( ( 'USE' 'INDEX' names ) // H2
          | indexedBy // SQLite
          )?

        | ( '(' query ')'
          | values
          | tableFunc
          | deltaTable
          | tableCollection
          | 'JSON_TABLE' // TODO
          | xmlTable
          | unnest
          | pivot
          | unpivot
          ) tableAlias?
        ;

        join
            // TODO add 'LATERAL'
            : 'NATURAL'?
              ( 'LEFT' ( 'SEMI' | 'ANTI' | 'OUTER' )?
              | ( 'RIGHT' | 'FULL' ) 'OUTER'?
              | 'INNER'
              | 'CROSS'
              )?
              'JOIN'
            ;

    tableAlias : 'AS'? alias names? ;

    values : 'VALUES' terms ;

    // H2 data change delta table http://h2database.com/html/grammar.html#data_change_delta_table
    // DB2 intermediate result table
    deltaTable : ( 'NEW' | 'OLD' | 'FINAL' ) 'TABLE' '(' ( delete | insert | merge | update ) ')' ;

    // PL/SQL table collection expression
    tableCollection : 'TABLE' '(' term ')' ;

    unnest
        : 'UNNEST' '(' ( array ( ',' array )* )? ')' ( 'WITH' 'ORDINALITY' )? ;

    tableFunc
        // H2 table function http://h2database.com/html/functions.html#table
        : ( 'TABLE' | 'TABLE_DISTINCT' ) '(' tableFuncParam ( ',' tableFuncParam )* ')'
        |  'SYSTEM_RANGE' '(' term ',' term ( ',' term )? ')'
        ;

        tableFuncParam
//            : name ( type | qname | 'NULL' ) '=' subterm ;
            : name ( type | 'NULL' ) '=' subterm ;

    offset
        : 'OFFSET' term rowRows? ;

    limit
        : 'LIMIT' term ( ',' term )? ;

    fetch
        : 'FETCH' ( 'FIRST' | 'NEXT' ) ( term 'PERCENT'? )? rowRows ( 'ONLY' | withTies ) ;

    forUpdate
        : 'FOR'
          ( 'READ' 'ONLY'
          | ( 'NO' 'KEY' )? 'UPDATE'
          | 'KEY'? 'SHARE'
          )
          ( 'OF' qname ( ',' qname )* )?
          ( 'NOWAIT' | 'WAIT' INTEGER | 'SKIP' 'LOCKED' )?
        ;

    quantifier
        : 'DISTINCT' ( 'ON' '(' terms ')' )?
        | 'ALL'
        ;

    top
        : 'TOP' ( INTEGER | FLOAT | '(' term ')' ) 'PERCENT'? withTies? ;

    item
        : term ( 'AS'? alias )?                       # ItemTerm
        | (( qname '.' )? '*' ) ( 'EXCEPT' qnames )?  # ItemTableRef
        ;

    into
        : 'INTO' ( temporary | 'UNLOGGED' )? 'TABLE'? ( qname | VARIABLE ) ( ',' ( qname | VARIABLE ) )* ;

        pivot
            // T-SQL, PL/SQL
            : 'PIVOT'
              '('
                aliasedFunction ( ',' aliasedFunction )*
                'FOR' ( qname | qnames )
                'IN' '(' aliasedTerms ')'
              ')'
            // PL/SQL
            | 'PIVOT' 'XML'
              '('
                aliasedFunction ( ',' aliasedFunction )*
                'FOR' ( qname | qnames )
                'IN' '(' ( query | 'ANY' ) ')'
              ')'
            ;

            aliasedFunction : function ( 'AS'? alias )? ;

        unpivot
            : 'UNPIVOT' (( 'INCLUDE' | 'EXCLUDE' ) 'NULLS' )?
              '('
                ( qname | qnames )
                'FOR' ( qname | qnames )
                'IN' '(' aliasedTerms ')'
              ')'
            ;

    where
        : 'WHERE' ( term | 'CURRENT' 'OF' name ) ;

    groupBy
        : 'GROUP' 'BY' allDistinct? groupByItem ( ',' groupByItem )* ;

        groupByItem
            : terms
            | 'ROLLUP' '(' terms ')' | 'CUBE' '(' terms ')'
            | 'GROUPING' 'SETS' '(' groupByItem ( ',' groupByItem )* ')'
            ;

    having
        : 'HAVING' terms ;

    windows
        : 'WINDOW' windowAlias ( ',' windowAlias )* ;

        windowAlias
            : alias 'AS' window ;

    qualify
        : 'QUALIFY' term ;

    xmlTable
        : 'XMLTABLE'
          '('
            subterm passing?
            'COLUMNS' xmlColumn ( ',' xmlColumn )*
          ')'
        ;

        passing
            : 'PASSING' ( 'BY' ( 'REF' | 'VALUE' ))? aliasedTerms ;

        xmlColumn
           : name ( type ( 'PATH' subterm )? | 'FOR' 'ORDINALITY' )
           ;

terms
    : term ( ',' term )* ;

aliasedTerm
    : term ( 'AS'? alias )? ;

aliasedTerms
    : aliasedTerm ( ',' aliasedTerm )* ;

term
    : term 'OR' term
    | term 'AND' term
    | subterm
    // TODO: '||' as logical OR
    // TODO: 'XOR'
    ;

// TODO verify precedences
// TODO combine operators into lexer rules
subterm
    : ( '+' | '-' | TILDE ) subterm # SubtermUnary
    | ( 'NOT' | '!' ) subterm # SubtermUnary
    | literal  ( '::' type )* # SubtermLiteral
    | case # SubtermCase
    | 'INTERVAL' subterm timeCast # SubtermInterval
    | subterm 'AT' ( 'LOCAL' | timeZone string ) # SubtermAtTZ
    | ( 'NEXT' | 'CURRENT' ) 'VALUE' 'FOR' qname # SubtermSequence
    | array # SubtermArray
    | '(' query ')' # SubtermSubquery
    | function ( '.' name | index | '::' type )* # SubtermFunction
    | { fixID(); } qname # SubtermColumn
    | 'ROW'? '(' terms? ')' ( '.' name )? # SubtermRow
    | subterm 'COLLATE' type # SubtermCollate
    | <assoc=right> subterm '^' subterm # SubtermOperator
    | subterm ( '<<' | '>>' | '&' | '|' ) subterm # SubtermOperator
    | subterm  ( '||' | '->' | '->>' ) subterm # SubtermOperator
    | subterm ( '*' | '/' | 'DIV' | '%' | 'MOD' ) subterm # SubtermOperator
    | subterm ( '+' | '-' ) subterm # SubtermOperator
    | subterm predicate #SubtermPredicate
    | ( 'ANY' | 'SOME' | 'ALL' ) '(' query ')' # SubtermFixme
    | VARIABLE assign subterm # SubtermAssign
    ;

predicate
    : compare subterm                                                         # PredicateCompare
    | ( 'ISNULL' | 'NOTNULL' | 'NOT' 'NULL' )                                 # PredicateFixme
    | 'IS' 'NOT'? logicals                                                    # PredicateLogical
    | 'IS' 'NOT'? 'DISTINCT' 'FROM' subterm                                   # PredicateDistinct
    | 'IS' 'NOT'? 'JSON' jsonType? uniqueKeys?                                 # PredicateJSON
    | 'IS' 'NOT'? 'OF' 'TYPE'? '(' 'ONLY'? type ( ',' type )* ')'             # PredicateOfType
    | 'NOT'? 'BETWEEN' ( 'ASYMMETRIC' | 'SYMMETRIC' )? subterm 'AND' subterm  # PredicateBETWEEN
    | 'NOT'? 'IN' ( '(' ( query | terms )? ')' | name )?                      # PredicateIN
    | 'NOT'? likes subterm ( 'ESCAPE' subterm )?                              # PredicateLIKE
    | 'NOT'? 'LIKE' ( 'ANY' | 'ALL' ) '(' terms ')'                           # PredicateLIKETerms
    ;

//        | 'RAISE' '(' ('IGNORE' | ('ROLLBACK' | 'ABORT' | 'FAIL') ',' string) ')'  # PredicateRaise
//   | '(' subterm ',' subterm ')' 'OVERLAPS' '(' subterm ',' subterm ')' # SubtermOverlaps
//    // CrateDB ?
////    | 'MATCH' '(' name ',' string ')' 'USING' qname 'WITH' '(' subterm ')'
// TODO: H2's INTERSECTS for 2D bounding boxes. Better as a function?
// | row 'INTERSECTS' '(' term ',' term ')'

//    predicate
////        | 'IS' 'NOT'? truth                                                        # PredicateIsTruth
//        | 'IS' 'NOT'? term                                                         # PredicateIS
//        ;

    jsonType
        : 'VALUE' | 'ARRAY' | 'OBJECT' | 'SCALAR' ;

    logicals
        : 'NULL' | 'UNKNOWN' | 'TRUE' | 'FALSE' | 'DISTINCT'
        // PL/SQL
        | 'NAN' | 'INFINITE' | 'PRESENT' | 'A' 'SET' | 'EMPTY'
        ;

    likes : 'LIKE' | 'RLIKE' | 'ILIKE' | 'REGEXP' | 'GLOB' | 'MATCH' ;

    assign
        : EQ | ASSIGN
        ;

    compare
        : EQ | COMPARE | TILDE | MATCH
        ;

literal
    : INTEGER
    | FLOAT
    | datetime
    | string
    | 'TRUE'
    | 'FALSE'
    | BITS
    | BYTES
    | OCTALS
    | 'UNKNOWN'
    | 'NULL'
    | 'ON'
    | 'OFF'
//    | 'DEFAULT'
    | PARAMETER
    | VARIABLE
    ;

datetime
    : 'DATE' string
    | ( '{d' | '{t' | '{ts' ) string '}'
    | ( 'TIME' | 'TIMESTAMP' ) ( withWithout timeZone )? string?
    | 'CURRENT_TIMESTAMP'
    ;

boolean
    : 'TRUE' | 'FALSE' /* | 'ON' | 'OFF'  */ ;

timeCast
    : timeUnit precision? ( 'TO' timeUnit precision? )?
    ;

timeUnit
    : 'EPOCH' | 'MILLENNIUM' | 'CENTURY' | 'DECADE' | 'YEAR' | 'YEARS'
    | 'QUARTER' | 'MONTH' | 'MONTHS' | 'WEEK' | 'WEEKS' | 'DAY' | 'DAYS'
    | 'HOUR' | 'HOURS' | 'MINUTE' | 'MINUTES' | 'SECOND' | 'SECONDS'
    | 'MCS' | 'MILLISECOND' | 'MICROSECOND' | 'NS' | 'NANOSECOND'
    | 'TIMEZONE_HOUR' | 'TIMEZONE_MINUTE' | 'TIMEZONE_SECOND'
    | 'ISO_WEEK_YEAR' | 'ISO_YEAR' | 'ISOYEAR'
    | 'ISO_DAY_OF_WEEK' | 'DAY_OF_WEEK' | 'ISODOW' | 'DOW' ;

    case
        : 'CASE' term whenSimple+ caseElse? 'END'  # CaseSimple
        | 'CASE' whenSearch+ caseElse? 'END'       # CaseSearch
        ;

        whenSimple : 'WHEN' ( predicate | terms ) caseThen ;
        whenSearch : 'WHEN' term caseThen ;
        caseThen : 'THEN' term ;
        caseElse : 'ELSE' term ;

type
    : 'ROW' '(' name type ( ',' name type )* ')'
    | 'SETOF' type
    | type 'ARRAY' ( '[' INTEGER ']' )?
    | type ( '[' INTEGER? ']' )+
    | scalar
    ;

scalar
//    : 'BIGINT'
//    | 'BINARY' precision?
//    | 'BIT' 'VARYING'? length?
//    | 'BLOB' precision?
////    | 'LARGE'? 'BLOB' precision?
//    | 'BOOL'
//    | 'BOOLEAN'
//    | chars precision?
//    | 'CLOB'
//    | 'DATE'
//    | 'DATETIME'
//    | 'DEC' precision?
//    | 'DECFLOAT'
//    | 'DECIMAL' precision?
//    | 'DOUBLE' 'PRECISION'?
//    | 'FLOAT' length?
//    | 'INT'
//    | 'BIG'? 'INTEGER'
////    | 'INTEGER'
//    | 'INTERVAL'
//    | 'JSON'
//    | 'JSONB'
//    | 'LONG'
//    | 'NCLOB'
//    | 'NUM' precision?
//    | 'NUMBER' precision?
//    | 'NUMERIC' precision?
//    | 'RAW'
//    | 'REAL'
//    | 'SMALLINT'
//    | 'STR'
//    | 'TEXT'
//    | ( 'TIME' | 'TIMESTAMP' ) length? ( withWithout 'LOCAL'? timeZone )
    : ( 'TIME' | 'TIMESTAMP' ) length? ( withWithout 'LOCAL'? timeZone )
//    | 'TINYINT'
//    | 'UUID'
//    | 'VARBINARY'
//    | 'VARINT'
//    | 'XML'
    | id+ precision?
    ;

// chars
//    : ( 'CHARACTER' | 'CHAR' | 'NCHAR' ) 'VARYING'?
//    | 'VARCHAR'
//    | 'VARCHAR2'
//    | 'VARCHAR_IGNORECASE'
//    | 'NATIONAL' ( 'CHARACTER' | 'CHAR' ) 'VARYING'?
//    | 'STRING'
//    ;

length
    : '(' INTEGER ')' ;

precision
    : '(' signedInteger ( ',' signedInteger )? ')'
    ;

signedNumber : signedInteger | signedFloat ;
signedInteger : ( '+' | '-' )? INTEGER ;
signedFloat : ( '+' | '-' )? FLOAT ;

array
    : 'ARRAY' arrayTerms  ;

    arrayTerms : '[' ( terms | arrayNested )? ']' ;

    arrayNested : arrayTerms ( ',' arrayTerms )* ;

function
    : 'TRIM' '(' ( 'BOTH' | 'LEADING' | 'TRAILING' )? ( term? 'FROM' )? terms ')'
    | 'CURRENT_DATE' params?
    | 'CURRENT_TIME' params?
    | 'CURRENT_TIMESTAMP' params?
    | 'LOCALTIME' params?
    | 'LOCALTIMESTAMP' params?
    | 'CURRENT_USER' params?
    | 'USER' params?
    | 'CURRENT_ROLE'
    | 'SESSION_USER'
    | 'CURRENT_CATALOG'
    | 'CURRENT_SCHEMA'
    | 'SYSTEM_USER'
    | 'SUBSTRING' '(' term 'FROM' term ( 'FOR' term )? ')'
    | 'JSON_OBJECTAGG' '(' jsonPairs onNull? uniqueKeys? ')' filter? over?
    | 'JSON_OBJECT' '(' jsonPairs? onNull? uniqueKeys? formatJson? ')'
    | 'JSON_ARRAY' '(' ( terms | '(' query ')' )? formatJson? onNull? ')'
    | ( 'CAST' | 'TRY_CAST' ) '(' term 'AS' type ( 'FORMAT' string )? ')'
    | 'UNIQUE' ( ( 'ALL' | 'NOT' )? 'DISTINCT' )? '(' query ')'
    | 'EXISTS' '(' query ')'
    | 'EXTRACT' '(' timeUnit 'FROM' subterm ')'
    | 'DATEDIFF' '(' timeUnit ',' string ',' string ')'
    | 'TIMESTAMPDIFF' '(' timeUnit ',' string ',' string ')'
    | 'DATEADD' '(' timeUnit ',' signedInteger ',' datetime ')'
    | 'DATE_TRUNC' '(' timeUnit ',' datetime ')'
    | 'COLLECT' '(' ( 'DISTINCT' | 'UNIQUE' ) name orderBy? ')'

    | 'XMLATTRIBUTES' '(' xmlAttrib ( ',' xmlAttrib )* ')'
    | 'XMLCONCAT' '(' terms ')'
    | 'XMLELEMENT' '(' 'NAME'? name ( ',' terms )? ')'
//   | 'XMLELEMENT' '(' 'NAME' collabel (',' (xml_attributes | expr_list))? ')'
    | 'XMLEXISTS' '(' subterm passing? ')'
    | 'XMLFOREST' '(' xmlAttrib ( ',' xmlAttrib )* ')'
    | 'XMLPARSE' '(' xmlContent term ( 'PRESERVE' | 'STRIP' ) 'WHITESPACE' ')'
    | 'XMLPI' '(' 'NAME' name ( ',' term )? ')'
    | 'XMLROOT' '(' 'XML' term ',' 'VERSION' ( term | 'NO' 'VALUE' ) ',' 'STANDALONE' ( 'YES' | 'NO' 'VALUE'? ) ')'
    | 'XMLSERIALIZE' '(' xmlContent term 'AS' type ')'

    | 'ARRAY' '(' query ')' // Postgres
    | '{fn' function '}' //  ODBC style

    | aggregateFunction
    ;

    params : '(' INTEGER? ')' ;

    // TODO Postgres functions
 /*
   : 'COLLATION' 'FOR' '(' a_expr ')'

   | 'NORMALIZE' '(' a_expr (',' unicode_normal_form)? ')'
   | 'OVERLAY' '(' a_expr 'PLACING' a_expr 'FROM' a_expr ('FOR' a_expr)? ')'
   | 'POSITION' '(' (subterm 'IN' subterm)? ')'
   | 'SUBSTRING' '(' substr_list? ')'
   | 'TREAT' '(' a_expr 'AS' type ')'
   | 'TRIM' '(' ('BOTH' | 'LEADING' | 'TRAILING')? trim_list ')'
   | 'NULLIF' '(' a_expr ',' a_expr ')'
   | 'COALESCE' '(' expr_list ')'
   | 'GREATEST' '(' expr_list ')'
   | 'LEAST' '(' expr_list ')'
 */
//    | 'SIN' '(' subterm ')'
//    | 'LEFT' '(' subterm ',' subterm ')'
//    | 'LOWER' '(' subterm ')'
////    | analyticFunction
////    | keyword? 'FUNCTION' keyword '(' terms? ')' // TODO: T-SQL style
////    | keyword '.' keyword '(' terms? ')' // TODO: T-SQL style?
//        'SUM' '(' allDistinct? term ')' filter? over?

    aggregateFunction
        : functionName
          '(' ( ( qname '.' )? '*' | allDistinct? ( term ( ',' term )* )? )
          orderBy?
    //    ( 'ON' 'OVERFLOW' ( 'ERROR' | 'TRUNCATE' name? withWithout 'COUNT' ))?
          ( 'ON' 'OVERFLOW' 'ERROR' )?
          ( 'SEPARATOR' term )? onNull?
    //      respectIgnore?  // TODO: Oracle
          ')'
          withinGroup?
          filter?
          ( 'FROM' firstLast )?
          nullTreatment? over?
        ;

//      ( ')' withinGroup
//      | ')' nullTreatment? over
//      | nullTreatment ')' over
//      | ')'
//      )


    //    analyticFunction
//        : ( 'FIRST_VALUE'
//          | 'LAST_VALUE'
//          | 'NTH_VALUE'
//          )
//          '(' terms respectIgnore? ')' respectIgnore? over
//        // Generic syntax for all analytic functions?
//        | qname '(' term respectIgnore? ')' respectIgnore? over
//
//        ;


    functionName
        : name
        | 'RIGHT'
        | 'LEFT'
        | 'SECOND'
        | 'YEAR'
        | 'MINUTE'
        | 'MONTH'
        | 'HOUR'
        | 'SET'
        | 'ANY'
        | 'SOME'
        ;


    withinGroup
        : 'WITHIN' 'GROUP' '(' orderBy ')' ;

    filter
        : 'FILTER' '(' 'WHERE' term ')' ;

    over
        : 'OVER' window ;

    xmlAttrib
        : term ( 'AS' alias )? ;

    xmlContent
        : 'DOCUMENT' | 'CONTENT' ;

window
    : '(' name? partitionBy? orderBy? windowFrame? ')'
    | name
    ;

    partitionBy
        : 'PARTITION' 'BY' terms ;

    windowFrame
        : ( 'RANGE' | 'ROWS' | 'GROUPS' )
          ( preceding | 'BETWEEN' following 'AND' following )
          ( 'EXCLUDE' ( 'CURRENT' 'ROW' | 'GROUP' | 'TIES' | 'NO' 'OTHERS' )? )?
        ;

        preceding
//            : ( 'UNBOUNDED' | 'CATEGORY' | term ) 'PRECEDING'
            : term 'PRECEDING'
            | 'CURRENT' 'ROW'
            ;

        following
//            : ( 'UNBOUNDED' | term ) 'FOLLOWING'
            : term 'FOLLOWING'
            | preceding
            ;

orderBy
    : 'ORDER' 'BY' orderByTerm ( ',' orderByTerm )*
    ;

    orderByTerm
        : term sortDir? ( 'NULLS' firstLast )?
        ;

    sortDir
        : 'ASC'
        | 'DESC'
        // Postgres
//        | 'USING' compare
//        | 'USING' ( EQ | COMPARE )
        ;


jsonPairs
        : jsonPair ( ',' jsonPair )* ;

        jsonPair
            : jsonKey ':' term
            | 'KEY'? jsonKey 'VALUE' term
            ;

            jsonKey
                : 'NULL' | name ;


index
    : '[' ( term | term? ':' term? )? ']' ;

allDistinct
    : 'ALL' | 'DISTINCT' ;

firstLast
    : 'FIRST' | 'LAST' ;

formatJson
    : 'FORMAT' 'JSON' ;

onNull
    : ( 'NULL' | 'ABSENT' ) 'ON' 'NULL' ;

nullTreatment
    : ( 'RESPECT' | 'IGNORE' ) 'NULLS' ;

rowRows
    : 'ROW' | 'ROWS' ;

timeZone
    : 'TIME' 'ZONE' ;

uniqueKeys
    : withWithout? 'UNIQUE' 'KEYS' ;

withTies
    : 'WITH' 'TIES' ;

withWithout
    : 'WITH' | 'WITHOUT' ;

alias
    : id | string ;

qnames0
    : qname ( ',' qname )* ;

qnames
    : '(' qname ( ',' qname )* ')' ;

qname
    : name ( '.' name )*
    ;

names
    : '(' name ( ',' name )* ')' ;

name
    : id | string ;

string
    : STRING+
    | UNICODE_STRING STRING* uescape?
    | NATIONAL_STRING STRING*
    ;

    uescape
        : 'UESCAPE' STRING ;

id :
    // exclude punctuation tokens
    ~( EQ | COMPARE | ASSIGN | TILDE | MATCH
    | '!' | '+' | '-' | '*' | '/' | '%' | '^'
    | '>>' | '<<' | '->' | '->>' | '|' | '||' | '&' | '&&'
    | '.' | ':' | '::' | ';'
    | '(' | ')' | '{' | '}' | ','
    | '['

    // exclude all other tokens except ID
    | UNICODE_ID
    | STRING
    | NATIONAL_STRING
    | UNICODE_STRING
//    | QUOTED
//    | BACKTICKS
    | DOLLARS
    | BITS
    | BYTES
    | BLOB
    | OCTALS
    | INTEGER
    | FLOAT
    | PARAMETER
    | VARIABLE

    // and exclude anything lexer doesn't recognize
    | OTHER

    // finally, exclude tokens we manually set to type RESERVED.
    // TODO See Reserved.fixID() (method name will change)
    | RESERVED
    )

    // accept UNICODE IDs
    | UNICODE_ID uescape?

    // accept T-SQL style bracketed names
    | '[' .*? ']'
    ;

// separate token because EQ is used both for assignments and comparisons. (TODO right?)
EQ      : '=' ;
ASSIGN  : ':=' | '+=' | '-=' | '*=' | '/=' | '%=' | '&=' | '^=' | '|=' ;
COMPARE : '==' | '<>' | '!=' | '<' | '<=' | '>' | '>=' | '&&' ;
TILDE   : '~' ;
MATCH   : '~*' | '!~' | '!~*' ;

UNICODE_ID
    : 'U&' QUOTED ;

UNICODE_STRING
    : 'U&' STRING ;

NATIONAL_STRING
    :  [NE] STRING ;

STRING
// TODO combine strings
//    : ( 'U&' | 'N' | 'E' )? '\'' ( ~'\'' | '\'\'' )* '\'' ;
    : '\'' ( ~( '\'' ) | '\'\'' )* '\'' ;

BACKTICKS : '`' ( ~( '`' ) | '``' )* '`' ;

QUOTED : '"' ( ~( '"' ) | '""' )* '"' ;

// TODO combine IDs
// TODO figure out why "varchar_whatever" isn't an ID token
ID : HEAD BODY* ;

fragment HEAD //options { caseInsensitive=false; }
    : [A-Z_]
    // Valid characters from 0x80 to 0xFF
//    | [\u00AA\u00B5\u00BA\u00C0-\u00D6\u00D8-\u00F6\u00F8-\u00FF]

        /*
   | // these are the letters above 0xFF which only need a single UTF-16 code unit
   [\u0100-\uD7FF\uE000-\uFFFF]
   {charIsLetter()}?
   | // letters which require multiple UTF-16 code units
   [\uD800-\uDBFF] [\uDC00-\uDFFF]
   {
    CheckIfUtf32Letter()
   }?
*/

//   | '\u00C0' .. '\u00D6'
//   | '\u00D8' .. '\u00F6'

//   | '\u00F8' .. '\u02FF'
//   | '\u0370' .. '\u037D'
//   | '\u037F' .. '\u1FFF'
//   | '\u200C' .. '\u200D'
//   | '\u2070' .. '\u218F'
//   | '\u2C00' .. '\u2FEF'
//   | '\u3001' .. '\uD7FF'
//   | '\uF900' .. '\uFDCF'
//   | '\uFDF0' .. '\uFFFD'
    ;

// TODO compare each dialect's rules for literals. eg SQLite allows ':'?
fragment BODY// options //{ caseInsensitive=false; }
    : [0-9#$@]
    | HEAD
//    | '\u00B7'
//    | '\u0300' .. '\u036F'
//    | '\u203F' .. '\u2040'
    ;

DOLLARS
    : '$$' .*? '$$' ;

BITS
    : '0b' [01]+
    | 'B' '\'' [01]+ '\''
    ;

BYTES
    : '0x' HEX+
    | 'X' '\'' ( HEX | ' ' | '\'\'' )* '\''
    ;

// TODO Ensures even number of digits. Does it matter?
BLOB
    : 'X' HEXHEX ( ' ' HEXHEX )* ;

    fragment HEXHEX
        : '\'' ( HEX HEX ' '? )* '\'' ;

 OCTALS
    : '0o' [0-7]+
    ;

fragment HEX
    : [A-F0-9] ;

// TODO support underscore (visual grouping) in numbers
INTEGER
    : DIGIT+ 'L'? ;

// matches "0.e1" or ".0e1", but not ".e1"
FLOAT
    : ( DIGIT+ ( '.' DIGIT* )? | '.' DIGIT+ ) ( 'E' [-+]? DIGIT+ )? [FD]?;

PARAMETER
    : '?' DIGIT*
    ;

fragment DIGIT
    : [0-9] ;

// TODO separate alts for each style & dialect
VARIABLE
    : [:$] ( INTEGER | ID )
    | '@' '@'? ID*
    ;

// \u000B line (vertical) tab
// \u000C form feed
WHITESPACE : [ \b\t\r\n\u000B\u000C]+ -> channel ( HIDDEN ) ;

COMMENT
//    : '--' .*? ( '\n' | EOF ) -> channel( HIDDEN ) ;
    // TODO is this better? cuz doesn't consume '\n' ?
    : '--' ~ [\r\n]* -> channel( HIDDEN ) ;

BLOCK_COMMENT
    : '/*' ( BLOCK_COMMENT | . )*? '*/' -> channel( HIDDEN ) ;

RESERVED : '\u007F'; // Placeholder value

OTHER : . ;

// TODO BOZO this crude OPERATOR token accepts way more than spec'd

// Postgres 4.1.3 https://www.postgresql.org/docs/current/sql-syntax-lexical.html#SQL-SYNTAX-OPERATORS
//OPERATOR
////    : ( '+' | '-' | [*/<>=~!@#%^&|`?] )+
////    : ( '+' | '-' | [*/<>=~!@#%^&|`] )+
//    :
//     { operatorEnabled }?
//    [*/<>=!@#%^&|`]+
//    ;


