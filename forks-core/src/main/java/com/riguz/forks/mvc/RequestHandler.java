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
    private final FunctionCall functionCall;

    public RequestHandler(Object controller, Method action, FunctionCall functionCall) throws ActionNotFoundException {
        this(Hashs.md5(controller.getClass().toString()), controller, action, functionCall);
    }

    public RequestHandler(String id, Object controller, Method action, FunctionCall functionCall) throws ActionNotFoundException {
        this.id = id;
        this.controller = controller;
        this.action = action;
        this.functionCall = functionCall;
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

    public FunctionCall getFunctionCall() {
        return functionCall;
    }

    @Override
    public String toString() {
        return "(" + action + ")";
    }
}
