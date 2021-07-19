package com.riguz.forks.http;

public enum HttpStatus {
    OK(200),
    NOT_FOUND(404),
    BAD_REQUEST(401),
    SERVER_ERROR(500);

    private final int statusCode;

    HttpStatus(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
