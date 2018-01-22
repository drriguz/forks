package com.riguz.forks.router;

import com.riguz.forks.http.HttpMethod;
import com.riguz.gags.base.Strings;
import com.riguz.gags.struct.Trie;

import java.util.HashMap;
import java.util.Map;

public class TrieRouter<T> extends Trie<Map<HttpMethod, T>> implements Router<T> {

    @Override
    public void add(HttpMethod method, String pattern, T handler) {
        if (method == null || Strings.isNullOrEmpty(pattern) || handler == null) {
            throw new IllegalArgumentException("Invalid route path:" + method + " " + pattern);
        }
        Map<HttpMethod, T> handlers = this.find(pattern);
        if (handlers == null) {
            handlers = new HashMap<>(HttpMethod.values().length);
            handlers.put(method, handler);
            this.insert(pattern, handlers);
        } else {
            if (handlers.containsKey(method)) {
                throw new IllegalArgumentException("Duplicate handler:" + method.name() + " " + pattern);
            }
            handlers.put(method, handler);
        }
    }

    @Override
    public void complete() {
        this.print();
    }

    @Override
    public T resolve(HttpMethod method, String requestPath) {
        if (method == null || requestPath == null) {
            throw new IllegalArgumentException("Request could not be null");
        }
        Map<HttpMethod, T> handlers = this.find(requestPath);
        if (handlers != null) {
            return handlers.get(method);
        } else {
            return null;
        }
    }
}
