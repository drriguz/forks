package com.riguz.forks.config.route;

import java.util.Collections;
import java.util.List;

public class RouteRule {
    private final List<String> filters;
    private final String method;
    private final String pattern;
    private final FunctionCall functionCall;

    public RouteRule(List<String> filters, String method, String pattern, FunctionCall functionCall) {
        this.filters = Collections.unmodifiableList(filters);
        this.method = method;
        this.pattern = pattern;
        this.functionCall = functionCall;
    }

    public List<String> getFilters() {
        return filters;
    }

    public String getMethod() {
        return method;
    }

    public String getPattern() {
        return pattern;
    }

    public FunctionCall getFunctionCall() {
        return functionCall;
    }
}
