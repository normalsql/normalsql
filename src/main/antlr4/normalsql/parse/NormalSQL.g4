// Copyright 2010-2023 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

/*
 NormalSQL.g4, SQL DML grammar for ANTLR 4.x

 Style:

 Child rules are indented when its (mostly) only used by a parent rule.

   Heuristics:

      1. Use alt labels, subrules, or both, to ease parse tree navigation.
         Versus using element labels.

      2. Per DRY, create subrule for 3 (sometimes 2) or more copypastas.
         Except when doing so conflicts with #1 or #3.

      3. Inline lists to ease processing parse trees, eg comma separated
         items or terms?

Labels for rule alts use UPPERCASE for SQL keywords, MixedCase for other rules. See rules
'subterm' and 'predicate'. TODO Maybe use underscores as separator.

*/

grammar NormalSQL;

options {
caseInsensitive=true;
contextSuperClass=org.antlr.v4.runtime.RuleContextWithAltNum;
}

@parser::header { import static normalsql.parse.Reserved.*; }

// convenience for debugging
aaa1 : script ;

script : ( statement | ';' )* EOF ;
//script : statement ( ';' statement )* EOF ;

transaction
    // Postgres
    : 'BEGIN'  ( 'TRANSACTION' | 'WORK' )?  ( transactionModePostgres ( ',' transactionModePostgres )* )?
    // MySQL
    | 'START' 'TRANSACTION' (transactionModeMySQL (',' transactionModeMySQL)* )?
    // MySQL
    | 'COMMIT' ( 'TRANSACTION' | 'WORK' )? ( 'AND' 'NO'? 'CHAIN' )? ( 'NO'? 'RELEASE' )?
    // MySQL
    | 'ROLLBACK' ( 'TRANSACTION' | 'WORK' )? ( 'AND' 'NO'? 'CHAIN' )? ( 'NO'? 'RELEASE' )?
     | 'ROLLBACK' ( 'TRANSACTION' | 'WORK' )? (( 'TO' 'SAVEPOINT'? )? name )?
     | 'SAVEPOINT' qname
    | 'END' 'TRANSACTION'?
    | 'RELEASE' 'SAVEPOINT'? qname
    | 'RESET' qname
   ;
//    ( 'DEFERRED' | 'EXCLUSIVE' | 'IMMEDIATE' | 'TRANSACTION' | 'WORK' )?

  transactionModePostgres
    : 'ISOLATION' 'LEVEL' ( 'SERIALIZABLE' | 'REPEATABLE' 'READ' | 'READ' ( 'COMMITTED' | 'UNCOMMITTED' ))*
    | 'READ' ( 'WRITE' |  'ONLY' )
    | 'NOT'? 'DEFERRABLE'
    ;

    transactionModeMySQL
        : 'WITH' 'CONSISTENT' 'SNAPSHOT'
        | 'READ' 'WRITE'
        | 'READ' 'ONLY'
        ;

statement
    :  explain?
    ( alter
    | ( 'ANALYZE' | 'ANALYSE' ) qname?
    | 'ATTACH' 'DATABASE'? term 'AS' ( qname | 'NULL' )
    | transaction
    | create
    | delete
    | 'DETACH' 'DATABASE'? term
    | drop
    | insert
    | merge
    | pragma
    | query
    | 'REINDEX' qname?
    | set
    | 'SHOW' 'CREATE'
      ( 'DATABASE' | 'EVENT' | 'FUNCTION' | 'PROCEDURE' | 'SCHEMA' | 'TABLE' | 'TRIGGER' | 'USER' | 'VIEW' ) name // MySQL
    | 'SHOW' 'EXTENDED'? 'FULL'? 'TABLES' ( 'FROM' | 'IN' ) qname ( 'LIKE' string | 'WHERE' term ) // MySQL
    | 'SHOW' ( 'ALL' | name ) // Postgres
    | update
    | 'VACUUM' qname? ( 'INTO' term )?
    )
    ;

    pragma : 'PRAGMA' qname ( '=' pragmaValue | '(' pragmaValue ')' )? ;

        pragmaValue : literal | signedNumber | name ;

explain
    : 'EXPLAIN' 'ANALYZE'? 'VERBOSE'? ( '(' option ( ',' option )* ')' )?
    | 'EXPLAIN' 'QUERY' 'PLAN'
    ;

    option
        : 'ANALYZE' boolean?
        | 'VERBOSE' boolean?
        | 'COSTS' boolean?
        | 'SETTINGS' boolean?
        | 'BUFFERS' boolean?
        | 'WAL' boolean?
        | 'TIMING' boolean?
        | 'SUMMARY' boolean?
        | 'FORMAT' ( 'TEXT' | 'XML' | 'JSON' | 'YAML' )
        ;

// https://www.postgresql.org/docs/current/sql-altertable.html
alter
    : 'ALTER' 'TABLE' qname
      ( 'RENAME' ( 'COLUMN'? qname )? 'TO' qname
      | 'ADD' 'COLUMN'? notExists? columnDef
      | 'ALTER' 'COLUMN' qname
        ( 'SET' 'STATISTICS' ( signedInteger |  'DEFAULT' )
        | 'SET' 'STORAGE'  ( 'PLAIN' | 'EXTERNAL' | 'EXTENDED' | 'MAIN' | 'DEFAULT' )
        )
      | 'DROP' 'COLUMN'? qname exists?
      )
    ;



// TODO so much more to add
set
    : 'SET' ( 'LOCAL' | 'GLOBAL' | 'SESSION' )? ( qname | VARIABLE ) ( '=' | 'TO' ) terms ;

//reset
//    : 'RESET' qname;

drop
    : 'DROP' dropsicle exists? qnames0 ( 'CASCADE' | 'RESTRICT' )?
//    | 'DROP' 'TEMPORARY'? 'FUNCTION' ifExists? table ( '(' ( type ( ',' type )* )? ')' )?
    ;

    // TODO new name. this copypasta from Postgres probably needs to be validated.
    dropsicle
        : 'ACCESS METHOD'
        | 'COLLATION'
        | 'CONVERSION'
        | 'EVENT' 'TRIGGER'
        | 'EXTENSION'
        | 'FOREIGN' 'DATA' 'WRAPPER'
        | 'FOREIGN' 'TABLE'
        | 'INDEX'
        | 'MATERIALIZED' 'VIEW'
        | 'PROCEDURAL'? 'LANGUAGE'
        | 'PUBLICATION'
        | 'ROLE'
        | 'SCHEMA'
        | 'SEQUENCE'
        | 'SERVER'
        | 'STATISTICS'
        | 'TABLE'
        | 'TEXT' 'SEARCH' ( 'CONFIGURATION' | 'DICTIONARY' | 'PARSER' | 'TEMPLATE' | 'VIEW' )
        | 'TRIGGER'
        | 'VIEW'
        ;

create
    : createSchema
    | createIndex
    | createMaterializedView
    | createRole
    | createTable
    | createTrigger
    | createView
    | createVirtualTable
    ;

createIndex
      : 'CREATE' 'UNIQUE'? 'INDEX' notExists? qname 'ON' qname indexedColumns where?
      ;
//    : 'CREATE' (OR REPLACE)? TEMPORARY? FUNCTION tableRef '(' (sqlParameterDeclaration (',' sqlParameterDeclaration)*)? ')' RETURNS type (COMMENT string)? routineCharacteristics routineBody
//    | 'CREATE' (OR REPLACE)? VIEW tableRef (SECURITY (DEFINER | INVOKER))? AS query
//    | 'CREATE' TYPE tableRef AS ( '(' sqlParameterDeclaration (',' sqlParameterDeclaration)* ')' | type)

    indexedColumn
//        : ( qname | term ) sortDir? ;
//        : qname sortDir? ;
        : term sortDir? ;

    indexedColumns
        : '(' indexedColumn ( ',' indexedColumn )* ')' ;


createMaterializedView
    : 'CREATE' 'MATERIALIZED' 'VIEW' notExists? qname  qnames?
//    (COMMENT string)? (WITH properties)? AS (query | '(' query ')' )
        ( 'USING' . )?
        ( 'WITH' '(' .*? ')' )?
        ( 'TABLESPACE' . )?
        'AS' query
        ( 'WITH' ( 'NO' )? 'DATA' )?
    ;

createSchema
    : 'CREATE' ( 'DATABASE' | 'SCHEMA' ) notExists? name ( 'AUTHORIZATION' name )?
      ( 'DEFAULT'?
        ( 'CHARACTER' 'SET' '='? name
        | 'COLLATE' '='? name
        | 'ENCRYPTION' '='? ( 'Y' | 'N' )
        )
      )*
      ( 'OWNER' name )?
      create*
   ;

createTable
    : 'CREATE' ( 'CACHED' | 'MEMORY' )? ( 'LOCAL' | 'GLOBAL' )? temporary? 'UNLOGGED'?
      'TABLE' notExists? qname
      ( '(' columnDef ( ',' columnDef )* ( ',' tableStuff )* ')' ( 'WITHOUT' ID )?
      | 'AS' query ( 'WITH' 'NO'? 'DATA' )?
      ) // TODO (COMMENT string)? (WITH properties)?

      ( createTableOptions ( ',' createTableOptions )? )?
      ;

  columnDef
        : name ( type | columnStuff )*  // TODO (COMMENT string)? (WITH properties)?
        ;


  createTableOptions
    : 'STRICT' | 'WITHOUT' 'ROWID' // SQLite
    ;

// https://www.sqlite.org/vtab.html
createVirtualTable
    : 'CREATE' 'VIRTUAL' 'TABLE' notExists? qname
      'USING' qname ( '(' ( moduleArgument ( ',' moduleArgument?  )*  )? ')' )?
    ;

    moduleArgument
        : ( name | literal | type )+
        | ( name | literal )+ ( '=' ( name | literal )* )?
//        | compare
//        | COMPARE
//        | assign
//        | ASSIGN
//        | EQ
        ;

createRole
    : 'CREATE' 'ROLE' name 'WITH'?
        ( 'ADMIN' names0
        | 'BYPASSRLS' | 'NOBYPASSRLS'
        | 'CONNECTION' 'LIMIT' INTEGER
        | 'CREATEDB' | 'NOCREATEDB'
        | 'CREATEROLE' | 'NOCREATEROLE'
        | 'ENCRYPTED'? 'PASSWORD' ( string | 'NULL' )
        | 'IN'? 'GROUP' names0
        | 'IN'? 'ROLE' names0
        | 'INHERIT' | 'NOINHERIT'
        | 'LOGIN' | 'NOLOGIN'
        | 'REPLICATION' | 'NOREPLICATION'
        | 'SUPERUSER' | 'NOSUPERUSER'
        | 'SYSID' string //  TODO need UUID token ?
        | 'VALID' 'UNTIL' string // TODO need timestamp ?
        )*
      ;

createTrigger
     : 'CREATE' temporary? 'TRIGGER' notExists? qname ( 'BEFORE' | 'AFTER' | 'INSTEAD' 'OF' )?
       ( 'DELETE' | 'INSERT' | 'UPDATE' ( 'OF' qnames0 )? ) 'ON' qname
       ( 'FOR' 'EACH' 'ROW' )? ( 'WHEN' term )?
       'BEGIN' (( update | insert | delete | query ) ';' )+
       // TODO remove '?' after testing!!
       'END'?
     ;

temporary : 'TEMP' | 'TEMPORARY' ;

// https://www.postgresql.org/docs/current/sql-createview.html
// https://learn.microsoft.com/en-us/sql/t-sql/statements/create-view-transact-sql
createView
      : 'CREATE' ( 'OR' ( 'REPLACE' | 'ALTER' ))?
       temporary? 'RECURSIVE'? // Postgres
        ( 'ALGORITHM' '=' ('UNDEFINED' | 'MERGE' | 'TEMPTABLE' ))? // MySQL
        ( 'DEFINER' '=' name )? // MySQL
        ( 'SQL' 'SECURITY' ( 'DEFINER' | 'INVOKER' )*)? // MySQL
        'VIEW' notExists? qname qnames?
        ( 'WITH' names0 )? // Transact-SQL
        ( 'WITH' '(' .*? ')' )? // Postgres
        'AS' query
        ( 'WITH' ( 'CASCADED' | 'LOCAL' )? 'CHECK' 'OPTION' )?
      ;

    exists : ( 'IF' 'EXISTS' ) ;
    notExists : 'IF' 'NOT' 'EXISTS' ;

    columnStuff
        : ( 'CONSTRAINT' name )?
          ( 'PRIMARY' 'KEY' sortDir? onConflict? 'AUTOINCREMENT'?
          | 'AUTOINCREMENT'
          | 'COUNTER'
          | 'NOT'? 'NULL' onConflict?
          | 'UNIQUE' onConflict?
          | 'CHECK' '(' term ')' ( 'NO' 'INHERIT' )?
          | 'DEFAULT' ( literal | term )
          | 'COLLATE' collationName
          | foreignKey
          | ( 'GENERATED' 'ALWAYS' )? 'AS' '(' term ')' ( 'STORED' | 'VIRTUAL' )?
          | 'INVISIBLE'
          | 'IMPLICITLY'? 'HIDDEN'
          )
          // SQLite undocumented
          ( 'CONSTRAINT' name )*
        ;

    // SQLite
    tableStuff
        : ( 'CONSTRAINT' name)?
          ( 'CHECK' '(' term ')' onConflict?
          | ( 'FOREIGN' 'KEY' names foreignKey )
          | ( 'PRIMARY' 'KEY' | 'UNIQUE' ) indexedColumns onConflict?
          // SQLite per autoinc.test autoinc-7.1
          | 'PRIMARY' 'KEY' '(' name 'AUTOINCREMENT' ')'
          )+
          // SQLite undocumented
          ( 'CONSTRAINT' name )*
          ;

        onConflict
            : 'ON' 'CONFLICT' afirr
            ;

        foreignKey
            : 'REFERENCES' name names?
              ( 'ON' ( 'DELETE' | 'UPDATE' )
                ( 'SET' ( 'NULL' | 'DEFAULT' )
                | 'CASCADE'
                | 'RESTRICT'
                | 'NO' 'ACTION'
                )
              | 'MATCH' ( 'FULL' |  'PARTIAL' |  'SIMPLE' )
//              | 'MATCH' name
              )*
              ( 'NOT'? 'DEFERRABLE' ( 'INITIALLY' ( 'DEFERRED' | 'IMMEDIATE' ))? )?
            ;

with
    : 'WITH' 'RECURSIVE'? cte ( ',' cte )*
    ;

    cte
        // TODO add rule for column aliases
        : alias ( '(' name ( ',' name )* ')' )? 'AS' ( 'NOT'? 'MATERIALIZED' )?
          '(' ( query | insert | update ) ')'
          ( 'SEARCH' ( 'BREADTH' | 'DEPTH' ) 'FIRST' 'BY' name ( ',' name )* 'SET' name )?
          ( 'CYCLE' name ( ',' name )* 'SET' name ( 'TO' literal 'DEFAULT' literal )? ( 'USING' name )? )?
        ;

delete
    : with? 'DELETE' 'FROM' 'ONLY'? tableRef
      indexedBy?
      // TODO need sources which doesn't collide with this rule's orderBy, limit, offset
      // Postgres
//      ( 'USING' sources ( ',' sources )* )?
//      ( 'USING' sources )?
      where? returning? orderBy? limit? offset?
    ;

insert
    : with?
        // TODO this is getting too nutty; split into rule per dialect
      ( 'INSERT' ( 'OR' afirr )? | 'REPLACE' )
      ( 'LOW_PRIORITY' | 'DELAYED' | 'HIGH_PRIORITY' )? 'IGNORE'? // MySQL
      'OVERWRITE'? // Hive, Snowflake
      'INTO'? tableRef
      ( 'PARTITION'  ( qnames | '(' setters ')'  ) )? // MySQL or Hive
      overriding?

      ( names? ( query upsert* | 'DEFAULT' 'VALUES' )
      | 'SET' setters ( 'AS' name names? )? // MySQL
      )

      ( 'ON' 'DUPLICATE' 'KEY' 'UPDATE' setters )? // MySQL
      returning?
    ;

    // Postgres?
    overriding
        : 'OVERRIDING' ( 'SYSTEM' | 'USER' ) 'VALUE' ;

    // SQLite
    upsert
        : 'ON' 'CONFLICT' ( terms where? )?
          'DO' ( 'NOTHING' | 'UPDATE' 'SET' setters where? )
        ;

update
    : with? 'UPDATE'
      'ONLY'? // Postgres
      ( 'OR' afirr )? // SQLite
      'LOW_PRIORITY'? 'IGNORE'? // MySQL
//      tableRef ( '*' | ( ',' tableRef )* ) // Postgres or MySQL
      tables // MySQL
      indexedBy?
      'SET' setters
      ( 'FROM' tables )?
      where? returning? orderBy? limit? offset?
    ;

    setters
        : setter ( ',' setter )* ;

    setter
        : ( qname | qnames ) '=' term ;

    afirr
        : 'ABORT' | 'FAIL' | 'IGNORE' | 'REPLACE' | 'ROLLBACK' ;

    returning
        : 'RETURNING' returned ( ',' returned )* ;

        returned
            : term ( 'AS'? alias )?
//            : '*' | term ( 'AS'? alias )?
            ;

tableRef
    : qname ( 'AS'? name )?
    ;

merge
    : with? 'MERGE' 'INTO' 'ONLY'? tableRef
      'USING' 'ONLY'? ( query | qname alias? ) 'ON' terms
      when*
    ;

    when
        : 'WHEN' 'NOT'? 'MATCHED' ( 'AND' subterm )* 'THEN'
          ( 'INSERT' qnames? overriding? ( values | 'DEFAULT' 'VALUES' ) where?
          | 'UPDATE' 'SET' setters ( 'DELETE'? where )?
          | 'DELETE'
          | 'DO' 'NOTHING'
          )
        ;

query
    : with? combine
      // Because various dialects order these clauses differently
      // TODO: Allow just one of each clause (somehow)
      ( orderBy | offset | fetch | limit | forUpdate )*
    ;

    combine
        : combine ( 'INTERSECT' | 'MINUS' ) combine
        | combine ( 'UNION' 'ALL'? | 'EXCEPT' ) combine
        | combine 'MULTISET' combine
        | select
        | '(' query ')'
        | values
        // MySQL table statement
        | 'TABLE' qname
        ;

    select
        : 'SELECT' quantifier?
          top?
          ( item ( ',' item )* ','? )?
          into?
          ( 'FROM' tables )?
          where?
          groupBy?
          having?
          windows?
          qualify?
        ;

    tables
        : tables ',' tables
          // TODO validate this. added ON clause to pass sqlite's tkt2141.test
          ( 'ON' term )?
        | tables join tables ( 'ON' term | 'USING' qnames )?

        | ( qname | '(' qname ')' ) tableAlias?
          ( 'USE' 'INDEX' names // H2
          | ( 'USE' | 'IGNORE' | 'FORCE' ) 'INDEX' ( 'FOR' 'JOIN' )? '(' id ')' // Postgres? MySQL?
          | indexedBy // SQLite
          | 'TABLESAMPLE' qname '(' terms ')' ( 'REPEATABLE' '(' term ')' )? // Postgres
          )?

        | 'LATERAL'?
          ( '(' query ')'
          | function
          | values
          | tableFunc
          | tableRows
          | deltaTable
          | tableCollection
          | 'JSON_TABLE' // TODO
          | xmlTable
          | unnest
          | pivot
          | unpivot
          )
          tableAlias?

        | '(' tables ')' tableAlias?
        ;

        join
            : 'NATURAL'?
              ( 'LEFT' ( 'SEMI' | 'ANTI' | 'OUTER' )?
              | ( 'RIGHT' | 'FULL' ) 'OUTER'?
              | 'INNER'
              | 'CROSS'
              )?
              'JOIN' 'LATERAL'?
            ;

    tableAlias : 'AS'? alias names? ;

    values : 'VALUES' terms ;

    // H2 data change delta table http://h2database.com/html/grammar.html#data_change_delta_table
    // DB2 intermediate result table
    deltaTable : ( 'NEW' | 'OLD' | 'FINAL' ) 'TABLE' '(' ( delete | insert | merge | update ) ')' ;

    // PL/SQL table collection expression
    tableCollection : 'TABLE' '(' term ')' ;

    unnest
        : 'UNNEST' '(' ( (qname | array ) ( ',' (qname | array ) )* )? ')' ( 'WITH' 'ORDINALITY' )? ;

    tableFunc
        // H2 table function http://h2database.com/html/functions.html#table
        : ( 'TABLE' | 'TABLE_DISTINCT' ) '(' tableFuncParam ( ',' tableFuncParam )* ')'
        |  'SYSTEM_RANGE' '(' term ',' term ( ',' term )? ')'
        ;

        tableFuncParam
//            : name ( type | qname | 'NULL' ) '=' subterm ;
            : name ( type | 'NULL' ) '=' subterm ;

    tableRows
        : 'ROWS' 'FROM' '(' tableRowsFunc ( ',' tableRowsFunc )* ')'
        ;

    tableRowsFunc
        : function 'AS' '(' tableRowsFuncType ( ',' tableRowsFuncType )* ')'
        ;

    tableRowsFuncType
        : name type // ( 'COLLATE' qname )?
        ;

    offset
        : 'OFFSET' term rowRows? ;

    limit
        : 'LIMIT' term ( ',' term )? ;

    fetch
        : 'FETCH' ( 'FIRST' | 'NEXT' ) ( term 'PERCENT'? )? rowRows ( 'ONLY' | withTies ) ;

    forUpdate
        : 'FOR'
          ( 'READ' 'ONLY'
          | ( 'NO' 'KEY' )? 'UPDATE'
          | 'KEY'? 'SHARE'
          )
          ( 'OF' qname ( ',' qname )* )?
          ( 'NOWAIT' | 'WAIT' INTEGER | 'SKIP' 'LOCKED' )?
        ;

    quantifier
        : 'DISTINCT' ( 'ON' '(' terms ')' )?
        | 'ALL'
        ;

    top
        : 'TOP' ( INTEGER | FLOAT | '(' term ')' ) 'PERCENT'? withTies? ;

    item
        : term ( 'AS'? alias )?                       # ItemTerm
        | ( qname | '*' ) ( 'EXCEPT' qnames )?  # ItemColumns
//        | (( qname '.' )? '*' ) ( 'EXCEPT' qnames )?  # ItemTableRef
        ;

    into
        : 'INTO' ( temporary | 'UNLOGGED' )? 'TABLE'? ( qname | VARIABLE ) ( ',' ( qname | VARIABLE ) )* ;

        pivot
            // T-SQL, PL/SQL
            : 'PIVOT'
              '('
                aliasedFunction ( ',' aliasedFunction )*
                'FOR' ( qname | qnames )
                'IN' '(' aliasedTerms ')'
              ')'
            // PL/SQL
            | 'PIVOT' 'XML'
              '('
                aliasedFunction ( ',' aliasedFunction )*
                'FOR' ( qname | qnames )
                'IN' '(' ( query | 'ANY' ) ')'
              ')'
            ;

            aliasedFunction : function ( 'AS'? alias )? ;

        unpivot
            : 'UNPIVOT' (( 'INCLUDE' | 'EXCLUDE' ) 'NULLS' )?
              '('
                ( qname | qnames )
                'FOR' ( qname | qnames )
                'IN' '(' aliasedTerms ')'
              ')'
            ;

    where
        : 'WHERE' ( term | 'CURRENT' 'OF' name ) ;

    groupBy
        : 'GROUP' 'BY' allDistinct? groupByItem ( ',' groupByItem )* ;

        groupByItem
            : terms
            | 'ROLLUP' '(' terms ')' | 'CUBE' '(' terms ')'
            | 'GROUPING' 'SETS' '(' groupByItem ( ',' groupByItem )* ')'
            ;

    having
        : 'HAVING' terms ;

    windows
        : 'WINDOW' windowAlias ( ',' windowAlias )* ;

        windowAlias
            : alias 'AS' window ;

    qualify
        : 'QUALIFY' term ;

    xmlTable
        : 'XMLTABLE'
          '('
            subterm passing?
            'COLUMNS' xmlColumn ( ',' xmlColumn )*
          ')'
        ;

        passing
            : 'PASSING' ( 'BY' ( 'REF' | 'VALUE' ))? aliasedTerms ;

        xmlColumn
           : name ( type ( 'PATH' subterm )? | 'FOR' 'ORDINALITY' ) ;

    // SQLite
    indexedBy
        : 'NOT' 'INDEXED' | 'INDEXED' 'BY' qname ;

terms
    : term ( ',' term )* ;

aliasedTerm
    : term ( 'AS'? alias )? ;

aliasedTerms
    : aliasedTerm ( ',' aliasedTerm )* ;

term
    : term 'OR' term
    | term 'AND' term
    | subterm
    // TODO: '||' as logical OR
    // TODO: 'XOR'
    ;

// TODO verify precedences
// TODO combine operators into lexer rules
subterm
    : literal                                  # SubtermLiteral
    | qname                                    # SubtermColumn

    | ( '+' | '-' | TILDE ) subterm            # SubtermUnary
    | ( 'NOT' | '!' ) subterm                  # SubtermUnary
    // Postgres unary operators: absolute value, square root, and mystery
    // https://www.postgresql.org/docs/17/typeconv-oper.html
    | ( '@' | '|/' | '||/' ) subterm                  # SubtermUnary
    | ( 'ANY' | 'SOME' | 'ALL' ) '(' query ')' # SubtermFixme
    | function ( '.' name )*                   # SubtermFunction
//    | function ( '.' name | index | '::' type )* # SubtermFunction
    | subterm index                            # SubtermIndex
    | subterm '::' type                        # SubtermTypecast
    | 'CASE' term whenSimple+ caseElse? 'END'       # CaseSimple
    | 'CASE' whenSearch+ caseElse? 'END'            # CaseSearch
    | 'INTERVAL' subterm timeCast?                   # SubtermInterval
    | subterm 'AT' ( 'LOCAL' | timeZone string ) # SubtermAtTZ
    | ( 'NEXT' | 'CURRENT' ) 'VALUE' 'FOR' qname # SubtermSequence
    | array                                      # SubtermArray
    | '(' query ')'                              # SubtermSubquery
    | 'ROW'? '(' terms? ')' ( '.' name )?        # SubtermRow
    | subterm 'COLLATE' collationName            # SubtermCollate

    | subterm compare subterm                                                         # SubtermCompare
    | subterm ( 'ISNULL' | 'NOTNULL' | 'NOT' 'NULL' )                                 # SubtermFixme
//    | subterm 'IS' 'NOT'? logicals                                                    # SubtermLogical
    | subterm 'IS' 'NOT'? subterm                                                    # SubtermLogical
    | subterm 'IS' 'NOT'? 'DISTINCT' 'FROM' subterm                                   # SubtermDistinct
    | subterm 'IS' 'NOT'? 'JSON' jsonType? uniqueKeys?                                # SubtermJSON
    | subterm 'IS' 'NOT'? 'OF' 'TYPE'? '(' 'ONLY'? type ( ',' type )* ')'             # SubtermOfType
    | subterm 'NOT'? 'BETWEEN' ( 'ASYMMETRIC' | 'SYMMETRIC' )? subterm 'AND' subterm  # SubtermBETWEEN
    | subterm 'NOT'? 'IN' ( '(' ( query | terms )? ')' | qname )?                      # SubtermIN
    // can't remember why I had to split SubtermLIKE. IIRC, ambiguity something something
//    | subterm 'NOT'? likes subterm ( 'ESCAPE' subterm )?                              # SubtermLIKE
    | subterm 'NOT'? likes subterm 'ESCAPE' subterm                                   # SubtermLIKE
    | subterm 'NOT'? likes subterm                                                    # SubtermLIKE
    | subterm 'NOT'? 'LIKE' ( 'ANY' | 'ALL' ) '(' terms ')'                           # SubtermLIKETerms

    | <assoc=right> subterm '^' subterm                   # SubtermOperator
    | subterm ( '<<' | '>>' | '&' | '|' ) subterm         # SubtermOperator
    | subterm  ( '||' | '->>' | '->' | '#>>' | '#>' ) subterm            # SubtermOperator
    | subterm ( '*' | '/' | 'DIV' | '%' | 'MOD' ) subterm # SubtermOperator
    | subterm ( '+' | '-' ) subterm                       # SubtermOperator
    | VARIABLE assign subterm                             # SubtermAssign
    | '*' # SubtermAll
    ;

predicate
    : compare subterm                                                         # PredicateCompare
    | ( 'ISNULL' | 'NOTNULL' | 'NOT' 'NULL' )                                 # PredicateFixme
//    | 'IS' 'NOT'? logicals                                                    # PredicateLogical
    | 'IS' 'NOT'? subterm                                                    # PredicateLogical
    | 'IS' 'NOT'? 'DISTINCT' 'FROM' subterm                                   # PredicateDistinct
    | 'IS' 'NOT'? 'JSON' jsonType? uniqueKeys?                                # PredicateJSON
    | 'IS' 'NOT'? 'OF' 'TYPE'? '(' 'ONLY'? type ( ',' type )* ')'             # PredicateOfType
    | 'NOT'? 'BETWEEN' ( 'ASYMMETRIC' | 'SYMMETRIC' )? subterm 'AND' subterm  # PredicateBETWEEN
    | 'NOT'? 'IN' ( '(' ( query | terms )? ')' | name )?                      # PredicateIN
    | 'NOT'? likes subterm ( 'ESCAPE' subterm )?                              # PredicateLIKE
    | 'NOT'? 'LIKE' ( 'ANY' | 'ALL' ) '(' terms ')'                           # PredicateLIKETerms
    ;

//        | 'RAISE' '(' ('IGNORE' | ('ROLLBACK' | 'ABORT' | 'FAIL') ',' string) ')'  # PredicateRaise
//   | '(' subterm ',' subterm ')' 'OVERLAPS' '(' subterm ',' subterm ')' # SubtermOverlaps
//    // CrateDB ?
////    | 'MATCH' '(' name ',' string ')' 'USING' qname 'WITH' '(' subterm ')'
// TODO: H2's INTERSECTS for 2D bounding boxes. Better as a function?
// | row 'INTERSECTS' '(' term ',' term ')'

//    predicate
//        | 'IS' 'NOT'? term                                                         # PredicateIS
//        ;

    jsonType
        : 'VALUE' | 'ARRAY' | 'OBJECT' | 'SCALAR' ;

    logicals
        : 'NULL' | 'UNKNOWN' | 'TRUE' | 'FALSE' | 'DISTINCT'
        // PL/SQL
        | 'NAN' | 'INFINITE' | 'PRESENT' | 'A' 'SET' | 'EMPTY'
        ;

    likes
        : 'LIKE' | 'RLIKE' | 'ILIKE' | 'REGEXP' | 'GLOB' | 'MATCH' | 'SIMILAR' 'TO' ;

    assign
        : EQ | ASSIGN ;

    compare
        : EQ | COMPARE | TILDE | MATCH
        | POSTGRES_COMPARE | STARTS_WITH
        ;

    collationName
        : 'BINARY' | 'NOCASE' | 'RTRIM' | name ;

literal
    : INTEGER
    | FLOAT
    | BITS
    | BYTES
    | OCTALS
    | datetime
    | PARAMETER
    | VARIABLE
    | string
    | 'TRUE'
    | 'FALSE'
    ;

datetime
    : 'DATE' string
    | ( '{d' | '{t' | '{ts' ) string '}'
    | ( 'TIME' | 'TIMESTAMP' ) ( withWithout timeZone )? string?
    | 'CURRENT_TIMESTAMP'
    ;

boolean
    : 'TRUE' | 'FALSE' ;

timeCast
    : timeUnit precision? ( 'TO' timeUnit precision? )? ;

timeUnit
    : 'EPOCH' | 'MILLENNIUM' | 'CENTURY' | 'DECADE' | 'YEAR' | 'YEARS'
    | 'QUARTER' | 'MONTH' | 'MONTHS' | 'WEEK' | 'WEEKS' | 'DAY' | 'DAYS'
    | 'HOUR' | 'HOURS' | 'MINUTE' | 'MINUTES' | 'SECOND' | 'SECONDS'
    | 'MCS' | 'MILLISECOND' | 'MICROSECOND' | 'NS' | 'NANOSECOND'
    | 'TIMEZONE_HOUR' | 'TIMEZONE_MINUTE' | 'TIMEZONE_SECOND'
    | 'ISO_WEEK_YEAR' | 'ISO_YEAR' | 'ISOYEAR'
    | 'ISO_DAY_OF_WEEK' | 'DAY_OF_WEEK' | 'ISODOW' | 'DOW' ;

    whenSimple : 'WHEN' ( predicate | terms ) caseThen ;
        whenSearch : 'WHEN' term caseThen ;
        caseThen : 'THEN' term ;
        caseElse : 'ELSE' term ;

type
    : 'ROW' '(' name type ( ',' name type )* ')'
    | 'SETOF' type
    | type 'ARRAY' ( '[' INTEGER ']' )?
    | type ( '[' INTEGER? ']' )+
    | scalar
    ;

scalar
    : 'BIGINT'
    | 'BINARY' precision?
    | 'BIT' 'VARYING'? length?
    | 'LARGE'? 'BLOB' precision?
    | 'BOOL'
    | 'BOOLEAN'
    | chars precision?
    | 'CLOB'
    | 'DATE'
    | 'DATETIME'
    | 'DEC' precision?
    | 'DECFLOAT'
    | 'DECIMAL' precision?
    | 'DOUBLE' 'PRECISION'?
    | 'FLOAT' length?
    | 'INT'
    | ( 'BIG' | 'UNSIGNED' )? 'INTEGER'
    | 'INTERVAL'
    | 'JSON'
    | 'JSONB'
    | 'LONG'
    | 'NCLOB'
    | 'NUM' precision?
    | 'NUMBER' precision?
    | 'NUMERIC' precision?
    | 'RAW'
    | 'REAL'
    | 'SMALLINT'
    | 'STR'
    | 'TEXT' length?
    | ( 'TIME' | 'TIMESTAMP' ) length? ( withWithout 'LOCAL'? timeZone )?
    | 'TINYINT'
    | 'UUID'
    | 'VARBINARY'
    | 'VARINT'
    | 'XML'
    | id precision?
    | string
    ;

 chars
    : ( 'CHARACTER' | 'CHAR' | 'NCHAR' ) 'VARYING'?
    | 'VARCHAR'
    | 'VARCHAR2'
    | 'VARCHAR_IGNORECASE'
    | 'NATIONAL' ( 'CHARACTER' | 'CHAR' ) 'VARYING'?
    | 'STRING'
    ;

length
    : '(' INTEGER ')' ;

precision
    : '(' signedInteger ( ',' signedInteger )? ')' ;

signedNumber : signedInteger | signedFloat ;
signedInteger : ( '+' | '-' )? INTEGER ;
signedFloat : ( '+' | '-' )? FLOAT ;

array
    : 'ARRAY' arrayTerms  ;

    arrayTerms
        : '[' ( terms | arrayNested )? ']' ;

    arrayNested
        : arrayTerms ( ',' arrayTerms )* ;


function
    : coreFunction
    | aggregateFunction
    | analyticFunction
    | windowFunction
    | '{fn' function '}' //  ODBC style
    | simpleFunction
    ;

coreFunction
    : 'ARRAY' '(' query ')' // Postgres
    | ( 'CAST' | 'TRY_CAST' ) '(' term 'AS' type ( 'FORMAT' string )? ')'
    | 'COLLECT' '(' ( 'DISTINCT' | 'UNIQUE' ) name orderBy? ')'
    | 'CURRENT_DATE' params?
    | 'CURRENT_TIME' params?
    | 'CURRENT_TIMESTAMP' params?
    | 'CURRENT_CATALOG'
    | 'CURRENT_ROLE'
    | 'CURRENT_SCHEMA'
    | 'CURRENT_USER' params?
    | 'DATEADD' '(' timeUnit ',' signedInteger ',' datetime ')'
    | 'DATEDIFF' '(' timeUnit ',' string ',' string ')'
    | 'DATE_TRUNC' '(' timeUnit ',' datetime ')'
    | 'EXISTS' '(' query ')'
    | 'EXTRACT' '(' timeUnit 'FROM' subterm ')'
    | 'JSON_ARRAYAGG' '(' qname orderBy? onNull?
//      uniqueKeys?
      ')'
//      filter? over?
    | 'JSON_OBJECTAGG' '(' jsonPair orderBy? onNull?
      uniqueKeys?
      ')'
      filter?
//      over?
    | 'JSON_OBJECT' '(' jsonPairs? onNull? uniqueKeys? formatJson? ')'
    | 'JSON_ARRAY' '(' ( terms | '(' query ')' )? formatJson? onNull? ')'
    | 'LOCALTIME' params?
    | 'LOCALTIMESTAMP' params?
    | 'SESSION_USER'
    | 'SUBSTRING' '(' term 'FROM' term ( 'FOR' term )? ')'
    | 'SYSTEM_USER'
    | 'USER' params?
    | 'UNIQUE' ( ( 'ALL' | 'NOT' )? 'DISTINCT' )? '(' query ')'
    | 'TIMESTAMPDIFF' '(' timeUnit ',' string ',' string ')'
    | 'TRIM' '(' ( 'BOTH' | 'LEADING' | 'TRAILING' )? ( term? 'FROM' )? terms ')'
    | 'XMLATTRIBUTES' '(' xmlAttrib ( ',' xmlAttrib )* ')'
    | 'XMLCONCAT' '(' terms ')'
    | 'XMLELEMENT' '(' 'NAME'? name ( ',' terms )? ')'
//   | 'XMLELEMENT' '(' 'NAME' collabel (',' (xml_attributes | expr_list))? ')'
    | 'XMLEXISTS' '(' subterm passing? ')'
    | 'XMLFOREST' '(' xmlAttrib ( ',' xmlAttrib )* ')'
    | 'XMLPARSE' '(' xmlContent term ( 'PRESERVE' | 'STRIP' ) 'WHITESPACE' ')'
    | 'XMLPI' '(' 'NAME' name ( ',' term )? ')'
    | 'XMLROOT' '(' 'XML' term ',' 'VERSION' ( term | 'NO' 'VALUE' ) ',' 'STANDALONE' ( 'YES' | 'NO' 'VALUE'? ) ')'
    | 'XMLSERIALIZE' '(' xmlContent term 'AS' type ')'
    ;

    params
        : '(' INTEGER? ')' ;

    // TODO Postgres functions
 /*
   : 'COLLATION' 'FOR' '(' a_expr ')'

   | 'NORMALIZE' '(' a_expr (',' unicode_normal_form)? ')'
   | 'OVERLAY' '(' a_expr 'PLACING' a_expr 'FROM' a_expr ('FOR' a_expr)? ')'
   | 'POSITION' '(' (subterm 'IN' subterm)? ')'
   | 'SUBSTRING' '(' substr_list? ')'
   | 'TREAT' '(' a_expr 'AS' type ')'
   | 'TRIM' '(' ('BOTH' | 'LEADING' | 'TRAILING')? trim_list ')'
   | 'NULLIF' '(' a_expr ',' a_expr ')'
   | 'COALESCE' '(' expr_list ')'
   | 'GREATEST' '(' expr_list ')'
   | 'LEAST' '(' expr_list ')'
 */
//    | 'SIN' '(' subterm ')'
//    | 'LEFT' '(' subterm ',' subterm ')'
//    | 'LOWER' '(' subterm ')'
////    | analyticFunction
////    | keyword? 'FUNCTION' keyword '(' terms? ')' // TODO: T-SQL style
////    | keyword '.' keyword '(' terms? ')' // TODO: T-SQL style?
//        'SUM' '(' allDistinct? term ')' filter? over?

    simpleFunction
        : qname '(' term ( ',' term )* ')'
//        : qname '(' ( '*' |  term ( ',' term )*  ) ')'
        ;

    windowFunction
        : qname '(' terms ')' filter? over
        ;

    aggregateFunction
        : 'GROUP_CONCAT' '(' 'DISTINCT'? terms orderBy? ( 'SEPARATOR' term )? ')' filter?
       // https://docs.oracle.com/en/database/oracle/oracle-database/12.2/sqlrf/LISTAGG.html
        | 'LISTAGG' '(' ( 'ALL' | 'DISTINCT' )? terms ( 'ON' 'OVERFLOW' ( 'ERROR' | 'TRUNCATE' ))? ')' 'WITHIN' 'GROUP' '(' orderBy? ')' filter? over?
        | qname '(' ( 'ALL' | 'DISTINCT' )? terms orderBy? ')' filter? over?
        // https://docs.oracle.com/en/database/oracle/oracle-database/12.2/sqlrf/NTH_VALUE.html
        | qname '('  terms ')' ( 'FROM' firstLast )? nullTreatment? over?
        | qname '(' 'DISTINCT'? terms ')' 'WITHIN' 'GROUP' '(' orderBy? ')' filter? over?
        | qname '('  terms ')' nullTreatment '(' orderBy? ')' filter?
        ;

    //    ( 'ON' 'OVERFLOW' ( 'ERROR' | 'TRUNCATE' name? withWithout 'COUNT' ))?
//          ( 'ON' 'OVERFLOW' 'ERROR' )?

    analyticFunction
        : id '(' signedNumber? ')' withinGroup? over?
        ;

    withinGroup
        : 'WITHIN' 'GROUP' '(' orderBy ')' ;

    filter
        : 'FILTER' '(' 'WHERE' term ')' ;

    over
        : 'OVER' window ;

    xmlAttrib
        : term ( 'AS' alias )? ;

    xmlContent
        : 'DOCUMENT' | 'CONTENT' ;

window
    : '(' name? partitionBy? orderBy? frameRange? ')'
    | name
    ;

    partitionBy
        : 'PARTITION' 'BY' terms ;

    frameRange
        : ( 'RANGE' | 'ROWS' | 'GROUPS' )
          ( 'BETWEEN' start=framePoint 'AND' )? end=framePoint
          ( 'EXCLUDE' ( 'CURRENT' 'ROW' | 'GROUP' | 'TIES' | 'NO' 'OTHERS' ) )?
        ;

        framePoint
            : ( term | 'UNBOUNDED' ) ( 'PRECEDING' | 'FOLLOWING' )
            | 'CURRENT' 'ROW'
            ;

orderBy
    : 'ORDER' 'BY' orderByTerm ( ',' orderByTerm )* ;

    orderByTerm
        : term sortDir? ( 'NULLS' firstLast )?
        ;

    sortDir
        : 'ASC'
        | 'DESC'
        | 'USING' compare // Postgres?
        ;

    jsonPairs
        : jsonPair ( ',' jsonPair )* ;

    jsonPair
        : jsonKey ':' term
        | 'KEY'? jsonKey 'VALUE' term
        ;

    jsonKey
        : 'NULL' | name ;


index
    : '[' ( term | term? ':' term? )? ']' ;

allDistinct
    : 'ALL' | 'DISTINCT' ;

firstLast
    : 'FIRST' | 'LAST' ;

formatJson
    : 'FORMAT' 'JSON' ;

onNull
    : ( 'NULL' | 'ABSENT' ) 'ON' 'NULL' ;

// TODO inline
nullTreatment
    : ( 'RESPECT' | 'IGNORE' ) 'NULLS' ;

rowRows
    : 'ROW' | 'ROWS' ;

timeZone
    : 'TIME' 'ZONE' ;

uniqueKeys
    : withWithout? 'UNIQUE' 'KEYS' ;

withTies
    : 'WITH' 'TIES' ;

withWithout
    : 'WITH' | 'WITHOUT' ;

// TODO inline rule name, DRY the misc alias
alias
    : name ;

qnames0
    : qname ( ',' qname )* ;

qnames
    : '(' qname ( ',' qname )* ')' ;

qname
    : name ( '.' name )* ( '.' '*' )? ;

names0
    : name ( ',' name )* ;

names
    : '(' name ( ',' name )* ')' ;

name
    : id
    | QUOTED // TODO try changing QUOTED token into parser rule
    | BACKTICKS
    | string
    | UNICODE_ID uescape?
    // T-SQL
    | brackets
    ;

brackets : '[' .*? ']' ;

string
    : STRING+
    | UNICODE_STRING STRING* uescape?
    | NATIONAL_STRING STRING*
    ;

    uescape
        : 'UESCAPE' STRING ;

// TODO rename from 'id' to 'keyword'
// Exclude everything but this grammar's tokens and ID tokens.
id :
    // exclude punctuation
    ~( EQ | COMPARE | ASSIGN | TILDE | MATCH  | POSTGRES_COMPARE | STARTS_WITH
    | '!' | '+' | '-' | '*' | '/' | '%' | '^'
    | '>>' | '<<' | '->' | '->>' | '|' | '||' | '&' | '&&'
    | '.' | ':' | '::' | ';'
    | '(' | ')' | '{' | '}' | ','
    | '['

    // exclude literals
    | UNICODE_ID
    | QUOTED
    | BACKTICKS
    | STRING
    | NATIONAL_STRING
    | UNICODE_STRING
    | BITS
    | BYTES
    | OCTALS
    | INTEGER
    | FLOAT
    | PARAMETER
    | VARIABLE
    | 'TRUE'
    | 'FALSE'

    // and exclude anything lexer doesn't recognize
    | OTHER
    )
    ;

// separate token because EQ is used both for assignments and comparisons. (TODO right?)
EQ      : '=' ;
ASSIGN  : ':=' | '+=' | '-=' | '*=' | '/=' | '%=' | '&=' | '^=' | '|=' ;
COMPARE : '==' | '<>' | '!=' | '<' | '<=' | '>' | '>=' | '&&' ;
TILDE   : '~' ;
MATCH   : '~*' | '!~' | '!~*' ;
STARTS_WITH : '^@' ;

// TODO gotta catch them all
// https://www.postgresql.org/docs/current/functions-array.html
// https://www.postgresql.org/docs/current/functions-range.html
// https://www.postgresql.org/docs/current/functions-geometry.html
POSTGRES_COMPARE
    : '@@' | '@>' | '@<' | '<->' | '&>' | '&<'
    ;

// TODO compare each dialect's rules for identifiers and strings. eg SQLite allows ':'?

UNICODE_STRING : 'U&' STRING ;

NATIONAL_STRING : [NE] STRING ;

STRING
    : '\'' ( ~'\'' | '\'\'' )* '\''
    // Postgres
    | '$$' .*? '$$'
    ;

BACKTICKS : '`' ( ~( '`' ) | '``' )* '`' ;

QUOTED : '"' ( ~( '"' ) | '""' )* '"' ;

ID : [A-Z_] [A-Z_0-9#$@]* ;

UNICODE_ID : 'U&' '"' ( ~( '"' ) | '""' )* '"' ;

BITS
    : '0b' [01]+
    | 'B' '\'' [01]+ '\''
    ;

BYTES
    : '0x' [A-F0-9]+
    | 'X' '\'' ( [A-F0-9] | ' ' | '\'\'' )* '\''
    ;

 OCTALS : '0o' [0-7]+ ;

// TODO support underscore (visual grouping) in numbers
INTEGER
    : DIGIT+ 'L'? ;

// matches "0.e1" or ".0e1", but not ".e1"
FLOAT
    : ( DIGIT+ ( '.' DIGIT* )? | '.' DIGIT+ ) ( 'E' [-+]? DIGIT+ )? [FD]?;

PARAMETER
    : '?' DIGIT* ;

fragment DIGIT
    : [0-9] ;

// TODO separate alts for each style & dialect
VARIABLE
    : [:$] ( INTEGER | ID )
    | '@' '@'? ID*
    ;

// \u000B line (vertical) tab
// \u000C form feed
WHITESPACE : [ \b\t\r\n\u000B\u000C]+ -> channel ( HIDDEN ) ;

COMMENT
//    : '--' .*? ( '\n' | EOF ) -> channel( HIDDEN ) ;
    // TODO is this better? cuz doesn't consume '\n' ?
    : '--' ~ [\r\n]* -> channel( HIDDEN ) ;

BLOCK_COMMENT
    : '/*' ( BLOCK_COMMENT | . )*? '*/' -> channel( HIDDEN ) ;

OTHER : . ;

// TODO BOZO this crude OPERATOR token accepts way more than spec'd

// Postgres 4.1.3 https://www.postgresql.org/docs/current/sql-syntax-lexical.html#SQL-SYNTAX-OPERATORS
//OPERATOR
////    : ( '+' | '-' | [*/<>=~!@#%^&|`?] )+
////    : ( '+' | '-' | [*/<>=~!@#%^&|`] )+
//    :
//     { operatorEnabled }?
//    [*/<>=!@#%^&|`]+
//    ;
