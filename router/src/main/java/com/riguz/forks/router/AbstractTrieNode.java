package com.riguz.forks.router;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractTrieNode<T, E extends AbstractTrieNode<T, E>> {

    protected final Character path;
    protected final Map<Character, E> children = new HashMap<>();

    protected T payload;

    public AbstractTrieNode() {
        this.path = null;
        this.payload = null;
    }

    public AbstractTrieNode(char path) {
        this.path = path;
        this.payload = null;
    }

    public T getPayload() {
        return this.payload;
    }

    public Character getPath() {
        return path;
    }

    public String getPathAsString() {
        return this.path == null ? "" : String.valueOf(this.path);
    }

    public boolean hasPayload() {
        return this.payload != null;
    }

    public boolean isContinuous() {
        return this.children.size() == 1;
    }

    public E getNext() {
        if (!this.isContinuous()) {
            throw new UnsupportedOperationException("Child path is ambiguous");
        }
        return this.children.values().stream().findFirst().get();
    }

    public abstract int insert(String path, int offset, T payload);

    public abstract E find(String path, int offset);
}
