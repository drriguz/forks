package com.riguz.forks.config.property;

public class Property {
    String name;
    Object value;

    public Property(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Property{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
