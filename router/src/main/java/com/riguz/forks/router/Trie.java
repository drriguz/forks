package com.riguz.forks.router;

import com.riguz.gags.base.Strings;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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

    public T resolve(String path) {
        E node = this.root.resolve(path);
        return node == null ? null : node.payload;
    }

    protected String descPath(E node) {
        String payload = node.payload == null ? " × " : " (" + node.payload.toString() + ")";
        return payload;
    }

    protected List<String> dump(E node, String path) {
        if (node.isContinuous() && !node.hasPayload()) {
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
