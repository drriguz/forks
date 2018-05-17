package com.riguz.forks.json;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

@RunWith(Parameterized.class)
public class DirectRead {
    JsonParser parser;

    @Parameterized.Parameters
    public static List<JsonParser> getParserImpl() {
        return Arrays.asList(new JsonParser(),
                new JsonParser(3),
                new JsonParser(5),
                new JsonParser(15),
                new JsonParser(1024));
    }

    public DirectRead(JsonParser parser) {
        this.parser = parser;
    }

    @Test
    public void readBool() throws IOException {
        assertEquals(true, parser.parse("true"));
        assertEquals(false, parser.parse("false"));
    }

    @Test
    public void readNull() throws IOException {
        assertEquals(null, parser.parse("null"));
    }

    @Test
    public void readString() throws IOException {
        assertEquals("Hello world!", parser.parse("\"Hello world!\""));
    }

    @Test
    public void readBoolWithBlank() throws IOException {
        assertEquals(true, parser.parse("  true"));
        assertEquals(false, parser.parse("false\n"));
    }

}
