package com.riguz.forks.mvc;

import javax.inject.Inject;

import com.riguz.forks.http.HttpRequest;
import com.riguz.forks.http.HttpResponse;
import com.riguz.forks.http.RequestDelegate;
import com.riguz.forks.router.Router;

public class Dispatcher implements RequestDelegate {

    protected final Router<RequestHandler> router;

    @Inject
    public Dispatcher(Router<RequestHandler> router) {
        this.router = router;
    }

    @Override
    public void delegate(HttpRequest request, HttpResponse response) {
        RequestHandler handler = this.router.resolve(request);
        if (handler == null) {
            response.sendError(404, "Not found");
            return;
        }
        Result result = handler.handle(new RequestContext(request, response));
        response.writeContent(result.toString());
    }
}
