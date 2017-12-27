package com.riguz.forks.router;

import java.lang.reflect.Method;

public class RequestHandler {

    Class<?> actionClass;
    Method actionMethod;
    int id;

    public RequestHandler(int id) {
        this.id = id;
    }

    public RequestHandler(Class<?> actionClass, Method actionMethod) {
        this.actionClass = actionClass;
        this.actionMethod = actionMethod;
    }

    public Class<?> getActionClass() {
        return this.actionClass;
    }

    public Method getActionMethod() {
        return this.actionMethod;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "[" + this.getId() + "]";
    }
}
