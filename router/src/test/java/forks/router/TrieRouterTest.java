package forks.router;

import com.riguz.forks.router.RequestHandler;
import com.riguz.forks.router.Router;
import com.riguz.forks.router.TrieRouter;
import com.riguz.forks.router.http.HttpMethod;
import org.junit.Test;

public class TrieRouterTest {

    @Test
    public void testTrieRouter() {
        Router router = new TrieRouter();
        router.add(HttpMethod.GET, "/home", new RequestHandler(1));
        router.add(HttpMethod.POST, "/home", new RequestHandler(2));
        router.add(HttpMethod.DELETE, "/home", new RequestHandler(3));
    }
}
