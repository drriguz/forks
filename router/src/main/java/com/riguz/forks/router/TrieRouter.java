package com.riguz.forks.router;

import com.riguz.forks.http.HttpMethod;
import com.riguz.forks.http.RouteFeature;
import com.riguz.gags.base.Strings;
import com.riguz.gags.struct.Trie;

import java.util.HashMap;
import java.util.Map;

public class TrieRouter extends Trie<Map<HttpMethod, RequestHandler>> implements Router {

    @Override
    public void add(HttpMethod method, String pattern, RequestHandler handler) {
        if (method == null || Strings.isNullOrEmpty(pattern) || handler == null) {
            throw new IllegalArgumentException("Invalid route path:" + method + " " + pattern);
        }
        Map<HttpMethod, RequestHandler> handlers = this.find(pattern);
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

    }

    @Override
    public RequestHandler resolve(RouteFeature request) {
        if (request == null) {
            throw new IllegalArgumentException("Request could not be null");
        }
        Map<HttpMethod, RequestHandler> handlers = this.find(request.getRequestPath());
        if (handlers != null) {
            return handlers.get(request);
        } else {
            return null;
        }
    }
}
