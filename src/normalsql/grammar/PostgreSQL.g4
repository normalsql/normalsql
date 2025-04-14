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

/*
warning(154): PostgreSQL.g4:28:0: rule parse contains an optional block with at least one alternative that can match an empty string
*/
parse
  : statement? ( SEMI statement? )* EOF
  ;

statement
    : altereventtrigstmt
    | altercollationstmt
    | alterdatabasestmt
    | alterdatabasesetstmt
    | alterdefaultprivilegesstmt
    | alterdomainstmt
    | alterenumstmt
    | alterextensionstmt
    | alterextensioncontentsstmt
    | alterfdwstmt
    | alterforeignserverstmt
    | alterfunctionstmt
    | altergroupstmt
    | alterobjectdependsstmt
    | alterobjectschemastmt
    | alterownerstmt
    | alteroperatorstmt
    | altertypestmt
    | alterpolicystmt
    | alterseqstmt
    | altersystemstmt
    | altertablestmt
    | alterTablespace
    | altercompositetypestmt
    | alterpublicationstmt
    | alterrolesetstmt
    | alterrolestmt
    | altersubscriptionstmt
    | alterstatsstmt
    | altertsconfigurationstmt
    | altertsdictionarystmt
    | alterusermappingstmt
    | analyzestmt
    | callstmt
    | 'CHECKPOINT'
    | closeportalstmt
    | clusterstmt
    | commentstmt
    | constraintssetstmt
    | copystmt
    | createamstmt
    | createTableAs
    | createassertionstmt
    | createcaststmt
    | createconversionstmt
    | createdomainstmt
    | createextensionstmt
    | createfdwstmt
    | createforeignserverstmt
    | createForeignTable
    | createfunctionstmt
    | creategroupstmt
    | creatematviewstmt
    | createopclassstmt
    | createopfamilystmt
    | createpublicationstmt
    | alteropfamilystmt
    | createpolicystmt
    | createplangstmt
    | createschemastmt
    | createseqstmt
    | createTable
    | createsubscriptionstmt
    | createstatsstmt
    | createtablespacestmt
    | createtransformstmt
    | createTrigger
    | createeventtrigstmt
    | createrolestmt
    | createuserstmt
    | createusermappingstmt
    | createdbstmt
    | deallocatestmt
    | declarecursorstmt
    | definestmt
    | delete
    | discardstmt
    | dostmt
    | dropcaststmt
    | dropOperator
//    | dropopfamilystmt
    | dropownedstmt
    | dropstmt
    | dropsubscriptionstmt
    | droptablespacestmt
    | droptransformstmt
    | droprolestmt
    | dropusermappingstmt
    | dropdbstmt
    | executePrepared
    | explainstmt
    | fetchstmt
    | grantstmt
    | grantrolestmt
    | importforeignschemastmt
    | createIndex
    | insert
    | mergestmt
    | listenstmt
    | refreshmatviewstmt
    | loadstmt
    | lockstmt
    | notifystmt
    | preparestmt
    | reassignownedstmt
    | reindex
    | removeaggrstmt
    | removefuncstmt
    | removeoperstmt
    | renamestmt
    | revokestmt
    | revokerolestmt
    | rulestmt
    | seclabelstmt
    | select
    | transactionstmt
    | truncatestmt
    | unlistenstmt
    | update
    | vacuumstmt
    | variableresetstmt
    | variablesetstmt
    | viewstmt
    ;

callstmt
    : 'CALL' genericFunction
    ;

createrolestmt
    : 'CREATE' 'ROLE' id 'WITH'? createoptroleelem*
    ;

createoptroleelem
    : alteroptroleelem
    | 'SYSID' integer
    | 'ADMIN' ids
    | 'ROLE' ids
    | 'IN' ( 'ROLE' | 'GROUP' ) ids
    ;

createuserstmt
    : 'CREATE' 'USER' id 'WITH'? createoptroleelem* ;

creategroupstmt
    : 'CREATE' 'GROUP' id 'WITH'? createoptroleelem* ;

alterrolestmt
    : 'ALTER' ( 'ROLE' | 'USER' ) id 'WITH'? alteroptroleelem*
    ;

alteroptroleelem
    : 'PASSWORD' ( string | 'NULL' )
    | ( 'ENCRYPTED' | 'UNENCRYPTED' ) 'PASSWORD' string
    | 'INHERIT'
    | 'CONNECTION' 'LIMIT' signedDecimal
    | 'VALID' 'UNTIL' string
    | 'USER' ids
    | id
    ;

alterrolesetstmt
    : 'ALTER' ( 'ROLE' | 'USER' ) 'ALL'? id ('IN' 'DATABASE' id)? setresetclause
    ;

droprolestmt
    : 'DROP' ( 'ROLE' | 'USER' | 'GROUP' ) ifExists? ids
    ;

altergroupstmt
  : 'ALTER' 'GROUP' id addDrop 'USER' ids ;

addDrop
  : 'ADD' | 'DROP' ;

createschemastmt
  : 'CREATE' 'SCHEMA' ifNotExists? ( id? 'AUTHORIZATION' )? id
    ( createTable
    | createIndex
    | createseqstmt
    | createTrigger
    | grantstmt
    | viewstmt
    )*
  ;

variablesetstmt
  : 'SET' ( 'LOCAL' | 'SESSION' )? set_rest ;

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

// Can be either a literal or a typecast (?)
interval
  : 'INTERVAL' ( string? timeUnit | '(' integer ')' string? )?
  ;

value
    : id | number | string ;

name
  : id | string ;

variableresetstmt
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
    | variableresetstmt
    ;

constraintssetstmt
    : 'SET' 'CONSTRAINTS' ( 'ALL' | qnames ) ( 'DEFERRED' | 'IMMEDIATE' )
    ;

discardstmt
    : 'DISCARD' ( 'ALL' | 'TEMP' | 'TEMPORARY' | 'PLANS' | 'SEQUENCES' )
    ;

altereventtrigstmt
    : 'ALTER' 'EVENT' 'TRIGGER' id
      ( 'ENABLE' ( 'REPLICA' |  'ALWAYS' )? | 'DISABLE' )
    ;

altertablestmt
    : 'ALTER' 'TABLE' ifExists? descendants ( alter_table_cmds | ('ATTACH' 'PARTITION' qname forValues | 'DETACH' 'PARTITION' qname ) )
    | 'ALTER' 'INDEX' ifExists? qname ( alter_table_cmds | 'ATTACH' 'PARTITION' qname )
    | 'ALTER' 'SEQUENCE' ifExists? qname alter_table_cmds
    | 'ALTER' 'VIEW' ifExists? qname alter_table_cmds
    | 'ALTER' 'MATERIALIZED' 'VIEW' ifExists? qname alter_table_cmds
    | 'ALTER' ( 'INDEX' | 'TABLE' | 'MATERIALIZED' 'VIEW' ) 'ALL' 'IN' 'TABLESPACE' id ( 'OWNED' 'BY' ids )? 'SET' 'TABLESPACE' id 'NOWAIT'?
    | 'ALTER' 'FOREIGN' 'TABLE' ifExists? descendants alter_table_cmds
    ;

alter_table_cmds
    : alter_table_cmd ( ',' alter_table_cmd )*
    ;

alter_table_cmd
    : 'ADD' tableconstraint
//    | 'ADD' ifNotExists? columnDef
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
    | 'DROP' 'COLUMN'? ifExists? id drop_behavior_?
    | 'ALTER' 'COLUMN'? id ('SET' 'DATA')? 'TYPE' type collate? ('USING' term)?
    | 'ALTER' 'COLUMN'? id genericOptions
    | 'ALTER' 'CONSTRAINT' id timing*
    | 'VALIDATE' 'CONSTRAINT' id
    | 'DROP' 'CONSTRAINT' ifExists? id drop_behavior_?
    | 'SET' 'WITHOUT' 'OIDS'
    | 'CLUSTER' 'ON' id
    | 'SET' 'WITHOUT' 'CLUSTER'
    | 'SET' 'LOGGED'
    | 'SET' 'UNLOGGED'
    | ( 'DISABLE' | 'ENABLE' ) ( 'ALWAYS' | 'REPLICA' )? 'TRIGGER' id
    | ( 'DISABLE' | 'ENABLE' ) 'TRIGGER' ( 'ALL' | 'USER' )
    | 'ENABLE' ( 'ALWAYS' | 'REPLICA' )? 'RULE' id
    | 'DISABLE' 'RULE' id
    | 'INHERIT' qname
    | noInherit qname
    | 'OF' qname
    | 'NOT' 'OF'
    | 'OWNER' 'TO' id
    | 'SET' 'TABLESPACE' id
    | 'SET' definition
    | 'RESET' definition
    | 'REPLICA' 'IDENTITY' ( 'NOTHING' | 'FULL' | 'DEFAULT' | 'USING' 'INDEX' id )
    | ( 'DISABLE' | 'ENABLE' | 'NO'? 'FORCE' ) 'ROW' 'LEVEL' 'SECURITY'
    | genericOptions
    ;

noInherit : 'NO' 'INHERIT' ;

drop_behavior_
  : 'CASCADE'
  | 'RESTRICT'
  ;

collate
    : 'COLLATE' qname ;

alter_identity_column_option
    : 'RESTART' ( 'WITH'? number )?
    | 'SET' ( seqoptelem | generatedWhen )
    ;

altercompositetypestmt
    : 'ALTER' 'TYPE' qname alter_type_cmd ( ',' alter_type_cmd )*
    ;

alter_type_cmd
    : 'ADD' 'ATTRIBUTE' tablefuncelement drop_behavior_?
    | 'DROP' 'ATTRIBUTE' ifExists? id drop_behavior_?
    | 'ALTER' 'ATTRIBUTE' id ('SET' 'DATA')? 'TYPE' type collate? drop_behavior_?
    ;

closeportalstmt
  : 'CLOSE' ( id | 'ALL' ) ;

copystmt
    : 'COPY' 'BINARY'? qname columns?
      ('FROM' | 'TO') 'PROGRAM'? copy_file_name ('USING'? 'DELIMITERS' string)? 'WITH'? copy_options where?

    | 'COPY' '(' (select | insert | update | delete) ')'
      'TO' 'PROGRAM'? copy_file_name 'WITH'? copy_options
    ;

copy_file_name
    : string
    | 'STDIN'
    | 'STDOUT'
    ;

copy_options
    : copy_opt_item*
    | '(' copy_generic_opt_elem ( ',' copy_generic_opt_elem )* ')'
    ;

copy_opt_item
    : 'BINARY'
    | 'FREEZE'
    | 'DELIMITER' 'AS'? string
    | 'NULL' 'AS'? string
    | 'CSV'
    | 'HEADER'
    | 'QUOTE' 'AS'? string
    | 'ESCAPE' 'AS'? string
    | 'FORCE' 'QUOTE' ids
    | 'FORCE' 'QUOTE' '*'
    | 'FORCE' 'NOT'? 'NULL' ids
    | 'ENCODING' string
    ;

copy_generic_opt_elem
    : id
      ( name
      | number
      | '*'
      | '(' name ( ',' name )* ')'
      )?
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

createTableAs
    : 'CREATE' scope? 'TABLE' ifNotExists? qname columns?
      fixme 'AS' ( select | executePrepared )
      withData?
    ;

   // TODO better name
   fixme
     : usingID?
       ( withDef | 'WITHOUT' 'OIDS' )?
       ( 'ON' 'COMMIT' ( 'DROP' | 'DELETE' 'ROWS' | 'PRESERVE' 'ROWS' ))?
       tablespaceID?
     ;

createForeignTable
    : 'CREATE' 'FOREIGN' 'TABLE' ifNotExists? qname
      ( '(' ( column ( ',' column )* )? ')' inherit?
      | 'PARTITION' 'OF' parentTable forValues
      )
      'SERVER' id genericOptions?
    ;

forValues
  : 'FOR' 'VALUES' 'WITH' '(' 'modulus' number ','  'remainder' number ')'
  | 'FOR' 'VALUES' 'IN' '(' terms ')'
  | 'FOR' 'VALUES' 'FROM' '(' terms ')' 'TO' '(' terms ')'
  | 'DEFAULT'
  ;

executePrepared
    : 'EXECUTE' name ( '(' terms ')' )?
    ;

scope
    : ( 'LOCAL' | 'GLOBAL' )? ( 'TEMPORARY' | 'TEMP' )
    | 'UNLOGGED'
    ;

usingID
  : 'USING' id ;

parentTable
    : qname ( '(' column ( ',' column )* ')' )?
    ;

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
    : id type? genericOptions? colconstraint*
    ;

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
    | 'REFERENCES' qname columns? refMatchType? refAction*
    ;

generatedWhen
    : 'GENERATED' ( 'ALWAYS' | 'BY' 'DEFAULT' ) ;

tableconstraint
    : ( 'CONSTRAINT' id )?
      ( 'CHECK' '(' term ')' noInherit?
      | ( 'UNIQUE' ( 'NULLS' 'NOT'? 'DISTINCT' )? | 'PRIMARY' 'KEY' ) columns? indexParams
      | 'EXCLUDE' usingID? '(' excludeElement ( ',' excludeElement )* ')' indexParams ( 'WHERE' '(' term ')' )?
      | 'FOREIGN' 'KEY' columns 'REFERENCES' qname columns? refMatchType? refAction*
      )
      ( 'NOT'? 'DEFERRABLE' )?
      ( 'INITIALLY' ( 'IMMEDIATE' | 'DEFERRED' ))?
      ( 'NOT' 'VALID' )?
    ;

indexParams
  : ( 'INCLUDE' columns )?
    withDef?
    // TODO i don't think optional 'TABLESPACE' is correct
    ( 'USING' 'INDEX' 'TABLESPACE'? id )?
  ;

refMatchType
    : 'MATCH' ( 'FULL' | 'PARTIAL' | 'SIMPLE' )
    ;

refAction
    : 'ON' ( 'DELETE' | 'UPDATE' )
      ( 'NO' 'ACTION'
      | 'RESTRICT'
      | 'CASCADE'
      | 'SET' ( 'NULL' | 'DEFAULT' ) columns?
      )
    ;

excludeElement
    : ( id | '(' term ')' ) collate? 'WITH' operator
    ;

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

//usingIndexID
//    : 'USING' 'INDEX' id
//    ;

createstatsstmt
    : 'CREATE' 'STATISTICS' ifNotExists? qname columns? 'ON' terms from
    ;

alterstatsstmt
    : 'ALTER' 'STATISTICS' ifExists? qname 'SET' 'STATISTICS' signedDecimal
    ;

withData
    : 'WITH' 'NO'? 'DATA' ;

creatematviewstmt
    : 'CREATE' 'UNLOGGED'? 'MATERIALIZED' 'VIEW' ifNotExists? qname columns?
      usingID? withDef? tablespaceID? 'AS' select withData?
    ;

refreshmatviewstmt
    : 'REFRESH' 'MATERIALIZED' 'VIEW' 'CONCURRENTLY'? qname withData?
    ;

createseqstmt
    : 'CREATE' scope? 'SEQUENCE' ifNotExists? qname seqoptelem*
    ;

alterseqstmt
    : 'ALTER' 'SEQUENCE' ifExists? qname seqoptelem+
    ;

seqoptelem
    : 'AS' simpletypename
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
    : signedFloat
    | signedDecimal
    ;

createplangstmt
    : 'CREATE' orReplace? 'TRUSTED'? 'PROCEDURAL'? 'LANGUAGE' id
      ( 'HANDLER' qname ('INLINE' qname)? ('VALIDATOR' qname
    | 'NO' 'VALIDATOR')? )?
    ;

createtablespacestmt
    : 'CREATE' 'TABLESPACE' id ('OWNER' id)? 'LOCATION' string withDef?
    ;

droptablespacestmt
    : 'DROP' 'TABLESPACE' ifExists? id
    ;

createextensionstmt
    : 'CREATE' 'EXTENSION' ifNotExists? id 'WITH'?
      ( 'SCHEMA' id
      | 'VERSION' name
      | 'FROM' name
      | 'CASCADE'
      )*
    ;

ifExists : 'IF' 'EXISTS'  ;
ifNotExists : 'IF' 'NOT' 'EXISTS'  ;

alterextensionstmt
  : 'ALTER' 'EXTENSION' id 'UPDATE' ( 'TO' name )* ;

alterextensioncontentsstmt
    : 'ALTER' 'EXTENSION' id addDrop object_type_name id
    | 'ALTER' 'EXTENSION' id addDrop object_type_any_name qname
    | 'ALTER' 'EXTENSION' id addDrop 'AGGREGATE' aggSignature
    | 'ALTER' 'EXTENSION' id addDrop 'CAST' '(' type 'AS' type ')'
    | 'ALTER' 'EXTENSION' id addDrop 'DOMAIN' type
    | 'ALTER' 'EXTENSION' id addDrop 'FUNCTION' funcSignature
    | 'ALTER' 'EXTENSION' id addDrop 'OPERATOR' operator_with_argtypes
    | 'ALTER' 'EXTENSION' id addDrop 'OPERATOR' 'CLASS' qname usingID
    | 'ALTER' 'EXTENSION' id addDrop 'OPERATOR' 'FAMILY' qname usingID
    | 'ALTER' 'EXTENSION' id addDrop 'PROCEDURE' funcSignature
    | 'ALTER' 'EXTENSION' id addDrop 'ROUTINE' funcSignature
    | 'ALTER' 'EXTENSION' id addDrop 'TRANSFORM' 'FOR' type 'LANGUAGE' id
    | 'ALTER' 'EXTENSION' id addDrop 'TYPE' type
    ;

createfdwstmt
    : 'CREATE' 'FOREIGN' 'DATA' 'WRAPPER' id ( genericOptions | handler+ )?
    ;


alterfdwstmt
    : 'ALTER' 'FOREIGN' 'DATA' 'WRAPPER' id ( genericOptions | handler+ )
    ;

createforeignserverstmt
    : 'CREATE' 'SERVER' ifNotExists? id ( 'TYPE' string )? foreign_server_version? 'FOREIGN' 'DATA' 'WRAPPER' id genericOptions?
    ;

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

foreign_server_version
    : 'VERSION' ( string | 'NULL' ) ;

alterforeignserverstmt
    : 'ALTER' 'SERVER' id ( genericOptions | foreign_server_version genericOptions? )
    ;

importforeignschemastmt
    : 'IMPORT' 'FOREIGN' 'SCHEMA' id  (( 'LIMIT' 'TO' | 'EXCEPT' )? '(' descendants ( ',' descendants )* ')' )?
      'FROM' 'SERVER' id 'INTO' id genericOptions?
    ;

createusermappingstmt
    : 'CREATE' 'USER' 'MAPPING' ifNotExists? 'FOR' id 'SERVER' id genericOptions?
    ;

dropusermappingstmt
    : 'DROP' 'USER' 'MAPPING' ifExists? 'FOR' id 'SERVER' id
    ;

alterusermappingstmt
    : 'ALTER' 'USER' 'MAPPING' 'FOR' id 'SERVER' id genericOptions
    ;

createpolicystmt
    : 'CREATE' 'POLICY' id 'ON' qname ('AS' id)? rowsecuritydefaultforcmd? ('TO' ids)? rowsecurityoptionalexpr?
        rowsecurityoptionalwithcheck?
    ;

alterpolicystmt
    : 'ALTER' 'POLICY' id 'ON' qname ('TO' ids)? rowsecurityoptionalexpr? rowsecurityoptionalwithcheck?
    ;

rowsecurityoptionalexpr
    : 'USING' '(' term ')'
    ;

rowsecurityoptionalwithcheck
    : 'WITH' 'CHECK' '(' term ')'
    ;

rowsecuritydefaultforcmd
    : 'FOR' ( 'ALL' | 'SELECT' | 'INSERT' | 'UPDATE' | 'DELETE' )
    ;

createamstmt
    : 'CREATE' 'ACCESS' 'METHOD' id 'TYPE' ('INDEX' | 'TABLE') 'HANDLER' qname
    ;

createTrigger
  : 'CREATE' orReplace? 'CONSTRAINT'? 'TRIGGER' id
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
    : 'INSERT'
    | 'DELETE'
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

createeventtrigstmt
    : 'CREATE' 'EVENT' 'TRIGGER' id 'ON' id
      ( 'WHEN' event_trigger_when_item ( 'AND' event_trigger_when_item )* )?
      executeFunction
    ;

event_trigger_when_item
    : id 'IN' '(' string ( ',' string )* ')'
    ;

createassertionstmt
    : 'CREATE' 'ASSERTION' qname 'CHECK' '(' term ')' timing* ;

definestmt
    : 'CREATE' orReplace? 'AGGREGATE' aggSignature definition
    | 'CREATE' orReplace? 'AGGREGATE' qname definition
    | 'CREATE' 'OPERATOR' operator definition
    | 'CREATE' 'TYPE' qname definition?
    | 'CREATE' 'TYPE' qname 'AS' '(' (tablefuncelement ( ',' tablefuncelement )*)? ')'
    | 'CREATE' 'TYPE' qname 'AS' 'ENUM' '(' ( string ( ',' string )* )? ')'
    | 'CREATE' 'TYPE' qname 'AS' 'RANGE' definition
    | 'CREATE' textSearchConfig qname definition
    | 'CREATE' 'COLLATION' ifNotExists? qname definition
    | 'CREATE' 'COLLATION' ifNotExists? qname 'FROM' qname
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


alterenumstmt
    : 'ALTER' 'TYPE' qname 'ADD' 'VALUE' ifNotExists? string (( 'BEFORE' | 'AFTER' ) string )?
    | 'ALTER' 'TYPE' qname 'RENAME' 'VALUE' string 'TO' string
    ;

createopclassstmt
    : 'CREATE' 'OPERATOR' 'CLASS' qname 'DEFAULT'? forType usingID ('FAMILY' qname)?
      'AS' opclass_item_list
    ;

opclass_purpose
    : 'FOR' ( 'SEARCH' |  'ORDER' 'BY' qname )
    ;

createopfamilystmt
    : 'CREATE' 'OPERATOR' 'FAMILY' qname usingID
    ;

alteropfamilystmt
    : 'ALTER' 'OPERATOR' 'FAMILY' qname usingID
      ('ADD' opclass_item_list | 'DROP' opclass_drop ( ',' opclass_drop )*)
    ;

opclass_item_list
    : opclass_item ( ',' opclass_item )*
    ;

opclass_item
    : 'OPERATOR' integer operator opclass_purpose? 'RECHECK'?
    | 'OPERATOR' integer operator_with_argtypes opclass_purpose? 'RECHECK'?
    | 'FUNCTION' integer funcSignature
    | 'FUNCTION' integer '(' type ( ',' type )* ')' funcSignature
    | 'STORAGE' type
    ;


opclass_drop
    : ( 'OPERATOR' | 'FUNCTION' ) integer '(' type ( ',' type )* ')'
    ;

dropOperator
    : 'DROP' 'OPERATOR' ( 'CLASS' | 'FAMILY' ) ifExists? qname usingID drop_behavior_?
    ;

dropownedstmt
    : 'DROP' 'OWNED' 'BY' ids drop_behavior_?
    ;

reassignownedstmt
    : 'REASSIGN' 'OWNED' 'BY' ids 'TO' id
    ;

dropstmt
    : 'DROP' object_type_any_name ifExists? qnames drop_behavior_?
    | 'DROP' object_type_name_on_any_name ifExists? id 'ON' qname drop_behavior_?
    | 'DROP' drop_type_name ifExists? ids drop_behavior_?
    | 'DROP' ( 'TYPE' | 'DOMAIN' ) ifExists? type ( ',' type )* drop_behavior_?
    | 'DROP' 'INDEX' 'CONCURRENTLY' ifExists? qnames drop_behavior_?
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

truncatestmt
    : 'TRUNCATE' 'TABLE'? descendants ( ',' descendants )* (( 'CONTINUE' | 'RESTART' ) 'IDENTITY' )? drop_behavior_?
    ;

commentstmt
  : 'COMMENT' 'ON'
    ( object_type_any_name qname
    | 'COLUMN' qname
    | object_type_name id
    | 'TYPE' type
    | 'DOMAIN' type
    | 'AGGREGATE' aggSignature
    | routine funcSignature
    | 'OPERATOR' operator_with_argtypes
    | 'CONSTRAINT' id 'ON' 'DOMAIN'? qname?
    | object_type_name_on_any_name id 'ON' qname
    | 'TRANSFORM' 'FOR' type 'LANGUAGE' id
    | 'OPERATOR' ( 'CLASS' | 'FAMILY' ) qname usingID
    | 'LARGE' 'OBJECT' number
    | 'CAST' '(' type 'AS' type ')'
    )
    'IS' ( string | 'NULL' )
  ;

seclabelstmt
    : 'SECURITY' 'LABEL' ('FOR' name)? 'ON'
      ( object_type_any_name qname
      | 'COLUMN' qname
      | object_type_name id
      | 'TYPE' type
      | 'DOMAIN' type
      | 'AGGREGATE' aggSignature
      | 'FUNCTION' funcSignature
      | 'LARGE' 'OBJECT' number
      | 'PROCEDURE' funcSignature
      | 'ROUTINE' funcSignature
      )
      'IS' (string | 'NULL')
    ;



fetchstmt
    : ( 'FETCH' | 'MOVE' )

      ( 'NEXT'
      | 'PRIOR'
      | 'FIRST'
      | 'LAST'
      | ( 'ABSOLUTE' | 'RELATIVE' )? signedDecimal
      | 'ALL'
      | ( 'FORWARD' | 'BACKWARD' ) (signedDecimal | 'ALL' )?
      )?

      ('FROM' | 'IN')? id ;


revokestmt
    : 'REVOKE' privileges 'ON' privilege_target 'FROM' grantee ( ',' grantee )* drop_behavior_?
    | 'REVOKE' 'GRANT' 'OPTION' 'FOR' privileges 'ON' privilege_target 'FROM' grantee ( ',' grantee )* drop_behavior_?
    ;

privileges
    : privilege_list
    | 'ALL'
    | 'ALL' 'PRIVILEGES'? ( columns )?
    ;

privilege_list
    : privilege ( ',' privilege )*
    ;

privilege
    : ( 'SELECT' | 'REFERENCES' | 'CREATE' | id ) columns?
    ;

privilege_target
    : qnames
    | 'TABLE' qnames
    | 'SEQUENCE' qnames
    | 'FOREIGN' 'DATA' 'WRAPPER' ids
    | 'FOREIGN' 'SERVER' ids
    | 'FUNCTION' funcSignature ( ',' funcSignature )*
    | 'PROCEDURE' funcSignature ( ',' funcSignature )*
    | 'ROUTINE' funcSignature ( ',' funcSignature )*
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

grantrolestmt
    : 'GRANT' privilege_list 'TO' ids ('WITH' 'ADMIN' 'OPTION')? granted_by_?
    ;

revokerolestmt
    : 'REVOKE' ('ADMIN' 'OPTION' 'FOR')? privilege_list 'FROM' ids granted_by_? drop_behavior_?
    ;

granted_by_
    : 'GRANTED' 'BY' id
    ;

alterdefaultprivilegesstmt
    : 'ALTER' 'DEFAULT' 'PRIVILEGES'
      ( 'IN' 'SCHEMA' ids | 'FOR' 'ROLE' ids | 'FOR' 'USER' ids )*
      ( 'GRANT' privileges 'ON' defacl_privilege_target 'TO' grantee ( ',' grantee )* withGrantOption?
      | 'REVOKE' ('GRANT' 'OPTION' 'FOR')? privileges 'ON' defacl_privilege_target 'FROM' grantee ( ',' grantee )* drop_behavior_?
      )
    ;

grantstmt
    : 'GRANT' privileges 'ON' privilege_target 'TO' grantee ( ',' grantee )* withGrantOption?
    ;


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
      ('INCLUDE' '(' index_params ')')? withDef? tablespaceID? where?
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

createfunctionstmt
  : 'CREATE' orReplace? ( 'FUNCTION' | 'PROCEDURE' ) qname
    '(' ( paramDef ( ',' paramDef )* )? ')'

    ( 'RETURNS' ( type | 'TABLE' '(' id type ( ',' id type )* ')' ) )?
    ( 'AS' string ( ',' string )?
    | 'LANGUAGE' name
    | 'TRANSFORM' forType ( ',' forType )*
    | 'WINDOW'
    | funcOptions
    )+
  ;

orReplace
  : 'OR' 'REPLACE' ;

paramDef
  : id? argmode? id? type (( 'DEFAULT' | '=' ) term )? ;

funcSignature
    : qname ( '(' triplets? ')' )? ;

aggSignature
  : qname '(' ( '*' | ( triplets? 'ORDER' 'BY' )? triplets ) ')' ;

triplets
  : triplet ( ',' triplet )* ;

triplet
  : ( argmode id? | id argmode? )? type ;

argmode
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
  | variableresetstmt
  | 'PARALLEL' id
  ;

forType
  : 'FOR' 'TYPE' type ;

withDef
    : 'WITH' definition ;

alterfunctionstmt
    : 'ALTER' routine funcSignature funcOptions+ 'RESTRICT'? ;

routine : 'FUNCTION' | 'PROCEDURE' | 'ROUTINE' ;

removefuncstmt
    : 'DROP' routine ifExists? funcSignature ( ',' funcSignature )* drop_behavior_?
    ;

removeaggrstmt
    : 'DROP' 'AGGREGATE' ifExists? aggSignature ( ',' aggSignature )* drop_behavior_?
    ;

removeoperstmt
    : 'DROP' 'OPERATOR' ifExists? operator_with_argtypes ( ',' operator_with_argtypes )* drop_behavior_?
    ;

// qualitified operator
operator
    : ( id '.' )*
      ( OPERATOR
      // Include all implicitly defined operator tokens (in this grammar) here
      | '+' | '-' | '*' | '/' | '&' | '%' | '^' | '<' | '>' | '=' | '<=' | '>=' | '<>'
      )
    ;

operator_with_argtypes
    : operator '(' type ( ',' type )? ')'
    ;

dostmt
    : 'DO' ( 'LANGUAGE'? name )+
    ;

createcaststmt
    : 'CREATE' 'CAST' '(' type 'AS' type ')'
      ('WITH' 'FUNCTION' funcSignature | 'WITHOUT' 'FUNCTION' | 'WITH' 'INOUT')
      ('AS' ( 'IMPLICIT' |  'ASSIGNMENT' ))?
    ;

dropcaststmt
    : 'DROP' 'CAST' ifExists? '(' type 'AS' type ')' drop_behavior_?
    ;

createtransformstmt
    : 'CREATE' orReplace? 'TRANSFORM' 'FOR' type 'LANGUAGE' id '(' transform_element_list ')'
    ;

transform_element_list
    : 'FROM' 'SQL' 'WITH' 'FUNCTION' funcSignature ( ',' 'TO' 'SQL' 'WITH' 'FUNCTION' funcSignature )?
    | 'TO' 'SQL' 'WITH' 'FUNCTION' funcSignature ( ',' 'FROM' 'SQL' 'WITH' 'FUNCTION' funcSignature )?
    ;

droptransformstmt
    : 'DROP' 'TRANSFORM' ifExists? 'FOR' type 'LANGUAGE' id drop_behavior_?
    ;

reindex
  : 'REINDEX' ( '(' reindexOption (  ',' reindexOption )* ')' )?
    ( 'INDEX' | 'TABLE' | 'SCHEMA' | 'SYSTEM' | 'DATABASE' )
    'CONCURRENTLY'? qname?
  ;

  reindexOption
    :  'CONCURRENTLY' boolean?
    |  'TABLESPACE' qname?
    |  'VERBOSE' boolean?
    ;

    // TODO add support for 1 and 0
    boolean
      : 'TRUE' | 'FALSE' | 'ON' | 'OFF' ;


alterTablespace
    : 'ALTER' 'TABLESPACE' id ( 'SET' | 'RESET' ) definition
    | 'ALTER' 'TABLESPACE' id 'OWNER' 'TO' id
    | 'ALTER' 'TABLESPACE' id 'RENAME' 'TO' id
    ;

renamestmt
    : 'ALTER' 'AGGREGATE' aggSignature 'RENAME' 'TO' id
    | 'ALTER' 'COLLATION' qname 'RENAME' 'TO' id
    | 'ALTER' 'CONVERSION' qname 'RENAME' 'TO' id
    | 'ALTER' 'DATABASE' id 'RENAME' 'TO' id
    | 'ALTER' 'DOMAIN' qname 'RENAME' 'TO' id
    | 'ALTER' 'DOMAIN' qname 'RENAME' 'CONSTRAINT' id 'TO' id
    | 'ALTER' 'FOREIGN' 'DATA' 'WRAPPER' id 'RENAME' 'TO' id
    | 'ALTER' 'FUNCTION' funcSignature 'RENAME' 'TO' id
    | 'ALTER' 'GROUP' id 'RENAME' 'TO' id
    | 'ALTER' 'PROCEDURAL'? 'LANGUAGE' id 'RENAME' 'TO' id
    | 'ALTER' 'OPERATOR' 'CLASS' qname usingID 'RENAME' 'TO' id
    | 'ALTER' 'OPERATOR' 'FAMILY' qname usingID 'RENAME' 'TO' id
    | 'ALTER' 'POLICY' ifExists? id 'ON' qname 'RENAME' 'TO' id
    | 'ALTER' 'PROCEDURE' funcSignature 'RENAME' 'TO' id
    | 'ALTER' 'PUBLICATION' id 'RENAME' 'TO' id
    | 'ALTER' 'ROUTINE' funcSignature 'RENAME' 'TO' id
    | 'ALTER' 'SCHEMA' id 'RENAME' 'TO' id
    | 'ALTER' 'SERVER' id 'RENAME' 'TO' id
    | 'ALTER' 'SUBSCRIPTION' id 'RENAME' 'TO' id
    | 'ALTER' 'TABLE' ifExists? descendants 'RENAME' 'TO' id
    | 'ALTER' 'SEQUENCE' ifExists? qname 'RENAME' 'TO' id
    | 'ALTER' 'VIEW' ifExists? qname 'RENAME' 'TO' id
    | 'ALTER' 'MATERIALIZED' 'VIEW' ifExists? qname 'RENAME' 'TO' id
    | 'ALTER' 'INDEX' ifExists? qname 'RENAME' 'TO' id
    | 'ALTER' 'FOREIGN' 'TABLE' ifExists? descendants 'RENAME' 'TO' id
    | 'ALTER' 'TABLE' ifExists? descendants 'RENAME' 'COLUMN'? id 'TO' id
    | 'ALTER' 'VIEW' ifExists? qname 'RENAME' 'COLUMN'? id 'TO' id
    | 'ALTER' 'MATERIALIZED' 'VIEW' ifExists? qname 'RENAME' 'COLUMN'? id 'TO' id
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
    | 'ALTER' 'TYPE' qname 'RENAME' 'ATTRIBUTE' id 'TO' id drop_behavior_?
    ;

alterobjectdependsstmt
    : 'ALTER' 'FUNCTION' funcSignature 'NO'? 'DEPENDS' 'ON' 'EXTENSION' id
    | 'ALTER' 'PROCEDURE' funcSignature 'NO'? 'DEPENDS' 'ON' 'EXTENSION' id
    | 'ALTER' 'ROUTINE' funcSignature 'NO'? 'DEPENDS' 'ON' 'EXTENSION' id
    | 'ALTER' 'TRIGGER' id 'ON' qname 'NO'? 'DEPENDS' 'ON' 'EXTENSION' id
    | 'ALTER' 'MATERIALIZED' 'VIEW' qname 'NO'? 'DEPENDS' 'ON' 'EXTENSION' id
    | 'ALTER' 'INDEX' qname 'NO'? 'DEPENDS' 'ON' 'EXTENSION' id
    ;

alterobjectschemastmt
    : 'ALTER' 'AGGREGATE' aggSignature 'SET' 'SCHEMA' id
    | 'ALTER' 'COLLATION' qname 'SET' 'SCHEMA' id
    | 'ALTER' 'CONVERSION' qname 'SET' 'SCHEMA' id
    | 'ALTER' 'DOMAIN' qname 'SET' 'SCHEMA' id
    | 'ALTER' 'EXTENSION' id 'SET' 'SCHEMA' id


    | 'ALTER' 'FUNCTION' funcSignature 'SET' 'SCHEMA' id
    | 'ALTER' 'PROCEDURE' funcSignature 'SET' 'SCHEMA' id
    | 'ALTER' 'ROUTINE' funcSignature 'SET' 'SCHEMA' id

    | 'ALTER' 'OPERATOR' operator_with_argtypes 'SET' 'SCHEMA' id
    | 'ALTER' 'OPERATOR' ( 'CLASS' | 'FAMILY' ) qname usingID 'SET' 'SCHEMA' id
    | 'ALTER' 'OPERATOR'  qname usingID 'SET' 'SCHEMA' id
    | 'ALTER' 'TABLE' ifExists? descendants 'SET' 'SCHEMA' id
    | 'ALTER' 'STATISTICS' qname 'SET' 'SCHEMA' id
    | 'ALTER' textSearchConfig qname 'SET' 'SCHEMA' id
    | 'ALTER' 'SEQUENCE' ifExists? qname 'SET' 'SCHEMA' id
    | 'ALTER' 'VIEW' ifExists? qname 'SET' 'SCHEMA' id
    | 'ALTER' 'MATERIALIZED' 'VIEW' ifExists? qname 'SET' 'SCHEMA' id
    | 'ALTER' 'TYPE' qname 'SET' 'SCHEMA' id
    | 'ALTER' 'FOREIGN' 'TABLE' ifExists? descendants 'SET' 'SCHEMA' id
    ;

textSearchConfig : 'TEXT' 'SEARCH' ( 'PARSER' | 'DICTIONARY' | 'TEMPLATE' | 'CONFIGURATION' ) ;

alteroperatorstmt
    : 'ALTER' 'OPERATOR' operator_with_argtypes 'SET' '(' operator_def_elem ( ',' operator_def_elem )* ')'
    ;

operator_def_elem
    : id '='
      ( type | keyword | operator | number | string )
    ;

altertypestmt
    : 'ALTER' 'TYPE' qname 'SET' '(' operator_def_elem ( ',' operator_def_elem )* ')'
    ;

alterownerstmt
    : 'ALTER' 'AGGREGATE' aggSignature 'OWNER' 'TO' id

    | 'ALTER' 'FUNCTION' funcSignature 'OWNER' 'TO' id
    | 'ALTER' 'PROCEDURE' funcSignature 'OWNER' 'TO' id
    | 'ALTER' 'ROUTINE' funcSignature 'OWNER' 'TO' id

    | 'ALTER' 'OPERATOR' operator_with_argtypes 'OWNER' 'TO' id

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
    | 'ALTER' 'SUBSCRIPTION' id 'OWNER' 'TO' id
    | 'ALTER' 'TEXT' 'SEARCH' 'DICTIONARY' qname 'OWNER' 'TO' id
    | 'ALTER' 'TEXT' 'SEARCH' 'CONFIGURATION' qname 'OWNER' 'TO' id
    | 'ALTER' 'TYPE' qname 'OWNER' 'TO' id

    | 'ALTER' 'LARGE' 'OBJECT' number 'OWNER' 'TO' id
    | 'ALTER' 'OPERATOR' 'CLASS' qname usingID 'OWNER' 'TO' id
    | 'ALTER' 'OPERATOR' 'FAMILY' qname usingID 'OWNER' 'TO' id
    ;

createpublicationstmt
    : 'CREATE' 'PUBLICATION' id ('FOR' 'TABLE' descendants ( ',' descendants )* | 'FOR' 'ALL' 'TABLES')? withDef?
    ;

alterpublicationstmt
    : 'ALTER' 'PUBLICATION' id 'SET' definition
    | 'ALTER' 'PUBLICATION' id ( 'ADD' | 'SET' | 'DROP' ) 'TABLE' descendants ( ',' descendants )*
    ;

createsubscriptionstmt
    : 'CREATE' 'SUBSCRIPTION' id 'CONNECTION' string 'PUBLICATION' ids withDef?
    ;

altersubscriptionstmt
    : 'ALTER' 'SUBSCRIPTION' id 'SET' definition
    | 'ALTER' 'SUBSCRIPTION' id 'CONNECTION' string
    | 'ALTER' 'SUBSCRIPTION' id 'REFRESH' 'PUBLICATION' withDef?
    | 'ALTER' 'SUBSCRIPTION' id 'SET' 'PUBLICATION' ids withDef?
    | 'ALTER' 'SUBSCRIPTION' id 'ENABLE'
    | 'ALTER' 'SUBSCRIPTION' id 'DISABLE'
    ;

dropsubscriptionstmt
    : 'DROP' 'SUBSCRIPTION' ifExists? id drop_behavior_?
    ;

rulestmt
    : 'CREATE' orReplace? 'RULE' id 'AS'
      'ON' ( 'SELECT' | 'UPDATE' | 'DELETE' | 'INSERT' )
      'TO' qname where? 'DO' ( 'INSTEAD' | 'ALSO' )?
      ( 'NOTHING' | ruleactionstmt | '(' ruleactionstmt? ( SEMI ruleactionstmt? )* ')' )
    ;

ruleactionstmt
    : select
    | insert
    | update
    | delete
    | notifystmt
    ;

notifystmt
    : 'NOTIFY' id ( ',' string )? ;

listenstmt
    : 'LISTEN' id ;

unlistenstmt
    : 'UNLISTEN' ( id | '*' ) ;

transactionstmt
    : ( 'ABORT' | | 'COMMIT' | 'END' | 'ROLLBACK' ) transaction_? ( 'AND' 'NO'? 'CHAIN' )?
    | ( 'BEGIN' transaction_? | 'START' 'TRANSACTION' ) ( transactionMode ( ',' transactionMode )* )?
    | 'RELEASE'? 'SAVEPOINT' id
    | 'RELEASE' id
    | 'ROLLBACK' transaction_? 'TO' 'SAVEPOINT'? id
    | 'PREPARE' 'TRANSACTION' string
    | 'COMMIT' 'PREPARED' string
    | 'ROLLBACK' 'PREPARED' string
    ;

transaction_
    : 'WORK'
    | 'TRANSACTION'
    ;

viewstmt
    : 'CREATE' ( 'OR' 'REPLACE' )? scope? (
        'VIEW' qname columns? withDef?
        | 'RECURSIVE' 'VIEW' qname columns withDef?
    ) 'AS' select ('WITH' ( 'CASCADED' | 'LOCAL' )? 'CHECK' 'OPTION')?
    ;

loadstmt
    : 'LOAD' string
    ;

createdbstmt
    : 'CREATE' 'DATABASE' id 'WITH'? createdb_opt_item*
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

alterdatabasestmt
    : 'ALTER' 'DATABASE' id ( 'WITH'? createdb_opt_item* | 'SET' 'TABLESPACE' id )
    ;

alterdatabasesetstmt
    : 'ALTER' 'DATABASE' id setresetclause
    ;

dropdbstmt
    : 'DROP' 'DATABASE' ifExists? id ( 'WITH'? '(' 'FORCE' ( ',' 'FORCE' )* ')' )?
    ;

altercollationstmt
    : 'ALTER' 'COLLATION' qname 'REFRESH' 'VERSION'
    ;

altersystemstmt
    : 'ALTER' 'SYSTEM' ( 'SET' | 'RESET' ) qname ( 'TO' | '=' ) ( 'DEFAULT' | value ( ',' value )* )
    ;

createdomainstmt
    : 'CREATE' 'DOMAIN' qname 'AS'? type colconstraint*
    ;

alterdomainstmt
  : 'ALTER' 'DOMAIN' qname
    ( ( 'SET' 'DEFAULT' term | 'DROP' 'DEFAULT' )
    | 'DROP' 'NOT' 'NULL'
    | 'SET' 'NOT' 'NULL'
    | 'ADD' tableconstraint
    | 'DROP' 'CONSTRAINT' ifExists? id drop_behavior_?
    | 'VALIDATE' 'CONSTRAINT' id
    )
  ;

altertsdictionarystmt
    : 'ALTER' 'TEXT' 'SEARCH' 'DICTIONARY' qname definition
    ;

altertsconfigurationstmt
    : 'ALTER' 'TEXT' 'SEARCH' 'CONFIGURATION' qname 'ADD' 'MAPPING' 'FOR' ids 'WITH' qnames
    | 'ALTER' 'TEXT' 'SEARCH' 'CONFIGURATION' qname 'ALTER' 'MAPPING' ( 'FOR' ids )? ( 'REPLACE' qname )? 'WITH' qnames
    | 'ALTER' 'TEXT' 'SEARCH' 'CONFIGURATION' qname 'DROP' 'MAPPING' ifExists? 'FOR' ids
    ;

createconversionstmt
    : 'CREATE' 'DEFAULT'? 'CONVERSION' qname 'FOR' string 'TO' string 'FROM' qname
    ;

clusterstmt
    : 'CLUSTER' 'VERBOSE'? ( qname usingID? | id 'ON' qname )?
    ;

vacuumstmt
    : 'VACUUM' 'FULL'? 'FREEZE'? 'VERBOSE'? ANALYZE? (vacuum_relation ( ',' vacuum_relation )* )?
    | 'VACUUM' '(' optionElems ')' ( vacuum_relation ( ',' vacuum_relation )*)?
    ;

analyzestmt
    : ANALYZE 'VERBOSE'? ( vacuum_relation ( ',' vacuum_relation )* )?
    | ANALYZE '(' optionElems ')' ( vacuum_relation ( ',' vacuum_relation )* )?
    ;


vacuum_relation
    : qname columns?
    ;

explainstmt
    : 'EXPLAIN' ANALYZE? 'VERBOSE'? explainablestmt
    | 'EXPLAIN' '(' optionElems ')' explainablestmt
    ;

explainablestmt
    : select
    | insert
    | update
    | delete
    | declarecursorstmt
    | createTableAs
    | creatematviewstmt
    | refreshmatviewstmt
    | executePrepared
    ;

optionElems
    : option_elem ( ',' option_elem )*
    ;

option_elem
    : id (name | number)?
    ;

preparestmt
    : 'PREPARE' id ( '(' type ( ',' type )* ')' )? 'AS'
      (select | insert | update | delete)
    ;


deallocatestmt
    : 'DEALLOCATE' 'PREPARE'? ( id |  'ALL' )
    ;

insert
    : with? 'INSERT' 'INTO' qname ( 'AS' id )?

      ( ( '(' qnames ')' )? ( 'OVERRIDING' ('USER' | 'SYSTEM') 'VALUE' )? select
      | 'DEFAULT' 'VALUES'
      )

      ( 'ON' 'CONFLICT'
        ( '(' index_params ')' where? | 'ON' 'CONSTRAINT' id )?
        'DO' ( 'UPDATE' 'SET' set_clause_list where? | 'NOTHING' )
      )?
      returning?
    ;

returning
  : 'RETURNING' ( item ( ',' item )* )? ;

// https://www.postgresql.org/docs/current/sql-merge.html
mergestmt
    : 'MERGE' 'INTO'? qname aliasColumns? 'USING' ( '(' select ')' | qname ) aliasColumns? 'ON' term
      ( merge_insert_clause merge_update_clause?
      | merge_update_clause merge_insert_clause?
      )
      ('WHEN' 'MATCHED' 'THEN'? 'DELETE')?
    ;

merge_insert_clause
  : 'WHEN' 'NOT' 'MATCHED' ( 'AND' term )? 'THEN'? 'INSERT' ( '(' qnames ')' )? values
  ;

merge_update_clause
  : 'WHEN' 'MATCHED' ( 'AND' term )? 'THEN'? 'UPDATE' 'SET' set_clause_list
  ;

delete
    : with? 'DELETE' 'FROM' descendants alias? ( 'USING' tables ( ',' tables )* )? whereCurrent? returning?
    ;

lockstmt
    : 'LOCK' 'TABLE'? descendants ( ',' descendants )* ('IN' lock_type 'MODE')? 'NOWAIT'?
    ;

lock_type
    : 'ACCESS' ( 'SHARE' | 'EXCLUSIVE' )
    | 'ROW' ( 'SHARE' | 'EXCLUSIVE' )
    | 'SHARE' ( 'UPDATE' 'EXCLUSIVE' | 'ROW' 'EXCLUSIVE' )?
    | 'EXCLUSIVE'
    ;

update
    : with? 'UPDATE' descendants alias? 'SET' set_clause_list from? whereCurrent? returning?
    ;

set_clause_list
    : set_clause ( ',' set_clause )*
    ;

set_clause
    : qname '=' term
    | '(' qnames ')' '=' term
    ;

declarecursorstmt
    : 'DECLARE' id ( 'NO'? 'SCROLL' | 'SCROLL' | 'BINARY' | 'INSENSITIVE' )* 'CURSOR' ( withers 'HOLD' )? 'FOR' select
    ;

select
  : with? selectCombo orderBy? ( limit | fetch | offset | locking )* ;

    limit
      : 'LIMIT' term ;

    fetch
      : 'FETCH' firstNext term? rowRows ( 'ONLY' | 'WITH' 'TIES' ) ;

    offset
      : 'OFFSET' term rowRows? ;

    locking
      : 'FOR' ( ( 'NO' 'KEY' )? 'UPDATE' | 'KEY'? 'SHARE' ) ( 'OF' qnames )? ( 'NOWAIT' | 'SKIP' 'LOCKED' )? ;

selectCombo
    : selectCore ( ( 'UNION' | 'EXCEPT' | 'INTERSECT' ) allDistinct? selectCore )* ;

// TODO will this workaround left-recurse?
//selectCombo
//    : selectCombo ( ( 'UNION' | 'EXCEPT' | 'INTERSECT' ) allDistinct? ) selectCombo
//    | simpleCore
//    ;

selectCore
  : 'SELECT' quantifier? ( item ( ',' item )* ','? )?
    ( 'INTO' ( 'TEMPORARY' | 'TEMP' | 'UNLOGGED' )? 'TABLE'?  qname )?
    from? where? groupBy? having? window?
  | values
  | 'TABLE' descendants
  | '(' select ')'
  ;

    item
      : term alias? | '*' ;

with
  : 'WITH' 'RECURSIVE'? cte ( ',' cte )* ;

cte
  : id columns? 'AS' ( 'NOT'? 'MATERIALIZED' )? '(' ( select | insert | update | delete ) ')' ;

allDistinct
  : 'ALL' | 'DISTINCT' ;

quantifier
  : 'ALL' | 'DISTINCT' ( 'ON' '(' terms ')' )? ;

orderBy
  : 'ORDER' 'BY' sortby ( ',' sortby  )* ;

sortby
  : term ( 'USING' operator | sortDir? ) nullsOrder? ;

rowRows
    : 'ROW' | 'ROWS' ;

firstNext
    : 'FIRST' | 'NEXT' ;

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

values
  : 'VALUES' '(' terms ')' ( ',' '(' terms ')' )* ;

from
  : 'FROM' tables ( ',' tables )* ;

tables
  : tables joinType? 'JOIN' tables ( 'USING' columns | 'ON' term )
  | tables 'NATURAL' joinType? 'JOIN' tables
  | tables 'CROSS' 'JOIN' tables
  | 'LATERAL'? tableFunc
  | 'LATERAL'? '(' select ')' aliasColumns?
  | 'LATERAL'? function ( 'WITH' 'ORDINALITY' )? aliasColumns?
  | 'LATERAL'? 'ROWS' 'FROM' '(' tableFunc? ( ',' tableFunc? )* ')'  ( 'WITH' 'ORDINALITY' )? aliasColumns?
  | 'LATERAL'? xmltable aliasColumns?
  | '(' tables ')' aliasColumns?
  | descendants aliasColumns? ( 'TABLESAMPLE' qname '(' terms ')' ( 'REPEATABLE' '(' term ')' )? )?
  ;

joinType
  : 'INNER'
  | ( 'FULL' | 'LEFT' | 'RIGHT'  ) 'OUTER'?
  ;

genericFunction
  : qname '('
    ( ( args ( ',' 'VARIADIC' arg )?
      | 'VARIADIC' arg
      | allDistinct args
      ) orderBy?
    | '*'
    )?
    ')'
  ;

// TODO review func_expr_windowless
tableFunc
  : function ( ( 'AS' | 'AS'? id ) '(' id type ( ',' id type )* ')' )? ;

aliasColumns
  : 'AS'? id columns? ;

descendants
  : qname '*'?
  | 'ONLY' qname
  ;

alias
  : 'AS'? name ;

where
  : 'WHERE' term ;

whereCurrent
  : 'WHERE' ( 'CURRENT' 'OF' id | term ) ;

tablefuncelement
    : id type collate?
    ;

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

type
  : 'SETOF'? simpletypename
    (  ( '[' DECIMAL? ']' )*
    | 'ARRAY' ( '[' DECIMAL ']' )?
    )
  ;

simpletypename
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
  : '(' ( DECIMAL | simpletypename ) ( ',' ( signedDecimal | simpletypename ) )? ')'
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

//precendence accroding to Table 4.2. Operator Precedence ( highest to lowest)

//https://www.postgresql.org/docs/12/sql-syntax-lexical.html#SQL-PRECEDENCE

/*
original version of a_expr, for info
 a_expr: c_expr
        //::	left	PostgreSQL-style typecast
       | a_expr '::' typename -- 1
       | a_expr 'COLLATE' any_name -- 2
       | a_expr 'AT' 'TIME' 'ZONE' a_expr-- 3
       //right	unary plus, unary minus
       | ( '+'| '-') a_expr -- 4
        //left	exponentiation
       | a_expr '^' a_expr -- 5
        //left	multiplication, division, modulo
       | a_expr ( '*' | '/' | '%') a_expr -- 6
        //left	addition, subtraction
       | a_expr ( '+' | '-') a_expr -- 7
        //left	all other native and user-defined operators
       | a_expr qual_op a_expr -- 8
       | qual_op a_expr -- 9
        //range containment, set membership, string matching 'BETWEEN' 'IN' 'LIKE' 'ILIKE' 'SIMILAR'
       | a_expr 'NOT'? ( LIKE|ILIKE|SIMILAR TO|( 'BETWEEN' 'SYMMETRIC'?)) a_expr opt_escape -- 10
        //< > = <= >= <>	 	comparison operators
       | a_expr ( '<' | '>' | '=' | '<=' | '>=' | '<>') a_expr -- 11
       //IS 'ISNULL' 'NOTNULL'	 	'IS' TRUE, 'IS' FALSE, 'IS' NULL, 'IS' 'DISTINCT' FROM, etc
       | a_expr 'IS' 'NOT'?
            (
                'NULL'
                |TRUE_P
                |FALSE_P
                |UNKNOWN
                |DISTINCT 'FROM' a_expr
                |OF '(' type_list ')'
                |DOCUMENT_P
                |unicode_normal_form? 'NORMALIZED'
            ) -- 12
       | a_expr ( ISNULL|NOTNULL) -- 13
       | row 'OVERLAPS' row -- 14
       //NOT	right	logical negation
       | 'NOT' a_expr -- 15
        //AND	left	logical conjunction
       | a_expr 'AND' a_expr -- 16
        //OR	left	logical disjunction
       | a_expr 'OR' a_expr -- 17
       | a_expr ( '<<'|'>>') a_expr -- 18
       | a_expr qual_op -- 19
       | a_expr 'NOT'? 'IN' in_expr -- 20
       | a_expr subquery_Op sub_type ( select_with_parens|'(' a_expr ')') -- 21
       | 'UNIQUE' select_with_parens -- 22
       | 'DEFAULT' -- 23
;
*/

terms
  : term ( ',' term )* ;

term
  : term 'OR' term
  | term 'AND' term
  | subterm
  ;

// TODO will these instances of OPERATOR need to be qualitified?
subterm
  : subterm ( '::' type )+
  | subterm collate
  | subterm 'AT' 'TIME' 'ZONE' term
  // unary left
  | ('NOT' | '-' | '+' | OPERATOR ) subterm
  // unary right
  | <assoc=right> subterm OPERATOR
  | <assoc=right> subterm '^' subterm
  | subterm ( '<<' | '>>' | '&' | '|' ) subterm
  | subterm ( '*' | '/' | '%' ) subterm
  | subterm ( '+' | '-' ) subterm
  | subterm OPERATOR subterm?

  // workaround for ANTLR's left recursion pattern recogniizer not seeing this
  | subterm 'NOT'? ( 'LIKE' | 'ILIKE' | 'SIMILAR' 'TO' ) subterm 'ESCAPE' subterm
  | subterm 'NOT'? ( 'LIKE' | 'ILIKE' | 'SIMILAR' 'TO' ) subterm

  | subterm ( '<' | '>' | '=' | '<=' | '>=' | '<>' ) subterm
  | subterm ( 'ISNULL' | 'NOTNULL' )
  | subterm 'IS' 'NOT'? ('DISTINCT' 'FROM')? subterm
  | subterm 'IS' 'NOT'? 'OF' '(' type ( ',' type )* ')'
  | subterm 'IS' 'NOT'? unicode_normal_form? 'NORMALIZED'
  | subterm 'NOT'? 'IN' '(' ( select |  terms ) ')'
  | subterm 'NOT'? 'BETWEEN' 'SYMMETRIC'? subterm 'AND' subterm
  | 'CASE' term? when+ ( 'ELSE' term )? 'END'
  | function ( 'WITHIN' 'GROUP' '(' orderBy ')' )? ( 'FILTER' '(' where ')' )? ('OVER' ( window_specification | id ))?
  | ( 'ANY' | 'SOME' | 'ALL' )? '(' ( select | term ) ')' index*
  // TODO do these "nestings" also need index suffix?
  | 'EXISTS' '(' select ')'
  | 'ARRAY' ( '(' select ')' | array )
  | 'GROUPING' '(' terms ')'
  | 'UNIQUE' '(' select ')'
  | row
  | row 'OVERLAPS' row
  | atom
  ;

atom
  : PARAM index*
  | integer
  | FLOAT
  | string
  | interval
  | type string
  | qname
  ;

function
    : genericFunction
    | 'COLLATION' 'FOR' '(' term ')'

//    | 'CURRENT_DATE'
////    | 'CURRENT_TIME' ( '(' integer ')' )?
////    | 'CURRENT_TIMESTAMP' ( '(' integer ')' )?
////    | 'LOCALTIME' ( '(' integer ')' )?
////    | 'LOCALTIMESTAMP' ( '(' integer ')' )?
//
//    | 'CURRENT_ROLE'
//    | 'CURRENT_USER'
//    | 'SESSION_USER'
//    | 'SYSTEM_USER'
//    | 'USER'
//
//    | 'CURRENT_CATALOG'
//    | 'CURRENT_SCHEMA'

    | 'CAST' '(' term 'AS' type ')'
//    | 'EXTRACT' '(' (extract_arg 'FROM' term)? ')'
    | 'EXTRACT' '(' ( name 'FROM' term )? ')'
    | 'NORMALIZE' '(' term ( ',' unicode_normal_form )? ')'
    | 'OVERLAY' '(' ( overlay_list | args? ) ')'
    | 'POSITION' '(' ( term 'IN' term )? ')'
    | 'SUBSTRING' '(' ( substr_list | args? ) ')'
    | 'TREAT' '(' term 'AS' type ')'
    | 'TRIM' '(' ( 'BOTH' | 'LEADING' | 'TRAILING' )? ( term? 'FROM' )? terms ')'
//    | 'NULLIF' '(' term ',' term ')'
//    | 'COALESCE' '(' terms ')'
//    | 'GREATEST' '(' terms ')'
//    | 'LEAST' '(' terms ')'
//
//    | 'XMLCONCAT' '(' terms ')'
    | 'XMLELEMENT' '(' 'NAME' id ( ',' ( 'XMLATTRIBUTES' '(' xmlAttrib ( ',' xmlAttrib )* ')' | terms ) )? ')'
    | 'XMLEXISTS' '(' xmlPassings ')'
    | 'XMLFOREST' '(' xmlAttrib ( ',' xmlAttrib )* ')'
    | 'XMLPARSE' '(' root term ('PRESERVE' | 'STRIP') 'WHITESPACE'? ')'
    | 'XMLPI' '(' 'NAME' id ( ',' term )? ')'
    | 'XMLROOT' '(' 'XML' term ',' 'VERSION' (term |  'NO' 'VALUE') ( ',' 'STANDALONE' ( 'YES' | 'NO' 'VALUE'? ) )? ')'
    | 'XMLSERIALIZE' '(' root term 'AS' simpletypename ')'

    | 'JSON' '(' jsonValue json_key_uniqueness_constraint? ')'
    | 'JSON_ARRAY' '(' ( jsonValue (',' jsonValue)* json_object_constructor_null_clause? jsonReturning? | '(' select ')' jsonFormat? jsonReturning? | jsonReturning? ) ')'
    | 'JSON_EXISTS' '(' jsonValue ',' term jsonPassing? jsonOnError? ')'
    | 'JSON_OBJECT' '(' ( args | jsonPair (',' jsonPair)* json_object_constructor_null_clause? json_key_uniqueness_constraint? jsonReturning? | jsonReturning? ) ')'
    | 'JSON_QUERY' '(' jsonValue ',' term jsonPassing? jsonReturning? json_wrapper_behavior? ( 'KEEP' | 'OMIT' ) 'QUOTES' ( 'ON' 'SCALAR' 'STRING' )?? jsonOnEmpty? jsonOnError? ')'
//    | 'JSON_SCALAR' '(' term ')'
    | 'JSON_SERIALIZE' '(' jsonValue jsonReturning? ')'
    | 'JSON_VALUE' '(' jsonValue ',' term jsonPassing? jsonReturning? jsonOnEmpty? jsonOnError? ')'
//    | 'MERGE_ACTION' '(' ')'
    ;

xmlAttrib
  : term ( 'AS' id )? ;

root
  : 'DOCUMENT' | 'CONTENT' ;

xmlPassings
    : term 'PASSING' ( 'BY' ( 'REF' | 'VALUE' ) )? term ( 'BY' ( 'REF' | 'VALUE' ) )?
    ;

window
    : 'WINDOW' window_definition ( ',' window_definition )* ;

window_definition
    : id 'AS' window_specification ;

window_specification
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

row
  : 'ROW' '(' terms? ')'
  | '(' terms ',' term ')'
  ;


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

args
  : arg ( ',' arg )* ;

arg
  :  ( id ( ':=' | '=>' ) )? term ;

array
    : '[' ( terms | array ( ',' array )* )? ']' ;

unicode_normal_form
    : 'NFC'
    | 'NFD'
    | 'NFKC'
    | 'NFKD'
    ;

overlay_list
    : term 'PLACING' term 'FROM' term ( 'FOR' term )? ;

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
  : ( 'WITHOUT' | 'WITH' ( 'UNCONDITIONAL' | 'CONDITIONAL' )? ) 'ARRAY'? 'WRAPPER'
  ;

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

jsonFormat : 'FORMAT_LA' 'JSON' ( 'ENCODING' id )? ;

json_key_uniqueness_constraint
  : withers 'UNIQUE' 'KEYS'? ;

jsonPair
  : atom 'VALUE' jsonValue
  | term ':' jsonValue
  ;

json_object_constructor_null_clause
  : ( 'NULL' | 'ABSENT' ) 'ON' 'NULL' ;

qnames
  : qname ( ',' qname )* ;

qname
  : name index* ;

columns
  : '(' id ( ',' id )* ')' ;

ids
  : id ( ',' id )* ;

integer
  : DECIMAL
  | BINARY
  | OCTAL
  | HEXIDECIMAL
  ;

string
  : StringConstant
  | UnicodeEscapeStringConstant ( 'UESCAPE' StringConstant )?
  | BinaryStringConstant
  | HexadecimalStringConstant
  ;

signedDecimal
  : ('+' | '-')? DECIMAL ;

signedFloat
  : ('+' | '-')? FLOAT ;

id
  : Identifier ( 'UESCAPE' StringConstant )?
  | QuotedIdentifier
  | UnicodeQuotedIdentifier
  | PLSQLVARIABLENAME
  | keyword
  ;

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
//  | 'AS'
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
  | 'SELECT'
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
  : '$' [0-9]+ ;

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
  | (  [<>=~!@#%^&|`?] | '*' )+
  ;

Identifier
  : [A-Z_\u007F-\uFFFF] [A-Z_$0-9\u007F-\uFFFF]* ;

QuotedIdentifier
  : '"' ('""' | ~ [\u0000"])* '"' ;

UnicodeQuotedIdentifier
  : 'U' '&' QuotedIdentifier ;

StringConstant
  : '\'' ('\'\'' | ~ '\'')* '\''
  // TODO nested dollar quoted strings
  | TAG .*? TAG
  ;

UnicodeEscapeStringConstant
  : 'U' '&' '\'' ( '\'\'' | ~ '\'' )* '\''
  // https://www.postgresql.org/docs/current/sql-syntax-lexical.html#SQL-SYNTAX-STRINGS-ESCAPE
  | 'E' '\'' ( '\'\'' | ESCAPE_SEQUENCE | ~( '\'' | '\\' ) )* '\''
  ;

// FIXME add octal, hex, unicode sequences
fragment ESCAPE_SEQUENCE
  : '\\' ('\\' | '\'' | 't' | 'n' | 'r' | 'b' | 'f' )
  ;

BinaryStringConstant
  : 'B' '\'' [01]* '\'' ;

HexadecimalStringConstant
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
  :
    DIGITS '.' DIGITS?
    (
        'E' [+-]? DIGITS
    )?
    | '.' DIGITS ('E' [+-]? DIGITS)?
    | DIGITS 'E' [+-]? DIGITS
;

PLSQLVARIABLENAME
  : ':' [A-Z_\u007F-\uFFFF] [A-Z_$0-9\u007F-\uFFFF]* ;

LineComment
  : '--' ~ [\r\n]* -> channel (HIDDEN) ;

BlockComment
  : ('/*' ('/'* BlockComment | ~ [/*] | '/'+ ~ [/*] | '*'+ ~ [/*])* '*'* '*/') -> channel (HIDDEN) ;

SEMI
  : ';'   | '\\' ';' ;

META
  : '\\' ~[;]? ~[\r\n\\]* -> type( SEMI ) ;

Whitespace
  : [ \t]+ -> channel( HIDDEN ) ;

Newline
  : ('\r' '\n'? | '\n') -> channel( HIDDEN ) ;

UNKNOWN
  : . ;

fragment DIGITS
  : [0-9]+ ;

fragment TAG
  : '$' ( [A-Z_\u007F-\uFFFF] [A-Z_0-9\u007F-\uFFFF]* )? '$' ;
