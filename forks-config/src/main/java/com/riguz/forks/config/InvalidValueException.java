package com.riguz.forks.config;

import org.antlr.v4.runtime.misc.ParseCancellationException;

public class InvalidValueException extends RuntimeException {

    public InvalidValueException(String message) {
        super(message);
    }
}
