package com.riguz.forks.router.impl;

import com.riguz.commons.base.Strings;
import com.riguz.forks.http.HttpMethod;
import com.riguz.forks.router.old.Resolved;
import com.riguz.forks.router.old.Router;
import com.riguz.forks.router.trie.Trie;

import java.util.HashMap;
import java.util.Map;

public class PatternTrieRouter<T>
        extends Trie<Map<HttpMethod, T>, Resolved<Map<HttpMethod, T>>, PatternTrieNode<Map<HttpMethod, T>>>
        implements Router<T> {

    public PatternTrieRouter() {
        super(PatternTrieNode.empty());
    }

    protected String descPath(PatternTrieNode<Map<HttpMethod, T>> node) {
        String paramDesc = node.hasPattern() ? "{" + node.getParamName() + "}" : "";
        return paramDesc + super.descPath(node);
    }

    @Override
    public void add(HttpMethod method, String pattern, T handler) {
        if (method == null || Strings.isNullOrEmpty(pattern) || handler == null) {
            throw new IllegalArgumentException("Invalid route path:" + method + " " + pattern);
        }
        final Resolved<Map<HttpMethod, T>> resolved = this.root.find(pattern);
        if (!resolved.matched() || !resolved.hasValue()) {
            final Map<HttpMethod, T> handlers = new HashMap<>();
            handlers.put(method, handler);
            this.insert(pattern, handlers);
        } else {
            final Map<HttpMethod, T> handlers = resolved.getPayload();
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
    public Resolved<T> resolve(HttpMethod method, String requestUrl) {
        if (method == null || requestUrl == null) {
            throw new IllegalArgumentException("Request could not be null");
        }
        Resolved<Map<HttpMethod, T>> resolved = this.root.find(requestUrl);
        if (!resolved.matched() || !resolved.hasValue())
            return Resolved.notMatched();
        else {
            Map<HttpMethod, T> handlers = resolved.getPayload();
            return Resolved.of(resolved.getPayload().get(method), resolved.getParams());
        }
    }
}
