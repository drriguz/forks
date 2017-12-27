package com.riguz.forks.router.http;

public interface RouteFeature {

    HttpMethod getRequestMethod();

    String getRequestPath();
}
