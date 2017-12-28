package com.riguz.forks.http.undertow;

import com.riguz.forks.http.HttpResponse;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;

import java.io.OutputStream;
import java.nio.ByteBuffer;

public class UndertowResponse implements HttpResponse {

    private HttpServerExchange exchange;

    public UndertowResponse(HttpServerExchange exchange) {
        this.exchange = exchange;
    }

    @Override
    public HttpResponse setContentLength(long length) {
        this.exchange.setResponseContentLength(length);
        return this;
    }

    @Override
    public HttpResponse writeContent(String content) {
        this.exchange.getResponseSender().send(content);
        return this;
    }

    @Override
    public HttpResponse writeContent(ByteBuffer byteBuffer) {
        this.exchange.getResponseSender().send(byteBuffer);
        return this;
    }

    @Override
    public OutputStream getOutputStream() {
        return this.exchange.getOutputStream();
    }

    @Override
    public void flash() {
        this.exchange.endExchange();
    }

    @Override
    public HttpResponse sendError(int status, String message) {
        this.exchange.setStatusCode(status);
        this.exchange.getResponseSender().send(message);
        return this;
    }

    @Override
    public HttpResponse sendError(int status) {
        this.exchange.setStatusCode(status);
        return this;
    }

    @Override
    public HttpResponse sendRedirect(String url) {
        return null;
    }

    @Override
    public HttpResponse setHeader(String name, String value) {
        this.exchange.getResponseHeaders().put(new HttpString(name), value);
        return this;
    }

    @Override
    public HttpResponse setStatus(int status) {
        this.exchange.setStatusCode(status);
        return this;
    }
}
