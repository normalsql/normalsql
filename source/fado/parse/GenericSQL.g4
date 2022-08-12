/*
 GenericSQL.g4, for ANTLR 4.x

 Copyright 2022, 2014, 2011, 2010 Jason Osgood

 Generic SQL DML grammar.

*/

grammar GenericSQL;

options {
  contextSuperClass=fado.parse.GlobbingRuleContext;
  caseInsensitive = true;
}

statement
  : ( select
    | insert
    | update
//  | delete
    )
    SEMI? EOF
  ;
  
select
  : SELECT
    ( ALL | DISTINCT | UNIQUE )?
    ( TOP expression PERCENT? ( WITH TIES )? )?
    itemList
    into?
    from?
    where?
    groupBy?
    having?
    // window?
    qualify?
    union?
    orderBy?
    limit?
    // offset? -- different from 'limit' rule?
    // fetch?
    forUpdate?
  ;

insert
  : INSERT into columnList?
  ( values
// | select
  )
  ;  

update
  : UPDATE tableRef SET setter ( COMMA setter )*
    where?
  ;

columnList
  : LPAREN columnName ( COMMA columnName )* RPAREN
  ;

setter
  : columnName EQ literal
  ;
  
into
  : INTO tableRef ( COMMA tableRef )*
  ;

itemList
  : list+=item ( COMMA list+=item )*
  ;
  
item
  : star=STAR exceptions?
  | tableName DOT star=STAR exceptions?
  | expression alias?
  ;

// http://www.h2database.com/html/grammar.html#wildcard_expression
exceptions
  : EXCEPT LPAREN columnRef ( COMMA columnRef )* RPAREN
  ;

expressionList
  : expression ( COMMA expression )*
  ;

expression
  : literal
  | Variable
  | op=( MINUS | PLUS | TILDE ) expression
  | function
  | columnRef

  // Best guess for precedence of  operators
  | left=expression op=STRCAT right=expression
  | left=expression op=( STAR | DIV | MOD ) right=expression
  | left=expression op=( PLUS | MINUS ) right=expression
  | left=expression op=( LT2 | GT2 | AMP | PIPE ) right=expression
  | left=expression op=( LT | LTE | GT | GTE ) right=expression
  | left=expression op=( EQ | NEQ1 | NEQ2 ) right=expression
  // TODO: This should build LL parse tree, not LR
  | left=expression op=( AND | OR ) right=expression
  | op=NOT right=expression

  | LPAREN expression RPAREN
  | LPAREN query RPAREN
  | left=expression NOT* op=( LIKE | ILIKE ) right=expression ( ESCAPE expression )?
  | left=expression NOT* op=REGEXP right=expression ( ESCAPE expression )?
  | expression IS NOT? op=NULL
  | left=expression NOT* op=IN LPAREN ( select | list=expressionList )? RPAREN
// TODO additional 'IN' rules
//          | ( databaseName DOT )? table_name
//          | ( databaseName DOT )? table_function_name LPAREN ( expression ( COMMA expression )* )? RPAREN
  | left=expression NOT* op=BETWEEN ( ASYMMETRIC | SYMMETRIC )? lower=expression AND upper=expression

  | op=EXISTS LPAREN query RPAREN
  | op=UNIQUE LPAREN query RPAREN
  // http://www.h2database.com/html/grammar.html#condition
  | op=INTERSECTS LPAREN expression COMMA expression RPAREN
  | op=CASE expression? ( WHEN expression THEN expression )+ ( ELSE expression )? END
  | op=( CAST | TRY_CAST ) LPAREN expression AS typeName RPAREN
  | expression op=COLLATE collateName
//  TODO | raise_function
  | op=( ALL | ANY | SOME ) LPAREN query RPAREN
  ;

// TODO: http://www.h2database.com/html/grammar.html#query
// TODO: merge 'query' and 'from' rules?
query
  : select
//  | explicit from
//  | from value
  ;

// TODO inline these 'name' rules
alias
  : AS? aliasName
  ;

// TODO filter_clause? over_clause?
function
  : functionName LPAREN STAR RPAREN
  | functionName LPAREN ( ALL | DISTINCT ) expressionList RPAREN
  | functionName LPAREN expressionList? RPAREN
  | '{fn' odbcFunctionName LPAREN expressionList? RPAREN '}'
  | Identifier? FUNCTION functionName LPAREN expressionList? RPAREN // T-SQL
  | Identifier DOT functionName LPAREN expressionList? RPAREN // T-SQL?
  ;

functionName
  : Identifier
  ;

odbcFunctionName
  : LEFT | RIGHT | INSERT // also SQL reserved words
  | Identifier
  ;

from
  : FROM fromItem ( COMMA fromItem )*
  ;
  
fromItem
  : table useIndex?
  | fromItem join
  | LPAREN fromItem RPAREN
  | function
  ;

table
  : ( LPAREN select RPAREN
    | tableRef
    )
    alias?
  ;

useIndex
  : USE INDEX LPAREN Identifier ( COMMA Identifier )* RPAREN
  ;

join
  : 
    ( JOIN
    | INNER JOIN
    | LEFT JOIN
    | LEFT OUTER JOIN
    | RIGHT JOIN 
    | RIGHT OUTER JOIN
    | OUTER JOIN 
    | NATURAL JOIN
    )
    // ( INNER | ( LEFT | RIGHT )? OUTER? | NATURAL )? JOIN
    from

    ( ON expression
    | USING LPAREN columnName ( COMMA columnName )* RPAREN
    )?

  | LPAREN join RPAREN
  ;

union
  : ( UNION ALL?
    | EXCEPT
    | INTERSECT
    )
    ( LPAREN query RPAREN
    | query
    )
  ;

where
  : WHERE expression
  ;
  
groupBy
  : GROUP BY
    ( expressionList?
    | LPAREN expressionList? RPAREN
    )
  ;
  
having
  : HAVING expressionList
  ;

qualify
  : QUALIFY expression
  ;
  
orderBy
  : ORDER BY orderByItem ( COMMA orderByItem )*
  ;
  
orderByItem
  : expression ( ASC | DESC )?
  ;

limit
  : LIMIT expression (( OFFSET | COMMA ) expression )?
  ;

forUpdate
  : FOR UPDATE
  ;

values
  : VALUES LPAREN literal ( COMMA literal )* RPAREN
  ;

//value
//  : literal
//  | columnRef
//  ;
  
literal
  : Float
  | Integer
  | String
  | TRUE
  | FALSE
  | NULL
  | date
  ;

// TODO turn this rule into a token?
date
  : '{d' String '}' // Date
  | '{t' String '}' // Time
  | '{ts' String '}' // Timestamp
  ;

tableRef
  : tableName
  | databaseName DOT tableName
  ;
  
columnRef
  : (( databaseName DOT )? tableName DOT )? columnName
  ;

databaseName
  : Identifier
  ;
  
tableName
  : Identifier
  ;
  
columnName
  : Identifier
  ;

typeName
  : Identifier
  ;

collateName
  : Identifier
  ;

aliasName
  : Identifier
  ;

// Lexer

// Keywords
  
ALL       : 'ALL' ;
AND       : 'AND' ;
ANY       : 'ANY' ;
AS        : 'AS' ;
ASC       : 'ASC' ;
ASYMMETRIC: 'ASYMMETRIC' ;
BETWEEN   : 'BETWEEN' ;
BY        : 'BY' ;
CASE      : 'CASE' ;
CAST      : 'CAST' ;
COLLATE   : 'COLLATE' ;
DELETE    : 'DELETE' ;
DESC      : 'DESC' ;
DISTINCT  : 'DISTINCT' ;
DIV       : 'DIV' ;
ELSE      : 'ELSE' ;
END       : 'END' ;
ESCAPE    : 'ESCAPE' ;
EXCEPT    : 'EXCEPT' ;
EXISTS    : 'EXISTS' ;
FALSE     : 'FALSE' ;
FOR       : 'FOR' ;
FROM      : 'FROM' ;
FULL      : 'FULL' ;
GROUP     : 'GROUP' ;
HAVING    : 'HAVING' ;
ILIKE     : 'ILIKE' ;
IN        : 'IN' ;
INDEX     : 'INDEX' ;
INNER     : 'INNER' ;
INSERT    : 'INSERT' ;
INTO      : 'INTO' ;
INTERSECT : 'INTERSECT' ;
INTERSECTS: 'INTERSECTS' ;
IS        : 'IS' ;
JOIN      : 'JOIN' ;
LEFT      : 'LEFT' ;
LIKE      : 'LIKE' ;
LIMIT     : 'LIMIT' ;
NATURAL   : 'NATURAL' ;
NOT       : 'NOT' ;
NULL      : 'NULL' ;
OFFSET    : 'OFFSET' ;
ON        : 'ON' ;
OR        : 'OR' ;
ORDER     : 'ORDER' ;
OUTER     : 'OUTER' ;
PERCENT   : 'PERCENT' ;
PIPE      : 'PIPE' ;
QUALIFY   : 'QUALIFY' ;
REGEXP    : 'REGEXP' ;
RIGHT     : 'RIGHT' ;
SELECT    : 'SELECT' ;
SET       : 'SET' ;
SOME      : 'SOME' ;
SYMMETRIC : 'SYMMETRIC' ;
THEN      : 'THEN' ;
TIES      : 'TIES' ;
TRUE      : 'TRUE' ;
TRY_CAST  : 'TRY_CAST' ;
TOP       : 'TOP' ;
UNION     : 'UNION' ;
UNIQUE    : 'UNIQUE' ;
UPDATE    : 'UPDATE' ;
USE       : 'USE' ;
USING     : 'USING' ;
VALUES    : 'VALUES' ;
WITH      : 'WITH' ;
WHEN      : 'WHEN' ;
WHERE     : 'WHERE' ;

// Punctuation

DOT      : '.' ;
COMMA    : ',' ;
LPAREN   : '(' ;
RPAREN   : ')' ;
LCURLY   : '{' ;
RCURLY   : '}' ;
QUESTION : '?' ;
COLON    : ':' ;
SEMI     : ';' ;

STRCAT   : '||' ;
FUNCTION : '::' ;

AMP      : '&' ;
EQ       : '=' ;
NEQ1     : '<>' ;
NEQ2     : '!=' ;
LT       : '<' ;
LT2      : '<<' ;
LTE      : '<=' ;
GT       : '>' ;
GT2      : '>>' ;
GTE      : '>=' ;

PLUS     : '+' ;
MINUS    : '-' ;
DIVIDE   : '/' ;
STAR     : '*' ;
MOD      : '%' ;

TILDE    : '~' ;

// Token rules

Integer
  : DIGIT+ 'L'?
  ;

Float
  // matches "0.e1" or ".0e1", but not ".e1"
  : ( DIGIT+ ( '.' DIGIT* )?
    | '.' DIGIT+
    )
    ( 'E' [-+]? DIGIT+ )?
  ;

String options { caseInsensitive=false; }
  : [NE]? '\'' ( ~'\'' | '\'\'' )* '\''
  ;

Identifier
  : '"' ( ~'"' | '""' )* '"'
  | '`' ( ~'`' | '``' )* '`'
  | '[' ~']'* ']'
  | [A-Z_] [A-Z_0-9]*
  ;

Variable
  : [:@$] Identifier
  | '?' DIGIT*
  ;

Comment
  : '--' ~[\r\n]* -> channel( HIDDEN )
  ;

BlockComment
  : '/*' ( Comment | . )*? '*/' -> channel( HIDDEN )
  ;

Whitespace
  : [ \u000B\t\r\n] -> channel( HIDDEN )
  ;

fragment DIGIT : [0-9] ;

