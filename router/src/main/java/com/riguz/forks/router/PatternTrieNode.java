package com.riguz.forks.router;

import com.riguz.gags.base.Strings;
import com.riguz.gags.tuple.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

class PatternTrieNode<T> {

    static final Character WILDCARD_PARAM_PATTERN = '*';
    static final Character NAMED_PARAM_PATTERN = ':';
    static final Character PATH_SPLITTER = '/';

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

    private static boolean isReserved(Character path) {
        return NAMED_PARAM_PATTERN == path
            || WILDCARD_PARAM_PATTERN == path
            || PATH_SPLITTER == path;
    }

    private Pair<Character, String> extractParam(String path, int offset) {
        Character paramType = path.charAt(offset);
        if (NAMED_PARAM_PATTERN == paramType) {
            // valid:   /foo/:name
            //          /foo/:name/path
            //          /foo/:name/*file
            // invalid: /foo:name
            //          /foo/:name*
            //          /foo/:/
            StringBuilder paramNameBuilder = new StringBuilder();
            if (offset == path.length() - 1) {
                throw new IllegalArgumentException("Invalid path pattern(:name) :name is empty");
            }
            for (int i = offset + 1; i < path.length(); i++) {
                char p = path.charAt(i);
                if (p == PATH_SPLITTER) {
                    if (i == offset + 1) {
                        throw new IllegalArgumentException("Invalid path pattern(:name) :name is empty");
                    }
                    break;
                }
                if (isReserved(p)) {
                    throw new IllegalArgumentException("Invalid path pattern found:" + path + " at index " + i);
                }
                paramNameBuilder.append(p);
            }
            return new Pair<>(NAMED_PARAM_PATTERN, paramNameBuilder.toString());

        } else if (WILDCARD_PARAM_PATTERN == paramType) {
            // valid:   /foo/*file
            // invalid: /foo/*
            //          /foo/**
            //          /foo/*:
            //          /foo/*/
            //          /foo/*file/...
            StringBuilder paramNameBuilder = new StringBuilder();
            if (offset == path.length() - 1) {
                throw new IllegalArgumentException("Invalid path pattern(*name) :name is empty");
            }
            for (int i = offset + 1; i < path.length(); i++) {
                char p = path.charAt(i);
                if (isReserved(p)) {
                    throw new IllegalArgumentException("Invalid path pattern found:" + path + " at index " + i);
                }
                paramNameBuilder.append(p);
            }
            return new Pair<>(WILDCARD_PARAM_PATTERN, paramNameBuilder.toString());
        }
        return null;
    }

    private PatternTrieNode<T> getOrCreate(Character key) {
        PatternTrieNode<T> node = children.get(key);
        if (node == null) {
            node = new PatternTrieNode<>(key);
            children.put(key, node);
        }
        return node;
    }

    int insert(String path, int offset, T payload) {
        System.out.println("+" + path + " " + offset + " " + payload);
        if (Strings.isNullOrEmpty(path) || offset >= path.length()) {
            return 0;
        }
        char key = path.charAt(offset);
        PatternTrieNode<T> node = this.getOrCreate(key);
        Pair<Character, String> param = this.extractParam(path, offset);
        if (param != null) {
            node = new PatternTrieNode<>(param.getLeft(), param.getRight());
            offset += param.getRight().length();
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

    private void print(PatternTrieNode<T> node, String parentPath) {
        String path = parentPath == null ? "" : parentPath + node.pattern;
        if (node.payload != null) {
            System.out.println(path + " -> " + node.payload);
        }
        for (Entry<Character, PatternTrieNode<T>> e : node.children.entrySet()) {
            print(e.getValue(), path);
        }
    }

    public void print() {
        print(this, null);
    }
}
