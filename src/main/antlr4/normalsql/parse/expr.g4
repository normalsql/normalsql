grammar expr;

stat: expr '=' expr ';' // e.g., x=y; or x=f(x);
    | expr ';'          // e.g., f(x); or f(g(x));
    ;
expr: expr '*' expr
//    | expr '+' expr
    | expr '+' expr 'gorp'?
    | expr '(' expr ')' // f(x)
    | id
    ;

 id : ID ;

 ID : [a-zA-Z]+;

 WHITESPACE : [ \b\t\r\n\u000B\u000C] -> channel ( HIDDEN ) ;