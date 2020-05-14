package com.riguz.forks.router.impl;

import com.riguz.forks.http.HttpMethod;
import com.riguz.forks.router.impl.AbstractTrieRouter;
import com.riguz.forks.router.trie.TrieNode;

import java.util.Map;

public class TrieRouter<T>
    extends AbstractTrieRouter<T, TrieNode<Map<HttpMethod, T>>> {

    public TrieRouter() {
        super(TrieNode.empty());
    }
}
