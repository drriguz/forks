package com.riguz.forks.json;

import org.junit.Test;

import java.io.IOException;

import static com.riguz.forks.json.Token.*;
import static junit.framework.TestCase.*;

public class TokenReaderTest {
    private void verify(String content, Token[] expected) {
        TokenReader reader = new TokenReader(content);
        for (int i = 0; i < expected.length; i++) {
            assertTrue(reader.hasNext());
            assertEquals(expected[i], reader.next());
        }
        assertFalse(reader.hasNext());
        assertNull(reader.next());
    }

    @Test
    public void trueLiteral() {
        String str = "true true truetrue     true";
        Token[] expected = {TRUE, SPACE, TRUE, SPACE, TRUE, TRUE, SPACE, TRUE};
        verify(str, expected);
    }

    @Test
    public void falseLiteral() {
        String str = "false false falsetruefalse     ";
        Token[] expected = {FALSE, SPACE, FALSE, SPACE, FALSE, TRUE, FALSE, SPACE};
        verify(str, expected);
    }

    @Test
    public void simpleObject() {
        String str = "{\"name\":\"riguz\"}";
        Token[] expected = {OBJECT_START, STRING, COLON, STRING, OBJECT_END};
        verify(str, expected);
    }

    @Test
    public void readTokens() throws IOException {
        TokenReader reader = new TokenReader("{}");
        assertTrue(reader.hasNext());
        assertEquals(OBJECT_START, reader.next());
        assertEquals(OBJECT_END, reader.next());
        assertEquals(null, reader.next());
        assertEquals(null, reader.next());
        assertFalse(reader.hasNext());
    }

    @Test
    public void tokenWithSpaces() {
        TokenReader reader = new TokenReader("{[123.45    \"abcde fg \", ,,] } ");
        assertTrue(reader.hasNext());
        Token[] expecteds = {OBJECT_START, ARRAY_START, NUMBER, SPACE, STRING, COMMA, SPACE, COMMA, COMMA, ARRAY_END, SPACE, OBJECT_END, SPACE};
        for (Token expected : expecteds) {
            assertEquals(expected, reader.next());
        }
        assertFalse(reader.hasNext());
        assertNull(reader.next());
    }

    @Test
    public void tokenWithEscapes() {
        verify("\"\\\"\"", new Token[]{STRING});
        verify("\"hello \\\" world \"", new Token[]{STRING});
    }
}
