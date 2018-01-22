package com.riguz.gags.struct;

import com.riguz.gags.base.Strings;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

class TrieNode<T> {

    final Character path;
    boolean isLeaf;
    int occurances = 0;
    final Map<Character, TrieNode<T>> children = new HashMap<>();

    T payload;

    TrieNode() {
        this.path = null;
        this.payload = null;
    }

    TrieNode(char path) {
        this.path = path;
        this.payload = null;
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

    public TrieNode<T> getNext() {
        if (!this.isContinuous()) {
            throw new UnsupportedOperationException("No next node found");
        }
        return this.children.values().stream().findFirst().get();
    }

    int insert(String path, int offset, T payload) {
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
            node.occurances += 1;
            if (node.payload != null) {
                throw new IllegalArgumentException("Conflict path detected:" + path);
            }
            node.payload = payload;
        }
        return node.insert(path, offset + 1, payload);
    }

    TrieNode<T> find(String path, int offset) {
        if (Strings.isNullOrEmpty(path) || offset >= path.length()) {
            return null;
        }
        if (this.children == null) {
            return null;
        }
        char key = path.charAt(offset);
        if (offset == path.length() - 1) {
            return this.children.get(key);
        }
        TrieNode<T> node = this.children.get(key);
        return node == null ? null : node.find(path, offset + 1);
    }

    private void print(TrieNode<T> node, String parentPath) {
        String path = parentPath + node.path;
        if (node.payload != null) {
            System.out.println(path + "->" + node.payload);
        }
        for (Entry<Character, TrieNode<T>> e : node.children.entrySet()) {
            print(e.getValue(), path);
        }
    }

    public void print() {
        print(this, "");
    }
}
