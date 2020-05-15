package com.riguz.forks.router;

import java.util.Collections;
import java.util.Map;

public class Resolved<T> {
    private final boolean matched;
    private final T payload;
    private final Map<String, String> params;

    private Resolved(boolean matched, T payload, Map<String, String> params) {
        this.matched = matched;
        this.payload = payload;
        this.params = params;
    }

    public static <T> Resolved<T> notMatched() {
        return new Resolved<>(false, null, Collections.emptyMap());
    }

    public static <T> Resolved<T> of(T payload, Map<String, String> params) {
        return new Resolved<>(false, payload, params);
    }

    public boolean matched() {
        return matched;
    }

    public boolean hasValue() {
        return payload != null;
    }

    public T getPayload() {
        return payload;
    }

    public Map<String, String> getParams() {
        return params;
    }
}
