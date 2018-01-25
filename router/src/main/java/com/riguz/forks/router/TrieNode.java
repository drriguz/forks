package com.riguz.forks.router;

import com.riguz.gags.base.Strings;

public class TrieNode<T> extends AbstractTrieNode<T, TrieNode<T>> {

    private TrieNode() {
        super();
    }

    public static <T> TrieNode<T> empty() {
        return new TrieNode<>();
    }

    public TrieNode(char path) {
        super(path);
    }

    @Override
    public int insert(String path, T payload) {
        return this.insert(path, 0, payload);
    }

    public int insert(String path, int offset, T payload) {
        if (Strings.isNullOrEmpty(path) || offset >= path.length()) {
            return 0;
        }
        char key = path.charAt(offset);
        TrieNode<T> node = children.get(key);
        if (node == null) {
            node = new TrieNode<>(key);
            children.put(key, node);
        }
        if (offset == path.length() - 1) {
            if (node.payload != null) {
                throw new IllegalArgumentException("Conflict path detected:" + path);
            }
            node.payload = payload;
        }
        return node.insert(path, offset + 1, payload);
    }

    @Override
    protected TrieNode<T> find(String path) {
        return this.find(path, 0);
    }

    @Override
    public TrieNode<T> resolve(String path) {
        return this.find(path);
    }

    public TrieNode<T> find(String path, int offset) {
        if (Strings.isNullOrEmpty(path) || offset >= path.length()) {
            return null;
        }
        char key = path.charAt(offset);
        if (offset == path.length() - 1) {
            return this.children.get(key);
        }
        TrieNode<T> node = this.children.get(key);
        return node == null ? null : node.find(path, offset + 1);
    }

}
