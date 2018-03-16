parser grammar PParser;

@header { package com.riguz.forks; }
options { tokenVocab=XLexer; }

definations
    : (defination SEMI)*
    EOF
    ;

defination
    : basicDefination
    | arrayDefination
    ;
basicDefination
    : type NAME ASSIGN expression
    ;
arrayDefination
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
    : BOOL_LITERAL
    | INT_LITERAL
    | HEX_LITERAL
    | DECIMAL_LITERAL
    | string
    | mapExpression
    ;
string
    : (STRING_LITERAL | REFERENCE ) (LINK string)?
    ;

mapExpression
    : LBRACE (defination SEMI)* RBRACE
    ;