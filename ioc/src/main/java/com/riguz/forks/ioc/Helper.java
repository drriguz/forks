package com.riguz.forks.ioc;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;

import com.riguz.gags.base.Arrays;
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
            if (method.isAnnotationPresent(Bind.class)) {
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

    public static Annotation getQualifier(Method providerMethod) {
        return getQualifier(Arrays.concat(
            providerMethod.getReturnType().getDeclaredAnnotations(),
            providerMethod.getDeclaredAnnotations()));
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
    public static boolean isSingletonProvider(Method provider) {
        return provider.getAnnotation(Singleton.class) != null
            || provider.getReturnType().getAnnotation(Singleton.class) != null;
    }

    /**
     * Get constructor for injection
     */
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

    /**
     * Append dependency to the chain
     */
    static Set<Target<?>> appendChain(final Set<Target<?>> chain,
        final Target<?> newTarget) {
        Set<Target<?>> newChain = new LinkedHashSet<>();
        if (chain != null) {
            newChain.addAll(chain);
        }
        newChain.add(newTarget);
        return newChain;
    }

    static String printChain(final Set<Target<?>> chain, final Target<?> target) {
        StringBuilder chainString = new StringBuilder();
        for (Target<?> key : chain) {
            chainString.append(key.toString()).append(" -> ");
        }
        return chainString.append(target.toString()).toString();
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
     * @param target inject target
     * @param constructor inject constructor
     * @param paramProviders constructor parameter providers
     */
    public static <T> T createNewInstance(final Target<T> target,
        final Constructor<T> constructor,
        final Provider<?>[] paramProviders) {
        try {
            return constructor.newInstance(Helper.getProviderValues(paramProviders));
        } catch (Exception e) {
            logger.error("Unable to instance object:{}", e);
            throw new InjectException("Unable to instance object:" + target);
        }
    }

    /**
     * Create new instance from provide method
     */
    public static <T> T createNewInstance(final Target<T> target,
        Object module,
        Method method,
        final Provider<?>[] paramProviders) {
        // create new singleton instance
        try {
            return (T) method.invoke(module, Helper.getProviderValues(paramProviders));
        } catch (Exception e) {
            logger.error("Unable to instance object:{}", e);
            throw new InjectException("Unable to instance object:" + target);
        }
    }
}
