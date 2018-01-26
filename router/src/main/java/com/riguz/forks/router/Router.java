package com.riguz.forks.router;

import com.riguz.forks.http.HttpMethod;
import com.riguz.gags.tuple.Pair;

import java.util.Map;

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

    void complete();

    Pair<T, Map<String, String>> resolve(HttpMethod method, String requestPath);
}
