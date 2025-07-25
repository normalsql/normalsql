/*
  Copyright 2025 Jason Osgood

  Refactored MySQL grammar to adopt NormalSQL's rules, idioms, and style.
  Goal is for misc dialects to all emit the same parse tree (given the
  same input). A work in progress, as grammars converge over time, trial &
  error, balancing tradeoffs (lex vs parse vs semantic validation).

  Based on the excellent prior works done by Mike Lischke, Ivan Kochurkin,
  Ivan Khudyashev. As well as MySQL's original sql_yacc.yy.

  Prior copyright notices below.

  I'm unsure what license is in effect, so haven't added my own. IIRC, ANTLR4's
  grammars-v4 project requires MIT.

  FYI, NormalSQL uses APL2. It seemed the default (for Java projects). Having no
  opinions or preferences, I'll switch licenses as needed. If I continue to use
  and maintain this fork of grammars-v4, I may adopt same license, just
  just to keep things more simple for everyone. Would be grateful to have someone,
  any one, tell me what'd best for everyone.

*/


/*
 * Copyright © 2025, Oracle and/or its affiliates
 */

/*
 * Merged in all changes up to mysql-trunk git revision [d2c9971] (24. January 2024).
 *
 * MySQL grammar for ANTLR 4.5+ with language features from MySQL 8.0 and up.
 * The server version in the generated parser can be switched at runtime, making it so possible
 * to switch the supported feature set dynamically.
 *
 * The coverage of the MySQL language should be 100%, but there might still be bugs or omissions.
 *
 * To use this grammar you will need a few support classes (which should be close to where you found this grammar).
 * These classes implement the target specific action code, so we don't clutter the grammar with that
 * and make it simpler to adjust it for other targets. See the demo/test project for further details.
 *
 * Written by Mike Lischke. Direct all bug reports, omissions etc. to mike.lischke@oracle.com.
 */

 /*
  MySQL (Positive Technologies) grammar
  The MIT License (MIT).
  Copyright (c) 2015-2017, Ivan Kochurkin (kvanttt@gmail.com), Positive Technologies.
  Copyright (c) 2017, Ivan Khudyashev (IHudyashov@ptsecurity.com)

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

/*
   Copyright (c) 2000, 2025, Oracle and/or its affiliates.

   This program is free software; you can redistribute it and/or modify
   it under the terms of the GNU General Public License, version 2.0,
   as published by the Free Software Foundation.

   This program is designed to work with certain software (including
   but not limited to OpenSSL) that is licensed under separate terms,
   as designated in a particular file or component or in included license
   documentation.  The authors of MySQL hereby grant you an additional
   permission to link the program and your derivative works with the
   separately licensed software that they have either included with
   the program or referenced in the documentation.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License, version 2.0, for more details.

   You should have received a copy of the GNU General Public License
   along with this program; if not, write to the Free Software
   Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301  USA
 */

/**

   I have no idea how to accomodate MySQL extensions.
   Note: update rule 'keyword' whenever a new
   keyword (token) is added (or removed).

   Have matched order of alts from sql_yacc.yy, as needed, because I'm getting
   cross-eyed doing side-by-side comparisons.

   I think it'd be cool to topo sort the parser's rules.

   Inline delimited lists to ease post-parsing. By shortening parse tree,
   this moves (some) complexity from app code to grammar.

      // Good
      values : 'VALUES' term ( ',' term )* ;

      // Bad
      values : 'VALUES' terms ;
      terms : terms ( ',' term )* ;


   Have inlined most statements for now. May re-refactor once grammar has settled down.


   Known problems:

    - Only handles UTF-8 encoding

       - Does not handle multibyte Unicode

       - Does not handle pre-Unicode code page stuff

       - Does not handle emojis

     - Incorrectly discerns some numbers vs identifiers (that begin w/ digit)

       - eg "SELECT 1ea10.1a20" yields an ID, FLOAT, and ID. Should just be ID.

*/

grammar MySQL;

options {
    caseInsensitive = true;
}

statements
    : ( dml | ddl )? ( ( ';' | '#end' ) ( dml | ddl )? )* EOF ;

dml
    : select
    | insert
    | replace
    | update
    | delete
    ;

with
    : 'WITH' 'RECURSIVE'? cte ( ',' cte )* ;

cte
    : name columns? 'AS' '(' select ')' ;

select
    : with?
      selectCore
      ( ( 'UNION' | 'EXCEPT' | 'INTERSECT' ) ( 'DISTINCT' | 'ALL' )? selectCore )*
      orderBy? limit? into? locking* into?
    ;

    into
        : 'INTO'
          ( 'OUTFILE' string namedCharset? fieldHandling? lineHandling?
          | 'DUMPFILE' string
          | qname ( ',' qname )*
          )
        ;

    selectCore
        : 'SELECT' modifier* item ( ',' item )* into?
          ( 'FROM' tables )? where? groupBy? having?
          ( 'WINDOW' window ( ',' window )* )?
          ( 'QUALIFY' term )?
        | values
        | '(' select ')'
        | 'TABLE' qname
        ;

        modifier
            : 'ALL'
            | 'DISTINCT'
            | 'DISTINCTROW'
            | 'HIGH_PRIORITY'
            | 'STRAIGHT_JOIN'
            | 'SQL_SMALL_RESULT'
            | 'SQL_BIG_RESULT'
            | 'SQL_BUFFER_RESULT'
            | 'SQL_CALC_FOUND_ROWS'
            | 'SQL_NO_CACHE'
            ;

        item
            : ( qname '.' )? '*'  # ItemAll
            | qname alias?  # ItemColumn
            | term alias?  # ItemTerm
            ;

        tables
            : tables ',' tables
            | tables ( ( 'INNER' | 'CROSS' )? 'JOIN' | 'STRAIGHT_JOIN' ) tables ( 'ON' term | 'USING' '(' name ( ',' name )* ')' )?
            | tables ( 'LEFT' | 'RIGHT' ) 'OUTER'? 'JOIN' tables ( 'ON' term | 'USING' '(' name ( ',' name )* ')' )
            | tables ( 'NATURAL' ( 'INNER' | ( 'LEFT' | 'RIGHT' ) 'OUTER'? )? 'JOIN' ) tables
            | 'LATERAL'? '(' select ')' tableAlias? columns?
            | qname partition? tableAlias? indexHint* ( 'TABLESAMPLE' ( 'SYSTEM' | 'BERNOULLI' ) '(' literal ')' )?
            | values tableAlias?
            | 'JSON_TABLE' '(' term ',' string jsonColumns ')' tableAlias?
            | qname '(' term ( ',' term )* ')' tableAlias?
            | '{' 'OJ' tables '}'
            | '(' tables ')'
            ;

            tableAlias
                : 'AS'? name ;

        where
            : 'WHERE' term ;

        groupBy
            : 'GROUP' 'BY' orderTerm ( ',' orderTerm )* ( 'WITH' 'ROLLUP' )?
            | 'GROUP' 'BY' ( 'ROLLUP' | 'CUBE' ) terms
            ;

        orderBy
            : 'ORDER' 'BY' orderTerm ( ',' orderTerm )* ;

        orderTerm
            : term direction_? ;

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


    alias
      : 'AS'? name ;


insert
    : 'INSERT' ( 'LOW_PRIORITY' | 'DELAYED' | 'HIGH_PRIORITY' )?
      'IGNORE'?
      'INTO'? qname partition?
      ( insertFromConstructor valuesAlias?
      | 'SET' setter ( ',' setter )* valuesAlias?
      | insertQueryExpression
      )
      ( 'ON' 'DUPLICATE' 'KEY' 'UPDATE' setter ( ',' setter )* )?
    ;

    insertFromConstructor
        : ( '(' ( wild ( ',' wild )* )? ')' )? ( 'VALUES' | 'VALUE' ) term  ( ',' term )* ;

    insertQueryExpression
        : with? selectCore orderBy? limit?
        | '(' select ')'
        | ( '(' ( wild ( ',' wild )* )? ')' )? select
        ;

// TODO fold rule replace into rule insert?
replace
    : 'REPLACE' ( 'LOW_PRIORITY' | 'DELAYED' )?
      'INTO'? qname partition?
      ( insertFromConstructor
      | 'SET' setter ( ',' setter )*
      | insertQueryExpression
      )?
    ;

update
    : with? 'UPDATE' 'LOW_PRIORITY'? 'IGNORE'? tableReferenceList
      'SET' setter ( ',' setter )*
      where?
      orderBy? limitCount?
    ;

delete
    : with? 'DELETE' 'LOW_PRIORITY'? 'QUICK'? 'IGNORE'?
      ( 'FROM' qname alias? partition? where? orderBy? limitCount?
      | wild ( ',' wild )* 'FROM' tableReferenceList where?
      | 'FROM' wild ( ',' wild )* 'USING' tableReferenceList where?
      )
    ;

    wild : qname ( '.' '*' )? ;

ddl
    : 'CREATE' database_ notExists? name databaseOption*
    | 'ALTER' database_ qname databaseOption+
    | 'DROP' database_ exists? qname

    | 'CREATE' definer? 'EVENT' notExists? qname 'ON' 'SCHEDULE' schedule ( 'ON' 'COMPLETION' 'NOT'? 'PRESERVE' )? ( 'ENABLE' | 'DISABLE' ( 'ON' 'REPLICA' )? )? comment? 'DO' compoundStatement
    | 'ALTER' definer? 'EVENT' qname ( 'ON' 'SCHEDULE' schedule )? ( 'ON' 'COMPLETION' 'NOT'? 'PRESERVE' )? ( 'RENAME' 'TO' qname )? ( 'ENABLE' | 'DISABLE' ( 'ON' 'REPLICA' )? )? comment? ( 'DO' compoundStatement )?
    | 'DROP' 'EVENT' exists? qname
    | 'CREATE' 'AGGREGATE'? 'FUNCTION' notExists? qname 'RETURNS' ( 'STRING' | integer_ | 'REAL' | decimal_ ) 'SONAME' string

    | 'CREATE' definer? 'FUNCTION' notExists? qname '(' ( functionParameter ( ',' functionParameter )* )? ')' 'RETURNS' dataType collate? characteristic* storedRoutineBody
    | 'ALTER' 'FUNCTION' qname characteristic*
    | 'DROP' 'FUNCTION' exists? qname

    | 'CREATE' definer? 'PROCEDURE' notExists? qname '(' ( procedureParameter ( ',' procedureParameter )* )? ')' characteristic* storedRoutineBody
    | 'ALTER' 'PROCEDURE' qname characteristic*
    | 'DROP' 'PROCEDURE' exists? qname

    | 'CREATE' ( 'UNIQUE' | 'FULLTEXT' | 'SPATIAL' )? 'INDEX' qname indexType? 'ON' qname '(' keyPart ( ',' keyPart )* ')' indexOption* commonIndexOption*

    | 'DROP' onlineOption? 'INDEX' qname 'ON' qname commonIndexOption*

    | 'CREATE' 'LOGFILE' 'GROUP' qname 'ADD' 'UNDOFILE' string ( logfileAlterOption ( ','? logfileAlterOption )* )?
    | 'ALTER' 'LOGFILE' 'GROUP' qname 'ADD' 'UNDOFILE' string ( logfileCreateOptions ( ','? logfileCreateOptions )* )?
    | 'DROP' 'LOGFILE' 'GROUP' qname ( logfileDropOption ( ','? logfileDropOption )* )?

    | 'CREATE' 'RESOURCE' 'GROUP' qname 'TYPE' equal? ( 'USER' | 'SYSTEM' ) resourceGroupVcpuList? resourceGroupPriority? enable_?
    | 'ALTER' 'RESOURCE' 'GROUP' qname resourceGroupVcpuList? resourceGroupPriority? enable_? 'FORCE'?
    | 'DROP' 'RESOURCE' 'GROUP' qname 'FORCE'?
    | 'SET' 'RESOURCE' 'GROUP' qname ( 'FOR' INTEGER ( ','? INTEGER )* )?

    | 'CREATE' 'ROLE' notExists? user ( ',' user )*
    | 'DROP' 'ROLE' exists? user ( ',' user )*

    | 'CREATE' 'SERVER' qname 'FOREIGN' 'DATA' 'WRAPPER' qname 'OPTIONS' '(' serverOption ( ',' serverOption )* ')'
    | 'ALTER' 'SERVER' qname 'OPTIONS' '(' serverOption ( ',' serverOption )* ')'
    | 'DROP' 'SERVER' exists? name

    | 'CREATE' 'TEMPORARY'? 'TABLE' notExists? qname
      ( '(' ( createDef  | tableConstraintDef ) ( ',' ( createDef  | tableConstraintDef ) )* ')' )?
      ( tableCreateOption ( ','? tableCreateOption )* )? partitionBy? ( ( 'REPLACE' | 'IGNORE' )? 'AS'? select )?
    | 'CREATE' 'TEMPORARY'? 'TABLE' notExists? qname ( 'LIKE' qname | '(' 'LIKE' qname ')' )
    | 'ALTER' onlineOption? 'TABLE' qname ( tableAlterOption ( ','? tableAlterOption )* )?
    | 'DROP' 'TEMPORARY'? table_ exists? qname ( ',' qname )* ( 'RESTRICT' | 'CASCADE' )?
    | 'RENAME' table_ qname 'TO' qname ( ',' qname 'TO' qname )*

    | 'CREATE' 'UNDO'? 'TABLESPACE' qname ( tablespaceOption ( ','? tablespaceOption )* )?
    | 'ALTER' 'UNDO'? 'TABLESPACE' qname tablespaceOption ( ','? tablespaceOption )*
    | 'DROP' 'UNDO'? 'TABLESPACE' qname ( tablespaceOption ( ','? tablespaceOption )* )?

    | 'CREATE' definer? 'TRIGGER' notExists? qname ( 'BEFORE' | 'AFTER' ) ( 'INSERT' | 'UPDATE' | 'DELETE' ) 'ON' qname 'FOR' 'EACH' 'ROW' ( ( 'FOLLOWS' | 'PRECEDES' ) qname )? compoundStatement
    | 'DROP' 'TRIGGER' exists? qname

    | 'CREATE' 'USER' notExists? createAuthOption ( ',' createAuthOption )*
      ( 'DEFAULT' roles )?
      require?
      resourceWith?
      passwordOption*
      ( comment | 'ATTRIBUTE' string )*

    | 'ALTER' 'USER' exists? user alterAuthOption? ( ',' user alterAuthOption? )*
      require?
      resourceWith?
      passwordOption*
      ( comment | 'ATTRIBUTE' string )*

    | 'ALTER' 'USER' exists? 'USER' '(' ')' alterAuthOption
    | 'ALTER' 'USER' exists? ( 'USER' '(' ')' | user ) ( INTEGER 'FACTOR' )?
    | 'ALTER' 'USER' exists? user 'DEFAULT' roles

    | 'DROP' 'USER' exists? user ( ',' user )*
    | 'RENAME' 'USER' user 'TO' user ( ',' user 'TO' user )*

    | 'CREATE' orReplace_? viewAlgorithm? definer? security? 'VIEW' notExists?  qname columns? 'AS' select viewCheckOption?
    | 'ALTER' viewAlgorithm? definer? security? 'VIEW' qname columns? 'AS' select viewCheckOption?
    | 'DROP' 'VIEW' exists? qname ( ',' qname )* ( 'RESTRICT' | 'CASCADE' )?

    | 'ALTER' 'INSTANCE'
        ( enable_ 'INNODB' 'REDO_LOG'
        | 'ROTATE' ( 'INNODB' | 'BINLOG' ) 'MASTER' 'KEY'
        | 'RELOAD' 'TLS'
            ( 'FOR' 'CHANNEL' ( 'mysql_main' | 'mysql_admin' ) )?
            ( 'NO' 'ROLLBACK' 'ON' 'ERROR' )?
        | 'RELOAD' 'KEYRING'
        )

    | 'CREATE' orReplace_? 'SPATIAL' 'REFERENCE' 'SYSTEM' notExists? INTEGER srsAttribute*
    | 'DROP' 'SPATIAL' 'REFERENCE' 'SYSTEM' exists? INTEGER
    | 'CALL' qname ( '(' ( term ( ',' term )* )? ')' )?
    | 'DO' item ( ',' item )*
    | 'HANDLER' qname 'OPEN' alias?
    | 'HANDLER' qname ( 'CLOSE' | 'READ' handlerReadOrScan where? limit? )
    | loadData
    | 'COMMIT' 'WORK'? ( 'AND' 'NO'? 'CHAIN' )? ( 'NO'? 'RELEASE' )?
    | 'SAVEPOINT' qname
    | 'ROLLBACK' 'WORK'? ( 'TO' 'SAVEPOINT'? name | ( 'AND' 'NO'? 'CHAIN' )? ( 'NO'? 'RELEASE' )? )
    | 'RELEASE' 'SAVEPOINT' qname
    | 'LOCK' table_ lockItem ( ',' lockItem )*
    | 'LOCK' 'INSTANCE' 'FOR' 'BACKUP'
    | 'UNLOCK' ( table_ | 'INSTANCE' )

    | 'XA' ( 'START' | 'BEGIN' ) xid ( 'JOIN' | 'RESUME' )?
    | 'XA' 'END' xid ( 'SUSPEND' ( 'FOR' 'MIGRATE' )?)?
    | 'XA' 'PREPARE' xid
    | 'XA' 'COMMIT' xid ( 'ONE' 'PHASE' )?
    | 'XA' 'ROLLBACK' xid
    | 'XA' 'RECOVER' ( 'CONVERT' 'XID' )?

    | 'PURGE' 'BINARY' 'LOGS' ( 'TO' string | 'BEFORE' term )
    | 'CHANGE' 'REPLICATION' 'SOURCE' 'TO' sourceDefinition ( ',' sourceDefinition )* channel?
    | 'RESET' resetOption ( ',' resetOption )*
    | 'RESET' 'PERSIST' ( exists? qname )?
    | 'CHANGE' 'REPLICATION' 'FILTER' filterDefinition ( ',' filterDefinition )* channel?
    | ( 'START' groupReplicationStartOptions? | 'STOP' ) 'GROUP_REPLICATION'
    | 'PREPARE' qname 'FROM' qname
    | 'EXECUTE' qname ( 'USING' qname ( ',' qname )* )?
    | ( 'DEALLOCATE' | 'DROP' ) 'PREPARE' name

    | 'CLONE' 'LOCAL' 'DATA' 'DIRECTORY' equal? string
    | 'CLONE' 'REMOTE' ( 'FOR' 'REPLICATION' )?
    | 'CLONE' 'INSTANCE' 'FROM' user ':' INTEGER 'IDENTIFIED' 'BY' name ( ssl | 'DATA' 'DIRECTORY' equal? string ssl? )?

    | grant
    | revoke
    | setRole
    | analyze
    | 'CHECK' table_ qname ( ',' qname )* checkOption*
    | 'CHECKSUM' 'TABLE' qname ( ',' qname )* ( 'QUICK' | 'EXTENDED' )?
    | 'OPTIMIZE' noLogging? table_ qname ( ',' qname )*
    | 'REPAIR' noLogging? table_ qname ( ',' qname )* repairType*
    | 'UNINSTALL' ( 'PLUGIN' name | 'COMPONENT' string ( ',' string )* )
    | 'INSTALL' 'PLUGIN' name 'SONAME' string
    | 'INSTALL' 'COMPONENT' string ( ',' string )* ( 'SET' setter ( ',' setter )* )?

    | 'SET' setter ( ',' setter )*
    | 'SET' 'NAMES' ( equal term | qname collate? | 'DEFAULT' | 'BINARY' )
    | 'SET' ( 'GLOBAL' | 'SESSION' )? 'TRANSACTION' transactionCharacteristics ( ',' transactionCharacteristics )*
    | 'SET' 'PASSWORD' ( 'FOR' user )? ( equal string | 'TO' 'RANDOM' ) replaceString? retainCurrentPassword?
    | 'SET' charset_ qname

    | 'TRUNCATE' 'TABLE'? qname
    | 'IMPORT' 'TABLE' 'FROM' string ( ',' string )*

    | 'SHOW' 'BINARY' 'LOG' 'STATUS'
    | 'SHOW' 'BINARY' 'LOGS'
    | 'SHOW' 'COUNT' '(' '*' ')' ( 'WARNINGS' | 'ERRORS' )

    | 'SHOW' 'CREATE' database_ notExists? qname
    | 'SHOW' 'CREATE' 'EVENT' qname
    | 'SHOW' 'CREATE' 'FUNCTION' qname
    | 'SHOW' 'CREATE' 'FUNCTION' 'CODE' qname
    | 'SHOW' 'CREATE' 'LIBRARY' qname
    | 'SHOW' 'CREATE' 'PROCEDURE' qname
    | 'SHOW' 'CREATE' 'PROCEDURE' 'CODE' qname
    | 'SHOW' 'CREATE' 'TABLE' qname
    | 'SHOW' 'CREATE' 'TRIGGER' qname
    | 'SHOW' 'CREATE' 'VIEW' qname

    | 'SHOW' 'CREATE' 'USER' user

    | 'SHOW' 'ENGINE' ( qname | 'ALL' ) ( 'LOGS' | 'MUTEX' | 'STATUS' )
    | 'SHOW' 'STORAGE'? 'ENGINES'

    | 'SHOW' charset_ like?
    | 'SHOW' 'COLLATION' like?
    | 'SHOW' 'DATABASES' like?
    | 'SHOW' 'LIBRARY' 'STATUS' like?
    | 'SHOW' 'FUNCTION' 'STATUS' like?
    | 'SHOW' 'PROCEDURE' 'STATUS' like?
    | 'SHOW' scope? 'STATUS' like?
    | 'SHOW' scope? 'VARIABLES' like?

    | 'SHOW' 'EVENTS' inDb? like?
    | 'SHOW' ( 'FULL' | 'EXTENDED' 'FULL'? )? ( 'FIELDS' | 'COLUMNS' ) inDb inDb? like?
    | 'SHOW' 'TABLE' 'STATUS' inDb? like?
    | 'SHOW' 'OPEN' 'TABLES' inDb? like?
    | 'SHOW' ( 'FULL' | 'EXTENDED' 'FULL'? )? 'TABLES' inDb? like?
    | 'SHOW' 'FULL'? 'TRIGGERS' inDb? like?
    | 'SHOW' 'EXTENDED'? ( 'KEYS' | 'INDEX' | 'INDEXES' ) inDb inDb? where?

    | 'SHOW' 'FUNCTION' 'CODE' qname
    | 'SHOW' 'PROCEDURE' 'CODE' qname

    | 'SHOW' 'GRANTS' ( 'FOR' user ( 'USING' user ( ',' user )* )? )?
    | 'SHOW' 'PARSE_TREE' ( dml | ddl )
    | 'SHOW' 'PLUGINS'
    | 'SHOW' 'PRIVILEGES'
    | 'SHOW' 'FULL'? 'PROCESSLIST'
    | 'SHOW' 'PROFILE' ( profileDef ( ',' profileDef )* )? ( 'FOR' 'QUERY' INTEGER )? limit?
    | 'SHOW' 'PROFILES'
    | 'SHOW' 'RELAYLOG' 'EVENTS' ( 'IN' string )? ( 'FROM' INTEGER )? limit? channel?
    | 'SHOW' 'REPLICA' 'STATUS' channel?
    | 'SHOW' 'REPLICAS'
    | 'SHOW' 'REPLICA' 'HOSTS'

    // TODO verify FROM & IN
    | 'SHOW' 'BINLOG' 'EVENTS' ( 'IN' string )? ( 'FROM' INTEGER )? limit?
    | 'SHOW' 'ERRORS' limit?
    | 'SHOW' 'WARNINGS' limit?


    | 'SHUTDOWN'
    | ( 'SIGNAL' signalCondition | 'RESIGNAL' signalCondition? ) ( 'SET' signalItem ( ',' signalItem )* )?
    | 'START' 'TRANSACTION' ( startTransactionMode ( ',' startTransactionMode )* )?
    | 'START' 'REPLICA' replicaThreadOptions? ( 'UNTIL' replicaUntil )? userOption? ( 'PASSWORD' '=' string )? defaultAuthOption? pluginDirOption? channel?
    | 'STOP' 'REPLICA' replicaThreadOptions? channel?

    | 'BINLOG' string
    | 'CACHE' 'INDEX' tableIndexList ( ',' tableIndexList )* 'IN' ( name | 'DEFAULT' )
    | 'CACHE' 'INDEX' qname 'PARTITION' '(' partitionList ')' cacheKeyList? 'IN' ( name | 'DEFAULT' )
    | 'FLUSH' noLogging? ( flushTables | flushOption ( ',' flushOption )* )
    | 'KILL' ( 'CONNECTION' | 'QUERY' )? term
    | 'LOAD' 'INDEX' 'INTO' 'CACHE' preloadKeys ( ',' preloadKeys )*

    | 'CREATE' 'LIBRARY' notExists? qname ( 'LANGUAGE' name | comment )+ 'AS' string
    | 'DROP' 'LIBRARY' exists? qname
    | 'ALTER' 'LIBRARY' exists? qname comment?

    | ( 'EXPLAIN' | 'DESCRIBE' | 'DESC' ) qname qname ?

    | ( 'EXPLAIN' | 'DESCRIBE' | 'DESC' ) 'ANALYZE'?
      ( 'FORMAT' '=' ( 'TRADITIONAL' | 'JSON' | 'TREE' | string ) )?
      ( 'INTO' qname )?
      ( ( 'FOR' database_ name )? ( select | delete | insert | replace | update )
      | 'FOR' 'CONNECTION' INTEGER
      )

    | 'HELP' name
    | 'USE' name
    | 'RESTART'
    | 'GET' ( 'CURRENT' | 'STACKED' )? 'DIAGNOSTICS'
       ( statementInformationItem ( ',' statementInformationItem )*
       | 'CONDITION' literal conditionInformationItem ( ',' conditionInformationItem )*
       )
    | beginWork
    ;

analyze
    : 'ANALYZE' noLogging? table_ qname ( ',' qname )*
      ( 'UPDATE' 'HISTOGRAM' 'ON' name
      ( ( ',' name )* ( 'WITH' INTEGER 'BUCKETS' )? ( ( 'MANUAL' | 'AUTO' )? 'UPDATE' )?
      | 'USING' 'DATA' string
      )
      | 'DROP' 'HISTOGRAM' 'ON' name ( ',' name )*
      )?
    ;

database_
    : 'DATABASE' | 'SCHEMA' ;

commonIndexOption
    : 'ALGORITHM' '='? ( name | 'DEFAULT' )
    | 'LOCK' '='? ( name | 'DEFAULT' )
    ;

replaceString
    : 'REPLACE' string ;

startTransactionMode
    : ( 'WITH' 'CONSISTENT' 'SNAPSHOT' | 'READ' ( 'WRITE' | 'ONLY' ) ) ;

signalCondition
    : name
    | 'SQLSTATE' 'VALUE'? ( name | INTEGER )
    ;

logfileAlterOption
    : logfileCreateOptions
    | 'UNDO_BUFFER_SIZE' '='? byteSize
    | 'REDO_BUFFER_SIZE' '='? byteSize
    | 'NODEGROUP' '='? INTEGER
    | 'COMMENT' '='? string
    ;

logfileCreateOptions
    : 'INITIAL_SIZE' '='? byteSize
    | logfileDropOption
    ;

logfileDropOption
    : 'WAIT'
    | 'NO_WAIT'
    | 'STORAGE'? 'ENGINE' '='? name
    ;

place
    : 'AFTER' name | 'FIRST' ;

partitionList
    : 'ALL'
    | name ( ',' name )*
    ;

viewCheckOption
    : 'WITH' ( 'CASCADED' | 'LOCAL' )? 'CHECK' 'OPTION' ;

databaseOption
    : 'DEFAULT'? charset_ '='? nameOr
    | 'DEFAULT'? 'COLLATE' '='? nameOr
    | 'DEFAULT'? 'ENCRYPTION' '='? string
    | 'READ' 'ONLY' '='? decimalDefault
    ;

storedRoutineBody
    : compoundStatement
    | 'AS' string
    ;

comment
    : 'COMMENT' string ;

security
    : 'SQL' 'SECURITY' ( 'DEFINER' | 'INVOKER' ) ;

characteristic
    : comment
    | 'LANGUAGE' ( name | 'SQL' )
    // Only used for CREATE
    | 'NOT'? 'DETERMINISTIC'
    | ( 'CONTAINS' | 'NO' ) 'SQL'
    | ( 'READS'
    | 'MODIFIES' ) 'SQL' 'DATA'
    | security
    | 'USING' '(' qname ( ',' qname )*')'
    ;

indexOption
    : 'KEY_BLOCK_SIZE' '='? INTEGER
    | indexType
    | 'WITH' 'PARSER' name
    | comment
    | visibility
    | 'ENGINE_ATTRIBUTE' '='? string
    | 'SECONDARY_ENGINE_ATTRIBUTE' '='? string
    ;

// TODO verify how this is used. there's overlap with
indexNameAndType
    : name indexType?
    | name? indexType
    ;

indexType
    : ( 'USING' | 'TYPE' ) ( 'BTREE' | 'HASH' | 'RTREE' ) ;

referenceDef
    : 'REFERENCES' qname ( '(' name ( ',' name )* ')' )?
      ( 'MATCH' ( 'FULL' | 'PARTIAL' | 'SIMPLE' ) )?
      ( referenceOption referenceOption? )?
    ;

referenceOption
    : 'ON' ( 'UPDATE' | 'DELETE' )
      ( 'RESTRICT' | 'CASCADE' | 'SET' null | 'SET' 'DEFAULT' | 'NO' 'ACTION' )
    ;

serverOption
    : 'HOST' string
    | 'DATABASE' string
    | 'USER' string
    | 'PASSWORD' string
    | 'SOCKET' string
    | 'OWNER' string
    | 'PORT' INTEGER
    ;

viewAlgorithm
    : 'ALGORITHM' '=' ( 'UNDEFINED' | 'MERGE' | 'TEMPTABLE' ) ;

orReplace_
    : 'OR' 'REPLACE' ;

srsAttribute
    : 'NAME' 'TEXT' string
    | 'DEFINITION' 'TEXT' string
    | 'ORGANIZATION' string 'IDENTIFIED' 'BY' INTEGER
    | 'DESCRIPTION' 'TEXT' string
    ;

handlerReadOrScan
    : ( 'FIRST' | 'NEXT' )
    | name
      ( ( 'FIRST' | 'NEXT' | 'PREV' | 'LAST' )
      | ( '=' | '<' | '>' | '<=' | '>=' ) terms
      )
    ;

terms
    : '(' term  ( ',' term )* ')' ;

valuesAlias
    : 'AS' name columns? ;

loadData
    : 'LOAD' ( 'DATA' | 'XML' ) ( 'LOW_PRIORITY' | 'CONCURRENT' )? 'FROM'?
      'LOCAL'? ( 'INFILE' | 'URL' | 'S3' ) string
      ( 'COUNT' INTEGER )?
      ( 'IN' 'PRIMARY' 'KEY' 'ORDER' )?
      ( 'REPLACE' | 'IGNORE' )?
      'INTO' 'TABLE' qname partition?
      namedCharset?

      ( 'ROWS' 'IDENTIFIED' 'BY' string )?

      fieldHandling?
      lineHandling?

      ( 'IGNORE' INTEGER ( 'LINES' | 'ROWS' ) )?

      ( '(' ( qname ( ',' qname )* )? ')' )?
      ( 'SET' setter ( ',' setter )* )?
    ;

limit
    : 'LIMIT' literal ( ( ',' | 'OFFSET' ) literal )? ;

limitCount
    : 'LIMIT' INTEGER ;

direction_
    : 'ASC' | 'DESC' ;

tableReferenceList
    : tables ( ',' tables )* ;

values
    : 'VALUES' term ( ',' term )* ;

locking
    : 'FOR' ( 'UPDATE' | 'SHARE' ) ( 'OF' qname ( ',' qname )* )? ( 'SKIP' 'LOCKED' | 'NOWAIT' )?
    | 'LOCK' 'IN' 'SHARE' 'MODE'
    ;

jsonColumns
    : 'COLUMNS' '(' jsonColumn ( ',' jsonColumn )* ')' ;

jsonColumn
    : name 'FOR' 'ORDINALITY'
    | name dataType collate? 'EXISTS'? 'PATH' string ( jsonResponse jsonResponse? )?
    | 'NESTED' 'PATH' string jsonColumns
    ;

jsonResponse
    : ( 'ERROR' | 'NULL' | 'DEFAULT' string ) 'ON' ( 'EMPTY' | 'ERROR' ) ;

indexHint
    : 'USE' indexKey indexHintScope? '(' ( indexName ( ',' indexName )* )? ')'
    | ( 'IGNORE' | 'FORCE' ) indexKey indexHintScope? '(' ( indexName ( ',' indexName )* ) ')'
    ;

indexKey
    : 'INDEX' | 'KEY' ;

indexHintScope
    : 'FOR' ( 'JOIN' | 'ORDER' 'BY' | 'GROUP' 'BY' ) ;

indexName
    : name | 'PRIMARY' ;

beginWork
    : 'BEGIN' 'WORK'? ;

lockItem
    : qname alias? ( 'READ' 'LOCAL'? | 'WRITE' ) ;

xid
    : string ( ',' string ( ',' ( INTEGER | string ) )? )? ;

resetOption
    : 'BINARY' 'LOGS' 'AND' 'GTIDS' ( 'TO' INTEGER )?
    | 'REPLICA' 'ALL'? channel?
    ;

sourceDefinition
    : 'SOURCE_HOST' '=' string
    | 'NETWORK_NAMESPACE' '=' string
    | 'SOURCE_BIND' '=' string
    | 'SOURCE_USER' '=' string
    | 'SOURCE_PASSWORD' '=' string
    | 'SOURCE_PORT' '=' INTEGER
    | 'SOURCE_CONNECT_RETRY' '=' INTEGER
    | 'SOURCE_RETRY_COUNT' '=' INTEGER
    | 'SOURCE_DELAY' '=' INTEGER
    | 'SOURCE_SSL' '=' INTEGER
    | 'SOURCE_SSL_CA' '=' string
    | 'SOURCE_SSL_CAPATH' '=' string
    | 'SOURCE_TLS_VERSION' '=' string
    | 'SOURCE_SSL_CERT' '=' string
    | 'SOURCE_TLS_CIPHERSUITES' '=' ( name | 'NULL' )
    | 'SOURCE_SSL_CIPHER' '=' string
    | 'SOURCE_SSL_KEY' '=' string
    | 'SOURCE_SSL_VERIFY_SERVER_CERT' '=' INTEGER
    | 'SOURCE_SSL_CRL' '=' string
    | 'SOURCE_SSL_CRLPATH' '=' string
    | 'SOURCE_PUBLIC_KEY_PATH' '=' string
    | 'GET_SOURCE_PUBLIC_KEY' '=' INTEGER
    | 'SOURCE_HEARTBEAT_PERIOD' '=' literal
    | 'IGNORE_SERVER_IDS' '=' '(' ( INTEGER ( ',' INTEGER )* )? ')'
    | 'SOURCE_COMPRESSION_ALGORITHM' '=' string
    | 'SOURCE_ZSTD_COMPRESSION_LEVEL' '=' INTEGER
    | 'SOURCE_AUTO_POSITION' '=' INTEGER
    | 'PRIVILEGE_CHECKS_USER' '=' user
    | 'REQUIRE_ROW_FORMAT' '=' INTEGER
    | 'REQUIRE_TABLE_PRIMARY_KEY_CHECK' '=' ( 'STREAM' | 'ON' | 'OFF' | 'GENERATE' )
    | 'SOURCE_CONNECTION_AUTO_FAILOVER' '=' INTEGER
    | 'ASSIGN_GTIDS_TO_ANONYMOUS_TRANSACTIONS' '=' ( 'OFF' | 'LOCAL' | string )
    | 'GTID_ONLY' '=' INTEGER
    | sourceFileDef
    ;

sourceFileDef
    : 'SOURCE_LOG_FILE' '=' string
    | 'SOURCE_LOG_POS' '=' INTEGER
    | 'RELAY_LOG_FILE' '=' string
    | 'RELAY_LOG_POS' '=' INTEGER
    ;

// TODO use qname (vs name, string) for all?
filterDefinition
    : 'REPLICATE_DO_DB' '=' '(' ( name ( ',' name )* )? ')'
    | 'REPLICATE_IGNORE_DB' '=' '(' ( name ( ',' name )* )? ')'
    | 'REPLICATE_DO_TABLE' '=' '(' ( qname ( ',' qname )* )? ')'
    | 'REPLICATE_IGNORE_TABLE' '=' '(' ( qname ( ',' qname )* )? ')'
    | 'REPLICATE_WILD_DO_TABLE' '=' '(' ( string ( ',' string )* )? ')'
    | 'REPLICATE_WILD_IGNORE_TABLE' '=' '(' ( string ( ',' string )* )? ')'
    | 'REPLICATE_REWRITE_DB' '=' '(' ( schemaIdentifierPair ( ',' schemaIdentifierPair )* )? ')'
    ;

schemaIdentifierPair
    : '(' name ',' name ')' ;

replicaUntil
    : sourceFileDef
    | 'SQL_BEFORE_GTIDS' '=' string
    | 'SQL_AFTER_GTIDS' '=' string
    | 'SQL_AFTER_MTS_GAPS'
    ;

userOption
    : 'USER' '=' string ;

defaultAuthOption
    : 'DEFAULT_AUTH' '=' string ;

pluginDirOption
    : 'PLUGIN_DIR' '=' string ;

replicaThreadOptions
    : replicaThreadOption ( ',' replicaThreadOption )* ;

replicaThreadOption
    : 'SQL_THREAD'
    | 'RELAY_THREAD'
    ;

groupReplicationStartOptions
    : groupReplicationStartOption ( ',' groupReplicationStartOption )* ;

groupReplicationStartOption
    : 'USER' '=' string
    | 'PASSWORD' '=' string
    | 'DEFAULT_AUTH' '=' string
    ;

ssl
    : 'REQUIRE' 'NO'? 'SSL' ;

resourceWith
    : 'WITH'
      ( ( 'MAX_QUERIES_PER_HOUR'
        | 'MAX_UPDATES_PER_HOUR'
        | 'MAX_CONNECTIONS_PER_HOUR'
        | 'MAX_USER_CONNECTIONS'
        ) INTEGER
      )+ ;

require
    : 'REQUIRE' ( 'NONE' | tlsOption ( 'AND'? tlsOption )* ) ;

tlsOption
    : 'SSL'
    | 'X509'
    | ( 'CIPHER' | 'ISSUER' | 'SUBJECT' ) string
    ;

passwordOption
    : 'PASSWORD' 'EXPIRE' ( 'DEFAULT' | 'NEVER' | 'INTERVAL' INTEGER 'DAY' )?
    | 'PASSWORD' 'HISTORY' ( 'DEFAULT' | INTEGER )
    | 'PASSWORD' 'REUSE' 'INTERVAL' ( 'DEFAULT' | INTEGER 'DAY' )
    | 'PASSWORD' 'REQUIRE' 'CURRENT' ( 'DEFAULT' | 'OPTIONAL' )?
    | 'FAILED_LOGIN_ATTEMPTS' INTEGER
    | 'PASSWORD_LOCK_TIME' ( INTEGER | 'UNBOUNDED' )
    | 'ACCOUNT' ( 'LOCK' | 'UNLOCK' )
    ;

grant
    : 'GRANT' ( roleOrPrivilegesList | 'ALL' 'PRIVILEGES'? ) 'ON' aclType? grantIdentifier
      'TO' grantTargetList ( 'WITH' 'GRANT' 'OPTION' )?
      // require? grantOptions?
      ( 'AS' user ( 'WITH' roles )? )?
    | 'GRANT' 'PROXY' 'ON' user 'TO' grantTargetList
    | 'GRANT' roleOrPrivilegesList 'TO' user ( ',' user )* ( 'WITH' 'ADMIN' 'OPTION' )?
    ;

grantTargetList
    :  user ( ',' user )* ;

revoke
    : 'REVOKE' exists?
      ( roleOrPrivilegesList 'FROM' user ( ',' user )*
      | roleOrPrivilegesList 'ON' aclType? grantIdentifier 'FROM' user ( ',' user )*
      | 'ALL' 'PRIVILEGES'?
        ( 'ON' aclType? grantIdentifier
        | ',' 'GRANT' 'OPTION'
        )
        'FROM' user ( ',' user )*
      | 'PROXY' 'ON' user 'FROM' user ( ',' user )*
      )
      ( 'IGNORE' 'UNKNOWN' 'USER' )?
    ;

aclType
    : 'TABLE'
    | 'FUNCTION'
    | 'PROCEDURE'
    ;

roleOrPrivilegesList
    : roleOrPrivilege ( ',' roleOrPrivilege )* ;

roleOrPrivilege
    : ( name columns? | user )
    | ( 'SELECT' | 'INSERT' | 'UPDATE' | 'REFERENCES' ) columns?
    | ( 'DELETE' | 'USAGE' | 'INDEX' | 'DROP' | 'EXECUTE' | 'RELOAD' | 'SHUTDOWN' | 'PROCESS' | 'FILE' | 'PROXY' | 'SUPER' | 'EVENT' | 'TRIGGER' )
    | 'GRANT' 'OPTION'
    | 'SHOW' 'DATABASES'
    | 'CREATE' ( 'TEMPORARY' 'TABLES' | 'ROUTINE' | 'TABLESPACE' | 'USER' | 'VIEW' )?
    | 'LOCK' 'TABLES'
    | 'REPLICATION' ( 'CLIENT' | 'REPLICA' )
    | 'SHOW' 'VIEW'
    | 'ALTER' 'ROUTINE'?
    | ( 'CREATE' | 'DROP' ) 'ROLE'
    ;

grantIdentifier
    : ( '*' | name ) ( '.' ( '*' | name ) )? ;

setRole
    : 'SET' roles
    | 'SET' 'DEFAULT' roles 'TO' user ( ',' user )*
    ;

roles
    : 'ROLE'
      ( user ( ',' user )*
      | 'ALL' ( 'EXCEPT' user ( ',' user )* )?
      | 'NONE'
      | 'DEFAULT'
      )
    ;

checkOption
    : 'FOR' 'UPGRADE'
    | 'QUICK' | 'FAST' | 'MEDIUM' | 'EXTENDED' | 'CHANGED'
    ;

repairType
    : 'QUICK'
    | 'EXTENDED'
    | 'USE_FRM'
    ;

transactionCharacteristics
    : 'ISOLATION' 'LEVEL'
      ( 'REPEATABLE' 'READ'
      | 'READ' ( 'COMMITTED' | 'UNCOMMITTED' )
      | 'SERIALIZABLE'
      )
    | 'READ' ( 'WRITE' | 'ONLY' )
    ;

inDb
    : ( 'FROM' | 'IN' ) qname ;

profileDef
    : 'CPU'
    | 'MEMORY'
    | 'BLOCK' 'IO'
    | 'CONTEXT' 'SWITCHES'
    | 'PAGE' 'FAULTS'
    | 'IPC'
    | 'SWAPS'
    | 'SOURCE'
    | 'ALL'
    ;

tableIndexList
    : qname cacheKeyList? ;

cacheKeyList
    : indexKey '(' ( namePrimary ( ',' namePrimary )* )? ')' ;

namePrimary
    : name | 'PRIMARY' ;

flushOption
    : ( 'HOSTS' | 'PRIVILEGES' | 'STATUS' | 'USER_RESOURCES' )
    | ( 'BINARY' | 'ENGINE' | 'ERROR' | 'GENERAL' | 'SLOW' )? 'LOGS'
    | 'RELAY' 'LOGS' channel?
    | 'OPTIMIZER_COSTS'
    ;

flushTables
    : table_
      ( 'WITH' 'READ' 'LOCK'
      | qname ( ',' qname )* ( 'FOR' 'EXPORT' | 'WITH' 'READ' 'LOCK' )?
      )?
    ;

table_
    : 'TABLE' | 'TABLES' ;

preloadKeys
    : qname ( 'PARTITION' '(' partitionList ')' )? cacheKeyList? ( 'IGNORE' 'LEAVES' )? ;

resourceGroupVcpuList
    : 'VCPU' equal? range ( ','? range )* ;

range
    : INTEGER ( '-' INTEGER )? ;

resourceGroupPriority
    : 'THREAD_PRIORITY' equal? INTEGER ;

enable_
    : 'ENABLE' | 'DISABLE' ;

term
    : qname
    | literal
    | term ( '->' | '->>' ) string
    | term 'AT' 'LOCAL'
    | 'BINARY' term
    | 'CAST' '(' term 'AS' castType 'ARRAY'? ')'
    | 'CAST' '(' term 'AT' 'TIME' 'ZONE' 'INTERVAL'? string 'AS' 'DATETIME' ( '(' INTEGER ')' )? ')'
    | 'CONVERT' '(' term ',' castType ')'
    | 'CONVERT' '(' term 'USING' nameOr ')'
    | term 'COLLATE' name
    | '!' term
    | ( '+' | '-' | '~' ) term
    | term '^' term
    | term ( '*' | '/' | 'DIV' | '%' | 'MOD' ) term
    | term ( '+' | '-' ) term
    | term ( '<<' | '>>' ) term
    | term '&' term
    | term '|' term
    | term ( '=' | ':=' | '!=' | '<>' | '<=>' | '>=' | '>' | '<=' | '<' ) term

    | function
    | windowFunctionCall
    | 'GROUPING' terms
    | '{' name term '}'
    | 'MATCH' ( qname ( ',' qname )* | '(' qname ( ',' qname )* ')' ) 'AGAINST' '(' term fulltextOptions? ')'
    | 'DEFAULT' '(' qname ')'
    | term 'IS' 'NOT'? ( 'TRUE' | 'FALSE' | 'UNKNOWN' | null )
    | term 'NOT'? 'LIKE' term // ( 'ESCAPE' term )?
    | term 'ESCAPE' term
    | term 'NOT'? ( 'REGEXP' | 'RLIKE' ) term
    | term 'NOT'? 'IN' ( '(' select ')' | terms )
    | term 'MEMBER' 'OF'? '(' term ')'
    | term 'NOT'? 'BETWEEN' term 'AND' term
    | 'CASE' term? ( 'WHEN' term 'THEN' term )+ ( 'ELSE' term )? 'END'
    | term 'SOUNDS' 'LIKE' term
    | 'EXISTS'? '(' select ')'
    | ( 'ALL' | 'ANY' | 'SOME' ) '(' select ')'

    | 'NOT' term
    | term ( 'AND' | '&&' ) term
    | term 'XOR' term
    | term ( 'OR' | '||' ) term
    | 'ROW'? '(' ( term ( ',' term )* )? ')'
    | values
    | qname ':=' term
    ;

nameOr
    : name | 'BINARY' | 'DEFAULT' ;

interval
    : 'INTERVAL' term timeUnitToo ;

windowFunctionCall
    : ( 'ROW_NUMBER' | 'RANK' | 'DENSE_RANK' | 'CUME_DIST' | 'PERCENT_RANK' ) '(' ')' over
    | 'NTILE' ( '(' INTEGER ')' | '(' term ')' ) over
    | ( 'LEAD' | 'LAG' ) '(' term ( ',' term ( ',' term )? )? ')' nullTreatment? over
    | ( 'FIRST_VALUE' | 'LAST_VALUE' ) '(' term ')' nullTreatment? over
    | 'NTH_VALUE' '(' term ',' term ')' ( 'FROM' ( 'FIRST' | 'LAST' ) )? nullTreatment? over
    ;

over
    : 'OVER' ( name | windowDef ) ;

nullTreatment
    : ( 'RESPECT' | 'IGNORE' ) 'NULLS' ;

fulltextOptions
    : 'IN' 'BOOLEAN' 'MODE'
    | 'IN' 'NATURAL' 'LANGUAGE' 'MODE' ( 'WITH' 'QUERY' 'EXPANSION' )?
    | 'WITH' 'QUERY' 'EXPANSION'
    ;

function
    : ID '(' ( udfExpr ( ',' udfExpr )* )? ')'
    | qname '(' ( term ( ',' term )* )? ')' over?

    | 'IF' '(' term ',' term ',' term ')'
    | 'REPLACE' '(' term ',' term ',' term ')'
    | 'INSERT' '(' term ',' term ',' term ',' term ')'
    | 'INTERVAL' '(' term ',' term ( ',' term )* ')'
    | 'LEFT' '(' term ',' term ')'
    | 'RIGHT' '(' term ',' term ')'
    | 'MOD' '(' term ',' term ')'
    | 'USER' '(' ')'
    | 'DATABASE' '(' ')'
    | 'SCHEMA' '(' ')'

    | 'CHAR' '(' term ( ',' term )* ( 'USING' nameOr )? ')'
    | 'CURRENT_USER' ( '(' ')' )?
    | 'JSON_VALUE' '(' term ',' string ( 'RETURNING' castType )? ( jsonResponse jsonResponse? )? ')'
    | 'TRIM' '(' ( term ( 'FROM' term )? | 'LEADING' term? 'FROM' term | 'TRAILING' term? 'FROM' term | 'BOTH' term? 'FROM' term ) ')'

    | 'CURDATE' ( '(' ')' )?
    | 'UTC_DATE' ( '(' ')' )?
    | now
    | ( 'CURTIME' | 'SYSDATE' | 'UTC_TIME' | 'UTC_TIMESTAMP' ) ( '(' INTEGER? ')' )?
    | ( 'DATE_ADD' | 'DATE_SUB' ) '(' term ',' interval ')'
    | ( 'TIMESTAMPADD' | 'TIMESTAMPDIFF' ) '(' timeUnit ',' term ',' term ')'

    | 'EXTRACT' '(' timeUnitToo 'FROM' term ')'
    | 'GET_FORMAT' '(' ( 'DATE' | 'TIME' | 'DATETIME' | 'TIMESTAMP' ) ',' term ')'
    | 'POSITION' '(' term 'IN' term ')'
    | 'SUBSTRING' '(' term ( ',' term ( ',' term )? | 'FROM' term ( 'FOR' term )? ) ')'

    | 'WEIGHT_STRING' '(' term ( 'AS' ( 'CHAR' | 'BINARY' ) '(' INTEGER ')' )? ')'

    | 'COUNT' '(' ( 'ALL'? '*' | 'ALL'? term | 'DISTINCT' term ( ',' term )* ) ')' over?
    | ( 'AVG' | 'ST_COLLECT' | 'MIN' | 'MAX' | 'SUM' ) '(' 'DISTINCT'? 'ALL'? term ')' over?
    | ( 'BIT_AND' | 'BIT_OR' | 'BIT_XOR' | 'STD' | 'VARIANCE' | 'STDDEV_SAMP' | 'VAR_SAMP' ) '(' 'ALL'? term ')'
    | 'GROUP_CONCAT' '(' 'DISTINCT'? term ( ',' term )* orderBy? ( 'SEPARATOR' string )? ')'
    ;

udfExpr
    : term alias? ;

integer_
    : 'INT' | 'INTEGER' ;

decimal_
    : 'DEC' | 'DECIMAL' ;

castType
    : 'BINARY' fieldLength?
    | ( decimal_ | 'FLOAT' ) floatPrecision?
    | 'DOUBLE' 'PRECISION'?
    | 'REAL'
    | 'CHAR' fieldLength? ( charset_ name )?
    | 'CHAR' fieldLength? charset_? ( 'BINARY' | 'ASCII' | 'UNICODE' )+
    | ( 'NCHAR' | 'NATIONAL' 'CHAR' ) fieldLength?
    | 'DATE'
    | 'DATETIME' fieldLength?
    | 'TIME' fieldLength?
    | 'JSON'
    | ( 'SIGNED' | 'UNSIGNED' )? integer_?
    | spatialType
    | 'YEAR'
    ;

dataType
    : ( integer_ | 'TINYINT' | 'SMALLINT' | 'MEDIUMINT' | 'BIGINT' ) fieldLength? fieldOptions*
    | ( 'REAL' | 'DOUBLE' 'PRECISION'? ) floatPrecision? fieldOptions*
    | ( 'FLOAT' | decimal_ | 'NUMERIC' | 'FIXED' ) floatPrecision? fieldOptions*
    | 'BIT' fieldLength?
    | 'BOOL'
    | 'BOOLEAN'
    | ( character_ 'VARYING'?
      | 'NATIONAL' character_ 'VARYING'?
      | 'NATIONAL'? 'VARCHAR'
      | 'NVARCHAR'
      | 'NCHAR' ( 'VARCHAR' | 'VARYING' )?
      ) fieldLength? characterType*
    | 'BINARY' fieldLength?
    | 'VARBINARY' fieldLength
    | 'VECTOR' fieldLength?
    | 'YEAR' fieldLength?
    | 'DATE'
    | 'TIME' fieldLength?
    | 'TIMESTAMP' fieldLength?
    | 'DATETIME' fieldLength?
    | 'TINYBLOB'
    | 'BLOB' fieldLength?
    | spatialType
    | 'MEDIUMBLOB'
    | 'LONGBLOB'
    | 'LONG' 'VARBINARY'
    | 'LONG' ( 'CHAR' 'VARYING' | 'VARCHAR' )? characterType*
    | 'TINYTEXT' characterType*
    | 'TEXT' fieldLength? characterType*
    | 'MEDIUMTEXT' characterType*
    | 'LONGTEXT' characterType*
    | 'ENUM' '(' name ( ',' name )* ')' characterType*
    | 'SET' '(' name ( ',' name )* ')' characterType*
    | 'LONG'
    | 'SERIAL'
    | 'JSON'
    | ( 'FLOAT4'
      | 'FLOAT8'
      | 'INT1'
      | 'INT2'
      | 'INT3'
      | 'INT4'
      | 'INT8'
      | 'MIDDLEINT' 
      ) fieldOptions*
    ;

spatialType
    : 'POINT'
    | 'LINESTRING'
    | 'POLYGON'
    | 'MULTIPOINT'
    | 'MULTILINESTRING'
    | 'MULTIPOLYGON'
    | 'GEOMETRY'
    | 'GEOMETRYCOLLECTION'
    | 'GEOMCOLLECTION'
    ;

namedCharset
    : charset_ nameOr ;

charset_
    : ( character_ 'SET' | 'CHARSET' ) ;

character_
    : 'CHARACTER' | 'CHAR' ;

timeUnitToo
    : timeUnit
    | 'SECOND_MICROSECOND'
    | 'MINUTE_MICROSECOND'
    | 'MINUTE_SECOND'
    | 'HOUR_MICROSECOND'
    | 'HOUR_SECOND'
    | 'HOUR_MINUTE'
    | 'DAY_MICROSECOND'
    | 'DAY_SECOND'
    | 'DAY_MINUTE'
    | 'DAY_HOUR'
    | 'YEAR_MONTH'
    ;

timeUnit
    : 'MICROSECOND'
    | 'SECOND'
    | 'MINUTE'
    | 'HOUR'
    | 'DAY'
    | 'WEEK'
    | 'MONTH'
    | 'QUARTER'
    | 'YEAR'
    ;

channel
    : 'FOR' 'CHANNEL' string ;

compoundStatement
    : dml
    | ddl
    | 'RETURN' term
    | 'IF' ifBody 'END' 'IF'
    | 'CASE' term? ( 'WHEN' term thenStatement )+ ( 'ELSE' ( compoundStatement ';' )+ )? 'END' 'CASE'
    | name ':' beginEndBlock name?
    | beginEndBlock
    | name ':' unlabeledControl name?
    | unlabeledControl
    | 'LEAVE' name
    | 'ITERATE' name
    | 'OPEN' name
    | 'FETCH' ( 'NEXT'? 'FROM' )? name 'INTO' name ( ',' name )*
    | 'CLOSE' name
    ;

ifBody
    : term thenStatement ( 'ELSEIF' ifBody | 'ELSE' ( compoundStatement ';' )+ )? ;

thenStatement
    : 'THEN' ( compoundStatement ';' )+ ;

beginEndBlock
    : 'BEGIN' ( spDeclaration ';' )* ( compoundStatement ';' )* 'END' ;

unlabeledControl
    : loopBlock
    | whileDoBlock
    | repeatUntilBlock
    ;

loopBlock
    : 'LOOP' ( compoundStatement ';' )+ 'END' 'LOOP' ;

whileDoBlock
    : 'WHILE' term 'DO' ( compoundStatement ';' )+ 'END' 'WHILE' ;

repeatUntilBlock
    : 'REPEAT' ( compoundStatement ';' )+ 'UNTIL' term 'END' 'REPEAT' ;

spDeclaration
    : 'DECLARE' name ( ',' name )* dataType collate? ( 'DEFAULT' term )?
    | 'DECLARE' name 'CONDITION' 'FOR' spCondition
    | 'DECLARE' ( 'CONTINUE' | 'EXIT' | 'UNDO' ) 'HANDLER' 'FOR' handlerCondition ( ',' handlerCondition )* compoundStatement
    | 'DECLARE' name 'CURSOR' 'FOR' select
    ;

spCondition
    : INTEGER
    | 'SQLSTATE' 'VALUE'? string
    ;

handlerCondition
    : spCondition
    | name
    | 'SQLWARNING'
    | 'NOT' 'FOUND'
    | 'SQLEXCEPTION'
    ;

statementInformationItem
    : qname '=' ( 'NUMBER' | 'ROW_COUNT' ) ;

conditionInformationItem
    : qname '=' ( signalName | 'RETURNED_SQLSTATE' ) ;

signalItem
    : signalName '=' ( qname | 'NULL' | INTEGER ) ;

signalName
    : 'CLASS_ORIGIN'
    | 'SUBCLASS_ORIGIN'
    | 'CONSTRAINT_CATALOG'
    | 'CONSTRAINT_SCHEMA'
    | 'CONSTRAINT_NAME'
    | 'CATALOG_NAME'
    | 'SCHEMA_NAME'
    | 'TABLE_NAME'
    | 'COLUMN_NAME'
    | 'CURSOR_NAME'
    | 'MESSAGE_TEXT'
    | 'MYSQL_ERRNO'
    ;

schedule
    : 'AT' term
    | 'EVERY' term timeUnitToo ( 'STARTS' term )? ( 'ENDS' term )?
    ;

constraintEnforcement
    : 'NOT'? 'ENFORCED' ;

tableConstraintDef
    : indexKey indexNameAndType? '(' keyPart ( ',' keyPart )* ')' indexOption*
    | 'FULLTEXT' indexKey? name? '(' keyPart ( ',' keyPart )* ')' indexOption*
    | 'SPATIAL' indexKey? name? '(' keyPart ( ',' keyPart )* ')' indexOption*
    | ( 'CONSTRAINT' name?)?
        ( ( 'CLUSTERING' 'KEY' | 'PRIMARY' 'KEY' | 'UNIQUE' indexKey? ) indexNameAndType? '(' keyPart ( ',' keyPart )* ')' indexOption*
        | 'FOREIGN' 'KEY' name? '(' keyPart ( ',' keyPart )* ')' referenceDef
        | 'CHECK' '(' term ')' constraintEnforcement?
        )
    ;

createDef
    : qname dataType columnAttribute*
    ;

// TODO refactor this and table constraint defs to better match docs
// https://dev.mysql.com/doc/refman/9.3/en/create-table.html
columnAttribute
    : 'NOT'? null
    | 'NOT' 'SECONDARY'
    | 'DEFAULT' ( now | literal | '(' term ')' )
    | 'ON' 'UPDATE' now
    | 'AUTO_INCREMENT'
    | 'SERIAL' 'DEFAULT' 'VALUE'
    | 'PRIMARY'? 'KEY'
    | 'UNIQUE' 'KEY'?
    | comment
    | 'COLUMN_FORMAT' ( 'FIXED' | 'DYNAMIC' | 'DEFAULT' )
    | 'STORAGE' ( 'DISK' | 'MEMORY' | 'DEFAULT' )
    | 'SRID' INTEGER
    | ( 'CONSTRAINT' name? )? 'CHECK' '(' term ')' ( 'NOT'? 'ENFORCED' )?
    | constraintEnforcement
    | 'ENGINE_ATTRIBUTE' '='? string
    | 'SECONDARY_ENGINE_ATTRIBUTE' '='? string
    | visibility
    | ( 'GENERATED' 'ALWAYS' )? 'AS' '(' term ')' ( 'VIRTUAL' | 'STORED' )?
    | referenceDef
    | collate
    ;

now
    : ( 'NOW' | 'CURRENT_TIMESTAMP' | 'LOCALTIME' | 'LOCALTIMESTAMP' ) ( '(' INTEGER? ')' )? ;

keyPart
    : ( name fieldLength? | '(' term ')' ) direction_? ;

visibility
    : 'VISIBLE' | 'INVISIBLE' ;

fieldOptions
    : 'SIGNED' | 'UNSIGNED' | 'ZEROFILL' ;

characterType
    : 'ASCII'
    | 'BINARY'
    | 'BYTE'
    | namedCharset
    | 'UNICODE'
    ;

tableCreateOption
    : 'AUTOEXTEND_SIZE' '='? byteSize
    | 'AUTO_INCREMENT' '='? INTEGER
    | 'AVG_ROW_LENGTH' '='? INTEGER
    | 'CHECKSUM' '='? INTEGER
    | 'COLLATE' '='? nameOr
    | 'COMMENT' '='? string
    | 'COMPRESSION' '='? string
    | 'CONNECTION' '='? string
    | 'DATA' 'DIRECTORY' '='? string
    | 'DEFAULT'? charset_ '='? nameOr
    | 'DELAY_KEY_WRITE' '='? INTEGER
    | 'ENCRYPTION' '='? string
    | 'ENGINE' '='? name
    | 'ENGINE_ATTRIBUTE' '='? string
    | 'INDEX' 'DIRECTORY' '='? string
    | 'INSERT_METHOD' '='? ( 'NO' | 'FIRST' | 'LAST' )
    | 'KEY_BLOCK_SIZE' '='? INTEGER
    | 'MAX_ROWS' '='? INTEGER
    | 'MIN_ROWS' '='? INTEGER
    | 'PACK_KEYS' '='? decimalDefault
    | 'PASSWORD' '='? string
    | 'ROW_FORMAT' '='? ( ID | 'DEFAULT' | 'DYNAMIC' | 'FIXED' | 'COMPRESSED' | 'REDUNDANT' | 'COMPACT' )
    | 'SECONDARY_ENGINE' equal? name
    | 'SECONDARY_ENGINE_ATTRIBUTE' '='? string
    | 'START' 'TRANSACTION'
    | 'STATS_AUTO_RECALC' '='? decimalDefault
    | 'STATS_PERSISTENT' '='? decimalDefault
    | 'STATS_SAMPLE_PAGES' '='? ( FLOAT | INTEGER | 'DEFAULT' )
    | 'STORAGE' ( 'DISK' | 'MEMORY' )
    | 'TABLESPACE' '='? name
    | 'TABLE_TYPE' '='? name
    | 'TRANSACTIONAL' '='? INTEGER
    | 'UNION' '='? '(' ( qname ( ',' qname )* )? ')'
    ;

partitionOption
    : 'COMMENT' '='? string
    | 'DEFAULT'? 'ENGINE' '='? name
    | 'NODEGROUP' '='? INTEGER
    | 'TABLESPACE' '='? name
    | 'DATA' 'DIRECTORY' '='? string
    | 'INDEX' 'DIRECTORY' '='? string
    | ( 'MAX_ROWS' | 'MIN_ROWS' ) '='? INTEGER
    ;

tablespaceOption
    : 'AUTOEXTEND_SIZE' '='? INTEGER
    | 'COMMENT' '='? string
    | 'ENCRYPTION' '='? string
    | 'ENGINE' '='? name
    | 'EXTENT_SIZE' '='? byteSize
    | 'FILE_BLOCK_SIZE' '='? byteSize
    | 'INITIAL_SIZE' '='? byteSize
    | 'MAX_SIZE' '='? byteSize
    | 'NODEGROUP' '='? INTEGER
    | 'RENAME' 'TO' name
    | 'SET' ( 'ACTIVE' | 'INACTIVE' )
    | 'USE' 'LOGFILE' 'GROUP' name
    | 'WAIT'
    | ( 'ADD' | 'DROP' ) 'DATAFILE' string
    ;

decimalDefault
    : INTEGER | 'DEFAULT' ;

tableAlterOption
    : commonIndexOption
    | validation_

    | 'ADD' 'COLUMN'?  createDef  place?
    | 'CHANGE' 'COLUMN'? name createDef place?
    | 'MODIFY' 'COLUMN'? createDef place?

    | 'ADD' 'COLUMN'? '(' ( createDef | tableConstraintDef ) ( ',' ( createDef | tableConstraintDef ) )* ')'
    | 'ADD' tableConstraintDef

    | 'DROP' ( 'COLUMN'? name ( 'RESTRICT' | 'CASCADE' )? | 'FOREIGN' 'KEY' name | 'PRIMARY' 'KEY' | indexKey qname | 'CHECK' name | 'CONSTRAINT' name )
    | enable_ 'KEYS'
    | 'ALTER' 'COLUMN'? name ( 'SET' 'DEFAULT' ( '(' term ')' | literal ) | 'DROP' 'DEFAULT' | 'SET' visibility )
    | 'ALTER' 'INDEX' qname visibility
    | 'ALTER' 'CHECK' name constraintEnforcement
    | 'ALTER' 'CONSTRAINT' name constraintEnforcement
    | 'RENAME' 'COLUMN' name 'TO' name
    | 'RENAME' ( 'TO' | 'AS' )? qname
    | 'RENAME' indexKey qname 'TO' name
    | 'CONVERT' 'TO' namedCharset collate?
    | 'FORCE'
    | orderBy
    | tableCreateOption
    | partitionBy
    | 'REMOVE' 'PARTITIONING'
    | 'DISCARD' 'TABLESPACE'
    | 'IMPORT' 'TABLESPACE'
    | 'ADD' 'PARTITION' ( noLogging? ( '(' partitionDef ( ',' partitionDef )* ')' | 'PARTITIONS' INTEGER ) )?
    | 'DROP' 'PARTITION' name ( ',' name )*
    | 'REBUILD' 'PARTITION' noLogging? partitionList
    | 'OPTIMIZE' 'PARTITION' noLogging? partitionList noLogging?
    | 'ANALYZE' 'PARTITION' noLogging? partitionList
    | 'CHECK' 'PARTITION' partitionList checkOption*
    | 'REPAIR' 'PARTITION' noLogging? partitionList repairType*
    | 'COALESCE' 'PARTITION' noLogging? INTEGER
    | 'TRUNCATE' 'PARTITION' partitionList
    | 'REORGANIZE' 'PARTITION' noLogging? ( name ( ',' name )* 'INTO' '(' partitionDef ( ',' partitionDef )* ')' )?
    | 'EXCHANGE' 'PARTITION' name 'WITH' 'TABLE' qname validation_?
    | 'DISCARD' 'PARTITION' partitionList 'TABLESPACE'
    | 'IMPORT' 'PARTITION' partitionList 'TABLESPACE'
    | 'SECONDARY_LOAD'
    | 'SECONDARY_UNLOAD'
    ;

validation_
    : ( 'WITH' | 'WITHOUT' ) 'VALIDATION' ;

partitionBy
    : 'PARTITION' 'BY'
      ( hash
      | ( 'RANGE' | 'LIST' )
          ( '(' term ')'
          | 'COLUMNS' '(' ( name ( ',' name )* )? ')'
          )
      )
      ( 'PARTITIONS' INTEGER )?
      ( 'SUBPARTITION' 'BY' hash ( 'SUBPARTITIONS' INTEGER )? )?
      ( '(' partitionDef ( ',' partitionDef )* ')' )?
    ;

hash
    : 'LINEAR'?
      ( 'HASH' '(' term ')'
      | 'KEY' ( 'ALGORITHM' '=' INTEGER )? '(' ( name ( ',' name )* )? ')'
      )
      ;

partitionDef
    : 'PARTITION' name
        ( 'VALUES'
            ( 'LESS' 'THAN' term
            | 'IN' terms
            )
        )?
        partitionOption*
        ( '(' subpartition ( ',' subpartition )* ')' )?
    ;

subpartition
    : 'SUBPARTITION' name partitionOption* ;

definer
    : 'DEFINER' '=' user ;

exists
    : 'IF' 'EXISTS' ;

notExists
    : 'IF' 'NOT' 'EXISTS' ;

procedureParameter
    : ( 'IN' | 'OUT' | 'INOUT' )? functionParameter ;

functionParameter
    : name dataType collate? ;

collate
    : 'COLLATE' nameOr ;

// TODO allow any ID or keyword
setter
    : scope? ( qname | charset_ ) equal? ( term | 'ALL' | 'BINARY' | 'ROW' ) ;

fieldHandling
    : ( 'FIELDS' | 'COLUMNS' )
      ( ( 'TERMINATED' | 'OPTIONALLY'? 'ENCLOSED' | 'ESCAPED' ) 'BY' string )+
    ;

lineHandling
    : 'LINES' ( ( 'STARTING' | 'TERMINATED' ) 'BY' string )+ ;

createAuthOption
    : user ( identified ( 'AND' identified ( 'AND' identified )? )? )? ;

identified
    // TODO missing initial auth option clause https://dev.mysql.com/doc/refman/9.3/en/create-user.html
    : 'IDENTIFIED' 'WITH' qname ( 'AS' qname )?
    | 'IDENTIFIED' ( 'WITH' qname )? ( 'BY' ( qname | 'RANDOM' 'PASSWORD' ))
    ;

alterAuthOption
    : identified replaceString? retainCurrentPassword?
    | 'DISCARD' 'OLD' 'PASSWORD'
    ;

retainCurrentPassword
    : 'RETAIN' 'CURRENT' 'PASSWORD' ;

userRegistration
    : factor 'INITIATE' 'REGISTRATION'
    | factor 'UNREGISTER'
    | factor 'FINISH' 'REGISTRATION' 'SET' 'CHALLENGE_RESPONSE' 'AS' string
    ;

factor
    : INTEGER 'FACTOR' ;

user
    : name ( '@' | qname )?
    | 'CURRENT_USER' ( '(' ')' )?
    ;

like
    : 'LIKE' name | where ;

onlineOption
    : 'ONLINE' | 'OFFLINE' ;

noLogging
    : 'LOCAL' | 'NO_WRITE_TO_BINLOG' ;

partition
    : 'PARTITION' '(' name ( ',' name )* ')' ;

// TODO inline this?
columns
    : '(' name ( ',' name )* ')' ;

qname
    : ( '.'? name | LOCAL | GLOBAL | 'DEFAULT' )
      // TODO allow any ID or keyword
      ( '.' name ( '.' name )? )? ;

literal
    : ID
    | keyword
    | string
    | ( '+' | '-' )? INTEGER
    | ( '+' | '-' )? FLOAT
    | BINARY
    | SIZE
    | datetime
    | null
    | 'TRUE'
    | 'FALSE'
    | 'ON'
    | 'OFF'
    | 'DEFAULT'
    | 'MAXVALUE'
    | PARAM
    // TODO is this needed? mooted by rule qname?
    | LOCAL
    | GLOBAL
    | interval
    ;

string
    : CHARSET? ( STRING | QUOTED )+
    | CHARSET? HEXADECIMAL
    | CHARSET? BLOB
    | NATIONAL ( STRING | QUOTED )*
    // TODO disable <secret> after testing
    | '<secret>'
    ;

null
    : 'NULL' | NOPE ;

datetime
    : ( 'DATE' | 'TIME' | 'TIMESTAMP' ) string ;

fieldLength
    : '(' INTEGER ')' ;

floatPrecision
    : '(' ( INTEGER | FLOAT | INTEGER ',' INTEGER ) ')' ;

name
    : ID
    | keyword
    | string
    // TODO remove this after lexer rule CHARSET is fixed
    | CHARSET
    ;

byteSize
    : INTEGER | SIZE ;

equal
    : '=' | ':=' ;

scope
    : 'PERSIST'
    | 'PERSIST_ONLY'
    | 'GLOBAL'
    | 'LOCAL'
    | 'SESSION'
    ;

// Comment out reserved words
keyword
    : 'ACCOUNT'
    | 'ACTION'
    | 'ACTIVE'
    | 'ADD'
    | 'ADDDATE'
    | 'ADMIN'
    | 'AFTER'
    | 'AGAINST'
    | 'AGGREGATE'
    | 'ALGORITHM'
//    | 'ALL'
//    | 'ALTER'
    | 'ALWAYS'
//    | 'ANALYZE'
//    | 'AND'
    | 'ANY'
    | 'ARRAY'
//    | 'AS'
//    | 'ASC'
    | 'ASCII'
    | 'ASSIGN_GTIDS_TO_ANONYMOUS_TRANSACTIONS'
    | 'AT'
    | 'ATTRIBUTE'
    | 'AUTHENTICATION'
    | 'AUTO'
    | 'AUTO_INCREMENT'
    | 'AUTOEXTEND_SIZE'
    | 'AVG'
    | 'AVG_ROW_LENGTH'
    | 'BACKUP'
//    | 'BEFORE'
    | 'BEGIN'
    | 'BERNOULLI'
//    | 'BETWEEN'
//    | 'BIGINT'
//    | 'BINARY'
    | 'BINLOG'
    | 'BIT'
    | 'BIT_AND'
    | 'BIT_OR'
    | 'BIT_XOR'
//    | 'BLOB'
    | 'BLOCK'
    | 'BOOL'
    | 'BOOLEAN'
//    | 'BOTH'
    | 'BTREE'
    | 'BUCKETS'
    | 'BULK'
//    | 'BY'
    | 'BYTE'
    | 'CACHE'
//    | 'CALL'
//    | 'CASCADE'
    | 'CASCADED'
//    | 'CASE'
    | 'CAST'
    | 'CATALOG_NAME'
    | 'CHAIN'
    | 'CHALLENGE_RESPONSE'
//    | 'CHANGE'
    | 'CHANGED'
    | 'CHANNEL'
//    | 'CHAR'
//    | 'CHARACTER'
    | 'CHARSET'
//    | 'CHECK'
    | 'CHECKSUM'
    | 'CIPHER'
    | 'CLASS_ORIGIN'
    | 'CLIENT'
    | 'CLONE'
    | 'CLOSE'
    | 'CLUSTERING'
    | 'COALESCE'
    | 'CODE'
//    | 'COLLATE'
    | 'COLLATION'
//    | 'COLUMN'
    | 'COLUMN_FORMAT'
    | 'COLUMN_NAME'
    | 'COLUMNS'
    | 'COMMENT'
    | 'COMMIT'
    | 'COMMITTED'
    | 'COMPACT'
    | 'COMPLETION'
    | 'COMPONENT'
    | 'COMPRESSED'
    | 'COMPRESSION'
    | 'CONCURRENT'
//    | 'CONDITION'
    | 'CONNECTION'
    | 'CONSISTENT'
//    | 'CONSTRAINT'
    | 'CONSTRAINT_CATALOG'
    | 'CONSTRAINT_NAME'
    | 'CONSTRAINT_SCHEMA'
    | 'CONTAINS'
    | 'CONTEXT'
//    | 'CONTINUE'
//    | 'CONVERT'
    | 'COUNT'
    | 'CPU'
//    | 'CREATE'
//    | 'CROSS'
//    | 'CUBE'
//    | 'CUME_DIST'
    | 'CURDATE'
    | 'CURRENT'
//    | 'CURRENT_TIMESTAMP'
//    | 'CURRENT_USER'
//    | 'CURSOR'
    | 'CURSOR_NAME'
    | 'CURTIME'
    | 'DATA'
//    | 'DATABASE'
//    | 'DATABASES'
    | 'DATAFILE'
    | 'DATE'
    | 'DATE_ADD'
    | 'DATE_SUB'
    | 'DATETIME'
    | 'DAY'
//    | 'DAY_HOUR'
//    | 'DAY_MICROSECOND'
//    | 'DAY_MINUTE'
//    | 'DAY_SECOND'
    | 'DEALLOCATE'
//    | 'DEC'
//    | 'DECIMAL'
//    | 'DECLARE'
//    | 'DEFAULT'
    | 'DEFAULT_AUTH'
    | 'DEFINER'
    | 'DEFINITION'
    | 'DELAY_KEY_WRITE'
//    | 'DELAYED'
//    | 'DELETE'
//    | 'DENSE_RANK'
//    | 'DESC'
//    | 'DESCRIBE'
    | 'DESCRIPTION'
//    | 'DETERMINISTIC'
    | 'DIAGNOSTICS'
    | 'DIRECTORY'
    | 'DISABLE'
    | 'DISCARD'
    | 'DISK'
//    | 'DISTINCT'
//    | 'DISTINCTROW'
//    | 'DIV'
    | 'DO'
//    | 'DOUBLE'
//    | 'DROP'
//    | 'DUAL'
    | 'DUMPFILE'
    | 'DUPLICATE'
    | 'DYNAMIC'
//    | 'EACH'
//    | 'ELSE'
//    | 'ELSEIF'
//    | 'EMPTY'
    | 'ENABLE'
    | 'ENCLOSED'
    | 'ENCRYPTION'
    | 'END'
    | 'ENDS'
    | 'ENFORCED'
    | 'ENGINE'
    | 'ENGINE_ATTRIBUTE'
    | 'ENGINES'
    | 'ENUM'
    | 'ERROR'
    | 'ERRORS'
    | 'ESCAPE'
//    | 'ESCAPED'
    | 'EVENT'
    | 'EVENTS'
    | 'EVERY'
//    | 'EXCEPT'
    | 'EXCHANGE'
    | 'EXCLUDE'
    | 'EXECUTE'
//    | 'EXISTS'
//    | 'EXIT'
    | 'EXPANSION'
    | 'EXPIRE'
//    | 'EXPLAIN'
    | 'EXPORT'
    | 'EXTENDED'
    | 'EXTENT_SIZE'
    | 'EXTRACT'
    | 'FACTOR'
    | 'FAILED_LOGIN_ATTEMPTS'
//    | 'FALSE'
    | 'FAST'
    | 'FAULTS'
//    | 'FETCH'
    | 'FIELDS'
    | 'FILE'
    | 'FILE_BLOCK_SIZE'
    | 'FILTER'
    | 'FINISH'
    | 'FIRST'
//    | 'FIRST_VALUE'
    | 'FIXED'
//    | 'FLOAT'
//    | 'FLOAT4'
//    | 'FLOAT8'
    | 'FLUSH'
    | 'FOLLOWING'
    | 'FOLLOWS'
//    | 'FOR'
//    | 'FORCE'
//    | 'FOREIGN'
    | 'FORMAT'
    | 'FOUND'
//    | 'FROM'
    | 'FULL'
//    | 'FULLTEXT'
//    | 'FUNCTION'
    | 'GENERAL'
    | 'GENERATE'
//    | 'GENERATED'
    | 'GEOMCOLLECTION'
    | 'GEOMETRY'
    | 'GEOMETRYCOLLECTION'
//    | 'GET'
    | 'GET_FORMAT'
    | 'GET_SOURCE_PUBLIC_KEY'
    | 'GLOBAL'
//    | 'GRANT'
    | 'GRANTS'
//    | 'GROUP'
    | 'GROUP_CONCAT'
    | 'GROUP_REPLICATION'
//    | 'GROUPING'
//    | 'GROUPS'
    | 'GTID_ONLY'
    | 'GTIDS'
    | 'HANDLER'
    | 'HASH'
//    | 'HAVING'
    | 'HELP'
//    | 'HIGH_PRIORITY'
    | 'HISTOGRAM'
    | 'HISTORY'
    | 'HOST'
    | 'HOSTS'
    | 'HOUR'
//    | 'HOUR_MICROSECOND'
//    | 'HOUR_MINUTE'
//    | 'HOUR_SECOND'
    | 'IDENTIFIED'
//    | 'IF'
//    | 'IGNORE'
    | 'IGNORE_SERVER_IDS'
    | 'IMPORT'
//    | 'IN'
    | 'INACTIVE'
//    | 'INDEX'
    | 'INDEXES'
//    | 'INFILE'
    | 'INITIAL'
    | 'INITIAL_SIZE'
    | 'INITIATE'
//    | 'INNER'
    | 'INNODB'
//    | 'INOUT'
//    | 'INSERT'
    | 'INSERT_METHOD'
    | 'INSTALL'
    | 'INSTANCE'
//    | 'INT'
//    | 'INT1'
//    | 'INT2'
//    | 'INT3'
//    | 'INT4'
//    | 'INT8'
//    | 'INTEGER'
//    | 'INTERSECT'
//    | 'INTERVAL'
//    | 'INTO'
    | 'INVISIBLE'
    | 'INVOKER'
    | 'IO'
    | 'IPC'
//    | 'IS'
    | 'ISOLATION'
    | 'ISSUER'
//    | 'ITERATE'
//    | 'JOIN'
    | 'JSON'
    | 'JSON_ARRAYAGG'
    | 'JSON_OBJECTAGG'
//    | 'JSON_TABLE'
    | 'JSON_VALUE'
//    | 'KEY'
    | 'KEY_BLOCK_SIZE'
    | 'KEYRING'
//    | 'KEYS'
//    | 'KILL'
//    | 'LAG'
    | 'LANGUAGE'
    | 'LAST'
//    | 'LAST_VALUE'
//    | 'LATERAL'
//    | 'LEAD'
//    | 'LEADING'
//    | 'LEAVE'
    | 'LEAVES'
//    | 'LEFT'
    | 'LESS'
    | 'LEVEL'
    | 'LIBRARY'
//    | 'LIKE'
//    | 'LIMIT'
//    | 'LINEAR'
//    | 'LINES'
    | 'LINESTRING'
    | 'LIST'
//    | 'LOAD'
    | 'LOCAL'
//    | 'LOCALTIME'
//    | 'LOCALTIMESTAMP'
//    | 'LOCK'
    | 'LOCKED'
    | 'LOG'
    | 'LOGFILE'
    | 'LOGS'
    | 'LONG'
//    | 'LONGBLOB'
//    | 'LONGTEXT'
//    | 'LOOP'
//    | 'LOW_PRIORITY'
//    | 'MANUAL'
    | 'MASTER'
//    | 'MATCH'
    | 'MAX'
    | 'MAX_CONNECTIONS_PER_HOUR'
    | 'MAX_QUERIES_PER_HOUR'
    | 'MAX_ROWS'
    | 'MAX_SIZE'
    | 'MAX_UPDATES_PER_HOUR'
    | 'MAX_USER_CONNECTIONS'
//    | 'MAXVALUE'
    | 'MEDIUM'
//    | 'MEDIUMBLOB'
//    | 'MEDIUMINT'
//    | 'MEDIUMTEXT'
    | 'MEMBER'
    | 'MEMORY'
    | 'MERGE'
    | 'MESSAGE_TEXT'
    | 'MICROSECOND'
//    | 'MIDDLEINT'
    | 'MIGRATE'
    | 'MIN'
    | 'MIN_ROWS'
    | 'MINUTE'
//    | 'MINUTE_MICROSECOND'
//    | 'MINUTE_SECOND'
//    | 'MOD'
    | 'MODE'
//    | 'MODIFIES'
    | 'MODIFY'
    | 'MONTH'
    | 'MULTILINESTRING'
    | 'MULTIPOINT'
    | 'MULTIPOLYGON'
    | 'MUTEX'
    | 'mysql_admin'
    | 'MYSQL_ERRNO'
    | 'mysql_main'
    | 'NAME'
    | 'NAMES'
    | 'NATIONAL'
//    | 'NATURAL'
    | 'NCHAR'
    | 'NESTED'
    | 'NETWORK_NAMESPACE'
    | 'NEVER'
    | 'NEXT'
    | 'NO'
    | 'NO_WAIT'
//    | 'NO_WRITE_TO_BINLOG'
    | 'NODEGROUP'
    | 'NONE'
//    | 'NOT'
    | 'NOW'
    | 'NOWAIT'
//    | 'NTH_VALUE'
//    | 'NTILE'
//    | 'NULL'
    | 'NULLS'
    | 'NUMBER'
//    | 'NUMERIC'
    | 'NVARCHAR'
//    | 'OF'
    | 'OFF'
    | 'OFFLINE'
    | 'OFFSET'
    | 'OJ'
    | 'OLD'
//    | 'ON'
    | 'ONE'
    | 'ONLINE'
    | 'ONLY'
    | 'OPEN'
//    | 'OPTIMIZE'
//    | 'OPTIMIZER_COSTS'
//    | 'OPTION'
    | 'OPTIONAL'
//    | 'OPTIONALLY'
    | 'OPTIONS'
//    | 'OR'
//    | 'ORDER'
    | 'ORDINALITY'
    | 'ORGANIZATION'
    | 'OTHERS'
//    | 'OUT'
//    | 'OUTER'
//    | 'OUTFILE'
//    | 'OVER'
    | 'OWNER'
    | 'PACK_KEYS'
    | 'PAGE'
//    | 'PARALLEL'
    | 'PARSE_TREE'
    | 'PARSER'
    | 'PARTIAL'
//    | 'PARTITION'
    | 'PARTITIONING'
    | 'PARTITIONS'
    | 'PASSWORD'
    | 'PASSWORD_LOCK_TIME'
    | 'PATH'
//    | 'PERCENT_RANK'
    | 'PERSIST'
    | 'PERSIST_ONLY'
    | 'PHASE'
    | 'PLUGIN'
    | 'PLUGIN_DIR'
    | 'PLUGINS'
    | 'POINT'
    | 'POLYGON'
    | 'PORT'
    | 'POSITION'
    | 'PRECEDES'
    | 'PRECEDING'
//    | 'PRECISION'
    | 'PREPARE'
    | 'PRESERVE'
    | 'PREV'
//    | 'PRIMARY'
    | 'PRIVILEGE_CHECKS_USER'
    | 'PRIVILEGES'
//    | 'PROCEDURE'
    | 'PROCESS'
    | 'PROCESSLIST'
    | 'PROFILE'
    | 'PROFILES'
    | 'PROXY'
//    | 'PURGE'
//    | 'QUALIFY'
    | 'QUARTER'
    | 'QUERY'
    | 'QUICK'
    | 'RANDOM'
//    | 'RANGE'
    | 'RANK'
//    | 'READ'
//    | 'READS'
//    | 'REAL'
    | 'REBUILD'
    | 'RECOVER'
//    | 'RECURSIVE'
    | 'REDO_BUFFER_SIZE'
    | 'REDO_LOG'
    | 'REDUNDANT'
    | 'REFERENCE'
//    | 'REFERENCES'
//    | 'REGEXP'
    | 'REGISTRATION'
    | 'RELAY'
    | 'RELAY_LOG_FILE'
    | 'RELAY_LOG_POS'
    | 'RELAY_THREAD'
    | 'RELAYLOG'
//    | 'RELEASE'
    | 'RELOAD'
    | 'REMOTE'
    | 'REMOVE'
//    | 'RENAME'
    | 'REORGANIZE'
    | 'REPAIR'
    | 'REPEAT'
    | 'REPEATABLE'
//    | 'REPLACE'
    | 'REPLICA'
    | 'REPLICAS'
    | 'REPLICATE_DO_DB'
    | 'REPLICATE_DO_TABLE'
    | 'REPLICATE_IGNORE_DB'
    | 'REPLICATE_IGNORE_TABLE'
    | 'REPLICATE_REWRITE_DB'
    | 'REPLICATE_WILD_DO_TABLE'
    | 'REPLICATE_WILD_IGNORE_TABLE'
    | 'REPLICATION'
//    | 'REQUIRE'
    | 'REQUIRE_ROW_FORMAT'
    | 'REQUIRE_TABLE_PRIMARY_KEY_CHECK'
    | 'RESET'
//    | 'RESIGNAL'
    | 'RESOURCE'
    | 'RESPECT'
    | 'RESTART'
//    | 'RESTRICT'
    | 'RESUME'
    | 'RETAIN'
//    | 'RETURN'
    | 'RETURNED_SQLSTATE'
//    | 'RETURNING'
    | 'RETURNS'
    | 'REUSE'
    | 'REVERSE'
//    | 'REVOKE'
//    | 'RIGHT'
//    | 'RLIKE'
    | 'ROLE'
    | 'ROLLBACK'
    | 'ROLLUP'
    | 'ROTATE'
    | 'ROUTINE'
//    | 'ROW'
    | 'ROW_COUNT'
    | 'ROW_FORMAT'
//    | 'ROW_NUMBER'
//    | 'ROWS'
    | 'RTREE'
    | 'S3'
    | 'SAVEPOINT'
    | 'SCHEDULE'
//    | 'SCHEMA'
    | 'SCHEMA_NAME'
    | 'SECOND'
//    | 'SECOND_MICROSECOND'
    | 'SECONDARY'
    | 'SECONDARY_ENGINE'
    | 'SECONDARY_ENGINE_ATTRIBUTE'
    | 'SECONDARY_LOAD'
    | 'SECONDARY_UNLOAD'
    | 'SECURITY'
//    | 'SELECT'
//    | 'SEPARATOR'
    | 'SERIAL'
    | 'SERIALIZABLE'
    | 'SERVER'
    | 'SESSION'
//    | 'SET'
    | 'SHARE'
//    | 'SHOW'
    | 'SHUTDOWN'
//    | 'SIGNAL'
    | 'SIGNED'
    | 'SIMPLE'
    | 'SKIP'
    | 'SLOW'
//    | 'SMALLINT'
    | 'SNAPSHOT'
    | 'SOCKET'
    | 'SOME'
    | 'SONAME'
    | 'SOUNDS'
    | 'SOURCE'
    | 'SOURCE_AUTO_POSITION'
    | 'SOURCE_BIND'
    | 'SOURCE_COMPRESSION_ALGORITHM'
    | 'SOURCE_CONNECT_RETRY'
    | 'SOURCE_CONNECTION_AUTO_FAILOVER'
    | 'SOURCE_DELAY'
    | 'SOURCE_HEARTBEAT_PERIOD'
    | 'SOURCE_HOST'
    | 'SOURCE_LOG_FILE'
    | 'SOURCE_LOG_POS'
    | 'SOURCE_PASSWORD'
    | 'SOURCE_PORT'
    | 'SOURCE_PUBLIC_KEY_PATH'
    | 'SOURCE_RETRY_COUNT'
    | 'SOURCE_SSL'
    | 'SOURCE_SSL_CA'
    | 'SOURCE_SSL_CAPATH'
    | 'SOURCE_SSL_CERT'
    | 'SOURCE_SSL_CIPHER'
    | 'SOURCE_SSL_CRL'
    | 'SOURCE_SSL_CRLPATH'
    | 'SOURCE_SSL_KEY'
    | 'SOURCE_SSL_VERIFY_SERVER_CERT'
    | 'SOURCE_TLS_CIPHERSUITES'
    | 'SOURCE_TLS_VERSION'
    | 'SOURCE_USER'
    | 'SOURCE_ZSTD_COMPRESSION_LEVEL'
//    | 'SPATIAL'
//    | 'SQL'
    | 'SQL_AFTER_GTIDS'
    | 'SQL_AFTER_MTS_GAPS'
    | 'SQL_BEFORE_GTIDS'
//    | 'SQL_BIG_RESULT'
    | 'SQL_BUFFER_RESULT'
//    | 'SQL_CALC_FOUND_ROWS'
    | 'SQL_NO_CACHE'
//    | 'SQL_SMALL_RESULT'
    | 'SQL_THREAD'
//    | 'SQLEXCEPTION'
//    | 'SQLSTATE'
//    | 'SQLWARNING'
    | 'SRID'
//    | 'SSL'
    | 'ST_COLLECT'
    | 'STACKED'
    | 'START'
//    | 'STARTING'
    | 'STARTS'
    | 'STATS_AUTO_RECALC'
    | 'STATS_PERSISTENT'
    | 'STATS_SAMPLE_PAGES'
    | 'STATUS'
    | 'STD'
    | 'STDDEV_SAMP'
    | 'STOP'
    | 'STORAGE'
//    | 'STORED'
//    | 'STRAIGHT_JOIN'
    | 'STREAM'
    | 'STRING'
    | 'SUBCLASS_ORIGIN'
    | 'SUBDATE'
    | 'SUBJECT'
    | 'SUBPARTITION'
    | 'SUBPARTITIONS'
    | 'SUBSTRING'
    | 'SUM'
    | 'SUPER'
    | 'SUSPEND'
    | 'SWAPS'
    | 'SWITCHES'
    | 'SYSDATE'
//    | 'SYSTEM'
//    | 'TABLE'
    | 'TABLE_CHECKSUM'
    | 'TABLE_NAME'
    | 'TABLE_TYPE'
    | 'TABLES'
//    | 'TABLESAMPLE'
    | 'TABLESPACE'
    | 'TEMPORARY'
    | 'TEMPTABLE'
//    | 'TERMINATED'
    | 'TEXT'
    | 'THAN'
//    | 'THEN'
    | 'THREAD_PRIORITY'
    | 'TIES'
    | 'TIME'
    | 'TIMESTAMP'
    | 'TIMESTAMPADD'
    | 'TIMESTAMPDIFF'
//    | 'TINYBLOB'
//    | 'TINYINT'
//    | 'TINYTEXT'
    | 'TLS'
//    | 'TO'
    | 'TRADITIONAL'
//    | 'TRAILING'
    | 'TRANSACTION'
    | 'TRANSACTIONAL'
    | 'TREE'
//    | 'TRIGGER'
    | 'TRIGGERS'
    | 'TRIM'
//    | 'TRUE'
    | 'TRUNCATE'
    | 'TYPE'
    | 'UNBOUNDED'
    | 'UNCOMMITTED'
    | 'UNDEFINED'
//    | 'UNDO'
    | 'UNDO_BUFFER_SIZE'
    | 'UNDOFILE'
    | 'UNICODE'
    | 'UNINSTALL'
//    | 'UNION'
//    | 'UNIQUE'
    | 'UNKNOWN'
//    | 'UNLOCK'
    | 'UNREGISTER'
//    | 'UNSIGNED'
    | 'UNTIL'
//    | 'UPDATE'
    | 'UPGRADE'
    | 'URL'
//    | 'USAGE'
//    | 'USE'
    | 'USE_FRM'
    | 'USER'
    | 'USER_RESOURCES'
//    | 'USING'
//    | 'UTC_DATE'
//    | 'UTC_TIME'
//    | 'UTC_TIMESTAMP'
    | 'VALIDATION'
    | 'VALUE'
//    | 'VALUES'
    | 'VAR_SAMP'
//    | 'VARBINARY'
//    | 'VARCHAR'
    | 'VARIABLES'
    | 'VARIANCE'
//    | 'VARYING'
    | 'VCPU'
    | 'VECTOR'
    | 'VIEW'
//    | 'VIRTUAL'
    | 'VISIBLE'
    | 'WAIT'
    | 'WARNINGS'
    | 'WEEK'
    | 'WEIGHT_STRING'
//    | 'WHEN'
//    | 'WHERE'
//    | 'WHILE'
//    | 'WINDOW'
//    | 'WITH'
    | 'WITHOUT'
    | 'WORK'
    | 'WRAPPER'
//    | 'WRITE'
    | 'X509'
    | 'XA'
    | 'XID'
    | 'XML'
//    | 'XOR'
    | 'YEAR'
//    | 'YEAR_MONTH'
//    | 'ZEROFILL'
    | 'ZONE'
    ;

PARAM
    : '?' ;

HEXADECIMAL
    : '0x' BASE16 ;

INTEGER
    : BASE10 ;

BINARY
    : '0b' BASE2+ ;

FLOAT
    : ( BASE10 ( '.' BASE10? )? | '.' BASE10 ) ( 'E' [-+]? BASE10 )? ;

SIZE
    : BASE10 [KMGT] ;

QUOTED
    : '"' ( '\\'? .)*? '"' ;

LOCAL
    : '@' ( ID | STRING | QUOTED | IPV4 | IPV6 ) ;

GLOBAL
    : '@' '@' ( ID ( '.' ID )? )? ;


STRING
    : '$$' .*? '$$'
    | ( '\'' ( '\\'? . )*? '\'' )+
    ;

NATIONAL
    : 'N' STRING ;

// TODO match against supported list, otherwise is an ID
CHARSET
    : '_' [A-Z0-9]+ ;

ID
    : '`' ( ~'`' | '``' )* '`'
    | [A-Z0-9_$\u0080-\uFFFF]+
    ;

BLOB
    : 'x\'' BASE16? '\''
    | 'b\'' BASE2? '\''
    ;

// MySQL synonym for NULL. A separate token from 'NULL'
// because I'm not clear where it's permitted.
NOPE options { caseInsensitive = false; }
    : '\\N' ;

MYSQL_COMMENT
    : '/*!' ( BLOCK_COMMENT | . )*?  '*/' -> channel( HIDDEN );

BLOCK_COMMENT
    : '/*' ~[!] .*? '*/' -> channel( HIDDEN );

// Another MySQL-ism...?
POUND_COMMENT
    : '#' ~[\n\r]* -> channel( HIDDEN ) ;

COMMENT
    // MySQL requires whitespace before comment
    : '--' ( [ \t] ~[\n\r]* | [\n\r] | EOF ) -> channel( HIDDEN )
    // Playing around with variations, to see which I prefer.
//    : '--' ( [ \t] .*? )? ( [\n\r] | EOF ) -> channel( HIDDEN )
    ;

WHITESPACE
    : [ \t\f\r\n]+ -> channel( HIDDEN ) ;

fragment IPV4 
    : BASE10 '.' BASE10 '.' BASE10 '.' BASE10;

fragment IPV6 
    : ( GROUPS? '::' )? GROUPS ;

fragment GROUPS 
    : BASE16 ( ':' BASE16 )* ;

fragment BASE2 
    : [01]+ ;

fragment BASE10 
    : [0-9]+ ;

fragment BASE16 
    : [0-9A-F]+ ;
