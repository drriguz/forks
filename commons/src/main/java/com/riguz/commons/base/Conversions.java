package com.riguz.commons.base;

import javax.activation.UnsupportedDataTypeException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Conversions {

    public static String toString(Object obj) {
        return Conversions.toString(obj, "");
    }

    public static String toString(Object obj, String defaultValue) {
        return obj != null ? String.valueOf(obj) : defaultValue;
    }

    public static double toDouble(String str) {
        if (Strings.isNullOrEmpty(str)) {
            throw new IllegalArgumentException("Empty string");
        }
        return Double.parseDouble(str);
    }

    public static int toInt(String str) {
        if (Strings.isNullOrEmpty(str)) {
            throw new IllegalArgumentException("Empty string");
        }
        return Integer.parseInt(str);
    }

    public static long toLong(String str) {
        if (Strings.isNullOrEmpty(str)) {
            throw new IllegalArgumentException("Empty string");
        }
        return Long.parseLong(str);
    }

    public static float toFloat(String str) {
        if (Strings.isNullOrEmpty(str)) {
            throw new IllegalArgumentException("Empty string");
        }
        return Float.parseFloat(str);
    }

    public static boolean toBoolean(String str) {
        if (Strings.isNullOrEmpty(str)) {
            throw new IllegalArgumentException("Empty string");
        }
        return Boolean.parseBoolean(str);
    }

    public static Date toDate(String str, String format) throws ParseException {
        if (Strings.isNullOrEmpty(str)) {
            throw new IllegalArgumentException("Empty string");
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.parse(str);
    }

    public static Object parse(Class<?> cls, String str) throws UnsupportedDataTypeException, ParseException {
        if (cls == String.class) {
            return str;
        }
        if (cls == Integer.class || cls == int.class) {
            return toInt(str);
        }
        if (cls == Long.class || cls == long.class) {
            return toLong(str);
        }
        if (cls == Float.class || cls == float.class) {
            return toFloat(str);
        }
        if (cls == Double.class || cls == double.class) {
            return toDouble(str);
        }
        if (cls == Boolean.class || cls == boolean.class) {
            return toBoolean(str);
        }
        if (cls == Date.class) {
            return toDate(str, "yyyy-MM-dd");
        }
        throw new UnsupportedDataTypeException("Unsupported type:" + cls);
    }
}
