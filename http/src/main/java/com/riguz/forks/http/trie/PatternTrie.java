package com.riguz.forks.http.trie;

import java.util.*;

public class PatternTrie<T> {
    final PatternTrieNode<T> root;

    public PatternTrie() {
        this.root = PatternTrieNode.empty();
    }

    public T search(String path) {
        return root.search(path, new HashMap<>());
    }

    public T search(String path, Map<String, String> pathVariables) {
        return root.search(path, pathVariables);
    }

    public void insert(String pattern, final T payload) {
        root.insert(pattern, payload);
    }

    public void verbose() {
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println(String.join("\n", dump()));
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
    }

    public List<String> dump() {
        return dump(this.root, "");
    }

    private String describePayload(PatternTrieNode<T> node) {
        return node.hasPayload() ? " (" + node.payload.toString() + ")" : " × ";
    }

    private List<String> dump(PatternTrieNode<T> node, String path) {
        if (node.isContinuous() && !shouldBreak(node)) {
            return dump(node.getNext(), path + node.getPath());
        }

        List<String> tree = new LinkedList<>();
        tree.add(path + node.getPath() + this.describePayload(node));

        String childPrefix = path.replaceAll("├", "│")
                .replaceAll("╰", " ")
                .replaceAll("/", " ")
                .replaceAll("\\w", " ");

        Iterator<PatternTrieNode<T>> iterator = node.children.values().iterator();
        while (iterator.hasNext()) {
            PatternTrieNode<T> childNode = iterator.next();
            String subPath = childPrefix + (iterator.hasNext() ? "├" : "╰");
            tree.addAll(this.dump(childNode, subPath));
        }
        return tree;
    }

    private boolean shouldBreak(PatternTrieNode<T> node) {
        return node.token != null && node.token.isParameter() || node.hasPayload();
    }
}
