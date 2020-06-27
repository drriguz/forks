package com.riguz.forks.http.trie;

import com.riguz.forks.http.HttpMethod;
import com.riguz.forks.http.Routable;
import com.riguz.forks.http.Router;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class PatternTrieRouterTest {
    static class Request implements Routable {
        private final HttpMethod httpMethod;
        private final String path;

        Request(HttpMethod httpMethod, String path) {
            this.httpMethod = httpMethod;
            this.path = path;
        }

        @Override
        public HttpMethod getHttpMethod() {
            return httpMethod;
        }

        @Override
        public String getPath() {
            return path;
        }
    }

    @Test
    public void resolve() {
        Router<String> router = new PatternTrieRouter<>();

        router.addRoute(HttpMethod.GET, "/", "home");
        router.addRoute(HttpMethod.GET, "/user", "user");
        router.addRoute(HttpMethod.GET, "/user/:id", "user detail");
        router.addRoute(HttpMethod.GET, "/user/:id/profile", "user profile");
        router.addRoute(HttpMethod.POST, "/files/*fileName", "file");
        router.addRoute(HttpMethod.PUT, "/usermanagement/:id", "manage");
        router.addRoute(HttpMethod.GET, "/user/:id/add", "add user");
        router.addRoute(HttpMethod.GET, "/user/:id/edit", "edit user");
        router.addRoute(HttpMethod.PATCH, "/user/:id/upload/*fileName", "upload");

        assertNotNull(router.route(new Request(HttpMethod.GET, "/")));
        assertNotNull(router.route(new Request(HttpMethod.GET, "/user")));
        assertNotNull(router.route(new Request(HttpMethod.GET, "/user/100")));
        assertNotNull(router.route(new Request(HttpMethod.PATCH, "/user/100/upload/1.jpg")));
    }
}
