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

options { caseInsensitive=true; }

@lexer::members
{
    public boolean bracketsEnabled = false;
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
    | query
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
      | 'ADD' 'COLUMN'? qname columnDef
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
        | compare | assign
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
        'VIEW' ifNotExists? qname columns? 'AS' query ;

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
        '(' query ')'
        ( 'SEARCH' ( 'BREADTH' | 'DEPTH' ) 'FIRST' 'BY' name ( ',' name )* 'SET' name )?
        ( 'CYCLE' name ( ',' name )* 'SET' name ( 'TO' literal 'DEFAULT' literal )? ( 'USING' name )? )?
        ;

delete
    : with? 'DELETE' 'FROM' 'ONLY'? qname ( 'AS' name )? indexedBy?
      // Postgres
      ( 'USING' sources ( ',' sources )* )?
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
        // MySQL table statement
        | 'TABLE' table
        | values
        | '(' query ')'
        ;

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

select
    : 'SELECT' quantifier? top? ( item ( ',' item )* ','? )? into?
      ( 'FROM' sources )? where? groupBy? having? windows? qualify?
    ;

    quantifier
        : 'DISTINCT' ( 'ON' '(' terms ')' )?
        | 'ALL'
        | 'UNIQUE'
        ;

    top
        : 'TOP' ( INTEGER | FLOAT | '(' term ')' ) 'PERCENT'? withTies? ;

    item
        : (( table '.' )? '*' ) ( 'EXCEPT' columns )?  # ItemTableRef
        | term ( 'AS'? alias )?                        # ItemTerm
        ;

    into
        : 'INTO' ( temporary | 'UNLOGGED' )? 'TABLE'? ( qname | VARIABLE ) ( ',' ( qname | VARIABLE ) )* ;

    sources
        :  source ( join source ( 'ON' term | 'USING' columns )* | pivot | unpivot )* ;

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
                'FOR' ( column | columns )
                'IN' '(' aliasedTerms ')'
              ')'
            // PL/SQL
            | 'PIVOT' 'XML'
              '('
                aliasedFunction ( ',' aliasedFunction )*
                'FOR' ( column | columns )
                'IN' '(' ( query | 'ANY' ) ')'
              ')'
            ;

            aliasedFunction : function ( 'AS'? alias )? ;

        unpivot
            : 'UNPIVOT' (( 'INCLUDE' | 'EXCLUDE' ) 'NULLS' )?
              '('
                ( column | columns )
                'FOR' ( column | columns )
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

source
    : ( unnest
      | values
      | tableFunc
      // H2 data change delta table http://h2database.com/html/grammar.html#data_change_delta_table
      // DB2 intermediate result table
      | ( 'NEW' | 'OLD' | 'FINAL' ) 'TABLE' '(' ( delete | insert | merge | update ) ')'
      // PL/SQL table collection expression
      | 'TABLE' '(' term ')'
      | 'JSON_TABLE' // TODO
      | xmlTable
      | function
      | '(' sources ')'
      | table
      | query
      )
      ( 'AS'? alias names? )?
      // H2
      ( 'USE' 'INDEX' names )?
      // SQLite
      ( 'NOT' 'INDEXED' | 'INDEXED' 'BY' qname )?
    ;

    // TODO: Should this part of rule 'function'?
    unnest
        : 'UNNEST' '(' array ( ',' array )* ')' ( 'WITH' 'ORDINALITY' )? ;

    // H2 table function http://h2database.com/html/functions.html#table
    tableFunc
        : ( 'TABLE' | 'TABLE_DISTINCT' ) '(' tableFuncParam ( ',' tableFuncParam )* ')' ;

        tableFuncParam
            : name ( type | qname | 'NULL' ) '=' subterm ;

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
    : '(' term ')'
    | subterm
    // TODO redundant w/ subterm unary. still ok?
    | 'NOT' term
    | term 'AND' term
    | term 'XOR' term
//    | term ( 'OR' | '||' ) term
    | term 'OR' term
    // CrateDB ?
    | 'MATCH' '(' name ',' string ')' 'USING' qname 'WITH' '(' subterm ')'
    | VARIABLE assign term
   ;

    assign
        : '=' | ':=' | '+=' | '-=' | '*=' | '/=' | '%=' | '&=' | '^=' | '|=' ;

subterm
    : literal                                                       # SubtermLiteral
    | ( '+' | '-' | '~' | '!' | 'NOT' ) subterm                               # SubtermUnary
    | subterm '::' type                                               # SubtermCast
    // Postgres?
    | subterm ( '::' subterm )+                                       # SubtermScope
    | subterm index+                                                  # SubtermIndex
    | function ( '.' function )*                                      # SubtermFunction

//    | qname                                                          # SubtermQNAME
    | column                                                          # SubtermColumn

    // TODO: BINARY
    | subterm 'COLLATE' name                                         # SubtermCOLLATE
    | subterm '&' subterm                                             # SubtermBinary
    | subterm '|' subterm                                             # SubtermBinary
    | subterm predicate                                               # SubtermPredicate
    | subterm '||' subterm                                            # SubtermBinary
    | subterm ( '->' | '->>' ) subterm                                # SubtermBinary
    | subterm ( '<<' | '>>' ) subterm                                 # SubtermBinary
    | subterm 'AT' ( 'LOCAL' | timeZone string )                      # SubtermTime
    // PL/SQL, ODBC
    | subterm interval                                                # SubtermInterval
    | 'INTERVAL' subterm interval?                                    # SubtermInterval
    | query                                                           # SubtermQuery
    | array                                                           # SubtermArray
    | case                                                            # SubtermCase
    | ( 'CAST' | 'TRY_CAST' ) '(' term 'AS' type ')'                  # SubtermCast
    | 'EXISTS' '(' query ')'                                          # SubtermEXISTS
    | 'UNIQUE' ( ( 'ALL' | 'NOT' )? 'DISTINCT' )? '(' query ')'       # SubtermUNIQUE
    | ( 'NEXT' | 'CURRENT' ) 'VALUE' 'FOR' column                     # SubtermSequence
    // PL/SQL
    | '(' subterm ',' subterm ')' 'OVERLAPS' '(' subterm ',' subterm ')' # SubtermOverlaps
//   TODO  | sequenceValueExpression
    // TODO these may need to be in a parent rule, for proper precedence
    | 'ROW'? '(' terms? ')' ( '.' name )?                             # SubtermRow
    | function ( '.' function )*                                      # SubtermFunction
    | <assoc=right> subterm '^' subterm                               # SubtermBinary
    | subterm ( '*' | '/' | 'DIV' | '%' | 'MOD' ) subterm             # SubtermBinary
    | subterm ( '+' | '-' ) subterm                                   # SubtermBinary
    ;

    case
        : 'CASE' term ( 'WHEN' ( terms | predicate ) 'THEN' term )+ ( 'ELSE' term )? 'END'   // # CaseSimple
        | 'CASE' ( 'WHEN' term 'THEN' term )+ ( 'ELSE' term )? 'END'                        //  # CaseSearch
        ;

    predicate
        : compare subterm                                                          # PredicateCompare
        | ( 'ISNULL' | 'NOTNULL' | 'NOT' 'NULL' )                                  # PredicateIsNull
        | 'IS' 'NOT'? truth                                                        # PredicateIsTruth
        // PL/SQL dialect?
        | 'IS' 'NOT'? logicals                                                     # PredicateLogical
        | 'IS' 'NOT'? 'DISTINCT' 'FROM' subterm                                    # PredicateDistinct
        | 'IS' 'NOT'? 'OF' 'TYPE'? '(' 'ONLY'? type ( ',' type )* ')'              # PredicateOfType
        | 'IS' 'NOT'? 'JSON' jsonType? uniqueKeys?                                 # PredicateJSON
        | 'IS' 'NOT'? term                                                         # PredicateIS
        | 'NOT'? 'IN' '(' ( query | terms )? ')'                                   # PredicateIN
        // PL/SQL dialect
        | 'NOT'? 'IN' subterm                                                      # PredicateIN
        | 'NOT'? 'BETWEEN' ( 'ASYMMETRIC' | 'SYMMETRIC' )? subterm 'AND' subterm   # PredicateBETWEEN
        | 'NOT'? ( 'LIKE' | 'ILIKE' | 'REGEXP' | 'GLOB' | 'MATCH' ) subterm ( 'ESCAPE' ( string | term ) )?      # PredicateLIKE
        | 'RAISE' '(' ('IGNORE' | ('ROLLBACK' | 'ABORT' | 'FAIL') ',' string) ')'  # PredicateRaise
        ;

        compare
            : '=' | '<>' | '!=' | '^=' | '<' | '<=' | '>' | '>=' | '&&'
            // Postgres match regex
            | '~' | '~*' | '!~' | '!~*'
            | OPERATOR
            ;

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
    | 'DOUBLE' 'PRECISION'
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
    | 'EXTRACT' '(' name 'FROM' .*? ')' // TODO
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
    | 'ARRAY' '(' query ')'
    | '{fn' function '}' //  ODBC style
    // Generic syntax for all analytic functions?
    | qname '(' term respectIgnore? ')' respectIgnore? over?
    // Generic syntax for all aggregate functions?
    | qname
      '(' ( ( table '.' )? '*' | allDistinct? terms orderBy?
//    ( 'ON' 'OVERFLOW' ( 'ERROR' | 'TRUNCATE' name? withWithout 'COUNT' ))?
      ( 'ON' 'OVERFLOW' 'ERROR' )?
      ( 'SEPARATOR' term )? onNull? )? ')'
//      ( 'SEPARATOR' term )? onNull? )? respectIgnore? ')' // TODO: Oracle
      withinGroup? filter? ( 'FROM' firstLast )?
      respectIgnore? over?
//    | keyword? 'FUNCTION' keyword '(' terms? ')' // TODO: T-SQL style
//    | keyword '.' keyword '(' terms? ')' // TODO: T-SQL style?
    | 'CURRENT_DATE'
    | 'CURRENT_TIME'
    | 'CURRENT_TIMESTAMP'
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
        | 'USING' compare
        ;

literal
    : ( '+' | '-' )? ( INTEGER | FLOAT )
    | BITS
    | BYTES
    | OCTALS
//    | BLOB
//    | BLOB2
    | truth | boolean
    | 'DEFAULT'
    | 'DATE' string
    | ( '{d' | '{t' | '{ts' ) string '}'
    | ( 'TIME' | 'TIMESTAMP' ) ( withWithout timeZone )? string?
    | jsonObject
    | jsonArray
    | PARAMETER
    | VARIABLE
    | string
    ;

truth
    : 'TRUE' | 'FALSE' | 'UNKNOWN' | 'NULL' ;

boolean
    : 'TRUE' | 'FALSE' | 'ON' | 'OFF' ;

interval
    : timeUnit precision? ( 'TO' timeUnit precision? )?
    ;

timeUnit
    : 'EPOCH' | 'MILLENNIUM' | 'CENTURY' | 'DECADE' | 'YEAR' | 'YEARS'
    | 'QUARTER' | 'MONTH' | 'MONTHS' | 'WEEK' | 'WEEKS' | 'DAY' | 'DAYS'
    | 'HOUR' | 'HOURS' | 'MINUTE' | 'MINUTES' | 'SECOND' | 'SECONDS'
    | 'MILLISECOND' | 'MICROSECOND' | 'NANOSECOND' ;

jsonArray
    : 'JSON_ARRAY' '(' ( terms | '(' query ')' )? formatJson? onNull? ')' ;

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

columns
    : '(' column ( ',' column )* ')' ;

// TODO: remove 'column' and 'table' rules, use 'qname' instead
column
    : name ( '.' name )*;

table
    : name ( '.' name )*;

qname
    : VARIABLE
    | PARAMETER
    | name ( '.' name )*
    ;

index
    : '[' ( term | term? ':' term? )? ']' ;

// All tokens in this grammars which are unreserved SQL keywords. Keep up to
// date manually. (Ugh.)
unreserved
    : ~(
        // Exclude tokens which are SQL's reserved keywords...
//        'ALL'
//        | 'AND'
         'AND'
//        | 'ANY'
        | 'ARRAY'
//        | 'AS'
        | 'BETWEEN'
        | 'BOTH'
//        | 'CASE'
        | 'CAST'
        | 'CHECK'
//        | 'CONSTRAINT'
//        | 'CROSS'
//        | 'DAY'
//        | 'DELETE'
        | 'DEFAULT'
        | 'DISTINCT'
        | 'ELSE'
        | 'END'
        | 'EXCEPT'
        | 'EXISTS'
//        | 'FALSE'
        | 'FOR'
        | 'FOREIGN'
//        | 'FROM'
//        | 'FULL'
        | 'GENERATED' // SQLite
        | 'GROUP'
//        | 'GROUPS'
        | 'HAVING'
//        | 'HOUR'
//        | 'IF'
        | 'IN'
//        | 'INNER'
//        | 'INSERT'
        | 'INTERSECT'
        | 'INTERVAL'
//        | 'INTO'
        | 'IS'
        | 'JOIN'
//        | 'KEY'
        | 'LEADING'
//        | 'LEFT'
//        | 'LIKE'
//        | 'LIMIT'
        | 'MINUS'
//        | 'MINUTE'
//        | 'MONTH'
//        | 'NATURAL'
        | 'NOT'
//        | 'NULL'
//        | 'OF'
        | 'OFFSET'
        | 'ON'
        | 'OR'
//        | 'ORDER'
//        | 'OVER'
//        | 'PARTITION'
//        | 'PRIMARY'
        | 'QUALIFY'
//        | 'RANGE'
//        | 'REGEXP'
//        | 'RIGHT'
//        | 'ROW'
//        | 'ROWNUM'
        | 'ROWS'
//        | 'SECOND'
        | 'SELECT'
//        | 'SESSION_USER'
//        | 'SET'
//        | 'SOME'
        | 'SYMMETRIC'
//        | 'SYSTEM_USER'
//        | 'TABLE'
//        | 'TO'
//        | 'TOP'
        | 'TRAILING'
//        | 'TRUE'
        | 'UESCAPE'
        | 'UNION'
        | 'UNIQUE'
        | 'UNKNOWN'
//        | 'USER'
        | 'USING'
//        | 'VALUE'
        | 'VALUES'
        | 'WHEN'
        | 'WHERE'
//        | 'WINDOW'
        | 'WITH'
//        | 'YEAR'

        // ...And exclude all the other tokens which cannot be a keyword or name
        | INTEGER | FLOAT | BITS | BYTES | OCTALS
//        | BLOB | BLOB2
        | PARAMETER | VARIABLE
        | STRING | UNICODE_STRING | NATIONAL_STRING
        | ';' | '(' | ')' | '[' | ']' | ',' | '.' | '*' | '=' | ':=' | '<>' | '!=' | '<' | '<=' | '>' | '>=' | '&&'
       )
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
    : name | string ;

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
    | STRING
    | UNICODE_NAME uescape?
    | DOLLARS
    | VARIABLE
    | PARAMETER
    | unreserved
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
    : '\'' ( ~'\'' | '\'\'' )* '\'' ;

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
    : [0-9#$@]
    | HEAD
    | '\u00B7'
    | '\u0300' .. '\u036F'
    | '\u203F' .. '\u2040'
    ;

BRACKETS
    : { bracketsEnabled }? '[' ~']'* ']' ;

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

// TODO BOZO this crude OPERATOR token accepts way more than spec'd
// Postgres 4.1.3 https://www.postgresql.org/docs/current/sql-syntax-lexical.html#SQL-SYNTAX-OPERATORS
OPERATOR
//    : ( '+' | '-' | [*/<>=~!@#%^&|`?] )+
//    : ( '+' | '-' | [*/<>=~!@#%^&|`] )+
    : [*/<>=~!@#%^&|`]+
    ;

// END