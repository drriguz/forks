package com.riguz.forks.exceptions;

import com.riguz.forks.config.route.FunctionCall;

public class ActionNotFoundException extends Exception {
    public ActionNotFoundException(Class<?> controllerClass, FunctionCall action) {
        super("Action not found in class:" + controllerClass + " method:" + action);
    }
}
