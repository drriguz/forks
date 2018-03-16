lexer grammar PLexer;

TYPE
    : 'string'
    ;
NAME
    : [a-zA-Z_][a-zA-Z_0-9]*
    ;

SPLITER
    : ';'
    ;
TO
    : '='
    ;

REFERENCE
    : '${' NAME '}'
    ;

STRING
    : '"' (Char | REFERENCE)* '"'
    | BLOCK_STRING
    ;

fragment
Char
    : ~["\\\n\r]
    | EscapeSequence
    ;

fragment
EscapeSequence
    : '\\' ["\\nrt]
    ;
WS
    : [ \t]+ -> skip
    ;

NEW_LINE
    : ('\r' '\n'? | '\n')
    ;

LINE_COMMENT
    : '//' ~[\n]* ->skip
    ;

BLOCK_STRING_BEGIN
    : '---' -> pushMode(BLOCK_STRING_MODE)
    ;

mode BLOCK_STRING_MODE;
BLOCK_STRING_END
    : '`' -> popMode
    ;

BLOCK_STRING
    : ~'`'+
    ;