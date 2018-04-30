package com.riguz.forks.router;

import com.riguz.commons.base.Strings;
import com.riguz.commons.tuple.Pair;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Trie<T, E extends AbstractTrieNode<T, E>> {

    protected E root;

    public Trie(E root) {
        if (root == null || !root.isEmpty()) {
            throw new IllegalArgumentException("Illegal root node");
        }
        this.root = root;
    }

    public int insert(String path, T payload) {
        if (Strings.isNullOrEmpty(path)) {
            throw new IllegalArgumentException("Path cannot be empty");
        }
        return this.root.insert(path, payload);
    }

    public T find(String path) {
        E node = this.root.find(path);
        return node == null ? null : node.payload;
    }

    public Pair<T, Map<String, String>> resolve(String path) {
        Pair<E, Map<String, String>> node = this.root.resolve(path);

        if (node == null)
            return null;
        else {
            T payload = node.getLeft().getPayload();
            if (payload == null) {
                // TODO: support 405 method not supported
                return null;
            } else {
                return Pair.of(node.getLeft().getPayload(), node.getRight());
            }
        }
    }

    protected String descPath(E node) {
        String payload = node.payload == null ? " × " : " (" + node.payload.toString() + ")";
        return payload;
    }

    protected List<String> dump(E node, String path) {
        if (node.isContinuous() && !node.shouldBreakTree()) {
            return dump(node.getNext(), path + node.getPathAsString());
        }
        List<String> tree = new LinkedList<>();
        tree.add(path + node.getPathAsString() + this.descPath(node));

        String childPrefix = path.replaceAll("├", "│")
                .replaceAll("╰", " ")
                .replaceAll("/", " ")
                .replaceAll("\\w", " ");
        Iterator<E> iterator = node.children.values().iterator();
        while (iterator.hasNext()) {
            E childNode = iterator.next();
            String subPath = childPrefix + (iterator.hasNext() ? "├" : "╰");
            tree.addAll(this.dump(childNode, subPath));
        }
        return tree;
    }

    public String dump() {
        List<String> tree = this.dump(this.root, "");
        StringBuilder builder = new StringBuilder();
        for (String line : tree) {
            builder.append("\n" + line);
        }
        return builder.toString();
    }
}
