package com.riguz.forks.ioc;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Supplier;

public class SingletonProducer<T> extends Producer<T> {

    private static final ConcurrentMap<Class<?>, Object> singletons = new ConcurrentHashMap<>(128);

    public SingletonProducer(Class<T> type, Supplier<T> provider) {
        super(type, InjectScope.SINGLETON, provider);
    }

    @Override
    public T get() {
        Object instance = singletons.get(this.type);
        if (instance != null) {
            return (T) instance;
        }
        instance = this.provider.get();
        Object i = singletons.putIfAbsent(this.type, instance);
        if (i != null) {
            instance = i;
        }
        return (T) instance;
    }
}
