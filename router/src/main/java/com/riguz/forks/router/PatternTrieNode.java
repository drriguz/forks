package com.riguz.forks.router;

import com.riguz.gags.base.Strings;
import com.riguz.gags.tuple.Pair;

public class PatternTrieNode<T> extends AbstractTrieNode<T, PatternTrieNode<T>> {

    protected static final Character WILDCARD_PARAM_PATTERN = '*';
    protected static final Character NAMED_PARAM_PATTERN = ':';
    protected static final Character PATH_SPLITTER = '/';

    protected final String paramName;

    public static <T> PatternTrieNode<T> empty() {
        return new PatternTrieNode<>();
    }

    private PatternTrieNode() {
        super();
        this.paramName = null;
    }

    public PatternTrieNode(Character pattern, String paramName) {
        this(pattern, paramName, null);
    }

    public PatternTrieNode(Character pattern, String paramName, T payload) {
        super(pattern);
        this.paramName = paramName;
        this.payload = payload;
    }

    public String getParamName() {
        return paramName;
    }

    public boolean hasPattern() {
        return this.path == NAMED_PARAM_PATTERN || this.path == WILDCARD_PARAM_PATTERN;
    }

    private static boolean isReserved(Character path) {
        return NAMED_PARAM_PATTERN == path
            || WILDCARD_PARAM_PATTERN == path
            || PATH_SPLITTER == path;
    }

    private Pair<Character, String> extractNameParam(String path, int offset) {
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

    private Pair<Character, String> extractWildcardParam(String path, int offset) {
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

    private Pair<Character, String> extractParam(String path, int offset) {
        Character paramType = path.charAt(offset);
        if (NAMED_PARAM_PATTERN == paramType) {
            return this.extractNameParam(path, offset);
        } else if (WILDCARD_PARAM_PATTERN == paramType) {
            return this.extractWildcardParam(path, offset);
        }
        return null;
    }

    private PatternTrieNode<T> getOrCreate(Character key) {
        return this.getOrCreate(key, null);
    }

    private PatternTrieNode<T> getOrCreate(Character key, String paramName) {
        PatternTrieNode<T> node = this.children.get(key);
        if (node == null) {
            node = new PatternTrieNode<>(key, paramName);
            this.children.put(key, node);
        }
        return node;
    }

    @Override
    public int insert(String path, T payload) {
        return this.insert(path, 0, payload);
    }

    protected int insert(String path, int offset, T payload) {
        if (Strings.isNullOrEmpty(path) || offset >= path.length()) {
            return 0;
        }
        char key = path.charAt(offset);
        Pair<Character, String> param = this.extractParam(path, offset);
        if (param != null) {
            key = param.getLeft();
            offset += param.getRight().length();
        }

        PatternTrieNode<T> node = param == null ? this.getOrCreate(key) : this.getOrCreate(key, param.getRight());

        if (offset == path.length() - 1) {
            if (node.payload != null) {
                throw new IllegalArgumentException("Conflict path detected:" + path);
            }
            node.payload = payload;
        }
        return node.insert(path, offset + 1, payload);
    }

    @Override
    public PatternTrieNode<T> resolve(String path) {
        return null;
    }

    @Override
    public PatternTrieNode<T> find(String path, int offset) {
        if (Strings.isNullOrEmpty(path) || offset >= path.length()) {
            return null;
        }
        char key = path.charAt(offset);

        if (this.path == NAMED_PARAM_PATTERN) {
            StringBuilder param = new StringBuilder();
            while (offset < path.length() && path.charAt(offset) != PATH_SPLITTER) {
                param.append(path.charAt(offset++));
            }
            System.out.println("Param Value:" + path + " => " + param.toString());
        } else if (this.path == WILDCARD_PARAM_PATTERN) {
            StringBuilder param = new StringBuilder();
            while (offset < path.length() && path.charAt(offset) != PATH_SPLITTER) {
                param.append(path.charAt(offset++));
            }
            System.out.println("Param Value:" + path + " => " + param.toString());
        } else {

            if (this.path != key) {
                return null;
            }
            PatternTrieNode<T> node = this.children.get(key);
            if (node == null) {
                throw new RuntimeException("Invalid router detected");
            }
            return node.find(path, offset + 1);
        }
        return null;
    }
}
