package com.riguz.forks.mvc;

import com.riguz.forks.config.route.FunctionCall;
import com.riguz.forks.config.route.RouteRule;
import com.riguz.forks.exceptions.ActionNotFoundException;

import java.lang.reflect.Method;

public class RequestHandler {

    private final String id;
    private final Method action;

    public RequestHandler(String id, Class<?> controllerClass, FunctionCall action) throws ActionNotFoundException {
        this.id = id;
        this.action = this.attach(controllerClass, action);
    }

    private Method attach(Class<?> controllerClass, FunctionCall action) throws ActionNotFoundException {
        try {
            return controllerClass.getMethod(action.getMethod(), action.getParamTypes());
        } catch (NoSuchMethodException e) {
            throw new ActionNotFoundException(controllerClass, action);
        }
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "(" + action + ")";
    }
}
