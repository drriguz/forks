package com.riguz.forks.json;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.*;

public class JsonParser {
    protected static final int DEFAULT_BUFFER_SIZE = 1024;


    public JsonParser() {
        this(DEFAULT_BUFFER_SIZE);
    }

    public JsonParser(int bufferSize) {
    }

    public Object parse(String json) {
        return this.parse(new StringReader(json));
    }

    public Object parse(Reader input) {
        try (TokenReader reader = new TokenReader(input)) {
            Object value = readValue(reader);
            Token lastToken = reader.nextSkipSpace();
            if (lastToken != null)
                throw new SyntaxException("Invalid token found, expected EOF but got:" + lastToken.name(), lastToken, reader.getLocation());
            checkJsonValue(value);
            return value;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void checkJsonValue(Object value) {
        if (value != null) {
            if (!Map.class.isAssignableFrom(value.getClass()) && !List.class.isAssignableFrom(value.getClass()))
                throw new SyntaxException("Expected object or array", null);
        }
    }

    protected Object readValue(TokenReader reader) {
        return readValue(reader, null);
    }

    protected Object readValue(TokenReader reader, Token readed) {
        Token token = readed != null ? readed : reader.next();
        if (token == null)
            throw new SyntaxException("Expected value", token, reader.getLocation());
        switch (token) {
            case SPACE:
                return readValue(reader);
            case TRUE:
                return true;
            case FALSE:
                return false;
            case NULL:
                return null;
            case NUMBER:
                return Double.parseDouble(reader.getCaptured());
            case STRING:
                return reader.getCaptured();
            case OBJECT_START:
                return readObject(reader);
            case ARRAY_START:
                return readArray(reader);
            default:
                return null;
        }
    }

    protected List<Object> readArray(TokenReader reader) {
        List<Object> array = new LinkedList<>();
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
                    Object value = readValue(reader, token);
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

    protected Object readObject(TokenReader reader) {
        Map<String, Object> map = new LinkedHashMap<>();
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
            Object value = readValue(reader);
            token = reader.nextSkipSpace();
            map.put(name, value);
            hasContent = true;
        } while (token == Token.COMMA);

        if (token != Token.OBJECT_END)
            throw new SyntaxException("Expected object close", token, reader.getLocation());
        return map;
    }
}
