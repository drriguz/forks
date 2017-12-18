package com.riguz.gags.base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Classes {
    private static final Logger logger = LoggerFactory.getLogger(Classes.class);

    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    public static String getClassPath() {
        String classpath = "";
        URL resource = getClassLoader().getResource("");
        if (resource != null) {
            classpath = resource.getPath();
        }
        return classpath;
    }

    public static Class<?> loadClass(String className) {
        return loadClass(className, true);
    }

    public static Class<?> loadClass(String className, boolean isInitialized) {
        Class<?> cls;
        try {
            cls = Class.forName(className, isInitialized, getClassLoader());
        } catch (ClassNotFoundException e) {
            logger.error("Failed to load class:{}", e);
            throw new RuntimeException(e);
        }
        return cls;
    }

    public static Method getMethod(Class<?> theClass, String methodName, Class<?>... paramClasses) {
        try {
            if (paramClasses != null) 
                return theClass.getMethod(methodName, paramClasses);
            else
                return theClass.getMethod(methodName);
        } catch (NoSuchMethodException e) {
            //e.printStackTrace();
        } catch (SecurityException e) {
            //e.printStackTrace();
        }
        return null;
    }

    public static <T> int hashXorCode(Object... args) {
        int hashCode = 0;
        for (Object o : args)
            hashCode = hashCode ^ (o == null ? 0 : o.hashCode());
        return hashCode;
    }
    
    @SuppressWarnings("unchecked")
    public static <T> T invokeMethod(Method method, Object instance, Object[] objects) throws IllegalAccessException, InvocationTargetException {
        method.setAccessible(true);
        return (T)method.invoke(instance, objects);
    }
}
