package com.riguz.forks.ioc;

import com.riguz.gags.base.Classes;
import javax.inject.Named;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

public class InjectType<T> {

    private Class<T> type;
    private Annotation qualifier;

    public InjectType(Class<T> type, Annotation qualifier) {
        this.type = type;
        this.qualifier = qualifier;
    }

    public static <T> InjectType<T> of(Class<T> targetClass) {
        /**
         * @Named("foo")
         * class Foo{}
         */
        if (targetClass == null) {
            throw new IllegalArgumentException("Target class should not be null");
        }
        return new InjectType<>(targetClass, Helper.getQualifier(targetClass.getDeclaredAnnotations()));
    }

    public static InjectType<?> of(Field field) {
        /**
         * @Named("foo")
         * @Inject
         * private Foo foo;
         */
        return new InjectType<>(field.getType(), Helper.getQualifier(field.getAnnotations()));
    }

    public static InjectType<?> of(Method provider) {
        /**
         * @Named("foo") Foo foo() { return new Foo(); }
         */
        return new InjectType<>(provider.getReturnType(), Helper.getQualifier(provider));
    }

    @Override
    public boolean equals(Object another) {
        if (another == null || !(another instanceof InjectType)) {
            return false;
        }
        InjectType<?> o = (InjectType<?>) another;
        if (!Objects.equals(this.type, o.type)) {
            return false;
        }
        if (!this.hasQualifier()) {
            return !o.hasQualifier();
        } else {
            if (!o.hasQualifier()) {
                return false;
            }
            if (this.isNamedType()) {
                return Objects.equals(this.getName(), o.getName());
            } else {
                return Objects.equals(this.getQualifierType(), this.getQualifierType());
            }
        }
    }

    private String getName() {
        if (this.isNamedType()) {
            return ((Named) this.qualifier).value();
        }
        return null;
    }

    private boolean hasQualifier() {
        return this.qualifier != null;
    }

    private boolean isNamedType() {
        return this.qualifier != null && this.qualifier.annotationType() == Named.class;
    }

    public Class<?> getQualifierType() {
        return this.hasQualifier() ? this.qualifier.annotationType() : null;
    }

    public Class<T> getType() {
        return this.type;
    }

    public Annotation getQualifier() {
        return qualifier;
    }

    @Override
    public int hashCode() {
        return Classes.hashXorCode(this.type, this.qualifier);
    }

    @Override
    public String toString() {
        String remark = "";
        if (this.qualifier != null) {
            remark += "@" + this.qualifier.annotationType().getSimpleName();
            if (this.qualifier.annotationType() == Named.class) {
                remark += " name=" + ((Named) this.qualifier).value();
            }
        }
        return this.type.getSimpleName() + remark;
    }
}
