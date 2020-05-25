package com.riguz.forks.router.impl;

import com.riguz.commons.base.Strings;
import com.riguz.commons.tuple.Pair;
import com.riguz.forks.router.old.Resolved;
import com.riguz.forks.router.trie.AbstractTrieNode;

import java.util.LinkedHashMap;
import java.util.Map;

public class PatternTrieNode<T>
        extends AbstractTrieNode<T, Resolved<T>, PatternTrieNode<T>> {
    protected static final Character WILDCARD_PARAM_PATTERN = '*';
    protected static final Character NAMED_PARAM_PATTERN = ':';
    protected static final Character PATH_SPLITTER = '/';

    protected final String paramName;

    public static <T> PatternTrieNode<T> empty() {
        return new PatternTrieNode<>();
    }

    private PatternTrieNode() {
        super(null, null);
        this.paramName = null;
    }

    public PatternTrieNode(Character pattern, String paramName) {
        this(pattern, paramName, null);
    }

    public PatternTrieNode(Character pattern, String paramName, T payload) {
        super(pattern, payload);
        if (isPattern(pattern) && Strings.isNullOrEmpty(paramName)) {
            throw new IllegalArgumentException("No argument name with pattern:" + pattern);
        }
        this.paramName = paramName;
    }

    public String getParamName() {
        return paramName;
    }

    public boolean hasParam() {
        return this.hasPattern() && !Strings.isNullOrEmpty(this.paramName);
    }

    public boolean hasPattern() {
        return isPattern(this.path);
    }

    @Override
    public boolean isContinuous() {
        return super.isContinuous() && !hasPattern();
    }

    private static boolean isReserved(Character path) {
        return NAMED_PARAM_PATTERN == path
                || WILDCARD_PARAM_PATTERN == path
                || PATH_SPLITTER == path;
    }

    private static boolean isPattern(Character path) {
        return NAMED_PARAM_PATTERN == path || WILDCARD_PARAM_PATTERN == path;
    }

    private static Pair<Character, String> extractNameParam(String path, int offset) {
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
    }

    private static Pair<Character, String> extractWildcardParam(String path, int offset) {
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

    private static Pair<Character, String> extractParam(String path, int offset) {
        Character paramType = path.charAt(offset);
        if (NAMED_PARAM_PATTERN == paramType) {
            return extractNameParam(path, offset);
        } else if (WILDCARD_PARAM_PATTERN == paramType) {
            return extractWildcardParam(path, offset);
        }
        return null;
    }

    private PatternTrieNode<T> getOrCreate(Character key) {
        return this.getOrCreate(key, null);
    }

    private PatternTrieNode<T> getOrCreate(Character key, String paramName) {
        PatternTrieNode<T> node = (PatternTrieNode<T>) this.children.get(key);
        if (node == null) {
            node = new PatternTrieNode<>(key, paramName);
            this.children.put(key, node);
        } else {
            if (paramName == null) {
                if (node.hasParam()) {
                    throw new IllegalArgumentException(
                            "Conflict path: existing path with param:" + node.getParamName());
                }
            } else if (!paramName.equals(node.getParamName())) {
                throw new IllegalArgumentException(
                        "Ambiguous argument name found:" + paramName + "," + node.getParamName());
            }
        }
        return node;
    }

    @Override
    public void insert(String path, T payload) {
        this.insert(path, 0, payload);
    }

    private int insert(String path, int offset, T payload) {
        if (Strings.isNullOrEmpty(path) || offset >= path.length()) {
            return 0;
        }
        char key = path.charAt(offset);
        Pair<Character, String> param = extractParam(path, offset);
        PatternTrieNode<T> node;
        if (param != null) {
            key = param.getLeft();
            offset += param.getRight().length();
            node = this.getOrCreate(key, param.getRight());
        } else {
            node = this.getOrCreate(key);
        }

        if (offset == path.length() - 1) {
            if (node.payload != null) {
                throw new IllegalArgumentException("Conflict path detected:" + path);
            }
            node.payload = payload;
            return 1;
        } else {
            return node.insert(path, offset + 1, payload);
        }
    }

    @Override
    public Resolved<T> find(String path) {
        final Map<String, String> pathVariables = new LinkedHashMap<>();
        PatternTrieNode<T> node = this.findNode(path, 0, pathVariables);
        return node == null ? Resolved.notMatched() : Resolved.of(node.getPayload(), pathVariables);
    }

    @Override
    public T getValue(String path) {
        final Map<String, String> pathVariables = new LinkedHashMap<>();
        PatternTrieNode<T> node = this.findNode(path, 0, pathVariables);
        return node == null ? null : node.getPayload();
    }

    private PatternTrieNode<T> findNode(String path, int offset, Map<String, String> pathVariables) {
        if (Strings.isNullOrEmpty(path) || offset >= path.length()) {
            return null;
        }
        char key = path.charAt(offset);
        PatternTrieNode<T> next = this.children.get(WILDCARD_PARAM_PATTERN);
        if (next != null) {
            String paramValue = path.substring(offset);
            pathVariables.put(next.getParamName(), paramValue);
            return next;
        }
        next = this.children.get(NAMED_PARAM_PATTERN);
        if (next != null) {
            StringBuilder builder = new StringBuilder();
            while (offset <= path.length() - 1) {
                builder.append(path.charAt(offset));
                if (offset == path.length() - 1 || PATH_SPLITTER == path.charAt(offset + 1)) {
                    break;
                }
                offset += 1;
            }
            String paramValue = builder.toString();
            pathVariables.put(next.getParamName(), paramValue);
        } else {
            next = this.children.get(key);
        }
        if (offset == path.length() - 1 || next == null) {
            return next;
        } else {
            return next.findNode(path, offset + 1, pathVariables);
        }
    }
}
