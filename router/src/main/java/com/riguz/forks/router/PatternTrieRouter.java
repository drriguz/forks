package com.riguz.forks.router;

import com.riguz.forks.http.HttpMethod;

import java.util.Map;

public class PatternTrieRouter<T>
    extends AbstractTrieRouter<T, PatternTrieNode<Map<HttpMethod, T>>> {

    public PatternTrieRouter() {
        super(new PatternTrieNode<>());
    }
}
