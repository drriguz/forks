package com.riguz.forks.router.trie;

import com.riguz.commons.base.Strings;
import com.riguz.commons.tuple.Pair;

import java.util.Collections;
import java.util.Map;

public class TrieNode<T> extends AbstractTrieNode<T, TrieNode<T>> {

    private TrieNode() {
        super();
    }

    public static <T> TrieNode<T> empty() {
        return new TrieNode<>();
    }

    public TrieNode(char path) {
        super(path);
    }

    @Override
    public int insert(String path, T payload) {
        return this.insert(path, 0, payload);
    }

    public int insert(String path, int offset, T payload) {
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
            if (node.hasPayload()) {
                throw new IllegalArgumentException("Conflict path detected:" + path);
            }
            node.payload = payload;
            return 1;
        } else {
            return node.insert(path, offset + 1, payload);
        }
    }

    @Override
    public Pair<TrieNode<T>, Map<String, String>> resolve(String path) {
        TrieNode<T> node = this.find(path);
        return node == null ? null : Pair.of(node, Collections.emptyMap());
    }

}
