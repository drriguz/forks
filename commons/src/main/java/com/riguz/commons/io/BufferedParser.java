package com.riguz.commons.io;

import java.io.IOException;
import java.io.Reader;

public abstract class BufferedParser {

    protected Reader reader;
    protected final int bufferSize;
    protected final char[] buffer;

    protected int bufferOffset;
    protected int currentIndex;
    protected int loadedBufferSize;
    protected int lineNumber;
    protected int currentValue;

    protected StringBuilder captureBuffer;
    protected int captureOffset;

    public BufferedParser(int bufferSize) {
        if (bufferSize <= 0) {
            throw new IllegalArgumentException("Invalid buffer size");
        }
        this.bufferSize = bufferSize;
        this.buffer = new char[this.bufferSize];
    }

    public void parse(Reader reader) throws IOException {
        if (reader == null) {
            throw new IllegalArgumentException("Reader must not be null");
        }
        this.reader = reader;
        this.bufferOffset = 0;
        this.currentIndex = 0;
        this.currentValue = -1;
        this.lineNumber = 1;

        this.read();
        this.skipWhiteSpace();
        this.readValue();
        this.skipWhiteSpace();
        if (!this.isEndOfText()) {
            this.error("Unexpected character");
        }
    }

    private void error(String msg) {
        int lineOffset = this.bufferOffset + this.currentIndex;
        String location = " at " + this.lineNumber + ":" + lineOffset;
        throw new ParserException(msg + location);
    }

    protected abstract void readValue() throws IOException;

    protected boolean read() throws IOException {
        if (this.loadedBufferSize == -1) {
            return false;
        }
        if (this.currentIndex == this.loadedBufferSize) {
            this.bufferOffset += this.loadedBufferSize;
            this.loadedBufferSize = this.reader.read(this.buffer, 0, this.bufferSize);
            this.currentIndex = 0;
            if (this.loadedBufferSize == -1) {
                // reaches end
                this.currentValue = -1;
                return false;
            }
        }
        if (this.currentValue == '\n') {
            this.lineNumber++;
        }
        this.currentValue = this.buffer[this.currentIndex++];
        return true;
    }

    protected boolean readChar(char ch) throws IOException {
        if (this.currentValue != ch) {
            return false;
        }
        read();
        return true;
    }

    protected void readRequiredChar(char ch) throws IOException {
        if (!readChar(ch)) {
            this.error("Expected:" + ch);
        }
    }

    protected void startCapture() {
        if (this.captureBuffer == null) {
            this.captureBuffer = new StringBuilder();
        }
        this.captureOffset = this.currentIndex - 1;
    }

    protected void pauseCapture() {
        int end = isEndOfText() ? this.currentIndex : this.currentIndex - 1;
        this.captureBuffer.append(this.buffer, this.captureOffset, end - this.captureOffset);
        this.captureOffset = -1;
    }

    protected String endCapture() {
        int start = this.captureOffset;
        int end = this.currentIndex - 1;
        String captured = new String(this.buffer, start, end - start);
        if (this.captureBuffer.length() > 0) {
            this.captureBuffer.append(captured);
            captured = this.captureBuffer.toString();
            this.captureBuffer.setLength(0);
        }
        return captured;
    }

    protected void skipWhiteSpace() throws IOException {
        while (isWhiteSpace()) {
            read();
        }
    }

    protected boolean isWhiteSpace() {
        return this.currentValue == ' '
            || this.currentValue == '\t'
            || this.currentValue == '\n'
            || this.currentValue == '\r';
    }

    protected boolean isDigit() {
        return this.currentValue >= '0' && this.currentValue <= '9';
    }

    protected boolean isHexDigit() {
        return this.currentValue >= '0' && this.currentValue <= '9'
            || this.currentValue >= 'a' && this.currentValue <= 'f'
            || this.currentValue >= 'A' && this.currentValue <= 'F';
    }

    protected boolean isEndOfText() {
        return this.loadedBufferSize == -1;
    }

    static class ParserException extends RuntimeException {

        public ParserException(String message) {
            super(message);
        }
    }
}
