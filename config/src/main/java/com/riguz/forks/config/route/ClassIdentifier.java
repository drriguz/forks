package com.riguz.forks.config.route;

import java.util.Objects;

public class ClassIdentifier {
    private String className;
    private String alias;

    private ClassIdentifier() {
    }

    public static ClassIdentifier of(String packageName, String name, String alias) {
        ClassIdentifier classIdentifier = new ClassIdentifier();
        classIdentifier.className = packageName + "." + name;
        classIdentifier.alias = alias != null ? alias : name;
        return classIdentifier;
    }

    public String getClassName() {
        return className;
    }

    public String getAlias() {
        return alias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassIdentifier that = (ClassIdentifier) o;
        return Objects.equals(className, that.className) &&
                Objects.equals(alias, that.alias);
    }

    @Override
    public int hashCode() {

        return Objects.hash(className, alias);
    }
}
