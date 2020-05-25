package com.riguz.forks.router.impl;

import com.riguz.forks.http.HttpMethod;
import com.riguz.forks.router.old.Router;
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
        assertEquals("index", router.resolve(HttpMethod.GET, "/").getPayload());
        assertEquals("home", router.resolve(HttpMethod.GET, "/home").getPayload());
        assertEquals("about", router.resolve(HttpMethod.GET, "/about").getPayload());
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
        assertEquals("index", router.resolve(HttpMethod.GET, "/").getPayload());
        assertEquals("user", router.resolve(HttpMethod.GET, "/user").getPayload());
        assertEquals("userIndex", router.resolve(HttpMethod.GET, "/user/index").getPayload());
        assertEquals("userProfile", router.resolve(HttpMethod.GET, "/user/profile").getPayload());
    }
}
