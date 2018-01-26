package com.riguz.forks.router;

import com.riguz.forks.http.HttpMethod;
import com.riguz.gags.base.Strings;

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
    public void complete() {
        String tree = this.dump();
        System.out.println(tree);
    }

    @Override
    public T resolve(HttpMethod method, String requestPath) {
        if (method == null || requestPath == null) {
            throw new IllegalArgumentException("Request could not be null");
        }
        Map<HttpMethod, T> handlers = this.resolve(requestPath);
        if (handlers != null) {
            return handlers.get(method);
        } else {
            return null;
        }
    }
}
