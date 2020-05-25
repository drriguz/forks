package com.riguz.forks.router.old;

import com.riguz.forks.http.HttpMethod;

public interface Router<T> {

    void add(HttpMethod method, String pattern, T handler);

    default void addGet(String pattern, T handler) {
        this.add(HttpMethod.GET, pattern, handler);
    }

    default void addPost(String pattern, T handler) {
        this.add(HttpMethod.POST, pattern, handler);
    }

    default void addPut(String pattern, T handler) {
        this.add(HttpMethod.PUT, pattern, handler);
    }

    default void addDelete(String pattern, T handler) {
        this.add(HttpMethod.DELETE, pattern, handler);
    }

    default void complete() {
    }

    Resolved<T> resolve(HttpMethod method, String requestUrl);
}
