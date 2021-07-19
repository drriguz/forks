package com.riguz.forks.mvc;

import com.riguz.forks.http.HttpStatus;

import java.io.Serializable;

public abstract class Result implements Serializable {

    protected HttpStatus httpStatus;

    public Result() {
        this.httpStatus = HttpStatus.OK;
    }

    public Result(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
