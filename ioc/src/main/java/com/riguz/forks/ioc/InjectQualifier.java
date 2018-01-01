package com.riguz.forks.ioc;

import com.riguz.gags.base.Classes;
import com.riguz.gags.base.Strings;
import javax.inject.Named;
import javax.inject.Qualifier;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

public class InjectQualifier<T> {

    final Class<T> targetClass;
    final Class<? extends Annotation> qualifier;
    final String name;

    private InjectQualifier(Class<T> targetClass, Class<? extends Annotation> qualifier, String name) {
        this.targetClass = targetClass;
        this.qualifier = qualifier;
        this.name = name;
    }

    public InjectQualifier(Class<T> targetClass, Class<? extends Annotation> qualifier) {
        this(targetClass, qualifier, null);
    }

    public InjectQualifier(Class<T> targetClass, String name) {
        this(targetClass, Named.class, name);
    }

    public InjectQualifier(Class<T> targetClass) {
        this(targetClass, null, null);
    }

    public static <T> InjectQualifier<T> of(Class<T> targetClass, Annotation qualifier) {
        if (qualifier != null && qualifier.getClass() == Named.class) {
            String name = ((Named) qualifier).value();
            return new InjectQualifier<>(targetClass, name);
        }
        return new InjectQualifier<>(targetClass, qualifier == null ? null : qualifier.getClass());
    }

    public static <T> InjectQualifier<T> of(Class<T> targetClass) {
        if (targetClass == null) {
            throw new IllegalArgumentException("Target class should not be null");
        }
        return of(targetClass, Helper.getQualifier(targetClass.getDeclaredAnnotations()));
    }

    public static InjectQualifier<?> of(Field field) {
        return of(field.getType(), Helper.getQualifier(field.getAnnotations()));
    }

    public static InjectQualifier<?> of(Method provider) {
        Class<?> returnType = provider.getReturnType();
        Annotation classQualifier = Helper.getQualifier(returnType);
        Annotation providerQualifier = Helper.getQualifier(provider);
        return of(provider.getReturnType(), Helper.getQualifier(provider.getAnnotations()));
    }

    @Override
    public String toString() {
        String remark = "";
        if (!Strings.isNullOrEmpty(this.name)) {
            remark += " name=[" + this.name + "]";
        }
        if (this.qualifier != null) {
            remark += " @<" + this.qualifier.getSimpleName() + ">";
        }
        return this.targetClass.getName() + remark;
    }

    @Override
    public int hashCode() {
        return Classes.hashXorCode(this.targetClass, this.qualifier, this.name);
    }

    @Override
    public boolean equals(Object another) {
        if (this == another) {
            return true;
        }
        if (another == null || !(another instanceof InjectQualifier)) {
            return false;
        }
        InjectQualifier<?> o = (InjectQualifier<?>) another;
        return Objects.equals(this.targetClass, o.targetClass)
            && Objects.equals(this.qualifier, o.qualifier)
            && Objects.equals(this.name, o.name);
    }
}
