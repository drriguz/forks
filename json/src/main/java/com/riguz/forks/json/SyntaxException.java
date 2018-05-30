package com.riguz.forks.json;

import com.riguz.forks.json.simple.Token;

public class SyntaxException extends RuntimeException {

    public SyntaxException(String message) {
        super(message);
    }

    public SyntaxException(String message, Token token, Location location) {
        super(message + " at:" + location + " currentToken:" + token);
    }

    public SyntaxException(String message, Location location) {
        super(message + " at:" + location);
    }
}
