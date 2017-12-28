package com.riguz.forks.router;

import com.riguz.forks.http.HttpMethod;
import com.riguz.forks.http.RouteFeature;

public interface Router {

    void add(HttpMethod method, String pattern, RequestHandler handler);

    void complete();

    RequestHandler resolve(RouteFeature request);
}
