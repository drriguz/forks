package com.riguz.forks.ioc;

import javax.inject.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Supplier;

public class Injector {

    private static final Logger logger = LoggerFactory.getLogger(Injector.class);

    private final ConcurrentMap<InjectType<?>, Producer<?>> producers = new ConcurrentHashMap<>(96);

    public Injector(Object... modules) {
        for (Object module : modules) {
            if (module == null) {
                throw new IllegalArgumentException("Invalid null config found");
            }
            for (Method method : module.getClass().getDeclaredMethods()) {
                if (method.isAnnotationPresent(Bind.class)) {
                    InjectType<?> injectType = InjectType.of(method);
                    InjectScope injectScope = InjectScope.of(method);
                    method.setAccessible(true);
                    Producer<?> producer = this.createProducer(injectType, injectScope, module, method);
                    this.bindProducer(injectType, producer);
                }
            }
        }
    }

    private <T> Producer<T> createProducer(InjectType<T> injectType,
        InjectScope injectScope,
        Object config,
        Method provider) {
        @SuppressWarnings("unchecked")
        Supplier<T> supplier = () -> {
            try {
                return (T) provider.invoke(config, this.createParams(provider));
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new InjectException("Failed to invoke method", e);
            }
        };
        return InjectScope.SINGLETON == injectScope ?
            new SingletonProducer<>(injectType.getType(), supplier) :
            new Producer<>(injectType.getType(), supplier);
    }

    @SuppressWarnings("unchecked")
    private <T> Producer<T> getProducer(InjectType<T> type) {
        return (Producer<T>) this.producers.get(type);
    }

    private Producer<?> bindProducer(InjectType<?> type, Producer<?> producer) {
        Producer<?> existing = this.producers.putIfAbsent(type, producer);
        if (existing != null) {
            logger.warn("Concurrent problem detected when binding producer:{}", type);
            return existing;
        }
        logger.debug("Binded {} to producer: {}", type, producer);
        return producer;
    }

    public <T> Provider<T> getProvider(InjectType<T> type) {
        return () -> this.getInstance(type);
    }

    public <T> Provider<T> getProvider(Class<T> type) {
        return getProvider(InjectType.of(type));
    }

    public <T> T getInstance(Class<T> type) {
        return getInstance(InjectType.of(type));
    }

    @SuppressWarnings("unchecked")
    public <T> T getInstance(InjectType<T> type) {
        Producer<T> impl = this.getProducer(type);
        if (impl != null) {
            logger.debug("Getting instance {} from existing producer:{}", type, impl);
            return impl.get();
        }

        InjectScope typeScope = Helper.getTypeScope(type.getType());
        Supplier<T> supplier = () -> constructObject(type.getType());
        Producer<T> producer = (InjectScope.SINGLETON == typeScope) ?
            new SingletonProducer<>(type.getType(), supplier) :
            new Producer<>(type.getType(), supplier);
        return (T) bindProducer(type, producer).get();
    }

    private <T> T constructObject(Class<T> type) {
        final Constructor<T> constructor = (Constructor<T>) Helper.getInjectConstructor(type);
        final Object[] paramValues = this.createParams(constructor);
        try {
            return constructor.newInstance(paramValues);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new InjectException("Failed to new instance of :" + type.getSimpleName(), e);
        }
    }

    private Object[] createParams(Executable method) {
        final Class<?>[] paramTypes = method.getParameterTypes();
        final Type[] paramGenericTypes = method.getGenericParameterTypes();
        final Annotation[][] annotations = method.getParameterAnnotations();

        final Object[] paramValues = new Object[paramTypes.length];
        for (int i = 0; i < paramTypes.length; i++) {
            Class<?> paramType = paramTypes[i];
            if (paramType == Provider.class) {
                Type genericType = paramGenericTypes[i];
                paramValues[i] = (Provider) () -> getInstance(genericType.getClass());
            } else {
                InjectType<?> paramInjectType = InjectType.of(paramType, Helper.getQualifier(annotations[i]));
                paramValues[i] = this.getInstance(paramInjectType);
            }
        }
        return paramValues;
    }
}
