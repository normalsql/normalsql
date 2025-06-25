// Copyright 2010-2025 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

/*
 PostgreSQL.g4

 Synthesis of Oleksii Kovalov' (et al) PostgreSQL grammar
 ( https://github.com/antlr/grammars-v4/tree/master/sql/postgresql ) and
 NormalSQL's grammar.

 Given the same input, goal is for each dialect's grammar to emit the
 same parse tree. This enables NormalSQL to be dialect agnostic. We'll see.

 I copied Kovalov's grammar, incrementally changing it, fixing any
 regressions I introduced. Over time, it became more like NormalSQL.g4.
 Rule names, idioms, style, and so forth.

 Kovalov's work was invaluable. I learned quite a bit about PostgreSQL
 and it's grammar. Like meta commands, unary right operators (?!),
 and much more.

 I don't yet know if Apache-2.0 and MIT licenses are compatible or
 whatever. I'll change NormalSQL's license(s) as needed.

*/

/*
PostgreSQL grammar.
The MIT License (MIT).
Copyright (c) 2021-2023, Oleksii Kovalov (Oleksii.Kovalov@outlook.com).
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
*/

grammar PostgreSQL;

options {
  caseInsensitive=true;
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
  : statement? ( SEMI statement? )* EOF
  ;

statement
    : 'ALTER' 'EVENT' 'TRIGGER' id ( 'ENABLE' ( 'REPLICA' |  'ALWAYS' )? | 'DISABLE' )
    | 'ALTER' 'COLLATION' qname 'REFRESH' 'VERSION'
    | 'ALTER' 'DATABASE' id ( 'WITH'? createdb_opt_item* | 'SET' 'TABLESPACE' id )
    | 'ALTER' 'DATABASE' id setresetclause
    | 'ALTER' 'DEFAULT' 'PRIVILEGES'
      ( 'IN' 'SCHEMA' ids | 'FOR' 'ROLE' ids | 'FOR' 'USER' ids )*
      ( 'GRANT' privileges 'ON' defacl_privilege_target 'TO' grantee ( ',' grantee )* withGrantOption?
      | 'REVOKE' ('GRANT' 'OPTION' 'FOR')? privileges 'ON' defacl_privilege_target 'FROM' grantee ( ',' grantee )* cascade?
      )
    | 'ALTER' 'DOMAIN' qname
      ( ( 'SET' 'DEFAULT' term | 'DROP' 'DEFAULT' )
      | 'DROP' 'NOT' 'NULL'
      | 'SET' 'NOT' 'NULL'
      | 'ADD' tableconstraint
      | 'DROP' 'CONSTRAINT' ifExists? id cascade?
      | 'VALIDATE' 'CONSTRAINT' id
      )
    | 'ALTER' 'TYPE' qname 'ADD' 'VALUE' ifNotExists? string (( 'BEFORE' | 'AFTER' ) string )?
    | 'ALTER' 'TYPE' qname 'RENAME' 'VALUE' string 'TO' string
    | 'ALTER' 'EXTENSION' id 'UPDATE' ( 'TO' name )*
    | 'ALTER' 'EXTENSION' id addDrop object_type_name id
    | 'ALTER' 'EXTENSION' id addDrop object_type_any_name qname
    | 'ALTER' 'EXTENSION' id addDrop 'AGGREGATE' aggregateSignature
    | 'ALTER' 'EXTENSION' id addDrop 'CAST' '(' type 'AS' type ')'
    | 'ALTER' 'EXTENSION' id addDrop 'DOMAIN' type
    | 'ALTER' 'EXTENSION' id addDrop 'FUNCTION' functionSignature
    | 'ALTER' 'EXTENSION' id addDrop 'PROCEDURE' functionSignature
    | 'ALTER' 'EXTENSION' id addDrop 'ROUTINE' functionSignature
    | 'ALTER' 'EXTENSION' id addDrop 'OPERATOR' operatorSignature
    | 'ALTER' 'EXTENSION' id addDrop 'OPERATOR' 'CLASS' qname usingID
    | 'ALTER' 'EXTENSION' id addDrop 'OPERATOR' 'FAMILY' qname usingID
    | 'ALTER' 'EXTENSION' id addDrop 'TRANSFORM' 'FOR' type 'LANGUAGE' id
    | 'ALTER' 'EXTENSION' id addDrop 'TYPE' type
    | 'ALTER' 'FOREIGN' 'DATA' 'WRAPPER' id ( genericOptions | handler+ )
    | 'ALTER' 'SERVER' id ( genericOptions | version genericOptions? )
    | 'ALTER' routine functionSignature funcOptions+ 'RESTRICT'?
    | 'ALTER' 'GROUP' id addDrop 'USER' ids
    | 'ALTER' 'FUNCTION' functionSignature 'NO'? 'DEPENDS' 'ON' 'EXTENSION' id
    | 'ALTER' 'PROCEDURE' functionSignature 'NO'? 'DEPENDS' 'ON' 'EXTENSION' id
    | 'ALTER' 'ROUTINE' functionSignature 'NO'? 'DEPENDS' 'ON' 'EXTENSION' id
    | 'ALTER' 'TRIGGER' id 'ON' qname 'NO'? 'DEPENDS' 'ON' 'EXTENSION' id
    | 'ALTER' 'MATERIALIZED' 'VIEW' qname 'NO'? 'DEPENDS' 'ON' 'EXTENSION' id
    | 'ALTER' 'INDEX' qname 'NO'? 'DEPENDS' 'ON' 'EXTENSION' id
    | ('ALTER' 'AGGREGATE' aggregateSignature 'SET' 'SCHEMA' id
    | 'ALTER' 'COLLATION' qname 'SET' 'SCHEMA' id
    | 'ALTER' 'CONVERSION' qname 'SET' 'SCHEMA' id
    | 'ALTER' 'DOMAIN' qname 'SET' 'SCHEMA' id
    | 'ALTER' 'EXTENSION' id 'SET' 'SCHEMA' id


    | 'ALTER' 'FUNCTION' functionSignature 'SET' 'SCHEMA' id
    | 'ALTER' 'PROCEDURE' functionSignature 'SET' 'SCHEMA' id
    | 'ALTER' 'ROUTINE' functionSignature 'SET' 'SCHEMA' id

    | 'ALTER' 'OPERATOR' operatorSignature 'SET' 'SCHEMA' id
    | 'ALTER' 'OPERATOR' ( 'CLASS' | 'FAMILY' ) qname usingID 'SET' 'SCHEMA' id
    | 'ALTER' 'OPERATOR'  qname usingID 'SET' 'SCHEMA' id
    | 'ALTER' 'TABLE' ifExists? descendants 'SET' 'SCHEMA' id
    | 'ALTER' 'STATISTICS' qname 'SET' 'SCHEMA' id
    | 'ALTER' textSearchConfig qname 'SET' 'SCHEMA' id
    | 'ALTER' 'SEQUENCE' ifExists? qname 'SET' 'SCHEMA' id
    | 'ALTER' 'VIEW' ifExists? qname 'SET' 'SCHEMA' id
    | 'ALTER' 'MATERIALIZED' 'VIEW' ifExists? qname 'SET' 'SCHEMA' id
    | 'ALTER' 'TYPE' qname 'SET' 'SCHEMA' id
    | 'ALTER' 'FOREIGN' 'TABLE' ifExists? descendants 'SET' 'SCHEMA' id)
    | ('ALTER' 'AGGREGATE' aggregateSignature 'OWNER' 'TO' id

    | 'ALTER' 'FUNCTION' functionSignature 'OWNER' 'TO' id
    | 'ALTER' 'PROCEDURE' functionSignature 'OWNER' 'TO' id
    | 'ALTER' 'ROUTINE' functionSignature 'OWNER' 'TO' id

    | 'ALTER' 'OPERATOR' operatorSignature 'OWNER' 'TO' id

    | 'ALTER' 'COLLATION' qname 'OWNER' 'TO' id
    | 'ALTER' 'CONVERSION' qname 'OWNER' 'TO' id
    | 'ALTER' 'DATABASE' id 'OWNER' 'TO' id
    | 'ALTER' 'DOMAIN' qname 'OWNER' 'TO' id
    | 'ALTER' 'EVENT' 'TRIGGER' id 'OWNER' 'TO' id
    | 'ALTER' 'FOREIGN' 'DATA' 'WRAPPER' id 'OWNER' 'TO' id
    | 'ALTER' 'PROCEDURAL'? 'LANGUAGE' id 'OWNER' 'TO' id
    | 'ALTER' 'PUBLICATION' id 'OWNER' 'TO' id
    | 'ALTER' 'SCHEMA' id 'OWNER' 'TO' id
    | 'ALTER' 'SERVER' id 'OWNER' 'TO' id
    | 'ALTER' 'STATISTICS' qname 'OWNER' 'TO' id
    | 'ALTER' 'TEXT' 'SEARCH' 'DICTIONARY' qname 'OWNER' 'TO' id
    | 'ALTER' 'TEXT' 'SEARCH' 'CONFIGURATION' qname 'OWNER' 'TO' id
    | 'ALTER' 'TYPE' qname 'OWNER' 'TO' id

    | 'ALTER' 'LARGE' 'OBJECT' number 'OWNER' 'TO' id
    | 'ALTER' 'OPERATOR' 'CLASS' qname usingID 'OWNER' 'TO' id
    | 'ALTER' 'OPERATOR' 'FAMILY' qname usingID 'OWNER' 'TO' id)
    | 'ALTER' 'OPERATOR' operatorSignature 'SET' '(' operator_def_elem ( ',' operator_def_elem )* ')'
    | 'ALTER' 'TYPE' qname 'SET' '(' operator_def_elem ( ',' operator_def_elem )* ')'
    | 'ALTER' 'POLICY' id 'ON' qname ('TO' ids)? rowsecurityoptionalexpr? rowsecurityoptionalwithcheck?
    | 'ALTER' 'SEQUENCE' ifExists? qname seqoptelem+
    | 'ALTER' 'SYSTEM' ( 'SET' | 'RESET' ) qname ( 'TO' | '=' ) ( 'DEFAULT' | value ( ',' value )* )
    | 'ALTER' 'TABLE' ifExists? descendants ( alter_table_cmds | ('ATTACH' 'PARTITION' qname forValues | 'DETACH' 'PARTITION' qname ) )
    | 'ALTER' 'INDEX' ifExists? qname ( alter_table_cmds | 'ATTACH' 'PARTITION' qname )
    | 'ALTER' 'SEQUENCE' ifExists? qname alter_table_cmds
    | 'ALTER' 'VIEW' ifExists? qname alter_table_cmds
    | 'ALTER' 'MATERIALIZED' 'VIEW' ifExists? qname alter_table_cmds
    | 'ALTER' ( 'INDEX' | 'TABLE' | 'MATERIALIZED' 'VIEW' ) 'ALL' 'IN' 'TABLESPACE' id ( 'OWNED' 'BY' ids )? 'SET' 'TABLESPACE' id 'NOWAIT'?
    | 'ALTER' 'FOREIGN' 'TABLE' ifExists? descendants alter_table_cmds
    | 'ALTER' 'TABLESPACE' id ( 'SET' | 'RESET' ) definition
    | 'ALTER' 'TABLESPACE' id 'OWNER' 'TO' id
    | 'ALTER' 'TABLESPACE' id 'RENAME' 'TO' id
    | 'ALTER' 'TYPE' qname alter_type_cmd ( ',' alter_type_cmd )*
    | 'ALTER' 'PUBLICATION' id 'SET' definition
    | 'ALTER' 'PUBLICATION' id ( 'ADD' | 'SET' | 'DROP' ) 'TABLE' descendants ( ',' descendants )*
    | 'ALTER' ( 'ROLE' | 'USER' ) 'ALL'? id ( 'IN' 'DATABASE' id )? setresetclause
    | 'ALTER' ( 'ROLE' | 'USER' ) id 'WITH'? alteroptroleelem*

    | 'ALTER' 'SUBSCRIPTION' id 'SET' definition
    | 'ALTER' 'SUBSCRIPTION' id 'CONNECTION' string
    | 'ALTER' 'SUBSCRIPTION' id 'REFRESH' 'PUBLICATION' withDef?
    | 'ALTER' 'SUBSCRIPTION' id 'SET' 'PUBLICATION' ids withDef?
    | 'ALTER' 'SUBSCRIPTION' id 'ENABLE'
    | 'ALTER' 'SUBSCRIPTION' id 'DISABLE'
    | 'ALTER' 'SUBSCRIPTION' id 'OWNER' 'TO' id
    | 'ALTER' 'SUBSCRIPTION' id 'RENAME' 'TO' id

    | 'ALTER' 'STATISTICS' ifExists? qname 'SET' 'STATISTICS' signedDecimal
    | ('ALTER' 'TEXT' 'SEARCH' 'CONFIGURATION' qname 'ADD' 'MAPPING' 'FOR' ids 'WITH' qnames
    | 'ALTER' 'TEXT' 'SEARCH' 'CONFIGURATION' qname 'ALTER' 'MAPPING' ( 'FOR' ids )? ( 'REPLACE' qname )? 'WITH' qnames
    | 'ALTER' 'TEXT' 'SEARCH' 'CONFIGURATION' qname 'DROP' 'MAPPING' ifExists? 'FOR' ids)
    | 'ALTER' 'TEXT' 'SEARCH' 'DICTIONARY' qname definition
    | 'ALTER' 'USER' 'MAPPING' 'FOR' id 'SERVER' id genericOptions
    | ANALYZE 'VERBOSE'? ( tableRef ( ',' tableRef )* )?
    | ANALYZE '(' optionElems ')' ( tableRef ( ',' tableRef )* )?
    | 'CALL' genericFunction
    | 'CHECKPOINT'
    | 'CLOSE' ( id | 'ALL' )
    | 'CLUSTER' 'VERBOSE'? ( qname usingID? | id 'ON' qname )?
    | 'COMMENT' 'ON'
      ( object_type_any_name qname
      | 'COLUMN' qname
      | object_type_name id
      | 'TYPE' type
      | 'DOMAIN' type
      | 'AGGREGATE' aggregateSignature
      | routine functionSignature
      | 'OPERATOR' operatorSignature
      | 'CONSTRAINT' id 'ON' 'DOMAIN'? qname?
      | object_type_name_on_any_name id 'ON' qname
      | 'TRANSFORM' 'FOR' type 'LANGUAGE' id
      | 'OPERATOR' ( 'CLASS' | 'FAMILY' ) qname usingID
      | 'LARGE' 'OBJECT' number
      | 'CAST' '(' type 'AS' type ')'
      )
      'IS' ( string | 'NULL' )
    | 'SET' 'CONSTRAINTS' ( 'ALL' | qnames ) ( 'DEFERRED' | 'IMMEDIATE' )
    | ('COPY' tableRef 'FROM' 'PROGRAM'? name copyWithOptions? where?
    | 'COPY' ( tableRef | '(' query ')' ) 'TO' 'PROGRAM'? name copyWithOptions?

  // PostgreSQL before 9.0
  | 'COPY' ( tableRef | '(' query ')' )
    ( 'FROM' | 'TO' ) 'PROGRAM'? name
    ( 'WITH'? 'BINARY'?
      ( 'DELIMITER' 'AS'? string )?
      ( 'NULL' 'AS'? string )?
      ( 'CSV' 'HEADER'?
        ( 'QUOTE' 'AS'? string )?
        ( 'ESCAPE' 'AS'? string )?
        ( 'FORCE' 'NOT'? 'NULL' names )? // FROM
        ( 'FORCE' 'QUOTE' ( '*' | names ) )? // TO
      )?
    )

  // PostgreSQL before 7.3
  | 'COPY' 'BINARY'? qname
    ( 'FROM' | 'TO' ) name ( 'USING'? 'DELIMITERS' string )? ( 'WITH' 'NULL' 'AS' string )?)
    | 'CREATE' 'ACCESS' 'METHOD' id 'TYPE' ('INDEX' | 'TABLE') 'HANDLER' qname
    | 'CREATE' scope? 'TABLE' ifNotExists? tableRef
      fixme 'AS' ( select | executePrepared )
      withData?
    | 'CREATE' 'ASSERTION' qname 'CHECK' '(' term ')' timing*
    | 'CREATE' 'CAST' '(' type 'AS' type ')'
      ( 'WITH' 'FUNCTION' functionSignature | 'WITHOUT' 'FUNCTION' | 'WITH' 'INOUT' )
      ( 'AS' ( 'IMPLICIT' |  'ASSIGNMENT' ))?
    | 'CREATE' 'DEFAULT'? 'CONVERSION' qname 'FOR' string 'TO' string 'FROM' qname
    | 'CREATE' 'DOMAIN' qname 'AS'? type colconstraint*
    | 'CREATE' 'EXTENSION' ifNotExists? id 'WITH'?
    ( 'SCHEMA' id
    | 'VERSION' name
    | 'FROM' name
    | 'CASCADE'
    )*
    | 'CREATE' 'FOREIGN' 'DATA' 'WRAPPER' id ( genericOptions | handler+ )?
    | 'CREATE' 'SERVER' ifNotExists? id ( 'TYPE' string )? version? 'FOREIGN' 'DATA' 'WRAPPER' id genericOptions?
    | 'CREATE' 'FOREIGN' 'TABLE' ifNotExists? qname
    ( '(' ( column ( ',' column )* )? ')' inherit?
    | 'PARTITION' 'OF' parentTable forValues
    )
    'SERVER' id genericOptions?
    | 'CREATE' orReplace_? ( 'FUNCTION' | 'PROCEDURE' ) qname
    '(' ( paramDef ( ',' paramDef )* )? ')'

    ( 'RETURNS' ( type | 'TABLE' '(' id type ( ',' id type )* ')' ) )?
    ( 'AS' string ( ',' string )?
    | 'LANGUAGE' name
    | 'TRANSFORM' forType ( ',' forType )*
    | 'WINDOW'
    | funcOptions
    )+
    | 'CREATE' 'GROUP' id 'WITH'? createoptroleelem*
    | 'CREATE' 'UNLOGGED'? 'MATERIALIZED' 'VIEW' ifNotExists? tableRef
    usingID? withDef? tablespaceID? 'AS' select withData?
    | 'CREATE' 'OPERATOR' 'CLASS' qname 'DEFAULT'? forType usingID ('FAMILY' qname)?
      'AS' opclass_item_list
    | 'CREATE' 'OPERATOR' 'FAMILY' qname usingID
    | 'CREATE' 'PUBLICATION' id ('FOR' 'TABLE' descendants ( ',' descendants )* | 'FOR' 'ALL' 'TABLES')? withDef?
    | 'ALTER' 'OPERATOR' 'FAMILY' qname usingID
      ('ADD' opclass_item_list | 'DROP' opclass_drop ( ',' opclass_drop )*)
    | 'CREATE' 'POLICY' id 'ON' qname ('AS' id)? rowsecuritydefaultforcmd? ('TO' ids)? rowsecurityoptionalexpr?
        rowsecurityoptionalwithcheck?
    | 'CREATE' orReplace_? 'TRUSTED'? 'PROCEDURAL'? 'LANGUAGE' id
      ( 'HANDLER' qname ('INLINE' qname)? ('VALIDATOR' qname
    | 'NO' 'VALIDATOR')? )?
    | 'CREATE' 'SCHEMA' ifNotExists? ( id? 'AUTHORIZATION' )? id
      ( createTable
      | createIndex
      | createSequence
      | createTrigger
      | 'GRANT' privileges 'ON' privilege_target 'TO' grantee ( ',' grantee )* withGrantOption?
      | createView
      )*
    | createSequence
    | createTable
    | 'CREATE' 'SUBSCRIPTION' id 'CONNECTION' string 'PUBLICATION' ids withDef?
    | 'CREATE' 'STATISTICS' ifNotExists? tableRef 'ON' terms ( 'FROM' tables )?
    | 'CREATE' 'TABLESPACE' id ( 'OWNER' id )? 'LOCATION' string withDef?
    | 'CREATE' orReplace_? 'TRANSFORM' 'FOR' type 'LANGUAGE' id '(' transform_element_list ')'
    | createTrigger
    | 'CREATE' 'EVENT' 'TRIGGER' id 'ON' id
      ( 'WHEN' event_trigger_when_item ( 'AND' event_trigger_when_item )* )?
      executeFunction
    | 'CREATE' 'ROLE' id 'WITH'? createoptroleelem*
    | 'CREATE' 'USER' id 'WITH'? createoptroleelem*
    | 'CREATE' 'USER' 'MAPPING' ifNotExists? 'FOR' id 'SERVER' id genericOptions?
    | 'CREATE' 'DATABASE' id 'WITH'? createdb_opt_item*
    | 'DEALLOCATE' 'PREPARE'? ( id |  'ALL' )
    | declareCursor
    | 'CREATE' orReplace_? 'AGGREGATE' aggregateSignature definition
    | 'CREATE' orReplace_? 'AGGREGATE' qname definition
    | 'CREATE' 'OPERATOR' operator definition
    | 'CREATE' 'TYPE' qname definition?
    | 'CREATE' 'TYPE' qname 'AS' '(' (tablefuncelement ( ',' tablefuncelement )*)? ')'
    | 'CREATE' 'TYPE' qname 'AS' 'ENUM' '(' ( string ( ',' string )* )? ')'
    | 'CREATE' 'TYPE' qname 'AS' 'RANGE' definition
    | 'CREATE' textSearchConfig qname definition
    | 'CREATE' 'COLLATION' ifNotExists? qname definition
    | 'CREATE' 'COLLATION' ifNotExists? qname 'FROM' qname
    | delete
    | 'DISCARD' ( 'ALL' | 'TEMP' | 'TEMPORARY' | 'PLANS' | 'SEQUENCES' )
    | 'DO' ( 'LANGUAGE'? name )+
    | 'DROP' 'CAST' ifExists? '(' type 'AS' type ')' cascade?
    | dropOperator
    | 'DROP' 'OWNED' 'BY' ids cascade?
    | 'DROP' object_type_any_name ifExists? qnames cascade?
    | 'DROP' object_type_name_on_any_name ifExists? id 'ON' qname cascade?
    | 'DROP' drop_type_name ifExists? ids cascade?
    | 'DROP' ( 'TYPE' | 'DOMAIN' ) ifExists? type ( ',' type )* cascade?
    | 'DROP' 'INDEX' 'CONCURRENTLY' ifExists? qnames cascade?
    | 'DROP' 'SUBSCRIPTION' ifExists? id cascade?
    | 'DROP' 'TABLESPACE' ifExists? id
    | 'DROP' 'TRANSFORM' ifExists? 'FOR' type 'LANGUAGE' id cascade?
    | 'DROP' ( 'ROLE' | 'USER' | 'GROUP' ) ifExists? ids
    | 'DROP' 'USER' 'MAPPING' ifExists? 'FOR' id 'SERVER' id
    | 'DROP' 'DATABASE' ifExists? id ( 'WITH'? '(' 'FORCE' ( ',' 'FORCE' )* ')' )?
    | executePrepared
    | ('EXPLAIN' ANALYZE? 'VERBOSE'? explainable
    | 'EXPLAIN' '(' optionElems ')' explainable)
    | ( 'FETCH' | 'MOVE' )

      ( 'NEXT'
      | 'PRIOR'
      | 'FIRST'
      | 'LAST'
      | ( 'ABSOLUTE' | 'RELATIVE' )? signedDecimal
      | 'ALL'
      | ( 'FORWARD' | 'BACKWARD' ) ( signedDecimal | 'ALL' )?
      )?

      ( 'FROM' | 'IN' )? id
    | 'GRANT' privileges 'ON' privilege_target 'TO' grantee ( ',' grantee )* withGrantOption?
    | 'GRANT' privilege ( ',' privilege )* 'TO' ids ( 'WITH' 'ADMIN' 'OPTION' )? grantedBy?
    | 'IMPORT' 'FOREIGN' 'SCHEMA' id  ( ( 'LIMIT' 'TO' | 'EXCEPT' )? '(' descendants ( ',' descendants )* ')' )?
      'FROM' 'SERVER' id 'INTO' id genericOptions?
    | createIndex
    | insert
    | 'MERGE' 'INTO'? qname tableAlias? 'USING' ( '(' select ')' | qname ) tableAlias? 'ON' term
      ( merge_insert_clause merge_update_clause?
      | merge_update_clause merge_insert_clause?
      )
      ( 'WHEN' 'MATCHED' 'THEN'? 'DELETE' )?
    | 'LISTEN' id
    | refreshMaterized
    | 'LOAD' string
    | 'LOCK' 'TABLE'? descendants ( ',' descendants )* ( 'IN' lockType 'MODE' )? 'NOWAIT'?
    | 'NOTIFY' id ( ',' string )?
    | 'PREPARE' id ( '(' type ( ',' type )* ')' )? 'AS' query
    | 'REASSIGN' 'OWNED' 'BY' ids 'TO' id
    | reindex
    | 'DROP' 'AGGREGATE' ifExists? aggregateSignature ( ',' aggregateSignature )* cascade?
    | 'DROP' routine ifExists? functionSignature ( ',' functionSignature )* cascade?
    | 'DROP' 'OPERATOR' ifExists? operatorSignature ( ',' operatorSignature )* cascade?
    | 'ALTER' 'AGGREGATE' aggregateSignature 'RENAME' 'TO' id
    | 'ALTER' 'COLLATION' qname 'RENAME' 'TO' id
    | 'ALTER' 'CONVERSION' qname 'RENAME' 'TO' id
    | 'ALTER' 'DATABASE' id 'RENAME' 'TO' id
    | 'ALTER' 'DOMAIN' qname 'RENAME' ( 'CONSTRAINT' id )? 'TO' id
    | 'ALTER' 'FOREIGN' 'DATA' 'WRAPPER' id 'RENAME' 'TO' id
    | 'ALTER' 'FUNCTION' functionSignature 'RENAME' 'TO' id
    | 'ALTER' 'GROUP' id 'RENAME' 'TO' id
    | 'ALTER' 'PROCEDURAL'? 'LANGUAGE' id 'RENAME' 'TO' id
    | 'ALTER' 'OPERATOR' 'CLASS' qname usingID 'RENAME' 'TO' id
    | 'ALTER' 'OPERATOR' 'FAMILY' qname usingID 'RENAME' 'TO' id
    | 'ALTER' 'POLICY' ifExists? id 'ON' qname 'RENAME' 'TO' id
    | 'ALTER' 'PROCEDURE' functionSignature 'RENAME' 'TO' id
    | 'ALTER' 'PUBLICATION' id 'RENAME' 'TO' id
    | 'ALTER' 'ROUTINE' functionSignature 'RENAME' 'TO' id
    | 'ALTER' 'SCHEMA' id 'RENAME' 'TO' id
    | 'ALTER' 'SERVER' id 'RENAME' 'TO' id
    | 'ALTER' 'TABLE' ifExists? descendants 'RENAME' 'TO' id
    | 'ALTER' 'SEQUENCE' ifExists? qname 'RENAME' 'TO' id
    | 'ALTER' 'VIEW' ifExists? qname 'RENAME' 'TO' id
    | 'ALTER' 'MATERIALIZED' 'VIEW' ifExists? qname 'RENAME' 'TO' id
    | 'ALTER' 'MATERIALIZED' 'VIEW' ifExists? qname 'RENAME' 'COLUMN'? id 'TO' id
    | 'ALTER' 'INDEX' ifExists? qname 'RENAME' 'TO' id
    | 'ALTER' 'FOREIGN' 'TABLE' ifExists? descendants 'RENAME' 'TO' id
    | 'ALTER' 'TABLE' ifExists? descendants 'RENAME' 'COLUMN'? id 'TO' id
    | 'ALTER' 'VIEW' ifExists? qname 'RENAME' 'COLUMN'? id 'TO' id
    | 'ALTER' 'TABLE' ifExists? descendants 'RENAME' 'CONSTRAINT' id 'TO' id
    | 'ALTER' 'FOREIGN' 'TABLE' ifExists? descendants 'RENAME' 'COLUMN'? id 'TO' id
    | 'ALTER' 'RULE' id 'ON' qname 'RENAME' 'TO' id
    | 'ALTER' 'TRIGGER' id 'ON' qname 'RENAME' 'TO' id
    | 'ALTER' 'EVENT' 'TRIGGER' id 'RENAME' 'TO' id
    | 'ALTER' 'ROLE' id 'RENAME' 'TO' id
    | 'ALTER' 'USER' id 'RENAME' 'TO' id
    | 'ALTER' 'STATISTICS' qname 'RENAME' 'TO' id
    | 'ALTER' textSearchConfig qname 'RENAME' 'TO' id
    | 'ALTER' 'TYPE' qname 'RENAME' 'TO' id
    | 'ALTER' 'TYPE' qname 'RENAME' 'ATTRIBUTE' id 'TO' id cascade?
    | 'REVOKE' ( 'GRANT' 'OPTION' 'FOR' )? privileges 'ON' privilege_target 'FROM' grantee ( ',' grantee )* cascade?
    | 'REVOKE' ('ADMIN' 'OPTION' 'FOR')? privilege ( ',' privilege )* 'FROM' ids grantedBy? cascade?
    | 'CREATE' orReplace_? 'RULE' id 'AS' 'ON' ( 'SELECT' | 'UPDATE' | 'DELETE' | 'INSERT' ) 'TO' qname where? 'DO' ( 'INSTEAD' | 'ALSO' )? ( 'NOTHING' | actionable | '(' actionable? ( SEMI actionable? )* ')' )
    | 'SECURITY' 'LABEL' ('FOR' name)? 'ON'
      ( object_type_any_name qname
      | 'COLUMN' qname
      | object_type_name id
      | 'TYPE' type
      | 'DOMAIN' type
      | 'AGGREGATE' aggregateSignature
      | 'FUNCTION' functionSignature
      | 'LARGE' 'OBJECT' number
      | 'PROCEDURE' functionSignature
      | 'ROUTINE' functionSignature
      )
      'IS' (string | 'NULL')
    | select
    | (( 'ABORT' | 'COMMIT' | 'END' | 'ROLLBACK' ) transaction_? ( 'AND' 'NO'? 'CHAIN' )?
    | ( 'BEGIN' transaction_? | 'START' 'TRANSACTION' ) ( transactionMode ( ',' transactionMode )* )?
    | 'RELEASE'? 'SAVEPOINT' id
    | 'RELEASE' id
    | 'ROLLBACK' transaction_? 'TO' 'SAVEPOINT'? id
    | 'PREPARE' 'TRANSACTION' string
    | 'COMMIT' 'PREPARED' string
    | 'ROLLBACK' 'PREPARED' string)
    | 'TRUNCATE' 'TABLE'? descendants ( ',' descendants )* (( 'CONTINUE' | 'RESTART' ) 'IDENTITY' )? cascade?
    | 'UNLISTEN' ( id | '*' )
    | update
    | ('VACUUM' 'FULL'? 'FREEZE'? 'VERBOSE'? ANALYZE? (tableRef ( ',' tableRef )* )?
    | 'VACUUM' '(' optionElems ')' ( tableRef ( ',' tableRef )*)?)
    | resetVariable
    | 'SET' ( 'LOCAL' | 'SESSION' )? set_rest
    | createView
    ;

with
  : 'WITH' 'RECURSIVE'? cte ( ',' cte )* ;

cte
  : id columns? 'AS' ( 'NOT'? 'MATERIALIZED' )? '(' query ')' ;

select
  : with?
    selectCore ( ( 'UNION' | 'EXCEPT' | 'INTERSECT' ) unique2? selectCore )*
    orderBy?
    ( limit | fetch | offset | locking )* ;

  selectCore
    : 'SELECT' unique? ( item ( ',' item )* ','? )?
      ( 'INTO' ( 'TEMPORARY' | 'TEMP' | 'UNLOGGED' )? 'TABLE'?  qname )?
      ( 'FROM' tables )? where? groupBy? having? window?
    | values
    | '(' select ')'
    | 'TABLE' descendants
    ;

    item
        : '*'  # ItemAll
        | qname alias?  # ItemColumn
        | term alias?  # ItemTerm
        ;

    tables
      : tables ',' tables
      | tables joinType? 'JOIN' tables ( 'ON' term | 'USING' columns )
      | tables 'NATURAL' joinType? 'JOIN' tables
      | tables 'CROSS' 'JOIN' tables
      | 'LATERAL'? tableFunc
      | 'LATERAL'? '(' select ')' tableAlias?
      | 'LATERAL'? function ( 'WITH' 'ORDINALITY' )? tableAlias?
      | 'LATERAL'? 'ROWS' 'FROM' '(' tableFunc? ( ',' tableFunc? )* ')'  ( 'WITH' 'ORDINALITY' )? tableAlias?
      | 'LATERAL'? xmltable tableAlias?
      | '(' tables ')' tableAlias?
      | descendants tableAlias? ( 'TABLESAMPLE' qname '(' terms ')' ( 'REPEATABLE' '(' term ')' )? )?
      ;

        joinType
          : 'INNER'
          | ( 'FULL' | 'LEFT' | 'RIGHT' ) 'OUTER'?
          ;

        // TODO review func_expr_windowless
        tableFunc
          : function ( ( 'AS' | 'AS'? id ) '(' id type ( ',' id type )* ')' )? ;

        xmltable
          : 'XMLTABLE' '('
            ( 'XMLNAMESPACES' '(' xmlNamespace ( ',' xmlNamespace )* ')' ',' )?
             xmlPassings
            'COLUMNS' xmlColumn ( ',' xmlColumn )* ')'
          ;

          xmlNamespace
            : term 'AS' id
            | 'DEFAULT' term
            ;

          xmlColumn
            : id
              ( type ( 'DEFAULT' term | id term | 'NOT'? 'NULL' )*
              | 'FOR' 'ORDINALITY'
              )
            ;

        descendants
          : qname '*'?
          | 'ONLY' qname
          ;

    tableAlias
      : 'AS'? id ( '(' qname ( ',' qname )* ')' )? ;

    where
      : 'WHERE' term ;

    groupBy
      : 'GROUP' 'BY' groupByItem ( ',' groupByItem )* ;

    groupByItem
      : '(' ')'
      | 'CUBE' '(' terms ')'
      | 'ROLLUP' '(' terms ')'
      | 'GROUPING' 'SETS' '(' groupByItem ( ',' groupByItem )* ')'
      | term
      ;

    having
      : 'HAVING' term ;

    window
      : 'WINDOW' windowDef ( ',' windowDef )* ;

    windowDef
      : id 'AS' windowSpec ;

    windowSpec
      : '(' id? ( 'PARTITION' 'BY' terms )? orderBy? frame_clause_? ')' ;

    frame_clause_
      : ( 'RANGE' | 'ROWS' | 'GROUPS' )
        ( frame_bound | 'BETWEEN' frame_bound 'AND' frame_bound )
        ( 'EXCLUDE' ( 'CURRENT' 'ROW' | 'GROUP' | 'TIES' | 'NO' 'OTHERS' ))?
      ;

    frame_bound
      : 'UNBOUNDED' ( 'PRECEDING' | 'FOLLOWING' )
      | 'CURRENT' 'ROW'
      | term ( 'PRECEDING' | 'FOLLOWING' )
      ;


    values
      : 'VALUES' terms ;

    insert
        : with? 'INSERT'
          'INTO' qname alias?

          ( columns? ( 'OVERRIDING' ('USER' | 'SYSTEM') 'VALUE' )? select
    //      ( ( '(' qnames ')' )? ( 'OVERRIDING' ('USER' | 'SYSTEM') 'VALUE' )? select
          | 'DEFAULT' 'VALUES'
          )

          ( 'ON' 'CONFLICT'
            ( '(' index_params ')' where? | 'ON' 'CONSTRAINT' id )?
            'DO' ( 'UPDATE' 'SET' setter ( ',' setter )* where? | 'NOTHING' )
          )?
          returning?
        ;






createoptroleelem
    : alteroptroleelem
    | 'SYSID' integer
    | 'ADMIN' ids
    | 'ROLE' ids
    | 'IN' ( 'ROLE' | 'GROUP' ) ids
    ;

alteroptroleelem
    : ( 'ENCRYPTED' | 'UNENCRYPTED' )? 'PASSWORD' string
    | 'INHERIT'
    | 'CONNECTION' 'LIMIT' signedDecimal
    | 'VALID' 'UNTIL' string
    | 'USER' ids
    | id
    ;

addDrop
  : 'ADD' | 'DROP' ;

set_rest
    : ( 'SESSION' 'CHARACTERISTICS' 'AS' )? 'TRANSACTION' transactionMode ( ','? transactionMode )*
    | set_rest_more
    ;


set_rest_more
  : qname ( 'TO' | '=' ) ( 'DEFAULT' | value ( ',' value )* )
  | id 'FROM' 'CURRENT'
  | 'TIME' 'ZONE' ( string | id | interval | number | 'DEFAULT' | 'LOCAL' )
  | 'CATALOG' string
  | 'SCHEMA' string
  | 'NAMES' (string | 'DEFAULT')?
  | 'ROLE' name
  | 'SESSION' 'AUTHORIZATION' name
  | 'XML' 'OPTION' root
  | 'TRANSACTION' 'SNAPSHOT' string
  ;

transactionMode
  : 'ISOLATION' 'LEVEL' ( 'READ' ( 'UNCOMMITTED' | 'COMMITTED' ) | 'REPEATABLE' 'READ' | 'SERIALIZABLE' )
  | 'READ' ( 'ONLY' |  'WRITE' )
  | 'NOT'? 'DEFERRABLE'
  ;

interval
  : 'INTERVAL' ( string? timeUnit | '(' integer ')' string? )? ;

value
  : id | number | string ;

names
  : name ( ',' name )* ;

name
  : id | string ;

resetVariable
  : ( 'RESET' | 'SHOW' )
    ( qname
    | 'ALL'
    | 'TIME' 'ZONE'
    | 'TRANSACTION' 'ISOLATION' 'LEVEL'
    | 'SESSION' 'AUTHORIZATION'
    )
  ;

setresetclause
    : 'SET' set_rest
    | resetVariable
    ;

alter_table_cmds
    : alter_table_cmd ( ',' alter_table_cmd )*
    ;

alter_table_cmd
    : 'ADD' tableconstraint
    | 'ADD' 'COLUMN'? ifNotExists? columnDef
    | 'ALTER' 'COLUMN'? id ('SET' 'DEFAULT' term | 'DROP' 'DEFAULT' )
    | 'ALTER' 'COLUMN'? id 'DROP' 'NOT' 'NULL'
    | 'ALTER' 'COLUMN'? id 'SET' 'NOT' 'NULL'
    | 'ALTER' 'COLUMN'? id 'DROP' 'EXPRESSION' ifExists?
    | 'ALTER' 'COLUMN'? id 'SET' 'STATISTICS' signedDecimal
    | 'ALTER' 'COLUMN'? integer 'SET' 'STATISTICS' signedDecimal
    | 'ALTER' 'COLUMN'? id 'SET' definition
    | 'ALTER' 'COLUMN'? id 'RESET' definition
    | 'ALTER' 'COLUMN'? id 'SET' 'STORAGE' id
    | 'ALTER' 'COLUMN'? id 'ADD' generatedWhen 'AS' 'IDENTITY' ( '(' seqoptelem+ ')' )?
    | 'ALTER' 'COLUMN'? id alter_identity_column_option+
    | 'ALTER' 'COLUMN'? id 'DROP' 'IDENTITY' ifExists?
    | 'ALTER' 'COLUMN'? id ( 'SET' 'DATA' )? 'TYPE' type collate? ( 'USING' term )?
    | 'ALTER' 'COLUMN'? id genericOptions
    | 'ALTER' 'CONSTRAINT' id timing*
    | 'VALIDATE' 'CONSTRAINT' id
    | 'DROP' 'COLUMN'? ifExists? id cascade?
    | 'DROP' 'CONSTRAINT' ifExists? id cascade?
    | 'CLUSTER' 'ON' id
    | ( 'DISABLE' | 'ENABLE' ) ( 'ALWAYS' | 'REPLICA' )? 'TRIGGER' id
    | ( 'DISABLE' | 'ENABLE' ) 'TRIGGER' ( 'ALL' | 'USER' )
    | 'ENABLE' ( 'ALWAYS' | 'REPLICA' )? 'RULE' id
    | 'DISABLE' 'RULE' id
    | 'INHERIT' qname
    | noInherit qname
    | 'OF' qname
    | 'NOT' 'OF'
    | 'OWNER' 'TO' id
    | 'SET' 'WITHOUT' 'OIDS'
    | 'SET' 'WITHOUT' 'CLUSTER'
    | 'SET' 'LOGGED'
    | 'SET' 'UNLOGGED'
    | 'SET' 'TABLESPACE' id
    | 'SET' definition
    | 'RESET' definition
    | 'REPLICA' 'IDENTITY' ( 'NOTHING' | 'FULL' | 'DEFAULT' | 'USING' 'INDEX' id )
    | ( 'DISABLE' | 'ENABLE' | 'NO'? 'FORCE' ) 'ROW' 'LEVEL' 'SECURITY'
    | genericOptions
    ;

noInherit
  : 'NO' 'INHERIT' ;

cascade
  : 'CASCADE' | 'RESTRICT' ;

collate
  : 'COLLATE' qname ;

alter_identity_column_option
  : 'RESTART' ( 'WITH'? number )?
  | 'SET' ( seqoptelem | generatedWhen )
  ;

alter_type_cmd
  : 'ADD' 'ATTRIBUTE' tablefuncelement cascade?
  | 'DROP' 'ATTRIBUTE' ifExists? id cascade?
  | 'ALTER' 'ATTRIBUTE' id ( 'SET' 'DATA' )? 'TYPE' type collate? cascade?
  ;

query
  : select | insert | update | delete ;

copyWithOptions
  : 'WITH'? '(' copyOptions ( ',' copyOptions )* ')' ;

copyOptions
  : 'FORMAT' name
  | 'FREEZE' boolean
  | 'DELIMITER' string
  | 'NULL' string
  | 'DEFAULT' string
  | 'HEADER' ( boolean | 'MATCH' )
  | 'QUOTE' string
  | 'ESCAPE' string
  | 'FORCE_QUOTE' ( '*' | columns )
  | 'FORCE_NOT_NULL' ( '*' | columns )
  | 'FORCE_NULL' ( '*' | columns )
  | 'ON_ERROR' id
  | 'ENCODING' string
  | 'LOG_VERBOSITY' id
  ;

createTable
  : 'CREATE' scope? 'TABLE' ifNotExists? qname
    ( '(' ( column ( ',' column )* )? ')' inherit?
    | 'PARTITION' 'OF' parentTable forValues
    | 'OF' parentTable
    )
    ( 'PARTITION' 'BY' id '(' part_elem ( ',' part_elem )* ')' )?
    fixme
  ;

// TODO better name
   fixme
     : usingID?
       ( withDef | 'WITHOUT' 'OIDS' )?
       ( 'ON' 'COMMIT' ( 'DROP' | 'DELETE' 'ROWS' | 'PRESERVE' 'ROWS' ))?
       tablespaceID?
     ;

forValues
  : 'FOR' 'VALUES' 'WITH' '(' 'modulus' number ','  'remainder' number ')'
  | 'FOR' 'VALUES' 'IN' '(' terms ')'
  | 'FOR' 'VALUES' 'FROM' '(' terms ')' 'TO' '(' terms ')'
  | 'DEFAULT'
  ;

executePrepared
  : 'EXECUTE' name ( '(' terms ')' )? ;

scope
    : ( 'LOCAL' | 'GLOBAL' )? ( 'TEMPORARY' | 'TEMP' )
    | 'UNLOGGED'
    ;

usingID
  : 'USING' id ;

parentTable
  : qname ( '(' column ( ',' column )* ')' )? ;

column
  : tableconstraint
  | 'LIKE' qname
    ( ( 'INCLUDING' | 'EXCLUDING' )
      ( 'COMMENTS'
      | 'CONSTRAINTS'
      | 'DEFAULTS'
      | 'IDENTITY'
      | 'GENERATED'
      | 'INDEXES'
      | 'STATISTICS'
      | 'STORAGE'
      | 'ALL'
      )
    )*
  | columnDef
  ;

columnDef
  : id type? genericOptions? colconstraint* ;

colconstraint
    : ( 'CONSTRAINT' id )? colconstraintelem
    | ('NOT'? 'DEFERRABLE' | 'INITIALLY' ( 'DEFERRED' | 'IMMEDIATE' ))
    | collate
    ;

colconstraintelem
    : 'NOT'? 'NULL'
    | 'UNIQUE' withDef? usingIndexTablespaceID?
    | 'PRIMARY' 'KEY' withDef? usingIndexTablespaceID?
    | 'CHECK' '(' term ')' noInherit?
    | 'DEFAULT' term
    | generatedWhen 'AS' ( 'IDENTITY' ('(' seqoptelem+ ')')? | '(' term ')' 'STORED' )
    | 'REFERENCES' tableRef refMatchType? refAction*
    ;

generatedWhen
  : 'GENERATED' ( 'ALWAYS' | 'BY' 'DEFAULT' ) ;

tableconstraint
  : ( 'CONSTRAINT' id )?
    ( 'CHECK' '(' term ')' noInherit?
    | ( 'UNIQUE' ( 'NULLS' 'NOT'? 'DISTINCT' )? | 'PRIMARY' 'KEY' ) columns? indexParams
    | 'EXCLUDE' usingID? '(' excludeElement ( ',' excludeElement )* ')' indexParams ( 'WHERE' '(' term ')' )?
    | 'FOREIGN' 'KEY' columns 'REFERENCES' tableRef refMatchType? refAction*
    )
    ( 'NOT'? 'DEFERRABLE' )?
    ( 'INITIALLY' ( 'IMMEDIATE' | 'DEFERRED' ))?
    ( 'NOT' 'VALID' )?
  ;

indexParams
  : ( 'INCLUDE' columns )?
    withDef?
    // TODO is optional 'TABLESPACE' is correct?
    ( 'USING' 'INDEX' 'TABLESPACE'? id )?
  ;

refMatchType
  : 'MATCH' ( 'FULL' | 'PARTIAL' | 'SIMPLE' ) ;

refAction
  : 'ON' ( 'DELETE' | 'UPDATE' )
    ( 'NO' 'ACTION'
    | 'RESTRICT'
    | 'CASCADE'
    | 'SET' ( 'NULL' | 'DEFAULT' ) columns?
    )
  ;

excludeElement
  : ( id | '(' term ')' ) collate? 'WITH' operator ;

inherit
  : 'INHERITS' '(' qnames ')' ;

part_elem
    : id collate? qname?
    | function collate? qname?
    | '(' term ')' collate? qname?
    ;

tablespaceID
  : 'TABLESPACE' id ;

usingIndexTablespaceID
  : 'USING' 'INDEX' 'TABLESPACE' id ;

withData
  : 'WITH' 'NO'? 'DATA' ;

refreshMaterized
  : 'REFRESH' 'MATERIALIZED' 'VIEW' 'CONCURRENTLY'? qname withData? ;

createSequence
  : 'CREATE' scope? 'SEQUENCE' ifNotExists? qname seqoptelem* ;

seqoptelem
    : 'AS' scalarType
    | 'CACHE' number
    | 'CYCLE'
    | 'INCREMENT' 'BY'? number
    | 'MAXVALUE' number
    | 'MINVALUE' number
    | 'NO' ( 'MAXVALUE' | 'MINVALUE' | 'CYCLE' )
    | 'OWNED' 'BY' qname
    | 'SEQUENCE' 'NAME' qname
    | 'START' 'WITH'? number
    | 'RESTART' 'WITH'? number?
    ;

number
  : signedFloat | signedDecimal ;

ifExists
  : 'IF' 'EXISTS' ;

ifNotExists
  : 'IF' 'NOT' 'EXISTS' ;

handler
  : 'HANDLER' qname
  | 'NO' 'HANDLER'
  | 'VALIDATOR' qname
  | 'NO' 'VALIDATOR'
  ;

genericOptions
  : 'OPTIONS' '(' optionAction ( ',' optionAction )* ')'
  | 'WITH' 'OPTIONS'?
  ;

optionAction
  : ( 'SET' | 'ADD' | 'DROP' )? id string? ;

version
  : 'VERSION' ( string | 'NULL' ) ;

rowsecurityoptionalexpr
    : 'USING' '(' term ')'
    ;

rowsecurityoptionalwithcheck
    : 'WITH' 'CHECK' '(' term ')'
    ;

rowsecuritydefaultforcmd
    : 'FOR' ( 'ALL' | 'SELECT' | 'INSERT' | 'UPDATE' | 'DELETE' )
    ;

createTrigger
  : 'CREATE' orReplace_? 'CONSTRAINT'? 'TRIGGER' id
    ( 'BEFORE' | 'AFTER' | 'INSTEAD' 'OF' ) ( event ( 'OR' event )* )? 'ON' qname
    ( 'FROM' qname )?
    timing*
    ( 'REFERENCING' triggertransition+ )?
    ( 'FOR' 'EACH'? ( 'ROW' | 'STATEMENT' ) )?
    ( 'WHEN' '(' term ')' )?
    executeFunction
  ;

executeFunction
  : 'EXECUTE' ( 'FUNCTION' | 'PROCEDURE' )? qname '(' terms? ')' ;

event
  : 'DELETE'
  | 'INSERT'
  | 'UPDATE' ( 'OF' ids )?
  | 'TRUNCATE'
  ;

triggertransition
    : ( 'NEW' | 'OLD' ) ( 'TABLE' | 'ROW' ) alias
    ;

timing
    : 'NOT'? 'DEFERRABLE'
    | 'INITIALLY' ( 'IMMEDIATE' | 'DEFERRED' )?
    | 'NOT' 'VALID'
    | noInherit
    ;

event_trigger_when_item
    : id 'IN' '(' string ( ',' string )* ')'
    ;

definition
    : '(' def_elem ( ',' def_elem )* ')'
    ;

def_elem
    : qname ( '=' def_arg )?
    ;

def_arg
    : type
    | keyword
    | operator
    | number
    | string
    | 'NONE'
    ;


opclass_purpose
    : 'FOR' ( 'SEARCH' |  'ORDER' 'BY' qname )
    ;

opclass_item_list
    : opclass_item ( ',' opclass_item )*
    ;

opclass_item
    : 'OPERATOR' integer operator opclass_purpose? 'RECHECK'?
    | 'OPERATOR' integer operatorSignature opclass_purpose? 'RECHECK'?
    | 'FUNCTION' integer functionSignature
    | 'FUNCTION' integer '(' type ( ',' type )* ')' functionSignature
    | 'STORAGE' type
    ;


opclass_drop
    : ( 'OPERATOR' | 'FUNCTION' ) integer '(' type ( ',' type )* ')'
    ;

dropOperator
    : 'DROP' 'OPERATOR' ( 'CLASS' | 'FAMILY' ) ifExists? qname usingID cascade?
    ;

object_type_any_name
    : 'TABLE'
    | 'SEQUENCE'
    | 'VIEW'
    | 'MATERIALIZED' 'VIEW'
    | 'INDEX'
    | 'FOREIGN' 'TABLE'
    | 'COLLATION'
    | 'CONVERSION'
    | 'STATISTICS'
    | textSearchConfig
    ;

object_type_name
    : drop_type_name
    | 'DATABASE'
    | 'ROLE'
    | 'SUBSCRIPTION'
    | 'TABLESPACE'
    ;

drop_type_name
    : 'ACCESS' 'METHOD'
    | 'EVENT' 'TRIGGER'
    | 'EXTENSION'
    | 'FOREIGN' 'DATA' 'WRAPPER'
    | 'PROCEDURAL'? 'LANGUAGE'
    | 'PUBLICATION'
    | 'SCHEMA'
    | 'SERVER'
    ;

object_type_name_on_any_name
    : 'POLICY'
    | 'RULE'
    | 'TRIGGER'
    ;

privileges
  : privilege ( ',' privilege )*
  | 'ALL' 'PRIVILEGES'? ( columns )?
  ;

privilege
  : ( 'SELECT' | 'REFERENCES' | 'CREATE' | id ) columns? ;

privilege_target
    : qnames
    | 'TABLE' qnames
    | 'SEQUENCE' qnames
    | 'FOREIGN' 'DATA' 'WRAPPER' ids
    | 'FOREIGN' 'SERVER' ids
    | 'FUNCTION' functionSignature ( ',' functionSignature )*
    | 'PROCEDURE' functionSignature ( ',' functionSignature )*
    | 'ROUTINE' functionSignature ( ',' functionSignature )*
    | 'DATABASE' ids
    | 'DOMAIN' qnames
    | 'LANGUAGE' ids
    | 'LARGE' 'OBJECT' number ( ',' number )*
    | 'SCHEMA' ids
    | 'TABLESPACE' ids
    | 'TYPE' qnames
    | 'ALL' ( 'TABLES' | 'SEQUENCES' | 'FUNCTIONS' | 'PROCEDURES' | 'ROUTINES' ) 'IN' 'SCHEMA' ids
    ;

grantee
  : 'GROUP'? id ;

withGrantOption
  : 'WITH' 'GRANT' 'OPTION' ;

grantedBy
  : 'GRANTED' 'BY' id ;

defacl_privilege_target
    : 'TABLES'
    | 'FUNCTIONS'
    | 'ROUTINES'
    | 'SEQUENCES'
    | 'TYPES'
    | 'SCHEMAS'
    ;

createIndex
    : 'CREATE' 'UNIQUE'? 'INDEX' 'CONCURRENTLY'? ( ifNotExists? id )?
      'ON' descendants usingID? '(' index_params ')'
      ( 'INCLUDE' '(' index_params ')' )? withDef? tablespaceID? where?
    ;

index_params
  : index_elem ( ',' index_elem )*
  ;

    index_elem
      : ( id | function | '(' term ')' )
        collate? ( qname definition? )? sortDir? nullsOrder?
      ;

sortDir
    : 'ASC' | 'DESC' ;

nullsOrder
    : 'NULLS' ('FIRST' |  'LAST') ;

orReplace_
  : 'OR' 'REPLACE' ;

paramDef
  : id? argMode? id? type (( 'DEFAULT' | '=' ) term )? ;

// TODO create a smaller arg just for functions?
functionSignature
    : qname ( '(' aggregateArgs? ')' )? ;

aggregateSignature
  : qname '(' ( '*' | ( aggregateArgs? 'ORDER' 'BY' )? aggregateArgs ) ')' ;

aggregateArgs
  : aggregateArg ( ',' aggregateArg )* ;

aggregateArg
  : ( argMode id? | id argMode? )? type ;

argMode
  : 'IN' 'OUT'?
  | 'OUT'
  | 'INOUT'
  | 'VARIADIC'
  ;

funcOptions
  : 'CALLED' 'ON' 'NULL' 'INPUT'
  | 'RETURNS' 'NULL' 'ON' 'NULL' 'INPUT'
  | 'STRICT'
  | 'IMMUTABLE'
  | 'STABLE'
  | 'VOLATILE'
  | 'EXTERNAL'? 'SECURITY' ( 'DEFINER' | 'INVOKER' )
  | 'LEAKPROOF'
  | 'NOT' 'LEAKPROOF'
  | 'COST' number
  | 'ROWS' number
  | 'SUPPORT' qname
  | 'SET' set_rest_more
  | resetVariable
  | 'PARALLEL' id
  ;

forType
  : 'FOR' 'TYPE' type ;

withDef
  : 'WITH' definition ;

routine
  : 'FUNCTION' | 'PROCEDURE' | 'ROUTINE' ;

// qualitified operator
operator
    : ( id '.' )*
      ( OPERATOR
      // Include all implicitly defined operator tokens (in this grammar) here
      | '+' | '-' | '*' | '/' | '&' | '%' | '^' | '<' | '>' | '=' | '<=' | '>=' | '<>'
      )
    | 'OPERATOR' '(' operator ')'
    ;

operatorSignature
    : operator '(' type ( ',' type )? ')' ;

transform_element_list
    : 'FROM' 'SQL' 'WITH' 'FUNCTION' functionSignature ( ',' 'TO' 'SQL' 'WITH' 'FUNCTION' functionSignature )?
    | 'TO' 'SQL' 'WITH' 'FUNCTION' functionSignature ( ',' 'FROM' 'SQL' 'WITH' 'FUNCTION' functionSignature )?
    ;

reindex
  : 'REINDEX' ( '(' reindexOption (  ',' reindexOption )* ')' )?
    ( 'INDEX' | 'TABLE' | 'SCHEMA' | 'SYSTEM' | 'DATABASE' )
    'CONCURRENTLY'? qname?
  ;

  reindexOption
    : 'CONCURRENTLY' boolean?
    | 'TABLESPACE' qname?
    | 'VERBOSE' boolean?
    ;

textSearchConfig : 'TEXT' 'SEARCH' ( 'PARSER' | 'DICTIONARY' | 'TEMPLATE' | 'CONFIGURATION' ) ;

operator_def_elem
    : id '='
      ( type | keyword | operator | number | string )
    ;

actionable
    : select
    | insert
    | update
    | delete
    | 'NOTIFY' id ( ',' string )?
    ;

transaction_
    : 'WORK'
    | 'TRANSACTION'
    ;

createView
    : 'CREATE' orReplace_? scope?
    ( 'VIEW' tableRef withDef? | 'RECURSIVE' 'VIEW' qname columns withDef?
    ) 'AS' select ( 'WITH' ( 'CASCADED' | 'LOCAL' )? 'CHECK' 'OPTION' )?
    ;

createdb_opt_item
    : (id
    | 'CONNECTION' 'LIMIT'
    | 'ENCODING'
    | 'LOCATION'
    | 'OWNER'
    | 'TABLESPACE'
    | 'TEMPLATE') '='? ( signedDecimal | name | 'DEFAULT' )
    ;

tableRef
  : qname columns? ;

explainable
    : select
    | insert
    | update
    | delete
    | declareCursor
    | 'CREATE' scope? 'TABLE' ifNotExists? tableRef fixme 'AS' ( select | executePrepared ) withData?
    | 'CREATE' 'UNLOGGED'? 'MATERIALIZED' 'VIEW' ifNotExists? tableRef usingID? withDef? tablespaceID? 'AS' select withData?
    | refreshMaterized
    | executePrepared
    ;

optionElems
    : option_elem ( ',' option_elem )*
    ;

option_elem
    : id (name | number)?
    ;

returning
  : 'RETURNING' ( item ( ',' item )* )? ;

// https://www.postgresql.org/docs/current/sql-merge.html
merge_insert_clause
  : 'WHEN' 'NOT' 'MATCHED' ( 'AND' term )? 'THEN'? 'INSERT' ( '(' qnames ')' )? values
  ;

merge_update_clause
  : 'WHEN' 'MATCHED' ( 'AND' term )? 'THEN'? 'UPDATE' 'SET' setter ( ',' setter )*
  ;

delete
  : with? 'DELETE' 'FROM' descendants alias? ( 'USING' tables ( ',' tables )* )? whereCurrent? returning? ;

lockType
  : ( 'ACCESS' | 'ROW' ) ( 'SHARE' | 'EXCLUSIVE' )
  | 'SHARE' ( ( 'UPDATE' | 'ROW' ) 'EXCLUSIVE' )?
  | 'EXCLUSIVE'
  ;

update
  : with? 'UPDATE' descendants alias?
    'SET' setter ( ',' setter )*
    ( 'FROM' tables )? whereCurrent? returning?
  ;

setter
  : qname '=' term
  | '(' qnames ')' '=' term
  ;

declareCursor
    : 'DECLARE' id ( 'NO'? 'SCROLL' | 'BINARY' | 'INSENSITIVE' )* 'CURSOR' ( withers 'HOLD' )? 'FOR' select
    ;

    limit
      : 'LIMIT' term ;

    fetch
      : 'FETCH' firstNext term? rowRows ( 'ONLY' | 'WITH' 'TIES' ) ;

    offset
      : 'OFFSET' term rowRows? ;

    locking
      : 'FOR' ( ( 'NO' 'KEY' )? 'UPDATE' | 'KEY'? 'SHARE' ) ( 'OF' qnames )? ( 'NOWAIT' | 'SKIP' 'LOCKED' )? ;

unique2
  : 'ALL' | 'DISTINCT' ;

unique
  : 'ALL' | 'DISTINCT' ( 'ON' '(' terms ')' )? ;

orderBy
  : 'ORDER' 'BY' sortby ( ',' sortby  )* ;

sortby
  : term ( 'USING' operator | sortDir? ) nullsOrder? ;

rowRows
  : 'ROW' | 'ROWS' ;

firstNext
  : 'FIRST' | 'NEXT' ;

alias
  : 'AS'? name ;

whereCurrent
  : 'WHERE' ( 'CURRENT' 'OF' id | term ) ;

tablefuncelement
    : id type collate?
    ;

type
  : 'SETOF'? scalarType
    (  ( '[' DECIMAL? ']' )*
    | 'ARRAY' ( '[' DECIMAL ']' )?
    )
  ;

scalarType
  : id ( '.' id )* precision?
  | id ( '.' id )* ( '%TYPE' | '%ROWTYPE' )?
  | numeric
  | 'BIT' 'VARYING'? scale?
  | character
  | ( 'TIMESTAMP' | 'TIME' ) ( '(' integer ')' )? ( withers 'TIME' 'ZONE' )?
  | interval
  | 'JSON'
  ;

withers
  : 'WITH' | 'WITHOUT' ;

precision
  : '(' ( DECIMAL | scalarType ) ( ',' ( signedDecimal | scalarType ) )? ')'
  ;

numeric
  : 'INT'
  | 'INTEGER'
  | 'SMALLINT'
  | 'BIGINT'
  | 'REAL'
  | 'FLOAT' scale?
  | 'DOUBLE' 'PRECISION'
  | 'DECIMAL' precision?
  | 'DEC' precision?
  | 'NUMERIC' precision?
  | 'BOOLEAN'
  ;

scale
  : '(' integer ')' ;

character
  : ( ( 'CHARACTER' | 'CHAR' | 'NCHAR' ) 'VARYING'?
    | 'VARCHAR'
    | 'NATIONAL' ( 'CHARACTER' | 'CHAR' ) 'VARYING'?
    )

    scale?
  ;

timeUnit
    : 'YEAR'
    | 'MONTH'
    | 'DAY'
    | 'HOUR'
    | 'MINUTE'
    | second
    | 'YEAR' 'TO' 'MONTH'
    | 'DAY' 'TO' ( 'HOUR' | 'MINUTE' | second )
    | 'HOUR' 'TO' ( 'MINUTE' | second )
    | 'MINUTE' 'TO' second
    ;

second
  : 'SECOND' scale? ;

terms
  : term ( ',' term )* ;

// https://www.postgresql.org/docs/12/sql-syntax-lexical.html#SQL-PRECEDENCE
term
  : term ( '::' type )+ #TermIgnore
  | literal #TermLiteral
  | qname  #TermColumn
  // unary left
  // TODO instances of OPERATOR need to be qualitified?
  | ( '-' | '+' | OPERATOR ) term #TermIgnore
  | term collate #TermIgnore
  | term 'AT' ( 'TIME' 'ZONE' | 'LOCAL' ) term #TermIgnore
  | <assoc=right> term '^' term #TermIgnore
  | term ( '*' | '/' | '%' ) term #TermIgnore
  | term ( '+' | '-' ) term #TermIgnore
  // TODO instances of OPERATOR need to be qualitified?
  | term OPERATOR term?  #TermCompare
  | term ( '<<' | '>>' | '&' | '|' ) term #TermIgnore
  | term 'NOT'? 'BETWEEN' 'SYMMETRIC'? term 'AND' term #TermBETWEEN
  | term 'NOT'? 'IN' '(' ( select |  terms ) ')' #TermIN
  | term 'NOT'? ( 'LIKE' | 'ILIKE' | 'SIMILAR' 'TO' ) term #TermLIKE
  | term 'ESCAPE' term #TermIgnore
  | term ( '<' | '>' | '<=' | '>=' ) term #TermCompare
  | term ( '=' | '==' | '!=' | '<>' ) term #TermCompare

  | term 'IS' 'NOT'? ( 'DISTINCT' 'FROM' )? term #TermIgnore
  | term ( 'ISNULL' | 'NOTNULL' | 'NOT' 'NULL' ) #TermIgnore
  | term 'IS' 'NOT'? normalForm? 'NORMALIZED' #TermIgnore
  | term 'IS' 'NOT'? 'OF' '(' type ( ',' type )* ')' #TermIgnore

  // best guess for precedence for following...
  | row #TermRow
  | row 'OVERLAPS' row #TermOverlaps
  // unary right
  | <assoc=right> term OPERATOR #TermIgnore
  | 'CASE' term? when+ ( 'ELSE' term )? 'END' #TermIgnore
  | function ( 'WITHIN' 'GROUP' '(' orderBy ')' )? ( 'FILTER' '(' where ')' )? ( 'OVER' ( windowSpec | id ))?  #TermIgnore
  // TODO do these other nestings also need index suffix?
  | 'EXISTS' '(' select ')' #TermIgnore
  | 'ARRAY' ( '(' select ')' | array ) #TermIgnore
  | 'GROUPING' '(' terms ')' #TermIgnore
  | 'UNIQUE' '(' select ')' #TermIgnore
  | ( 'ANY' | 'SOME' | 'ALL' )? '(' ( select | term ) ')' index* #TermIgnore
  | 'NOT' term #TermIgnore
  // workaround to ensure BETWEEN beats AND, building correct parse tree
  | term { notBETWEEN( $ctx ) }? 'AND' term #TermIgnore
  | term 'OR' term #TermIgnore
  ;

literal
  : PARAM
  | integer
  | FLOAT
  | string
  | interval
  // TODO move this to be a type cast somewhere else?
  | type string
  ;

function
    : genericFunction
    // Chaotic mutant function signatures go here
    | 'COLLATION' 'FOR' '(' term ')'
    | 'CAST' '(' term 'AS' type ')'
    | 'EXTRACT' '(' ( name 'FROM' term )? ')'
    | 'NORMALIZE' '(' term ( ',' normalForm )? ')'
    | 'OVERLAY' '(' ( term 'PLACING' term 'FROM' term ( 'FOR' term )? | args? ) ')'
    | 'POSITION' '(' ( term 'IN' term )? ')'
    | 'SUBSTRING' '(' ( substr_list | args? ) ')'
    | 'TREAT' '(' term 'AS' type ')'
    | 'TRIM' '(' ( 'BOTH' | 'LEADING' | 'TRAILING' )? ( term? 'FROM' )? terms ')'
    | 'XMLELEMENT' '(' 'NAME' id ( ',' ( 'XMLATTRIBUTES' '(' xmlAttrib ( ',' xmlAttrib )* ')' | terms ) )? ')'
    | 'XMLEXISTS' '(' xmlPassings ')'
    | 'XMLFOREST' '(' xmlAttrib ( ',' xmlAttrib )* ')'
    | 'XMLPARSE' '(' root term ('PRESERVE' | 'STRIP') 'WHITESPACE'? ')'
    | 'XMLPI' '(' 'NAME' id ( ',' term )? ')'
    | 'XMLROOT' '(' 'XML' term ',' 'VERSION' (term |  'NO' 'VALUE') ( ',' 'STANDALONE' ( 'YES' | 'NO' 'VALUE'? ) )? ')'
    | 'XMLSERIALIZE' '(' root term 'AS' scalarType ')'
    | 'JSON' '(' jsonValue jsonUniqueKeys? ')'
    | 'JSON_ARRAY' '(' ( jsonValue (',' jsonValue)* jsonOnNull? jsonReturning? | '(' select ')' jsonFormat? jsonReturning? | jsonReturning? ) ')'
    | 'JSON_EXISTS' '(' jsonValue ',' term jsonPassing? jsonOnError? ')'
    | 'JSON_OBJECT' '(' ( args | jsonPair (',' jsonPair)* jsonOnNull? jsonUniqueKeys? jsonReturning? | jsonReturning? ) ')'
    | 'JSON_QUERY' '(' jsonValue ',' term jsonPassing? jsonReturning? json_wrapper_behavior? ( 'KEEP' | 'OMIT' ) 'QUOTES' ( 'ON' 'SCALAR' 'STRING' )?? jsonOnEmpty? jsonOnError? ')'
    | 'JSON_SERIALIZE' '(' jsonValue jsonReturning? ')'
    | 'JSON_VALUE' '(' jsonValue ',' term jsonPassing? jsonReturning? jsonOnEmpty? jsonOnError? ')'
    ;

genericFunction
  : qname '('
    ( ( args ( ',' 'VARIADIC' arg )?
      | 'VARIADIC' arg
      | unique2 args
      ) orderBy?
    | '*'
    )?
    ')'
  ;


xmlAttrib
  : term ( 'AS' id )? ;

root
  : 'DOCUMENT' | 'CONTENT' ;

xmlPassings
  : term 'PASSING' ( 'BY' ( 'REF' | 'VALUE' ) )? term ( 'BY' ( 'REF' | 'VALUE' ) )? ;

row
  : 'ROW' '(' ( term ( ',' term )* )? ')'
  | '(' term ( ',' term )+ ')'
  ;

args
  : arg ( ',' arg )* ;

arg
  :  ( id ( ':=' | '=>' ) )? term ;

array
  : '[' ( terms | array ( ',' array )* )? ']' ;

normalForm
  : 'NFC' | 'NFD' | 'NFKC' | 'NFKD' ;

substr_list
    : term 'FROM' term 'FOR' term
    | term 'FOR' term 'FROM' term
    | term 'FROM' term
    | term 'FOR' term
    | term 'SIMILAR' term 'ESCAPE' term
    ;

when
  : 'WHEN' term 'THEN' term ;

index // TODO better name. deref? chain? back to indirection?
  : '.' ( id | '*' )
  | '[' ( term | term? ':' term? ) ']'
  ;

jsonPassing
  : 'PASSING' jsonValueAlias ( ',' jsonValueAlias )* ;

jsonValueAlias
  : jsonValue 'AS' id ;

jsonReturning
  : 'RETURNING' type jsonFormat? ;

json_wrapper_behavior
  : ( 'WITHOUT' | 'WITH' ( 'UNCONDITIONAL' | 'CONDITIONAL' )? ) 'ARRAY'? 'WRAPPER' ;

jsonOnEmpty 
  : jsonBehavior 'ON' 'EMPTY' ;

jsonOnError
  : jsonBehavior 'ON' 'ERROR' ;

jsonBehavior
  : 'DEFAULT' term
  | ( 'ERROR'
    | 'NULL'
    | 'TRUE'
    | 'FALSE'
    | 'UNKNOWN'
    | 'EMPTY' ( 'ARRAY'|'OBJECT' )?
    )
  ;

jsonValue
  : term jsonFormat? ;

jsonFormat
  : 'FORMAT_LA' 'JSON' ( 'ENCODING' id )? ;

jsonUniqueKeys
  : withers 'UNIQUE' 'KEYS'? ;

jsonPair
  : literal 'VALUE' jsonValue
  | term ':' jsonValue
  ;

jsonOnNull
  : ( 'NULL' | 'ABSENT' ) 'ON' 'NULL' ;

// TODO refactor qnames, ids, columns, etc
qnames
  : qname ( ',' qname )* ;

qname
  : name index* ;

columns
//  : '(' id ( ',' id )* ')' ;
  : '(' qname ( ',' qname )* ')' ;

ids
  : id ( ',' id )* ;

integer
  : DECIMAL
  | BINARY
  | OCTAL
  | HEXIDECIMAL
  ;

string
  : STRING
  | UnicodeEscapeStringConstant ( 'UESCAPE' STRING )?
  | BITS
  | BLOB
  ;

signedDecimal
  : ('+' | '-')? DECIMAL ;

signedFloat
  : ('+' | '-')? FLOAT ;

id
  : ID ( 'UESCAPE' STRING )?
  | VARIABLE
  | keyword
  ;

boolean
  : 'TRUE' | 'FALSE' ;

// TODO disable (comment out) reserved keywords
keyword
  : 'ABORT'
  | 'ABSENT'
  | 'ABSOLUTE'
  | 'ACCESS'
  | 'ACTION'
  | 'ADD'
  | 'ADMIN'
  | 'AFTER'
  | 'AGGREGATE'
  | 'ALL'
  | 'ALSO'
  | 'ALTER'
  | 'ALWAYS'
  | ANALYZE
  | 'AND'
  | 'ANY'
  | 'ARRAY'
//  | 'AS' reserved word
  | 'ASC'
  | 'ASENSITIVE'
  | 'ASSERTION'
  | 'ASSIGNMENT'
  | 'ASYMMETRIC'
  | 'AT'
  | 'ATOMIC'
  | 'ATTACH'
  | 'ATTRIBUTE'
  | 'AUTHORIZATION'
  | 'BACKWARD'
  | 'BEFORE'
  | 'BEGIN'
  | 'BETWEEN'
  | 'BIGINT'
  | 'BINARY'
  | 'BIT'
  | 'BOOLEAN'
  | 'BOTH'
  | 'BREADTH'
  | 'BY'
  | 'CACHE'
  | 'CALL'
  | 'CALLED'
  | 'CASCADE'
  | 'CASCADED'
  | 'CASE'
  | 'CAST'
  | 'CATALOG'
  | 'CHAIN'
  | 'CHAR'
  | 'CHARACTER'
  | 'CHARACTERISTICS'
  | 'CHECK'
  | 'CHECKPOINT'
  | 'CLASS'
  | 'CLOSE'
  | 'CLUSTER'
  | 'COALESCE'
  | 'COLLATE'
  | 'COLLATION'
  | 'COLUMN'
  | 'COLUMNS'
  | 'COMMENT'
  | 'COMMENTS'
  | 'COMMIT'
  | 'COMMITTED'
  | 'COMPRESSION'
  | 'CONCURRENTLY'
  | 'CONDITIONAL'
  | 'CONFIGURATION'
  | 'CONFLICT'
  | 'CONNECTION'
  | 'CONSTRAINT'
  | 'CONSTRAINTS'
  | 'CONTENT'
  | 'CONTINUE'
  | 'CONVERSION'
  | 'COPY'
  | 'COST'
  | 'CREATE'
  | 'CROSS'
  | 'CSV'
  | 'CUBE'
  | 'CURRENT'
  | 'CURRENT_CATALOG'
  | 'CURRENT_DATE'
  | 'CURRENT_ROLE'
  | 'CURRENT_SCHEMA'
  | 'CURRENT_TIME'
  | 'CURRENT_TIMESTAMP'
  | 'CURRENT_USER'
  | 'CURSOR'
  | 'CYCLE'
  | 'DATA'
  | 'DATABASE'
  | 'DAY'
  | 'DEALLOCATE'
  | 'DEC'
  | 'DECIMAL'
  | 'DECLARE'
  | 'DEFAULT'
  | 'DEFAULTS'
  | 'DEFERRABLE'
  | 'DEFERRED'
  | 'DEFINER'
  | 'DELETE'
  | 'DELIMITER'
  | 'DELIMITERS'
  | 'DEPENDS'
  | 'DEPTH'
  | 'DESC'
  | 'DETACH'
  | 'DICTIONARY'
  | 'DISABLE'
  | 'DISCARD'
  | 'DISTINCT'
  | 'DO'
  | 'DOCUMENT'
  | 'DOMAIN'
  | 'DOUBLE'
  | 'DROP'
  | 'EACH'
  | 'ELSE'
  | 'EMPTY'
  | 'ENABLE'
  | 'ENCODING'
  | 'ENCRYPTED'
  | 'END'
  | 'ENUM'
  | 'ERROR'
  | 'ESCAPE'
  | 'EVENT'
  | 'EXCEPT'
  | 'EXCLUDE'
  | 'EXCLUDING'
  | 'EXCLUSIVE'
  | 'EXECUTE'
  | 'EXISTS'
  | 'EXPLAIN'
  | 'EXPRESSION'
  | 'EXTENSION'
  | 'EXTERNAL'
  | 'EXTRACT'
  | 'FALSE'
  | 'FAMILY'
  | 'FETCH'
  | 'FILTER'
  | 'FINALIZE'
  | 'FIRST'
  | 'FLOAT'
  | 'FOLLOWING'
  | 'FOR'
  | 'FORCE'
  | 'FOREIGN'
  | 'FORMAT'
  | 'FORMAT_LA'
  | 'FORWARD'
  | 'FREEZE'
  | 'FROM'
  | 'FULL'
  | 'FUNCTION'
  | 'FUNCTIONS'
  | 'GENERATED'
  | 'GLOBAL'
  | 'GRANT'
  | 'GRANTED'
  | 'GREATEST'
  | 'GROUP'
  | 'GROUPING'
  | 'GROUPS'
  | 'HANDLER'
  | 'HAVING'
  | 'HEADER'
  | 'HOLD'
  | 'HOUR'
  | 'IDENTITY'
  | 'IF'
  | 'ILIKE'
  | 'IMMEDIATE'
  | 'IMMUTABLE'
  | 'IMPLICIT'
  | 'IMPORT'
  | 'IN'
  | 'INCLUDE'
  | 'INCLUDING'
  | 'INCREMENT'
  | 'INDENT'
  | 'INDEX'
  | 'INDEXES'
  | 'INHERIT'
  | 'INHERITS'
  | 'INITIALLY'
  | 'INLINE'
  | 'INNER'
  | 'INOUT'
  | 'INPUT'
  | 'INSENSITIVE'
  | 'INSERT'
  | 'INSTEAD'
  | 'INT'
  | 'INTEGER'
  | 'INTERSECT'
  | 'INTERVAL'
  | 'INTO'
  | 'INVOKER'
  | 'IS'
  | 'ISNULL'
  | 'ISOLATION'
  | 'JOIN'
  | 'JSON'
  | 'JSON_ARRAY'
  | 'JSON_ARRAYAGG'
  | 'JSON_EXISTS'
  | 'JSON_OBJECT'
  | 'JSON_OBJECTAGG'
  | 'JSON_QUERY'
  | 'JSON_SCALAR'
  | 'JSON_SERIALIZE'
  | 'JSON_TABLE'
  | 'JSON_VALUE'
  | 'KEEP'
  | 'KEY'
  | 'KEYS'
  | 'LABEL'
  | 'LANGUAGE'
  | 'LARGE'
  | 'LAST'
  | 'LATERAL'
  | 'LEADING'
  | 'LEAKPROOF'
  | 'LEAST'
  | 'LEFT'
  | 'LEVEL'
  | 'LIKE'
  | 'LIMIT'
  | 'LISTEN'
  | 'LOAD'
  | 'LOCAL'
  | 'LOCALTIME'
  | 'LOCALTIMESTAMP'
  | 'LOCATION'
  | 'LOCK'
  | 'LOCKED'
  | 'LOGGED'
  | 'MAPPING'
  | 'MATCH'
  | 'MATCHED'
  | 'MATERIALIZED'
  | 'MAXVALUE'
  | 'MERGE'
  | 'MERGE_ACTION'
  | 'METHOD'
  | 'MINUTE'
  | 'MINVALUE'
  | 'MODE'
  | 'MONTH'
  | 'MOVE'
  | 'NAME'
  | 'NAMES'
  | 'NATIONAL'
  | 'NATURAL'
  | 'NCHAR'
  | 'NESTED'
  | 'NEW'
  | 'NEXT'
  | 'NFC'
  | 'NFD'
  | 'NFKC'
  | 'NFKD'
  | 'NO'
  | 'NONE'
  | 'NORMALIZE'
  | 'NORMALIZED'
  | 'NOT'
  | 'NOTHING'
  | 'NOTIFY'
  | 'NOTNULL'
  | 'NOWAIT'
  | 'NULL'
  | 'NULLIF'
  | 'NULLS'
  | 'NUMERIC'
  | 'OBJECT'
  | 'OF'
  | 'OFF'
  | 'OFFSET'
  | 'OIDS'
  | 'OLD'
  | 'OMIT'
  | 'ON'
  | 'ONLY'
  | 'OPERATOR'
  | 'OPTION'
  | 'OPTIONS'
  | 'OR'
  | 'ORDER'
  | 'ORDINALITY'
  | 'OTHERS'
  | 'OUT'
  | 'OUTER'
  | 'OVER'
  | 'OVERLAPS'
  | 'OVERLAY'
  | 'OVERRIDING'
  | 'OWNED'
  | 'OWNER'
  | 'PARALLEL'
  | 'PARAMETER'
  | 'PARSER'
  | 'PARTIAL'
  | 'PARTITION'
  | 'PASSING'
  | 'PASSWORD'
  | 'PATH'
  | 'PERIOD'
  | 'PLACING'
  | 'PLAN'
  | 'PLANS'
  | 'POLICY'
  | 'POSITION'
  | 'PRECEDING'
  | 'PRECISION'
  | 'PREPARE'
  | 'PREPARED'
  | 'PRESERVE'
  | 'PRIMARY'
  | 'PRIOR'
  | 'PRIVILEGES'
  | 'PROCEDURAL'
  | 'PROCEDURE'
  | 'PROCEDURES'
  | 'PROGRAM'
  | 'PUBLICATION'
  | 'QUOTE'
  | 'QUOTES'
  | 'RANGE'
  | 'READ'
  | 'REAL'
  | 'REASSIGN'
  | 'RECHECK'
  | 'RECURSIVE'
  | 'REF'
  | 'REFERENCES'
  | 'REFERENCING'
  | 'REFRESH'
  | 'REINDEX'
  | 'RELATIVE'
  | 'RELEASE'
  | 'RENAME'
  | 'REPEATABLE'
  | 'REPLACE'
  | 'REPLICA'
  | 'RESET'
  | 'RESTART'
  | 'RESTRICT'
  | 'RETURN'
  | 'RETURNING'
  | 'RETURNS'
  | 'REVOKE'
  | 'RIGHT'
  | 'ROLE'
  | 'ROLLBACK'
  | 'ROLLUP'
  | 'ROUTINE'
  | 'ROUTINES'
  | 'ROW'
  | 'ROWS'
  | 'RULE'
  | 'SAVEPOINT'
  | 'SCALAR'
  | 'SCHEMA'
  | 'SCHEMAS'
  | 'SCROLL'
  | 'SEARCH'
  | 'SECOND'
  | 'SECURITY'
//  | 'SELECT'
  | 'SEQUENCE'
  | 'SEQUENCES'
  | 'SERIALIZABLE'
  | 'SERVER'
  | 'SESSION'
  | 'SESSION_USER'
  | 'SET'
  | 'SETOF'
  | 'SETS'
  | 'SHARE'
  | 'SHOW'
  | 'SIMILAR'
  | 'SIMPLE'
  | 'SKIP'
  | 'SMALLINT'
  | 'SNAPSHOT'
  | 'SOME'
  | 'SOURCE'
  | 'SQL'
  | 'STABLE'
  | 'STANDALONE'
  | 'START'
  | 'STATEMENT'
  | 'STATISTICS'
  | 'STDIN'
  | 'STDOUT'
  | 'STORAGE'
  | 'STORED'
  | 'STRICT'
  | 'STRING'
  | 'STRIP'
  | 'SUBSCRIPTION'
  | 'SUBSTRING'
  | 'SUPPORT'
  | 'SYMMETRIC'
  | 'SYSID'
  | 'SYSTEM'
  | 'SYSTEM_USER'
  | 'TABLE'
  | 'TABLES'
  | 'TABLESAMPLE'
  | 'TABLESPACE'
  | 'TARGET'
  | 'TEMP'
  | 'TEMPLATE'
  | 'TEMPORARY'
  | 'TEXT'
  | 'THEN'
  | 'TIES'
  | 'TIME'
  | 'TIMESTAMP'
  | 'TO'
  | 'TRAILING'
  | 'TRANSACTION'
  | 'TRANSFORM'
  | 'TREAT'
  | 'TRIGGER'
  | 'TRIM'
  | 'TRUE'
  | 'TRUNCATE'
  | 'TRUSTED'
  | 'TYPE'
  | 'TYPES'
  | 'UESCAPE'
  | 'UNBOUNDED'
  | 'UNCOMMITTED'
  | 'UNCONDITIONAL'
  | 'UNENCRYPTED'
  | 'UNION'
  | 'UNIQUE'
  | 'UNKNOWN'
  | 'UNLISTEN'
  | 'UNLOGGED'
  | 'UNTIL'
  | 'UPDATE'
  | 'USER'
  | 'USING'
  | 'VACUUM'
  | 'VALID'
  | 'VALIDATE'
  | 'VALIDATOR'
  | 'VALUE'
  | 'VALUES'
  | 'VARCHAR'
  | 'VARIADIC'
  | 'VARYING'
  | 'VERBOSE'
  | 'VERSION'
  | 'VIEW'
  | 'VIEWS'
  | 'VOLATILE'
  | 'WHEN'
  | 'WHERE'
  | 'WHITESPACE'
  | 'WINDOW'
  | 'WITH'
  | 'WITHIN'
  | 'WITHOUT'
  | 'WORK'
  | 'WRAPPER'
  | 'WRITE'
  | 'XML'
  | 'XMLATTRIBUTES'
  | 'XMLCONCAT'
  | 'XMLELEMENT'
  | 'XMLEXISTS'
  | 'XMLFOREST'
  | 'XMLNAMESPACES'
  | 'XMLPARSE'
  | 'XMLPI'
  | 'XMLROOT'
  | 'XMLSERIALIZE'
  | 'XMLTABLE'
  | 'YEAR'
  | 'YES'
  | 'ZONE'
  ;

ANALYZE
  : 'ANALYZE' | 'ANALYSE' ;

PARAM
  : '$' DIGITS ;

// https://www.postgresql.org/docs/current/sql-syntax-lexical.html#SQL-SYNTAX-OPERATORS

// FIXME pattern matching fails on edge cases, switch to mode
// FIXME discern comments /* and -- from operators
// FIXME disallow '+' ending (per rules)
OPERATOR
//  ( '+' | [-/<>=~!@#%^&|`?] | '*' )* ( [-/<>=~!@#%^&|`?] | '*' )
  : '<->'
  | '@-@'
  | '@+@'
  | '!=-'
  | '|/' // square root
  | '||/' // cube root
  | '->' // JSON thing
  | '->>' // JSON thing
  | '-|-'
  | '!+!'
  | '?' '|' '|'
  | '?' '-' '|'
  | ( [<>=~!@#%^&|`?] | '*' )+
  ;


// TODO include these known operators? or let OPERATOR (or operator) find them?
/*
    | '||'
    | '->'
    | '->>'
    | '#-'
    | '#>'
    | '#>>'
    | '!='
    | '~'
    | '@'
    | '@@'
    | '@>'
    | '<@'
    | '|/'
    | '||/'
    | '*<'
    | '*<='
    | '*<>'
    | '*>='
    | '*>'
    | '*='
    | '&'
    | '&&'
    | '&<'
    | '&>'
    | '?'
    | '!!'
    | '|'
    | '-|-'
    | '==='
*/

ID
  : ( 'U' '&' )? '"' ( '""' | ~ [\u0000"] )* '"'
  | [A-Z_\u007F-\uFFFF] [A-Z_$0-9\u007F-\uFFFF]*
  ;

STRING
  : '\'' ('\'\'' | ~ '\'')* '\''
  // TODO separate lexer rules for nested dollar quoted strings
  | TAG .*? TAG
  ;

UnicodeEscapeStringConstant
  : 'U' '&' '\'' ( '\'\'' | ~ '\'' )* '\''
  // https://www.postgresql.org/docs/current/sql-syntax-lexical.html#SQL-SYNTAX-STRINGS-ESCAPE
  | 'E' '\'' ( '\'\'' | ESCAPE_SEQUENCE | ~( '\'' | '\\' ) )* '\''
  ;

// TODO add octal, hex, unicode sequences
fragment ESCAPE_SEQUENCE
  : '\\' ('\\' | '\'' | 't' | 'n' | 'r' | 'b' | 'f' )
  ;

BITS
  : 'B' '\'' [01]* '\'' ;

BLOB
  : 'X' '\'' [0-9A-F]* '\'' ;

DECIMAL
  : DIGITS ;

BINARY
  : '0b' [01]+ ;

OCTAL
  : '0o' [0-7]+ ;

HEXIDECIMAL
  : '0x' [A-F0-9]+ ;

FLOAT
  : ( DIGITS ( '.' DIGITS? )? | '.' DIGITS ) ( 'E' [-+]? DIGITS )? ;

VARIABLE
  : ':' [A-Z_\u007F-\uFFFF] [A-Z_$0-9\u007F-\uFFFF]* ;

LineComment
  : '--' ~ [\r\n]* -> channel (HIDDEN) ;

BlockComment
  : ('/*' ('/'* BlockComment | ~ [/*] | '/'+ ~ [/*] | '*'+ ~ [/*])* '*'* '*/') -> channel (HIDDEN) ;

SEMI
  : ';'   | '\\' ';' ;

META
  : '\\' ~[;]? ~[\r\n\\]* -> type( SEMI ) ;

// \u000B line (vertical) tab
// \u000C form feed
WHITESPACE
  : [ \b\t\r\n\u000B\u000C]+ -> channel ( HIDDEN ) ;

UNKNOWN
  : . ;

fragment DIGITS
  : [0-9]+ ;

fragment TAG
  : '$' ( [A-Z_\u007F-\uFFFF] [A-Z_0-9\u007F-\uFFFF]* )? '$' ;
