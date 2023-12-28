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

@parser::members
{

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
aaa2 : term ;

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
    | select
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
//        | COMPARE
//        | assign
//        | ASSIGN
//        | EQ
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
        '(' select ')'
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
    : with? 'MERGE' 'INTO' 'ONLY'? name ( 'AS'? name )?
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

select
    : with? combine
      // Because various dialects order these clauses differently
      // TODO: Allow just one of each clause (somehow)
      ( orderBy | offset | fetch | limit | forUpdate )*
    ;

    combine
        : combine ( 'INTERSECT' | 'MINUS' ) combine
        | combine ( 'UNION' 'ALL'? | 'EXCEPT' ) combine
        | combine 'MULTISET' combine
        | selectCore
        ;

    selectCore
        : 'SELECT' quantifier?
          top?
          ( item ( ',' item )* ','? )?
          into?
          ( 'FROM' sources )?
          where?
          groupBy?
          having?
          windows?
          qualify?
        | '(' select ')'
        | 'VALUES' terms
        // MySQL table statement
        | 'TABLE' qname
        ;

    sources
        : sources ',' sources
          // TODO validate this. added ON clause to pass sqlite's tkt2141.test
          ( 'ON' term )?
        | sources join sources  ( 'ON' term | 'USING' qnames )?
        | '(' sources ')' sourceAlias?
        | source
        ;

        // TODO add 'LATERAL'
        join
            : 'NATURAL'?
              ( 'LEFT' ( 'SEMI' | 'ANTI' | 'OUTER' )?
              | ( 'RIGHT' | 'FULL' ) 'OUTER'?
              | 'INNER'
              | 'CROSS'
              )?
              'JOIN'
            ;

    source
        : ( qname | '(' qname ')' )  sourceAlias?
          ( ( 'USE' 'INDEX' names ) // H2
          | indexedBy // SQLite
          )?
        | table sourceAlias?
        ;

        sourceAlias : 'AS'? alias names? ;

    table
        : '(' select ')'
        | 'VALUES' terms
//        // MySQL table statement
//        | 'TABLE' qname
        | tableFunc
        // H2 data change delta table http://h2database.com/html/grammar.html#data_change_delta_table
        // DB2 intermediate result table
        | ( 'NEW' | 'OLD' | 'FINAL' ) 'TABLE' '(' ( delete | insert | merge | update ) ')'
        // PL/SQL table collection expression
        | 'TABLE' '(' term ')'
        | 'JSON_TABLE' // TODO
        | xmlTable
        | unnest
        | pivot
        | unpivot
        ;

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
//        : term ( 'AS'? name )?                       # ItemTerm
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
    : ( '+' | '-' | TILDE ) subterm # SubtermFixme
    | ( 'NOT' | '!' ) subterm # SubtermFixme
    | value ( '.' name | index | '::' type )* # SubtermValue
    | subterm 'COLLATE' type # SubtermFixme
    | <assoc=right> subterm '^' subterm # SubtermFixme
    | subterm ( '<<' | '>>' | '&' | '|' ) subterm # SubtermFixme
    | subterm  ( '||' | '->' | '->>' ) subterm # SubtermFixme
    | subterm 'IS' 'NOT'? ( 'NULL' | 'UNKNOWN' | 'TRUE' | 'FALSE' | 'DISTINCT' ) # SubtermFixme
    | subterm ( 'ISNULL' | 'NOTNULL' | 'NOT' 'NULL' ) # SubtermFixme
    | subterm 'IS' 'NOT'? 'DISTINCT' 'FROM' subterm # SubtermFixme
    | subterm 'IS' 'NOT'? 'OF' 'TYPE'? '(' 'ONLY'? type ( ',' type )* ')' # SubtermFixme
    | subterm ( '*' | '/' | 'DIV' | '%' | 'MOD' ) subterm # SubtermFixme
    | subterm ( '+' | '-' ) subterm # SubtermFixme
    | subterm compare subterm # SubtermFixme

    /*
     ANTLR's left-recursion magic doesn't match this...
        | subterm 'NOT'? likes subterm ( 'ESCAPE' subterm )?
      ... so manually split alts as workaround
    */
    | subterm 'NOT'? likes subterm 'ESCAPE' subterm # SubtermFixme
    | subterm 'NOT'? likes subterm # SubtermFixme

    | subterm 'NOT'? 'LIKE' ( 'ANY' | 'ALL' ) '(' terms ')' # SubtermFixme
    | subterm 'NOT'? 'IN' ( '(' ( ( term | select ) ( ',' ( term | select ) )* )? ')' | name )? # SubtermFixme
    | subterm 'NOT'? 'BETWEEN' ( 'ASYMMETRIC' | 'SYMMETRIC' )? subterm 'AND' subterm # SubtermFixme
//    | subterm compare ( 'ANY' | 'SOME' | 'ALL' ) '(' select ')' # SubtermFixme
    | ( 'ANY' | 'SOME' | 'ALL' ) '(' select ')' # SubtermFixme
    | VARIABLE assign subterm # SubtermFixme
//        | 'RAISE' '(' ('IGNORE' | ('ROLLBACK' | 'ABORT' | 'FAIL') ',' string) ')'  # PredicateRaise
//   | '(' subterm ',' subterm ')' 'OVERLAPS' '(' subterm ',' subterm ')' # SubtermOverlaps
//    // CrateDB ?
////    | 'MATCH' '(' name ',' string ')' 'USING' qname 'WITH' '(' subterm ')'
// TODO: H2's INTERSECTS for 2D bounding boxes. Better as a function?
// | row 'INTERSECTS' '(' term ',' term ')'
    ;

// BOZO always update these alts as subterm's (related) alts change
predicate
    : 'IS' 'NOT'? ( 'NULL' | 'UNKNOWN' | 'TRUE' | 'FALSE' | 'DISTINCT' ) # PredicateFixme
    | ( 'ISNULL' | 'NOTNULL' | 'NOT' 'NULL' ) # PredicateFixme
    | 'IS' 'NOT'? 'DISTINCT' 'FROM' term # PredicateFixme
    | 'IS' 'NOT'? 'OF' 'TYPE'? '(' 'ONLY'? type ( ',' type )* ')' # PredicateFixme
    | compare subterm # PredicateFixme
    | 'NOT'? likes subterm ( 'ESCAPE' subterm )? # PredicateLIKE
    | 'NOT'? 'LIKE' ( 'ANY' | 'ALL' ) '(' terms ')' # PredicateFixme
    | 'NOT'? 'IN' ( '(' ( ( term | select ) ( ',' ( term | select ) )* )? ')' | name )? # PredicateIN
    | 'NOT'? 'BETWEEN' ( 'ASYMMETRIC' | 'SYMMETRIC' )? subterm 'AND' subterm # PredicateBETWEEN
//    | compare ( 'ANY' | 'SOME' | 'ALL' ) '(' select ')' # PredicateOperator
//    | 'EXISTS' LPAREN selectStatement RPAREN
    ;

//test
//        | subterm 'IS' 'NOT'? logicals
//        | subterm 'IS' 'NOT'? 'JSON' jsonType? uniqueKeys?
//        | subterm 'IS' 'NOT'? subterm

//    predicate
//        : ( EQ | COMPARE | ASSIGN ) term                                        # PredicateOperator
//        | ( 'ISNULL' | 'NOTNULL' | 'NOT' 'NULL' )                                  # PredicateNull
////        | 'IS' 'NOT'? truth                                                        # PredicateIsTruth
//        // PL/SQL dialect?
//        | 'IS' 'NOT'? logicals                                                     # PredicateLogical
//        | 'IS' 'NOT'? 'DISTINCT' 'FROM' term                                    # PredicateDistinct
//        | 'IS' 'NOT'? 'OF' 'TYPE'? '(' 'ONLY'? type ( ',' type )* ')'              # PredicateOfType
//        | 'IS' 'NOT'? 'JSON' jsonType? uniqueKeys?                                 # PredicateJSON
//        | 'IS' 'NOT'? term                                                         # PredicateIS
////        | 'NOT'? 'IN' '(' ( table | terms )? ')'                                   # PredicateIN
//        | 'NOT'? 'IN' '(' terms? ')'                                               # PredicateIN
//        // PL/SQL dialect
////        | 'NOT'? 'IN' subterm                                                      # PredicateIN
//        ;

//        logicals
//            : 'NAN' | 'INFINITE' | 'PRESENT' | 'A' 'SET' | 'EMPTY'
//            ;
//
//        jsonType
//            : 'VALUE' | 'ARRAY' | 'OBJECT' | 'SCALAR' ;


    likes : 'LIKE' | 'RLIKE' | 'ILIKE' | 'REGEXP' | 'GLOB' | 'MATCH' ;

    assign
        : ASSIGN
        ;

    compare
        : EQ | COMPARE | TILDE | MATCH
        ;

// TODO merge 'value' w/ 'subterm'?
value
    : literal
    | case
    | 'INTERVAL' subterm timeCast
    | value 'AT' ( 'LOCAL' | timeZone string )
    | ( 'NEXT' | 'CURRENT' ) 'VALUE' 'FOR' qname
    | array
    | '(' select ')'
    | function
    | { fixID(); } id
    | 'ROW'? '(' terms? ')'
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
    // TODO value or literal
    : 'DATE' string
    | ( '{d' | '{t' | '{ts' ) string '}'

    // TODO function
    | 'CURRENT_DATE'
//    | 'TIMESTAMP' string
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

values
    : 'VALUES' terms ;

array
    : 'ARRAY' arrayTerms  ;

    arrayTerms : '[' ( terms | arrayNested )? ']' ;
    arrayNested : arrayTerms ( ',' arrayTerms )* ;


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

params : '(' INTEGER? ')' ;

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
    | 'JSON_ARRAY' '(' ( terms | '(' select ')' )? formatJson? onNull? ')'
    | ( 'CAST' | 'TRY_CAST' ) '(' term 'AS' type ( 'FORMAT' string )? ')'
    | 'UNIQUE' ( ( 'ALL' | 'NOT' )? 'DISTINCT' )? '(' select ')'
    | 'EXISTS' '(' select ')'
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

    | 'ARRAY' '(' select ')' // Postgres
    | '{fn' function '}' //  ODBC style

    | aggregateFunction
    ;


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
    : id | string
    ;

qnames0
    : qname ( ',' qname )* ;

qnames
    : '(' qname ( ',' qname )* ')' ;

qname
    : name ( '.' name )*
    ;

name
    : id
    | string
    ;

names
    : '(' name ( ',' name )* ')' ;

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

    // all tokens except IDs
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

    // and anything lexer doesn't recognize
    | OTHER

    // finally, tokens we setType( RESERVED )
    | RESERVED
    )
    // special rule to accept UNICODE IDs
    | UNICODE_ID uescape?
    // special rule to accept T-SQL style brackets
    | '[' .*? ']'
    ;


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

// TODO combine strings
STRING
//    : ( 'U&' | 'N' | 'E' )? '\'' ( ~'\'' | '\'\'' )* '\'' ;
    : '\'' ( ~( '\'' ) | '\'\'' )* '\'' ;

BACKTICKS : '`' ( ~( '`' ) | '``' )* '`' ;

QUOTED : '"' ( ~( '"' ) | '""' )* '"' ;

// TODO combine IDs
// TODO figure out why 'varchar_whatever' isn't an ID token
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

// TODO This variant ensures even number of digits. Does it matter?
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
WHITESPACE : [ \b\t\r\n\u000B\u000C] -> channel ( HIDDEN ) ;

COMMENT
//    : '--' .*? ( '\n' | EOF ) -> channel( HIDDEN ) ;
    // TODO is this better? cuz doesn't consume '\n' ?
    : '--' ~ [\r\n]* -> channel( HIDDEN ) ;

BLOCK_COMMENT
    : '/*' ( BLOCK_COMMENT | . )*? '*/' -> channel( HIDDEN ) ;


RESERVED : '\u007F';

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


