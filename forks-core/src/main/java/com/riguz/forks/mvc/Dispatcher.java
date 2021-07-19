package com.riguz.forks.mvc;

import com.riguz.commons.tuple.Pair;
import com.riguz.forks.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;

import java.util.Map;

public class Dispatcher implements RequestDelegate {
    private static final Logger logger = LoggerFactory.getLogger(Dispatcher.class);

    protected final Router<RequestHandler> router;
    protected final ActionExecutor actionExecutor;
    protected final Resolver<Exception> exceptionResolver;
    protected final Resolver<Object> responseResolver;

    @Inject
    public Dispatcher(Router<RequestHandler> router,
                      ActionExecutor actionExecutor,
                      @Named("exceptionResolver") Resolver<Exception> exceptionResolver,
                      @Named("responseResolver") Resolver<Object> responseResolver) {
        this.router = router;
        this.actionExecutor = actionExecutor;
        this.exceptionResolver = exceptionResolver;
        this.responseResolver = responseResolver;
    }

    @Override
    public void delegate(HttpRequest request, HttpResponse response) {
        logger.info("=>Resolving :{}", request.getRequestURI());
        Endpoint<RequestHandler> endpoint = this.router.route(request);
        if (endpoint == null) {
            this.resolve404(request, response);
            return;
        }
        RequestHandler handler = endpoint.getHandler();
        Map<String, String> pathVariables = endpoint.getPathVariables();
        logger.debug("Resolved path variables:{}", pathVariables);
        try {
            final Object result = this.actionExecutor.execute(handler, new RequestContext(request, response, pathVariables));
            this.responseResolver.resolve(request, response, result);
        } catch (Exception e) {
            this.exceptionResolver.resolve(request, response, e);
        }
    }

    private void resolve404(HttpRequest request, HttpResponse response) {
        response.sendError(404, "Not found");
    }
}