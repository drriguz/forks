package com.riguz.forks.json;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class JsonArray extends JsonValue {
    private final List<JsonValue> values = new LinkedList<>();

    public JsonArray() {
    }

    public JsonArray(JsonValue... items) {
        add(items);
    }

    public int getSize() {
        return values.size();
    }

    public JsonValue get(int i) {
        return values.get(i);
    }

    public void add(JsonValue... items) {
        for (JsonValue item : items)
            values.add(item);
    }

    public static JsonArray of(JsonValue... items) {
        return new JsonArray(items);
    }

    private static <T> JsonArray of(Function<T, JsonValue> constructor, T[] items) {
        JsonArray array = new JsonArray();
        for (T item : items)
            array.add(constructor.apply(item));
        return array;
    }

    public static JsonArray of(Double... items) {
        return of(JsonNumber::new, items);
    }

    public static JsonArray of(String... items) {
        return of(JsonString::new, items);
    }

    public static JsonArray of(Boolean... items) {
        return of(item -> item ? JsonLiteral.TRUE : JsonLiteral.FALSE, items);
    }

    @Override
    public boolean isArray() {
        return true;
    }

    @Override
    public JsonArray asArray() {
        return this;
    }

    public List<JsonValue> getValues() {
        return Collections.unmodifiableList(values);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JsonArray array = (JsonArray) o;
        return Objects.equals(values, array.values);
    }

    @Override
    public int hashCode() {

        return Objects.hash(values);
    }

    @Override
    public String toString() {
        return "JsonArray{" +
                "values=" + values +
                '}';
    }
}
