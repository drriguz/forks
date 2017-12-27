package com.riguz.forks.router.ioc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Provider;
import javax.inject.Singleton;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class IocContext {
    private final ConcurrentMap<Target<?>, Object> singletons = new ConcurrentHashMap<>();
    private final ConcurrentMap<Target<?>, Provider<?>> providers = new ConcurrentHashMap<>();
    private final ConcurrentMap<Class<?>, List<InjectFieldProperties>> injectFieldsCache = new ConcurrentHashMap<>();

    private final Logger logger = LoggerFactory.getLogger(IocContext.class);

    public IocContext() {

    }

    public IocContext(final Iterable<?> modules) {
        for (final Object module : modules) {
            if (module instanceof Class)
                throw new IllegalArgumentException("Please use an instance instead of Class:" + ((Class<?>) module).getName());
            Set<Method> providesMethods = IocHelper.getProviders(module.getClass());
            for (Method method : providesMethods)
                this.addProvidesFromModule(module, method);
        }
    }

    public IocContext(Object... modules) {
        this(Arrays.asList(modules));
    }

    @SuppressWarnings("unchecked")
    public <T> Provider<T> getProvider(final Target<T> target) {
        return this.getOrCreateProvider(target, null);
    }

    public <T> Provider<T> getProvider(Class<T> type) {
        return this.getOrCreateProvider(new Target<T>(type), null);
    }

    /**
     * Get instance by class and qualifier
     *
     * @param target
     * @param <T>
     * @return
     */
    public <T> T getInstance(final Target<T> target) {
        return this.getOrCreateProvider(target, null).get();
    }

    /**
     * Get instance by type
     *
     * @param type
     * @param <T>
     * @return
     */
    public <T> T getInstance(Class<T> type) {
        return this.getOrCreateProvider(new Target<T>(type), null).get();
    }

    /**
     * Add providers from config module
     *
     * @param module
     * @param method
     */
    protected void addProvidesFromModule(final Object module, final Method method) {
        final Target<?> target = Target.of(method.getReturnType(), IocHelper.getQualifier(method.getAnnotations()));
        if (this.providers.containsKey(target))
            throw new RuntimeException("Multiple providers found for:" + target.toString());
        boolean isSingleton = IocHelper.isSingletonProvider(method);
        final Provider<?>[] paramProviders = this.createParamProviders(target, method, Collections.singleton(target));
        Provider<?> provider = this.createProvider(target, module, method, paramProviders, isSingleton);
        this.providers.put(target, provider);
    }

    /**
     * Create singleton instance
     *
     * @param target
     * @param constructor
     * @param paramProviders
     * @param <T>
     */
    protected <T> void createSingletonIfNotExists(final Target<T> target, Constructor<T> constructor, final Provider<?>[] paramProviders) {
        if (!this.singletons.containsKey(target)) {
            synchronized (this.singletons) {
                if (!this.singletons.containsKey(target)) {
                    T instance = IocHelper.createNewInstance(target, constructor, paramProviders);
                    this.singletons.put(target, instance);
                }
            }
        }
    }

    /**
     * Create singleton instance from method
     *
     * @param target
     * @param module
     * @param method
     * @param paramProviders
     * @param <T>
     */
    protected <T> void createSingletonIfNotExists(final Target<T> target, Object module, Method method, final Provider<?>[] paramProviders) {
        if (!IocContext.this.singletons.containsKey(target)) {
            synchronized (IocContext.this.singletons) {
                if (!IocContext.this.singletons.containsKey(target)) {
                    T instance = IocHelper.createNewInstance(target, module, method, paramProviders);
                    IocContext.this.singletons.put(target, instance);
                }
            }
        }
    }

    /**
     * Create a provider for instance
     *
     * @param target         inject target
     * @param constructor    inject constructor
     * @param paramProviders constructor parameter providers
     * @param isSingleton    the inject target is singleton
     * @param <T>
     * @return
     */
    protected <T> Provider<T> createProvider(final Target<T> target, Constructor<T> constructor, final Provider<?>[] paramProviders, boolean isSingleton) {
        return new Provider<T>() {
            @SuppressWarnings("unchecked")
            @Override
            public T get() {
                if (isSingleton) {
                    createSingletonIfNotExists(target, constructor, paramProviders);
                    return (T) IocContext.this.singletons.get(target);
                }
                return IocHelper.createNewInstance(target, constructor, paramProviders);
            }
        };
    }

    /**
     * Create provider from provide method
     *
     * @param target
     * @param module
     * @param method
     * @param paramProviders
     * @param isSingleton
     * @param <T>
     * @return
     */
    protected <T> Provider<T> createProvider(final Target<T> target, Object module, Method method, final Provider<?>[] paramProviders, boolean isSingleton) {
        return new Provider<T>() {
            @SuppressWarnings("unchecked")
            @Override
            public T get() {
                if (isSingleton) {
                    createSingletonIfNotExists(target, module, method, paramProviders);
                    return (T) IocContext.this.singletons.get(target);
                }
                // or create new instance each time
                return IocHelper.createNewInstance(target, module, method, paramProviders);
            }
        };
    }

    /**
     * Get or create provider with dependencies
     *
     * @param target
     * @param chain
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    protected <T> Provider<T> getOrCreateProvider(final Target<T> target, final Set<Target<?>> chain) {
        if (this.providers.containsKey(target))
            return (Provider<T>) this.providers.get(target);
        final Constructor<T> constructor = (Constructor<T>) IocHelper.getInjectConstructor(target.targetClass);
        final Provider<?>[] paramProviders = createParamProviders(target, constructor, chain);

        boolean isSingleton = target.targetClass.isAnnotationPresent(Singleton.class);
        return createProvider(target, constructor, paramProviders, isSingleton);
    }


    /**
     * Create providers for inject constructor
     *
     * @param target       inject class, eg. A(B b, C c)
     * @param paramClasses constructor parameter classes, eg.[B.class, C.class]
     * @param paramTypes   constructor parameter generic types, eg.[A, B]
     * @param annotations  constructor parameter annotations, eg[@Singleton, ...]
     * @param chain        targets to be injected, eg. [A, B, C]
     * @return
     */
    protected Provider<?>[] createParamProviders(final Target<?> target,
                                                 final Class<?>[] paramClasses,
                                                 final Type[] paramTypes,
                                                 final Annotation[][] annotations,
                                                 final Set<Target<?>> chain) {
        Provider<?>[] providers = new Provider<?>[paramTypes.length];
        for (int i = 0; i < paramTypes.length; i++) {
            Class<?> paramClass = paramClasses[i];
            Annotation qualifier = IocHelper.getQualifier(annotations[i]);

            if (Provider.class.equals(paramClass)) {
                // if the argument is a provider
                Class<?> providerType = (Class<?>) ((ParameterizedType) paramTypes[i]).getActualTypeArguments()[0];
                final Target<?> newTarget = Target.of(providerType, qualifier);
                providers[i] = this.getOrCreateProvider(newTarget, null);
            } else {
                // a commom type
                final Target<?> paramTarget = Target.of(paramClass, qualifier);
                final Set<Target<?>> dependencyChain = IocHelper.appendChain(chain, target);
                if (dependencyChain.contains(paramTarget))
                    throw new RuntimeException("Circular dependency found:" + IocHelper.printChain(dependencyChain, paramTarget));
                providers[i] = this.getOrCreateProvider(paramTarget, dependencyChain);
            }
        }
        return providers;
    }

    protected Provider<?>[] createParamProviders(final Target<?> target, final Method providerMethod, Set<Target<?>> chain) {
        return createParamProviders(target,
                providerMethod.getParameterTypes(),
                providerMethod.getGenericParameterTypes(),
                providerMethod.getParameterAnnotations(),
                chain);
    }

    protected Provider<?>[] createParamProviders(final Target<?> target, final Constructor<?> providerMethod, Set<Target<?>> chain) {
        return createParamProviders(target,
                providerMethod.getParameterTypes(),
                providerMethod.getGenericParameterTypes(),
                providerMethod.getParameterAnnotations(),
                chain);
    }

    /**
     * Inject fields with @Inject annotation
     *
     * @param target
     */
    public void injectFields(Object target) {
        List<InjectFieldProperties> fields = null;
        if (this.injectFieldsCache.containsKey(target.getClass())) {
            fields = this.injectFieldsCache.get(target.getClass());
        } else {
            fields = IocHelper.getInjectFieldProperties(target.getClass());
            this.injectFieldsCache.putIfAbsent(target.getClass(), fields);
        }
        for (InjectFieldProperties fieldProperties : fields) {
            Field injectField = fieldProperties.getField();
            Object value = fieldProperties.isProvider ? this.getProvider(fieldProperties.getTarget()) : this.getInstance(fieldProperties.getTarget());
            try {
                injectField.set(target, value);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
