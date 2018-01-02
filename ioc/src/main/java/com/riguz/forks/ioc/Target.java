package com.riguz.forks.ioc;

import com.riguz.gags.base.Classes;
import com.riguz.gags.base.Strings;
import javax.inject.Named;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * eg. @Named Foo foo; @Qualifier Bar bar;
 * @param <T>
 */
public class Target<T> {

    final Class<T> targetClass;
    final Class<? extends Annotation> qualifier;
    final String name;

    private Target(Class<T> targetClass, Class<? extends Annotation> qualifier, String name) {
        this.targetClass = targetClass;
        this.qualifier = qualifier;
        this.name = name;
    }

    public Target(Class<T> targetClass, Class<? extends Annotation> qualifier) {
        this(targetClass, qualifier, null);
    }

    public Target(Class<T> targetClass, String name) {
        this(targetClass, Named.class, name);
    }

    public Target(Class<T> targetClass) {
        this(targetClass, null, null);
    }

    public static <T> Target<T> of(Class<T> targetClass, Annotation qualifier) {
        if (qualifier != null && qualifier.annotationType() == Named.class) {
            String name = ((Named) qualifier).value();
            return new Target<>(targetClass, name);
        }
        return new Target<>(targetClass, qualifier == null ? null : qualifier.annotationType());
    }

    public static <T> Target<T> of(Class<T> targetClass) {
        if (targetClass == null) {
            throw new IllegalArgumentException("Target class should not be null");
        }
        return of(targetClass, Helper.getQualifier(targetClass.getDeclaredAnnotations()));
    }

    public static Target<?> of(Field field) {
        return of(field.getType(), Helper.getQualifier(field.getAnnotations()));
    }

    public static Target<?> of(Method provider) {
        return of(provider.getReturnType(), Helper.getQualifier(provider));
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
        return this.targetClass.getSimpleName() + remark;
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
        if (another == null || !(another instanceof Target)) {
            return false;
        }
        Target<?> o = (Target<?>) another;
        return Objects.equals(this.targetClass, o.targetClass)
            && Objects.equals(this.qualifier, o.qualifier)
            && Objects.equals(this.name, o.name);
    }
}
