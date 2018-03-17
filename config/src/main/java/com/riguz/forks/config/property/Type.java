package com.riguz.forks.config.property;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Map;

public enum Type {
    BOOL(Boolean.class),
    INT(Integer.class),
    DECIMAL(Double.class),
    STRING(String.class),
    MAP(Map.class);

    private Class<?> type;

    Type(Class<?> type) {
        this.type = type;
    }

    public Class<?> getType() {
        return type;
    }

    @Override
    public String toString() {
        return this.name() + "(" + this.type + ")";
    }
}
