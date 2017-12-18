package com.riguz.gags.base;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Beans {
    private static final Logger logger = LoggerFactory.getLogger(Beans.class);

    @SuppressWarnings("unchecked")
    public static <T> T newInstance(String className) {
        T instance;
        try {
            Class<?> objectClass = Classes.loadClass(className);
            instance = (T) objectClass.newInstance();
        } catch (Exception e) {
            logger.error("Failed to new instance:{}", e);
            throw new RuntimeException(e);
        }
        return instance;
    }

    public static <T> T newInstance(Class<T> objectClass) {
        T instance;
        try {
            instance = objectClass.newInstance();
        } catch (Exception e) {
            logger.error("Failed to new instance:{}", e);
            throw new RuntimeException(e);
        }
        return instance;
    }

    public static <T> T bind(Class<T> beanClass, String prefix, Map<String, String[]> parameters, String[] fields, boolean errorIfNotExist, boolean errorIfConvertFailed) {
        Object model = newInstance(beanClass);
        if (model == null) throw new IllegalArgumentException("Model class not supported");

        Method[] methods = beanClass.getMethods();
        Map<String, Method> methodDict = new HashMap<>();
        for (Method m : methods)
            methodDict.put(m.getName(), m);

        for (String field : fields) {
            String setterName = "set" + field.substring(0, 1).toUpperCase() + field.substring(1);
            if (!methodDict.containsKey(setterName)) throw new IllegalArgumentException("Specified attribute setter not found:" + field);
            
            Method setter = methodDict.get(setterName);
            Class<?>[] arguments = setter.getParameterTypes();
            if (arguments == null || arguments.length != 1) throw new IllegalArgumentException("Specified attribute setter not valid:" + field);
            
            String paramName = field;
            if (!Strings.isNullOrEmpty(prefix)) paramName = prefix + "." + field;

            if (!parameters.containsKey(field) && errorIfNotExist) throw new RuntimeException("No value found for field[" + field + "] the name is:" + paramName);
            try {
                Object argument = Conversions.parse(arguments[0], parameters.get(paramName)[0]);
                setter.invoke(model, argument);
            } catch (Exception ex) {
                if(errorIfConvertFailed)
                    throw new RuntimeException(ex);
            }
        }
        return (T)model;
    }
}
