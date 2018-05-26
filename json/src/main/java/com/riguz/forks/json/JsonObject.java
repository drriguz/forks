package com.riguz.forks.json;

import java.util.*;

public class JsonObject extends JsonValue {
    private final Map<String, JsonValue> properties = new HashMap<>();

    public void set(String name, JsonValue value) {
        properties.put(name, value);
    }

    public Set<String> keySet() {
        return Collections.unmodifiableSet(properties.keySet());
    }

    public JsonValue get(String name) {
        return properties.get(name);
    }

    public String getString(String name) {
        return get(name).asString();
    }

    public double getNumber(String name) {
        return get(name).asNumber();
    }

    public int getSize() {
        return properties.size();
    }

    @Override
    public boolean isObject() {
        return true;
    }

    @Override
    public JsonObject asObject() {
        return this;
    }

    @Override
    public double asNumber() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JsonObject that = (JsonObject) o;
        return Objects.equals(properties, that.properties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(properties);
    }

    @Override
    public String toString() {
        return "JsonObject{" +
                "properties=" + properties +
                '}';
    }
}
