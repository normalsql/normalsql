// Copyright 2010-2023 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

/*
 NormalSQL.g4, SQL DML grammar for ANTLR 4.x
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
    | 'DROP' 'TEMPORARY'? 'FUNCTION' ifExists? table ( LP ( type ( ',' type )* )? RP )?
    | 'DROP' 'VIEW' ifExists? table
    | 'DROP' 'DROP' 'ROLE' name
    ;

    ifExists : 'IF' 'EXISTS' ;

create
//    : 'CREATE' (OR REPLACE)? TEMPORARY? FUNCTION tableRef LP (sqlParameterDeclaration (',' sqlParameterDeclaration)*)? RP RETURNS type (COMMENT string)? routineCharacteristics routineBody
//    | 'CREATE' (OR REPLACE)? VIEW tableRef (SECURITY (DEFINER | INVOKER))? AS query
//    | 'CREATE' MATERIALIZED VIEW (IF NOT EXISTS)? tableRef (COMMENT string)? (WITH properties)? AS (query | LP query RP )
//    | 'CREATE' ROLE id (WITH ADMIN grantor)?
//    | 'CREATE' SCHEMA (IF NOT EXISTS)? tableRef (WITH properties)?
    : 'CREATE' 'TABLE' ifNotExists? table LP columnDef ( ',' columnDef )* RP // (COMMENT string)? (WITH properties)?
//    | 'CREATE' TABLE ifNotExists? tableRef columnAliases? (COMMENT string)? (WITH properties)? AS (query | LP query RP ) (WITH (NO)? DATA)?
//    | 'CREATE' TYPE tableRef AS ( LP sqlParameterDeclaration (',' sqlParameterDeclaration)* RP | type)
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
   : 'UPDATE' column 'SET' setter ( COMMA setter )* where? ;

   setter
      : column EQ literal ;

query
   : with? sets orderBy? ( offset | fetch | limit )* forUpdate?
   ;

    with
        : 'WITH' 'RECURSIVE'? cte ( ',' cte )*
        ;

        cte
            : name ( LP name ( COMMA name )* RP )? 'AS' LP query RP
            ; // TODO rule for column aliases

      offset
         : 'OFFSET' term rowRows? ;

      fetch
         : 'FETCH' ( 'FIRST' | 'NEXT' ) ( term 'PERCENT'? )? rowRows ( 'ONLY' | withTies ) ;

      limit
         : 'LIMIT' term (( 'OFFSET' | COMMA ) term )? ;

      forUpdate
         : 'FOR' 'UPDATE' ;

sets
   : sets ( 'INTERSECT' | 'MINUS' ) allDistinct? sets # Ignore
   | sets ( 'UNION' | 'EXCEPT' ) allDistinct? sets # Ignore
   | sets 'MULTISET' allDistinct? sets # Ignore
//   | rows
//   ;
//
//rows
//   :
   | 'SELECT' quantifier? top? ( item ( COMMA item )* COMMA? )? into?
      ( 'FROM' join ( ',' join )* )?
      where? groupBy? having? windows? qualify?       # Select
   | 'TABLE' table                                                  # RowsTable
   | 'VALUES' terms                                                    # RowsValues
   | LP query RP                                                       # RowsNested
   ;

   quantifier
      : 'DISTINCT' ( 'ON' LP terms RP )? | 'ALL' | 'UNIQUE' ;

   item
      : (( table DOT )? WILDCARD ) ( 'EXCEPT' columns )?   # ItemTableRef
      | term ( 'AS'? name )?                                       # ItemColumn
      ;

   top
      : 'TOP' ( Decimal | Real | LP term RP ) 'PERCENT'? withTies? ;

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
                : 'INNER'| ( 'LEFT'| 'RIGHT'| 'FULL' ) 'OUTER'?
                ;

            joinCriteria
                : 'ON' term | 'USING' columns ;

        source
            : ( query
            | function
            | unnest
            | ( 'TABLE' | 'TABLE_DISTINCT' ) LP columnSpec ( COMMA columnSpec )* RP
            | ( 'NEW' | 'OLD' | 'FINAL' ) 'TABLE' LP ( delete | insert | merge | update ) RP
            | 'JSON_TABLE' // TODO
            | 'XMLTABLE' // TODO
            | LP join RP
            | table )
//            ( 'AS'? name columns? )?
            ( 'AS'? name names? )?
            ;

            unnest
               : 'UNNEST' LP array ( COMMA array )* RP ( 'WITH' 'ORDINALITY' )? ;

           columnSpec
               : name ( dataType | schema ) EQ ( array | row ) ;

   where
      : 'WHERE' term ;

   groupBy
      : 'GROUP' 'BY' terms? ;

   having
      : 'HAVING' terms ;

   windows
      : 'WINDOW' windowAlias ( COMMA windowAlias )* ;

      windowAlias
         : name 'AS' window ;

         window
            : LP name? partitionBy? orderBy? windowFrame? RP
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
   | keyword+
   ; // TODO dataTypes
   
row
   : term ; // TODO row value expression
   
terms
   : term ( COMMA term )* ;

// TODO  | term ( 'OR' || '||' ) term
term
   : 'NOT' term                                # TermNOT
   | term 'AND' term                           # TermAND
   | term 'OR' term                            # TermOR
   | 'EXISTS' LP query RP                      # TermEXISTS
   | 'UNIQUE' /* nullsDistinct */  LP query RP # TermUNIQUE
   | 'INTERSECTS' LP subterm COMMA subterm RP  # TermIntersects
   | subterm                                   # TermSubterm
// TODO assignment operators go here ?
   ;

// TODO '||' can be either string concatenation or logical OR
subterm
   : subterm ( '::' keyword )+                                       # SubtermScope
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
   | LP terms RP DOT name                                              # SubtermFieldRef
   | LP terms? RP                                                    # SubtermNested
   | query                                                           # SubtermQuery
   | case                                                            # SubtermCase
   | array                                                           # SubtermArray
   | ( 'CAST' | 'TRY_CAST' ) LP term 'AS' type RP                    # SubtermCast
   | subterm 'AT' ( 'LOCAL' | timeZone ( interval | string ))?       # SubtermTime
   | ( 'NEXT' | 'CURRENT' ) 'VALUE' 'FOR' column                  # SubtermSequence
   | 'ROW' LP terms? RP                                              # SubtermRow
   | function                                                        # SubtermFunction
   | literal                                                         # SubtermValue
   | column                                                       # SubtermRef
   //              | term 'COLLATE' id # TermCollate TODO
   //              | sequenceValueExpression TODO
   //              | arrayElementReference TODO
   ;

   case
      : 'CASE' term ( 'WHEN' ( terms | predicate ) 'THEN' term )+ ( 'ELSE' term )? 'END'   // # SubtermCaseSimple
      | 'CASE' ( 'WHEN' term 'THEN' term )+ ( 'ELSE' term )? 'END'                        //  # SubtermCaseSearch
      ;

predicate
    : compare subterm                                                              # PredicateCompare
    | ( MATCH1 | MATCH2 | MATCH3 | MATCH4 ) subterm                                # PredicateMatch
    | 'IS' 'NOT'? truth                                                            # PredicateTruth
    | 'IS' 'NOT'? 'DISTINCT' 'FROM' subterm                                        # PredicateDistinct
    | 'IS' 'NOT'? 'OF' LP type ( COMMA type )* RP                                  # PredicateOfType
    | 'IS' 'NOT'? 'JSON' jsonType? uniqueKeys?                                     # PredicateJSON
    | 'NOT'? 'BETWEEN' ( 'ASYMMETRIC' | 'SYMMETRIC' )? subterm 'AND' subterm       # PredicateBETWEEN
    | 'NOT'? 'IN' LP ( query | terms )? RP                                         # PredicateIN
    | 'NOT'? ( 'LIKE' | 'ILIKE' ) subterm ( 'ESCAPE' string )?                     # PredicateLike
    | 'NOT'? 'REGEXP' subterm ( 'ESCAPE' string )?                                 # PredicateRegex
    ;

    compare
        : LT | LTE | GT | GTE | EQ | NEQ | OVERLAP ;

    jsonType
        : 'VALUE' | 'ARRAY' | 'OBJECT' | 'SCALAR' ;

// TODO subrule for dialect & custom compare operators?
// TODO might have to be in lexer
//compare
//   : '=' | '>' | '<' | '<=' | '>=' | '<>' | '!='
////   | '!>' | '!<'
////   | { isComparison( ... ) }
//   ;

type
    : 'ROW' LP name type ( COMMA name type )* RP
    | type 'ARRAY' ( LB Decimal RB )?
    | keyword+ ( LP Decimal ( COMMA Decimal )? RP keyword* )?
    ;

array
   : 'ARRAY' LB terms? RB ;

function
   : 'TRIM' LP ( 'BOTH' | 'LEADING' | 'TRAILING' )? term? 'FROM'? term RP
   | 'SUBSTRING' LP term 'FROM' term ( 'FOR' term )? RP
   | 'JSON_OBJECTAGG' LP jsonPairs onNull? uniqueKeys? RP filter? over?
   | 'EXTRACT' LP keyword 'FROM' .*? RP // TODO
   | '{fn' function '}' //  ODBC style
   // Generic syntax for all aggregate functions
//  ( 'ON' 'OVERFLOW' ( 'ERROR' | 'TRUNCATE' name? withWithout 'COUNT' ))?
   | keyword
     LP ( ( table DOT )? WILDCARD | allDistinct? terms orderBy? ( 'ON' 'OVERFLOW' 'ERROR' )?
     ( 'SEPARATOR' subterm )? onNull? )? RP
     withinGroup? filter? ( 'FROM' firstLast )?
     respectIgnore? over?
   // | ID? 'FUNCTION' ID LP terms? RP // T-SQL
   // | ID DOT ID LP terms? RP // T-SQL?
   ;

    withinGroup
       : 'WITHIN' 'GROUP' LP orderBy RP ;

    filter
       : 'FILTER' LP 'WHERE' term RP ;

    over
       : 'OVER' window ;

orderBy
    : 'ORDER' 'BY' orderByItem ( COMMA orderByItem )*
    ;

    orderByItem
        : term ( 'ASC' | 'DESC' )? ( 'NULLS' firstLast )?
        ;

literal
   : Decimal
   | Real
   | Bytes
   | Blob
   | truth
   | 'DATE' string
   | ( '{d' | '{t' | '{ts' ) string '}'
   | ( 'TIME' | 'TIMESTAMP' ) ( withWithout timeZone )? string?
   | interval
   | jsonObject
   | jsonArray
   | Parameter
   | Variable
   | string
   ;

truth
   : 'TRUE' | 'FALSE' | 'UNKNOWN' | 'NULL' ;

interval
   : 'INTERVAL' string (keyword ( 'TO' keyword )? )?
   ;
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
   : 'JSON_ARRAY' LP ( terms | LP query RP )? formatJson? onNull? RP ;

jsonObject
   : 'JSON_OBJECT' LP jsonPairs? onNull? uniqueKeys? formatJson? RP ;

    jsonPairs
       : jsonPair ( COMMA jsonPair )* ;

        jsonPair
           : jsonKey ':' term
           | 'KEY'? jsonKey 'VALUE' term
           ;

            jsonKey
               : 'NULL' | string | keyword ;

columns
   : LP column ( COMMA column )* RP ;

column
   : ((( name DOT )? name DOT )? name DOT )? name;

table
   : (( name DOT )? name DOT )? name ;

schema
   : ( name DOT )? name ;


// this works too. more "semantic" might be easier for post parser stuff
//column : ( table DOT )? name ;
//table : ( schema DOT )? name ;
//schema : ( catalog DOT )? name ;
//catalog : name ;

index
   : LB ( term | term? ':' term? )? RB ;

string
   : String+
   | UnicodeString String* uescape?
   | NationalString String*
   ;

names
   : LP name ( COMMA name )* RP ;

name
   : Name
   | UnicodeName uescape?
   | Backticks
   | Dollars
   | keyword
   ;

   uescape
      : 'UESCAPE' string ;

keyword
   : Keyword
   | good
   ;

/*
    All the tokens used by this grammar, with the (most-est) reserved SQL keywords commented out.
    Add new tokens as needed.
*/
// TODO separate rules for valid identifiers, valid aliases, and valid function names
// TODO how to allow keyword as columnRef?
// TODO refresh (regenerate) list as grammar grows
good
    : 'ABSENT'
    | 'ALL'
    // | 'AND'
    | 'ARRAY'
     | 'AS' //
    | 'ASC'
    | 'ASYMMETRIC'
    | 'AT'
    // | 'BETWEEN'
    // | 'BOTH'
    // | 'BY'
     | 'CASE' //
    // | 'CAST'
    | 'CATEGORY'
    // | 'CROSS'
    | 'CURRENT'
    | 'DATE'
    // | 'DELETE'
    | 'DESC'
    // | 'DISTINCT'
    | 'DIV'
    // | 'ELSE'
    // | 'END'
    | 'ERROR'
    | 'ESCAPE'
    // | 'EXCEPT'
    | 'EXCLUDE'
    // | 'EXISTS'
    | 'EXTRACT'
    // | 'FALSE'
    | 'FETCH'
    | 'FILTER'
    | 'FINAL'
    | 'FIRST'
    | 'FOLLOWING'
    // | 'FOR'
    | 'FORMAT'
    // | 'FROM'
    // | 'FULL'
    // | 'GROUP'
    | 'GROUP_CONCAT'
    | 'GROUPS'
    // | 'HAVING'
    | 'IGNORE'
    | 'ILIKE'
    // | 'IN'
    // | 'INNER'
    // | 'INSERT'
    // | 'INTERSECT'
    | 'INTERSECTS'
    | 'INTERVAL'
    // | 'INTO'
    // | 'IS'
    // | 'JOIN'
    | 'JSON'
    | 'JSON_ARRAY'
    | 'JSON_ARRAYAGG'
    | 'JSON_OBJECT'
    | 'JSON_OBJECTAGG'
    | 'JSON_TABLE'
    | 'KEY'
    | 'KEYS'
    | 'LAST'
    // | 'LEADING'
     | 'LEFT' //
    // | 'LIKE'
    | 'LIMIT'
    | 'LISTAGG'
    | 'LOCAL'
    | 'MERGE'
    | 'MINUS'
    | 'MOD'
    | 'MULTISET'
    // | 'NATURAL'
    | 'NEW'
    | 'NEXT'
    | 'NO'
    // | 'NOT'
    // | 'NULL'
    | 'NULLS'
    | 'OBJECT'
    // | 'OF'
    | 'OFFSET'
    | 'OLD'
    // | 'ON'
    | 'ONLY'
    // | 'OR'
    // | 'ORDER'
    | 'ORDINALITY'
    | 'OTHERS'
    | 'OUTER'
    | 'OVER'
    | 'OVERFLOW'
    | 'PARTITION'
    | 'PERCENT'
    | 'PRECEDING'
    | 'QUALIFY'
    | 'RANGE'
    | 'RECURSIVE'
    | 'REGEXP'
    | 'RESPECT'
     | 'RIGHT' //
     | 'ROLE'
    | 'ROW'
    | 'ROWS'
    | 'SCALAR'
    | 'SCHEMA'
    // | 'SELECT'
    | 'SEPARATOR'
    | 'SET'
    | 'STRING_AGG'
    | 'SUBSTRING'
    | 'SYMMETRIC'
    // | 'TABLE'
    | 'TABLE_DISTINCT'
    // | 'THEN'
    | 'TIES'
    | 'TIME'
    | 'TIMESTAMP'
    | 'TO'
    | 'TOP'
    | 'TRAILING'
    | 'TRIM'
    // | 'TRUE'
    | 'TRY_CAST'
    | 'UESCAPE'
    | 'UNBOUNDED'
    // | 'UNION'
    // | 'UNIQUE'
    | 'UNKNOWN'
    | 'UNNEST'
    // | 'UPDATE'
    // | 'USING'
    | 'VALUE'
    // | 'VALUES'
    // | 'WHEN'
    // | 'WHERE'
    | 'WINDOW'
     | 'WITH' //
    | 'WITHIN'
    | 'WITHOUT'
    | 'XMLTABLE'
    | 'ZONE'
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

Keyword
   : [A-Z_#] [A-Z_#$@0-9]* ;

UnicodeString
   : 'U&' String ;

NationalString
   : [NE] String ;

/*
    Tokenizes 'abc '' \a \' \\ xyz' into single token correctly
*/
String
//   : '\'' ( ~'\'' | '\\'. | '\'\'' )* '\'' ;
      : '\'' ( ~'\'' | '\'\'' )* '\'' ;
//      : '\'' ( ~'\'' | '\'\'' )* '\'' ;
//      : '\'' ('\\'. | '\'\'' | ~('\'' | '\\'))* '\'' ;


UnicodeName
   : 'U&' Name ;

// TODO square bracket names
Name
   : '"' ( ~'"' | '""' )* '"' ;

Dollars
  : '$$' .*? '$$' ;

Backticks
   : '`' ( ~'`' | '``' )* '`' ;

Blob
   : 'X' BLOB ( ' ' BLOB )* ;

    fragment BLOB
       : '\'' ( HEX HEX ' '? )* '\'' ;

Bytes
   : '0x' HEX+ ;

fragment HEX
   : [A-F0-9] ;

Decimal
   : DIGIT+ 'L'? ;

// matches "0.e1" or ".0e1", but not ".e1"
Real
   : ( DIGIT+ ( '.' DIGIT* )? | '.' DIGIT+ ) ( 'E' [-+]? DIGIT+ )? ;

Parameter
   : '?' DIGIT* ;

fragment DIGIT
   : [0-9] ;

Variable
   : '@' [A-Z_$@#0-9]* // T-SQL?
   | ':' [A-Z_] [A-Z_0-9$]* // Postgres?
   | ':' Name // Postgres?
   ;

Comment
   : '--' .*? ( '\n' | EOF ) -> channel( HIDDEN ) ;

Block
   : '/*' ( Block | . )*? '*/' -> channel( HIDDEN ) ;

Spaces
   : [ \t\r\n\u000B\u000C] -> channel ( HIDDEN ) ;

LP       : '(' ;
RP       : ')' ;
LB       : '[' ;
RB       : ']' ;
COMMA    : ',' ;
DOT      : '.' ;
WILDCARD : '*' ;

EQ       : '=' | ':=' ;
NEQ      : '<>' | '!=' ;
LT       : '<' ;
LTE      : '<=' ;
GT       : '>' ;
GTE      : '>=' ;
OVERLAP  : '&&' ;
// TODO add isAssign for dialects (for symantec predicate)
//ASSIGN   : ':=' ;

// TODO replace w/ isOperator (for symantec predicate)
MATCH1 : '~' ; // match regex case sensitive
MATCH2 : '~*' ; // match regex case insensitive
MATCH3 : '!~' ; // not match regex case sensitive
MATCH4 : '!~*' ; // not match regex case insensitive
   
ERROR : . ;
