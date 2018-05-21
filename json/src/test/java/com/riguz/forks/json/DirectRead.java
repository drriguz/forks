package com.riguz.forks.json;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import sun.security.pkcs.ParsingException;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;

//@RunWith(Parameterized.class)
public class DirectRead {
    JsonParser parser;

    /*
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
    */
    public DirectRead() {
        this.parser = new JsonParser();
    }

    @Test
    public void readEmpty() throws IOException {
        try {
            parser.parse("");
            fail();
        } catch (ParsingException ex) {

        }
    }

    @Test
    public void readBool() throws IOException {
        assertEquals(true, parser.parse("true"));
        assertEquals(false, parser.parse("false"));
    }

    @Test
    public void readUncompletedBool() throws IOException {
        try {
            parser.parse("trust");
            parser.parse("tru");
            parser.parse("trum");
            parser.parse("ts");
            parser.parse("t rue");
            fail();
        } catch (ParsingException e) {

        }
    }

    @Test
    public void readNull() throws IOException {
        assertEquals(null, parser.parse("null"));
    }

    @Test
    public void readString() throws IOException {
        assertEquals("", parser.parse("\"\""));
        assertEquals("Hello world!", parser.parse("\"Hello world!\""));
        assertEquals("Hello 中国!", parser.parse("\"Hello 中国!\""));
        assertEquals("Hello \n中国!", parser.parse("\"Hello \n中国!\""));
    }

    @Test
    public void readUnclosedString() throws IOException {
        try {
            parser.parse("\"Hello");
            fail();
        } catch (ParsingException e) {

        }
    }

    @Test
    public void readEscapedString() throws IOException {
        assertEquals("Hello \"World", parser.parse("\"Hello \"World\""));
    }

    @Test
    public void readBoolWithBlank() throws IOException {
        assertEquals(true, parser.parse("  true"));
        assertEquals(false, parser.parse("false\n"));
    }

    @Test
    public void readNumber() throws IOException {
        assertEquals(0.0, parser.parse("0"));
        assertEquals(1.0, parser.parse("1"));
        assertEquals(123.0, parser.parse("123"));
        assertEquals(-123.0, parser.parse("-123"));
        assertEquals(123.98, parser.parse("123.98"));
        assertEquals(-123.98, parser.parse("-123.98"));
    }

    @Test
    public void readLargeNumber() throws IOException {
        assertEquals(9007199254740992d, parser.parse("9007199254740992.0"));
    }

    @Test
    public void readObject() throws IOException {
        Map<String, Object> result = (Map<String, Object>) parser.parse("{\"name\":\"riguz\"}");
        assertEquals(1, result.size());
        assertEquals("riguz", (String) result.get("name"));
        result = (Map<String, Object>) parser.parse("{\"name\":\"riguz\", \"age\": 18}");
        assertEquals(2, result.size());
        assertEquals("riguz", (String) result.get("name"));
        assertEquals(18, result.get("age"));
    }

    @Test
    public void readNested() throws IOException {
        Map<String, Object> result = (Map<String, Object>)
                parser.parse("{  \n" +
                        "   \"name\":{  \n" +
                        "      \"first\":\"riguz\",\n" +
                        "      \"last\":\"lee\"\n" +
                        "   },\n" +
                        "   \"age\":18,\n" +
                        "   \"sex\":\"Male\",\n" +
                        "   \"address\":{  \n" +
                        "      \"province\":\"hubei\",\n" +
                        "      \"city\":\"wuhan\"\n" +
                        "   }\n" +
                        "}");
        assertEquals(3, result.size());
        assertEquals(18, result.get("age"));
    }
}
