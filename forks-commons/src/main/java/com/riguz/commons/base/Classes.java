package com.riguz.commons.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.*;

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
            if (paramClasses != null) {
                return theClass.getMethod(methodName, paramClasses);
            } else {
                return theClass.getMethod(methodName);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int hashXorCode(Object... args) {
        int hashCode = 0;
        for (Object o : args) {
            hashCode = hashCode ^ (o == null ? 0 : o.hashCode());
        }
        return hashCode;
    }

    @SuppressWarnings("unchecked")
    public static <T> T invokeMethod(Method method, Object instance, Object[] objects)
        throws IllegalAccessException, InvocationTargetException {
        method.setAccessible(true);
        return (T) method.invoke(instance, objects);
    }

    public static Set<Method> getAllMethods(Class<?> target) {
        Set<Method> methods = new LinkedHashSet<>();
        Collections.addAll(methods, target.getMethods());

        Map<Object, Set<Package>> types = new HashMap<>();
        final Set<Package> pkgIndependent = Collections.emptySet();
        for (Method m : methods) {
            types.put(methodKey(m), pkgIndependent);
        }
        for (Class<?> current = target; current != null; current = current.getSuperclass()) {
            for (Method m : current.getDeclaredMethods()) {
                final int mod = m.getModifiers(),
                    access = Modifier.PUBLIC | Modifier.PROTECTED | Modifier.PRIVATE;
                if (!Modifier.isStatic(mod)) {
                    switch (mod & access) {
                        case Modifier.PUBLIC:
                            continue;
                        default:
                            Set<Package> pkg =
                                types.computeIfAbsent(methodKey(m), key -> new HashSet<>());
                            if (pkg != pkgIndependent && pkg.add(current.getPackage())) {
                                break;
                            } else {
                                continue;
                            }
                        case Modifier.PROTECTED:
                            if (types.putIfAbsent(methodKey(m), pkgIndependent) != null) {
                                continue;
                            }
                            // otherwise fall-through
                        case Modifier.PRIVATE:
                    }
                }
                methods.add(m);
            }
        }
        return methods;
    }

    private static Object methodKey(Method m) {
        return java.util.Arrays.asList(m.getName(),
            MethodType.methodType(m.getReturnType(), m.getParameterTypes()));
    }
}
