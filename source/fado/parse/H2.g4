/*
 GenericSQL.g4, for ANTLR 4.x

 Copyright 2022, 2014, 2011, 2010 Jason Osgood

 Generic SQL DML grammar.

*/

grammar H2;

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

query         : rows (( 'UNION' 'ALL'? | 'EXCEPT' | 'INTERSECT' | 'MINUS' ) allDistinct? rows )* ;

rows          : select
              | 'TABLE' tableRef // orderBy? offset? fetch?
              | values // orderBy? offset? fetch?
              | LP query RP
              ;

values        : 'VALUES' terms ;

select        : 'SELECT' distinct? top? ( item ( COMMA item )* )? into? ( 'FROM' from )?
                where? groupBy? having? windows? qualify? orderBy? offset? fetch? limit? forUpdate?
              ;

    distinct      : 'DISTINCT' ( 'ON' LP terms RP )? | 'ALL' | 'UNIQUE' ;

    item          : (( tableRef DOT )? STAR ) ( 'EXCEPT' LP refs RP )?
                  | term alias?
                  ;

        alias         : 'AS'? name ;

    top           : 'TOP' ( Decimal | Real | LP term RP ) 'PERCENT'? ( 'WITH' 'TIES' )? ;

    into          : 'INTO' refs ;

    // TODO try 'term' style self-recursion
    from          : ( source | LP from RP ) ( joinType from ( 'ON' term | 'USING' names )? )* ;

        source          : ( query
                        | values
                        | function
                        | unnest
                        | ( 'TABLE' | 'TABLE_DISTINCT' ) LP columnSpec ( COMMA columnSpec )* RP
                        | ( 'NEW' | 'OLD' | 'FINAL' ) 'TABLE' LP ( delete | insert | merge | update ) RP
//                        | 'JSON_TABLE' // TODO
//                        | 'XMLTABLE' // TODO
                        | LP source RP
                        | tableRef )
                        ( alias names? )? useIndex? // TODO not every alt allows 'useIndex'
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

term          : LP term? RP # TermNested
              | term 'AND' term # TermAND
              | term 'OR' term # TermOR
              | term predicate # TermPredicate // TODO does this alt belong in 'subterm'?
              | 'NOT' term # TermNOT
              | ( 'ALL' | 'ANY' | 'SOME' | 'EXISTS' | 'UNIQUE' ) LP query RP # TermQuantified // TODO where to put 'quantified'?
              | 'INTERSECTS' LP subterm COMMA subterm RP  # TermIntersects
              | subterm #TermSubterm
              ;

subterm       : subterm CONCAT subterm # SubtermConcat
              | subterm CARET subterm  # SubtermCaret
              | subterm ( STAR | DIVIDE | MODULO ) subterm  # SubtermMultiplication
              | subterm ( PLUS | MINUS ) subterm  # SubtermAddition
              | subterm ( LSHIFT | RSHIFT | AMP | PIPE ) subterm # SubtermBitwise
              | LP term RP # SubtermNestedTerm
              | ( PLUS | MINUS ) subterm # SubtermUnary
              | LP term RP DOT name # SubtermFieldReference
              | query # SubtermQuery
              | 'CASE' term ( 'WHEN' ( terms | predicate ) 'THEN' term )+ ( 'ELSE' term )? 'END' # SubtermCaseSimple
              | 'CASE' ( 'WHEN' term 'THEN' term )+ ( 'ELSE' term )? 'END' # SubtermCaseSearch
              | subterm TYPECAST id # SubtermTypeCast
              | array # SubtermArray
              | 'ROW'? LP terms RP ( DOT name )? # SubtermRow
              | ( 'CAST' | 'TRY_CAST' ) LP term 'AS' type RP # SubtermCast
              | subterm 'AT' ( 'LOCAL' | timeZone ( interval | string ) )? # SubtermTime
              | ( 'NEXT' | 'CURRENT' ) 'VALUE' 'FOR' ref # SubtermSequence
              | function # SubtermFunction
              | value # SubtermValue
              | ref # SubtermRef
//              | term 'COLLATE' id # TermCollate TODO
//              | sequenceValueExpression TODO
//              | arrayElementReference TODO
              ;

predicate     : ( LT | LTE | GT | GTE | EQ | NEQ | OVERLAP ) term
              | ( TILDE1 | TILDE2 | TILDE3 | TILDE4 ) term
              | 'IS' 'NOT'? 'NULL'
              | 'IS' 'NOT'? bool
              | 'IS' 'NOT'? 'DISTINCT' 'FROM' term // TODO should this be 'subterm'?
            //| 'IS' 'NOT'? 'DISTINCT' 'FROM' ( 'ALL' | 'ANY' | 'SOME' | 'EXISTS' | 'UNIQUE' ) LP query RP // TODO where to put 'quantified'?
              | 'IS' 'NOT'? 'OF' LP type ( COMMA type )* RP
              | 'IS' 'NOT'? 'JSON' ( 'VALUE' | 'ARRAY' | 'OBJECT' | 'SCALAR' )? uniqueKeys?
              | 'NOT'? 'BETWEEN' ( 'ASYMMETRIC' | 'SYMMETRIC' )? subterm 'AND' subterm
              | 'NOT'? 'IN' LP ( query | terms )? RP
              | 'NOT'? ( 'LIKE' | 'ILIKE' ) subterm ( 'ESCAPE' string )?
              | 'NOT'? 'REGEXP' subterm ( 'ESCAPE' string )?
              ;

type          : 'ROW' LP name type ( COMMA name type )* RP
              | type 'ARRAY' ( LS decimal RS )?
              | id+ ( LP decimal ( COMMA decimal )? RP id* )?
              ;

array         : 'ARRAY' LS terms? RS ;

function      : 'TRIM' LP ( 'BOTH' | 'LEADING' | 'TRAILING' )? term? 'FROM'? term RP
              | 'SUBSTRING' LP term 'FROM' term ( 'FOR' term )? RP
              | 'JSON_OBJECTAGG' LP jsonKeys onNull? uniqueKeys? RP filter? over?
              | 'JSON_ARRAYAGG' LP allDistinct? term orderBy? onNull? RP filter? over?
              | 'EXTRACT' LP id 'FROM' term RP
              | 'LISTAGG' LP allDistinct? term COMMA string
                ( 'ON' 'OVERFLOW' ( 'ERROR' | 'TRUNCATE' string? withWithout 'COUNT' ))? RP withinGroup? filter? over?
              | 'STRING_AGG' LP term COMMA string orderBy RP
              | 'GROUP_CONCAT' LP 'DISTINCT'? terms orderBy? ( 'SEPARATOR' string )? RP filter?
              | id LP ( STAR | allDistinct? terms )? RP withinGroup? filter? firstLast? respectIgnore? over?
              | id LP allDistinct? ( value | name ) orderBy RP ( LS term RS )? filter? over?
              | id LP id 'FROM' id String RP
              | '{fn' id LP terms? RP '}'               //  ODBC style
              // | ID? 'FUNCTION' ID LP terms? RP // T-SQL
              // | ID DOT ID LP terms? RP // T-SQL?
              ;

    withinGroup   : 'WITHIN' 'GROUP' LP orderBy RP ;

    filter        : 'FILTER' LP 'WHERE' term RP ;

    over          : 'OVER' window ;

value         : real
              | decimal
              | string
              | Hexadecimal
              | bool
              | 'NULL'
              | date
              | dateODBC
              | time
              | interval
              | jsonObject
              | jsonArray
              | Variable
              ;

// Types
real          : ( MINUS | PLUS )? Real ;
decimal       : ( MINUS | PLUS )? Decimal ;
string        : String+ | unicode | national | blob ;
unicode       : Unicode String* ( 'UESCAPE' String )? ;
national      : National String* ;
blob          : Blob String* ;
bool          : 'TRUE' | 'FALSE' | 'UNKNOWN' ;
time          : ( 'TIME' | 'TIMESTAMP' ) ( withWithout timeZone )? String? ;
date          : 'DATE' String ;
dateODBC      : ( '{d' | '{t' | '{ts' ) String '}' ;
interval      : 'INTERVAL' term id ( 'TO' id )? ;
//interval : 'INTERVAL' expression timeSpan ;
//timeSpan      : 'EPOCH'
//              | 'YEAR' ( 'TO' 'MONTH' )?
//              | 'MONTH'
//              | 'DAY' ( 'TO' ( 'HOUR' | 'MINUTE' | 'SECOND' ) )?
//              | 'HOUR' ( 'TO' ( 'MINUTE' | 'SECOND' ) )?
//              | 'MINUTE' ( 'TO' 'SECOND' )?
//              | 'SECOND'
//              ;
jsonObject    : 'JSON_OBJECT' LP jsonKeys? onNull? uniqueKeys? formatJson? RP ;
jsonKeys      : jsonKey ( COMMA jsonKey )* ;
jsonKey       : ( string | name ) COLON term | 'KEY'? ( string | name ) 'VALUE' term ;
jsonArray     : 'JSON_ARRAY' LP ( terms | LP rows RP )? formatJson? onNull? RP ;

// DRY
allDistinct   : 'ALL' | 'DISTINCT' ;
firstLast     : 'FROM' ( 'FIRST' | 'LAST' ) ;
respectIgnore : ( 'RESPECT' | 'IGNORE' ) 'NULLS' ;
onNull        : ( 'NULL' | 'ABSENT' ) 'ON' 'NULL' ;
uniqueKeys    : withWithout 'UNIQUE' 'KEYS' ;
withWithout   : 'WITH' | 'WITHOUT' ;
formatJson    : 'FORMAT' 'JSON' ;
timeZone      : 'TIME' 'ZONE' ;
rowRows       : 'ROW' | 'ROWS' ;

refs          : ref ( COMMA ref )* ;
ref           : ((( database=name DOT )? schema=name DOT )? table=name DOT )? column=name ; // TODO: add _ROWID_ ?
tableRef      : (( database=name DOT )? schema=name DOT )? table=name ;
names         : LP name ( COMMA name )* RP ;
name          : id  | Name ( 'UESCAPE' String )?;
ids           : LP id ( COMMA id )* RP ;
id            : ID | { isKeyword( getCurrentToken() ) }? . ;

// Tokens
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
//AT       : '@' ; // absolute value
//BANG     : '!' ; // factorial

EQ       : '=' | ':=' ;
NEQ      : '<>' | '!=' ;
LT       : '<' ;
LTE      : '<=' ;
GT       : '>' ;
GTE      : '>=' ;
OVERLAP  : '&&' ;

CONCAT   : '||' ; // concatenation
AMP      : '&'  ; // bitwise AND
PIPE     : '|'  ; // bitwise XOR
LSHIFT   : '<<' ; // bitwise shift left
RSHIFT   : '>>' ; // bitwise shift right
//POUND    : '#'  ; // bitwise XOR
// TILDE     : '~' ; // bitwise NOT
// https://www.postgresql.org/docs/current/functions-matching.html
// POSIX Regular Expressions
TILDE1   : '~' ; // match regex case sensitive
TILDE2   : '~*' ; // match regex case insensitive
TILDE3   : '!~' ; // not match regex case sensitive
TILDE4   : '!~*' ; // not match regex case insensitive

DOT      : '.' ;
TYPECAST : '::' ;
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