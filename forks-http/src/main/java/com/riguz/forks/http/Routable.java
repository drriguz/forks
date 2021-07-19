package com.riguz.forks.http;

public interface Routable {
    HttpMethod getHttpMethod();

    String getPath();
}
