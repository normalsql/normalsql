// Copyright 2010-2025 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

/*
  SQLite.g4

  Synthesis of Bart Keirs' (et al) sqlite-parser
  ( https://github.com/antlr/grammars-v4/tree/master/sql/sqlite ) and
  NormalSQL's grammar.

  Given the same input, goal is for each dialect's grammar to emit the
  same parse tree. This enables NormalSQL to be dialect agnostic. We'll see.

  I copied Keirs's grammar, incrementally changing it, fixing any
  regressions I introduced. Over time, it became more like NormalSQL.g4.
  Rule names, idioms, style, and so forth.

  Keirs' work was a huge help. I picked the smallest SQL grammar to validate
  my strategy (of distinct grammars per dialect instead of an pluripotent grammar
  covering multiple dialects). It was fun learning SQLite specific differences;
  I corrected things I misunderstood about both SQL and SQLite. Woot.

  I don't yet know if Apache-2.0 and MIT licenses are compatible or
  whatever. I'll change NormalSQL's license(s) as needed. I have no opinions or
  preferences; NormalSQL uses APL 2.0 because it seems to be the default for FOSS.

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

@parser::members
{
boolean notBETWEEN( ParserRuleContext ctx )
{
  if( ctx.children == null ) return true;
  for( var c : ctx.children )
  {
    if( c.getText().equalsIgnoreCase( "BETWEEN" ) )
    {
      return false;
     }
  }
  return true;
};
}

statements
  : statement? ( ';' statement? )* EOF ;

statement
  : ( 'EXPLAIN' ( 'QUERY' 'PLAN' )? )?
    ( alter
    | analyze
    | attach
    | begin
    | commit
    | createIndex
    | createTable
    | createTrigger
    | createView
    | createVirtualTable
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
  : 'ATTACH' 'DATABASE'? term 'AS' name ;

begin
  : 'BEGIN' ( 'DEFERRED' | 'IMMEDIATE' | 'EXCLUSIVE' )? ( 'TRANSACTION' name? )? ;

commit
  : ( 'COMMIT' | 'END' ) 'TRANSACTION'? ;

createIndex
  : 'CREATE' 'UNIQUE'? 'INDEX' ifNotExists? qname 'ON' name columnIndexes where? ;

createTable
  : 'CREATE' temp? 'TABLE' ifNotExists? qname
    ( '(' columnDef ( ',' columnDef )*? ( ',' table_constraint )* ')' ( 'WITHOUT' ID )?
    | 'AS' select
    )
  ;

createTrigger
  : 'CREATE' temp? 'TRIGGER' ifNotExists? qname
    ( 'BEFORE' | 'AFTER' | 'INSTEAD' 'OF' )?
    ( 'DELETE' | 'INSERT' | 'UPDATE' ( 'OF' name ( ',' name )* )? )
    'ON' qname ( 'FOR' 'EACH' 'ROW' )?
    ( 'WHEN' term )?
    'BEGIN' ( ( update | insert | delete | select ) ';' )+ 'END'
  ;

createView
  : 'CREATE'? temp? 'VIEW' ifNotExists? qname columns? 'AS' select ;

createVirtualTable
  : 'CREATE' 'VIRTUAL' 'TABLE' ifNotExists? qname
     // TODO support "tokenize=<tokenizer name> <tokenizer args>" module arguments
     // https://www.sqlite.org/fts3.html#creating_and_destroying_fts_tables
    'USING' name ( '(' columnDef ( ',' columnDef )* ')' )?
  ;

delete
  : with? 'DELETE' 'FROM' indexedBy where? returning? ;

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
  : ( name | term ) ( 'COLLATE' name )? sortDir? ;

ifNotExists
  : 'IF' 'NOT' 'EXISTS' ;

columnDef
  : name type? constraint* ;

type
  : name+ (  '(' signed_number ( ',' signed_number )? ')' )? ;

constraint
  : ( 'CONSTRAINT' name )?
    ( ( 'PRIMARY' 'KEY' sortDir? onConflict? 'AUTOINCREMENT'? )
    | ( 'NOT'? 'NULL' | 'UNIQUE' ) onConflict?
    | 'CHECK' '(' term ')'
    | 'DEFAULT' ( signed_number | literal | '(' term ')' )
    | 'COLLATE' name
    | foreign_key
    | ( 'GENERATED' 'ALWAYS' )? 'AS' '(' term ')' ( 'STORED' | 'VIRTUAL' )?
    )
  ;

signed_number
  : ('+' | '-')? NUMBER ;

table_constraint
  : ( 'CONSTRAINT' name )?
    ( ( 'PRIMARY' 'KEY' | 'UNIQUE' ) columnIndexes onConflict?
    | 'CHECK' '(' term ')'
    | 'FOREIGN' 'KEY' columns foreign_key
    )
  ;

foreign_key
  : 'REFERENCES' name columns?
    ( 'ON' ( 'DELETE' | 'UPDATE' )
      ( 'SET' ( 'NULL' | 'DEFAULT' ) | 'CASCADE' | 'RESTRICT' | 'NO' 'ACTION' )
      | 'MATCH' name
    )*
    ( 'NOT'? 'DEFERRABLE' ( 'INITIALLY' ( 'DEFERRED' | 'IMMEDIATE' ))?)?
  ;

onConflict
  : 'ON' 'CONFLICT' action ;

temp
  : 'TEMP' | 'TEMPORARY' ;

with
  : 'WITH' 'RECURSIVE'? cte ( ',' cte )* ;

cte
  : name columns? 'AS' ( 'NOT'? 'MATERIALIZED' )? '(' select ')' ;

terms
  : term ( ',' term )* ;

term
  : literal
  | qname

  | ( '~' | '+' | '-' ) term
  | term 'COLLATE' name
  | term ( '||' | '->' | '->>' ) term
  | term ( '*' | '/' | '%' ) term
  | term ( '+' | '-' ) term
  | term ( '&' | '|' | '<<' | '>>' ) term
  | term 'ESCAPE' term
  | term ( '<' | '>' | '<=' | '>=' ) term
  | term ( '=' | '==' | '!=' | '<>' ) term

  | term 'IS' 'NOT'? ( 'DISTINCT' 'FROM' )? term
  | term ( 'ISNULL' | 'NOTNULL' | 'NOT' 'NULL' )
  | term 'NOT'? ( 'LIKE' | 'GLOB' | 'REGEXP' | 'MATCH' ) term
  | term 'NOT'? 'IN' ( '(' ( select | terms )? ')' | qname ( '(' terms? ')' )? )
  | term 'NOT'? 'BETWEEN' term 'AND' term

  | 'CASE' term? ( 'WHEN' term 'THEN' term )+ ( 'ELSE' term )? 'END'
  | function filter? over?
  | raise
  | 'EXISTS'? '(' select ')'
  | '(' term ')'
  | 'NOT' term
  // workaround to ensure BETWEEN beats AND, building correct parse tree
  | term { notBETWEEN( $ctx ) }? 'AND' term // #TermIgnore
  | term 'OR' term // #TermIgnore
  ;

function
  : name '(' ( ( 'DISTINCT'? terms orderBy? ) | '*'  )? ')'
  | 'CAST' '(' term 'AS' type ')'
  ;

raise
  : 'RAISE' '(' ( 'IGNORE' | ( 'ROLLBACK' | 'ABORT' | 'FAIL' ) ',' string ) ')' ;

values
  : 'VALUES' terms ;

insert
  : with?
    ( 'INSERT'
    | 'REPLACE'
    | 'INSERT' 'OR' action
    )
    'INTO' qname alias? columns?
    ( select upsert? | 'DEFAULT' 'VALUES' )
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

setter
  : 'SET' assign ( ',' assign )* ;

assign
  : ( name | columns ) '=' term ;

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

select
  : with?
    selectCore (( 'UNION' 'ALL'? | 'INTERSECT' | 'EXCEPT' ) selectCore )*
    orderBy? limit?
  ;

selectCore
  : 'SELECT' ( 'DISTINCT' | 'ALL' )? ( item ( ',' item )* ','? )?
    from?
    where?
    // TODO decide between inlined or separate rules for GROUP BY and WINDOW
    ( 'GROUP' 'BY' terms ( 'HAVING' term )? )?
    ( 'WINDOW' window ( ',' window )* )?
  | values
  | '(' select ')'
  ;

window
  : name 'AS' windowDef ;

items
  : item ( ',' item )* ;

item
  : '*'
//  | qname '.' '*'
  | term alias?
  ;

from
  : 'FROM' tables ( ',' tables )* ;

tables
  : tables joinType? 'JOIN' tables ( 'ON' term | 'USING' columns ) indexedBy?
  | tables 'NATURAL' joinType? 'JOIN' tables
  | tables 'CROSS' 'JOIN' tables
  | qname tableAlias? indexedBy?
  | '(' select  ')' tableAlias?
  | '(' tables ')' tableAlias?
  ;

joinType
  : 'INNER'
  | ( 'FULL' | 'LEFT' | 'RIGHT' ) 'OUTER'?
  ;

tableAlias
  : alias columns? ;

update
  : with? 'UPDATE' ( 'OR' action )? indexedBy
    setter
    from?
    where? returning?
  ;

where
  : 'WHERE' term ;

indexedBy
  : 'INDEXED' 'BY' name
  | 'NOT' 'INDEXED'
  ;

vacuum
  : 'VACUUM' name? ( 'INTO' name )? ;

filter
  : 'FILTER' '(' 'WHERE' term ')' ;

over
  : 'OVER' ( name | windowDef ) ;

windowDef
  : '(' name? ( 'PARTITION' 'BY' terms )? orderBy? windowFrame? ')' ;

windowFrame
  : ( 'RANGE' | 'ROWS' | 'GROUPS' )
    ( 'BETWEEN' windowFrameBounds 'AND' )? windowFrameBounds
    ( 'EXCLUDE' ( 'NO' 'OTHERS' | 'CURRENT' 'ROW' | 'GROUP' | 'TIES' ))?
  ;

windowFrameBounds
  : ( 'UNBOUNDED'  | term  ) ( 'PRECEDING' | 'FOLLOWING' )
//  : ( 'UNBOUNDED'   ) ( 'PRECEDING' | 'FOLLOWING' )
  | 'CURRENT' 'ROW'
  ;

limit
  : 'LIMIT' term (( 'OFFSET' | ',' ) term )? ;

orderBy
  : 'ORDER' 'BY' orderingTerm ( ',' orderingTerm )* ;

orderingTerm
  : term ( 'COLLATE' name )? sortDir? ( 'NULLS' ( 'FIRST' | 'LAST' ))? ;

sortDir
  : 'ASC' | 'DESC' ;

alias
  : 'AS'? name ;

qname
  : name ( '.' name )* ;

columns
  : '(' name ( ',' name )* ')' ;

name
  : ID | string | unreservedKeyword ;

// TODO regenerate this list of keywords (then commenting out reserved)
unreservedKeyword
  : 'ABORT'
  | 'AFTER'
  | 'ALL'
  | 'ALWAYS'
  | 'ANALYZE'
  | 'ATTACH'
  | 'AUTOINCREMENT'
  | 'BEFORE'
  | 'CONFLICT'
  | 'DATABASE'
  | 'DETACH'
  | 'DO'
  | 'EXCLUDE'
  | 'EXCLUSIVE'
  | 'EXPLAIN'
  | 'FAIL'
  | 'FILTER'
  | 'FOLLOWING'
  | 'GENERATED'
  | 'GLOB'
  | 'GROUPS'
  | 'IF'
  | 'IGNORE'
  | 'INDEXED'
  | 'INSTEAD'
  | 'ISNULL'
  | 'LIMIT'
  | 'MATERIALIZED'
  | 'NOTHING'
  | 'NOTNULL'
  | 'NULL'
  | 'NULLS'
  | 'OFFSET'
  | 'OTHERS'
  | 'OVER'
  | 'PARTITION'
  | 'PLAN'
  | 'PRAGMA'
  | 'PRECEDING'
  | 'QUERY'
  | 'RAISE'
  | 'RANGE'
  | 'RECURSIVE'
  | 'REGEXP'
  | 'REINDEX'
  | 'RELEASE'
  | 'RENAME'
  | 'REPLACE'
  | 'RETURNING'
  | 'ROWS'
  | 'SAVEPOINT'
  | 'STORED'
  | 'TEMP'
  | 'TIES'
  | 'TRIGGER'
  | 'UNBOUNDED'
  | 'USING'
  | 'VACUUM'
  | 'VIRTUAL'
  | 'WINDOW'
  | 'WITHOUT'
  ;

literal
  : PARAM
  | NUMBER
  | string
  | BLOB
  ;

string
  : STRING+ ;

ID
  : '"' ( ~'"' | '""' )* '"'
  | '`' ( ~'`' | '``' )* '`'
  | '[' ~']'* ']'
  | [A-Z_\u007F-\uFFFF] [A-Z0-9_$\u007F-\uFFFF]*
  ;

STRING
  : '\'' ( ~'\'' | '\'\'' )* '\'' ;

BLOB
  : 'X' STRING ;

NUMBER
  : ( DIGITS ( '.' DIGITS? )? | '.' DIGITS ) ( 'E' [-+]? DIGITS )?
  | '0x' HEX_DIGIT+
  ;

PARAM
  : '?' DIGITS*
  | [:@$] ID
  ;

COMMENT
  : '--' ~[\r\n]* (( '\r'? '\n' ) | EOF ) -> channel(HIDDEN) ;

BLOCK_COMMENT
  : '/*' .*? '*/' -> channel(HIDDEN) ;

WHITESPACE
  : [ \u000B\t\r\n] -> channel(HIDDEN) ;

fragment DIGITS    : [0-9]+;
fragment HEX_DIGIT : [0-9A-F];
