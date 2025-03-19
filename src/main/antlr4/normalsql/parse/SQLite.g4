// Copyright 2010-2025 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

/*
 SQLite.g4

 Synthesis of Bart Keirs' (et al) SQLiteParser.g4 (see license below) and NormalSQL grammars.

 Part of my ongoing effort use same semantic (post parse) processing for multiple dialects.

 We'll see.


*/

/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 by Bart Kiers
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 * Project : sqlite-parser; an ANTLR4 grammar for SQLite https://github.com/bkiers/sqlite-parser
 * Developed by:
 *     Bart Kiers, bart@big-o.nl
 *     Martin Mirchev, marti_2203@abv.bg
 *     Mike Lische, mike@lischke-online.de
 */


grammar SQLite;

options {
caseInsensitive = true;
}

parse
  : statement? ( ';' statement? )* EOF ;

statement
  : ( 'EXPLAIN' ( 'QUERY' 'PLAN' )? )?
    ( alter
    | analyze
    | attach
    | begin
    | commit
    | create_index
    | create_table
    | create_trigger
    | create_view
    | create_virtual_table
    | delete ( orderBy? limit )?
    | detach
    | drop
    | insert
    | pragma
    | reindex
    | release
    | rollback
    | savepoint
    | selectCombo
    | update ( orderBy? limit )?
    | vacuum
    )
  ;

alter
  : 'ALTER' 'TABLE' qname
    ( 'RENAME'
    ( 'TO' name | 'COLUMN'? name 'TO' name )
    | 'ADD' 'COLUMN'? columnDef
    | 'DROP' 'COLUMN'? name
    )
  ;

analyze
  : 'ANALYZE' qname? ;

attach
  : 'ATTACH' 'DATABASE'? expr 'AS' name ;

begin
  : 'BEGIN' ( 'DEFERRED' | 'IMMEDIATE' | 'EXCLUSIVE' )? ( 'TRANSACTION' name? )? ;

commit
  : ( 'COMMIT' | 'END' ) 'TRANSACTION'? ;

create_index
  : 'CREATE' 'UNIQUE'? 'INDEX' ifNotExists? qname 'ON' name columnIndexes where?
  ;

create_table
  : 'CREATE' temp? 'TABLE' ifNotExists? qname
    ( '(' columnDef ( ',' columnDef )*? ( ',' table_constraint )* ')' ( 'WITHOUT' ID )?
    | 'AS' selectCombo
    )
  ;

create_trigger
  : 'CREATE' temp? 'TRIGGER' ifNotExists? qname
    ( 'BEFORE' | 'AFTER' | 'INSTEAD' 'OF' )?
    ( 'DELETE' | 'INSERT' | 'UPDATE' ( 'OF' name ( ',' name )* )? )
    'ON' name ( 'FOR' 'EACH' 'ROW' )?
    ( 'WHEN' expr )?
    'BEGIN' ( ( update | insert | delete | selectCombo ) ';' )+ 'END'
  ;

create_view
  : 'CREATE'? 'VIEW' ifNotExists? qname columns? 'AS' selectCombo
  ;

create_virtual_table
  : 'CREATE' 'VIRTUAL' 'TABLE' ifNotExists? qname 'USING' name
    ( '(' module_argument ( ',' module_argument )* ')' )?
  ;

delete
  : with? 'DELETE' 'FROM' qualifiedName where? returning? ;

detach
  : 'DETACH' 'DATABASE'? name ;

drop
  : 'DROP' ( 'INDEX' | 'TABLE' | 'TRIGGER' | 'VIEW' ) ( 'IF' 'EXISTS' )? qname ;

rollback
  : 'ROLLBACK' 'TRANSACTION'? ( 'TO' 'SAVEPOINT'? name )? ;

savepoint
  : 'SAVEPOINT' name ;

release
  : 'RELEASE' 'SAVEPOINT'? name ;

columnIndexes
  : '(' columnIndex ( ',' columnIndex )* ')' ;

columnIndex
  : ( name | expr ) ( 'COLLATE' name )? sortDir? ;

ifNotExists : 'IF' 'NOT' 'EXISTS' ;

columnDef
  : name type? constraint* ;

type
  : name+
//  (  '(' signed_number ( ',' signed_number )? ')' )?
  ;

constraint
  : ( 'CONSTRAINT' name )?
    ( ( 'PRIMARY' 'KEY' sortDir? conflict? 'AUTOINCREMENT'? )
    | ( 'NOT'? 'NULL' | 'UNIQUE' ) conflict?
    | 'CHECK' '(' expr ')'
    | 'DEFAULT' ( signed_number | literal | '(' expr ')' )
    | 'COLLATE' name
    | foreign_key
    | ( 'GENERATED' 'ALWAYS' )? 'AS' '(' expr ')' ( 'STORED' | 'VIRTUAL' )?
    )
  ;

signed_number
  : ('+' | '-')? NUMBER ;

table_constraint
  : ( 'CONSTRAINT' name )?
    ( ( 'PRIMARY' 'KEY' | 'UNIQUE' ) columnIndexes conflict?
    | 'CHECK' '(' expr ')'
    | 'FOREIGN' 'KEY' columns foreign_key
    )
  ;

foreign_key
    : 'REFERENCES' name columns?
      ( 'ON' ('DELETE' | 'UPDATE') (
            'SET' ('NULL' | 'DEFAULT')
            | 'CASCADE'
            | 'RESTRICT'
            | 'NO' 'ACTION'
        )
        | 'MATCH' name
    )* ('NOT'? 'DEFERRABLE' ('INITIALLY' ('DEFERRED' | 'IMMEDIATE'))?)?
;

conflict
  : 'ON' 'CONFLICT' action ;

temp : 'TEMP' | 'TEMPORARY' ;

with
  : 'WITH' 'RECURSIVE'? cte ( ',' cte )*
  ;

cte
  : name columns? 'AS' '(' selectCombo ')' ;

exprs : expr ( ',' expr )* ;

/*
 SQLite understands the following binary operators, in order from highest to lowest precedence:
    ||
    * / %
    + -
    << >> & |
    < <= > >=
    = == != <> IS IS NOT IS DISTINCT FROM IS NOT DISTINCT FROM IN LIKE GLOB MATCH REGEXP
    AND
    OR
 */
expr
  : literal
  | PARAM
  | qname
  | ( '-' | '+' | '~' | 'NOT' ) expr
  | expr '||' expr
  | expr ( '*' | '/' | '%' ) expr
  | expr ( '+' | '-' ) expr
  | expr ( '<<' | '>>' | '&' | '|' ) expr
  | expr ( '<' | '<=' | '>' | '>=' ) expr
  | expr ( '=' | '==' | '!=' | '<>' | 'IS' | 'IS' 'NOT' | 'IS' 'NOT'? 'DISTINCT' 'FROM' | 'IN' | 'LIKE' | 'GLOB' | 'MATCH' | 'REGEXP' ) expr
  | expr 'AND' expr
  | expr 'OR' expr
  | name '(' ( 'DISTINCT'? exprs  | '*' )? ')' filter? over?
  | '(' exprs ')'
  | 'CAST' '(' expr 'AS' type ')'
  | expr 'COLLATE' name
  | expr 'NOT'? ( 'LIKE' | 'GLOB' | 'REGEXP' | 'MATCH' ) expr ( 'ESCAPE' expr )?
  | expr ( 'ISNULL' | 'NOTNULL' | 'NOT' 'NULL' )
  | expr 'IS' 'NOT'? expr
  | expr 'NOT'? 'BETWEEN' expr 'AND' expr
  | expr 'NOT'? 'IN'
    ( '(' ( selectCombo | exprs )? ')'
    | qname ( '(' exprs? ')' )?
    )
  | ( 'NOT'? 'EXISTS' )? '(' selectCombo ')'
  | 'CASE' expr? ( 'WHEN' expr 'THEN' expr )+ ( 'ELSE' expr )? 'END'
  | raise
  ;

raise
  : 'RAISE' '(' ( 'IGNORE' | ( 'ROLLBACK' | 'ABORT' | 'FAIL' ) ',' STRING ) ')'
  ;

values
  : 'VALUES' value_row ( ',' value_row )* ;

value_row
  : '(' exprs ')' ;

insert
  : with?
    ( 'INSERT'
    | 'REPLACE'
    | 'INSERT' 'OR' action
    )
    'INTO' qname alias? columns?
    ( ( values | selectCombo ) upsert?
    | 'DEFAULT' 'VALUES'
    )
    returning?
;

action
  : 'ABORT' | 'FAIL' | 'IGNORE' | 'REPLACE' | 'ROLLBACK' ;

returning
  : 'RETURNING' items ;

upsert
  : 'ON' 'CONFLICT' ( columnIndexes where? )?
    'DO' ( 'NOTHING' | 'UPDATE' setter where? )
  ;

setter : 'SET' assign ( ',' assign )* ;

assign : ( name | columns ) '=' expr ;

pragma
  : 'PRAGMA' qname
    ( '=' pragma_value
    | '(' pragma_value ')'
    )?
  ;

pragma_value
  : signed_number
  | name
  ;

reindex
  : 'REINDEX' qname? ;

selectCombo
  : with?
    select (( 'UNION' 'ALL'? | 'INTERSECT' | 'EXCEPT' ) select )*
    orderBy? limit?
  ;

join_clause
  : table_or_subquery ( join_operator table_or_subquery join_constraint? )* ;

select
  : ( 'SELECT' ('DISTINCT' | 'ALL')? items
    ( 'FROM' (table_or_subquery (',' table_or_subquery)* | join_clause) )?
      where?
      ( 'GROUP' 'BY' exprs ( 'HAVING' expr )? )?
      ('WINDOW' window ( ',' window )*)?
    )
  | values
;

window : name 'AS' windowDef ;

table_or_subquery
    : qualifiedName
    | qname '(' exprs ')' alias?
    | '(' (table_or_subquery (',' table_or_subquery)* | join_clause) ')'
    | '(' selectCombo ')' alias?
;

items : item ( ',' item )* ;

item
  : '*'
  | name '.' '*'
  | expr alias?
  ;

join_operator
  : ','
  | 'NATURAL'? ( ( 'LEFT' | 'RIGHT' | 'FULL' ) 'OUTER'? | 'INNER' | 'CROSS' )? 'JOIN'
  ;

join_constraint
  : 'ON' expr
  | 'USING' columns
  ;

update
  : with? 'UPDATE' ( 'OR' action )? qualifiedName
    setter
    ( 'FROM' ( table_or_subquery ( ',' table_or_subquery)* | join_clause ) )?
    where? returning?
  ;

where : 'WHERE' expr ;

qualifiedName
  : qname alias?
    ( 'INDEXED' 'BY' name
    | 'NOT' 'INDEXED'
    )?
  ;

vacuum
  : 'VACUUM' name? ( 'INTO' name )?
  ;

filter
  : 'FILTER' '(' 'WHERE' expr ')'
  ;

windowDef
  : '(' name? ( 'PARTITION' 'BY' exprs )? orderBy? frame_spec? ')'
  ;

over
  : 'OVER' ( name | windowDef ) ;

frame_spec
    : frame_clause ( 'EXCLUDE' ( 'NO' 'OTHERS' | 'CURRENT' 'ROW' | 'GROUP' | 'TIES' ))?
;

frame_clause
    : ( 'RANGE' | 'ROWS' | 'GROUPS' )
      ( frame_single
      | 'BETWEEN' frame_left 'AND' frame_right
      )
    ;


limit
  : 'LIMIT' expr (( 'OFFSET' | ',' ) expr)? ;

frame_left
  : expr 'PRECEDING'
  | expr 'FOLLOWING'
  | 'CURRENT' 'ROW'
  | 'UNBOUNDED' 'PRECEDING'
  ;

frame_right
  : expr 'PRECEDING'
  | expr 'FOLLOWING'
  | 'CURRENT' 'ROW'
  | 'UNBOUNDED' 'FOLLOWING'
  ;

frame_single
  : expr 'PRECEDING'
  | 'UNBOUNDED' 'PRECEDING'
  | 'CURRENT' 'ROW'
  ;


//simple_function_invocation
//    : name '(' (exprs | '*') ')'
//;
//
//aggregate_function_invocation
//    : name '(' ('DISTINCT'? exprs | '*')? ')' filter?
//;
//
//window_function_invocation
//    : window_function '(' ( exprs | '*' )? ')' filter? 'OVER' (
//        window_defn
//        | name
//    )
//;

//window_function
//  : ('FIRST_VALUE' | 'LAST_VALUE') '(' expr ')' 'OVER' '(' partitionBy? orderBy frame_clause? ')'
//  | ('CUME_DIST' | 'PERCENT_RANK') '(' ')' 'OVER' '(' partitionBy? orderBy? ')'
//  | ('DENSE_RANK' | 'RANK' | 'ROW_NUMBER') '(' ')' 'OVER' '(' partitionBy? orderBy ')'
//  | ('LAG' | 'LEAD') '(' expr (',' signed_number)? (',' signed_number)? ')' 'OVER' '(' partitionBy? orderBy ')'
//  | 'NTH_VALUE' '(' expr ',' signed_number ')' 'OVER' '(' partitionBy? orderBy frame_clause? ')'
//  | 'NTILE' '(' expr ')' 'OVER' '(' partitionBy? orderBy ')'
//  ;

//partitionBy
//  : 'PARTITION' 'BY' expr+ ;

orderBy
  : 'ORDER' 'BY' orderingTerm ( ',' orderingTerm )*
  ;

orderingTerm
  : expr ('COLLATE' name)? sortDir? ('NULLS' ('FIRST' | 'LAST'))?
  ;

sortDir
  : 'ASC' | 'DESC' ;

//TODO BOTH OF THESE HAVE TO BE REWORKED TO FOLLOW THE SPEC
module_argument
    :
    expr
    | columnDef
;

alias : 'AS'? gorp ;

gorp
  : ID
  | STRING
  | keyword // ???
  ;

qname
  : name ( '.' name )* ;

columns
  : '(' name ( ',' name )* ')' ;

name
  : ID
  | keyword
  | STRING
  | '(' name ')'
  ;

// http://www.sqlite.org/lang_keywords.html
// TODO remove reserved keywords
keyword
    : 'ABORT'
    | 'ACTION'
    | 'ADD'
    | 'AFTER'
    | 'ALL'
    | 'ALTER'
    | 'ANALYZE'
    | 'AND'
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
    | 'CHECK'
    | 'COLLATE'
    | 'COLUMN'
    | 'COMMIT'
    | 'CONFLICT'
    | 'CONSTRAINT'
    | 'CREATE'
    | 'CROSS'
    | 'CURRENT_DATE'
    | 'CURRENT_TIME'
    | 'CURRENT_TIMESTAMP'
    | 'DATABASE'
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
    | 'FAIL'
    | 'FOR'
    | 'FOREIGN'
    | 'FROM'
    | 'FULL'
    | 'GLOB'
    | 'GROUP'
    | 'HAVING'
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
    | 'INTO'
    | 'IS'
    | 'ISNULL'
    | 'JOIN'
    | 'KEY'
    | 'LEFT'
    | 'LIKE'
    | 'LIMIT'
    | 'MATCH'
    | 'NATURAL'
    | 'NO'
    | 'NOT'
    | 'NOTNULL'
    | 'NULL'
    | 'OF'
    | 'OFFSET'
    | 'ON'
    | 'OR'
    | 'ORDER'
    | 'OUTER'
    | 'PLAN'
    | 'PRAGMA'
    | 'PRIMARY'
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
    | 'ROWS'
    | 'SAVEPOINT'
    | 'SELECT'
    | 'SET'
    | 'TABLE'
    | 'TEMP'
    | 'TEMPORARY'
    | 'THEN'
    | 'TO'
    | 'TRANSACTION'
    | 'TRIGGER'
    | 'UNION'
    | 'UNIQUE'
    | 'UPDATE'
    | 'USING'
    | 'VACUUM'
    | 'VALUES'
    | 'VIEW'
    | 'VIRTUAL'
    | 'WHEN'
    | 'WHERE'
    | 'WITH'
    | 'WITHOUT'
    | 'FIRST_VALUE'
    | 'OVER'
    | 'PARTITION'
    | 'RANGE'
    | 'PRECEDING'
    | 'UNBOUNDED'
    | 'CURRENT'
    | 'FOLLOWING'
    | 'CUME_DIST'
    | 'DENSE_RANK'
    | 'LAG'
    | 'LAST_VALUE'
    | 'LEAD'
    | 'NTH_VALUE'
    | 'NTILE'
    | 'PERCENT_RANK'
    | 'RANK'
    | 'ROW_NUMBER'
    | 'GENERATED'
    | 'ALWAYS'
    | 'STORED'
    | 'TRUE'
    | 'FALSE'
    | 'WINDOW'
    | 'NULLS'
    | 'FIRST'
    | 'LAST'
    | 'FILTER'
    | 'GROUPS'
    | 'EXCLUDE'
    ;

literal
  : NUMBER
  | STRING
  | BLOB
  | 'NULL'
  | 'TRUE'
  | 'FALSE'
  | 'CURRENT_TIME'
  | 'CURRENT_DATE'
  | 'CURRENT_TIMESTAMP'
  ;

ID
  : '"' (~'"' | '""')* '"'
  | '`' (~'`' | '``')* '`'
  | '[' ~']'* ']'
  | [A-Z_\u007F-\uFFFF] [A-Z_0-9\u007F-\uFFFF]*
  ;

NUMBER
  : ( DIGITS ( '.' DIGITS? )? | '.' DIGITS )
    ( 'E' [-+]? DIGITS )?
  | '0x' HEX_DIGIT+
  ;

PARAM
  : '?' DIGITS*
  | [:@$] ID
  ;

STRING : '\'' ( ~'\'' | '\'\'' )* '\'' ;

BLOB : 'X' STRING ;

SINGLE_LINE_COMMENT: '--' ~[\r\n]* (( '\r'? '\n' ) | EOF ) -> channel(HIDDEN);

MULTILINE_COMMENT: '/*' .*? '*/' -> channel(HIDDEN);

SPACES: [ \u000B\t\r\n] -> channel(HIDDEN);

UNEXPECTED_CHAR: . ;

fragment HEX_DIGIT : [0-9A-F];
fragment DIGITS    : [0-9]+;
