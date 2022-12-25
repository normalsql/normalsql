// Copyright 2010-2022 Jason Osgood


// SPDX-License-Identifier: Apache-2.0


/*
 NormalSQL.g4, SQL DML grammar for ANTLR 4.x
*/
grammar NormalSQL;

options 
{ 
    contextSuperClass = normalsql.parse.GlobbingRuleContext; 
    caseInsensitive = true; 
}
@ parser :: header
{
import java.util.HashSet;
}
@ parser :: members
{
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

parse
   : statement? ( ';' statement? )* EOF ;

statement
   : delete
   | insert
   | merge
   | update
   | query
   ;

delete
   : 'DELETE' ; // TODO
   
insert
   : 'INSERT' into refs? ( values | select ) ; // TODO
   
merge
   : 'MERGE' ; // TODO
   
update
   : 'UPDATE' ref 'SET' setter ( COMMA setter )* where? ;

   setter
       : ref EQ value ;

query
   : rows (( 'UNION' 'ALL'? | 'EXCEPT' | 'INTERSECT' | 'MINUS' ) allDistinct? rows )* ;

rows
   : select
   | with
   | 'TABLE' tableRef // orderBy? offset? fetch?
   | values // orderBy? offset? fetch?
   | LP query RP
   ;

    with
       : 'WITH' 'RECURSIVE'? cte ( COMMA cte )* statement ;

        cte
           : id ( LP id ( COMMA id )* RP )? 'AS' LP query RP ;

values
   : 'VALUES' terms ;

select
   : 'SELECT' distinct? top? ( item ( COMMA item )* COMMA? )? into? ( 'FROM' join ) ? where?
      groupBy? having? windows? qualify? orderBy? offset? fetch? limit? forUpdate? ;

   distinct
      : 'DISTINCT' ( 'ON' LP terms RP )? | 'ALL' | 'UNIQUE' ;

   item
      : (( tableRef PERIOD )? WILDCARD )  ( 'EXCEPT' refs )?            # ItemTable
      | term alias?                                                     # ItemColumn
      ;

    alias : 'AS'? id ;

    top
       : 'TOP' ( Decimal | Real | LP term RP ) 'PERCENT'? withTies? ;

    into
       : 'INTO' tableRef ;

    join
       : join joinType? 'JOIN' join ( 'ON' term | 'USING' refs )?        # JoinTwo
       | join COMMA join                                                 # JoinOldStyle
       | source                                                          # JoinSource
       | LP join RP                                                      # JoinNested
       ;

        joinType
           : 'INNER'
           | ( 'FULL' | 'LEFT' | 'RIGHT' ) 'OUTER'?
           | 'CROSS'
           | 'NATURAL'
           ;

        source
           : ( query
             | values
             | function
             | unnest
             | ( 'TABLE' | 'TABLE_DISTINCT' ) LP columnSpec ( COMMA columnSpec )* RP
             | ( 'NEW' | 'OLD' | 'FINAL' ) 'TABLE' LP ( delete | insert | merge | update ) RP
             | 'JSON_TABLE' // TODO
             | 'XMLTABLE' // TODO
             | LP source RP
             | tableRef
             )
             ( alias refs? )? useIndex? // TODO which alts allow 'useIndex'?
           ;

    where
       : 'WHERE' term ;

    groupBy
       : 'GROUP' 'BY' terms? ;

    having
       : 'HAVING' terms ;

    windows
       : 'WINDOW' windowAlias ( COMMA windowAlias )* ;

        windowAlias
           : id 'AS' window ;

            window
               : id
               | LP id? partitionBy? orderBy? windowFrame? RP
               ;

                partitionBy
                   : 'PARTITION' 'BY' terms ;

                windowFrame
                   : ( 'RANGE' | 'ROWS' | 'GROUPS' ) ( preceding | 'BETWEEN' following 'AND' following )
                     ( 'EXCLUDE' ( 'CURRENT' 'ROW' | 'GROUP' | 'TIES' | 'NO' 'OTHERS' )? )?
                   ;

                    preceding
                       : ( 'UNBOUNDED' | 'CATEGORY' | term ) 'PRECEDING'
                       | 'CURRENT' 'ROW'
                       ;

                    following
                       : ( 'UNBOUNDED' | term ) 'FOLLOWING'
                       | preceding
                       ;

    qualify
       : 'QUALIFY' term
       ;

    orderBy
       : 'ORDER' 'BY' orderByItem ( COMMA orderByItem )* ;

        orderByItem
           : term ( ' ASC' | 'DESC' )? ( 'NULLS' firstLast )? ;

    offset
       : 'OFFSET' term rowRows? ;

    fetch
       : 'FETCH' ( 'FIRST' | 'NEXT' ) ( term 'PERCENT'? )? rowRows ( 'ONLY' | withTies ) ;

    limit
       : 'LIMIT' term (( 'OFFSET' | COMMA ) term )? ;

    forUpdate
       : 'FOR' 'UPDATE' ;

    unnest
       : 'UNNEST' LP array ( COMMA array )* RP ( 'WITH' 'ORDINALITY' )? ;

columnSpec
   : id ( dataType | domainRef ) EQ ( array | row ) ;

dataType
   : 'NULL'
   | keyword+
   ; // TODO dataTypes
   
row
   : term ; // TODO row value expression
   
useIndex
   : 'USE' 'INDEX' refs ;

terms
   : term ( COMMA term )* ;

term
   : 'NOT' term                                # TermNOT
   | term 'AND' term                           # TermAND
   | term 'OR' term                            # TermOR
   | 'EXISTS' LP query RP                      # TermEXISTS
   | 'UNIQUE' LP query RP                      # TermUNIQUE
   | 'INTERSECTS' LP subterm COMMA subterm RP  # TermIntersects
   | subterm                                   # TermSubterm
   ;

subterm
   : subterm '||' subterm                                                         # SubtermConcat
   | subterm ( '::' keyword index* )+                                                         # SubtermTypeCast
//   | ( PLUS | MINUS ) subterm                                                                       # SubtermUnary
   | SIGN subterm                                                                       # SubtermUnary
   | subterm '^' subterm                                                                       # SubtermPower
   | subterm ( '*' | '/' | '%' ) subterm                                             # SubtermProduct
//   | subterm ( PLUS | MINUS ) subterm                                                         # SubtermSum
   | subterm SIGN subterm                                                         # SubtermSum
   | subterm ( SHIFTL | SHIFTR | AMPERSAND | VERTICAL ) subterm                             # SubtermBitwise
   | subterm predicate                                                                       # SubtermPredicate
   | LP RP                                                                                     # SubtermEmpty
   | LP terms RP PERIOD id                                                                        # SubtermFieldRef
   | LP terms RP                                                                                       # SubtermNested
   | query                                                                                       # SubtermQuery
   | 'CASE' term ( 'WHEN' ( terms | predicate ) 'THEN' term )+ ( 'ELSE' term )? 'END'    # SubtermCaseSimple
   | 'CASE' ( 'WHEN' term 'THEN' term )+ ( 'ELSE' term )? 'END'                          # SubtermCaseSearch
   | array                                                                               # SubtermArray
   | ( 'CAST' | 'TRY_CAST' ) LP term 'AS' type RP                                        # SubtermCast
   | subterm 'AT' ( 'LOCAL' | timeZone ( interval | String ))?                           # SubtermTime
   | ( 'NEXT' | 'CURRENT' ) 'VALUE' 'FOR' ref                                            # SubtermSequence
   | 'ROW' LP terms? RP                                                                                       # SubtermRow
   | function                                                                                       # SubtermFunction
   | value                                                                                       # SubtermValue
   | ref                                                                                       # SubtermRef
   //              | term 'COLLATE' id # TermCollate TODO
   //              | sequenceValueExpression TODO
   //              | arrayElementReference TODO
   ;

// TODO subrule for dialect assignment operators
// TODO subrule for dialect & custom comparison operators?
predicate
   : op = ( LT | LTE | GT | GTE | EQ | NEQ | OVERLAP ) subterm # PredicateCompare
   | ( MATCH1 | MATCH2 | MATCH3 | MATCH4 ) subterm # PredicateMatch
   | 'IS' 'NOT'? 'NULL' # PredicateNull
   | 'IS' 'NOT'? truth # PredicateTruth
   | 'IS' 'NOT'? 'DISTINCT' 'FROM' subterm # PredicateDistinct
   | 'IS' 'NOT'? 'OF' LP type ( COMMA type )* RP # PredicateType
   | 'IS' 'NOT'? 'JSON' ( 'VALUE' | 'ARRAY' | 'OBJECT' | 'SCALAR' )? uniqueKeys? # PredicateJSON
   | 'NOT'? 'BETWEEN' ( 'ASYMMETRIC' | 'SYMMETRIC' )? subterm 'AND' subterm # PredicateBETWEEN
   | 'NOT'? 'IN' LP ( query | terms )? RP # PredicateIN
   | 'NOT'? ( 'LIKE' | 'ILIKE' ) subterm ( 'ESCAPE' String )? # PredicateLIKE
   | 'NOT'? 'REGEXP' subterm ( 'ESCAPE' String )? # PredicateREGEXP
   ;

type
   : 'ROW' LP id type ( COMMA id type )* RP
   | type 'ARRAY' ( LB Decimal RB )?
   | keyword+ ( LP Decimal ( COMMA Decimal )? RP keyword* )?
   ;

array
   : 'ARRAY' LB terms? RB ;

function
   : 'TRIM' LP ( 'BOTH' | 'LEADING' | 'TRAILING' )? term? 'FROM'? term RP
   | 'SUBSTRING' LP term 'FROM' term ( 'FOR' term )? RP
   | 'JSON_OBJECTAGG' LP jsonPairs onNull? uniqueKeys? RP filter? over?
   | 'JSON_ARRAYAGG' LP allDistinct? term orderBy? onNull? RP filter? over?
   | 'EXTRACT' LP keyword 'FROM' .*? RP // TODO
   | 'LISTAGG' LP allDistinct? subterm COMMA subterm
   //                ( 'ON' 'OVERFLOW' ( 'ERROR' | 'TRUNCATE' name? withWithout 'COUNT' ))?
   ( 'ON' 'OVERFLOW' 'ERROR' )? RP withinGroup? filter? over?
   | 'STRING_AGG' LP subterm COMMA subterm orderBy RP
   | 'GROUP_CONCAT' LP 'DISTINCT'? terms orderBy? ( 'SEPARATOR' subterm )? RP filter?
   //              | ( 'ALL' | 'ANY' | 'SOME' ) LP terms RP // "quantified"?
   | '{fn' function '}' //  ODBC style
   | keyword LP ( WILDCARD | allDistinct? terms )? RP withinGroup? filter? ( 'FROM' firstLast )? respectIgnore? over?
   | keyword LP allDistinct? term orderBy RP ( LB term RB )? filter? over?
   //              | keyword LP id 'FROM' id SingleQ RP
   // | ID? 'FUNCTION' ID LP terms? RP // T-SQL
   // | ID DOT ID LP terms? RP // T-SQL?
   ;

withinGroup
   : 'WITHIN' 'GROUP' LP orderBy RP ;

filter
   : 'FILTER' LP 'WHERE' term RP ;

over
   : 'OVER' window ;

value
   : Decimal
   | Real
   | Bytes
   | Blob
   | truth
   | 'NULL'
   | 'DATE' String
   | ( '{d' | '{t' | '{ts' ) String '}'
   | ( 'TIME' | 'TIMESTAMP' ) ( withWithout timeZone )? String?
   | interval
   | jsonObject
   | jsonArray
   | Parameter
   | Variable
   | String
   ;

truth
   : 'TRUE' | 'FALSE' | 'UNKNOWN' ;

interval
   : 'INTERVAL' String (keyword ( 'TO' keyword )? )?
   ;
   //interval : 'INTERVAL' expression timeSpan ;
   //timeSpan      : 'EPOCH'
   //              | 'YEAR' ( 'TO' 'MONTH' )?
   //              | 'MONTH'
   //              | 'DAY' ( 'TO' ( 'HOUR' | 'MINUTE' | 'SECOND' ) )?
   //              | 'HOUR' ( 'TO' ( 'MINUTE' | 'SECOND' ) )?
   //              | 'MINUTE' ( 'TO' 'SECOND' )?
   //              | 'SECOND'
   //              ;
   
jsonArray
   : 'JSON_ARRAY' LP ( terms | LP rows RP )? formatJson? onNull? RP ;

jsonObject
   : 'JSON_OBJECT' LP jsonPairs? onNull? uniqueKeys? formatJson? RP ;

    jsonPairs
       : jsonPair ( COMMA jsonPair )* ;

        jsonPair
           : jsonKey ':' term
           | 'KEY'? jsonKey 'VALUE' term
           ;

            jsonKey
               : 'NULL' | String | keyword ;

refs
   : LP ref ( COMMA ref )* RP ;

ref
   : ((( schema = id PERIOD )? domain = id PERIOD )? table = id PERIOD )? column = id index* ;

tableRef
   : (( schema = id PERIOD )? domain = id PERIOD )? table = id ;

domainRef
   : ( schema = id PERIOD )? domain = id PERIOD ;

index
   : LB ( term | term? ':' term? )? RB ;

string
   : String
   | UnicodeString uescape?
   | NationalString
   ;

id
   : Id
   | UnicodeId uescape?
   | Backticks
   | Dollars
   | keyword
   ;

   uescape
      : 'UESCAPE' STRING ;

keyword
   : Keyword
   | { isKeyword( getCurrentToken() ) }? .
   ;

allDistinct
   : 'ALL' | 'DISTINCT' ;

firstLast
   : 'FIRST' | 'LAST' ;

formatJson
   : 'FORMAT' 'JSON' ;

onNull
   : ( 'NULL' | 'ABSENT' ) 'ON' 'NULL' ;

respectIgnore
   : ( 'RESPECT' | 'IGNORE' ) 'NULLS' ;

rowRows
   : 'ROW' | 'ROWS' ;

timeZone
   : 'TIME' 'ZONE' ;

uniqueKeys
   : withWithout 'UNIQUE' 'KEYS' ;

withTies
   : 'WITH' 'TIES' ;

withWithout
   : 'WITH' | 'WITHOUT' ;

Keyword
   : [A-Z_#] [A-Z_#$@0-9]* ;

String
   : STRING+ ;

UnicodeString // options { caseInsensitive=false; }
   : 'U&' STRING+ ;

NationalString // options { caseInsensitive=false; }
   : [NE] STRING+ ;

fragment STRING
   : '\'' ( ~'\'' | '\'\'' )* '\'' ;

Id
   : ID ;

UnicodeId // options { caseInsensitive=false; }
   : 'U&' ID ;

fragment ID
   : '"' ( ~'"' | '""' )* '"' ;
   //ID     : '"' ( '""' | ~ [\u0000"] )* '"' ; TODO Why does this variation work? Is it better?

Dollars
  : '$$' .*? '$$' ;
   //TODO square bracket identifiers

Backticks
   : '`' ( ~'`' | '``' )* '`' ;

Blob
   : 'X' BLOB ( ' ' BLOB )* ;

    fragment BLOB
       : '\'' ( HEX HEX ' '? )* '\'' ;

Bytes
   : '0x' HEX+ ;

fragment HEX
   : [A-F0-9] ;

Decimal
   : DIGIT+ 'L'? ;

// matches "0.e1" or ".0e1", but not ".e1"
Real
   : ( DIGIT+ ( '.' DIGIT* )? | '.' DIGIT+ ) ( 'E' [-+]? DIGIT+ )? ;

Parameter
   : '?' DIGIT* ;

fragment DIGIT
   : [0-9] ;

Variable
   : '@' [A-Z_$@#0-9]* // T-SQL?
   | ':' [A-Z_] [A-Z_0-9$]* // Postgres?
   | ':' '"' ( ~'"' | '""' )* '"' // Postgres?
   ;

Comment
   : '--' ~ [\r\n]* -> channel( HIDDEN ) ;

Block
   : '/*' .*? '*/' -> channel( HIDDEN ) ;

Spaces
   : [ \t\r\n] -> channel ( HIDDEN ) ;

LP       : '(' ;
RP       : ')' ;
LB       : '[' ;
RB       : ']' ;
COMMA    : ',' ;
PERIOD   : '.' ;
//PLUS     : '+' ;
//MINUS    : '-' ;
SIGN     : [+-] ;
WILDCARD : '*' ;

// TODO add isAssign for dialects (for symantec predicate)
EQ       : '=' | ':=' ;
NEQ      : '<>' | '!=' ;
LT       : '<' ;
LTE      : '<=' ;
GT       : '>' ;
GTE      : '>=' ;
OVERLAP  : '&&' ;
AMPERSAND : '&' ;
VERTICAL : '|' ;
SHIFTL   : '<<' ; // bitwise shift left
SHIFTR   : '>>' ; // bitwise shift right
   //POUND    : '#'  ; // bitwise XOR
   // TILDE     : '~' ; // bitwise NOT
   // https://www.postgresql.org/docs/current/functions-matching.html
   // POSIX Regular Expressions

// TODO replace w/ isOperator (for symantec predicate)
MATCH1 : '~' ; // match regex case sensitive
MATCH2 : '~*' ; // match regex case insensitive
MATCH3 : '!~' ; // not match regex case sensitive
MATCH4 : '!~*' ; // not match regex case insensitive
   
ERROR : . ;