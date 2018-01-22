package com.riguz.forks.router;

import com.riguz.forks.http.HttpMethod;
import com.riguz.gags.base.Strings;

import java.util.HashMap;
import java.util.Map;

public class PatternTrieRouter<T> implements Router<T> {

    private PatternTrieNode<Map<HttpMethod, T>> root = new PatternTrieNode<>();

    private Map<HttpMethod, T> find(String path) {
        PatternTrieNode<Map<HttpMethod, T>> node = this.root.find(path, 0);
        if (node == null) {
            return null;
        }
        return node.payload;
    }

    private void insert(String path, Map<HttpMethod, T> handlers) {
        this.root.insert(path, 0, handlers);
    }

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
        this.root.print();
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
