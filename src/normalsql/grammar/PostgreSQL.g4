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
  : ( dml | ddl )? ( SEMI ( dml | ddl )? )* EOF
  ;

dml
    : select
    | delete
    | update
    | insert
    | 'MERGE' 'INTO'? qname tableAlias? 'USING' ( '(' select ')' | qname ) tableAlias? 'ON' term
      ( merge_insert_clause merge_update_clause?
      | merge_update_clause merge_insert_clause?
      )
      ( 'WHEN' 'MATCHED' 'THEN'? 'DELETE' )?
    ;

ddl
    : 'ALTER' 'EVENT' 'TRIGGER' id ( 'ENABLE' fireWhen? | 'DISABLE' )
    | 'ALTER' 'COLLATION' qname 'REFRESH' 'VERSION'
    | 'ALTER' 'DATABASE' id ( 'WITH'? databaseItem* | 'SET' 'TABLESPACE' id )
    | 'ALTER' 'DATABASE' id setresetclause
    | 'ALTER' 'DEFAULT' 'PRIVILEGES'
      ( 'IN' 'SCHEMA' ids | 'FOR' 'ROLE' ids | 'FOR' 'USER' ids )*
      ( 'GRANT' privileges 'ON' defacl_privilege_target 'TO' grantee ( ',' grantee )* withGrantOption_?
      | 'REVOKE' ( 'GRANT' 'OPTION' 'FOR' )? privileges 'ON' defacl_privilege_target 'FROM' grantee ( ',' grantee )* cascade?
      )

    | 'ALTER' 'DOMAIN' qname
      ( ( 'SET' 'DEFAULT' term | 'DROP' 'DEFAULT' )
      | 'DROP' 'NOT' 'NULL'
      | 'SET' 'NOT' 'NULL'
      | 'ADD' tableconstraint
      | 'DROP' 'CONSTRAINT' exists? id cascade?
      | 'VALIDATE' 'CONSTRAINT' id
      )
    | 'ALTER' 'TYPE' qname 'ADD' 'VALUE' notExists? string ( ( 'BEFORE' | 'AFTER' ) string )?
    | 'ALTER' 'TYPE' qname 'RENAME' 'VALUE' string 'TO' string

    | 'ALTER' 'EXTENSION' id 'UPDATE' ( 'TO' name )*

    | 'ALTER' 'EXTENSION' id ( 'ADD' | 'DROP' )
      ( object_type_name id
      | object_type_any_name qname
      | 'AGGREGATE' aggregateSignature
      | 'CAST' '(' type 'AS' type ')'
      | 'DOMAIN' type
      | fpr functionSig
      | 'OPERATOR' operatorSignature
      | 'OPERATOR' ( 'CLASS' | 'FAMILY' )? qname usingID
      | 'TRANSFORM' 'FOR' type 'LANGUAGE' id
      | 'TYPE' type
      )

    | 'ALTER' fdw_ id ( genericOptions | handler+ )
    | 'ALTER' 'SERVER' id ( genericOptions | version genericOptions? )
    | 'ALTER' fpr functionSig funcOptions+ 'RESTRICT'?
    | 'ALTER' 'GROUP' id ( 'ADD' | 'DROP' ) 'USER' ids

    | 'ALTER' ( ( 'INDEX'  |  materialized_  | 'TRIGGER' id 'ON' ) qname  | fpr functionSig ) 'NO'? 'DEPENDS' 'ON' 'EXTENSION' id

    | 'ALTER'
      ( 'AGGREGATE' aggregateSignature
      | fpr functionSig
      | 'OPERATOR' operatorSignature
      | 'OPERATOR' ( 'CLASS' | 'FAMILY' )? qname usingID
      | 'COLLATION' qname
      | 'CONVERSION' qname
      | 'DOMAIN' qname
      | 'EXTENSION' id
      | 'STATISTICS' qname
      | textSearchConfig qname
      | 'TYPE' qname
      | ( 'SEQUENCE' | 'VIEW' | materialized_ ) exists? qname
      | 'FOREIGN'? 'TABLE' exists? descendants
      )
      'SET' 'SCHEMA' id

    | 'ALTER'
      ( 'AGGREGATE' aggregateSignature
      | fpr functionSig
      | 'OPERATOR' operatorSignature
      | 'OPERATOR' ( 'CLASS' | 'FAMILY' ) qname usingID
      | 'COLLATION' qname
      | 'CONVERSION' qname
      | 'DATABASE' id
      | 'DOMAIN' qname
      | 'EVENT' 'TRIGGER' id
      | fdw_ id
      | 'LARGE' 'OBJECT' number
      | 'PROCEDURAL'? 'LANGUAGE' id
      | 'PUBLICATION' id
      | 'SCHEMA' id
      | 'SERVER' id
      | 'STATISTICS' qname
      | 'TABLESPACE' id
      | textSearchConfig qname
//      | 'TEXT' 'SEARCH' 'CONFIGURATION' qname
//      | 'TEXT' 'SEARCH' 'DICTIONARY' qname
      | 'TYPE' qname
      ) 'OWNER' 'TO' id

    | 'ALTER' 'OPERATOR' operatorSignature 'SET' '(' setDuck ( ',' setDuck )* ')'
    | 'ALTER' 'TYPE' qname 'SET' '(' setDuck ( ',' setDuck )* ')'
    | 'ALTER' 'POLICY' id 'ON' qname ('TO' ids)? rowsecurityoptionalexpr? rowsecurityoptionalwithcheck?
    | 'ALTER' 'SEQUENCE' exists? qname seqoptelem+
    | 'ALTER' 'SYSTEM' ( 'SET' | 'RESET' ) qname ( 'TO' | '=' ) ( 'DEFAULT' | value ( ',' value )* )
    | 'ALTER' 'TABLE' exists? descendants ( alter_table_cmds | ( 'ATTACH' 'PARTITION' qname forValues | 'DETACH' 'PARTITION' qname ) )
    | 'ALTER' 'INDEX' exists? qname ( alter_table_cmds | 'ATTACH' 'PARTITION' qname )

    | 'ALTER' 'SEQUENCE' exists? qname alter_table_cmds
    | 'ALTER' 'VIEW' exists? qname alter_table_cmds
    | 'ALTER' materialized_ exists? qname alter_table_cmds
    | 'ALTER' 'FOREIGN' 'TABLE' exists? descendants alter_table_cmds
    | 'ALTER' 'TYPE' qname alter_type_cmd ( ',' alter_type_cmd )*

    | 'ALTER' ( 'INDEX' | 'TABLE' | materialized_ ) 'ALL' 'IN' 'TABLESPACE' id ( 'OWNED' 'BY' ids )? 'SET' 'TABLESPACE' id 'NOWAIT'?
    | 'ALTER' 'TABLESPACE' id ( 'SET' | 'RESET' ) definition
    | 'ALTER' 'PUBLICATION' id 'SET' definition
    | 'ALTER' 'PUBLICATION' id ( 'ADD' | 'SET' | 'DROP' ) 'TABLE' descendants ( ',' descendants )*
    | 'ALTER' ( 'ROLE' | 'USER' ) 'ALL'? id ( 'IN' 'DATABASE' id )? setresetclause
    | 'ALTER' ( 'ROLE' | 'USER' ) id 'WITH'? // alteroptroleelem*

    | 'ALTER' 'SUBSCRIPTION'
      ( id 'SET' definition
      | 'CONNECTION' string
      | 'REFRESH' 'PUBLICATION' withDef?
      | 'SET' 'PUBLICATION' ids withDef?
      | 'ENABLE'
      | 'DISABLE'
      | ( 'OWNER' | 'RENAME' ) 'TO' id
      )

    | 'ALTER' 'STATISTICS' exists? qname 'SET' 'STATISTICS' signedDecimal
    | 'ALTER' 'TEXT' 'SEARCH' 'CONFIGURATION' qname
      ( 'ADD' 'MAPPING' 'FOR' ids 'WITH' qnames
      | 'ALTER' 'MAPPING' ( 'FOR' ids )? ( 'REPLACE' qname )? 'WITH' qnames
      | 'DROP' 'MAPPING' exists? 'FOR' ids
      )
    | 'ALTER' 'TEXT' 'SEARCH' 'DICTIONARY' qname definition
    | 'ALTER' 'USER' 'MAPPING' 'FOR' id 'SERVER' id genericOptions

    | 'ALTER'
      ( 'AGGREGATE' aggregateSignature
      | 'COLLATION' qname
      | 'CONVERSION' qname
      | 'DATABASE' id
      | fdw_ id
      | 'GROUP' id
      | 'PROCEDURAL'? 'LANGUAGE' id
      | 'OPERATOR' ( 'CLASS' | 'OPERATOR' )? qname usingID
      | 'PUBLICATION' id
      | fpr functionSig
      | 'SCHEMA' id
      | 'SERVER' id
      | 'SEQUENCE' exists? qname
      | 'TABLESPACE' id
      | 'INDEX' exists? qname
      | 'EVENT' 'TRIGGER' id
      | 'ROLE' id
      | 'USER' id
      | 'STATISTICS' qname
      | textSearchConfig qname
      | alterRenameOn
      )
      'RENAME' 'TO' id


    | 'ALTER' 'FOREIGN' 'TABLE' exists? ( descendants | qname | qname ) 'RENAME' ( 'COLUMN'? id )? 'TO' id

    | 'ALTER' ( 'DOMAIN' qname | 'TABLE' exists? descendants )? 'RENAME' ( 'CONSTRAINT' id )? 'TO' id
    | 'ALTER' 'TYPE' qname 'RENAME' ('ATTRIBUTE' id)? 'TO' id cascade?

    | 'ALTER' 'OPERATOR' 'FAMILY' qname usingID ( 'ADD' opAddItem ( ',' opAddItem )* | 'DROP' opDropItem ( ',' opDropItem )*)


    | 'CALL' genericFunction
    | 'CHECKPOINT'
    | 'CLOSE' ( id | 'ALL' )
    | 'CLUSTER' 'VERBOSE'? ( qname usingID? | id 'ON' qname )?

    | 'SET' 'CONSTRAINTS' ( 'ALL' | qnames ) ( 'DEFERRED' | 'IMMEDIATE' )

    | 'COPY' tableRef 'FROM' 'PROGRAM'? name copyWithOptions? where?
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
  | 'COPY' 'BINARY'? qname ( 'FROM' | 'TO' ) name ( 'USING'? 'DELIMITERS' string )? ( 'WITH' 'NULL' 'AS' string )?

    | 'CREATE' 'ACCESS' 'METHOD' id 'TYPE' ('INDEX' | 'TABLE') 'HANDLER' qname
    | 'CREATE' scope? 'TABLE' notExists? tableRef fixme 'AS' ( select | executePrepared ) withData?
    | 'CREATE' 'ASSERTION' qname 'CHECK' '(' term ')' timing*
    | 'CREATE' 'CAST' '(' type 'AS' type ')' ( 'WITH' 'FUNCTION' functionSig | 'WITHOUT' 'FUNCTION' | 'WITH' 'INOUT' ) ( 'AS' ( 'IMPLICIT' |  'ASSIGNMENT' ) )?
    | 'CREATE' 'DEFAULT'? 'CONVERSION' qname 'FOR' string 'TO' string 'FROM' qname
    | 'CREATE' 'DOMAIN' qname 'AS'? type colconstraint*
    | 'CREATE' 'EXTENSION' notExists? id 'WITH'? ( 'SCHEMA' id | 'VERSION' name | 'FROM' name | 'CASCADE' )*
    | 'CREATE' fdw_ id ( genericOptions | handler+ )?
    | 'CREATE' 'SERVER' notExists? id ( 'TYPE' string )? version? fdw_ id genericOptions?
    | 'CREATE' 'FOREIGN' 'TABLE' notExists? qname
      ( '(' ( column ( ',' column )* )? ')' inherit?
      | 'PARTITION' 'OF' parentTable forValues
      )
      'SERVER' id genericOptions?

    | 'CREATE' orReplace_? ( 'FUNCTION' | 'PROCEDURE' ) qname '(' ( paramDef ( ',' paramDef )* )? ')'
      ( 'RETURNS' ( type | 'TABLE' '(' id type ( ',' id type )* ')' ) )?
      ( 'AS' string ( ',' string )?
      | 'LANGUAGE' name
      | 'TRANSFORM' forType ( ',' forType )*
      | 'WINDOW'
      | funcOptions
      )+

    | 'CREATE' 'UNLOGGED'? materialized_ notExists? tableRef usingID? withDef? tablespaceID? 'AS' select withData?
    | 'CREATE' 'OPERATOR' 'CLASS' qname 'DEFAULT'? forType usingID ( 'FAMILY' qname )? 'AS' opAddItem ( ',' opAddItem )*
    | 'CREATE' 'OPERATOR' 'FAMILY' qname usingID
    | 'CREATE' 'PUBLICATION' id ('FOR' 'TABLE' descendants ( ',' descendants )* | 'FOR' 'ALL' 'TABLES')? withDef?


    | 'CREATE' 'POLICY' id 'ON' qname ('AS' id)? rowsecuritydefaultforcmd? ('TO' ids)? rowsecurityoptionalexpr? rowsecurityoptionalwithcheck?
    | 'CREATE' orReplace_? 'TRUSTED'? 'PROCEDURAL'? 'LANGUAGE' id ( 'HANDLER' qname ('INLINE' qname)? ('VALIDATOR' qname | 'NO' 'VALIDATOR')? )?
    | 'CREATE' 'SCHEMA' notExists? id? ( 'AUTHORIZATION' id )?
      ( createTable
      | createIndex
      | createSequence
      | createTrigger
      | 'GRANT' privileges 'ON' privilege_target 'TO' grantee ( ',' grantee )* withGrantOption_?
      | createView
      )*
    | createSequence
    | createTable
    | 'CREATE' 'SUBSCRIPTION' id 'CONNECTION' string 'PUBLICATION' ids withDef?
    | 'CREATE' 'STATISTICS' notExists? tableRef 'ON' terms ( 'FROM' tables )?
    | 'CREATE' 'TABLESPACE' id ( 'OWNER' id )? 'LOCATION' string withDef?
    | 'CREATE' orReplace_? 'TRANSFORM' 'FOR' type 'LANGUAGE' id '(' sqlWith ( ',' sqlWith )? ')'
    | createTrigger
    | 'CREATE' 'EVENT' 'TRIGGER' id 'ON' id ( 'WHEN' triggerWhen ( 'AND' triggerWhen )* )? executeFunction

    | 'CREATE' ( 'ROLE' | 'USER' | 'GROUP' ) id 'WITH'? createRoleItem*

    | 'CREATE' 'USER' 'MAPPING' notExists? 'FOR' id 'SERVER' id genericOptions?
    | 'CREATE' 'DATABASE' id 'WITH'? databaseItem*
    | 'CREATE' orReplace_? 'AGGREGATE' ( qname | aggregateSignature )? definition
    | 'CREATE' 'OPERATOR' operator definition

    | 'CREATE' 'TYPE' qname definition?
    | 'CREATE' 'TYPE' qname 'AS' '(' ( tablefuncelement ( ',' tablefuncelement )*)? ')'
    | 'CREATE' 'TYPE' qname 'AS' 'ENUM' '(' ( string ( ',' string )* )? ')'
    | 'CREATE' 'TYPE' qname 'AS' 'RANGE' definition
    | 'CREATE' textSearchConfig qname definition
    | createView

    | 'CREATE' 'COLLATION' notExists? qname ( definition | 'FROM' qname )?
    | 'CREATE' orReplace_? 'RULE' id 'AS' 'ON' ( 'SELECT' | 'UPDATE' | 'DELETE' | 'INSERT' ) 'TO' qname where? 'DO' ( 'INSTEAD' | 'ALSO' )? ( 'NOTHING' | actionable | '(' actionable? ( SEMI actionable? )* ')' )


    | 'DEALLOCATE' 'PREPARE'? ( id |  'ALL' )
    | declareCursor
    | 'DISCARD' ( 'ALL' | 'TEMP' | 'TEMPORARY' | 'PLANS' | 'SEQUENCES' )
    | 'DO' ( 'LANGUAGE'? name )+

    | 'DROP' 'AGGREGATE' exists? aggregateSignature ( ',' aggregateSignature )* cascade?
    | 'DROP' fpr exists? functionSig ( ',' functionSig )* cascade?
    | 'DROP' 'OPERATOR' exists? operatorSignature ( ',' operatorSignature )* cascade?

    | 'DROP' 'CAST' exists? '(' type 'AS' type ')' cascade?
    | 'DROP' 'OPERATOR' ( 'CLASS' | 'FAMILY' ) exists? qname usingID cascade?
    | 'DROP' ( 'POLICY' | 'RULE' | 'TRIGGER' ) exists? id 'ON' qname cascade?
    | 'DROP' ( 'TYPE' | 'DOMAIN' ) exists? type ( ',' type )* cascade?
    | 'DROP' 'SUBSCRIPTION' exists? id cascade?
    | 'DROP' 'TRANSFORM' exists? 'FOR' type 'LANGUAGE' id cascade?

    | 'DROP' 'OWNED' 'BY' ids cascade?
    | 'DROP' object_type_any_name exists? qnames cascade?
    | 'DROP' drop_type_name exists? ids cascade?
    | 'DROP' 'INDEX' 'CONCURRENTLY' exists? qnames cascade?

    | 'DROP' ( 'ROLE' | 'USER' | 'GROUP' ) exists? ids
    | 'DROP' 'TABLESPACE' exists? id
    | 'DROP' 'USER' 'MAPPING' exists? 'FOR' id 'SERVER' id

    | 'DROP' 'DATABASE' exists? id ( 'WITH'? '(' 'FORCE' ( ',' 'FORCE' )* ')' )?


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

    | 'GRANT' privileges 'ON' privilege_target 'TO' grantee ( ',' grantee )* withGrantOption_?
    | 'GRANT' privilege ( ',' privilege )* 'TO' ids ( 'WITH' 'ADMIN' 'OPTION' )? grantedBy?
    | 'IMPORT' 'FOREIGN' 'SCHEMA' id  ( ( 'LIMIT' 'TO' | 'EXCEPT' )? '(' descendants ( ',' descendants )* ')' )? 'FROM' 'SERVER' id 'INTO' id genericOptions?
    | createIndex

    | 'LISTEN' id
    | refreshMaterized
    | 'LOAD' string
    | 'LOCK' 'TABLE'? descendants ( ',' descendants )* ( 'IN' lockType 'MODE' )? 'NOWAIT'?
    | 'NOTIFY' id ( ',' string )?
    | 'PREPARE' id ( '(' type ( ',' type )* ')' )? 'AS' query
    | 'REASSIGN' 'OWNED' 'BY' ids 'TO' id
    | reindex

    | 'REVOKE' ( 'GRANT' 'OPTION' 'FOR' )? privileges 'ON' privilege_target 'FROM' grantee ( ',' grantee )* cascade?
    | 'REVOKE' ( 'ADMIN' 'OPTION' 'FOR' )? privilege ( ',' privilege )* 'FROM' ids grantedBy? cascade?

    | 'SECURITY' 'LABEL' ( 'FOR' name )? 'ON'
      ( object_type_any_name qname
      | object_type_name id
      | 'AGGREGATE' aggregateSignature
      | 'COLUMN' qname
      | 'DOMAIN' type
      | 'LARGE' 'OBJECT' number
      | 'TYPE' type
      | fpr functionSig
      )
      'IS' ( string | 'NULL' )

    | 'COMMENT' 'ON'
      ( object_type_any_name qname
      | object_type_name id
      | 'AGGREGATE' aggregateSignature
      | 'TYPE' type
      | 'CAST' '(' type 'AS' type ')'
      | 'COLUMN' qname
      | 'CONSTRAINT' id 'ON' 'DOMAIN'? qname?
      | 'DOMAIN' type
      | 'LARGE' 'OBJECT' number
      | 'OPERATOR' operatorSignature
      | 'OPERATOR' ( 'CLASS' | 'FAMILY' ) qname usingID
      | ( 'POLICY' | 'RULE' | 'TRIGGER' ) id 'ON' qname
      | fpr functionSig
      | 'TRANSFORM' 'FOR' type 'LANGUAGE' id
      )
      'IS' ( string | 'NULL' )



    | ( 'ABORT' | 'COMMIT' | 'END' | 'ROLLBACK' ) transaction? ( 'AND' 'NO'? 'CHAIN' )?
    | ( 'BEGIN' transaction? | 'START' 'TRANSACTION' ) ( transactionMode ( ',' transactionMode )* )?
    | 'RELEASE'? 'SAVEPOINT' id
    | 'RELEASE' id
    | 'ROLLBACK' transaction? 'TO' 'SAVEPOINT'? id
    | 'PREPARE' 'TRANSACTION' string
    | 'COMMIT' 'PREPARED' string
    | 'ROLLBACK' 'PREPARED' string

    | 'TRUNCATE' 'TABLE'? descendants ( ',' descendants )* (( 'CONTINUE' | 'RESTART' ) 'IDENTITY' )? cascade?
    | 'UNLISTEN' ( id | '*' )
    | 'VACUUM' 'FULL'? 'FREEZE'? 'VERBOSE'? ANALYZE? (tableRef ( ',' tableRef )* )?
    | 'VACUUM' '(' optionElems ')' ( tableRef ( ',' tableRef )* )?
    | ANALYZE ( 'VERBOSE' | '(' optionElems ')' )? ( tableRef ( ',' tableRef )* )?

    | resetVariable
    | 'SET' ( 'LOCAL' | 'SESSION' )? set_rest
    ;


      alterRenameOn
          : ( 'POLICY' exists? | 'RULE' | 'TRIGGER' ) id 'ON' qname ;


materialized_
    : 'MATERIALIZED' 'VIEW' ;

with
  : 'WITH' 'RECURSIVE'? cte ( ',' cte )* ;

cte
  : id columns? 'AS' ( 'NOT'? 'MATERIALIZED' )? '(' query ')' ;

select
    : with?
      selectCore ( ( 'UNION' | 'EXCEPT' | 'INTERSECT' ) unique_? selectCore )*
      orderBy? ( limit | fetch | offset | locking )*
    ;

    selectCore
        : 'SELECT' uniqueOn? ( item ( ',' item )* ','? )?
          ( 'INTO' ( 'TEMPORARY' | 'TEMP' | 'UNLOGGED' )? 'TABLE'?  qname )?
          ( 'FROM' tables )? where? groupBy? having?
          ( 'WINDOW' window ( ',' window )* )?
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
          : id 'AS' windowDef ;
/*
//        windowDef
//          : '(' id? ( 'PARTITION' 'BY' term ( ',' term )* )? orderBy?
//            frame_clause_? ')' ;
//
//        frame_clause_
//          : ( 'RANGE' | 'ROWS' | 'GROUPS' )
//            ( frame_bound | 'BETWEEN' frame_bound 'AND' frame_bound )
//            ( 'EXCLUDE' ( 'CURRENT' 'ROW' | 'GROUP' | 'TIES' | 'NO' 'OTHERS' ))?
//          ;
//
//        frame_bound
//            : 'UNBOUNDED' ( 'PRECEDING' | 'FOLLOWING' )
//            | 'CURRENT' 'ROW'
//            | term ( 'PRECEDING' | 'FOLLOWING' )
//            ;
*/
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
            : 'VALUES' terms ;

    insert
        : with? 'INSERT'
          'INTO' qname alias?

          ( columns? ( 'OVERRIDING' ('USER' | 'SYSTEM') 'VALUE' )? select
    //      ( ( '(' qnames ')' )? ( 'OVERRIDING' ('USER' | 'SYSTEM') 'VALUE' )? select
          | 'DEFAULT' 'VALUES'
          )

          ( 'ON' 'CONFLICT'
            ( '(' indexItem ( ',' indexItem )* ')' where? | 'ON' 'CONSTRAINT' id )?
            'DO' ( 'UPDATE' 'SET' setter ( ',' setter )* where? | 'NOTHING' )
          )?
          returning?
        ;

createRoleItem
    : ( 'ENCRYPTED' | 'UNENCRYPTED' )? 'PASSWORD' string
    | 'INHERIT'
    | 'CONNECTION' 'LIMIT' signedDecimal
    | 'VALID' 'UNTIL' string
    | 'USER' ids
    | id
    | 'SYSID' integer
    | 'ADMIN' ids
    | 'ROLE' ids
    | 'IN' ( 'ROLE' | 'GROUP' ) ids
    ;

set_rest
    : ( 'SESSION' 'CHARACTERISTICS' 'AS' )? 'TRANSACTION' transactionMode ( ','? transactionMode )*
    | set_rest_more
    ;


timezone_
    : 'TIME' 'ZONE' ;

set_rest_more
  : qname ( 'TO' | '=' ) ( 'DEFAULT' | value ( ',' value )* )
  | id 'FROM' 'CURRENT'
  | timezone_ ( name | interval | number )
  | 'CATALOG' string
  | 'SCHEMA' string
  | 'NAMES' ( string | 'DEFAULT' )?
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
//    | 'ALL'
    | timezone_
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
    | 'ADD' 'COLUMN'? notExists? columnDef
    | 'ALTER' 'COLUMN'? id ('SET' 'DEFAULT' term | 'DROP' 'DEFAULT' )
    | 'ALTER' 'COLUMN'? id 'DROP' 'NOT' 'NULL'
    | 'ALTER' 'COLUMN'? id 'SET' 'NOT' 'NULL'
    | 'ALTER' 'COLUMN'? id 'DROP' 'EXPRESSION' exists?
    | 'ALTER' 'COLUMN'? id 'SET' 'STATISTICS' signedDecimal
    | 'ALTER' 'COLUMN'? integer 'SET' 'STATISTICS' signedDecimal
    | 'ALTER' 'COLUMN'? id 'SET' definition
    | 'ALTER' 'COLUMN'? id 'RESET' definition
    | 'ALTER' 'COLUMN'? id 'SET' 'STORAGE' id
    | 'ALTER' 'COLUMN'? id 'ADD' generatedWhen 'AS' 'IDENTITY' ( '(' seqoptelem+ ')' )?
    | 'ALTER' 'COLUMN'? id alter_identity_column_option+
    | 'ALTER' 'COLUMN'? id 'DROP' 'IDENTITY' exists?
    | 'ALTER' 'COLUMN'? id ( 'SET' 'DATA' )? 'TYPE' type collate? ( 'USING' term )?
    | 'ALTER' 'COLUMN'? id genericOptions
    | 'ALTER' 'CONSTRAINT' id timing*
    | 'VALIDATE' 'CONSTRAINT' id
    | 'DROP' 'COLUMN'? exists? id cascade?
    | 'DROP' 'CONSTRAINT' exists? id cascade?
    | 'CLUSTER' 'ON' id
    | ( 'DISABLE' | 'ENABLE' ) fireWhen? 'TRIGGER' id
    | ( 'DISABLE' | 'ENABLE' ) 'TRIGGER' ( 'ALL' | 'USER' )
    | 'ENABLE' fireWhen? 'RULE' id
    | 'DISABLE' 'RULE' id
    | 'NO'? 'INHERIT' qname
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

fireWhen
    : 'ALWAYS' | 'REPLICA' ;

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
  | 'DROP' 'ATTRIBUTE' exists? id cascade?
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
  : 'CREATE' scope? 'TABLE' notExists? qname
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
  : 'REFRESH' materialized_ 'CONCURRENTLY'? qname withData? ;

createSequence
  : 'CREATE' scope? 'SEQUENCE' notExists? qname seqoptelem* ;

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

exists
    : 'IF' 'EXISTS' ;

notExists
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

triggerWhen
    : id 'IN' '(' string ( ',' string )* ')'
    ;

definition
    : '(' def_elem ( ',' def_elem )* ')'
    ;

def_elem
    : qname ( '=' ( duck | 'NONE' ) )?
    ;


opAddItem
    : 'OPERATOR' integer operatorSignature? ( 'FOR' ( 'SEARCH' |  'ORDER' 'BY' qname ) )? 'RECHECK'?
    | 'FUNCTION' integer functionSig
    | 'FUNCTION' integer '(' type ( ',' type )* ')' functionSig
    | 'STORAGE' type
    ;

opDropItem
    : ( 'OPERATOR' | 'FUNCTION' ) integer '(' type ( ',' type )* ')'
    ;

object_type_any_name
    : 'COLLATION'
    | 'CONVERSION'
    | 'FOREIGN' 'TABLE'
    | 'INDEX'
    | materialized_
    | 'SEQUENCE'
    | 'STATISTICS'
    | 'TABLE'
    | textSearchConfig
    | 'VIEW'
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
    | fdw_
    | 'PROCEDURAL'? 'LANGUAGE'
    | 'PUBLICATION'
    | 'SCHEMA'
    | 'SERVER'
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
    | 'DOMAIN' qnames
    |  fdw_ ids
    | 'FOREIGN' 'SERVER' ids
    | fpr functionSig ( ',' functionSig )*
    | 'DATABASE' ids
    | 'LANGUAGE' ids
    | 'LARGE' 'OBJECT' number ( ',' number )*
    | 'SCHEMA' ids
    | 'TABLESPACE' ids
    | 'TYPE' qnames
    | 'ALL' ( 'TABLES' | 'SEQUENCES' | 'FUNCTIONS' | 'PROCEDURES' | 'ROUTINES' ) 'IN' 'SCHEMA' ids
    ;

defacl_privilege_target
    : 'TABLES'
    | 'FUNCTIONS'
    | 'ROUTINES'
    | 'SEQUENCES'
    | 'TYPES'
    | 'SCHEMAS'
    ;


fdw_
    : 'FOREIGN' 'DATA' 'WRAPPER' ;

withGrantOption_
  : 'WITH' 'GRANT' 'OPTION' ;

grantee
  : 'GROUP'? id ;

grantedBy
  : 'GRANTED' 'BY' id ;

createIndex
    : 'CREATE' 'UNIQUE'? 'INDEX' 'CONCURRENTLY'? ( notExists? id )?
      'ON' descendants usingID? '(' indexItem ( ',' indexItem )* ')'
      ( 'INCLUDE' '(' indexItem ( ',' indexItem )* ')' )? withDef? tablespaceID? where?
    ;

indexItem
      : ( id | function | '(' term ')' )
        collate? ( qname definition? )? direction_? nullsOrder?
      ;

direction_
    : 'ASC' | 'DESC' ;

nullsOrder
    : 'NULLS' ( 'FIRST' |  'LAST' ) ;

orReplace_
  : 'OR' 'REPLACE' ;

paramDef
  : id? argMode? id? type ( ( 'DEFAULT' | '=' ) term )? ;

// TODO create a smaller arg just for functions?
functionSig
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
  | 'NOT'? 'LEAKPROOF'
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

fpr
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

sqlWith
    : ( 'TO' | 'FROM' ) 'SQL' 'WITH' 'FUNCTION' functionSig ;

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

textSearchConfig
    : 'TEXT' 'SEARCH' ( 'PARSER' | 'DICTIONARY' | 'TEMPLATE' | 'CONFIGURATION' ) ;

setDuck
    : id '=' duck
    ;

duck
    : type | keyword | operator | number | string ;

actionable
    : select
    | insert
    | update
    | delete
    | 'NOTIFY' id ( ',' string )?
    ;

transaction
    : 'WORK'
    | 'TRANSACTION'
    ;

createView
    : 'CREATE' orReplace_? scope?
      ( 'VIEW' tableRef withDef? | 'RECURSIVE' 'VIEW' qname columns withDef?
      ) 'AS' select ( 'WITH' ( 'CASCADED' | 'LOCAL' )? 'CHECK' 'OPTION' )?
    ;

databaseItem
    : ( id | 'CONNECTION' 'LIMIT' | 'ENCODING' | 'LOCATION' | 'OWNER' | 'TABLESPACE' | 'TEMPLATE' )
//      '='? ( signedDecimal | name | 'DEFAULT' )
      '='? ( signedDecimal | name )
    ;

tableRef
  : qname columns? ;

explainable
    : select
    | insert
    | update
    | delete
    | declareCursor
    | 'CREATE' scope? 'TABLE' notExists? tableRef fixme 'AS' ( select | executePrepared ) withData?
    | 'CREATE' 'UNLOGGED'? materialized_ notExists? tableRef usingID? withDef? tablespaceID? 'AS' select withData?
    | refreshMaterized
    | executePrepared
    ;

optionElems
    : option_elem ( ',' option_elem )*
    ;

option_elem
    : id ( name | number )?
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

uniqueOn
  : 'ALL' | 'DISTINCT' ( 'ON' '(' terms ')' )? ;

orderBy
  : 'ORDER' 'BY' orderTerm ( ',' orderTerm  )* ;

orderTerm
  : term ( 'USING' operator | direction_? ) nullsOrder? ;

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
  | ( 'TIMESTAMP' | 'TIME' ) ( '(' integer ')' )? ( withers
timezone_ )?
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
  | term 'AT' ( timezone_ | 'LOCAL' ) term #TermIgnore
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
  | function ( 'WITHIN' 'GROUP' '(' orderBy ')' )? ( 'FILTER' '(' where ')' )? ( 'OVER' ( windowDef | id ))?  #TermIgnore
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
      | unique_ args
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

unique_
  : 'ALL' | 'DISTINCT' ;

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
