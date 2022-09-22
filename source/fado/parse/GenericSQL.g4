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

@parser::header
{
import java.util.HashSet;
}

@parser::members
{
	HashSet<String> __keywords = keywords();

	public HashSet<String> keywords()
	{
		HashSet<String> words = new HashSet<>();
		// Skip first literal
		for( int nth = 1; nth < _LITERAL_NAMES.length; nth++ )
		{
			String symbol = _SYMBOLIC_NAMES[ nth ];
			if( symbol == null )
			{
				String keyword = _LITERAL_NAMES[ nth ];
				keyword = keyword.substring( 1, keyword.length() - 1 );
				if( Character.isLetter( keyword.charAt( 0 )))
				{
					words.add( keyword );
				}
			}
		}
		return words;
	}

    public boolean isKeyword( Token t )
    {
        String text = t.getText().toUpperCase();
        boolean contains = keywords().contains( text );
        return contains;
    }
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

// TODO experiment replace ( exprList ) w/ just expr
select
  : 'SELECT'
    distinct?
    ( 'TOP' expression 'PERCENT'? ( 'WITH' 'TIES' )? )?
    ( item ( COMMA item )* )?
    into?
    ( 'FROM' join )?
    where?
    groupBy?
    having?
    ( 'WINDOW' windowAlias ( COMMA windowAlias )* )?
    qualify?
    union?
    orderBy?
    offset?
    fetch?
    limit?
    forUpdate?
  ;

distinct : 'ALL'
         | 'DISTINCT' ( 'ON' LPAREN expressionList RPAREN )?
         | 'UNIQUE' // oracle
         ;

item     : (( reference DOT )? STAR ) exceptions?
         | expression alias?
         ;

alias    : 'AS'? name
         ;

join     : table ( COMMA table )*
         | LPAREN join RPAREN
         | table ( ( 'INNER' | ( 'FULL' | 'LEFT' | 'RIGHT' ) 'OUTER' | 'CROSS' | 'NATURAL' )?
           'JOIN' join ( 'ON' expression | 'USING' LPAREN name ( COMMA name )* RPAREN )? )*
           | table
         ;

table    : source ( 'AS'? name ( LPAREN name ( COMMA name )* RPAREN )? )? useIndex?
         ;

source   : query
         | reference
         | function
         | unnest
         | ( 'TABLE' | 'TABLE_DISTINCT' ) LPAREN tableRow ( COMMA tableRow )* RPAREN
         | LPAREN source RPAREN
         ;

//tableRow : ( name | reference ) keyword EQ ( array | row )
tableRow : ( name | reference ) keyword EQ ( array | expression )
         ;

query    : select
         | 'TABLE' reference orderBy? // offset? fetch?
//         | 'VALUES' row ( COMMA row )*
         | 'VALUES' expressionList
         ;

unnest : 'UNNEST' LPAREN array ( COMMA array )* RPAREN ( 'WITH' 'ORDINALITY' )?
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

exceptions
  : 'EXCEPT' LPAREN reference ( COMMA reference )* RPAREN
  ;

expressionList
  : LPAREN expressionList RPAREN
  | expression ( COMMA expression )*
  ;

// Best guess for precedence of operators
expression
  : expression CONCAT expression
  | expression CARET expression
  | expression ( STAR | DIVIDE | MODULO ) expression
  | expression ( PLUS | MINUS ) expression
  | expression ( LSHIFT | RSHIFT | AMP | PIPE ) expression
  | expression compare
  | 'NOT' expression
  | LPAREN expression RPAREN
  | LPAREN RPAREN
  | quantified
  | expression like
  | expression regexp
  | expression isNull
  | expression isDistinct
  | expression isBoolean
  | expression isType
  | expression in
  | expression between
  | 'INTERSECTS' LPAREN expression COMMA expression RPAREN
  | 'CASE' expression ( 'WHEN' ( expressionList | whenPred ) 'THEN' expression )+ ( 'ELSE' expression )? 'END'
  | 'CASE' ( 'WHEN' expressionList 'THEN' expression )+ ( 'ELSE' expression )? 'END'
  | ( 'CAST' | 'TRY_CAST' ) LPAREN expression 'AS' type RPAREN
  | expression 'COLLATE' keyword
//  | raise_function
  // TODO: This should build LL parse tree, not LR
  | expression ( 'AND' | 'OR' ) expression
  | LPAREN query RPAREN 'EXCEPT' LPAREN query RPAREN
  | LPAREN expressionList RPAREN DOT name
  | 'ROW' LPAREN expressionList RPAREN
  | query
  | literal
  | Variable
      | ( MINUS | PLUS ) expression
      | function
      | reference
//      | row
      | array
;

whenPred  : compare
          | quantified
          | isNull
          | isDistinct
          | isBoolean
          | isType
          | between
          | in
          | like
          | regexp
          ;

compare    : ( LT | LTE | GT | GTE | EQ | NEQ | OVERLAP ) expression ;
quantified : ( 'ALL' | 'ANY' | 'SOME' | 'EXISTS' | 'UNIQUE' )? LPAREN query RPAREN ;
isNull     : 'IS' 'NOT'? 'NULL' ;
isDistinct : 'IS' 'NOT'? 'DISTINCT' 'FROM' expression ;
isBoolean  : 'IS' 'NOT'? boolean ;
isType     : 'IS' 'OF' LPAREN keyword ( COMMA keyword )* RPAREN;
between    : 'NOT'* 'BETWEEN' ( 'ASYMMETRIC' | 'SYMMETRIC' )? expression 'AND' expression ;

// TODO additional 'IN' rules
//          | ( databaseName DOT )? table_name
//          | ( databaseName DOT )? table_function_name LPAREN ( expression ( COMMA expression )* )? RPAREN
in         : 'NOT'* 'IN' expressionList;
//in         : 'NOT'* 'IN' LPAREN (  expressionList )? RPAREN ;
//in         : 'NOT'* 'IN' LPAREN ( /* query */ | expressionList )? RPAREN ;
like       : 'NOT'* ( 'LIKE' | 'ILIKE' ) expression ( 'ESCAPE' expression )? ;
regexp     : 'NOT'* 'REGEXP' expression ( 'ESCAPE' expression )? ;



function
  : name LPAREN ( ( 'ALL' | 'DISTINCT' )? expressionList | STAR )? RPAREN
    ( 'FILTER' LPAREN 'WHERE' expression RPAREN )?
    ( 'OVER' window )?
  // some ODBC function names are also SQL reserved words
  | '{fn' keyword LPAREN expressionList? RPAREN '}'
//  | ID? 'FUNCTION' ID LPAREN expressionList? RPAREN // T-SQL
//  | ID DOT ID LPAREN expressionList? RPAREN // T-SQL?
  ;


type : 'ROW' LPAREN name type ( COMMA name type )* RPAREN
     | keyword* ( LPAREN integer ( COMMA integer )? RPAREN keyword* )?
     ;

useIndex : 'USE' 'INDEX' LPAREN keyword ( COMMA keyword )* RPAREN
         ;

union    : ( 'UNION' 'ALL'? | 'EXCEPT' | 'INTERSECT' ) query // ( LPAREN query RPAREN | query )
         ;

where    : 'WHERE' expression
         ;
  
//groupBy  : 'GROUP' 'BY' ( expressionList? | LPAREN expressionList? RPAREN )
groupBy  : 'GROUP' 'BY' expressionList?
         ;
  
having   : 'HAVING' expressionList
         ;

windowAlias : name 'AS' window
            ;

window      : name
            | LPAREN name? partitionBy? orderBy? windowFrame? RPAREN
            ;

windowFrame : ( 'RANGE'| 'ROWS' | 'GROUPS' )
              ( preceding | 'BETWEEN' following 'AND' following )
              ( 'EXCLUDE' ( 'CURRENT' 'ROW' | 'GROUP' | 'TIES' | 'NO' 'OTHERS' )? )?
            ;

preceding : ( 'UNBOUNDED' | literal ) 'PRECEDING'
          | 'CURRENT' 'ROW'
          ;

following : ( 'UNBOUNDED' | literal ) 'FOLLOWING'
          | preceding
          ;

qualify  : 'QUALIFY' expression
         ;

partitionBy : 'PARTITION' 'BY' expressionList
            ;

orderBy : 'ORDER' 'BY' orderByItem ( COMMA orderByItem )*
        ;

//orderByItem  : expression ( 'COLLATE' ID )? ( 'ASC' | 'DESC' )? ( 'NULLS' ( 'FIRST' | 'LAST' ))?
orderByItem  : expression ( 'ASC' | 'DESC' )? ( 'NULLS' ( 'FIRST' | 'LAST' ))?
          ;

offset      : 'OFFSET' expression ( 'ROW' | 'ROWS' )
            ;

fetch  : 'FETCH' ( 'FIRST' | 'NEXT' ) ( expression 'PERCENT'? )?
         ( 'ROW' | 'ROWS' ) ( 'ONLY' | ( 'WITH' 'TIES' ))
       ;

limit       : 'LIMIT' expression (( 'OFFSET' | COMMA ) expression )?
            ;

forUpdate   : 'FOR' 'UPDATE'
            ;

//row       : 'ROW'? LPAREN expressionList? RPAREN
//          // two or more expressions
////          | LPAREN expression COMMA expressionList RPAREN
////          | expression
//          ;

array     : 'ARRAY' LSQUARE expressionList? RSQUARE
          ;

literal
  : ( MINUS | PLUS )? Float
  | integer
  | String
  | Blob
  | boolean
  | 'NULL'
  | date
  | ( 'TIME' | 'TIMESTAMP' ) (( 'WITH' | 'WITHOUT' ) 'TIME' 'ZONE' )? String?
  | QUESTION
  | 'INTERVAL' String ( 'YEAR' | 'MONTH' | 'DAY' | 'HOUR' | 'SECOND' )
  ;

integer   : ( MINUS | PLUS )? Integer ;
// TODO Convert boolean values from keywords to lexer tokens?
boolean   : 'TRUE' | 'FALSE' | 'UNKNOWN' ;
date
// ODBC date time
     : '{d' String '}'
         | '{t' String '}'
         | '{ts' String '}'
  | 'DATE' String
;
keyword   : ID | { isKeyword( getCurrentToken() ) }? . ;
name      : String | keyword;
reference : name ( DOT name )* ;

// Lexer

// Punctuation

//NULL : 'NULL';

LPAREN   : '(' ;
RPAREN   : ')' ;
LSQUARE  : '[' ;
RSQUARE  : ']' ;
COMMA    : ',' ;
SEMI     : ';' ;

PLUS     : '+' ;
MINUS    : '-' ;
DIVIDE   : '/' ;
STAR     : '*' ; // multiplication
MODULO   : '%' ;
CARET    : '^' ; // exponent
AT       : '@' ; // absolute value
BANG     : '!' ; // factorial

EQ       : '=' | ':=' ;
NEQ      : '<>' | '!=' ;
LT       : '<' ;
LTE      : '<=' ;
GT       : '>' ;
GTE      : '>=' ;
OVERLAP  : '&&' ;

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

Blob options { caseInsensitive=false; }
  : [X] '\'' ( ~'\'' | '\'\'' )* '\''
  ;


ID
  : '"' ( ~'"' | '""' )* '"'
  | '`' ( ~'`' | '``' )* '`'
//  | '[' ~']'* ']'
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
  : '/*' .*? '*/' -> channel( HIDDEN )
  ;

Whitespace
  : [ \t\r\n] -> channel( HIDDEN )
  ;

fragment DIGIT : [0-9] ;

Whoops : . ;