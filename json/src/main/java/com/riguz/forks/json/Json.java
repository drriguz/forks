package com.riguz.forks.json;

import java.io.Reader;

public interface Json {
    JsonValue parse(String json);

    JsonObject parseObject(String json);

    JsonArray parseArray(String json);

    JsonObject parseObject(Reader input);

    JsonArray parseArray(Reader input);

    JsonValue parse(Reader input);
}
