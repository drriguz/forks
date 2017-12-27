package com.riguz.gags.struct;

import com.riguz.gags.base.Strings;

public class Trie<T> {

    private TrieNode<T> root;

    public Trie() {
        this.root = new TrieNode<>();
    }

    public int insert(String path, T payload) {
        if (Strings.isNullOrEmpty(path)) {
            throw new IllegalArgumentException("Path cannot be empty");
        }
        return this.root.insert(path, 0, payload);
    }

    public T find(String path) {
        TrieNode<T> node = this.root.find(path, 0);
        if (node == null) {
            return null;
        }
        return node.payload;
    }
}
