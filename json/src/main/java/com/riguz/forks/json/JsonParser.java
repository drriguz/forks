package com.riguz.forks.json;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.LinkedHashMap;
import java.util.Map;

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
            return value;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected Object readValue(TokenReader reader) {
        Token token = reader.next();
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
            default:
                return null;
        }
    }

    protected Object readObject(TokenReader reader) {
        Map<String, Object> map = new LinkedHashMap<>();
        Token token;
        do {
            token = reader.nextSkipSpace();
            if (token != Token.STRING)
                break;
            String name = reader.getCaptured();
            token = reader.nextSkipSpace();
            if (token != Token.COLON)
                throw new SyntaxException("Expected ':'", token, reader.getLocation());
            Object value = readValue(reader);
            token = reader.nextSkipSpace();
            map.put(name, value);
        } while (token == Token.COMMA);

        if (token != Token.OBJECT_END)
            throw new SyntaxException("Expected object close", token, reader.getLocation());
        return map;
    }
}
