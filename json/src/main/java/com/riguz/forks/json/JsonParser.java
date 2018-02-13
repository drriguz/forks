/*******************************************************************************
 * Copyright (c) 2013, 2016 EclipseSource.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/
package com.riguz.forks.json;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class JsonParser {

    private static final int MAX_NESTING_LEVEL = 1000;
    private static final int MIN_BUFFER_SIZE = 10;
    private static final int DEFAULT_BUFFER_SIZE = 1024;

    private final JsonHandler<Object, Object> handler;
    private Reader reader;
    private char[] buffer;
    private int bufferOffset;
    private int currentIndexOfBuffer;
    private int bufferActualLength;
    private int line;
    private int lineOffset;
    private int currentValue;
    private StringBuilder captureBuffer;
    private int captureStartIndexOfBuffer;
    private int nestingLevel;

    /*
     * |                      bufferOffset
     *                        v
     * [a|b|c|d|e|f|g|h|i|j|k|l|m|n|o|p|q|r|s|t]        < input
     *                       [l|m|n|o|p|q|r|s|t|?|?]    < buffer
     *                          ^               ^
     *                       |  currentIndexOfBuffer           bufferActualLength
     */
    @SuppressWarnings("unchecked")
    public JsonParser(JsonHandler<?, ?> handler) {
        if (handler == null) {
            throw new NullPointerException("handler is null");
        }
        this.handler = (JsonHandler<Object, Object>) handler;
        handler.parser = this;
    }

    public void parse(String string) {
        if (string == null) {
            throw new NullPointerException("string is null");
        }
        int bufferSize = Math.max(MIN_BUFFER_SIZE, Math.min(DEFAULT_BUFFER_SIZE, string.length()));
        try {
            parse(new StringReader(string), bufferSize);
        } catch (IOException exception) {
            // StringReader does not throw IOException
            throw new RuntimeException(exception);
        }
    }

    public void parse(Reader reader) throws IOException {
        parse(reader, DEFAULT_BUFFER_SIZE);
    }

    public void parse(Reader reader, int bufferSize) throws IOException {
        if (reader == null) {
            throw new NullPointerException("reader is null");
        }
        if (bufferSize <= 0) {
            throw new IllegalArgumentException("buffer size is zero or negative");
        }
        this.reader = reader;
        buffer = new char[bufferSize];
        bufferOffset = 0;
        currentIndexOfBuffer = 0;
        bufferActualLength = 0;
        line = 1;
        lineOffset = 0;
        currentValue = 0;
        captureStartIndexOfBuffer = -1;
        read();
        skipWhiteSpace();
        readValue();
        skipWhiteSpace();
        if (!isEndOfText()) {
            throw error("Unexpected character");
        }
    }

    private void readValue() throws IOException {
        switch (currentValue) {
            case 'n':
                readNull();
                break;
            case 't':
                readTrue();
                break;
            case 'f':
                readFalse();
                break;
            case '"':
                readString();
                break;
            case '[':
                readArray();
                break;
            case '{':
                readObject();
                break;
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
                readNumber();
                break;
            default:
                throw expected("value");
        }
    }

    private void readArray() throws IOException {
        Object array = handler.startArray();
        read();
        if (++nestingLevel > MAX_NESTING_LEVEL) {
            throw error("Nesting too deep");
        }
        skipWhiteSpace();
        if (readChar(']')) {
            nestingLevel--;
            handler.endArray(array);
            return;
        }
        do {
            skipWhiteSpace();
            handler.startArrayValue(array);
            readValue();
            handler.endArrayValue(array);
            skipWhiteSpace();
        } while (readChar(','));
        if (!readChar(']')) {
            throw expected("',' or ']'");
        }
        nestingLevel--;
        handler.endArray(array);
    }

    private void readObject() throws IOException {
        Object object = handler.startObject();
        read();
        if (++nestingLevel > MAX_NESTING_LEVEL) {
            throw error("Nesting too deep");
        }
        skipWhiteSpace();
        if (readChar('}')) {
            nestingLevel--;
            handler.endObject(object);
            return;
        }
        do {
            skipWhiteSpace();
            handler.startObjectName(object);
            String name = readName();
            handler.endObjectName(object, name);
            skipWhiteSpace();
            if (!readChar(':')) {
                throw expected("':'");
            }
            skipWhiteSpace();
            handler.startObjectValue(object, name);
            readValue();
            handler.endObjectValue(object, name);
            skipWhiteSpace();
        } while (readChar(','));
        if (!readChar('}')) {
            throw expected("',' or '}'");
        }
        nestingLevel--;
        handler.endObject(object);
    }

    private String readName() throws IOException {
        if (currentValue != '"') {
            throw expected("name");
        }
        return readStringInternal();
    }

    private void readNull() throws IOException {
        handler.startNull();
        read();
        readRequiredChar('u');
        readRequiredChar('l');
        readRequiredChar('l');
        handler.endNull();
    }

    private void readTrue() throws IOException {
        handler.startBoolean();
        read();
        readRequiredChar('r');
        readRequiredChar('u');
        readRequiredChar('e');
        handler.endBoolean(true);
    }

    private void readFalse() throws IOException {
        handler.startBoolean();
        read();
        readRequiredChar('a');
        readRequiredChar('l');
        readRequiredChar('s');
        readRequiredChar('e');
        handler.endBoolean(false);
    }

    private void readRequiredChar(char ch) throws IOException {
        if (!readChar(ch)) {
            throw expected("'" + ch + "'");
        }
    }

    private void readString() throws IOException {
        handler.startString();
        handler.endString(readStringInternal());
    }

    private String readStringInternal() throws IOException {
        read();
        startCapture();
        while (currentValue != '"') {
            if (currentValue == '\\') {
                pauseCapture();
                readEscape();
                startCapture();
            } else if (currentValue < 0x20) {
                throw expected("valid string character");
            } else {
                read();
            }
        }
        String string = endCapture();
        read();
        return string;
    }

    private void readEscape() throws IOException {
        read();
        switch (currentValue) {
            case '"':
            case '/':
            case '\\':
                captureBuffer.append((char) currentValue);
                break;
            case 'b':
                captureBuffer.append('\b');
                break;
            case 'f':
                captureBuffer.append('\f');
                break;
            case 'n':
                captureBuffer.append('\n');
                break;
            case 'r':
                captureBuffer.append('\r');
                break;
            case 't':
                captureBuffer.append('\t');
                break;
            case 'u':
                char[] hexChars = new char[4];
                for (int i = 0; i < 4; i++) {
                    read();
                    if (!isHexDigit()) {
                        throw expected("hexadecimal digit");
                    }
                    hexChars[i] = (char) currentValue;
                }
                captureBuffer.append((char) Integer.parseInt(new String(hexChars), 16));
                break;
            default:
                throw expected("valid escape sequence");
        }
        read();
    }

    private void readNumber() throws IOException {
        handler.startNumber();
        startCapture();
        readChar('-');
        int firstDigit = currentValue;
        if (!readDigit()) {
            throw expected("digit");
        }
        if (firstDigit != '0') {
            while (readDigit()) {
            }
        }
        readFraction();
        readExponent();
        handler.endNumber(endCapture());
    }

    private boolean readFraction() throws IOException {
        if (!readChar('.')) {
            return false;
        }
        if (!readDigit()) {
            throw expected("digit");
        }
        while (readDigit()) {
        }
        return true;
    }

    private boolean readExponent() throws IOException {
        if (!readChar('e') && !readChar('E')) {
            return false;
        }
        if (!readChar('+')) {
            readChar('-');
        }
        if (!readDigit()) {
            throw expected("digit");
        }
        while (readDigit()) {
        }
        return true;
    }

    private boolean readChar(char ch) throws IOException {
        if (currentValue != ch) {
            return false;
        }
        read();
        return true;
    }

    private boolean readDigit() throws IOException {
        if (!isDigit()) {
            return false;
        }
        read();
        return true;
    }

    private void skipWhiteSpace() throws IOException {
        while (isWhiteSpace()) {
            read();
        }
    }

    private void read() throws IOException {
        if (currentIndexOfBuffer == bufferActualLength) {
            if (captureStartIndexOfBuffer != -1) {
                captureBuffer.append(buffer, captureStartIndexOfBuffer, bufferActualLength - captureStartIndexOfBuffer);
                captureStartIndexOfBuffer = 0;
            }
            bufferOffset += bufferActualLength;
            bufferActualLength = reader.read(buffer, 0, buffer.length);
            currentIndexOfBuffer = 0;
            if (bufferActualLength == -1) {
                // no more data
                currentValue = -1;
                currentIndexOfBuffer++;
                return;
            }
        }
        if (currentValue == '\n') {
            line++;
            lineOffset = bufferOffset + currentIndexOfBuffer;
        }
        currentValue = buffer[currentIndexOfBuffer++];
    }

    private void startCapture() {
        if (captureBuffer == null) {
            captureBuffer = new StringBuilder();
        }
        captureStartIndexOfBuffer = currentIndexOfBuffer - 1;
    }

    private void pauseCapture() {
        int end = currentValue == -1 ? currentIndexOfBuffer : currentIndexOfBuffer - 1;
        captureBuffer.append(buffer, captureStartIndexOfBuffer, end - captureStartIndexOfBuffer);
        captureStartIndexOfBuffer = -1;
    }

    private String endCapture() {
        int start = captureStartIndexOfBuffer;
        int end = currentIndexOfBuffer - 1;
        captureStartIndexOfBuffer = -1;
        if (captureBuffer.length() > 0) {
            captureBuffer.append(buffer, start, end - start);
            String captured = captureBuffer.toString();
            captureBuffer.setLength(0);
            return captured;
        }
        return new String(buffer, start, end - start);
    }

    Location getLocation() {
        int offset = bufferOffset + currentIndexOfBuffer - 1;
        int column = offset - lineOffset + 1;
        return new Location(offset, line, column);
    }

    private ParseException expected(String expected) {
        if (isEndOfText()) {
            return error("Unexpected end of input");
        }
        return error("Expected " + expected);
    }

    private ParseException error(String message) {
        return new ParseException(message, getLocation());
    }

    private boolean isWhiteSpace() {
        return currentValue == ' ' || currentValue == '\t' || currentValue == '\n' || currentValue == '\r';
    }

    private boolean isDigit() {
        return currentValue >= '0' && currentValue <= '9';
    }

    private boolean isHexDigit() {
        return currentValue >= '0' && currentValue <= '9'
            || currentValue >= 'a' && currentValue <= 'f'
            || currentValue >= 'A' && currentValue <= 'F';
    }

    private boolean isEndOfText() {
        return currentValue == -1;
    }

}
