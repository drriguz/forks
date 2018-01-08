package com.riguz.demo;

import com.riguz.forks.AbstractConfig;
import com.riguz.forks.http.HttpMethod;
import com.riguz.forks.mvc.RequestHandler;
import com.riguz.forks.router.Router;

public class AppConfig extends AbstractConfig {

    @Override
    protected Router<RequestHandler> loadRoute(Router<RequestHandler> router) {
        router.add(HttpMethod.GET, "/", new RequestHandler("1", null));
        return router;
    }
}
