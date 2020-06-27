package com.riguz.forks.http.trie;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PatternTrieRouteCheckingTest {
    @Test(expected = InvalidPatternException.class)
    public void checkInvalidUrl() {
        PatternTrie<String> trie = new PatternTrie<>();
        trie.insert("/foo/+", "abc");
    }

    @Test(expected = InvalidPatternException.class)
    public void checkConflict() {
        PatternTrie<String> trie = new PatternTrie<>();
        trie.insert("/foo", "abc");
        trie.insert("/foo", "abc");
    }

    @Test(expected = InvalidPatternException.class)
    public void checkWildcardParameterWithoutName() {
        PatternTrie<String> trie = new PatternTrie<>();
        trie.insert("/file/*", "abc");
    }

    @Test(expected = InvalidPatternException.class)
    public void checkNamedParameterWithoutName() {
        PatternTrie<String> trie = new PatternTrie<>();
        trie.insert("/file/:", "abc");
    }

    @Test(expected = InvalidPatternException.class)
    public void checkWildcardConflictWithGeneral() {
        PatternTrie<String> trie = new PatternTrie<>();
        trie.insert("/file/*name", "detail");
        trie.insert("/file/upload", "upload");
    }

    @Test(expected = InvalidPatternException.class)
    public void checkWildcardConflictWithNamed() {
        PatternTrie<String> trie = new PatternTrie<>();
        trie.insert("/file/*name", "detail");
        trie.insert("/file/:id", "upload");
    }

    @Test(expected = InvalidPatternException.class)
    public void checkNamedConflictWithGeneral() {
        PatternTrie<String> trie = new PatternTrie<>();
        trie.insert("/file/:name", "detail");
        trie.insert("/file/upload", "upload");
    }

    @Test(expected = InvalidPatternException.class)
    public void checkDuplicatedNamedParameter() {
        PatternTrie<String> trie = new PatternTrie<>();
        trie.insert("/file/:id/details/:id", "detail");
    }
}
