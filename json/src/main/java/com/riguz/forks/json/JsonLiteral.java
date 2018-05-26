package com.riguz.forks.json;

import java.util.Objects;

public class JsonLiteral extends JsonValue {
    public static final JsonLiteral TRUE = new JsonLiteral(LiteralType.TRUE);
    public static final JsonLiteral FALSE = new JsonLiteral(LiteralType.FALSE);
    public static final JsonLiteral NULL = new JsonLiteral(LiteralType.NULL);

    enum LiteralType {
        TRUE,
        FALSE,
        NULL
    }

    protected final LiteralType type;

    private JsonLiteral(LiteralType type) {
        this.type = type;
    }

    @Override
    public boolean isTrue() {
        return this.type == LiteralType.TRUE;
    }

    @Override
    public boolean isFalse() {
        return this.type == LiteralType.FALSE;
    }

    @Override
    public boolean isNull() {
        return this.type == LiteralType.NULL;
    }

    @Override
    public String asString() {
        return this.type.name().toLowerCase();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JsonLiteral that = (JsonLiteral) o;
        return type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

    @Override
    public String toString() {
        return "JsonLiteral{" +
                "type=" + type +
                '}';
    }
}
