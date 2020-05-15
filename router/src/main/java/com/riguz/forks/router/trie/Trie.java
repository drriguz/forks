package com.riguz.forks.router.trie;

import com.riguz.commons.base.Strings;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Trie<T, R, C extends AbstractTrieNode<T, R, C>> {
    protected final C root;

    public Trie(C root) {
        if (root == null || !root.isEmpty()) {
            throw new IllegalArgumentException("Root node should be empty node");
        }
        this.root = root;
    }

    public void insert(String path, T payload) {
        if (Strings.isNullOrEmpty(path)) {
            throw new IllegalArgumentException("Path cannot be empty");
        }
        this.root.insert(path, payload);
    }

    public R find(String path) {
        return root.find(path);
    }

    public T getValue(String path) {
        return root.getValue(path);
    }

    protected String descPath(AbstractTrieNode<T, R, C> node) {
        return node.getPayload() == null ? " × " : " (" + node.getPayload().toString() + ")";
    }

    protected List<String> dump(AbstractTrieNode<T, R, C> node, String path) {
        if (node.isContinuous()) {
            return dump(node.getNext(), path + node.getDisplayPath());
        }
        List<String> tree = new LinkedList<>();
        tree.add(path + node.getDisplayPath() + this.descPath(node));

        String childPrefix = path.replaceAll("├", "│")
                .replaceAll("╰", " ")
                .replaceAll("/", " ")
                .replaceAll("\\w", " ");
        Iterator<C> iterator = node.children.values().iterator();
        while (iterator.hasNext()) {
            C childNode = iterator.next();
            String subPath = childPrefix + (iterator.hasNext() ? "├" : "╰");
            tree.addAll(this.dump(childNode, subPath));
        }
        return tree;
    }

    public String dump() {
        List<String> tree = this.dump(this.root, "");
        StringBuilder builder = new StringBuilder();
        for (String line : tree) {
            builder.append("\n").append(line);
        }
        return builder.toString();
    }
}
