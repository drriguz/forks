package forks.router;

import com.riguz.forks.http.HttpMethod;
import com.riguz.forks.router.PatternTrieRouter;
import com.riguz.forks.router.Router;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PatternRouterTest {

    @Test
    public void resolve() {
        Router<String> router = new PatternTrieRouter<>();
        router.addGet("/", "index");
        router.addGet("/home", "home");
        router.addGet("/about", "about");
        router.complete();

        assertEquals("index", router.resolve(HttpMethod.GET, "/"));
        assertEquals("home", router.resolve(HttpMethod.GET, "/home"));
        assertEquals("about", router.resolve(HttpMethod.GET, "/about"));
        assertEquals(null, router.resolve(HttpMethod.GET, "/foo"));
    }

    @Test
    public void resolveWildcardPath() {
        Router<String> router = new PatternTrieRouter<>();
        router.addGet("/", "index");
        router.addGet("/files/*file", "file");
        router.complete();
        assertEquals("file", router.resolve(HttpMethod.GET, "/files/1.jpg"));
    }

    @Test
    public void resolveNamedPath() {
        Router<String> router = new PatternTrieRouter<>();
        router.addGet("/", "index");
        router.addGet("/users/:user/profile", "user");
        router.complete();
        assertEquals("user", router.resolve(HttpMethod.GET, "/users/1/profile"));
    }

    @Test
    public void resolvePattern() {
        Router<String> router = new PatternTrieRouter<>();
        router.addGet("/", "index");
        router.addGet("/user", "user");
        router.addGet("/user/:id", "userDetail");
        router.addGet("/user/:id/profile", "userProfile");
        router.addGet("/files/*file", "file");

        router.complete();

        assertEquals("index", router.resolve(HttpMethod.GET, "/"));
        assertEquals("user", router.resolve(HttpMethod.GET, "/user"));
        assertEquals("userDetail", router.resolve(HttpMethod.GET, "/user/1"));
        assertEquals("userProfile", router.resolve(HttpMethod.GET, "/user/1/profile"));
        assertEquals("file", router.resolve(HttpMethod.GET, "/files/1.jpg"));
    }

    @Test
    public void resolveSharedPrefix() {
        Router<String> router = new PatternTrieRouter<>();
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
