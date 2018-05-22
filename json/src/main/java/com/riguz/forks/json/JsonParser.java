package com.riguz.forks.json;

import sun.security.pkcs.ParsingException;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public class JsonParser implements Closeable {
    protected static final int DEFAULT_BUFFER_SIZE = 1024;
    private static final char[] TRUE_TOKENS = "true".toCharArray();
    private static final char[] FALSE_TOKENS = "false".toCharArray();
    private static final char[] NULL_TOKENS = "null".toCharArray();

    protected final BufferedReaderWrapper input;
    protected final CaptureStream captureStream;

    public JsonParser() {
        this(DEFAULT_BUFFER_SIZE);
    }

    public JsonParser(int bufferSize) {
        this.input = new BufferedReaderWrapper(bufferSize, null);
        this.captureStream = new CaptureStream();
    }

    public Object parse(String json) throws IOException {
        return this.parse(new StringReader(json));
    }

    public Object parse(Reader reader) throws IOException {
        //this.input.attach(reader);
        if (this.input.read() == -1)
            throw new ParsingException("Unexpected EOL found");
        return readValue();
    }

    private Object readValue() throws IOException {
        input.skipWhiteSpace();
        switch (input.getValue()) {
            case 't':
                input.readExpected(TRUE_TOKENS);
                return true;
            case 'f':
                input.readExpected(FALSE_TOKENS);
                return false;
            case 'n':
                input.readExpected(NULL_TOKENS);
                return null;
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
                return readNumber();
            case '"':
                return readString();
            case '{':
                return readObject();
            case '[':
                return readArray();
            default:
                throw new ParsingException("Expected value but got unexpected char:" + input.getValue());
        }
    }

    private boolean isDigit() {
        return input.getValue() >= '0' && input.getValue() <= '9';
    }

    private double readNumber() throws IOException {
        captureStream.startCapture(input.getValue());
        boolean foundDot = false;
        do {
            if (input.read() == -1 || (!isDigit() && input.getValue() != '.'))
                break;
            if (input.getValue() == '.') {
                if (foundDot)
                    throw new ParsingException("Invalid number format found: multi-dot");
                foundDot = true;
            }
            captureStream.capture(input.getValue());
        } while (true);
        return Double.parseDouble(captureStream.toString());
    }

    private String readString() throws IOException {
        captureStream.startCapture();
        boolean closed = false;
        do {
            if (input.read() == -1)
                break;
            if (input.getValue() == '"') {
                closed = true;
                break;
            }
            captureStream.capture(input.getValue());
        } while (true);
        if (!closed)
            throw new ParsingException("Unclosed string found");
        return captureStream.toString();
    }

    private void readNext() throws IOException {
        input.read();
        input.skipWhiteSpace();
    }

    private Object readObject() throws IOException {
        Map<String, Object> result = new LinkedHashMap<>();
        do {
            readNext();
            if (input.getValue() == '"') {
                String name = readString();
                readNext();
                if (input.getValue() != ':')
                    throw new ParsingException("Expected : after property name");
                readNext();
                Object value = readValue();
                result.put(name, value);
            }
        } while (input.getValue() == ',');
        readNext();

        return result;
    }

    private Object readArray() throws IOException {
        return null;
    }

    @Override
    public void close() throws IOException {
        if (input != null)
            input.close();
    }
}
