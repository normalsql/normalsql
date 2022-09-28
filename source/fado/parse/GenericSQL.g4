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

@parser::header {
import java.util.HashSet;
}

@parser::members {
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
        String text1 = t.getText();
        if( !Character.isLetter( text1.charAt( 0 ))) return false;
        String text = text1.toUpperCase();
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
    SEMI
  ;

// TODO experiment replace ( exprList ) w/ just expr
select
  : 'SELECT'
    distinct?
    ( 'TOP' ( Integer | Float | LPAREN expression RPAREN ) 'PERCENT'? ( 'WITH' 'TIES' )? )?
    ( item ( COMMA item )* )?
    into?
    ( 'FROM' join ( COMMA join )* )?
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

join     : ( LPAREN join RPAREN )
         | join joinStyle join joinOn?
         | table
         ;

joinStyle : ( 'INNER' | ( 'FULL' | 'LEFT' | 'RIGHT' ) 'OUTER'? | 'CROSS' | 'NATURAL' )? 'JOIN'
          ;

joinOn    : 'ON' expression
          | 'USING' LPAREN name ( COMMA name )* RPAREN
          ;

table    : source  ( 'AS'? name ( LPAREN name ( COMMA name )* RPAREN )? )? useIndex?
         ;

source   : LPAREN source RPAREN
         | query
         | reference
         | function
         | unnest
         | ( 'TABLE' | 'TABLE_DISTINCT' ) LPAREN tableRow ( COMMA tableRow )* RPAREN
         ;

//tableRow : ( name | reference ) keyword EQ ( array | row )
tableRow : ( name | reference ) keyword EQ ( array | expression )
         ;

query    : select
         | 'TABLE' reference orderBy? // offset? fetch?
         | 'VALUES' row ( COMMA row )*
//         | 'VALUES' expressionList
         ;

row :  'ROW'? LPAREN expressionList RPAREN
| expression
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
  : expression ( COMMA expression )*
  | LPAREN expressionList RPAREN
  ;

// Best guess for precedence of operators
expression
  : LPAREN RPAREN
    | LPAREN expression RPAREN
    | LPAREN query RPAREN 'EXCEPT' LPAREN query RPAREN
    | LPAREN expressionList RPAREN DOT name
   | expression CONCAT expression
  | expression CARET expression
  | expression ( STAR | DIVIDE | MODULO ) expression
  | expression ( PLUS | MINUS ) expression
  | expression ( LSHIFT | RSHIFT | AMP | PIPE ) expression
  | expression compare
  | 'NOT' expression
//  | row
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
  | function
  | expression function2
  | query
  | literal
  | Variable
  | ( MINUS | PLUS ) expression
  | reference
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

compare    : ( LT | LTE | GT | GTE | EQ | NEQ | OVERLAP | TILDE1 | TILDE2 | TILDE3 | TILDE4 ) expression ;
quantified : ( 'ALL' | 'ANY' | 'SOME' | 'EXISTS' | 'UNIQUE' )? LPAREN query RPAREN ;
isNull     : 'IS' 'NOT'? 'NULL' ;
isDistinct : 'IS' 'NOT'? 'DISTINCT' 'FROM' expression ;
isBoolean  : 'IS' 'NOT'? bool ;
isType     : 'IS' 'OF' LPAREN keyword ( COMMA keyword )* RPAREN;
between    : 'NOT'* 'BETWEEN' ( 'ASYMMETRIC' | 'SYMMETRIC' )? expression 'AND' expression ;

// TODO additional 'IN' rules
//          | ( databaseName DOT )? table_name
//          | ( databaseName DOT )? table_function_name LPAREN ( expression ( COMMA expression )* )? RPAREN
in         : 'NOT'* 'IN' expressionList;
like       : 'NOT'* ( 'LIKE' | 'ILIKE' ) expression ( 'ESCAPE' expression )? ;
regexp     : 'NOT'* 'REGEXP' expression ( 'ESCAPE' expression )? ;

function : 'TRIM' LPAREN ( 'BOTH' | 'LEADING' | 'TRAILING' )? expression? 'FROM'? expression RPAREN
         | 'SUBSTRING' LPAREN expression 'FROM' expression ( 'FOR' expression )? RPAREN
         | 'JSON_OBJECTAGG' LPAREN jsonKeys onNull? uniqueKeys? RPAREN filter? over?
         | 'JSON_ARRAYAGG' LPAREN ( 'ALL' | 'DISTINCT' )? expression orderBy? onNull? RPAREN filter? over?
         | 'EXTRACT' LPAREN timeSpan 'FROM' expression RPAREN
         // Window functions
         | keyword LPAREN ( ( 'ALL' | 'DISTINCT' )? expressionList | STAR )? RPAREN
           filter? ( 'FROM' ( 'FIRST' | 'LAST' ) )? ( ( 'RESPECT' | 'IGNORE' ) 'NULLS' )? over?

         // Aggregate functions
         | keyword LPAREN literal RPAREN 'WITHIN' 'GROUP' LPAREN orderBy RPAREN filter? over?
         | keyword LPAREN ( 'ALL' | 'DISTINCT' )? ( literal | name ) orderBy RPAREN filter? over?

         // Generic functions
         | keyword LPAREN keyword 'FROM' keyword String RPAREN
//         | keyword

         // some ODBC function names are also SQL reserved words
         | '{fn' keyword LPAREN expressionList? RPAREN '}'
         // | ID? 'FUNCTION' ID LPAREN expressionList? RPAREN // T-SQL
         // | ID DOT ID LPAREN expressionList? RPAREN // T-SQL?
         ;

filter : 'FILTER' LPAREN 'WHERE' expression RPAREN
       ;

over   : 'OVER' window
       ;

function2
  : FUNCTION keyword
  ;

type : 'ROW' LPAREN name type ( COMMA name type )* RPAREN
     | keyword+ ( LPAREN integer ( COMMA integer )? RPAREN keyword* )?
//     | keyword ( LPAREN integer ( COMMA integer )? RPAREN keyword* )?
     ;

useIndex : 'USE' 'INDEX' LPAREN keyword ( COMMA keyword )* RPAREN
         ;

union    : ( 'UNION' 'ALL'? | 'EXCEPT' | 'INTERSECT' ) ( LPAREN query RPAREN | query )
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

preceding : ( 'UNBOUNDED' | 'CATEGORY' | expression ) 'PRECEDING'
          | 'CURRENT' 'ROW'
          ;

following : ( 'UNBOUNDED' | expression ) 'FOLLOWING'
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

offset      : 'OFFSET' expression ( 'ROW' | 'ROWS' )?
            ;

fetch  : 'FETCH' ( 'FIRST' | 'NEXT' ) ( expression 'PERCENT'? )?
         ( 'ROW' | 'ROWS' ) ( 'ONLY' | ( 'WITH' 'TIES' ))
       ;

limit       : 'LIMIT' expression (( 'OFFSET' | COMMA ) expression )?
            ;

forUpdate   : 'FOR' 'UPDATE'
            ;

array     : 'ARRAY' LSQUARE expressionList? RSQUARE
          ;

literal
  : float
  | integer
  | String
  | Blob
  | Unicode ( 'UESCAPE' String )?
  | Hex
  | bool
  | 'NULL'
  | date
  | ( 'TIME' | 'TIMESTAMP' ) (( 'WITH' | 'WITHOUT' ) 'TIME' 'ZONE' )? String?
  | QUESTION
  | interval
  | jsonObject
  | jsonArray
  ;

interval : 'INTERVAL' String timeSpan ;

timeSpan : 'YEAR' ( 'TO' 'MONTH' )?
         | 'MONTH'
         | 'DAY' ( 'TO' ( 'HOUR' | 'MINUTE' | 'SECOND' ) )?
         | 'HOUR' ( 'TO' ( 'MINUTE' | 'SECOND' ) )?
         | 'MINUTE' ( 'TO' 'SECOND' )?
         | 'SECOND'
         ;

float   : ( MINUS | PLUS )? Float ;
integer   : ( MINUS | PLUS )? Integer ;
// TODO Convert boolean values from keywords to lexer tokens?
bool       : 'TRUE' | 'FALSE' | 'UNKNOWN' ;

date       : 'DATE' String
           | 'TIMESTAMP' String
           // ODBC date time
           | '{d' String '}'
           | '{t' String '}'
           | '{ts' String '}'
           ;

jsonObject    : 'JSON_OBJECT' LPAREN jsonKeys? onNull? uniqueKeys? formatJson? RPAREN
              ;

jsonKeys      : jsonKey ( COMMA jsonKey )*
              ;

jsonKey       : name COLON expression
              | 'KEY'? name 'VALUE' expression
              ;

jsonArray    : 'JSON_ARRAY' LPAREN ( expressionList | LPAREN query RPAREN )?
               formatJson? onNull?
               RPAREN
             ;

onNull : ( 'NULL' | 'ABSENT' ) 'ON' 'NULL' ;
uniqueKeys : ( 'WITH' | 'WITHOUT' ) 'UNIQUE' 'KEYS' ;
formatJson : 'FORMAT' 'JSON' ;

keyword   : ID | { isKeyword( getCurrentToken() ) }? . ;
name      : String | Unicode | keyword ;
reference : name ( DOT name )* ;

// Lexer

// Punctuation

// Reserved keywords
//NULL : 'NULL';
VALUES : 'VALUES';
SELECT : 'SELECT';
FROM   : 'FROM';
WHERE  : 'WHERE';
INSERT : 'INSERT';
DELETE : 'DELETE';
JOIN   : 'JOIN';
//LEFT   : 'LEFT';

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
// TILDE     : '~' ; // bitwise NOT
// https://www.postgresql.org/docs/current/functions-matching.html
// POSIX Regular Exressions
TILDE1    : '~' ; // match regex case sensitive
TILDE2    : '~*' ; // match regex case insensitive
TILDE3    : '!~' ; // not match regex case sensitive
TILDE4    : '!~*' ; // not match regex case insensitive

LSHIFT   : '<<' ; // bitwise shift left
RSHIFT   : '>>' ; // bitwise shift right

DOT      : '.' ;
FUNCTION : '::' ;
COLON    : ':' ;
QUESTION : '?' ;

Hex
  : '0x' [A-F0-9]+
  ;

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

String // options { caseInsensitive=false; }
  : [N]? GOBBLE
  ;

Blob // options { caseInsensitive=false; }
  : 'X' GOBBLE
  ;

Unicode // options { caseInsensitive=false; }
  : 'U&' GOBBLE// ( 'UESCAPE' )
  ;

ID
  : '"' ( ~'"' | '""' )* '"'
  | '`' ( ~'`' | '``' )* '`'
//  | '[' ~']'* ']'
  | [A-Z_] [A-Z_0-9]*
  ;

Variable
  :
  [:@$] ID
  |
  '?' DIGIT*
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

fragment GOBBLE : '\'' ( ~'\'' | '\'\'' )* '\'' ;
fragment DIGIT : [0-9] ;

Whoops : . ;