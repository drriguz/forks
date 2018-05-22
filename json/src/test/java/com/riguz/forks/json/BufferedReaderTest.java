package com.riguz.forks.json;

import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import static junit.framework.TestCase.assertEquals;

public class BufferedReaderTest {
    @Test
    public void readEmpty() throws IOException {

        Reader input = new StringReader("");
        BufferedReaderWrapper reader = new BufferedReaderWrapper(128, input);
        assertEquals(-1, reader.read());
    }

    @Test
    public void readToEnd() throws IOException {

        for (int i = 0; i < 128; i++) {
            BufferedReaderWrapper reader = new BufferedReaderWrapper(4, new StringReader(str));
            verify(reader);
        }
    }

    final String str = "abcdabcdabcda";

    private void verify(BufferedReaderWrapper reader) throws IOException {
        for (int i = 0; i < str.length(); i++) {
            assertEquals(str.charAt(i), (char) reader.read());
        }
        for (int i = 0; i < 100; i++) {
            assertEquals(-1, reader.read());
        }
    }

    @Test
    public void skipWhiteSpace() throws IOException {
        final String str = " \t\nabc\t\t def     ";
        Reader input = new StringReader(str);
        BufferedReaderWrapper reader = new BufferedReaderWrapper(4, input);
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
