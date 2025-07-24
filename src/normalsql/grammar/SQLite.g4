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
  contextSuperClass = normalsql.knockout.GlobbingRuleContext;
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
  : ( ( dml | ddl | explain )? ';' )* EOF;

dml
    : select
    | insert
    // ORDER BY & LIMIT only supported for top-level statements
    // https://www.sqlite.org/compile.html#enable_update_delete_limit
    | update ( orderBy? limit )?
    | delete ( orderBy? limit )?
    ;

with
  : 'WITH' 'RECURSIVE'? cte ( ',' cte )* ;

cte
  : name ( '(' name ( ',' name )* ')' )? 'AS' ( 'NOT'? 'MATERIALIZED' )? '(' select ')' ;

select
    : with?
      selectCore ( ( 'UNION' 'ALL'? | 'INTERSECT' | 'EXCEPT' ) selectCore )*
      orderBy? limit?
    ;

    selectCore
        : 'SELECT' unique_? ( item ( ',' item )* ','? )?
          ( 'FROM' tables )? where? groupBy? having?
          ( 'WINDOW' window ( ',' window )* )?
        | values
        | '(' select ')'
        ;

        item
            : ( qname '.' )? '*'  # ItemAll
            | qname alias?  # ItemColumn
            | term alias?  # ItemTerm
            ;

        tables
            : tables ',' tables
            | tables joinType? 'JOIN' tables ( 'ON' term | 'USING' '(' name ( ',' name )* ')' ) indexedBy?
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
              : alias ( '(' name ( ',' name )* ')' )? ;

        where
            : 'WHERE' term ;

        groupBy
            : 'GROUP' 'BY' term ( ',' term )* ;

        having
            : 'HAVING' term ;

        window
            : name 'AS' windowDef ;

            windowDef
                : '(' name? ( 'PARTITION' 'BY' term ( ',' term )* )? orderBy?
                    ( ( 'RANGE' | 'ROWS' | 'GROUPS' )
                      ( 'BETWEEN' frameBounds 'AND' )?
                      frameBounds
                      ( 'EXCLUDE' ( 'CURRENT' 'ROW' | 'GROUP' | 'TIES' | 'NO' 'OTHERS' ) )?
                    )?
                  ')'
                ;

            frameBounds
                :  term  ( 'PRECEDING' | 'FOLLOWING' )
                | 'CURRENT' 'ROW'
                ;

        values
            : 'VALUES' term ( ',' term )* ;


insert
    : with?
      ( 'INSERT' ( 'OR' action )? | 'REPLACE' )
      'INTO' qname alias?
      ( '(' qname ( ',' qname )* ')' )?
      ( ( values | select ) upsert?
      | 'DEFAULT' 'VALUES'
      )
      returning?
    ;

    action
        : 'ABORT' | 'FAIL' | 'IGNORE' | 'REPLACE' | 'ROLLBACK' ;


    upsert
        : 'ON' 'CONFLICT' ( columnIndexes where? )?
          'DO' ( 'NOTHING' | 'UPDATE' setVariables where? )
        ;

update
    : with? 'UPDATE' ( 'OR' action )? qname indexedBy?
      setVariables
      ( 'FROM' tables )?
      where? returning?
    ;

    indexedBy
      : 'INDEXED' 'BY' name
      | 'NOT' 'INDEXED'
      ;

    returning
        : 'RETURNING' item ( ',' item )* ;

delete
    : with? 'DELETE' 'FROM' qname indexedBy?
      where? returning? ;

ddl
    : alter
    | analyze
    | attach
    | begin
    | commit
    | createIndex
    | createTable
    | createTrigger
    | createView
    | createVirtualTable
    | detach
    | drop
    | pragma
    | reindex
    | release
    | rollback
    | savepoint
    | vacuum
    ;

explain
    : 'EXPLAIN' ( 'QUERY' 'PLAN' )? ( dml | ddl ) ;


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
  : 'CREATE' temp_? 'TABLE' ifNotExists? qname
    ( '(' columnDef ( ',' columnDef )*? ( ',' table_constraint )* ')' ( 'WITHOUT' ID )?
    | 'AS' select
    )
  ;

createTrigger
  : 'CREATE' temp_? 'TRIGGER' ifNotExists? qname
    ( 'BEFORE' | 'AFTER' | 'INSTEAD' 'OF' )?
    ( 'DELETE' | 'INSERT' | 'UPDATE' ( 'OF' name ( ',' name )* )? )
    'ON' qname ( 'FOR' 'EACH' 'ROW' )?
    ( 'WHEN' term )?
    'BEGIN' ( ( update | insert | delete | select ) ';' )+ 'END'
  ;

createView
  : 'CREATE'? temp_? 'VIEW' ifNotExists? qname ( '(' name ( ',' name )* ')' )? 'AS' select ;

createVirtualTable
  : 'CREATE' 'VIRTUAL' 'TABLE' ifNotExists? qname
     // TODO support "tokenize=<tokenizer name> <tokenizer args>" module arguments
     // https://www.sqlite.org/fts3.html#creating_and_destroying_fts_tables
    'USING' name ( '(' columnDef ( ',' columnDef )* ')' )?
  ;

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
  : ( name | term ) ( 'COLLATE' name )? direction_? ;

ifNotExists
  : 'IF' 'NOT' 'EXISTS' ;

columnDef
  : name type? constraint* ;

type
  : name+ (  '(' signed_number ( ',' signed_number )? ')' )? ;

constraint
  : ( 'CONSTRAINT' name )?
    ( ( 'PRIMARY' 'KEY' direction_? onConflict? 'AUTOINCREMENT'? )
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
    | 'FOREIGN' 'KEY' '(' name ( ',' name )* ')' foreign_key
    )
  ;

foreign_key
  : 'REFERENCES' name ( '(' name ( ',' name )* ')' )?
    ( 'ON' ( 'DELETE' | 'UPDATE' )
      ( 'SET' ( 'NULL' | 'DEFAULT' ) | 'CASCADE' | 'RESTRICT' | 'NO' 'ACTION' )
      | 'MATCH' name
    )*
    ( 'NOT'? 'DEFERRABLE' ( 'INITIALLY' ( 'DEFERRED' | 'IMMEDIATE' ) )? )?
  ;

onConflict
  : 'ON' 'CONFLICT' action ;

term
  : literal #TermLiteral
  | qname  #TermColumn
  | ( '~' | '+' | '-' ) term #TermIgnore
  | term 'COLLATE' qname #TermIgnore
  | term ( '||' | '->' | '->>' ) term #TermIgnore
  | term ( '*' | '/' | '%' ) term #TermIgnore
  | term ( '+' | '-' ) term #TermIgnore
  | term ( '&' | '|' | '<<' | '>>' ) term #TermIgnore
  | term 'ESCAPE' term #TermIgnore
  | term ( '<' | '>' | '<=' | '>=' ) term #TermCompare
  | term ( '=' | '==' | '!=' | '<>' ) term #TermCompare

  | term 'IS' 'NOT'? ( 'DISTINCT' 'FROM' )? term #TermIgnore
  | term ( 'ISNULL' | 'NOTNULL' | 'NOT' 'NULL' ) #TermIgnore
  | term 'NOT'? ( 'LIKE' | 'GLOB' | 'REGEXP' | 'MATCH' ) term #TermLIKE
  | term 'NOT'? 'IN' '(' term ( ',' term )* ')' #TermIN
  | term 'NOT'? 'IN' '(' select? ')' # TermIgnore
  | term 'NOT'? 'IN'  qname ( '(' ( term ( ',' term )* )? ')' )?  # TermIgnore
  | term 'NOT'? 'BETWEEN' term 'AND' term #TermBETWEEN

  | 'CASE' term? ( 'WHEN' term 'THEN' term )+ ( 'ELSE' term )? 'END' #TermIgnore
  | function filter? over? #TermIgnore
  | raise #TermIgnore
  | 'EXISTS'? '(' select ')' #TermIgnore
  | '(' term ( ',' term )* ')' #TermRow
  | 'NOT' term #TermIgnore
  // workaround to ensure BETWEEN beats AND, building correct parse tree
  | term 'AND' term #TermIgnore
//  | term { notBETWEEN( $ctx ) }? 'AND' term #TermIgnore
  | term 'OR' term #TermIgnore
  ;

    function
      : name '(' ( ( unique_? term ( ',' term )* orderBy? ) | '*'  )? ')'
      | 'CAST' '(' term 'AS' type ')'
      ;

orderBy
    : 'ORDER' 'BY' orderTerm ( ',' orderTerm )* ;

    orderTerm
        : term ( 'COLLATE' name )? direction_? ( 'NULLS' ( 'FIRST' | 'LAST' ))? ;

raise
    : 'RAISE' '(' ( 'IGNORE' | ( 'ROLLBACK' | 'ABORT' | 'FAIL' ) ',' string ) ')' ;

setVariables
    : 'SET' assign ( ',' assign )* ;

assign
    : ( name | '(' name ( ',' name )* ')' ) '=' term ;

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

vacuum
  : 'VACUUM' name? ( 'INTO' name )? ;

filter
  : 'FILTER' '(' 'WHERE' term ')' ;

over
  : 'OVER' ( name | windowDef ) ;

limit
  : 'LIMIT' term ( ( 'OFFSET' | ',' ) term )? ;

alias
  : 'AS'? name ;

qname
  : name ( '.' name )* ;

// TODO figure out when a string either name or literal
name
  : ID | string | keyword ;

direction_
  : 'ASC' | 'DESC' ;

temp_
  : 'TEMP' | 'TEMPORARY' ;

unique_
     : 'ALL' | 'DISTINCT' ;

keyword
  : 'ABORT'
  | 'ACTION'
  | 'ADD'
  | 'AFTER'
  | 'ALL'
  | 'ALTER'
  | 'ALWAYS'
  | 'ANALYZE'
  | 'AND'
//  | 'AS'
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
  | 'CURRENT'
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
  | 'DO'
  | 'DROP'
  | 'EACH'
  | 'ELSE'
  | 'END'
  | 'ESCAPE'
  | 'EXCEPT'
  | 'EXCLUDE'
  | 'EXCLUSIVE'
  | 'EXISTS'
  | 'EXPLAIN'
  | 'FAIL'
  | 'FALSE'
  | 'FILTER'
  | 'FIRST'
  | 'FOLLOWING'
  | 'FOR'
  | 'FOREIGN'
  | 'FROM'
  | 'FULL'
  | 'GENERATED'
  | 'GLOB'
  | 'GROUP'
  | 'GROUPS'
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
  | 'LAST'
  | 'LEFT'
  | 'LIKE'
  | 'LIMIT'
  | 'MATCH'
  | 'MATERIALIZED'
  | 'NATURAL'
  | 'NO'
  | 'NOT'
  | 'NOTHING'
  | 'NOTNULL'
  | 'NULL'
  | 'NULLS'
  | 'OF'
  | 'OFFSET'
  | 'ON'
  | 'OR'
  | 'ORDER'
  | 'OTHERS'
  | 'OUTER'
  | 'OVER'
  | 'PARTITION'
  | 'PLAN'
  | 'PRAGMA'
  | 'PRECEDING'
  | 'PRIMARY'
  | 'QUERY'
  | 'RAISE'
  | 'RANGE'
  | 'RECURSIVE'
  | 'REFERENCES'
  | 'REGEXP'
  | 'REINDEX'
  | 'RELEASE'
  | 'RENAME'
  | 'REPLACE'
  | 'RESTRICT'
  | 'RETURNING'
  | 'RIGHT'
  | 'ROLLBACK'
  | 'ROW'
  | 'ROWS'
  | 'SAVEPOINT'
//  | 'SELECT'
  | 'SET'
  | 'STORED'
  | 'TABLE'
  | 'TEMP'
  | 'TEMPORARY'
  | 'THEN'
  | 'TIES'
  | 'TO'
  | 'TRANSACTION'
  | 'TRIGGER'
  | 'TRUE'
  | 'UNBOUNDED'
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
  | 'WINDOW'
  | 'WITH'
  | 'WITHOUT'
  ;

literal
  : PARAM
  | NUMBER
  | string
  | BLOB
//  | qname
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
  : '--' ~[\r\n]* ( ( '\r'? '\n' ) | EOF ) -> channel( HIDDEN ) ;

BLOCK_COMMENT
  : '/*' .*? '*/' -> channel( HIDDEN ) ;

WHITESPACE
  : [ \u000B\t\r\n] -> channel( HIDDEN ) ;

fragment DIGITS    : [0-9_]+;
fragment HEX_DIGIT : [0-9A-F_];
