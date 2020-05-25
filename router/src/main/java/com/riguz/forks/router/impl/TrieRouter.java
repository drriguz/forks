package com.riguz.forks.router.impl;

import com.riguz.commons.base.Strings;
import com.riguz.forks.http.HttpMethod;
import com.riguz.forks.router.old.Resolved;
import com.riguz.forks.router.old.Router;
import com.riguz.forks.router.trie.Trie;
import com.riguz.forks.router.trie.TrieNode;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TrieRouter<T> implements Router<T> {
    
    public TrieRouter() {
        super();
    }

    @Override
    public void add(HttpMethod method, String pattern, T handler) {
        if (method == null || Strings.isNullOrEmpty(pattern) || handler == null) {
            throw new IllegalArgumentException("Invalid route path:" + method + " " + pattern);
        }
        TrieNode<Map<HttpMethod, T>> node = find(pattern);
        Map<HttpMethod, T> handlers = node == null ? null : node.getPayload();
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
    public Resolved<T> resolve(HttpMethod method, String requestPath) {
        if (method == null || requestPath == null) {
            throw new IllegalArgumentException("Request could not be null");
        }
        TrieNode<Map<HttpMethod, T>> node = this.root.find(requestPath);
        Map<HttpMethod, T> handlers = node == null ? null : node.getPayload();
        return handlers == null ? null : Resolved.of(handlers.get(method), Collections.emptyMap());
    }
}
