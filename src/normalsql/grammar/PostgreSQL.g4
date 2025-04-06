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
    | createtrigstmt
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
    | execute
    | explainstmt
    | fetchstmt
    | grantstmt
    | grantrolestmt
    | importforeignschemastmt
    | indexstmt
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
    | variableshowstmt
    | viewstmt
    ;

callstmt
    : 'CALL' func_application
    ;

createrolestmt
    : 'CREATE' 'ROLE' id 'WITH'? createoptroleelem*
    ;

alteroptroleelem
    : 'PASSWORD' ( string | 'NULL' )
    | ( 'ENCRYPTED' | 'UNENCRYPTED' ) 'PASSWORD' string
    | 'INHERIT'
    | 'CONNECTION' 'LIMIT' signedDecimal
    | 'VALID' 'UNTIL' string
    | 'USER' ids
    | identifier
    ;

createoptroleelem
    : alteroptroleelem
    | 'SYSID' integer
    | 'ADMIN' ids
    | 'ROLE' ids
    | 'IN' ( 'ROLE' | 'GROUP' ) ids
    ;

createuserstmt
    : 'CREATE' 'USER' id 'WITH'? createoptroleelem*
    ;

alterrolestmt
    : 'ALTER' ( 'ROLE' | 'USER' ) id 'WITH'? alteroptroleelem*
    ;

alterrolesetstmt
    : 'ALTER' ( 'ROLE' | 'USER' ) 'ALL'? id ('IN' 'DATABASE' id)? setresetclause
    ;

droprolestmt
    : 'DROP' ( 'ROLE' | 'USER' | 'GROUP' ) ifExists? ids
    ;

creategroupstmt
    : 'CREATE' 'GROUP' id 'WITH'? createoptroleelem*
    ;

altergroupstmt
    : 'ALTER' 'GROUP' id add_drop 'USER' ids
    ;

add_drop
    : 'ADD'
    | 'DROP'
    ;

createschemastmt
    : 'CREATE' 'SCHEMA' ifNotExists? ( id? 'AUTHORIZATION' id | id )
      ( createTable
      | indexstmt
      | createseqstmt
      | createtrigstmt
      | grantstmt
      | viewstmt
      )*
    ;

variablesetstmt
    : 'SET' ( 'LOCAL' | 'SESSION' )? set_rest
    ;

set_rest
    : ( 'SESSION' 'CHARACTERISTICS' 'AS' )? 'TRANSACTION' transaction_mode_list
    | set_rest_more
    ;

 transaction_mode_list
     : transaction_mode_item ( ','? transaction_mode_item )*
     ;

 transaction_mode_item
     : 'ISOLATION' 'LEVEL' ( 'READ' ( 'UNCOMMITTED' | 'COMMITTED' ) | 'REPEATABLE' 'READ' | 'SERIALIZABLE' )
     | 'READ' ( 'ONLY' |  'WRITE' )
     | 'NOT'? 'DEFERRABLE'
     ;


set_rest_more
  : qname ( 'TO' | '=' ) ( var_list | 'DEFAULT' )
  | id 'FROM' 'CURRENT'
  | 'TIME' 'ZONE' zone_value
  | 'CATALOG' string
  | 'SCHEMA' string
  | 'NAMES' (string | 'DEFAULT')?
  | 'ROLE' name
  | 'SESSION' 'AUTHORIZATION' name
  | 'XML' 'OPTION' document_or_content
  | 'TRANSACTION' 'SNAPSHOT' string
  ;


var_list
    : var_value ( ',' var_value )*
    ;

var_value
    : boolean_or_string_
    | number
    ;

boolean_or_string_
    : 'TRUE'
    | 'FALSE'
    | 'ON'
    | name
    ;

zone_value
    : string
    | identifier
    | 'INTERVAL' string timeUnit?
    | 'INTERVAL' '(' integer ')' string
    | number
    | 'DEFAULT'
    | 'LOCAL'
    ;

name
  : id | string ;

variableresetstmt
  : 'RESET'
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

variableshowstmt
    : 'SHOW'
       ( id | 'TIME' 'ZONE' | 'TRANSACTION' 'ISOLATION' 'LEVEL' | 'SESSION' 'AUTHORIZATION' | 'ALL' )
    ;

constraintssetstmt
    : 'SET' 'CONSTRAINTS' ( 'ALL' | qnames ) ('DEFERRED' | 'IMMEDIATE')
    ;

discardstmt
    : 'DISCARD' ( 'ALL' | 'TEMP' | 'TEMPORARY' | 'PLANS' | 'SEQUENCES' )
    ;

altertablestmt
    : 'ALTER' 'TABLE' ifExists? relation_expr ( alter_table_cmds | ('ATTACH' 'PARTITION' qname partitionboundspec | 'DETACH' 'PARTITION' qname ) )
    | 'ALTER' 'INDEX' ifExists? qname ( alter_table_cmds | 'ATTACH' 'PARTITION' qname )
    | 'ALTER' ( 'INDEX' | 'TABLE' | 'MATERIALIZED' 'VIEW' ) 'ALL' 'IN' 'TABLESPACE' id ( 'OWNED' 'BY' ids )? 'SET' 'TABLESPACE' id 'NOWAIT'?
    | 'ALTER' 'SEQUENCE' ifExists? qname alter_table_cmds
    | 'ALTER' 'VIEW' ifExists? qname alter_table_cmds
    | 'ALTER' 'MATERIALIZED' 'VIEW' ifExists? qname alter_table_cmds
    | 'ALTER' 'FOREIGN' 'TABLE' ifExists? relation_expr alter_table_cmds
    ;

alter_table_cmds
    : alter_table_cmd ( ',' alter_table_cmd )*
    ;

alter_table_cmd
    : 'ADD' tableconstraint
          | 'ADD' ifNotExists? columnDef
    | 'ADD' 'COLUMN' ifNotExists? columnDef
    | 'ALTER' 'COLUMN'? id ('SET' 'DEFAULT' a_expr
    | 'DROP' 'DEFAULT')
    | 'ALTER' 'COLUMN'? id 'DROP' 'NOT' 'NULL'
    | 'ALTER' 'COLUMN'? id 'SET' 'NOT' 'NULL'
    | 'ALTER' 'COLUMN'? id 'DROP' 'EXPRESSION' ifExists?
    | 'ALTER' 'COLUMN'? id 'SET' 'STATISTICS' signedDecimal
    | 'ALTER' 'COLUMN'? integer 'SET' 'STATISTICS' signedDecimal
    | 'ALTER' 'COLUMN'? id 'SET' definition
    | 'ALTER' 'COLUMN'? id 'RESET' definition
    | 'ALTER' 'COLUMN'? id 'SET' 'STORAGE' id
    | 'ALTER' 'COLUMN'? id 'ADD' 'GENERATED' generated_when 'AS' 'IDENTITY' ('(' seqoptelem+ ')')?
    | 'ALTER' 'COLUMN'? id alter_identity_column_option+
    | 'ALTER' 'COLUMN'? id 'DROP' 'IDENTITY' ifExists?
    | 'DROP' 'COLUMN'? ifExists? id drop_behavior_?
    | 'ALTER' 'COLUMN'? id ('SET' 'DATA')? 'TYPE' typename collate_clause_? ('USING' a_expr)?
    | 'ALTER' 'COLUMN'? id alter_generic_options
    | 'ALTER' 'CONSTRAINT' id constraintattributespec*
    | 'VALIDATE' 'CONSTRAINT' id
    | 'DROP' 'CONSTRAINT' ifExists? id drop_behavior_?
    | 'SET' 'WITHOUT' 'OIDS'
    | 'CLUSTER' 'ON' id
    | 'SET' 'WITHOUT' 'CLUSTER'
    | 'SET' 'LOGGED'
    | 'SET' 'UNLOGGED'
    | ( 'DISABLE' | 'ENABLE' ) ( 'ALWAYS' | 'REPLICA' )? 'TRIGGER' id
    | ( 'DISABLE' | 'ENABLE' ) 'TRIGGER' ( 'ALL' | 'USER' )

    | 'ENABLE' 'RULE' id
    | 'ENABLE' 'ALWAYS' 'RULE' id
    | 'ENABLE' 'REPLICA' 'RULE' id
    | 'DISABLE' 'RULE' id
    | 'INHERIT' qname
    | 'NO' 'INHERIT' qname
    | 'OF' qname
    | 'NOT' 'OF'
    | 'OWNER' 'TO' id
    | 'SET' 'TABLESPACE' id
    | 'SET' definition
    | 'RESET' definition
    | 'REPLICA' 'IDENTITY' ('NOTHING' | 'FULL' | 'DEFAULT' | 'USING' 'INDEX' id)
    | ( 'DISABLE' | 'ENABLE' | 'NO'? 'FORCE' ) 'ROW' 'LEVEL' 'SECURITY'
    | alter_generic_options
    ;

drop_behavior_
    : 'CASCADE'
    | 'RESTRICT'
    ;

collate_clause_
    : 'COLLATE' qname
    ;

alter_identity_column_option
    : 'RESTART' ( 'WITH'? number )?
    | 'SET' ( seqoptelem | 'GENERATED' generated_when )
    ;

partitionboundspec
    : 'FOR' 'VALUES' 'WITH' '(' hash_partbound_elem ( ',' hash_partbound_elem )* ')'
    | 'FOR' 'VALUES' 'IN' '(' expr_list ')'
    | 'FOR' 'VALUES' 'FROM' '(' expr_list ')' 'TO' '(' expr_list ')'
    | 'DEFAULT'
    ;

hash_partbound_elem
    : id integer
    ;

altercompositetypestmt
    : 'ALTER' 'TYPE' qname alter_type_cmd ( ',' alter_type_cmd )*
    ;

alter_type_cmd
    : 'ADD' 'ATTRIBUTE' tablefuncelement drop_behavior_?
    | 'DROP' 'ATTRIBUTE' ifExists? id drop_behavior_?
    | 'ALTER' 'ATTRIBUTE' id ('SET' 'DATA')? 'TYPE' typename collate_clause_? drop_behavior_?
    ;

closeportalstmt
    : 'CLOSE' ( id | 'ALL' )
    ;

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
      ( boolean_or_string_
      | number
      | '*'
      | '(' copy_generic_opt_arg_list ')'
      )?
    ;

copy_generic_opt_arg_list
    : boolean_or_string_ ( ',' boolean_or_string_ )*
    ;

createTable
    : 'CREATE' opttemp? 'TABLE' ifNotExists? qname 
      ( '(' ( tableelement ( ',' tableelement )* )? ')' inherit?
      | typedTableElements
      | 'PARTITION' typedTableElements partitionboundspec
      )
      ('PARTITION' 'BY' id '(' part_elem ( ',' part_elem )* ')')?
      fixme
      ;

createTableAs
    : 'CREATE' opttemp? 'TABLE' ifNotExists? qname columns?
      fixme 'AS' ( select | execute )
      with_data_?
    ;

   fixme
     : usingID?
       (withDef | 'WITHOUT' 'OIDS')?
       ( 'ON' 'COMMIT' ( 'DROP' | 'DELETE' 'ROWS' | 'PRESERVE' 'ROWS' ))?
       tablespaceID?
     ;

createForeignTable
    : 'CREATE' 'FOREIGN' 'TABLE' ifNotExists? qname
      ( '(' ( tableelement ( ',' tableelement )* )? ')' inherit?
      | 'PARTITION' typedTableElements partitionboundspec
      )
      'SERVER' id create_generic_options?
    ;

execute
    : 'EXECUTE' id ('(' expr_list ')')?
    ;

opttemp
    : ( 'LOCAL' | 'GLOBAL' )? ( 'TEMPORARY' | 'TEMP' )
    | 'UNLOGGED'
    ;

usingID : 'USING' id ;

typedTableElements
    : 'OF' qname ( '(' typedtableelement ( ',' typedtableelement )* ')' )?
    ;

    typedtableelement
        : id ( 'WITH' 'OPTIONS' )? colconstraint*
        | tableconstraint
        ;


tableelement
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
    : id typename create_generic_options? colconstraint*
    ;

colconstraint
    : ( 'CONSTRAINT' id )? colconstraintelem
//    | colconstraintelem
    | ('NOT'? 'DEFERRABLE' | 'INITIALLY' ( 'DEFERRED' | 'IMMEDIATE' ))
    | 'COLLATE' qname
    ;

colconstraintelem
    : 'NOT'? 'NULL'
    | 'UNIQUE' withDef? usingIndexTablespaceID?
    | 'PRIMARY' 'KEY' withDef? usingIndexTablespaceID?
    | 'CHECK' '(' a_expr ')' ('NO' 'INHERIT')?
    | 'DEFAULT' b_expr
    | 'GENERATED' generated_when 'AS' ( 'IDENTITY' ('(' seqoptelem+ ')')? | '(' a_expr ')' 'STORED' )
    | 'REFERENCES' qname columns? key_match? key_delete*
    ;

generated_when
    : 'ALWAYS'
    | 'BY' 'DEFAULT'
    ;

tableconstraint
    : ( 'CONSTRAINT' id )?
      ( 'CHECK' '(' a_expr ')' constraintattributespec*
      | ( 'UNIQUE' | 'PRIMARY' 'KEY' )? columns? c_include_? withDef? (usingIndexTablespaceID? | usingIndexID) constraintattributespec*
      | 'EXCLUDE' usingID? '(' exclusionconstraintelem ( ',' exclusionconstraintelem )* ')' c_include_? withDef? usingIndexTablespaceID? ( 'WHERE' '(' a_expr ')')? constraintattributespec*
      | 'FOREIGN' 'KEY' columns 'REFERENCES' qname columns? key_match? key_delete* constraintattributespec*
      )
    ;

c_include_
    : 'INCLUDE' columns
    ;

key_match
    : 'MATCH' ( 'FULL' | 'PARTIAL' | 'SIMPLE' )
    ;

exclusionconstraintelem
    : index_elem 'WITH' ( any_operator | 'OPERATOR' '(' any_operator ')' )
    ;

key_delete
    : 'ON' ( 'DELETE' | 'UPDATE' )
      ('NO' 'ACTION'
      | 'RESTRICT'
      | 'CASCADE'
      | 'SET' ( 'NULL' | 'DEFAULT' ))
    ;

inherit
    : 'INHERITS' '(' qnames ')'
    ;

part_elem
    : id collate_? qname?
    | func_expr_windowless collate_? qname?
    | '(' a_expr ')' collate_? qname?
    ;

tablespaceID
    : 'TABLESPACE' id
    ;

usingIndexTablespaceID
    : 'USING' 'INDEX' 'TABLESPACE' id
    ;

usingIndexID
    : 'USING' 'INDEX' id
    ;

createstatsstmt
    : 'CREATE' 'STATISTICS' ifNotExists? qname columns? 'ON' expr_list 'FROM' from_list
    ;

alterstatsstmt
    : 'ALTER' 'STATISTICS' ifExists? qname 'SET' 'STATISTICS' signedDecimal
    ;


with_data_
    : 'WITH' 'NO'? 'DATA'
    ;

creatematviewstmt
    : 'CREATE' 'UNLOGGED'? 'MATERIALIZED' 'VIEW' ifNotExists? qname
      columns?
      usingID? withDef? tablespaceID? 'AS' select with_data_?
    ;

refreshmatviewstmt
    : 'REFRESH' 'MATERIALIZED' 'VIEW' 'CONCURRENTLY'? qname with_data_?
    ;

createseqstmt
    : 'CREATE' opttemp? 'SEQUENCE' ifNotExists? qname seqoptelem*
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
    : 'ALTER' 'EXTENSION' id 'UPDATE' ('TO' name)*
    ;

alterextensioncontentsstmt
    : 'ALTER' 'EXTENSION' id add_drop object_type_name id
    | 'ALTER' 'EXTENSION' id add_drop object_type_any_name qname
    | 'ALTER' 'EXTENSION' id add_drop 'AGGREGATE' aggregate_with_argtypes
    | 'ALTER' 'EXTENSION' id add_drop 'CAST' '(' typename 'AS' typename ')'
    | 'ALTER' 'EXTENSION' id add_drop 'DOMAIN' typename
    | 'ALTER' 'EXTENSION' id add_drop 'FUNCTION' function_with_argtypes
    | 'ALTER' 'EXTENSION' id add_drop 'OPERATOR' operator_with_argtypes
    | 'ALTER' 'EXTENSION' id add_drop 'OPERATOR' 'CLASS' qname usingID
    | 'ALTER' 'EXTENSION' id add_drop 'OPERATOR' 'FAMILY' qname usingID
    | 'ALTER' 'EXTENSION' id add_drop 'PROCEDURE' function_with_argtypes
    | 'ALTER' 'EXTENSION' id add_drop 'ROUTINE' function_with_argtypes
    | 'ALTER' 'EXTENSION' id add_drop 'TRANSFORM' 'FOR' typename 'LANGUAGE' id
    | 'ALTER' 'EXTENSION' id add_drop 'TYPE' typename
    ;

createfdwstmt
    : 'CREATE' 'FOREIGN' 'DATA' 'WRAPPER' id fdw_option* create_generic_options?
    ;

fdw_option
    : 'HANDLER' qname
    | 'NO' 'HANDLER'
    | 'VALIDATOR' qname
    | 'NO' 'VALIDATOR'
    ;

alterfdwstmt
    : 'ALTER' 'FOREIGN' 'DATA' 'WRAPPER' id
      ( fdw_option* alter_generic_options |  fdw_option+ )
    ;

create_generic_options
    : 'OPTIONS' '(' generic_option_elem ( ',' generic_option_elem )* ')'
    ;

alter_generic_options
    : 'OPTIONS' '(' alter_generic_option_elem ( ',' alter_generic_option_elem )* ')'
    ;

alter_generic_option_elem
    : generic_option_elem
    | 'SET' generic_option_elem
    | 'ADD' generic_option_elem
    | 'DROP' id
    ;

generic_option_elem
    : id string
    ;

createforeignserverstmt
    : 'CREATE' 'SERVER' ifNotExists? id ( 'TYPE' string )? foreign_server_version? 'FOREIGN' 'DATA' 'WRAPPER' id create_generic_options?
    ;

foreign_server_version
    : 'VERSION' ( string | 'NULL' )
    ;

alterforeignserverstmt
    : 'ALTER' 'SERVER' id ( alter_generic_options | foreign_server_version alter_generic_options? )
    ;

importforeignschemastmt
    : 'IMPORT' 'FOREIGN' 'SCHEMA' id  (( 'LIMIT' 'TO' | 'EXCEPT' )? '(' relation_expr_list ')' )?
      'FROM' 'SERVER' id 'INTO' id create_generic_options?
    ;

createusermappingstmt
    : 'CREATE' 'USER' 'MAPPING' ifNotExists? 'FOR' auth_ident 'SERVER' id create_generic_options?
    ;

auth_ident
    : id
    | 'USER'
    ;

dropusermappingstmt
    : 'DROP' 'USER' 'MAPPING' ifExists? 'FOR' auth_ident 'SERVER' id
    ;

alterusermappingstmt
    : 'ALTER' 'USER' 'MAPPING' 'FOR' auth_ident 'SERVER' id alter_generic_options
    ;

createpolicystmt
    : 'CREATE' 'POLICY' id 'ON' qname ('AS' identifier)? rowsecuritydefaultforcmd? ('TO' ids)? rowsecurityoptionalexpr?
        rowsecurityoptionalwithcheck?
    ;

alterpolicystmt
    : 'ALTER' 'POLICY' id 'ON' qname ('TO' ids)? rowsecurityoptionalexpr? rowsecurityoptionalwithcheck?
    ;

rowsecurityoptionalexpr
    : 'USING' '(' a_expr ')'
    ;

rowsecurityoptionalwithcheck
    : 'WITH' 'CHECK' '(' a_expr ')'
    ;

rowsecuritydefaultforcmd
    : 'FOR' ( 'ALL' | 'SELECT' | 'INSERT' | 'UPDATE' | 'DELETE' )
    ;

createamstmt
    : 'CREATE' 'ACCESS' 'METHOD' id 'TYPE' ('INDEX' | 'TABLE') 'HANDLER' qname
    ;

createtrigstmt
    : 'CREATE' 'TRIGGER' id
      ('BEFORE' | 'AFTER' | 'INSTEAD' 'OF') triggerevents 'ON' qname
      ('REFERENCING' triggertransition+ )?
      ('FOR' 'EACH'? ('ROW' | 'STATEMENT'))?
      triggerwhen? 'EXECUTE' function_or_procedure qname '(' triggerfuncargs? ')'

    | 'CREATE' 'CONSTRAINT' 'TRIGGER' id
      'AFTER' triggerevents 'ON' qname
      ('FROM' qname)? constraintattributespec* 'FOR' 'EACH' 'ROW'
      triggerwhen? 'EXECUTE' function_or_procedure qname '(' triggerfuncargs? ')'
    ;

triggerevents
    : triggeroneevent ( 'OR' triggeroneevent )*
    ;

triggeroneevent
    : 'INSERT'
    | 'DELETE'
    | 'UPDATE' ('OF' ids)?
    | 'TRUNCATE'
    ;

triggertransition
    : ('NEW' | 'OLD') ('TABLE' | 'ROW') alias
    ;

triggerwhen
    : 'WHEN' '(' a_expr ')'
    ;

function_or_procedure
    : 'FUNCTION'
    | 'PROCEDURE'
    ;

triggerfuncargs
    : triggerfuncarg ( ',' triggerfuncarg )*
    ;

triggerfuncarg
    : integer
    | FLOAT
    | string
    | id
    ;

constraintattributespec
    : 'NOT'? 'DEFERRABLE'
    | 'INITIALLY' ( 'IMMEDIATE' | 'DEFERRED' )?
    | 'NOT' 'VALID'
    | 'NO' 'INHERIT'
    ;

createeventtrigstmt
    : 'CREATE' 'EVENT' 'TRIGGER' id 'ON' id
      ( 'WHEN' event_trigger_when_item ( 'AND' event_trigger_when_item )* )?
      'EXECUTE' function_or_procedure qname '(' ')'
    ;

event_trigger_when_item
    : id 'IN' '(' string ( ',' string )* ')'
    ;

altereventtrigstmt
    : 'ALTER' 'EVENT' 'TRIGGER' id ('ENABLE' ( 'REPLICA' |  'ALWAYS' )?
    | 'DISABLE')
    ;

createassertionstmt
    : 'CREATE' 'ASSERTION' qname 'CHECK' '(' a_expr ')' constraintattributespec*
    ;

definestmt
    : 'CREATE' orReplace? 'AGGREGATE' aggregate_with_argtypes definition
    | 'CREATE' orReplace? 'AGGREGATE' qname definition
    | 'CREATE' 'OPERATOR' any_operator definition
    | 'CREATE' 'TYPE' qname definition?
    | 'CREATE' 'TYPE' qname 'AS' '(' tablefuncelementlist? ')'
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
    : func_type
    | reserved_keyword
    | qual_all_op
    | number
    | string
    | 'NONE'
    ;


alterenumstmt
    : 'ALTER' 'TYPE' qname 'ADD' 'VALUE' ifNotExists? string (( 'BEFORE' | 'AFTER' ) string )?
    | 'ALTER' 'TYPE' qname 'RENAME' 'VALUE' string 'TO' string
    ;

createopclassstmt
    : 'CREATE' 'OPERATOR' 'CLASS' qname 'DEFAULT'? 'FOR' 'TYPE' typename usingID ('FAMILY' qname)? 'AS' opclass_item_list
    ;

opclass_item_list
    : opclass_item ( ',' opclass_item )*
    ;

opclass_item
    : 'OPERATOR' integer any_operator opclass_purpose? 'RECHECK'?
    | 'OPERATOR' integer operator_with_argtypes opclass_purpose? 'RECHECK'?
    | 'FUNCTION' integer function_with_argtypes
    | 'FUNCTION' integer '(' type_list ')' function_with_argtypes
    | 'STORAGE' typename
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


opclass_drop
    : ('OPERATOR' | 'FUNCTION') integer '(' type_list ')'
    ;

dropOperator
    : 'DROP' 'OPERATOR' ( 'CLASS' | 'FAMILY' ) ifExists? qname usingID drop_behavior_?
    ;

//dropopfamilystmt
//    : 'DROP' 'OPERATOR' 'FAMILY' ifExists? qname usingID drop_behavior_?
//    ;

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
    | 'DROP' ( 'TYPE' | 'DOMAIN' ) ifExists? typename ( ',' typename )* drop_behavior_?
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
    : 'TRUNCATE' 'TABLE'? relation_expr_list (( 'CONTINUE' | 'RESTART' ) 'IDENTITY' )? drop_behavior_?
    ;

commentstmt
  : 'COMMENT' 'ON'
    ( object_type_any_name qname
    | 'COLUMN' qname
    | object_type_name id
    | 'TYPE' typename
    | 'DOMAIN' typename
    | 'AGGREGATE' aggregate_with_argtypes
    | ('FUNCTION' | 'PROCEDURE' | 'ROUTINE') function_with_argtypes
    | 'OPERATOR' operator_with_argtypes
    | 'CONSTRAINT' id 'ON' 'DOMAIN'? qname?
    | object_type_name_on_any_name id 'ON' qname
   | 'TRANSFORM' 'FOR' typename 'LANGUAGE' id
    | 'OPERATOR' ('CLASS' | 'FAMILY' ) qname usingID
    | 'LARGE' 'OBJECT' number
    | 'CAST' '(' typename 'AS' typename ')'
    )
    'IS' ( string | 'NULL' )
  ;

seclabelstmt
    : 'SECURITY' 'LABEL' ('FOR' name)? 'ON'
      ( object_type_any_name qname
      | 'COLUMN' qname
      | object_type_name id
      | 'TYPE' typename
      | 'DOMAIN' typename
      | 'AGGREGATE' aggregate_with_argtypes
      | 'FUNCTION' function_with_argtypes
      | 'LARGE' 'OBJECT' number
      | 'PROCEDURE' function_with_argtypes
      | 'ROUTINE' function_with_argtypes
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

grantstmt
    : 'GRANT' privileges 'ON' privilege_target 'TO' grantee_list grant_grant_option_?
    ;

revokestmt
    : 'REVOKE' privileges 'ON' privilege_target 'FROM' grantee_list drop_behavior_?
    | 'REVOKE' 'GRANT' 'OPTION' 'FOR' privileges 'ON' privilege_target 'FROM' grantee_list drop_behavior_?
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
    | 'FUNCTION' function_with_argtypes_list
    | 'PROCEDURE' function_with_argtypes_list
    | 'ROUTINE' function_with_argtypes_list
    | 'DATABASE' ids
    | 'DOMAIN' qnames
    | 'LANGUAGE' ids
    | 'LARGE' 'OBJECT' number ( ',' number )*
    | 'SCHEMA' ids
    | 'TABLESPACE' ids
    | 'TYPE' qnames
    | 'ALL' 'TABLES' 'IN' 'SCHEMA' ids
    | 'ALL' 'SEQUENCES' 'IN' 'SCHEMA' ids
    | 'ALL' 'FUNCTIONS' 'IN' 'SCHEMA' ids
    | 'ALL' 'PROCEDURES' 'IN' 'SCHEMA' ids
    | 'ALL' 'ROUTINES' 'IN' 'SCHEMA' ids
    ;

grantee_list
    : grantee ( ',' grantee )*
    ;

grantee
    : 'GROUP'? id
    ;

grant_grant_option_
    : 'WITH' 'GRANT' 'OPTION'
    ;

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
    : 'ALTER' 'DEFAULT' 'PRIVILEGES' defacloption* defaclaction
    ;

defacloption
    : 'IN' 'SCHEMA' ids
    | 'FOR' 'ROLE' ids
    | 'FOR' 'USER' ids
    ;

defaclaction
    : 'GRANT' privileges 'ON' defacl_privilege_target 'TO' grantee_list grant_grant_option_?
    | 'REVOKE' ('GRANT' 'OPTION' 'FOR')? privileges 'ON' defacl_privilege_target 'FROM' grantee_list drop_behavior_?
    ;

defacl_privilege_target
    : 'TABLES'
    | 'FUNCTIONS'
    | 'ROUTINES'
    | 'SEQUENCES'
    | 'TYPES'
    | 'SCHEMAS'
    ;

indexstmt
    : 'CREATE' 'UNIQUE'? 'INDEX' 'CONCURRENTLY'? (ifNotExists? id)?
      'ON' relation_expr usingID? '(' index_params ')'
      ('INCLUDE' '(' index_params ')')? withDef? tablespaceID? where?
    ;

index_params
  : index_elem ( ',' index_elem )*
  ;

    index_elem
      : ( id | func_expr_windowless | '(' a_expr ')' )
        collate_? ( qname (definition)? )? sortDir? nullsOrder?
      ;

collate_
    : 'COLLATE' qname
    ;

sortDir
    : 'ASC'
    | 'DESC'
    ;

nullsOrder
    : 'NULLS' ('FIRST' |  'LAST')
    ;

createfunctionstmt
  : 'CREATE' orReplace? ( 'FUNCTION' | 'PROCEDURE' ) qname
    '(' ( func_arg_with_default ( ',' func_arg_with_default )* )? ')'
    ( 'RETURNS' ( func_type | 'TABLE' '(' table_func_column_list ')' ) )?
    ('AS' func_as
    | 'LANGUAGE' name
    | 'TRANSFORM' transform_type_list
    | 'WINDOW'
    | common_func_opt_item)+
  ;

orReplace
    : 'OR' 'REPLACE'
    ;

func_args_list
    : func_arg ( ',' func_arg )*
    ;

function_with_argtypes_list
    : function_with_argtypes ( ',' function_with_argtypes )*
    ;

function_with_argtypes
    : qname ( '(' func_args_list? ')' )?
    ;

func_arg_with_default
      : func_arg ( ( 'DEFAULT' | '=' ) a_expr )?
      ;

func_arg
//    : argmode? id? func_type
    : ( argmode id? | id argmode? )? func_type
    ;

argmode
    : 'IN' 'OUT'?
    | 'OUT'
    | 'INOUT'
    | 'VARIADIC'
    ;

func_type
    : typename
    | 'SETOF'? id ( '.' id )+ '%' 'TYPE'
    ;

aggregate_with_argtypes
    : qname '(' ( '*' | (func_args_list? 'ORDER' 'BY' )? func_args_list ) ')'
    ;

common_func_opt_item
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

func_as
    :
    /* |AS 'definition'*/ def = string
    /*| 'AS' 'obj_file', 'link_symbol'*/
    | string ',' string
    ;

transform_type_list
    : 'FOR' 'TYPE' typename ( ',' 'FOR' 'TYPE' typename )*
    ;

withDef
    : 'WITH' definition
    ;


table_func_column_list
  : table_func_column ( ',' table_func_column )*
  ;

    table_func_column
      : id func_type
      ;

alterfunctionstmt
    : 'ALTER' ( 'FUNCTION' | 'PROCEDURE' | 'ROUTINE' ) function_with_argtypes common_func_opt_item+ ('RESTRICT')?
    ;

removefuncstmt
    : 'DROP' ('FUNCTION' | 'PROCEDURE' | 'ROUTINE') ifExists? function_with_argtypes_list drop_behavior_?
    ;

removeaggrstmt
    : 'DROP' 'AGGREGATE' ifExists? aggregate_with_argtypes ( ',' aggregate_with_argtypes )* drop_behavior_?
    ;

removeoperstmt
    : 'DROP' 'OPERATOR' ifExists? operator_with_argtypes ( ',' operator_with_argtypes )* drop_behavior_?
    ;

oper_argtypes
    : '(' typename ')'
    | '(' typename ',' typename ')'
    | '(' 'NONE' ',' typename ')'
    | '(' typename ',' 'NONE' ')'
    ;

any_operator
    : ( id '.' )* (Operator | mathop)
    ;

operator_with_argtypes
    : any_operator oper_argtypes
    ;

dostmt
    : 'DO' ( 'LANGUAGE'? name )+
    ;

createcaststmt
    : 'CREATE' 'CAST' '(' typename 'AS' typename ')'
      ('WITH' 'FUNCTION' function_with_argtypes | 'WITHOUT' 'FUNCTION' | 'WITH' 'INOUT')
      ('AS' ( 'IMPLICIT' |  'ASSIGNMENT' ))?
    ;

dropcaststmt
    : 'DROP' 'CAST' ifExists? '(' typename 'AS' typename ')' drop_behavior_?
    ;

createtransformstmt
    : 'CREATE' orReplace? 'TRANSFORM' 'FOR' typename 'LANGUAGE' id '(' transform_element_list ')'
    ;

transform_element_list
    : 'FROM' 'SQL' 'WITH' 'FUNCTION' function_with_argtypes ( ',' 'TO' 'SQL' 'WITH' 'FUNCTION' function_with_argtypes )?
    | 'TO' 'SQL' 'WITH' 'FUNCTION' function_with_argtypes ( ',' 'FROM' 'SQL' 'WITH' 'FUNCTION' function_with_argtypes )?
    ;

droptransformstmt
    : 'DROP' 'TRANSFORM' ifExists? 'FOR' typename 'LANGUAGE' id drop_behavior_?
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
    : 'ALTER' 'AGGREGATE' aggregate_with_argtypes 'RENAME' 'TO' id
    | 'ALTER' 'COLLATION' qname 'RENAME' 'TO' id
    | 'ALTER' 'CONVERSION' qname 'RENAME' 'TO' id
    | 'ALTER' 'DATABASE' id 'RENAME' 'TO' id
    | 'ALTER' 'DOMAIN' qname 'RENAME' 'TO' id
    | 'ALTER' 'DOMAIN' qname 'RENAME' 'CONSTRAINT' id 'TO' id
    | 'ALTER' 'FOREIGN' 'DATA' 'WRAPPER' id 'RENAME' 'TO' id
    | 'ALTER' 'FUNCTION' function_with_argtypes 'RENAME' 'TO' id
    | 'ALTER' 'GROUP' id 'RENAME' 'TO' id
    | 'ALTER' 'PROCEDURAL'? 'LANGUAGE' id 'RENAME' 'TO' id
    | 'ALTER' 'OPERATOR' 'CLASS' qname usingID 'RENAME' 'TO' id
    | 'ALTER' 'OPERATOR' 'FAMILY' qname usingID 'RENAME' 'TO' id
    | 'ALTER' 'POLICY' ifExists? id 'ON' qname 'RENAME' 'TO' id
    | 'ALTER' 'PROCEDURE' function_with_argtypes 'RENAME' 'TO' id
    | 'ALTER' 'PUBLICATION' id 'RENAME' 'TO' id
    | 'ALTER' 'ROUTINE' function_with_argtypes 'RENAME' 'TO' id
    | 'ALTER' 'SCHEMA' id 'RENAME' 'TO' id
    | 'ALTER' 'SERVER' id 'RENAME' 'TO' id
    | 'ALTER' 'SUBSCRIPTION' id 'RENAME' 'TO' id
    | 'ALTER' 'TABLE' ifExists? relation_expr 'RENAME' 'TO' id
    | 'ALTER' 'SEQUENCE' ifExists? qname 'RENAME' 'TO' id
    | 'ALTER' 'VIEW' ifExists? qname 'RENAME' 'TO' id
    | 'ALTER' 'MATERIALIZED' 'VIEW' ifExists? qname 'RENAME' 'TO' id
    | 'ALTER' 'INDEX' ifExists? qname 'RENAME' 'TO' id
    | 'ALTER' 'FOREIGN' 'TABLE' ifExists? relation_expr 'RENAME' 'TO' id
    | 'ALTER' 'TABLE' ifExists? relation_expr 'RENAME' 'COLUMN'? id 'TO' id
    | 'ALTER' 'VIEW' ifExists? qname 'RENAME' 'COLUMN'? id 'TO' id
    | 'ALTER' 'MATERIALIZED' 'VIEW' ifExists? qname 'RENAME' 'COLUMN'? id 'TO' id
    | 'ALTER' 'TABLE' ifExists? relation_expr 'RENAME' 'CONSTRAINT' id 'TO' id
    | 'ALTER' 'FOREIGN' 'TABLE' ifExists? relation_expr 'RENAME' 'COLUMN'? id 'TO' id
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
    : 'ALTER' 'FUNCTION' function_with_argtypes 'NO'? 'DEPENDS' 'ON' 'EXTENSION' id
    | 'ALTER' 'PROCEDURE' function_with_argtypes 'NO'? 'DEPENDS' 'ON' 'EXTENSION' id
    | 'ALTER' 'ROUTINE' function_with_argtypes 'NO'? 'DEPENDS' 'ON' 'EXTENSION' id
    | 'ALTER' 'TRIGGER' id 'ON' qname 'NO'? 'DEPENDS' 'ON' 'EXTENSION' id
    | 'ALTER' 'MATERIALIZED' 'VIEW' qname 'NO'? 'DEPENDS' 'ON' 'EXTENSION' id
    | 'ALTER' 'INDEX' qname 'NO'? 'DEPENDS' 'ON' 'EXTENSION' id
    ;

alterobjectschemastmt
    : 'ALTER' 'AGGREGATE' aggregate_with_argtypes 'SET' 'SCHEMA' id
    | 'ALTER' 'COLLATION' qname 'SET' 'SCHEMA' id
    | 'ALTER' 'CONVERSION' qname 'SET' 'SCHEMA' id
    | 'ALTER' 'DOMAIN' qname 'SET' 'SCHEMA' id
    | 'ALTER' 'EXTENSION' id 'SET' 'SCHEMA' id


    | 'ALTER' 'FUNCTION' function_with_argtypes 'SET' 'SCHEMA' id
    | 'ALTER' 'PROCEDURE' function_with_argtypes 'SET' 'SCHEMA' id
    | 'ALTER' 'ROUTINE' function_with_argtypes 'SET' 'SCHEMA' id

     | 'ALTER' 'OPERATOR' operator_with_argtypes 'SET' 'SCHEMA' id
    | 'ALTER' 'OPERATOR' ( 'CLASS' | 'FAMILY' ) qname usingID 'SET' 'SCHEMA' id
    | 'ALTER' 'OPERATOR'  qname usingID 'SET' 'SCHEMA' id
    | 'ALTER' 'TABLE' ifExists? relation_expr 'SET' 'SCHEMA' id
    | 'ALTER' 'STATISTICS' qname 'SET' 'SCHEMA' id
    | 'ALTER' textSearchConfig qname 'SET' 'SCHEMA' id
    | 'ALTER' 'SEQUENCE' ifExists? qname 'SET' 'SCHEMA' id
    | 'ALTER' 'VIEW' ifExists? qname 'SET' 'SCHEMA' id
    | 'ALTER' 'MATERIALIZED' 'VIEW' ifExists? qname 'SET' 'SCHEMA' id
    | 'ALTER' 'TYPE' qname 'SET' 'SCHEMA' id
    | 'ALTER' 'FOREIGN' 'TABLE' ifExists? relation_expr 'SET' 'SCHEMA' id
    ;

textSearchConfig : 'TEXT' 'SEARCH' ( 'PARSER' | 'DICTIONARY' | 'TEMPLATE' | 'CONFIGURATION' ) ;

alteroperatorstmt
    : 'ALTER' 'OPERATOR' operator_with_argtypes 'SET' '(' operator_def_elem ( ',' operator_def_elem )* ')'
    ;

operator_def_elem
    : id '=' operator_def_arg
    ;

operator_def_arg
    : func_type
    | reserved_keyword
    | qual_all_op
    | number
    | string
    ;

altertypestmt
    : 'ALTER' 'TYPE' qname 'SET' '(' operator_def_elem ( ',' operator_def_elem )* ')'
    ;

alterownerstmt
    : 'ALTER' 'AGGREGATE' aggregate_with_argtypes 'OWNER' 'TO' id
    | 'ALTER' 'COLLATION' qname 'OWNER' 'TO' id
    | 'ALTER' 'CONVERSION' qname 'OWNER' 'TO' id
    | 'ALTER' 'DATABASE' id 'OWNER' 'TO' id
    | 'ALTER' 'DOMAIN' qname 'OWNER' 'TO' id
    | 'ALTER' 'FUNCTION' function_with_argtypes 'OWNER' 'TO' id
    | 'ALTER' 'PROCEDURAL'? 'LANGUAGE' id 'OWNER' 'TO' id
    | 'ALTER' 'LARGE' 'OBJECT' number 'OWNER' 'TO' id
    | 'ALTER' 'OPERATOR' operator_with_argtypes 'OWNER' 'TO' id
    | 'ALTER' 'OPERATOR' 'CLASS' qname usingID 'OWNER' 'TO' id
    | 'ALTER' 'OPERATOR' 'FAMILY' qname usingID 'OWNER' 'TO' id
    | 'ALTER' 'PROCEDURE' function_with_argtypes 'OWNER' 'TO' id
    | 'ALTER' 'ROUTINE' function_with_argtypes 'OWNER' 'TO' id
    | 'ALTER' 'SCHEMA' id 'OWNER' 'TO' id
    | 'ALTER' 'TYPE' qname 'OWNER' 'TO' id
    | 'ALTER' 'STATISTICS' qname 'OWNER' 'TO' id
    | 'ALTER' 'TEXT' 'SEARCH' 'DICTIONARY' qname 'OWNER' 'TO' id
    | 'ALTER' 'TEXT' 'SEARCH' 'CONFIGURATION' qname 'OWNER' 'TO' id
    | 'ALTER' 'FOREIGN' 'DATA' 'WRAPPER' id 'OWNER' 'TO' id
    | 'ALTER' 'SERVER' id 'OWNER' 'TO' id
    | 'ALTER' 'EVENT' 'TRIGGER' id 'OWNER' 'TO' id
    | 'ALTER' 'PUBLICATION' id 'OWNER' 'TO' id
    | 'ALTER' 'SUBSCRIPTION' id 'OWNER' 'TO' id
    ;

createpublicationstmt
    : 'CREATE' 'PUBLICATION' id ('FOR' 'TABLE' relation_expr_list | 'FOR' 'ALL' 'TABLES')? withDef?
    ;

alterpublicationstmt
    : 'ALTER' 'PUBLICATION' id 'SET' definition
    | 'ALTER' 'PUBLICATION' id ( 'ADD' | 'SET' | 'DROP' ) 'TABLE' relation_expr_list
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
    | ( 'BEGIN' transaction_? | 'START' 'TRANSACTION' ) ( transaction_mode_item ( ',' transaction_mode_item )* )?
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
    : 'CREATE' ( 'OR' 'REPLACE' )? opttemp? (
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
    : (identifier
    | 'CONNECTION' 'LIMIT'
    | 'ENCODING'
    | 'LOCATION'
    | 'OWNER'
    | 'TABLESPACE'
    | 'TEMPLATE') '='? ( signedDecimal | boolean_or_string_ | 'DEFAULT' )
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
    : 'ALTER' 'SYSTEM' ( 'SET' | 'RESET' ) qname ( 'TO' | '=' ) ( var_list | 'DEFAULT' )
    ;

createdomainstmt
    : 'CREATE' 'DOMAIN' qname 'AS'? typename colconstraint*
    ;

alterdomainstmt
  : 'ALTER' 'DOMAIN' qname
    ( ('SET' 'DEFAULT' a_expr
    | 'DROP' 'DEFAULT')
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
    : 'CLUSTER' 'VERBOSE'? (qname usingID? | id 'ON' qname)?
    ;

vacuumstmt
    : 'VACUUM' 'FULL'? 'FREEZE'? 'VERBOSE'? ANALYZE? (vacuum_relation ( ',' vacuum_relation )*)?
    | 'VACUUM' '(' optionElems ')' (vacuum_relation ( ',' vacuum_relation )*)?
    ;

analyzestmt
    : ANALYZE 'VERBOSE'? (vacuum_relation ( ',' vacuum_relation )*)?
    | ANALYZE '(' optionElems ')' (vacuum_relation ( ',' vacuum_relation )*)?
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
    | execute
    ;

optionElems
    : option_elem ( ',' option_elem )*
    ;

option_elem
    : id (boolean_or_string_ | number)?
    ;

preparestmt
    : 'PREPARE' id ( '(' type_list ')' )? 'AS'
      (select | insert | update | delete)
    ;


deallocatestmt
    : 'DEALLOCATE' 'PREPARE'? ( id |  'ALL' )
    ;

insert
    : with_clause? 'INSERT' 'INTO' qname ( 'AS' id )?

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
    : 'RETURNING' target_list
    ;

// https://www.postgresql.org/docs/current/sql-merge.html
mergestmt
    : 'MERGE' 'INTO'? qname aliasColumns? 'USING' ( select_with_parens | qname ) aliasColumns? 'ON' a_expr
      ( merge_insert_clause merge_update_clause?
      | merge_update_clause merge_insert_clause?
      )
      ('WHEN' 'MATCHED' 'THEN'? 'DELETE')?
    ;

merge_insert_clause
  : 'WHEN' 'NOT' 'MATCHED' ( 'AND' a_expr )? 'THEN'? 'INSERT' ( '(' qnames ')' )? values
  ;

merge_update_clause
  : 'WHEN' 'MATCHED' ( 'AND' a_expr )? 'THEN'? 'UPDATE' 'SET' set_clause_list
  ;

delete
    : with_clause? 'DELETE' 'FROM' relation_expr_opt_alias ('USING' from_list)? where_or_current_clause? returning?
    ;

lockstmt
    : 'LOCK' 'TABLE'? relation_expr_list ('IN' lock_type 'MODE')? 'NOWAIT'?
    ;

lock_type
    : 'ACCESS' ( 'SHARE' | 'EXCLUSIVE' )
    | 'ROW' ( 'SHARE' | 'EXCLUSIVE' )
    | 'SHARE' ( 'UPDATE' 'EXCLUSIVE' | 'ROW' 'EXCLUSIVE' )?
    | 'EXCLUSIVE'
    ;

update
    : with_clause? 'UPDATE' relation_expr_opt_alias 'SET' set_clause_list from? where_or_current_clause? returning?
    ;

set_clause_list
    : set_clause ( ',' set_clause )*
    ;

set_clause
    : qname '=' a_expr
    | '(' qnames ')' '=' a_expr
    ;

declarecursorstmt
    : 'DECLARE' id ( 'NO'? 'SCROLL' | 'SCROLL' | 'BINARY' | 'INSENSITIVE' )* 'CURSOR' (( 'WITH' | 'WITHOUT' ) 'HOLD' )? 'FOR' select
    ;

select
    : select_no_parens
    | select_with_parens
    ;

select_with_parens
    : '(' select_no_parens ')'
    | '(' select_with_parens ')'
    ;

select_no_parens
    : with_clause? select_clause orderBy?
      ( for_locking_clause select_limit? | select_limit for_locking_clause? )?
    ;

select_clause
    : simple_select_intersect ( ( 'UNION' | 'EXCEPT' ) allDistinct? simple_select_intersect )*
    ;

simple_select_intersect
    : simple_select_primary ( 'INTERSECT' allDistinct? simple_select_primary )*
    ;

simple_select_primary
    :  'SELECT'
	      ( 'ALL'? target_list? | distinct_clause target_list )
	      ( 'INTO' ( ( 'LOCAL' | 'GLOBAL' )? ( 'TEMPORARY' | 'TEMP' ) 'TABLE'? | 'UNLOGGED' 'TABLE'? | 'TABLE' )? qname )?
	      from? where? group_clause? having_clause? window_clause?

    | values
    | 'TABLE' relation_expr
    | select_with_parens
    ;

with_clause
    : 'WITH' 'RECURSIVE'? cte ( ',' cte )*
    ;

cte
    : id columns? 'AS' ('NOT'? 'MATERIALIZED')?
      '(' (select | insert | update | delete) ')'
    ;

allDistinct
    : 'ALL'
    | 'DISTINCT'
    ;

distinct_clause
    : 'DISTINCT' ( 'ON' '(' expr_list ')' )?
    ;

orderBy
    : 'ORDER' 'BY' sortby ( ',' sortby  )*
    ;

sortby
    : a_expr ( 'USING' qual_all_op | sortDir?) nullsOrder?
    ;

select_limit
    : limit_clause offset_clause?
    | offset_clause limit_clause?
    ;

limit_clause
    : 'LIMIT' (a_expr | 'ALL') ( ',' a_expr )?
    | 'FETCH' first_or_next (
        select_fetch_first_value row_or_rows ( 'ONLY' | 'WITH' 'TIES' )
        | row_or_rows ( 'ONLY' | 'WITH' 'TIES' )
    )
    ;

offset_clause
    : 'OFFSET' ( a_expr | select_fetch_first_value row_or_rows )
    ;

select_fetch_first_value
    : c_expr
    | '+' i_or_f_const
    | '-' i_or_f_const
    ;

i_or_f_const
    : integer
    | FLOAT
    ;

row_or_rows
    : 'ROW'
    | 'ROWS'
    ;

first_or_next
    : 'FIRST'
    | 'NEXT'
    ;

group_clause
    : 'GROUP' 'BY' group_by_list

    ;

group_by_list
    : group_by_item ( ',' group_by_item )*
    ;

group_by_item
    : '(' ')'
    | 'CUBE' '(' expr_list ')'
    | 'ROLLUP' '(' expr_list ')'
    | 'GROUPING' 'SETS' '(' group_by_list ')'
    | a_expr
    ;

having_clause
    : 'HAVING' a_expr
    ;

for_locking_clause
    : ('FOR' ( ( 'NO' 'KEY'  )? 'UPDATE' | 'KEY'? 'SHARE' ) ('OF' qnames)? ('NOWAIT' | 'SKIP' 'LOCKED')?)+
    | 'FOR' 'READ' 'ONLY'
    ;

values
    : 'VALUES' '(' expr_list ')' ( ',' '(' expr_list ')' )*
    ;

from
    : 'FROM' from_list
    ;

from_list
    : table_ref ( ',' table_ref )*
    ;

table_ref
    : (
        relation_expr aliasColumns? tablesample_clause?
        | func_table func_alias_clause?
        | xmltable aliasColumns?
        | select_with_parens aliasColumns?
        | 'LATERAL' (
            xmltable aliasColumns?
            | func_table func_alias_clause?
            | select_with_parens aliasColumns?
        )
        | '(' table_ref (
            'CROSS' 'JOIN' table_ref
            | 'NATURAL' join_type? 'JOIN' table_ref
            | join_type? 'JOIN' table_ref join_qual
         )? ')' aliasColumns?
    ) (
        'CROSS' 'JOIN' table_ref
        | 'NATURAL' join_type? 'JOIN' table_ref
        | join_type? 'JOIN' table_ref join_qual
     )*
    ;

aliasColumns
    : 'AS'? id ( columns )?
    ;

alias
    : 'AS'? id
    ;

func_alias_clause
    : aliasColumns
    | ( 'AS' id? | id ) '(' tablefuncelementlist ')'

    ;

join_type
    : ( 'FULL' | 'LEFT' | 'RIGHT' | 'INNER' ) 'OUTER'?
    ;

join_qual
    : 'USING' columns
    | 'ON' a_expr
    ;

relation_expr
    : qname '*'?
    | 'ONLY' ( qname | '(' qname ')' )
    ;

relation_expr_list
    : relation_expr ( ',' relation_expr )*
    ;

relation_expr_opt_alias
    : relation_expr ( alias )?
    ;

tablesample_clause
    : 'TABLESAMPLE' qname '(' expr_list ')' ('REPEATABLE' '(' a_expr ')')?
    ;

func_table
    : func_expr_windowless ('WITH' 'ORDINALITY')?
    | 'ROWS' 'FROM' '(' rowsfrom_list ')' ('WITH' 'ORDINALITY')?
    ;

rowsfrom_item
    : func_expr_windowless ('AS' '(' tablefuncelementlist ')')?
    ;

rowsfrom_list
    : rowsfrom_item ( ',' rowsfrom_item )*
    ;

where
    : 'WHERE' a_expr
    ;

where_or_current_clause
    : 'WHERE' ( 'CURRENT' 'OF' id | a_expr )
    ;

tablefuncelementlist
    : tablefuncelement ( ',' tablefuncelement )*
    ;

tablefuncelement
    : id typename collate_clause_?
    ;

xmltable
    : 'XMLTABLE' '(' (
        c_expr xmlexists_argument 'COLUMNS' xmltable_column_list
        | 'XMLNAMESPACES' '(' xml_namespace_list ')' ',' c_expr xmlexists_argument 'COLUMNS' xmltable_column_list
    ) ')'
    ;

xmltable_column_list
    : xmltable_column_el ( ',' xmltable_column_el )*
    ;

xmltable_column_el
    : id ( typename (xmltable_column_option_el)* | 'FOR' 'ORDINALITY' )
    ;

xmltable_column_option_el
    : 'DEFAULT' a_expr
    | identifier a_expr
    | 'NOT'? 'NULL'
    ;

xml_namespace_list
    : xml_namespace_el ( ',' xml_namespace_el )*
    ;

xml_namespace_el
    : b_expr 'AS' id
    | 'DEFAULT' b_expr
    ;

typename
    : 'SETOF'? simpletypename
      (  ( '[' integer? ']' )*
      | 'ARRAY' ( '[' integer ']' )?
      )
    ;

simpletypename
    : id ( '.' id )* type_modifiers_?
    | numeric
    | bit
    | character
    | constdatetime
    | 'INTERVAL' ( timeUnit? | '(' integer ')' )
    | 'JSON'
    ;

consttypename
    : numeric
    | bit
    | character_c ( '(' integer ')' )?
    | constdatetime
    | 'JSON'
    ;

type_modifiers_
    : '(' expr_list ')'
    ;

numeric
    : 'INT'
    | 'INTEGER'
    | 'SMALLINT'
    | 'BIGINT'
    | 'REAL'
    | 'FLOAT' ('(' integer ')')?
    | 'DOUBLE' 'PRECISION'
    | 'DECIMAL' type_modifiers_?
    | 'DEC' type_modifiers_?
    | 'NUMERIC' type_modifiers_?
    | 'BOOLEAN'
    ;

bit
    : 'BIT' 'VARYING'? '(' expr_list ')'?
    ;

character
    : character_c ( '(' integer ')' )?
    ;

character_c
    : ( 'CHARACTER' | 'CHAR' | 'NCHAR' ) 'VARYING'?
    | 'VARCHAR'
    | 'NATIONAL' ( 'CHARACTER' | 'CHAR' ) 'VARYING'?
    ;

constdatetime
    : ( 'TIMESTAMP' | 'TIME' ) ( '(' integer ')' )? (( 'WITH' | 'WITHOUT' ) 'TIME' 'ZONE' )?
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
    : 'SECOND' ( '(' integer ')' )?
    ;

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

a_expr
    : a_expr_lessless qual_op?
    ;

a_expr_lessless
    : a_expr_or ( ( '<<' | '>>' ) a_expr_or )*
    ;

a_expr_or
    : a_expr_and ( 'OR' a_expr_and )*
    ;

a_expr_and
    : a_expr_between ( 'AND' a_expr_between )*
    ;

a_expr_between
    : a_expr_in ( 'NOT'? 'BETWEEN' 'SYMMETRIC'? a_expr_in 'AND' a_expr_in )?
    ;

a_expr_in
    : a_expr_unary_not ( 'NOT'? 'IN' in_expr )?
    ;

a_expr_unary_not
    : 'NOT'? a_expr_isnull
    ;

a_expr_isnull
    : a_expr_is_not ( 'ISNULL' | 'NOTNULL' )?
    ;

a_expr_is_not
    : a_expr_compare (
        'IS' 'NOT'? (
            'NULL'
            | 'TRUE'
            | 'FALSE'
            | 'UNKNOWN'
            | 'DISTINCT' 'FROM' a_expr
            | 'OF' '(' type_list ')'
            | 'DOCUMENT'
            | unicode_normal_form? 'NORMALIZED'
        )
     )?
    ;

a_expr_compare
    : a_expr_like (
        ( '<' | '>' | '=' | '<=' | '>=' | '<>' ) a_expr_like
        | subquery_Op ('ANY' | 'SOME' | 'ALL') ( select_with_parens | '(' a_expr ')')
      )?
    ;

a_expr_like
    : a_expr_qual_op ( 'NOT'? ( 'LIKE' | 'ILIKE' | 'SIMILAR' 'TO' ) a_expr_qual_op ('ESCAPE' a_expr)? )?
    ;

a_expr_qual_op
    : a_expr_unary_qualop ( qual_op a_expr_unary_qualop )*
    ;

a_expr_unary_qualop
    : qual_op? a_expr_add
    ;

a_expr_add
    : a_expr_mul ( ( '-' | '+' ) a_expr_mul )*
    ;

a_expr_mul
    : a_expr_caret ( ( '*' | '/' | '%' ) a_expr_caret )*
    ;

a_expr_caret
    : a_expr_unary_sign ( '^' a_expr_unary_sign )?
    ;

a_expr_unary_sign
    : ( '-' | '+' )? a_expr_at_time_zone
    ;

a_expr_at_time_zone
    : c_expr ( '::' typename )* ( 'COLLATE' qname )? ( 'AT' 'TIME' 'ZONE' a_expr )?
    ;

b_expr
    : c_expr
    | b_expr '::' typename
    //right	unary plus, unary minus
    | ( '+' | '-' ) b_expr
    //^	left	exponentiation
    | b_expr '^' b_expr
    //* / %	left	multiplication, division, modulo
    | b_expr ( '*' | '/' | '%' ) b_expr
    //+ -	left	addition, subtraction
    | b_expr ( '+' | '-' ) b_expr
    //( any other operator)	left	all other native and user-defined operators
    | b_expr qual_op b_expr
    //< > = <= >= <>	 	comparison operators
    | b_expr ( '<' | '>' | '=' | '<=' | '>=' | '<>' ) b_expr
    | qual_op b_expr
    | b_expr qual_op
    //S 'ISNULL' 'NOTNULL'	 	'IS' TRUE, 'IS' FALSE, 'IS' NULL, 'IS' 'DISTINCT' FROM, etc
    | b_expr 'IS' 'NOT'? ( 'DISTINCT' 'FROM' b_expr | 'OF' '(' type_list ')' | 'DOCUMENT' )
    ;

c_expr
    : 'EXISTS' select_with_parens                                        # c_expr_exists
    | 'ARRAY' ( select_with_parens | array_expr )                          # c_expr_expr
    | PARAM indirection_el*                                            # c_expr_expr
    | 'GROUPING' '(' expr_list ')'                        # c_expr_expr
    | 'UNIQUE' select_with_parens                                 # c_expr_expr
    | qname                                                        # c_expr_expr
    | literal                                                       # c_expr_expr
    | '(' a_expr_in_parens = a_expr ')' indirection_el* # c_expr_expr
    | case_expr                                                        # c_expr_case
    | func_expr                                                        # c_expr_expr
    | select_with_parens indirection_el*                                  # c_expr_expr
    | 'ROW' '(' expr_list? ')'                                                     # c_expr_expr
    | '(' expr_list ',' a_expr ')'                                                     # c_expr_expr
    | row 'OVERLAPS' row                               # c_expr_expr
    | 'DEFAULT'                                                          # c_expr_expr
    ;

func_application
    : qname
    '(' (
        func_arg_list
        ( ',' 'VARIADIC' func_arg_expr )? orderBy?
        | 'VARIADIC' func_arg_expr orderBy?
        | allDistinct func_arg_list orderBy?
        | '*'
      )?
    ')'
    ;

func_expr
    : func_application ('WITHIN' 'GROUP' '(' orderBy ')')? ('FILTER' '(' 'WHERE' a_expr ')')? ('OVER' ( window_specification | id ))?
    | func_expr_common_subexpr
    ;

func_expr_windowless
    : func_application
    | func_expr_common_subexpr
    ;

func_expr_common_subexpr
    : 'COLLATION' 'FOR' '(' a_expr ')'

    | 'CURRENT_DATE'
    | 'CURRENT_TIME' ( '(' integer ')' )?
    | 'CURRENT_TIMESTAMP' ( '(' integer ')' )?
    | 'LOCALTIME' ( '(' integer ')' )?
    | 'LOCALTIMESTAMP' ( '(' integer ')' )?

    | 'CURRENT_ROLE'
    | 'CURRENT_USER'
    | 'SESSION_USER'
    | 'SYSTEM_USER'
    | 'USER'

    | 'CURRENT_CATALOG'
    | 'CURRENT_SCHEMA'

    | 'CAST' '(' a_expr 'AS' typename ')'
    | 'EXTRACT' '(' (extract_arg 'FROM' a_expr)? ')'
    | 'NORMALIZE' '(' a_expr ( ',' unicode_normal_form )? ')'
    | 'OVERLAY' '(' ( overlay_list | func_arg_list? ) ')'
    | 'POSITION' '(' (b_expr 'IN' b_expr)? ')'
    | 'SUBSTRING' '(' ( substr_list | func_arg_list? ) ')'
    | 'TREAT' '(' a_expr 'AS' typename ')'
    | 'TRIM' '(' ( 'BOTH' | 'LEADING' | 'TRAILING' )? ( a_expr? 'FROM' )? expr_list ')'
    | 'NULLIF' '(' a_expr ',' a_expr ')'
    | 'COALESCE' '(' expr_list ')'
    | 'GREATEST' '(' expr_list ')'
    | 'LEAST' '(' expr_list ')'

    | 'XMLCONCAT' '(' expr_list ')'
    | 'XMLELEMENT' '(' 'NAME' id ( ',' ( 'XMLATTRIBUTES' '(' xml_attribute_el ( ',' xml_attribute_el )* ')' | expr_list ) )? ')'
    | 'XMLEXISTS' '(' c_expr xmlexists_argument ')'
    | 'XMLFOREST' '(' xml_attribute_el ( ',' xml_attribute_el )* ')'
    | 'XMLPARSE' '(' document_or_content a_expr ('PRESERVE' | 'STRIP') 'WHITESPACE'? ')'
    | 'XMLPI' '(' 'NAME' id ( ',' a_expr )? ')'
    | 'XMLROOT' '(' 'XML' a_expr ',' 'VERSION' (a_expr |  'NO' 'VALUE') (',' 'STANDALONE' ( 'YES' | 'NO' 'VALUE'? ))? ')'
    | 'XMLSERIALIZE' '(' document_or_content a_expr 'AS' simpletypename ')'

    | 'JSON' '(' json_value_expr json_key_uniqueness_constraint? ')'
    | 'JSON_ARRAY' '(' ( json_value_expr (',' json_value_expr)* json_object_constructor_null_clause? json_returning_clause? | select_no_parens ('FORMAT_LA' 'JSON' ( 'ENCODING' id )?)? json_returning_clause? | json_returning_clause? ) ')'
    | 'JSON_EXISTS' '(' json_value_expr ',' a_expr json_passing_clause? jsonOnError? ')'
    | 'JSON_OBJECT' '(' ( func_arg_list | json_name_and_value (',' json_name_and_value)* json_object_constructor_null_clause? json_key_uniqueness_constraint? json_returning_clause? | json_returning_clause? ) ')'
    | 'JSON_QUERY' '(' json_value_expr ',' a_expr json_passing_clause? json_returning_clause? json_wrapper_behavior? ( 'KEEP' | 'OMIT' ) 'QUOTES' ( 'ON' 'SCALAR' 'STRING' )?? jsonOnEmpty? jsonOnError? ')'
    | 'JSON_SCALAR' '(' a_expr ')'
    | 'JSON_SERIALIZE' '(' json_value_expr json_returning_clause? ')'
    | 'JSON_VALUE' '(' json_value_expr ',' a_expr json_passing_clause? json_returning_clause? jsonOnEmpty? jsonOnError? ')'
    | 'MERGE_ACTION' '(' ')'
    ;

xml_attribute_el
    : a_expr ( 'AS' id )?
    ;

document_or_content
    : 'DOCUMENT'
    | 'CONTENT'
    ;

xmlexists_argument
    : 'PASSING' xml_passing_mech? c_expr xml_passing_mech?
    ;

xml_passing_mech
    : 'BY' ( 'REF' | 'VALUE' )
    ;

window_clause
    : 'WINDOW' window_definition ( ',' window_definition )*
    ;

window_definition
    : id 'AS' window_specification
    ;

window_specification
    : '(' id? ('PARTITION' 'BY' expr_list)? orderBy? frame_clause_? ')'
    ;

frame_clause_
    : ( 'RANGE' | 'ROWS' | 'GROUPS' )
      ( frame_bound | 'BETWEEN' frame_bound 'AND' frame_bound )
      ( 'EXCLUDE' ( 'CURRENT' 'ROW' | 'GROUP' | 'TIES' | 'NO' 'OTHERS' ))?
    ;

frame_bound
    : 'UNBOUNDED' ( 'PRECEDING' | 'FOLLOWING' )
    | 'CURRENT' 'ROW'
    | a_expr ( 'PRECEDING' | 'FOLLOWING' )
    ;

row
    : 'ROW' '(' expr_list? ')'
    | '(' expr_list ',' a_expr ')'
    ;

mathop
    : '+'
    | '-'
    | '*'
    | '/'
    | '%'
    | '^'
    | '<'
    | '>'
    | '='
    | '<='
    | '>='
    | '<>'
    ;

qual_op
    : Operator
//    | '||'
//    | '->'
//    | '->>'
//    | '#-'
//    | '#>'
//    | '#>>'
//    | '!='
//    | '~'
//    | '@'
//    | '@@'
//    | '@>'
//    | '<@'
//    | '|/'
//    | '||/'
//    | '*<'
//    | '*<='
//    | '*<>'
//    | '*>='
//    | '*>'
//    | '*='
//    | '&'
//    | '&&'
//    | '&<'
//    | '&>'
//    | '?'
//    | '!!'
//    | '|'
//    | '-|-'
//    | '==='

    | 'OPERATOR' '(' any_operator ')'
    ;

qual_all_op
    : (Operator | mathop)
    | 'OPERATOR' '(' any_operator ')'
    ;

subquery_Op
    : (Operator | mathop)
    | 'OPERATOR' '(' any_operator ')'
    | 'LIKE'
    | 'NOT' 'LIKE'
    | 'ILIKE'
    | 'NOT' 'ILIKE'
    ;

expr_list
    : a_expr ( ',' a_expr )*
    ;

func_arg_list
    : func_arg_expr ( ',' func_arg_expr )*
    ;

func_arg_expr
    : a_expr
    | id ( ':=' | '=>' ) a_expr
    ;

type_list
    : typename ( ',' typename )*
    ;

array_expr
    : '[' ( expr_list | array_expr_list )? ']'
    ;

array_expr_list
    : array_expr ( ',' array_expr )*
    ;

extract_arg
    : identifier
    | 'YEAR'
    | 'MONTH'
    | 'DAY'
    | 'HOUR'
    | 'MINUTE'
    | 'SECOND'
    | string
    ;

unicode_normal_form
    : 'NFC'
    | 'NFD'
    | 'NFKC'
    | 'NFKD'
    ;

overlay_list
    : a_expr 'PLACING' a_expr 'FROM' a_expr ( 'FOR' a_expr )?
    ;

substr_list
    : a_expr 'FROM' a_expr 'FOR' a_expr
    | a_expr 'FOR' a_expr 'FROM' a_expr
    | a_expr 'FROM' a_expr
    | a_expr 'FOR' a_expr
    | a_expr 'SIMILAR' a_expr 'ESCAPE' a_expr
    ;

in_expr
    : select_with_parens               # in_expr_select
    | '(' expr_list ')' # in_expr_list
    ;

case_expr
    : 'CASE' (a_expr)? when_clause+ case_default? 'END'
    ;

when_clause
    : 'WHEN' a_expr 'THEN' a_expr
    ;

case_default
    : 'ELSE' a_expr
    ;

indirection_el
    : '.' ( id | '*' )
    | '[' ( a_expr | a_expr? ':' a_expr? ) ']'
    ;

json_passing_clause
: 'PASSING' json_argument (',' json_argument)* ;

json_argument
  : json_value_expr 'AS' id ;

json_wrapper_behavior
  : ( 'WITHOUT' | 'WITH' ( 'UNCONDITIONAL' | 'CONDITIONAL' )? ) 'ARRAY'? 'WRAPPER'
  ;

json_behavior
  : 'DEFAULT' a_expr
  | ('ERROR'
    | 'NULL'
    | 'TRUE'
    | 'FALSE'
    | 'UNKNOWN'
    | 'EMPTY' ( 'ARRAY'|'OBJECT' )?
    )
  ;

jsonOnEmpty : json_behavior 'ON' 'EMPTY' ;

jsonOnError:
			json_behavior 'ON' 'ERROR'
		;

json_value_expr:
			a_expr ('FORMAT_LA' 'JSON' ( 'ENCODING' id )?)?
		;

json_returning_clause:
			'RETURNING' typename ('FORMAT_LA' 'JSON' ( 'ENCODING' id )?)?
		;

//json_predicate_type_constraint:
//			'JSON'
//			| 'JSON' 'VALUE'
//			| 'JSON' 'ARRAY'
//			| 'JSON' 'OBJECT'
//			| 'JSON' 'SCALAR'
//		;

json_key_uniqueness_constraint:
			('WITH' | 'WITHOUT') 'UNIQUE' 'KEYS'?
		;

json_name_and_value:
			c_expr 'VALUE' json_value_expr
			|
			a_expr ':' json_value_expr
		;

json_object_constructor_null_clause
  : ( 'NULL' | 'ABSENT' ) 'ON' 'NULL' ;

//json_aggregate_func:
//			'JSON_OBJECTAGG' '('
//				json_name_and_value
//				json_object_constructor_null_clause?
//				json_key_uniqueness_constraint?
//				json_returning_clause
//			')'
//			| 'JSON_ARRAYAGG' '('
//				json_value_expr
//				json_array_aggregate_order_by_clause?
//				json_object_constructor_null_clause?
//				json_returning_clause
//			')'
//		;

//json_array_aggregate_order_by_clause:
//			'ORDER' 'BY' sortby ( ',' sortby  )*
//		;

target_list
    : target_el ( ',' target_el )*
    ;

target_el
    : a_expr ( 'AS' id | identifier | bare_label_keyword )?
    | '*'
    ;

qnames
    : qname ( ',' qname )*
    ;

qname
    : name indirection_el*
    ;

columns
    : '(' id ( ',' id )* ')'
    ;

ids
    : id ( ',' id )*
    ;

literal
    : integer
    | FLOAT
    | string
    | BinaryStringConstant
    | HexadecimalStringConstant
    | qname ( string | '(' func_arg_list orderBy? ')' string )
    | consttypename string
    | 'INTERVAL' ( string timeUnit? | '(' integer ')' string )
    | 'TRUE'
    | 'FALSE'
    | 'NULL'
    ;

integer
    : DECIMAL
    | BINARY
    | OCTAL
    | HEXIDECIMAL
    ;

string
  : string_ ( 'UESCAPE' string_ )? ;

string_
  : StringConstant
  | UnicodeEscapeStringConstant
  ;

signedDecimal
  : ('+' | '-')? integer ;

signedFloat
  : ('+' | '-')? FLOAT ;


id
  : identifier
  | unreserved_keyword
  | col_name_keyword
  | type_func_name_keyword
  | reserved_keyword
  ;

/*
 * Keyword category lists.  Generally, every keyword present in
 * the Postgres grammar should appear in exactly one of these lists.
 *
 * Put a new keyword into the first list that it can go into without causing
 * shift or reduce conflicts.  The earlier lists define "less reserved"
 * categories of keywords.
 *
 * Make sure that each keyword's category in kwlist.h matches where
 * it is listed here.  ( Someday we may be able to generate these lists and
 * kwlist.h's table from one source of truth.)
 */

/* "Unreserved" keywords --- available for use as any kind of name.
 */
unreserved_keyword
    : 'ABORT'
    | 'ABSENT'
    | 'ABSOLUTE'
    | 'ACCESS'
    | 'ACTION'
    | 'ADD'
    | 'ADMIN'
    | 'AFTER'
    | 'AGGREGATE'
    | 'ALSO'
    | 'ALTER'
    | 'ALWAYS'
    | 'ASENSITIVE'
    | 'ASSERTION'
    | 'ASSIGNMENT'
    | 'AT'
    | 'ATOMIC'
    | 'ATTACH'
    | 'ATTRIBUTE'
    | 'BACKWARD'
    | 'BEFORE'
    | 'BEGIN'
    | 'BREADTH'
    | 'BY'
    | 'CACHE'
    | 'CALL'
    | 'CALLED'
    | 'CASCADE'
    | 'CASCADED'
    | 'CATALOG'
    | 'CHAIN'
    | 'CHARACTERISTICS'
    | 'CHECKPOINT'
    | 'CLASS'
    | 'CLOSE'
    | 'CLUSTER'
    | 'COLUMNS'
    | 'COMMENT'
    | 'COMMENTS'
    | 'COMMIT'
    | 'COMMITTED'
    | 'COMPRESSION'
    | 'CONDITIONAL'
    | 'CONFIGURATION'
    | 'CONFLICT'
    | 'CONNECTION'
    | 'CONSTRAINTS'
    | 'CONTENT'
    | 'CONTINUE'
    | 'CONVERSION'
    | 'COPY'
    | 'COST'
    | 'CSV'
    | 'CUBE'
    | 'CURRENT'
    | 'CURSOR'
    | 'CYCLE'
    | 'DATA'
    | 'DATABASE'
    | 'DAY'
    | 'DEALLOCATE'
    | 'DECLARE'
    | 'DEFAULTS'
    | 'DEFERRED'
    | 'DEFINER'
    | 'DELETE'
    | 'DELIMITER'
    | 'DELIMITERS'
    | 'DEPENDS'
    | 'DEPTH'
    | 'DETACH'
    | 'DICTIONARY'
    | 'DISABLE'
    | 'DISCARD'
    | 'DOCUMENT'
    | 'DOMAIN'
    | 'DOUBLE'
    | 'DROP'
    | 'EACH'
    | 'EMPTY'
    | 'ENABLE'
    | 'ENCODING'
    | 'ENCRYPTED'
    | 'ENUM'
    | 'ERROR'
    | 'ESCAPE'
    | 'EVENT'
    | 'EXCLUDE'
    | 'EXCLUDING'
    | 'EXCLUSIVE'
    | 'EXECUTE'
    | 'EXPLAIN'
    | 'EXPRESSION'
    | 'EXTENSION'
    | 'EXTERNAL'
    | 'FAMILY'
    | 'FILTER'
    | 'FINALIZE'
    | 'FIRST'
    | 'FOLLOWING'
    | 'FORCE'
    | 'FORMAT'
    | 'FORWARD'
    | 'FUNCTION'
    | 'FUNCTIONS'
    | 'GENERATED'
    | 'GLOBAL'
    | 'GRANTED'
    | 'GROUPS'
    | 'HANDLER'
    | 'HEADER'
    | 'HOLD'
    | 'HOUR'
    | 'IDENTITY'
    | 'IF'
    | 'IMMEDIATE'
    | 'IMMUTABLE'
    | 'IMPLICIT'
    | 'IMPORT'
    | 'INCLUDE'
    | 'INCLUDING'
    | 'INCREMENT'
    | 'INDENT'
    | 'INDEX'
    | 'INDEXES'
    | 'INHERIT'
    | 'INHERITS'
    | 'INLINE'
    | 'INPUT'
    | 'INSENSITIVE'
    | 'INSERT'
    | 'INSTEAD'
    | 'INVOKER'
    | 'ISOLATION'
    | 'KEEP'
    | 'KEY'
    | 'KEYS'
    | 'LABEL'
    | 'LANGUAGE'
    | 'LARGE'
    | 'LAST'
    | 'LEAKPROOF'
    | 'LEVEL'
    | 'LISTEN'
    | 'LOAD'
    | 'LOCAL'
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
    | 'METHOD'
    | 'MINUTE'
    | 'MINVALUE'
    | 'MODE'
    | 'MONTH'
    | 'MOVE'
    | 'NAME'
    | 'NAMES'
    | 'NESTED'
    | 'NEW'
    | 'NEXT'
    | 'NFC'
    | 'NFD'
    | 'NFKC'
    | 'NFKD'
    | 'NO'
    | 'NORMALIZED'
    | 'NOTHING'
    | 'NOTIFY'
    | 'NOWAIT'
    | 'NULLS'
    | 'OBJECT'
    | 'OF'
    | 'OFF'
    | 'OIDS'
    | 'OLD'
    | 'OMIT'
    | 'OPERATOR'
    | 'OPTION'
    | 'OPTIONS'
    | 'ORDINALITY'
    | 'OTHERS'
    | 'OVER'
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
    | 'PLAN'
    | 'PLANS'
    | 'POLICY'
    | 'PRECEDING'
    | 'PREPARE'
    | 'PREPARED'
    | 'PRESERVE'
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
    | 'REASSIGN'
//    | 'RECHECK'
    | 'RECURSIVE'
    | 'REF'
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
    | 'RETURNS'
    | 'REVOKE'
    | 'ROLE'
    | 'ROLLBACK'
    | 'ROLLUP'
    | 'ROUTINE'
    | 'ROUTINES'
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
    | 'SEQUENCE'
    | 'SEQUENCES'
    | 'SERIALIZABLE'
    | 'SERVER'
    | 'SESSION'
    | 'SET'
    | 'SETS'
    | 'SHARE'
    | 'SHOW'
    | 'SIMPLE'
    | 'SKIP'
    | 'SNAPSHOT'
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
    | 'SUPPORT'
    | 'SYSID'
    | 'SYSTEM'
    | 'TABLES'
    | 'TABLESPACE'
    | 'TARGET'
    | 'TEMP'
    | 'TEMPLATE'
    | 'TEMPORARY'
    | 'TEXT'
    | 'TIES'
    | 'TRANSACTION'
    | 'TRANSFORM'
    | 'TRIGGER'
    | 'TRUNCATE'
    | 'TRUSTED'
    | 'TYPE'
    | 'TYPES'
    | 'UESCAPE'
    | 'UNBOUNDED'
    | 'UNCOMMITTED'
    | 'UNCONDITIONAL'
    | 'UNENCRYPTED'
    | 'UNKNOWN'
    | 'UNLISTEN'
    | 'UNLOGGED'
    | 'UNTIL'
    | 'UPDATE'
    | 'VACUUM'
    | 'VALID'
    | 'VALIDATE'
    | 'VALIDATOR'
    | 'VALUE'
    | 'VARYING'
    | 'VERSION'
    | 'VIEW'
    | 'VIEWS'
    | 'VOLATILE'
    | 'WHITESPACE'
    | 'WITHIN'
    | 'WITHOUT'
    | 'WORK'
    | 'WRAPPER'
    | 'WRITE'
    | 'XML'
    | 'YEAR'
    | 'YES'
    | 'ZONE'
    ;

/* Column identifier --- keywords that can be column, table, etc names.
 *
 * Many of these keywords will in fact be recognized as type or function
 * names too; but they have special productions for the purpose, and so
 * can't be treated as "generic" type or function names.
 *
 * The type names appearing here are not usable as function names
 * because they can be followed by '(' in typename productions, which
 * looks too much like a function call for an LR( 1) parser.
 */
col_name_keyword
    : 'BETWEEN'
    | 'BIGINT'
    | 'BIT'
    | 'BOOLEAN'
    | 'CHAR'
    | character
    | 'COALESCE'
    | 'DEC'
    | 'DECIMAL'
    | 'EXISTS'
    | 'EXTRACT'
    | 'FLOAT'
    | 'GREATEST'
    | 'GROUPING'
    | 'INOUT'
    | 'INT'
    | 'INTEGER'
    | 'INTERVAL'
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
    | 'LEAST'
    | 'MERGE_ACTION'
    | 'NATIONAL'
    | 'NCHAR'
    | 'NONE'
    | 'NORMALIZE'
    | 'NULLIF'
    | 'NUMERIC'
    | 'OUT'
    | 'OVERLAY'
    | 'POSITION'
    | 'PRECISION'
    | 'REAL'
    | 'ROW'
    | 'SETOF'
    | 'SMALLINT'
    | 'SUBSTRING'
    | 'TIME'
    | 'TIMESTAMP'
    | 'TREAT'
    | 'TRIM'
    | 'VALUES'
    | 'VARCHAR'
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
    ;

/* Type/function identifier --- keywords that can be type or function names.
 *
 * Most of these are keywords that are used as operators in expressions;
 * in general such keywords can't be column names because they would be
 * ambiguous with variables, but they are unambiguous as function identifiers.
 *
 * Do not include POSITION, SUBSTRING, etc here since they have explicit
 * productions in a_expr to support the goofy SQL9x argument syntax.
 * - thomas 2000-11-28
 */
type_func_name_keyword
    : 'AUTHORIZATION'
    | 'BINARY'
    | 'COLLATION'
    | 'CONCURRENTLY'
    | 'CROSS'
    | 'CURRENT_SCHEMA'
    | 'FREEZE'
    | 'FULL'
    | 'ILIKE'
    | 'INNER'
    | 'IS'
    | 'ISNULL'
    | 'JOIN'
    | 'LEFT'
    | 'LIKE'
    | 'NATURAL'
    | 'NOTNULL'
    | 'OUTER'
    | 'OVERLAPS'
    | 'RIGHT'
    | 'SIMILAR'
    | 'TABLESAMPLE'
    | 'VERBOSE'
    ;

/* Reserved keyword --- these keywords are usable only as a name.
 *
 * Keywords appear here if they could not be distinguished from variable,
 * type, or function names in some contexts.  Don't put things here unless
 * forced to.
 */
reserved_keyword
    : 'ALL'
    | ANALYZE
    | 'AND'
    | 'ANY'
    | 'ARRAY'
    | 'AS'
    | 'ASC'
    | 'ASYMMETRIC'
    | 'BOTH'
    | 'CASE'
    | 'CAST'
    | 'CHECK'
    | 'COLLATE'
    | 'COLUMN'
    | 'CONSTRAINT'
    | 'CREATE'
    | 'CURRENT_CATALOG'
    | 'CURRENT_DATE'
    | 'CURRENT_ROLE'
    | 'CURRENT_TIME'
    | 'CURRENT_TIMESTAMP'
    | 'CURRENT_USER'
    | 'DEFAULT'
    | 'DEFERRABLE'
    | 'DESC'
    | 'DISTINCT'
    | 'DO'
    | 'ELSE'
    | 'END'
    | 'EXCEPT'
    | 'FALSE'
    | 'FETCH'
    | 'FOR'
    | 'FOREIGN'
    | 'FROM'
    | 'GRANT'
    | 'GROUP'
    | 'HAVING'
    | 'IN'
    | 'INITIALLY'
    | 'INTERSECT'
    | 'INTO'
    | 'LATERAL'
    | 'LEADING'
    | 'LIMIT'
    | 'LOCALTIME'
    | 'LOCALTIMESTAMP'
    | 'NOT'
    | 'NULL'
    | 'OFFSET'
    | 'ON'
    | 'ONLY'
    | 'OR'
    | 'ORDER'
    | 'PLACING'
    | 'PRIMARY'
    | 'REFERENCES'
    | 'RETURNING'
    | 'SELECT'
    | 'SESSION_USER'
    | 'SOME'
    | 'SYMMETRIC'
    | 'SYSTEM_USER'
    | 'TABLE'
    | 'THEN'
    | 'TO'
    | 'TRAILING'
    | 'TRUE'
    | 'UNION'
    | 'UNIQUE'
    | 'USER'
    | 'USING'
    | 'VARIADIC'
    | 'WHEN'
    | 'WHERE'
    | 'WINDOW'
    | 'WITH'
    ;

/*
 * While all keywords can be used as column labels when preceded by AS,
 * not all of them can be used as a "bare" column label without AS.
 * Those that can be used as a bare label must be listed here,
 * in addition to appearing in one of the category lists above.
 *
 * Always add a new keyword to this list if possible.  Mark it 'BARE_LABEL'
 * in kwlist.h if it is included here, or 'AS_LABEL' if it is not.
 */
bare_label_keyword
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
    | 'CROSS'
    | 'CSV'
    | 'CUBE'
    | 'CURRENT_CATALOG'
    | 'CURRENT_DATE'
    | 'CURRENT'
    | 'CURRENT_ROLE'
    | 'CURRENT_SCHEMA'
    | 'CURRENT_TIME'
    | 'CURRENT_TIMESTAMP'
    | 'CURRENT_USER'
    | 'CURSOR'
    | 'CYCLE'
    | 'DATA'
    | 'DATABASE'
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
    | 'FINALIZE'
    | 'FIRST'
    | 'FLOAT'
    | 'FOLLOWING'
    | 'FORCE'
    | 'FOREIGN'
    | 'FORMAT'
    | 'FORWARD'
    | 'FREEZE'
    | 'FULL'
    | 'FUNCTION'
    | 'FUNCTIONS'
    | 'GENERATED'
    | 'GLOBAL'
    | 'GRANTED'
    | 'GREATEST'
    | 'GROUPING'
    | 'GROUPS'
    | 'HANDLER'
    | 'HEADER'
    | 'HOLD'
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
    | 'INTERVAL'
    | 'INVOKER'
    | 'IS'
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
    | 'MINVALUE'
    | 'MODE'
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
    | 'NOWAIT'
    | 'NULL'
    | 'NULLIF'
    | 'NULLS'
    | 'NUMERIC'
    | 'OBJECT'
    | 'OF'
    | 'OFF'
    | 'OIDS'
    | 'OLD'
    | 'OMIT'
    | 'ONLY'
    | 'OPERATOR'
    | 'OPTION'
    | 'OPTIONS'
    | 'OR'
    | 'ORDINALITY'
    | 'OTHERS'
    | 'OUT'
    | 'OUTER'
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
    | 'VERBOSE'
    | 'VERSION'
    | 'VIEW'
    | 'VIEWS'
    | 'VOLATILE'
    | 'WHEN'
    | 'WHITESPACE'
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
    | 'YES'
    | 'ZONE'
    ;

identifier
    : Identifier ( 'UESCAPE' string_ )?
    | QuotedIdentifier
    | UnicodeQuotedIdentifier
    | PLSQLVARIABLENAME
    ;

//noise : ANALYZE ;
noise2 : '(' 'SELECT' . ')' EOF;


ANALYZE
    : 'ANALYZE' | 'ANALYSE' ;

PARAM: '$' ([0-9])+;
//PARAM: [:$] ([0-9])+;
// Postgres Lexical Structure Operators 4.1.3
// https://www.postgresql.org/docs/current/sql-syntax-lexical.html#SQL-SYNTAX-OPERATORS

// FIXME pattern matching fails on edge cases, switch to mode
// FIXME discern comments /* and -- from operators
// FIXME disallow '+' ending (per rules)
Operator
//  ( '+' | [-/<>=~!@#%^&|`?] | '*' )* ( [-/<>=~!@#%^&|`?] | '*' )
  : (  [<>=~!@#%^&|`?] | '*' )+
  | '<->'
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
  ;

Identifier
  : [A-Z_\u007F-\uFFFF] [A-Z_$0-9\u007F-\uFFFF]*;

QuotedIdentifier
  : '"' ('""' | ~ [\u0000"])* '"';

UnicodeQuotedIdentifier
  : 'U' '&' QuotedIdentifier;

StringConstant
  : '\'' ('\'\'' | ~ '\'')* '\''
  // FIXME nested dollar quoted strings
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

BinaryStringConstant: 'B' '\'' [01]* '\'';

HexadecimalStringConstant: 'X' '\'' [0-9A-F]* '\'';

DECIMAL
  : DIGITS ;

BINARY
  : '0b' [01]+ ;

OCTAL: '0o' [0-7]+ ;

HEXIDECIMAL: '0x' [A-F0-9]+;

FLOAT
  :
    DIGITS '.' DIGITS?
    (
        'E' [+-]? DIGITS
    )?
    | '.' DIGITS ('E' [+-]? DIGITS)?
    | DIGITS 'E' [+-]? DIGITS
;

PLSQLVARIABLENAME: ':' [A-Z_\u007F-\uFFFF] [A-Z_$0-9\u007F-\uFFFF]*;

//PLSQLIDENTIFIER: ':"' ('\\' . | '""' | ~ ('"' | '\\'))* '"';


LineComment
  : '--' ~ [\r\n]* -> channel (HIDDEN) ;

BlockComment
  : ('/*' ('/'* BlockComment | ~ [/*] | '/'+ ~ [/*] | '*'+ ~ [/*])* '*'* '*/') -> channel (HIDDEN) ;

SEMI
  : ';'   | '\\' ';' ;

META
  : '\\' ~[;]? ~[\r\n\\]* -> type( SEMI ) ;

Whitespace
  : [ \t]+ -> channel (HIDDEN) ;

Newline
  : ('\r' '\n'? | '\n') -> channel (HIDDEN) ;

UNKNOWN
  : . ;

fragment DIGITS: [0-9]+;

fragment TAG
  : '$' ( [A-Z_\u007F-\uFFFF] [A-Z_0-9\u007F-\uFFFF]* )? '$' ;
