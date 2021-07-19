package com.riguz.forks.config;

import org.antlr.v4.runtime.misc.ParseCancellationException;

public class DuplicateException extends RuntimeException {

    public DuplicateException(String message) {
        super(message);
    }
}
