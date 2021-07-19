package com.riguz.forks.http.undertow;

import com.riguz.forks.http.HttpMethod;
import com.riguz.forks.http.HttpRequest;
import io.undertow.server.HttpServerExchange;

import java.io.InputStream;
import java.util.Deque;
import java.util.Map;

public class UndertowRequest implements HttpRequest {

    private HttpServerExchange exchange;

    Map<String, Deque<String>> params;

    public UndertowRequest(HttpServerExchange exchange) {
        this.exchange = exchange;
        this.params = exchange.getQueryParameters();
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.valueOf(this.exchange.getRequestMethod().toString());
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public String getPath() {
        return this.exchange.getRequestPath();
    }

    @Override
    public String getRequestQuery() {
        return this.exchange.getQueryString();
    }

    @Override
    public String getRequestURI() {
        return this.exchange.getRequestURI();
    }

    @Override
    public String getRequestURL() {
        return this.exchange.getRequestURL();
    }

    @Override
    public InputStream getInputStream() {
        return this.exchange.getInputStream();
    }

    @Override
    public String getParamValue(String name) {
        Deque<String> param = this.params.get(name);
        if (param == null || param.isEmpty()) {
            return null;
        }
        return param.peekFirst();
    }

    @Override
    public String[] getParamValues(String name) {
        Deque<String> param = this.params.get(name);
        if (param == null || param.isEmpty()) {
            return null;
        }
        String[] values = new String[param.size()];
        values = param.toArray(values);
        return values;
    }

    @Override
    public Iterable<String> getParamNames() {
        return this.params.keySet();
    }
}
