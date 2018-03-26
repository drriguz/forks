package com.riguz.forks.config.property;

public class Property {
    String name;
    Object value;

    @Override
    public String toString() {
        return "Property{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
