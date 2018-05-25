package com.riguz.forks.json;

public class JsonString extends JsonValue {
    protected final String value;

    public JsonString(String value) {
        this.value = value;
    }

    @Override
    public boolean isTrue() {
        return false;
    }

    @Override
    public boolean isFalse() {
        return false;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public boolean isObject() {
        return false;
    }

    @Override
    public boolean isArray() {
        return false;
    }

    @Override
    public boolean isNumber() {
        return false;
    }

    @Override
    public boolean isString() {
        return false;
    }

    @Override
    public String asString() {
        return null;
    }

    @Override
    public double asNumber() {
        return 0;
    }
}
