package com.riguz.forks.json;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import sun.security.pkcs.ParsingException;

import java.io.*;

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
    protected ByteOutputStream byteOutputStream;

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


    private boolean readBool(boolean isTrue) throws IOException {
        if (isTrue)
            read('r', 'u', 'e');
        else
            read('a', 'l', 's', 'e');
        return isTrue;
    }

    private void readNull() throws IOException {
        read('u', 'l', 'l');
    }

    private String readString() throws IOException {
        int start = index;
        byteOutputStream = new ByteOutputStream();
        readUntil((byte) '"');
        return byteOutputStream.toString();
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

    private int readUntil(byte expected) throws IOException {
        while (read()) {
            if (value == expected) {
                return index - 1;
            }
            byteOutputStream.write(value);
        }
        throw new ParsingException("Expected :" + (char) expected);
    }

    private void read(char... expectedChars) throws IOException {
        for (char expected : expectedChars) {
            if (!read() || value != expected)
                throw new ParsingException("Expected:" + expected);
        }
    }

    @Override
    public void close() throws IOException {
        if (inputStream != null)
            inputStream.close();
    }
}
