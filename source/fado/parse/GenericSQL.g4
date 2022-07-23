/*
 GenericSQL.g4, for ANTLR 4.x

 Copyright 2022, 2014, 2011, 2010 Jason Osgood

 Generic SQL grammar.

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
  
//subSelect
//  : select
//  | LPAREN select RPAREN
//  ;
  
select
  : SELECT
    ( ALL | DISTINCT | UNIQUE )?
    ( TOP Integer PERCENT? )?
    itemList
    into?
    from?
    join*
//    ( joinList )
    where?
    groupBy?
    having?
    orderBy?
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
  
setter
  : columnName EQ literal
  ;
  
into
  : INTO tableRef ( COMMA tableRef )*
  ;

columnList
  : LPAREN columnName ( COMMA columnName )* RPAREN
  ;
  
values
  : VALUES LPAREN literal ( COMMA literal )* RPAREN
  ;  

itemList
  : STAR
  | item ( COMMA item )*
  ;
  
item
  : function alias?
  | expression alias?
  | allColumns
  | caseExpression alias?
  ;

allColumns
  : tableAlias DOT STAR
  ;

alias
  : AS? Identifier
  ;
  
function
  : functionName LPAREN RPAREN
  | functionName LPAREN STAR RPAREN
  | functionName LPAREN value RPAREN
  | functionName LPAREN ( ALL | DISTINCT )? conditionList RPAREN
  | functionName LPAREN expressionList RPAREN
  | '{fn' odbcFunctionName LPAREN expressionList? RPAREN '}'
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
//  : LPAREN subSelect RPAREN  ( ( AS )? alias )?
  : LPAREN select RPAREN alias?
  | tableRef alias?
  ;
/*
joinList
  : ( join )*
  ;
*/
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
  fromItem  // alias
  /*
  ( ON conditionList
  | USING LPAREN columnRef ( COMMA columnRef )* RPAREN
  )?
  */
  ( ON conditionList )?
  ;
  

//union
//  : UNION // Wrong
//  ;
//  

where
  : WHERE conditionList
  ;
  
groupBy
  : GROUP BY columnRef ( COMMA columnRef )*
  ;
  
having
  : HAVING conditionList
  ;
  
orderBy
  : ORDER BY orderByItem ( COMMA orderByItem )*
  ;
  
orderByItem
  : columnRef ( ASC | DESC )?
  ;
  
nestedCondition
  : LPAREN conditionList RPAREN
  ;
  
conditionList
  : condition ( ( OR | AND ) condition )*
  ;
  
condition
   // ( NOT )?
//    ( 
    : literal
    | comparison
    | nestedCondition
    | in
    | between
    | isNull
    | exists
    | like
    | quantifier
//    )
  ;

in
//  : expression ( NOT )? IN LPAREN ( subSelect | expressionList ) RPAREN
  : expression NOT? IN LPAREN ( select | expressionList ) RPAREN
  ;
  
between
  : expression NOT? BETWEEN expression AND expression
  ;
  
isNull
  : expression IS NOT? NULL
  ;
  
exists
  : EXISTS expression
  ;
  
like
//  : columnRef ( NOT )? LIKE String
  : expression NOT? LIKE expression
//    ESCAPE Literal
  ;
    
comparison
  : expression comparator expression
  ;

comparator 
  : EQ
  | NEQ1
  | NEQ2
  | LTE
  | LT
  | GTE
  | GT
  ;
  
quantifier
//  : expression ( ALL | ANY | SOME ) LPAREN subSelect RPAREN
  : expression ( ALL | ANY | SOME ) LPAREN select RPAREN
  ;
  
expressionList
  : expression ( COMMA expression )*
  ;

nestedExpression
  : LPAREN expression RPAREN
  ;
  
expression
  : value (( PLUS | MINUS | STAR | DIVIDE ) value )*
  ;

//value
//  : NULL 
//  | caseWhenExpression
//  | ( unary )?
//    ( Float
//    | Integer
//    | column
//    | nestedExpression
////    | LPAREN subSelect RPAREN
//    )
//  | '{d' Timestamp '}' // Date
//  | '{t' Timestamp '}' // Time
//  | '{ts' Timestamp '}' // Timestamp
//  ;
    
value
  : literal 
  | caseExpression
  | function
  | unary?
    ( columnRef
    | nestedExpression
//    | LPAREN subSelect RPAREN
    )
  ;
  
literal
  : unary? Float
  | unary? Integer
  | String
  | TRUE
  | FALSE
  | date
  ;

date
  : '{d' String '}' // Date
  | '{t' String '}' // Time
  | '{ts' String '}' // Timestamp
  ;
    
unary
  : MINUS
  | PLUS
  ;
 
caseExpression
  : CASE ( WHEN condition THEN value )+ ( ELSE value )? END
  | CASE value ( WHEN value THEN value )+ ( ELSE value )? END
  ;

tableRef
  : tableName
  | databaseName DOT tableName
  ;
  
columnRef
  : columnName 
  | tableAlias DOT columnName
  ;

databaseName
  : Identifier
//  | QuotedIdentifier
  ;
  
tableName
  : Identifier
//  | QuotedIdentifier
  ;
  
tableAlias
  : Identifier
  ;
  
columnName
  : Identifier
//  | QuotedIdentifier
  ;
  
ALL       : 'ALL';
AND       : 'AND';
ANY       : 'ANY';
AS        : 'AS';
ASC       : 'ASC';
BETWEEN   : 'BETWEEN';
BY        : 'BY';
CASE      : 'CASE';
DELETE    : 'DELETE';
DESC      : 'DESC';
DISTINCT  : 'DISTINCT';
ELSE      : 'ELSE';
END       : 'END';
EXISTS    : 'EXISTS';
FALSE     : 'FALSE';
FROM      : 'FROM';
FULL      : 'FULL';
GROUP     : 'GROUP';
HAVING    : 'HAVING';
IN        : 'IN';
INNER     : 'INNER';
INSERT    : 'INSERT';
INTO      : 'INTO';
IS        : 'IS';
JOIN      : 'JOIN';
LEFT      : 'LEFT';
LIKE      : 'LIKE';
NATURAL   : 'NATURAL';
NOT       : 'NOT';
NULL      : 'NULL';
ON        : 'ON';
OR        : 'OR';
ORDER     : 'ORDER';
OUTER     : 'OUTER';
PERCENT   : 'PERCENT';
RIGHT     : 'RIGHT';
SELECT    : 'SELECT';
SET       : 'SET';
SOME      : 'SOME';
THEN      : 'THEN';
TRUE      : 'TRUE';
TOP       : 'TOP';
UNION     : 'UNION';
UNIQUE    : 'UNIQUE';
UPDATE    : 'UPDATE';
USING     : 'USING';
VALUES    : 'VALUES';
WHEN      : 'WHEN';
WHERE     : 'WHERE';

Integer 
  : DIGIT+ 'L'?
  ;
  
Float
  : DIGIT+ ( '.' DIGIT* )? ( 'E' [-+]? DIGIT+ )?
  | '.' DIGIT+ ( 'E' [-+]? DIGIT+ )?
  ;
  
String options { caseInsensitive=false; }
  : 'N'? '\'' (~'\'' | '\'\'')* '\''
  ;
  
Identifier
  : '"' (~'"' | '""')* '"'
//  | '`' (~'`' | '``')* '`'
  | '[' ~']'* ']'
  | [a-zA-Z_@#] [a-zA-Z0-9_@#$]*
  ;

// QuotedIdentifier
//  : '[' ( . )* ']'
//  | '"' ( . )* '"'
//  ;
 
Comment
  : '--' ~[\r\n]* -> channel(HIDDEN)
 // | '//' ~[\r\n]* -> channel(HIDDEN)
//  | '/*' ( . )* '*/' -> channel(HIDDEN)
  ;
  
Whitespace 
  : [ \u000B\t\r\n] -> channel(HIDDEN)
  ;

DOT      : '.'  ;
COMMA    : ','  ;
LPAREN   : '('  ;
RPAREN   : ')'  ;
LCURLY   : '{'  ;
RCURLY   : '}'  ;
STRCAT   : '||' ;
QUESTION : '?'  ;
COLON    : ':'  ;
SEMI     : ';'  ;

EQ       : '='  ;
NEQ1     : '<>' ;
NEQ2     : '!=' ;
LTE      : '<=' ;
LT       : '<'  ;
GTE      : '>=' ;
GT       : '>'  ;

PLUS     : '+'  ;
MINUS    : '-'  ;
DIVIDE   : '/'  ;
STAR     : '*'  ;
MOD      : '%'  ;

fragment DIGIT : [0-9];

