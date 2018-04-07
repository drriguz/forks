package com.riguz.forks.config.route;

import java.util.Collections;
import java.util.List;

public class RouteConfig {
    private List<ClassIdentifier> controllers = Collections.emptyList();
    private List<ClassIdentifier> filters = Collections.emptyList();
    private List<RouteGroup> routes = Collections.emptyList();

    public RouteConfig(List<ClassIdentifier> controllers, List<ClassIdentifier> filters, List<RouteGroup> routes) {
        this.controllers = Collections.unmodifiableList(controllers);
        this.filters = Collections.unmodifiableList(filters);
        this.routes = Collections.unmodifiableList(routes);
    }

    public List<ClassIdentifier> getControllers() {
        return controllers;
    }

    public List<ClassIdentifier> getFilters() {
        return filters;
    }

    public List<RouteGroup> getRoutes() {
        return routes;
    }
}
