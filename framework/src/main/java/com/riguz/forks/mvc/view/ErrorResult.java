package com.riguz.forks.mvc.view;

import com.riguz.forks.mvc.Result;

public class ErrorResult extends Result {
    private static final long serialVersionUID = 3141042849245711550L;

    String message = "";

    public ErrorResult(int code, String message) {
        super(code);
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
