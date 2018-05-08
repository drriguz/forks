package com.riguz.forks.mvc;

import com.riguz.forks.http.HttpRequest;
import com.riguz.forks.http.HttpResponse;
import com.riguz.forks.mvc.view.JsonResult;

public class ViewResolver implements Resolver<Result> {

    @Override
    public void resolve(HttpRequest request, HttpResponse response, Result result) {
        if (result == null) {
        } else {
            if (result instanceof JsonResult) {
                JsonResult<?> jsonResult = (JsonResult<?>) result;
            }
            response.writeContent(result.toString());
        }
    }
}
