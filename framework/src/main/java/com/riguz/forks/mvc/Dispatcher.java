package com.riguz.forks.mvc;

import com.riguz.forks.http.HttpRequest;
import com.riguz.forks.http.HttpResponse;
import com.riguz.forks.http.RequestDelegate;
import com.riguz.forks.router.Router;
import com.riguz.gags.tuple.Pair;
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
        Pair<RequestHandler, Map<String, String>> handler = this.router
            .resolve(request.getRequestMethod(), request.getRequestPath());
        if (handler == null) {
            response.sendError(404, "Not found");
            return;
        }
        Result result = handler.getLeft().handle(new RequestContext(request, response));
        response.writeContent(result.toString());
    }
}
