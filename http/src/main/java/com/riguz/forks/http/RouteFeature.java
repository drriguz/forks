package com.riguz.forks.http;

public interface RouteFeature {

    HttpMethod getRequestMethod();

    String getRequestPath();
}
