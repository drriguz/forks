package com.riguz.forks.mvc;

import java.io.Serializable;

public abstract class Result implements Serializable {
    private static final long serialVersionUID = -355866140808231232L;

    public static final int OK = 200;
    public static final int NOT_FOUND = 404;

    int code;

    public Result() {
        this.code = OK;
    }

    public Result(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    protected void setCode(int code) {
        this.code = code;
    }

}
