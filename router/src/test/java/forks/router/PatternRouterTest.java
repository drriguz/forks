package forks.router;

import com.riguz.forks.http.HttpMethod;
import com.riguz.forks.router.PatternTrieRouter;
import com.riguz.forks.router.Router;
import com.riguz.gags.tuple.Pair;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PatternRouterTest {

    @Test
    public void resolve() {
        Router<String> router = new PatternTrieRouter<>();
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
    public void resolveWildcardPath() {
        Router<String> router = new PatternTrieRouter<>();
        router.addGet("/", "index");
        router.addGet("/files/*file", "file");
        router.complete();
        validate(router.resolve(HttpMethod.GET, "/files/1.jpg"), "file", "1.jpg");
    }

    private void validate(Pair<String, Map<String, String>> result, String handler, String... args) {
        if (handler == null) {
            assertNull(result);
        } else {
            assertEquals(handler, result.getLeft());
            assertEquals(args.length, result.getRight().size());
            for (String arg : args) {
                assertEquals(true, result.getRight().containsValue(arg));
            }
        }
    }

    @Test
    public void resolveNamedPath() {
        Router<String> router = new PatternTrieRouter<>();
        router.addGet("/", "index");
        router.addGet("/users/:user/profile", "user");
        router.complete();
        validate(router.resolve(HttpMethod.GET, "/users/1/profile"), "user", "1");
    }

    @Test
    public void resolveMultiNamedPath() {
        Router<String> router = new PatternTrieRouter<>();
        router.addGet("/", "index");
        router.addGet("/users/:user/profile/:id", "user");
        router.complete();
        assertEquals("user", router.resolve(HttpMethod.GET, "/users/1/profile/2").getLeft());
    }

    @Test
    public void resolveComplexNamedPath() {
        Router<String> router = new PatternTrieRouter<>();
        router.addGet("/", "index");
        router.addGet("/users/:user/profile/:id/*file", "user");
        router.complete();
        assertEquals("user", router.resolve(HttpMethod.GET, "/users/1/profile/2/1.jpg").getLeft());
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

        assertEquals("index", router.resolve(HttpMethod.GET, "/").getLeft());
        assertEquals("user", router.resolve(HttpMethod.GET, "/user").getLeft());
        assertEquals("userDetail", router.resolve(HttpMethod.GET, "/user/1").getLeft());
        assertEquals("userProfile", router.resolve(HttpMethod.GET, "/user/1/profile").getLeft());
        assertEquals("file", router.resolve(HttpMethod.GET, "/files/1.jpg").getLeft());
    }

    @Test
    public void resolveSharedPrefix() {
        Router<String> router = new PatternTrieRouter<>();
        router.addGet("/", "index");
        router.addGet("/user", "user");
        router.addGet("/user/index", "userIndex");
        router.addGet("/user/profile", "userProfile");
        assertEquals("index", router.resolve(HttpMethod.GET, "/").getLeft());
        assertEquals("user", router.resolve(HttpMethod.GET, "/user").getLeft());
        assertEquals("userIndex", router.resolve(HttpMethod.GET, "/user/index").getLeft());
        assertEquals("userProfile", router.resolve(HttpMethod.GET, "/user/profile").getLeft());
    }
}
