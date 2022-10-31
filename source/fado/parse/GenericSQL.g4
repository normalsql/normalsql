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
			// Keywords are literals without a matching symbol
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
        if( !Character.isAlphabetic( text1.charAt( text1.length() - 1 ))) return false;
        if( !Character.isAlphabetic( text1.charAt( 0 ))) return false;
        String text = text1.toUpperCase();
        boolean contains = keywords().contains( text );
        return contains;
    }
}

parse   : statement* EOF ;

statement     : ( delete | insert | merge | update | query ) SEMI? ;

delete        : 'DELETE' ; // TODO

insert        : 'INSERT' into ( LP refs RP )? ( values | select ) ; // TODO

merge         : 'MERGE' ; // TODO

update        : 'UPDATE' ref 'SET' setter ( COMMA setter )* where? ;

    setter        : ref EQ value ;

query         : rows (( 'UNION' | 'EXCEPT' | 'INTERSECT' | 'MINUS' ) allDistinct? rows )* ;

rows          : LP query RP
              | values
              | select
              | 'TABLE' tableRef
              ;

values        : 'VALUES' terms ;

select        : 'SELECT' distinct? top? ( column ( COMMA column )* )? into? ( 'FROM' from )?
                where? groupBy? having? windows? qualify? orderBy? offset? fetch? limit? forUpdate?
              ;

    distinct      : 'DISTINCT' ( 'ON' LP terms RP )? | 'ALL' | 'UNIQUE' ;

    column        : (( tableRef DOT )? STAR ) ( 'EXCEPT' LP refs RP )?
                  | term alias?
                  ;

    top           : 'TOP' ( Decimal | Real | LP term RP ) 'PERCENT'? ( 'WITH' 'TIES' )? ;

    into          : 'INTO' refs ;

    // TODO try 'term' style self-recursion
    from          : ( table | LP from RP ) ( joinType from ( 'ON' term | 'USING' names )? )* ;

        table         : ( LP query RP
                        | values
                        | function
                        | unnest
                        | ( 'TABLE' | 'TABLE_DISTINCT' ) LP columnSpec ( COMMA columnSpec )* RP
                        | ( 'NEW' | 'OLD' | 'FINAL' ) 'TABLE' LP ( delete | insert | merge | update ) RP
//                        | 'JSON_TABLE' // TODO
//                        | 'XMLTABLE' // TODO
                        | LP table RP
                        | tableRef )
                        ( alias names? )? useIndex? // TODO not every 'table' allows 'useIndex'
                      ;

        joinType      : ( 'INNER' | ( 'FULL' | 'LEFT' | 'RIGHT' ) 'OUTER'? | 'CROSS' | 'NATURAL' )? 'JOIN'
                      | COMMA
                      ;

    where         : 'WHERE' term ;

    groupBy       : 'GROUP' 'BY' terms? ;

    having        : 'HAVING' terms ;

    windows       : 'WINDOW' windowAlias ( COMMA windowAlias )* ;

        windowAlias   : name 'AS' window ;

        window        : name | LP name? partitionBy? orderBy? windowFrame? RP ;

            partitionBy   : 'PARTITION' 'BY' terms ;

            windowFrame   : ( 'RANGE'| 'ROWS' | 'GROUPS' )
                            ( preceding | 'BETWEEN' following 'AND' following )
                            ( 'EXCLUDE' ( 'CURRENT' 'ROW' | 'GROUP' | 'TIES' | 'NO' 'OTHERS' )? )?
                          ;

            preceding     : ( 'UNBOUNDED' | 'CATEGORY' | term ) 'PRECEDING'
                          | 'CURRENT' 'ROW'
                          ;

            following     : ( 'UNBOUNDED' | term ) 'FOLLOWING'
                          | preceding
                          ;

    qualify       : 'QUALIFY' term ;

    orderBy       : 'ORDER' 'BY' orderByItem ( COMMA orderByItem )* ;

        //orderByItem  : term ( 'COLLATE' ID )? ( 'ASC' | 'DESC' )? ( 'NULLS' ( 'FIRST' | 'LAST' ))?
        orderByItem   : term ( 'ASC' | 'DESC' )? ( 'NULLS' ( 'FIRST' | 'LAST' ))? ;

    offset        : 'OFFSET' term rowRows? ;

    fetch         : 'FETCH' ( 'FIRST' | 'NEXT' ) ( term 'PERCENT'? )? rowRows ( 'ONLY' | ( 'WITH' 'TIES' )) ;

    limit         : 'LIMIT' term (( 'OFFSET' | COMMA ) term )? ;

    forUpdate     : 'FOR' 'UPDATE' ;

    unnest        : 'UNNEST' LP array ( COMMA array )* RP ( 'WITH' 'ORDINALITY' )? ;

    columnSpec    : ( name | ref ) id EQ ( array | term ) ; // TODO: just use 'ref'?

    useIndex      : 'USE' 'INDEX' ids ;

terms         : term ( COMMA term )* ;

term          : function # TermFunction
              | term CONCAT term # TermConcat
              | term CARET term # TermCaret
              | term ( STAR | DIVIDE | MODULO ) term # TermMultiplication
              | term ( PLUS | MINUS ) term # TermAddition
              | term ( LSHIFT | RSHIFT | AMP | PIPE ) term # TermBitwise
              | term comparison term # TermComparison
              | term matchRegex term # TermMatchRegex
              | quantified # TermQuantified
              | term predicate # TermWithPredicate
              | term isType # TermIsType
              | term 'NOT'? ( between | in | like | regexp ) # TermCompare2
              | 'INTERSECTS' LP term COMMA term RP # TermIntersects
              | 'CASE' term ( 'WHEN' ( terms | whenPred ) 'THEN' term )+ ( 'ELSE' term )? 'END' # TermCaseSwitch
              | 'CASE' ( 'WHEN' terms 'THEN' term )+ ( 'ELSE' term )? 'END' # TermCaseTests
              | ( 'CAST' | 'TRY_CAST' ) LP term 'AS' type RP # TermCast
              | term 'COLLATE' id # TermCollate
              | term function2 # TermMethod
              | ( MINUS | PLUS | 'NOT' ) term # TermUnary
              | term op=( 'AND' | 'OR' ) term # TermBoolean
              | term 'AT' ( 'LOCAL' | timeZone ( interval | string ) )? # TermTime
              | ( 'NEXT' | 'CURRENT' ) 'VALUE' 'FOR' ref # TermSequence
              | value # TermValue
              | ref # TermRef
//              | ID # TermRef
              | array # TermArray
              | LP terms RP DOT name # TermColumn
              | LP term? RP # TermNested
              | query # TermQuery
              | 'ROW'? LP terms RP # TermRow
              ;

    comparison       : LT | LTE | GT | GTE | EQ | NEQ | OVERLAP ;

    matchRegex    : TILDE1 | TILDE2 | TILDE3 | TILDE4 ;

    quantified    : ( 'ALL' | 'ANY' | 'SOME' | 'EXISTS' | 'UNIQUE' )? LP rows RP ; // TODO change 'rows' to 'union'?

    predicate     : 'IS' 'NOT'? ( 'NULL' | bool | 'DISTINCT' 'FROM' term );

    isType        : 'IS' 'OF' ids ;

    // TODO additional 'IN' rules
    //          | ( databaseName DOT )? table_name
    //          | ( databaseName DOT )? table_function_name LPAREN ( expression ( COMMA expression )* )? RPAREN
    in            : 'IN' terms ;

    like          : ( 'LIKE' | 'ILIKE' ) term ( 'ESCAPE' term )? ;

    regexp        : 'REGEXP' term ( 'ESCAPE' term )? ;

    between       : 'BETWEEN' ( 'ASYMMETRIC' | 'SYMMETRIC' )? term 'AND' term ;

    whenPred      : comparison term
                  | matchRegex term
                  | quantified
                  | predicate
                  | isType
                  | 'NOT'? ( between | in | like | regexp )
                  ;

function      : 'TRIM' LP ( 'BOTH' | 'LEADING' | 'TRAILING' )? term? 'FROM'? term RP
              | 'SUBSTRING' LP term 'FROM' term ( 'FOR' term )? RP
              | 'JSON_OBJECTAGG' LP jsonKeys onNull? uniqueKeys? RP filter? over?
              | 'JSON_ARRAYAGG' LP allDistinct? term orderBy? onNull? RP filter? over?
              | 'EXTRACT' LP id 'FROM' term RP
              | 'LISTAGG' LP allDistinct? term COMMA string
                ( 'ON' 'OVERFLOW' ( 'ERROR' | 'TRUNCATE' string? withWithout 'COUNT' ))? RP withinGroup? filter? over?
              | 'STRING_AGG' LP term COMMA string orderBy RP
              | 'GROUP_CONCAT' LP 'DISTINCT'? terms orderBy? ( 'SEPARATOR' string )? RP filter?
              | id LP ( allDistinct? terms | STAR )? RP withinGroup? filter? firstLast? respectIgnore? over?
              | id LP allDistinct? ( value | name ) orderBy RP ( LS term RS )? filter? over?
              | id LP id 'FROM' id String RP
              //  ODBC style
              | '{fn' id LP terms? RP '}'
              //         | id LPAREN ( ( 'ALL' | 'DISTINCT' )? terms | STAR )? RPAREN
              //           filter? firstLast? respectIgnore? over?
              // | ID? 'FUNCTION' ID LPAREN terms? RPAREN // T-SQL
              // | ID DOT ID LPAREN terms? RPAREN // T-SQL?
              ;

    withinGroup   : 'WITHIN' 'GROUP' LP orderBy RP ;

    filter        : 'FILTER' LP 'WHERE' term RP ;

    over          : 'OVER' window ;

function2     : CAST id ; // TODO inline this within 'term' rule?

type          : 'ROW' LP name type ( COMMA name type )* RP
              | id+ ( LP decimal ( COMMA decimal )? RP id* )?
              ;

array         : 'ARRAY' LS terms? RS ;

value         : real
              | decimal
              | string
              | Hexadecimal
              | bool
              | 'NULL'
              | date
              | time
              | interval
              | jsonObject
              | jsonArray
              | Variable
              ;

real          : ( MINUS | PLUS )? Real ;
decimal       : ( MINUS | PLUS )? Decimal ;
string        : String+ | unicode | national | blob ;
unicode       : Unicode String* ( 'UESCAPE' String )? ;
national      : National String* ;
blob          : Blob String* ;
bool          : 'TRUE' | 'FALSE' | 'UNKNOWN' ;
time          : ( 'TIME' | 'TIMESTAMP' ) ( withWithout timeZone )? String? ;
//interval : 'INTERVAL' expression timeSpan ;
interval      : 'INTERVAL' term id ( 'TO' id )? ;
date          : 'DATE' String
              // ODBC date time
              | ( '{d' | '{t' | '{ts' ) String '}'
              ;

jsonObject    : 'JSON_OBJECT' LP jsonKeys? onNull? uniqueKeys? formatJson? RP ;
jsonKeys      : jsonKey ( COMMA jsonKey )* ;
jsonKey       : ( string | name ) COLON term | 'KEY'? ( string | name ) 'VALUE' term ;
jsonArray     : 'JSON_ARRAY' LP ( terms | LP rows RP )? formatJson? onNull? RP ;

allDistinct   : 'ALL' | 'DISTINCT' ;
firstLast     : 'FROM' ( 'FIRST' | 'LAST' ) ;
respectIgnore : ( 'RESPECT' | 'IGNORE' ) 'NULLS' ;
onNull        : ( 'NULL' | 'ABSENT' ) 'ON' 'NULL' ;
uniqueKeys    : withWithout 'UNIQUE' 'KEYS' ;
withWithout   : 'WITH' | 'WITHOUT' ;
formatJson    : 'FORMAT' 'JSON' ;
timeZone      : 'TIME' 'ZONE' ;
rowRows       : 'ROW' | 'ROWS' ;
timeSpan      : 'EPOCH'
              | 'YEAR' ( 'TO' 'MONTH' )?
              | 'MONTH'
              | 'DAY' ( 'TO' ( 'HOUR' | 'MINUTE' | 'SECOND' ) )?
              | 'HOUR' ( 'TO' ( 'MINUTE' | 'SECOND' ) )?
              | 'MINUTE' ( 'TO' 'SECOND' )?
              | 'SECOND'
              ;

alias         : 'AS'? name ;
//tableAlias    : alias names? ;

refs          : ref ( COMMA ref )* ;
// TODO create separate tableRef and columnRef, add labels
//ref           : name ( DOT name )* ;
ref           : (( schemaName=name DOT )? tableName=name DOT )? columnName=name ;
tableRef      : ( schemaName=name DOT )? tableName=name ;
names         : LP name ( COMMA name )* RP ;
name          : id  | Name ( 'UESCAPE' String )?;
ids           : LP id ( COMMA id )* RP ;
id            : ID | { isKeyword( getCurrentToken() ) }? . ;

// Lexemes

LP       : '(' ;
RP       : ')' ;
LS       : '[' ;
RS       : ']' ;
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
TILDE1   : '~' ; // match regex case sensitive
TILDE2   : '~*' ; // match regex case insensitive
TILDE3   : '!~' ; // not match regex case sensitive
TILDE4   : '!~*' ; // not match regex case insensitive

LSHIFT   : '<<' ; // bitwise shift left
RSHIFT   : '>>' ; // bitwise shift right

DOT      : '.' ;
CAST     : '::' ;
COLON    : ':' ;
//QUESTION : '?' ;

String   : QUOTED ;
National : [NE] QUOTED ;
Unicode  : 'U&' QUOTED ;
// Blob        : 'X' ( HEX HEX )+ ; TODO: this rule collides with ID, dunno why
Blob     : 'X' QUOTED ;
Name     : 'U&'? '"' ( ~'"' | '""' )* '"' ;

ID       : '`' ( ~'`' | '``' )* '`'
//         | ALPHA ALPHANUM*
         | ALPHA ( ALPHA | DIGIT )*
         ;

Decimal  : DIGIT+ 'L'?;
// matches "0.e1" or ".0e1", but not ".e1"
Real     : ( DIGIT+ ( '.' DIGIT* )? | '.' DIGIT+ ) EXPO? ;
Hexadecimal : '0x' HEX+ 'L'?;

// TODO verify 'Variable' rule
Variable : [:@$] ID
         | '?' DIGIT*
         ;

Comment  : '--' ~[\r\n]* -> channel( HIDDEN ) ;
Block    : '/*' .*? '*/' -> channel( HIDDEN ) ;
Spaces   : [ \t\r\n] -> channel( HIDDEN ) ;

fragment QUOTED   : '\'' ( ~'\'' | '\'\'' )* '\'' ;
fragment DIGIT    : [0-9] ;
fragment HEX      : [0-9A-F] ;
fragment ALPHA    : [A-Z_] ;
//fragment ALPHANUM : [A-Z_0-9] ;
fragment EXPO     : 'E' [-+]? DIGIT+ ;

ERROR : . ;