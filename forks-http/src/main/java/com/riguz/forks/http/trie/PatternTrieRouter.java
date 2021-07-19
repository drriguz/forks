package com.riguz.forks.http.trie;

import com.riguz.forks.http.Endpoint;
import com.riguz.forks.http.HttpMethod;
import com.riguz.forks.http.Routable;
import com.riguz.forks.http.Router;

import java.util.HashMap;
import java.util.Map;

public class PatternTrieRouter<T> implements Router<T> {
    private final PatternTrie<T>[] routes = new PatternTrie[HttpMethod.values().length];

    public PatternTrieRouter() {
        for (HttpMethod method : HttpMethod.values()) {
            PatternTrie<T> route = new PatternTrie<>();
            routes[method.ordinal()] = route;
        }
    }

    @Override
    public void addRoute(HttpMethod httpMethod, String pattern, T handler) {
        routes[httpMethod.ordinal()].insert(pattern, handler);
    }

    @Override
    public Endpoint<T> route(Routable routable) {
        Map<String, String> pathVariables = new HashMap<>();
        T handler = routes[routable.getHttpMethod().ordinal()].search(routable.getPath());

        return handler == null ? null : new Endpoint<>(pathVariables, handler);
    }
}
