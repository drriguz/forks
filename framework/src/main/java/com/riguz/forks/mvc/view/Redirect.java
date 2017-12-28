package com.riguz.forks.mvc.view;

import com.riguz.forks.mvc.Result;

public class Redirect extends Result {
    private static final long serialVersionUID = 3141042849245711550L;

    String path = "";

    public Redirect(String path) {
        super(Result.OK);
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
