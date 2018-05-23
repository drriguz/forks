package com.riguz.forks.json;

public class SyntaxException extends RuntimeException {
    public SyntaxException(String message, Location location) {
        super(message + " at:" + location);
    }
}
