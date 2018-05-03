package com.riguz.forks.router;

import com.riguz.forks.config.route.FunctionCall;
import com.riguz.forks.ioc.Injector;
import com.riguz.forks.mvc.RequestContext;
import com.riguz.forks.mvc.RequestHandler;
import com.riguz.forks.mvc.Result;
import com.riguz.forks.mvc.view.JsonResult;

import javax.inject.Inject;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class ActionExecutor {
    public Result execute(RequestHandler handler, RequestContext context) {
        Object controller = handler.getController();
        try {
            Result result = (Result) handler.getAction().invoke(controller,
                    this.bindActionParams(handler.getFunctionCall(), context));
            System.out.println("->" + result);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return new JsonResult<String>("Hello World!");
    }

    private Object[] bindActionParams(FunctionCall functionCall, RequestContext context) {
        List<Object> arauments = new ArrayList<>();
        String[] pathParamNames = functionCall.getParamNames();
        Class<?>[] pathParamTypes = functionCall.getParamTypes();
        for (int i = 0; i < pathParamNames.length; i++) {
            System.out.println(pathParamNames[i]);
            String param = context.getPathVariable(pathParamNames[i]);
            Class<?> paramType = pathParamTypes[i];
            arauments.add(convert(param, paramType));
        }
        return arauments.toArray();
    }

    private static <T> T convert(String argument, Class<T> argumentType) {
        if (argumentType == int.class)
            return (T) Integer.valueOf(argument);
        else if (argumentType == long.class)
            return (T) Long.valueOf(argument);
        else
            return (T) argument;
    }

}
