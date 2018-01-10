package forks.router;

import com.riguz.forks.http.HttpMethod;
import com.riguz.forks.router.PatternTrieRouter;
import com.riguz.forks.router.Router;
import com.riguz.forks.router.TrieRouter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RouterTest {

    @Test
    public void resolve() {
        Router<String> router = new TrieRouter<>();
        router.addGet("/", "index");
        router.addGet("/home", "home");
        router.addGet("/about", "about");
        assertEquals("index", router.resolve(HttpMethod.GET, "/"));
        assertEquals("home", router.resolve(HttpMethod.GET, "/home"));
        assertEquals("about", router.resolve(HttpMethod.GET, "/about"));
        assertEquals(null, router.resolve(HttpMethod.GET, "/foo"));
    }

    @Test
    public void resolveSharedPrefix() {
        Router<String> router = new TrieRouter<>();
        router.addGet("/", "index");
        router.addGet("/user", "user");
        router.addGet("/user/index", "userIndex");
        router.addGet("/user/profile", "userProfile");
        assertEquals("index", router.resolve(HttpMethod.GET, "/"));
        assertEquals("user", router.resolve(HttpMethod.GET, "/user"));
        assertEquals("userIndex", router.resolve(HttpMethod.GET, "/user/index"));
        assertEquals("userProfile", router.resolve(HttpMethod.GET, "/user/profile"));
    }
}
