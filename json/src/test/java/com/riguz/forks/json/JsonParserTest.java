package com.riguz.forks.json;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

//@RunWith(Parameterized.class)
public class JsonParserTest {
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

    public JsonParserTest(JsonParser parser) {
        this.parser = parser;
    }
    */
    public JsonParserTest() {
        this.parser = new JsonParser();
    }

    @Test
    public void readEmpty() {
        try {
            parser.parse("");
            fail();
        } catch (SyntaxException e) {

        }
    }

    @Test
    public void readBool() {
        assertEquals(true, parser.parse("true"));
        assertEquals(false, parser.parse("false"));
    }

    @Test
    public void readUncompletedBool() {
        try {
            parser.parse("trust");
            parser.parse("tru");
            parser.parse("trum");
            parser.parse("ts");
            parser.parse("t rue");
            fail();
        } catch (SyntaxException e) {

        }
    }

    @Test
    public void readNull() {
        assertEquals(null, parser.parse("null"));
    }

    @Test
    public void readString() {
        assertEquals("", parser.parse("\"\""));
        assertEquals("Hello world!", parser.parse("\"Hello world!\""));
        assertEquals("Hello 中国!", parser.parse("\"Hello 中国!\""));
        assertEquals("Hello \n中国!", parser.parse("\"Hello \n中国!\""));
    }

    @Test
    public void readUnclosedString() {

        try {
            parser.parse("\"Hello");
            fail();
        } catch (SyntaxException e) {

        }
    }

    @Test
    public void readEscapedString() {
        assertEquals("hello \" world ", parser.parse("\"hello \\\" world \""));
        assertEquals("红尘の人", parser.parse("\"\\u7ea2\\u5c18\\u306e\\u4eba\""));
        assertEquals("\f\b\n\t\r   \r\r\r \"\"\"\"\\", parser.parse("\"\\f\\b\\n\\t\\r   \\r\\r\\r \\\"\\\"\\\"\\\"\\\\\""));
    }

    @Test
    public void readBoolWithBlank() {
        assertEquals(true, parser.parse("  true"));
        assertEquals(false, parser.parse("false\n"));
    }

    @Test
    public void readNumber() {
        assertEquals(0.0, parser.parse("0"));
        assertEquals(1.0, parser.parse("1"));
        assertEquals(123.0, parser.parse("123"));
        assertEquals(-123.0, parser.parse("-123"));
        assertEquals(123.98, parser.parse("123.98"));
        assertEquals(-123.98, parser.parse("-123.98"));
    }

    @Test
    public void readLargeNumber() {
        assertEquals(9007199254740992d, parser.parse("9007199254740992.0"));
    }

    @Test
    public void readEmptyObject() {
        Map<String, Object> result = (Map<String, Object>) parser.parse("{}");
        assertEquals(0, result.size());
    }


    @Test
    public void readMinObject() {
        Map<String, Object> result = (Map<String, Object>) parser.parse("{\"name\":\"riguz\"}");
        assertEquals(1, result.size());
        assertEquals("riguz", (String) result.get("name"));
    }

    @Test
    public void readObject() {
        Map<String, Object> result = (Map<String, Object>) parser.parse("{\"name\":\"riguz\", \"age\": 18}");
        assertEquals(2, result.size());
        assertEquals("riguz", (String) result.get("name"));
        assertEquals(18.0, result.get("age"));
    }

    @Test
    public void readNested() {
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
        assertEquals(4, result.size());
        assertEquals(18.0, result.get("age"));
        assertEquals("Male", result.get("sex"));
        Map<String, Object> name = (Map<String, Object>) result.get("name");
        assertEquals(2, name.size());
        assertEquals("riguz", name.get("first"));
        assertEquals("lee", name.get("last"));
        Map<String, Object> address = (Map<String, Object>) result.get("address");
        assertEquals(2, name.size());
        assertEquals("hubei", address.get("province"));
        assertEquals("wuhan", address.get("city"));
    }

    @Test
    public void readEmptyArray() {
        List<Object> result = (List<Object>) parser.parse("[]");
        assertEquals(0, result.size());
    }

    @Test
    public void readSimpleArray() {
        List<Object> result = (List<Object>) parser.parse("[1, 2, 3]");
        assertEquals(3, result.size());
        assertEquals(1.0, result.get(0));
        assertEquals(2.0, result.get(1));
        assertEquals(3.0, result.get(2));
    }

    @Test
    public void readNestedArray() {
        List<Object> result = (List<Object>) parser.parse("[[[[[[[[1], [2, 3], [4, 5, 6]]]]]]]]");
        assertEquals(1, result.size());
        result = (List<Object>) result.get(0);
        result = (List<Object>) result.get(0);
        result = (List<Object>) result.get(0);
        result = (List<Object>) result.get(0);
        result = (List<Object>) result.get(0);
        result = (List<Object>) result.get(0);
        assertEquals(3, result.size());
        assertEquals(1, ((List<Object>) result.get(0)).size());
        assertEquals(2, ((List<Object>) result.get(1)).size());
        assertEquals(3, ((List<Object>) result.get(2)).size());
    }
}
