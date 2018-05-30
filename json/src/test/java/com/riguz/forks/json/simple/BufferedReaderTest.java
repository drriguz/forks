package com.riguz.forks.json.simple;

import com.riguz.forks.json.Location;
import com.riguz.forks.json.simple.BufferedReaderWrapper;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import static com.riguz.forks.json.simple.BufferedReaderWrapper.INVALID;
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

    @Test
    public void reportLocation() {
        final String str = " \n \n";
        BufferedReaderWrapper reader = new BufferedReaderWrapper(128, str);
        TestCase.assertEquals(new Location(1, 0, INVALID), reader.location());
        reader.read();
        assertEquals(new Location(1, 1, ' '), reader.location());
        reader.read();
        assertEquals(new Location(2, 2, '\n'), reader.location());
        reader.read();
        reader.read();
        assertEquals(new Location(3, 4, '\n'), reader.location());
        reader.read();
        assertEquals(new Location(3, 5, INVALID), reader.location());
        reader.read();
        assertEquals(new Location(3, 6, INVALID), reader.location());
    }
}
