package com.riguz.forks.router;

import java.util.Map;

public class PathAwareHandler<T> {
    private final T handler;
    private final Map<String, String> params;

    public PathAwareHandler(T handler, Map<String, String> params) {
        this.handler = handler;
        this.params = params;
    }

    public T getHandler() {
        return handler;
    }

    public Map<String, String> getParams() {
        return params;
    }
}
