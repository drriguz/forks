grammar cf;
@header {
    package com.riguz.forks.antlr;
}

// parsers
script
  : (stat SPLITER)* EOF
  ;

stat
  :
  assignment
  ;

assignment
  : simpleAssignment
  | arrayAssignment
  ;

simpleAssignment
  : dataType NAME TO expression
  ;
arrayAssignment
  : dataType NAME TO '[' (expression ',')* expression ']'
  ;

dataType
  : BOOL
  | INT
  | FLOAT
  | STRING
  ;

expression
  : NAME
  | BOOL_LITERAL
  | INT_LITERAL
  | FLOAT_LITERAL
  | stringLiteral
  ;

stringLiteral
  : STRING_SEQUENCE
  ;

// lexers
BOOL
  : 'bool'
  ;

INT
  : 'int'
  ;

FLOAT
  : 'float'
  ;

STRING
  : 'string'
  ;

MAP
  : 'map'
  ;

SPLITER
  : ';'
  ;

TO
  : '='
  ;

BOOL_LITERAL
  : 'true'
  | 'false'
  ;

INT_LITERAL
  : Digit+
  ;

FLOAT_LITERAL
  : Digit+ '.' Digit+
  ;

REFERENCE
  : '${' NAME '}'
  ;

STRING_SEQUENCE
  : '"' (Char | REFERENCE)* '"'
  ;

NAME
  : [a-zA-Z_][a-zA-Z_0-9]*
  ;

fragment
Digit
  : [0-9]
  ;

fragment
Char
  : ~["\n\r${}]
  | EscapeSequence
  ;

fragment
EscapeSequence
  : '\\' ["\\nrt${}]
  ;

WS
  :
  [ \t\r\n]+ -> skip
  ;