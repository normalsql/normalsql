/*
based on
https://github.com/tunnelvisionlabs/antlr4-grammar-postgresql/blob/master/src/com/tunnelvisionlabs/postgresql/PostgreSqlLexer.g4
*/

/*
 * [The "MIT license"]
 * Copyright (C) 2014 Sam Harwell, Tunnel Vision Laboratories, LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * 1. The above copyright notice and this permission notice shall be included in
 *    all copies or substantial portions of the Software.
 * 2. THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 *    THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 *    FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 *    DEALINGS IN THE SOFTWARE.
 * 3. Except as contained in this notice, the name of Tunnel Vision
 *    Laboratories, LLC. shall not be used in advertising or otherwise to
 *    promote the sale, use or other dealings in this Software without prior
 *    written authorization from Tunnel Vision Laboratories, LLC.
 */

// $antlr-format alignTrailingComments true, columnLimit 150, maxEmptyLinesToKeep 1, reflowComments false, useTab false
// $antlr-format allowShortRulesOnASingleLine true, allowShortBlocksOnASingleLine true, minEmptyLines 0, alignSemicolons ownLine
// $antlr-format alignColons trailing, singleLineOverrulesHangingColon true, alignLexerCommands true, alignLabels true, alignTrailers true

lexer grammar PostgreSQLLexer;
/* Reference:
 * http://www.postgresql.org/docs/9.3/static/sql-syntax-lexical.html
 */

options {
    superClass = PostgreSQLLexerBase;
    caseInsensitive = true;
}


// Insert here @header for C++ lexer.


Dollar: '$';

OPEN_PAREN: '(';

CLOSE_PAREN: ')';

OPEN_BRACKET: '[';

CLOSE_BRACKET: ']';

COMMA: ',';

SEMI: ';';

COLON: ':';

STAR: '*';

EQUAL: '=';

DOT: '.';
//NamedArgument	: ':=';

PLUS: '+';

MINUS: '-';

SLASH: '/';

CARET: '^';

LT: '<';

GT: '>';

LESS_LESS: '<<';

GREATER_GREATER: '>>';

COLON_EQUALS: ':=';

LESS_EQUALS: '<=';

EQUALS_GREATER: '=>';

GREATER_EQUALS: '>=';

DOT_DOT: '..';

NOT_EQUALS: '<>';

TYPECAST: '::';

PERCENT: '%';

PARAM: '$' ([0-9])+;
//PARAM: [:$] ([0-9])+;

// this rule does not allow + or - at the end of a multi-character operator
Operator:
    (
        (
            OperatorCharacter
            | ('+' | '-' {this.CheckLaMinus()}? )+ (OperatorCharacter | '/' {this.CheckLaStar()}? )
            | '/'        {this.CheckLaStar()}?
        )+
        | // special handling for the single-character operators + and -
        [+-]
    )
    //TODO somehow rewrite this part without using Actions
    {this.HandleLessLessGreaterGreater();}
;
/* This rule handles operators which end with + or -, and sets the token type to Operator. It is comprised of four
 * parts, in order:
 *
 *   1. A prefix, which does not contain a character from the required set which allows + or - to appear at the end of
 *      the operator.
 *   2. A character from the required set which allows + or - to appear at the end of the operator.
 *   3. An optional sub-token which takes the form of an operator which does not include a + or - at the end of the
 *      sub-token.
 *   4. A suffix sequence of + and - characters.
 */

OperatorEndingWithPlusMinus:
    (OperatorCharacterNotAllowPlusMinusAtEnd | '-' {this.CheckLaMinus()}? | '/' {this.CheckLaStar()}? )* OperatorCharacterAllowPlusMinusAtEnd Operator? (
        '+'
        | '-' {this.CheckLaMinus()}?
    )+        -> type (Operator)
;
// Each of the following fragment rules omits the +, -, and / characters, which must always be handled in a special way

// by the operator rules above.

fragment OperatorCharacter: [*<>=~!@%^&|`?#];
// these are the operator characters that don't count towards one ending with + or -

fragment OperatorCharacterNotAllowPlusMinusAtEnd: [*<>=+];
// an operator may end with + or - if it contains one of these characters

fragment OperatorCharacterAllowPlusMinusAtEnd: [~!@%^&|`?#];
//

// KEYWORDS (Appendix C)



JSON: 'JSON';
JSON_ARRAY: 'JSON_ARRAY';
JSON_ARRAYAGG: 'JSON_ARRAYAGG';
JSON_EXISTS: 'JSON_EXISTS';
JSON_OBJECT: 'JSON_OBJECT';
JSON_OBJECTAGG: 'JSON_OBJECTAGG';
JSON_QUERY: 'JSON_QUERY';
JSON_SCALAR: 'JSON_SCALAR';
JSON_SERIALIZE: 'JSON_SERIALIZE';
JSON_TABLE: 'JSON_TABLE';
JSON_VALUE: 'JSON_VALUE';
MERGE_ACTION: 'MERGE_ACTION';

SYSTEM_USER: 'SYSTEM_USER';

ABSENT: 'ABSENT';
ASENSITIVE: 'ASENSITIVE';
ATOMIC: 'ATOMIC';
BREADTH: 'BREATH';
COMPRESSION: 'COMPRESSION';
CONDITIONAL: 'CONDITIONAL';
DEPTH: 'DEPTH';
EMPTY_P: 'EMPTY';
FINALIZE: 'FINALIZE';
INDENT: 'INDENT';
KEEP: 'KEEP';
KEYS: 'KEYS';
NESTED: 'NESTED';
OMIT: 'OMIT';
PARAMETER: 'PARAMETER';
PATH: 'PATH';
PLAN: 'PLAN';
QUOTES: 'QUOTES';
SCALAR: 'SCALAR';
SOURCE: 'SOURCE';
STRING_P: 'STRING';
TARGET: 'TARGET';
UNCONDITIONAL: 'UNCONDITIONAL';

PERIOD: 'PERIOD';

FORMAT_LA: 'FORMAT_LA';

//

//

// reserved keywords

//

ALL: 'ALL';

ANALYSE: 'ANALYSE';

ANALYZE: 'ANALYZE';

AND: 'AND';

ANY: 'ANY';

ARRAY: 'ARRAY';

AS: 'AS';

ASC: 'ASC';

ASYMMETRIC: 'ASYMMETRIC';

BOTH: 'BOTH';

CASE: 'CASE';

CAST: 'CAST';

CHECK: 'CHECK';

COLLATE: 'COLLATE';

COLUMN: 'COLUMN';

CONSTRAINT: 'CONSTRAINT';

CREATE: 'CREATE';

CURRENT_CATALOG: 'CURRENT_CATALOG';

CURRENT_DATE: 'CURRENT_DATE';

CURRENT_ROLE: 'CURRENT_ROLE';

CURRENT_TIME: 'CURRENT_TIME';

CURRENT_TIMESTAMP: 'CURRENT_TIMESTAMP';

CURRENT_USER: 'CURRENT_USER';

DEFAULT: 'DEFAULT';

DEFERRABLE: 'DEFERRABLE';

DESC: 'DESC';

DISTINCT: 'DISTINCT';

DO: 'DO';

ELSE: 'ELSE';

EXCEPT: 'EXCEPT';

FALSE_P: 'FALSE';

FETCH: 'FETCH';

FOR: 'FOR';

FOREIGN: 'FOREIGN';

FROM: 'FROM';

GRANT: 'GRANT';

GROUP_P: 'GROUP';

HAVING: 'HAVING';

IN_P: 'IN';

INITIALLY: 'INITIALLY';

INTERSECT: 'INTERSECT';

INTO: 'INTO';

LATERAL_P: 'LATERAL';

LEADING: 'LEADING';

LIMIT: 'LIMIT';

LOCALTIME: 'LOCALTIME';

LOCALTIMESTAMP: 'LOCALTIMESTAMP';

NOT: 'NOT';

NULL_P: 'NULL';

OFFSET: 'OFFSET';

ON: 'ON';

ONLY: 'ONLY';

OR: 'OR';

ORDER: 'ORDER';

PLACING: 'PLACING';

PRIMARY: 'PRIMARY';

REFERENCES: 'REFERENCES';

RETURNING: 'RETURNING';

SELECT: 'SELECT';

SESSION_USER: 'SESSION_USER';

SOME: 'SOME';

SYMMETRIC: 'SYMMETRIC';

TABLE: 'TABLE';

THEN: 'THEN';

TO: 'TO';

TRAILING: 'TRAILING';

TRUE_P: 'TRUE';

UNION: 'UNION';

UNIQUE: 'UNIQUE';

USER: 'USER';

USING: 'USING';

VARIADIC: 'VARIADIC';

WHEN: 'WHEN';

WHERE: 'WHERE';

WINDOW: 'WINDOW';

WITH: 'WITH';

//

// reserved keywords (can be function or type)

//

AUTHORIZATION: 'AUTHORIZATION';

BINARY: 'BINARY';

COLLATION: 'COLLATION';

CONCURRENTLY: 'CONCURRENTLY';

CROSS: 'CROSS';

CURRENT_SCHEMA: 'CURRENT_SCHEMA';

FREEZE: 'FREEZE';

FULL: 'FULL';

ILIKE: 'ILIKE';

INNER_P: 'INNER';

IS: 'IS';

ISNULL: 'ISNULL';

JOIN: 'JOIN';

LEFT: 'LEFT';

LIKE: 'LIKE';

NATURAL: 'NATURAL';

NOTNULL: 'NOTNULL';

OUTER_P: 'OUTER';

OVER: 'OVER';

OVERLAPS: 'OVERLAPS';

RIGHT: 'RIGHT';

SIMILAR: 'SIMILAR';

VERBOSE: 'VERBOSE';
//

// non-reserved keywords

//

ABORT_P: 'ABORT';

ABSOLUTE_P: 'ABSOLUTE';

ACCESS: 'ACCESS';

ACTION: 'ACTION';

ADD_P: 'ADD';

ADMIN: 'ADMIN';

AFTER: 'AFTER';

AGGREGATE: 'AGGREGATE';

ALSO: 'ALSO';

ALTER: 'ALTER';

ALWAYS: 'ALWAYS';

ASSERTION: 'ASSERTION';

ASSIGNMENT: 'ASSIGNMENT';

AT: 'AT';

ATTRIBUTE: 'ATTRIBUTE';

BACKWARD: 'BACKWARD';

BEFORE: 'BEFORE';

BEGIN_P: 'BEGIN';

BY: 'BY';

CACHE: 'CACHE';

CALLED: 'CALLED';

CASCADE: 'CASCADE';

CASCADED: 'CASCADED';

CATALOG: 'CATALOG';

CHAIN: 'CHAIN';

CHARACTERISTICS: 'CHARACTERISTICS';

CHECKPOINT: 'CHECKPOINT';

CLASS: 'CLASS';

CLOSE: 'CLOSE';

CLUSTER: 'CLUSTER';

COMMENT: 'COMMENT';

COMMENTS: 'COMMENTS';

COMMIT: 'COMMIT';

COMMITTED: 'COMMITTED';

CONFIGURATION: 'CONFIGURATION';

CONNECTION: 'CONNECTION';

CONSTRAINTS: 'CONSTRAINTS';

CONTENT_P: 'CONTENT';

CONTINUE_P: 'CONTINUE';

CONVERSION_P: 'CONVERSION';

COPY: 'COPY';

COST: 'COST';

CSV: 'CSV';

CURSOR: 'CURSOR';

CYCLE: 'CYCLE';

DATA_P: 'DATA';

DATABASE: 'DATABASE';

DAY_P: 'DAY';

DEALLOCATE: 'DEALLOCATE';

DECLARE: 'DECLARE';

DEFAULTS: 'DEFAULTS';

DEFERRED: 'DEFERRED';

DEFINER: 'DEFINER';

DELETE_P: 'DELETE';

DELIMITER: 'DELIMITER';

DELIMITERS: 'DELIMITERS';

DICTIONARY: 'DICTIONARY';

DISABLE_P: 'DISABLE';

DISCARD: 'DISCARD';

DOCUMENT_P: 'DOCUMENT';

DOMAIN_P: 'DOMAIN';

DOUBLE_P: 'DOUBLE';

DROP: 'DROP';

EACH: 'EACH';

ENABLE_P: 'ENABLE';

ENCODING: 'ENCODING';

ENCRYPTED: 'ENCRYPTED';

ENUM_P: 'ENUM';

ESCAPE: 'ESCAPE';

EVENT: 'EVENT';

EXCLUDE: 'EXCLUDE';

EXCLUDING: 'EXCLUDING';

EXCLUSIVE: 'EXCLUSIVE';

EXECUTE: 'EXECUTE';

EXPLAIN: 'EXPLAIN';

EXTENSION: 'EXTENSION';

EXTERNAL: 'EXTERNAL';

FAMILY: 'FAMILY';

FIRST_P: 'FIRST';

FOLLOWING: 'FOLLOWING';

FORCE: 'FORCE';

FORWARD: 'FORWARD';

FUNCTION: 'FUNCTION';

FUNCTIONS: 'FUNCTIONS';

GLOBAL: 'GLOBAL';

GRANTED: 'GRANTED';

HANDLER: 'HANDLER';

HEADER_P: 'HEADER';

HOLD: 'HOLD';

HOUR_P: 'HOUR';

IDENTITY_P: 'IDENTITY';

IF_P: 'IF';

IMMEDIATE: 'IMMEDIATE';

IMMUTABLE: 'IMMUTABLE';

IMPLICIT_P: 'IMPLICIT';

INCLUDING: 'INCLUDING';

INCREMENT: 'INCREMENT';

INDEX: 'INDEX';

INDEXES: 'INDEXES';

INHERIT: 'INHERIT';

INHERITS: 'INHERITS';

INLINE_P: 'INLINE';

INSENSITIVE: 'INSENSITIVE';

INSERT: 'INSERT';

INSTEAD: 'INSTEAD';

INVOKER: 'INVOKER';

ISOLATION: 'ISOLATION';

KEY: 'KEY';

LABEL: 'LABEL';

LANGUAGE: 'LANGUAGE';

LARGE_P: 'LARGE';

LAST_P: 'LAST';
//LC_COLLATE			: 'LC'_'COLLATE;

//LC_CTYPE			: 'LC'_'CTYPE;

LEAKPROOF: 'LEAKPROOF';

LEVEL: 'LEVEL';

LISTEN: 'LISTEN';

LOAD: 'LOAD';

LOCAL: 'LOCAL';

LOCATION: 'LOCATION';

LOCK_P: 'LOCK';

MAPPING: 'MAPPING';

MATCH: 'MATCH';

MATCHED: 'MATCHED';

MATERIALIZED: 'MATERIALIZED';

MAXVALUE: 'MAXVALUE';

MERGE: 'MERGE';

MINUTE_P: 'MINUTE';

MINVALUE: 'MINVALUE';

MODE: 'MODE';

MONTH_P: 'MONTH';

MOVE: 'MOVE';

NAME_P: 'NAME';

NAMES: 'NAMES';

NEXT: 'NEXT';

NO: 'NO';

NOTHING: 'NOTHING';

NOTIFY: 'NOTIFY';

NOWAIT: 'NOWAIT';

NULLS_P: 'NULLS';

OBJECT_P: 'OBJECT';

OF: 'OF';

OFF: 'OFF';

OIDS: 'OIDS';

OPERATOR: 'OPERATOR';

OPTION: 'OPTION';

OPTIONS: 'OPTIONS';

OWNED: 'OWNED';

OWNER: 'OWNER';

PARSER: 'PARSER';

PARTIAL: 'PARTIAL';

PARTITION: 'PARTITION';

PASSING: 'PASSING';

PASSWORD: 'PASSWORD';

PLANS: 'PLANS';

PRECEDING: 'PRECEDING';

PREPARE: 'PREPARE';

PREPARED: 'PREPARED';

PRESERVE: 'PRESERVE';

PRIOR: 'PRIOR';

PRIVILEGES: 'PRIVILEGES';

PROCEDURAL: 'PROCEDURAL';

PROCEDURE: 'PROCEDURE';

PROGRAM: 'PROGRAM';

QUOTE: 'QUOTE';

RANGE: 'RANGE';

READ: 'READ';

REASSIGN: 'REASSIGN';

RECHECK: 'RECHECK';

RECURSIVE: 'RECURSIVE';

REF: 'REF';

REFRESH: 'REFRESH';

REINDEX: 'REINDEX';

RELATIVE_P: 'RELATIVE';

RELEASE: 'RELEASE';

RENAME: 'RENAME';

REPEATABLE: 'REPEATABLE';

REPLACE: 'REPLACE';

REPLICA: 'REPLICA';

RESET: 'RESET';

RESTART: 'RESTART';

RESTRICT: 'RESTRICT';

RETURNS: 'RETURNS';

REVOKE: 'REVOKE';

ROLE: 'ROLE';

ROLLBACK: 'ROLLBACK';

ROWS: 'ROWS';

RULE: 'RULE';

SAVEPOINT: 'SAVEPOINT';

SCHEMA: 'SCHEMA';

SCROLL: 'SCROLL';

SEARCH: 'SEARCH';

SECOND_P: 'SECOND';

SECURITY: 'SECURITY';

SEQUENCE: 'SEQUENCE';

SEQUENCES: 'SEQUENCES';

SERIALIZABLE: 'SERIALIZABLE';

SERVER: 'SERVER';

SESSION: 'SESSION';

SET: 'SET';

SHARE: 'SHARE';

SHOW: 'SHOW';

SIMPLE: 'SIMPLE';

SNAPSHOT: 'SNAPSHOT';

STABLE: 'STABLE';

STANDALONE_P: 'STANDALONE';

START: 'START';

STATEMENT: 'STATEMENT';

STATISTICS: 'STATISTICS';

STDIN: 'STDIN';

STDOUT: 'STDOUT';

STORAGE: 'STORAGE';

STRICT_P: 'STRICT';

STRIP_P: 'STRIP';

SYSID: 'SYSID';

SYSTEM_P: 'SYSTEM';

TABLES: 'TABLES';

TABLESPACE: 'TABLESPACE';

TEMP: 'TEMP';

TEMPLATE: 'TEMPLATE';

TEMPORARY: 'TEMPORARY';

TEXT_P: 'TEXT';

TRANSACTION: 'TRANSACTION';

TRIGGER: 'TRIGGER';

TRUNCATE: 'TRUNCATE';

TRUSTED: 'TRUSTED';

TYPE_P: 'TYPE';

TYPES_P: 'TYPES';

UNBOUNDED: 'UNBOUNDED';

UNCOMMITTED: 'UNCOMMITTED';

UNENCRYPTED: 'UNENCRYPTED';

UNKNOWN: 'UNKNOWN';

UNLISTEN: 'UNLISTEN';

UNLOGGED: 'UNLOGGED';

UNTIL: 'UNTIL';

UPDATE: 'UPDATE';

VACUUM: 'VACUUM';

VALID: 'VALID';

VALIDATE: 'VALIDATE';

VALIDATOR: 'VALIDATOR';
//VALUE				: 'VALUE;

VARYING: 'VARYING';

VERSION_P: 'VERSION';

VIEW: 'VIEW';

VOLATILE: 'VOLATILE';

WHITESPACE_P: 'WHITESPACE';

WITHOUT: 'WITHOUT';

WORK: 'WORK';

WRAPPER: 'WRAPPER';

WRITE: 'WRITE';

XML_P: 'XML';

YEAR_P: 'YEAR';

YES_P: 'YES';

ZONE: 'ZONE';
//

// non-reserved keywords (can not be function or type)

//

BETWEEN: 'BETWEEN';

BIGINT: 'BIGINT';

BIT: 'BIT';

BOOLEAN_P: 'BOOLEAN';

CHAR_P: 'CHAR';

CHARACTER: 'CHARACTER';

COALESCE: 'COALESCE';

DEC: 'DEC';

DECIMAL_P: 'DECIMAL';

EXISTS: 'EXISTS';

EXTRACT: 'EXTRACT';

FLOAT_P: 'FLOAT';

GREATEST: 'GREATEST';

INOUT: 'INOUT';

INT_P: 'INT';

INTEGER: 'INTEGER';

INTERVAL: 'INTERVAL';

LEAST: 'LEAST';

NATIONAL: 'NATIONAL';

NCHAR: 'NCHAR';

NONE: 'NONE';

NULLIF: 'NULLIF';

NUMERIC: 'NUMERIC';

OVERLAY: 'OVERLAY';

POSITION: 'POSITION';

PRECISION: 'PRECISION';

REAL: 'REAL';

ROW: 'ROW';

SETOF: 'SETOF';

SMALLINT: 'SMALLINT';

SUBSTRING: 'SUBSTRING';

TIME: 'TIME';

TIMESTAMP: 'TIMESTAMP';

TREAT: 'TREAT';

TRIM: 'TRIM';

VALUES: 'VALUES';

VARCHAR: 'VARCHAR';

XMLATTRIBUTES: 'XMLATTRIBUTES';

XMLCOMMENT: 'XMLCOMMENT';

XMLAGG: 'XMLAGG';

XML_IS_WELL_FORMED: 'XML_IS_WELL_FORMED';

XML_IS_WELL_FORMED_DOCUMENT: 'XML_IS_WELL_FORMED_DOCUMENT';

XML_IS_WELL_FORMED_CONTENT: 'XML_IS_WELL_FORMED_CONTENT';

XPATH: 'XPATH';

XPATH_EXISTS: 'XPATH_EXISTS';

XMLCONCAT: 'XMLCONCAT';

XMLELEMENT: 'XMLELEMENT';

XMLEXISTS: 'XMLEXISTS';

XMLFOREST: 'XMLFOREST';

XMLPARSE: 'XMLPARSE';

XMLPI: 'XMLPI';

XMLROOT: 'XMLROOT';

XMLSERIALIZE: 'XMLSERIALIZE';
//MISSED

CALL: 'CALL';

CURRENT_P: 'CURRENT';

ATTACH: 'ATTACH';

DETACH: 'DETACH';

EXPRESSION: 'EXPRESSION';

GENERATED: 'GENERATED';

LOGGED: 'LOGGED';

STORED: 'STORED';

INCLUDE: 'INCLUDE';

ROUTINE: 'ROUTINE';

TRANSFORM: 'TRANSFORM';

IMPORT_P: 'IMPORT';

POLICY: 'POLICY';

METHOD: 'METHOD';

REFERENCING: 'REFERENCING';

NEW: 'NEW';

OLD: 'OLD';

VALUE_P: 'VALUE';

SUBSCRIPTION: 'SUBSCRIPTION';

PUBLICATION: 'PUBLICATION';

OUT_P: 'OUT';

END_P: 'END';

ROUTINES: 'ROUTINES';

SCHEMAS: 'SCHEMAS';

PROCEDURES: 'PROCEDURES';

INPUT_P: 'INPUT';

SUPPORT: 'SUPPORT';

PARALLEL: 'PARALLEL';

SQL_P: 'SQL';

DEPENDS: 'DEPENDS';

OVERRIDING: 'OVERRIDING';

CONFLICT: 'CONFLICT';

SKIP_P: 'SKIP';

LOCKED: 'LOCKED';

TIES: 'TIES';

ROLLUP: 'ROLLUP';

CUBE: 'CUBE';

GROUPING: 'GROUPING';

SETS: 'SETS';

TABLESAMPLE: 'TABLESAMPLE';

ORDINALITY: 'ORDINALITY';

XMLTABLE: 'XMLTABLE';

COLUMNS: 'COLUMNS';

XMLNAMESPACES: 'XMLNAMESPACES';

ROWTYPE: 'ROWTYPE';

NORMALIZED: 'NORMALIZED';

WITHIN: 'WITHIN';

FILTER: 'FILTER';

GROUPS: 'GROUPS';

OTHERS: 'OTHERS';

NFC: 'NFC';

NFD: 'NFD';

NFKC: 'NFKC';

NFKD: 'NFKD';

UESCAPE: 'UESCAPE';

VIEWS: 'VIEWS';

NORMALIZE: 'NORMALIZE';

DUMP: 'DUMP';

ERROR: 'ERROR';

USE_VARIABLE: 'USE_VARIABLE';

USE_COLUMN: 'USE_COLUMN';

CONSTANT: 'CONSTANT';

PERFORM: 'PERFORM';

GET: 'GET';

DIAGNOSTICS: 'DIAGNOSTICS';

STACKED: 'STACKED';

ELSIF: 'ELSIF';

WHILE: 'WHILE';

FOREACH: 'FOREACH';

SLICE: 'SLICE';

EXIT: 'EXIT';

RETURN: 'RETURN';

RAISE: 'RAISE';

SQLSTATE: 'SQLSTATE';

DEBUG: 'DEBUG';

INFO: 'INFO';

NOTICE: 'NOTICE';

WARNING: 'WARNING';

EXCEPTION: 'EXCEPTION';

ASSERT: 'ASSERT';

LOOP: 'LOOP';

OPEN: 'OPEN';

FORMAT: 'FORMAT';


Identifier: [a-zA-Z_\u007F-\uFFFF] [a-zA-Z_$0-9\u007F-\uFFFF]*;

QuotedIdentifier: '"' ('""' | ~ [\u0000"])* '"';

UnicodeQuotedIdentifier: 'U' '&' QuotedIdentifier;

StringConstant: '\'' ('\'\'' | ~ '\'')* '\'';

BeginEscapeStringConstant: 'E' '\'' -> more, pushMode (EscapeStringConstantMode);

UnicodeEscapeStringConstant: 'U' '&' '\'' ('\'\'' | ~ '\'')* '\'';

BeginDollarStringConstant: '$' Tag? '$' {this.PushTag();} -> pushMode (DollarQuotedStringMode);
/* "The tag, if any, of a dollar-quoted string follows the same rules as an
 * unquoted identifier, except that it cannot contain a dollar sign."
 */

fragment Tag: [a-zA-Z_\u007F-\uFFFF] ([a-zA-Z_\u007F-\uFFFF] | [0-9])*;

BinaryStringConstant: 'B' '\'' [01]* '\'';

HexadecimalStringConstant: 'X' '\'' [0-9A-F]* '\'';

Integral: Digits;

BinaryIntegral: '0b' Digits;

OctalIntegral: '0o' Digits;

HexadecimalIntegral: '0x' Digits;

Numeric:
    Digits '.' Digits?
    (
        'E' [+-]? Digits
    )?
    | '.' Digits ('E' [+-]? Digits)?
    | Digits 'E' [+-]? Digits
;

fragment Digits: [0-9]+;

PLSQLVARIABLENAME: ':' [A-Z_] [A-Z_0-9$]*;

PLSQLIDENTIFIER: ':"' ('\\' . | '""' | ~ ('"' | '\\'))* '"';

Whitespace: [ \t]+ -> channel (HIDDEN);

Newline: ('\r' '\n'? | '\n') -> channel (HIDDEN);

LineComment: '--' ~ [\r\n]* -> channel (HIDDEN);

BlockComment:
    ('/*' ('/'* BlockComment | ~ [/*] | '/'+ ~ [/*] | '*'+ ~ [/*])* '*'* '*/') -> channel (HIDDEN)
;

MetaCommand: '\\' -> pushMode(META), more ;

ErrorCharacter: .;

mode EscapeStringConstantMode;
EscapeStringConstant: EscapeStringText '\'' -> mode (AfterEscapeStringConstantMode);


fragment EscapeStringText options { caseInsensitive = false; }:
    (
        '\'\''
        | '\\' (
            // two-digit hex escapes are still valid when treated as single-digit escapes
            'x' [0-9a-fA-F]
            | 'u' [0-9a-fA-F] [0-9a-fA-F] [0-9a-fA-F] [0-9a-fA-F]
            | 'U' [0-9a-fA-F] [0-9a-fA-F] [0-9a-fA-F] [0-9a-fA-F] [0-9a-fA-F] [0-9a-fA-F] [0-9a-fA-F] [0-9a-fA-F]
            | // Any character other than the Unicode escapes can follow a backslash. Some have special meaning,
            // but that doesn't affect the syntax.
            ~ [xuU]
        )
        | ~ ['\\]
    )*
;

InvalidUnterminatedEscapeStringConstant:
    InvalidEscapeStringText
    // Handle a final unmatched \ character appearing at the end of the file
    '\\'? EOF
;

fragment InvalidEscapeStringText: ('\'\'' | '\\' . | ~ ['\\])*;

mode AfterEscapeStringConstantMode;
AfterEscapeStringConstantMode_Whitespace: Whitespace -> type (Whitespace), channel (HIDDEN);

AfterEscapeStringConstantMode_Newline:
    Newline -> type (Newline), channel (HIDDEN), mode (AfterEscapeStringConstantWithNewlineMode)
;

AfterEscapeStringConstantMode_NotContinued:
     -> skip, popMode
;

mode AfterEscapeStringConstantWithNewlineMode;
AfterEscapeStringConstantWithNewlineMode_Whitespace:
    Whitespace -> type (Whitespace), channel (HIDDEN)
;

AfterEscapeStringConstantWithNewlineMode_Newline: Newline -> type (Newline), channel (HIDDEN);

AfterEscapeStringConstantWithNewlineMode_Continued:
    '\'' -> more, mode (EscapeStringConstantMode)
;

AfterEscapeStringConstantWithNewlineMode_NotContinued:
     -> skip, popMode
;

mode DollarQuotedStringMode;
DollarText:
    ~ '$'+
    //| '$'([0-9])+
    | // this alternative improves the efficiency of handling $ characters within a dollar-quoted string which are

    // not part of the ending tag.
    '$' ~ '$'*
;

// NB: Next rule on two lines in order to make transformGrammar.py easy.
EndDollarStringConstant: ('$' Tag? '$') {this.IsTag()}?
    {this.PopTag();} -> popMode;

mode META;
MetaSemi : {this.IsSemiColon()}? ';' -> type(SEMI), popMode ;
MetaOther : ~[;\r\n\\"] .*? ('\\\\' | [\r\n]+) -> type(SEMI), popMode ;
