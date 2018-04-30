package com.riguz.forks.router;

import com.riguz.forks.mvc.RequestContext;
import com.riguz.forks.mvc.RequestHandler;
import com.riguz.forks.mvc.Result;
import com.riguz.forks.mvc.view.JsonResult;

public class ActionExecutor {
    public Result execute(RequestHandler handler, RequestContext context) {

        return new JsonResult<String>("Hello World!");
    }
}
