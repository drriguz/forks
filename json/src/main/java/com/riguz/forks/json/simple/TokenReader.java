package com.riguz.forks.json.simple;

import com.riguz.forks.json.Location;
import com.riguz.forks.json.SyntaxException;

import java.io.Reader;
import java.io.StringReader;
import java.util.Iterator;

public class TokenReader extends BufferedReaderWrapper implements Iterator<Token> {
    protected final CaptureStream captureStream;

    public TokenReader(int bufferSize, String content) {
        this(bufferSize, new StringReader(content));
    }

    public TokenReader(int bufferSize, Reader reader) {
        super(bufferSize, reader);
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
        captureStream.startCapture(); // digit or '-'
        if (value == '-') {
            captureStream.capture('-');
            read();
        }
        if (value == '0') {
            captureStream.capture('0');
            read();
        } else if (isDigit()) {
            readDigits();
        }
        if (value == '.') {
            captureStream.capture((char) value);
            read();
            if (isDigit())
                readDigits();
            else
                throw new SyntaxException("Expected digit after dot", getLocation());
        }
        if (value == 'e' || value == 'E') {
            captureStream.capture('e');
            read();
            if (value == '+' || value == '-') {
                captureStream.capture((char) value);
                read();
                if (isDigit())
                    readDigits();
                else
                    throw new SyntaxException("Expected exponent in number", getLocation());
            } else if (isDigit()) {
                readDigits();
            } else
                throw new SyntaxException("Expected exponent in number", getLocation());

        }
    }

    protected void readDigits() {
        do {
            captureStream.capture((char) value);
            read();
        } while (isDigit());
    }

    protected void eatString() {
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
            case '/':
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


    public Location getLocation() {
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
                eatString();
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
