package com.riguz.forks.json;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class JsonParser {
    protected static final int DEFAULT_BUFFER_SIZE = 1024;


    public JsonParser() {
        this(DEFAULT_BUFFER_SIZE);
    }

    public JsonParser(int bufferSize) {
    }

    public JsonValue parse(String json) {
        return this.parse(new StringReader(json));
    }

    public JsonObject parseObject(String json) {
        return this.parseObject(new StringReader(json));
    }

    public JsonArray parseArray(String json) {
        return this.parseArray(new StringReader(json));
    }

    public JsonObject parseObject(Reader input) {
        return parse(input).asObject();
    }

    public JsonArray parseArray(Reader input) {
        return parse(input).asArray();
    }

    public JsonValue parse(Reader input) {
        try (TokenReader reader = new TokenReader(input)) {
            JsonValue value = readValue(reader);
            Token lastToken = reader.nextSkipSpace();
            if (lastToken != null)
                throw new SyntaxException("Invalid token found, expected EOF but got:" + lastToken.name(), lastToken, reader.getLocation());
            checkJsonValue(value);
            return value;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void checkJsonValue(JsonValue value) {
        if (value == null)
            throw new RuntimeException("Expected parsed value not null");
        if (!(value instanceof JsonObject)
                && !(value instanceof JsonArray)
                && !value.isNull())
            throw new SyntaxException("Expected object or array but got：" + value);
    }

    protected JsonValue readValue(TokenReader reader) {
        return readValue(reader, null);
    }

    protected JsonValue readValue(TokenReader reader, Token readed) {
        Token token = readed != null ? readed : reader.nextSkipSpace();
        if (token == null)
            throw new SyntaxException("Expected value", token, reader.getLocation());
        switch (token) {
            case TRUE:
                return JsonLiteral.TRUE;
            case FALSE:
                return JsonLiteral.FALSE;
            case NULL:
                return JsonLiteral.NULL;
            case NUMBER:
                return new JsonNumber(Double.parseDouble(reader.getCaptured()));
            case STRING:
                return new JsonString(reader.getCaptured());
            case OBJECT_START:
                return readObject(reader);
            case ARRAY_START:
                return readArray(reader);
            default:
                return null;
        }
    }

    protected JsonArray readArray(TokenReader reader) {
        JsonArray array = new JsonArray();
        // []
        // [123]
        // [123, 234]
        Token token;
        boolean hasElements = false;
        do {
            token = reader.nextSkipSpace();
            switch (token) {
                case NUMBER:
                case STRING:
                case ARRAY_START:
                case OBJECT_START:
                case TRUE:
                case FALSE:
                case NULL:
                    JsonValue value = readValue(reader, token);
                    array.add(value);
                    token = reader.nextSkipSpace();
                    hasElements = true;
                    break;
                case ARRAY_END:
                    if (hasElements)
                        throw new SyntaxException("Expected array value after comma", reader.getLocation());
                    break;
                default:
                    throw new SyntaxException("Expected array value after comma", reader.getLocation());
            }
        } while (token == Token.COMMA);
        if (token != Token.ARRAY_END)
            throw new SyntaxException("Expected array value", reader.getLocation());
        return array;
    }

    protected JsonObject readObject(TokenReader reader) {
        JsonObject obj = new JsonObject();
        Token token;
        boolean hasContent = false;
        do {
            token = reader.nextSkipSpace();
            if (token != Token.STRING) {
                if (hasContent)
                    throw new SyntaxException("Expected object property", reader.getLocation());
                break;
            }
            String name = reader.getCaptured();
            token = reader.nextSkipSpace();
            if (token != Token.COLON)
                throw new SyntaxException("Expected ':'", token, reader.getLocation());
            JsonValue value = readValue(reader);
            token = reader.nextSkipSpace();
            obj.set(name, value);
            hasContent = true;
        } while (token == Token.COMMA);

        if (token != Token.OBJECT_END)
            throw new SyntaxException("Expected object close", token, reader.getLocation());
        return obj;
    }
}
