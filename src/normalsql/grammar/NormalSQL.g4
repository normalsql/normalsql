// Copyright 2010-2025 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

/*
 NormalSQL.g4, SQL DML grammar for ANTLR 4.x

 Update 2025-04-19:

  This grammar will eventually be deprecated.

  The initial strategy of having an
  pluripotent grammar support multiple dialects suffered a complexity catastrophe.
  The second strategy of a heirarchy of grammars, where each dialect would extend
  a common base gramar, didn't fly.

  The current strategy is to have a distinct grammar for each dialect. These grammars
  will be like NormalSQL, adopting the rule names, idioms, and so forth, so
  they all generate the same parse tree (given the same input).


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

options {
  caseInsensitive=true;
  contextSuperClass=org.antlr.v4.runtime.RuleContextWithAltNum;
}

@parser::header { import static normalsql.util.Reserved.*; }

parse : statement? ( ';' statement? )* EOF ;

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
//    | createVirtualTable
    | delete
    | 'DETACH' 'DATABASE'? term
    | drop
    | 'END' 'TRANSACTION'?
    | insert
    | merge
    | pragma
    | select
    | 'REINDEX' qname?
    | 'RELEASE' 'SAVEPOINT'? qname
    | 'RESET' qname
    | 'ROLLBACK' 'TRANSACTION'? (( 'TO' 'SAVEPOINT'? )? name )?
    | 'SAVEPOINT' qname
    | set
    | update
    | 'VACUUM' qname? ( 'INTO' term )?
    )
  ;

    pragma : 'PRAGMA' qname ( '=' pragmaValue | '(' pragmaValue ')' )? ;

        pragmaValue : literal | number | name ;

explain
  : 'EXPLAIN' 'ANALYZE'? 'VERBOSE'? ( '(' option ( ',' option )* ')' )?
  | 'EXPLAIN' 'QUERY' 'PLAN'
  ;

    option
      : 'ANALYZE' boolean?
      | 'VERBOSE' boolean?
      | 'COSTS' ( boolean | 'ON' | 'OFF' )?
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
  | 'DROP' 'TEMPORARY'? 'FUNCTION' ifExists? qname ( '(' ( type ( ',' type )* )? ')' )?
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
    ( '(' ')'
    | '(' columnDef ( ',' columnDef )* ( ',' tableStuff )* ')' ( 'WITHOUT' ID )?
    | 'AS' select ( 'WITH' 'NO'? 'DATA' )?
    ) // TODO (COMMENT string)? (WITH properties)?
    // SQLite
    'STRICT'?

    // PostgreSQL
    ( 'INHERITS' '(' columnDef ( ',' columnDef )* ')' )?
  ;

createTrigger
  : 'CREATE' temporary? 'TRIGGER' ifNotExists? qname ( 'BEFORE' | 'AFTER' | 'INSTEAD' 'OF' )?
   ( 'DELETE' | 'INSERT' | 'UPDATE' ( 'OF' qnames0 )? ) 'ON' qname
   ( 'FOR' 'EACH' 'ROW' )? ( 'WHEN' term )?
   'BEGIN' (( update | insert | delete | select ) ';' )+
   // TODO remove '?' after testing!!
   'END'?
  ;

temporary
  : 'TEMP' | 'TEMPORARY' ;

createView
  : 'CREATE' temporary?
    // Postgres?
    ( 'OR' 'REPLACE' )?
    'VIEW' ifNotExists? qname qnames? 'AS' select
  ;

createIndex
  : 'CREATE' 'UNIQUE'? 'INDEX' ifNotExists? qname 'ON' qname indexedColumns where? ;
//  : 'CREATE' (OR REPLACE)? TEMPORARY? FUNCTION tableRef '(' (sqlParameterDeclaration (',' sqlParameterDeclaration)*)? ')' RETURNS type (COMMENT string)? routineCharacteristics routineBody
//  | 'CREATE' (OR REPLACE)? VIEW tableRef (SECURITY (DEFINER | INVOKER))? AS query
//  | 'CREATE' MATERIALIZED VIEW (IF NOT EXISTS)? tableRef (COMMENT string)? (WITH properties)? AS (query | '(' query ')' )
//  | 'CREATE' ROLE id (WITH ADMIN grantor)?
//  | 'CREATE' SCHEMA (IF NOT EXISTS)? tableRef (WITH properties)?
//  | 'CREATE' TYPE tableRef AS ( '(' sqlParameterDeclaration (',' sqlParameterDeclaration)* ')' | type)

    indexedColumn
      : term sortDir? ;

    indexedColumns
      : '(' indexedColumn ( ',' indexedColumn )* ')' ;

    ifNotExists
      : 'IF' 'NOT' 'EXISTS' ;

    ifExists
      : 'IF' 'EXISTS' ;

    columnDef
      : name ( type | constraint )*  // TODO (COMMENT string)? (WITH properties)?
      ;

    constraint
      : ( 'CONSTRAINT' name )?
        ( 'PRIMARY' 'KEY' sortDir? onConflict? 'AUTOINCREMENT'?
        | 'AUTOINCREMENT'
        | 'COUNTER'
        | 'NOT'? 'NULL' onConflict?
        | 'UNIQUE' onConflict?
        | 'CHECK' '(' term ')' ( 'NO' 'INHERIT' )?
        | 'DEFAULT' ( literal | term )
        | 'COLLATE' collationName
        | foreignKey
        | ( 'GENERATED' 'ALWAYS' )? 'AS' '(' term ')' ( 'STORED' | 'VIRTUAL' )?
        | 'INVISIBLE'
        | 'IMPLICITLY'? 'HIDDEN'
        )
        // SQLite undocumented
        ( 'CONSTRAINT' name )*
      ;

    // SQLite
    tableStuff
      : ( 'CONSTRAINT' name)?
        ( 'CHECK' '(' term ')' onConflict?
        | ( 'FOREIGN' 'KEY' columns foreignKey )
        | ( 'PRIMARY' 'KEY' | 'UNIQUE' ) indexedColumns onConflict?
        // SQLite per autoinc.test autoinc-7.1
        | 'PRIMARY' 'KEY' '(' name 'AUTOINCREMENT' ')'
        )+
        // SQLite undocumented
        ( 'CONSTRAINT' name )*
      ;

        onConflict
          : 'ON' 'CONFLICT' action ;

        foreignKey
          : 'REFERENCES' name columns?
            ( 'ON' ( 'DELETE' | 'UPDATE' )
              ( 'SET' ( 'NULL' | 'DEFAULT' )
              | 'CASCADE'
              | 'RESTRICT'
              | 'NO' 'ACTION'
              )
            | 'MATCH' ( 'FULL' |  'PARTIAL' |  'SIMPLE' )
//          | 'MATCH' name
            )*
            ( 'NOT'? 'DEFERRABLE' ( 'INITIALLY' ( 'DEFERRED' | 'IMMEDIATE' ))? )?
          ;

with
  : 'WITH' 'RECURSIVE'? cte ( ',' cte )* ;

    cte
        // TODO add rule for column aliases
      : name columns? 'AS' ( 'NOT'? 'MATERIALIZED' )?
      '(' select ')'
      ( 'SEARCH' ( 'BREADTH' | 'DEPTH' ) 'FIRST' 'BY' names 'SET' name )?
      ( 'CYCLE' names 'SET' name ( 'TO' literal 'DEFAULT' literal )? ( 'USING' name )? )?
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
    ( 'INSERT' ( 'OR' action )? | 'REPLACE' )
    'INTO' qname ( 'AS' name )? columns?
    overriding?
//      ( source upsert* | 'DEFAULT' 'VALUES' )
    ( select upsert* | 'DEFAULT' 'VALUES' )
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
  : with? 'MERGE' 'INTO' 'ONLY'? name alias?
//      'USING' 'ONLY'? source 'ON' terms
    'USING' 'ONLY'? select 'ON' terms
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
    : with? 'UPDATE' ( 'OR' action )?
      qname alias? indexedBy?
      'SET' setter ( ',' setter )*
      // TODO need sources which doesn't collide with this rule's orderBy, limit, offset
//      ( 'FROM' sources )?
      where? returning? orderBy? limit? offset?
    ;

    setter
      : ( qname | qnames ) '=' term ;

action
  : 'ABORT' | 'FAIL' | 'IGNORE' | 'REPLACE' | 'ROLLBACK' ;

// SQLite
indexedBy
  : 'NOT' 'INDEXED' | 'INDEXED' 'BY' qname ;

returning
  : 'RETURNING' returned ( ',' returned )* ;

    returned
      : '*' | term alias? ;

select
  : with?
    selectCore ( combine selectCore )*
    // Because various dialects order these clauses differently
    // TODO: Allow just one of each clause (somehow)
    ( orderBy | offset | fetch | limit | forUpdate )*
  ;

    combine
      : ( 'INTERSECT' | 'UNION' | 'EXCEPT' ) 'ALL'?
      | 'MINUS'
      | 'MULTISET'
      ;

    selectCore
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
      | values
      | '(' select ')'
      // MySQL table statement
      | 'TABLE' qname
      ;

    tables
      : tables ',' tables
        // TODO validate this. added ON clause to pass sqlite's tkt2141.test
        ( 'ON' term )?
      | tables join tables  ( 'ON' term | 'USING' qnames )?

      | ( qname | '(' qname ')' ) tableAlias?
        ( ( 'USE' 'INDEX' columns ) // H2
        | indexedBy // SQLite
        )?

      | ( '(' select ')'
        | values
        | tableFunc
        | deltaTable
        | tableCollection
        | 'JSON_TABLE' // TODO
        | xmlTable
        | unnest
        | pivot
        | unpivot
        )
        tableAlias?

      | '(' tables ')' tableAlias?
      ;

        // TODO add 'LATERAL'?
        join
          : 'NATURAL'?
            ( 'LEFT' ( 'SEMI' | 'ANTI' | 'OUTER' )?
            | ( 'RIGHT' | 'FULL' ) 'OUTER'?
            | 'INNER'
            | 'CROSS'
            )?
            'JOIN'
          ;

    tableAlias : alias columns? ;

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
//        |  'SYSTEM_RANGE' '(' term ',' term ( ',' term )? ')'
      // TODO should probably limit this to simple functions
      | function
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
        ( 'NOWAIT' | 'WAIT' DECIMAL | 'SKIP' 'LOCKED' )?
      ;

    quantifier
      : 'DISTINCT' ( 'ON' '(' terms ')' )?
      | 'ALL'
      ;

    top
      : 'TOP' ( DECIMAL | FLOAT | '(' term ')' ) 'PERCENT'? withTies? ;

    item
      : term alias?                       # ItemTerm
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
              'IN' '(' ( select | 'ANY' ) ')'
            ')'
          ;

            aliasedFunction
              : function alias? ;

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
      : 'WINDOW' window ( ',' window )* ;

        window
          : name 'AS' windowDef ;

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
          : name ( type ( 'PATH' subterm )? | 'FOR' 'ORDINALITY' ) ;

terms
  : term ( ',' term )* ;

aliasedTerm
  : term alias? ;

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
    : literal                                  # SubtermLiteral
    | qname                                    # SubtermColumn

    | subterm index                            # SubtermIndex
    | subterm '::' type                        # SubtermTypecast

    | ( '+' | '-' | TILDE | 'NOT' | '!' ) subterm                  # SubtermUnary
    | subterm  ( '||' | '->' | '->>' ) subterm            # SubtermOperator

    | <assoc=right> subterm '^' subterm                   # SubtermOperator
    | subterm ( '*' | '/' | 'DIV' | '%' | 'MOD' ) subterm # SubtermOperator
    | subterm ( '+' | '-' ) subterm                       # SubtermOperator
    | subterm ( '<<' | '>>' | '&' | '|' ) subterm         # SubtermOperator
    | subterm compare subterm                                                         # SubtermCompare
    | VARIABLE assign subterm                             # SubtermAssign

//    | name '(' ( 'DISTINCT'? terms  | '*' )? ')' filter? over?                  # SubtermFunction
    | function # SubtermFunction
    | '(' terms ')'                              # SubtermNested
    | 'CAST' '(' term 'AS' type ')'  #SubtermCAST
//    | term 'COLLATE' collationName            # SubtermCOLLATE
    | subterm 'COLLATE' collationName            # SubtermCollate

    | ( 'ANY' | 'SOME' | 'ALL' ) '(' select ')' # SubtermFixme

//    | subterm 'NOT'? likes subterm ( 'ESCAPE' subterm )?                              # SubtermLIKE
    | subterm 'NOT'? likes subterm 'ESCAPE' subterm                                   # SubtermLIKE
    | subterm 'NOT'? likes subterm                                                    # SubtermLIKE
    | subterm 'NOT'? 'LIKE' ( 'ANY' | 'ALL' ) '(' terms ')'                           # SubtermLIKETerms


//    | function ( '.' name )*                   # SubtermFunction
//    | function ( '.' name | index | '::' type )* # SubtermFunction
    | 'CASE' term whenSimple+ caseElse? 'END'       # CaseSimple
    | 'CASE' whenSearch+ caseElse? 'END'            # CaseSearch
    | 'INTERVAL' subterm timeCast                   # SubtermInterval
    | subterm 'AT' ( 'LOCAL' | timeZone string ) # SubtermAtTZ
//    | '(' select ')'                              # SubtermSubquery
    | 'ROW'? '(' terms? ')' ( '.' name )?        # SubtermRow

    | subterm ( 'ISNULL' | 'NOTNULL' | 'NOT' 'NULL' )                                 # SubtermFixme
    | subterm 'IS' 'NOT'? subterm                                                    # SubtermLogical
    | subterm 'IS' 'NOT'? 'DISTINCT' 'FROM' subterm                                   # SubtermDistinct
    | subterm 'IS' 'NOT'? 'JSON' jsonType? uniqueKeys?                                # SubtermJSON
    | subterm 'IS' 'NOT'? 'OF' 'TYPE'? '(' 'ONLY'? type ( ',' type )* ')'             # SubtermOfType
    | subterm 'NOT'? 'BETWEEN' ( 'ASYMMETRIC' | 'SYMMETRIC' )? subterm 'AND' subterm  # SubtermBETWEEN
//    | subterm 'NOT'? 'IN' ( select | terms )?                      # SubtermIN

    | subterm 'NOT'? 'IN' ( '(' ( select | terms )? ')' | name )?                      # SubtermIN

    | ( 'NEXT' | 'CURRENT' ) 'VALUE' 'FOR' qname # SubtermSequence
    | array                                      # SubtermArray
    | select                              # SubtermSubquery
    ;

predicate
  : compare subterm                                                         # PredicateCompare
  | ( 'ISNULL' | 'NOTNULL' | 'NOT' 'NULL' )                                 # PredicateFixme
  | 'IS' 'NOT'? logicals                                                    # PredicateLogical
  | 'IS' 'NOT'? 'DISTINCT' 'FROM' subterm                                   # PredicateDistinct
  | 'IS' 'NOT'? 'JSON' jsonType? uniqueKeys?                                # PredicateJSON
  | 'IS' 'NOT'? 'OF' 'TYPE'? '(' 'ONLY'? type ( ',' type )* ')'             # PredicateOfType
  | 'NOT'? 'BETWEEN' ( 'ASYMMETRIC' | 'SYMMETRIC' )? subterm 'AND' subterm  # PredicateBETWEEN
  | 'NOT'? 'IN' ( '(' ( select | terms )? ')' | name )?                      # PredicateIN
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
//        | 'IS' 'NOT'? term                                                         # PredicateIS
//        ;

    jsonType
      : 'VALUE' | 'ARRAY' | 'OBJECT' | 'SCALAR' ;

    logicals
      : 'NULL' | 'UNKNOWN' | 'TRUE' | 'FALSE' | 'DISTINCT'
      // PL/SQL
      | 'NAN' | 'INFINITE' | 'PRESENT' | 'A' 'SET' | 'EMPTY'
      ;

    likes
      : 'LIKE' | 'RLIKE' | 'ILIKE' | 'REGEXP' | 'GLOB' | 'MATCH' ;

    assign
      : EQ | ASSIGN ;

    compare
      : EQ | COMPARE | TILDE | MATCH ;

    collationName
      : 'BINARY' | 'NOCASE' | 'RTRIM' | name ;

literal
  : DECIMAL
  | FLOAT
  | BINARY
  | HEXIDECIMAL
  | OCTAL
  | datetime
  | PARAMETER
  | VARIABLE
  | string
  | 'TRUE'
  | 'FALSE'
  ;

datetime
  : 'DATE' string
  | ( '{d' | '{t' | '{ts' ) string '}'
  | ( 'TIME' | 'TIMESTAMP' ) ( withWithout timeZone )? string?
  | 'CURRENT_TIMESTAMP'
  ;

boolean
  : 'TRUE' | 'FALSE' ;

timeCast
    : timeUnit precision? ( 'TO' timeUnit precision? )? ;

timeUnit
  : 'EPOCH' | 'MILLENNIUM' | 'CENTURY' | 'DECADE' | 'YEAR' | 'YEARS'
  | 'QUARTER' | 'MONTH' | 'MONTHS' | 'WEEK' | 'WEEKS' | 'DAY' | 'DAYS'
  | 'HOUR' | 'HOURS' | 'MINUTE' | 'MINUTES' | 'SECOND' | 'SECONDS'
  | 'MCS' | 'MILLISECOND' | 'MICROSECOND' | 'NS' | 'NANOSECOND'
  | 'TIMEZONE_HOUR' | 'TIMEZONE_MINUTE' | 'TIMEZONE_SECOND'
  | 'ISO_WEEK_YEAR' | 'ISO_YEAR' | 'ISOYEAR'
  | 'ISO_DAY_OF_WEEK' | 'DAY_OF_WEEK' | 'ISODOW' | 'DOW' ;

  whenSimple : 'WHEN' ( predicate | terms ) caseThen ;
    whenSearch : 'WHEN' term caseThen ;
    caseThen : 'THEN' term ;
    caseElse : 'ELSE' term ;

type
  : 'ROW' '(' name type ( ',' name type )* ')'
  | 'SETOF' type
  | type 'ARRAY' ( '[' DECIMAL ']' )?
  | type ( '[' DECIMAL? ']' )+
  | scalar
  ;

scalar
  : 'BIGINT'
  | 'BINARY' precision?
  | 'BIT' 'VARYING'? length?
  | 'LARGE'? 'BLOB' precision?
  | 'BOOL'
  | 'BOOLEAN'
  | chars precision?
  | 'CLOB'
  | 'DATE'
  | 'DATETIME'
  | 'DEC' precision?
  | 'DECFLOAT'
  | 'DECIMAL' precision?
  | 'DOUBLE' 'PRECISION'?
  | 'FLOAT' length?
  | 'INT'
  | 'BIG'? 'INTEGER'
  | 'INTERVAL'
  | 'JSON'
  | 'JSONB'
  | 'LONG'
  | 'NCLOB'
  | 'NUM' precision?
  | 'NUMBER' precision?
  | 'NUMERIC' precision?
  | 'RAW'
  | 'REAL'
  | 'SMALLINT'
  | 'STR'
  | 'TEXT' length?
  | ( 'TIME' | 'TIMESTAMP' ) length? ( withWithout 'LOCAL'? timeZone )?
  | 'TINYINT'
  | 'UUID'
  | 'VARBINARY'
  | 'VARINT'
  | 'XML'
  | id precision?
  ;

chars
  : ( 'CHARACTER' | 'CHAR' | 'NCHAR' ) 'VARYING'?
  | 'VARCHAR'
  | 'VARCHAR2'
  | 'VARCHAR_IGNORECASE'
  | 'NATIONAL' ( 'CHARACTER' | 'CHAR' ) 'VARYING'?
  | 'STRING'
  ;

length
    : '(' DECIMAL ')' ;

precision
    : '(' signedInteger ( ',' signedInteger )? ')' ;

number : signedInteger | signedFloat ;
signedInteger : ( '+' | '-' )? DECIMAL ;
signedFloat : ( '+' | '-' )? FLOAT ;

array
    : 'ARRAY' arrayTerms  ;

    arrayTerms
        : '[' ( terms | arrayNested )? ']' ;

    arrayNested
        : arrayTerms ( ',' arrayTerms )* ;

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
    // MySQL, Postgres, H2
    | 'SUBSTRING' '(' term 'FROM' term ( 'FOR' term )? ')'
    | 'JSON_OBJECTAGG' '(' jsonPairs onNull? uniqueKeys? ')' filter? over?
    | 'JSON_OBJECT' '(' jsonPairs? onNull? uniqueKeys? formatJson? ')'
    | 'JSON_ARRAY' '(' terms formatJson? onNull? ')'
//    | ( 'CAST' | 'TRY_CAST' ) '(' term 'AS' type ( 'FORMAT' string )? ')'
//    | 'UNIQUE' ( ( 'ALL' | 'NOT' )? 'DISTINCT' )? '(' select ')'
//    | 'EXISTS' '(' select ')'
    | 'EXTRACT' '(' timeUnit 'FROM' subterm ')'
//    | 'DATEDIFF' '(' timeUnit ',' string ',' string ')'
//    | 'TIMESTAMPDIFF' '(' timeUnit ',' string ',' string ')'
//    | 'DATEADD' '(' timeUnit ',' signedInteger ',' datetime ')'
//    | 'DATE_TRUNC' '(' timeUnit ',' datetime ')'
//    | 'COLLECT' '(' ( 'DISTINCT' | 'UNIQUE' ) name orderBy? ')'
//
//    | 'XMLATTRIBUTES' '(' xmlAttrib ( ',' xmlAttrib )* ')'
//    | 'XMLCONCAT' '(' terms ')'
//    | 'XMLELEMENT' '(' 'NAME'? name ( ',' terms )? ')'
////   | 'XMLELEMENT' '(' 'NAME' collabel (',' (xml_attributes | expr_list))? ')'
//    | 'XMLEXISTS' '(' subterm passing? ')'
//    | 'XMLFOREST' '(' xmlAttrib ( ',' xmlAttrib )* ')'
//    | 'XMLPARSE' '(' xmlContent term ( 'PRESERVE' | 'STRIP' ) 'WHITESPACE' ')'
//    | 'XMLPI' '(' 'NAME' name ( ',' term )? ')'
//    | 'XMLROOT' '(' 'XML' term ',' 'VERSION' ( term | 'NO' 'VALUE' ) ',' 'STANDALONE' ( 'YES' | 'NO' 'VALUE'? ) ')'
//    | 'XMLSERIALIZE' '(' xmlContent term 'AS' type ')'
//
//    | 'ARRAY' '(' select ')' // Postgres
    | '{fn' function '}' //  ODBC style
    | aggregateFunction
    ;

    // MySQL
    separator
      : 'SEPARATOR' term ;

    params
      : '(' DECIMAL? ')' ;

 /*
    // TODO Postgres functions
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
//    | 'SIN' '(' subterm ')'
//    | 'LEFT' '(' subterm ',' subterm ')'
//    | 'LOWER' '(' subterm ')'
////    | analyticFunction
////    | keyword? 'FUNCTION' keyword '(' terms? ')' // TODO: T-SQL style
////    | keyword '.' keyword '(' terms? ')' // TODO: T-SQL style?
//        'SUM' '(' allDistinct? term ')' filter? over?
 */

    aggregateFunction
        : name
          '('
            ( ( allDistinct? terms  | '*' ) orderBy? )?
            overflow?
            separator?
            onNull?
            // respectIgnore?  // TODO: Oracle
          ')'
          withinGroup?
          filter?
          ( 'FROM' ('FIRST' | 'LAST') )?
          nullTreatment?
          over?
        ;

// Oracle https://modern-sql.com/feature/listagg
// SQL:2016 T625
overflow : 'ON' 'OVERFLOW' ( 'ERROR' | 'TRUNCATE' name? withWithout 'COUNT' ) ;



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

//    functionName
//        : name
//        | 'RIGHT'
//        | 'LEFT'
//        | 'SECOND'
//        | 'YEAR'
//        | 'MINUTE'
//        | 'MONTH'
//        | 'HOUR'
//        | 'SET'
//        | 'ANY'
//        | 'SOME'
//        ;

    withinGroup
        : 'WITHIN' 'GROUP' '(' orderBy ')' ;

    filter
        : 'FILTER' '(' 'WHERE' term ')' ;

    over
        : 'OVER' windowDef ;

    xmlAttrib
        : term ( 'AS' alias )? ;

    xmlContent
        : 'DOCUMENT' | 'CONTENT' ;

windowDef
  : '(' name? partitionBy? orderBy? windowFrame? ')'
  | name
  ;

  partitionBy
    : 'PARTITION' 'BY' terms ;

windowFrame
  : ( 'RANGE' | 'ROWS' | 'GROUPS' )
    ( 'BETWEEN' windowFrameBounds 'AND' )? windowFrameBounds
    ( 'EXCLUDE' ( 'NO' 'OTHERS' | 'CURRENT' 'ROW' | 'GROUP' | 'TIES' ))?
//    ( 'EXCLUDE' ( 'CURRENT' 'ROW' | 'GROUP' | 'TIES' | 'NO' 'OTHERS' )? )?
  ;

windowFrameBounds
  // TODO resolve ambig, cuz term -> qname also matches 'UNBOUNDED'
  : ( term | 'UNBOUNDED' ) ( 'PRECEDING' | 'FOLLOWING' )
  | 'CURRENT' 'ROW'
  ;

orderBy
    : 'ORDER' 'BY' orderByTerm ( ',' orderByTerm )* ;

    orderByTerm
        : term sortDir? ( 'NULLS' ('FIRST' | 'LAST') )? ;

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
    : 'AS'? name ;

qnames0
    : qname ( ',' qname )* ;

// TODO unnecessary? use columns rule instead?
qnames
    : '(' qname ( ',' qname )* ')' ;

qname
    : name ( '.' name )* ;

columns
    : '(' name ( ',' name )* ')' ;

names
     : name ( ',' name )* ;

name
    : id
    // BOZO these two are redundant, no?
    | QUOTED // TODO try changing QUOTED token into parser rule
    | BACKTICKS
    | string
    | UNICODE_ID uescape?
    // T-SQL
    | brackets
    ;

brackets : '[' .*? ']' ;

string
    : STRING+
    | UNICODE_STRING STRING* uescape?
    | NATIONAL_STRING STRING*
    ;

    uescape
        : 'UESCAPE' STRING ;

// TODO rename from 'id' to 'keyword'
// Exclude everything but this grammar's tokens and ID tokens.
id :
    // exclude punctuation
    ~( EQ | COMPARE | ASSIGN | TILDE | MATCH
    | '!' | '+' | '-' | '*' | '/' | '%' | '^'
    | '>>' | '<<' | '->' | '->>' | '|' | '||' | '&' | '&&'
    | '.' | ':' | '::' | ';'
    | '(' | ')' | '{' | '}' | ','
    | '['

    // exclude literals
    | UNICODE_ID
    | QUOTED
    | BACKTICKS
    | STRING
    | NATIONAL_STRING
    | UNICODE_STRING
    | BINARY
    | HEXIDECIMAL
    | OCTAL
    | DECIMAL
    | FLOAT
    | PARAMETER
    | VARIABLE
    | 'TRUE'
    | 'FALSE'

    // and exclude anything lexer doesn't recognize
    | OTHER
    )
    ;

// separate token because EQ is used both for assignments and comparisons. (TODO right?)
EQ      : '=' ;
ASSIGN  : ':=' | '+=' | '-=' | '*=' | '/=' | '%=' | '&=' | '^=' | '|=' ;
COMPARE : '==' | '<>' | '!=' | '<' | '<=' | '>' | '>=' | '&&' ;
TILDE   : '~' ;
MATCH   : '~*' | '!~' | '!~*' ;

// TODO compare each dialect's rules for identifiers and strings. eg SQLite allows ':'?

UNICODE_STRING : 'U&' STRING ;

NATIONAL_STRING : [NE] STRING ;

STRING
    : '\'' ( ~'\'' | '\'\'' )* '\''
    // Postgres
    | '$$' .*? '$$'
    ;

// TODO merge backticks, quoted, ID, UNICODE_ID?
BACKTICKS : '`' ( ~( '`' ) | '``' )* '`' ;

QUOTED : '"' ( ~( '"' ) | '""' )* '"' ;

ID : [A-Z_\u007F-\uFFFF] [A-Z_0-9#$@\u007F-\uFFFF]* ;

// TODO inline uescape?
UNICODE_ID : 'U&' '"' ( ~( '"' ) | '""' )* '"' ;

BINARY
    : '0b' [01]+
    | 'B' '\'' [01]+ '\''
    ;

HEXIDECIMAL
    : '0x' [A-F0-9]+
    | 'X' '\'' ( [A-F0-9] | ' ' | '\'\'' )* '\''
    ;

OCTAL : '0o' [0-7]+ ;

// TODO support underscore (visual grouping) in numbers
DECIMAL
    : DIGIT+ 'L'? ;

// matches "0.e1" or ".0e1", but not ".e1"
FLOAT
    : ( DIGIT+ ( '.' DIGIT* )? | '.' DIGIT+ ) ( 'E' [-+]? DIGIT+ )? [FD]?;

PARAMETER
  : '?' DIGIT* ;

fragment DIGIT
  : [0-9] ;

// TODO separate alts for each style & dialect
VARIABLE
  : [:$] ( DECIMAL | ID )
  | '@' '@'? ID*
  ;

// \u000B line (vertical) tab
// \u000C form feed
WHITESPACE
  : [ \b\t\r\n\u000B\u000C]+ -> channel ( HIDDEN ) ;

COMMENT
  //    : '--' .*? ( '\n' | EOF ) -> channel( HIDDEN ) ;
  // TODO is this better? cuz doesn't consume '\n' ?
  : '--' ~ [\r\n]* -> channel( HIDDEN ) ;

BLOCK_COMMENT
  : '/*' ( BLOCK_COMMENT | . )*? '*/' -> channel( HIDDEN ) ;

OTHER : . ;
