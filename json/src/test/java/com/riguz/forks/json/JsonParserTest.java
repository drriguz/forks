package com.riguz.forks.json;

import org.junit.Test;

import static junit.framework.TestCase.*;

public class JsonParserTest {
    JsonParser parser;

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
        assertEquals(JsonArray.of(JsonLiteral.TRUE), parser.parse("[true]"));
        assertEquals(JsonArray.of(JsonLiteral.FALSE), parser.parse("[false]"));
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
        assertEquals(JsonLiteral.NULL, parser.parse("null"));
    }

    @Test
    public void readString() {
        assertEquals(JsonArray.of(""), parser.parse("[\"\"]"));
        assertEquals(JsonArray.of("Hello world!"), parser.parse("[\"Hello world!\"]"));
        assertEquals(JsonArray.of("Hello 中国!"), parser.parse("[\"Hello 中国!\"]"));
        assertEquals(JsonArray.of("Hello \n中国!"), parser.parse("[\"Hello \\n中国!\"]"));
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
        assertEquals(JsonArray.of("hello \" world "), parser.parse("[\"hello \\\" world \"]"));
        assertEquals(JsonArray.of("红尘の人"), parser.parse("[\"\\u7ea2\\u5c18\\u306e\\u4eba\"]"));
        assertEquals(JsonArray.of("\f\b\n\t\r   \r\r\r \"\"\"\"\\"), parser.parse("[\"\\f\\b\\n\\t\\r   \\r\\r\\r \\\"\\\"\\\"\\\"\\\\\"]"));
    }

    @Test
    public void readBoolWithBlank() {
        assertEquals(JsonArray.of(true), parser.parse("[  true]"));
        assertEquals(JsonArray.of(false), parser.parse("[false\n]"));
    }

    @Test
    public void readNumber() {
        assertEquals(JsonArray.of(0.0), parser.parse("[0]"));
        assertEquals(JsonArray.of(1.0), parser.parse("[1]"));
        assertEquals(JsonArray.of(123.0), parser.parse("[123]"));
        assertEquals(JsonArray.of(-123.0), parser.parse("[-123]"));
        assertEquals(JsonArray.of(123.98), parser.parse("[123.98]"));
        assertEquals(JsonArray.of(-123.98), parser.parse("[-123.98]"));
    }

    @Test
    public void readLargeNumber() {
        assertEquals(JsonArray.of(9007199254740992d), parser.parse("[9007199254740992.0]"));
    }

    @Test
    public void readEmptyObject() {
        JsonValue result = parser.parse("{}");
        assertTrue(result.isObject());
        assertEquals(0, result.asObject().getSize());
    }


    @Test
    public void readMinObject() {
        JsonValue result = parser.parse("{\"name\":\"riguz\"}");
        assertEquals("riguz", result.asObject().getString("name"));
    }

    @Test
    public void readObject() {
        JsonObject result = parser.parseObject("{\"name\":\"riguz\", \"age\": 18}");
        assertEquals(2, result.getSize());
        assertEquals("riguz", result.getString("name"));
        assertEquals(18.0, result.getNumber("age"));
    }

    @Test
    public void readNested() {
        JsonObject result =
                parser.parseObject("{  \n" +
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
        assertEquals(4, result.getSize());
        assertEquals(18.0, result.getNumber("age"));
        assertEquals("Male", result.getString("sex"));
        JsonValue name = result.get("name");
        assertTrue(name.isObject());
        assertEquals("riguz", name.asObject().getString("first"));
        assertEquals("lee", name.asObject().getString("last"));
        JsonObject address = result.get("address").asObject();
        assertEquals(2, address.getSize());
        assertEquals("hubei", address.getString("province"));
        assertEquals("wuhan", address.getString("city"));
    }

    @Test
    public void readEmptyArray() {
        JsonArray result = parser.parseArray("[]");
        assertEquals(0, result.getSize());
    }

    @Test
    public void readSimpleArray() {
        JsonArray array = parser.parseArray("[1, 2, 3]");
        assertEquals(JsonArray.of(1.0, 2.0, 3.0), array);
    }

    @Test
    public void readNestedArray() {
        JsonArray result = parser.parseArray("[[[[[[[[1], [2, 3], [4, 5, 6]]]]]]]]");
        assertEquals(1, result.getSize());
        result = result.get(0).asArray().get(0).asArray().get(0).asArray().get(0).asArray().get(0).asArray().get(0).asArray();

        assertEquals(3, result.getSize());
        assertEquals(JsonArray.of(1.0), result.get(0));
        assertEquals(JsonArray.of(2.0, 3.0), result.get(1));
        assertEquals(JsonArray.of(4.0, 5.0, 6.0), result.get(2));
    }
}
