package com.riguz.forks.json;

import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import static com.riguz.forks.json.Token.*;
import static junit.framework.TestCase.*;

public class TokenReaderTest {
    @Test
    public void sameTokens() {
        String[] strs = {
                "truetruetrue true    true true  ",
                " falsefalse false    false false",
                "nullnullnull nullnull null null   null  ",
                "12345 12345 12.3 123.45 12344.09 ",
                "\"abc\" \"\" \"abc\""
        };
        Token[] expected = {
                TRUE,
                FALSE,
                NULL,
                NUMBER,
                STRING
        };
        for (int i = 0; i < 3; i++) {
            TokenReader reader = new TokenReader(strs[i]);
            while (reader.hasNext())
                assertEquals(expected[i], reader.next());
        }
    }

    @Test
    public void readTokens() throws IOException {
        TokenReader reader = new TokenReader("{}");

        assertTrue(reader.hasNext());
        assertEquals(OBJECT_START, reader.next());
        assertEquals(OBJECT_END, reader.next());
        assertEquals(null, reader.next());
        assertFalse(reader.hasNext());
    }

    @Test
    public void tokenWithSpaces() {
        TokenReader reader = new TokenReader("{[123.45    \"abcde fg \", ,,] } ");
        assertTrue(reader.hasNext());
        Token[] expecteds = {OBJECT_START, ARRAY_STSRT, NUMBER, STRING, COMMA, COMMA, COMMA, ARRAY_END, OBJECT_END};
        for (Token expected : expecteds) {
            assertEquals(expected, reader.next());
        }
        assertFalse(reader.hasNext());
        assertNull(reader.next());
    }
}
