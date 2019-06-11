package com.riguz.forks.mvc;

import com.riguz.forks.http.HttpRequest;
import com.riguz.forks.http.HttpResponse;
import com.riguz.forks.mvc.view.JsonResult;

public class ResponseResolver implements Resolver<Object> {

    @Override
    public void resolve(HttpRequest request, HttpResponse response, Object result) {
        if (result == null) {
        } else {
            response.writeContent(result.toString());
        }
    }
}
