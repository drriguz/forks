package com.riguz.forks.http.trie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

import static com.riguz.forks.http.Rfc3986.isLegalPath;

public class PatternTrieNode<T> {
    private static final Logger logger = LoggerFactory.getLogger(PatternTrieNode.class);

    final PatternTrieNode<T> parent;
    final Token token;
    T payload;
    final Map<Character, PatternTrieNode<T>> children = new HashMap<>();

    private PatternTrieNode() {
        this.token = null;
        this.payload = null;
        this.parent = null;
    }

    public PatternTrieNode(PatternTrieNode<T> parent, Token token) {
        this.parent = parent;
        this.token = token;
    }

    public static <T> PatternTrieNode<T> empty() {
        return new PatternTrieNode<>();
    }

    public Token getToken() {
        return token;
    }

    public T getPayload() {
        return payload;
    }

    public PatternTrieNode<T> getNext() {
        if (isContinuous())
            return this.children.values().iterator().next();
        throw new UnsupportedOperationException("Node is not continuous");
    }


    public String getPath() {
        if (token == null)
            return "";
        return String.valueOf(token.getPath());
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public boolean isContinuous() {
        return this.children.size() == 1;
    }

    public boolean hasChildren() {
        return !this.children.isEmpty();
    }

    public boolean hasPayload() {
        return this.payload != null;
    }

    public T search(String path, Map<String, String> pathVariables) {
        if (path == null)
            throw new IllegalArgumentException("Path should not be null");
        try {
            PatternTrieNode<T> node = search(path, 0, pathVariables);
            return node == null ? null : node.getPayload();
        } catch (IllegalArgumentException ex) {
            logger.warn("Illegal path detected:{}", path, ex);
            return null;
        }
    }

    private PatternTrieNode<T> search(String path, int offset, Map<String, String> pathVariables) {
        if (offset >= path.length())
            return null;
        final char p = path.charAt(offset);

        PatternTrieNode<T> next = this.children.get(Token.WILDCARD_PATTERN);
        if (next != null) {
            String value = Token.feedParameter(path, offset, false);
            pathVariables.put(next.getToken().getParameterName(), value);
            return next;
        }

        next = this.children.get(Token.NAMED_PATTERN);
        if (next != null) {
            String value = Token.feedParameter(path, offset, true);
            offset += value.length() - 1;
            pathVariables.put(next.getToken().getParameterName(), value);
        } else if (isLegalPath(p)) {
            next = this.children.get(p);
        }
        if (next == null)
            return null;
        else if (offset == path.length() - 1)
            return next;
        else
            return next.search(path, offset + 1, pathVariables);
    }


    public void insert(String pattern, final T payload) {
        if (pattern == null || pattern.isEmpty())
            throw new IllegalArgumentException("Path should not be null");
        insert(pattern, 0, payload);
    }

    private void insert(String pattern, int offset, final T payload) {
        if (offset >= pattern.length())
            throw new RuntimeException("Unexpected behavior occurs when insert");
        final Token token = Token.feed(pattern, offset);
        if (token.isParameter())
            offset += token.getLength() - 1; // token length includes the identifier

        PatternTrieNode<T> next = this.children.get(token.getPath());
        if (next == null) {
            next = new PatternTrieNode<>(this, token);
            this.children.put(token.getPath(), next);
            checkConflict();
        }

        if (offset == pattern.length() - 1) {
            if (next.payload != null)
                throw new InvalidPatternException("Path conflict:" + pattern);
            next.payload = payload;
        } else
            next.insert(pattern, offset + 1, payload);
    }

    private void checkConflict() {
        PatternTrieNode<T> wildcardParameter = this.children.get(Token.WILDCARD_PATTERN);
        PatternTrieNode<T> namedParameter = this.children.get(Token.NAMED_PATTERN);
        if ((wildcardParameter != null || namedParameter != null) && this.children.size() > 1)
            throw new InvalidPatternException("Parameter pattern conflict at:" + this.getAbsolutePath());
        Set<String> parameters = new HashSet<>();
        PatternTrieNode<T> node = this;
        do {
            List<String> nodeParams = node.children.values()
                    .stream()
                    .map(PatternTrieNode::getToken)
                    .filter(Token::isParameter)
                    .map(Token::getParameterName)
                    .collect(Collectors.toList());

            for (String param : nodeParams) {
                if (!parameters.add(param))
                    throw new InvalidPatternException("Duplicated parameter found: " + param);
            }
            node = node.parent;
        } while (node != null);
    }


    private String getAbsolutePath() {
        StringBuilder builder = new StringBuilder();
        PatternTrieNode<T> node = this;
        do {
            builder.insert(0, node.getPath());
            node = node.parent;
        } while (node != null);

        return builder.toString();
    }
}
