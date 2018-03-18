parser grammar CfParser;

@header { package com.riguz.forks.antlr; }
options { tokenVocab=CfLexer; }

properties
    : (property SEMI)*
    EOF
    ;

property
    : basicProperty
    | arrayProperty
    ;
basicProperty
    : type NAME ASSIGN expression
    ;
arrayProperty
    : type NAME ASSIGN LBRACK (expression COMMA)* RBRACK
    ;
type
    : BOOL
    | INT
    | DECIMAL
    | STRING
    | MAP
    ;
expression
    : basicExpression      #basic
    | nestedExpression     #nested
    ;
basicExpression
    : BOOL_LITERAL         #bool
    | INT_LITERAL          #int
    | HEX_LITERAL          #hex
    | DECIMAL_LITERAL      #decimal
    ;
nestedExpression
    : stringExpression     #string
    | mapExpression        #map
    ;

stringExpression
    : (STRING_LITERAL | REFERENCE ) (LINK stringExpression)?
    ;

mapExpression
    : LBRACE (property SEMI)* RBRACE
    ;