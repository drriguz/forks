package com.riguz.forks.json;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Iterator;

public class TokenReader extends BufferedReaderWrapper implements Iterator<Token> {
    public TokenReader(String content) {
        this(new StringReader(content));
    }

    public TokenReader(Reader reader) {
        super(reader);
        read();
    }

    @Override
    public boolean hasNext() {
        if (size < 0 || index > size)
            return false;
        skipWhiteSpace();
        return value != -1;
    }

    protected void expectNext(char... expected) {
        for (char e : expected) {
            read();
            if (value != e)
                throw new SyntaxException("Expected:" + e + " but got:" + getValue());
        }
    }

    private static final char[] TRUE_TOKENS = "rue".toCharArray();
    private static final char[] FALSE_TOKENS = "alse".toCharArray();
    private static final char[] NULL_TOKENS = "ull".toCharArray();

    @Override
    public Token next() {
        if (!hasNext())
            return null;
        Token token = Token.of((char) value);
        switch (token) {
            case OBJECT_START:
            case OBJECT_END:
            case ARRAY_STSRT:
            case ARRAY_END:
            case COLON:
            case COMMA:
                read();
                break;
            case TRUE:
                expectNext(TRUE_TOKENS);
                read();
                break;
            case FALSE:
                expectNext(FALSE_TOKENS);
                read();
                break;
            case NULL:
                expectNext(NULL_TOKENS);
                read();
                break;
            case NUMBER:
            case STRING:
            default:
                break;
        }
        return token;
    }
}
