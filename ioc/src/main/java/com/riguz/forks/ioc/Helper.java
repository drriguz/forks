package com.riguz.forks.ioc;

import javax.inject.Inject;
import javax.inject.Qualifier;
import javax.inject.Singleton;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public final class Helper {

    private Helper() {

    }

    public static Constructor<?> getInjectConstructor(Class<?> type) {
        Constructor<?> injectConstructor = null, noArgConstructor = null;
        for (Constructor<?> c : type.getDeclaredConstructors()) {
            if (c.isAnnotationPresent(Inject.class)) {
                if (injectConstructor != null) {
                    throw new InjectException("Multiple inject constructors found in " + type.getName());
                }
                injectConstructor = c;
            }
            if (c.getParameterTypes().length == 0) {
                noArgConstructor = c;
            }
        }
        Constructor<?> targetConstructor = injectConstructor != null ? injectConstructor : noArgConstructor;
        if (targetConstructor == null) {
            throw new InjectException(
                "No inject constructor or no-arg constructor found in " + type.getName());
        }
        targetConstructor.setAccessible(true);
        return targetConstructor;
    }

    public static Annotation getQualifier(Annotation[] annotations) {
        Annotation qualifier = null;
        for (Annotation a : annotations) {
            if (a.annotationType().isAnnotationPresent(Qualifier.class)) {
                if (qualifier != null && !qualifier.equals(a)) {
                    throw new InjectException("Multi-qualifier found:" + qualifier + " ," + a);
                }
                qualifier = a;
            }
        }
        return qualifier;
    }

    public static Annotation getQualifier(AnnotatedElement target) {
        return getQualifier(target.getDeclaredAnnotations());
    }

    public static InjectScope getTypeScope(Class<?> type) {
        if (type.getDeclaredAnnotation(Singleton.class) != null) {
            return InjectScope.SINGLETON;
        }
        return InjectScope.DEFAULT;
    }
}
