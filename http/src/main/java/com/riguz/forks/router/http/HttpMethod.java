package com.riguz.forks.router.http;

public enum HttpMethod {
    GET(0), POST(1), PUT(2), DELETE(3);
    private int value;

    HttpMethod(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
