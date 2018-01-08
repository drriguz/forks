package com.riguz.forks.mvc;

import java.lang.reflect.Method;
import java.util.function.Function;

public class RequestHandler {

    private final String id;
    private final Function<RequestContext, Result> action;

    public RequestHandler(String id,
        Function<RequestContext, Result> action) {
        this.id = id;
        this.action = action;
    }

    public static RequestHandler of(Object controller, Method actionMethod) {

        return null;
    }

    public Result handle(final RequestContext context) {
        return this.action.apply(context);
    }

    public String getId() {
        return id;
    }
}
