package com.riguz.forks.http;

public interface Router<T> {
    void addRoute(HttpMethod httpMethod, String pattern, T handler);

    Endpoint<T> route(Routable routable);
}
