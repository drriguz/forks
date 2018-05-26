package com.riguz.forks.json;

import java.util.Objects;

public class JsonNumber extends JsonValue {
    protected final double value;

    public JsonNumber(double value) {
        this.value = value;
    }

    @Override
    public boolean isNumber() {
        return true;
    }

    @Override
    public String asString() {
        return String.valueOf(value);
    }

    @Override
    public double asNumber() {
        return value;
    }

    @Override
    public double asLong() {
        return new Double(value).longValue();
    }

    @Override
    public int asInt() {
        return new Double(value).intValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JsonNumber that = (JsonNumber) o;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "JsonNumber{" +
                "value=" + value +
                '}';
    }
}
