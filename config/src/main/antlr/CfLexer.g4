lexer grammar CfLexer;
@header { package com.riguz.forks.antlr; }

// types
STRING   : 'string';
INT      : 'int';
BOOL     : 'bool';
FLOAT    : 'float';

// keywords
SHARED   : 'shared';
SCOPE    : 'scope';

// operators & marks
ASSIGN: '=';
LBRACE: '{';
RBRACE: '}';
LBRACK: '[';
RBRACK: ']';
SEMI  : ';';
COMMA : ',';
LINK  : '..';


// expressions
BOOL_LITERAL    : 'true' | 'false';
INT_LITERAL     : Digit+;
HEX_LITERAL     : '0x' HexDigit+;
FLOAT_LITERAL   : Digit+ '.' Digit+;
STRING_LITERAL  : '"' (Char | EscapeSequence )* '"';

NAME     : [a-zA-Z_][a-zA-Z_0-9]*;
REFERENCE: '${' NAME '}';

fragment Char          : ~["\\\n\r];
fragment EscapeSequence: '\\' ["\\nrt];
fragment Digit         : [0-9];
fragment HexDigit      : [0-9a-f];

// comments
COMMENT      : '/*' .*? '*/'    -> channel(HIDDEN);
LINE_COMMENT : '//' ~[\r\n]*    -> channel(HIDDEN);
WS           : [ \t\r\n\u000C]+ -> channel(HIDDEN);
