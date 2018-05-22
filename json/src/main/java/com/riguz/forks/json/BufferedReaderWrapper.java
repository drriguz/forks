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
        this(DEFAULT_BUFFER_SIZE, null);
    }

    public BufferedReaderWrapper(Reader reader) {
        this(DEFAULT_BUFFER_SIZE, reader);
    }

    public BufferedReaderWrapper(int bufferSize, Reader reader) {
        this.buffer = new char[bufferSize];
        this.reader = reader;
        this.index = 0;
        this.size = 0;
        this.value = -1;
        loadBuffer();
    }

    public char getValue() {
        return (char) value;
    }

    public void skipWhiteSpace() {
        while (this.isBlank())
            read();
    }

    private boolean isBlank() {
        return value == ' ' || value == '\n' || value == '\t' || value == '\r';
    }

    public int read() {
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

    private boolean loadBuffer() {
        try {
            size = reader.read(buffer, 0, buffer.length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (size == -1)
            return false;

        index = 0;
        return true;
    }

    @Override
    public void close() throws IOException {
        if (reader != null)
            reader.close();
    }
}
