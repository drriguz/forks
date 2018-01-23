package com.riguz.forks.router;

import com.riguz.gags.base.Strings;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Trie<T, E extends AbstractTrieNode<T, E>> {

    protected E root;

    public Trie(E root) {
        this.root = root;
    }

    public int insert(String path, T payload) {
        if (Strings.isNullOrEmpty(path)) {
            throw new IllegalArgumentException("Path cannot be empty");
        }
        return this.root.insert(path, 0, payload);
    }

    public T find(String path) {
        E node = this.root.find(path, 0);
        if (node == null) {
            return null;
        }
        return node.payload;
    }

    private List<String> dump(E node, String path) {
        if (node.isContinuous() && !node.hasPayload()) {
            return dump(node.getNext(), path + node.getPathAsString());
        }
        List<String> tree = new LinkedList<>();
        String payload = node.payload == null ? " × " : " (" + node.payload.toString() + ")";
        tree.add(path + node.getPathAsString() + payload);

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
