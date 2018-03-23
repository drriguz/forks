parser grammar CfParser;

@header { package com.riguz.forks.antlr; }
options { tokenVocab=CfLexer; }

script
    : shared? scope*
      EOF
    ;
scope
    : SCOPE NAME LBRACE (property SEMI)* RBRACE SEMI
    ;
shared
    : SHARED LBRACE (property SEMI)* RBRACE SEMI
    ;
property
    : type NAME ASSIGN expression        #basicProperty
    | type NAME ASSIGN LBRACK
        expression? (COMMA expression)*
      RBRACK                             #arrayProperty
    ;

type
    : BOOL
    | INT
    | DECIMAL
    | STRING
    ;
expression
    : BOOL_LITERAL         #bool
    | INT_LITERAL          #int
    | HEX_LITERAL          #hex
    | DECIMAL_LITERAL      #decimal
    | stringExpression     #string
    ;

stringExpression
    : (STRING_LITERAL | REFERENCE ) (LINK stringExpression)?
    ;
