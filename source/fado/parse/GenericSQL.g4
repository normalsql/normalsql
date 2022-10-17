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
  : ( union
    | insert
    | update
//  | delete
//  | merge
    )
    SEMI // ?
  ;

union    : rowset (( 'UNION' | 'EXCEPT' | 'INTERSECT' | 'MINUS' ) ( 'ALL' | 'DISTINCT' )? rowset )*
         ;

rowset   : LPAREN union RPAREN
         | select
         | 'TABLE' reference
         | values
         ;

// TODO experiment replace ( exprList ) w/ just expr
select
  : 'SELECT'
    distinct?
    ( 'TOP' ( Decimal | Real | LPAREN expression RPAREN ) 'PERCENT'? ( 'WITH' 'TIES' )? )?
    ( column ( COMMA column )* )?
    into?
    ( 'FROM' join )?
    where?
    groupBy?
    having?
    ( 'WINDOW' windowAlias ( COMMA windowAlias )* )?
    qualify?
    orderBy? offset? fetch? limit?
    forUpdate?
  ;

values : 'VALUES' expressionList
       ;

distinct : 'ALL'
         | 'DISTINCT' ( 'ON' LPAREN expressionList RPAREN )?
         | 'UNIQUE' // oracle
         ;

column   : (( reference DOT )? STAR ) exceptions?
         | expression alias?
         ;

alias    : 'AS'? name
         ;

join     : ( table | LPAREN join RPAREN )
           ( joinType join ( 'ON' expression | 'USING' columnRefs )? )*
         ;

table    : LPAREN union RPAREN tableAlias?
         | values tableAlias?
         | function tableAlias?
         | unnest
         | ( 'TABLE' | 'TABLE_DISTINCT' ) LPAREN columnSpec ( COMMA columnSpec )* RPAREN tableAlias?
         // TODO 'delete' and 'merge' rules
         | ( 'NEW' | 'OLD' | 'FINAL' ) 'TABLE' LPAREN ( insert | update /* | delete | merge */ ) RPAREN
////         | 'JSON_TABLE'
////         | 'XMLTABLE'
         | LPAREN table RPAREN tableAlias?
         | reference tableAlias? useIndex?
         ;

tableAlias : alias columnRefs? ;

columnRefs : LPAREN name ( COMMA name )* RPAREN
         ;

joinType : ( 'INNER' | ( 'FULL' | 'LEFT' | 'RIGHT' ) 'OUTER'? | 'CROSS' | 'NATURAL' )? 'JOIN'
         | COMMA
         ;

// TODO: just use 'reference'
columnSpec  : ( name | reference ) keyword EQ ( array | expression )
       ;

unnest : 'UNNEST' LPAREN array ( COMMA array )* RPAREN ( 'WITH' 'ORDINALITY' )?
       ;

insert
  : 'INSERT' into refs?
//  ( values
//// | select
//  )
  ;  

refs : LPAREN reference ( COMMA reference )* RPAREN ;


update
  : 'UPDATE' reference 'SET' setter ( COMMA setter )*
    where?
  ;


setter
  : reference EQ value
  ;
  
into
  : 'INTO' reference ( COMMA reference )*
  ;

exceptions
  : 'EXCEPT' LPAREN reference ( COMMA reference )* RPAREN
  ;

expressionList
  : expression ( COMMA expression )*
  ;

// Best guess for precedence of operators
expression
  : LPAREN RPAREN
  | LPAREN expression RPAREN
  | union
  | 'ROW'? LPAREN expressionList RPAREN
  | LPAREN expressionList RPAREN DOT name
  | expression CONCAT expression
  | expression CARET expression
  | expression ( STAR | DIVIDE | MODULO ) expression
  | expression ( PLUS | MINUS ) expression
  | expression ( LSHIFT | RSHIFT | AMP | PIPE ) expression
  | expression compare
  | 'NOT' expression
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
  | expression ( 'AND' | 'OR' ) expression // TODO: This should build LL parse tree, not LR
  | function
  | expression function2
  | value
  | Variable // TODO move this to literal?
  | ( MINUS | PLUS ) expression // TODO merge with 'NOT', remove 'NOT'*
  | reference
  | array
  | expression 'AT' ( 'LOCAL' | 'TIME' 'ZONE' ( interval | string ) )?
  | ( 'NEXT' | 'CURRENT' ) 'VALUE' 'FOR' reference
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
quantified : ( 'ALL' | 'ANY' | 'SOME' | 'EXISTS' | 'UNIQUE' )? LPAREN rowset RPAREN ; // TODO should be 'union'?
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
         | 'JSON_ARRAYAGG' LPAREN allDistinct? expression orderBy? onNull? RPAREN filter? over?
         | 'EXTRACT' LPAREN keyword 'FROM' expression RPAREN
         | 'LISTAGG' LPAREN allDistinct? expression COMMA string
           ( 'ON''OVERFLOW' ( 'ERROR' | 'TRUNCATE' string? ( 'WITH' | 'WITHOUT' ) 'COUNT' ))? RPAREN withinGroup? filter? over?
         | 'STRING_AGG' LPAREN expression COMMA string orderBy RPAREN
         | 'GROUP_CONCAT' LPAREN 'DISTINCT'? expressionList orderBy? ( 'SEPARATOR' string )? RPAREN filter?

         | keyword LPAREN ( allDistinct? expressionList | STAR )? RPAREN withinGroup? filter? firstLast? respectIgnore? over?

         | keyword LPAREN allDistinct? ( value | name ) orderBy RPAREN ( LSQUARE expression RSQUARE )? filter? over?

         | keyword LPAREN keyword 'FROM' keyword String RPAREN

         // some ODBC function names are also SQL reserved words
         | '{fn' keyword LPAREN expressionList? RPAREN '}'

         //         | keyword LPAREN ( ( 'ALL' | 'DISTINCT' )? expressionList | STAR )? RPAREN
         //           filter? firstLast? respectIgnore? over?

         // | ID? 'FUNCTION' ID LPAREN expressionList? RPAREN // T-SQL
         // | ID DOT ID LPAREN expressionList? RPAREN // T-SQL?
         ;

allDistinct : 'ALL' | 'DISTINCT' ;
withinGroup : 'WITHIN' 'GROUP' LPAREN orderBy RPAREN ;
firstLast : 'FROM' ( 'FIRST' | 'LAST' ) ;
respectIgnore : ( 'RESPECT' | 'IGNORE' ) 'NULLS' ;
filter : 'FILTER' LPAREN 'WHERE' expression RPAREN
       ;

over   : 'OVER' window
       ;

function2
  : CAST keyword
  ;

type : 'ROW' LPAREN name type ( COMMA name type )* RPAREN
     | keyword+ ( LPAREN decimal ( COMMA decimal )? RPAREN keyword* )?
     ;

useIndex : 'USE' 'INDEX' LPAREN keyword ( COMMA keyword )* RPAREN
         ;


where    : 'WHERE' expression
         ;
  
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

value
  : real
  | decimal
  | string
  | Hexadecimal
  | bool
  | 'NULL'
  | date
  | time
  | QUESTION
  | interval
  | jsonObject
  | jsonArray
  ;

real     : ( MINUS | PLUS )? Real ;
decimal  : ( MINUS | PLUS )? Decimal ;
string   : String+ | unicode | national | blob ;
unicode  : Unicode String* ( 'UESCAPE' String )? ;
national : National String* ;
blob     : Blob String* ;

// TODO isKeyword(...) ignore quoted strings Unicode and Blob
keyword   : ID | { isKeyword( getCurrentToken() ) }? . ; // TODO rename to 'id'
name      : keyword  | Name  ( 'UESCAPE' String )?;
reference : name ( DOT name )* ; // TODO: rename to 'ref'?


// TODO Convert boolean values from keywords to lexer tokens?
bool       : 'TRUE' | 'FALSE' | 'UNKNOWN' ;

date       : 'DATE' String
           // ODBC date time
           | '{d' String '}'
           | '{t' String '}'
           | '{ts' String '}'
           ;

time       : ( 'TIME' | 'TIMESTAMP' ) (( 'WITH' | 'WITHOUT' ) 'TIME' 'ZONE' )? String?
           ;

//interval : 'INTERVAL' expression timeSpan ;
interval : 'INTERVAL' expression keyword ( 'TO' keyword )?;

timeSpan : 'EPOCH'
         | 'YEAR' ( 'TO' 'MONTH' )?
         | 'MONTH'
         | 'DAY' ( 'TO' ( 'HOUR' | 'MINUTE' | 'SECOND' ) )?
         | 'HOUR' ( 'TO' ( 'MINUTE' | 'SECOND' ) )?
         | 'MINUTE' ( 'TO' 'SECOND' )?
         | 'SECOND'
         ;

jsonObject    : 'JSON_OBJECT' LPAREN jsonKeys? onNull? uniqueKeys? formatJson? RPAREN
              ;

jsonKeys      : jsonKey ( COMMA jsonKey )*
              ;

jsonKey       : ( string | name ) COLON expression
              | 'KEY'? ( string | name ) 'VALUE' expression
              ;

jsonArray    : 'JSON_ARRAY' LPAREN ( expressionList | LPAREN rowset RPAREN )? // TODO should be 'union'?
               formatJson? onNull?
               RPAREN
             ;

onNull : ( 'NULL' | 'ABSENT' ) 'ON' 'NULL' ;
uniqueKeys : ( 'WITH' | 'WITHOUT' ) 'UNIQUE' 'KEYS' ;
formatJson : 'FORMAT' 'JSON' ;


// Lexer

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
CAST     : '::' ;
COLON    : ':' ;
QUESTION : '?' ;

String   : QUOTE1 ;
National : [NE] QUOTE1 ;
Unicode  : 'U&' QUOTE1 ;
Name : 'U&'? '"' ( ~'"' | '""' )* '"' ;

ID
  : '`' ( ~'`' | '``' )* '`'
  | ALPHA ALPHANUM*
  ;

Decimal  : DIGIT+ 'L'?;
// matches "0.e1" or ".0e1", but not ".e1"
Real     : ( DIGIT+ ( '.' DIGIT* )? | '.' DIGIT+ ) EXPO? ;

Hexadecimal : '0x' HEX+ 'L'?;
/*
// TODO: this rule collides with ID, dunno why
Blob        : 'X' ( HEX HEX )+ ;
*/
Blob        : 'X' QUOTE1 ;

// TODO validate 'Variable' rule
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

fragment QUOTE1 : '\'' ( ~'\'' | '\'\'' )* '\'' ;
fragment DIGIT : [0-9] ;
fragment HEX   : [0-9A-F] ;
fragment ALPHA : [A-Z_] ;
fragment ALPHANUM : [A-Z_0-9] ;
fragment EXPO  : 'E' [-+]? DIGIT+ ;

ERROR : . ;