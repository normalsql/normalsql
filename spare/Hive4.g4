/**
   Licensed to the Apache Software Foundation ( ASF ) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   ( the "License" ); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
grammar Hive4;

options { caseInsensitive = true; }

aaa1 : select ';'? EOF ;
a2 : term EOF ;

// starting rule
statement
    : ( explainStatement | execStatement )
    ;

explainStatement
    : 'EXPLAIN' ( 
        explainOption* execStatement
        | 'REWRITE' queryStatementExpression
       )
    ;

explainOption
    : 'EXTENDED'
    | 'FORMATTED'
    | 'DEPENDENCY'
    | 'CBO' ( 'COST' | 'JOINCOST' )?
    | 'LOGICAL'
    | 'AUTHORIZATION'
    | 'ANALYZE'
    | 'REOPTIMIZATION'
    | 'LOCKS'
    | 'AST'
    | 'VECTORIZATION' vectorizationOnly? vectorizatonDetail?
    | 'DEBUG'
    | 'DDL'
    ;

vectorizationOnly
    : 'ONLY'
    ;

vectorizatonDetail
    : 'SUMMARY'
    | 'OPERATOR'
    | 'EXPRESSION'
    | 'DETAIL'
    ;

execStatement
    : queryStatementExpression
    | loadStatement
    | exportStatement
    | importStatement
    | replDumpStatement
    | replLoadStatement
    | replStatusStatement
    | ddlStatement
    | deleteStatement
    | updateStatement
    | sqlTransactionStatement
    | mergeStatement
    | prepareStatement
    | executeStatement
    ;

loadStatement
    : 'LOAD' 'DATA' 'LOCAL'? 'INPATH' StringLiteral 'OVERWRITE'? 'INTO' 'TABLE' tableOrPartition inputFileFormat?
    ;

replicationClause
    : 'FOR' 'METADATA'? 'REPLICATION' '(' StringLiteral ')'
    ;

exportStatement
    : 'EXPORT'
      'TABLE' tableOrPartition
      'TO' StringLiteral
      replicationClause?
    ;

importStatement
       : 'IMPORT'
         ( 'EXTERNAL'? 'TABLE' tableOrPartition )?
         'FROM' StringLiteral
         tableLocation?
    ;

replDumpStatement
    : 'REPL' 'DUMP'
        replDbPolicy
        ( 'REPLACE' replDbPolicy )?
        ( 'WITH' replConfigs )?
    ;

replDbPolicy
    : id ( '.' replTableLevelPolicy )?
    ;

replLoadStatement
    : 'REPL' 'LOAD'
      replDbPolicy
      ( 'INTO' id )?
      ( 'WITH' replConfigs )?
    ;

replConfigs
    : '(' replConfigsList ')'
    ;

replConfigsList
    : keyValueProperty ( ',' keyValueProperty )*
    ;

replTableLevelPolicy
    : StringLiteral ( '.' StringLiteral )?
    ;

replStatusStatement
    : 'REPL' 'STATUS' id ( 'WITH' replConfigs )? ;

ddlStatement
    : createDatabaseStatement
    | switchDatabaseStatement
    | dropDatabaseStatement
    | createTableStatement
    | dropTableStatement
    | truncateTableStatement
    | alterStatement
    | descStatement
    | showStatement
    | metastoreCheck
    | createViewStatement
    | createMaterializedViewStatement
    | createScheduledQueryStatement
    | alterScheduledQueryStatement
    | dropScheduledQueryStatement
    | dropViewStatement
    | dropMaterializedViewStatement
    | createFunctionStatement
    | createMacroStatement
    | dropFunctionStatement
    | reloadFunctionsStatement
    | dropMacroStatement
    | analyzeStatement
    | lockStatement
    | unlockStatement
    | lockDatabase
    | unlockDatabase
    | createRoleStatement
    | dropRoleStatement
    | grantPrivileges
    | revokePrivileges
    | showGrants
    | showRoleGrants
    | showRolePrincipals
    | showRoles
    | grantRole
    | revokeRole
    | setRole
    | showCurrentRole
    | abortTransactionStatement
    | abortCompactionStatement
    | killQueryStatement
    | resourcePlanDdlStatements
    | createDataConnectorStatement
    | dropDataConnectorStatement
    ;

ifExists
    : 'IF' 'EXISTS'
    ;

restrictOrCascade
    : 'RESTRICT'
    | 'CASCADE'
    ;

ifNotExists
    : 'IF' 'NOT' 'EXISTS'
    ;

force
    : 'FORCE'
    ;

rewriteEnabled
    : 'ENABLE' 'REWRITE'
    ;

rewriteDisabled
    : 'DISABLE' 'REWRITE'
    ;

storedAsDirs
    : 'STORED' 'AS' 'DIRECTORIES'
    ;

orReplace
    : 'OR' 'REPLACE'
    ;

createDatabaseStatement
    : 'CREATE' db_schema
        ifNotExists?
        id
        databaseComment?
        dbLocation?
        dbManagedLocation?
        ( 'WITH' 'DBPROPERTIES' dbProperties )?
    | 'CREATE' 'REMOTE' db_schema
        ifNotExists?
        id
        databaseComment?
        dbConnectorName
        ( 'WITH' 'DBPROPERTIES' dbProperties )?
    ;

dbLocation
    : 'LOCATION' StringLiteral
    ;

dbManagedLocation
    : 'MANAGEDLOCATION' StringLiteral
    ;

dbProperties
    : '(' dbPropertiesList ')'
    ;

dbPropertiesList
    : keyValueProperty ( ',' keyValueProperty )*
    ;

dbConnectorName
    : 'USING' id
    ;

switchDatabaseStatement
    : 'USE' id
    ;

dropDatabaseStatement
    : 'DROP' db_schema ifExists? id restrictOrCascade?
    ;

databaseComment
    : 'COMMENT' StringLiteral
    ;

truncateTableStatement
    : 'TRUNCATE' 'TABLE'? tablePartitionPrefix ( 'COLUMNS' '(' columnNameList ')' )? force?
    ;

dropTableStatement
    : 'DROP' 'TABLE' ifExists? tableName 'PURGE'? replicationClause?
    ;

inputFileFormat
    : 'INPUTFORMAT' StringLiteral 'SERDE' StringLiteral
    ;

tabTypeExpr
    : id ( '.' id )?
        ( id ( '.' ( 'ELEM_TYPE' | 'KEY_TYPE' | 'VALUE_TYPE' | id ) )* )?
    ;

partTypeExpr
    : tabTypeExpr partitionSpec?
    ;

tabPartColTypeExpr
    : tableName partitionSpec? extColumnName?
    ;

descStatement
    : ( 'DESCRIBE' | 'DESC' )
    ( 
        db_schema 'EXTENDED'? id
        | 'DATACONNECTOR' 'EXTENDED'? id
        | 'FUNCTION' 'EXTENDED'? descFuncNames
        | ( 'FORMATTED' | 'EXTENDED' ) tabPartColTypeExpr
        | tabPartColTypeExpr
     )
    ;

analyzeStatement
    : 'ANALYZE' 'TABLE' tableOrPartition
      (  'COMPUTE' 'STATISTICS' ( 'NOSCAN' | 'FOR' 'COLUMNS' columnNameList? )?
      | 'CACHE' 'METADATA'
       )
    ;

from_in
    : 'FROM'
    | 'IN'
    ;

db_schema
    : 'DATABASE'
    | 'SCHEMA'
    ;

showStatement
    : 'SHOW' ( 'DATABASES' | 'SCHEMAS' ) ( 'LIKE' showStmtIdentifier )?
    | 'SHOW' 'EXTENDED'? 'TABLES' ( from_in id )? showTablesFilterExpr?
    | 'SHOW' 'VIEWS' ( from_in id )? ( 'LIKE' showStmtIdentifier | showStmtIdentifier )?
    | 'SHOW' 'MATERIALIZED' 'VIEWS' ( from_in id )? ( 'LIKE' showStmtIdentifier|showStmtIdentifier )?
    | 'SHOW' 'SORTED'? 'COLUMNS' from_in tableName ( from_in id )? ( 'LIKE' showStmtIdentifier|showStmtIdentifier )?
    | 'SHOW' 'FUNCTIONS' ( 'LIKE' showFunctionIdentifier )?
    | 'SHOW' 'PARTITIONS' tableName partitionSpec? whereClause? orderByClause? limitClause?
    | 'SHOW' 'CREATE' ( db_schema id | 'TABLE' tableName )
    | 'SHOW' 'TABLE' 'EXTENDED' ( from_in id )? 'LIKE' showStmtIdentifier partitionSpec?
    | 'SHOW' 'TBLPROPERTIES' tableName ( '(' StringLiteral ')' )?
    | 'SHOW' 'LOCKS' ( db_schema id 'EXTENDED'? | partTypeExpr? 'EXTENDED'? )
    | 'SHOW' 'COMPACTIONS'
      (  compactionId
      | db_schema id compactionPool? compactionType? compactionStatus? orderByClause? limitClause?
      | partTypeExpr? compactionPool? compactionType? compactionStatus? orderByClause? limitClause?
       )
    | 'SHOW' 'TRANSACTIONS'
    | 'SHOW' 'CONF' StringLiteral
    | 'SHOW' 'RESOURCE' ( 'PLAN' id | 'PLANS' )
    | 'SHOW' 'DATACONNECTORS'
    ;

showTablesFilterExpr
    : 'WHERE' id EQUAL StringLiteral
    | 'LIKE' showStmtIdentifier
    | showStmtIdentifier
    ;

lockStatement
    : 'LOCK' 'TABLE' tableName partitionSpec? lockMode
    ;

lockDatabase
    : 'LOCK' db_schema id lockMode
    ;

lockMode
    : 'SHARED'
    | 'EXCLUSIVE'
    ;

unlockStatement
    : 'UNLOCK' 'TABLE' tableName partitionSpec?
    ;

unlockDatabase
    : 'UNLOCK' db_schema id
    ;

createRoleStatement
    : 'CREATE' 'ROLE' id
    ;

dropRoleStatement
    : 'DROP' 'ROLE' id
    ;

grantPrivileges
    : 'GRANT' privilegeList
      privilegeObject?
      'TO' principalSpecification
      withGrantOption?
    ;

revokePrivileges
    : 'REVOKE' grantOptionFor? privilegeList privilegeObject? 'FROM' principalSpecification
    ;

grantRole
    : 'GRANT' 'ROLE'? id ( ',' id )* 'TO' principalSpecification withAdminOption?
    ;

revokeRole
    : 'REVOKE' adminOptionFor? 'ROLE'? id ( ',' id )* 'FROM' principalSpecification
    ;

showRoleGrants
    : 'SHOW' 'ROLE' 'GRANT' principalName
    ;

showRoles
    : 'SHOW' 'ROLES'
    ;

showCurrentRole
    : 'SHOW' 'CURRENT' 'ROLES'
    ;

setRole
    : 'SET' 'ROLE' ( 'ALL' | 'NONE' | id )
    ;

showGrants
    : 'SHOW' 'GRANT' principalName? ( 'ON' privilegeIncludeColObject )?
    ;

showRolePrincipals
    : 'SHOW' 'PRINCIPALS' id
    ;

privilegeIncludeColObject
    : 'ALL'
    | privObjectCols
    ;

privilegeObject
    : 'ON' privObject
    ;

/**
database or table type. Type is optional, default type is table
*/
privObject
    : db_schema id
    | 'TABLE'? tableName partitionSpec?
    | 'URI' StringLiteral
    | 'SERVER' id
    ;

privObjectCols
    : db_schema id
    | 'TABLE'? tableName ( '(' columnNameList ')' )? partitionSpec?
    | 'URI' StringLiteral
    | 'SERVER' id
    ;

privilegeList
    : privlegeDef ( ',' privlegeDef )*
    ;

privlegeDef
    : privilegeType ( '(' columnNameList ')' )?
    ;

privilegeType
    : 'ALL'
    | 'ALTER'
    | 'UPDATE'
    | 'CREATE'
    | 'DROP'
    | 'LOCK'
    | 'SELECT'
    | 'SHOW_DATABASE'
    | 'INSERT'
    | 'DELETE'
    ;

principalSpecification
    : principalName ( ',' principalName )*
    ;

principalName
    : 'USER' principalIdentifier
    | 'GROUP' principalIdentifier
    | 'ROLE' id
    ;

withGrantOption
    : 'WITH' 'GRANT' 'OPTION'
    ;

grantOptionFor
    : 'GRANT' 'OPTION' 'FOR'
    ;

adminOptionFor
    : 'ADMIN' 'OPTION' 'FOR'
    ;

withAdminOption
    : 'WITH' 'ADMIN' 'OPTION'
    ;

metastoreCheck
    : 'MSCK' 'REPAIR'? 'TABLE' tableName
      ( ( 'ADD' | 'DROP' | 'SYNC' ) 'PARTITIONS' partitionSelectorSpec? )?
    ;

resources
    :  resource ( ',' resource )*
    ;

resource
    : ('JAR' | 'FILE' | 'ARCHIVE') StringLiteral
    ;

createFunctionStatement
    : 'CREATE' 'TEMPORARY'? 'FUNCTION' functionIdentifier 'AS' StringLiteral
      ( 'USING' resources )?
    ;

dropFunctionStatement
    : 'DROP' 'TEMPORARY'? 'FUNCTION' ifExists? functionIdentifier
    ;

reloadFunctionsStatement
    : 'RELOAD' ( 'FUNCTIONS' | 'FUNCTION' )
    ;

createMacroStatement
    : 'CREATE' 'TEMPORARY' 'MACRO' ID
      '(' columnNameTypeList? ')' term
    ;

dropMacroStatement
    : 'DROP' 'TEMPORARY' 'MACRO' ifExists? ID
    ;

createViewStatement
    : 'CREATE' orReplace? 'VIEW' ifNotExists? tableName
        ( '(' columnNameCommentList ')' )? tableComment? viewPartition?
        tablePropertiesPrefixed? 'AS' selectStatementWithCTE
    ;

viewPartition
    : 'PARTITIONED' 'ON' ( '(' columnNameList | 'SPEC' '(' partitionTransformSpec ) ')'
    ;

viewOrganization
    : 'CLUSTERED' 'ON' '(' columnNameList ')'
    | 'DISTRIBUTED' 'ON' '(' columnNameList ')' 'SORTED' 'ON' '(' columnNameList ')'
    ;

dropViewStatement
    : 'DROP' 'VIEW' ifExists? viewName
    ;

createMaterializedViewStatement
    : 'CREATE' 'MATERIALIZED' 'VIEW' ifNotExists? tableName
        rewriteDisabled? tableComment? viewPartition? viewOrganization?
        tableRowFormat? tableFileFormat? tableLocation?
        tablePropertiesPrefixed? 'AS' selectStatementWithCTE
    ;

dropMaterializedViewStatement
    : 'DROP' 'MATERIALIZED' 'VIEW' ifExists? viewName
    ;

createScheduledQueryStatement
    : 'CREATE' 'SCHEDULED' 'QUERY' id
        scheduleSpec
        executedAsSpec?
        enableSpecification?
        definedAsSpec
    ;

dropScheduledQueryStatement
    : 'DROP' 'SCHEDULED' 'QUERY' id
    ;

alterScheduledQueryStatement
    : 'ALTER' 'SCHEDULED' 'QUERY' id alterScheduledQueryChange
    ;

alterScheduledQueryChange
    : scheduleSpec
    | executedAsSpec
    | enableSpecification
    | definedAsSpec
    | 'EXECUTE'
    ;

scheduleSpec
    : 'CRON' StringLiteral
    | 'EVERY' Number? intervalQualifiers
        ( ( 'AT' | 'OFFSET' 'BY' ) StringLiteral )?
    ;

executedAsSpec
    : 'EXECUTED' 'AS' StringLiteral
    ;

definedAsSpec
    : 'DEFINED'? 'AS' statement
    ;

showFunctionIdentifier
    : functionIdentifier
    | StringLiteral
    ;

showStmtIdentifier
    : id
    | StringLiteral
    ;

tableComment
    : 'COMMENT' StringLiteral
    ;

createTablePartitionSpec
    : 'PARTITIONED' 'BY'
        (  '(' ( createTablePartitionColumnTypeSpec | createTablePartitionColumnSpec )
        | 'SPEC' '(' partitionTransformSpec
         ) ')'
    ;

createTablePartitionColumnTypeSpec
    : columnNameTypeConstraint ( ',' columnNameTypeConstraint )*
    ;

createTablePartitionColumnSpec
    : columnName ( ',' columnName )*
    ;

partitionTransformSpec
    : columnNameTransformConstraint ( ',' columnNameTransformConstraint )*
    ;

columnNameTransformConstraint
    : partitionTransformType
    ;

partitionTransformType
    : columnName
    | ( 'YEAR' | 'MONTH' | 'DAY' | 'HOUR' ) '(' columnName ')'
    | ( 'TRUNCATE' | 'BUCKET' ) '(' Number ',' columnName ')'
    ;

tableBuckets
    : 'CLUSTERED' 'BY' '(' columnNameList ')'
        ( 'SORTED' 'BY' '(' columnNameOrderList ')' )? 'INTO' Number 'BUCKETS'
    ;

tableImplBuckets
    : 'CLUSTERED' 'INTO' Number 'BUCKETS'
    ;

tableSkewed
    : 'SKEWED' 'BY' '(' columnNameList ')' 'ON' '(' skewedValueElement ')' storedAsDirs?
    ;

rowFormat
    : rowFormatSerde
    | rowFormatDelimited
    ;

recordReader
    : 'RECORDREADER' StringLiteral
    ;

recordWriter
    : 'RECORDWRITER' StringLiteral
    ;

rowFormatSerde
    : 'ROW' 'FORMAT' 'SERDE' StringLiteral ( 'WITH' 'SERDEPROPERTIES' tableProperties )?
    ;

rowFormatDelimited
    : 'ROW' 'FORMAT' 'DELIMITED' tableRowFormatFieldIdentifier? tableRowFormatCollItemsIdentifier?
        tableRowFormatMapKeysIdentifier? tableRowFormatLinesIdentifier? tableRowNullFormat?
    ;

tableRowFormat
    : rowFormatDelimited
    | rowFormatSerde
    ;

tablePropertiesPrefixed
    : 'TBLPROPERTIES' tableProperties
    ;

tableProperties
    : '(' tablePropertiesList ')'
    ;

tablePropertiesList
    : keyValueProperty ( ',' keyValueProperty )*
    | keyProperty ( ',' keyProperty )*
    ;

keyValueProperty
    : StringLiteral EQUAL StringLiteral
    ;

keyProperty
    : StringLiteral
    ;

tableRowFormatFieldIdentifier
    : 'FIELDS' 'TERMINATED' 'BY' StringLiteral ( 'ESCAPED' 'BY' StringLiteral )?
    ;

tableRowFormatCollItemsIdentifier
    : 'COLLECTION' 'ITEMS' 'TERMINATED' 'BY' StringLiteral
    ;

tableRowFormatMapKeysIdentifier
    : 'MAP' 'KEYS' 'TERMINATED' 'BY' StringLiteral
    ;

tableRowFormatLinesIdentifier
    : 'LINES' 'TERMINATED' 'BY' StringLiteral
    ;

tableRowNullFormat
    : 'NULL' 'DEFINED' 'AS' StringLiteral
    ;

tableFileFormat
    : 'STORED' 'AS' 'INPUTFORMAT'
        StringLiteral 'OUTPUTFORMAT'
        StringLiteral
        ( 'INPUTDRIVER' StringLiteral 'OUTPUTDRIVER' StringLiteral )?
    | 'STORED' 'BY' StringLiteral
        ( 'WITH' 'SERDEPROPERTIES' tableProperties )?
        ( 'STORED' 'AS' id )?
    | 'STORED' 'BY' id
        ( 'WITH' 'SERDEPROPERTIES' tableProperties )?
        ( 'STORED' 'AS' id )?
    | 'STORED' 'AS' id
    ;

tableLocation
    : 'LOCATION' StringLiteral
    ;

columnNameTypeList
    : columnNameType ( ',' columnNameType )*
    ;

columnNameTypeOrConstraintList
    : columnNameTypeOrConstraint ( ',' columnNameTypeOrConstraint )*
    ;

columnNameColonTypeList
    : columnNameColonType ( ',' columnNameColonType )*
    ;

columnNameList
    : columnName ( ',' columnName )*
    ;

columnName
    : id
    ;

extColumnName
    : id ( '.' ( 'ELEM_TYPE' | 'KEY_TYPE' | 'VALUE_TYPE' | id ) )*
    ;

columnNameOrderList
    : columnNameOrder ( ',' columnNameOrder )*
    ;

columnParenthesesList
    : '(' columnNameList ')'
    ;

enableSpecification
    : 'ENABLE'
    | 'DISABLE'
    ;

createConstraint
    : ( 'CONSTRAINT' id )? tableLevelConstraint constraintOptions?
    ;

alterConstraintWithName
    : 'CONSTRAINT' id tableLevelConstraint constraintOptions?
    ;

tableLevelConstraint
    : pkUkConstraint
    | checkConstraint
    ;

pkUkConstraint
    : tableConstraintType columnParenthesesList
    ;

checkConstraint
    : 'CHECK' '(' term ')'
    ;

createForeignKey
    : ( 'CONSTRAINT' id )? 'FOREIGN' 'KEY' columnParenthesesList
        'REFERENCES' tableName columnParenthesesList constraintOptions?
    ;

alterForeignKeyWithName
    : 'CONSTRAINT' id 'FOREIGN' 'KEY' columnParenthesesList
        'REFERENCES' tableName columnParenthesesList constraintOptions?
    ;

skewedValueElement
    : skewedColumnValues
    | skewedColumnValuePairList
    ;

skewedColumnValuePairList
    : skewedColumnValuePair ( ',' skewedColumnValuePair )*
    ;

skewedColumnValuePair
    : '(' skewedColumnValues ')'
    ;

skewedColumnValues
    : skewedColumnValue ( ',' skewedColumnValue )*
    ;

skewedColumnValue
    : literal
    ;

skewedValueLocationElement
    : skewedColumnValue
    | skewedColumnValuePair
    ;

orderSpecification
    : 'ASC'
    | 'DESC'
    ;

nullOrdering
    : 'NULLS' ( 'FIRST' | 'LAST' )
    ;

columnNameOrder
    : id orderSpecification? nullOrdering?
    ;

columnNameCommentList
    : columnNameComment ( ',' columnNameComment )*
    ;

columnNameComment
    : id ( 'COMMENT' StringLiteral )?
    ;

orderSpecificationRewrite
    : 'ASC'
    | 'DESC'
    ;

columnRefOrder
    : term orderSpecificationRewrite? nullOrdering?
    ;

columnNameType
    : id colType ( 'COMMENT' StringLiteral )?
    ;

columnNameTypeOrConstraint
    : tableConstraint
    | columnNameTypeConstraint
    ;

tableConstraint
    : createForeignKey
    | createConstraint
    ;

columnNameTypeConstraint
    : id colType columnConstraint? ( 'COMMENT' StringLiteral )?
    ;

columnConstraint
    : foreignKeyConstraint
    | colConstraint
    ;

foreignKeyConstraint
    : ( 'CONSTRAINT' id )? 'REFERENCES' tableName '(' columnName ')' constraintOptions?
    ;

colConstraint
    : ( 'CONSTRAINT' id )? columnConstraintType constraintOptions?
    ;

alterColumnConstraint
    : alterForeignKeyConstraint
    | alterColConstraint
    ;

alterForeignKeyConstraint
    : ( 'CONSTRAINT' id )? 'REFERENCES' tableName '(' columnName ')' constraintOptions?
    ;

alterColConstraint
    : ( 'CONSTRAINT' id )? columnConstraintType constraintOptions?
    ;

columnConstraintType
    : 'NOT' 'NULL'
    | 'DEFAULT' defaultVal
    | checkConstraint
    | tableConstraintType
    ;

defaultVal
    : literal
    | function
    | castExpression
    ;

tableConstraintType
    : 'PRIMARY' 'KEY'
    | 'UNIQUE'
    ;

constraintOptions
    : ( enableSpecification ( 'VALIDATE' | 'NOVALIDATE' )?
      | 'NOT'? 'ENFORCED'
      )
      ( 'RELY' | 'NORELY' )?
    ;

columnNameColonType
    : id ':' colType ( 'COMMENT' StringLiteral )?
    ;

colType
    : type
    ;

colTypeList
    : colType ( ',' colType )*
    ;

type
    : primitiveType
    | listType
    | structType
    | mapType
    | unionType;

primitiveType
    : 'TINYINT'
    | 'SMALLINT'
    | 'INT'
    | 'BIGINT'
    | 'BOOLEAN'
    | 'FLOAT'
    | 'REAL'
    | 'DOUBLE' 'PRECISION'?
    | 'DATE'
    | 'DATETIME'
    | 'TIMESTAMP'
    | 'TIMESTAMPLOCALTZ'
    //| 'TIMESTAMPTZ'
    | 'TIMESTAMP' 'WITH' 'LOCAL' 'TIME' 'ZONE'
    //| 'TIMESTAMP' 'WITH' 'TIME' 'ZONE'
    // Uncomment to allow intervals as table column types
    //| 'INTERVAL' 'YEAR' 'TO' 'MONTH'
    //| 'INTERVAL' 'DAY' 'TO' 'SECOND'
    | 'STRING'
    | 'BINARY'
    | 'DECIMAL' ( '(' Number ( ',' Number )? ')' )?
    | ( 'VARCHAR' | 'CHAR' ) '(' Number ')'
    ;

listType
    : 'ARRAY' LESSTHAN type GREATERTHAN
    ;

structType
    : 'STRUCT' LESSTHAN columnNameColonTypeList GREATERTHAN
    ;

mapType
    : 'MAP' LESSTHAN primitiveType ',' type GREATERTHAN
    ;

unionType
    : 'UNIONTYPE' LESSTHAN colTypeList GREATERTHAN
    ;

setOperator
    : ( 'UNION' | 'INTERSECT' | 'EXCEPT' | 'MINUS' ) ( 'ALL' | 'DISTINCT' )?
    ;

queryStatementExpression
    /* Would be nice to do this as a gated semantic perdicate
       But the predicate gets pushed as a lookahead decision.
       Calling rule doesnot know about topLevel
    */
    : withClause? queryStatementExpressionBody
    ;

queryStatementExpressionBody
    : fromStatement
    | regularBody
    ;

withClause
    : 'WITH' cteStatement ( ',' cteStatement )*
    ;

cteStatement
    : id ( '(' columnNameList ')' )? 'AS' '(' queryStatementExpression ')'
    ;

fromStatement
    : singleFromStatement ( setOperator singleFromStatement )*
    ;

singleFromStatement
    : fromClause body+
    ;

/*
The valuesClause rule below ensures that the parse tree for
"insert into table FOO values ( 1,2 ),( 3,4 )" looks the same as
"insert into table FOO select a,b from ( values( 1,2 ),( 3,4 ) ) as BAR( a,b )" which itself is made to look
very similar to the tree for "insert into table FOO select a,b from BAR".
*/
regularBody
    : insertClause select
    | select
    ;

select
    : selectCore ( setOperator selectCore )*
      orderByClause?
      clusterByClause?
      distributeByClause?
      sortByClause?
      limitClause?
    ;

selectCore
//    : selectClause
    : 'SELECT' all_distinct? item ( ',' item )*
      fromClause?
      whereClause?
      groupByClause?
      havingClause?
      window_clause?
      qualifyClause?
    | '(' select ')'
    | valuesClause
    ;

selectStatementWithCTE
    : withClause? select
    ;

body
    : insertClause? selectClause lateralView? whereClause? groupByClause? havingClause? window_clause? qualifyClause? orderByClause? clusterByClause? distributeByClause? sortByClause? limitClause?
    ;

insertClause
    : 'INSERT' ( 'OVERWRITE' destination ifNotExists?
                | 'INTO' 'TABLE'? tableOrPartition ( '(' columnNameList ')' )?
                 )
    ;

destination
    : 'LOCAL'? 'DIRECTORY' StringLiteral tableRowFormat? tableFileFormat?
    | 'TABLE' tableOrPartition
    ;

limitClause
    : 'LIMIT' ( ( Number ',' )? Number | Number 'OFFSET' Number )
    ;

deleteStatement
    : 'DELETE' 'FROM' tableName whereClause?
    ;

columnAssignmentClause
    : id EQUAL ( 'DEFAULT' | subterm )
    ;

setColumnsClause
    : 'SET' columnAssignmentClause ( ',' columnAssignmentClause )*
    ;

updateStatement
    : 'UPDATE' tableName setColumnsClause whereClause?
    ;

/*
BEGIN user defined transaction boundaries; follows SQL 2003 standard exactly except for addition of
"setAutoCommitStatement" which is not in the standard doc but is supported by most SQL engines.
*/
sqlTransactionStatement
    : startTransactionStatement
    | commitStatement
    | rollbackStatement
    | setAutoCommitStatement
    ;

startTransactionStatement
    : 'START' 'TRANSACTION' ( transactionMode ( ',' transactionMode )*  )?
    ;

transactionMode
    : isolationLevel
    | transactionAccessMode
    ;

transactionAccessMode
    : 'READ' ( 'ONLY' | 'WRITE' )
    ;

isolationLevel
    : 'ISOLATION' 'LEVEL' 'SNAPSHOT'
    ;

/*READ UNCOMMITTED | READ COMMITTED | REPEATABLE READ | SERIALIZABLE may be supported later*/
commitStatement
    : 'COMMIT' 'WORK'?
    ;

rollbackStatement
    : 'ROLLBACK' 'WORK'?
    ;

setAutoCommitStatement
    : 'SET' 'AUTOCOMMIT' booleanValue
    ;
/*
END user defined transaction boundaries
*/

abortTransactionStatement
    : 'ABORT' 'TRANSACTIONS' Number+
    ;

abortCompactionStatement
    : 'ABORT' 'COMPACTIONS' Number+
    ;

/*
BEGIN SQL Merge statement
*/
mergeStatement
    : 'MERGE' QUERY_HINT? 'INTO' tableName ( 'AS'? id )? 'USING' joinSourcePart 'ON' term whenClauses
    ;
/*
Allow 0,1 or 2 WHEN MATCHED clauses and 0 or 1 WHEN NOT MATCHED
Each WHEN clause may have AND <boolean predicate>.
If 2 WHEN MATCHED clauses are present, 1 must be UPDATE the other DELETE and the 1st one
must have AND <boolean predicate>
*/
whenClauses
    : ( whenMatchedAndClause | whenMatchedThenClause )* whenNotMatchedClause?
    ;

whenNotMatchedClause
    : 'WHEN' 'NOT' 'MATCHED' ( 'AND' term )? 'THEN' 'INSERT' columnParenthesesList? 'VALUES' expressionsNested
    ;

whenMatchedAndClause
    : 'WHEN' 'MATCHED' 'AND' term 'THEN' updateOrDelete
    ;

whenMatchedThenClause
    : 'WHEN' 'MATCHED' 'THEN' updateOrDelete
    ;

updateOrDelete
    : 'UPDATE' setColumnsClause
    | 'DELETE'
    ;
/*
END SQL Merge statement
*/

killQueryStatement
    : 'KILL' 'QUERY' StringLiteral+
    ;

/*
BEGIN SHOW COMPACTIONS statement
*/
compactionId
    : 'COMPACT_ID' EQUAL Number
    ;

compactionPool
    : 'POOL' StringLiteral
    ;

compactionType
    : 'TYPE' StringLiteral
    ;

compactionStatus
    : 'STATUS' StringLiteral
    ;

/*
END SHOW COMPACTIONS statement
*/

alterStatement
    : 'ALTER' (  'TABLE' tableName alterTableStatementSuffix
               | 'VIEW' tableName 'AS'? alterViewStatementSuffix
               | 'MATERIALIZED' 'VIEW' tableName alterMaterializedViewStatementSuffix
               | db_schema alterDatabaseStatementSuffix
               | 'DATACONNECTOR' alterDataConnectorStatementSuffix
                )
    ;

alterTableStatementSuffix
    : alterStatementSuffixRename
    | alterStatementSuffixDropPartitions
    | alterStatementSuffixAddPartitions
    | alterStatementSuffixTouch
    | alterStatementSuffixArchive
    | alterStatementSuffixUnArchive
    | alterStatementSuffixProperties
    | alterStatementSuffixSkewedby
    | alterStatementSuffixExchangePartition
    | alterStatementPartitionKeyType
    | alterStatementSuffixDropConstraint
    | alterStatementSuffixAddConstraint
    | alterTblPartitionStatementSuffix
    | partitionSpec alterTblPartitionStatementSuffix
    | alterStatementSuffixSetOwner
    | alterStatementSuffixSetPartSpec
    | alterStatementSuffixExecute
    ;

alterTblPartitionStatementSuffix
    : alterStatementSuffixFileFormat
    | alterStatementSuffixLocation
    | alterStatementSuffixMergeFiles
    | alterStatementSuffixSerdeProperties
    | alterStatementSuffixRenamePart
    | alterStatementSuffixBucketNum
    | alterTblPartitionStatementSuffixSkewedLocation
    | alterStatementSuffixClusterbySortby
    | alterStatementSuffixCompact
    | alterStatementSuffixUpdateStatsCol
    | alterStatementSuffixUpdateStats
    | alterStatementSuffixRenameCol
    | alterStatementSuffixAddCol
    | alterStatementSuffixUpdateColumns
    ;

alterStatementPartitionKeyType
    : 'PARTITION' 'COLUMN' '(' columnNameType ')'
    ;

alterViewStatementSuffix
    : alterViewSuffixProperties
    | alterStatementSuffixRename
    | alterStatementSuffixAddPartitions
    | alterStatementSuffixDropPartitions
    | selectStatementWithCTE
    ;

alterMaterializedViewStatementSuffix
    : alterMaterializedViewSuffixRewrite
    | 'REBUILD'
    ;

alterMaterializedViewSuffixRewrite
    : rewriteEnabled
    | rewriteDisabled
    ;

alterDatabaseStatementSuffix
    : alterDatabaseSuffixProperties
    | alterDatabaseSuffixSetOwner
    | alterDatabaseSuffixSetLocation
    ;

alterDatabaseSuffixProperties
    : id 'SET' 'DBPROPERTIES' dbProperties
    ;

alterDatabaseSuffixSetOwner
    : id 'SET' 'OWNER' principalName
    ;

alterDatabaseSuffixSetLocation
    : id 'SET' ( 'LOCATION' | 'MANAGEDLOCATION' ) StringLiteral
    ;

alterDatabaseSuffixSetManagedLocation
    : id 'SET' 'MANAGEDLOCATION' StringLiteral
    ;

alterStatementSuffixRename
    : 'RENAME' 'TO' tableName
    ;

alterStatementSuffixAddCol
    : ( 'ADD' | 'REPLACE' ) 'COLUMNS' '(' columnNameTypeList ')' restrictOrCascade?
    ;

alterStatementSuffixAddConstraint
    :  'ADD' ( alterForeignKeyWithName | alterConstraintWithName )
    ;

alterStatementSuffixUpdateColumns
    : 'UPDATE' 'COLUMNS' restrictOrCascade?
    ;

alterStatementSuffixDropConstraint
    : 'DROP' 'CONSTRAINT' id
    ;

alterStatementSuffixRenameCol
    : 'CHANGE' 'COLUMN'? id id colType alterColumnConstraint?
        ( 'COMMENT' StringLiteral )? alterStatementChangeColPosition? restrictOrCascade?
    ;

alterStatementSuffixUpdateStatsCol
    : 'UPDATE' 'STATISTICS' 'FOR' 'COLUMN'? id 'SET' tableProperties ( 'COMMENT' StringLiteral )?
    ;

alterStatementSuffixUpdateStats
    : 'UPDATE' 'STATISTICS' 'SET' tableProperties
    ;

alterStatementChangeColPosition
    : 'FIRST'
    | 'AFTER' id
    ;

alterStatementSuffixAddPartitions
    : 'ADD' ifNotExists? alterStatementSuffixAddPartitionsElement+
    ;

alterStatementSuffixAddPartitionsElement
    : partitionSpec ( 'LOCATION' StringLiteral )?
    ;

alterStatementSuffixTouch
    : 'TOUCH' partitionSpec*
    ;

alterStatementSuffixArchive
    : 'ARCHIVE' partitionSpec*
    ;

alterStatementSuffixUnArchive
    : 'UNARCHIVE' partitionSpec*
    ;

alterStatementSuffixDropPartitions
    : 'DROP' ifExists? 'PARTITION' partitionSelectorSpec ( ',' 'PARTITION' partitionSelectorSpec )* 'PURGE'? replicationClause?
    ;

alterStatementSuffixProperties
    : 'SET' 'TBLPROPERTIES' tableProperties
    | 'UNSET' 'TBLPROPERTIES' ifExists? tableProperties
    ;

alterViewSuffixProperties
    : 'SET' 'TBLPROPERTIES' tableProperties
    | 'UNSET' 'TBLPROPERTIES' ifExists? tableProperties
    ;

alterStatementSuffixSerdeProperties
    : 'SET'
      ( 'SERDE' StringLiteral ( 'WITH' 'SERDEPROPERTIES' tableProperties )?
      | 'SERDEPROPERTIES' tableProperties
      )
    | 'UNSET' 'SERDEPROPERTIES' tableProperties
    ;

tablePartitionPrefix
    : tableName partitionSpec?
    ;

alterStatementSuffixFileFormat
    : 'SET' 'FILEFORMAT' fileFormat
    ;

alterStatementSuffixClusterbySortby
    : 'NOT' ( 'CLUSTERED' | 'SORTED' )
    | tableBuckets
    ;

alterTblPartitionStatementSuffixSkewedLocation
    : 'SET' 'SKEWED' 'LOCATION' skewedLocations
    ;

skewedLocations
    : '(' skewedLocationsList ')'
    ;

skewedLocationsList
    : skewedLocationMap ( ',' skewedLocationMap )*
    ;

skewedLocationMap
    : skewedValueLocationElement EQUAL StringLiteral
    ;

alterStatementSuffixLocation
    : 'SET' 'LOCATION' StringLiteral
    ;

alterStatementSuffixSkewedby
    : tableSkewed
    | 'NOT' ( 'SKEWED' | storedAsDirs )
    ;

alterStatementSuffixExchangePartition
    : 'EXCHANGE' partitionSpec 'WITH' 'TABLE' tableName
    ;

alterStatementSuffixRenamePart
    : 'RENAME' 'TO' partitionSpec
    ;

alterStatementSuffixStatsPart
    : 'UPDATE' 'STATISTICS' 'FOR' 'COLUMN'? id 'SET' tableProperties ( 'COMMENT' StringLiteral )?
    ;

alterStatementSuffixMergeFiles
    : 'CONCATENATE'
    ;

alterStatementSuffixBucketNum
    : 'INTO' Number 'BUCKETS'
    ;

blocking
    : 'AND' 'WAIT'
    ;

compactPool
    : 'POOL' StringLiteral
    ;

alterStatementSuffixCompact
    : 'COMPACT' StringLiteral tableImplBuckets? blocking? compactPool? ( 'WITH' 'OVERWRITE' 'TBLPROPERTIES' tableProperties )?
    ;

alterStatementSuffixSetOwner
    : 'SET' 'OWNER' principalName
    ;

alterStatementSuffixSetPartSpec
    : 'SET' 'PARTITION' 'SPEC' '(' partitionTransformSpec ')'
    ;

alterStatementSuffixExecute
    : 'EXECUTE' (  'ROLLBACK' '(' ( StringLiteral | Number )
                 | 'EXPIRE_SNAPSHOTS' '(' StringLiteral
                 | 'SET_CURRENT_SNAPSHOT' '(' Number
                  ) ')'
    ;

fileFormat
    : 'INPUTFORMAT' StringLiteral 'OUTPUTFORMAT' StringLiteral 'SERDE' StringLiteral
        ( 'INPUTDRIVER' StringLiteral 'OUTPUTDRIVER' StringLiteral )?
    | id
    ;

alterDataConnectorStatementSuffix
    : alterDataConnectorSuffixProperties
    | alterDataConnectorSuffixSetOwner
    | alterDataConnectorSuffixSetUrl
    ;

alterDataConnectorSuffixProperties
    : id 'SET' 'DCPROPERTIES' dcProperties
    ;

alterDataConnectorSuffixSetOwner
    : id 'SET' 'OWNER' principalName
    ;

alterDataConnectorSuffixSetUrl
    : id 'SET' 'URL' StringLiteral
    ;

likeTableOrFile
    : 'LIKE' 'FILE'
    | 'LIKE' 'FILE' id StringLiteral
    | 'LIKE' tableName
    ;

/**
Rules for parsing createtable
*/
createTableStatement
    : 'CREATE' 'TEMPORARY'? 'TRANSACTIONAL'? 'EXTERNAL'? 'TABLE' ifNotExists? tableName
      (   likeTableOrFile
         createTablePartitionSpec?
         tableRowFormat?
         tableFileFormat?
         tableLocation?
         tablePropertiesPrefixed?
       | ( '(' columnNameTypeOrConstraintList ')' )?
         tableComment?
         createTablePartitionSpec?
         tableBuckets?
         tableSkewed?
         tableRowFormat?
         tableFileFormat?
         tableLocation?
         tablePropertiesPrefixed?
         ( 'AS' selectStatementWithCTE )?
       )
    | 'CREATE' 'MANAGED' 'TABLE' ifNotExists? tableName
       (  likeTableOrFile
         tableRowFormat?
         tableFileFormat?
         tableLocation?
         tablePropertiesPrefixed?
       | ( '(' columnNameTypeOrConstraintList ')' )?
         tableComment?
         createTablePartitionSpec?
         tableBuckets?
         tableSkewed?
         tableRowFormat?
         tableFileFormat?
         tableLocation?
         tablePropertiesPrefixed?
         ( 'AS' selectStatementWithCTE )?
        )
    ;

createDataConnectorStatement
    : 'CREATE' 'DATACONNECTOR' ifNotExists? id dataConnectorType dataConnectorUrl dataConnectorComment?
        ( 'WITH' 'DCPROPERTIES' dcProperties )?
    ;

dataConnectorComment
    : 'COMMENT' StringLiteral
    ;

dataConnectorUrl
    : 'URL' StringLiteral
    ;

dataConnectorType
    : 'TYPE' StringLiteral
    ;

dcProperties
    : '(' dbPropertiesList ')'
    ;

dropDataConnectorStatement
    : 'DROP' 'DATACONNECTOR' ifExists? id
    ;

tableAllColumns
    : STAR
    | tableName '.' STAR
    ;

// ( table|column )
aliasList
    : id ( ',' id )*
    ;

/**
Rules for parsing fromClause
  from [col1, col2, col3] table1, [col4, col5] table2
*/
fromClause
    : 'FROM' fromSource
    ;

fromSource
    : 'UNIQUEJOIN' uniqueJoinSource ( ',' uniqueJoinSource )+
    | joinSource
    ;

atomjoinSource
    : tableSource lateralView*
    | virtualTableSource lateralView*
    | subQuerySource lateralView*
    | partitionedTableFunction lateralView*
    | '(' joinSource ')'
    ;

joinSource
    : atomjoinSource ( joinToken joinSourcePart ( 'ON' term | 'USING' columnParenthesesList )? )*
    ;

joinSourcePart
    : ( tableSource | virtualTableSource | subQuerySource | partitionedTableFunction ) lateralView*
    ;

uniqueJoinSource
    : 'PRESERVE'? uniqueJoinTableSource expressionsNested
    ;

joinToken
    : ','
    | (  'INNER'
      | 'CROSS'
      | ( 'RIGHT' | 'FULL' ) 'OUTER'?
      | 'LEFT' ( 'SEMI' | 'ANTI' | 'OUTER' )?
       )? 'JOIN'
    ;

lateralView
    : 'LATERAL' 'VIEW' 'OUTER' function tableAlias ( 'AS' id ( ',' id )* )?
    | ','? 'LATERAL' (  'VIEW' function tableAlias ( 'AS' id ( ',' id )* )?
                        | 'TABLE' '(' valuesClause ')' 'AS'? tableAlias ( '(' id ( ',' id )* ')' )?
                         )
    ;

tableAlias
    : id
    ;

tableBucketSample
    : 'TABLESAMPLE' '(' 'BUCKET' Number 'OUT' 'OF' Number
        ( 'ON' term ( ',' term )* )? ')'
    ;

splitSample
    : 'TABLESAMPLE' '(' ( Number ( 'PERCENT' | 'ROWS' ) | ByteLengthLiteral ) ')'
    ;

tableSample
    : tableBucketSample
    | splitSample
    ;

tableSource
    : tableName tableProperties? tableSample? asOfClause? ( 'AS'? id )?
    ;

asOfClause
    : 'FOR' (  'SYSTEM_TIME' 'AS' 'OF' term
             | 'FOR' 'SYSTEM_VERSION' 'AS' 'OF' Number
              )
    ;

uniqueJoinTableSource
    : tableName tableSample? ( 'AS'? id )?
    ;

tableName
    : id '.' id ( '.' id )?
    | id
    ;

viewName
    : ( id '.' )? id
    ;

subQuerySource
    : '(' queryStatementExpression ')' 'AS'? id
    ;

/**
Rules for parsing PTF clauses
*/
partitioningSpec
    : partitionByClause orderByClause?
    | orderByClause
    | distributeByClause sortByClause?
    | sortByClause
    | clusterByClause
    ;

partitionTableFunctionSource
    : subQuerySource
    | tableSource
    | partitionedTableFunction
    ;

partitionedTableFunction
    : id '(' 'ON'
        partitionTableFunctionSource partitioningSpec?
        ( ID '(' term ')' ( ',' ID '(' term ')' )* )?
        ')' id?
    ;

/**
Rules for parsing whereClause
 where a=b and ...
*/
whereClause
    : 'WHERE' term
    ;

/**
in support of SELECT * FROM ( VALUES( 1,2,3 ),( 4,5,6 ),... ) as FOO( a,b,c ) and
 INSERT INTO <table> ( col1,col2,... ) VALUES( ... ),( ... ),...
 INSERT INTO <table> ( col1,col2,... ) SELECT * FROM ( VALUES( 1,2,3 ),( 4,5,6 ),... ) as Foo( a,b,c )

VALUES( 1 ),( 2 ) means 2 rows, 1 column each.
VALUES( 1,2 ),( 3,4 ) means 2 rows, 2 columns each.
VALUES( 1,2,3 ) means 1 row, 3 columns
*/
valuesClause
    : 'VALUES' valuesTableConstructor
    ;

valuesTableConstructor
    : expressionsNested ( ',' expressionsNested )*
    | firstValueRowConstructor ( ',' expressionsNested )*
    ;

firstValueRowConstructor
    : '(' firstExpressionsWithAlias ')'
    ;

firstExpressionsWithAlias
    : expressionWithAlias ( ',' expressionWithAlias )*
    ;

expressionWithAlias
    : term ( 'AS'? id )?
    ;

/*
This represents a clause like this:
TABLE( VALUES( 1,2 ),( 2,3 ) ) as VirtTable( col1,col2 )
*/
virtualTableSource
    : 'TABLE' '(' valuesClause ')' 'AS'? tableAlias ( '(' id ( ',' id )* )? ')'
    ;

/*
Rules for parsing selectClause
 select a,b,c ...
*/
selectClause
    : 'SELECT' QUERY_HINT? ( all_distinct? item ( ',' item )* | 'TRANSFORM' selectTrfmClause )
    | trfmClause
    ;

all_distinct
    : 'ALL'
    | 'DISTINCT'
    ;

selectTrfmClause
    : '(' selectExpressionList ')'
        rowFormat recordWriter
        'USING' StringLiteral
        ( 'AS' ( '(' ( aliasList | columnNameTypeList ) ')' | aliasList | columnNameTypeList ) )?
        rowFormat recordReader
    ;

item
    : tableAllColumns
    | ( term ( 'AS'? id | 'AS' '(' id ( ',' id )* ')' )? )
    ;

trfmClause
    : ( 'MAP' | 'REDUCE' ) selectExpressionList
        rowFormat recordWriter
        'USING' StringLiteral
        ( 'AS' ( '(' ( aliasList | columnNameTypeList ) ')' | aliasList | columnNameTypeList ) )?
        rowFormat recordReader
    ;

selectExpression
    : tableAllColumns
    | term
    ;

selectExpressionList
    : selectExpression ( ',' selectExpression )*
    ;

/**
Rules for windowing clauses
*/
window_clause
    : 'WINDOW' window_defn ( ',' window_defn )*
    ;

window_defn
    : id 'AS' window_specification
    ;

window_specification
    : id
    | '(' id? partitioningSpec? window_frame? ')'
    ;

window_frame
    : window_range_expression
    | window_value_expression
    ;

window_range_expression
    : 'ROWS' (  window_frame_start_boundary
              | 'BETWEEN' window_frame_boundary 'AND' window_frame_boundary
               )
    ;

window_value_expression
    : 'RANGE' (  window_frame_start_boundary
               | 'BETWEEN' window_frame_boundary 'AND' window_frame_boundary
                )
    ;

window_frame_start_boundary
    : 'UNBOUNDED' 'PRECEDING'
    | 'CURRENT' 'ROW'
    | Number 'PRECEDING'
    ;

window_frame_boundary
    : ( 'UNBOUNDED' | Number ) ( 'PRECEDING' | 'FOLLOWING' )
    | 'CURRENT' 'ROW'
    ;

// group by a,b
groupByClause
    : 'GROUP' 'BY' groupby_expression
    ;

// support for new and old rollup/cube syntax
groupby_expression
    : rollupStandard
    | rollupOldSyntax
    | '(' ')'
    ;

// standard rollup syntax
rollupStandard
    : ( 'ROLLUP' | 'CUBE' ) '(' term ( ',' term )* ')'
    ;

// old hive rollup syntax
rollupOldSyntax
    : terms
        ( 'WITH' 'ROLLUP' | 'WITH' 'CUBE' )?
        ( 'GROUPING' 'SETS' '(' groupingSetExpression ( ',' groupingSetExpression )* ')' )?
    ;

groupingSetExpression
    : groupingSetExpressionMultiple
    | term
    ;

groupingSetExpressionMultiple
    : '(' term? ( ',' term )* ')'
    ;

havingClause
    : 'HAVING' term
    ;

qualifyClause
    : 'QUALIFY' term
    ;


columnRefOrderInParenthesis
    : '(' columnRefOrder ( ',' columnRefOrder )* ')'
    ;

columnRefOrderNotInParenthesis
    : columnRefOrder ( ',' columnRefOrder )*
    ;

// order by a,b
orderByClause
    : 'ORDER' 'BY' columnRefOrder ( ',' columnRefOrder )*
    ;

clusterByClause
    : 'CLUSTER' 'BY' expressionsEither
    ;

partitionByClause
    : 'PARTITION' 'BY' expressionsEither
    ;

distributeByClause
    : 'DISTRIBUTE' 'BY' expressionsEither
    ;

sortByClause
    : 'SORT' 'BY' ( columnRefOrderInParenthesis | columnRefOrderNotInParenthesis )
    ;

// TRIM( [LEADING|TRAILING|BOTH] trim_characters FROM str )
trimFunction
    : 'TRIM' '(' ( 'LEADING' | 'TRAILING' | 'BOTH' )?
        selectExpression? 'FROM' selectExpression ')'
    ;

// fun( par1, par2, par3 )
function
    : trimFunction
    | functionName
    '('
//      ( STAR | all_distinct? ( selectExpression ( ',' selectExpression )* )? )
      ( STAR | all_distinct? ( term ( ',' term )* )? )
      (
        // SELECT rank( 3 ) WITHIN GROUP ( <order by clause> )
        ')' 'WITHIN' 'GROUP' '(' orderByClause ')'
        // No null treatment: SELECT first_value( b ) OVER ( <window spec> )
        // Standard null treatment spec: SELECT first_value( b ) IGNORE NULLS OVER ( <window spec> )
        | ')' nullTreatment? 'OVER' window_specification
        // Non-standard null treatment spec: SELECT first_value( b IGNORE NULLS ) OVER ( <window spec> )
        | nullTreatment ')' 'OVER' window_specification
        | ')'
       )
    ;

nullTreatment
    : (  'RESPECT' | 'IGNORE'  ) 'NULLS'
    ;

functionName
//    : functionIdentifier // Keyword IF is also a function name
    : id
    | sql11ReservedKeywordsUsedAsFunctionName
    ;

castExpression
    : 'CAST' '(' term 'AS' primitiveType ( 'FORMAT' StringLiteral )? ')'
    ;


floorExpression
    : 'FLOOR' '(' term ( 'TO' floorDateQualifiers )? ')'
    ;

floorDateQualifiers
    : 'YEAR'
    | 'QUARTER'
    | 'MONTH'
    | 'WEEK'
    | 'DAY'
    | 'HOUR'
    | 'MINUTE'
    | 'SECOND'
    ;

extractExpression
    : 'EXTRACT' '(' timeQualifiers 'FROM' term ')'
    ;

timeQualifiers
    : 'YEAR'
    | 'QUARTER'
    | 'MONTH'
    | 'WEEK'
    | 'DAY'
    | 'DOW'
    | 'HOUR'
    | 'MINUTE'
    | 'SECOND'
    ;

literal
    : intervalLiteral
    | Number
    | dateLiteral
    | timestampLiteral
    | timestampLocalTZLiteral
    | StringLiteral
    | stringLiteralSequence
    | IntegralLiteral
    | NumberLiteral
    | charSetStringLiteral
    | booleanValue
    | 'NULL'
    | QUESTION
    ;

stringLiteralSequence
    : StringLiteral StringLiteral+
    ;

charSetStringLiteral
    : CharSetName CharSetLiteral
    ;

dateLiteral
    : 'DATE' StringLiteral
    | 'CURRENT_DATE'
    ;

timestampLiteral
    : 'TIMESTAMP' StringLiteral
    | 'CURRENT_TIMESTAMP'
    ;

timestampLocalTZLiteral
    : 'TIMESTAMPLOCALTZ' StringLiteral
    ;

intervalValue
    : StringLiteral
    | Number
    ;

intervalLiteral
    : intervalValue intervalQualifiers
    ;

intervalExpression
    : '(' intervalValue ')' intervalQualifiers
    | 'INTERVAL' ( intervalValue | '(' term ')' ) intervalQualifiers
    ;

intervalQualifiers
    : 'YEAR' 'TO' 'MONTH'
    | 'DAY' 'TO' 'SECOND'
    | 'YEAR'
    | 'MONTH'
    | 'DAY'
    | 'HOUR'
    | 'MINUTE'
    | 'SECOND'
    ;

expressionsEither
    : expressionsNested
    | terms
    ;

expressionsNested
    : '(' terms ')'
    ;

terms
    : term ( ',' term )*
    ;

term
    : term 'OR' term
    | term 'AND' term
    | subterm
    ;

subterm
    : value ( (  '[' term ']'  ) | (  '.' id  ) )*
    | (  PLUS | MINUS | TILDE  ) subterm
    | subterm 'IS' 'NOT'? ( 'NULL' | 'TRUE' | 'FALSE' | 'DISTINCT' 'FROM' )
    | subterm BITWISEOR subterm
    | subterm ( STAR | DIVIDE | MOD | DIV ) subterm
    | subterm ( PLUS | MINUS ) subterm
    | subterm CONCATENATE subterm
    | subterm AMPERSAND subterm
    | subterm BITWISEXOR subterm
    | subterm ( EQUAL | EQUAL_NS | NOTEQUAL | LESSTHANOREQUALTO | LESSTHAN | GREATERTHANOREQUALTO | GREATERTHAN  ) subterm
    | subterm 'NOT'? ( 'LIKE' | 'RLIKE' | 'REGEXP' ) subterm
    | subterm 'NOT'? 'LIKE' ( 'ANY' | 'ALL' ) expressionsNested
    | subterm 'NOT'? 'IN' '(' (  select | terms  ) ')'
    | subterm 'NOT'? 'BETWEEN' subterm 'AND' subterm
    | 'EXISTS' '(' select ')'
    | ( 'NOT' | '!' ) subterm
//    | subQuerySelectorOperator ( 'ANY' | 'SOME' | 'ALL' ) '(' selectStatement ')'
    ;

predicate
    :  'IS' 'NOT'? ( 'NULL' | 'TRUE' | 'FALSE' | 'DISTINCT' 'FROM' )
    |  ( EQUAL | EQUAL_NS | NOTEQUAL | LESSTHANOREQUALTO | LESSTHAN | GREATERTHANOREQUALTO | GREATERTHAN  ) subterm
    |  'NOT'? ( 'LIKE' | 'RLIKE' | 'REGEXP' ) subterm
    |  'NOT'? 'LIKE' ( 'ANY' | 'ALL' ) expressionsNested
    |  'NOT'? 'IN' '(' (  select | terms  ) ')'
    |  'NOT'? 'BETWEEN' subterm 'AND' subterm
//    | 'EXISTS' '(' selectStatement ')'
    ;

caseExpression
    : 'CASE' term ( 'WHEN' (  predicate | term  ) 'THEN' term  )+ (  'ELSE' term  )? 'END'
    ;

whenExpression
    : 'CASE' ( 'WHEN' term 'THEN' term )+ ( 'ELSE' term )? 'END'
    ;

value
    : literal
    | intervalExpression
    | castExpression
    | extractExpression
    | floorExpression
    | caseExpression
    | whenExpression
    | '(' select ')'
//    | selectStatement
    | function
    | id
    | '(' terms ')'
    ;

booleanValue
    : 'TRUE'
    | 'FALSE'
    ;

tableOrPartition
    : tableName partitionSpec?
    ;

partitionSpec
    : 'PARTITION' '(' partitionVal ( ',' partitionVal )* ')'
    ;

partitionVal
    : id ( EQUAL literal )?
    ;

partitionSelectorSpec
    : '(' partitionSelectorVal ( ',' partitionSelectorVal )* ')'
    ;

partitionSelectorVal
    : id ( 'LIKE' | subQuerySelectorOperator ) literal
    ;

subQuerySelectorOperator
    : EQUAL
    | NOTEQUAL
    | LESSTHANOREQUALTO
    | LESSTHAN
    | GREATERTHANOREQUALTO
    | GREATERTHAN
    ;

sysFuncNames
    : 'AND'
    | 'OR'
    | 'NOT'
    | 'LIKE'
    | 'IF'
    | 'CASE'
    | 'WHEN'
    | 'FLOOR'
    | 'TINYINT'
    | 'SMALLINT'
    | 'INT'
    | 'BIGINT'
    | 'FLOAT'
    | 'REAL'
    | 'DOUBLE'
    | 'BOOLEAN'
    | 'STRING'
    | 'BINARY'
    | 'ARRAY'
    | 'MAP'
    | 'STRUCT'
    | 'UNIONTYPE'
    | EQUAL
    | EQUAL_NS
    | NOTEQUAL
    | LESSTHANOREQUALTO
    | LESSTHAN
    | GREATERTHANOREQUALTO
    | GREATERTHAN
    | DIVIDE
    | PLUS
    | MINUS
    | STAR
    | MOD
    | DIV
    | AMPERSAND
    | TILDE
    | BITWISEOR
    | BITWISEXOR
    | 'RLIKE'
    | 'REGEXP'
    | 'IN'
    | 'BETWEEN'
    ;

descFuncNames
    : sysFuncNames
    | StringLiteral
    | functionIdentifier
    ;

id
    : ID
    | nonReserved
    ;

functionIdentifier
    : id ( '.' id )?
    ;

principalIdentifier
    : id
    //| QuotedIdentifier
    ;

/**
 Here is what you have to do if you would like to add a new keyword.
 Note that non reserved keywords are basically the keywords that can be used as identifiers.
 ( 1 ) Add a new entry to HiveLexer, e.g., 'TRUE' : 'TRUE';
 ( 2 ) If it is reserved, you do NOT need to change IdentifiersParser.g
                        because all the KW_* are automatically not only keywords, but also reserved keywords.
                        However, you need to add a test to TestSQL11ReservedKeyWordsNegative.java.
     Otherwise it is non-reserved, you need to put them in the nonReserved list below.
If you are not sure, please refer to the SQL2011 column in
http://www.postgresql.org/docs/9.5/static/sql-keywords-appendix.html
*/
nonReserved
    : 'ABORT'
    | 'ACTIVATE'
    | 'ACTIVE'
    | 'ADD'
    | 'ADMIN'
    | 'AFTER'
    | 'ALLOC_FRACTION'
    | 'ANALYZE'
    | 'ARCHIVE'
    | 'ASC'
    | 'AST'
    | 'AT'
    | 'AUTOCOMMIT'
    | 'BATCH'
    | 'BEFORE'
    | 'BUCKET'
    | 'BUCKETS'
    | 'CACHE'
    | 'CASCADE'
    | 'CBO'
    | 'CHANGE'
    | 'CHECK'
    | 'CLUSTER'
    | 'CLUSTERED'
    | 'CLUSTERSTATUS'
    | 'COLLECTION'
    | 'COLUMNS'
    | 'COMMENT'
    | 'COMPACT'
    | 'COMPACTIONS'
    | 'COMPUTE'
    | 'CONCATENATE'
    | 'CONTINUE'
    | 'COST'
    | 'CRON'
    | 'DATA'
    | 'DATABASES'
    | 'DATETIME'
    | 'DAY'
    | 'DAYOFWEEK'
    | 'DBPROPERTIES'
    | 'DCPROPERTIES'
    | 'DEBUG'
    | 'DEFAULT'
    | 'DEFERRED'
    | 'DEFINED'
    | 'DELIMITED'
    | 'DEPENDENCY'
    | 'DESC'
    | 'DETAIL'
    | 'DIRECTORIES'
    | 'DIRECTORY'
    | 'DISABLE'
    | 'DISTRIBUTE'
    | 'DISTRIBUTED'
    | 'DO'
    | 'DOW'
    | 'DUMP'
    | 'ELEM_TYPE'
    | 'ENABLE'
    | 'ENFORCED'
    | 'ESCAPED'
    | 'EVERY'
    | 'EXCLUSIVE'
    | 'EXECUTE'
    | 'EXECUTED'
    | 'EXPIRE_SNAPSHOTS'
    | 'EXPLAIN'
    | 'EXPORT'
    | 'EXPRESSION'
    | 'FIELDS'
    | 'FILE'
    | 'FILEFORMAT'
    | 'FIRST'
    | 'FORMAT'
    | 'FORMATTED'
    | 'FUNCTIONS'
    | 'HOLD_DDLTIME'
    | 'HOUR'
    | 'IDXPROPERTIES'
    | 'IGNORE'
    | 'INDEX'
    | 'INDEXES'
    | 'INPATH'
    | 'INPUTDRIVER'
    | 'INPUTFORMAT'
    | 'ISOLATION'
    | 'ITEMS'
    | 'JAR'
    | 'JOINCOST'
    | 'KEY'
    | 'KEYS'
    | 'KEY_TYPE'
    | 'KILL'
    | 'LAST'
    | 'LEVEL'
    | 'LIMIT'
    | 'LINES'
    | 'LOAD'
    | 'LOCATION'
    | 'LOCK'
    | 'LOCKS'
    | 'LOGICAL'
    | 'LONG'
    | 'MANAGED'
    | 'MANAGEDLOCATION'
    | 'MANAGEMENT'
    | 'MAPJOIN'
    | 'MAPPING'
    | 'MATCHED'
    | 'MATERIALIZED'
    | 'METADATA'
    | 'MINUTE'
    | 'MONTH'
    | 'MOVE'
    | 'MSCK'
    | 'NORELY'
    | 'NOSCAN'
    | 'NOVALIDATE'
    | 'NO_DROP'
    | 'NULLS'
    | 'OFFLINE'
    | 'OFFSET'
    | 'OPERATOR'
    | 'OPTION'
    | 'OUTPUTDRIVER'
    | 'OUTPUTFORMAT'
    | 'OVERWRITE'
    | 'OWNER'
    | 'PARTITIONED'
    | 'PARTITIONS'
    | 'PATH'
    | 'PLAN'
    | 'PLANS'
    | 'PLUS'
    | 'POOL'
    | 'PRINCIPALS'
    | 'PROTECTION'
    | 'PURGE'
    | 'QUARTER'
    | 'QUERY'
    | 'QUERY_PARALLELISM'
    | 'READ'
    | 'READONLY'
    | 'REBUILD'
    | 'RECORDREADER'
    | 'RECORDWRITER'
    | 'RELOAD'
    | 'RELY'
    | 'REMOTE'
    | 'RENAME'
    | 'REOPTIMIZATION'
    | 'REPAIR'
    | 'REPL'
    | 'REPLACE'
    | 'REPLICATION'
    | 'RESOURCE'
    | 'RESPECT'
    | 'RESTRICT'
    | 'REWRITE'
    | 'ROLE'
    | 'ROLES'
    | 'SCHEDULED'
    | 'SCHEDULING_POLICY'
    | 'SCHEMA'
    | 'SCHEMAS'
    | 'SECOND'
    | 'SEMI'
    | 'SERDE'
    | 'SERDEPROPERTIES'
    | 'SERVER'
    | 'SETS'
    | 'SET_CURRENT_SNAPSHOT'
    | 'SHARED'
    | 'SHOW'
    | 'SHOW_DATABASE'
    | 'SKEWED'
    | 'SNAPSHOT'
    | 'SORT'
    | 'SORTED'
    | 'SPEC'
    | 'SSL'
    | 'STATISTICS'
    | 'STATUS'
    | 'STORED'
    | 'STREAMTABLE'
    | 'STRING'
    | 'STRUCT'
    | 'SUMMARY'
    | 'SYSTEM_TIME'
    | 'SYSTEM_VERSION'
    | 'TABLES'
    | 'TBLPROPERTIES'
    | 'TEMPORARY'
    | 'TERMINATED'
    | 'TIMESTAMPTZ'
    | 'TINYINT'
    | 'TOUCH'
    | 'TRANSACTION'
    | 'TRANSACTIONAL'
    | 'TRANSACTIONS'
    | 'TRIM'
    | 'TYPE'
    | 'UNARCHIVE'
    | 'UNDO'
    | 'UNIONTYPE'
    | 'UNKNOWN'
    | 'UNLOCK'
    | 'UNMANAGED'
    | 'UNSET'
    | 'UNSIGNED'
    | 'URI'
    | 'URL'
    | 'USE'
    | 'UTC'
    | 'UTCTIMESTAMP'
    | 'VALIDATE'
    | 'VALUE_TYPE'
    | 'VECTORIZATION'
    | 'VIEW'
    | 'VIEWS'
    | 'WAIT'
    | 'WEEK'
    | 'WHILE'
    | 'WITHIN'
    | 'WORK'
    | 'WORKLOAD'
    | 'WRITE'
    | 'YEAR'
    | 'ZONE'
    ;

/**
The following SQL2011 reserved keywords are used as function name only, but not as identifiers.
*/
sql11ReservedKeywordsUsedAsFunctionName
    : 'ARRAY'
    | 'BIGINT'
    | 'BINARY'
    | 'BOOLEAN'
    | 'CURRENT_DATE'
    | 'CURRENT_TIMESTAMP'
    | 'DATE'
    | 'DOUBLE'
    | 'FLOAT'
    | 'GROUPING'
    | 'IF'
    | 'INT'
    | 'MAP'
    | 'REAL'
    | 'SMALLINT'
    | 'TIMESTAMP'
    ;

// starting rule
hint
    : hintList EOF
    ;

hintList
    : hintItem ( ',' hintItem )*
    ;

hintItem
    : hintName ( '(' hintArgs ')' )?
    ;

hintName
    : 'MAPJOIN'
    | 'SEMI'
    | 'STREAMTABLE'
    | 'PKFK_JOIN'
    ;

hintArgs
    : hintArgName ( ',' hintArgName )*
    ;

hintArgName
    : ID
    | Number
    | 'NONE'
    ;

//----------------------- Rules for parsing Prepare statement-----------------------------
prepareStatement
    : 'PREPARE' id 'FROM' queryStatementExpression
    ;

executeStatement
    : 'EXECUTE' id 'USING' executeParamList
    ;

//TODO: instead of constant using expression will provide richer and broader parameters
executeParamList
    : literal ( ',' literal )*
    ;

resourcePlanDdlStatements
    : createResourcePlanStatement
    | alterResourcePlanStatement
    | dropResourcePlanStatement
    | globalWmStatement
    | replaceResourcePlanStatement
    | createTriggerStatement
    | alterTriggerStatement
    | dropTriggerStatement
    | createPoolStatement
    | alterPoolStatement
    | dropPoolStatement
    | createMappingStatement
    | alterMappingStatement
    | dropMappingStatement
    ;

rpAssign
    : 'QUERY_PARALLELISM' EQUAL Number
    | 'DEFAULT' 'POOL' EQUAL poolPath
    ;

rpAssignList
    : rpAssign ( ',' rpAssign )*
    ;

rpUnassign
    : 'QUERY_PARALLELISM'
    | 'DEFAULT' 'POOL'
    ;

rpUnassignList
    : rpUnassign ( ',' rpUnassign )*
    ;

createResourcePlanStatement
    : 'CREATE' 'RESOURCE' 'PLAN' ifNotExists?
        ( id 'LIKE' id | id ( 'WITH' rpAssignList )? )
    ;

activate
    : 'ACTIVATE' ( 'WITH' 'REPLACE' )?
    ;

disable
    : 'DISABLE'
    ;

alterResourcePlanStatement
    : 'ALTER' 'RESOURCE' 'PLAN' id ( 
          'VALIDATE'
        | 'DISABLE'
        | 'SET' rpAssignList
        | 'UNSET' rpUnassignList
        | 'RENAME' 'TO' id
        | activate 'ENABLE'?
        | 'ENABLE' activate?
       )
    ;

/** It might make sense to make this more generic, if something else could be enabled/disabled.
    For now, it's only used for WM. Translate into another form of an alter statement. */
globalWmStatement
    : ( 'ENABLE' | 'DISABLE' ) 'WORKLOAD' 'MANAGEMENT'
    ;

replaceResourcePlanStatement
    : 'REPLACE' ( 
          'ACTIVE' 'RESOURCE' 'PLAN' 'WITH' id
        | 'RESOURCE' 'PLAN' id 'WITH' id
       )
    ;

dropResourcePlanStatement
    : 'DROP' 'RESOURCE' 'PLAN' ifExists? id
    ;

poolPath
    : id ( '.' id )*
    ;

triggerExpression
    : triggerAtomExpression
    ;

triggerExpressionStandalone
    : triggerExpression EOF
    ;

/*
  The rules triggerOrExpression and triggerAndExpression are not being used right now.
  Only > operator is supported, this should be changed if logic in ExpressionFactory changes.
*/
triggerOrExpression
    : triggerAndExpression ( 'OR' triggerAndExpression )*
    ;

triggerAndExpression
    : triggerAtomExpression ( 'AND' triggerAtomExpression )*
    ;

triggerAtomExpression
    : id comparisionOperator triggerLiteral
    ;

triggerLiteral
    : Number
    | StringLiteral
    ;

comparisionOperator
    : GREATERTHAN
    ;

triggerActionExpression
    : 'KILL'
    | 'MOVE' 'TO' poolPath
    ;

triggerActionExpressionStandalone
    : triggerActionExpression EOF
    ;

createTriggerStatement
    : 'CREATE' 'TRIGGER' id '.' id
      'WHEN' triggerExpression 'DO' triggerActionExpression
    ;

alterTriggerStatement
    : 'ALTER' 'TRIGGER' id '.' id
      (  'WHEN' triggerExpression 'DO' triggerActionExpression
      | ( 'ADD' 'TO' | 'DROP' 'FROM' ) ( 'POOL' poolPath | 'UNMANAGED' )
       )
    ;

dropTriggerStatement
    : 'DROP' 'TRIGGER' id '.' id
    ;

poolAssign
    : (  'ALLOC_FRACTION' EQUAL Number
      | 'QUERY_PARALLELISM' EQUAL Number
      | 'SCHEDULING_POLICY' EQUAL StringLiteral
      | 'PATH' EQUAL poolPath
       )
    ;

poolAssignList
    : poolAssign ( ',' poolAssign )*
    ;

createPoolStatement
    : 'CREATE' 'POOL' id '.' poolPath
      'WITH' poolAssignList
    ;

alterPoolStatement
    : 'ALTER' 'POOL' id '.' poolPath
        (  'SET' poolAssignList
        | 'UNSET' 'SCHEDULING_POLICY'
        | ( 'ADD' | 'DROP' ) 'TRIGGER' id
         )
    ;

dropPoolStatement
    : 'DROP' 'POOL' id '.' poolPath
    ;

createMappingStatement
    : 'CREATE' ( 'USER' | 'GROUP' | 'APPLICATION' )
         'MAPPING' StringLiteral
         'IN' id ( 'TO' poolPath | 'UNMANAGED' )
         ( 'WITH' 'ORDER' Number )?
    ;

alterMappingStatement
    : 'ALTER' ( 'USER' | 'GROUP' | 'APPLICATION' )
         'MAPPING' StringLiteral
         'IN' id ( 'TO' poolPath | 'UNMANAGED' )
         ( 'WITH' 'ORDER' Number )?
    ;

dropMappingStatement
    : 'DROP' ( 'USER' | 'GROUP' | 'APPLICATION' ) 'MAPPING'
         StringLiteral 'IN' id
    ;

/*
// Keywords
KW_ABORT                               : 'ABORT';
KW_ACTIVATE                            : 'ACTIVATE';
KW_ACTIVE                              : 'ACTIVE';
KW_ADD                                 : 'ADD';
KW_ADMIN                               : 'ADMIN';
KW_AFTER                               : 'AFTER';
KW_ALL                                 : 'ALL';
KW_ALLOC_FRACTION                      : 'ALLOC_FRACTION';
KW_ALTER                               : 'ALTER';
KW_ANALYZE                             : 'ANALYZE';
KW_AND                                 : 'AND';
KW_ANTI                                : 'ANTI';
KW_ANY                                 : 'ANY';
KW_APPLICATION                         : 'APPLICATION';
KW_ARCHIVE                             : 'ARCHIVE';
KW_ARRAY                               : 'ARRAY';
KW_AS                                  : 'AS';
KW_ASC                                 : 'ASC';
KW_AST                                 : 'AST';
KW_AT                                  : 'AT';
KW_AUTHORIZATION                       : 'AUTHORIZATION';
KW_AUTOCOMMIT                          : 'AUTOCOMMIT';
KW_BATCH                               : 'KW_BATCH';
KW_BEFORE                              : 'BEFORE';
KW_BETWEEN                             : 'BETWEEN';
KW_BIGINT                              : 'BIGINT';
KW_BINARY                              : 'BINARY';
KW_BOOLEAN                             : 'BOOLEAN';
KW_BOTH                                : 'BOTH';
KW_BUCKET                              : 'BUCKET';
KW_BUCKETS                             : 'BUCKETS';
KW_BY                                  : 'BY';
KW_CACHE                               : 'CACHE';
KW_CASCADE                             : 'CASCADE';
KW_CASE                                : 'CASE';
KW_CAST                                : 'CAST';
KW_CBO                                 : 'CBO';
KW_CHANGE                              : 'CHANGE';
KW_CHAR                                : 'CHAR';
KW_CHECK                               : 'CHECK';
KW_CLUSTER                             : 'CLUSTER';
KW_CLUSTERED                           : 'CLUSTERED';
KW_CLUSTERSTATUS                       : 'CLUSTERSTATUS';
KW_COLLECTION                          : 'COLLECTION';
KW_COLUMN                              : 'COLUMN';
KW_COLUMNS                             : 'COLUMNS';
KW_COMMENT                             : 'COMMENT';
KW_COMMIT                              : 'COMMIT';
KW_COMPACT                             : 'COMPACT';
KW_COMPACTIONS                         : 'COMPACTIONS';
KW_COMPACT_ID                          : 'COMPACTIONID';
KW_COMPUTE                             : 'COMPUTE';
KW_CONCATENATE                         : 'CONCATENATE';
KW_CONF                                : 'CONF';
KW_CONSTRAINT                          : 'CONSTRAINT';
KW_CONTINUE                            : 'CONTINUE';
KW_COST                                : 'COST';
KW_CREATE                              : 'CREATE';
KW_CRON                                : 'CRON';
KW_CROSS                               : 'CROSS';
KW_CUBE                                : 'CUBE';
KW_CURRENT                             : 'CURRENT';
KW_CURRENT_DATE                        : 'CURRENT_DATE';
KW_CURRENT_TIMESTAMP                   : 'CURRENT_TIMESTAMP';
KW_CURSOR                              : 'CURSOR';
KW_DATA                                : 'DATA';
KW_DATABASE                            : 'DATABASE';
KW_DATABASES                           : 'DATABASES';
KW_DATACONNECTOR                       : 'CONNECTOR';
KW_DATACONNECTORS                      : 'CONNECTORS';
KW_DATE                                : 'DATE';
KW_DATETIME                            : 'DATETIME';
KW_DAY                                 : 'DAY' 'S'?;
KW_DAYOFWEEK                           : 'KW_DAYOFWEEK';
KW_DBPROPERTIES                        : 'DBPROPERTIES';
KW_DCPROPERTIES                        : 'DCPROPERTIES';
KW_DDL                                 : 'DDL';
KW_DEBUG                               : 'DEBUG';
KW_DECIMAL                             : 'DEC' 'IMAL'? | 'NUMERIC';
KW_DEFAULT                             : 'DEFAULT';
KW_DEFERRED                            : 'DEFERRED';
KW_DEFINED                             : 'DEFINED';
KW_DELETE                              : 'DELETE';
KW_DELIMITED                           : 'DELIMITED';
KW_DEPENDENCY                          : 'DEPENDENCY';
KW_DESC                                : 'DESC';
KW_DESCRIBE                            : 'DESCRIBE';
KW_DETAIL                              : 'DETAIL';
KW_DIRECTORIES                         : 'DIRECTORIES';
KW_DIRECTORY                           : 'DIRECTORY';
KW_DISABLE                             : 'DISABLE' 'D'?;
KW_DISTINCT                            : 'DISTINCT';
KW_DISTRIBUTE                          : 'DISTRIBUTE';
KW_DISTRIBUTED                         : 'DISTRIBUTED';
KW_DO                                  : 'DO';
KW_DOUBLE                              : 'DOUBLE';
KW_DOW                                 : 'DAYOFWEEK';
KW_DROP                                : 'DROP';
KW_DUMP                                : 'DUMP';
KW_ELEM_TYPE                           : '$ELEM$';
KW_ELSE                                : 'ELSE';
KW_ENABLE                              : 'ENABLE' 'D'?;
KW_END                                 : 'END';
KW_ENFORCED                            : 'ENFORCED';
KW_ESCAPED                             : 'ESCAPED';
KW_EVERY                               : 'EVERY';
KW_EXCEPT                              : 'EXCEPT';
KW_EXCHANGE                            : 'EXCHANGE';
KW_EXCLUSIVE                           : 'EXCLUSIVE';
KW_EXECUTE                             : 'EXECUTE';
KW_EXECUTED                            : 'EXECUTED';
KW_EXISTS                              : 'EXISTS';
KW_EXPIRE_SNAPSHOTS                    : 'EXPIRE_SNAPSHOTS';
KW_EXPLAIN                             : 'EXPLAIN';
KW_EXPORT                              : 'EXPORT';
KW_EXPRESSION                          : 'EXPRESSION';
KW_EXTENDED                            : 'EXTENDED';
KW_EXTERNAL                            : 'EXTERNAL';
KW_EXTRACT                             : 'EXTRACT';
KW_FALSE                               : 'FALSE';
KW_FETCH                               : 'FETCH';
KW_FIELDS                              : 'FIELDS';
KW_FILE                                : 'FILE';
KW_FILEFORMAT                          : 'FILEFORMAT';
KW_FIRST                               : 'FIRST';
KW_FLOAT                               : 'FLOAT';
KW_FLOOR                               : 'FLOOR';
KW_FOLLOWING                           : 'FOLLOWING';
KW_FOR                                 : 'FOR';
KW_FORCE                               : 'FORCE';
KW_FOREIGN                             : 'FOREIGN';
KW_FORMAT                              : 'FORMAT';
KW_FORMATTED                           : 'FORMATTED';
KW_FROM                                : 'FROM';
KW_FULL                                : 'FULL';
KW_FUNCTION                            : 'FUNCTION';
KW_FUNCTIONS                           : 'FUNCTIONS';
KW_GRANT                               : 'GRANT';
KW_GROUP                               : 'GROUP';
KW_GROUPING                            : 'GROUPING';
KW_HAVING                              : 'HAVING';
KW_HOLD_DDLTIME                        : 'KW_HOLD_DDLTIME';
KW_HOUR                                : 'HOUR' 'S'?;
KW_IDXPROPERTIES                       : 'IDXPROPERTIES';
KW_IF                                  : 'IF';
KW_IGNORE                              : 'IGNORE';
KW_IMPORT                              : 'IMPORT';
KW_IN                                  : 'IN';
KW_INDEX                               : 'INDEX';
KW_INDEXES                             : 'INDEXES';
KW_INNER                               : 'INNER';
KW_INPATH                              : 'INPATH';
KW_INPUTDRIVER                         : 'INPUTDRIVER';
KW_INPUTFORMAT                         : 'INPUTFORMAT';
KW_INSERT                              : 'INSERT';
KW_INT                                 : 'INT' 'EGER'?;
KW_INTERSECT                           : 'INTERSECT';
KW_INTERVAL                            : 'INTERVAL';
KW_INTO                                : 'INTO';
KW_IS                                  : 'IS';
KW_ISOLATION                           : 'ISOLATION';
KW_ITEMS                               : 'ITEMS';
KW_JAR                                 : 'JAR';
KW_JOIN                                : 'JOIN';
KW_JOINCOST                            : 'JOINCOST';
KW_KEY                                 : 'KEY';
KW_KEYS                                : 'KEYS';
KW_KEY_TYPE                            : '$KEY$';
KW_KILL                                : 'KILL';
KW_LAST                                : 'LAST';
KW_LATERAL                             : 'LATERAL';
KW_LEADING                             : 'LEADING';
KW_LEFT                                : 'LEFT';
KW_LESS                                : 'LESS';
KW_LEVEL                               : 'LEVEL';
KW_LIKE                                : 'LIKE';
KW_LIMIT                               : 'LIMIT';
KW_LINES                               : 'LINES';
KW_LOAD                                : 'LOAD';
KW_LOCAL                               : 'LOCAL';
KW_LOCATION                            : 'LOCATION';
KW_LOCK                                : 'LOCK';
KW_LOCKS                               : 'LOCKS';
KW_LOGICAL                             : 'LOGICAL';
KW_LONG                                : 'LONG';
KW_MACRO                               : 'MACRO';
KW_MANAGED                             : 'MANAGED';
KW_MANAGEDLOCATION                     : 'MANAGEDLOCATION';
KW_MANAGEMENT                          : 'MANAGEMENT';
KW_MAP                                 : 'MAP';
KW_MAPJOIN                             : 'MAPJOIN';
KW_MAPPING                             : 'MAPPING';
KW_MATCHED                             : 'MATCHED';
KW_MATERIALIZED                        : 'MATERIALIZED';
KW_MERGE                               : 'MERGE';
KW_METADATA                            : 'METADATA';
KW_MINUS                               : 'MINUS';
KW_MINUTE                              : 'MINUTE' 'S'?;
KW_MONTH                               : 'MONTH' 'S'?;
KW_MORE                                : 'MORE';
KW_MOVE                                : 'MOVE';
KW_MSCK                                : 'MSCK';
KW_NONE                                : 'NONE';
KW_NORELY                              : 'NORELY';
KW_NOSCAN                              : 'NOSCAN';
KW_NOT                                 : 'NOT' | '!';
KW_NOVALIDATE                          : 'NOVALIDATE';
KW_NO_DROP                             : 'KW_NO_DROP';
KW_NULL                                : 'NULL';
KW_NULLS                               : 'NULLS';
KW_OF                                  : 'OF';
KW_OFFLINE                             : 'KW_OFFLINE';
KW_OFFSET                              : 'OFFSET';
KW_ON                                  : 'ON';
KW_ONLY                                : 'ONLY';
KW_OPERATOR                            : 'OPERATOR';
KW_OPTION                              : 'OPTION';
KW_OR                                  : 'OR';
KW_ORDER                               : 'ORDER';
KW_OUT                                 : 'OUT';
KW_OUTER                               : 'OUTER';
KW_OUTPUTDRIVER                        : 'OUTPUTDRIVER';
KW_OUTPUTFORMAT                        : 'OUTPUTFORMAT';
KW_OVER                                : 'OVER';
KW_OVERWRITE                           : 'OVERWRITE';
KW_OWNER                               : 'OWNER';
KW_PARTITION                           : 'PARTITION';
KW_PARTITIONED                         : 'PARTITIONED';
KW_PARTITIONS                          : 'PARTITIONS';
KW_PATH                                : 'PATH';
KW_PERCENT                             : 'PERCENT';
KW_PKFK_JOIN                           : 'PKFK_JOIN';
KW_PLAN                                : 'PLAN';
KW_PLANS                               : 'PLANS';
KW_PLUS                                : 'PLUS';
KW_POOL                                : 'POOL';
KW_PRECEDING                           : 'PRECEDING';
KW_PRECISION                           : 'PRECISION';
KW_PREPARE                             : 'PREPARE';
KW_PRESERVE                            : 'PRESERVE';
KW_PRIMARY                             : 'PRIMARY';
KW_PRINCIPALS                          : 'PRINCIPALS';
KW_PROCEDURE                           : 'PROCEDURE';
KW_PROTECTION                          : 'KW_PROTECTION';
KW_PURGE                               : 'PURGE';
KW_QUALIFY                             : 'QUALIFY';
KW_QUARTER                             : 'QUARTER';
KW_QUERY                               : 'QUERY';
KW_QUERY_PARALLELISM                   : 'QUERY_PARALLELISM';
KW_RANGE                               : 'RANGE';
KW_READ                                : 'READ';
KW_READONLY                            : 'KW_READONLY';
KW_READS                               : 'READS';
KW_REAL                                : 'REAL';
KW_REBUILD                             : 'REBUILD';
KW_RECORDREADER                        : 'RECORDREADER';
KW_RECORDWRITER                        : 'RECORDWRITER';
KW_REDUCE                              : 'REDUCE';
KW_REFERENCES                          : 'REFERENCES';
KW_REGEXP                              : 'REGEXP';
KW_RELOAD                              : 'RELOAD';
KW_RELY                                : 'RELY';
KW_REMOTE                              : 'REMOTE';
KW_RENAME                              : 'RENAME';
KW_REOPTIMIZATION                      : 'REOPTIMIZATION';
KW_REPAIR                              : 'REPAIR';
KW_REPL                                : 'REPL';
KW_REPLACE                             : 'REPLACE';
KW_REPLICATION                         : 'REPLICATION';
KW_RESOURCE                            : 'RESOURCE';
KW_RESPECT                             : 'RESPECT';
KW_RESTRICT                            : 'RESTRICT';
KW_REVOKE                              : 'REVOKE';
KW_REWRITE                             : 'REWRITE';
KW_RIGHT                               : 'RIGHT';
KW_RLIKE                               : 'RLIKE';
KW_ROLE                                : 'ROLE';
KW_ROLES                               : 'ROLES';
KW_ROLLBACK                            : 'ROLLBACK';
KW_ROLLUP                              : 'ROLLUP';
KW_ROW                                 : 'ROW';
KW_ROWS                                : 'ROWS';
KW_SCHEDULED                           : 'SCHEDULED';
KW_SCHEDULING_POLICY                   : 'SCHEDULING_POLICY';
KW_SCHEMA                              : 'SCHEMA';
KW_SCHEMAS                             : 'SCHEMAS';
KW_SECOND                              : 'SECOND' 'S'?;
KW_SELECT                              : 'SELECT';
KW_SEMI                                : 'SEMI';
KW_SERDE                               : 'SERDE';
KW_SERDEPROPERTIES                     : 'SERDEPROPERTIES';
KW_SERVER                              : 'SERVER';
KW_SET                                 : 'SET';
KW_SETS                                : 'SETS';
KW_SET_CURRENT_SNAPSHOT                : 'SET_CURRENT_SNAPSHOT';
KW_SHARED                              : 'SHARED';
KW_SHOW                                : 'SHOW';
KW_SHOW_DATABASE                       : 'SHOW_DATABASE';
KW_SKEWED                              : 'SKEWED';
KW_SMALLINT                            : 'SMALLINT';
KW_SNAPSHOT                            : 'SNAPSHOT';
KW_SOME                                : 'SOME';
KW_SORT                                : 'SORT';
KW_SORTED                              : 'SORTED';
KW_SPEC                                : 'SPEC';
KW_SSL                                 : 'SSL';
KW_START                               : 'START';
KW_STATISTICS                          : 'STATISTICS';
KW_STATUS                              : 'STATUS';
KW_STORED                              : 'STORED';
KW_STREAMTABLE                         : 'STREAMTABLE';
KW_STRING                              : 'STRING';
KW_STRUCT                              : 'STRUCT';
KW_SUMMARY                             : 'SUMMARY';
KW_SYNC                                : 'SYNC';
KW_SYSTEM_TIME                         : 'SYSTEM_TIME';
KW_SYSTEM_VERSION                      : 'SYSTEM_VERSION';
KW_TABLE                               : 'TABLE';
KW_TABLES                              : 'TABLES';
KW_TABLESAMPLE                         : 'TABLESAMPLE';
KW_TBLPROPERTIES                       : 'TBLPROPERTIES';
KW_TEMPORARY                           : 'TEMPORARY';
KW_TERMINATED                          : 'TERMINATED';
KW_THEN                                : 'THEN';
KW_TIME                                : 'TIME';
KW_TIMESTAMP                           : 'TIMESTAMP';
KW_TIMESTAMPLOCALTZ                    : 'TIMESTAMPLOCALTZ';
KW_TIMESTAMPTZ                         : 'KW_TIMESTAMPTZ';
KW_TINYINT                             : 'TINYINT';
KW_TO                                  : 'TO';
KW_TOUCH                               : 'TOUCH';
KW_TRAILING                            : 'TRAILING';
KW_TRANSACTION                         : 'TRANSACTION';
KW_TRANSACTIONAL                       : 'TRANSACTIONAL';
KW_TRANSACTIONS                        : 'TRANSACTIONS';
KW_TRANSFORM                           : 'TRANSFORM';
KW_TRIGGER                             : 'TRIGGER';
KW_TRIM                                : 'TRIM';
KW_TRUE                                : 'TRUE';
KW_TRUNCATE                            : 'TRUNCATE';
KW_TYPE                                : 'TYPE';
KW_UNARCHIVE                           : 'UNARCHIVE';
KW_UNBOUNDED                           : 'UNBOUNDED';
KW_UNDO                                : 'UNDO';
KW_UNION                               : 'UNION';
KW_UNIONTYPE                           : 'UNIONTYPE';
KW_UNIQUE                              : 'UNIQUE';
KW_UNIQUEJOIN                          : 'UNIQUEJOIN';
KW_UNKNOWN                             : 'UNKNOWN';
KW_UNLOCK                              : 'UNLOCK';
KW_UNMANAGED                           : 'UNMANAGED';
KW_UNSET                               : 'UNSET';
KW_UNSIGNED                            : 'UNSIGNED';
KW_UPDATE                              : 'UPDATE';
KW_URI                                 : 'URI';
KW_URL                                 : 'URL';
KW_USE                                 : 'USE';
KW_USER                                : 'USER';
KW_USING                               : 'USING';
KW_UTC                                 : 'UTC';
KW_UTCTIMESTAMP                        : 'UTC_TMESTAMP';
KW_VALIDATE                            : 'VALIDATE';
KW_VALUES                              : 'VALUES';
KW_VALUE_TYPE                          : '$VALUE$';
KW_VARCHAR                             : 'VARCHAR';
KW_VECTORIZATION                       : 'VECTORIZATION';
KW_VIEW                                : 'VIEW';
KW_VIEWS                               : 'VIEWS';
KW_WAIT                                : 'WAIT';
KW_WEEK                                : 'WEEK' 'S'?;
KW_WHEN                                : 'WHEN';
KW_WHERE                               : 'WHERE';
KW_WHILE                               : 'WHILE';
KW_WINDOW                              : 'WINDOW';
KW_WITH                                : 'WITH';
KW_WITHIN                              : 'WITHIN';
KW_WORK                                : 'WORK';
KW_WORKLOAD                            : 'WORKLOAD';
KW_WRITE                               : 'WRITE';
KW_YEAR                                : 'YEAR' 'S'?;
KW_ZONE                                : 'ZONE';
*/
// Operators
// NOTE: if you add a new function/operator, add it to sysFuncNames so that describe function _FUNC_ will work.

EQUAL : '=' | '==';
EQUAL_NS : '<=>';
NOTEQUAL : '<>' | '!=';
LESSTHANOREQUALTO : '<=';
LESSTHAN : '<';
GREATERTHANOREQUALTO : '>=';
GREATERTHAN : '>';

DIVIDE : '/';
PLUS : '+';
MINUS : '-';
STAR : '*';
MOD : '%';
DIV : 'DIV';

AMPERSAND : '&';
TILDE : '~';
BITWISEOR : '|';
CONCATENATE : '||';
BITWISEXOR : '^';
QUESTION : '?';
DOLLAR : '$';

StringLiteral
    : ( '\'' ( ~( '\'' | '\\' ) | ( '\\' . ) )* '\''
    | '"' ( ~( '"' | '\\' ) | ( '\\' . ) )* '"'
     )+
    ;

CharSetLiteral
    : StringLiteral
    | '0' 'X' ( HexDigit | Digit )+
    ;

IntegralLiteral
    : Digit+ ( 'L' | 'S' | 'Y' )
    ;

NumberLiteral
    : Number ( 'B'? 'D' )
    ;

ByteLengthLiteral
    : Digit+ [BKMG]
    ;

Number
    : Digit+ ( '.' Digit* Exponent? | Exponent )?
    ;

/*
An Identifier can be:
- tableName
- columnName
- select expr alias
- lateral view aliases
- database name
- view name
- subquery alias
- function name
- ptf argument identifier
- index name
- property name for: db,tbl,partition...
- fileFormat
- role name
- privilege name
- principal name
- macro name
- hint name
- window name
*/
ID
    : [A-Z_] [A-Z0-9_]*

//    : ( Letter | Digit ) ( Letter | Digit | '_' )*
//    | QuotedIdentifier
//    | '`' RegexComponent+ '`'
    ;

fragment
QuotedIdentifier
    : '`'  ( '``' | ~'`' )* '`'
    ;

fragment
Letter
    : 'A'..'Z'
    ;

fragment
HexDigit
    : 'A'..'F'
    ;

fragment
Digit
    : '0'..'9'
    ;

fragment
Exponent
    : 'E' (  PLUS | MINUS  )? Digit+
    ;

fragment
RegexComponent
    : 'A'..'Z' | '0'..'9' | '_'
    | PLUS | STAR | QUESTION | MINUS | '.'
    | '(' | ')' | '[' | ']' | '{' | '}'
    | BITWISEXOR | BITWISEOR | DOLLAR | '!'
    ;

CharSetName
    : '_' ( Letter | Digit | '_' | '-' | '.' | ':' )+
    ;

WHITE_SPACE
    : (  ' ' | '\r' | '\t' | '\n'  ) -> channel(  HIDDEN  )
    ;

LINE_COMMENT
    : '--' ~(  '\n' | '\r'  )* -> channel(  HIDDEN  )
    ;

QUERY_HINT
    : SHOW_HINT
    | HIDDEN_HINT
    ;

SHOW_HINT
    : '/*+' ( QUERY_HINT | . )*? '*/' ->channel( HIDDEN )
    ;

HIDDEN_HINT
    : '/*' ( QUERY_HINT | . )*? '*/' -> channel( HIDDEN )
    ;
