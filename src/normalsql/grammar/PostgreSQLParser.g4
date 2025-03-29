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

parser grammar PostgreSQLParser;

options {
  tokenVocab = PostgreSQLLexer;
  caseInsensitive=true;
}

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
    | CHECKPOINT
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
    : CALL func_application
    ;

createrolestmt
    : CREATE ROLE rolespec (WITH)? createoptroleelem*
    ;

alteroptroleelem
    : PASSWORD ( string | NULL_P )
    | ( ENCRYPTED | UNENCRYPTED ) PASSWORD string
    | INHERIT
    | CONNECTION LIMIT signediconst
    | VALID UNTIL string
    | USER role_list
    | identifier
    ;

createoptroleelem
    : alteroptroleelem
    | SYSID integer
    | ADMIN role_list
    | ROLE role_list
    | IN_P ( ROLE | GROUP_P ) role_list
    ;

createuserstmt
    : CREATE USER rolespec (WITH)? createoptroleelem*
    ;

alterrolestmt
    : ALTER ( ROLE | USER ) rolespec (WITH)? alteroptroleelem*
    ;

alterrolesetstmt
    : ALTER ( ROLE | USER ) ALL? rolespec (IN_P DATABASE name)? setresetclause
    ;

droprolestmt
    : DROP ( ROLE | USER | GROUP_P ) ifExists? role_list
    ;

creategroupstmt
    : CREATE GROUP_P rolespec (WITH)? createoptroleelem*
    ;

altergroupstmt
    : ALTER GROUP_P rolespec add_drop USER role_list
    ;

add_drop
    : ADD_P
    | DROP
    ;

createschemastmt
    : CREATE SCHEMA ifNotExists? ( (name)? AUTHORIZATION rolespec | name ) (createstmt
    | indexstmt
    | createseqstmt
    | createtrigstmt
    | grantstmt
    | viewstmt)*
    ;

variablesetstmt
    : SET ( LOCAL | SESSION )? set_rest
    ;

set_rest
    : TRANSACTION transaction_mode_list
    | SESSION CHARACTERISTICS AS TRANSACTION transaction_mode_list
    | set_rest_more
    ;

generic_set
    : var_name ( TO | EQUAL ) ( var_list | DEFAULT )
    ;

set_rest_more
    : generic_set
    | var_name FROM CURRENT_P
    | TIME ZONE zone_value
    | CATALOG string
    | SCHEMA string
    | NAMES encoding_?
    | ROLE nonreservedword_or_sconst
    | SESSION AUTHORIZATION nonreservedword_or_sconst
    | XML_P OPTION document_or_content
    | TRANSACTION SNAPSHOT string
    ;

var_name
    : name ( DOT name )*
    ;

var_list
    : var_value ( COMMA var_value )*
    ;

var_value
    : boolean_or_string_
    | numericonly
    ;

iso_level
    : READ ( UNCOMMITTED | COMMITTED )
    | REPEATABLE READ
    | SERIALIZABLE
    ;

boolean_or_string_
    : TRUE_P
    | FALSE_P
    | ON
    | nonreservedword_or_sconst
    ;

zone_value
    : string
    | identifier
    | constinterval string interval_?
    | constinterval OPEN_PAREN integer CLOSE_PAREN string
    | numericonly
    | DEFAULT
    | LOCAL
    ;

encoding_
    : string
    | DEFAULT
    
    ;

nonreservedword_or_sconst
    : nonreservedword
    | string
    ;

variableresetstmt
    : RESET reset_rest
    ;

reset_rest
    : (var_name
    | ALL)
    | TIME ZONE
    | TRANSACTION ISOLATION LEVEL
    | SESSION AUTHORIZATION
    ;

setresetclause
    : SET set_rest
    | variableresetstmt
    ;

functionsetresetclause
    : SET set_rest_more
    | variableresetstmt
    ;

variableshowstmt
    : SHOW ( var_name | TIME ZONE | TRANSACTION ISOLATION LEVEL | SESSION AUTHORIZATION | ALL )
    ;

constraintssetstmt
    : SET CONSTRAINTS (ALL | qnames) (DEFERRED | IMMEDIATE)
    ;

discardstmt
    : DISCARD ( ALL | TEMP | TEMPORARY | PLANS | SEQUENCES )
    ;

altertablestmt
    : ALTER TABLE ifExists? relation_expr ( alter_table_cmds | (ATTACH PARTITION qname partitionboundspec
    | DETACH PARTITION qname) )
    | ALTER INDEX ifExists? qname ( alter_table_cmds | ATTACH PARTITION qname )
    | ALTER TABLE ALL IN_P TABLESPACE name ( OWNED BY role_list  )? SET TABLESPACE name (NOWAIT)?
    | ALTER INDEX ALL IN_P TABLESPACE name ( OWNED BY role_list )? SET TABLESPACE name (NOWAIT)?
    | ALTER SEQUENCE ifExists? qname alter_table_cmds
    | ALTER VIEW ifExists? qname alter_table_cmds
    | ALTER MATERIALIZED VIEW ifExists? qname alter_table_cmds
    | ALTER MATERIALIZED VIEW ALL IN_P TABLESPACE name ( OWNED BY role_list )? SET TABLESPACE name (NOWAIT)?
    | ALTER FOREIGN TABLE ifExists? relation_expr alter_table_cmds
    ;

alter_table_cmds
    : alter_table_cmd ( COMMA alter_table_cmd )*
    ;

alter_table_cmd
    : ADD_P columnDef
    | ADD_P ifNotExists columnDef
    | ADD_P COLUMN columnDef
    | ADD_P COLUMN ifNotExists columnDef
    | ALTER column_? name alter_column_default
    | ALTER column_? name DROP NOT NULL_P
    | ALTER column_? name SET NOT NULL_P
    | ALTER column_? name DROP EXPRESSION
    | ALTER column_? name DROP EXPRESSION IF_P EXISTS
    | ALTER column_? name SET STATISTICS signediconst
    | ALTER column_? integer SET STATISTICS signediconst
    | ALTER column_? name SET reloptions
    | ALTER column_? name RESET reloptions
    | ALTER column_? name SET STORAGE name
    | ALTER column_? name ADD_P GENERATED generated_when AS IDENTITY_P optparenthesizedseqoptlist?
    | ALTER column_? name alter_identity_column_option_list
    | ALTER column_? name DROP IDENTITY_P
    | ALTER column_? name DROP IDENTITY_P IF_P EXISTS
    | DROP column_? IF_P EXISTS name drop_behavior_?
    | DROP column_? name drop_behavior_?
    | ALTER column_? name set_data_? TYPE_P typename collate_clause_? alter_using?
    | ALTER column_? name alter_generic_options
    | ADD_P tableconstraint
    | ALTER CONSTRAINT name constraintattributespec
    | VALIDATE CONSTRAINT name
    | DROP CONSTRAINT IF_P EXISTS name drop_behavior_?
    | DROP CONSTRAINT name drop_behavior_?
    | SET WITHOUT OIDS
    | CLUSTER ON name
    | SET WITHOUT CLUSTER
    | SET LOGGED
    | SET UNLOGGED
    | ENABLE_P TRIGGER name
    | ENABLE_P ALWAYS TRIGGER name
    | ENABLE_P REPLICA TRIGGER name
    | ENABLE_P TRIGGER ALL
    | ENABLE_P TRIGGER USER
    | DISABLE_P TRIGGER name
    | DISABLE_P TRIGGER ALL
    | DISABLE_P TRIGGER USER
    | ENABLE_P RULE name
    | ENABLE_P ALWAYS RULE name
    | ENABLE_P REPLICA RULE name
    | DISABLE_P RULE name
    | INHERIT qname
    | NO INHERIT qname
    | OF any_name
    | NOT OF
    | OWNER TO rolespec
    | SET TABLESPACE name
    | SET reloptions
    | RESET reloptions
    | REPLICA IDENTITY_P replica_identity
    | ENABLE_P ROW LEVEL SECURITY
    | DISABLE_P ROW LEVEL SECURITY
    | FORCE ROW LEVEL SECURITY
    | NO FORCE ROW LEVEL SECURITY
    | alter_generic_options
    ;

alter_column_default
    : SET DEFAULT a_expr
    | DROP DEFAULT
    ;

drop_behavior_
    : CASCADE
    | RESTRICT
    
    ;

collate_clause_
    : COLLATE any_name
    
    ;

alter_using
    : USING a_expr
    
    ;

replica_identity
    : NOTHING
    | FULL
    | DEFAULT
    | USING INDEX name
    ;

reloptions
    : OPEN_PAREN reloption_list CLOSE_PAREN
    ;

reloptions_
    : WITH reloptions
    
    ;

reloption_list
    : reloption_elem ( COMMA reloption_elem )*
    ;

reloption_elem
    : colLabel ( EQUAL def_arg | DOT colLabel ( EQUAL def_arg )? )?
    ;

alter_identity_column_option_list
    : alter_identity_column_option+
    ;

alter_identity_column_option
    : RESTART ( (WITH)? numericonly )?
    | SET ( seqoptelem | GENERATED generated_when )
    ;

partitionboundspec
    : FOR VALUES WITH OPEN_PAREN hash_partbound CLOSE_PAREN
    | FOR VALUES IN_P OPEN_PAREN expr_list CLOSE_PAREN
    | FOR VALUES FROM OPEN_PAREN expr_list CLOSE_PAREN TO OPEN_PAREN expr_list CLOSE_PAREN
    | DEFAULT
    ;

hash_partbound_elem
    : nonreservedword integer
    ;

hash_partbound
    : hash_partbound_elem ( COMMA hash_partbound_elem )*
    ;

altercompositetypestmt
    : ALTER TYPE_P any_name alter_type_cmds
    ;

alter_type_cmds
    : alter_type_cmd ( COMMA alter_type_cmd )*
    ;

alter_type_cmd
    : ADD_P ATTRIBUTE tablefuncelement drop_behavior_?
    | DROP ATTRIBUTE ifExists? name drop_behavior_?
    | ALTER ATTRIBUTE name set_data_? TYPE_P typename collate_clause_? drop_behavior_?
    ;

closeportalstmt
    : CLOSE ( name | ALL )
    ;

copystmt
    : COPY (BINARY)? qname column_list_? copy_from (PROGRAM)? copy_file_name copy_delimiter? (WITH)? copy_options where?
    | COPY OPEN_PAREN preparablestmt CLOSE_PAREN TO (PROGRAM)? copy_file_name (WITH)? copy_options
    ;

copy_from
    : FROM
    | TO
    ;

copy_file_name
    : string
    | STDIN
    | STDOUT
    ;

copy_options
    : copy_opt_item*
    | OPEN_PAREN copy_generic_opt_list CLOSE_PAREN
    ;

copy_opt_item
    : BINARY
    | FREEZE
    | DELIMITER (AS)? string
    | NULL_P (AS)? string
    | CSV
    | HEADER_P
    | QUOTE (AS)? string
    | ESCAPE (AS)? string
    | FORCE QUOTE columnlist
    | FORCE QUOTE STAR
    | FORCE NOT NULL_P columnlist
    | FORCE NULL_P columnlist
    | ENCODING string
    ;

copy_delimiter
    : (USING)? DELIMITERS string
    ;

copy_generic_opt_list
    : copy_generic_opt_elem ( COMMA copy_generic_opt_elem )*
    ;

copy_generic_opt_elem
    : colLabel copy_generic_opt_arg?
    ;

copy_generic_opt_arg
    : boolean_or_string_
    | numericonly
    | STAR
    | OPEN_PAREN copy_generic_opt_arg_list CLOSE_PAREN
    
    ;

copy_generic_opt_arg_list
    : boolean_or_string_ ( COMMA boolean_or_string_ )*
    ;

createstmt
    : CREATE opttemp? TABLE ifNotExists? qname (
        OPEN_PAREN (tableelementlist)? CLOSE_PAREN optinherit? (partitionspec)? table_access_method_clause? optwith? oncommitoption? opttablespace?
        | OF any_name opttypedtableelementlist? (partitionspec)? table_access_method_clause? optwith? oncommitoption? opttablespace?
        | PARTITION OF qname opttypedtableelementlist? partitionboundspec (partitionspec)? table_access_method_clause? optwith? oncommitoption?
            opttablespace?
    )
    ;

opttemp
    : TEMPORARY
    | TEMP
    | LOCAL ( TEMPORARY | TEMP )
    | GLOBAL ( TEMPORARY | TEMP )
    | UNLOGGED
    
    ;

opttypedtableelementlist
    : OPEN_PAREN typedtableelementlist CLOSE_PAREN
    ;

tableelementlist
    : tableelement ( COMMA tableelement )*
    ;

typedtableelementlist
    : typedtableelement ( COMMA typedtableelement )*
    ;

tableelement
    : tableconstraint
    | tablelikeclause
    | columnDef
    ;

typedtableelement
    : columnOptions
    | tableconstraint
    ;

columnDef
    : name typename create_generic_options? colconstraint*
    ;

columnOptions
    : name ( WITH OPTIONS )? colconstraint*
    ;

colconstraint
    : CONSTRAINT name colconstraintelem
    | colconstraintelem
    | constraintattr
    | COLLATE any_name
    ;

colconstraintelem
    : NOT? NULL_P
    | UNIQUE definition_? optconstablespace?
    | PRIMARY KEY definition_? optconstablespace?
    | CHECK OPEN_PAREN a_expr CLOSE_PAREN (NO INHERIT)?
    | DEFAULT b_expr
    | GENERATED generated_when AS ( 
        IDENTITY_P optparenthesizedseqoptlist?
        | OPEN_PAREN a_expr CLOSE_PAREN STORED
    )
    | REFERENCES qname column_list_? key_match? key_actions?
    ;

generated_when
    : ALWAYS
    | BY DEFAULT
    ;

constraintattr
    : NOT? DEFERRABLE
    | INITIALLY ( DEFERRED | IMMEDIATE )
    ;

tablelikeclause
    : LIKE qname tablelikeoptionlist
    ;

tablelikeoptionlist
    : ( ( INCLUDING | EXCLUDING ) tablelikeoption )*
    ;

tablelikeoption
    : COMMENTS
    | CONSTRAINTS
    | DEFAULTS
    | IDENTITY_P
    | GENERATED
    | INDEXES
    | STATISTICS
    | STORAGE
    | ALL
    ;

tableconstraint
    : ( CONSTRAINT name )? constraintelem
    ;

constraintelem
    : CHECK OPEN_PAREN a_expr CLOSE_PAREN constraintattributespec
    | UNIQUE ( 
        OPEN_PAREN columnlist CLOSE_PAREN c_include_? definition_? optconstablespace? constraintattributespec
        | existingindex constraintattributespec
    )
    | PRIMARY KEY ( 
        OPEN_PAREN columnlist CLOSE_PAREN c_include_? definition_? optconstablespace? constraintattributespec
        | existingindex constraintattributespec
    )
    | EXCLUDE access_method_clause? OPEN_PAREN exclusionconstraintlist CLOSE_PAREN c_include_? definition_? optconstablespace? exclusionwhereclause?
        constraintattributespec
    | FOREIGN KEY OPEN_PAREN columnlist CLOSE_PAREN REFERENCES qname column_list_? key_match? key_actions? constraintattributespec
    ;

column_list_
    : OPEN_PAREN columnlist CLOSE_PAREN
    ;

columnlist
    : name ( COMMA name )*
    ;

c_include_
    : INCLUDE OPEN_PAREN columnlist CLOSE_PAREN
    ;

key_match
    : MATCH ( FULL | PARTIAL | SIMPLE )
    ;

exclusionconstraintlist
    : exclusionconstraintelem ( COMMA exclusionconstraintelem )*
    ;

exclusionconstraintelem
    : index_elem WITH ( any_operator | OPERATOR OPEN_PAREN any_operator CLOSE_PAREN )
    ;

exclusionwhereclause
    : WHERE OPEN_PAREN a_expr CLOSE_PAREN
    
    ;

key_actions
    : key_update
    | key_delete
    | key_update key_delete
    | key_delete key_update
    
    ;

key_update
    : ON UPDATE key_action
    ;

key_delete
    : ON DELETE_P key_action
    ;

key_action
    : NO ACTION
    | RESTRICT
    | CASCADE
    | SET ( NULL_P | DEFAULT )
    ;

optinherit
    : INHERITS OPEN_PAREN qnames CLOSE_PAREN
    ;

partitionspec
    : PARTITION BY name OPEN_PAREN part_params CLOSE_PAREN
    ;

part_params
    : part_elem ( COMMA part_elem )*
    ;

part_elem
    : name collate_? class_?
    | func_expr_windowless collate_? class_?
    | OPEN_PAREN a_expr CLOSE_PAREN collate_? class_?
    ;

table_access_method_clause
    : USING name
    ;

optwith
    : WITH reloptions
    | WITHOUT OIDS
    ;

oncommitoption
    : ON COMMIT ( DROP | DELETE_P ROWS | PRESERVE ROWS )
    ;

opttablespace
    : TABLESPACE name
    ;

optconstablespace
    : USING INDEX TABLESPACE name
    ;

existingindex
    : USING INDEX name
    ;

createstatsstmt
    : CREATE STATISTICS ifNotExists? any_name name_list_? ON expr_list FROM from_list
    ;

alterstatsstmt
    : ALTER STATISTICS ifExists? any_name SET STATISTICS signediconst
    ;

createasstmt
    : CREATE opttemp? TABLE ifNotExists? create_as_target AS selectstmt with_data_?
    ;

create_as_target
    : qname column_list_? table_access_method_clause? optwith? oncommitoption? opttablespace?
    ;

with_data_
    : WITH ( DATA_P | NO DATA_P )
    ;

creatematviewstmt
    : CREATE (UNLOGGED)? MATERIALIZED VIEW ifNotExists? create_mv_target AS selectstmt with_data_?
    ;

create_mv_target
    : qname column_list_? table_access_method_clause? reloptions_? opttablespace?
    ;

refreshmatviewstmt
    : REFRESH MATERIALIZED VIEW concurrently_? qname with_data_?
    ;

createseqstmt
    : CREATE opttemp? SEQUENCE ifNotExists? qname seqoptelem*
    ;

alterseqstmt
    : ALTER SEQUENCE ifExists? qname seqoptelem+
    ;

optparenthesizedseqoptlist
    : OPEN_PAREN seqoptelem+ CLOSE_PAREN
    ;

seqoptelem
    : AS simpletypename
    | CACHE numericonly
    | CYCLE
    | INCREMENT (BY)? numericonly
    | MAXVALUE numericonly
    | MINVALUE numericonly
    | NO ( MAXVALUE | MINVALUE | CYCLE )
    | OWNED BY any_name
    | SEQUENCE NAME_P any_name
    | START (WITH)? numericonly
    | RESTART (WITH)? numericonly?
    ;

numericonly
    : Numeric
    | PLUS Numeric
    | MINUS Numeric
    | signediconst
    ;

numericonly_list
    : numericonly ( COMMA numericonly )*
    ;

createplangstmt
    : CREATE or_replace_? (TRUSTED)? (PROCEDURAL)? LANGUAGE name (
        HANDLER handler_name inline_handler_? (validator_clause)?
     )?
    ;

handler_name
    : name ( DOT colLabel )+?
    ;

inline_handler_
    : INLINE_P handler_name
    ;

validator_clause
    : VALIDATOR handler_name
    | NO VALIDATOR
    ;

createtablespacestmt
    : CREATE TABLESPACE name (OWNER rolespec)? LOCATION string reloptions_?
    ;

droptablespacestmt
    : DROP TABLESPACE ifExists? name
    ;

createextensionstmt
    : CREATE EXTENSION ifNotExists? name (WITH)? create_extension_opt_item*
    ;
    
ifExists : IF_P EXISTS  ;
ifNotExists : IF_P NOT EXISTS  ;

create_extension_opt_item
    : SCHEMA name
    | VERSION_P nonreservedword_or_sconst
    | FROM nonreservedword_or_sconst
    | CASCADE
    ;

alterextensionstmt
    : ALTER EXTENSION name UPDATE alter_extension_opt_list
    ;

alter_extension_opt_list
    : alter_extension_opt_item*
    ;

alter_extension_opt_item
    : TO nonreservedword_or_sconst
    ;

alterextensioncontentsstmt
    : ALTER EXTENSION name add_drop object_type_name name
    | ALTER EXTENSION name add_drop object_type_any_name any_name
    | ALTER EXTENSION name add_drop AGGREGATE aggregate_with_argtypes
    | ALTER EXTENSION name add_drop CAST OPEN_PAREN typename AS typename CLOSE_PAREN
    | ALTER EXTENSION name add_drop DOMAIN_P typename
    | ALTER EXTENSION name add_drop FUNCTION function_with_argtypes
    | ALTER EXTENSION name add_drop OPERATOR operator_with_argtypes
    | ALTER EXTENSION name add_drop OPERATOR CLASS any_name USING name
    | ALTER EXTENSION name add_drop OPERATOR FAMILY any_name USING name
    | ALTER EXTENSION name add_drop PROCEDURE function_with_argtypes
    | ALTER EXTENSION name add_drop ROUTINE function_with_argtypes
    | ALTER EXTENSION name add_drop TRANSFORM FOR typename LANGUAGE name
    | ALTER EXTENSION name add_drop TYPE_P typename
    ;

createfdwstmt
    : CREATE FOREIGN DATA_P WRAPPER name (fdw_option+)? create_generic_options?
    ;

fdw_option
    : HANDLER handler_name
    | NO HANDLER
    | VALIDATOR handler_name
    | NO VALIDATOR
    ;

alterfdwstmt
    : ALTER FOREIGN DATA_P WRAPPER name (fdw_option+)? alter_generic_options
    | ALTER FOREIGN DATA_P WRAPPER name fdw_option+
    ;

create_generic_options
    : OPTIONS OPEN_PAREN generic_option_list CLOSE_PAREN
    ;

generic_option_list
    : generic_option_elem ( COMMA generic_option_elem )*
    ;

alter_generic_options
    : OPTIONS OPEN_PAREN alter_generic_option_list CLOSE_PAREN
    ;

alter_generic_option_list
    : alter_generic_option_elem ( COMMA alter_generic_option_elem )*
    ;

alter_generic_option_elem
    : generic_option_elem
    | SET generic_option_elem
    | ADD_P generic_option_elem
    | DROP colLabel
    ;

generic_option_elem
    : colLabel string
    ;

createforeignserverstmt
    : CREATE SERVER ifNotExists? name (TYPE_P string)? (foreign_server_version)? FOREIGN DATA_P WRAPPER name create_generic_options?
    ;

foreign_server_version
    : VERSION_P ( string | NULL_P )
    ;

alterforeignserverstmt
    : ALTER SERVER name ( alter_generic_options | foreign_server_version alter_generic_options? )
    ;

createforeigntablestmt
    : CREATE FOREIGN TABLE qname OPEN_PAREN (tableelementlist)? CLOSE_PAREN optinherit? SERVER name create_generic_options?
    | CREATE FOREIGN TABLE ifNotExists qname OPEN_PAREN (tableelementlist)? CLOSE_PAREN optinherit? SERVER name create_generic_options?
    | CREATE FOREIGN TABLE qname PARTITION OF qname opttypedtableelementlist? partitionboundspec SERVER name create_generic_options?
    | CREATE FOREIGN TABLE ifNotExists qname PARTITION OF qname opttypedtableelementlist? partitionboundspec SERVER name
        create_generic_options?
    ;

importforeignschemastmt
    : IMPORT_P FOREIGN SCHEMA name import_qualification? FROM SERVER name INTO name create_generic_options?
    ;

import_qualification
    : ( LIMIT TO | EXCEPT ) OPEN_PAREN relation_expr_list CLOSE_PAREN
    
    ;

createusermappingstmt
    : CREATE USER MAPPING ifNotExists? FOR auth_ident SERVER name create_generic_options?
    ;

auth_ident
    : rolespec
    | USER
    ;

dropusermappingstmt
    : DROP USER MAPPING ifExists? FOR auth_ident SERVER name
    ;

alterusermappingstmt
    : ALTER USER MAPPING FOR auth_ident SERVER name alter_generic_options
    ;

createpolicystmt
    : CREATE POLICY name ON qname rowsecuritydefaultpermissive? rowsecuritydefaultforcmd? (TO role_list)? rowsecurityoptionalexpr?
        rowsecurityoptionalwithcheck?
    ;

alterpolicystmt
    : ALTER POLICY name ON qname (TO role_list)? rowsecurityoptionalexpr? rowsecurityoptionalwithcheck?
    ;

rowsecurityoptionalexpr
    : USING OPEN_PAREN a_expr CLOSE_PAREN
    ;

rowsecurityoptionalwithcheck
    : WITH CHECK OPEN_PAREN a_expr CLOSE_PAREN
    ;

rowsecuritydefaultpermissive
    : AS identifier
    
    ;

rowsecuritydefaultforcmd
    : FOR ( ALL | SELECT | INSERT | UPDATE | DELETE_P )
    ;

createamstmt
    : CREATE ACCESS METHOD name TYPE_P (INDEX
    | TABLE) HANDLER handler_name
    ;

createtrigstmt
    : CREATE            TRIGGER name triggeractiontime triggerevents ON qname (REFERENCING triggertransition+)? (FOR (EACH)? triggerfortype)?                      triggerwhen? EXECUTE function_or_procedure func_name OPEN_PAREN triggerfuncargs? CLOSE_PAREN
    | CREATE CONSTRAINT TRIGGER name AFTER triggerevents             ON qname optconstrfromtable? constraintattributespec FOR EACH ROW triggerwhen? EXECUTE function_or_procedure func_name OPEN_PAREN triggerfuncargs? CLOSE_PAREN
    ;

triggeractiontime
    : BEFORE
    | AFTER
    | INSTEAD OF
    ;

triggerevents
    : triggeroneevent ( OR triggeroneevent )*
    ;

triggeroneevent
    : INSERT
    | DELETE_P
    | UPDATE
    | UPDATE OF columnlist
    | TRUNCATE
    ;

triggertransition
    : (NEW
    | OLD) (TABLE
    | ROW) (AS)? name
    ;

triggerfortype
    : ROW
    | STATEMENT
    ;

triggerwhen
    : WHEN OPEN_PAREN a_expr CLOSE_PAREN
    
    ;

function_or_procedure
    : FUNCTION
    | PROCEDURE
    ;

triggerfuncargs
    : triggerfuncarg ( COMMA triggerfuncarg )*
    ;

triggerfuncarg
    : integer
    | Numeric
    | string
    | colLabel
    ;

optconstrfromtable
    : FROM qname
    ;

constraintattributespec
    : constraintattributeElem*
    ;

constraintattributeElem
    : NOT DEFERRABLE
    | DEFERRABLE
    | INITIALLY IMMEDIATE
    | INITIALLY DEFERRED
    | NOT VALID
    | NO INHERIT
    ;

createeventtrigstmt
    : CREATE EVENT TRIGGER name ON colLabel EXECUTE function_or_procedure func_name OPEN_PAREN CLOSE_PAREN
    | CREATE EVENT TRIGGER name ON colLabel WHEN event_trigger_when_list EXECUTE function_or_procedure func_name OPEN_PAREN CLOSE_PAREN
    ;

event_trigger_when_list
    : event_trigger_when_item ( AND event_trigger_when_item )*
    ;

event_trigger_when_item
    : name IN_P OPEN_PAREN event_trigger_value_list CLOSE_PAREN
    ;

event_trigger_value_list
    : string ( COMMA string )*
    ;

altereventtrigstmt
    : ALTER EVENT TRIGGER name enable_trigger
    ;

enable_trigger
    : ENABLE_P
    | ENABLE_P REPLICA
    | ENABLE_P ALWAYS
    | DISABLE_P
    ;

createassertionstmt
    : CREATE ASSERTION any_name CHECK OPEN_PAREN a_expr CLOSE_PAREN constraintattributespec
    ;

definestmt
    : CREATE or_replace_? AGGREGATE func_name aggr_args definition
    | CREATE or_replace_? AGGREGATE func_name old_aggr_definition
    | CREATE OPERATOR any_operator definition
    | CREATE TYPE_P any_name definition
    | CREATE TYPE_P any_name
    | CREATE TYPE_P any_name AS OPEN_PAREN (tablefuncelementlist)? CLOSE_PAREN
    | CREATE TYPE_P any_name AS ENUM_P OPEN_PAREN enum_val_list_? CLOSE_PAREN
    | CREATE TYPE_P any_name AS RANGE definition
    | CREATE TEXT_P SEARCH PARSER any_name definition
    | CREATE TEXT_P SEARCH DICTIONARY any_name definition
    | CREATE TEXT_P SEARCH TEMPLATE any_name definition
    | CREATE TEXT_P SEARCH CONFIGURATION any_name definition
    | CREATE COLLATION any_name definition
    | CREATE COLLATION ifNotExists any_name definition
    | CREATE COLLATION any_name FROM any_name
    | CREATE COLLATION ifNotExists any_name FROM any_name
    ;

definition
    : OPEN_PAREN def_list CLOSE_PAREN
    ;

def_list
    : def_elem ( COMMA def_elem )*
    ;

def_elem
    : colLabel ( EQUAL def_arg )?
    ;

def_arg
    : func_type
    | reserved_keyword
    | qual_all_op
    | numericonly
    | string
    | NONE
    ;

old_aggr_definition
    : OPEN_PAREN old_aggr_list CLOSE_PAREN
    ;

old_aggr_list
    : old_aggr_elem ( COMMA old_aggr_elem )*
    ;

old_aggr_elem
    : identifier EQUAL def_arg
    ;

enum_val_list_
    : enum_val_list
    
    ;

enum_val_list
    : string ( COMMA string )*
    ;

alterenumstmt
    : ALTER TYPE_P any_name ADD_P VALUE_P (ifNotExists)? string (( BEFORE | AFTER ) string )?
    | ALTER TYPE_P any_name RENAME VALUE_P string TO string
    ;

createopclassstmt
    : CREATE OPERATOR CLASS any_name (DEFAULT)? FOR TYPE_P typename USING name (FAMILY any_name)? AS opclass_item_list
    ;

opclass_item_list
    : opclass_item ( COMMA opclass_item )*
    ;

opclass_item
    : OPERATOR integer any_operator opclass_purpose? (RECHECK)?
    | OPERATOR integer operator_with_argtypes opclass_purpose? (RECHECK)?
    | FUNCTION integer function_with_argtypes
    | FUNCTION integer OPEN_PAREN type_list CLOSE_PAREN function_with_argtypes
    | STORAGE typename
    ;

opclass_purpose
    : FOR ( SEARCH |  ORDER BY any_name )
    ;

createopfamilystmt
    : CREATE OPERATOR FAMILY any_name USING name
    ;

alteropfamilystmt
    : ALTER OPERATOR FAMILY any_name USING name ADD_P opclass_item_list
    | ALTER OPERATOR FAMILY any_name USING name DROP opclass_drop_list
    ;

opclass_drop_list
    : opclass_drop ( COMMA opclass_drop )*
    ;

opclass_drop
    : OPERATOR integer OPEN_PAREN type_list CLOSE_PAREN
    | FUNCTION integer OPEN_PAREN type_list CLOSE_PAREN
    ;

dropopclassstmt
    : DROP OPERATOR CLASS any_name USING name drop_behavior_?
    | DROP OPERATOR CLASS IF_P EXISTS any_name USING name drop_behavior_?
    ;

dropopfamilystmt
    : DROP OPERATOR FAMILY any_name USING name drop_behavior_?
    | DROP OPERATOR FAMILY IF_P EXISTS any_name USING name drop_behavior_?
    ;

dropownedstmt
    : DROP OWNED BY role_list drop_behavior_?
    ;

reassignownedstmt
    : REASSIGN OWNED BY role_list TO rolespec
    ;

dropstmt
    : DROP object_type_any_name IF_P EXISTS any_name_list_ drop_behavior_?
    | DROP object_type_any_name any_name_list_ drop_behavior_?
    | DROP drop_type_name IF_P EXISTS names drop_behavior_?
    | DROP drop_type_name names drop_behavior_?
    | DROP object_type_name_on_any_name name ON any_name drop_behavior_?
    | DROP object_type_name_on_any_name IF_P EXISTS name ON any_name drop_behavior_?
    | DROP TYPE_P type_name_list drop_behavior_?
    | DROP TYPE_P IF_P EXISTS type_name_list drop_behavior_?
    | DROP DOMAIN_P type_name_list drop_behavior_?
    | DROP DOMAIN_P IF_P EXISTS type_name_list drop_behavior_?
    | DROP INDEX CONCURRENTLY any_name_list_ drop_behavior_?
    | DROP INDEX CONCURRENTLY IF_P EXISTS any_name_list_ drop_behavior_?
    ;

object_type_any_name
    : TABLE
    | SEQUENCE
    | VIEW
    | MATERIALIZED VIEW
    | INDEX
    | FOREIGN TABLE
    | COLLATION
    | CONVERSION_P
    | STATISTICS
    | TEXT_P SEARCH PARSER
    | TEXT_P SEARCH DICTIONARY
    | TEXT_P SEARCH TEMPLATE
    | TEXT_P SEARCH CONFIGURATION
    ;

object_type_name
    : drop_type_name
    | DATABASE
    | ROLE
    | SUBSCRIPTION
    | TABLESPACE
    ;

drop_type_name
    : ACCESS METHOD
    | EVENT TRIGGER
    | EXTENSION
    | FOREIGN DATA_P WRAPPER
    | (PROCEDURAL)? LANGUAGE
    | PUBLICATION
    | SCHEMA
    | SERVER
    ;

object_type_name_on_any_name
    : POLICY
    | RULE
    | TRIGGER
    ;

any_name_list_
    : any_name ( COMMA any_name )*
    ;

any_name
    : name ( DOT colLabel )*
    ;

type_name_list
    : typename ( COMMA typename )*
    ;

truncatestmt
    : TRUNCATE (TABLE)? relation_expr_list (( CONTINUE_P | RESTART ) IDENTITY_P )? drop_behavior_?
    ;

commentstmt
    : COMMENT ON object_type_any_name any_name IS comment_text
    | COMMENT ON COLUMN any_name IS comment_text
    | COMMENT ON object_type_name name IS comment_text
    | COMMENT ON TYPE_P typename IS comment_text
    | COMMENT ON DOMAIN_P typename IS comment_text
    | COMMENT ON AGGREGATE aggregate_with_argtypes IS comment_text
    | COMMENT ON FUNCTION function_with_argtypes IS comment_text
    | COMMENT ON OPERATOR operator_with_argtypes IS comment_text
    | COMMENT ON CONSTRAINT name ON any_name IS comment_text
    | COMMENT ON CONSTRAINT name ON DOMAIN_P any_name IS comment_text
    | COMMENT ON object_type_name_on_any_name name ON any_name IS comment_text
    | COMMENT ON PROCEDURE function_with_argtypes IS comment_text
    | COMMENT ON ROUTINE function_with_argtypes IS comment_text
    | COMMENT ON TRANSFORM FOR typename LANGUAGE name IS comment_text
    | COMMENT ON OPERATOR CLASS any_name USING name IS comment_text
    | COMMENT ON OPERATOR FAMILY any_name USING name IS comment_text
    | COMMENT ON LARGE_P OBJECT_P numericonly IS comment_text
    | COMMENT ON CAST OPEN_PAREN typename AS typename CLOSE_PAREN IS comment_text
    ;

comment_text
    : string
    | NULL_P
    ;

seclabelstmt
    : SECURITY LABEL provider_? ON object_type_any_name any_name IS security_label
    | SECURITY LABEL provider_? ON COLUMN any_name IS security_label
    | SECURITY LABEL provider_? ON object_type_name name IS security_label
    | SECURITY LABEL provider_? ON TYPE_P typename IS security_label
    | SECURITY LABEL provider_? ON DOMAIN_P typename IS security_label
    | SECURITY LABEL provider_? ON AGGREGATE aggregate_with_argtypes IS security_label
    | SECURITY LABEL provider_? ON FUNCTION function_with_argtypes IS security_label
    | SECURITY LABEL provider_? ON LARGE_P OBJECT_P numericonly IS security_label
    | SECURITY LABEL provider_? ON PROCEDURE function_with_argtypes IS security_label
    | SECURITY LABEL provider_? ON ROUTINE function_with_argtypes IS security_label
    ;

provider_
    : FOR nonreservedword_or_sconst
    
    ;

security_label
    : string
    | NULL_P
    ;

fetchstmt
    : FETCH fetch_args
    | MOVE fetch_args
    ;

fetch_args
    : name
    | from_in name
    | NEXT from_in_? name
    | PRIOR from_in_? name
    | FIRST_P from_in_? name
    | LAST_P from_in_? name
    | ABSOLUTE_P signediconst from_in_? name
    | RELATIVE_P signediconst from_in_? name
    | signediconst from_in_? name
    | ALL from_in_? name
    | FORWARD from_in_? name
    | FORWARD signediconst from_in_? name
    | FORWARD ALL from_in_? name
    | BACKWARD from_in_? name
    | BACKWARD signediconst from_in_? name
    | BACKWARD ALL from_in_? name
    ;

from_in
    : FROM
    | IN_P
    ;

from_in_
    : from_in
    
    ;

grantstmt
    : GRANT privileges ON privilege_target TO grantee_list grant_grant_option_?
    ;

revokestmt
    : REVOKE privileges ON privilege_target FROM grantee_list drop_behavior_?
    | REVOKE GRANT OPTION FOR privileges ON privilege_target FROM grantee_list drop_behavior_?
    ;

privileges
    : privilege_list
    | ALL
    | ALL PRIVILEGES
    | ALL OPEN_PAREN columnlist CLOSE_PAREN
    | ALL PRIVILEGES OPEN_PAREN columnlist CLOSE_PAREN
    ;

privilege_list
    : privilege ( COMMA privilege )*
    ;

privilege
    : SELECT column_list_?
    | REFERENCES column_list_?
    | CREATE column_list_?
    | name column_list_?
    ;

privilege_target
    : qnames
    | TABLE qnames
    | SEQUENCE qnames
    | FOREIGN DATA_P WRAPPER names
    | FOREIGN SERVER names
    | FUNCTION function_with_argtypes_list
    | PROCEDURE function_with_argtypes_list
    | ROUTINE function_with_argtypes_list
    | DATABASE names
    | DOMAIN_P any_name_list_
    | LANGUAGE names
    | LARGE_P OBJECT_P numericonly_list
    | SCHEMA names
    | TABLESPACE names
    | TYPE_P any_name_list_
    | ALL TABLES IN_P SCHEMA names
    | ALL SEQUENCES IN_P SCHEMA names
    | ALL FUNCTIONS IN_P SCHEMA names
    | ALL PROCEDURES IN_P SCHEMA names
    | ALL ROUTINES IN_P SCHEMA names
    ;

grantee_list
    : grantee ( COMMA grantee )*
    ;

grantee
    : rolespec
    | GROUP_P rolespec
    ;

grant_grant_option_
    : WITH GRANT OPTION
    
    ;

grantrolestmt
    : GRANT privilege_list TO role_list grant_admin_option_? granted_by_?
    ;

revokerolestmt
    : REVOKE privilege_list FROM role_list granted_by_? drop_behavior_?
    | REVOKE ADMIN OPTION FOR privilege_list FROM role_list granted_by_? drop_behavior_?
    ;

grant_admin_option_
    : WITH ADMIN OPTION
    
    ;

granted_by_
    : GRANTED BY rolespec
    
    ;

alterdefaultprivilegesstmt
    : ALTER DEFAULT PRIVILEGES defacloptionlist defaclaction
    ;

defacloptionlist
    : defacloption*
    ;

defacloption
    : IN_P SCHEMA names
    | FOR ROLE role_list
    | FOR USER role_list
    ;

defaclaction
    : GRANT privileges ON defacl_privilege_target TO grantee_list grant_grant_option_?
    | REVOKE privileges ON defacl_privilege_target FROM grantee_list drop_behavior_?
    | REVOKE GRANT OPTION FOR privileges ON defacl_privilege_target FROM grantee_list drop_behavior_?
    ;

defacl_privilege_target
    : TABLES
    | FUNCTIONS
    | ROUTINES
    | SEQUENCES
    | TYPES_P
    | SCHEMAS
    ;

//create index

indexstmt
    : CREATE unique_? INDEX concurrently_? index_name_? ON relation_expr access_method_clause? OPEN_PAREN index_params CLOSE_PAREN include_?
        reloptions_? opttablespace? where?
    | CREATE unique_? INDEX concurrently_? ifNotExists name ON relation_expr access_method_clause? OPEN_PAREN index_params CLOSE_PAREN
        include_? reloptions_? opttablespace? where?
    ;

unique_
    : UNIQUE
    
    ;

single_name_
    : name
    ;

concurrently_
    : CONCURRENTLY
    
    ;

index_name_
    : name
    
    ;

access_method_clause
    : USING name
    
    ;

index_params
    : index_elem ( COMMA index_elem )*
    ;

index_elem_options
    : collate_? class_? asc_desc_? nulls_order_?
    | collate_? any_name reloptions asc_desc_? nulls_order_?
    ;

index_elem
    : name index_elem_options
    | func_expr_windowless index_elem_options
    | OPEN_PAREN a_expr CLOSE_PAREN index_elem_options
    ;

include_
    : INCLUDE OPEN_PAREN index_including_params CLOSE_PAREN
    
    ;

index_including_params
    : index_elem ( COMMA index_elem )*
    ;

collate_
    : COLLATE any_name
    
    ;

class_
    : any_name
    
    ;

asc_desc_
    : ASC
    | DESC
    
    ;

//TOD NULLS_LA was used

nulls_order_
    : NULLS_P FIRST_P
    | NULLS_P LAST_P
    
    ;

createfunctionstmt
    : CREATE or_replace_? ( FUNCTION | PROCEDURE ) func_name func_args_with_defaults (
        RETURNS ( func_type | TABLE OPEN_PAREN table_func_column_list CLOSE_PAREN )
     )? createfunc_opt_list
    ;

or_replace_
    : OR REPLACE
    
    ;

func_args
    : OPEN_PAREN func_args_list? CLOSE_PAREN
    ;

func_args_list
    : func_arg ( COMMA func_arg )*
    ;

function_with_argtypes_list
    : function_with_argtypes ( COMMA function_with_argtypes )*
    ;

function_with_argtypes
    : func_name func_args
    | type_func_name_keyword
    | name (indirection_el+)?
    ;

func_args_with_defaults
    : OPEN_PAREN func_args_with_defaults_list? CLOSE_PAREN
    ;

func_args_with_defaults_list
    : func_arg_with_default ( COMMA func_arg_with_default )*
    ;

func_arg
    : arg_class (type_function_name)? func_type
    | type_function_name arg_class? func_type
    | func_type
    ;

arg_class
    : IN_P OUT_P?
    | OUT_P
    | INOUT
    | VARIADIC
    ;

func_type
    : typename
    | SETOF? type_function_name ( DOT colLabel )+ PERCENT TYPE_P
    ;

func_arg_with_default
    : func_arg ( ( DEFAULT | EQUAL ) a_expr )?
    ;

aggr_args
    : OPEN_PAREN ( 
        STAR
        | aggr_args_list
        | ORDER BY aggr_args_list
        | aggr_args_list ORDER BY aggr_args_list
    ) CLOSE_PAREN
    ;

aggr_args_list
    : func_arg ( COMMA func_arg )*
    ;

aggregate_with_argtypes
    : func_name aggr_args
    ;

aggregate_with_argtypes_list
    : aggregate_with_argtypes ( COMMA aggregate_with_argtypes )*
    ;

createfunc_opt_list
    : createfunc_opt_item+
    //                    | createfunc_opt_list createfunc_opt_item
    ;

common_func_opt_item
    : CALLED ON NULL_P INPUT_P
    | RETURNS NULL_P ON NULL_P INPUT_P
    | STRICT_P
    | IMMUTABLE
    | STABLE
    | VOLATILE
    | EXTERNAL SECURITY DEFINER
    | EXTERNAL SECURITY INVOKER
    | SECURITY DEFINER
    | SECURITY INVOKER
    | LEAKPROOF
    | NOT LEAKPROOF
    | COST numericonly
    | ROWS numericonly
    | SUPPORT any_name
    | functionsetresetclause
    | PARALLEL name
    ;

createfunc_opt_item
    : AS func_as
    | LANGUAGE nonreservedword_or_sconst
    | TRANSFORM transform_type_list
    | WINDOW
    | common_func_opt_item
    ;

//https://www.postgresql.org/docs/9.1/sql-createfunction.html

//    | AS 'definition'

//    | AS 'obj_file', 'link_symbol'

func_as
    :
    /* |AS 'definition'*/ def = string
    /*| AS 'obj_file', 'link_symbol'*/
    | string COMMA string
    ;

transform_type_list
    : FOR TYPE_P typename ( COMMA FOR TYPE_P typename )*
    ;

definition_
    : WITH definition
    
    ;

table_func_column
    : type_function_name func_type
    ;

table_func_column_list
    : table_func_column ( COMMA table_func_column )*
    ;

alterfunctionstmt
    : ALTER ( FUNCTION | PROCEDURE | ROUTINE ) function_with_argtypes alterfunc_opt_list restrict_?
    ;

alterfunc_opt_list
    : common_func_opt_item+
    ;

restrict_
    : RESTRICT
    
    ;

removefuncstmt
    : DROP FUNCTION function_with_argtypes_list drop_behavior_?
    | DROP FUNCTION IF_P EXISTS function_with_argtypes_list drop_behavior_?
    | DROP PROCEDURE function_with_argtypes_list drop_behavior_?
    | DROP PROCEDURE IF_P EXISTS function_with_argtypes_list drop_behavior_?
    | DROP ROUTINE function_with_argtypes_list drop_behavior_?
    | DROP ROUTINE IF_P EXISTS function_with_argtypes_list drop_behavior_?
    ;

removeaggrstmt
    : DROP AGGREGATE aggregate_with_argtypes_list drop_behavior_?
    | DROP AGGREGATE IF_P EXISTS aggregate_with_argtypes_list drop_behavior_?
    ;

removeoperstmt
    : DROP OPERATOR operator_with_argtypes_list drop_behavior_?
    | DROP OPERATOR IF_P EXISTS operator_with_argtypes_list drop_behavior_?
    ;

oper_argtypes
    : OPEN_PAREN typename CLOSE_PAREN
    | OPEN_PAREN typename COMMA typename CLOSE_PAREN
    | OPEN_PAREN NONE COMMA typename CLOSE_PAREN
    | OPEN_PAREN typename COMMA NONE CLOSE_PAREN
    ;

any_operator
    : ( name DOT )* all_op
    ;

operator_with_argtypes_list
    : operator_with_argtypes ( COMMA operator_with_argtypes )*
    ;

operator_with_argtypes
    : any_operator oper_argtypes
    ;

dostmt
    : DO dostmt_opt_list
    ;

dostmt_opt_list
    : dostmt_opt_item+
    ;

dostmt_opt_item
    : string
    | LANGUAGE nonreservedword_or_sconst
    ;

createcaststmt
    : CREATE CAST OPEN_PAREN typename AS typename CLOSE_PAREN WITH FUNCTION function_with_argtypes cast_context?
    | CREATE CAST OPEN_PAREN typename AS typename CLOSE_PAREN WITHOUT FUNCTION cast_context?
    | CREATE CAST OPEN_PAREN typename AS typename CLOSE_PAREN WITH INOUT cast_context?
    ;

cast_context
    : AS IMPLICIT_P
    | AS ASSIGNMENT
    
    ;

dropcaststmt
    : DROP CAST if_exists_? OPEN_PAREN typename AS typename CLOSE_PAREN drop_behavior_?
    ;

if_exists_
    : IF_P EXISTS
    
    ;

createtransformstmt
    : CREATE or_replace_? TRANSFORM FOR typename LANGUAGE name OPEN_PAREN transform_element_list CLOSE_PAREN
    ;

transform_element_list
    : FROM SQL_P WITH FUNCTION function_with_argtypes COMMA TO SQL_P WITH FUNCTION function_with_argtypes
    | TO SQL_P WITH FUNCTION function_with_argtypes COMMA FROM SQL_P WITH FUNCTION function_with_argtypes
    | FROM SQL_P WITH FUNCTION function_with_argtypes
    | TO SQL_P WITH FUNCTION function_with_argtypes
    ;

droptransformstmt
    : DROP TRANSFORM if_exists_? FOR typename LANGUAGE name drop_behavior_?
    ;

reindexstmt
    : REINDEX reindex_option_list? reindex_target_relation concurrently_? qname
    | REINDEX reindex_option_list? SCHEMA concurrently_? name
    | REINDEX reindex_option_list? reindex_target_all concurrently_? single_name_?
    ;

reindex_target_relation
    : INDEX
    | TABLE
    ;

reindex_target_all
    : SYSTEM_P
    | DATABASE
    ;

reindex_option_list
    : OPEN_PAREN utility_option_list CLOSE_PAREN
    ;

altertblspcstmt
    : ALTER TABLESPACE name SET reloptions
    | ALTER TABLESPACE name RESET reloptions
    ;

renamestmt
    : ALTER AGGREGATE aggregate_with_argtypes RENAME TO name
    | ALTER COLLATION any_name RENAME TO name
    | ALTER CONVERSION_P any_name RENAME TO name
    | ALTER DATABASE name RENAME TO name
    | ALTER DOMAIN_P any_name RENAME TO name
    | ALTER DOMAIN_P any_name RENAME CONSTRAINT name TO name
    | ALTER FOREIGN DATA_P WRAPPER name RENAME TO name
    | ALTER FUNCTION function_with_argtypes RENAME TO name
    | ALTER GROUP_P rolespec RENAME TO rolespec
    | ALTER (PROCEDURAL)? LANGUAGE name RENAME TO name
    | ALTER OPERATOR CLASS any_name USING name RENAME TO name
    | ALTER OPERATOR FAMILY any_name USING name RENAME TO name
    | ALTER POLICY name ON qname RENAME TO name
    | ALTER POLICY IF_P EXISTS name ON qname RENAME TO name
    | ALTER PROCEDURE function_with_argtypes RENAME TO name
    | ALTER PUBLICATION name RENAME TO name
    | ALTER ROUTINE function_with_argtypes RENAME TO name
    | ALTER SCHEMA name RENAME TO name
    | ALTER SERVER name RENAME TO name
    | ALTER SUBSCRIPTION name RENAME TO name
    | ALTER TABLE relation_expr RENAME TO name
    | ALTER TABLE IF_P EXISTS relation_expr RENAME TO name
    | ALTER SEQUENCE qname RENAME TO name
    | ALTER SEQUENCE IF_P EXISTS qname RENAME TO name
    | ALTER VIEW qname RENAME TO name
    | ALTER VIEW IF_P EXISTS qname RENAME TO name
    | ALTER MATERIALIZED VIEW qname RENAME TO name
    | ALTER MATERIALIZED VIEW IF_P EXISTS qname RENAME TO name
    | ALTER INDEX qname RENAME TO name
    | ALTER INDEX IF_P EXISTS qname RENAME TO name
    | ALTER FOREIGN TABLE relation_expr RENAME TO name
    | ALTER FOREIGN TABLE IF_P EXISTS relation_expr RENAME TO name
    | ALTER TABLE relation_expr RENAME column_? name TO name
    | ALTER TABLE IF_P EXISTS relation_expr RENAME column_? name TO name
    | ALTER VIEW qname RENAME column_? name TO name
    | ALTER VIEW IF_P EXISTS qname RENAME column_? name TO name
    | ALTER MATERIALIZED VIEW qname RENAME column_? name TO name
    | ALTER MATERIALIZED VIEW IF_P EXISTS qname RENAME column_? name TO name
    | ALTER TABLE relation_expr RENAME CONSTRAINT name TO name
    | ALTER TABLE IF_P EXISTS relation_expr RENAME CONSTRAINT name TO name
    | ALTER FOREIGN TABLE relation_expr RENAME column_? name TO name
    | ALTER FOREIGN TABLE IF_P EXISTS relation_expr RENAME column_? name TO name
    | ALTER RULE name ON qname RENAME TO name
    | ALTER TRIGGER name ON qname RENAME TO name
    | ALTER EVENT TRIGGER name RENAME TO name
    | ALTER ROLE rolespec RENAME TO rolespec
    | ALTER USER rolespec RENAME TO rolespec
    | ALTER TABLESPACE name RENAME TO name
    | ALTER STATISTICS any_name RENAME TO name
    | ALTER TEXT_P SEARCH PARSER any_name RENAME TO name
    | ALTER TEXT_P SEARCH DICTIONARY any_name RENAME TO name
    | ALTER TEXT_P SEARCH TEMPLATE any_name RENAME TO name
    | ALTER TEXT_P SEARCH CONFIGURATION any_name RENAME TO name
    | ALTER TYPE_P any_name RENAME TO name
    | ALTER TYPE_P any_name RENAME ATTRIBUTE name TO name drop_behavior_?
    ;

column_
    : COLUMN
    
    ;

set_data_
    : SET DATA_P
    
    ;

alterobjectdependsstmt
    : ALTER FUNCTION function_with_argtypes no_? DEPENDS ON EXTENSION name
    | ALTER PROCEDURE function_with_argtypes no_? DEPENDS ON EXTENSION name
    | ALTER ROUTINE function_with_argtypes no_? DEPENDS ON EXTENSION name
    | ALTER TRIGGER name ON qname no_? DEPENDS ON EXTENSION name
    | ALTER MATERIALIZED VIEW qname no_? DEPENDS ON EXTENSION name
    | ALTER INDEX qname no_? DEPENDS ON EXTENSION name
    ;

no_
    : NO
    
    ;

alterobjectschemastmt
    : ALTER AGGREGATE aggregate_with_argtypes SET SCHEMA name
    | ALTER COLLATION any_name SET SCHEMA name
    | ALTER CONVERSION_P any_name SET SCHEMA name
    | ALTER DOMAIN_P any_name SET SCHEMA name
    | ALTER EXTENSION name SET SCHEMA name
    | ALTER FUNCTION function_with_argtypes SET SCHEMA name
    | ALTER OPERATOR operator_with_argtypes SET SCHEMA name
    | ALTER OPERATOR CLASS any_name USING name SET SCHEMA name
    | ALTER OPERATOR FAMILY any_name USING name SET SCHEMA name
    | ALTER PROCEDURE function_with_argtypes SET SCHEMA name
    | ALTER ROUTINE function_with_argtypes SET SCHEMA name
    | ALTER TABLE relation_expr SET SCHEMA name
    | ALTER TABLE IF_P EXISTS relation_expr SET SCHEMA name
    | ALTER STATISTICS any_name SET SCHEMA name
    | ALTER TEXT_P SEARCH PARSER any_name SET SCHEMA name
    | ALTER TEXT_P SEARCH DICTIONARY any_name SET SCHEMA name
    | ALTER TEXT_P SEARCH TEMPLATE any_name SET SCHEMA name
    | ALTER TEXT_P SEARCH CONFIGURATION any_name SET SCHEMA name
    | ALTER SEQUENCE qname SET SCHEMA name
    | ALTER SEQUENCE IF_P EXISTS qname SET SCHEMA name
    | ALTER VIEW qname SET SCHEMA name
    | ALTER VIEW IF_P EXISTS qname SET SCHEMA name
    | ALTER MATERIALIZED VIEW qname SET SCHEMA name
    | ALTER MATERIALIZED VIEW IF_P EXISTS qname SET SCHEMA name
    | ALTER FOREIGN TABLE relation_expr SET SCHEMA name
    | ALTER FOREIGN TABLE IF_P EXISTS relation_expr SET SCHEMA name
    | ALTER TYPE_P any_name SET SCHEMA name
    ;

alteroperatorstmt
    : ALTER OPERATOR operator_with_argtypes SET OPEN_PAREN operator_def_list CLOSE_PAREN
    ;

operator_def_list
    : operator_def_elem ( COMMA operator_def_elem )*
    ;

operator_def_elem
    : colLabel EQUAL NONE
    | colLabel EQUAL operator_def_arg
    ;

operator_def_arg
    : func_type
    | reserved_keyword
    | qual_all_op
    | numericonly
    | string
    ;

altertypestmt
    : ALTER TYPE_P any_name SET OPEN_PAREN operator_def_list CLOSE_PAREN
    ;

alterownerstmt
    : ALTER AGGREGATE aggregate_with_argtypes OWNER TO rolespec
    | ALTER COLLATION any_name OWNER TO rolespec
    | ALTER CONVERSION_P any_name OWNER TO rolespec
    | ALTER DATABASE name OWNER TO rolespec
    | ALTER DOMAIN_P any_name OWNER TO rolespec
    | ALTER FUNCTION function_with_argtypes OWNER TO rolespec
    | ALTER (PROCEDURAL)? LANGUAGE name OWNER TO rolespec
    | ALTER LARGE_P OBJECT_P numericonly OWNER TO rolespec
    | ALTER OPERATOR operator_with_argtypes OWNER TO rolespec
    | ALTER OPERATOR CLASS any_name USING name OWNER TO rolespec
    | ALTER OPERATOR FAMILY any_name USING name OWNER TO rolespec
    | ALTER PROCEDURE function_with_argtypes OWNER TO rolespec
    | ALTER ROUTINE function_with_argtypes OWNER TO rolespec
    | ALTER SCHEMA name OWNER TO rolespec
    | ALTER TYPE_P any_name OWNER TO rolespec
    | ALTER TABLESPACE name OWNER TO rolespec
    | ALTER STATISTICS any_name OWNER TO rolespec
    | ALTER TEXT_P SEARCH DICTIONARY any_name OWNER TO rolespec
    | ALTER TEXT_P SEARCH CONFIGURATION any_name OWNER TO rolespec
    | ALTER FOREIGN DATA_P WRAPPER name OWNER TO rolespec
    | ALTER SERVER name OWNER TO rolespec
    | ALTER EVENT TRIGGER name OWNER TO rolespec
    | ALTER PUBLICATION name OWNER TO rolespec
    | ALTER SUBSCRIPTION name OWNER TO rolespec
    ;

createpublicationstmt
    : CREATE PUBLICATION name publication_for_tables_? definition_?
    ;

publication_for_tables_
    : publication_for_tables
    
    ;

publication_for_tables
    : FOR TABLE relation_expr_list
    | FOR ALL TABLES
    ;

alterpublicationstmt
    : ALTER PUBLICATION name SET definition
    | ALTER PUBLICATION name ADD_P TABLE relation_expr_list
    | ALTER PUBLICATION name SET TABLE relation_expr_list
    | ALTER PUBLICATION name DROP TABLE relation_expr_list
    ;

createsubscriptionstmt
    : CREATE SUBSCRIPTION name CONNECTION string PUBLICATION publication_name_list definition_?
    ;

publication_name_list
    : publication_name_item ( COMMA publication_name_item )*
    ;

publication_name_item
    : colLabel
    ;

altersubscriptionstmt
    : ALTER SUBSCRIPTION name SET definition
    | ALTER SUBSCRIPTION name CONNECTION string
    | ALTER SUBSCRIPTION name REFRESH PUBLICATION definition_?
    | ALTER SUBSCRIPTION name SET PUBLICATION publication_name_list definition_?
    | ALTER SUBSCRIPTION name ENABLE_P
    | ALTER SUBSCRIPTION name DISABLE_P
    ;

dropsubscriptionstmt
    : DROP SUBSCRIPTION name drop_behavior_?
    | DROP SUBSCRIPTION IF_P EXISTS name drop_behavior_?
    ;

rulestmt
    : CREATE or_replace_? RULE name AS ON event TO qname where? DO instead_? ruleactionlist
    ;

ruleactionlist
    : NOTHING
    | ruleactionstmt
    | OPEN_PAREN ruleactionmulti CLOSE_PAREN
    ;

ruleactionmulti
    : ruleactionstmtOrEmpty? ( SEMI ruleactionstmtOrEmpty? )*
    ;

ruleactionstmt
    : selectstmt
    | insertstmt
    | updatestmt
    | deletestmt
    | notifystmt
    ;

ruleactionstmtOrEmpty
    : ruleactionstmt
    
    ;

event
    : SELECT
    | UPDATE
    | DELETE_P
    | INSERT
    ;

instead_
    : INSTEAD
    | ALSO
    
    ;

notifystmt
    : NOTIFY name notify_payload?
    ;

notify_payload
    : COMMA string
    
    ;

listenstmt
    : LISTEN name
    ;

unlistenstmt
    : UNLISTEN name
    | UNLISTEN STAR
    ;

transactionstmt
    : ABORT_P transaction_? transaction_chain_?
    | BEGIN_P transaction_? transaction_mode_list_or_empty?
    | START TRANSACTION transaction_mode_list_or_empty?
    | COMMIT transaction_? transaction_chain_?
    | END_P transaction_? transaction_chain_?
    | ROLLBACK transaction_? transaction_chain_?
    | SAVEPOINT name
    | RELEASE SAVEPOINT name
    | RELEASE name
    | ROLLBACK transaction_? TO SAVEPOINT name
    | ROLLBACK transaction_? TO name
    | PREPARE TRANSACTION string
    | COMMIT PREPARED string
    | ROLLBACK PREPARED string
    ;

transaction_
    : WORK
    | TRANSACTION
    
    ;

transaction_mode_item
    : ISOLATION LEVEL iso_level
    | READ ONLY
    | READ WRITE
    | DEFERRABLE
    | NOT DEFERRABLE
    ;

transaction_mode_list
    : transaction_mode_item ( COMMA? transaction_mode_item )*
    ;

transaction_mode_list_or_empty
    : transaction_mode_list
    
    ;

transaction_chain_
    : AND NO? CHAIN
    
    ;

viewstmt
    : CREATE ( OR REPLACE )? opttemp? ( 
        VIEW qname column_list_? reloptions_?
        | RECURSIVE VIEW qname OPEN_PAREN columnlist CLOSE_PAREN reloptions_?
    ) AS selectstmt check_option_?
    ;

check_option_
    : WITH ( CASCADED | LOCAL )? CHECK OPTION
    
    ;

loadstmt
    : LOAD string
    ;

createdbstmt
    : CREATE DATABASE name (WITH)? createdb_opt_list?
    ;

createdb_opt_list
    : createdb_opt_items
    
    ;

createdb_opt_items
    : createdb_opt_item+
    ;

createdb_opt_item
    : createdb_opt_name equal_? ( signediconst | boolean_or_string_ | DEFAULT )
    ;

createdb_opt_name
    : identifier
    | CONNECTION LIMIT
    | ENCODING
    | LOCATION
    | OWNER
    | TABLESPACE
    | TEMPLATE
    ;

equal_
    : EQUAL
    
    ;

alterdatabasestmt
    : ALTER DATABASE name ( WITH createdb_opt_list? | createdb_opt_list? | SET TABLESPACE name )
    ;

alterdatabasesetstmt
    : ALTER DATABASE name setresetclause
    ;

dropdbstmt
    : DROP DATABASE ifExists? name ( (WITH)? OPEN_PAREN drop_option_list CLOSE_PAREN )?
    ;

drop_option_list
    : drop_option ( COMMA drop_option )*
    ;

drop_option
    : FORCE
    ;

altercollationstmt
    : ALTER COLLATION any_name REFRESH VERSION_P
    ;

altersystemstmt
    : ALTER SYSTEM_P ( SET | RESET ) generic_set
    ;

createdomainstmt
    : CREATE DOMAIN_P any_name (AS)? typename colconstraint*
    ;

alterdomainstmt
    : ALTER DOMAIN_P any_name ( 
        alter_column_default
        | DROP NOT NULL_P
        | SET NOT NULL_P
        | ADD_P tableconstraint
        | DROP CONSTRAINT ifExists? name drop_behavior_?
        | VALIDATE CONSTRAINT name
    )
    ;

altertsdictionarystmt
    : ALTER TEXT_P SEARCH DICTIONARY any_name definition
    ;

altertsconfigurationstmt
    : ALTER TEXT_P SEARCH CONFIGURATION any_name ADD_P MAPPING FOR names WITH any_name_list_
    | ALTER TEXT_P SEARCH CONFIGURATION any_name ALTER MAPPING FOR names WITH any_name_list_
    | ALTER TEXT_P SEARCH CONFIGURATION any_name ALTER MAPPING REPLACE any_name WITH any_name
    | ALTER TEXT_P SEARCH CONFIGURATION any_name ALTER MAPPING FOR names REPLACE any_name WITH any_name
    | ALTER TEXT_P SEARCH CONFIGURATION any_name DROP MAPPING FOR names
    | ALTER TEXT_P SEARCH CONFIGURATION any_name DROP MAPPING IF_P EXISTS FOR names
    ;

createconversionstmt
    : CREATE (DEFAULT)? CONVERSION_P any_name FOR string TO string FROM any_name
    ;

clusterstmt
    : CLUSTER (VERBOSE)? qname (USING name)?
    | CLUSTER (VERBOSE)?
    | CLUSTER (VERBOSE)? name ON qname
    ;

vacuumstmt
    : VACUUM (FULL)? (FREEZE)? (VERBOSE)? (analyze_keyword)? (vacuum_relation_list)?
    | VACUUM OPEN_PAREN vac_analyze_option_list CLOSE_PAREN (vacuum_relation_list)?
    ;

analyzestmt
    : analyze_keyword (VERBOSE)? (vacuum_relation_list)?
    | analyze_keyword OPEN_PAREN vac_analyze_option_list CLOSE_PAREN (vacuum_relation_list)?
    ;

utility_option_list
    : utility_option_elem (  ',' utility_option_elem )*
    ;

vac_analyze_option_list
    : vac_analyze_option_elem ( COMMA vac_analyze_option_elem )*
    ;

analyze_keyword
    : ANALYZE
    | ANALYSE
    ;

utility_option_elem
    : utility_option_name utility_option_arg?
    ;

utility_option_name
    : nonreservedword
    | analyze_keyword
    | FORMAT_LA
    ;

utility_option_arg
    : boolean_or_string_
    | numericonly
    ;

vac_analyze_option_elem
    : vac_analyze_option_name vac_analyze_option_arg?
    ;

vac_analyze_option_name
    : nonreservedword
    | analyze_keyword
    ;

vac_analyze_option_arg
    : boolean_or_string_
    | numericonly
    ;

name_list_
    : OPEN_PAREN names CLOSE_PAREN
    ;

vacuum_relation
    : qname name_list_?
    ;

vacuum_relation_list
    : vacuum_relation ( COMMA vacuum_relation )*
    ;

explainstmt
    : EXPLAIN explainablestmt
    | EXPLAIN analyze_keyword (VERBOSE)? explainablestmt
    | EXPLAIN VERBOSE explainablestmt
    | EXPLAIN OPEN_PAREN explain_option_list CLOSE_PAREN explainablestmt
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

explain_option_list
    : explain_option_elem ( COMMA explain_option_elem )*
    ;

explain_option_elem
    : explain_option_name explain_option_arg?
    ;

explain_option_name
    : nonreservedword
    | analyze_keyword
    ;

explain_option_arg
    : boolean_or_string_
    | numericonly
    ;

preparestmt
    : PREPARE name prep_type_clause? AS preparablestmt
    ;

prep_type_clause
    : OPEN_PAREN type_list CLOSE_PAREN
    ;

preparablestmt
    : selectstmt
    | insertstmt
    | updatestmt
    | deletestmt
    ;

executestmt
    : EXECUTE name execute_param_clause?
    | CREATE opttemp? TABLE create_as_target AS EXECUTE name execute_param_clause? with_data_?
    | CREATE opttemp? TABLE ifNotExists create_as_target AS EXECUTE name execute_param_clause? with_data_?
    ;

execute_param_clause
    : OPEN_PAREN expr_list CLOSE_PAREN
    
    ;

deallocatestmt
    : DEALLOCATE PREPARE? ( name |  ALL )
    ;

insertstmt
    : (with_clause)? INSERT INTO qname ( AS name )? insert_rest on_conflict_? returning_clause?
    ;

insert_rest
    : selectstmt
    | OVERRIDING override_kind VALUE_P selectstmt
    | OPEN_PAREN insert_column_list CLOSE_PAREN ( OVERRIDING override_kind VALUE_P )? selectstmt
    | DEFAULT VALUES
    ;

override_kind
    : USER
    | SYSTEM_P
    ;

insert_column_list
    : insert_column_item ( COMMA insert_column_item )*
    ;

insert_column_item
    : name indirection_el*
    ;

on_conflict_
    : ON CONFLICT conf_expr_? DO ( UPDATE SET set_clause_list where? | NOTHING )
    ;

conf_expr_
    : OPEN_PAREN index_params CLOSE_PAREN where?
    | ON CONSTRAINT name
    ;

returning_clause
    : RETURNING target_list
    ;

// https://www.postgresql.org/docs/current/sql-merge.html
mergestmt
    : MERGE INTO? qname alias_clause? USING ( select_with_parens | qname ) alias_clause? ON a_expr
    ( merge_insert_clause merge_update_clause?
    | merge_update_clause merge_insert_clause?
    ) merge_delete_clause?
    ;

merge_insert_clause
    : WHEN NOT MATCHED ( AND a_expr )? THEN? INSERT ( OPEN_PAREN insert_column_list CLOSE_PAREN )? values
    ;

merge_update_clause
    : WHEN MATCHED ( AND a_expr )? THEN? UPDATE SET set_clause_list
    ;

merge_delete_clause
    : WHEN MATCHED THEN? DELETE_P
    ;

deletestmt
    : (with_clause)? DELETE_P FROM relation_expr_opt_alias (USING from_list)? where_or_current_clause? returning_clause?
    ;

lockstmt
    : LOCK_P (TABLE)? relation_expr_list (IN_P lock_type MODE)? (NOWAIT)?
    ;

lock_type
    : ACCESS ( SHARE | EXCLUSIVE )
    | ROW ( SHARE | EXCLUSIVE )
    | SHARE ( UPDATE EXCLUSIVE | ROW EXCLUSIVE )?
    | EXCLUSIVE
    ;

nowait_or_skip_
    : NOWAIT
    | SKIP_P LOCKED
    ;

updatestmt
    : (with_clause)? UPDATE relation_expr_opt_alias SET set_clause_list from? where_or_current_clause? returning_clause?
    ;

set_clause_list
    : set_clause ( COMMA set_clause )*
    ;

set_clause
    : set_target EQUAL a_expr
    | OPEN_PAREN set_target_list CLOSE_PAREN EQUAL a_expr
    ;

set_target
    : name indirection_el*
    ;

set_target_list
    : set_target ( COMMA set_target )*
    ;

declarecursorstmt
    : DECLARE name ( NO SCROLL | SCROLL | BINARY | INSENSITIVE )* CURSOR hold_? FOR selectstmt
    ;

hold_
    : ( WITH | WITHOUT ) HOLD
    ;

selectstmt
    : select_no_parens
    | select_with_parens
    ;

select_with_parens
    : OPEN_PAREN select_no_parens CLOSE_PAREN
    | OPEN_PAREN select_with_parens CLOSE_PAREN
    ;

select_no_parens
    : select_clause (orderBy)? (
        for_locking_clause (select_limit)?
        | select_limit (for_locking_clause)?
     )?
    | with_clause select_clause (orderBy)? (
        for_locking_clause (select_limit)?
        | select_limit (for_locking_clause)?
     )?
    ;

select_clause
    : simple_select_intersect ( ( UNION | EXCEPT ) all_or_distinct? simple_select_intersect )*
    ;

simple_select_intersect
    : simple_select_pramary ( INTERSECT all_or_distinct? simple_select_pramary )*
    ;

simple_select_pramary
    : ( 
        SELECT
	(  ALL? target_list?
		into_clause? from? where?
		group_clause? having_clause? window_clause?
	| distinct_clause target_list
		into_clause? from? where?
		group_clause? having_clause? window_clause?
        )
    )
    | values
    | TABLE relation_expr
    | select_with_parens
    ;

with_clause
    : WITH RECURSIVE? cte_list
    ;

cte_list
    : cte ( COMMA cte )*
    ;

cte
    : name name_list_? AS materialized? OPEN_PAREN preparablestmt CLOSE_PAREN
    ;

materialized
    : NOT? MATERIALIZED
    ;

into_clause
    : INTO opttempTableName
    ;

opttempTableName
    : ( LOCAL | GLOBAL )? ( TEMPORARY | TEMP ) (TABLE)? qname
    | UNLOGGED (TABLE)? qname
    | TABLE qname
    | qname
    ;

all_or_distinct
    : ALL
    | DISTINCT
    ;

distinct_clause
    : DISTINCT ( ON OPEN_PAREN expr_list CLOSE_PAREN )?
    ;

orderBy
    : ORDER BY sortby ( COMMA sortby  )*
    ;

sortby
    : a_expr ( USING qual_all_op | asc_desc_?) nulls_order_?
    ;

select_limit
    : limit_clause offset_clause?
    | offset_clause limit_clause?
    ;

limit_clause
    : LIMIT (a_expr | ALL) ( COMMA a_expr )?
    | FETCH first_or_next ( 
        select_fetch_first_value row_or_rows ( ONLY | WITH TIES )
        | row_or_rows ( ONLY | WITH TIES )
    )
    ;

offset_clause
    : OFFSET ( a_expr | select_fetch_first_value row_or_rows )
    ;

select_fetch_first_value
    : c_expr
    | PLUS i_or_f_const
    | MINUS i_or_f_const
    ;

i_or_f_const
    : integer
    | Numeric
    ;

row_or_rows
    : ROW
    | ROWS
    ;

first_or_next
    : FIRST_P
    | NEXT
    ;

group_clause
    : GROUP_P BY group_by_list
    
    ;

group_by_list
    : group_by_item ( COMMA group_by_item )*
    ;

group_by_item
    : OPEN_PAREN CLOSE_PAREN
    | CUBE OPEN_PAREN expr_list CLOSE_PAREN
    | ROLLUP OPEN_PAREN expr_list CLOSE_PAREN
    | GROUPING SETS OPEN_PAREN group_by_list CLOSE_PAREN
    | a_expr
    ;

having_clause
    : HAVING a_expr
    
    ;

for_locking_clause
    : (FOR ( ( NO KEY  )? UPDATE | KEY? SHARE ) (OF qnames)? nowait_or_skip_?)+
    | FOR READ ONLY
    ;

values
    : VALUES OPEN_PAREN expr_list CLOSE_PAREN ( COMMA OPEN_PAREN expr_list CLOSE_PAREN )*
    ;

from
    : FROM from_list
    ;

from_list
    : table_ref ( COMMA table_ref )*
    ;

table_ref
    : ( 
        relation_expr alias_clause? tablesample_clause?
        | func_table func_alias_clause?
        | xmltable alias_clause?
        | select_with_parens alias_clause?
        | LATERAL_P ( 
            xmltable alias_clause?
            | func_table func_alias_clause?
            | select_with_parens alias_clause?
        )
        | OPEN_PAREN table_ref ( 
            CROSS JOIN table_ref
            | NATURAL join_type? JOIN table_ref
            | join_type? JOIN table_ref join_qual
         )? CLOSE_PAREN alias_clause?
    ) ( 
        CROSS JOIN table_ref
        | NATURAL join_type? JOIN table_ref
        | join_type? JOIN table_ref join_qual
     )*
    ;

alias_clause
    : AS? name ( OPEN_PAREN names CLOSE_PAREN )?
    ;

func_alias_clause
    : alias_clause
    | ( AS name? | name ) OPEN_PAREN tablefuncelementlist CLOSE_PAREN
    
    ;

join_type
    : ( FULL | LEFT | RIGHT | INNER_P ) OUTER_P?
    ;

join_qual
    : USING OPEN_PAREN names CLOSE_PAREN
    | ON a_expr
    ;

relation_expr
    : qname STAR?
    | ONLY ( qname | OPEN_PAREN qname CLOSE_PAREN )
    ;

relation_expr_list
    : relation_expr ( COMMA relation_expr )*
    ;

relation_expr_opt_alias
    : relation_expr ( AS? name )?
    ;

tablesample_clause
    : TABLESAMPLE func_name OPEN_PAREN expr_list CLOSE_PAREN (REPEATABLE OPEN_PAREN a_expr CLOSE_PAREN)?
    ;

func_table
    : func_expr_windowless (WITH ORDINALITY)?
    | ROWS FROM OPEN_PAREN rowsfrom_list CLOSE_PAREN (WITH ORDINALITY)?
    ;

rowsfrom_item
    : func_expr_windowless (AS OPEN_PAREN tablefuncelementlist CLOSE_PAREN)?
    ;

rowsfrom_list
    : rowsfrom_item ( COMMA rowsfrom_item )*
    ;

where
    : WHERE a_expr
    ;

where_or_current_clause
    : WHERE ( CURRENT_P OF name | a_expr )
    ;

tablefuncelementlist
    : tablefuncelement ( COMMA tablefuncelement )*
    ;

tablefuncelement
    : name typename collate_clause_?
    ;

xmltable
    : XMLTABLE OPEN_PAREN ( 
        c_expr xmlexists_argument COLUMNS xmltable_column_list
        | XMLNAMESPACES OPEN_PAREN xml_namespace_list CLOSE_PAREN COMMA c_expr xmlexists_argument COLUMNS xmltable_column_list
    ) CLOSE_PAREN
    ;

xmltable_column_list
    : xmltable_column_el ( COMMA xmltable_column_el )*
    ;

xmltable_column_el
    : name ( typename (xmltable_column_option_el+)? | FOR ORDINALITY )
    ;

xmltable_column_option_el
    : DEFAULT a_expr
    | identifier a_expr
    | NOT? NULL_P
    ;

xml_namespace_list
    : xml_namespace_el ( COMMA xml_namespace_el )*
    ;

xml_namespace_el
    : b_expr AS colLabel
    | DEFAULT b_expr
    ;

typename
    : SETOF? simpletypename
	(  ( OPEN_BRACKET integer? CLOSE_BRACKET )*
	| ARRAY ( OPEN_BRACKET integer CLOSE_BRACKET )?
	)
    ;

simpletypename
    : generictype
    | numeric
    | bit
    | character
    | constdatetime
    | constinterval ( interval_? | OPEN_PAREN integer CLOSE_PAREN )
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
    : type_function_name ( DOT colLabel )+? type_modifiers_?
    ;

type_modifiers_
    : OPEN_PAREN expr_list CLOSE_PAREN
    
    ;

numeric
    : INT_P
    | INTEGER
    | SMALLINT
    | BIGINT
    | REAL
    | FLOAT_P float_?
    | DOUBLE_P PRECISION
    | DECIMAL_P type_modifiers_?
    | DEC type_modifiers_?
    | NUMERIC type_modifiers_?
    | BOOLEAN_P
    ;

float_
    : OPEN_PAREN integer CLOSE_PAREN
    
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
    : BIT varying_? OPEN_PAREN expr_list CLOSE_PAREN
    ;

bitwithoutlength
    : BIT varying_?
    ;

character
    : character_c ( OPEN_PAREN integer CLOSE_PAREN )?
    ;

constcharacter
    : character_c ( OPEN_PAREN integer CLOSE_PAREN )?
    ;

character_c
    : ( CHARACTER | CHAR_P | NCHAR ) varying_?
    | VARCHAR
    | NATIONAL ( CHARACTER | CHAR_P ) varying_?
    ;

varying_
    : VARYING
    
    ;

constdatetime
    : ( TIMESTAMP | TIME ) ( OPEN_PAREN integer CLOSE_PAREN )? timezone_?
    ;

constinterval
    : INTERVAL
    ;

//TODO with_la was used

timezone_
    : WITH TIME ZONE
    | WITHOUT TIME ZONE
    
    ;

interval_
    : YEAR_P
    | MONTH_P
    | DAY_P
    | HOUR_P
    | MINUTE_P
    | interval_second
    | YEAR_P TO MONTH_P
    | DAY_P TO ( HOUR_P | MINUTE_P | interval_second )
    | HOUR_P TO ( MINUTE_P | interval_second )
    | MINUTE_P TO interval_second
    
    ;

interval_second
    : SECOND_P ( OPEN_PAREN integer CLOSE_PAREN )?
    ;

jsonType
    : JSON
    ;

escape_
    : ESCAPE a_expr
    
    ;

//precendence accroding to Table 4.2. Operator Precedence ( highest to lowest)

//https://www.postgresql.org/docs/12/sql-syntax-lexical.html#SQL-PRECEDENCE

/*
original version of a_expr, for info
 a_expr: c_expr
        //::	left	PostgreSQL-style typecast
       | a_expr TYPECAST typename -- 1
       | a_expr COLLATE any_name -- 2
       | a_expr AT TIME ZONE a_expr-- 3
       //right	unary plus, unary minus
       | ( PLUS| MINUS) a_expr -- 4
        //left	exponentiation
       | a_expr CARET a_expr -- 5
        //left	multiplication, division, modulo
       | a_expr ( STAR | SLASH | PERCENT) a_expr -- 6
        //left	addition, subtraction
       | a_expr ( PLUS | MINUS) a_expr -- 7
        //left	all other native and user-defined operators
       | a_expr qual_op a_expr -- 8
       | qual_op a_expr -- 9
        //range containment, set membership, string matching BETWEEN IN LIKE ILIKE SIMILAR
       | a_expr NOT? ( LIKE|ILIKE|SIMILAR TO|( BETWEEN SYMMETRIC?)) a_expr opt_escape -- 10
        //< > = <= >= <>	 	comparison operators
       | a_expr ( LT | GT | EQUAL | LESS_EQUALS | GREATER_EQUALS | NOT_EQUALS) a_expr -- 11
       //IS ISNULL NOTNULL	 	IS TRUE, IS FALSE, IS NULL, IS DISTINCT FROM, etc
       | a_expr IS NOT?
            ( 
                NULL_P
                |TRUE_P
                |FALSE_P
                |UNKNOWN
                |DISTINCT FROM a_expr
                |OF OPEN_PAREN type_list CLOSE_PAREN
                |DOCUMENT_P
                |unicode_normal_form? NORMALIZED
            ) -- 12
       | a_expr ( ISNULL|NOTNULL) -- 13
       | row OVERLAPS row -- 14
       //NOT	right	logical negation
       | NOT a_expr -- 15
        //AND	left	logical conjunction
       | a_expr AND a_expr -- 16
        //OR	left	logical disjunction
       | a_expr OR a_expr -- 17
       | a_expr ( LESS_LESS|GREATER_GREATER) a_expr -- 18
       | a_expr qual_op -- 19
       | a_expr NOT? IN_P in_expr -- 20
       | a_expr subquery_Op sub_type ( select_with_parens|OPEN_PAREN a_expr CLOSE_PAREN) -- 21
       | UNIQUE select_with_parens -- 22
       | DEFAULT -- 23
;
*/

a_expr
    : a_expr_qual
    ;

/*23*/

/*moved to c_expr*/

/*22*/

/*moved to c_expr*/

/*19*/

a_expr_qual
    : a_expr_lessless (  qual_op | )
    ;

/*18*/

a_expr_lessless
    : a_expr_or ( ( LESS_LESS | GREATER_GREATER ) a_expr_or )*
    ;

/*17*/

a_expr_or
    : a_expr_and ( OR a_expr_and )*
    ;

/*16*/

a_expr_and
    : a_expr_between ( AND a_expr_between )*
    ;

/*21*/

a_expr_between
    : a_expr_in ( NOT? BETWEEN SYMMETRIC? a_expr_in AND a_expr_in )?
    ;

/*20*/

a_expr_in
    : a_expr_unary_not ( NOT? IN_P in_expr )?
    ;

/*15*/

a_expr_unary_not
    : NOT? a_expr_isnull
    ;

/*14*/

/*moved to c_expr*/

/*13*/

a_expr_isnull
    : a_expr_is_not ( ISNULL | NOTNULL )?
    ;

/*12*/

a_expr_is_not
    : a_expr_compare ( 
        IS NOT? ( 
            NULL_P
            | TRUE_P
            | FALSE_P
            | UNKNOWN
            | DISTINCT FROM a_expr
            | OF OPEN_PAREN type_list CLOSE_PAREN
            | DOCUMENT_P
            | unicode_normal_form? NORMALIZED
        )
     )?
    ;

/*11*/

a_expr_compare
    : a_expr_like ( 
        ( LT | GT | EQUAL | LESS_EQUALS | GREATER_EQUALS | NOT_EQUALS ) a_expr_like
        | subquery_Op sub_type ( select_with_parens | OPEN_PAREN a_expr CLOSE_PAREN) /*21*/
      )?
    ;

/*10*/

a_expr_like
    : a_expr_qual_op ( NOT? ( LIKE | ILIKE | SIMILAR TO ) a_expr_qual_op escape_? )?
    ;

/* 8*/

a_expr_qual_op
    : a_expr_unary_qualop ( qual_op a_expr_unary_qualop )*
    ;

/* 9*/

a_expr_unary_qualop
    : qual_op? a_expr_add
    ;

/* 7*/

a_expr_add
    : a_expr_mul ( ( MINUS | PLUS ) a_expr_mul )*
    ;

/* 6*/

a_expr_mul
    : a_expr_caret ( ( STAR | SLASH | PERCENT ) a_expr_caret )*
    ;

/* 5*/

a_expr_caret
    : a_expr_unary_sign ( CARET a_expr_unary_sign )?
    ;

/* 4*/

a_expr_unary_sign
    : ( MINUS | PLUS )? a_expr_at_time_zone /* */
    ;

/* 3*/

a_expr_at_time_zone
    : a_expr_collate ( AT TIME ZONE a_expr )?
    ;

/* 2*/

a_expr_collate
    : a_expr_typecast ( COLLATE any_name )?
    ;

/* 1*/

a_expr_typecast
    : c_expr ( TYPECAST typename )*
    ;

b_expr
    : c_expr
    | b_expr TYPECAST typename
    //right	unary plus, unary minus
    | ( PLUS | MINUS ) b_expr
    //^	left	exponentiation
    | b_expr CARET b_expr
    //* / %	left	multiplication, division, modulo
    | b_expr ( STAR | SLASH | PERCENT ) b_expr
    //+ -	left	addition, subtraction
    | b_expr ( PLUS | MINUS ) b_expr
    //( any other operator)	left	all other native and user-defined operators
    | b_expr qual_op b_expr
    //< > = <= >= <>	 	comparison operators
    | b_expr ( LT | GT | EQUAL | LESS_EQUALS | GREATER_EQUALS | NOT_EQUALS ) b_expr
    | qual_op b_expr
    | b_expr qual_op
    //S ISNULL NOTNULL	 	IS TRUE, IS FALSE, IS NULL, IS DISTINCT FROM, etc
    | b_expr IS NOT? ( DISTINCT FROM b_expr | OF OPEN_PAREN type_list CLOSE_PAREN | DOCUMENT_P )
    ;

c_expr
    : EXISTS select_with_parens                                        # c_expr_exists
    | ARRAY ( select_with_parens | array_expr )                          # c_expr_expr
    | PARAM indirection_el*                                            # c_expr_expr
    | GROUPING OPEN_PAREN expr_list CLOSE_PAREN                        # c_expr_expr
    | /*22*/ UNIQUE select_with_parens                                 # c_expr_expr
    | columnref                                                        # c_expr_expr
    | aexprconst                                                       # c_expr_expr
    | OPEN_PAREN a_expr_in_parens = a_expr CLOSE_PAREN indirection_el* # c_expr_expr
    | case_expr                                                        # c_expr_case
    | func_expr                                                        # c_expr_expr
    | select_with_parens (indirection_el+)?                                  # c_expr_expr
    | explicit_row                                                     # c_expr_expr
    | implicit_row                                                     # c_expr_expr
    | row OVERLAPS row /* 14*/                                         # c_expr_expr
    | DEFAULT                                                          # c_expr_expr
    ;

plsqlvariablename
    : PLSQLVARIABLENAME
    ;

func_application
    : func_name OPEN_PAREN ( 
        func_arg_list ( COMMA VARIADIC func_arg_expr )? (orderBy)?
        | VARIADIC func_arg_expr (orderBy)?
        | ( ALL | DISTINCT ) func_arg_list (orderBy)?
        | STAR
        |
    ) CLOSE_PAREN
    ;

func_expr
    : func_application within_group_clause? filter_clause? over_clause?
    | func_expr_common_subexpr
    ;

func_expr_windowless
    : func_application
    | func_expr_common_subexpr
    ;

func_expr_common_subexpr
    : COLLATION FOR OPEN_PAREN a_expr CLOSE_PAREN
    | CURRENT_DATE
    | CURRENT_TIME ( OPEN_PAREN integer CLOSE_PAREN )?
    | CURRENT_TIMESTAMP ( OPEN_PAREN integer CLOSE_PAREN )?
    | LOCALTIME ( OPEN_PAREN integer CLOSE_PAREN )?
    | LOCALTIMESTAMP ( OPEN_PAREN integer CLOSE_PAREN )?
    | CURRENT_ROLE
    | CURRENT_USER
    | SESSION_USER
    | SYSTEM_USER
    | USER
    | CURRENT_CATALOG
    | CURRENT_SCHEMA
    | CAST OPEN_PAREN a_expr AS typename CLOSE_PAREN
    | EXTRACT OPEN_PAREN extract_list? CLOSE_PAREN
    | NORMALIZE OPEN_PAREN a_expr ( COMMA unicode_normal_form )? CLOSE_PAREN
    | OVERLAY OPEN_PAREN ( overlay_list | func_arg_list? ) CLOSE_PAREN
    | POSITION OPEN_PAREN position_list? CLOSE_PAREN
    | SUBSTRING OPEN_PAREN ( substr_list | func_arg_list? ) CLOSE_PAREN
    | TREAT OPEN_PAREN a_expr AS typename CLOSE_PAREN
    | TRIM OPEN_PAREN ( BOTH | LEADING | TRAILING )? trim_list CLOSE_PAREN
    | NULLIF OPEN_PAREN a_expr COMMA a_expr CLOSE_PAREN
    | COALESCE OPEN_PAREN expr_list CLOSE_PAREN
    | GREATEST OPEN_PAREN expr_list CLOSE_PAREN
    | LEAST OPEN_PAREN expr_list CLOSE_PAREN
    | XMLCONCAT OPEN_PAREN expr_list CLOSE_PAREN
    | XMLELEMENT OPEN_PAREN NAME_P colLabel ( COMMA ( xml_attributes | expr_list ) )? CLOSE_PAREN
    | XMLEXISTS OPEN_PAREN c_expr xmlexists_argument CLOSE_PAREN
    | XMLFOREST OPEN_PAREN xml_attribute_list CLOSE_PAREN
    | XMLPARSE OPEN_PAREN document_or_content a_expr xml_whitespace_option? CLOSE_PAREN
    | XMLPI OPEN_PAREN NAME_P colLabel ( COMMA a_expr )? CLOSE_PAREN
    | XMLROOT OPEN_PAREN XML_P a_expr COMMA xml_root_version xml_root_standalone_? CLOSE_PAREN
    | XMLSERIALIZE OPEN_PAREN document_or_content a_expr AS simpletypename CLOSE_PAREN
    | JSON_OBJECT OPEN_PAREN ( func_arg_list
		| json_name_and_value_list
		  json_object_constructor_null_clause?
		  json_key_uniqueness_constraint?
		  json_returning_clause?
		| json_returning_clause? )
		CLOSE_PAREN
    | JSON_ARRAY OPEN_PAREN ( json_value_expr_list
		  json_object_constructor_null_clause?
		  json_returning_clause?
		| select_no_parens
		  (FORMAT_LA JSON ( ENCODING name )?)?
		  json_returning_clause?
		| json_returning_clause?
		)
		CLOSE_PAREN
    | JSON '(' json_value_expr json_key_uniqueness_constraint? ')'
    | JSON_SCALAR '(' a_expr ')'
    | JSON_SERIALIZE '(' json_value_expr json_returning_clause? ')'
    | MERGE_ACTION '(' ')'
    | JSON_QUERY '('
		json_value_expr ',' a_expr json_passing_clause?
		json_returning_clause?
		json_wrapper_behavior
		json_quotes_clause?
		json_behavior_clause?
		')'
    | JSON_EXISTS '('
		json_value_expr ',' a_expr json_passing_clause?
		json_on_error_clause?
		')'
    | JSON_VALUE '('
		json_value_expr ',' a_expr json_passing_clause?
		json_returning_clause?
		json_behavior_clause?
		')'
    ;

/* SQL/XML support */

xml_root_version
    : VERSION_P a_expr
    | VERSION_P NO VALUE_P
    ;

xml_root_standalone_
    : COMMA STANDALONE_P YES_P
    | COMMA STANDALONE_P NO
    | COMMA STANDALONE_P NO VALUE_P
    ;

xml_attributes
    : XMLATTRIBUTES OPEN_PAREN xml_attribute_list CLOSE_PAREN
    ;

xml_attribute_list
    : xml_attribute_el ( COMMA xml_attribute_el )*
    ;

xml_attribute_el
    : a_expr ( AS colLabel )?
    ;

document_or_content
    : DOCUMENT_P
    | CONTENT_P
    ;

xml_whitespace_option
    : PRESERVE WHITESPACE_P
    | STRIP_P WHITESPACE_P
    
    ;

xmlexists_argument
    : PASSING c_expr
    | PASSING c_expr xml_passing_mech
    | PASSING xml_passing_mech c_expr
    | PASSING xml_passing_mech c_expr xml_passing_mech
    ;

xml_passing_mech
    : BY ( REF | VALUE_P )
    ;

within_group_clause
    : WITHIN GROUP_P OPEN_PAREN orderBy CLOSE_PAREN
    
    ;

filter_clause
    : FILTER OPEN_PAREN WHERE a_expr CLOSE_PAREN
    
    ;

window_clause
    : WINDOW window_definition_list
    
    ;

window_definition_list
    : window_definition ( COMMA window_definition )*
    ;

window_definition
    : name AS window_specification
    ;

over_clause
    : OVER ( window_specification | name )
    
    ;

window_specification
    : OPEN_PAREN existing_window_name_? partition_clause_? (orderBy)? frame_clause_? CLOSE_PAREN
    ;

existing_window_name_
    : name
    
    ;

partition_clause_
    : PARTITION BY expr_list
    
    ;

frame_clause_
    : RANGE frame_extent window_exclusion_clause_?
    | ROWS frame_extent window_exclusion_clause_?
    | GROUPS frame_extent window_exclusion_clause_?
    
    ;

frame_extent
    : frame_bound
    | BETWEEN frame_bound AND frame_bound
    ;

frame_bound
    : UNBOUNDED ( PRECEDING | FOLLOWING )
    | CURRENT_P ROW
    | a_expr ( PRECEDING | FOLLOWING )
    ;

window_exclusion_clause_
    : EXCLUDE ( CURRENT_P ROW | GROUP_P | TIES | NO OTHERS ) ;

row
    : ROW OPEN_PAREN expr_list? CLOSE_PAREN
    | OPEN_PAREN expr_list COMMA a_expr CLOSE_PAREN
    ;

explicit_row
    : ROW OPEN_PAREN expr_list? CLOSE_PAREN
    ;

/*
TODO:
for some reason v1
implicit_row: OPEN_PAREN expr_list COMMA a_expr CLOSE_PAREN;
works better than v2
implicit_row: OPEN_PAREN expr_list  CLOSE_PAREN;
while looks like they are almost the same, except v2 requieres at least 2 items in list
while v1 allows single item in list
*/

implicit_row
    : OPEN_PAREN expr_list COMMA a_expr CLOSE_PAREN
    ;

sub_type
    : ANY
    | SOME
    | ALL
    ;

all_op
    : Operator
    | mathop
    ;

mathop
    : PLUS
    | MINUS
    | STAR
    | SLASH
    | PERCENT
    | CARET
    | LT
    | GT
    | EQUAL
    | LESS_EQUALS
    | GREATER_EQUALS
    | NOT_EQUALS
    ;

qual_op
    : Operator
    | OPERATOR OPEN_PAREN any_operator CLOSE_PAREN
    ;

qual_all_op
    : all_op
    | OPERATOR OPEN_PAREN any_operator CLOSE_PAREN
    ;

subquery_Op
    : all_op
    | OPERATOR OPEN_PAREN any_operator CLOSE_PAREN
    | LIKE
    | NOT LIKE
    | ILIKE
    | NOT ILIKE
    ;

expr_list
    : a_expr ( COMMA a_expr )*
    ;

func_arg_list
    : func_arg_expr ( COMMA func_arg_expr )*
    ;

func_arg_expr
    : a_expr
    | type_function_name ( COLON_EQUALS | EQUALS_GREATER ) a_expr
    ;

type_list
    : typename ( COMMA typename )*
    ;

array_expr
    : OPEN_BRACKET ( expr_list | array_expr_list )? CLOSE_BRACKET
    ;

array_expr_list
    : array_expr ( COMMA array_expr )*
    ;

extract_list
    : extract_arg FROM a_expr
    
    ;

extract_arg
    : identifier
    | YEAR_P
    | MONTH_P
    | DAY_P
    | HOUR_P
    | MINUTE_P
    | SECOND_P
    | string
    ;

unicode_normal_form
    : NFC
    | NFD
    | NFKC
    | NFKD
    ;

overlay_list
    : a_expr PLACING a_expr FROM a_expr ( FOR a_expr )?
    ;

position_list
    : b_expr IN_P b_expr
    
    ;

substr_list
    : a_expr FROM a_expr FOR a_expr
    | a_expr FOR a_expr FROM a_expr
    | a_expr FROM a_expr
    | a_expr FOR a_expr
    | a_expr SIMILAR a_expr ESCAPE a_expr
    ;

trim_list
    : a_expr FROM expr_list
    | FROM expr_list
    | expr_list
    ;

in_expr
    : select_with_parens               # in_expr_select
    | OPEN_PAREN expr_list CLOSE_PAREN # in_expr_list
    ;

case_expr
    : CASE case_arg? when_clause_list case_default? END_P
    ;

when_clause_list
    : when_clause+
    ;

when_clause
    : WHEN a_expr THEN a_expr
    ;

case_default
    : ELSE a_expr
    
    ;

case_arg
    : a_expr
    
    ;

columnref
    : name (indirection_el+)?
    ;

indirection_el
    : DOT ( colLabel | STAR )
    | OPEN_BRACKET ( a_expr | (a_expr)? COLON (a_expr)? ) CLOSE_BRACKET
    ;

json_passing_clause:
			PASSING json_arguments
		;

json_arguments:
			json_argument
			| json_arguments ',' json_argument
		;

json_argument
  : json_value_expr AS colLabel ;

/* ARRAY is a noise word */
json_wrapper_behavior:
			  WITHOUT WRAPPER
			| WITHOUT ARRAY	WRAPPER
			| WITH WRAPPER
			| WITH ARRAY WRAPPER
			| WITH CONDITIONAL ARRAY WRAPPER
			| WITH UNCONDITIONAL ARRAY WRAPPER
			| WITH CONDITIONAL WRAPPER
			| WITH UNCONDITIONAL WRAPPER
			|
		;

json_behavior:
			DEFAULT a_expr
			| json_behavior_type
		;

json_behavior_type:
			ERROR
			| NULL_P
			| TRUE_P
			| FALSE_P
			| UNKNOWN
			| EMPTY_P ARRAY
			| EMPTY_P OBJECT_P
			/* non-standard, for Oracle compatibility only */
			| EMPTY_P
		;

json_behavior_clause:
			json_behavior ON EMPTY_P
			| json_behavior ON ERROR
			| json_behavior ON EMPTY_P json_behavior ON ERROR
		;

json_on_error_clause:
			json_behavior ON ERROR
		;

json_value_expr:
			a_expr (FORMAT_LA JSON ( ENCODING name )?)?
		;

json_quotes_clause
: ( KEEP | OMIT ) QUOTES ( ON SCALAR STRING_P )?
;

json_returning_clause:
			RETURNING typename (FORMAT_LA JSON ( ENCODING name )?)?
		;

/*
 * We must assign the only-JSON production a precedence less than IDENT in
 * order to favor shifting over reduction when JSON is followed by VALUE_P,
 * OBJECT_P, or SCALAR.  ( ARRAY doesn't need that treatment, because it's a
 * fully reserved word.)  Because json_predicate_type_constraint is always
 * followed by json_key_uniqueness_constraint_opt, we also need the only-JSON
 * production to have precedence less than WITH and WITHOUT.  UNBOUNDED isn't
 * really related to this syntax, but it's a convenient choice because it
 * already has a precedence less than IDENT for other reasons.
 */
json_predicate_type_constraint:
			JSON
			| JSON VALUE_P
			| JSON ARRAY
			| JSON OBJECT_P
			| JSON SCALAR
		;

/*
 * KEYS is a noise word here.  To avoid shift/reduce conflicts, assign the
 * KEYS-less productions a precedence less than IDENT ( i.e., less than KEYS).
 * This prevents reducing them when the next token is KEYS.
 */
json_key_uniqueness_constraint:
			WITH UNIQUE KEYS
			| WITH UNIQUE
			| WITHOUT UNIQUE KEYS
			| WITHOUT UNIQUE
		;

json_name_and_value_list:
			json_name_and_value
			| json_name_and_value_list ',' json_name_and_value
		;

json_name_and_value:
			c_expr VALUE_P json_value_expr
			|
			a_expr ':' json_value_expr
		;

/* empty means false for objects, true for arrays */
json_object_constructor_null_clause
  : ( NULL_P | ABSENT ) ON NULL_P ;

json_value_expr_list:
			json_value_expr
			| json_value_expr_list ',' json_value_expr
		;

json_aggregate_func:
			JSON_OBJECTAGG '('
				json_name_and_value
				json_object_constructor_null_clause?
				json_key_uniqueness_constraint?
				json_returning_clause
			')'
			| JSON_ARRAYAGG '('
				json_value_expr
				json_array_aggregate_order_by_clause?
				json_object_constructor_null_clause?
				json_returning_clause
			')'
		;

json_array_aggregate_order_by_clause:
			ORDER BY sortby ( COMMA sortby  )*
		;

target_list
    : target_el ( COMMA target_el )*
    ;

target_el
    : a_expr ( AS colLabel | (identifier
    | bare_label_keyword) )?
    | STAR
    ;

qnames
    : qname ( COMMA qname )*
    ;

qname
    : name (indirection_el+)?
    ;

names
    : name ( COMMA name )*
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
    | func_name ( string | OPEN_PAREN func_arg_list (orderBy)? CLOSE_PAREN string )
    | consttypename string
    | constinterval ( string interval_? | OPEN_PAREN integer CLOSE_PAREN string )
    | TRUE_P
    | FALSE_P
    | NULL_P
    ;

integer
    : Integral
    | BinaryIntegral
    | OctalIntegral
    | HexadecimalIntegral
    ;

string
    : string_ ( UESCAPE string_ )?
    ;

string_
    : StringConstant
    | UnicodeEscapeStringConstant
    | BeginDollarStringConstant DollarText* EndDollarStringConstant
    | EscapeStringConstant
    ;

signediconst
    : (PLUS | MINUS)+ integer
    ;

rolespec
    : nonreservedword
    | CURRENT_USER
    | SESSION_USER
    ;

role_list
    : rolespec ( COMMA rolespec )*
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
    | EXIT //NB: not in gram.y official source.
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
    : ABORT_P
    | ABSENT
    | ABSOLUTE_P
    | ACCESS
    | ACTION
    | ADD_P
    | ADMIN
    | AFTER
    | AGGREGATE
    | ALSO
    | ALTER
    | ALWAYS
    | ASENSITIVE
    | ASSERTION
    | ASSIGNMENT
    | AT
    | ATOMIC
    | ATTACH
    | ATTRIBUTE
    | BACKWARD
    | BEFORE
    | BEGIN_P
    | BREADTH
    | BY
    | CACHE
    | CALL
    | CALLED
    | CASCADE
    | CASCADED
    | CATALOG
    | CHAIN
    | CHARACTERISTICS
    | CHECKPOINT
    | CLASS
    | CLOSE
    | CLUSTER
    | COLUMNS
    | COMMENT
    | COMMENTS
    | COMMIT
    | COMMITTED
    | COMPRESSION
    | CONDITIONAL
    | CONFIGURATION
    | CONFLICT
    | CONNECTION
    | CONSTRAINTS
    | CONTENT_P
    | CONTINUE_P
    | CONVERSION_P
    | COPY
    | COST
    | CSV
    | CUBE
    | CURRENT_P
    | CURSOR
    | CYCLE
    | DATA_P
    | DATABASE
    | DAY_P
    | DEALLOCATE
    | DECLARE
    | DEFAULTS
    | DEFERRED
    | DEFINER
    | DELETE_P
    | DELIMITER
    | DELIMITERS
    | DEPENDS
    | DEPTH
    | DETACH
    | DICTIONARY
    | DISABLE_P
    | DISCARD
    | DOCUMENT_P
    | DOMAIN_P
    | DOUBLE_P
    | DROP
    | EACH
    | EMPTY_P
    | ENABLE_P
    | ENCODING
    | ENCRYPTED
    | ENUM_P
    | ERROR
    | ESCAPE
    | EVENT
    | EXCLUDE
    | EXCLUDING
    | EXCLUSIVE
    | EXECUTE
    | EXPLAIN
    | EXPRESSION
    | EXTENSION
    | EXTERNAL
    | FAMILY
    | FILTER
    | FINALIZE
    | FIRST_P
    | FOLLOWING
    | FORCE
    | FORMAT
    | FORWARD
    | FUNCTION
    | FUNCTIONS
    | GENERATED
    | GLOBAL
    | GRANTED
    | GROUPS
    | HANDLER
    | HEADER_P
    | HOLD
    | HOUR_P
    | IDENTITY_P
    | IF_P
    | IMMEDIATE
    | IMMUTABLE
    | IMPLICIT_P
    | IMPORT_P
    | INCLUDE
    | INCLUDING
    | INCREMENT
    | INDENT
    | INDEX
    | INDEXES
    | INHERIT
    | INHERITS
    | INLINE_P
    | INPUT_P
    | INSENSITIVE
    | INSERT
    | INSTEAD
    | INVOKER
    | ISOLATION
    | KEEP
    | KEY
    | KEYS
    | LABEL
    | LANGUAGE
    | LARGE_P
    | LAST_P
    | LEAKPROOF
    | LEVEL
    | LISTEN
    | LOAD
    | LOCAL
    | LOCATION
    | LOCK_P
    | LOCKED
    | LOGGED
    | MAPPING
    | MATCH
    | MATCHED
    | MATERIALIZED
    | MAXVALUE
    | MERGE
    | METHOD
    | MINUTE_P
    | MINVALUE
    | MODE
    | MONTH_P
    | MOVE
    | NAME_P
    | NAMES
    | NESTED
    | NEW
    | NEXT
    | NFC
    | NFD
    | NFKC
    | NFKD
    | NO
    | NORMALIZED
    | NOTHING
    | NOTIFY
    | NOWAIT
    | NULLS_P
    | OBJECT_P
    | OF
    | OFF
    | OIDS
    | OLD
    | OMIT
    | OPERATOR
    | OPTION
    | OPTIONS
    | ORDINALITY
    | OTHERS
    | OVER
    | OVERRIDING
    | OWNED
    | OWNER
    | PARALLEL
    | PARAMETER
    | PARSER
    | PARTIAL
    | PARTITION
    | PASSING
    | PASSWORD
    | PATH
    | PERIOD
    | PLAN
    | PLANS
    | POLICY
    | PRECEDING
    | PREPARE
    | PREPARED
    | PRESERVE
    | PRIOR
    | PRIVILEGES
    | PROCEDURAL
    | PROCEDURE
    | PROCEDURES
    | PROGRAM
    | PUBLICATION
    | QUOTE
    | QUOTES
    | RANGE
    | READ
    | REASSIGN
//    | RECHECK
    | RECURSIVE
    | REF
    | REFERENCING
    | REFRESH
    | REINDEX
    | RELATIVE_P
    | RELEASE
    | RENAME
    | REPEATABLE
    | REPLACE
    | REPLICA
    | RESET
    | RESTART
    | RESTRICT
    | RETURN
    | RETURNS
    | REVOKE
    | ROLE
    | ROLLBACK
    | ROLLUP
    | ROUTINE
    | ROUTINES
    | ROWS
    | RULE
    | SAVEPOINT
    | SCALAR
    | SCHEMA
    | SCHEMAS
    | SCROLL
    | SEARCH
    | SECOND_P
    | SECURITY
    | SEQUENCE
    | SEQUENCES
    | SERIALIZABLE
    | SERVER
    | SESSION
    | SET
    | SETS
    | SHARE
    | SHOW
    | SIMPLE
    | SKIP_P
    | SNAPSHOT
    | SOURCE
    | SQL_P
    | STABLE
    | STANDALONE_P
    | START
    | STATEMENT
    | STATISTICS
    | STDIN
    | STDOUT
    | STORAGE
    | STORED
    | STRICT_P
    | STRING_P
    | STRIP_P
    | SUBSCRIPTION
    | SUPPORT
    | SYSID
    | SYSTEM_P
    | TABLES
    | TABLESPACE
    | TARGET
    | TEMP
    | TEMPLATE
    | TEMPORARY
    | TEXT_P
    | TIES
    | TRANSACTION
    | TRANSFORM
    | TRIGGER
    | TRUNCATE
    | TRUSTED
    | TYPE_P
    | TYPES_P
    | UESCAPE
    | UNBOUNDED
    | UNCOMMITTED
    | UNCONDITIONAL
    | UNENCRYPTED
    | UNKNOWN
    | UNLISTEN
    | UNLOGGED
    | UNTIL
    | UPDATE
    | VACUUM
    | VALID
    | VALIDATE
    | VALIDATOR
    | VALUE_P
    | VARYING
    | VERSION_P
    | VIEW
    | VIEWS
    | VOLATILE
    | WHITESPACE_P
    | WITHIN
    | WITHOUT
    | WORK
    | WRAPPER
    | WRITE
    | XML_P
    | YEAR_P
    | YES_P
    | ZONE
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
    : BETWEEN
    | BIGINT
    | BIT
    | BOOLEAN_P
    | CHAR_P
    | character
    | COALESCE
    | DEC
    | DECIMAL_P
    | EXISTS
    | EXTRACT
    | FLOAT_P
    | GREATEST
    | GROUPING
    | INOUT
    | INT_P
    | INTEGER
    | INTERVAL
    | JSON
    | JSON_ARRAY
    | JSON_ARRAYAGG
    | JSON_EXISTS
    | JSON_OBJECT
    | JSON_OBJECTAGG
    | JSON_QUERY
    | JSON_SCALAR
    | JSON_SERIALIZE
    | JSON_TABLE
    | JSON_VALUE
    | LEAST
    | MERGE_ACTION
    | NATIONAL
    | NCHAR
    | NONE
    | NORMALIZE
    | NULLIF
    | NUMERIC
    | OUT_P
    | OVERLAY
    | POSITION
    | PRECISION
    | REAL
    | ROW
    | SETOF
    | SMALLINT
    | SUBSTRING
    | TIME
    | TIMESTAMP
    | TREAT
    | TRIM
    | VALUES
    | VARCHAR
    | XMLATTRIBUTES
    | XMLCONCAT
    | XMLELEMENT
    | XMLEXISTS
    | XMLFOREST
    | XMLNAMESPACES
    | XMLPARSE
    | XMLPI
    | XMLROOT
    | XMLSERIALIZE
    | XMLTABLE
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
    : AUTHORIZATION
    | BINARY
    | COLLATION
    | CONCURRENTLY
    | CROSS
    | CURRENT_SCHEMA
    | FREEZE
    | FULL
    | ILIKE
    | INNER_P
    | IS
    | ISNULL
    | JOIN
    | LEFT
    | LIKE
    | NATURAL
    | NOTNULL
    | OUTER_P
    | OVERLAPS
    | RIGHT
    | SIMILAR
    | TABLESAMPLE
    | VERBOSE
    ;

/* Reserved keyword --- these keywords are usable only as a ColLabel.
 *
 * Keywords appear here if they could not be distinguished from variable,
 * type, or function names in some contexts.  Don't put things here unless
 * forced to.
 */
reserved_keyword
    : ALL
    | ANALYSE
    | ANALYZE
    | AND
    | ANY
    | ARRAY
    | AS
    | ASC
    | ASYMMETRIC
    | BOTH
    | CASE
    | CAST
    | CHECK
    | COLLATE
    | COLUMN
    | CONSTRAINT
    | CREATE
    | CURRENT_CATALOG
    | CURRENT_DATE
    | CURRENT_ROLE
    | CURRENT_TIME
    | CURRENT_TIMESTAMP
    | CURRENT_USER
    | DEFAULT
    | DEFERRABLE
    | DESC
    | DISTINCT
    | DO
    | ELSE
    | END_P
    | EXCEPT
    | FALSE_P
    | FETCH
    | FOR
    | FOREIGN
    | FROM
    | GRANT
    | GROUP_P
    | HAVING
    | IN_P
    | INITIALLY
    | INTERSECT
    | INTO
    | LATERAL_P
    | LEADING
    | LIMIT
    | LOCALTIME
    | LOCALTIMESTAMP
    | NOT
    | NULL_P
    | OFFSET
    | ON
    | ONLY
    | OR
    | ORDER
    | PLACING
    | PRIMARY
    | REFERENCES
    | RETURNING
    | SELECT
    | SESSION_USER
    | SOME
    | SYMMETRIC
    | SYSTEM_USER
    | TABLE
    | THEN
    | TO
    | TRAILING
    | TRUE_P
    | UNION
    | UNIQUE
    | USER
    | USING
    | VARIADIC
    | WHEN
    | WHERE
    | WINDOW
    | WITH
    ;

/*
 * While all keywords can be used as column labels when preceded by AS,
 * not all of them can be used as a "bare" column label without AS.
 * Those that can be used as a bare label must be listed here,
 * in addition to appearing in one of the category lists above.
 *
 * Always add a new keyword to this list if possible.  Mark it BARE_LABEL
 * in kwlist.h if it is included here, or AS_LABEL if it is not.
 */
bare_label_keyword
    : ABORT_P
    | ABSENT
    | ABSOLUTE_P
    | ACCESS
    | ACTION
    | ADD_P
    | ADMIN
    | AFTER
    | AGGREGATE
    | ALL
    | ALSO
    | ALTER
    | ALWAYS
    | ANALYSE
    | ANALYZE
    | AND
    | ANY
    | ASC
    | ASENSITIVE
    | ASSERTION
    | ASSIGNMENT
    | ASYMMETRIC
    | AT
    | ATOMIC
    | ATTACH
    | ATTRIBUTE
    | AUTHORIZATION
    | BACKWARD
    | BEFORE
    | BEGIN_P
    | BETWEEN
    | BIGINT
    | BINARY
    | BIT
    | BOOLEAN_P
    | BOTH
    | BREADTH
    | BY
    | CACHE
    | CALL
    | CALLED
    | CASCADE
    | CASCADED
    | CASE
    | CAST
    | CATALOG
    | CHAIN
    | CHARACTERISTICS
    | CHECK
    | CHECKPOINT
    | CLASS
    | CLOSE
    | CLUSTER
    | COALESCE
    | COLLATE
    | COLLATION
    | COLUMN
    | COLUMNS
    | COMMENT
    | COMMENTS
    | COMMIT
    | COMMITTED
    | COMPRESSION
    | CONCURRENTLY
    | CONDITIONAL
    | CONFIGURATION
    | CONFLICT
    | CONNECTION
    | CONSTRAINT
    | CONSTRAINTS
    | CONTENT_P
    | CONTINUE_P
    | CONVERSION_P
    | COPY
    | COST
    | CROSS
    | CSV
    | CUBE
    | CURRENT_CATALOG
    | CURRENT_DATE
    | CURRENT_P
    | CURRENT_ROLE
    | CURRENT_SCHEMA
    | CURRENT_TIME
    | CURRENT_TIMESTAMP
    | CURRENT_USER
    | CURSOR
    | CYCLE
    | DATA_P
    | DATABASE
    | DEALLOCATE
    | DEC
    | DECIMAL_P
    | DECLARE
    | DEFAULT
    | DEFAULTS
    | DEFERRABLE
    | DEFERRED
    | DEFINER
    | DELETE_P
    | DELIMITER
    | DELIMITERS
    | DEPENDS
    | DEPTH
    | DESC
    | DETACH
    | DICTIONARY
    | DISABLE_P
    | DISCARD
    | DISTINCT
    | DO
    | DOCUMENT_P
    | DOMAIN_P
    | DOUBLE_P
    | DROP
    | EACH
    | ELSE
    | EMPTY_P
    | ENABLE_P
    | ENCODING
    | ENCRYPTED
    | END_P
    | ENUM_P
    | ERROR
    | ESCAPE
    | EVENT
    | EXCLUDE
    | EXCLUDING
    | EXCLUSIVE
    | EXECUTE
    | EXISTS
    | EXPLAIN
    | EXPRESSION
    | EXTENSION
    | EXTERNAL
    | EXTRACT
    | FALSE_P
    | FAMILY
    | FINALIZE
    | FIRST_P
    | FLOAT_P
    | FOLLOWING
    | FORCE
    | FOREIGN
    | FORMAT
    | FORWARD
    | FREEZE
    | FULL
    | FUNCTION
    | FUNCTIONS
    | GENERATED
    | GLOBAL
    | GRANTED
    | GREATEST
    | GROUPING
    | GROUPS
    | HANDLER
    | HEADER_P
    | HOLD
    | IDENTITY_P
    | IF_P
    | ILIKE
    | IMMEDIATE
    | IMMUTABLE
    | IMPLICIT_P
    | IMPORT_P
    | IN_P
    | INCLUDE
    | INCLUDING
    | INCREMENT
    | INDENT
    | INDEX
    | INDEXES
    | INHERIT
    | INHERITS
    | INITIALLY
    | INLINE_P
    | INNER_P
    | INOUT
    | INPUT_P
    | INSENSITIVE
    | INSERT
    | INSTEAD
    | INT_P
    | INTEGER
    | INTERVAL
    | INVOKER
    | IS
    | ISOLATION
    | JOIN
    | JSON
    | JSON_ARRAY
    | JSON_ARRAYAGG
    | JSON_EXISTS
    | JSON_OBJECT
    | JSON_OBJECTAGG
    | JSON_QUERY
    | JSON_SCALAR
    | JSON_SERIALIZE
    | JSON_TABLE
    | JSON_VALUE
    | KEEP
    | KEY
    | KEYS
    | LABEL
    | LANGUAGE
    | LARGE_P
    | LAST_P
    | LATERAL_P
    | LEADING
    | LEAKPROOF
    | LEAST
    | LEFT
    | LEVEL
    | LIKE
    | LISTEN
    | LOAD
    | LOCAL
    | LOCALTIME
    | LOCALTIMESTAMP
    | LOCATION
    | LOCK_P
    | LOCKED
    | LOGGED
    | MAPPING
    | MATCH
    | MATCHED
    | MATERIALIZED
    | MAXVALUE
    | MERGE
    | MERGE_ACTION
    | METHOD
    | MINVALUE
    | MODE
    | MOVE
    | NAME_P
    | NAMES
    | NATIONAL
    | NATURAL
    | NCHAR
    | NESTED
    | NEW
    | NEXT
    | NFC
    | NFD
    | NFKC
    | NFKD
    | NO
    | NONE
    | NORMALIZE
    | NORMALIZED
    | NOT
    | NOTHING
    | NOTIFY
    | NOWAIT
    | NULL_P
    | NULLIF
    | NULLS_P
    | NUMERIC
    | OBJECT_P
    | OF
    | OFF
    | OIDS
    | OLD
    | OMIT
    | ONLY
    | OPERATOR
    | OPTION
    | OPTIONS
    | OR
    | ORDINALITY
    | OTHERS
    | OUT_P
    | OUTER_P
    | OVERLAY
    | OVERRIDING
    | OWNED
    | OWNER
    | PARALLEL
    | PARAMETER
    | PARSER
    | PARTIAL
    | PARTITION
    | PASSING
    | PASSWORD
    | PATH
    | PERIOD
    | PLACING
    | PLAN
    | PLANS
    | POLICY
    | POSITION
    | PRECEDING
    | PREPARE
    | PREPARED
    | PRESERVE
    | PRIMARY
    | PRIOR
    | PRIVILEGES
    | PROCEDURAL
    | PROCEDURE
    | PROCEDURES
    | PROGRAM
    | PUBLICATION
    | QUOTE
    | QUOTES
    | RANGE
    | READ
    | REAL
    | REASSIGN
    | RECURSIVE
    | REF
    | REFERENCES
    | REFERENCING
    | REFRESH
    | REINDEX
    | RELATIVE_P
    | RELEASE
    | RENAME
    | REPEATABLE
    | REPLACE
    | REPLICA
    | RESET
    | RESTART
    | RESTRICT
    | RETURN
    | RETURNS
    | REVOKE
    | RIGHT
    | ROLE
    | ROLLBACK
    | ROLLUP
    | ROUTINE
    | ROUTINES
    | ROW
    | ROWS
    | RULE
    | SAVEPOINT
    | SCALAR
    | SCHEMA
    | SCHEMAS
    | SCROLL
    | SEARCH
    | SECURITY
    | SELECT
    | SEQUENCE
    | SEQUENCES
    | SERIALIZABLE
    | SERVER
    | SESSION
    | SESSION_USER
    | SET
    | SETOF
    | SETS
    | SHARE
    | SHOW
    | SIMILAR
    | SIMPLE
    | SKIP_P
    | SMALLINT
    | SNAPSHOT
    | SOME
    | SOURCE
    | SQL_P
    | STABLE
    | STANDALONE_P
    | START
    | STATEMENT
    | STATISTICS
    | STDIN
    | STDOUT
    | STORAGE
    | STORED
    | STRICT_P
    | STRING_P
    | STRIP_P
    | SUBSCRIPTION
    | SUBSTRING
    | SUPPORT
    | SYMMETRIC
    | SYSID
    | SYSTEM_P
    | SYSTEM_USER
    | TABLE
    | TABLES
    | TABLESAMPLE
    | TABLESPACE
    | TARGET
    | TEMP
    | TEMPLATE
    | TEMPORARY
    | TEXT_P
    | THEN
    | TIES
    | TIME
    | TIMESTAMP
    | TRAILING
    | TRANSACTION
    | TRANSFORM
    | TREAT
    | TRIGGER
    | TRIM
    | TRUE_P
    | TRUNCATE
    | TRUSTED
    | TYPE_P
    | TYPES_P
    | UESCAPE
    | UNBOUNDED
    | UNCOMMITTED
    | UNCONDITIONAL
    | UNENCRYPTED
    | UNIQUE
    | UNKNOWN
    | UNLISTEN
    | UNLOGGED
    | UNTIL
    | UPDATE
    | USER
    | USING
    | VACUUM
    | VALID
    | VALIDATE
    | VALIDATOR
    | VALUE_P
    | VALUES
    | VARCHAR
    | VARIADIC
    | VERBOSE
    | VERSION_P
    | VIEW
    | VIEWS
    | VOLATILE
    | WHEN
    | WHITESPACE_P
    | WORK
    | WRAPPER
    | WRITE
    | XML_P
    | XMLATTRIBUTES
    | XMLCONCAT
    | XMLELEMENT
    | XMLEXISTS
    | XMLFOREST
    | XMLNAMESPACES
    | XMLPARSE
    | XMLPI
    | XMLROOT
    | XMLSERIALIZE
    | XMLTABLE
    | YES_P
    | ZONE
    ;

identifier
    : Identifier ( UESCAPE string_ )?
    | QuotedIdentifier
    | UnicodeQuotedIdentifier
    | PLSQLVARIABLENAME
    ;
