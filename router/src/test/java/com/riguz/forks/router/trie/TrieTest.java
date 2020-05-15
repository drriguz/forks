package com.riguz.forks.router.trie;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TrieTest {

    @Test
    public void basicStructure() {
        Trie<String, TrieNode<String>, TrieNode<String>> tree = new Trie<>(TrieNode.empty());
        tree.insert("/home", "Hello");
        assertEquals("Hello", tree.getValue("/home"));
        assertEquals(null, tree.getValue("/home/"));
        assertEquals(null, tree.getValue("/homework"));
        assertEquals(null, tree.getValue("/hom"));
        assertEquals(null, tree.getValue("/homee"));
        assertEquals(null, tree.getValue("/hoxe"));
        tree.insert("/house", "world");
        assertEquals("world", tree.getValue("/house"));
        assertEquals(null, tree.getValue("/ho"));
        assertEquals("Hello", tree.getValue("/home"));
    }

    @Test
    public void duplicateInsert() {
        Trie<String, TrieNode<String>, TrieNode<String>> tree = new Trie<>(TrieNode.empty());
        tree.insert("/home", "Hello");
        try {
            tree.insert("/home", "Hello");
            fail();
        } catch (IllegalArgumentException ex) {

        }
        assertEquals("Hello", tree.getValue("/home"));
    }

    @Test
    public void print() {
        Trie<String, TrieNode<String>, TrieNode<String>> tree = new Trie<>(TrieNode.empty());
        tree.insert("/home", "Hello");
        tree.insert("/user", "User");
        tree.insert("/user/profile", "profile");
        tree.insert("/user/profile/1", "profile1");
        tree.insert("/user/profile/2", "profile1");
        tree.insert("/user/profile/3", "profile1");
        tree.insert("/user/settings", "settings");
        tree.insert("/user/settings/private", "settings");
        tree.insert("/user/settings/system", "settings");
        tree.insert("/user/settings/system/1", "settings");
        tree.insert("/user/settings/system/2", "settings");
        tree.insert("/about", "profile");
        String str = tree.dump();
        System.out.println(str);
    }

    @Test
    public void print1() {
        Trie<String, TrieNode<String>, TrieNode<String>> tree = new Trie<>(TrieNode.empty());
        tree.insert("/abc/de", "profile");
        tree.insert("/abc/fg", "settings");
        String str = tree.dump();
        System.out.println(str);
    }

    @Test
    public void print2() {
        Trie<String, TrieNode<String>, TrieNode<String>> tree = new Trie<>(TrieNode.empty());
        tree.insert("/abc/", "0");
        tree.insert("/abc/def", "1");
        tree.insert("/abc/bcd", "2");
        tree.insert("/abc/def/g", "3");
        tree.insert("/cmg", "4");
        String str = tree.dump();
        System.out.println(str);
    }
}
