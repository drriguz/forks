package com.riguz.forks.json;

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
}
