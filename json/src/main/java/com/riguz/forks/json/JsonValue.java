package com.riguz.forks.json;

import java.io.Serializable;

public abstract class JsonValue implements Serializable {
    public abstract boolean isTrue();

    public abstract boolean isFalse();

    public abstract boolean isNull();

    public abstract boolean isObject();

    public abstract boolean isArray();

    public abstract boolean isNumber();

    public abstract boolean isString();

    public abstract String asString();

    public abstract double asNumber();
}
