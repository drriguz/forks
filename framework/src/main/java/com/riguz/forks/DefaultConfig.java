package com.riguz.forks;

import com.riguz.forks.exceptions.InitializeException;
import com.riguz.forks.http.NetworkServer;
import com.riguz.forks.http.RequestDelegate;
import com.riguz.forks.http.undertow.UndertowServer;
import com.riguz.forks.ioc.Bind;
import com.riguz.forks.ioc.Injector;
import com.riguz.forks.json.DslJsonSerializer;
import com.riguz.forks.json.JsonSerializer;
import com.riguz.forks.mvc.*;
import com.riguz.forks.router.FileBasedPatternRouteLoader;
import com.riguz.forks.router.RouteLoader;
import com.riguz.forks.router.old.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
import javax.inject.Singleton;
import java.io.IOException;

public class DefaultConfig {
    private static final Logger logger = LoggerFactory.getLogger(DefaultConfig.class);
    public static final String ROUTER_FILE = "route.cf";


    @Bind
    @Named("exceptionResolver")
    public Resolver<Exception> exceptionResolver(ExceptionResolver exceptionResolver) {
        return exceptionResolver;
    }

    @Bind
    @Named("responseResolver")
    public Resolver<Object> viewResolver(ResponseResolver viewResolver) {
        return viewResolver;
    }

    @Bind
    public JsonSerializer jsonSerializer() {
        return new DslJsonSerializer();
    }

    @Bind
    @Singleton
    public Router<RequestHandler> router(TrieRouter<RequestHandler> router, Injector injector) {
        RouteLoader<RequestHandler> loader = null;
        try {
            loader = new FileBasedPatternRouteLoader(ROUTER_FILE, injector);
        } catch (IOException e) {
            logger.error("Failed to load route:{}", e);
            throw new InitializeException("Failed to load route from " + ROUTER_FILE);
        }
        return loader.load();
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
