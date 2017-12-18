package com.riguz.forks.ioc;

import java.lang.reflect.Field;

public class InjectFieldProperties {
    Field field;
    boolean isProvider = false;
    Target<?> target;

    public InjectFieldProperties(Field field, boolean isProvider, Target<?> target) {
        super();
        this.field = field;
        this.isProvider = isProvider;
        this.target = target;
    }

    public Field getField() {
        return this.field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public boolean isProvider() {
        return this.isProvider;
    }

    public void setProvider(boolean isProvider) {
        this.isProvider = isProvider;
    }

    public Target<?> getTarget() {
        return this.target;
    }

    public void setTarget(Target<?> target) {
        this.target = target;
    }
}
