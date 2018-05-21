package com.riguz.forks.json;

import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import static junit.framework.TestCase.assertEquals;

public class BufferedReaderTest {
    @Test
    public void readEmpty() throws IOException {
        BufferedReaderWrapper reader = new BufferedReaderWrapper(128);
        Reader input = new StringReader("");
        reader.attach(input);
        assertEquals(-1, reader.read());
    }

    @Test
    public void readToEnd() throws IOException {
        for (int i = 0; i < 128; i++) {
            BufferedReaderWrapper reader = new BufferedReaderWrapper(4);
            verify(reader);
        }
    }

    private void verify(BufferedReaderWrapper reader) throws IOException {
        final String str = "abcdabcdabcda";
        Reader input = new StringReader(str);
        reader.attach(input);
        for (int i = 0; i < str.length(); i++) {
            assertEquals(str.charAt(i), (char) reader.read());
        }
        for (int i = 0; i < 100; i++) {
            assertEquals(-1, reader.read());
        }
    }

    @Test
    public void skipWhiteSpace() throws IOException {
        BufferedReaderWrapper reader = new BufferedReaderWrapper(4);
        final String str = " \t\nabc\t\t def     ";
        Reader input = new StringReader(str);
        reader.attach(input);
        reader.read();
        reader.skipWhiteSpace();
        assertEquals('a', (char) reader.getValue());
        assertEquals('b', (char) reader.read());
        assertEquals('c', (char) reader.read());
        assertEquals('\t', (char) reader.read());
        reader.skipWhiteSpace();
        assertEquals('d', (char) reader.getValue());
        reader.skipWhiteSpace();
        reader.skipWhiteSpace();
        reader.skipWhiteSpace();
        assertEquals('d', (char) reader.getValue());
    }
}
