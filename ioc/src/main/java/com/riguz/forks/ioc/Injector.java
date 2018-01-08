package com.riguz.forks.ioc;

import com.riguz.gags.base.Classes;
import javax.inject.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.LinkedList;
import java.util.List;
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
            for (Method method : Classes.getAllMethods(module.getClass())) {
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
        if (producers.containsKey(injectType)) {
            throw new InjectException("Multi provider found:" + injectType);
        }
        @SuppressWarnings("unchecked")
        Supplier<T> supplier = () -> {
            try {
                return (T) provider.invoke(config, this.createParams(injectType, provider, null));
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
            logger.warn("Concurrent problem detected when binding producer:{}, existing：{}", type, existing);
            return existing;
        }
        logger.debug("Bind {} to producer: {}", type, producer);
        return producer;
    }

    public <T> Provider<T> getProvider(InjectType<T> type) {
        return () -> this.getInstance(type, null);
    }

    public <T> Provider<T> getProvider(Class<T> type) {
        return this.getProvider(InjectType.of(type));
    }

    public <T> T getInstance(Class<T> type) {
        return this.getInstance(InjectType.of(type), null);
    }

    public <T> T getInstance(InjectType<T> type) {
        return this.getInstance(type, null);
    }

    @SuppressWarnings("unchecked")
    private <T> T getInstance(InjectType<T> type, final List<InjectType<?>> dependencies) {
        Producer<T> impl = this.getProducer(type);
        if (impl != null) {
            logger.debug("Getting instance {} from existing producer:{}", type, impl);
            return impl.get();
        }
        InjectScope typeScope = Helper.getTypeScope(type.getType());
        Supplier<T> supplier = () -> constructObject(type, dependencies);
        Producer<T> producer = (InjectScope.SINGLETON == typeScope) ?
            new SingletonProducer<>(type.getType(), supplier) :
            new Producer<>(type.getType(), supplier);
        return (T) bindProducer(type, producer).get();
    }

    private <T> T constructObject(InjectType<T> type, final List<InjectType<?>> dependencies) {
        final Constructor<T> constructor = (Constructor<T>) Helper.getInjectConstructor(type.getType());
        final Object[] paramValues = this.createParams(type, constructor, dependencies);
        try {
            return constructor.newInstance(paramValues);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new InjectException("Failed to new instance of :" + type.getType().getSimpleName(), e);
        }
    }

    private Object[] createParams(final InjectType<?> type, Executable method, final List<InjectType<?>> dependencies) {
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
                final List<InjectType<?>> newDependencies = this.appendDependency(dependencies, type);
                if (newDependencies.contains(paramInjectType)) {
                    throw new InjectException(
                        "Circular dependencies detected:" + Helper.printDependencies(newDependencies, type));
                } else {
                    newDependencies.add(paramInjectType);
                }
                paramValues[i] = this.getInstance(paramInjectType, newDependencies);
            }
        }
        return paramValues;
    }

    private List<InjectType<?>> appendDependency(final List<InjectType<?>> dependencies, InjectType<?> type) {
        List<InjectType<?>> newDependencies = new LinkedList<>();
        if (dependencies != null) {
            newDependencies.addAll(dependencies);
        }
        newDependencies.add(type);
        return newDependencies;
    }

    private final ConcurrentMap<Class<?>, List<InjectField>> injectFieldsCache = new ConcurrentHashMap<>();

    public void injectFields(Object target) {
        List<InjectField> fields = this.injectFieldsCache.get(target.getClass());
        if (fields == null) {
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
                throw new InjectException("Failed to inject field", e);
            }
        }
    }
}
