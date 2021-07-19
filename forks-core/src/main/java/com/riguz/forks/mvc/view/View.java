package com.riguz.forks.mvc.view;

import com.riguz.forks.mvc.Result;

import java.util.HashMap;
import java.util.Map;

public class View extends Result {

    String path;
    Map<String, Object> parameters = new HashMap<>();

    public View(String path) {
        this.path = path;
    }

    public View(String path, Map<String, Object> parameters) {
        this.path = path;
        this.parameters = parameters;
    }

    public String getPath() {
        return this.path;
    }

    public View setPath(String path) {
        this.path = path;
        return this;
    }

    public Map<String, Object> getParameters() {
        return this.parameters;
    }

    public View setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
        return this;
    }

    public View set(String key, Object data) {
        this.parameters.put(key, data);
        return this;
    }
}
