package com.riguz.forks.config;

import com.riguz.forks.antlr.RouteParser;
import com.riguz.forks.config.route.*;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RouteTest {
    final String controllers1 = "controllers admin{\n" +
            "package com.riguz.forks.demo.controller\n" +
            "UserController->AdminUserController\n" +
            "FileController\n" +
            "}";

    final String controllers2 = "controllers admin{\n" +
            "package com.riguz.forks.admin.controller\n" +
            "PermissionController\n" +
            "RoleController\n" +
            "}";
    final String route1 = "routes guest {\n" +
            "+NocsrfFilter\n" +
            "get /posts      PostUserController.getPosts()\n" +
            "get /posts/:id  PostUserController.getPost(id: String)\n" +
            "}";

    @Test
    public void loadControllers() {
        String cfg = controllers1 + route1;

        RouteLoader loader = RouteLoader.fromString(cfg);
        RouteConfig config = loader.getRouteConfig();
        assertNotNull(config);
        assertEquals(2, config.getControllers().size());
        ClassIdentifier c1 = config.getControllers().get(0);
        ClassIdentifier c2 = config.getControllers().get(1);
        matchClass("com.riguz.forks.demo.controller.UserController", "AdminUserController", c1);
        matchClass("com.riguz.forks.demo.controller.FileController", "FileController", c2);
        assertEquals(0, config.getFilters().size());
    }

    private void matchClass(String className, String alias, ClassIdentifier it) {
        assertEquals(className, it.getClassName());
        assertEquals(alias, it.getAlias());
    }

    @Test
    public void loadMultiControllers() {
        String cfg = controllers1 + controllers2 + route1;

        RouteLoader loader = RouteLoader.fromString(cfg);
        RouteConfig config = loader.getRouteConfig();
        assertNotNull(config);
        assertEquals(4, config.getControllers().size());
        ClassIdentifier c1 = config.getControllers().get(0);
        ClassIdentifier c2 = config.getControllers().get(1);
        ClassIdentifier c3 = config.getControllers().get(2);
        ClassIdentifier c4 = config.getControllers().get(3);
        matchClass("com.riguz.forks.demo.controller.UserController", "AdminUserController", c1);
        matchClass("com.riguz.forks.demo.controller.FileController", "FileController", c2);
        matchClass("com.riguz.forks.admin.controller.PermissionController", "PermissionController", c3);
        matchClass("com.riguz.forks.admin.controller.RoleController", "RoleController", c4);
    }

    @Test
    public void loadRoute() {
        String cfg = controllers1 + route1;
        RouteLoader loader = RouteLoader.fromString(cfg);
        RouteConfig config = loader.getRouteConfig();
        assertNotNull(config);
        List<RouteRule> rules = config.getRoutes();
        assertNotNull(rules);
        assertEquals(2, rules.size());
        assertEquals("/posts", rules.get(0).getPattern());
        assertEquals("GET", rules.get(0).getMethod());
        assertEquals(new FunctionCall("PostUserController", "getPosts", null),
                rules.get(0).getFunctionCall());
        assertEquals(new FunctionCall("PostUserController", "getPost", new PathParam[]{new PathParam("id", String.class)}),
                rules.get(1).getFunctionCall());
        assertEquals("/posts/:id", rules.get(1).getPattern());
        assertEquals("GET", rules.get(1).getMethod());
    }
}
