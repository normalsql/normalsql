// Copyright 2010-2023 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

/*
 NormalSQL.g4, SQL DML grammar for ANTLR 4.x

 Style:

 Child rules are indented when its (mostly) only used by a parent rule.

   Heuristics:
      Create subrules to ease parse tree navigation.
      Per DRY, create subrule for 3 (sometimes 2) or more copypastas.

*/

grammar NormalSQL;

options { caseInsensitive=true; }

script
    : statement? ( ';' statement? )* EOF ;

statement
    // DML
    : delete
    | insert
    | merge
    | query
    | update

    // DDL
    | drop
    | create
    // | set // TODO
    ;

drop
    : 'DROP' 'MATERIALIZED' 'VIEW' ifExists? table
    | 'DROP' 'SCHEMA' ifExists? table ( 'CASCADE' | 'RESTRICT' )?
    | 'DROP' 'TABLE' ifExists? table
    | 'DROP' 'TEMPORARY'? 'FUNCTION' ifExists? table ( '(' ( type ( ',' type )* )? ')' )?
    | 'DROP' 'VIEW' ifExists? table
    | 'DROP' 'ROLE' name
    ;

    ifExists : 'IF' 'EXISTS' ;

create
//    : 'CREATE' (OR REPLACE)? TEMPORARY? FUNCTION tableRef '(' (sqlParameterDeclaration (',' sqlParameterDeclaration)*)? ')' RETURNS type (COMMENT string)? routineCharacteristics routineBody
//    | 'CREATE' (OR REPLACE)? VIEW tableRef (SECURITY (DEFINER | INVOKER))? AS query
//    | 'CREATE' MATERIALIZED VIEW (IF NOT EXISTS)? tableRef (COMMENT string)? (WITH properties)? AS (query | '(' query ')' )
//    | 'CREATE' ROLE id (WITH ADMIN grantor)?
//    | 'CREATE' SCHEMA (IF NOT EXISTS)? tableRef (WITH properties)?
    : 'CREATE' 'TABLE' ifNotExists? table '(' columnDef ( ',' columnDef )* ')' // (COMMENT string)? (WITH properties)?
//    | 'CREATE' TABLE ifNotExists? tableRef columnAliases? (COMMENT string)? (WITH properties)? AS (query | '(' query ')' ) (WITH (NO)? DATA)?
//    | 'CREATE' TYPE tableRef AS ( '(' sqlParameterDeclaration (',' sqlParameterDeclaration)* ')' | type)
    ;

    ifNotExists : 'IF' 'NOT' 'EXISTS' ;

    columnDef
        : name type ( 'NOT' 'NULL' )?  // (COMMENT string)? (WITH properties)?
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
    : with? 'DELETE' 'FROM' 'ONLY'? name ( 'AS' name )? indexedBy?
      // Postgres
      ( 'USING' from ( ',' from )* )?
      where? returning?
      ;

insert
    : with? ( 'INSERT' | 'REPLACE' )
      ( 'OR' ( 'ABORT' | 'FAIL'| 'IGNORE' | 'REPLACE' | 'ROLLBACK' ))?
      'INTO' qname ( 'AS' name )? names?
      overriding?
      ( source upsert* | 'DEFAULT' 'VALUES' )
      returning?
    ;

    overriding
        : 'OVERRIDING' ( 'SYSTEM' | 'USER' ) 'VALUE' ;

    // SQLite
    upsert
        : 'ON' 'CONFLICT' '(' terms ')' where? 'DO'
          ( 'NOTHING'
          | 'UPDATE' 'SET' setter ( ',' setter )* where?
          )
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
    : with? 'UPDATE' qname name? indexedBy?
      'SET' setter ( ',' setter )* from? where? returning? ;

    setter
        : ( qname | qnames ) '=' term ;

// SQLite
indexedBy
    : 'NOT' 'INDEXED' | 'INDEXED' 'BY' qname ;

returning
    : 'RETURNING' returned ( ',' returned )* ;

    returned
        : '*' | aliasedTerm
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
      ( 'FROM' from ( ',' from )* )? where? groupBy? having? windows? qualify?
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
        | aliasedTerm                                  # ItemColumn
        ;

    into
        : 'INTO' ( qname | VARIABLE ) ( ',' ( qname | VARIABLE ) )* ;

    from
        : source ( join | pivot | unpivot )* ;

        join
            : ( 'INNER' | ( 'LEFT' | 'RIGHT' | 'FULL' ) 'OUTER'? )? 'JOIN' source ( 'ON' term | 'USING' columns )*
            | ( 'CROSS' | 'NATURAL' 'FULL'? ) 'JOIN' source
            // TODO 'LATERAL'
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
      | '(' from ')'
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
    | <assoc=right> 'NOT' term
    | term 'AND' term
    | term 'XOR' term
//    | term ( 'OR' | '||' ) term
    | term 'OR' term
    | <assoc=right> VARIABLE assign term
   ;

    assign
        : '=' | ':=' | '+=' | '-=' | '*=' | '/=' | '%=' | '&=' | '^=' | '|=' ;

subterm
    : literal                                                         # SubtermLiteral
    | ( '+' | '-' | '~' | '!' ) subterm                               # SubtermUnary
    | subterm ( '::' subterm )+                                       # SubtermScope
    | subterm index+                                                  # SubtermIndex
    | function ( '.' function )*                                      # SubtermFunction
    | column                                                          # SubtermColumn
    // TODO: BINARY
    | subterm 'COLLATE' name                                          # SubtermBinary
    | subterm '&' subterm                                             # SubtermBinary
    | subterm '|' subterm                                             # SubtermBinary
    | subterm predicate                                               # SubtermPredicate
    | subterm '||' subterm                              # SubtermBinary
    | subterm ( '->' | '->>' ) subterm                                # SubtermBinary
    | subterm ( '<<' | '>>' ) subterm                                 # SubtermBinary
    | '(' terms ')' '.' name                                          # SubtermRowField
    | '(' subterm ')'                                                 # SubtermNested
    | '('')'                                                          # SubtermEmpty
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
    | 'ROW'? '(' terms? ')'                                           # SubtermRow
//   TODO  | sequenceValueExpression
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
        | 'IS' 'NOT'? truth                                                        # PredicateTruth
        | ( 'ISNULL' | 'NOTNULL' | 'NOT' 'NULL' )                                  # PredicateNulls
        // PL/SQL dialect
        | 'IS' 'NOT'? logicals                                                     # PredicateLogical
        | 'IS' 'NOT'? 'DISTINCT' 'FROM' subterm                                    # PredicateDistinct
        | 'IS' 'NOT'? 'OF' '(' type ( ',' type )* ')'                              # PredicateOfType
        | 'IS' 'NOT'? 'JSON' jsonType? uniqueKeys?                                 # PredicateJSON
        | 'NOT'? 'IN' '(' ( query | terms )? ')'                                   # PredicateIN
        // PL/SQL dialect
        | 'NOT'? 'IN' subterm                                                      # PredicateIN
        | 'NOT'? 'BETWEEN' ( 'ASYMMETRIC' | 'SYMMETRIC' )? subterm 'AND' subterm   # PredicateBETWEEN
        | 'NOT'? ( 'LIKE' | 'ILIKE' | 'REGEXP' ) subterm ( 'ESCAPE' string )?      # PredicateMatch
        | 'RAISE' '(' ('IGNORE' | ('ROLLBACK' | 'ABORT' | 'FAIL') ',' string) ')'  # PredicateRaise
        ;

        compare
            : '=' | '<>' | '!=' | '^=' | '<' | '<=' | '>' | '>=' | '&&'
            // Postgres match regex
            | '~' | '~*' | '!~' | '!~*'
            ;

        logicals
            : 'NAN' | 'INFINITE' | 'PRESENT' | 'A' 'SET' | 'EMPTY'
            | 'OF' 'TYPE'? '(' 'ONLY'? type (',' type )* ')'
            ;

        jsonType
            : 'VALUE' | 'ARRAY' | 'OBJECT' | 'SCALAR' ;

type
    : 'ROW' '(' name scalar ( ',' name scalar )* ')'
    | type 'ARRAY' ( '[' INTEGER ']' )?
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
    | 'FLOAT' ( '(' INTEGER ')' )?
    | 'DOUBLE' 'PRECISION'
    | 'DECIMAL' precision?
    | 'DEC' precision?
    | 'NUMBER' precision?
    | 'BOOLEAN'

    | 'VARBINARY'
    | 'BIT' 'VARYING'? // Postgres?
    | chars ( '(' INTEGER ')' )?

    | 'BLOB'
    | 'CLOB'
    | 'NCLOB'

    | 'DATE'
    | ( 'TIMESTAMP' | 'TIME' ) ( '(' INTEGER ')' )? ( 'WITH' 'LOCAL'? 'TIME' 'ZONE' )?

    | 'UUID'
//    | 'RAW'

    | name precision?
    ;

 chars
    : 'CHARACTER' 'VARYING'? // Postgres?
    | 'CHAR' 'VARYING'? // Postgres?
    | 'NCHAR' 'VARYING'? // Postgres?
    | 'VARCHAR'
    | 'VARCHAR2'
    | 'NATIONAL' ( 'CHARACTER' | 'CHAR' ) 'VARYING'? // Postgres?
    ;

precision
    : '(' subterm ( ',' subterm )? ')'
    ;

values
    : 'VALUES' terms ;

array
    : 'ARRAY' '[' terms? ']' ;

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

    | '{fn' function '}' //  ODBC style
    // Generic syntax for all analytic functions?
    | name '(' term respectIgnore? ')' respectIgnore? over?
    // Generic syntax for all aggregate functions?
    | name
      '(' ( ( table '.' )? '*' | allDistinct? terms orderBy?
//    ( 'ON' 'OVERFLOW' ( 'ERROR' | 'TRUNCATE' name? withWithout 'COUNT' ))?
      ( 'ON' 'OVERFLOW' 'ERROR' )?
      ( 'SEPARATOR' term )? onNull? )? ')'
//      ( 'SEPARATOR' term )? onNull? )? respectIgnore? ')' // TODO: Oracle
      withinGroup? filter? ( 'FROM' firstLast )?
      respectIgnore? over?
//    | keyword? 'FUNCTION' keyword '(' terms? ')' // TODO: T-SQL style
//    | keyword '.' keyword '(' terms? ')' // TODO: T-SQL style?
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
        : term ( 'ASC' | 'DESC' )? ( 'NULLS' firstLast )?
        ;

literal
    : ( '+' | '-' )? ( INTEGER | FLOAT )
    | BYTES
    | BLOB
    | truth
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

interval
    : timeUnit ( 'TO' timeUnit )?
    ;

    timeUnit
        : ( 'EPOCH' | 'YEAR' | 'MONTH' | 'DAY' | 'HOUR' | 'MINUTE' | 'SECOND' ) precision? ;

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
    : name ( '.' name )*;

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
        | 'CONSTRAINT'
        | 'CROSS'
//        | 'DAY'
//        | 'DELETE'
        | 'DEFAULT'
        | 'DISTINCT'
        | 'ELSE'
        | 'END'
        | 'EXCEPT'
        | 'EXISTS'
        | 'FALSE'
        | 'FOR'
        | 'FOREIGN'
        | 'FROM'
        | 'FULL'
        | 'GROUP'
        | 'GROUPS'
        | 'HAVING'
//        | 'HOUR'
//        | 'IF'
        | 'IN'
        | 'INNER'
//        | 'INSERT'
        | 'INTERSECT'
        | 'INTERVAL'
//        | 'INTO'
        | 'IS'
        | 'JOIN'
//        | 'KEY'
        | 'LEADING'
//        | 'LEFT'
        | 'LIKE'
//        | 'LIMIT'
        | 'MINUS'
//        | 'MINUTE'
//        | 'MONTH'
        | 'NATURAL'
        | 'NOT'
        | 'NULL'
//        | 'OF'
        | 'OFFSET'
        | 'ON'
        | 'OR'
        | 'ORDER'
//        | 'OVER'
        | 'PARTITION'
        | 'PRIMARY'
        | 'QUALIFY'
        | 'RANGE'
        | 'REGEXP'
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
        | 'TO'
        | 'TOP'
        | 'TRAILING'
        | 'TRUE'
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
        | 'WINDOW'
        | 'WITH'
//        | 'YEAR'

        // ...And exclude all the other tokens which cannot be a keyword or name
        | INTEGER | FLOAT | BYTES | BLOB | PARAMETER | VARIABLE
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

qnames
    : '(' qname ( ',' qname )* ')' ;

// TODO separate rules for valid identifiers, valid aliases, and valid function names
name
    : ID
    | STRING
    | UNICODE_NAME uescape?
    | DOLLARS
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
    : '\'' ( ~'\'' | '\'\'' )* '\'' ;

ID
    : '"' ( ~'"' | '""' )* '"'
    | '`' ( ~'`' | '``' )* '`'
    // T-SQL
//    | '[' ~']'* ']'
    | [A-Z_#] [A-Z_#$@0-9]*
    ;

DOLLARS
    : '$$' .*? '$$' ;

BLOB
    : 'X' HEXHEX ( ' ' HEXHEX )* ;

    fragment HEXHEX
        : '\'' ( HEX HEX ' '? )* '\'' ;

BYTES
    : '0x' HEX+ ;

fragment HEX
    : [A-F0-9] ;

INTEGER
    : DIGIT+ 'L'? ;

// matches "0.e1" or ".0e1", but not ".e1"
FLOAT
    : ( DIGIT+ ( '.' DIGIT* )? | '.' DIGIT+ ) ( 'E' [-+]? DIGIT+ )? [FD]?;

PARAMETER
    : '?' DIGIT* ;

fragment DIGIT
    : [0-9] ;

VARIABLE
    : '?' // DIGIT*
    | [:@$] ( INTEGER | ID )
    ;

COMMENT
    : '--' .*? ( '\n' | EOF ) -> channel( HIDDEN ) ;

BLOCK_COMMENT
    : '/*' ( BLOCK_COMMENT | . )*? '*/' -> channel( HIDDEN ) ;

WHITESPACE
// \u000B line (vertical) tab
// \u000C form feed
//    : [ \t\r\n\u000B\u000C] -> channel ( HIDDEN ) ;
    : [ \t\r\n] -> channel ( HIDDEN ) ;

ERROR : . ;
