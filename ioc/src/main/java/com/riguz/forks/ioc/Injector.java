package com.riguz.forks.ioc;

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

public class Injector {

    private final ConcurrentMap<Target<?>, Object> singletons = new ConcurrentHashMap<>();
    private final ConcurrentMap<Target<?>, Provider<?>> providers = new ConcurrentHashMap<>();

    private final ConcurrentMap<Class<?>, List<InjectField>> injectFieldsCache = new ConcurrentHashMap<>();

    private final Logger logger = LoggerFactory.getLogger(Injector.class);

    public Injector() {

    }

    public Injector(final Iterable<?> modules) {
        for (final Object module : modules) {
            if (module instanceof Class) {
                throw new InjectException(
                    "Please use an instance instead of Class:" + ((Class<?>) module).getName());
            }
            Set<Method> providesMethods = Helper.getProviders(module.getClass());
            for (Method method : providesMethods) {
                this.addProvidesFromModule(module, method);
            }
        }
    }

    public Injector(Object... modules) {
        this(Arrays.asList(modules));
    }

    public <T> Provider<T> getProvider(final Target<T> target) {
        return this.get(target, null);
    }

    public <T> Provider<T> getProvider(Class<T> type) {
        return this.get(Target.of(type), null);
    }

    /**
     * Get instance by class and qualifier
     */
    public <T> T getInstance(final Target<T> target) {
        return this.getProvider(target).get();
    }

    /**
     * Get instance by type
     */
    public <T> T getInstance(Class<T> type) {
        return this.getProvider(type).get();
    }

    protected void bind(Target<?> target, Provider<?> provider) {
        if (target == null || provider == null) {
            throw new IllegalArgumentException("Qualifier or provider should not be null");
        }
        if (this.providers.containsKey(target)) {
            throw new InjectException("Multiple providers found for:" + target);
        }
        logger.debug("Bind {} to {}", target, provider);
        this.providers.put(target, provider);
    }

    /**
     * Add providers from config module
     *
     * @Bind
     * @Qualifier public Foo foo() { return new Foo(); }
     * @Bind
     * @Singleton public Foo bar() { return new Bar(); }
     * @Bind
     * @Named("foobar") public Foo foobar(Bar bar){ return bar; }
     * @Singleton class Bar{}
     */
    protected void addProvidesFromModule(final Object module, final Method method) {
        final Target<?> target = Target.of(method);
        boolean isSingleton = Helper.isSingletonProvider(method);
        final Provider<?>[] paramProviders = this.createParamProviders(
            target, method, Collections.singleton(target));
        Provider<?> provider = this.createProvider(target, module, method, paramProviders, isSingleton);
        this.bind(target, provider);
    }

    /**
     * Create singleton instance from method
     */
    protected <T> void createSingletonIfNotExists(final Target<T> target, Object module,
        Method method, final Provider<?>[] paramProviders) {
        if (!Injector.this.singletons.containsKey(target)) {
            synchronized (Injector.this.singletons) {
                if (!Injector.this.singletons.containsKey(target)) {
                    T instance = Helper.createNewInstance(target, module, method, paramProviders);
                    Injector.this.singletons.put(target, instance);
                }
            }
        }
    }

    /**
     * Create a provider for instance
     *
     * @param target inject target
     * @param constructor inject constructor
     * @param paramProviders constructor parameter providers
     * @param isSingleton the inject target is singleton
     */
    protected <T> Provider<T> createProvider(final Target<T> target, Constructor<T> constructor,
        final Provider<?>[] paramProviders, boolean isSingleton) {
        return () -> {
            if (!isSingleton) {
                return Helper.createNewInstance(target, constructor, paramProviders);
            }
            if (!this.singletons.containsKey(target)) {
                T instance = Helper.createNewInstance(target, constructor, paramProviders);
                Object i = this.singletons.putIfAbsent(target, instance);
                if (i != null) {
                    instance = (T) i;
                }
                return instance;
            }
            return (T) Injector.this.singletons.get(target);
        };
    }

    /**
     * Create provider from provide method
     */
    protected <T> Provider<T> createProvider(final Target<T> target, Object module, Method method,
        final Provider<?>[] paramProviders, boolean isSingleton) {
        return () -> {
            if (isSingleton) {
                createSingletonIfNotExists(target, module, method, paramProviders);
                return (T) Injector.this.singletons.get(target);
            }
            // or create new instance each time
            return Helper.createNewInstance(target, module, method, paramProviders);
        };
    }

    /**
     * Get or create provider with dependencies
     */
    @SuppressWarnings("unchecked")
    protected <T> Provider<T> get(final Target<T> target,
        final Set<Target<?>> chain) {
        if (this.providers.containsKey(target)) {
            return (Provider<T>) this.providers.get(target);
        }
        final Constructor<T> constructor = (Constructor<T>) Helper.getInjectConstructor(target.targetClass);
        final Provider<?>[] paramProviders = createParamProviders(target, constructor, chain);

        boolean isSingleton = target.targetClass.isAnnotationPresent(Singleton.class);
        return createProvider(target, constructor, paramProviders, isSingleton);
    }

    protected Provider<?>[] createParamProviders(final Target<?> target,
        final Executable providerMethod,
        Set<Target<?>> chain) {
        final Class<?>[] paramClasses = providerMethod.getParameterTypes();
        final Type[] paramTypes = providerMethod.getGenericParameterTypes();
        final Annotation[][] annotations = providerMethod.getParameterAnnotations();

        Provider<?>[] providers = new Provider<?>[paramTypes.length];
        for (int i = 0; i < paramTypes.length; i++) {
            Class<?> paramClass = paramClasses[i];
            Annotation qualifier = Helper.getQualifier(annotations[i]);

            if (Provider.class == paramClass) {
                // if the argument is a provider
                // Foo(@Named("bar") Provider<Bar> bar)
                Class<?> providedType = (Class<?>) ((ParameterizedType) paramTypes[i]).getActualTypeArguments()[0];
                final Target<?> providedTarget = Target.of(providedType, qualifier);
                providers[i] = this.get(providedTarget, null);
            } else {
                // a commom type
                final Target<?> paramTarget = Target.of(paramClass, qualifier);
                final Set<Target<?>> dependencyChain = Helper.appendChain(chain, target);
                if (dependencyChain.contains(paramTarget)) {
                    throw new InjectException("Circular dependency found:" + Helper.printChain(dependencyChain,
                        paramTarget));
                }
                providers[i] = this.get(paramTarget, dependencyChain);
            }
        }
        return providers;
    }

    /**
     * Inject fields with @Inject annotation
     */
    public void injectFields(Object target) {
        List<InjectField> fields;
        if (this.injectFieldsCache.containsKey(target.getClass())) {
            fields = this.injectFieldsCache.get(target.getClass());
        } else {
            fields = Helper.getInjectFields(target.getClass());
            this.injectFieldsCache.putIfAbsent(target.getClass(), fields);
        }
        for (InjectField fieldProperties : fields) {
            Field injectField = fieldProperties.getField();
            Object value = fieldProperties.isProvider ? this.getProvider(fieldProperties.getTarget())
                : this.getInstance(fieldProperties.getTarget());
            try {
                injectField.set(target, value);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
