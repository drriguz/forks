package com.riguz.forks.router;

import com.riguz.forks.config.route.ClassIdentifier;
import com.riguz.forks.config.route.RouteConfig;
import com.riguz.forks.config.route.RouteParser;
import com.riguz.forks.exceptions.ActionNotFoundException;
import com.riguz.forks.exceptions.InitializeException;
import com.riguz.forks.http.HttpMethod;
import com.riguz.forks.mvc.RequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class FileBasedPatternRouteLoader implements RouteLoader<RequestHandler> {
    private static final Logger logger = LoggerFactory.getLogger(FileBasedPatternRouteLoader.class);
    private final String routerFilePath;
    private final RouteParser routeParser;

    public FileBasedPatternRouteLoader(String routerFilePath) throws IOException {
        this.routerFilePath = routerFilePath;
        this.routeParser = RouteParser.fromResource(routerFilePath);
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
            String className = null;
            try {
                className = controllers.get(routeRule.getFunctionCall().getController());
                Class<?> controllerClass = Class.forName(className);
                RequestHandler handler = new RequestHandler(routeRule.getPattern(), controllerClass, routeRule.getFunctionCall());
                logger.info("Adding route:{} {} -> {}", method, routeRule.getPattern(), routeRule.getFunctionCall());
                router.add(method, routeRule.getPattern(), handler);
            } catch (ActionNotFoundException e) {
                throw new InitializeException("Action not found:" + e.getMessage());
            } catch (ClassNotFoundException e) {
                throw new InitializeException("Controller not found:" + className);
            }

        });
        router.complete();
        return router;
    }
}
