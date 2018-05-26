package com.riguz.forks.json;

import java.util.Objects;

public class JsonString extends JsonValue {
    protected final String value;

    public JsonString(String value) {
        this.value = value;
    }

    @Override
    public boolean isString() {
        return false;
    }

    @Override
    public String asString() {
        return value;
    }

    @Override
    public double asNumber() {
        return Double.parseDouble(value);
    }

    @Override
    public double asLong() {
        return new Double(asNumber()).longValue();
    }

    @Override
    public int asInt() {
        return new Double(asNumber()).intValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JsonString that = (JsonString) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "JsonString{" +
                "value='" + value + '\'' +
                '}';
    }
}
