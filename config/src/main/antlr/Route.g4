grammar Route;
@header { package com.riguz.forks.antlr; }

routeConfig
    : (controllers NEW_LINE)+
      (filters NEW_LINE)*
      (routes NEW_LINE)+
      EOF
    ;
controllers
    : CONTROLLERS IDENTIFIER? '{'
        classNames
      '}'
    ;
filters
    : FILTERS IDENTIFIER? '{'
        classNames
      '}'
    ;
classNames
    : package NEW_LINE
      (className NEW_LINE)+
    ;
package
    : PACKAGE PACKAGE_NAME
    ;

className
    : IDENTIFIER ('->' IDENTIFIER)?
    ;
applyFilters
    : ('+' IDENTIFIER NEW_LINE)+
    ;
routes
    : ROUTES IDENTIFIER? '{'
        applyFilters?
        (route NEW_LINE)*
      '}'
    ;
route
    : methods pattern functionCall
    ;
methods
    : GET | POST | PUT | PATCH | DELETE
    ;
paths
    : SUB_PATH | NAMED_PATH | WILDCARD_PATH;
pattern
    : INDEX_PATH
    | paths+
    ;
functionCall
    : FUNCTION params? ')'
    ;
params
    : (IDENTIFIER ':' pathParamTypes)(',' IDENTIFIER ':' pathParamTypes)*
    ;

pathParamTypes
    : INT | LONG | STRING
    ;

// keywords
CONTROLLERS: 'controllers';
FILTERS    : 'filters';
ROUTES     : 'routes';
PACKAGE    : 'package';

GET   : 'get';
POST  : 'post';
PUT   : 'put';
DELETE: 'delete';
PATCH : 'patch';

INT   : 'Integer';
LONG  : 'Long';
STRING: 'String';

IDENTIFIER   : [a-zA-Z_][a-zA-Z_0-9]*;
PACKAGE_NAME : IDENTIFIER ('.' IDENTIFIER)*;
FUNCTION     : IDENTIFIER '.' IDENTIFIER '(';
INDEX_PATH   : '/';
SUB_PATH     : '/' IDENTIFIER;
NAMED_PATH   : '/:' IDENTIFIER;
WILDCARD_PATH: '/*' IDENTIFIER;

// comments
COMMENT      : '/*' .*? '*/'    -> channel(HIDDEN);
LINE_COMMENT : '//' ~[\r\n]*    -> channel(HIDDEN);
WS           : [ \t]+           -> channel(HIDDEN);
NEW_LINE     : ('\r'? '\n')+;


