package com.riguz.forks.router;

import com.riguz.forks.router.http.HttpMethod;
import com.riguz.forks.router.http.RouteFeature;

public interface Router {

    void add(HttpMethod method, String pattern, RequestHandler handler);

    void complete();

    RequestHandler resolve(RouteFeature request);
}
