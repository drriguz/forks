package com.riguz.forks.json;

import sun.security.pkcs.ParsingException;

import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;

public class BufferedReaderWrapper implements Closeable {
    protected static final int DEFAULT_BUFFER_SIZE = 1024;

    protected Reader reader;
    protected final char[] buffer;
    protected int index;
    protected int size;
    protected int value;

    public BufferedReaderWrapper() {
        this(DEFAULT_BUFFER_SIZE);
    }

    public BufferedReaderWrapper(int bufferSize) {
        this.buffer = new char[bufferSize];
    }

    public void attach(Reader reader) throws IOException {
        this.reader = reader;
        index = 0;
        size = 0;
        value = -1;
        loadBuffer();
    }

    public char getValue() {
        return (char) value;
    }

    public void skipWhiteSpace() throws IOException {
        while (this.isBlank())
            read();
    }

    private boolean isBlank() {
        return value == ' ' || value == '\n' || value == '\t' || value == '\r';
    }

    public int read() throws IOException {
        if (size > 0 && index < size)
            value = buffer[index];
        else if (index == size && loadBuffer())
            value = buffer[index];
        else
            value = -1;
        index += 1;
        return value;
    }

    public void readExpected(char... expected) throws IOException {
        for (int i = 1; i < expected.length; i++) {
            if (read() == -1 || value != expected[i])
                throw new ParsingException("Expected:" + expected[i] + " but got:" + (char) value);
        }
    }

    private boolean loadBuffer() throws IOException {
        size = reader.read(buffer, 0, buffer.length);
        if (size == -1) {
            return false;
        }
        index = 0;
        return true;
    }

    @Override
    public void close() throws IOException {
        if (reader != null)
            reader.close();
    }
}
