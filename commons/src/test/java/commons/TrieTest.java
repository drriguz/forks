package forks.commons;

import com.riguz.gags.struct.Trie;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TrieTest {

    @Test
    public void testTrieStructure() {
        Trie<String> tree = new Trie<>();
        tree.insert("/home", "Hello");
        assertEquals("Hello", tree.find("/home"));
        assertEquals(null, tree.find("/home/"));
        assertEquals(null, tree.find("/homework"));
        assertEquals(null, tree.find("/hom"));
        assertEquals(null, tree.find("/homee"));
        assertEquals(null, tree.find("/hoxe"));
        tree.insert("/house", "world");
        assertEquals("world", tree.find("/house"));
        assertEquals(null, tree.find("/ho"));
        assertEquals("Hello", tree.find("/home"));
    }

    @Test
    public void testDuplicateInsert() {
        Trie<String> tree = new Trie<>();
        tree.insert("/home", "Hello");
        try {
            tree.insert("/home", "Hello");
            fail();
        } catch (IllegalArgumentException ex) {

        }
        assertEquals("Hello", tree.find("/home"));
    }
}
