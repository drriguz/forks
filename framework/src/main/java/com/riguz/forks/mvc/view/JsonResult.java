package com.riguz.forks.mvc.view;

import com.riguz.forks.mvc.Result;

public class JsonResult extends Result {
    private static final long serialVersionUID = 3141042849245711550L;

    Object data = null;

    public JsonResult(Object data) {
        super(Result.OK);
        this.data = data;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
