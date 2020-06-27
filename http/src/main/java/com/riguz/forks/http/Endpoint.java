package com.riguz.forks.http;

import java.util.Collections;
import java.util.Map;

public class Endpoint<T> {
    private final Map<String, String> pathVariables;
    private final T handler;

    public Endpoint(Map<String, String> pathVariables, T handler) {
        this.pathVariables = Collections.unmodifiableMap(pathVariables);
        this.handler = handler;
    }

    public Map<String, String> getPathVariables() {
        return pathVariables;
    }

    public T getHandler() {
        return handler;
    }
}
