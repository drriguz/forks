package com.riguz.forks.config.property;

public enum Type {
    BOOL(Boolean.class),
    INT(Integer.class),
    FLOAT(Float.class),
    STRING(String.class);

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
