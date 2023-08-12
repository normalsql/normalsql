// Copyright 2010-2023 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

/*
 NormalSQL.g4, SQL DML grammar for ANTLR 4.x

 Style:

 Child rules are indented when its (mostly) only used by a parent rule.

*/

grammar NormalSQL;

options 
{ 
    contextSuperClass=normalsql.parse.GlobbingRuleContext;
    caseInsensitive=true;
}

parse
    : statement? ( ';' statement? )* EOF ;

statement
    : delete
    | insert
    | merge
    | update
    | query
    | drop
    | create
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
        : name type ('NOT' 'NULL')?  // (COMMENT string)? (WITH properties)?
        ;

delete
    : 'DELETE' ; // TODO
   
insert
    : 'INSERT' into names? /* rows */ ; // TODO
   
merge
    : 'MERGE' ; // TODO
   
update
    : 'UPDATE' column 'SET' setter ( ',' setter )* where? ;

    setter
//        : column EQ literal ;
        : column '=' literal ;

query
    : with? combine orderBy? ( offset | fetch | limit )* forUpdate?
    ;

    // TODO: inline 'with' and 'forUpdate'?
    with
        : 'WITH' 'RECURSIVE'? cte ( ',' cte )*
        ;

        cte
            : name ( '(' name ( ',' name )* ')' )? 'AS' '(' query ')'
            ; // TODO rule for column aliases

    offset
        : 'OFFSET' term rowRows? ;

    fetch
        : 'FETCH' ( 'FIRST' | 'NEXT' ) ( term 'PERCENT'? )? rowRows ( 'ONLY' | withTies ) ;

    limit
        : 'LIMIT' term (( 'OFFSET' | ',' ) term )? ;

    forUpdate
        : 'FOR' 'UPDATE' ;

combine
    : combine ( 'INTERSECT' | 'MINUS' ) allDistinct? combine
    | combine ( 'UNION' | 'EXCEPT' ) allDistinct? combine
    | combine 'MULTISET' allDistinct? combine
    | select
    | 'TABLE' table
    | values
    | '(' query ')'
    ;

    values : 'VALUES' terms ;

select
    : 'SELECT' quantifier? top? ( item ( ',' item )* ','? )? into?
      ( 'FROM' join ( ',' join )* )? where? groupBy? having? windows? qualify?
    ;
    quantifier
        : 'DISTINCT' ( 'ON' '(' terms ')' )? | 'ALL' | 'UNIQUE' ;

    item
        : (( table '.' )? '*' ) ( 'EXCEPT' columns )?   # ItemTableRef
        | term ( 'AS'? name )?                          # ItemColumn
        ;

    top
        : 'TOP' ( DECIMAL | REAL | '(' term ')' ) 'PERCENT'? withTies? ;

    into
        : 'INTO' table ;

    join
        : join
          ( 'CROSS' 'JOIN' source
          | joinType? 'JOIN' join joinCriteria?
          | 'NATURAL' joinType? 'JOIN' source
          )
        | source
        ;

        joinType
            : 'INNER'| ( 'LEFT'| 'RIGHT'| 'FULL' ) 'OUTER'? ;

        joinCriteria
            : 'ON' term | 'USING' columns ;

        source
            : ( unnest
              | values
              // TODO: refactor to subrule 'tableSpec'?
              | ( 'TABLE' | 'TABLE_DISTINCT' ) '(' columnSpec ( ',' columnSpec )* ')'
              | ( 'NEW' | 'OLD' | 'FINAL' ) 'TABLE' '(' ( delete | insert | merge | update ) ')'
              | 'JSON_TABLE' // TODO
              | 'XMLTABLE' // TODO
              | function
              | '(' join ')'
              | table
              | query
               )
              ( 'AS'? name names? )?
            ;

            unnest
                : 'UNNEST' '(' array ( ',' array )* ')' ( 'WITH' 'ORDINALITY' )? ;

            columnSpec
                : name ( dataType | schema ) '=' ( array | row ) ;

    where
        : 'WHERE' term ;

    groupBy
        : 'GROUP' 'BY' terms? ;

    having
        : 'HAVING' terms ;

    windows
        : 'WINDOW' windowAlias ( ',' windowAlias )* ;

        windowAlias
            : name 'AS' window ;

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

    qualify
        : 'QUALIFY' term ;

dataType
    : 'NULL'
    | id+
    ; // TODO dataTypes
   
row
    : term ; // TODO row value expression
   
terms
    : term ( ',' term )* ;

// TODO  | term ( 'OR' || '||' ) term
term
    : 'NOT' term                                # TermNOT
    | term 'AND' term                           # TermAND
    | term 'OR' term                            # TermOR
    | 'EXISTS' '(' query ')'                      # TermEXISTS
    | 'UNIQUE' /* nullsDistinct */  '(' query ')' # TermUNIQUE
    | 'INTERSECTS' '(' subterm ',' subterm ')'  # TermIntersects
    | subterm                                   # TermSubterm
    // TODO assignment operators go here ?
    ;

// TODO '||' can be either string concatenation or logical OR
subterm
    : subterm ( '::' id )+                                       # SubtermScope
    | subterm index+                                                  # SubtermIndex
    | ( '+' | '-' | '~' | '!' ) subterm                               # SubtermUnary
    | <assoc=right> subterm '^' subterm                               # SubtermBinary
    | subterm ( '*' | '/' | 'DIV' | '%' | 'MOD' ) subterm             # SubtermBinary
    | subterm ( '+' | '-' ) subterm                                   # SubtermBinary
    | subterm '||' subterm                                            # SubtermBinary
    | subterm ( '->' | '->>' ) subterm                                # SubtermBinary
    | subterm ( '<<' | '>>' ) subterm                                 # SubtermBinary
    | subterm '&' subterm                                             # SubtermBinary
    | subterm '|' subterm                                             # SubtermBinary
    | subterm predicate                                               # SubtermPredicate
    | '(' terms ')' '.' name                                              # SubtermFieldRef
    | '(' terms? ')'                                                    # SubtermNested
    | query                                                           # SubtermQuery
    | case                                                            # SubtermCase
    | array                                                           # SubtermArray
    | ( 'CAST' | 'TRY_CAST' ) '(' term 'AS' type ')'                    # SubtermCast
    | subterm 'AT' ( 'LOCAL' | timeZone ( interval | string ))?       # SubtermTime
    | ( 'NEXT' | 'CURRENT' ) 'VALUE' 'FOR' column                  # SubtermSequence
    | 'ROW' '(' terms? ')'                                              # SubtermRow
    | function                                                        # SubtermFunction
    | literal                                                         # SubtermValue
    | column                                                       # SubtermRef
//    | term 'COLLATE' id # TermCollate TODO
//    | sequenceValueExpression TODO
    ;

    case
        : 'CASE' term ( 'WHEN' ( terms | predicate ) 'THEN' term )+ ( 'ELSE' term )? 'END'   // # SubtermCaseSimple
        | 'CASE' ( 'WHEN' term 'THEN' term )+ ( 'ELSE' term )? 'END'                        //  # SubtermCaseSearch
        ;

predicate
    : comparator subterm                                                           # PredicateCompare
    | ( MATCH1 | MATCH2 | MATCH3 | MATCH4 ) subterm                                # PredicateMatch
    | 'IS' 'NOT'? truth                                                            # PredicateTruth
    | 'IS' 'NOT'? 'DISTINCT' 'FROM' subterm                                        # PredicateDistinct
    | 'IS' 'NOT'? 'OF' '(' type ( ',' type )* ')'                                  # PredicateOfType
    | 'IS' 'NOT'? 'JSON' jsonType? uniqueKeys?                                     # PredicateJSON
    | 'NOT'? 'BETWEEN' ( 'ASYMMETRIC' | 'SYMMETRIC' )? subterm 'AND' subterm       # PredicateBETWEEN
    | 'NOT'? 'IN' '(' ( query | terms )? ')'                                         # PredicateIN
    | 'NOT'? ( 'LIKE' | 'ILIKE' ) subterm ( 'ESCAPE' string )?                     # PredicateLike
    | 'NOT'? 'REGEXP' subterm ( 'ESCAPE' string )?                                 # PredicateRegex
    ;

    jsonType
        : 'VALUE' | 'ARRAY' | 'OBJECT' | 'SCALAR' ;

comparator  : '=' | ':=' | '<>' | '!=' | '<' | '<=' | '>' | '>=' | '&&' ;

type
    : 'ROW' '(' name type ( ',' name type )* ')'
    | type 'ARRAY' ( '[' DECIMAL ']' )?
    | id+ ( '(' DECIMAL ( ',' DECIMAL )? ')' id* )?
    ;

array
    : 'ARRAY' '[' terms? ']' ;

function
    : 'TRIM' '(' ( 'BOTH' | 'LEADING' | 'TRAILING' )? term? 'FROM'? term ')'
    | 'SUBSTRING' '(' term 'FROM' term ( 'FOR' term )? ')'
    | 'JSON_OBJECTAGG' '(' jsonPairs onNull? uniqueKeys? ')' filter? over?
    | 'EXTRACT' '(' id 'FROM' .*? ')' // TODO
    | '{fn' function '}' //  ODBC style
    // Generic syntax for all aggregate functions
    | id
      '(' ( ( table '.' )? '*' | allDistinct? terms orderBy?
//    ( 'ON' 'OVERFLOW' ( 'ERROR' | 'TRUNCATE' name? withWithout 'COUNT' ))?
      ( 'ON' 'OVERFLOW' 'ERROR' )?
      ( 'SEPARATOR' subterm )? onNull? )? ')'
      withinGroup? filter? ( 'FROM' firstLast )?
      respectIgnore? over?
//    | keyword? 'FUNCTION' keyword '(' terms? ')' // TODO: T-SQL style
//    | keyword '.' keyword '(' terms? ')' // TODO: T-SQL style?
    ;

    withinGroup
        : 'WITHIN' 'GROUP' '(' orderBy ')' ;

    filter
        : 'FILTER' '(' 'WHERE' term ')' ;

    over
        : 'OVER' window ;

orderBy
    : 'ORDER' 'BY' orderByItem ( ',' orderByItem )*
    ;

    orderByItem
        : term ( 'ASC' | 'DESC' )? ( 'NULLS' firstLast )?
        ;

literal
    : DECIMAL
    | REAL
    | BYTES
    | BLOB
    | truth
    // TODO: change date literals from 'string' rule to 'STRING' token?
    | 'DATE' string
    | ( '{d' | '{t' | '{ts' ) string '}'
    | ( 'TIME' | 'TIMESTAMP' ) ( withWithout timeZone )? string?
    | interval
    | jsonObject
    | jsonArray
    | PARAMETER
    | VARIABLE
    | string
    ;

truth
    : 'TRUE' | 'FALSE' | 'UNKNOWN' | 'NULL' ;

interval
    : 'INTERVAL' string (id ( 'TO' id )? )?
    ;

    // TODO: explicit, then uncomment time units in rule 'unreserved'
   //interval : 'INTERVAL' expression timeSpan ;
   //timeSpan      : 'EPOCH'
   //              | 'YEAR' ( 'TO' 'MONTH' )?
   //              | 'MONTH'
   //              | 'DAY' ( 'TO' ( 'HOUR' | 'MINUTE' | 'SECOND' ) )?
   //              | 'HOUR' ( 'TO' ( 'MINUTE' | 'SECOND' ) )?
   //              | 'MINUTE' ( 'TO' 'SECOND' )?
   //              | 'SECOND'
   //              ;
   
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
                : 'NULL' | string | id ;

columns
    : '(' column ( ',' column )* ')' ;

// These variants produce flattened parse tree
column
    : ((( name '.' )? name '.' )? name '.' )? name;

table
    : (( name '.' )? name '.' )? name ;

schema
    : ( name '.' )? name ;

// These variants work too. Maybe a "semantic" parse tree makes post parser stuff easier.
//column : ( table '.' )? name ;
//table : ( schema '.' )? name ;
//schema : ( catalog '.' )? name ;
//catalog : name ;

index
    : '[' ( term | term? ':' term? )? ']' ;

string
    : STRING+
    | UNICODE_STRING STRING* uescape?
    | NATIONAL_STRING STRING*
    ;

    uescape
        : 'UESCAPE' STRING ;

names
    : '(' name ( ',' name )* ')' ;

// TODO separate rules for valid identifiers, valid aliases, and valid function names
name
    : NAME
    | UNICODE_NAME uescape?
    | BACKTICKS
    | DOLLARS
    | ID
    | unreserved
    ;

unreserved
    : ~(
        // Exclude SQL's reserved keywords...
        'ALL'
        | 'AND'
        | 'ANY'
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
        | 'LEFT'
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
        | 'OVER'
        | 'PARTITION'
        | 'PRIMARY'
        | 'QUALIFY'
        | 'RANGE'
        | 'REGEXP'
        | 'RIGHT'
        | 'ROW'
//        | 'ROWNUM'
        | 'ROWS'
        | 'SECOND'
        | 'SELECT'
//        | 'SESSION_USER'
//        | 'SET'
        | 'SOME'
        | 'SYMMETRIC'
//        | 'SYSTEM_USER'
        | 'TABLE'
        | 'TO'
        | 'TOP'
        | 'TRAILING'
        | 'TRUE'
        | 'UESCAPE'
        | 'UNION'
        | 'UNIQUE'
        | 'UNKNOWN'
        | 'USER'
        | 'USING'
//        | 'VALUE'
        | 'VALUES'
        | 'WHEN'
        | 'WHERE'
        | 'WINDOW'
        | 'WITH'
//        | 'YEAR'

        // ...and all the other things which cannot be a name.
        | DECIMAL | REAL | BYTES | BLOB | PARAMETER | VARIABLE
        | STRING | UNICODE_STRING | NATIONAL_STRING
        | '(' | ')' | '[' | ']' | ',' | '.' | '*' | '=' | ':=' | '<>' | '!=' | '<' | '<=' | '>' | '>=' | '&&'
       )
    ;

id
    : ID
    // Roundabout way to accept grammar's keyword-like tokens
    | ~ ( DECIMAL | REAL | BYTES | BLOB | PARAMETER | VARIABLE
        | STRING | UNICODE_STRING | NATIONAL_STRING
        | '(' | ')' | '[' | ']' | ',' | '.' | '*' | '=' | ':=' | '<>' | '!=' | '<' | '<=' | '>' | '>=' | '&&'
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

ID
    : [A-Z_#] [A-Z_#$@0-9]* ;

UNICODE_STRING
    : 'U&' STRING ;

NATIONAL_STRING
    : [NE] STRING ;

STRING
    : '\'' ( ~'\'' | '\'\'' )* '\'' ;

UNICODE_NAME
    : 'U&' NAME ;

// TODO square bracket names
NAME
    : '"' ( ~'"' | '""' )* '"' ;

DOLLARS
    : '$$' .*? '$$' ;

BACKTICKS
    : '`' ( ~'`' | '``' )* '`' ;

BLOB
    : 'X' HEXHEX ( ' ' HEXHEX )* ;

    fragment HEXHEX
        : '\'' ( HEX HEX ' '? )* '\'' ;

BYTES
    : '0x' HEX+ ;

fragment HEX
    : [A-F0-9] ;

DECIMAL
    : DIGIT+ 'L'? ;

// matches "0.e1" or ".0e1", but not ".e1"
REAL
    : ( DIGIT+ ( '.' DIGIT* )? | '.' DIGIT+ ) ( 'E' [-+]? DIGIT+ )? ;

PARAMETER
    : '?' DIGIT* ;

fragment DIGIT
    : [0-9] ;

VARIABLE
    : '@' [A-Z_$@#0-9]* // T-SQL?
    | ':' [A-Z_] [A-Z_0-9$]* // Postgres?
    | ':' NAME // Postgres?
    ;

//COMPARATOR  : '=' | ':=' | '<>' | '!=' | '<' | '<=' | '>' | '>=' | '&&' ;

// TODO replace w/ isOperator (for symantec predicate)
MATCH1 : '~' ; // match regex case sensitive
MATCH2 : '~*' ; // match regex case insensitive
MATCH3 : '!~' ; // not match regex case sensitive
MATCH4 : '!~*' ; // not match regex case insensitive

COMMENT
    : '--' .*? ( '\n' | EOF ) -> channel( HIDDEN ) ;

BLOCK_COMMENT
    : '/*' ( BLOCK_COMMENT | . )*? '*/' -> channel( HIDDEN ) ;

WHITESPACE
    : [ \t\r\n\u000B\u000C] -> channel ( HIDDEN ) ;

ERROR : . ;
