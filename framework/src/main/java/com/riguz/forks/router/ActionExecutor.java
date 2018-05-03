package com.riguz.forks.router;

import com.riguz.forks.ioc.Injector;
import com.riguz.forks.mvc.RequestContext;
import com.riguz.forks.mvc.RequestHandler;
import com.riguz.forks.mvc.Result;
import com.riguz.forks.mvc.view.JsonResult;

import javax.inject.Inject;

public class ActionExecutor {
    public Result execute(RequestHandler handler, RequestContext context) {
        Object controller = handler.getController();
        System.out.println("->" + controller);
        return new JsonResult<String>("Hello World!");
    }
}
