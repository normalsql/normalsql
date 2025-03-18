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

parse : statement? ( ';' statement? )* EOF ;

statement
    : ('EXPLAIN' ('QUERY' 'PLAN')?)?
      ( alter_table
      | analyze
      | attach
      | begin
      | commit
      | create_index
      | create_table
      | create_trigger
      | create_view
      | create_virtual_table
//      | delete
      | delete ( orderBy? limit )?
      | detach
      | drop
      | insert
      | pragma
      | reindex
      | release
      | rollback
      | savepoint
      | select
//      | update
      | update ( orderBy? limit )?
      | vacuum
    )
;

alter_table
    : 'ALTER' 'TABLE' qname
      ( 'RENAME'
        ( 'TO' new_table_name = name
        | 'COLUMN'? old_column_name = name 'TO' new_column_name = name
        )
      | 'ADD' 'COLUMN'? column_def
      | 'DROP' 'COLUMN'? name
      )
    ;

analyze
    : 'ANALYZE' qname? ;

attach
    : 'ATTACH' 'DATABASE'? expr 'AS' name ;

begin
    : 'BEGIN' ( 'DEFERRED' | 'IMMEDIATE' | 'EXCLUSIVE' )? ( 'TRANSACTION' name? )?
;

commit
    : ( 'COMMIT' | 'END' ) 'TRANSACTION'?
;

rollback
    : 'ROLLBACK' 'TRANSACTION'? ( 'TO' 'SAVEPOINT'? name )?
;

savepoint
    : 'SAVEPOINT' name
;

release
    : 'RELEASE' 'SAVEPOINT'? name
;

create_index
  : 'CREATE' 'UNIQUE'? 'INDEX' ifNotExists? qname 'ON' name columnIndexes where?
  ;


columnIndexes
  : '(' columnIndex ( ',' columnIndex )* ')'
  ;

columnIndex
  : ( name | expr ) ('COLLATE' name)? sortDir?
  ;

create_table
    : 'CREATE' temp? 'TABLE' ifNotExists? qname (
        '(' column_def (',' column_def)*? (',' table_constraint)* ')' (
            'WITHOUT' row_ROW_ID = ID
        )?
        | 'AS' select
    )
;

ifNotExists : 'IF' 'NOT' 'EXISTS' ;

column_def
    : name type_name? column_constraint*
;

type_name
    : name*
      ( '(' signed_number ')'
      | '(' signed_number ',' signed_number ')'
      )?
;

column_constraint
    : ('CONSTRAINT' name)? (
        ('PRIMARY' 'KEY' sortDir? conflict? 'AUTOINCREMENT'?)
        | ('NOT'? 'NULL' | 'UNIQUE') conflict?
        | 'CHECK' '(' expr ')'
        | 'DEFAULT' (signed_number | literal | '(' expr ')')
        | 'COLLATE' name
        | foreign_key
        | ('GENERATED' 'ALWAYS')? 'AS' '(' expr ')' ('STORED' | 'VIRTUAL')?
    )
;

signed_number
    : ('+' | '-')? NUMBER
;

table_constraint
    : ('CONSTRAINT' name)? (
        ('PRIMARY' 'KEY' | 'UNIQUE') columnIndexes conflict?
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
    : 'ON' 'CONFLICT' ('ROLLBACK' | 'ABORT' | 'FAIL' | 'IGNORE' | 'REPLACE')
;

create_trigger
  : 'CREATE' temp? 'TRIGGER' ifNotExists? qname
    ( 'BEFORE' | 'AFTER' | 'INSTEAD' 'OF' )?
    ( 'DELETE' | 'INSERT' | 'UPDATE' ( 'OF' name ( ',' name )* )? )
    'ON' name ( 'FOR' 'EACH' 'ROW' )?
    ( 'WHEN' expr )?
    'BEGIN' ( (update | insert | delete | select) ';' )+ 'END'
  ;

create_view
    : 'CREATE'? 'VIEW' ifNotExists? qname
      columns? 'AS' select
    ;
    
temp : 'TEMP' | 'TEMPORARY' ;

create_virtual_table
    : 'CREATE' 'VIRTUAL' 'TABLE' ifNotExists? qname 'USING' name (
        '(' module_argument (',' module_argument)* ')'
    )?
;

with
    : 'WITH' 'RECURSIVE'? cte_table_name 'AS' '(' select ')'
      ( ',' cte_table_name 'AS' '(' select ')' )*
;

cte_table_name
    : name columns?
;

recursive_cte
    : cte_table_name 'AS' '(' select 'UNION' 'ALL'? select ')'
;

cte
    : name columns? 'AS' '(' select ')' ;

delete
    : with? 'DELETE' 'FROM' qualifiedName where? returning?
;

detach
    : 'DETACH' 'DATABASE'? name
;

drop
    : 'DROP' object = ( 'INDEX' | 'TABLE' | 'TRIGGER' | 'VIEW' ) ( 'IF' 'EXISTS' )? qname
;

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
    | ('-'
    | '+'
    | '~'
    | 'NOT') expr
    | expr '||' expr
    | expr ( '*' | '/' | '%') expr
    | expr ( '+' | '-') expr
    | expr ( '<<' | '>>' | '&' | '|') expr
    | expr ( '<' | '<=' | '>' | '>=') expr
    | expr (
        '='
        | '=='
        | '!='
        | '<>'
        | 'IS'
        | 'IS' 'NOT'
        | 'IS' 'NOT'? 'DISTINCT' 'FROM'
        | 'IN'
        | 'LIKE'
        | 'GLOB'
        | 'MATCH'
        | 'REGEXP'
    ) expr
    | expr 'AND' expr
    | expr 'OR' expr
    | name '(' (('DISTINCT'? expr ( ',' expr)*) | '*')? ')' filter? over?
    | '(' exprs ')'
    | 'CAST' '(' expr 'AS' type_name ')'
    | expr 'COLLATE' name
    | expr 'NOT'? ('LIKE' | 'GLOB' | 'REGEXP' | 'MATCH') expr ('ESCAPE' expr)?
    | expr ( 'ISNULL' | 'NOTNULL' | 'NOT' 'NULL')
    | expr 'IS' 'NOT'? expr
    | expr 'NOT'? 'BETWEEN' expr 'AND' expr
    | expr 'NOT'? 'IN'
      ( '(' (select | expr ( ',' expr)*)? ')'
      | qname
      | qname '(' (exprs)? ')'
      )
    | ( 'NOT'? 'EXISTS' )? '(' select ')'
    | 'CASE' expr? ('WHEN' expr 'THEN' expr)+ ('ELSE' expr)? 'END'
    | raise
;

raise
    : 'RAISE' '(' ( 'IGNORE' | ( 'ROLLBACK' | 'ABORT' | 'FAIL' ) ',' STRING ) ')'
;



values
    : 'VALUES' value_row (',' value_row)*
;


value_row
    : '(' exprs ')'
;

insert
    : with? (
        'INSERT'
        | 'REPLACE'
        | 'INSERT' 'OR' ( 'REPLACE' | 'ROLLBACK' | 'ABORT' | 'FAIL' | 'IGNORE')
    ) 'INTO' qname ('AS' name)? columns? (( ( values | select) upsert_clause?) | 'DEFAULT' 'VALUES') returning?
;

returning : 'RETURNING' items ;

upsert_clause
    : 'ON' 'CONFLICT' ( columnIndexes where? )?
      'DO'
        ( 'NOTHING'
        | 'UPDATE' 'SET'
            (name | columns) '=' expr (
                ',' (name | columns) '=' expr
            )* where?

        )
    ;

pragma
    : 'PRAGMA' qname 
      ( '=' pragma_value
      | '(' pragma_value ')'
      )?
;

pragma_value
    : signed_number
    | name
    | STRING
;

reindex
    : 'REINDEX' qname?
;

select
    : common_table_stmt? select_core (compound_operator select_core)* orderBy? limit?
;

join_clause
    : table_or_subquery (join_operator table_or_subquery join_constraint?)*
;

select_core
    : ( 'SELECT' ('DISTINCT' | 'ALL')? items
        ( 'FROM' (table_or_subquery (',' table_or_subquery)* | join_clause) )?
        ('WHERE' whereExpr = expr)?
        ( 'GROUP' 'BY' groupByExpr += expr (',' groupByExpr += expr )* (
                'HAVING' havingExpr = expr
            )?
        )?

        ('WINDOW' name 'AS' window_defn ( ',' name 'AS' window_defn)*)?
    )
    | values
;

factored_select_stmt
    : select
;

simple_select_stmt
    : common_table_stmt? select_core orderBy? limit?
;

compound_select_stmt
    : common_table_stmt? select_core (
        ('UNION' 'ALL'? | 'INTERSECT' | 'EXCEPT') select_core
    )+ orderBy? limit?
;

table_or_subquery
    : qualifiedName
    | qname '(' exprs ')' alias?
    | '(' (table_or_subquery (',' table_or_subquery)* | join_clause) ')'
//    | '(' select ')' ('AS'? name)?
    | '(' select ')' alias?
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

compound_operator
    : 'UNION' 'ALL'?
    | 'INTERSECT'
    | 'EXCEPT'
;

update
    : with? 'UPDATE' ('OR' ('ROLLBACK' | 'ABORT' | 'REPLACE' | 'FAIL' | 'IGNORE'))? qualifiedName 'SET' (
        name
        | columns
    ) '=' expr (',' (name | columns) '=' expr)* (
        'FROM' (table_or_subquery (',' table_or_subquery)* | join_clause)
    )? where? returning?
;


where : 'WHERE' expr ;

qualifiedName
    : qname ('AS' name)?
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

window_defn
    : '(' name? ('PARTITION' 'BY' exprs)? orderBy frame_spec? ')'
;

over
    : 'OVER' (
        name
        | '(' name? ('PARTITION' 'BY' exprs)? orderBy? frame_spec? ')'
    )
;

frame_spec
    : frame_clause ('EXCLUDE' ( 'NO' 'OTHERS' | 'CURRENT' 'ROW' | 'GROUP' | 'TIES'))?
;

frame_clause
    : ('RANGE' | 'ROWS' | 'GROUPS')
      ( frame_single
      | 'BETWEEN' frame_left 'AND' frame_right
      )
    ;

simple_function_invocation
    : name '(' (exprs | '*') ')'
;

aggregate_function_invocation
    : name '(' ('DISTINCT'? exprs | '*')? ')' filter?
;

window_function_invocation
    : window_function '(' ( exprs | '*' )? ')' filter? 'OVER' (
        window_defn
        | name
    )
;

common_table_stmt
    : 'WITH' 'RECURSIVE'? cte ( ',' cte )*
;


limit
    : 'LIMIT' expr (('OFFSET' | ',') expr)?
;


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


window_function
    : ('FIRST_VALUE' | 'LAST_VALUE') '(' expr ')' 'OVER' '(' partition_by? orderBy frame_clause? ')'
    | ('CUME_DIST' | 'PERCENT_RANK') '(' ')' 'OVER' '(' partition_by? orderBy? ')'
    | ('DENSE_RANK' | 'RANK' | 'ROW_NUMBER') '(' ')' 'OVER' '(' partition_by? orderBy ')'
    | ('LAG' | 'LEAD') '(' expr offset? default_value? ')' 'OVER' '(' partition_by? orderBy ')'
    | 'NTH_VALUE' '(' expr ',' signed_number ')' 'OVER' '(' partition_by? orderBy frame_clause? ')'
    | 'NTILE' '(' expr ')' 'OVER' '(' partition_by? orderBy ')'
;

offset
    : ',' signed_number
;

default_value
    : ',' signed_number
;

partition_by
    : 'PARTITION' 'BY' expr+
;

orderBy
  : 'ORDER' 'BY' orderingTerm ( ',' orderingTerm )*
  ;

orderingTerm
  : expr ('COLLATE' name)? sortDir? ('NULLS' ('FIRST' | 'LAST'))?
  ;

sortDir
  : 'ASC'
  | 'DESC'
  ;


//TODO BOTH OF THESE HAVE TO BE REWORKED TO FOLLOW THE SPEC
module_argument
    :
    expr
    | column_def
;

alias : 'AS'? gorp ;

gorp
    : ID
    | STRING
;

qname
    : name ( '.' name )*
    ;

columns
    : '(' name ( ',' name )* ')'
;

name
    : ID
    | keyword
    | STRING
    | '(' name ')'
  ;

// http://www.sqlite.org/lang_keywords.html

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
  : ( DIGIT+ ( '.' DIGIT* )?
    | '.' DIGIT+
    )
    ('E' [-+]? DIGIT+)?
  | '0x' HEX_DIGIT+
  ;

PARAM
  : '?' DIGIT*
  | [:@$] ID
  ;

STRING : '\'' ( ~'\'' | '\'\'')* '\'';

BLOB : 'X' STRING;

SINGLE_LINE_COMMENT: '--' ~[\r\n]* (('\r'? '\n') | EOF) -> channel(HIDDEN);

MULTILINE_COMMENT: '/*' .*? '*/' -> channel(HIDDEN);

SPACES: [ \u000B\t\r\n] -> channel(HIDDEN);

UNEXPECTED_CHAR: . ;

fragment HEX_DIGIT : [0-9A-F];
fragment DIGIT     : [0-9];
