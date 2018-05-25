package com.riguz.forks.json;

import java.io.Reader;
import java.io.StringReader;
import java.util.Iterator;

public class TokenReader extends BufferedReaderWrapper implements Iterator<Token> {
    protected final CaptureStream captureStream;

    public TokenReader(String content) {
        this(new StringReader(content));
    }

    public TokenReader(Reader reader) {
        super(reader);
        this.captureStream = new CaptureStream();
        read();
    }

    public String getCaptured() {
        return captureStream.toString();
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

    private boolean isHexDigit() {
        return value >= '0' && value <= '9'
                || value >= 'a' && value <= 'f'
                || value >= 'A' && value <= 'F';
    }

    protected void readNumbers() {
        boolean decimal = false;
        boolean leadingZero = value == '0';
        captureStream.startCapture((char) value);
        do {
            read();
            if (isDigit()) {
                if (leadingZero)
                    throw new SyntaxException("Number should not has leading zero", getLocation());
                leadingZero = false;
                captureStream.capture((char) value);
            } else if (value == '.') {
                if (decimal)
                    throw new SyntaxException("Multi dot found", getLocation());
                decimal = true;
                leadingZero = false;
                captureStream.capture((char) value);
            } else
                break;
        } while (true);
    }

    protected void readString() {
        boolean closed = false;
        captureStream.startCapture();
        do {
            read();
            if (value == -1) {
                break;
            } else if (value == '"') {
                closed = true;
                break;
            } else if (value == '\\')
                captureStream.capture(readEscaped());
            else {
                if (value == '\t' || value == '\n' || value == '\r' || value == '\0')
                    throw new SyntaxException("Tab and break are not allowed in string", getLocation());
                captureStream.capture((char) value);
            }
        } while (true);
        if (!closed)
            throw new SyntaxException("String not closed:", getLocation());
    }

    protected char readEscaped() {
        read();
        switch (value) {
            case 'b':
                return '\b';
            case 't':
                return '\t';
            case 'n':
                return '\n';
            case 'r':
                return '\r';
            case 'f':
                return '\f';
            case '"':
            case '\\':
                return (char) value;
            case 'u':
                char[] unicode = new char[4];
                for (int i = 0; i < 4; i++) {
                    read();
                    if (value == -1 || !isHexDigit())
                        throw new SyntaxException("Expected unicode escaped string", getLocation());
                    unicode[i] = (char) value;
                }
                char v = (char) Integer.parseInt(new String(unicode), 16);
                return v;
            default:
                throw new SyntaxException("Invalid escape string found", getLocation());
        }
    }


    protected Location getLocation() {
        return new Location(line, offset, (char) value);
    }

    private static final char[] TRUE_TOKENS = "rue".toCharArray();
    private static final char[] FALSE_TOKENS = "alse".toCharArray();
    private static final char[] NULL_TOKENS = "ull".toCharArray();

    public Token nextSkipSpace() {
        Token token = this.next();
        if (token == Token.SPACE)
            return this.next();
        return token;
    }

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
