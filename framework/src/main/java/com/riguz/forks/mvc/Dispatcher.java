package com.riguz.forks.mvc;

import com.riguz.commons.tuple.Pair;
import com.riguz.forks.http.HttpRequest;
import com.riguz.forks.http.HttpResponse;
import com.riguz.forks.http.RequestDelegate;
import com.riguz.forks.router.Router;
import javax.inject.Inject;

import java.util.Map;

public class Dispatcher implements RequestDelegate {

    protected final Router<RequestHandler> router;

    @Inject
    public Dispatcher(Router<RequestHandler> router) {
        this.router = router;
    }

    @Override
    public void delegate(HttpRequest request, HttpResponse response) {
        Pair<RequestHandler, Map<String, String>> resolved = this.router
            .resolve(request.getRequestMethod(), request.getRequestPath());
        if (resolved == null) {
            response.sendError(404, "Not found");
        } else {
            RequestHandler handler = resolved.getLeft();
            Map<String, String> pathVariables = resolved.getRight();
            Result result = handler.handle(new RequestContext(request, response, pathVariables));
            response.writeContent(result.toString());
        }
    }
}