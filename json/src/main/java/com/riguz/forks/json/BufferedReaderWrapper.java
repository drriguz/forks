package com.riguz.forks.json;

import sun.security.pkcs.ParsingException;

import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class BufferedReaderWrapper implements Closeable {
    protected static final int DEFAULT_BUFFER_SIZE = 1024;
    public static final char INVALID = (char) -1;

    protected Reader reader;
    protected final char[] buffer;
    protected int index;
    protected int size;
    protected int value;

    protected int offset;
    protected int line;

    public BufferedReaderWrapper() {
        this(DEFAULT_BUFFER_SIZE, null);
    }

    public BufferedReaderWrapper(String content) {
        this(new StringReader(content));
    }

    public BufferedReaderWrapper(Reader reader) {
        this(DEFAULT_BUFFER_SIZE, reader);
    }

    public BufferedReaderWrapper(int bufferSize, Reader reader) {
        this.buffer = new char[bufferSize];
        this.reader = reader;
        this.index = this.size = this.offset = 0;
        this.line = 1;
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

    public Location location() {
        return new Location(line, offset, (char) value);
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
        offset += 1;
        if (value == '\n')
            line += 1;
        return value;
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
