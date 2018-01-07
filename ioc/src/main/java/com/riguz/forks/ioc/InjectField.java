package com.riguz.forks.ioc;

import javax.inject.Provider;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

public class InjectField {

    final Field field;
    final boolean isProvider;
    final InjectType<?> target;

    private InjectField(Field field, InjectType<?> target) {
        this(field, false, target);
    }

    private InjectField(Field field, boolean isProvider, InjectType<?> target) {
        this.field = field;
        this.isProvider = isProvider;
        this.target = target;
    }

    public static InjectField of(Field field) {
        if (field.getType().equals(Provider.class)) {
            // @Inject
            // private Provider<Foo> foo;
            // field = foo; provider = Foo.class
            Class<?> provider = (Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
            return new InjectField(field, true, InjectType.of(provider));
        } else {
            // @Inject
            // private Bar bar;
            return new InjectField(field, InjectType.of(field));
        }
    }

    public Field getField() {
        return this.field;
    }

    public boolean isProvider() {
        return this.isProvider;
    }

    public InjectType<?> getTarget() {
        return target;
    }
}
