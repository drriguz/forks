package commons;

import com.riguz.gags.struct.Trie;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TrieTest {

    @Test
    public void basicStructure() {
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
    public void duplicateInsert() {
        Trie<String> tree = new Trie<>();
        tree.insert("/home", "Hello");
        try {
            tree.insert("/home", "Hello");
            fail();
        } catch (IllegalArgumentException ex) {

        }
        assertEquals("Hello", tree.find("/home"));
    }

    @Test
    public void print() {
        Trie<String> tree = new Trie<>();
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
        Trie<String> tree = new Trie<>();
        tree.insert("/abc/de", "profile");
        tree.insert("/abc/fg", "settings");
        tree.insert("/xyz", "settings");
        String str = tree.dump();
        System.out.println(str);
    }
}
