package com.riguz.forks.ioc;

import javax.inject.Singleton;

import java.lang.reflect.Method;

public enum InjectScope {
    DEFAULT,
    SINGLETON;

    public static InjectScope of(Method method) {
        if (method.getAnnotation(Singleton.class) != null
            || method.getReturnType().getAnnotation(Singleton.class) != null) {
            return SINGLETON;
        }
        return DEFAULT;
    }
}
