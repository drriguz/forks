package com.riguz.forks.mvc.view;

import com.riguz.forks.http.HttpStatus;
import com.riguz.forks.mvc.Result;

public class ErrorResult extends Result {

    String message = "";

    public ErrorResult(HttpStatus httpStatus, String message) {
        super(httpStatus);
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
