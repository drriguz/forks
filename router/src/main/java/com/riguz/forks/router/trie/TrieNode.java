package com.riguz.forks.router.trie;

import com.riguz.commons.base.Strings;
import com.riguz.commons.tuple.Pair;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TrieNode<T> extends AbstractTrieNode<T, TrieNode<T>, TrieNode<T>> {
    protected final Map<Character, TrieNode<T>> children = new HashMap<>();

    public static <T> TrieNode<T> empty() {
        return new TrieNode<>();
    }

    private TrieNode() {
        super(null, null);
    }

    public TrieNode(Character path) {
        super(path, null);
        if (path == null) {
            throw new IllegalArgumentException("None root node should has a valid path");
        }
    }

    @Override
    public TrieNode<T> find(String path) {
        return this.find(path, 0);
    }

    @Override
    public T getValue(String path) {
        TrieNode<T> node = find(path);
        return node == null ? null : node.getPayload();
    }

    private TrieNode<T> find(String path, int offset) {
        if (Strings.isNullOrEmpty(path) || offset >= path.length()) {
            return null;
        }
        TrieNode<T> node = this.children.get(path.charAt(offset));

        if (node == null || offset == path.length() - 1) {
            return node;
        } else {
            return node.find(path, offset + 1);
        }
    }

    public void insert(String path, T payload) {
        this.insert(path, 0, payload);
    }

    private void insert(String path, int offset, T payload) {
        if (Strings.isNullOrEmpty(path) || offset >= path.length()) {
            return;
        }
        char key = path.charAt(offset);
        TrieNode<T> node = children.get(key);
        if (node == null) {
            node = new TrieNode<>(key);
            children.put(key, node);
        }
        if (offset == path.length() - 1) {
            if (node.hasPayload()) {
                throw new IllegalArgumentException("Conflict path detected:" + path);
            }
            node.payload = payload;
        } else {
            node.insert(path, offset + 1, payload);
        }
    }
}
