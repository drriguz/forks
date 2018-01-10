package com.riguz.forks.router;

import com.riguz.gags.base.Strings;

import java.util.HashMap;
import java.util.Map;

class PatternTrieNode<T> {

    static final String WILDCARD_PARAM_PATTERN = "*";
    static final String NAMED_PARAM_PATTERN = ":";

    final Character pattern;
    final String paramName;

    int occurances = 0;
    T payload;

    final Map<Character, PatternTrieNode<T>> children = new HashMap<>();

    PatternTrieNode() {
        this(null, null, null);
    }

    PatternTrieNode(Character pattern) {
        this(pattern, null, null);
    }

    PatternTrieNode(Character pattern, String paramName) {
        this(pattern, paramName, null);
    }

    PatternTrieNode(Character pattern, String paramName, T payload) {
        this.pattern = pattern;
        this.paramName = paramName;
        this.payload = payload;
    }

    int insert(String path, int offset, T payload) {
        if (Strings.isNullOrEmpty(path) || offset >= path.length()) {
            return 0;
        }
        char key = path.charAt(offset);
        PatternTrieNode<T> node = children.get(key);
        if (node == null) {
            node = new PatternTrieNode<>(key);
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

    PatternTrieNode<T> find(String path, int offset) {
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
        PatternTrieNode<T> node = this.children.get(key);
        return node == null ? null : node.find(path, offset + 1);
    }
}
