package com.riguz.gags.struct;

import com.riguz.gags.base.Strings;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class Trie<T> {

    protected TrieNode<T> root;

    public Trie() {
        this.root = new TrieNode<>();
    }

    public int insert(String path, T payload) {
        if (Strings.isNullOrEmpty(path)) {
            throw new IllegalArgumentException("Path cannot be empty");
        }
        return this.root.insert(path, 0, payload);
    }

    public T find(String path) {
        TrieNode<T> node = this.root.find(path, 0);
        if (node == null) {
            return null;
        }
        return node.payload;
    }

    public void print() {
        this.print(this.root, "");
    }

    private void print(TrieNode<T> node, String parentPath) {
        if (node.path != null) {
            System.out.println(node.path);
        }
        for (Entry<Character, TrieNode<T>> child : node.children.entrySet()) {
            System.out.println(child.getKey());
            this.print(child.getValue(), child.getKey() + "");
        }
    }

    private List<String> dump(TrieNode<T> node, String path) {
        if (node.isContinuous()) {
            return dump(node.getNext(), path + node.getPathAsString());
        }
        List<String> tree = new LinkedList<>();
        tree.add(path + node.getPathAsString());

        Iterator<TrieNode<T>> iterator = node.children.values().iterator();

        String childPrefix = path.replaceAll("├", "│")
            .replaceAll("╰", " ")
            .replaceAll("\\w", " ");
        while (iterator.hasNext()) {
            TrieNode<T> childNode = iterator.next();
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
