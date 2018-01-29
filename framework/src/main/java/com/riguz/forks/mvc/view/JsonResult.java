package com.riguz.forks.mvc.view;

import com.riguz.forks.mvc.Result;

public class JsonResult<T> extends Result {

    private static final long serialVersionUID = 3141042849245711550L;

    T data = null;

    public JsonResult(T data) {
        super();
        this.data = data;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
