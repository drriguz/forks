package com.riguz.forks.router;

import com.riguz.forks.http.HttpMethod;

import java.util.Map;

public class TrieRouter<T>
    extends AbstractTrieRouter<T, TrieNode<Map<HttpMethod, T>>> {

    public TrieRouter() {
        super(new TrieNode<>());
    }

}
