package com.riguz.forks.config.route;

import java.util.Collections;
import java.util.List;

public class ClassGroup {
    private String packageName;
    private List<ClassIdentifier> controllers;

    public ClassGroup(String packageName, List<ClassIdentifier> controllers) {
        this.packageName = packageName;
        this.controllers = Collections.unmodifiableList(controllers);
    }
}
