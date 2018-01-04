package com.riguz.forks.ioc;

import javax.inject.Provider;

import java.util.function.Supplier;

public class Producer<T> implements Provider<T> {

    protected Class<T> type;
    protected InjectScope scope;
    protected Supplier<T> provider;

    public Producer(Class<T> type, InjectScope scope, Supplier<T> provider) {
        this.type = type;
        this.scope = scope;
        this.provider = provider;
    }

    public Producer(Class<T> type, Supplier<T> provider) {
        this(type, InjectScope.DEFAULT, provider);
    }

    public Class<T> getType() {
        return type;
    }

    public InjectScope getScope() {
        return scope;
    }

    @Override
    public T get() {
        return this.provider.get();
    }

    @Override
    public String toString() {
        return "[" + type.getSimpleName() + "]" + scope + " scope";
    }
}
