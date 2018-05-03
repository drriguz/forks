package com.riguz.forks.router;

import com.riguz.forks.config.route.ClassIdentifier;
import com.riguz.forks.config.route.FunctionCall;
import com.riguz.forks.config.route.RouteConfig;
import com.riguz.forks.config.route.RouteParser;
import com.riguz.forks.exceptions.ActionNotFoundException;
import com.riguz.forks.exceptions.InitializeException;
import com.riguz.forks.http.HttpMethod;
import com.riguz.forks.ioc.Injector;
import com.riguz.forks.mvc.RequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.stream.Collectors;

public class FileBasedPatternRouteLoader implements RouteLoader<RequestHandler> {
    private static final Logger logger = LoggerFactory.getLogger(FileBasedPatternRouteLoader.class);
    private final String routerFilePath;
    private final RouteParser routeParser;
    private final Injector injector;

    public FileBasedPatternRouteLoader(String routerFilePath, Injector injector) throws IOException {
        this.routerFilePath = routerFilePath;
        this.routeParser = RouteParser.fromResource(routerFilePath);
        this.injector = injector;
    }

    @Override
    public Router<RequestHandler> load() {
        RouteConfig config = this.routeParser.getRouteConfig();
        Map<String, String> controllers = config.getControllers()
                .stream()
                .collect(Collectors.toMap(ClassIdentifier::getAlias, ClassIdentifier::getClassName));
        Router<RequestHandler> router = new PatternTrieRouter<>();
        config.getRoutes().forEach(routeRule -> {
            HttpMethod method = HttpMethod.valueOf(routeRule.getMethod());
            try {
                RequestHandler handler = this.getHandler(controllers, routeRule.getFunctionCall());
                logger.info("Adding route:{} {} -> {}", method, routeRule.getPattern(), routeRule.getFunctionCall());
                router.add(method, routeRule.getPattern(), handler);
            } catch (ActionNotFoundException e) {
                throw new InitializeException("Action not found:" + e.getMessage());
            }
        });
        router.complete();
        return router;
    }

    private RequestHandler getHandler(Map<String, String> controllerAlias, FunctionCall functionCall) throws ActionNotFoundException {
        try {
            Class<?> controllerClass = Class.forName(controllerAlias.get(functionCall.getController()));
            Object controller = this.injector.getInstance(controllerClass);
            Method action = controllerClass.getMethod(functionCall.getMethod(), functionCall.getParamTypes());
            return new RequestHandler(controller, action, functionCall);
        } catch (NoSuchMethodException | ClassNotFoundException e) {
            logger.error("Failed to build handler:{}", e);
            throw new ActionNotFoundException(functionCall);
        }
    }
}
