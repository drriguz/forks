grammar cf;

script
  : stat+ EOF
  ;

stat
  :
  assignment
  ;

assignment
  : ID '=' STRING ';'
  ;

ID
  : [a-zA-Z_][a-zA-Z_0-9]*
  ;

INT
  : Digit+
  ;

FLOAT
  : Digit+ '.' Digit+
  ;

STRING
  : '"' Char* '"'
  ;


fragment
Digit
  : [0-9]
  ;

fragment
Char
  : ~["]
  | EscapeSequence
  ;

fragment
EscapeSequence
  : '\\' ["\\nrt]
  ;

WS
  :
  [ \t\r\n]+ -> skip
  ;