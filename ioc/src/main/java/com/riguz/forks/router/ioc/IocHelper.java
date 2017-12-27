package com.riguz.forks.router.ioc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Qualifier;
import javax.inject.Singleton;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.*;

public final class IocHelper {
    private static final Logger logger = LoggerFactory.getLogger(IocHelper.class);

    private IocHelper() {

    }

    /**
     * Get provider methods, ie. with @Provides annotation
     *
     * @param type
     * @return
     */
    public static Set<Method> getProviders(final Class<?> type) {
        Set<Method> providers = new HashSet<>();
        for (Method method : type.getMethods()) {
            if (method.isAnnotationPresent(Provides.class))
                providers.add(method);
        }
        return providers;
    }

    /**
     * Get qualifier from annotations
     *
     * @param annotations
     * @return
     */
    public static Annotation getQualifier(Annotation[] annotations) {
        Annotation qualifier = null;
        for (Annotation a : annotations) {
            if (a.annotationType().isAnnotationPresent(Qualifier.class)) {
                if (qualifier != null)
                    throw new IllegalArgumentException("Multi-qualifier found");
                qualifier = a;
            }
        }
        return qualifier;
    }

    /**
     * Get all fields of a class include derived fields
     *
     * @param type
     * @return
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
     *
     * @param type
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static List<InjectFieldProperties> getInjectFieldProperties(Class<?> type) {
        Set<Field> fields = getInjectFieldsRecursively(type);
        List<InjectFieldProperties> result = new ArrayList<>();
        for (Field field : fields) {
            InjectFieldProperties properties = new InjectFieldProperties(field, false, null);
            boolean isProvider = false;
            if (field.getType().equals(Provider.class)) {
                Class<?> provider = (Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
                properties.setProvider(true);
                properties.setTarget(new Target(provider));
            } else {
                properties.setTarget(Target.of(field.getType(), IocHelper.getQualifier(field.getAnnotations())));
            }
            result.add(properties);
        }
        return result;
    }

    /**
     * Get singleton annotation from method and class
     *
     * @param method
     * @return
     */
    public static boolean isSingletonProvider(Method method) {
        return method.getAnnotation(Singleton.class) != null || method.getReturnType().getAnnotation(Singleton.class) != null;
    }

    /**
     * Get constructor for injection
     *
     * @param type
     * @return
     */
    public static Constructor<?> getInjectConstructor(Class<?> type) {
        Constructor<?> injectConstructor = null, noArgConstructor = null;
        for (Constructor<?> c : type.getDeclaredConstructors()) {
            if (c.isAnnotationPresent(Inject.class)) {
                if (injectConstructor != null)
                    throw new IllegalArgumentException("Multiple inject constructors found in " + type.getName());
                injectConstructor = c;
            }
            if (c.getParameterTypes().length == 0)
                noArgConstructor = c;
        }
        Constructor<?> targetConstructor = injectConstructor != null ? injectConstructor : noArgConstructor;
        if (targetConstructor == null)
            throw new IllegalArgumentException("No inject constructor or no-arg constructor found in " + type.getName());
        targetConstructor.setAccessible(true);
        return targetConstructor;
    }

    /**
     * Append dependency to the chain
     *
     * @param chain
     * @param newTarget
     * @return
     */
    public static Set<Target<?>> appendChain(final Set<Target<?>> chain, final Target<?> newTarget) {
        Set<Target<?>> newChain = new LinkedHashSet<>();
        if (chain != null)
            newChain.addAll(chain);
        newChain.add(newTarget);
        return newChain;
    }

    public static String printChain(final Set<Target<?>> chain, Target<?> target) {
        StringBuilder chainString = new StringBuilder();
        for (Target<?> key : chain)
            chainString.append(key.toString()).append(" -> ");
        return chainString.append(target.toString()).toString();
    }

    /**
     * Get parameters from providers
     *
     * @param paramProviders
     * @return
     */
    private static Object[] getParams(Provider<?>[] paramProviders) {
        Object[] params = new Object[paramProviders.length];
        for (int i = 0; i < paramProviders.length; ++i)
            params[i] = paramProviders[i].get();
        return params;
    }

    /**
     * Create new instance of target
     *
     * @param target         inject target
     * @param constructor    inject constructor
     * @param paramProviders constructor parameter providers
     * @param <T>
     * @return
     */
    public static <T> T createNewInstance(final Target<T> target, Constructor<T> constructor, final Provider<?>[] paramProviders) {
        try {
            return constructor.newInstance(IocHelper.getParams(paramProviders));
        } catch (Exception e) {
            logger.error("Unable to instance object:{}", e);
            throw new RuntimeException("Unable to instance object:" + target.name);
        }
    }

    /**
     * Create new instance from provide method
     *
     * @param target
     * @param module
     * @param method
     * @param paramProviders
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T createNewInstance(final Target<T> target, Object module, Method method, final Provider<?>[] paramProviders) {
        // create new singleton instance
        try {
            return (T) method.invoke(module, IocHelper.getParams(paramProviders));
        } catch (Exception e) {
            logger.error("Unable to instance object:{}", e);
            throw new RuntimeException("Unable to instance object:" + target.name);
        }
    }
}
