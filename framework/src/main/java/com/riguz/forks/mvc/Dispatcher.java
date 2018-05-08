package com.riguz.forks.mvc;

import com.riguz.commons.tuple.Pair;
import com.riguz.forks.exceptions.ActionException;
import com.riguz.forks.http.HttpRequest;
import com.riguz.forks.http.HttpResponse;
import com.riguz.forks.http.RequestDelegate;
import com.riguz.forks.router.Router;
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
    protected final Resolver<Result> viewResolver;

    @Inject
    public Dispatcher(Router<RequestHandler> router,
                      ActionExecutor actionExecutor,
                      @Named("exceptionResolver") Resolver<Exception> exceptionResolver,
                      @Named("viewResolver") Resolver<Result> viewResolver) {
        this.router = router;
        this.actionExecutor = actionExecutor;
        this.exceptionResolver = exceptionResolver;
        this.viewResolver = viewResolver;
    }

    @Override
    public void delegate(HttpRequest request, HttpResponse response) {
        logger.info("=>Resolving :{}", request.getRequestURI());
        Pair<RequestHandler, Map<String, String>> resolved = this.router
                .resolve(request.getRequestMethod(), request.getRequestPath());
        if (resolved == null) {
            this.resolve404(request, response);
            return;
        }
        RequestHandler handler = resolved.getLeft();
        Map<String, String> pathVariables = resolved.getRight();
        logger.debug("Resolved path variables:{}", pathVariables);
        Result result = null;
        try {
            result = this.actionExecutor.execute(handler, new RequestContext(request, response, pathVariables));
            this.viewResolver.resolve(request, response, result);
        } catch (ActionException e) {
            this.exceptionResolver.resolve(request, response, e);
        }
    }

    private void resolve404(HttpRequest request, HttpResponse response) {
        response.sendError(404, "Not found");
    }
}