package forks.router;

import com.riguz.forks.http.HttpMethod;
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
        router.complete();
        assertEquals("index", router.resolve(HttpMethod.GET, "/").getLeft());
        assertEquals("home", router.resolve(HttpMethod.GET, "/home").getLeft());
        assertEquals("about", router.resolve(HttpMethod.GET, "/about").getLeft());
        assertEquals(null, router.resolve(HttpMethod.GET, "/foo"));
    }

    @Test
    public void resolveSharedPrefix() {
        Router<String> router = new TrieRouter<>();
        router.addGet("/", "index");
        router.addGet("/user", "user");
        router.addGet("/user/index", "userIndex");
        router.addGet("/user/profile", "userProfile");
        router.complete();
        assertEquals("index", router.resolve(HttpMethod.GET, "/").getLeft());
        assertEquals("user", router.resolve(HttpMethod.GET, "/user").getLeft());
        assertEquals("userIndex", router.resolve(HttpMethod.GET, "/user/index").getLeft());
        assertEquals("userProfile", router.resolve(HttpMethod.GET, "/user/profile").getLeft());
    }
}
