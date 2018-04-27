package com.riguz.forks.config.route;

import java.util.Arrays;
import java.util.Objects;

public class FunctionCall {
    private final String controller;
    private final String method;
    private final Class<?>[] paramTypes;

    public FunctionCall(String controller, String method, Class<?>[] paramTypes) {
        this.controller = controller;
        this.method = method;
        this.paramTypes = paramTypes;
    }

    public String getController() {
        return controller;
    }

    public String getMethod() {
        return method;
    }

    public Class<?>[] getParamTypes() {
        return paramTypes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FunctionCall that = (FunctionCall) o;
        return Objects.equals(controller, that.controller) &&
                Objects.equals(method, that.method) &&
                Arrays.equals(paramTypes, that.paramTypes);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(controller, method);
        result = 31 * result + Arrays.hashCode(paramTypes);
        return result;
    }

    @Override
    public String toString() {
        return "FunctionCall{" +
                "controller='" + controller + '\'' +
                ", method='" + method + '\'' +
                ", paramTypes=" + Arrays.toString(paramTypes) +
                '}';
    }
}
