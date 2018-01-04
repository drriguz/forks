package com.riguz.forks.ioc.old;

import javax.inject.Provider;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

public class InjectField {

    final Field field;
    final boolean isProvider;
    final Target<?> target;

    private InjectField(Field field, Target<?> target) {
        this(field, false, target);
    }

    private InjectField(Field field, boolean isProvider, Target<?> target) {
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
            return new InjectField(field, true, Target.of(provider));
        } else {
            // @Inject
            // private Bar bar;
            return new InjectField(field, Target.of(field));
        }
    }

    public Field getField() {
        return this.field;
    }

    public boolean isProvider() {
        return this.isProvider;
    }

    public Target<?> getTarget() {
        return this.target;
    }

}
