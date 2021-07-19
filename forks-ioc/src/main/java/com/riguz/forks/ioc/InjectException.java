package com.riguz.forks.ioc;

public class InjectException extends RuntimeException {

    public InjectException(String msg, Exception ex) {
        super(msg, ex);
    }

    public InjectException(String msg) {
        super(msg);
    }
}
