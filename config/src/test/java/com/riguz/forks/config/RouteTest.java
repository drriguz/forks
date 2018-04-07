package com.riguz.forks.config;

import com.riguz.forks.antlr.RouteParser;
import com.riguz.forks.config.route.ClassIdentifier;
import com.riguz.forks.config.route.RouteConfig;
import com.riguz.forks.config.route.RouteLoader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RouteTest {
    @Test
    public void loadRoute() {
        String cfg = "controllers admin{\n" +
                "package com.riguz.forks.demo.controller\n" +
                "UserController->AdminUserController\n" +
                "FileController\n" +
                "}" +
                "routes guest {\n" +
                "+NocsrfFilter\n" +
                "get /posts      PostUserController.getPosts()\n" +
                "get /posts/:id  PostUserController.getPost(id: String)\n" +
                "}";
        RouteLoader loader = RouteLoader.fromString(cfg);
        RouteConfig config = loader.getRouteConfig();
        assertNotNull(config);
        assertEquals(2, config.getControllers().size());
        ClassIdentifier c1 = config.getControllers().get(0);
        ClassIdentifier c2 = config.getControllers().get(1);
        assertEquals("com.riguz.forks.demo.controller.UserController", c1.getClassName());
        assertEquals("com.riguz.forks.demo.controller.FileController", c2.getClassName());
        assertEquals("AdminUserController", c1.getAlias());
        assertEquals("FileController", c2.getAlias());
        assertEquals(0, config.getFilters().size());
    }
}
