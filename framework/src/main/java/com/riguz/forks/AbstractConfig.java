package com.riguz.forks;

import com.riguz.forks.exceptions.InitializeException;
import com.riguz.forks.http.NetworkServer;
import com.riguz.forks.http.RequestDelegate;
import com.riguz.forks.http.undertow.UndertowServer;
import com.riguz.forks.ioc.Bind;
import com.riguz.forks.ioc.Injector;
import com.riguz.forks.mvc.DefaultExceptionResolver;
import com.riguz.forks.mvc.Dispatcher;
import com.riguz.forks.mvc.ExceptionResolver;
import com.riguz.forks.mvc.RequestHandler;
import com.riguz.forks.router.FileBasedPatternRouteLoader;
import com.riguz.forks.router.RouteLoader;
import com.riguz.forks.router.Router;
import com.riguz.forks.router.TrieRouter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.io.IOException;

public abstract class AbstractConfig {
    private static final Logger logger = LoggerFactory.getLogger(AbstractConfig.class);
    public static final String ROUTER_FILE = "route.cf";


    @Bind
    @Singleton
    public ExceptionResolver exceptionResolver(DefaultExceptionResolver exceptionResolver) {
        return exceptionResolver;
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
