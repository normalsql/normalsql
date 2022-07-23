/*
 GenericSQL.g4, for ANTLR 4.x

 Copyright 2022, 2014, 2011, 2010 Jason Osgood

 Generic SQL grammar.

*/

grammar GenericSQL;

options {
  contextSuperClass=fado.parse.GlobbingRuleContext;
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
  
ALL       : A L L ;
AND       : A N D ;
ANY       : A N Y ;
AS        : A S ;
ASC       : A S C ;
BETWEEN   : B E T W E E N ;
BY        : B Y ;
CASE      : C A S E ;
//CAST      : C A S T ;
//CROSS     : C R O S S ;
//CONTAINS  : C O N T A I N S ;
//DAY       : D A Y ;
//DEFAULT   : D E F A U L T ;
DELETE    : D E L E T E ;
DESC      : D E S C ;
DISTINCT  : D I S T I N C T ;
ELSE      : E L S E ;
END       : E N D ;
//ESCAPE    : E S C A P E ;
EXISTS    : E X I S T S ;
//EXTRACT   : E X T R A C T ;
FALSE     : F A L S E ;
//FOR       : F O R ;
//FREETEXT  : F R E E T E X T ;
FROM      : F R O M ;
FULL      : F U L L ;
GROUP     : G R O U P ;
HAVING    : H A V I N G ;
//HOUR      : H O U R ;
IN        : I N ;
INNER     : I N N E R ;
INSERT    : I N S E R T ;
//INTERVAL  : I N T E R V A L ;
INTO      : I N T O ;
IS        : I S ;
JOIN      : J O I N ;
LEFT      : L E F T ;
LIKE      : L I K E ;
//MINUTE    : M I N U T E ;
//MONTH     : M O N T H ;
NATURAL   : N A T U R A L ;
NOT       : N O T ;
NULL      : N U L L ;
ON        : O N ;
OR        : O R ;
ORDER     : O R D E R ;
OUTER     : O U T E R ;
PERCENT   : P E R C E N T ;
RIGHT     : R I G H T ;
//SECOND    : S E C O N D ;
SELECT    : S E L E C T ;
SET       : S E T ;
SOME      : S O M E ;
//SUBSTRING : S U B S T R I N G ;
THEN      : T H E N ;
TRUE      : T R U E ;
//TIMESTAMP : T I M E S T A M P ;
TOP       : T O P ;
UNION     : U N I O N ;
UNIQUE    : U N I Q U E ;
UPDATE    : U P D A T E ;
USING     : U S I N G ;
VALUES    : V A L U E S ;
WHEN      : W H E N ;
WHERE     : W H E R E ;
//YEAR      : Y E A R ;

//MAX       : M A X ;
//MIN       : M I N ;
//COUNT     : C O U N T ;

Integer 
  : DIGIT+ L?
  ;
  
Float
  : DIGIT+ ( '.' DIGIT* )? ( E [-+]? DIGIT+ )?
  | '.' DIGIT+ ( E [-+]? DIGIT+ )?
  ;
  
String
  : '\'' ( ~'\'' | '\'\'' )* '\''
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

fragment A : [aA];
fragment B : [bB];
fragment C : [cC];
fragment D : [dD];
fragment E : [eE];
fragment F : [fF];
fragment G : [gG];
fragment H : [hH];
fragment I : [iI];
fragment J : [jJ];
fragment K : [kK];
fragment L : [lL];
fragment M : [mM];
fragment N : [nN];
fragment O : [oO];
fragment P : [pP];
fragment Q : [qQ];
fragment R : [rR];
fragment S : [sS];
fragment T : [tT];
fragment U : [uU];
fragment V : [vV];
fragment W : [wW];
fragment X : [xX];
fragment Y : [yY];
fragment Z : [zZ];