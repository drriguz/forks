package com.riguz.forks.router.trie;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public abstract class AbstractTrieNode<T, R, C extends AbstractTrieNode<T, R, C>> {
    protected final Character path;
    protected final Map<Character, C> children = new HashMap<>();
    protected T payload;

    protected AbstractTrieNode(Character path, T payload) {
        this.path = path;
        this.payload = payload;
    }

    public boolean isEmpty() {
        return this.path == null;
    }

    public T getPayload() {
        return this.payload;
    }

    public Character getPath() {
        return path;
    }

    public String getDisplayPath() {
        return path == null ? "" : String.valueOf(path);
    }

    public boolean hasPayload() {
        return this.payload != null;
    }

    public boolean isContinuous() {
        return this.children.size() == 1;
    }

    public C getNext() {
        if (!this.isContinuous()) {
            throw new UnsupportedOperationException("Child path is ambiguous");
        }
        return this.children.values().stream().findFirst().get();
    }

    public abstract void insert(String path, T payload);

    public abstract R find(String path);

    public abstract T getValue(String path);
}
