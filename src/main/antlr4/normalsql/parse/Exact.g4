/** exact **/

grammar Exact;

options {
   caseInsensitive = true;
}

script : statement? ( ';' statement? )* EOF ;

statement
   : selectstmt
//   | insertstmt
//   | updatestmt
//   | deletestmt
   ;

selectstmt
   : select
   | select_with_parens
   ;

select_with_parens
   : '(' select ')'
   | '(' select_with_parens ')'
   ;

select
   : with_clause? select_clause sort_clause? (for_locking_clause select_limit? | select_limit for_locking_clause?)?
   ;

select_clause
   : selectCore
   | select_with_parens
   ;

selectCore
   : ( 'SELECT'
      ('ALL'? /* into_clause? */ items?
      | distinct_clause items
      )
//           into_clause?
           from_clause?
           where_clause?
           group_clause?
           having_clause?
           window_clause?
       | values_clause
       | 'TABLE' relation_expr
       | select_with_parens gorp
     )
     gorp*
   ;

   gorp : ( 'UNION'
   | 'INTERSECT'
   | 'EXCEPT' ) ( 'ALL'
   | 'DISTINCT' )? (selectCore | select_with_parens) ;

sort_clause
   : 'ORDER' 'BY' sortby (',' sortby)*
   ;

sortby
   : term ('USING' qual_all_op | asc_desc) nulls_order?
   ;

select_limit
   : limit_clause offset_clause?
   | offset_clause limit_clause?
   ;

limit_clause
   : 'LIMIT' (term | 'ALL') (',' term)?
   | 'FETCH' ( 'FIRST' | 'NEXT' ) (select_fetch_first_value row_or_rows withTies | row_or_rows withTies )
   ;


offset_clause
   : 'OFFSET' (term | select_fetch_first_value row_or_rows)
   ;

select_fetch_first_value
   : value
   | signedInt
   ;



for_locking_clause
   : for_locking_item+
   | 'FOR' 'READ' 'ONLY'
   ;

for_locking_item
   : 'FOR' (('NO' 'KEY')? 'UPDATE' | 'KEY'? 'SHARE') ('OF' qualified_name_list)? ( 'NOWAIT' | 'SKIP' 'LOCKED' )?
   ;


with_clause
   : 'WITH' 'RECURSIVE'? cte (',' cte)*
   ;

cte
   : name names0? 'AS' materialized? '(' statement ')'
   ;

materialized
   : 'NOT'? 'MATERIALIZED'
   ;

//into_clause
//   : 'INTO' ('STRICT'? opttempTableName | into_target)
//   ;

   distinct_clause
   : 'DISTINCT' ('ON' '(' terms ')')?
   ;

from_clause
   : 'FROM' table_refs
   ;

where_clause
   : 'WHERE' term
   ;

group_clause
   : 'GROUP' 'BY' group_by_list
   ;

group_by_list
   : group_by_item (',' group_by_item)*
   ;

group_by_item
   : term
   | empty_grouping_set
//   | cube_clause
//   | rollup_clause
//   | grouping_sets_clause
   ;

having_clause
   : 'HAVING' term
   ;

empty_grouping_set
   : '(' ')'
   ;

window_clause
   : 'WINDOW' window_definition (',' window_definition)*
   ;

window_definition
   : colid 'AS' window_specification
   ;

window_specification
   : '(' colid? partition_clause? sort_clause? frame_clause ')'
   ;

partition_clause
   : 'PARTITION' 'BY' terms
   ;

frame_clause
   : ( 'RANGE' | 'ROWS' | 'GROUPS' ) frame_extent frameExclude?
   ;

frame_extent
   : frame_bound
   | 'BETWEEN' frame_bound 'AND' frame_bound
   ;

frame_bound
   : 'UNBOUNDED' ('PRECEDING' | 'FOLLOWING')
   | 'CURRENT' 'ROW'
   | term ('PRECEDING' | 'FOLLOWING')
   ;

frameExclude
   : 'EXCLUDE' ('CURRENT' 'ROW' | 'GROUP' | 'TIES' | 'NO' 'OTHERS')
   ;

values_clause
   : 'VALUES' '(' terms ')' (',' '(' terms ')')*
   ;

relation_expr
   : qualified_name '*'?
   | 'ONLY' (qualified_name | '(' qualified_name ')')
   ;

table_refs
   : table_ref (',' table_ref)*
   ;

table_ref
   : ( relation_expr alias_clause? // tablesample_clause?
      | select_with_parens alias_clause?
//      | func_table func_alias_clause?
//      | xmltable alias_clause?
      | 'LATERAL' ( select_with_parens alias_clause?
//                  | xmltable alias_clause?
//                  | func_table func_alias_clause?
                  )
      | '(' table_ref joiner? ')' alias_clause?
     )
        joiner*
   ;

joiner
    : 'CROSS' 'JOIN' table_ref
    | 'NATURAL' join_type? 'JOIN' table_ref
    | join_type? 'JOIN' table_ref ('USING' '(' names ')' | 'ON' term)
    ;

join_type
   : ('FULL' | 'LEFT' | 'RIGHT' | 'INNER') 'OUTER'?
   ;

alias_clause
   : 'AS'? colid ('(' names ')')?
   ;

//func_alias_clause
//   : alias_clause
//   | ('AS' colid? | colid) '(' tablefuncelementlist ')'
//   ;

items
   : item (',' item)*
   ;

   item
   : term ('AS'? collabel | identifier )?
   | '*'
   ;

terms
   : term (',' term)*
   ;

term
   : a_expr_lessless qual_op?
   ;

a_expr_lessless
   : a_expr_or (('<<' | '>>') a_expr_or)*
   ;
   a_expr_or
   : a_expr_and ('OR' a_expr_and)*
   ;

a_expr_and
   : a_expr_in ('AND' a_expr_in)*
   ;

a_expr_in
   : a_expr_unary_not ('NOT'? 'IN' (select_with_parens | '(' terms ')'))?
   ;

a_expr_unary_not
   : ('NOT'|'~')? a_expr_isnull
   ;

a_expr_isnull
   : a_expr_is_not ('ISNULL' | 'NOTNULL')?
   ;

a_expr_is_not
   : a_expr_compare
     ('IS' 'NOT'?
       ( 'NULL'
       | 'TRUE'
       | 'FALSE' | 'UNKNOWN' | 'DISTINCT' 'FROM' term
       | 'OF' '(' types ')'
       | 'DOCUMENT'
//       | unicode_normal_form? 'NORMALIZED'
       )
     )?
   ;

a_expr_compare
   : a_expr_like
     ( ('<' | '>' | '=' | '<=' | '>=' | '<>') a_expr_like
     | subquery_Op ( 'ANY' | 'SOME' | 'ALL' ) ( select_with_parens | '(' term ')' )
     )?
   ;

subquery_Op
   : all_op
   | 'OPERATOR' '(' any_operator ')'
   | 'LIKE'
   | 'NOT' 'LIKE'
   | 'ILIKE'
   | 'NOT' 'ILIKE'
   ;

a_expr_like
   : a_expr_qual_op ('NOT'? ('LIKE' | 'ILIKE' | 'SIMILAR' 'TO' | 'BETWEEN' 'SYMMETRIC'?) a_expr_qual_op ('ESCAPE' term)?)?
   ;

a_expr_qual_op
   : a_expr_unary_qualop (qual_op a_expr_unary_qualop)*
   ;

a_expr_unary_qualop
   : qual_op? a_expr_add
   ;

a_expr_add
   : a_expr_mul (('-' | '+') a_expr_mul)*
   ;

a_expr_mul
   : a_expr_caret (('*' | '/' | '%') a_expr_caret)*
   ;


a_expr_caret
   : a_expr_unary_sign ('^' term)?
   ;

a_expr_unary_sign
   : ('-' | '+')? a_expr_at_time_zone


   ;

   a_expr_at_time_zone
   : a_expr_collate ('AT' 'TIME' 'ZONE' term)?
   ;


a_expr_collate
   : a_expr_typecast ('COLLATE' any_name)?
   ;

a_expr_typecast
   : value ('::' type)*
   ;

subterm
   : value
   | subterm '::' type
   //right	unary '+', unary '-'
   | ('+' | '-' ) subterm
   //^	left	exponentiation
   | subterm '^' subterm
   //* / %	left	multiplication, division, modulo
   | subterm ('*' | '/' | '%') subterm
   //+ -	left	addition, subtraction
   | subterm ('+' | '-') subterm
   //(any other operator)	left	all other native and user-defined operators
   | subterm qual_op subterm
   //< > = <= >= <>	 	comparison operators
   | subterm ('<' | '>' | '=' | '<=' | '>=' | '<>' ) subterm
   | qual_op subterm
   | subterm qual_op
   //S ISNULL NOTNULL	 	IS TRUE, IS FALSE, IS NULL, IS 'DISTINCT' FROM, etc
   | subterm 'IS' 'NOT'? ('DISTINCT' 'FROM' subterm | 'OF' '(' types ')' | 'DOCUMENT')
   ;

value
   : 'EXISTS' select_with_parens
   | 'ARRAY' (select_with_parens | array)
   | VARIABLE indirection_el*
   | 'GROUPING' '(' terms ')'
   | 'UNIQUE' select_with_parens
   | columnref
   | aexprconst
   | PLSQLVARIABLENAME
   | '(' a_expr_in_parens = term ')' indirection_el*
   | case_expr
   | func_expr
   | select_with_parens indirection_el*
   | explicit_row
   | implicit_row
   | row 'OVERLAPS' row
   ;

row
   : 'ROW' '(' terms? ')'
   | '(' terms ',' term ')'
   ;

explicit_row
   : 'ROW' '(' terms? ')'
   ;

implicit_row
   : '(' terms ',' term ')'
   ;

case_expr
   : 'CASE' term? when_clause+ 'ELSE' term 'END'
   ;

when_clause
   : 'WHEN' term 'THEN' term
   ;

func_application
   : func_name '('
     ( func_arg_list (',' 'VARIADIC' func_arg_expr)? sort_clause?
     | 'VARIADIC' func_arg_expr sort_clause
     | ('ALL' | 'DISTINCT') func_arg_list sort_clause
     | '*'
     )?
     ')'
   ;

func_expr
   : func_application within_group_clause? filter_clause? over_clause?
   | func_expr_common_subexpr
   ;

within_group_clause
   : 'WITHIN' 'GROUP' '(' sort_clause ')'
   ;

filter_clause
   : 'FILTER' '(' 'WHERE' term ')'
   ;

over_clause
   : 'OVER' (window_specification | colid)
   ;


//func_expr_windowless
//   : func_application
//   | func_expr_common_subexpr
//   ;

func_arg_list
   : func_arg_expr (',' func_arg_expr)*
   ;

func_arg_expr
   : term
   | type_function_name (':=' | '=>' ) term
   ;


func_expr_common_subexpr
   : 'COLLATION' 'FOR' '(' term ')'
   | 'CURRENT_DATE'
   | 'CURRENT_TIME' ('(' DECIMAL ')')?
   | 'CURRENT_TIMESTAMP' ('(' DECIMAL ')')?
   | 'LOCALTIME' ('(' DECIMAL ')')?
   | 'LOCALTIMESTAMP' ('(' DECIMAL ')')?
   | 'CURRENT_ROLE'
   | 'CURRENT_USER'
   | 'SESSION_USER'
   | 'USER'
   | 'CURRENT_CATALOG'
   | 'CURRENT_SCHEMA'
   | 'CAST' '(' term 'AS' type ')'
   | 'EXTRACT' '(' ( extract_arg 'FROM' term )? ')'
//   | 'NORMALIZE' '(' a_expr (',' unicode_normal_form)? ')'
   | 'OVERLAY' '(' term 'PLACING' term 'FROM' term ('FOR' term)? ')'
   | 'POSITION' '(' ( subterm 'IN' subterm )? ')'
   | 'SUBSTRING' '(' substr_list ')'
   | 'TREAT' '(' term 'AS' type ')'
   | 'TRIM' '(' ('BOTH' | 'LEADING' | 'TRAILING')? trim_list ')'
   | 'NULLIF' '(' term ',' term ')'
   | 'COALESCE' '(' terms ')'
   | 'GREATEST' '(' terms ')'
   | 'LEAST' '(' terms ')'
//   | 'XMLCONCAT' '(' expr_list ')'
//   | 'XMLELEMENT' '(' 'NAME' collabel (',' (xml_attributes | expr_list))? ')'
//   | 'XMLEXISTS' '(' c_expr xmlexists_argument ')'
//   | 'XMLFOREST' '(' xml_attribute_list ')'
//   | 'XMLPARSE' '(' document_or_content a_expr xml_whitespace_option ')'
//   | 'XMLPI' '(' 'NAME' collabel (',' a_expr)? ')'
//   | 'XMLROOT' '(' 'XML' a_expr ',' xml_root_version xml_root_standalone? ')'
//   | 'XMLSERIALIZE' '(' document_or_content a_expr 'AS' simpletypename ')'
   ;

extract_arg
   : identifier
   | 'YEAR'
   | 'MONTH'
   | 'DAY'
   | 'HOUR'
   | 'MINUTE'
   | 'SECOND'
   | sconst
   ;

substr_list
   : term 'FROM' term 'FOR' term
   | term 'FOR' term 'FROM' term
   | term 'FROM' term
   | term 'FOR' term
   | term 'SIMILAR' term 'ESCAPE' term
   | terms
   ;

trim_list
   : term 'FROM' terms
   | 'FROM' terms
   | terms
   ;


indirection_el
   : '.' (collabel | '*')
   | '[' (term | term? ':' term? ) ']'
   ;

columnref
//   : colid indirection?
   : colid indirection_el*
   ;

aexprconst
   : DECIMAL
   | FLOAT
   | sconst
   | BITS
   | BYTES
   | func_name (sconst | '(' func_arg_list sort_clause? ')' sconst)
   | consttypename sconst
   | 'INTERVAL' (sconst interval? | '(' DECIMAL ')' sconst)
   | 'TRUE'
   | 'FALSE'
   | 'NULL'
   ;

interval
   : 'YEAR'
   | 'MONTH'
   | 'DAY'
   | 'HOUR'
   | 'MINUTE'
   | interval_second
   | 'YEAR' 'TO' 'MONTH'
   | 'DAY' 'TO' ('HOUR' | 'MINUTE' | interval_second)
   | 'HOUR' 'TO' ('MINUTE' | interval_second)
   | 'MINUTE' 'TO' interval_second
   ;

interval_second
   : 'SECOND' ('(' DECIMAL ')')?
   ;

sconst
   : anysconst uescape?
   ;

anysconst
   : StringConstant
   | UnicodeEscapeStringConstant
//   | BeginDollarStringConstant DollarText* EndDollarStringConstant
//   | EscapeStringConstant
   ;

array
   : '[' (terms | arrays)? ']'
   ;

arrays
   : array (',' array)*
   ;



qual_op
   : Operator
   | 'OPERATOR' '(' any_operator ')'
   ;

qual_all_op
   : all_op
   | 'OPERATOR' '(' any_operator ')'
   ;

any_operator
   : (colid '.')* all_op
   ;


withTies : ('ONLY' | 'WITH' 'TIES') ;

row_or_rows : 'ROW' | 'ROWS' ;

nulls_order : 'NULLS' ('FIRST' | 'LAST') ;

asc_desc : 'ASC' | 'DESC' ;

any_name
   : colid attrs?
   ;

attrs
   :'.' collabel+
   ;

types
   : type (',' type)*
   ;

type
   : 'SETOF'? simpletypename ( ('[' DECIMAL? ']')* | 'ARRAY' ('[' DECIMAL ']')?)
   | qualified_name '%' ('ROWTYPE' | 'TYPE')
   ;

simpletypename
   : generictype
   | numeric
   | bit
   | character
   | constdatetime
   | 'INTERVAL' (interval? | '(' DECIMAL ')')
   ;

character
   : character_c ('(' DECIMAL ')')?
   ;

character_c
   : ('CHARACTER' | 'CHAR' | 'NCHAR') 'VARYING'? // TODO does 'VARYING' get a precision param too?
   | 'VARCHAR'
   | 'NATIONAL' ('CHARACTER' | 'CHAR') 'VARYING'?
   ;

constdatetime
   : ('TIMESTAMP' | 'TIME') ('(' DECIMAL ')')? (( 'WITH' | 'WITHOUT' ) 'TIME' 'ZONE')?
   ;



numeric
   : 'INT'
   | 'INTEGER'
   | 'SMALLINT'
   | 'BIGINT'
//   | 'REAL'
   | 'FLOAT' ('(' DECIMAL ')')?
   | 'DOUBLE' 'PRECISION'
   | 'DECIMAL' type_modifiers?
   | 'DEC' type_modifiers?
   | 'NUMERIC' type_modifiers? // TODO omitted from unreserved rules?
   | 'BOOLEAN'
   ;

bit
   : 'BIT' 'VARYING'? ('(' terms ')')? // TODO omitted?
   ;

generictype
   : type_function_name attrs? type_modifiers?
   ;

type_modifiers
   : '(' terms ')'
   ;


consttypename
   : numeric
   | bit
   | character
   | constdatetime
   ;


all_op : Operator | mathop ;

mathop
   : '+'
   | '-'
   | '*'
   | '/'
   | '%'
   | '^'
   | '<'
   | '>'
   | '='
   | '<='
   | '>='
   | '<>'
   ;

qualified_name_list
   : qualified_name (',' qualified_name)*
   ;

qualified_name
//   : colid indirection?
   : colid indirection_el*
   ;

names0 : '(' names ')' ;

names : name (',' name)* ;

name
   : colid
   ;

func_name
   : type_function_name
   | colid indirection_el+
   ;

uescape
   : 'UESCAPE' anysconst
   ;

signedInt
   : ( '+' | '-' )? DECIMAL
   ;

colid
   : identifier
   | col_name_keyword
   | unreserved_keyword
//   | plsql_unreserved_keyword
   ;

collabel
   : identifier
   | col_name_keyword
   | unreserved_keyword
//   | plsql_unreserved_keyword
   | type_func_name_keyword
   | reserved_keyword
   ;

type_function_name
   : identifier
   | unreserved_keyword
//   | plsql_unreserved_keyword
   | type_func_name_keyword
   ;

//nonreservedword
//   : identifier
//   | col_name_keyword
//   | unreserved_keyword
//   | type_func_name_keyword
//   ;

identifier
   : Identifier uescape?
   | QuotedIdentifier
   | UnicodeQuotedIdentifier
   | PLSQLVARIABLENAME
   | PLSQLIDENTIFIER
//   | plsql_unreserved_keyword
   ;

   unreserved_keyword
    : 'xyzzy'
//   : ABORT_P
//   | ABSOLUTE_P
//   | ACCESS
//   | ACTION
//   | ADD_P
//   | ADMIN
//   | AFTER
//   | AGGREGATE
//   | ALSO
//   | ALTER
//   | ALWAYS
//   | ASSERTION
//   | ASSIGNMENT
   | 'AT'
//   | ATTACH
//   | ATTRIBUTE
//   | BACKWARD
//   | BEFORE
   | 'BEGIN'
   | 'BY'
//   | CACHE
//   | CALL
//   | CALLED
//   | CASCADE
//   | CASCADED
//   | CATALOG
//   | CHAIN
//   | CHARACTERISTICS
//   | CHECKPOINT
//   | CLASS
//   | CLOSE
//   | CLUSTER
//   | COLUMNS
//   | COMMENT
//   | COMMENTS
//   | COMMIT
//   | COMMITTED
//   | CONFIGURATION
//   | CONFLICT
//   | CONNECTION
//   | CONSTRAINTS
//   | CONTENT_P
//   | CONTINUE_P
//   | CONVERSION_P
//   | COPY
//   | COST
//   | CSV
//   | CUBE
   | 'CURRENT'
//   | CURSOR
//   | CYCLE
//   | DATA_P
//   | DATABASE
   | 'DAY'
//   | DEALLOCATE
//   | DECLARE
//   | DEFAULTS
//   | DEFERRED
//   | DEFINER
//   | DELETE_P
//   | DELIMITER
//   | DELIMITERS
//   | DEPENDS
//   | DETACH
//   | DICTIONARY
//   | DISABLE_P
//   | DISCARD
   | 'DOCUMENT'
//   | DOMAIN_P
   | 'DOUBLE'
//   | DROP
//   | EACH
//   | ENABLE_P
//   | ENCODING
//   | ENCRYPTED
//   | ENUM_P
   | 'ESCAPE'
//   | EVENT
   | 'EXCLUDE'
//   | EXCLUDING
//   | EXCLUSIVE
//   | EXECUTE
//   | EXPLAIN
//   | EXPRESSION
//   | EXTENSION
//   | EXTERNAL
//   | FAMILY
   | 'FILTER'
   | 'FIRST'
   | 'FOLLOWING'
//   | FORCE
//   | FORWARD
//   | FUNCTION
//   | FUNCTIONS
//   | GENERATED
//   | GLOBAL
//   | GRANTED
   | 'GROUPS'
//   | HANDLER
//   | HEADER_P
//   | HOLD
   | 'HOUR'
//   | IDENTITY_P
//   | IF_P
//   | IMMEDIATE
//   | IMMUTABLE
//   | IMPLICIT_P
//   | IMPORT_P
//   | INCLUDE
//   | INCLUDING
//   | INCREMENT
//   | INDEX
//   | INDEXES
//   | INHERIT
//   | INHERITS
//   | INLINE_P
//   | INPUT_P
//   | INSENSITIVE
//   | INSERT
//   | INSTEAD
//   | INVOKER
//   | ISOLATION
   | 'KEY'
//   | LABEL
//   | LANGUAGE
//   | LARGE_P
   | 'LAST'
//   | LEAKPROOF
//   | LEVEL
//   | LISTEN
//   | LOAD
//   | LOCAL
//   | LOCATION
//   | LOCK_P
   | 'LOCKED'
//   | LOGGED
//   | MAPPING
//   | MATCH
   | 'MATERIALIZED'
//   | MAXVALUE
//   | METHOD
   | 'MINUTE'
//   | MINVALUE
//   | MODE
   | 'MONTH'
//   | MOVE
   | 'NAME'
//   | NAMES
//   | NEW
   | 'NEXT'
//   | NFC
//   | NFD
//   | NFKC
//   | NFKD
   | 'NO'
   | 'NORMALIZED'
//   | NOTHING
//   | NOTIFY
   | 'NOWAIT'
   | 'NULLS'
//   | OBJECT_P
   | 'OF'
//   | OFF
//   | OIDS
//   | OLD
   | 'OPERATOR'
//   | OPTION
//   | OPTIONS
//   | ORDINALITY
   | 'OTHERS'
   | 'OVER'
//   | OVERRIDING
//   | OWNED
//   | OWNER
//   | PARALLEL
//   | PARSER
//   | PARTIAL
   | 'PARTITION'
//   | PASSING
//   | PASSWORD
//   | PLANS
//   | POLICY
   | 'PRECEDING'
//   | PREPARE
//   | PREPARED
//   | PRESERVE
//   | PRIOR
//   | PRIVILEGES
//   | PROCEDURAL
//   | PROCEDURE
//   | PROCEDURES
//   | PROGRAM
//   | PUBLICATION
//   | QUOTE
   | 'RANGE'
   | 'READ'
//   | REASSIGN
//   | RECHECK
   | 'RECURSIVE'
//   | REF
//   | REFERENCING
//   | REFRESH
//   | REINDEX
//   | RELATIVE_P
//   | RELEASE
//   | RENAME
//   | REPEATABLE
//   | REPLACE
//   | REPLICA
//   | RESET
//   | RESTART
//   | RESTRICT
//   | RETURNS
//   | REVOKE
//   | ROLE
//   | ROLLBACK
//   | ROLLUP
//   | ROUTINE
//   | ROUTINES
   | 'ROWS'
//   | RULE
//   | SAVEPOINT
//   | SCHEMA
//   | SCHEMAS
//   | SCROLL
//   | SEARCH
   | 'SECOND'
//   | SECURITY
//   | SEQUENCE
//   | SEQUENCES
//   | SERIALIZABLE
//   | SERVER
//   | SESSION
//   | SET
//   | SETS
   | 'SHARE'
//   | SHOW
//   | SIMPLE
   | 'SKIP'
//   | SNAPSHOT
//   | SQL_P
//   | STABLE
//   | STANDALONE_P
//   | START
//   | STATEMENT
//   | STATISTICS
//   | STDIN
//   | STDOUT
//   | STORAGE
//   | STORED
//   | STRICT_P
//   | STRIP_P
//   | SUBSCRIPTION
//   | SUPPORT
//   | SYSID
//   | SYSTEM_P
//   | TABLES
//   | TABLESPACE
//   | TEMP
//   | TEMPLATE
//   | TEMPORARY
//   | TEXT_P
   | 'TIES'
//   | TRANSACTION
//   | TRANSFORM
//   | TRIGGER
//   | TRUNCATE
//   | TRUSTED
   | 'TYPE'
//   | TYPES_P
   | 'UESCAPE'
   | 'UNBOUNDED'
//   | UNCOMMITTED
//   | UNENCRYPTED
   | 'UNKNOWN'
//   | UNLISTEN
//   | UNLOGGED
//   | UNTIL
   | 'UPDATE'
//   | VACUUM
//   | VALID
//   | VALIDATE
//   | VALIDATOR
   | 'VALUE'
   | 'VARYING'
   | 'VERSION'
//   | VIEW
//   | VIEWS
//   | VOLATILE
//   | WHITESPACE_P
   | 'WITHIN'
   | 'WITHOUT'
//   | WORK
//   | WRAPPER
//   | WRITE
   | 'XML'
   | 'YEAR'
//   | YES_P
//   | ZONE
   ;

col_name_keyword
   : 'BETWEEN'
   | 'BIGINT'
   | bit
   | 'BOOLEAN'
   | 'CHAR'
   | character
   | 'COALESCE'
   | 'DEC'
   | 'DECIMAL'
   | 'EXISTS'
   | 'EXTRACT'
   | 'FLOAT'
   | 'GREATEST'
   | 'GROUPING'
//   | INOUT
   | 'INT'
   | 'INTEGER'
   | 'INTERVAL'
   | 'LEAST'
   | 'NATIONAL'
   | 'NCHAR'
//   | NONE
   | 'NORMALIZE'
   | 'NULLIF'
   | numeric
//   | OUT_P
   | 'OVERLAY'
   | 'POSITION'
   | 'PRECISION'
//   | 'REAL'
   | 'ROW'
   | 'SETOF'
   | 'SMALLINT'
   | 'SUBSTRING'
   | 'TIME'
   | 'TIMESTAMP'
   | 'TREAT'
   | 'TRIM'
   | 'VALUES'
   | 'VARCHAR'
   | 'XMLATTRIBUTES'
   | 'XMLCONCAT'
   | 'XMLELEMENT'
   | 'XMLEXISTS'
   | 'XMLFOREST'
   | 'XMLNAMESPACES'
   | 'XMLPARSE'
   | 'XMLPI'
   | 'XMLROOT'
   | 'XMLSERIALIZE'
   | 'XMLTABLE'
   ;

type_func_name_keyword
 : 'spaz'
//   : AUTHORIZATION
//   | BINARY
   | 'COLLATION'
//   | CONCURRENTLY
   | 'CROSS'
//   | CURRENT_SCHEMA
//   | FREEZE
   | 'FULL'
   | 'ILIKE'
   | 'INNER'
   | 'IS'
   | 'ISNULL'
   | 'JOIN'
   | 'LEFT'
   | 'LIKE'
   | 'NATURAL'
   | 'NOTNULL'
   | 'OUTER'
   | 'OVERLAPS'
   | 'RIGHT'
   | 'SIMILAR'
//   | TABLESAMPLE
//   | VERBOSE
   ;

reserved_keyword
   : 'ALL'
//   | ANALYSE
//   | ANALYZE
   | 'AND'
   | 'ANY'
   | 'ARRAY'
   | 'AS'
   | 'ASC'
//   | ASYMMETRIC
   | 'BOTH'
   | 'CASE'
   | 'CAST'
//   | CHECK
   | 'COLLATE'
//   | COLUMN
//   | CONSTRAINT
//   | CREATE
//   | CURRENT_CATALOG
   | 'CURRENT_DATE'
   | 'CURRENT_ROLE'
   | 'CURRENT_TIME'
   | 'CURRENT_TIMESTAMP'
   | 'CURRENT_USER'
   //                 | DEFAULT
//   | DEFERRABLE
   | 'DESC'
   | 'DISTINCT'
//   | DO
   | 'ELSE'
   | 'END'
//   | EXCEPT
   | 'FALSE'
   | 'FETCH'
   | 'FOR'
//   | FOREIGN
   | 'FROM'
//   | GRANT
   | 'GROUP'
   | 'HAVING'
   | 'IN'
//   | INITIALLY
//   | INTERSECT
   //                 | INTO
   | 'LATERAL'
   | 'LEADING'
   | 'LIMIT'
   | 'LOCALTIME'
   | 'LOCALTIMESTAMP'
   | 'NOT'
   | 'NULL'
   | 'OFFSET'
   | 'ON'
   | 'ONLY'
   | 'OR'
   | 'ORDER'
   | 'PLACING'
//   | PRIMARY
//   | REFERENCES
//   | RETURNING
//   | SELECT
   | 'SESSION_USER'
   | 'SOME'
   | 'SYMMETRIC'
//   | TABLE
   | 'THEN'
   | 'TO'
   | 'TRAILING'
   | 'TRUE'
//   | UNION
   | 'UNIQUE'
   | 'CURRENT_ROLE'
   | 'USING'
   | 'VARIADIC'
   | 'WHEN'
   | 'WHERE'
   | 'WINDOW'
   | 'WITH'
   ;

Identifier
   : IdentifierStartChar IdentifierChar*
   ;

fragment IdentifierStartChar options { caseInsensitive=false; }
   // these are the valid identifier start characters below 0x7F
   : [a-zA-Z_]
   // these are the valid characters from 0x80 to 0xFF
   | [\u00AA\u00B5\u00BA\u00C0-\u00D6\u00D8-\u00F6\u00F8-\u00FF]
   // these are the letters above 0xFF which only need a single UTF-16 code unit
//   | [\u0100-\uD7FF\uE000-\uFFFF] {charIsLetter()}?
   // letters which require multiple UTF-16 code units
//   | [\uD800-\uDBFF] [\uDC00-\uDFFF] { CheckIfUtf32Letter() }?
    ;

QuotedIdentifier
   : '"' ( ~'"' | '""' )* '"'
   ;

UnicodeQuotedIdentifier
   : 'U' '&' QuotedIdentifier
   ;

VARIABLE
   : '$' ([0-9])+
   ;

PLSQLVARIABLENAME
   : ':' [A-Z_] [A-Z_0-9$]*
   ;

StringConstant
    : '\'' ( ~'\'' | '\'\'' )* '\'' ;

UnicodeEscapeStringConstant
    : 'U&\'' ( ~'\'' | '\'\'' )* '\'' ;

    BITS
    : '0b' [01]+
    | 'B' '\'' [01]+ '\''
    ;

    BYTES
    : '0x' HEX+
    | 'X' '\'' ( HEX | ' ' | '\'\'' )* '\''
   ;

PLSQLIDENTIFIER
   : ':"' ('\\' . | '""' | ~ ('"' | '\\'))* '"'
   ;

FLOAT
   : Digits '.' Digits? /*? replaced with + to solve problem with DOT_DOT .. but this surely must be rewriten */

   ('E' [+-]? Digits)?
   | '.' Digits ('E' [+-]? Digits)?
   | Digits 'E' [+-]? Digits
   ;

DECIMAL
   : Digits
   ;

Operator
   : [*<>=~!@%^&|`?#]+
   ;

fragment HEX
    : [A-F0-9] ;

fragment IdentifierChar
   : StrictIdentifierChar
   | '$'
   ;

fragment StrictIdentifierChar
   : IdentifierStartChar
   | [0-9]
   ;

fragment Digits
   : [0-9]+
   ;

COMMENT
//    : '--' .*? ( '\n' | EOF ) -> channel( HIDDEN ) ;
    // TODO is this better? cuz doesn't consume '\n' ?
    : '--' ~ [\r\n]* -> channel( HIDDEN ) ;

BLOCK_COMMENT
    : '/*' ( BLOCK_COMMENT | . )*? '*/' -> channel( HIDDEN ) ;

// \u000B line (vertical) tab
// \u000C form feed
WHITESPACE : [ \b\t\r\n\u000B\u000C] -> channel ( HIDDEN ) ;