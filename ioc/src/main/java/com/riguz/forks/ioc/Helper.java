package com.riguz.forks.ioc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Qualifier;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Helper {

    private static final Logger logger = LoggerFactory.getLogger(Helper.class);

    private Helper() {

    }

    /**
     * Get provider methods, ie. with @Provides annotation
     */
    public static Set<Method> getProviders(final Class<?> type) {
        Set<Method> providers = new HashSet<>();
        for (Method method : type.getMethods()) {
            if (method.isAnnotationPresent(Provides.class)) {
                providers.add(method);
            }
        }
        return providers;
    }

    /**
     * Get qualifier from annotations
     */
    public static Annotation getQualifier(Annotation[] annotations) {
        Annotation qualifier = null;
        for (Annotation a : annotations) {
            if (a.annotationType().isAnnotationPresent(Qualifier.class)) {
                if (qualifier != null) {
                    throw new IllegalArgumentException("Multi-qualifier found");
                }
                qualifier = a;
            }
        }
        return qualifier;
    }

    /**
     * Get all fields of a class include derived fields
     */
    public static Set<Field> getInjectFieldsRecursively(Class<?> type) {
        Class<?> current = type;
        Set<Field> fields = new HashSet<>();
        while (!current.equals(Object.class)) {
            for (Field field : current.getDeclaredFields()) {
                if (field.isAnnotationPresent(Inject.class)) {
                    field.setAccessible(true);
                    fields.add(field);
                }
            }
            current = current.getSuperclass();
        }
        return fields;

    }

    /**
     * Get all fields of a class to be injected(ie. with @Inject annotation)
     */
    public static List<InjectField> getInjectFields(Class<?> type) {
        Set<Field> fields = getInjectFieldsRecursively(type);
        List<InjectField> result = new ArrayList<>();
        for (Field field : fields) {
            result.add(InjectField.of(field));
        }
        return result;
    }

    /**
     * Get singleton annotation from method and class
     */
    public static boolean isSingletonProvider(Method method) {
        return method.getAnnotation(Singleton.class) != null
            || method.getReturnType().getAnnotation(Singleton.class) != null;
    }

    /**
     * Get constructor for injection
     */
    public static Constructor<?> getInjectConstructor(Class<?> type) {
        Constructor<?> injectConstructor = null, noArgConstructor = null;
        for (Constructor<?> c : type.getDeclaredConstructors()) {
            if (c.isAnnotationPresent(Inject.class)) {
                if (injectConstructor != null) {
                    throw new IllegalArgumentException("Multiple inject constructors found in " + type.getName());
                }
                injectConstructor = c;
            }
            if (c.getParameterTypes().length == 0) {
                noArgConstructor = c;
            }
        }
        Constructor<?> targetConstructor = injectConstructor != null ? injectConstructor : noArgConstructor;
        if (targetConstructor == null) {
            throw new IllegalArgumentException(
                "No inject constructor or no-arg constructor found in " + type.getName());
        }
        targetConstructor.setAccessible(true);
        return targetConstructor;
    }

    /**
     * Append dependency to the chain
     */
    static Set<InjectQualifier<?>> appendChain(final Set<InjectQualifier<?>> chain,
        final InjectQualifier<?> newInjectQualifier) {
        Set<InjectQualifier<?>> newChain = new LinkedHashSet<>();
        if (chain != null) {
            newChain.addAll(chain);
        }
        newChain.add(newInjectQualifier);
        return newChain;
    }

    static String printChain(final Set<InjectQualifier<?>> chain, final InjectQualifier<?> injectQualifier) {
        StringBuilder chainString = new StringBuilder();
        for (InjectQualifier<?> key : chain) {
            chainString.append(key.toString()).append(" -> ");
        }
        return chainString.append(injectQualifier.toString()).toString();
    }

    /**
     * Get parameters from providers
     */
    private static Object[] getProviderValues(final Provider<?>[] paramProviders) {
        Object[] params = new Object[paramProviders.length];
        for (int i = 0; i < paramProviders.length; ++i) {
            params[i] = paramProviders[i].get();
        }
        return params;
    }

    /**
     * Create new instance of target
     *
     * @param injectQualifier inject target
     * @param constructor inject constructor
     * @param paramProviders constructor parameter providers
     */
    public static <T> T createNewInstance(final InjectQualifier<T> injectQualifier,
        final Constructor<T> constructor,
        final Provider<?>[] paramProviders) {
        try {
            return constructor.newInstance(Helper.getProviderValues(paramProviders));
        } catch (Exception e) {
            logger.error("Unable to instance object:{}", e);
            throw new RuntimeException("Unable to instance object:" + injectQualifier);
        }
    }

    /**
     * Create new instance from provide method
     */
    public static <T> T createNewInstance(final InjectQualifier<T> injectQualifier,
        Object module,
        Method method,
        final Provider<?>[] paramProviders) {
        // create new singleton instance
        try {
            return (T) method.invoke(module, Helper.getProviderValues(paramProviders));
        } catch (Exception e) {
            logger.error("Unable to instance object:{}", e);
            throw new RuntimeException("Unable to instance object:" + injectQualifier);
        }
    }
}
