package com.riguz.forks.json;

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
        return value != -1;
    }

    protected void expectNext(char... expected) {
        for (char e : expected) {
            read();
            if (value != e)
                throw new SyntaxException("Expected:" + e + " but got:" + getValue(), getLocation());
        }
    }

    private boolean isDigit() {
        return value >= '0' && value <= '9';
    }

    protected void readNumbers() {
        boolean decimal = false;
        do {
            read();
            if (isDigit()) {

            } else if (value == '.') {
                if (decimal)
                    throw new SyntaxException("Multi dot found", getLocation());
                decimal = true;
            } else
                break;
        } while (true);
    }

    protected void readString() {
        boolean closed = false;
        do {
            read();
            if (value == -1) {
                break;
            } else if (value == '"') {
                closed = true;
                break;
            } else if (value == '\\') {
                // todo: read escaped string
            }
        } while (true);
        if (!closed)
            throw new SyntaxException("String not closed:", getLocation());
    }

    protected Location getLocation() {
        return new Location(line, offset, (char) value);
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
            case ARRAY_START:
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
                readNumbers();
                break;
            case STRING:
                readString();
                read();
                break;
            case SPACE:
                skipWhiteSpace();
                break;
            default:
                break;
        }
        return token;
    }
}
