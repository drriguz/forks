package com.riguz.gags.struct;

import com.riguz.gags.base.Strings;

import java.util.HashMap;
import java.util.Map;

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
}
