package com.riguz.forks.router;

import com.riguz.gags.base.Strings;

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

    protected boolean shouldBreakTree() {
        return hasPayload();
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

    protected E find(String path) {
        return this.find(path, 0);
    }

    protected E find(String path, int offset) {
        if (Strings.isNullOrEmpty(path) || offset >= path.length()) {
            return null;
        }
        E node = this.children.get(path.charAt(offset));

        if (node == null || offset == path.length() - 1) {
            return node;
        } else {
            return node.find(path, offset + 1);
        }
    }

    public abstract E resolve(String path);

}
