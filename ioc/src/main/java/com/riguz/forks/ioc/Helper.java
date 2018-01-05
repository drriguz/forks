package com.riguz.forks.ioc;

import javax.inject.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Objects;

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

    public static boolean isSameQualifier(Annotation a, Annotation b) {
        if (a == null) {
            return b == null;
        }
        if (a.annotationType() != b.annotationType()) {
            return false;
        } else if (a.annotationType() == Named.class) {
            return Objects.equals(((Named) a).value(), ((Named) b).value());
        } else {
            return true;
        }
    }

    public static InjectScope getTypeScope(Class<?> type) {
        if (type.getDeclaredAnnotation(Singleton.class) != null) {
            return InjectScope.SINGLETON;
        }
        return InjectScope.DEFAULT;
    }

    static String printDependencies(final List<InjectType<?>> dependencies, final InjectType<?> target) {
        StringBuilder chainString = new StringBuilder();
        for (InjectType<?> key : dependencies) {
            chainString.append(key.toString()).append(" -> ");
        }
        return chainString.append(target.toString()).toString();
    }
}
