package com.riguz.forks.mvc;

import com.riguz.commons.encrypt.Hashs;
import com.riguz.forks.config.route.FunctionCall;
import com.riguz.forks.config.route.RouteRule;
import com.riguz.forks.exceptions.ActionNotFoundException;

import java.lang.reflect.Method;

public class RequestHandler {

    private final String id;
    private final Object controller;
    private final Method action;

    public RequestHandler(Object controller, Method action) throws ActionNotFoundException {
        this(Hashs.md5(controller.getClass().toString()), controller, action);
    }

    public RequestHandler(String id, Object controller, Method action) throws ActionNotFoundException {
        this.id = id;
        this.controller = controller;
        this.action = action;
    }

    public String getId() {
        return id;
    }

    public Method getAction() {
        return action;
    }

    public Object getController() {
        return controller;
    }

    @Override
    public String toString() {
        return "(" + action + ")";
    }
}
