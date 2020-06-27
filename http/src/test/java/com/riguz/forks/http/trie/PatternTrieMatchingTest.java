package com.riguz.forks.http.trie;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PatternTrieMatchingTest {
    @Test
    public void emptyTrie() {
        PatternTrie<String> trie = new PatternTrie<>();
        trie.verbose();

        assertNull(trie.search("/"));
        assertNull(trie.search("/abc"));
    }

    @Test
    public void singlePathMatching() {
        PatternTrie<String> trie = new PatternTrie<>();
        trie.insert("/foo", "foo");
        trie.verbose();

        assertEquals("foo", trie.search("/foo"));
        assertNull(trie.search("/abc"));
    }

    @Test
    public void multiplePathMatching() {
        PatternTrie<String> trie = new PatternTrie<>();
        trie.insert("foo", "foo");
        trie.insert("bar", "bar");
        trie.verbose();

        assertEquals("foo", trie.search("foo"));
        assertEquals("bar", trie.search("bar"));
        assertNull(trie.search("/abc"));
    }

    @Test
    public void multiplePathWithSharedPrefixMatching() {
        PatternTrie<String> trie = new PatternTrie<>();
        trie.insert("/foo", "foo");
        trie.insert("/bar", "bar");
        trie.verbose();

        assertEquals("foo", trie.search("/foo"));
        assertEquals("bar", trie.search("/bar"));
        assertNull(trie.search("/"));
        assertNull(trie.search("/f"));
        assertNull(trie.search("/fo"));
        assertNull(trie.search("/abc"));
    }

    @Test
    public void wildcardMatching() {
        PatternTrie<String> trie = new PatternTrie<>();
        trie.insert("/file/*name", "file");
        trie.verbose();

        assertEquals("file", trie.search("/file/1"));
        assertEquals("file", trie.search("/file/124"));
        assertEquals("file", trie.search("/file/a.jpeg"));
        assertNull(trie.search("/file/"));
        assertNull(trie.search("/fil/1.jpeg"));
    }

    @Test
    public void namedMatching() {
        PatternTrie<String> trie = new PatternTrie<>();
        trie.insert("/file/:name", "file");
        trie.insert("/file/:name/detail", "detail");
        trie.verbose();

        assertEquals("file", trie.search("/file/1"));
        assertEquals("file", trie.search("/file/124"));
        assertEquals("file", trie.search("/file/a.jpeg"));
        assertEquals("detail", trie.search("/file/a.jpeg/detail"));
        assertNull(trie.search("/file/"));
        assertNull(trie.search("/fil/1.jpeg"));
    }

    @Test
    public void multipleNamedMatching() {
        PatternTrie<String> trie = new PatternTrie<>();
        trie.insert("/user/:name/cars/:id", "cars");
        trie.verbose();

        assertEquals("cars", trie.search("/user/riguz/cars/suzuki"));
        assertNull(trie.search("/user/riguz/cars/"));
        assertNull(trie.search("/user/riguz"));
    }

    @Test
    public void complexMatching() {
        PatternTrie<String> trie = new PatternTrie<>();
        trie.insert("/", "home");
        trie.insert("/user", "user");
        trie.insert("/user/:id", "user detail");
        trie.insert("/user/:id/profile", "user profile");
        trie.insert("/files/*fileName", "file");
        trie.insert("/usermanagement/:id", "manage");
        trie.insert("/user/:id/add", "add user");
        trie.insert("/user/:id/edit", "edit user");
        trie.insert("/user/:id/upload/*fileName", "upload");

        trie.verbose();

        assertEquals("home", trie.search("/"));
        assertEquals("user", trie.search("/user"));
        assertEquals("user detail", trie.search("/user/123"));
        assertEquals("user profile", trie.search("/user/123/profile"));
        assertEquals("add user", trie.search("/user/123/add"));
        assertEquals("edit user", trie.search("/user/123/edit"));
        assertEquals("upload", trie.search("/user/123/upload/1.jpg"));
        assertEquals("file", trie.search("/files/1.jpg"));

    }

    @Test
    public void resolveParameters() {
        PatternTrie<String> trie = new PatternTrie<>();
        trie.insert("/", "home");
        trie.insert("/user", "user");
        trie.insert("/user/:id", "user detail");
        trie.insert("/user/:id/profile", "user profile");
        trie.insert("/files/*fileName", "file");
        trie.insert("/usermanagement/:id", "manage");
        trie.insert("/user/:id/add", "add user");
        trie.insert("/user/:id/edit", "edit user");
        trie.insert("/user/:id/upload/*fileName", "upload");


        Map<String, String> params = new HashMap<>();
        assertEquals("user detail", trie.search("/user/123", params));
        assertEquals("123", params.get("id"));

        params.clear();
        assertEquals("user profile", trie.search("/user/123/profile", params));
        assertEquals("123", params.get("id"));

        params.clear();
        assertEquals("add user", trie.search("/user/123/add", params));
        assertEquals("123", params.get("id"));

        params.clear();
        assertEquals("edit user", trie.search("/user/123/edit", params));
        assertEquals("123", params.get("id"));

        params.clear();
        assertEquals("upload", trie.search("/user/123/upload/1.jpg", params));
        assertEquals("123", params.get("id"));
        assertEquals("1.jpg", params.get("fileName"));

        params.clear();
        assertEquals("file", trie.search("/files/1.jpg", params));
        assertEquals("1.jpg", params.get("fileName"));

    }
}
