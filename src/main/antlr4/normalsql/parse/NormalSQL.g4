// Copyright 2010-2023 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

/*
 NormalSQL.g4, SQL DML grammar for ANTLR 4.x

 Style:

 Child rules are indented when its (mostly) only used by a parent rule.

   Heuristics:

      1. Create subrules to ease parse tree navigation.

      2. Per DRY, create subrule for 3 (sometimes 2) or more copypastas. Except when doing so
         conflicts with #1.

*/

grammar NormalSQL;

options {
 caseInsensitive=true;
 }

 @lexer::members
 {
     public boolean bracketsEnabled = false;
     public boolean operatorEnabled = false;
 }

script
    : statement? ( ';' statement? )* EOF ;

statement
    : explain?
    ( alter
    | ( 'ANALYZE' | 'ANALYSE' ) qname?
    | 'ATTACH' 'DATABASE'? term 'AS' qname
    | 'BEGIN' ( 'DEFERRED' | 'EXCLUSIVE' | 'IMMEDIATE' )? 'TRANSACTION'?
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
    | 'PRAGMA' qname ( '=' ( literal | name ) | '(' ( literal | name ) ')' )?
    | select
    | 'REINDEX' qname?
    | 'RELEASE' 'SAVEPOINT'? qname
    | 'RESET' qname
    | 'ROLLBACK' 'TRANSACTION'? ( 'TO' 'SAVEPOINT'? name )?
    | 'SAVEPOINT' qname
    | set
    | update
    | 'VACUUM' qname? ( 'INTO' term )?
    )
    ;

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
      | 'AS' select ( 'WITH' 'NO'? 'DATA' )?
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
        | COMPARE
//        | assign
        | ASSIGN
        | EQ
        ;

createTrigger
     : 'CREATE' temporary? 'TRIGGER' ifNotExists? qname ( 'BEFORE' | 'AFTER' | 'INSTEAD' 'OF' )?
       ( 'DELETE' | 'INSERT' | 'UPDATE' ( 'OF' qnames0 )? ) 'ON' qname
       ( 'FOR' 'EACH' 'ROW' )? ( 'WHEN' term )?
       'BEGIN' (( update | insert | delete | select ) ';' )+
       // TODO remove '?' after testing!!
       'END'?
     ;

temporary : 'TEMP' | 'TEMPORARY' ;

createView
      : 'CREATE' temporary?
        // Postgres?
        ( 'OR' 'REPLACE' )?
        'VIEW' ifNotExists? qname qnames? 'AS' select ;

createIndex
      : 'CREATE' 'UNIQUE'? 'INDEX' ifNotExists? qname 'ON' qname indexedColumns where? ;
//    : 'CREATE' (OR REPLACE)? TEMPORARY? FUNCTION tableRef '(' (sqlParameterDeclaration (',' sqlParameterDeclaration)*)? ')' RETURNS type (COMMENT string)? routineCharacteristics routineBody
//    | 'CREATE' (OR REPLACE)? VIEW tableRef (SECURITY (DEFINER | INVOKER))? AS query
//    | 'CREATE' MATERIALIZED VIEW (IF NOT EXISTS)? tableRef (COMMENT string)? (WITH properties)? AS (query | '(' query ')' )
//    | 'CREATE' ROLE id (WITH ADMIN grantor)?
//    | 'CREATE' SCHEMA (IF NOT EXISTS)? tableRef (WITH properties)?
//    | 'CREATE' TYPE tableRef AS ( '(' sqlParameterDeclaration (',' sqlParameterDeclaration)* ')' | type)


    indexedColumn
        : ( qname | term ) sortDir? ;

    indexedColumns
        : '(' indexedColumn ( ',' indexedColumn )* ')' ;

    ifNotExists : 'IF' 'NOT' 'EXISTS' ;

    columnDef
        : name type? columnStuff*  // TODO (COMMENT string)? (WITH properties)?
        ;

    columnStuff
        : ( 'CONSTRAINT' name )?
          ( 'PRIMARY' 'KEY' sortDir? onConflict? 'AUTOINCREMENT'?
          | 'NOT'? 'NULL' onConflict?
          | 'UNIQUE' onConflict?
          | 'CHECK' '(' term ')' ( 'NO' 'INHERIT' )?
          | 'DEFAULT' ( literal | term )
          | 'COLLATE' name
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
        '(' select ')'
        ( 'SEARCH' ( 'BREADTH' | 'DEPTH' ) 'FIRST' 'BY' name ( ',' name )* 'SET' name )?
        ( 'CYCLE' name ( ',' name )* 'SET' name ( 'TO' literal 'DEFAULT' literal )? ( 'USING' name )? )?
        ;

delete
    : with? 'DELETE' 'FROM' 'ONLY'? qname ( 'AS' name )? indexedBy?
      // Postgres
//      ( 'USING' sources ( ',' sources )* )?
      ( 'USING' sources )?
      where? returning? orderBy? limit? offset?
      ;

insert
    : with?
      ( 'INSERT' ( 'OR' afirr )? | 'REPLACE' )
      'INTO' qname ( 'AS' name )? names?
      overriding?
      ( source upsert* | 'DEFAULT' 'VALUES' )
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
      'USING' 'ONLY'? source 'ON' terms
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
      'SET' setter ( ',' setter )* ( 'FROM' sources )?
      where? returning? orderBy? limit? offset?  ;

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

select
    :  with? selectCore
      // Because various dialects order these clauses differently
      // TODO: Allow just one of each clause (somehow)
      ( orderBy | offset | fetch | limit | forUpdate )*
    | select ( unions select )+
    | '(' select ')'
    ;

    selectCore
        : 'SELECT' quantifier? top? ( item ( ',' item )* ','? )? into?
          ( 'FROM' sources )?
          where? groupBy? having?
          windows? qualify?
        | 'VALUES' terms
        // MySQL table statement
        | 'TABLE' qname

        | tableFunc
        // H2 data change delta table http://h2database.com/html/grammar.html#data_change_delta_table
        // DB2 intermediate result table
        | ( 'NEW' | 'OLD' | 'FINAL' ) 'TABLE' '(' ( delete | insert | merge | update ) ')'
        // PL/SQL table collection expression
        | 'TABLE' '(' term ')'
        | 'JSON_TABLE' // TODO
        | xmlTable
//        | function
        | unnest
        | '(' source ')'
        ;

    unions
        : 'INTERSECT'
        | 'MINUS'
        | 'UNION' 'ALL'?
        | 'EXCEPT'
        | 'MULTISET'
        ;

    sources
        : sources ( join sources ( 'ON' term | 'USING' qnames )* | pivot | unpivot )+
        | source
        | '(' sources ')'
        ;

    source
      : qname ( 'AS'? alias names? )?
        // H2
        ( ( 'USE' 'INDEX' names )
        // SQLite
          | ( 'NOT' 'INDEXED' | 'INDEXED' 'BY' qname )
        )?
      | ( select
        | '(' source ')'
        | tableFunc
        // H2 data change delta table http://h2database.com/html/grammar.html#data_change_delta_table
        // DB2 intermediate result table
        | ( 'NEW' | 'OLD' | 'FINAL' ) 'TABLE' '(' ( delete | insert | merge | update ) ')'
        // PL/SQL table collection expression
        | 'TABLE' '(' term ')'
        | 'JSON_TABLE' // TODO
        | xmlTable
        | function
        | unnest
        )
        ( 'AS'? alias names? )?
        ;

    unnest
        : 'UNNEST' '(' ( array ( ',' array )* )? ')' ( 'WITH' 'ORDINALITY' )? ;

    // H2 table function http://h2database.com/html/functions.html#table
    tableFunc
        : ( 'TABLE' | 'TABLE_DISTINCT' ) '(' tableFuncParam ( ',' tableFuncParam )* ')' ;

        tableFuncParam
            : name ( type | qname | 'NULL' ) '=' subterm ;

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
//        : qname ( 'AS'? alias )?  # ItemTableRef
//        | (( qname '.' )? '*' ) ( 'EXCEPT' qnames )?  # ItemTableRef
////        | term ( 'AS'? alias )?                        # ItemTerm
        : term ( 'AS'? alias )?                        # ItemTerm
        | (( qname '.' )? '*' ) ( 'EXCEPT' qnames )?  # ItemTableRef
//        |
        ;

    into
        : 'INTO' ( temporary | 'UNLOGGED' )? 'TABLE'? ( qname | VARIABLE ) ( ',' ( qname | VARIABLE ) )* ;

        join
            // TODO 'LATERAL'
            : ','
                // TODO argh figure out correct, robust join operators
            | 'NATURAL'?
                (  ( 'LEFT' | 'RIGHT' | 'FULL' | 'OUTER' )+ | 'INNER' | 'CROSS' )?
              'JOIN'
            ;

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
            : terms | 'ROLLUP' '(' terms ')' | 'CUBE' '(' terms ')'
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

// TODO: H2's INTERSECTS for 2D bounding boxes. Better as a function?
// | row 'INTERSECTS' '(' term ',' term ')'

term
//    : '(' terms ')'
//    | subterm
    : subterm
    // TODO redundant w/ subterm unary. still ok?
    | 'NOT' term
    | term ( 'AND' | 'XOR' | 'OR' ) term
//    | term 'AND' term
//    | term 'XOR' term
//    | term ( 'OR' | '||' ) term
//    | term 'OR' term
    // CrateDB ?
//    | 'MATCH' '(' name ',' string ')' 'USING' qname 'WITH' '(' subterm ')'
//    | VARIABLE assign term

//    | qname ASSIGN term
   ;

//    assign
//        : '=' | ':=' | '+=' | '-=' | '*=' | '/=' | '%=' | '&=' | '^=' | '|=' ;

subterm
    : literal                                                       # SubtermLiteral
    | qname                                                          # SubtermColumn
//    | ( '+' | '-' | '~' | '!' | 'NOT' ) subterm                               # SubtermUnary
    | ( '+' | '-' | '!' | 'NOT' ) subterm                               # SubtermUnary
    | subterm '||' subterm                                            # SubtermBinary
    | subterm ( '*' | '/' | 'DIV' | '%' | 'MOD' ) subterm             # SubtermBinary
    | subterm ( '+' | '-' ) subterm                                   # SubtermBinary
    | subterm ( '<<' | '>>' | '&' | '|' ) subterm                                 # SubtermBinary
    | subterm predicate                                               # SubtermPredicate
    | subterm '::' type                                               # SubtermCast
    // Postgres?
    | subterm ( '::' subterm )+                                       # SubtermScope
    | subterm index+                                                  # SubtermIndex
    | function ( '.' function )*                                      # SubtermFunction

    // TODO: BINARY
    | ( 'CAST' | 'TRY_CAST' ) '(' term 'AS' type ')'                  # SubtermCast
    | subterm 'COLLATE' name                                         # SubtermCOLLATE
    | subterm 'AT' ( 'LOCAL' | timeZone string )                      # SubtermTime
    // PL/SQL, ODBC
    | subterm timeCast                                                # SubtermInterval
    | interval                                                          # SubtermInterval
    | select                                                           # SubtermQuery
    | array                                                           # SubtermArray
    | (( 'NOT' )? 'EXISTS' )? '(' select ')'                          # SubtermEXISTS
    | case                                                            # SubtermCase
    | 'UNIQUE' ( ( 'ALL' | 'NOT' )? 'DISTINCT' )? '(' select ')'       # SubtermUNIQUE
    | ( 'NEXT' | 'CURRENT' ) 'VALUE' 'FOR' qname                     # SubtermSequence
    // PL/SQL
//    | '(' subterm ',' subterm ')' 'OVERLAPS' '(' subterm ',' subterm ')' # SubtermOverlaps
//   TODO  | sequenceValueExpression
    | 'ROW'? '(' terms? ')' ( '.' name )?                             # SubtermRow
    | <assoc=right> subterm '^' subterm                               # SubtermBinary
    ;

    case
        : 'CASE' term ( 'WHEN' ( terms | predicate ) 'THEN' term )+ ( 'ELSE' term )? 'END'   // # CaseSimple
        | 'CASE' ( 'WHEN' term 'THEN' term )+ ( 'ELSE' term )? 'END'                        //  # CaseSearch
        ;

    predicate
//        : compare subterm                                                          # PredicateCompare
        : ( EQ | COMPARE | ASSIGN ) subterm                                           # PredicateCompare
        | ( 'ISNULL' | 'NOTNULL' | 'NOT' 'NULL' )                                  # PredicateIsNull
//        | 'IS' 'NOT'? truth                                                        # PredicateIsTruth
        // PL/SQL dialect?
        | 'IS' 'NOT'? logicals                                                     # PredicateLogical
        | 'IS' 'NOT'? 'DISTINCT' 'FROM' subterm                                    # PredicateDistinct
        | 'IS' 'NOT'? 'OF' 'TYPE'? '(' 'ONLY'? type ( ',' type )* ')'              # PredicateOfType
        | 'IS' 'NOT'? 'JSON' jsonType? uniqueKeys?                                 # PredicateJSON
        | 'IS' 'NOT'? term                                                         # PredicateIS
        | 'NOT'? 'IN' '(' ( select | terms )? ')'                                   # PredicateIN
        // PL/SQL dialect
//        | 'NOT'? 'IN' subterm                                                      # PredicateIN
        | 'NOT'? 'BETWEEN' ( 'ASYMMETRIC' | 'SYMMETRIC' )? subterm 'AND' subterm   # PredicateBETWEEN
        | 'NOT'? ( 'LIKE' | 'ILIKE' | 'REGEXP' | 'GLOB' | 'MATCH' ) subterm ( 'ESCAPE' ( string | term ) )?      # PredicateLIKE
        | 'RAISE' '(' ('IGNORE' | ('ROLLBACK' | 'ABORT' | 'FAIL') ',' string) ')'  # PredicateRaise
        ;

//        compare
//            : '=' | '==' | '<>' | '!=' | '^=' | '<' | '<=' | '>' | '>=' | '&&'
//            // Postgres match regex
////            | '~' | '~*' | '!~' | '!~*'
////            | OPERATOR
//            ;

        logicals
            : 'NAN' | 'INFINITE' | 'PRESENT' | 'A' 'SET' | 'EMPTY'
            ;

        jsonType
            : 'VALUE' | 'ARRAY' | 'OBJECT' | 'SCALAR' ;

type
//    : 'ROW' '(' name scalar ( ',' name scalar )* ')'
    : 'ROW' '(' name type ( ',' name type )* ')'
    | 'SETOF' type
    | type 'ARRAY' ( '[' INTEGER ']' )?
    | type ( '[' INTEGER? ']' )+
    | scalar
    ;

scalar
    : 'INT'
    | 'INTEGER'
    | 'TINYINT'
    | 'SMALLINT'
    | 'BIGINT'
    | 'REAL'
    | 'DECFLOAT'
    | 'FLOAT' length?
    | 'DOUBLE' 'PRECISION'?
    | 'DECIMAL' precision?
    | 'DEC' precision?
    | 'NUMBER' precision?
    | 'NUMERIC' precision?
    | 'BOOL' | 'BOOLEAN'
    | 'INTERVAL'
    | 'VARBINARY'
    // Postgres?
    | 'BIT' 'VARYING'? length?
    | chars length?
    | 'BLOB' precision?
    | 'CLOB'
    | 'NCLOB'
    | 'DATE'
//    | ( 'TIMESTAMP' | 'TIME' ) length? ( ( 'WITH' | 'WITHOUT' ) 'LOCAL'? 'TIME' 'ZONE' )?
    | ( 'TIME' | 'TIMESTAMP' ) length? ( withWithout 'LOCAL'? timeZone )?

    | 'UUID'
    | 'JSON'
    | 'JSONB'
    | 'XML'

    | name+ precision?
    ;

 chars
    : ( 'CHARACTER' | 'CHAR' | 'NCHAR' ) 'VARYING'?
    | 'VARCHAR'
    | 'VARCHAR2'
    | 'NATIONAL' ( 'CHARACTER' | 'CHAR' ) 'VARYING'?
    ;

length
    : ( '(' INTEGER ')' ) ;

precision
    // TODO can this just be two INTEGERs?
    : '(' subterm ( ',' subterm )? ')'
    ;

values
    : 'VALUES' terms ;

array
    : 'ARRAY' arrayTerms  ;

    arrayTerms : '[' ( terms | arrayNested )? ']' ;
    arrayNested : arrayTerms ( ',' arrayTerms )* ;

function
    : 'TRIM' '(' ( 'BOTH' | 'LEADING' | 'TRAILING' )? term? 'FROM'? term ')'
    | 'SUBSTRING' '(' term 'FROM' term ( 'FOR' term )? ')'
    | 'JSON_OBJECTAGG' '(' jsonPairs onNull? uniqueKeys? ')' filter? over?
//    | 'EXTRACT' '(' timeUnit 'FROM' ( interval | datetime ) ')'
    | 'EXTRACT' '(' timeUnit 'FROM' subterm ')'
    | 'COLLECT' '(' ( 'DISTINCT' | 'UNIQUE' ) name orderBy? ')'
    | 'XMLATTRIBUTES' '(' xmlAttrib ( ',' xmlAttrib )* ')'
    | 'XMLCONCAT' '(' terms ')'
    | 'XMLELEMENT' '(' 'NAME'? name ( ',' terms )? ')'
    | 'XMLEXISTS' '(' subterm passing? ')'
    | 'XMLFOREST' '(' xmlAttrib ( ',' xmlAttrib )* ')'
    | 'XMLPARSE' '(' xmlContent term ( 'PRESERVE' | 'STRIP' ) 'WHITESPACE' ')'
    | 'XMLPI' '(' 'NAME' name ( ',' term )? ')'
    | 'XMLROOT' '(' 'XML' term ',' 'VERSION' ( term | 'NO' 'VALUE' ) ',' 'STANDALONE' ( 'YES' | 'NO' 'VALUE'? ) ')'
    | 'XMLSERIALIZE' '(' xmlContent term 'AS' type ')'
    // Postgres
    | 'ARRAY' '(' select ')'
    | '{fn' function '}' //  ODBC style
    // Generic syntax for all analytic functions?
    | qname '(' term respectIgnore? ')' respectIgnore? over?
    // Generic syntax for all aggregate functions?
    | qname
      '('
      ( ( qname '.' )? '*' | allDistinct? terms orderBy?
//    ( 'ON' 'OVERFLOW' ( 'ERROR' | 'TRUNCATE' name? withWithout 'COUNT' ))?
      ( 'ON' 'OVERFLOW' 'ERROR' )?
      ( 'SEPARATOR' term )? onNull? )?
      ')'
//      ( 'SEPARATOR' term )? onNull? )? respectIgnore? ')' // TODO: Oracle
      withinGroup?
      filter?
      ( 'FROM' firstLast )?
      respectIgnore? over?
//    | keyword? 'FUNCTION' keyword '(' terms? ')' // TODO: T-SQL style
//    | keyword '.' keyword '(' terms? ')' // TODO: T-SQL style?
    | 'CURRENT_DATE'
    | 'CURRENT_TIME'
    | 'CURRENT_TIMESTAMP'
    | 'SIN' '(' subterm ')'
    ;

// TODO: Maybe archetype rules for agg, win, etc functions?

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
            : ( 'UNBOUNDED' | 'CATEGORY' | term ) 'PRECEDING'
            | 'CURRENT' 'ROW'
            ;

        following
            : ( 'UNBOUNDED' | term ) 'FOLLOWING'
            | preceding
            ;

orderBy
    : 'ORDER' 'BY' orderByItem ( ',' orderByItem )*
    ;

    orderByItem
        : term sortDir? ( 'NULLS' firstLast )?
        ;

    sortDir
        : 'ASC'
        | 'DESC'
        // Postgres
//        | 'USING' compare
        | 'USING' ( EQ | COMPARE )
        ;

literal
    : ( '+' | '-' )? ( INTEGER | FLOAT )
    | BITS
    | BYTES
    | OCTALS
    | 'TRUE' | 'FALSE' | 'UNKNOWN' | 'NULL'
    | 'ON' | 'OFF'
    | 'DEFAULT'
    | datetime
    | jsonObject
    | jsonArray
    | PARAMETER
    | VARIABLE
    | string
    ;

datetime
    : 'DATE' string
    | ( '{d' | '{t' | '{ts' ) string '}'
    | ( 'TIME' | 'TIMESTAMP' ) ( withWithout timeZone )? string?
    ;

boolean
    : 'TRUE' | 'FALSE' | 'ON' | 'OFF' ;

interval
    : 'INTERVAL' subterm timeCast?
    ;

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

jsonArray
    : 'JSON_ARRAY' '(' ( terms | '(' select ')' )? formatJson? onNull? ')' ;

jsonObject
    : 'JSON_OBJECT' '(' jsonPairs? onNull? uniqueKeys? formatJson? ')' ;

    jsonPairs
        : jsonPair ( ',' jsonPair )* ;

        jsonPair
            : jsonKey ':' term
            | 'KEY'? jsonKey 'VALUE' term
            ;

            jsonKey
                : 'NULL' | string | name ;

qname
    : name ( '.' name )*
    ;

index
    : '[' ( term | term? ':' term? )? ']' ;

keyword
 : 'A'
 | 'ABORT'
 | 'ACTION'
 | 'ADD'
 | 'AFTER'
 | 'ALL'
 | 'ALTER'
 | 'ANALYZE'
 | 'AND'
 | 'ANY'
 | 'AS'
 | 'ASC'
 | 'ATTACH'
 | 'AUTOINCREMENT'
 | 'BEFORE'
 | 'BEGIN'
 | 'BETWEEN'
 | 'BY'
 | 'CASCADE'
 | 'CASE'
 | 'CAST'
 | 'CATEGORY'
 | 'CENTURY'
 | 'CHAR'
 | 'CHECK'
 | 'COLLATE'
 | 'COLUMN'
 | 'COLUMNS'
 | 'COMMIT'
 | 'CONFLICT'
 | 'CONSTRAINT'
 | 'COUNT'
 | 'CREATE'
 | 'CROSS'
 | 'CURRENT'
 | 'CURRENT_DATE'
 | 'CURRENT_TIME'
 | 'CURRENT_TIMESTAMP'
 | 'DECADE'
 | 'DATA'
 | 'DATABASE'
 | 'DAY'
 | 'DEFAULT'
 | 'DEFERRABLE'
 | 'DEFERRED'
 | 'DELETE'
 | 'DESC'
 | 'DETACH'
 | 'DISTINCT'
 | 'DROP'
 | 'EACH'
 | 'ELSE'
 | 'END'
 | 'ESCAPE'
 | 'EXCEPT'
 | 'EXCLUSIVE'
 | 'EXISTS'
 | 'EXPLAIN'
 | 'EXTRACT'
 | 'FAIL'
 | 'FILTER'
 | 'FIRST'
 | 'FOR'
 | 'FOREIGN'
// | 'FROM'
 | 'FULL'
 | 'GLOB'
 | 'GROUP'
 | 'HAVING'
 | 'HOUR'
 | 'IF'
 | 'IGNORE'
 | 'IMMEDIATE'
 | 'IN'
 | 'INDEX'
 | 'INDEXED'
 | 'INITIALLY'
 | 'INNER'
 | 'INSERT'
 | 'INSTEAD'
 | 'INTERSECT'
 | 'INT'
 | 'INTO'
 | 'IS'
 | 'ISNULL'
 | 'ISO_YEAR'
 | 'ISO_DAY_OF_WEEK'
 | 'JOIN'
 | 'KEY'
 | 'LAST'
 | 'LEFT'
 | 'LIKE'
 | 'LIMIT'
 | 'MATCH'
 | 'MILLENNIUM'
 | 'MINUTE'
 | 'MOD'
 | 'MONTH'
 | 'NAME'
 | 'NATURAL'
 | 'NO'
 | 'NOT'
 | 'NOTNULL'
// | 'NULL'
 | 'OF'
 | 'OFFSET'
 | 'ON'
 | 'OR'
 | 'ORDER'
 | 'OUTER'
 | 'PLAN'
 | 'PRAGMA'
 | 'PRIMARY'
 | 'QUARTER'
 | 'QUERY'
 | 'RAISE'
 | 'RECURSIVE'
 | 'REFERENCES'
 | 'REGEXP'
 | 'REINDEX'
 | 'RELEASE'
 | 'RENAME'
 | 'REPLACE'
 | 'RESTRICT'
 | 'RIGHT'
 | 'ROLLBACK'
 | 'ROW'
 | 'SAVEPOINT'
 | 'SCHEMA'
 | 'SECOND'
 | 'SELECT'
 | 'SET'
 | 'SETTINGS'
 | 'SEQUENCE'
 | 'SOME'
 | 'SUBSTRING'
 | 'TABLE'
 | 'TEMP'
 | 'TEMPORARY'
 | 'TEST'
 | 'THEN'
 | 'TO'
 | 'TRANSACTION'
 | 'TRIGGER'
 | 'TYPE'
 | 'UNION'
 | 'UNIQUE'
 | 'UPDATE'
 | 'USER'
 | 'USING'
 | 'VACUUM'
 | 'VALUE'
 | 'VALUES'
 | 'VARCHAR'
 | 'VIEW'
 | 'VIRTUAL'
 | 'WHEN'
 | 'WHERE'
 | 'WITH'
 | 'WITHOUT'
 | 'YEAR'
 ;

allDistinct
    : 'ALL' | 'DISTINCT' ;

firstLast
    : 'FIRST' | 'LAST' ;

formatJson
    : 'FORMAT' 'JSON' ;

onNull
    : ( 'NULL' | 'ABSENT' ) 'ON' 'NULL' ;

respectIgnore
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
//    : name | string ;
    : ID | STRING | UNICODE_NAME uescape? | keyword;

names
    : '(' name ( ',' name )* ')' ;

qnames0
    : qname ( ',' qname )* ;

qnames
    : '(' qname ( ',' qname )* ')' ;

// TODO separate rules for valid identifiers, valid aliases, and valid function names
name
    : ID
    | BRACKETS
//    | STRING
    | UNICODE_NAME uescape?
    | DOLLARS
    | keyword
    ;

string
    : STRING+
    | UNICODE_STRING STRING* uescape?
    | NATIONAL_STRING STRING*
    ;

    uescape
        : 'UESCAPE' STRING ;

UNICODE_NAME
    : 'U&' ID ;

UNICODE_STRING
    : 'U&' STRING ;

NATIONAL_STRING
    :  [NE] STRING ;

STRING
    // TODO allow newlines within STRINGs?
    // TODO allow newlines, but not whitespace, between STRINGs?
    : ( 'U&' | 'N' | 'E' )? '\'' ( ~'\'' | '\'\'' )* '\'' ;

// TODO no newlines etc within IDs?
ID
    : '"' ( ~'"' | '""' )* '"'
    | '`' ( ~'`' | '``' )* '`'
    | HEAD BODY*
    ;

fragment HEAD options { caseInsensitive=false; }
    : [a-zA-Z_]
    // Valid characters from 0x80 to 0xFF
    | [\u00AA\u00B5\u00BA\u00C0-\u00D6\u00D8-\u00F6\u00F8-\u00FF]

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
fragment BODY options { caseInsensitive=false; }
    : [0-9#$@_]
    | HEAD
//    | '\u00B7'
//    | '\u0300' .. '\u036F'
//    | '\u203F' .. '\u2040'
    ;

BRACKETS
    : 
     { bracketsEnabled }?
    '[' ~']'* ']' ;

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

// TODO This variant ensures even number of digits. Does it matter?
//BLOB
//    : 'X' HEXHEX ( ' ' HEXHEX )* ;
//
//    fragment HEXHEX
//        : '\'' ( HEX HEX ' '? )* '\'' ;

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
    : '?' DIGIT* ;

fragment DIGIT
    : [0-9] ;

// TODO separate alts for each style & dialect
VARIABLE
    : [:$] ( INTEGER | ID )
    | '@' '@'? HEAD BODY*
    ;

COMMENT
//    : '--' .*? ( '\n' | EOF ) -> channel( HIDDEN ) ;
    // TODO is this better? cuz doesn't consume '\n' ?
    : '--' ~ [\r\n]* -> channel( HIDDEN ) ;

BLOCK_COMMENT
    : '/*' ( BLOCK_COMMENT | . )*? '*/' -> channel( HIDDEN ) ;

// \u000B line (vertical) tab
// \u000C form feed
WHITESPACE : [ \b\t\r\n\u000B\u000C] -> channel ( HIDDEN ) ;

EQ : '=' ;

COMPARE
    : '==' | '<>' | '!=' | '<' | '<=' | '>' | '>=' | '&&'
    ;

ASSIGN
    : ':=' | '+=' | '-=' | '*=' | '/=' | '%=' | '&=' | '^=' | '|='
    | '~' | '~*' | '!~' | '!~*'
    ;


// TODO BOZO this crude OPERATOR token accepts way more than spec'd

// Postgres 4.1.3 https://www.postgresql.org/docs/current/sql-syntax-lexical.html#SQL-SYNTAX-OPERATORS
//OPERATOR
////    : ( '+' | '-' | [*/<>=~!@#%^&|`?] )+
////    : ( '+' | '-' | [*/<>=~!@#%^&|`] )+
//    :
//     { operatorEnabled }?
//    [*/<>=!@#%^&|`]+
//    ;


// END