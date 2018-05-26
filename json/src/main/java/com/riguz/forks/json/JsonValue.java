package com.riguz.forks.json;

import java.io.Serializable;

public abstract class JsonValue implements Serializable {
    public boolean isTrue() {
        return false;
    }

    public boolean isFalse() {
        return false;
    }

    public boolean isNull() {
        return false;
    }

    public boolean isObject() {
        return false;
    }

    public boolean isArray() {
        return false;
    }

    public boolean isNumber() {
        return false;
    }

    public boolean isString() {
        return false;
    }

    public String asString() {
        throw new UnsupportedOperationException("Not supported");
    }

    public double asNumber() {
        throw new UnsupportedOperationException("Not supported");
    }

    public JsonObject asObject() {
        throw new UnsupportedOperationException("Not supported");
    }

    public JsonArray asArray() {
        throw new UnsupportedOperationException("Not supported");
    }

    public double asLong() {
        throw new UnsupportedOperationException("Not supported");
    }

    public int asInt() {
        throw new UnsupportedOperationException("Not supported");
    }
}
