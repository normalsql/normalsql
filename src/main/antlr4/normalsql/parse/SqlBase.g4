/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

grammar SqlBase;

options
{
    caseInsensitive=true;
}

@ parser :: members
{
    public boolean topFlag = true;

    public boolean isValidKeyword() { return false; }
}

singleStatement
    : statement? ( ';' statement? )* EOF
    ;

//standaloneExpression
//    : expression EOF
//    ;
//
//standaloneRoutineBody
//    : routineBody EOF
//    ;

statement
    : query                                                            #statementDefault
    /*
    | USE schema=identifier                                            #use
    | USE catalog=identifier '.' schema=identifier                     #use
    | CREATE SCHEMA (IF NOT EXISTS)? qname (WITH properties)?                                             #createSchema
    | DROP SCHEMA (IF EXISTS)? qname (CASCADE | RESTRICT)?     #dropSchema
    | ALTER SCHEMA qname RENAME TO identifier                  #renameSchema
    | CREATE TABLE (IF NOT EXISTS)? qname columnAliases? (COMMENT string)? (WITH properties)? AS (query | LP query RP ) (WITH (NO)? DATA)?                                             #createTableAsSelect
    | CREATE TABLE (IF NOT EXISTS)? qname LP tableElement (',' tableElement)* RP (COMMENT string)? (WITH properties)?                                            #createTable
    | DROP TABLE (IF EXISTS)? qname                            #dropTable
    | INSERT INTO qname columnAliases? query                   #insertInto
    | DELETE FROM qname (WHERE expression)?             #delete
    | TRUNCATE TABLE qname                                     #truncateTable
    | ALTER TABLE (IF EXISTS)? from=qname RENAME TO to=qname                                     #renameTable
    | ALTER TABLE (IF EXISTS)? qname RENAME COLUMN (IF EXISTS)? from=identifier TO to=identifier    #renameColumn
    | ALTER TABLE (IF EXISTS)? qname DROP COLUMN (IF EXISTS)? column=qname                  #dropColumn
    | ALTER TABLE (IF EXISTS)? qname ADD COLUMN (IF NOT EXISTS)? column=columnDefinition            #addColumn
    | ANALYZE qname (WITH properties)?                         #analyze
    | CREATE TYPE qname AS ( LP sqlParameterDeclaration (',' sqlParameterDeclaration)* RP | type)                                                        #createType
    | CREATE (OR REPLACE)? VIEW qname (SECURITY (DEFINER | INVOKER))? AS query                   #createView
    | DROP VIEW (IF EXISTS)? qname                             #dropView
    | CREATE MATERIALIZED VIEW (IF NOT EXISTS)? qname (COMMENT string)? (WITH properties)? AS (query | LP query RP )                    #createMaterializedView
    | DROP MATERIALIZED VIEW (IF EXISTS)? qname                #dropMaterializedView
    | REFRESH MATERIALIZED VIEW qname WHERE expression  #refreshMaterializedView
    | CREATE (OR REPLACE)? TEMPORARY? FUNCTION functionName=qname LP (sqlParameterDeclaration (',' sqlParameterDeclaration)*)? RP RETURNS returnType=type (COMMENT string)? routineCharacteristics routineBody                             #createFunction
    | ALTER FUNCTION qname types? alterRoutineCharacteristics                                      #alterFunction
    | DROP TEMPORARY? FUNCTION (IF EXISTS)? qname types?       #dropFunction
    | CALL qname LP (callArgument (',' callArgument)*)? RP    #call
    | CREATE ROLE identifier (WITH ADMIN grantor)?                                          #createRole
    | DROP ROLE identifier                                        #dropRole
    | GRANT roles TO principal (',' principal)* (WITH ADMIN OPTION)? (GRANTED BY grantor)?                                          #grantRoles
    | REVOKE (ADMIN OPTION FOR)? roles FROM principal (',' principal)* (GRANTED BY grantor)?                                          #revokeRoles
    | SET ROLE (ALL | NONE | role=identifier)                          #setRole
    | GRANT (privilege (',' privilege)* | ALL PRIVILEGES) ON TABLE? qname TO grantee=principal (WITH GRANT OPTION)?                                           #grant
    | REVOKE (GRANT OPTION FOR)? (privilege (',' privilege)* | ALL PRIVILEGES) ON TABLE? qname FROM grantee=principal                #revoke
    | SHOW GRANTS (ON TABLE? qname)?                                     #showGrants
    | EXPLAIN ANALYZE? VERBOSE? ( LP explainOption (',' explainOption)* RP )? statement        #explain
    | SHOW CREATE TABLE qname                                  #showCreateTable
    | SHOW CREATE VIEW qname                                   #showCreateView
    | SHOW CREATE MATERIALIZED VIEW qname                      #showCreateMaterializedView
    | SHOW CREATE FUNCTION qname types?                        #showCreateFunction
    | SHOW TABLES ((FROM | IN) qname)? (LIKE pattern=string (ESCAPE escape=string)?)?                 #showTables
    | SHOW SCHEMAS ((FROM | IN) identifier)? (LIKE pattern=string (ESCAPE escape=string)?)?                 #showSchemas
    | SHOW CATALOGS (LIKE pattern=string (ESCAPE escape=string)?)?                 #showCatalogs
    | SHOW COLUMNS (FROM | IN) qname                           #showColumns
    | SHOW STATS FOR qname                                     #showStats
    | SHOW STATS FOR LP select RP                         #showStatsForQuery
    | SHOW CURRENT? ROLES ((FROM | IN) identifier)?                    #showRoles
    | SHOW ROLE GRANTS ((FROM | IN) identifier)?                       #showRoleGrants
    | DESCRIBE qname                                           #showColumns
    | DESC qname                                               #showColumns
    | SHOW FUNCTIONS (LIKE pattern=string (ESCAPE escape=string)?)?                 #showFunctions
    | SHOW SESSION (LIKE pattern=string (ESCAPE escape=string)?)?                 #showSession
    | SET SESSION qname EQ expression                          #setSession
    | RESET SESSION qname                                      #resetSession
    | START TRANSACTION (transactionMode (',' transactionMode)*)?      #startTransaction
    | COMMIT WORK?                                                     #commit
    | ROLLBACK WORK?                                                   #rollback
    | PREPARE identifier FROM statement                                #prepare
    | DEALLOCATE PREPARE identifier                                    #deallocate
    | EXECUTE identifier ( USING expressions )?         #execute
    | DESCRIBE INPUT identifier                                        #describeInput
    | DESCRIBE OUTPUT identifier                                       #describeOutput
    */
    ;

/*
tableElement
    : columnDefinition
    | likeClause
    ;

    columnDefinition
        : identifier type (NOT NULL)? (COMMENT string)? (WITH properties)?
        ;

    likeClause
        : LIKE qname ((INCLUDING | EXCLUDING) PROPERTIES)?
        ;

properties
    : LP property (',' property)* RP 
    ;

property
    : identifier EQ expression
    ;

sqlParameterDeclaration
    : identifier type
    ;

routineCharacteristics
    : routineCharacteristic*
    ;

routineCharacteristic
    : LANGUAGE language
    | determinism
    | nullCallClause
    ;

alterRoutineCharacteristics
    : alterRoutineCharacteristic*
    ;

alterRoutineCharacteristic
    : nullCallClause
    ;

routineBody
    : RETURN expression
    | EXTERNAL (NAME externalRoutineName)?
    ;
language
    : SQL
    | identifier
    ;

determinism
    : DETERMINISTIC
    | NOT DETERMINISTIC;

nullCallClause
    : RETURNS NULL ON NULL INPUT
    | CALLED ON NULL INPUT
    ;

externalRoutineName
    : identifier
    ;
*/

query
    : with? sets orderBy? ( offset | fetch | limit )* ( 'FOR' 'UPDATE' )?
    ;

    with
        : WITH RECURSIVE? namedQuery (',' namedQuery)*
        ;

    orderBy
        : ORDER BY sortItem ( ',' sortItem )*
        ;

        sortItem
            : term (ASC | DESC)? (NULLS (FIRST | LAST))?
            ;

  offset
     : 'OFFSET' term (ROW | ROWS)? // NormalSQL
//         : 'OFFSET' INTEGER_VALUE (ROW | ROWS)? // Presto
     ;

  fetch
//         : 'FETCH' ( 'FIRST' | 'NEXT' ) ( term 'PERCENT'? )? rowRows ( 'ONLY' | withTies ) // NormalSQL
     : 'FETCH' ( 'FIRST' | 'NEXT' ) subterm? ( ROW | PERCENT? ROWS ) ( ONLY | WITH TIES ) // Presto
     ;

  limit
     : 'LIMIT' term (( 'OFFSET' | ',' ) term )? // NormalSQL
//         : LIMIT ( subterm | ALL ) // Presto
    ;

sets
    : sets INTERSECT quantifier? sets
    | sets (UNION | EXCEPT) quantifier? sets
    | rows
    ;

rows
    : select
    | TABLE qname
    | VALUES terms
    | LP query RP // nested
    ;

select
    : SELECT quantifier? top? ( item ( ',' item )* ','? )?
      (FROM join (',' join)*)?
      (WHERE term)?
      groupBy?
      (HAVING term)?
      windows?
      ('QUALIFY' term)?
    ;

    top
        : { topFlag }? 'TOP' ( number | LP term RP ) 'PERCENT'? ( WITH TIES )? ;

    groupBy
        : GROUP BY quantifier? groupingElement (',' groupingElement)*
        ;

        groupingElement
            : groupingSet
            | ROLLUP LP terms? RP
            | CUBE LP terms? RP
            | GROUPING SETS LP groupingSet (',' groupingSet)* RP
            ;

            groupingSet
                : LP terms? RP
                | term
                ;
   windows
      : 'WINDOW' windowAlias ( ',' windowAlias )* ;

      windowAlias
         : id 'AS' window ;

         window
            : LP id? partitionBy? orderBy? windowFrame? RP
            | id
            ;

            partitionBy
               : 'PARTITION' 'BY' terms ;

    item
        : ( qname '.' )? ASTERISK ( 'EXCEPT' LP qname ( ',' qname )* RP  )?
        | term ( AS? id )?
        ;

        join
            : join (( LEFT | RIGHT | FULL ) OUTER? )? JOIN join joinCriteria?
            | join INNER JOIN join joinCriteria?
            | join ( INNER | CROSS | NATURAL ) JOIN join
            | source (AS? id columnAliases?)? ( TABLESAMPLE ( BERNOULLI | SYSTEM )? LP term RP  )?
            | LP join RP
            ;

        joinCriteria
            : ON term
            | USING LP idNoReserved (',' idNoReserved)* RP
            ;

namedQuery
    : idNoReserved columnAliases? AS LP query RP
    ;

quantifier
    : DISTINCT ( 'ON' LP terms? RP  )?
    | ALL
    | 'UNIQUE'
    ;

columnAliases
    : LP qname (',' qname)* RP
    ;

source
    : query
    | function
    | UNNEST LP terms? RP  (WITH ORDINALITY)?
    | LP source RP // nested
    | ( 'TABLE' | 'TABLE_DISTINCT' ) LP columnSpec ( ',' columnSpec )* RP
    | LATERAL LP query RP
    | LP join RP   // TODO: dupe rule?
    | qname
    ;

    columnSpec
//       : idNoReserved ( type | qname ) EQ ( 'ARRAY' LB terms? RB | LP terms? RP  ) ;
       : idNoReserved ( type | qname ) EQ ( 'ARRAY' LB terms? RB | LP terms? RP  ) ;

index
   : LB ( term | term? ':' term? )? RB ;

function
    : 'TRIM' LP ( 'BOTH' | 'LEADING' | 'TRAILING' )? term? 'FROM'? term RP
    | SUBSTRING LP subterm FROM subterm (FOR subterm)? RP // MySQL
    | NORMALIZE LP subterm (',' normalForm)? RP // BigQuery
    | EXTRACT LP intervalField FROM subterm RP // MySQL
   | 'GROUP_CONCAT' LP 'DISTINCT'? terms orderBy? ( 'SEPARATOR' string )? RP filter?
   | 'JSON_OBJECT' LP jsonPairs? onNull? uniqueKeys? formatJson? RP
   | 'JSON_ARRAY' LP ( terms | LP query RP )? formatJson? onNull? RP
      | 'JSON_OBJECTAGG' LP jsonPairs onNull? uniqueKeys? RP filter? over?
      | 'JSON_ARRAYAGG' LP ( 'ALL' | 'DISTINCT' )? term orderBy? onNull? RP filter? over?
   | '{fn' function '}' //  ODBC style
    | id LP ASTERISK? RP  filter? over?
    | id LP (( id | value | term ) ( ',' ( id | value | term ))* )? RP
    | id LP (quantifier? terms )? orderBy? onNull? ( 'ON' 'OVERFLOW' 'ERROR' )? RP withinGroup? filter? (FROM (FIRST | LAST))? (nullTreatment? over)?
//    | keyword LP ( WILDCARD | allDistinct? terms )? RP withinGroup? filter? ( 'FROM' firstLast )? respectIgnore? over?
//   | keyword LP ( WILDCARD | allDistinct? terms )? RP withinGroup? filter? ( 'FROM' firstLast )? respectIgnore? over?
//    | idOrAnyToken LP (quantifier? terms )? /* respectIgnore? */ orderBy? RP ( LB term RB )? filter? over?
    ;


    jsonPairs
       : jsonPair ( ',' jsonPair )* ;

        jsonPair
           : jsonKey ':' term
           | 'KEY'? jsonKey 'VALUE' term
           ;

            jsonKey
                : 'NULL' | string | id ;

    formatJson
        : 'FORMAT' 'JSON' ;

    uniqueKeys
       : withWithout? 'UNIQUE' 'KEYS' ;

withWithout
   : 'WITH' | 'WITHOUT' ;

withinGroup
   : 'WITHIN' 'GROUP' LP orderBy RP ;

onNull
   : ( 'NULL' | 'ABSENT' ) 'ON' 'NULL' ;

terms
    :
//    LP terms RP  |
    term ( ',' term )*
    ;

term
    : NOT term
    | term AND term
    | term OR term
    | 'UNIQUE' LP statement RP
//    | subterm predicate?
    | subterm
    ;

subterm
    : subterm ( '::' type index* )+ // Postgres (?)
    | subterm AT timeZoneSpecifier
    | ( 'NEXT' | 'CURRENT' ) 'VALUE' 'FOR' qname                  // column reference
    | (MINUS | PLUS) subterm
    | <assoc=right> subterm '^' subterm // NormalSQL
    | subterm (ASTERISK | SLASH | '%' ) subterm
    | subterm (PLUS | MINUS) subterm
    | subterm CONCAT subterm
    | subterm predicate
    | LP terms RP ( '.' id )? // field reference
    | LP terms? RP // nested
    | query
    | case
    | quantified LP query RP
    | function // # funky
    | value
    ;

case
    : CASE term ( WHEN ( terms | predicate ) THEN term )+ ( ELSE term )? END
    | CASE ( WHEN terms THEN term )+ (ELSE term)? END
    ;

predicate
    : op subterm
//    | op quantified LP query RP
    | NOT? BETWEEN ( 'ASYMMETRIC' | 'SYMMETRIC' )? subterm AND subterm
    | NOT? IN LP terms? RP
    | NOT? IN LP query RP
    | NOT? ( LIKE | 'ILIKE' ) subterm (ESCAPE subterm)?
    | NOT? 'REGEXP' subterm
    | IS NOT? NULL
    | IS NOT? booleanValue
    | IS NOT? DISTINCT FROM subterm
    | IS NOT? OF LP type RP
    ;

    op
        : EQ | NEQ | LT | LTE | GT | GTE | OVERLAP | '~' | '!~' | '!~*' | '~*'
        ;

/*
    predicate
       : op=( LT | LTE | GT | GTE | EQ | NEQ | OVERLAP ) subterm                      # PredicateCompare
       | ( MATCH1 | MATCH2 | MATCH3 | MATCH4 ) subterm                                # PredicateMatch
       | 'IS' 'NOT'? truth                                                            # PredicateTruth
       | 'IS' 'NOT'? 'DISTINCT' 'FROM' subterm                                        # PredicateDistinct
       | 'IS' 'NOT'? 'OF' LP type ( COMMA type )* RP                                  # PredicateOfType
       | 'IS' 'NOT'? 'JSON' ( 'VALUE' | 'ARRAY' | 'OBJECT' | 'SCALAR' )? uniqueKeys?  # PredicateJSON
       | 'NOT'? 'BETWEEN' ( 'ASYMMETRIC' | 'SYMMETRIC' )? subterm 'AND' subterm       # PredicateBETWEEN
       | 'NOT'? 'IN' LP ( query | terms )? RP                                         # PredicateIN
       | 'NOT'? ( 'LIKE' | 'ILIKE' ) subterm ( 'ESCAPE' string )?                     # PredicateMatch
       | 'NOT'? 'REGEXP' subterm ( 'ESCAPE' string )?                                 # PredicateMatch
       ;
*/

value
    : NULL                                                                                #nullLiteral
    | interval                                                                            #intervalLiteral
    | number                                                                              #numericLiteral
    | booleanValue                                                                        #booleanLiteral
    | string                                                                              #stringLiteral
    | BINARY_LITERAL                                                                      #binaryLiteral
    | '?'                                                                                 #parameter
    | POSITION LP subterm IN subterm RP                                  #position
    | LP term (',' term)+ RP                                                 #rowConstructor
    | ROW LP terms RP                                             #rowConstructor
    | LP (idNoReserved (',' idNoReserved)*)? RP  '->' term                             #lambda
    | LP query RP                                                                        #subqueryExpression
    | EXISTS LP query RP                                                                 #exists
    | CAST LP term AS type RP                                                      #cast
    | TRY_CAST LP term AS type RP                                                  #cast
    | ARRAY '[' terms? ']'                                       #arrayConstructor
    | value '[' subterm ']'                               #subscript
    | value '.' idNoReserved                                     #dereference
    | DATE string #date
    | ( '{d' | '{t' | '{ts' ) string '}' #odbcDate
    | ( TIME | TIMESTAMP ) ( ( WITH | WITHOUT ) TIME 'ZONE' )? string #time
    | CURRENT_DATE                                                                   #specialDateTimeFunction
    | CURRENT_TIME ( LP INTEGER_VALUE RP )?                                #specialDateTimeFunction
    | CURRENT_TIMESTAMP ( LP INTEGER_VALUE RP )?                           #specialDateTimeFunction
    | LOCALTIME ( LP INTEGER_VALUE RP )?                                   #specialDateTimeFunction
    | LOCALTIMESTAMP ( LP INTEGER_VALUE RP )?                              #specialDateTimeFunction
    | CURRENT_USER                                                                   #currentUser
    | GROUPING LP (qname (',' qname)*)? RP                               #groupingOperation
//    | function # funky
    | idNoReserved '->' term                                                          #lambda

    | Variable # Variable
    | qname # columnReference
    ;

string
    :
    UNICODE_STRING STRING* ( UESCAPE STRING )?
    |
    STRING+
    |
    NationalString STRING* ( UESCAPE STRING )?
    ;

nullTreatment
    : IGNORE NULLS
    | RESPECT NULLS
    ;

timeZoneSpecifier
    : TIME ZONE interval
    | TIME ZONE string
    | 'LOCAL'
    ;

quantified
    : ALL | SOME | ANY
    ;

booleanValue
    : TRUE | FALSE | 'UNKNOWN'
    ;


interval
    : INTERVAL (PLUS | MINUS)? string from=intervalField (TO to=intervalField)?
    ;

// TODO: Varies with dialect
intervalField
    : 'EPOCH' | YEAR | MONTH | DAY | HOUR | MINUTE | SECOND | 'MILLISECOND' | 'MICROSECOND' | 'NANOSECOND'
    | 'TIMEZONE_HOUR' | 'TIMEZONE_MINUTE'  | 'TIMEZONE_SECOND'
    | 'DAY_OF_WEEK' |
    | 'ISO_YEAR' | 'ISO_WEEK_YEAR' | 'ISO_DAY_OF_WEEK' | 'ISODOW' | 'ISOYEAR'
    | 'DOW' | 'MILLENNIUM' | 'CENTURY' | 'DECADE' | 'DATE'
    | 'MCS' | 'NS'
    ;

normalForm
    : NFD | NFC | NFKD | NFKC
    ;

types
    : LP ( type ( ',' type )* )? RP
    ;

type
    : type ARRAY
    | DOUBLE PRECISION
    | ARRAY '<' type '>'
    | MAP '<' type ',' type '>'
    | ROW LP idNoReserved type ( ',' idNoReserved type )* RP
    | INTERVAL from=intervalField TO to=intervalField
    | id+ ( LP typeParameter ( ',' typeParameter )* RP  id* )?
//    | idNoReserved+ ( LP typeParameter ( ',' typeParameter )* RP  id* )?
    | timestamp
    ;

    timestamp
        : ( TIME | TIMESTAMP ) ( WITH | WITHOUT ) TIME ZONE
        ;

typeParameter
    : INTEGER_VALUE | type
    ;

filter
    : FILTER LP WHERE term RP
    ;

over
    : OVER
    ( LP ( PARTITION BY terms )? orderBy? windowFrame? RP

    | id )
    ;

    windowFrame
       : ( 'RANGE' | 'ROWS' | 'GROUPS' )
         ( preceding | 'BETWEEN' following 'AND' following )
         ( 'EXCLUDE' ( 'CURRENT' 'ROW' | 'GROUP' | 'TIES' | 'NO' 'OTHERS' )? )?
       ;

           preceding
              : ( 'UNBOUNDED' | CATEGORY | term ) 'PRECEDING'
              | 'CURRENT' 'ROW'
              ;

           following
              : ( 'UNBOUNDED' | term ) 'FOLLOWING'
              | preceding
              ;
/*
explainOption
    : FORMAT value=(TEXT | GRAPHVIZ | JSON)                 #explainFormat
    | TYPE value=(LOGICAL | DISTRIBUTED | VALIDATE | IO)    #explainType
    ;

transactionMode
    : ISOLATION LEVEL levelOfIsolation    #isolationLevel
    | READ accessMode=(ONLY | WRITE)      #transactionAccessMode
    ;
levelOfIsolation
    : READ UNCOMMITTED                    #readUncommitted
    | READ COMMITTED                      #readCommitted
    | REPEATABLE READ                     #repeatableRead
    | SERIALIZABLE                        #serializable
    ;

callArgument
    : expression                    #positionalArgument
    | identifier '=>' expression    #namedArgument
    ;

privilege
    : SELECT | DELETE | INSERT | identifier
    ;

grantor
    : CURRENT_USER          #currentUserGrantor
    | CURRENT_ROLE          #currentRoleGrantor
    | principal             #specifiedPrincipal
    ;

principal
    : USER identifier       #userPrincipal
    | ROLE identifier       #rolePrincipal
    | identifier            #unspecifiedPrincipal
    ;

roles
    : identifier ( ',' identifier )*
    ;

*/

qname
    : idNoReserved ( '.' idNoReserved )*
    ;

idNoReserved
    : IDENTIFIER
    | QUOTED_IDENTIFIER
    | BACKQUOTED_IDENTIFIER
    | DIGIT_IDENTIFIER
    | UNICODE_IDENTIFIER ( UESCAPE STRING )?
    | { isValidKeyword() }? . // TODO: error handling
    ;

id
    : IDENTIFIER
    | QUOTED_IDENTIFIER
    | BACKQUOTED_IDENTIFIER
    | DIGIT_IDENTIFIER
    | UNICODE_IDENTIFIER ( UESCAPE STRING )?
    | . // TODO: exclude punctuation tokens
    ;

number
    : DECIMAL_VALUE
    | DOUBLE_VALUE
    | INTEGER_VALUE
    ;
/*
nonReserved
    // IMPORTANT: this rule must only contain tokens. Nested rules are not supported. See SqlParser.exitNonReserved
    : ADD | ADMIN | ALL | ANALYZE | ANY | ARRAY | AS | ASC | AT
    | BERNOULLI
    | CALL | CALLED | CASCADE | CATALOGS | CATEGORY | COLUMN | COLUMNS | COMMENT | COMMIT | COMMITTED | CURRENT | CURRENT_ROLE
    | DATA | DATE | DAY | DEFINER | DESC | DETERMINISTIC | DISTRIBUTED | DOUBLE
    | EXCLUDING | EXPLAIN | EXTERNAL
    | FETCH | FILTER | FIRST | FOLLOWING | FORMAT | FUNCTION | FUNCTIONS
    | GRANT | GRANTED | GRANTS | GRAPHVIZ | GROUPS
    | HOUR
    | IF | IGNORE | INCLUDING | INPUT | INTERVAL | INVOKER | IO | ISOLATION
    | JSON
    | LANGUAGE | LAST | LATERAL | LEVEL | LIMIT | LOGICAL
    | MAP | MATERIALIZED | MINUTE | MONTH
    | NAME | NFC | NFD | NFKC | NFKD | NO | NONE | NULLIF | NULLS
    | OFFSET | ONLY | OPTION | ORDINALITY | OUTPUT | OVER
    | PARTITION | PARTITIONS | POSITION | PRECEDING | PRECISION | PRIVILEGES | PROPERTIES
    | RANGE | READ | REFRESH | RENAME | REPEATABLE | REPLACE | RESET | RESPECT | RESTRICT | RETURN | RETURNS | REVOKE | ROLE | ROLES | ROLLBACK | ROW | ROWS
    | SCHEMA | SCHEMAS | SECOND | SECURITY | SERIALIZABLE | SESSION | SET | SETS | SQL
    | SHOW | SOME | START | STATS | SUBSTRING | SYSTEM
    | TABLES | TABLESAMPLE | TEMPORARY | TEXT | TIME | TIMESTAMP | TO | TRANSACTION | TRUNCATE | TRY_CAST | TYPE
    | UNBOUNDED | UNCOMMITTED | USE | USER
    | VALIDATE | VERBOSE | VIEW
    | WITH | WORK | WRITE
    | YEAR
    | ZONE
    ;
*/
ADD: 'ADD';
ADMIN: 'ADMIN';
ALL: 'ALL';
ALTER: 'ALTER';
ANALYZE: 'ANALYZE';
AND: 'AND';
ANY: 'ANY';
ARRAY: 'ARRAY';
AS: 'AS';
ASC: 'ASC';
AT: 'AT';
BERNOULLI: 'BERNOULLI';
BETWEEN: 'BETWEEN';
BY: 'BY';
CALL: 'CALL';
CALLED: 'CALLED';
CASCADE: 'CASCADE';
CASE: 'CASE';
CAST: 'CAST';
CATALOGS: 'CATALOGS';
CATEGORY: 'CATEGORY';
COLUMN: 'COLUMN';
COLUMNS: 'COLUMNS';
COMMENT: 'COMMENT';
COMMIT: 'COMMIT';
COMMITTED: 'COMMITTED';
CONSTRAINT: 'CONSTRAINT';
CREATE: 'CREATE';
CROSS: 'CROSS';
CUBE: 'CUBE';
CURRENT: 'CURRENT';
CURRENT_DATE: 'CURRENT_DATE';
CURRENT_ROLE: 'CURRENT_ROLE';
CURRENT_TIME: 'CURRENT_TIME';
CURRENT_TIMESTAMP: 'CURRENT_TIMESTAMP';
CURRENT_USER: 'CURRENT_USER';
DATA: 'DATA';
DATE: 'DATE';
DAY: 'DAY';
DEALLOCATE: 'DEALLOCATE';
DEFINER: 'DEFINER';
DELETE: 'DELETE';
DESC: 'DESC';
DESCRIBE: 'DESCRIBE';
DETERMINISTIC: 'DETERMINISTIC';
DISTINCT: 'DISTINCT';
DISTRIBUTED: 'DISTRIBUTED';
DOUBLE : 'DOUBLE';
DROP: 'DROP';
ELSE: 'ELSE';
END: 'END';
ESCAPE: 'ESCAPE';
EXCEPT: 'EXCEPT';
EXCLUDING: 'EXCLUDING';
EXECUTE: 'EXECUTE';
EXISTS: 'EXISTS';
EXPLAIN: 'EXPLAIN';
EXTRACT: 'EXTRACT';
EXTERNAL: 'EXTERNAL';
FALSE: 'FALSE';
FETCH: 'FETCH';
FILTER: 'FILTER';
FIRST: 'FIRST';
FOLLOWING: 'FOLLOWING';
FOR: 'FOR';
FORMAT: 'FORMAT';
FROM: 'FROM';
FULL: 'FULL';
FUNCTION: 'FUNCTION';
FUNCTIONS: 'FUNCTIONS';
GRANT: 'GRANT';
GRANTED: 'GRANTED';
GRANTS: 'GRANTS';
GRAPHVIZ: 'GRAPHVIZ';
GROUP: 'GROUP';
GROUPING: 'GROUPING';
GROUPS: 'GROUPS';
HAVING: 'HAVING';
HOUR: 'HOUR';
IF: 'IF';
IGNORE: 'IGNORE';
IN: 'IN';
INCLUDING: 'INCLUDING';
INNER: 'INNER';
INPUT: 'INPUT';
INSERT: 'INSERT';
INTERSECT: 'INTERSECT';
INTERVAL: 'INTERVAL';
INTO: 'INTO';
INVOKER: 'INVOKER';
IO: 'IO';
IS: 'IS';
ISOLATION: 'ISOLATION';
JSON: 'JSON';
JOIN: 'JOIN';
LANGUAGE: 'LANGUAGE';
LAST: 'LAST';
LATERAL: 'LATERAL';
LEFT: 'LEFT';
LEVEL: 'LEVEL';
LIKE: 'LIKE';
LIMIT: 'LIMIT';
LOCALTIME: 'LOCALTIME';
LOCALTIMESTAMP: 'LOCALTIMESTAMP';
LOGICAL: 'LOGICAL';
MAP: 'MAP';
MATERIALIZED: 'MATERIALIZED';
MINUTE: 'MINUTE';
MONTH: 'MONTH';
NAME: 'NAME';
NATURAL: 'NATURAL';
NEXT : 'NEXT';
NFC : 'NFC';
NFD : 'NFD';
NFKC : 'NFKC';
NFKD : 'NFKD';
NO: 'NO';
NONE: 'NONE';
NORMALIZE: 'NORMALIZE';
NOT: 'NOT';
NULL: 'NULL';
NULLIF: 'NULLIF';
NULLS: 'NULLS';
OFFSET: 'OFFSET';
OF: 'OF';
ON: 'ON';
ONLY: 'ONLY';
OPTION: 'OPTION';
OR: 'OR';
ORDER: 'ORDER';
ORDINALITY: 'ORDINALITY';
OUTER: 'OUTER';
OUTPUT: 'OUTPUT';
OVER: 'OVER';
PARTITION: 'PARTITION';
PARTITIONS: 'PARTITIONS';
PERCENT: 'PERCENT';
POSITION: 'POSITION';
PRECEDING: 'PRECEDING';
PREPARE: 'PREPARE';
PRECISION: 'PRECISION';
PRIVILEGES: 'PRIVILEGES';
PROPERTIES: 'PROPERTIES';
RANGE: 'RANGE';
READ: 'READ';
RECURSIVE: 'RECURSIVE';
REFRESH: 'REFRESH';
RENAME: 'RENAME';
REPEATABLE: 'REPEATABLE';
REPLACE: 'REPLACE';
RESET: 'RESET';
RESPECT: 'RESPECT';
RESTRICT: 'RESTRICT';
RETURN: 'RETURN';
RETURNS: 'RETURNS';
REVOKE: 'REVOKE';
RIGHT: 'RIGHT';
ROLE: 'ROLE';
ROLES: 'ROLES';
ROLLBACK: 'ROLLBACK';
ROLLUP: 'ROLLUP';
ROW: 'ROW';
ROWS: 'ROWS';
SCHEMA: 'SCHEMA';
SCHEMAS: 'SCHEMAS';
SECOND: 'SECOND';
SECURITY: 'SECURITY';
SELECT: 'SELECT';
SERIALIZABLE: 'SERIALIZABLE';
SESSION: 'SESSION';
SET: 'SET';
SETS: 'SETS';
SHOW: 'SHOW';
SOME: 'SOME';
SQL: 'SQL';
START: 'START';
STATS: 'STATS';
SUBSTRING: 'SUBSTRING';
SYSTEM: 'SYSTEM';
TABLE: 'TABLE';
TABLES: 'TABLES';
TABLESAMPLE: 'TABLESAMPLE';
TEMPORARY: 'TEMPORARY';
TEXT: 'TEXT';
THEN: 'THEN';
TIES: 'TIES';
TIME: 'TIME';
TIMESTAMP: 'TIMESTAMP';
TO: 'TO';
TRANSACTION: 'TRANSACTION';
TRUE: 'TRUE';
TRUNCATE: 'TRUNCATE';
TRY_CAST: 'TRY_CAST';
TYPE: 'TYPE';
UESCAPE: 'UESCAPE';
UNBOUNDED: 'UNBOUNDED';
UNCOMMITTED: 'UNCOMMITTED';
UNION: 'UNION';
UNNEST: 'UNNEST';
USE: 'USE';
USER: 'USER';
USING: 'USING';
VALIDATE: 'VALIDATE';
VALUES: 'VALUES';
VERBOSE: 'VERBOSE';
VIEW: 'VIEW';
WHEN: 'WHEN';
WHERE: 'WHERE';
WITH: 'WITH';
WITHOUT: 'WITHOUT';
WORK: 'WORK';
WRITE: 'WRITE';
YEAR: 'YEAR';
ZONE: 'ZONE';

EQ  : '=';
NEQ : '<>' | '!=';
LT  : '<';
LTE : '<=';
GT  : '>';
GTE : '>=';
OVERLAP  : '&&' ;

PLUS: '+';
MINUS: '-';
ASTERISK: '*';
SLASH: '/';
//PERCENT: '%';
CONCAT: '||';
LP: '(' ;
RP: ')' ;
LB: '[' ;
RB: ']' ;

STRING
    : '\'' ( ~'\'' | '\'\'' )* '\''
    ;

UNICODE_STRING
    : 'U&\'' ( ~'\'' | '\'\'' )* '\''
    ;

NationalString
   : [NE] STRING ;


// Note: we allow any character inside the binary literal and validate
// its a correct literal when the AST is being constructed. This
// allows us to provide more meaningful error messages to the user
BINARY_LITERAL
    :  'X\'' (~'\'')* '\''
    ;

INTEGER_VALUE
    : DIGIT+
    ;

DECIMAL_VALUE
    : DIGIT+ '.' DIGIT*
    | '.' DIGIT+
    ;

DOUBLE_VALUE
    : DIGIT+ ('.' DIGIT*)? EXPONENT
    | '.' DIGIT+ EXPONENT
    ;

UNICODE_IDENTIFIER
   : 'U&' QUOTED_IDENTIFIER ;

IDENTIFIER
   : [A-Z_#] [A-Z_#$@0-9]*
//    : (LETTER | '_') (LETTER | DIGIT | '_' | '@' | ':')*
   ;

DIGIT_IDENTIFIER
    : DIGIT (LETTER | DIGIT | '_' | '@' | ':')+
    ;

QUOTED_IDENTIFIER
    : '"' ( ~'"' | '""' )* '"'
    ;

BACKQUOTED_IDENTIFIER
    : '`' ( ~'`' | '``' )* '`'
    ;

Variable
   : '@' [A-Z_$@#0-9]* // T-SQL?
   | ':' [A-Z_] [A-Z_0-9$]* // Postgres?
   | ':' IDENTIFIER // Postgres?
   ;

fragment EXPONENT
    : 'E' [+-]? DIGIT+
    ;

fragment DIGIT
    : [0-9]
    ;

fragment LETTER
    : [A-Z]
    ;

SIMPLE_COMMENT
    : '--' ~[\r\n]* '\r'? '\n'? -> channel(HIDDEN)
    ;

BRACKETED_COMMENT
    : '/*' .*? '*/' -> channel(HIDDEN)
    ;

WS
    : [ \r\n\t]+ -> channel(HIDDEN)
    ;

// Catch-all for anything we can't recognize.
// We use this to be able to ignore and recover all the text
// when splitting statements with DelimiterLexer
UNRECOGNIZED
    : .
    ;
