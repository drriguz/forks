package com.riguz.forks.router;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractTrieNode<T, E extends AbstractTrieNode<T, E>> {

    protected final Character path;
    protected final Map<Character, E> children = new HashMap<>();

    protected T payload;

    protected AbstractTrieNode() {
        this.path = null;
        this.payload = null;
    }

    public AbstractTrieNode(Character path) {
        if (path == null) {
            throw new IllegalArgumentException("None root node should has a valid path");
        }
        this.path = path;
        this.payload = null;
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

    public abstract int insert(String path, T payload);

    protected abstract E find(String path);

    public abstract E resolve(String path);

}
