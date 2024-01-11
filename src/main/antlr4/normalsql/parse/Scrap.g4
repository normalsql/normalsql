grammar Scrap;

root : ( OTHER | '-' | '/' | '.' )* EOF ;

COMMENT
//    : '--' .*? ( '\n' | EOF ) -> channel( HIDDEN ) ;
    // TODO is this better? cuz doesn't consume '\n' ?
    : '--' ~ [\r\n]* -> channel( HIDDEN ) ;

BLOCK_COMMENT
    : '/*' ( BLOCK_COMMENT | . )*? '*/' -> channel( HIDDEN ) ;

//OTHER : ( [\b\t\r\n\u000B\u000C\u008A a-zA-Z0-9()[\]{}\\'"`+=<>,:;$%^#@!?|*_&~æª] )+ ;
OTHER : ( [\b\t\r\n\u000B\u000C a-zA-Z0-9()[\]{}\\'"`+=<>,:;$%^#@!?|*_&~\u0080-\uFFFF] )+ ;
/*
line 102:39 token recognition error at: ''
line 102:40 token recognition error at: 'æ'
line 104:42 token recognition error at: 'ª'
line 104:43 token recognition error at: ''
*/
