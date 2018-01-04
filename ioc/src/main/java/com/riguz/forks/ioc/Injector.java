package com.riguz.forks.ioc;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Supplier;

public class Injector {

    private final ConcurrentMap<InjectType<?>, Producer<?>> producers = new ConcurrentHashMap<>(96);

    public Injector(final Iterable<? extends InjectConfig> modules) {
        for (final InjectConfig module : modules) {
            if (module == null) {
                throw new IllegalArgumentException("Invalid null config found");
            }
            for (Method method : module.getClass().getMethods()) {
                if (method.isAnnotationPresent(Bind.class)) {

                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    private <T> Producer<T> getCachedProducer(InjectType<T> type) {
        return (Producer<T>) this.producers.get(type);
    }

    @SuppressWarnings("unchecked")
    private <T> Producer<T> cacheProducer(InjectType<T> type, Producer<T> producer) {
        Producer<?> existing = this.producers.putIfAbsent(type, producer);
        if (existing != null) {
            return (Producer<T>) existing;
        }
        return producer;
    }

    private <T> void bind(InjectType<T> type, Producer<? extends T> producer) {
        this.producers.putIfAbsent(type, producer);
    }

    public <T> T getInstance(InjectType<T> type) {
        Producer<T> impl = this.getCachedProducer(type);
        if (impl != null) {
            return impl.get();
        }

        InjectScope typeScope = Helper.getTypeScope(type.getType());
        Supplier<T> supplier = () -> constructObject(type.getType());
        Producer<T> producer = (InjectScope.SINGLETON == typeScope) ?
            new SingletonProducer<>(type.getType(), supplier) :
            new Producer<>(type.getType(), supplier);
        return cacheProducer(type, producer).get();
    }

    private <T> T constructObject(Class<T> type) {
        final Constructor<T> constructor = (Constructor<T>) Helper.getInjectConstructor(type);
        try {
            return constructor.newInstance();
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new InjectException("Failed to new instance of :" + type.getSimpleName(), e);
        }
    }
}
