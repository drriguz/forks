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
  | MAP
  ;

expression
  : REFERENCE
  | BOOL_LITERAL
  | INT_LITERAL
  | FLOAT_LITERAL
  | stringLiteral
  | mapExpression
  ;

mapExpression
  : '{' (propertity SPLITER)* '}'
  ;

propertity
  : simpleAssignment
  | arrayAssignment
  ;

longStringBlock
  : '(\n' LONG_STRING_BLOCK_LINE* '\n)'
  ;

stringLiteral
  : STRING_LITERAL
  | longStringBlock
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

LINE_COMMENT
  : '//' ~[\r\n]* ->skip
  ;

STRING_LITERAL
  : '"' (Char | REFERENCE)* '"'
  ;

LONG_STRING_LINE_ENDING
  : '(' (Char | REFERENCE)* ')'
  ;

LONG_STRING_LINE
  : '(' (Char | REFERENCE)* '\n'
  ;

LONG_STRING_BLOCK_LINE
  : (Char| REFERENCE)* '\n'
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
  : ~["\n\r${}()]
  | EscapeSequence
  ;

fragment
EscapeSequence
  : '\\' ["\\nrt${}()]
  ;

WS
  :
  [ \t\r\n]+ -> skip
  ;