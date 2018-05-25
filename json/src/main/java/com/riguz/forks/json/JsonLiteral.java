package com.riguz.forks.json;

public class JsonLiteral extends JsonValue {
    public static final JsonLiteral TRUE = new JsonLiteral(LiteralType.TRUE);
    public static final JsonLiteral FALSE = new JsonLiteral(LiteralType.FALSE);
    public static final JsonLiteral NULL = new JsonLiteral(LiteralType.NULL);

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

    enum LiteralType {
        TRUE,
        FALSE,
        NULL
    }

    protected final LiteralType type;

    private JsonLiteral(LiteralType type) {
        this.type = type;
    }
}
