package com.riguz.forks.ioc;


import com.riguz.gags.base.Classes;
import com.riguz.gags.base.Strings;
import javax.inject.Named;
import java.lang.annotation.Annotation;
import java.util.Objects;

public class Target<T> {
    final Class<T> targetClass;
    final Class<? extends Annotation> qualifier;
    final String name;

    public Target(Class<T> targetClass, Class<? extends Annotation> qualifier, String name) {
        this.targetClass = targetClass;
        this.qualifier = qualifier;
        this.name = name;
    }

    public Target(Class<T> targetClass, Class<? extends Annotation> qualifier) {
        this(targetClass, qualifier, null);
    }

    public Target(Class<T> targetClass, String name) {
        this(targetClass, Named.class, null);
    }

    public Target(Class<T> targetClass) {
        this(targetClass, null, null);
    }

    public static <T> Target<T> of(Class<T> targetClass, Annotation qualifier) {
        if (qualifier == null)
            return new Target<T>(targetClass, null, null);
        return new Target<T>(targetClass, qualifier.annotationType());
    }

    @Override
    public String toString() {
        String remark = "";
        if (!Strings.isNullOrEmpty(this.name)) remark += " name=[" + this.name + "]";
        if (this.qualifier != null) remark += " @<" + this.qualifier.getSimpleName() + ">";
        return this.targetClass.getName() + remark;
    }

    @Override
    public int hashCode() {
        return Classes.hashXorCode(this.targetClass, this.qualifier, this.name);
    }

    @Override
    public boolean equals(Object another) {
        if (this == another) return true;
        if (another == null || this.getClass() != another.getClass()) return false;
        Target<?> o = (Target<?>) another;
        return Objects.equals(this.targetClass, o.targetClass) && Objects.equals(this.qualifier, o.qualifier) && Objects.equals(this.name, o.name);
    }
}
