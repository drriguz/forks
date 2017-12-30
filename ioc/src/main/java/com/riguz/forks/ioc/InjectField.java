package com.riguz.forks.ioc;

import javax.inject.Provider;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

public class InjectField {

    final Field field;
    final boolean isProvider;
    final InjectQualifier<?> injectQualifier;

    private InjectField(Field field, InjectQualifier<?> injectQualifier) {
        this(field, false, injectQualifier);
    }

    private InjectField(Field field, boolean isProvider, InjectQualifier<?> injectQualifier) {
        this.field = field;
        this.isProvider = isProvider;
        this.injectQualifier = injectQualifier;
    }

    public static InjectField of(Field field) {
        if (field.getType().equals(Provider.class)) {
            // @Inject
            // private Provider<Foo> foo;
            // field = foo; provider = Foo.class
            Class<?> provider = (Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
            return new InjectField(field, true, InjectQualifier.of(provider));
        } else {
            // @Inject
            // private Bar bar;
            return new InjectField(field, InjectQualifier.of(field));
        }
    }

    public Field getField() {
        return this.field;
    }

    public boolean isProvider() {
        return this.isProvider;
    }

    public InjectQualifier<?> getInjectQualifier() {
        return this.injectQualifier;
    }

}
