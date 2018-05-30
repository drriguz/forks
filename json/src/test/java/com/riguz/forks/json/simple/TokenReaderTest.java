package com.riguz.forks.json.simple;

import com.riguz.forks.json.SyntaxException;
import com.riguz.forks.json.simple.Token;
import com.riguz.forks.json.simple.TokenReader;
import org.junit.Test;

import java.io.IOException;

import static com.riguz.forks.json.simple.Token.*;
import static junit.framework.TestCase.*;

public class TokenReaderTest {
    private void verify(String content, Token[] expected) {
        TokenReader reader = new TokenReader(1024, content);
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
        TokenReader reader = new TokenReader(1024, "{}");
        assertTrue(reader.hasNext());
        assertEquals(OBJECT_START, reader.next());
        assertEquals(OBJECT_END, reader.next());
        assertEquals(null, reader.next());
        assertEquals(null, reader.next());
        assertFalse(reader.hasNext());
    }

    @Test
    public void tokenWithSpaces() {
        TokenReader reader = new TokenReader(1024, "{[123.45    \"abcde fg \", ,,] } ");
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

    @Test
    public void tokenOfNumbers() {
        final Token[] tokens = {NUMBER, SPACE, NUMBER, SPACE, NUMBER};
        verify("0 1 12345", tokens);
        verify("0.0 0.1 0.123", tokens);
        verify("12.0 12.123 98912378.199912311312312123123", tokens);
        verify("1e1 1e0 0e0", tokens);
        verify("0e1 112e02 123e034", tokens);
        verify("0.0e1 112.1e02 123.123e034", tokens);
        verify("0.0e+1 112.1e-02 123.123e-034", tokens);
        verify("0e+1 112e+02 123e-034", tokens);
    }

    @Test(expected = SyntaxException.class)
    public void unclosedString() {
        TokenReader reader = new TokenReader(128, "\"Hello");
        reader.next();
    }
}
