package com.riguz.forks.json;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import sun.security.pkcs.ParsingException;

import java.io.*;
import java.util.function.Predicate;

public class JsonParser implements Closeable {
    protected static final int DEFAULT_BUFFER_SIZE = 1024;
    protected InputStream inputStream;

    /*
                       bufferOffset
                       v
        #####@@@@@#####@@@@@#####
                       @@@@@#####?????
                       ^         ^
                     index      loadedSize
        size = 20
        bufferSize = 15
        Round1: loaded=15, bufferOffset=0
        Round2: loaded=10, bufferOffset=15
     */
    protected final byte[] buffer;
    protected int bufferOffset;
    protected int index;
    protected int loadedSize;
    protected byte value;
    protected ByteCapture capture = new ByteCapture();

    public JsonParser() {
        this(DEFAULT_BUFFER_SIZE);
    }

    public JsonParser(int bufferSize) {
        this.buffer = new byte[bufferSize];
    }

    public Object parse(String json) throws IOException {
        return this.parse(new ByteInputStream(json.getBytes(), json.getBytes().length));
    }

    private void reset() throws IOException {
        bufferOffset = 0;
        index = 0;
        loadedSize = 0;
        loadBuffer();
    }

    public Object parse(InputStream input) throws IOException {
        this.inputStream = input;
        reset();
        read();
        skipBlank();
        switch (value) {
            case 't':
                return readBool(true);
            case 'f':
                return readBool(false);
            case 'n':
                readNull();
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
                throw new ParsingException("Unexpected char:" + value);
        }
    }

    private Number readNumber() throws IOException {
        capture.startCapture();
        capture.capture(value);
        captureUntil(b -> (b >= '0' && b <= '9'));
        if (value == '.') {
            // float number
            capture.capture(value);
            captureUntil(b -> (b >= '0' && b <= '9'));
            return capture.toFloat();
        } else {
            return capture.toInt();
        }
    }

    private boolean readBool(boolean isTrue) throws IOException {
        if (isTrue)
            expect('r', 'u', 'e');
        else
            expect('a', 'l', 's', 'e');
        return isTrue;
    }

    private void readNull() throws IOException {
        expect('u', 'l', 'l');
    }

    private String readString() throws IOException {
        int start = index;
        capture.startCapture();
        captureUntil((byte) '"');

        return capture.toString();
    }

    private Object readObject() throws IOException {
        return null;
    }

    private Object readArray() throws IOException {
        return null;
    }

    private static final byte END = (byte) -1;

    private void skipBlank() throws IOException {
        while (isBlank())
            read();
    }

    private boolean isBlank() {
        return value == ' ' || value == '\n' || value == '\t' || value == '\r';
    }

    private boolean read() throws IOException {
        if (index > loadedSize || loadedSize < 0) {
            System.out.println("EOF" + index);
            return false;
        } else if (index == loadedSize) {
            System.out.println("load" + index);
            // 0, 0   reach end
            if (loadedSize == buffer.length && loadBuffer()) {
                value = buffer[index++];
                return true;
            }
            return false;
        } else {
            System.out.print(index + " " + (char) buffer[index] + "] ");
            value = buffer[index++];
            return true;
        }
    }

    private boolean loadBuffer() throws IOException {
        int existingSize = loadedSize;
        System.out.println("loading:" + bufferOffset);
        loadedSize = inputStream.read(buffer, 0, buffer.length);
        System.out.println("loaded:" + loadedSize);
        if (loadedSize == -1) {
            System.out.println("Not loaded");
            return false;
        }
        index = 0;
        bufferOffset += existingSize;
        return true;
    }

    private void captureUntil(byte expected) throws IOException {
        while (read()) {
            if (value == expected) {
                return;
            }
            capture.capture(value);
        }
        throw new ParsingException("Expected :" + (char) expected);
    }

    private void expect(char... expectedChars) throws IOException {
        for (char expected : expectedChars) {
            if (!read() || value != expected)
                throw new ParsingException("Expected:" + expected);
        }
    }

    private void captureUntil(Predicate<Byte> expected) throws IOException {
        while (read()) {
            if (!expected.test(value))
                return;
            capture.capture(value);
        }
    }

    @Override
    public void close() throws IOException {
        if (inputStream != null)
            inputStream.close();
    }
}
