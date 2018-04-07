grammar Route;
@header { package com.riguz.forks.antlr; }

routeConfig
    : (controllers)+
      (filters)*
      (routes)+
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
    : packageName
      (classIdentifier )+
    ;
packageName
    : PACKAGE PACKAGE_NAME
    ;

classIdentifier
    : IDENTIFIER ('->' IDENTIFIER)?
    ;
applyFilters
    : ('+' IDENTIFIER )+
    ;
routes
    : ROUTES IDENTIFIER? '{'
        applyFilters?
        (route)*
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
WS           : [ \t\r\n]+       -> channel(HIDDEN);


