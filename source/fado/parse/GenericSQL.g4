/*
 GenericSQL.g4, for ANTLR 4.x

 Copyright 2022, 2014, 2011, 2010 Jason Osgood

 Generic SQL grammar.

 Synthesized from MacroScope.g, tsqlselect.g, the official SQL 92 BNF, etc. 

 ** Header from MacroScope.g **

 From http://www.antlr.org/grammar/1062280680642/MS_SQL_SELECT.html ,
 simplified & extended with other SQL commands.

 ** Header from tsqlselect.g **

 Version: 0.8
 ANTLR Version: 2.7.2
 Date: 2003.08.25

 Description: This is a MS SQL Server 2000 SELECT statement grammar.

 =======================================================================================
 Author: Tomasz Jastrzebski
 Contact: tdjastrzebski@yahoo.com
 Working parser/lexer generated based on this grammar will available for some time at:
 http://jastrzebski.europe.webmatrixhosting.net/mssqlparser.aspx
*/


grammar GenericSQL;

options {
  contextSuperClass=fado.parse.GlobbingRuleContext;
}

statement
  : select ( SEMI )? EOF
  | insert ( SEMI )? EOF
  | update ( SEMI )? EOF
//  | delete ( SEMI )? EOF
  ;
  
subSelect
  : select
  | LPAREN select RPAREN
  ;
  
select
  : SELECT
    ( ALL | DISTINCT | UNIQUE )?
    ( TOP Integer ( PERCENT )? )?
    itemList
    ( into )?
    from
    ( join )*
//    ( joinList )
    ( where )?
    ( groupBy )?
    ( having )?
    ( orderBy )?
  ;


insert
  : INSERT into ( columnList )?
  ( values
//| select_statement
  )
  ;  

update
  : UPDATE tableRef SET setter ( COMMA setter )*
    ( where )?
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
  : function ( AS? alias )? 
  | expression ( ( AS )? alias )?
  | allColumns
  | caseExpression ( ( AS )? alias )?
  ;

allColumns
  : tableAlias DOT STAR
  ;

alias
  : Identifier
  ;
  
function
  : Identifier LPAREN ( expression ( COMMA expression )* )? RPAREN
  ;  

//functionName
//  : Identifier
////  : COUNT
////  | MIN
////  | MAX
//  ;
  
from
  : FROM fromItem ( COMMA fromItem )*
  ;
  
fromItem
  : ( ( LPAREN subSelect RPAREN ) 
    | tableRef 
    )
    ( ( AS )? alias )?
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
  fromItem alias
  /*
  ( ON conditionList
  | USING LPAREN columnRef ( COMMA columnRef )* RPAREN
  )?
  */
  ON conditionList
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
    : comparison
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
  : expression ( NOT )? IN LPAREN ( subSelect | expressionList ) RPAREN
  ;
  
between
  : expression ( NOT )? BETWEEN expression AND expression
  ;
  
isNull
  : expression IS ( NOT )? NULL
  ;
  
exists
  : EXISTS expression
  ;
  
like
//  : columnRef ( NOT )? LIKE String
  
  : expression ( NOT )? LIKE expression
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
  : expression ( ALL | ANY | SOME ) LPAREN subSelect RPAREN
  ;
  
expressionList
  : expression ( COMMA expression )*
  ;

nestedExpression
  : LPAREN expression RPAREN
  ;
  
expression
  : multiply ( ( PLUS | MINUS ) multiply )*
  ;
 
multiply
  : value ( ( STAR | DIVIDE ) value )* 
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
  | ( unary )?
    ( columnRef
    | nestedExpression
//    | LPAREN subSelect RPAREN
    )
  ;
  
literal
  : ( unary )? Float
  | ( unary )? Integer
  | String
  | TRUE
  | FALSE
  | date
  ;

date
  : '{d' Timestamp '}' // Date
  | '{t' Timestamp '}' // Time
  | '{ts' Timestamp '}' // Timestamp
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

MAX       : M A X ;
MIN       : M I N ;
COUNT     : C O U N T ;


Integer 
  : ( DIGIT )+
  ;
  
Float
  : DIGIT+ ( '.' DIGIT* )? ( E [-+]? DIGIT+ )?
  | '.' DIGIT+ ( E [-+]? DIGIT+ )?
  ;
  
String
  : '\'' ( ~'\'' | '\'\'' )* '\''
  ;
  
Timestamp 
  : DIGIT DIGIT DIGIT DIGIT '-'
		DIGIT DIGIT '-'
		DIGIT DIGIT [t ]
		DIGIT DIGIT ':' DIGIT DIGIT ':' DIGIT DIGIT
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