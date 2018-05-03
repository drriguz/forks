package com.riguz.forks.exceptions;

import com.riguz.forks.config.route.FunctionCall;

public class ActionNotFoundException extends Exception {
    public ActionNotFoundException(FunctionCall functionCall) {
        super("Action not found:" + functionCall);
    }
}
