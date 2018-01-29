package com.riguz.forks.mvc.view;

import com.riguz.forks.mvc.Result;

public class Redirect extends Result {

    String path = "";

    public Redirect(String path) {
        super();
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
