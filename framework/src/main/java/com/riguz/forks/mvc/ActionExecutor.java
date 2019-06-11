package com.riguz.forks.mvc;

import com.riguz.forks.config.route.FunctionCall;
import com.riguz.forks.exceptions.ActionException;
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
    public Object execute(RequestHandler handler, RequestContext context) throws ActionException {
        Object controller = handler.getController();
        try {
            return handler.getAction().invoke(controller,
                    this.bindActionParams(handler.getFunctionCall(), context));
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new ActionException(e);
        } catch (NumberFormatException ex) {
            throw new ActionException(ex);
        }
    }

    private Object[] bindActionParams(FunctionCall functionCall, RequestContext context) {
        List<Object> arguments = new ArrayList<>();
        String[] pathParamNames = functionCall.getParamNames();
        if(pathParamNames != null) {
            Class<?>[] pathParamTypes = functionCall.getParamTypes();
            for (int i = 0; i < pathParamNames.length; i++) {
                System.out.println(pathParamNames[i]);
                String param = context.getPathVariable(pathParamNames[i]);
                Class<?> paramType = pathParamTypes[i];
                arguments.add(convert(param, paramType));
            }
        }
        return arguments.toArray();
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
