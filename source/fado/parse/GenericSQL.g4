/*
 GenericSQL.g4, for ANTLR 4.x

 Copyright 2022, 2014, 2011, 2010 Jason Osgood

 Generic SQL DML grammar.

*/

grammar GenericSQL;

options {
//  contextSuperClass=fado.parse.GlobbingRuleContext;
  caseInsensitive = true;
}

parse   : statement* EOF ;

statement
  : ( select
    | insert
    | update
//  | delete
    )
    SEMI?
  ;
  
select
  : 'SELECT'
    ( 'ALL' | 'DISTINCT' | 'UNIQUE' )?
    ( 'TOP' expression 'PERCENT'? ( 'WITH' 'TIES' )? )?
    item ( COMMA item )*
    into?
//    ( 'FROM' fromItem ( COMMA fromItem )* )?
    ( 'FROM' joiner )?
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

item
  : STAR exceptions?
  | reference DOT STAR exceptions?
  | expression alias?
  ;

joiner   : column ( COMMA column )*
         | column ( ( 'CROSS' | 'NATURAL' )? ( 'INNER' | ( 'FULL' | 'LEFT' | 'RIGHT' ) 'OUTER'? )? 'JOIN'
           column ( 'ON' expression | 'USING' LPAREN name ( COMMA name )* RPAREN )? )*
         //join     : ( 'CROSS' | 'NATURAL' )? ( 'INNER' | ( 'FULL' | 'LEFT' | 'RIGHT' ) 'OUTER'? )? 'JOIN' fromItem
         //           ( 'ON' expression | 'USING' LPAREN name ( COMMA name )* RPAREN )?
          | LPAREN joiner RPAREN
          ;

column : source ( 'AS'? name ( LPAREN name ( COMMA name )* RPAREN )? )?
       ;

source   : reference useIndex?
         | function
         | query
         | LPAREN source RPAREN
         ;

insert
  : 'INSERT' into columnList?
//  ( values
//// | select
//  )
  ;  

update
  : 'UPDATE' reference 'SET' setter ( COMMA setter )*
    where?
  ;

columnList
  : LPAREN reference ( COMMA reference )* RPAREN
  ;

setter
  : reference EQ literal
  ;
  
into
  : 'INTO' reference ( COMMA reference )*
  ;

// http://www.h2database.com/html/grammar.html#wildcard_expression
exceptions
  : 'EXCEPT' LPAREN reference ( COMMA reference )* RPAREN
  ;

expressionList
  : expression ( COMMA expression )*
  ;

expression
  : literal # stuff
  | Variable # var
  | ( MINUS | PLUS ) expression # unary
  | function # func
  | reference # ref

  // Best guess for precedence of operators
  | expression CONCAT expression # concat
  | expression CARET expression # caret
  | expression ( STAR | DIVIDE | MODULO ) expression # Products
  | expression ( PLUS | MINUS ) expression # Sums
  | expression ( LSHIFT | RSHIFT | AMP | PIPE ) expression # bitwise
  | expression ( LT | LTE | GT | GTE ) expression # compare
  | expression ( EQ | NEQ ) expression # equal
  // TODO: This should build LL parse tree, not LR
  | expression ( 'AND' | 'OR' ) expression # boolean
  | 'NOT' expression # not
  | LPAREN expressionList RPAREN # nested
  | LPAREN RPAREN # empty
  | ( 'ALL' | 'ANY' | 'SOME' | 'EXISTS' | 'UNIQUE' )? LPAREN query RPAREN # subquery
  | expression 'NOT'* ( 'LIKE' | 'ILIKE' ) expression ( 'ESCAPE' expression )? # LIKE
  | expression 'NOT'* 'REGEXP' expression ( 'ESCAPE' expression )? # REGEXP
  | expression 'IS' 'NOT'? 'NULL' # IsNull
  | expression 'IS' 'NOT'? 'DISTINCT' 'FROM' expression # IsDistinct
  | expression 'NOT'* 'IN' LPAREN ( query | expressionList )? RPAREN # in
// TODO additional 'IN' rules
//          | ( databaseName DOT )? table_name
//          | ( databaseName DOT )? table_function_name LPAREN ( expression ( COMMA expression )* )? RPAREN
  | expression 'NOT'* 'BETWEEN' ( 'ASYMMETRIC' | 'SYMMETRIC' )? expression 'AND' expression # between
  | 'INTERSECTS' LPAREN expression COMMA expression RPAREN # intersects
  | 'CASE' expression? ( 'WHEN' expression 'THEN' expression )+ ( 'ELSE' expression )? 'END' # case
  | ( 'CAST' | 'TRY_CAST' ) LPAREN expression 'AS' ID RPAREN # cast
  | expression 'COLLATE' ID # collate
//  TODO | raise_function
  ;

query
  : select
  | 'TABLE' reference orderBy? // offset? fetch?
  | 'VALUES' 'ROWS'? expressionList
  ;

alias    : 'AS'? name
         ;

function
  : name LPAREN ( ( 'ALL' | 'DISTINCT' )? expressionList? | STAR )? RPAREN
    ( 'FILTER' LPAREN 'WHERE' expression RPAREN )?
    ( 'OVER'
      ( reference
      | LPAREN reference?
        ( 'PARTITION' 'BY' expressionList )?
        ( 'ORDER' 'BY' orderSpec ( COMMA orderSpec )* )? window?
        RPAREN
      )
    )?

  // some ODBC function names are also SQL reserved words
  | '{fn' ( 'LEFT' | 'RIGHT' | 'INSERT' | name ) LPAREN expressionList? RPAREN '}'
//  | ID? 'FUNCTION' ID LPAREN expressionList? RPAREN // T-SQL
//  | ID DOT ID LPAREN expressionList? RPAREN // T-SQL?
  ;

orderSpec : expression ( 'COLLATE' ID )? ( 'ASC' | 'DESC' )? ( 'NULLS' ( 'FIRST' | 'LAST' ))?
          ;

window    : ( 'RANGE'| 'ROWS' | 'GROUPS' )
            ( preceding | 'BETWEEN' following 'AND' following )
            ( 'EXCLUDE' ( 'CURRENT' 'ROW' | 'GROUP' | 'TIES' | 'NO' 'OTHERS' )? )?
          ;

preceding : ( 'UNBOUNDED' | literal ) 'PRECEDING'
          | 'CURRENT' 'ROW'
          ;

following : ( 'UNBOUNDED' | literal ) 'FOLLOWING'
          | preceding
          ;

//table
//  : ( LPAREN select RPAREN
//    | name
//    )
//    alias?
//  ;

useIndex : 'USE' 'INDEX' LPAREN ID ( COMMA ID )* RPAREN
         ;

union    : ( 'UNION' 'ALL'? | 'EXCEPT' | 'INTERSECT' ) query // ( LPAREN query RPAREN | query )
         ;

where    : 'WHERE' expression
         ;
  
groupBy  : 'GROUP' 'BY' ( expressionList? | LPAREN expressionList? RPAREN )
         ;
  
having   : 'HAVING' expressionList
         ;

qualify  : 'QUALIFY' expression
         ;
  
orderBy
  : 'ORDER' 'BY' orderByItem ( COMMA orderByItem )*
  ;
  
orderByItem
  : expression ( 'ASC' | 'DESC' )?
  ;

limit
  : 'LIMIT' expression (( 'OFFSET' | COMMA ) expression )?
  ;

forUpdate
  : 'FOR' 'UPDATE'
  ;

//values
//  : 'VALUES' LPAREN literal ( COMMA literal )* RPAREN
//  ;

// TODO add 'UNKNOWN'?
literal
  : ( MINUS | PLUS )? Float
  | ( MINUS | PLUS )? Integer
  | String
  | 'TRUE'
  | 'FALSE'
  | 'NULL'
  | Date
  | 'DATE' String
  | QUESTION
  ;

reference : name ( DOT name )* ;

name      : ID | String ;

// Lexer

// Punctuation

LPAREN   : '(' ;
RPAREN   : ')' ;
//LCURLY   : '{' ;
//RCURLY   : '}' ;
LSQUARE  : '[' ;
RSQUARE  : ']' ;
COMMA    : ',' ;
SEMI     : ';' ;

PLUS     : '+' ;
MINUS    : '-' ;
DIVIDE   : '/' ;
STAR     : '*' ;
MODULO   : '%' ;
CARET    : '^' ; // exponent
AT       : '@' ; // absolute value
BANG     : '!' ; // factorial

EQ       : '=' ;
NEQ      : '<>' | '!=' ;
LT       : '<' ;
LTE      : '<=' ;
GT       : '>' ;
GTE      : '>=' ;

CONCAT   : '||' ; // concatenation
AMP      : '&' ; // bitwise AND
PIPE     : '|' ; // bitwise XOR
POUND    : '#' ; // bitwise XOR
TILDE    : '~' ; // bitwise NOT
LSHIFT   : '<<' ; // bitwise shift left
RSHIFT   : '>>' ; // bitwise shift right

DOT      : '.' ;
COLON    : ':' ;
FUNCTION : '::' ;
QUESTION : '?' ;

// Tokens

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

// ODBC date time
Date     : '{d' String '}'
         | '{t' String '}'
         | '{ts' String '}'
         ;

ID
  : '"' ( ~'"' | '""' )* '"'
  | '`' ( ~'`' | '``' )* '`'
  | '[' ~']'* ']'
  | [A-Z_] [A-Z_0-9]*
  ;

Variable
  : [:@$] ID
  | '?' DIGIT*
  ;

Comment
  : '--' ~[\r\n]* -> channel( HIDDEN )
  ;

BlockComment
  : '/*' ( Comment | . )*? '*/' -> channel( HIDDEN )
  ;

Whitespace
  : [ \t\r\n] -> channel( HIDDEN )
  ;

fragment DIGIT : [0-9] ;

Whoops : . ;