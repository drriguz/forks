package com.riguz.forks.config.route;

public class PathParam {
    private final String name;
    private final Class<?> type;

    public PathParam(String name, Class<?> type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Class<?> getType() {
        return type;
    }
}
