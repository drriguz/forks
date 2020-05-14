package com.riguz.forks.router.impl;

import com.riguz.commons.base.Strings;
import com.riguz.commons.tuple.Pair;
import com.riguz.forks.http.HttpMethod;
import com.riguz.forks.router.Router;
import com.riguz.forks.router.trie.AbstractTrieNode;
import com.riguz.forks.router.trie.Trie;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractTrieRouter<T, E extends AbstractTrieNode<Map<HttpMethod, T>, E>>
    extends Trie<Map<HttpMethod, T>, E>
    implements Router<T> {

    public AbstractTrieRouter(E root) {
        super(root);
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
    public Pair<T, Map<String, String>> resolve(HttpMethod method, String requestPath) {
        if (method == null || requestPath == null) {
            throw new IllegalArgumentException("Request could not be null");
        }
        Pair<Map<HttpMethod, T>, Map<String, String>> handlersPair = this.resolve(requestPath);
        if (handlersPair != null) {
            Map<HttpMethod, T> handlers = handlersPair.getLeft();
            T handler = handlers.get(method);
            if (handler != null) {
                return Pair.of(handler, handlersPair.getRight());
            }
        }
        return null;
    }
}
