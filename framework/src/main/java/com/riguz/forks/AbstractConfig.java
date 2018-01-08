package com.riguz.forks;

import com.riguz.forks.ioc.Bind;
import com.riguz.forks.mvc.RequestHandler;
import javax.inject.Singleton;

import com.riguz.forks.http.NetworkServer;
import com.riguz.forks.http.RequestDelegate;
import com.riguz.forks.http.undertow.UndertowServer;
import com.riguz.forks.mvc.Dispatcher;
import com.riguz.forks.router.Router;
import com.riguz.forks.router.TrieRouter;

public abstract class AbstractConfig {

    protected abstract Router<RequestHandler> loadRoute(Router<RequestHandler> router);

    @Bind
    @Singleton
    public Router<RequestHandler> router(TrieRouter<RequestHandler> router) {
        return this.loadRoute(router);
    }

    @Bind
    @Singleton
    public RequestDelegate delegator(Dispatcher dispatcher) {
        return dispatcher;
    }

    @Bind
    @Singleton
    public NetworkServer server(RequestDelegate delegate) {
        return new UndertowServer(8080, delegate);
    }
}
