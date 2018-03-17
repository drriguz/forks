package com.riguz.forks.config;

import org.antlr.v4.runtime.misc.ParseCancellationException;

public class SyntaxException extends ParseCancellationException {

    public SyntaxException(String message) {
        super(message);
    }
}
