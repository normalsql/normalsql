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

parse
  : statement? ( ';' statement? )* EOF
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
    | altertblspcstmt
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
    | createasstmt
    | createassertionstmt
    | createcaststmt
    | createconversionstmt
    | createdomainstmt
    | createextensionstmt
    | createfdwstmt
    | createforeignserverstmt
    | createforeigntablestmt
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
    | createstmt
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
    | deletestmt
    | discardstmt
    | dostmt
    | dropcaststmt
    | dropopclassstmt
    | dropopfamilystmt
    | dropownedstmt
    | dropstmt
    | dropsubscriptionstmt
    | droptablespacestmt
    | droptransformstmt
    | droprolestmt
    | dropusermappingstmt
    | dropdbstmt
    | executestmt
    | explainstmt
    | fetchstmt
    | grantstmt
    | grantrolestmt
    | importforeignschemastmt
    | indexstmt
    | insertstmt
    | mergestmt
    | listenstmt
    | refreshmatviewstmt
    | loadstmt
    | lockstmt
    | notifystmt
    | preparestmt
    | reassignownedstmt
    | reindexstmt
    | removeaggrstmt
    | removefuncstmt
    | removeoperstmt
    | renamestmt
    | revokestmt
    | revokerolestmt
    | rulestmt
    | seclabelstmt
    | selectstmt
    | transactionstmt
    | truncatestmt
    | unlistenstmt
    | updatestmt
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
    : 'CREATE' 'ROLE' rolespec 'WITH'? createoptroleelem*
    ;

alteroptroleelem
    : 'PASSWORD' ( string | 'NULL' )
    | ( 'ENCRYPTED' | 'UNENCRYPTED' ) 'PASSWORD' string
    | 'INHERIT'
    | 'CONNECTION' 'LIMIT' signedInteger
    | 'VALID' 'UNTIL' string
    | 'USER' role_list
    | identifier
    ;

createoptroleelem
    : alteroptroleelem
    | 'SYSID' integer
    | 'ADMIN' role_list
    | 'ROLE' role_list
    | 'IN' ( 'ROLE' | 'GROUP' ) role_list
    ;

createuserstmt
    : 'CREATE' 'USER' rolespec 'WITH'? createoptroleelem*
    ;

alterrolestmt
    : 'ALTER' ( 'ROLE' | 'USER' ) rolespec 'WITH'? alteroptroleelem*
    ;

alterrolesetstmt
    : 'ALTER' ( 'ROLE' | 'USER' ) 'ALL'? rolespec ('IN' 'DATABASE' name)? setresetclause
    ;

droprolestmt
    : 'DROP' ( 'ROLE' | 'USER' | 'GROUP' ) ifExists? role_list
    ;

creategroupstmt
    : 'CREATE' 'GROUP' rolespec 'WITH'? createoptroleelem*
    ;

altergroupstmt
    : 'ALTER' 'GROUP' rolespec add_drop 'USER' role_list
    ;

add_drop
    : 'ADD'
    | 'DROP'
    ;

createschemastmt
    : 'CREATE' 'SCHEMA' ifNotExists? ( name? 'AUTHORIZATION' rolespec | name ) (createstmt
    | indexstmt
    | createseqstmt
    | createtrigstmt
    | grantstmt
    | viewstmt)*
    ;

variablesetstmt
    : 'SET' ( 'LOCAL' | 'SESSION' )? set_rest
    ;

set_rest
    : 'TRANSACTION' transaction_mode_list
    | 'SESSION' 'CHARACTERISTICS' 'AS' 'TRANSACTION' transaction_mode_list
    | set_rest_more
    ;

generic_set
    : name ( 'TO' | '=' ) ( var_list | 'DEFAULT' )
    ;

set_rest_more
    : generic_set
    | name 'FROM' 'CURRENT'
    | 'TIME' 'ZONE' zone_value
    | 'CATALOG' string
    | 'SCHEMA' string
    | 'NAMES' encoding_?
    | 'ROLE' nonreservedword_or_sconst
    | 'SESSION' 'AUTHORIZATION' nonreservedword_or_sconst
    | 'XML' 'OPTION' document_or_content
    | 'TRANSACTION' 'SNAPSHOT' string
    ;


var_list
    : var_value ( ',' var_value )*
    ;

var_value
    : boolean_or_string_
    | numericonly
    ;

iso_level
    : 'READ' ( 'UNCOMMITTED' | 'COMMITTED' )
    | 'REPEATABLE' 'READ'
    | 'SERIALIZABLE'
    ;

boolean_or_string_
    : 'TRUE'
    | 'FALSE'
    | 'ON'
    | nonreservedword_or_sconst
    ;

zone_value
    : string
    | identifier
    | constinterval string interval_?
    | constinterval '(' integer ')' string
    | numericonly
    | 'DEFAULT'
    | 'LOCAL'
    ;

encoding_
    : string
    | 'DEFAULT'
    ;

nonreservedword_or_sconst
    : nonreservedword
    | string
    ;

variableresetstmt
    : 'RESET' (name
    | 'ALL'
    | 'TIME' 'ZONE'
    | 'TRANSACTION' 'ISOLATION' 'LEVEL'
    | 'SESSION' 'AUTHORIZATION')
    ;

setresetclause
    : 'SET' set_rest
    | variableresetstmt
    ;

variableshowstmt
    : 'SHOW' ( name | 'TIME' 'ZONE' | 'TRANSACTION' 'ISOLATION' 'LEVEL' | 'SESSION' 'AUTHORIZATION' | 'ALL' )
    ;

constraintssetstmt
    : 'SET' 'CONSTRAINTS' ('ALL' | qnames) ('DEFERRED' | 'IMMEDIATE')
    ;

discardstmt
    : 'DISCARD' ( 'ALL' | 'TEMP' | 'TEMPORARY' | 'PLANS' | 'SEQUENCES' )
    ;

altertablestmt
    : 'ALTER' 'TABLE' ifExists? relation_expr ( alter_table_cmds | ('ATTACH' 'PARTITION' qname partitionboundspec
    | 'DETACH' 'PARTITION' qname) )
    | 'ALTER' 'INDEX' ifExists? qname ( alter_table_cmds | 'ATTACH' 'PARTITION' qname )
    | 'ALTER' 'TABLE' 'ALL' 'IN' 'TABLESPACE' name ( 'OWNED' 'BY' role_list  )? 'SET' 'TABLESPACE' name ('NOWAIT')?
    | 'ALTER' 'INDEX' 'ALL' 'IN' 'TABLESPACE' name ( 'OWNED' 'BY' role_list )? 'SET' 'TABLESPACE' name ('NOWAIT')?
    | 'ALTER' 'SEQUENCE' ifExists? qname alter_table_cmds
    | 'ALTER' 'VIEW' ifExists? qname alter_table_cmds
    | 'ALTER' 'MATERIALIZED' 'VIEW' ifExists? qname alter_table_cmds
    | 'ALTER' 'MATERIALIZED' 'VIEW' 'ALL' 'IN' 'TABLESPACE' name ( 'OWNED' 'BY' role_list )? 'SET' 'TABLESPACE' name ('NOWAIT')?
    | 'ALTER' 'FOREIGN' 'TABLE' ifExists? relation_expr alter_table_cmds
    ;

alter_table_cmds
    : alter_table_cmd ( ',' alter_table_cmd )*
    ;

alter_table_cmd
    : 'ADD' columnDef
    | 'ADD' ifNotExists columnDef
    | 'ADD' 'COLUMN' columnDef
    | 'ADD' 'COLUMN' ifNotExists columnDef
    | 'ALTER' column_? name alter_column_default
    | 'ALTER' column_? name 'DROP' 'NOT' 'NULL'
    | 'ALTER' column_? name 'SET' 'NOT' 'NULL'
    | 'ALTER' column_? name 'DROP' 'EXPRESSION'
    | 'ALTER' column_? name 'DROP' 'EXPRESSION' 'IF' 'EXISTS'
    | 'ALTER' column_? name 'SET' 'STATISTICS' signedInteger
    | 'ALTER' column_? integer 'SET' 'STATISTICS' signedInteger
    | 'ALTER' column_? name 'SET' reloptions
    | 'ALTER' column_? name 'RESET' reloptions
    | 'ALTER' column_? name 'SET' 'STORAGE' name
    | 'ALTER' column_? name 'ADD' 'GENERATED' generated_when 'AS' 'IDENTITY' optparenthesizedseqoptlist?
    | 'ALTER' column_? name alter_identity_column_option_list
    | 'ALTER' column_? name 'DROP' 'IDENTITY'
    | 'ALTER' column_? name 'DROP' 'IDENTITY' 'IF' 'EXISTS'
    | 'DROP' column_? 'IF' 'EXISTS' name drop_behavior_?
    | 'DROP' column_? name drop_behavior_?
    | 'ALTER' column_? name set_data_? 'TYPE' typename collate_clause_? alter_using?
    | 'ALTER' column_? name alter_generic_options
    | 'ADD' tableconstraint
    | 'ALTER' 'CONSTRAINT' name constraintattributespec
    | 'VALIDATE' 'CONSTRAINT' name
    | 'DROP' 'CONSTRAINT' 'IF' 'EXISTS' name drop_behavior_?
    | 'DROP' 'CONSTRAINT' name drop_behavior_?
    | 'SET' 'WITHOUT' 'OIDS'
    | 'CLUSTER' 'ON' name
    | 'SET' 'WITHOUT' 'CLUSTER'
    | 'SET' 'LOGGED'
    | 'SET' 'UNLOGGED'
    | 'ENABLE' 'TRIGGER' name
    | 'ENABLE' 'ALWAYS' 'TRIGGER' name
    | 'ENABLE' 'REPLICA' 'TRIGGER' name
    | 'ENABLE' 'TRIGGER' 'ALL'
    | 'ENABLE' 'TRIGGER' 'USER'
    | 'DISABLE' 'TRIGGER' name
    | 'DISABLE' 'TRIGGER' 'ALL'
    | 'DISABLE' 'TRIGGER' 'USER'
    | 'ENABLE' 'RULE' name
    | 'ENABLE' 'ALWAYS' 'RULE' name
    | 'ENABLE' 'REPLICA' 'RULE' name
    | 'DISABLE' 'RULE' name
    | 'INHERIT' qname
    | 'NO' 'INHERIT' qname
    | 'OF' qname
    | 'NOT' 'OF'
    | 'OWNER' 'TO' rolespec
    | 'SET' 'TABLESPACE' name
    | 'SET' reloptions
    | 'RESET' reloptions
    | 'REPLICA' 'IDENTITY' replica_identity
    | 'ENABLE' 'ROW' 'LEVEL' 'SECURITY'
    | 'DISABLE' 'ROW' 'LEVEL' 'SECURITY'
    | 'FORCE' 'ROW' 'LEVEL' 'SECURITY'
    | 'NO' 'FORCE' 'ROW' 'LEVEL' 'SECURITY'
    | alter_generic_options
    ;

alter_column_default
    : 'SET' 'DEFAULT' a_expr
    | 'DROP' 'DEFAULT'
    ;

drop_behavior_
    : 'CASCADE'
    | 'RESTRICT'
    ;

collate_clause_
    : 'COLLATE' qname
    ;

alter_using
    : 'USING' a_expr
    ;

replica_identity
    : 'NOTHING'
    | 'FULL'
    | 'DEFAULT'
    | 'USING' 'INDEX' name
    ;

reloptions
    : '(' reloption_list ')'
    ;

reloptions_
    : 'WITH' reloptions
    ;

reloption_list
    : reloption_elem ( ',' reloption_elem )*
    ;

reloption_elem
    : colLabel ( '=' def_arg | '.' colLabel ( '=' def_arg )? )?
    ;

alter_identity_column_option_list
    : alter_identity_column_option+
    ;

alter_identity_column_option
    : 'RESTART' ( 'WITH'? numericonly )?
    | 'SET' ( seqoptelem | 'GENERATED' generated_when )
    ;

partitionboundspec
    : 'FOR' 'VALUES' 'WITH' '(' hash_partbound ')'
    | 'FOR' 'VALUES' 'IN' '(' expr_list ')'
    | 'FOR' 'VALUES' 'FROM' '(' expr_list ')' 'TO' '(' expr_list ')'
    | 'DEFAULT'
    ;

hash_partbound_elem
    : nonreservedword integer
    ;

hash_partbound
    : hash_partbound_elem ( ',' hash_partbound_elem )*
    ;

altercompositetypestmt
    : 'ALTER' 'TYPE' qname alter_type_cmds
    ;

alter_type_cmds
    : alter_type_cmd ( ',' alter_type_cmd )*
    ;

alter_type_cmd
    : 'ADD' 'ATTRIBUTE' tablefuncelement drop_behavior_?
    | 'DROP' 'ATTRIBUTE' ifExists? name drop_behavior_?
    | 'ALTER' 'ATTRIBUTE' name set_data_? 'TYPE' typename collate_clause_? drop_behavior_?
    ;

closeportalstmt
    : 'CLOSE' ( name | 'ALL' )
    ;

copystmt
    : 'COPY' ('BINARY')? qname (name_list_)? copy_from ('PROGRAM')? copy_file_name copy_delimiter? 'WITH'? copy_options where?
    | 'COPY' '(' preparablestmt ')' 'TO' ('PROGRAM')? copy_file_name 'WITH'? copy_options
    ;

copy_from
    : 'FROM'
    | 'TO'
    ;

copy_file_name
    : string
    | 'STDIN'
    | 'STDOUT'
    ;

copy_options
    : copy_opt_item*
    | '(' copy_generic_opt_list ')'
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
    | 'FORCE' 'QUOTE' names
    | 'FORCE' 'QUOTE' '*'
    | 'FORCE' 'NOT' 'NULL' names
    | 'FORCE' 'NULL' names
    | 'ENCODING' string
    ;

copy_delimiter
    : 'USING'? 'DELIMITERS' string
    ;

copy_generic_opt_list
    : copy_generic_opt_elem ( ',' copy_generic_opt_elem )*
    ;

copy_generic_opt_elem
    : colLabel copy_generic_opt_arg?
    ;

copy_generic_opt_arg
    : boolean_or_string_
    | numericonly
    | '*'
    | '(' copy_generic_opt_arg_list ')'

    ;

copy_generic_opt_arg_list
    : boolean_or_string_ ( ',' boolean_or_string_ )*
    ;

createstmt
    : 'CREATE' opttemp? 'TABLE' ifNotExists? qname (
        '(' (tableelementlist)? ')' optinherit? (partitionspec)? using? optwith? oncommitoption? opttablespace?
        | 'OF' qname opttypedtableelementlist? (partitionspec)? using? optwith? oncommitoption? opttablespace?
        | 'PARTITION' 'OF' qname opttypedtableelementlist? partitionboundspec (partitionspec)? using? optwith? oncommitoption?
            opttablespace?
    )
    ;

opttemp
    : ( 'LOCAL' | 'GLOBAL' )? ( 'TEMPORARY' | 'TEMP' )
    | 'UNLOGGED'
    ;

opttypedtableelementlist
    : '(' typedtableelement ( ',' typedtableelement )* ')'
    ;

    typedtableelement
        : name ( 'WITH' 'OPTIONS' )? colconstraint*
        | tableconstraint
        ;


tableelementlist
    : tableelement ( ',' tableelement )*
    ;

tableelement
    : tableconstraint
    | tablelikeclause
    | columnDef
    ;


columnDef
    : name typename create_generic_options? colconstraint*
    ;

colconstraint
    : 'CONSTRAINT' name colconstraintelem
    | colconstraintelem
    | constraintattr
    | 'COLLATE' qname
    ;

colconstraintelem
    : 'NOT'? 'NULL'
    | 'UNIQUE' definition_? optconstablespace?
    | 'PRIMARY' 'KEY' definition_? optconstablespace?
    | 'CHECK' '(' a_expr ')' ('NO' 'INHERIT')?
    | 'DEFAULT' b_expr
    | 'GENERATED' generated_when 'AS' (
        'IDENTITY' optparenthesizedseqoptlist?
        | '(' a_expr ')' 'STORED'
    )
    | 'REFERENCES' qname (name_list_)? key_match? key_actions?
    ;

generated_when
    : 'ALWAYS'
    | 'BY' 'DEFAULT'
    ;

constraintattr
    : 'NOT'? 'DEFERRABLE'
    | 'INITIALLY' ( 'DEFERRED' | 'IMMEDIATE' )
    ;

tablelikeclause
    : 'LIKE' qname ( ( 'INCLUDING' | 'EXCLUDING' ) tablelikeoption )*
    ;

tablelikeoption
    : 'COMMENTS'
    | 'CONSTRAINTS'
    | 'DEFAULTS'
    | 'IDENTITY'
    | 'GENERATED'
    | 'INDEXES'
    | 'STATISTICS'
    | 'STORAGE'
    | 'ALL'
    ;

tableconstraint
    : ( 'CONSTRAINT' name )? constraintelem
    ;

constraintelem
    : 'CHECK' '(' a_expr ')' constraintattributespec
    | 'UNIQUE' ( '(' names ')' c_include_? definition_? optconstablespace? constraintattributespec | existingindex constraintattributespec )
    | 'PRIMARY' 'KEY' ( '(' names ')' c_include_? definition_? optconstablespace? constraintattributespec | existingindex constraintattributespec )
    | 'EXCLUDE' (using)? '(' exclusionconstraintelem ( ',' exclusionconstraintelem )* ')' c_include_? definition_? optconstablespace? ('WHERE' '(' a_expr ')')? constraintattributespec
    | 'FOREIGN' 'KEY' '(' names ')' 'REFERENCES' qname (name_list_)? key_match? key_actions? constraintattributespec
    ;

c_include_
    : 'INCLUDE' '(' names ')'
    ;

key_match
    : 'MATCH' ( 'FULL' | 'PARTIAL' | 'SIMPLE' )
    ;

exclusionconstraintelem
    : index_elem 'WITH' ( any_operator | 'OPERATOR' '(' any_operator ')' )
    ;

key_actions
    : key_update
    | key_delete
    | key_update key_delete
    | key_delete key_update

    ;

key_update
    : 'ON' 'UPDATE' key_action
    ;

key_delete
    : 'ON' 'DELETE' key_action
    ;

key_action
    : 'NO' 'ACTION'
    | 'RESTRICT'
    | 'CASCADE'
    | 'SET' ( 'NULL' | 'DEFAULT' )
    ;

optinherit
    : 'INHERITS' '(' qnames ')'
    ;

partitionspec
    : 'PARTITION' 'BY' name '(' part_params ')'
    ;

part_params
    : part_elem ( ',' part_elem )*
    ;

part_elem
    : name collate_? (qname)?
    | func_expr_windowless collate_? (qname)?
    | '(' a_expr ')' collate_? (qname)?
    ;

using
    : 'USING' name
    ;

optwith
    : 'WITH' reloptions
    | 'WITHOUT' 'OIDS'
    ;

oncommitoption
    : 'ON' 'COMMIT' ( 'DROP' | 'DELETE' 'ROWS' | 'PRESERVE' 'ROWS' )
    ;

opttablespace
    : 'TABLESPACE' name
    ;

optconstablespace
    : 'USING' 'INDEX' 'TABLESPACE' name
    ;

existingindex
    : 'USING' 'INDEX' name
    ;

createstatsstmt
    : 'CREATE' 'STATISTICS' ifNotExists? qname name_list_? 'ON' expr_list 'FROM' from_list
    ;

alterstatsstmt
    : 'ALTER' 'STATISTICS' ifExists? qname 'SET' 'STATISTICS' signedInteger
    ;

createasstmt
    : 'CREATE' opttemp? 'TABLE' ifNotExists? create_as_target 'AS' selectstmt with_data_?
    ;

create_as_target
    : qname (name_list_)? using? optwith? oncommitoption? opttablespace?
    ;

with_data_
    : 'WITH' ( 'DATA' | 'NO' 'DATA' )
    ;

creatematviewstmt
    : 'CREATE' ('UNLOGGED')? 'MATERIALIZED' 'VIEW' ifNotExists? create_mv_target 'AS' selectstmt with_data_?
    ;

create_mv_target
    : qname (name_list_)? using? reloptions_? opttablespace?
    ;

refreshmatviewstmt
    : 'REFRESH' 'MATERIALIZED' 'VIEW' ('CONCURRENTLY')? qname with_data_?
    ;

createseqstmt
    : 'CREATE' opttemp? 'SEQUENCE' ifNotExists? qname seqoptelem*
    ;

alterseqstmt
    : 'ALTER' 'SEQUENCE' ifExists? qname seqoptelem+
    ;

optparenthesizedseqoptlist
    : '(' seqoptelem+ ')'
    ;

seqoptelem
    : 'AS' simpletypename
    | 'CACHE' numericonly
    | 'CYCLE'
    | 'INCREMENT' ('BY')? numericonly
    | 'MAXVALUE' numericonly
    | 'MINVALUE' numericonly
    | 'NO' ( 'MAXVALUE' | 'MINVALUE' | 'CYCLE' )
    | 'OWNED' 'BY' qname
    | 'SEQUENCE' 'NAME' qname
    | 'START' 'WITH'? numericonly
    | 'RESTART' 'WITH'? numericonly?
    ;

numericonly
    : Numeric
    | '+' Numeric
    | '-' Numeric
    | signedInteger
    ;

numericonly_list
    : numericonly ( ',' numericonly )*
    ;

createplangstmt
    : 'CREATE' orReplace? 'TRUSTED'? 'PROCEDURAL'? 'LANGUAGE' name (
        'HANDLER' handler_name inline_handler_? validator_clause?
     )?
    ;

handler_name
    : name ( '.' colLabel )*
    ;

inline_handler_
    : 'INLINE' handler_name
    ;

validator_clause
    : 'VALIDATOR' handler_name
    | 'NO' 'VALIDATOR'
    ;

createtablespacestmt
    : 'CREATE' 'TABLESPACE' name ('OWNER' rolespec)? 'LOCATION' string reloptions_?
    ;

droptablespacestmt
    : 'DROP' 'TABLESPACE' ifExists? name
    ;

createextensionstmt
    : 'CREATE' 'EXTENSION' ifNotExists? name 'WITH'? create_extension_opt_item*
    ;

ifExists : 'IF' 'EXISTS'  ;
ifNotExists : 'IF' 'NOT' 'EXISTS'  ;

create_extension_opt_item
    : 'SCHEMA' name
    | 'VERSION' nonreservedword_or_sconst
    | 'FROM' nonreservedword_or_sconst
    | 'CASCADE'
    ;

alterextensionstmt
    : 'ALTER' 'EXTENSION' name 'UPDATE' ('TO' nonreservedword_or_sconst)*
    ;

alterextensioncontentsstmt
    : 'ALTER' 'EXTENSION' name add_drop object_type_name name
    | 'ALTER' 'EXTENSION' name add_drop object_type_any_name qname
    | 'ALTER' 'EXTENSION' name add_drop 'AGGREGATE' aggregate_with_argtypes
    | 'ALTER' 'EXTENSION' name add_drop 'CAST' '(' typename 'AS' typename ')'
    | 'ALTER' 'EXTENSION' name add_drop 'DOMAIN' typename
    | 'ALTER' 'EXTENSION' name add_drop 'FUNCTION' function_with_argtypes
    | 'ALTER' 'EXTENSION' name add_drop 'OPERATOR' operator_with_argtypes
    | 'ALTER' 'EXTENSION' name add_drop 'OPERATOR' 'CLASS' qname 'USING' name
    | 'ALTER' 'EXTENSION' name add_drop 'OPERATOR' 'FAMILY' qname 'USING' name
    | 'ALTER' 'EXTENSION' name add_drop 'PROCEDURE' function_with_argtypes
    | 'ALTER' 'EXTENSION' name add_drop 'ROUTINE' function_with_argtypes
    | 'ALTER' 'EXTENSION' name add_drop 'TRANSFORM' 'FOR' typename 'LANGUAGE' name
    | 'ALTER' 'EXTENSION' name add_drop 'TYPE' typename
    ;

createfdwstmt
    : 'CREATE' 'FOREIGN' 'DATA' 'WRAPPER' name (fdw_option+)? create_generic_options?
    ;

fdw_option
    : 'HANDLER' handler_name
    | 'NO' 'HANDLER'
    | 'VALIDATOR' handler_name
    | 'NO' 'VALIDATOR'
    ;

alterfdwstmt
    : 'ALTER' 'FOREIGN' 'DATA' 'WRAPPER' name fdw_option* alter_generic_options
    | 'ALTER' 'FOREIGN' 'DATA' 'WRAPPER' name fdw_option+
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
    | 'DROP' colLabel
    ;

generic_option_elem
    : colLabel string
    ;

createforeignserverstmt
    : 'CREATE' 'SERVER' ifNotExists? name ( 'TYPE' string )? foreign_server_version? 'FOREIGN' 'DATA' 'WRAPPER' name create_generic_options?
    ;

foreign_server_version
    : 'VERSION' ( string | 'NULL' )
    ;

alterforeignserverstmt
    : 'ALTER' 'SERVER' name ( alter_generic_options | foreign_server_version alter_generic_options? )
    ;

createforeigntablestmt
    : 'CREATE' 'FOREIGN' 'TABLE' ifNotExists? qname '(' tableelementlist? ')' optinherit? 'SERVER' name create_generic_options?
    | 'CREATE' 'FOREIGN' 'TABLE' ifNotExists? qname 'PARTITION' 'OF' qname opttypedtableelementlist? partitionboundspec 'SERVER' name create_generic_options?
    ;

importforeignschemastmt
    : 'IMPORT' 'FOREIGN' 'SCHEMA' name ( 'LIMIT' 'TO' | 'EXCEPT' ) '(' relation_expr_list ')'? 'FROM' 'SERVER' name 'INTO' name create_generic_options?
    ;

createusermappingstmt
    : 'CREATE' 'USER' 'MAPPING' ifNotExists? 'FOR' auth_ident 'SERVER' name create_generic_options?
    ;

auth_ident
    : rolespec
    | 'USER'
    ;

dropusermappingstmt
    : 'DROP' 'USER' 'MAPPING' ifExists? 'FOR' auth_ident 'SERVER' name
    ;

alterusermappingstmt
    : 'ALTER' 'USER' 'MAPPING' 'FOR' auth_ident 'SERVER' name alter_generic_options
    ;

createpolicystmt
    : 'CREATE' 'POLICY' name 'ON' qname ('AS' identifier)? rowsecuritydefaultforcmd? ('TO' role_list)? rowsecurityoptionalexpr?
        rowsecurityoptionalwithcheck?
    ;

alterpolicystmt
    : 'ALTER' 'POLICY' name 'ON' qname ('TO' role_list)? rowsecurityoptionalexpr? rowsecurityoptionalwithcheck?
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
    : 'CREATE' 'ACCESS' 'METHOD' name 'TYPE' ('INDEX' | 'TABLE') 'HANDLER' handler_name
    ;

createtrigstmt
    : 'CREATE'            'TRIGGER' name triggeractiontime triggerevents 'ON' qname ('REFERENCING' triggertransition)* ('FOR' ('EACH')? triggerfortype)?                      triggerwhen? 'EXECUTE' function_or_procedure func_name '(' triggerfuncargs? ')'
    | 'CREATE' 'CONSTRAINT' 'TRIGGER' name 'AFTER' triggerevents             'ON' qname optconstrfromtable? constraintattributespec 'FOR' 'EACH' 'ROW' triggerwhen? 'EXECUTE' function_or_procedure func_name '(' triggerfuncargs? ')'
    ;

triggeractiontime
    : 'BEFORE'
    | 'AFTER'
    | 'INSTEAD' 'OF'
    ;

triggerevents
    : triggeroneevent ( 'OR' triggeroneevent )*
    ;

triggeroneevent
    : 'INSERT'
    | 'DELETE'
    | 'UPDATE'
    | 'UPDATE' 'OF' names
    | 'TRUNCATE'
    ;

triggertransition
    : ('NEW' | 'OLD') ('TABLE' | 'ROW') 'AS'? name
    ;

triggerfortype
    : 'ROW'
    | 'STATEMENT'
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
    | Numeric
    | string
    | colLabel
    ;

optconstrfromtable
    : 'FROM' qname
    ;

constraintattributespec
    : constraintattributeElem*
    ;

constraintattributeElem
    : 'NOT' 'DEFERRABLE'
    | 'DEFERRABLE'
    | 'INITIALLY' 'IMMEDIATE'
    | 'INITIALLY' 'DEFERRED'
    | 'NOT' 'VALID'
    | 'NO' 'INHERIT'
    ;

createeventtrigstmt
    : 'CREATE' 'EVENT' 'TRIGGER' name 'ON' colLabel 'EXECUTE' function_or_procedure func_name '(' ')'
    | 'CREATE' 'EVENT' 'TRIGGER' name 'ON' colLabel 'WHEN' event_trigger_when_list 'EXECUTE' function_or_procedure func_name '(' ')'
    ;

event_trigger_when_list
    : event_trigger_when_item ( 'AND' event_trigger_when_item )*
    ;

event_trigger_when_item
    : name 'IN' '(' string ( ',' string )* ')'
    ;

altereventtrigstmt
    : 'ALTER' 'EVENT' 'TRIGGER' name enable_trigger
    ;

enable_trigger
    : 'ENABLE' ( 'REPLICA' |  'ALWAYS' )?
    | 'DISABLE'
    ;

createassertionstmt
    : 'CREATE' 'ASSERTION' qname 'CHECK' '(' a_expr ')' constraintattributespec
    ;

definestmt
    : 'CREATE' orReplace? 'AGGREGATE' func_name aggr_args definition
    | 'CREATE' orReplace? 'AGGREGATE' func_name old_aggr_definition
    | 'CREATE' 'OPERATOR' any_operator definition
    | 'CREATE' 'TYPE' qname definition
    | 'CREATE' 'TYPE' qname
    | 'CREATE' 'TYPE' qname 'AS' '(' (tablefuncelementlist)? ')'
    | 'CREATE' 'TYPE' qname 'AS' 'ENUM' '(' ( string ( ',' string )* )? ')'
    | 'CREATE' 'TYPE' qname 'AS' 'RANGE' definition
    | 'CREATE' 'TEXT' 'SEARCH' 'PARSER' qname definition
    | 'CREATE' 'TEXT' 'SEARCH' 'DICTIONARY' qname definition
    | 'CREATE' 'TEXT' 'SEARCH' 'TEMPLATE' qname definition
    | 'CREATE' 'TEXT' 'SEARCH' 'CONFIGURATION' qname definition
    | 'CREATE' 'COLLATION' qname definition
    | 'CREATE' 'COLLATION' ifNotExists qname definition
    | 'CREATE' 'COLLATION' qname 'FROM' qname
    | 'CREATE' 'COLLATION' ifNotExists qname 'FROM' qname
    ;

definition
    : '(' def_elem ( ',' def_elem )* ')'
    ;

def_elem
    : colLabel ( '=' def_arg )?
    ;

def_arg
    : func_type
    | reserved_keyword
    | qual_all_op
    | numericonly
    | string
    | 'NONE'
    ;

old_aggr_definition
    : '(' old_aggr_elem ( ',' old_aggr_elem )* ')'
    ;

old_aggr_elem
    : identifier '=' def_arg
    ;

alterenumstmt
    : 'ALTER' 'TYPE' qname 'ADD' 'VALUE' (ifNotExists)? string (( 'BEFORE' | 'AFTER' ) string )?
    | 'ALTER' 'TYPE' qname 'RENAME' 'VALUE' string 'TO' string
    ;

createopclassstmt
    : 'CREATE' 'OPERATOR' 'CLASS' qname 'DEFAULT'? 'FOR' 'TYPE' typename 'USING' name ('FAMILY' qname)? 'AS' opclass_item_list
    ;

opclass_item_list
    : opclass_item ( ',' opclass_item )*
    ;

opclass_item
    : 'OPERATOR' integer any_operator opclass_purpose? ('RECHECK')?
    | 'OPERATOR' integer operator_with_argtypes opclass_purpose? ('RECHECK')?
    | 'FUNCTION' integer function_with_argtypes
    | 'FUNCTION' integer '(' type_list ')' function_with_argtypes
    | 'STORAGE' typename
    ;

opclass_purpose
    : 'FOR' ( 'SEARCH' |  'ORDER' 'BY' qname )
    ;

createopfamilystmt
    : 'CREATE' 'OPERATOR' 'FAMILY' qname 'USING' name
    ;

alteropfamilystmt
    : 'ALTER' 'OPERATOR' 'FAMILY' qname 'USING' name 'ADD' opclass_item_list
    | 'ALTER' 'OPERATOR' 'FAMILY' qname 'USING' name 'DROP' opclass_drop_list
    ;


opclass_drop_list
    : opclass_drop (',' opclass_drop)*
    ;

opclass_drop
    : 'OPERATOR' integer '(' type_list ')'
    | 'FUNCTION' integer '(' type_list ')'
    ;

dropopclassstmt
    : 'DROP' 'OPERATOR' 'CLASS' qname 'USING' name drop_behavior_?
    | 'DROP' 'OPERATOR' 'CLASS' ifExists qname using drop_behavior_?
    ;

dropopfamilystmt
    : 'DROP' 'OPERATOR' 'FAMILY' qname using drop_behavior_?
    | 'DROP' 'OPERATOR' 'FAMILY' ifExists qname using drop_behavior_?
    ;

dropownedstmt
    : 'DROP' 'OWNED' 'BY' role_list drop_behavior_?
    ;

reassignownedstmt
    : 'REASSIGN' 'OWNED' 'BY' role_list 'TO' rolespec
    ;

dropstmt
    : 'DROP' object_type_any_name ifExists qnames drop_behavior_?
    | 'DROP' object_type_any_name qnames drop_behavior_?
    | 'DROP' drop_type_name ifExists names drop_behavior_?
    | 'DROP' drop_type_name names drop_behavior_?
    | 'DROP' object_type_name_on_any_name name 'ON' qname drop_behavior_?
    | 'DROP' object_type_name_on_any_name ifExists name 'ON' qname drop_behavior_?
    | 'DROP' 'TYPE' type_name_list drop_behavior_?
    | 'DROP' 'TYPE' ifExists type_name_list drop_behavior_?
    | 'DROP' 'DOMAIN' type_name_list drop_behavior_?
    | 'DROP' 'DOMAIN' ifExists type_name_list drop_behavior_?
    | 'DROP' 'INDEX' 'CONCURRENTLY' qnames drop_behavior_?
    | 'DROP' 'INDEX' 'CONCURRENTLY' ifExists qnames drop_behavior_?
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
    | 'TEXT' 'SEARCH' 'PARSER'
    | 'TEXT' 'SEARCH' 'DICTIONARY'
    | 'TEXT' 'SEARCH' 'TEMPLATE'
    | 'TEXT' 'SEARCH' 'CONFIGURATION'
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
    | ('PROCEDURAL')? 'LANGUAGE'
    | 'PUBLICATION'
    | 'SCHEMA'
    | 'SERVER'
    ;

object_type_name_on_any_name
    : 'POLICY'
    | 'RULE'
    | 'TRIGGER'
    ;

type_name_list
    : typename ( ',' typename )*
    ;

truncatestmt
    : 'TRUNCATE' ('TABLE')? relation_expr_list (( 'CONTINUE' | 'RESTART' ) 'IDENTITY' )? drop_behavior_?
    ;

commentstmt
    : 'COMMENT' 'ON' object_type_any_name qname 'IS' comment_text
    | 'COMMENT' 'ON' 'COLUMN' qname 'IS' comment_text
    | 'COMMENT' 'ON' object_type_name name 'IS' comment_text
    | 'COMMENT' 'ON' 'TYPE' typename 'IS' comment_text
    | 'COMMENT' 'ON' 'DOMAIN' typename 'IS' comment_text
    | 'COMMENT' 'ON' 'AGGREGATE' aggregate_with_argtypes 'IS' comment_text
    | 'COMMENT' 'ON' 'FUNCTION' function_with_argtypes 'IS' comment_text
    | 'COMMENT' 'ON' 'OPERATOR' operator_with_argtypes 'IS' comment_text
    | 'COMMENT' 'ON' 'CONSTRAINT' name 'ON' qname 'IS' comment_text
    | 'COMMENT' 'ON' 'CONSTRAINT' name 'ON' 'DOMAIN' qname 'IS' comment_text
    | 'COMMENT' 'ON' object_type_name_on_any_name name 'ON' qname 'IS' comment_text
    | 'COMMENT' 'ON' 'PROCEDURE' function_with_argtypes 'IS' comment_text
    | 'COMMENT' 'ON' 'ROUTINE' function_with_argtypes 'IS' comment_text
    | 'COMMENT' 'ON' 'TRANSFORM' 'FOR' typename 'LANGUAGE' name 'IS' comment_text
    | 'COMMENT' 'ON' 'OPERATOR' 'CLASS' qname using 'IS' comment_text
    | 'COMMENT' 'ON' 'OPERATOR' 'FAMILY' qname using 'IS' comment_text
    | 'COMMENT' 'ON' 'LARGE' 'OBJECT' numericonly 'IS' comment_text
    | 'COMMENT' 'ON' 'CAST' '(' typename 'AS' typename ')' 'IS' comment_text
    ;

comment_text
    : string
    | 'NULL'
    ;

seclabelstmt
    : 'SECURITY' 'LABEL' provider_? 'ON' object_type_any_name qname 'IS' security_label
    | 'SECURITY' 'LABEL' provider_? 'ON' 'COLUMN' qname 'IS' security_label
    | 'SECURITY' 'LABEL' provider_? 'ON' object_type_name name 'IS' security_label
    | 'SECURITY' 'LABEL' provider_? 'ON' 'TYPE' typename 'IS' security_label
    | 'SECURITY' 'LABEL' provider_? 'ON' 'DOMAIN' typename 'IS' security_label
    | 'SECURITY' 'LABEL' provider_? 'ON' 'AGGREGATE' aggregate_with_argtypes 'IS' security_label
    | 'SECURITY' 'LABEL' provider_? 'ON' 'FUNCTION' function_with_argtypes 'IS' security_label
    | 'SECURITY' 'LABEL' provider_? 'ON' 'LARGE' 'OBJECT' numericonly 'IS' security_label
    | 'SECURITY' 'LABEL' provider_? 'ON' 'PROCEDURE' function_with_argtypes 'IS' security_label
    | 'SECURITY' 'LABEL' provider_? 'ON' 'ROUTINE' function_with_argtypes 'IS' security_label
    ;

provider_
    : 'FOR' nonreservedword_or_sconst
    ;

security_label
    : string
    | 'NULL'
    ;

fetchstmt
    : 'FETCH' fetch_args
    | 'MOVE' fetch_args
    ;

fetch_args
    : from_in? name
    | 'NEXT' (from_in)? name
    | 'PRIOR' (from_in)? name
    | 'FIRST' (from_in)? name
    | 'LAST' (from_in)? name
    | 'ABSOLUTE' signedInteger (from_in)? name
    | 'RELATIVE' signedInteger (from_in)? name
    | signedInteger (from_in)? name
    | 'ALL' (from_in)? name
    | 'FORWARD' (from_in)? name
    | 'FORWARD' signedInteger (from_in)? name
    | 'FORWARD' 'ALL' (from_in)? name
    | 'BACKWARD' (from_in)? name
    | 'BACKWARD' signedInteger (from_in)? name
    | 'BACKWARD' 'ALL' (from_in)? name
    ;

from_in
    : 'FROM'
    | 'IN'
    ;

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
    | 'ALL' 'PRIVILEGES'
    | 'ALL' '(' names ')'
    | 'ALL' 'PRIVILEGES' '(' names ')'
    ;

privilege_list
    : privilege ( ',' privilege )*
    ;

privilege
    : 'SELECT' (name_list_)?
    | 'REFERENCES' (name_list_)?
    | 'CREATE' (name_list_)?
    | name (name_list_)?
    ;

privilege_target
    : qnames
    | 'TABLE' qnames
    | 'SEQUENCE' qnames
    | 'FOREIGN' 'DATA' 'WRAPPER' names
    | 'FOREIGN' 'SERVER' names
    | 'FUNCTION' function_with_argtypes_list
    | 'PROCEDURE' function_with_argtypes_list
    | 'ROUTINE' function_with_argtypes_list
    | 'DATABASE' names
    | 'DOMAIN' qnames
    | 'LANGUAGE' names
    | 'LARGE' 'OBJECT' numericonly_list
    | 'SCHEMA' names
    | 'TABLESPACE' names
    | 'TYPE' qnames
    | 'ALL' 'TABLES' 'IN' 'SCHEMA' names
    | 'ALL' 'SEQUENCES' 'IN' 'SCHEMA' names
    | 'ALL' 'FUNCTIONS' 'IN' 'SCHEMA' names
    | 'ALL' 'PROCEDURES' 'IN' 'SCHEMA' names
    | 'ALL' 'ROUTINES' 'IN' 'SCHEMA' names
    ;

grantee_list
    : grantee ( ',' grantee )*
    ;

grantee
    : 'GROUP'? rolespec
    ;

grant_grant_option_
    : 'WITH' 'GRANT' 'OPTION'
    ;

grantrolestmt
    : 'GRANT' privilege_list 'TO' role_list ('WITH' 'ADMIN' 'OPTION')? granted_by_?
    ;

revokerolestmt
    : 'REVOKE' ('ADMIN' 'OPTION' 'FOR')? privilege_list 'FROM' role_list granted_by_? drop_behavior_?
    ;

granted_by_
    : 'GRANTED' 'BY' rolespec
    ;

alterdefaultprivilegesstmt
    : 'ALTER' 'DEFAULT' 'PRIVILEGES' defacloption* defaclaction
    ;

defacloption
    : 'IN' 'SCHEMA' names
    | 'FOR' 'ROLE' role_list
    | 'FOR' 'USER' role_list
    ;

defaclaction
    : 'GRANT' privileges 'ON' defacl_privilege_target 'TO' grantee_list grant_grant_option_?
    | 'REVOKE' privileges 'ON' defacl_privilege_target 'FROM' grantee_list drop_behavior_?
    | 'REVOKE' 'GRANT' 'OPTION' 'FOR' privileges 'ON' defacl_privilege_target 'FROM' grantee_list drop_behavior_?
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
    : 'CREATE' ('UNIQUE')? 'INDEX' ('CONCURRENTLY')? (ifNotExists? name)? 'ON' relation_expr (using)? '(' index_params ')' ('INCLUDE' '(' index_including_params ')')? reloptions_? opttablespace? where?
    ;

index_params
    : index_elem ( ',' index_elem )*
    ;

index_elem_options
    : collate_? ( qname reloptions? )? sortDir? nulls_order_?
    ;

index_elem
    : name index_elem_options
    | func_expr_windowless index_elem_options
    | '(' a_expr ')' index_elem_options
    ;

index_including_params
    : index_elem ( ',' index_elem )*
    ;

collate_
    : 'COLLATE' qname
    ;

sortDir
    : 'ASC'
    | 'DESC'
    ;

nulls_order_
    : 'NULLS' ('FIRST' |  'LAST')
    ;

createfunctionstmt
    : 'CREATE' orReplace? ( 'FUNCTION' | 'PROCEDURE' ) func_name func_args_with_defaults (
        'RETURNS' ( func_type | 'TABLE' '(' table_func_column_list ')' )
     )? createfunc_opt_item+
    ;

orReplace
    : 'OR' 'REPLACE'
    ;

func_args
    : '(' func_args_list? ')'
    ;

func_args_list
    : func_arg ( ',' func_arg )*
    ;

function_with_argtypes_list
    : function_with_argtypes ( ',' function_with_argtypes )*
    ;

function_with_argtypes
    : func_name func_args
    | type_func_name_keyword
    | name (indirection_el)*
    ;

func_args_with_defaults
    : '(' func_args_with_defaults_list? ')'
    ;

func_args_with_defaults_list
    : func_arg_with_default ( ',' func_arg_with_default )*
    ;

func_arg
    : arg_class (type_function_name)? func_type
    | type_function_name arg_class? func_type
    | func_type
    ;

arg_class
    : 'IN' 'OUT'?
    | 'OUT'
    | 'INOUT'
    | 'VARIADIC'
    ;

func_type
    : typename
    | 'SETOF'? type_function_name ( '.' colLabel )+ '%' 'TYPE'
    ;

func_arg_with_default
    : func_arg ( ( 'DEFAULT' | '=' ) a_expr )?
    ;

aggr_args
    : '(' ( '*' | (func_args_list? 'ORDER' 'BY' )? func_args_list ) ')'
    ;

aggregate_with_argtypes
    : func_name aggr_args
    ;

aggregate_with_argtypes_list
    : aggregate_with_argtypes ( ',' aggregate_with_argtypes )*
    ;

common_func_opt_item
    : 'CALLED' 'ON' 'NULL' 'INPUT'
    | 'RETURNS' 'NULL' 'ON' 'NULL' 'INPUT'
    | 'STRICT'
    | 'IMMUTABLE'
    | 'STABLE'
    | 'VOLATILE'
    | 'EXTERNAL' 'SECURITY' 'DEFINER'
    | 'EXTERNAL' 'SECURITY' 'INVOKER'
    | 'SECURITY' 'DEFINER'
    | 'SECURITY' 'INVOKER'
    | 'LEAKPROOF'
    | 'NOT' 'LEAKPROOF'
    | 'COST' numericonly
    | 'ROWS' numericonly
    | 'SUPPORT' qname
    | 'SET' set_rest_more
    | variableresetstmt
    | 'PARALLEL' name
    ;

createfunc_opt_item
    : 'AS' func_as
    | 'LANGUAGE' nonreservedword_or_sconst
    | 'TRANSFORM' transform_type_list
    | 'WINDOW'
    | common_func_opt_item
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

definition_
    : 'WITH' definition
    ;

table_func_column
    : type_function_name func_type
    ;

table_func_column_list
    : table_func_column ( ',' table_func_column )*
    ;

alterfunctionstmt
    : 'ALTER' ( 'FUNCTION' | 'PROCEDURE' | 'ROUTINE' ) function_with_argtypes alterfunc_opt_list restrict_?
    ;

alterfunc_opt_list
    : common_func_opt_item+
    ;

restrict_
    : 'RESTRICT'

    ;

removefuncstmt
    : 'DROP' 'FUNCTION' function_with_argtypes_list drop_behavior_?
    | 'DROP' 'FUNCTION' ifExists function_with_argtypes_list drop_behavior_?
    | 'DROP' 'PROCEDURE' function_with_argtypes_list drop_behavior_?
    | 'DROP' 'PROCEDURE' ifExists function_with_argtypes_list drop_behavior_?
    | 'DROP' 'ROUTINE' function_with_argtypes_list drop_behavior_?
    | 'DROP' 'ROUTINE' ifExists function_with_argtypes_list drop_behavior_?
    ;

removeaggrstmt
    : 'DROP' 'AGGREGATE' aggregate_with_argtypes_list drop_behavior_?
    | 'DROP' 'AGGREGATE' ifExists aggregate_with_argtypes_list drop_behavior_?
    ;

removeoperstmt
    : 'DROP' 'OPERATOR' operator_with_argtypes_list drop_behavior_?
    | 'DROP' 'OPERATOR' ifExists operator_with_argtypes_list drop_behavior_?
    ;

oper_argtypes
    : '(' typename ')'
    | '(' typename ',' typename ')'
    | '(' 'NONE' ',' typename ')'
    | '(' typename ',' 'NONE' ')'
    ;

any_operator
    : ( name '.' )* ( Operator  )
//    : ( name '.' )* ( Operator | mathop )
    ;

operator_with_argtypes_list
    : operator_with_argtypes ( ',' operator_with_argtypes )*
    ;

operator_with_argtypes
    : any_operator oper_argtypes
    ;

dostmt
    : 'DO' dostmt_opt_list
    ;

dostmt_opt_list
    : dostmt_opt_item+
    ;

dostmt_opt_item
    : string
    | 'LANGUAGE' nonreservedword_or_sconst
    ;

createcaststmt
    : 'CREATE' 'CAST' '(' typename 'AS' typename ')' 'WITH' 'FUNCTION' function_with_argtypes cast_context?
    | 'CREATE' 'CAST' '(' typename 'AS' typename ')' 'WITHOUT' 'FUNCTION' cast_context?
    | 'CREATE' 'CAST' '(' typename 'AS' typename ')' 'WITH' 'INOUT' cast_context?
    ;

cast_context
    : 'AS' 'IMPLICIT'
    | 'AS' 'ASSIGNMENT'

    ;

dropcaststmt
    : 'DROP' 'CAST' if_exists_? '(' typename 'AS' typename ')' drop_behavior_?
    ;

if_exists_
    : 'IF' 'EXISTS'

    ;

createtransformstmt
    : 'CREATE' orReplace? 'TRANSFORM' 'FOR' typename 'LANGUAGE' name '(' transform_element_list ')'
    ;

transform_element_list
    : 'FROM' 'SQL' 'WITH' 'FUNCTION' function_with_argtypes ',' 'TO' 'SQL' 'WITH' 'FUNCTION' function_with_argtypes
    | 'TO' 'SQL' 'WITH' 'FUNCTION' function_with_argtypes ',' 'FROM' 'SQL' 'WITH' 'FUNCTION' function_with_argtypes
    | 'FROM' 'SQL' 'WITH' 'FUNCTION' function_with_argtypes
    | 'TO' 'SQL' 'WITH' 'FUNCTION' function_with_argtypes
    ;

droptransformstmt
    : 'DROP' 'TRANSFORM' if_exists_? 'FOR' typename 'LANGUAGE' name drop_behavior_?
    ;

reindexstmt
    : 'REINDEX' reindex_option_list? reindex_target_relation ('CONCURRENTLY')? qname
    | 'REINDEX' reindex_option_list? 'SCHEMA' ('CONCURRENTLY')? name
    | 'REINDEX' reindex_option_list? reindex_target_all ('CONCURRENTLY')? (name)?
    ;

reindex_target_relation
    : 'INDEX'
    | 'TABLE'
    ;

reindex_target_all
    : 'SYSTEM'
    | 'DATABASE'
    ;

reindex_option_list
    : '(' utility_option_elem (  ',' utility_option_elem )* ')'
    ;

altertblspcstmt
    : 'ALTER' 'TABLESPACE' name 'SET' reloptions
    | 'ALTER' 'TABLESPACE' name 'RESET' reloptions
    ;

renamestmt
    : 'ALTER' 'AGGREGATE' aggregate_with_argtypes 'RENAME' 'TO' name
    | 'ALTER' 'COLLATION' qname 'RENAME' 'TO' name
    | 'ALTER' 'CONVERSION' qname 'RENAME' 'TO' name
    | 'ALTER' 'DATABASE' name 'RENAME' 'TO' name
    | 'ALTER' 'DOMAIN' qname 'RENAME' 'TO' name
    | 'ALTER' 'DOMAIN' qname 'RENAME' 'CONSTRAINT' name 'TO' name
    | 'ALTER' 'FOREIGN' 'DATA' 'WRAPPER' name 'RENAME' 'TO' name
    | 'ALTER' 'FUNCTION' function_with_argtypes 'RENAME' 'TO' name
    | 'ALTER' 'GROUP' rolespec 'RENAME' 'TO' rolespec
    | 'ALTER' ('PROCEDURAL')? 'LANGUAGE' name 'RENAME' 'TO' name
    | 'ALTER' 'OPERATOR' 'CLASS' qname using 'RENAME' 'TO' name
    | 'ALTER' 'OPERATOR' 'FAMILY' qname using 'RENAME' 'TO' name
    | 'ALTER' 'POLICY' name 'ON' qname 'RENAME' 'TO' name
    | 'ALTER' 'POLICY' ifExists name 'ON' qname 'RENAME' 'TO' name
    | 'ALTER' 'PROCEDURE' function_with_argtypes 'RENAME' 'TO' name
    | 'ALTER' 'PUBLICATION' name 'RENAME' 'TO' name
    | 'ALTER' 'ROUTINE' function_with_argtypes 'RENAME' 'TO' name
    | 'ALTER' 'SCHEMA' name 'RENAME' 'TO' name
    | 'ALTER' 'SERVER' name 'RENAME' 'TO' name
    | 'ALTER' 'SUBSCRIPTION' name 'RENAME' 'TO' name
    | 'ALTER' 'TABLE' relation_expr 'RENAME' 'TO' name
    | 'ALTER' 'TABLE' ifExists relation_expr 'RENAME' 'TO' name
    | 'ALTER' 'SEQUENCE' qname 'RENAME' 'TO' name
    | 'ALTER' 'SEQUENCE' ifExists qname 'RENAME' 'TO' name
    | 'ALTER' 'VIEW' qname 'RENAME' 'TO' name
    | 'ALTER' 'VIEW' ifExists qname 'RENAME' 'TO' name
    | 'ALTER' 'MATERIALIZED' 'VIEW' qname 'RENAME' 'TO' name
    | 'ALTER' 'MATERIALIZED' 'VIEW' ifExists qname 'RENAME' 'TO' name
    | 'ALTER' 'INDEX' qname 'RENAME' 'TO' name
    | 'ALTER' 'INDEX' ifExists qname 'RENAME' 'TO' name
    | 'ALTER' 'FOREIGN' 'TABLE' relation_expr 'RENAME' 'TO' name
    | 'ALTER' 'FOREIGN' 'TABLE' ifExists relation_expr 'RENAME' 'TO' name
    | 'ALTER' 'TABLE' relation_expr 'RENAME' column_? name 'TO' name
    | 'ALTER' 'TABLE' ifExists relation_expr 'RENAME' column_? name 'TO' name
    | 'ALTER' 'VIEW' qname 'RENAME' column_? name 'TO' name
    | 'ALTER' 'VIEW' ifExists qname 'RENAME' column_? name 'TO' name
    | 'ALTER' 'MATERIALIZED' 'VIEW' qname 'RENAME' column_? name 'TO' name
    | 'ALTER' 'MATERIALIZED' 'VIEW' ifExists qname 'RENAME' column_? name 'TO' name
    | 'ALTER' 'TABLE' relation_expr 'RENAME' 'CONSTRAINT' name 'TO' name
    | 'ALTER' 'TABLE' ifExists relation_expr 'RENAME' 'CONSTRAINT' name 'TO' name
    | 'ALTER' 'FOREIGN' 'TABLE' relation_expr 'RENAME' column_? name 'TO' name
    | 'ALTER' 'FOREIGN' 'TABLE' ifExists relation_expr 'RENAME' column_? name 'TO' name
    | 'ALTER' 'RULE' name 'ON' qname 'RENAME' 'TO' name
    | 'ALTER' 'TRIGGER' name 'ON' qname 'RENAME' 'TO' name
    | 'ALTER' 'EVENT' 'TRIGGER' name 'RENAME' 'TO' name
    | 'ALTER' 'ROLE' rolespec 'RENAME' 'TO' rolespec
    | 'ALTER' 'USER' rolespec 'RENAME' 'TO' rolespec
    | 'ALTER' 'TABLESPACE' name 'RENAME' 'TO' name
    | 'ALTER' 'STATISTICS' qname 'RENAME' 'TO' name
    | 'ALTER' 'TEXT' 'SEARCH' 'PARSER' qname 'RENAME' 'TO' name
    | 'ALTER' 'TEXT' 'SEARCH' 'DICTIONARY' qname 'RENAME' 'TO' name
    | 'ALTER' 'TEXT' 'SEARCH' 'TEMPLATE' qname 'RENAME' 'TO' name
    | 'ALTER' 'TEXT' 'SEARCH' 'CONFIGURATION' qname 'RENAME' 'TO' name
    | 'ALTER' 'TYPE' qname 'RENAME' 'TO' name
    | 'ALTER' 'TYPE' qname 'RENAME' 'ATTRIBUTE' name 'TO' name drop_behavior_?
    ;

column_
    : 'COLUMN'

    ;

set_data_
    : 'SET' 'DATA'

    ;

alterobjectdependsstmt
    : 'ALTER' 'FUNCTION' function_with_argtypes no_? 'DEPENDS' 'ON' 'EXTENSION' name
    | 'ALTER' 'PROCEDURE' function_with_argtypes no_? 'DEPENDS' 'ON' 'EXTENSION' name
    | 'ALTER' 'ROUTINE' function_with_argtypes no_? 'DEPENDS' 'ON' 'EXTENSION' name
    | 'ALTER' 'TRIGGER' name 'ON' qname no_? 'DEPENDS' 'ON' 'EXTENSION' name
    | 'ALTER' 'MATERIALIZED' 'VIEW' qname no_? 'DEPENDS' 'ON' 'EXTENSION' name
    | 'ALTER' 'INDEX' qname no_? 'DEPENDS' 'ON' 'EXTENSION' name
    ;

no_
    : 'NO'

    ;

alterobjectschemastmt
    : 'ALTER' 'AGGREGATE' aggregate_with_argtypes 'SET' 'SCHEMA' name
    | 'ALTER' 'COLLATION' qname 'SET' 'SCHEMA' name
    | 'ALTER' 'CONVERSION' qname 'SET' 'SCHEMA' name
    | 'ALTER' 'DOMAIN' qname 'SET' 'SCHEMA' name
    | 'ALTER' 'EXTENSION' name 'SET' 'SCHEMA' name
    | 'ALTER' 'FUNCTION' function_with_argtypes 'SET' 'SCHEMA' name
    | 'ALTER' 'OPERATOR' operator_with_argtypes 'SET' 'SCHEMA' name
    | 'ALTER' 'OPERATOR' 'CLASS' qname using 'SET' 'SCHEMA' name
    | 'ALTER' 'OPERATOR' 'FAMILY' qname using 'SET' 'SCHEMA' name
    | 'ALTER' 'PROCEDURE' function_with_argtypes 'SET' 'SCHEMA' name
    | 'ALTER' 'ROUTINE' function_with_argtypes 'SET' 'SCHEMA' name
    | 'ALTER' 'TABLE' relation_expr 'SET' 'SCHEMA' name
    | 'ALTER' 'TABLE' ifExists relation_expr 'SET' 'SCHEMA' name
    | 'ALTER' 'STATISTICS' qname 'SET' 'SCHEMA' name
    | 'ALTER' 'TEXT' 'SEARCH' 'PARSER' qname 'SET' 'SCHEMA' name
    | 'ALTER' 'TEXT' 'SEARCH' 'DICTIONARY' qname 'SET' 'SCHEMA' name
    | 'ALTER' 'TEXT' 'SEARCH' 'TEMPLATE' qname 'SET' 'SCHEMA' name
    | 'ALTER' 'TEXT' 'SEARCH' 'CONFIGURATION' qname 'SET' 'SCHEMA' name
    | 'ALTER' 'SEQUENCE' qname 'SET' 'SCHEMA' name
    | 'ALTER' 'SEQUENCE' ifExists qname 'SET' 'SCHEMA' name
    | 'ALTER' 'VIEW' qname 'SET' 'SCHEMA' name
    | 'ALTER' 'VIEW' ifExists qname 'SET' 'SCHEMA' name
    | 'ALTER' 'MATERIALIZED' 'VIEW' qname 'SET' 'SCHEMA' name
    | 'ALTER' 'MATERIALIZED' 'VIEW' ifExists qname 'SET' 'SCHEMA' name
    | 'ALTER' 'FOREIGN' 'TABLE' relation_expr 'SET' 'SCHEMA' name
    | 'ALTER' 'FOREIGN' 'TABLE' ifExists relation_expr 'SET' 'SCHEMA' name
    | 'ALTER' 'TYPE' qname 'SET' 'SCHEMA' name
    ;

alteroperatorstmt
    : 'ALTER' 'OPERATOR' operator_with_argtypes 'SET' '(' operator_def_list ')'
    ;

operator_def_list
    : operator_def_elem ( ',' operator_def_elem )*
    ;

operator_def_elem
    : colLabel '=' 'NONE'
    | colLabel '=' operator_def_arg
    ;

operator_def_arg
    : func_type
    | reserved_keyword
    | qual_all_op
    | numericonly
    | string
    ;

altertypestmt
    : 'ALTER' 'TYPE' qname 'SET' '(' operator_def_list ')'
    ;

alterownerstmt
    : 'ALTER' 'AGGREGATE' aggregate_with_argtypes 'OWNER' 'TO' rolespec
    | 'ALTER' 'COLLATION' qname 'OWNER' 'TO' rolespec
    | 'ALTER' 'CONVERSION' qname 'OWNER' 'TO' rolespec
    | 'ALTER' 'DATABASE' name 'OWNER' 'TO' rolespec
    | 'ALTER' 'DOMAIN' qname 'OWNER' 'TO' rolespec
    | 'ALTER' 'FUNCTION' function_with_argtypes 'OWNER' 'TO' rolespec
    | 'ALTER' ('PROCEDURAL')? 'LANGUAGE' name 'OWNER' 'TO' rolespec
    | 'ALTER' 'LARGE' 'OBJECT' numericonly 'OWNER' 'TO' rolespec
    | 'ALTER' 'OPERATOR' operator_with_argtypes 'OWNER' 'TO' rolespec
    | 'ALTER' 'OPERATOR' 'CLASS' qname using 'OWNER' 'TO' rolespec
    | 'ALTER' 'OPERATOR' 'FAMILY' qname using 'OWNER' 'TO' rolespec
    | 'ALTER' 'PROCEDURE' function_with_argtypes 'OWNER' 'TO' rolespec
    | 'ALTER' 'ROUTINE' function_with_argtypes 'OWNER' 'TO' rolespec
    | 'ALTER' 'SCHEMA' name 'OWNER' 'TO' rolespec
    | 'ALTER' 'TYPE' qname 'OWNER' 'TO' rolespec
    | 'ALTER' 'TABLESPACE' name 'OWNER' 'TO' rolespec
    | 'ALTER' 'STATISTICS' qname 'OWNER' 'TO' rolespec
    | 'ALTER' 'TEXT' 'SEARCH' 'DICTIONARY' qname 'OWNER' 'TO' rolespec
    | 'ALTER' 'TEXT' 'SEARCH' 'CONFIGURATION' qname 'OWNER' 'TO' rolespec
    | 'ALTER' 'FOREIGN' 'DATA' 'WRAPPER' name 'OWNER' 'TO' rolespec
    | 'ALTER' 'SERVER' name 'OWNER' 'TO' rolespec
    | 'ALTER' 'EVENT' 'TRIGGER' name 'OWNER' 'TO' rolespec
    | 'ALTER' 'PUBLICATION' name 'OWNER' 'TO' rolespec
    | 'ALTER' 'SUBSCRIPTION' name 'OWNER' 'TO' rolespec
    ;

createpublicationstmt
    : 'CREATE' 'PUBLICATION' name publication_for_tables_? definition_?
    ;

publication_for_tables_
    : publication_for_tables

    ;

publication_for_tables
    : 'FOR' 'TABLE' relation_expr_list
    | 'FOR' 'ALL' 'TABLES'
    ;

alterpublicationstmt
    : 'ALTER' 'PUBLICATION' name 'SET' definition
    | 'ALTER' 'PUBLICATION' name 'ADD' 'TABLE' relation_expr_list
    | 'ALTER' 'PUBLICATION' name 'SET' 'TABLE' relation_expr_list
    | 'ALTER' 'PUBLICATION' name 'DROP' 'TABLE' relation_expr_list
    ;

createsubscriptionstmt
    : 'CREATE' 'SUBSCRIPTION' name 'CONNECTION' string 'PUBLICATION' publication_name_list definition_?
    ;

publication_name_list
    : publication_name_item ( ',' publication_name_item )*
    ;

publication_name_item
    : colLabel
    ;

altersubscriptionstmt
    : 'ALTER' 'SUBSCRIPTION' name 'SET' definition
    | 'ALTER' 'SUBSCRIPTION' name 'CONNECTION' string
    | 'ALTER' 'SUBSCRIPTION' name 'REFRESH' 'PUBLICATION' definition_?
    | 'ALTER' 'SUBSCRIPTION' name 'SET' 'PUBLICATION' publication_name_list definition_?
    | 'ALTER' 'SUBSCRIPTION' name 'ENABLE'
    | 'ALTER' 'SUBSCRIPTION' name 'DISABLE'
    ;

dropsubscriptionstmt
    : 'DROP' 'SUBSCRIPTION' name drop_behavior_?
    | 'DROP' 'SUBSCRIPTION' ifExists name drop_behavior_?
    ;

rulestmt
    : 'CREATE' orReplace? 'RULE' name 'AS' 'ON' ('SELECT'
    | 'UPDATE'
    | 'DELETE'
    | 'INSERT') 'TO' qname where? 'DO' ('INSTEAD'
    | 'ALSO')? ruleactionlist
    ;

ruleactionlist
    : 'NOTHING'
    | ruleactionstmt
    | '(' ruleactionmulti ')'
    ;

ruleactionmulti
    : (ruleactionstmt)? ( ';' (ruleactionstmt)? )*
    ;

ruleactionstmt
    : selectstmt
    | insertstmt
    | updatestmt
    | deletestmt
    | notifystmt
    ;

notifystmt
    : 'NOTIFY' name (',' string)?
    ;

listenstmt
    : 'LISTEN' name
    ;

unlistenstmt
    : 'UNLISTEN' name
    | 'UNLISTEN' '*'
    ;

transactionstmt
    : 'ABORT' transaction_? transaction_chain_?
    | 'BEGIN' transaction_? (transaction_mode_list)?
    | 'START' 'TRANSACTION' (transaction_mode_list)?
    | 'COMMIT' transaction_? transaction_chain_?
    | 'END' transaction_? transaction_chain_?
    | 'ROLLBACK' transaction_? transaction_chain_?
    | 'SAVEPOINT' name
    | 'RELEASE' 'SAVEPOINT' name
    | 'RELEASE' name
    | 'ROLLBACK' transaction_? 'TO' 'SAVEPOINT' name
    | 'ROLLBACK' transaction_? 'TO' name
    | 'PREPARE' 'TRANSACTION' string
    | 'COMMIT' 'PREPARED' string
    | 'ROLLBACK' 'PREPARED' string
    ;

transaction_
    : 'WORK'
    | 'TRANSACTION'
    ;

transaction_mode_item
    : 'ISOLATION' 'LEVEL' iso_level
    | 'READ' 'ONLY'
    | 'READ' 'WRITE'
    | 'DEFERRABLE'
    | 'NOT' 'DEFERRABLE'
    ;

transaction_mode_list
    : transaction_mode_item ( ','? transaction_mode_item )*
    ;

transaction_chain_
    : 'AND' 'NO'? 'CHAIN'
    ;

viewstmt
    : 'CREATE' ( 'OR' 'REPLACE' )? opttemp? (
        'VIEW' qname (name_list_)? reloptions_?
        | 'RECURSIVE' 'VIEW' qname '(' names ')' reloptions_?
    ) 'AS' selectstmt ('WITH' ( 'CASCADED' | 'LOCAL' )? 'CHECK' 'OPTION')?
    ;

loadstmt
    : 'LOAD' string
    ;

createdbstmt
    : 'CREATE' 'DATABASE' name 'WITH'? createdb_opt_item*
    ;

createdb_opt_item
    : createdb_opt_name '='? ( signedInteger | boolean_or_string_ | 'DEFAULT' )
    ;

createdb_opt_name
    : identifier
    | 'CONNECTION' 'LIMIT'
    | 'ENCODING'
    | 'LOCATION'
    | 'OWNER'
    | 'TABLESPACE'
    | 'TEMPLATE'
    ;

alterdatabasestmt
    : 'ALTER' 'DATABASE' name ( 'WITH'? createdb_opt_item* | 'SET' 'TABLESPACE' name )
    ;

alterdatabasesetstmt
    : 'ALTER' 'DATABASE' name setresetclause
    ;

dropdbstmt
    : 'DROP' 'DATABASE' ifExists? name ( 'WITH'? '(' 'FORCE' ( ',' 'FORCE' )* ')' )?
    ;

altercollationstmt
    : 'ALTER' 'COLLATION' qname 'REFRESH' 'VERSION'
    ;

altersystemstmt
    : 'ALTER' 'SYSTEM' ( 'SET' | 'RESET' ) generic_set
    ;

createdomainstmt
    : 'CREATE' 'DOMAIN' qname 'AS'? typename colconstraint*
    ;

alterdomainstmt
    : 'ALTER' 'DOMAIN' qname (
        alter_column_default
        | 'DROP' 'NOT' 'NULL'
        | 'SET' 'NOT' 'NULL'
        | 'ADD' tableconstraint
        | 'DROP' 'CONSTRAINT' ifExists? name drop_behavior_?
        | 'VALIDATE' 'CONSTRAINT' name
    )
    ;

altertsdictionarystmt
    : 'ALTER' 'TEXT' 'SEARCH' 'DICTIONARY' qname definition
    ;

altertsconfigurationstmt
    : 'ALTER' 'TEXT' 'SEARCH' 'CONFIGURATION' qname 'ADD' 'MAPPING' 'FOR' names 'WITH' qnames
    | 'ALTER' 'TEXT' 'SEARCH' 'CONFIGURATION' qname 'ALTER' 'MAPPING' 'FOR' names 'WITH' qnames
    | 'ALTER' 'TEXT' 'SEARCH' 'CONFIGURATION' qname 'ALTER' 'MAPPING' 'REPLACE' qname 'WITH' qname
    | 'ALTER' 'TEXT' 'SEARCH' 'CONFIGURATION' qname 'ALTER' 'MAPPING' 'FOR' names 'REPLACE' qname 'WITH' qname
    | 'ALTER' 'TEXT' 'SEARCH' 'CONFIGURATION' qname 'DROP' 'MAPPING' 'FOR' names
    | 'ALTER' 'TEXT' 'SEARCH' 'CONFIGURATION' qname 'DROP' 'MAPPING' ifExists 'FOR' names
    ;

createconversionstmt
    : 'CREATE' 'DEFAULT'? 'CONVERSION' qname 'FOR' string 'TO' string 'FROM' qname
    ;

clusterstmt
    : 'CLUSTER' 'VERBOSE'? qname (using)?
    | 'CLUSTER' 'VERBOSE'?
    | 'CLUSTER' 'VERBOSE'? name 'ON' qname
    ;

vacuumstmt
    : 'VACUUM' 'FULL'? 'FREEZE'? 'VERBOSE'? analyze_keyword? vacuum_relation_list?
    | 'VACUUM' '(' vac_analyze_option_list ')' vacuum_relation_list?
    ;

analyzestmt
    : analyze_keyword 'VERBOSE'? vacuum_relation_list?
    | analyze_keyword '(' vac_analyze_option_list ')' vacuum_relation_list?
    ;

vac_analyze_option_list
    : vac_analyze_option_elem ( ',' vac_analyze_option_elem )*
    ;

analyze_keyword
    : 'ANALYZE'
    | 'ANALYSE'
    ;

utility_option_elem
    : (nonreservedword | analyze_keyword | 'FORMAT_LA') (boolean_or_string_ | numericonly)?
    ;

vac_analyze_option_elem
    : (nonreservedword
    | analyze_keyword) (boolean_or_string_ | numericonly)? ;

vacuum_relation
    : qname name_list_?
    ;

vacuum_relation_list
    : vacuum_relation ( ',' vacuum_relation )*
    ;

explainstmt
    : 'EXPLAIN' explainablestmt
    | 'EXPLAIN' analyze_keyword 'VERBOSE'? explainablestmt
    | 'EXPLAIN' 'VERBOSE' explainablestmt
    | 'EXPLAIN' '(' explain_option_elem ( ',' explain_option_elem )* ')' explainablestmt
    ;

explainablestmt
    : selectstmt
    | insertstmt
    | updatestmt
    | deletestmt
    | declarecursorstmt
    | createasstmt
    | creatematviewstmt
    | refreshmatviewstmt
    | executestmt
    ;

explain_option_elem
    : (nonreservedword | analyze_keyword) (boolean_or_string_ | numericonly)?
    ;

preparestmt
    : 'PREPARE' name ('(' type_list ')')? 'AS' preparablestmt
    ;

preparablestmt
    : selectstmt
    | insertstmt
    | updatestmt
    | deletestmt
    ;

executestmt
    : 'EXECUTE' name execute_param_clause?
    | 'CREATE' opttemp? 'TABLE' create_as_target 'AS' 'EXECUTE' name execute_param_clause? with_data_?
    | 'CREATE' opttemp? 'TABLE' ifNotExists create_as_target 'AS' 'EXECUTE' name execute_param_clause? with_data_?
    ;

execute_param_clause
    : '(' expr_list ')'

    ;

deallocatestmt
    : 'DEALLOCATE' 'PREPARE'? ( name |  'ALL' )
    ;

insertstmt
    : with_clause? 'INSERT' 'INTO' qname ( 'AS' name )? insert_rest ('ON' 'CONFLICT' ('(' index_params ')' where?
    | 'ON' 'CONSTRAINT' name)? 'DO' ( 'UPDATE' 'SET' set_clause_list where? | 'NOTHING' ))? returning_clause?
    ;

insert_rest
    : selectstmt
    | ( '(' qnames ')' )? ( 'OVERRIDING' ('USER' | 'SYSTEM') 'VALUE' )? selectstmt
    | 'DEFAULT' 'VALUES'
    ;

returning_clause
    : 'RETURNING' target_list
    ;

// https://www.postgresql.org/docs/current/sql-merge.html
mergestmt
    : 'MERGE' 'INTO'? qname alias_clause? 'USING' ( select_with_parens | qname ) alias_clause? 'ON' a_expr
    ( merge_insert_clause merge_update_clause?
    | merge_update_clause merge_insert_clause?
    ) merge_delete_clause?
    ;

merge_insert_clause
    : 'WHEN' 'NOT' 'MATCHED' ( 'AND' a_expr )? 'THEN'? 'INSERT' ( '(' qnames ')' )? values
    ;

merge_update_clause
    : 'WHEN' 'MATCHED' ( 'AND' a_expr )? 'THEN'? 'UPDATE' 'SET' set_clause_list
    ;

merge_delete_clause
    : 'WHEN' 'MATCHED' 'THEN'? 'DELETE'
    ;

deletestmt
    : with_clause? 'DELETE' 'FROM' relation_expr_opt_alias ('USING' from_list)? where_or_current_clause? returning_clause?
    ;

lockstmt
    : 'LOCK' ('TABLE')? relation_expr_list ('IN' lock_type 'MODE')? ('NOWAIT')?
    ;

lock_type
    : 'ACCESS' ( 'SHARE' | 'EXCLUSIVE' )
    | 'ROW' ( 'SHARE' | 'EXCLUSIVE' )
    | 'SHARE' ( 'UPDATE' 'EXCLUSIVE' | 'ROW' 'EXCLUSIVE' )?
    | 'EXCLUSIVE'
    ;

updatestmt
    : with_clause? 'UPDATE' relation_expr_opt_alias 'SET' set_clause_list from? where_or_current_clause? returning_clause?
    ;

set_clause_list
    : set_clause ( ',' set_clause )*
    ;

set_clause
    : qname '=' a_expr
    | '(' qnames ')' '=' a_expr
    ;

declarecursorstmt
    : 'DECLARE' name ( 'NO' 'SCROLL' | 'SCROLL' | 'BINARY' | 'INSENSITIVE' )* 'CURSOR' hold_? 'FOR' selectstmt
    ;

hold_
    : ( 'WITH' | 'WITHOUT' ) 'HOLD'
    ;

selectstmt
    : select_no_parens
    | select_with_parens
    ;

select_with_parens
    : '(' select_no_parens ')'
    | '(' select_with_parens ')'
    ;

select_no_parens
    : select_clause orderBy? (
        for_locking_clause (select_limit)?
        | select_limit (for_locking_clause)?
     )?
    | with_clause select_clause orderBy? (
        for_locking_clause (select_limit)?
        | select_limit (for_locking_clause)?
     )?
    ;

select_clause
    : simple_select_intersect ( ( 'UNION' | 'EXCEPT' ) all_or_distinct? simple_select_intersect )*
    ;

simple_select_intersect
    : simple_select_pramary ( 'INTERSECT' all_or_distinct? simple_select_pramary )*
    ;

simple_select_pramary
    : (
        'SELECT'
	(  'ALL'? target_list?
		into_clause? from? where?
		group_clause? having_clause? window_clause?
	| distinct_clause target_list
		into_clause? from? where?
		group_clause? having_clause? window_clause?
        )
    )
    | values
    | 'TABLE' relation_expr
    | select_with_parens
    ;

with_clause
    : 'WITH' 'RECURSIVE'? cte_list
    ;

cte_list
    : cte ( ',' cte )*
    ;

cte
    : name name_list_? 'AS' materialized? '(' preparablestmt ')'
    ;

materialized
    : 'NOT'? 'MATERIALIZED'
    ;

into_clause
    : 'INTO' opttempTableName
    ;

opttempTableName
    : ( 'LOCAL' | 'GLOBAL' )? ( 'TEMPORARY' | 'TEMP' ) ('TABLE')? qname
    | 'UNLOGGED' ('TABLE')? qname
    | 'TABLE' qname
    | qname
    ;

all_or_distinct
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
    : a_expr ( 'USING' qual_all_op | sortDir?) nulls_order_?
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
    | Numeric
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
        relation_expr alias_clause? tablesample_clause?
        | func_table func_alias_clause?
        | xmltable alias_clause?
        | select_with_parens alias_clause?
        | 'LATERAL' (
            xmltable alias_clause?
            | func_table func_alias_clause?
            | select_with_parens alias_clause?
        )
        | '(' table_ref (
            'CROSS' 'JOIN' table_ref
            | 'NATURAL' join_type? 'JOIN' table_ref
            | join_type? 'JOIN' table_ref join_qual
         )? ')' alias_clause?
    ) (
        'CROSS' 'JOIN' table_ref
        | 'NATURAL' join_type? 'JOIN' table_ref
        | join_type? 'JOIN' table_ref join_qual
     )*
    ;

alias_clause
    : 'AS'? name ( '(' names ')' )?
    ;

func_alias_clause
    : alias_clause
    | ( 'AS' name? | name ) '(' tablefuncelementlist ')'

    ;

join_type
    : ( 'FULL' | 'LEFT' | 'RIGHT' | 'INNER' ) 'OUTER'?
    ;

join_qual
    : 'USING' '(' names ')'
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
    : relation_expr ( 'AS'? name )?
    ;

tablesample_clause
    : 'TABLESAMPLE' func_name '(' expr_list ')' ('REPEATABLE' '(' a_expr ')')?
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
    : 'WHERE' ( 'CURRENT' 'OF' name | a_expr )
    ;

tablefuncelementlist
    : tablefuncelement ( ',' tablefuncelement )*
    ;

tablefuncelement
    : name typename collate_clause_?
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
    : name ( typename (xmltable_column_option_el)* | 'FOR' 'ORDINALITY' )
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
    : b_expr 'AS' colLabel
    | 'DEFAULT' b_expr
    ;

typename
    : 'SETOF'? simpletypename
	(  ( '[' integer? ']' )*
	| 'ARRAY' ( '[' integer ']' )?
	)
    ;

simpletypename
    : generictype
    | numeric
    | bit
    | character
    | constdatetime
    | constinterval ( interval_? | '(' integer ')' )
    | jsonType
    ;

consttypename
    : numeric
    | constbit
    | constcharacter
    | constdatetime
    | jsonType
    ;

generictype
    : type_function_name ( '.' colLabel )* type_modifiers_?
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
    | 'FLOAT' float_?
    | 'DOUBLE' 'PRECISION'
    | 'DECIMAL' type_modifiers_?
    | 'DEC' type_modifiers_?
    | 'NUMERIC' type_modifiers_?
    | 'BOOLEAN'
    ;

float_
    : '(' integer ')'

    ;

//todo: merge alts

bit
    : bitwithlength
    | bitwithoutlength
    ;

constbit
    : bitwithlength
    | bitwithoutlength
    ;

bitwithlength
    : 'BIT' varying_? '(' expr_list ')'
    ;

bitwithoutlength
    : 'BIT' varying_?
    ;

character
    : character_c ( '(' integer ')' )?
    ;

constcharacter
    : character_c ( '(' integer ')' )?
    ;

character_c
    : ( 'CHARACTER' | 'CHAR' | 'NCHAR' ) varying_?
    | 'VARCHAR'
    | 'NATIONAL' ( 'CHARACTER' | 'CHAR' ) varying_?
    ;

varying_
    : 'VARYING'

    ;

constdatetime
    : ( 'TIMESTAMP' | 'TIME' ) ( '(' integer ')' )? timezone_?
    ;

constinterval
    : 'INTERVAL'
    ;

//TODO with_la was used

timezone_
    : 'WITH' 'TIME' 'ZONE'
    | 'WITHOUT' 'TIME' 'ZONE'

    ;

interval_
    : 'YEAR'
    | 'MONTH'
    | 'DAY'
    | 'HOUR'
    | 'MINUTE'
    | interval_second
    | 'YEAR' 'TO' 'MONTH'
    | 'DAY' 'TO' ( 'HOUR' | 'MINUTE' | interval_second )
    | 'HOUR' 'TO' ( 'MINUTE' | interval_second )
    | 'MINUTE' 'TO' interval_second

    ;

interval_second
    : 'SECOND' ( '(' integer ')' )?
    ;

jsonType
    : 'JSON'
    ;

escape_
    : 'ESCAPE' a_expr

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

/*16*/

a_expr_and
    : a_expr_between ( 'AND' a_expr_between )*
    ;

/*21*/

a_expr_between
    : a_expr_in ( 'NOT'? 'BETWEEN' 'SYMMETRIC'? a_expr_in 'AND' a_expr_in )?
    ;

/*20*/

a_expr_in
    : a_expr_unary_not ( 'NOT'? 'IN' in_expr )?
    ;

/*15*/

a_expr_unary_not
    : 'NOT'? a_expr_isnull
    ;

/*14*/

/*moved to c_expr*/

/*13*/

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
        | subquery_Op sub_type ( select_with_parens | '(' a_expr ')')
      )?
    ;

a_expr_like
    : a_expr_qual_op ( 'NOT'? ( 'LIKE' | 'ILIKE' | 'SIMILAR' 'TO' ) a_expr_qual_op escape_? )?
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
    : ( '-' | '+' )? a_expr_at_time_zone /* */
    ;

a_expr_at_time_zone
    : a_expr_collate ( 'AT' 'TIME' 'ZONE' a_expr )?
    ;

a_expr_collate
    : a_expr_typecast ( 'COLLATE' qname )?
    ;

a_expr_typecast
    : c_expr ( '::' typename )*
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
    | aexprconst                                                       # c_expr_expr
    | '(' a_expr_in_parens = a_expr ')' indirection_el* # c_expr_expr
    | case_expr                                                        # c_expr_case
    | func_expr                                                        # c_expr_expr
    | select_with_parens (indirection_el)*                                  # c_expr_expr
    | explicit_row                                                     # c_expr_expr
    | implicit_row                                                     # c_expr_expr
    | row 'OVERLAPS' row                               # c_expr_expr
    | 'DEFAULT'                                                          # c_expr_expr
    ;

//plsqlvariablename
//    : 'PLSQLVARIABLENAME'
//    ;

func_application
    : func_name
    '(' (
        func_arg_list
        ( ',' 'VARIADIC' func_arg_expr )? orderBy?
        | 'VARIADIC' func_arg_expr orderBy?
        | ( 'ALL' | 'DISTINCT' ) func_arg_list orderBy?
        | '*'
      )?
    ')'
    ;

func_expr
    : func_application ('WITHIN' 'GROUP' '(' orderBy ')')? ('FILTER' '(' 'WHERE' a_expr ')')? ('OVER' ( window_specification | name ))?
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
    | 'POSITION' '(' position_list? ')'
    | 'SUBSTRING' '(' ( substr_list | func_arg_list? ) ')'
    | 'TREAT' '(' a_expr 'AS' typename ')'
    | 'TRIM' '(' ( 'BOTH' | 'LEADING' | 'TRAILING' )? trim_list ')'
    | 'NULLIF' '(' a_expr ',' a_expr ')'
    | 'COALESCE' '(' expr_list ')'
    | 'GREATEST' '(' expr_list ')'
    | 'LEAST' '(' expr_list ')'

    | 'XMLCONCAT' '(' expr_list ')'
    | 'XMLELEMENT' '(' 'NAME' colLabel ( ',' ( xml_attributes | expr_list ) )? ')'
    | 'XMLEXISTS' '(' c_expr xmlexists_argument ')'
    | 'XMLFOREST' '(' xml_attribute_list ')'
    | 'XMLPARSE' '(' document_or_content a_expr ('PRESERVE' | 'STRIP') 'WHITESPACE'? ')'
    | 'XMLPI' '(' 'NAME' colLabel ( ',' a_expr )? ')'
    | 'XMLROOT' '(' 'XML' a_expr ',' 'VERSION' (a_expr |  'NO' 'VALUE') xml_root_standalone_? ')'
    | 'XMLSERIALIZE' '(' document_or_content a_expr 'AS' simpletypename ')'

    | 'JSON' '(' json_value_expr json_key_uniqueness_constraint? ')'
    | 'JSON_ARRAY' '(' ( json_value_expr_list json_object_constructor_null_clause? json_returning_clause? | select_no_parens ('FORMAT_LA' 'JSON' ( 'ENCODING' name )?)? json_returning_clause? | json_returning_clause? ) ')'
    | 'JSON_EXISTS' '(' json_value_expr ',' a_expr json_passing_clause? jsonOnError? ')'
    | 'JSON_OBJECT' '(' ( func_arg_list | json_name_and_value_list json_object_constructor_null_clause? json_key_uniqueness_constraint? json_returning_clause? | json_returning_clause? ) ')'
    | 'JSON_QUERY' '(' json_value_expr ',' a_expr json_passing_clause? json_returning_clause? json_wrapper_behavior? json_quotes_clause? jsonOnEmpty? jsonOnError? ')'
    | 'JSON_SCALAR' '(' a_expr ')'
    | 'JSON_SERIALIZE' '(' json_value_expr json_returning_clause? ')'
    | 'JSON_VALUE' '(' json_value_expr ',' a_expr json_passing_clause? json_returning_clause? jsonOnEmpty? jsonOnError? ')'
    | 'MERGE_ACTION' '(' ')'
    ;

xml_root_standalone_
    : ',' 'STANDALONE' 'YES'
    | ',' 'STANDALONE' 'NO'
    | ',' 'STANDALONE' 'NO' 'VALUE'
    ;

xml_attributes
    : 'XMLATTRIBUTES' '(' xml_attribute_list ')'
    ;

xml_attribute_list
    : xml_attribute_el ( ',' xml_attribute_el )*
    ;

xml_attribute_el
    : a_expr ( 'AS' colLabel )?
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
    : 'WINDOW' window_definition_list
    ;

window_definition_list
    : window_definition ( ',' window_definition )*
    ;

window_definition
    : name 'AS' window_specification
    ;

window_specification
    : '(' name? partition_clause_? orderBy? frame_clause_? ')'
    ;

partition_clause_
    : 'PARTITION' 'BY' expr_list
    ;

frame_clause_
    : 'RANGE' frame_extent window_exclusion_clause_?
    | 'ROWS' frame_extent window_exclusion_clause_?
    | 'GROUPS' frame_extent window_exclusion_clause_?

    ;

frame_extent
    : frame_bound
    | 'BETWEEN' frame_bound 'AND' frame_bound
    ;

frame_bound
    : 'UNBOUNDED' ( 'PRECEDING' | 'FOLLOWING' )
    | 'CURRENT' 'ROW'
    | a_expr ( 'PRECEDING' | 'FOLLOWING' )
    ;

window_exclusion_clause_
    : 'EXCLUDE' ( 'CURRENT' 'ROW' | 'GROUP' | 'TIES' | 'NO' 'OTHERS' ) ;

row
    : 'ROW' '(' expr_list? ')'
    | '(' expr_list ',' a_expr ')'
    ;

explicit_row
    : 'ROW' '(' expr_list? ')'
    ;

implicit_row
    : '(' expr_list ',' a_expr ')'
    ;

sub_type
    : 'ANY'
    | 'SOME'
    | 'ALL'
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
    | '&'
//    | '&&'
//    | '@@'
//    | '==='
//    | '===='
//| '!=='
    ;

qual_op
    : Operator
    | '||'
    | '->'
    | '->>'
    | '#-'
    | '#>'
    | '#>>'
    | '!='
    | '~'
//    | '@'
//    | '@@'
//    | '@>'
//    | '<@'
//    | '|/'
//    | '||/'
    | '*<'
    | '*<='
    | '*<>'
    | '*>='
    | '*>'
    | '*='
    | '&'
//    | '&&'
//    | '&<'
//    | '&>'
    | '?'
    | '!!'
    | '|'
    | '-|-'
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
    | type_function_name ( ':=' | '=>' ) a_expr
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

position_list
    : b_expr 'IN' b_expr

    ;

substr_list
    : a_expr 'FROM' a_expr 'FOR' a_expr
    | a_expr 'FOR' a_expr 'FROM' a_expr
    | a_expr 'FROM' a_expr
    | a_expr 'FOR' a_expr
    | a_expr 'SIMILAR' a_expr 'ESCAPE' a_expr
    ;

trim_list
    : a_expr 'FROM' expr_list
    | 'FROM' expr_list
    | expr_list
    ;

in_expr
    : select_with_parens               # in_expr_select
    | '(' expr_list ')' # in_expr_list
    ;

case_expr
    : 'CASE' case_arg? when_clause+ case_default? 'END'
    ;

when_clause
    : 'WHEN' a_expr 'THEN' a_expr
    ;

case_default
    : 'ELSE' a_expr
    ;

case_arg
    : a_expr
    ;

indirection_el
    : '.' ( colLabel | '*' )
    | '[' ( a_expr | a_expr? ':' a_expr? ) ']'
    ;

json_passing_clause
: 'PASSING' json_arguments ;

json_arguments
: json_argument | json_arguments ',' json_argument ;

json_argument
  : json_value_expr 'AS' colLabel ;

json_wrapper_behavior
  : 'WITHOUT' 'ARRAY'?	'WRAPPER'
  | 'WITH' ( 'UNCONDITIONAL' | 'CONDITIONAL' )? 'ARRAY'? 'WRAPPER'
  ;

json_behavior
  : 'DEFAULT' a_expr
  | ('ERROR'
			| 'NULL'
			| 'TRUE'
			| 'FALSE'
			| 'UNKNOWN'
			| 'EMPTY' ( 'ARRAY'|'OBJECT' )?)
  ;

jsonOnEmpty : json_behavior 'ON' 'EMPTY' ;

jsonOnError:
			json_behavior 'ON' 'ERROR'
		;

json_value_expr:
			a_expr ('FORMAT_LA' 'JSON' ( 'ENCODING' name )?)?
		;

json_quotes_clause
: ( 'KEEP' | 'OMIT' ) 'QUOTES' ( 'ON' 'SCALAR' 'STRING' )?
;

json_returning_clause:
			'RETURNING' typename ('FORMAT_LA' 'JSON' ( 'ENCODING' name )?)?
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

json_name_and_value_list:
			json_name_and_value (',' json_name_and_value)*
		;

json_name_and_value:
			c_expr 'VALUE' json_value_expr
			|
			a_expr ':' json_value_expr
		;

json_object_constructor_null_clause
  : ( 'NULL' | 'ABSENT' ) 'ON' 'NULL' ;

json_value_expr_list:
			json_value_expr (',' json_value_expr)*
		;

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
    : a_expr ( 'AS' colLabel | (identifier | bare_label_keyword) )?
    | '*'
    ;

qnames
    : qname ( ',' qname )*
    ;

qname
    : name indirection_el*
    ;


name_list_
    : '(' names ')'
    ;

names
    : name ( ',' name )*
    ;

func_name
    : type_function_name
    | name indirection_el+
    ;

aexprconst
    : integer
    | Numeric
    | string
    | BinaryStringConstant
    | HexadecimalStringConstant
    | func_name ( string | '(' func_arg_list orderBy? ')' string )
    | consttypename string
    | constinterval ( string interval_? | '(' integer ')' string )
    | 'TRUE'
    | 'FALSE'
    | 'NULL'
    ;

integer
    : Integral
    | BinaryIntegral
    | OctalIntegral
    | HexadecimalIntegral
    ;

string
    : string_ ( 'UESCAPE' string_ )?
    ;

string_
    : StringConstant
    | UnicodeEscapeStringConstant
//    | BeginDollarStringConstant DollarText* EndDollarStringConstant
//    | EscapeStringConstant
    ;

signedInteger
    : ('+' | '-')? integer
    ;

rolespec
    : nonreservedword
    | 'CURRENT_USER'
    | 'SESSION_USER'
    ;

role_list
    : rolespec ( ',' rolespec )*
    ;

name
    : identifier
    | unreserved_keyword
    | col_name_keyword
    ;

type_function_name
    : identifier
    | unreserved_keyword
    | type_func_name_keyword
    ;

nonreservedword
    : identifier
    | unreserved_keyword
    | col_name_keyword
    | type_func_name_keyword
    ;

colLabel
    : identifier
    | unreserved_keyword
    | col_name_keyword
    | type_func_name_keyword
    | reserved_keyword
    | 'EXIT' //NB: not in gram.y official source.
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

/* Reserved keyword --- these keywords are usable only as a ColLabel.
 *
 * Keywords appear here if they could not be distinguished from variable,
 * type, or function names in some contexts.  Don't put things here unless
 * forced to.
 */
reserved_keyword
    : 'ALL'
    | 'ANALYSE'
    | 'ANALYZE'
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
    | 'ANALYSE'
    | 'ANALYZE'
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

snuff : stinky* ;
stinky : Operator ;


//  : '~' .*? '~'
Operator
//  : [@] [*<>=~!@%^&|`?#/+-]*
  : '@+@'
  | '@-@'

//  : [~!@%^&|`?#] [*<>=~!@%^&|`?#/+-]*
//  : [~]+ // [*<>=~!@%^&|`?#]* [-+]?
//  | [*<>=+] [*<>=~!@%^&|`?#/+-]* [*<>=~!@%^&|`?#]
  ;



/* This rule handles operators which end with + or -, and sets the token type to Operator. It is comprised of four
 * parts, in order:
 *
 *   1. 'A' prefix, which does not contain a character from the required set which allows + or - to appear at the end of
 *      the operator.
 *   2. 'A' character from the required set which allows + or - to appear at the end of the operator.
 *   3. An optional sub-token which takes the form of an operator which does not include a + or - at the end of the
 *      sub-token.
 *   4. 'A' suffix sequence of + and - characters.
 */

//OperatorEndingWithPlusMinus:
//    (OperatorCharacterNotAllowPlusMinusAtEnd | '-' {this.CheckLaMinus()}? | '/' {this.CheckLaStar()}? )* OperatorCharacterAllowPlusMinusAtEnd Operator? (
//        '+'
//        | '-' {this.CheckLaMinus()}?
//    )+        -> type (Operator)
//;
// Each of the following fragment rules omits the +, -, and / characters, which must always be handled in a special way

// by the operator rules above.

//fragment OperatorCharacter: [*<>=~!@%^&|`?#];
//// these are the operator characters that don't count towards one ending with + or -
//
//fragment OperatorCharacterNotAllowPlusMinusAtEnd: [*<>=+];
//// an operator may end with + or - if it contains one of these characters
//
//fragment OperatorCharacterAllowPlusMinusAtEnd: [~!@%^&|`?#];

 PARAM: '$' ([0-9])+;
  //PARAM: [:$] ([0-9])+;


//  Dollar: '$';

//  TYPECAST: '::';


Identifier: [A-Z_\u007F-\uFFFF] [A-Z_$0-9\u007F-\uFFFF]*;

QuotedIdentifier: '"' ('""' | ~ [\u0000"])* '"';

UnicodeQuotedIdentifier: 'U' '&' QuotedIdentifier;

StringConstant
  : '\'' ('\'\'' | ~ '\'')* '\''
      // Postgres
      | '$$' .*? '$$'
  ;

//BeginEscapeStringConstant: 'E' '\'' -> more, pushMode (EscapeStringConstantMode);

UnicodeEscapeStringConstant: 'U' '&' '\'' ('\'\'' | ~ '\'')* '\'';

//BeginDollarStringConstant: '$' Tag? '$' {this.PushTag();} -> pushMode (DollarQuotedStringMode);
///* "The tag, if any, of a dollar-quoted string follows the same rules as an
// * unquoted identifier, except that it cannot contain a dollar sign."
// */

//fragment Tag: [A-Z_\u007F-\uFFFF] [A-Z_0-9\u007F-\uFFFF]*;

BinaryStringConstant: 'B' '\'' [01]* '\'';

HexadecimalStringConstant: 'X' '\'' [0-9A-F]* '\'';

Integral: Digits;

BinaryIntegral: '0b' Digits;

OctalIntegral: '0o' Digits;

HexadecimalIntegral: '0x' Digits;

Numeric:
    Digits '.' Digits?
    (
        'E' [+-]? Digits
    )?
    | '.' Digits ('E' [+-]? Digits)?
    | Digits 'E' [+-]? Digits
;

fragment Digits: [0-9]+;

PLSQLVARIABLENAME: ':' [A-Z_\u007F-\uFFFF] [A-Z_$0-9\u007F-\uFFFF]*;

PLSQLIDENTIFIER: ':"' ('\\' . | '""' | ~ ('"' | '\\'))* '"';

Whitespace: [ \t]+ -> channel (HIDDEN);

Newline: ('\r' '\n'? | '\n') -> channel (HIDDEN);

LineComment: '--' ~ [\r\n]* -> channel (HIDDEN);

BlockComment:
    ('/*' ('/'* BlockComment | ~ [/*] | '/'+ ~ [/*] | '*'+ ~ [/*])* '*'* '*/') -> channel (HIDDEN)
;

//MetaCommand: '\\' -> pushMode(META), more ;

ErrorCharacter: .;

//mode EscapeStringConstantMode;
//EscapeStringConstant: EscapeStringText '\'' -> mode (AfterEscapeStringConstantMode);
//
//
//fragment EscapeStringText options { caseInsensitive = false; }:
//    (
//        '\'\''
//        | '\\' (
//            // two-digit hex escapes are still valid when treated as single-digit escapes
//            'x' [0-9a-fA-F]
//            | 'u' [0-9a-fA-F] [0-9a-fA-F] [0-9a-fA-F] [0-9a-fA-F]
//            | 'U' [0-9a-fA-F] [0-9a-fA-F] [0-9a-fA-F] [0-9a-fA-F] [0-9a-fA-F] [0-9a-fA-F] [0-9a-fA-F] [0-9a-fA-F]
//            | // Any character other than the Unicode escapes can follow a backslash. Some have special meaning,
//            // but that doesn't affect the syntax.
//            ~ [xuU]
//        )
//        | ~ ['\\]
//    )*
//;
//
//InvalidUnterminatedEscapeStringConstant:
//    InvalidEscapeStringText
//    // Handle a final unmatched \ character appearing at the end of the file
//    '\\'? 'EOF'
//;
//
//fragment InvalidEscapeStringText: ('\'\'' | '\\' . | ~ ['\\])*;

//mode AfterEscapeStringConstantMode;
//AfterEscapeStringConstantMode_Whitespace: Whitespace -> type (Whitespace), channel (HIDDEN);
//
//AfterEscapeStringConstantMode_Newline:
//    Newline -> type (Newline), channel (HIDDEN), mode (AfterEscapeStringConstantWithNewlineMode)
//;

//mode AfterEscapeStringConstantWithNewlineMode;
//AfterEscapeStringConstantWithNewlineMode_Whitespace:
//    Whitespace -> type (Whitespace), channel (HIDDEN)
//;
//
//AfterEscapeStringConstantWithNewlineMode_Newline: Newline -> type (Newline), channel (HIDDEN);
//
//AfterEscapeStringConstantWithNewlineMode_Continued:
//    '\'' -> more, mode (EscapeStringConstantMode)
//;

//mode DollarQuotedStringMode;
//DollarText:
//    ~ '$'+
//    //| '$'([0-9])+
//    | // this alternative improves the efficiency of handling $ characters within a dollar-quoted string which are
//
//    // not part of the ending tag.
//    '$' ~ '$'*
//;

//// NB: Next rule on two lines in order to make transformGrammar.py easy.
//EndDollarStringConstant: ('$' Tag? '$') {this.IsTag()}?
//    {this.PopTag();} -> popMode;

META : '\\' ~[;\r\n\\"] .*? ('\\\\' | [\r\n]+) -> skip // type(SEMI)
;
