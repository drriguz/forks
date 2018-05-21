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
        this.input = new BufferedReaderWrapper(bufferSize);
        this.captureStream = new CaptureStream();
    }

    public Object parse(String json) throws IOException {
        return this.parse(new StringReader(json));
    }

    public Object parse(Reader reader) throws IOException {
        this.input.attach(reader);
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
                throw new ParsingException("Unexpected char:" + input.getValue());
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

    private Object readObject() throws IOException {
        Map<String, Object> result = new LinkedHashMap<>();
        do {
            if (!readNextToken())
                throw new ParsingException("Unclosed object");
            expect('"');
            String name = readString();
            if (!readNextToken())
                throw new ParsingException("Unclosed object");
            expect(':');
            Object value = readValue();
            result.put(name, value);
        } while (read(','));
        expect('}');
        return result;
    }

    private Object readArray() throws IOException {
        return null;
    }

    private boolean isBlank() {
        return false;
//        return value == ' ' || value == '\n' || value == '\t' || value == '\r';
    }

    private boolean readNextToken() throws IOException {
//        if (index == loadedSize) {
////            // 0, 0   reach end
////            if (loadedSize == buffer.length && loadBuffer()) {
////                value = buffer[index++];
////                return true;
////            }
////            return false;
////        } else {
////            do {
////                if (index > loadedSize || loadedSize < 0) {
////                    return false;
////                }
////                value = buffer[index++];
////            } while (isBlank());
////            return true;
////        }
        return false;
    }

    private void captureUntil(byte expected) throws IOException {
        captureUntil(i -> i != expected);
    }

    private void captureUntil(Predicate<Character> expected) throws IOException {
//        while (read()) {
//            if (!expected.test(value)) {
//                return;
//            }
//            captureStream.captureStream(value);
//        }
    }

    private boolean read(char expected) throws IOException {
//        read();
//        return value == expected;
        return false;
    }

    private void expect(char expected, char... rest) throws IOException {

    }


    @Override
    public void close() throws IOException {
        if (input != null)
            input.close();
    }
}
