package com.riguz.forks.mvc;

import com.riguz.forks.http.HttpRequest;
import com.riguz.forks.http.HttpResponse;

import java.util.Map;

public class RequestContext {

    private final HttpRequest request;
    private final HttpResponse response;
    private final Map<String, String> pathVariables;

    public RequestContext(HttpRequest request, HttpResponse response, Map<String, String> pathVariables) {
        this.request = request;
        this.response = response;
        this.pathVariables = pathVariables;
    }

    public HttpRequest getRequest() {
        return request;
    }

    public HttpResponse getResponse() {
        return response;
    }

    public String getPathVariable(String name) {
        return this.pathVariables.get(name);
    }
}
