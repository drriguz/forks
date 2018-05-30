package com.riguz.forks.json.simple;

import com.riguz.forks.json.SyntaxException;

public enum Token {
    OBJECT_START, // {
    OBJECT_END,   // }
    ARRAY_START,  // [
    ARRAY_END,    // ]
    NUMBER,       // 123.4
    STRING,       // "Hello world!"
    TRUE,         // true
    FALSE,        // false
    NULL,         // null
    COLON,        // :
    COMMA,        // ,
    SPACE,        // \t \n \s ...
    EOF;          // terminate

    public static final char INVALID = (char) -1;


    public static Token of(char value) {
        switch (value) {
            case '{':
                return OBJECT_START;
            case '}':
                return OBJECT_END;
            case '[':
                return ARRAY_START;
            case ']':
                return ARRAY_END;
            case 't':
                return TRUE;
            case 'f':
                return FALSE;
            case 'n':
                return NULL;
            case ':':
                return COLON;
            case ',':
                return COMMA;
            case '"':
                return STRING;
            case '-':
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                return NUMBER;
            case ' ':
            case '\t':
            case '\n':
                return SPACE;
            case INVALID:
                return EOF;
            default:
                throw new SyntaxException("Invalid token found:" + value);
        }
    }
}
